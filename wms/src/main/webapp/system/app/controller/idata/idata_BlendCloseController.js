/**
 * 模块名称：混合板验收作业（单品验收）
 * 模块编码：4501
 * 创建：hcx
 */
var g_ICAcrossAreaBox4501='';//不同储区的商品是否验收到同一物流箱容器上: 1允许;0：不允许]
//var g_ICFreezeRate4501='';//达到报警比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
//var g_ICAlarmRate4501='';//达到冻结比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
var g_ICCheckPickCell4501='';//存储验收时是否需要校验拣货位;0----不校验；1----校验
var g_ICCheckQpalette4501='';//进货验收是否要判断商品标准堆叠:1----需要; 0---- 不需要
var g_strIsCanEdit4501=false;
var flag='0'; //用于选择行号，0为不选择，1为要选择
var articleNoFlag='';
var packingQtyFlag='';
var produceDateFlag='';
var expireDateFlag='';
var temperatureFlag='';
Ext.define('cms.controller.idata.idata_BlendCloseController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.idata.idata_BlendCloseUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//设置光标跳到下一输入框
			'idata_BlendCloseUI field':{
				specialkey:this.boxkeydown
			},//刷新
			'idata_BlendCloseUI button[id=refresh4501]':{
				click:this.refresh
			},//货主选择
			'combo[id=owner4501]':{
				select:this.ownerSelect
			},//供应商选择
			'wms_DefFieldValCombo[id=suppliers4501]':{
				select:this.suppliersSelect
			},//单据类型选择
			'wms_DefFieldValCombo[id=importType4501]':{
				beforequery:this.importTypeBeforeQuery,
				select:this.importTypeSelect
			},//扫描码头号
			'idata_BlendCloseUI textfield[id=cmbDockNo4501]':{
				blur:this.cmbDockNo4501Blur
			},//扫描预约流水号
			'idata_BlendCloseUI textfield[id=orderSerial4501]':{
				blur:this.orderSerial4501Blur
			},//采购单号选择
			'remoteCombo[id=poNo4501]':{
				beforequery:this.poNoBeforeQuery,
				select:this.poNoSelect
			},//助记码/条码选择加载单据列表
			'remoteCombo[id=identifierOrBarcode4501]':{
				beforequery:this.identifierOrBarcode1BeforeQuery,
				select:this.identifierOrBarcode1Select
			},//助记码/条码选择
			'remoteCombo[id=articleIdentifier4501]':{
				beforequery:this.articleIdentifierBeforeQuery,
				select:this.articleIdentifierSelect
			},//单据 列表Grid单击
			'idata_BlendCloseUI grid[id=grid4501_2]':{
				selectionchange:this.grid4501_2Selectionchange			
			},//商品信息 Grid单击
			'idata_BlendCloseUI grid[id=grid4501_1]':{
				selectionchange:this.grid4501_1Selectionchange			
			},//生产日期选择
			'idata_BlendCloseUI datefield[id=dateProduceDate4501]':
			{
				select:this.dateProduceDate4501Change
			},//有效期选择
			'idata_BlendCloseUI datefield[id=dateExpireDate4501]':
			{
				select:this.dateExpireDate4501Change
			},//校验箱数
			'idata_BlendCloseUI numberfield[id=numplanBox4501]':
			{
				blur:this.numplanBox4501Check
			},//校验中包数
			'idata_BlendCloseUI numberfield[id=numplanQmin4501]':
			{
				blur:this.numplanQmin4501Check
			},//校验散数
			'idata_BlendCloseUI numberfield[id=numplanDis4501]':
			{
				blur:this.numplanDis4501Check
			},//保存
			'idata_BlendCloseUI button[id=save4501]':{
				click:this.save
			},//TAB页切换
			'idata_BlendCloseUI tabpanel[id=tabPid4501]':{
				tabchange:this.tabPidchange
			},//品质选择
			'idata_BlendCloseUI wms_DefFieldValCombo[id=quality4501]':{
				beforequery:this.quality4501BeforeQuery
			}
			
		});
	},
	//界面初始化
	initializtion:function(){
		disableButtonFunc(1,'#save4501',$i18n.save);
		Ext.getCmp('cmbDockNo4501').focus(false, 10);
		
		//显示变量0为不显示，1为显示
		var planBox4501=commonGetModuleField('4501','planBox')[0].flag;
		var planQmin4501=commonGetModuleField('4501','planQmin')[0].flag;
		var planDis4501=commonGetModuleField('4501','planDis')[0].flag;
		var packingUnit4501=commonGetModuleField('4501','packingUnit')[0].flag;
		var packingSpec4501=commonGetModuleField('4501','packingSpec')[0].flag;
		
		var numplanBox4501=commonGetModuleField('4501','planBox')[0].flag;
		var numplanQmin4501=commonGetModuleField('4501','planQmin')[0].flag;
		var numplanDis4501=commonGetModuleField('4501','planDis')[0].flag;

		if(planBox4501==0){
			Ext.getCmp('planBox4501').setVisible(false);
		}
		if(planQmin4501==0){
			Ext.getCmp('planQmin4501').setVisible(false);
		}
		if(planDis4501==0){
			Ext.getCmp('planDis4501').setVisible(false);
		}
		if(packingUnit4501==0){
			Ext.getCmp('packingUnit4501').setVisible(false);
		}
		if(packingSpec4501==0){
			Ext.getCmp('packingSpec4501').setVisible(false);
		}
		if(numplanBox4501==0){
			Ext.getCmp('numplanBox4501').setVisible(false);
		}
		if(numplanQmin4501==0){
			Ext.getCmp('numplanQmin4501').setVisible(false);
		}
		if(numplanDis4501==0){
			Ext.getCmp('numplanDis4501').setVisible(false);
		}
	},
	//设置光标跳到下一输入框
	boxkeydown:function(th,e,eOpts){
	  	if (e.getKey() == e.ENTER) {
	  		if(th.id=='cmbWorkerNo4501'){
				Ext.getCmp('owner4501').focus();
	  		}else if(th.id=='orderSerial4501'){
	  			this.orderSerial4501Blur();
	  		}else if(th.id=='dateProduceDate4501'){
	  			this.dateProduceDate4501Change(th);
	  			Ext.getCmp('dateExpireDate4501').focus(false, 4);
	  		}else if(th.id=='dateExpireDate4501'){
	  			this.dateExpireDate4501Change(th);
	  			var data = Ext.getCmp('grid4501_1').getSelectionModel().getSelection();
	  			if(data[0].get("lotType")=='4' || data[0].get("lotType")=='2'){
	  				commonSetMsterReadOnlyByArray(
							new Array('txtLotNo4501'),true);
	  				if(data[0].get("temperatureFlag")=='1'){
						Ext.getCmp('temperature4501').focus(false, 6);
					}else{
						Ext.getCmp('numplanBox4501').focus(false, 7);
					}
	  				
	  			}else{
	  				Ext.getCmp('txtLotNo4501').focus(false, 5);
	  			}
	  		}else if(th.id=='txtLotNo4501'){
	  			var data = Ext.getCmp('grid4501_1').getSelectionModel().getSelection();
	  			if(data[0].get("temperatureFlag")=='1'){
					Ext.getCmp('temperature4501').focus(false, 6);
				}else{
					Ext.getCmp('numplanBox4501').focus(false, 7);
				}
	  		}else if(th.id=='numplanQmin4501'){
	  			if(Ext.getCmp('numplanDis4501').hidden==true){
	  				Ext.getCmp('quality4501').focus(false, 7);
	  			}else{
	  				Ext.getCmp('numplanDis4501').focus(false, 7);
	  			}
	  		}
	  		else if(th.id=='quality4501'){
	  				Ext.getCmp('save4501').focus(false, 7);
	  		}else{
		  		th.nextSibling().focus();	  		
	  		}
	  		
        }
	},
	 //刷新
	 refresh:function(){
	    disableButtonFunc(1,'#save4501',$i18n.save);
	    Ext.getCmp('owner4501').setValue('');
	    Ext.getCmp('owner4501').getStore().removeAll();
	    Ext.getCmp('owner4501').getStore().reload();
	    Ext.getCmp('suppliers4501').setValue('');
	    Ext.getCmp('suppliers4501').getStore().removeAll();
	    Ext.getCmp('suppliers4501').getStore().reload();
	    Ext.getCmp('importType4501').setValue('');
	    Ext.getCmp('importType4501').getStore().removeAll();
	    Ext.getCmp('importType4501').getStore().reload();
	    Ext.getCmp('orderSerial4501').setValue('');
	    Ext.getCmp('poNo4501').setValue('');
		Ext.getCmp('poNo4501').focus(false, 10);
		Ext.getCmp("identifierOrBarcode4501").setValue('');
		Ext.getCmp("articleIdentifier4501").setValue('');
		Ext.getCmp("dateProduceDate4501").setValue('');
		Ext.getCmp("dateExpireDate4501").setValue('');
		Ext.getCmp("txtLotNo4501").setValue('');
		Ext.getCmp("temperature4501").setValue('');
		Ext.getCmp("numplanBox4501").setValue('');
		Ext.getCmp("numplanQmin4501").setValue('');
		Ext.getCmp("numplanDis4501").setValue('');
 	  	Ext.getCmp('grid4501_1').getStore().removeAll();
 	  	Ext.getCmp('grid4501_2').getStore().removeAll();
 	  },
 	  
 	 quality4501BeforeQuery:function(){
 		var data = Ext.getCmp('grid4501_2').getSelectionModel().getSelection();
 		var str = {
				strOwnerNo : data[0].data.ownerNo,
				str : data[0].data.poType
		};
		Ext.apply(Ext.getCmp('quality4501').getStore().proxy.extraParams,str);
		Ext.getCmp('quality4501').getStore().removeAll();
		Ext.getCmp('quality4501').getStore().load();
 	 },
 	  
 	 //货主选择
 	 ownerSelect:function(){
 		var listDetail1  =  [];
		var strDtl = {
			columnId:'a.owner_no',
			value:Ext.getCmp("owner4501").getValue()
		};
		listDetail1.push(strDtl);
		
		var wheresql={
				str:Ext.encode(listDetail1)
			};
		Ext.apply(Ext.getCmp('suppliers4501').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('suppliers4501').getStore().removeAll();
 		Ext.getCmp('suppliers4501').getStore().load();
		
 		getSystemPara4501(Ext.getCmp("owner4501").getValue());
  	  }, 
  	importTypeBeforeQuery:function(){
  		var listDetail1  =  [];
  		if(!Ext.isEmpty(Ext.getCmp('owner4501').getValue())){
  			var strDtl = {
  					columnId:'a.owner_no',
  					value:Ext.getCmp("owner4501").getValue()
  				};
  				listDetail1.push(strDtl);
		}
  		var params={
				str:Ext.encode(listDetail1)
		};
		Ext.apply(Ext.getCmp('importType4501').getStore().proxy.extraParams,params);
		Ext.getCmp('importType4501').getStore().removeAll();
		Ext.getCmp('importType4501').getStore().load();
  	}, 
  	importTypeSelect:function(){
  		var listDetail1  =  [];
  		if(!Ext.isEmpty(Ext.getCmp('owner4501').getValue())){
  			var strDtl = {
  					columnId:'a.owner_no',
  					value:Ext.getCmp("owner4501").getValue()
  				};
  				listDetail1.push(strDtl);
		}
		
		if(!Ext.isEmpty(Ext.getCmp('suppliers4501').getValue())){
			var strDt2 = {
					columnId:'m.supplier_no',
					value:Ext.getCmp("suppliers4501").getValue()
				};
				listDetail1.push(strDt2);
		}
		if(!Ext.isEmpty(Ext.getCmp('importType4501').getValue())){
			var strDt3 = {
					columnId:'a.import_type',
					value:Ext.getCmp("importType4501").getValue()
				};
				listDetail1.push(strDt3);
		}
		var params={
				strWheresql:null,
				str:Ext.encode(listDetail1)
		};
		Ext.apply(Ext.getCmp('grid4501_2').getStore().proxy.extraParams,params);
		Ext.getCmp('grid4501_2').getStore().removeAll();
		Ext.getCmp('grid4501_2').getStore().load();
		
  	},
  	//供应商选择
  	suppliersSelect:function(){
  		var listDetail1  =  [];
  		if(!Ext.isEmpty(Ext.getCmp('owner4501').getValue())){
  			var strDtl = {
  					columnId:'a.owner_no',
  					value:Ext.getCmp("owner4501").getValue()
  				};
  				listDetail1.push(strDtl);
		}
		
		if(!Ext.isEmpty(Ext.getCmp('suppliers4501').getValue())){
			var strDt2 = {
					columnId:'m.supplier_no',
					value:Ext.getCmp("suppliers4501").getValue()
				};
				listDetail1.push(strDt2);
		}
		if(!Ext.isEmpty(Ext.getCmp('importType4501').getValue())){
			var strDt3 = {
					columnId:'a.import_type',
					value:Ext.getCmp("importType4501").getValue()
				};
				listDetail1.push(strDt3);
		}
		var params={
				strWheresql:Ext.getCmp("poNo4501").getValue(),
				str:Ext.encode(listDetail1)
		};
		var wheresql={
				strWheresql:Ext.getCmp("identifierOrBarcode4501").getValue(),   
				str:Ext.encode(listDetail1)
			};
		var str={
				strWheresql:null,
				str:Ext.encode(listDetail1)
			};
		Ext.getCmp('orderSerial4501').setValue('');
		Ext.getCmp('poNo4501').setValue('');
		Ext.apply(Ext.getCmp('poNo4501').getStore().proxy.extraParams,params);
		Ext.getCmp('poNo4501').getStore().removeAll();
		Ext.getCmp('poNo4501').getStore().load();
		
		Ext.getCmp('identifierOrBarcode4501').setValue('');
		Ext.apply(Ext.getCmp('identifierOrBarcode4501').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('identifierOrBarcode4501').getStore().removeAll();
		Ext.getCmp('identifierOrBarcode4501').getStore().load();
		
		Ext.apply(Ext.getCmp('grid4501_2').getStore().proxy.extraParams,str);
		Ext.getCmp('grid4501_2').getStore().removeAll();
		Ext.getCmp('grid4501_2').getStore().load();
		
		Ext.getCmp('grid4501_1').getStore().removeAll();
		
		Ext.getCmp('identifierOrBarcode4501').setValue('');

  	},
  	//扫描扫描台号
  	cmbDockNo4501Blur:function(){
  		if(Ext.isEmpty(Ext.getCmp('cmbDockNo4501').getValue()))
  		{
  			return;
  		}
  		//校验扫描台号
  		var params = {
  				str : Ext.getCmp('cmbDockNo4501').getValue(),
  			};
  		Ext.Ajax.request({
  			method:'POST',
  			url:'odata_ArrangePackAction_checkDock',
  			params:params,
  			success:function(response){
  				var data = Ext.decode(response.responseText);
  				if(!data.isSucc){
  					var audio = document.createElement("audio");
  					audio.src="system/music/a.mp3";
  					audio.play();
  					Ext.Msg.alert('提示',data.msg);
  					Ext.getCmp('cmbDockNo4501').setValue("");
  					Ext.getCmp('cmbDockNo4501').focus(false, 10);
  				}
  			}
  		});		
  	},
  //扫描预约流水号
  	orderSerial4501Blur:function(){
  		var listDetail1  =  [];
  		var listDetail2  =  [];
  		if(!Ext.isEmpty(Ext.getCmp('owner4501').getValue())){
  			var strDtl = {
  					columnId:'a.owner_no',
  					value:Ext.getCmp("owner4501").getValue()
  				};
  				listDetail1.push(strDtl);
  				listDetail2.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('suppliers4501').getValue())){
			var strDt2 = {
					columnId:'m.supplier_no',
					value:Ext.getCmp("suppliers4501").getValue()
				};
				listDetail1.push(strDt2);
				listDetail2.push(strDt2);
		}
		if(!Ext.isEmpty(Ext.getCmp('orderSerial4501').getValue())){
			var strDt3 = {
					columnId:'a.serial_no',
					value:Ext.getCmp('orderSerial4501').getValue()
				};
			var strDt4 = {
					columnId:'iim.serial_no',
					value:Ext.getCmp('orderSerial4501').getValue()
				};
				listDetail1.push(strDt3);
				listDetail2.push(strDt4);
		}
  		//校验预约流水号
  		var params = {
  				str : Ext.encode(listDetail1)
  			};
  		var str = {
  				str : Ext.encode(listDetail2)
  			};
  		Ext.Ajax.request({
  			method:'POST',
  			url:'idata_CheckAction_checkOrderSerial',
  			params:params,
  			success:function(response){
  				var data = Ext.decode(response.responseText);
  				if(!data.isSucc){
  					var audio = document.createElement("audio");
  					audio.src="system/music/a.mp3";
  					audio.play();
  					Ext.Msg.alert('提示',data.msg);
  					Ext.getCmp('orderSerial4501').setValue("");
  					Ext.getCmp('orderSerial4501').focus(false, 10);
  				}else{
  					Ext.getCmp("identifierOrBarcode4501").setValue('');
  					Ext.getCmp("poNo4501").setValue('');
  					Ext.apply(Ext.getCmp('grid4501_2').getStore().proxy.extraParams,str);
  					Ext.getCmp('grid4501_2').getStore().removeAll();
  					Ext.getCmp('grid4501_2').getStore().load();
  				}
  			}
  		});		
  	},
  	 //助记码/条码选择
  	articleIdentifierBeforeQuery:function(){
  		var data = Ext.getCmp('grid4501_2').getSelectionModel().getSelection();
		if(data.length!=0){
			var listDetail1  =  [];
	 		var strDtl = {
	 			columnId:'a.owner_no',
	 			value:Ext.getCmp("owner4501").getValue()
	 		};
	 		listDetail1.push(strDtl);
	 		var strDt2 = {
	 				columnId:'a.po_no',
	 				value:data[0].get('poNo')
	 			};
	 		listDetail1.push(strDt2);
	 		var params={
	 				str:Ext.encode(listDetail1)
	 			};
	 		Ext.apply(Ext.getCmp('articleIdentifier4501').getStore().proxy.extraParams,params);
	 		Ext.getCmp('articleIdentifier4501').getStore().removeAll();
	 		Ext.getCmp('articleIdentifier4501').getStore().load();
		}
   	 },
   	articleIdentifierSelect:function(){
   		var gridcount=Ext.getCmp("grid4501_1").getStore().getCount();
   		if(gridcount>0){
   			for(var i=0;i<gridcount;i++){
   	   	   		var data = Ext.getCmp('grid4501_1').getStore().getAt(i);
   	   	        if(data.length!=0){
                    if(data.get('barcode')==Ext.getCmp("articleIdentifier4501").getValue()){
            	        if(data.get('noCheckQty')==0){
        				    Ext.example.msg($i18n.prompt,$i18n_prompt.noCheckQty);
        				    Ext.getCmp("articleIdentifier4501").setValue('');
        				    Ext.getCmp('articleIdentifier4501').focus(false, 10);
        				    return;
        			     }else{
        				    Ext.getCmp('grid4501_1').getSelectionModel().select(i);
        				    Ext.getCmp('checkQty4501').focus(false, 10);
        				    disableButtonFunc(0,'#save4501',$i18n.save);

        			     }
                    }
  		        }
   			 }
   		}
   		
  	 },
 	  //采购单号选择
  	 poNoBeforeQuery:function(){
  		var listDetail1  =  [];
  		if(!Ext.isEmpty(Ext.getCmp('owner4501').getValue())){
  			var strDtl = {
  					columnId:'a.owner_no',
  					value:Ext.getCmp("owner4501").getValue()
  				};
  				listDetail1.push(strDtl);
		}
		
		if(!Ext.isEmpty(Ext.getCmp('suppliers4501').getValue())){
			var strDt2 = {
					columnId:'a.supplier_no',
					value:Ext.getCmp("suppliers4501").getValue()
				};
				listDetail1.push(strDt2);
		}
		
		var params={
			strWheresql:Ext.getCmp("poNo4501").getValue(),   
			str:Ext.encode(listDetail1)
		};
		Ext.apply(Ext.getCmp('poNo4501').getStore().proxy.extraParams,params);
		Ext.getCmp('poNo4501').getStore().removeAll();
		Ext.getCmp('poNo4501').getStore().load();
  	 },
 	 poNoSelect:function(){
 		var listDetail1  =  [];
 		if(!Ext.isEmpty(Ext.getCmp('owner4501').getValue())){
 			var strDtl = {
 					columnId:'a.owner_no',
 					value:Ext.getCmp("owner4501").getValue()
 				};
 				listDetail1.push(strDtl);
		}
		
		var strDt2 = {
				columnId:'a.po_no',
				value:Ext.getCmp("poNo4501").getValue()
			};
		listDetail1.push(strDt2);
		if(!Ext.isEmpty(Ext.getCmp('suppliers4501').getValue())){
			var strDt3 = {
					columnId:'m.supplier_no',
					value:Ext.getCmp("suppliers4501").getValue()
				};
			listDetail1.push(strDt3);
		}
		if(!Ext.isEmpty(Ext.getCmp('importType4501').getValue())){
			var strDt4 = {
					columnId:'a.import_type',
					value:Ext.getCmp("importType4501").getValue()
				};
				listDetail1.push(strDt4);
		}
		var params={
				strWheresql:'',   
				str:Ext.encode(listDetail1)
			};
		Ext.apply(Ext.getCmp('grid4501_2').getStore().proxy.extraParams,params);
		Ext.getCmp('grid4501_2').getStore().removeAll();
		Ext.getCmp('grid4501_2').getStore().load();
		Ext.getCmp("identifierOrBarcode4501").setValue('');
		Ext.getCmp("orderSerial4501").setValue('');
		Ext.getCmp('grid4501_1').getStore().removeAll();
		Ext.getCmp("articleIdentifier4501").setValue('');

 	 },
  	 //助记码/条码选择加载单据列表  
 	identifierOrBarcode1BeforeQuery:function(){
 		var listDetail1  =  [];
 		if(!Ext.isEmpty(Ext.getCmp('owner4501').getValue())){
 			var strDtl = {
 					columnId:'a.owner_no',
 					value:Ext.getCmp("owner4501").getValue()
 				};
 				listDetail1.push(strDtl);
		}
		
		if(!Ext.isEmpty(Ext.getCmp('suppliers4501').getValue())){
			var strDt2 = {
					columnId:'m.supplier_no',
					value:Ext.getCmp("suppliers4501").getValue()
				};
			listDetail1.push(strDt2);
		}
		if(!Ext.isEmpty(Ext.getCmp('importType4501').getValue())){
			var strDt4 = {
					columnId:'a.import_type',
					value:Ext.getCmp("importType4501").getValue()
				};
				listDetail1.push(strDt4);
		}
		var params={
				strWheresql:Ext.getCmp("identifierOrBarcode4501").getValue(),   
				str:Ext.encode(listDetail1)
			};
		Ext.apply(Ext.getCmp('identifierOrBarcode4501').getStore().proxy.extraParams,params);
		Ext.getCmp('identifierOrBarcode4501').getStore().removeAll();
		Ext.getCmp('identifierOrBarcode4501').getStore().load();
  	  },
  	identifierOrBarcode1Select:function(){
  		var listDetail1  =  [];
  		if(!Ext.isEmpty(Ext.getCmp('owner4501').getValue())){
  			var strDtl = {
  					columnId:'a.owner_no',
  					value:Ext.getCmp("owner4501").getValue()
  				};
  				listDetail1.push(strDtl);
		}
		
		if(!Ext.isEmpty(Ext.getCmp('suppliers4501').getValue())){
			var strDt2 = {
					columnId:'m.supplier_no',
					value:Ext.getCmp("suppliers4501").getValue()
				};
			listDetail1.push(strDt2);
		}
		if(!Ext.isEmpty(Ext.getCmp('importType4501').getValue())){
			var strDt4 = {
					columnId:'a.import_type',
					value:Ext.getCmp("importType4501").getValue()
				};
				listDetail1.push(strDt4);
		}
		var params={
				strWheresql:Ext.getCmp("identifierOrBarcode4501").getValue(),   
				str:Ext.encode(listDetail1)
			};
		
		Ext.apply(Ext.getCmp('grid4501_2').getStore().proxy.extraParams,params);
		Ext.getCmp('grid4501_2').getStore().removeAll();
		Ext.getCmp('grid4501_2').getStore().load();
		Ext.getCmp("poNo4501").setValue('');
		Ext.getCmp("orderSerial4501").setValue('');
		Ext.getCmp('grid4501_1').getStore().removeAll();
		Ext.getCmp("articleIdentifier4501").setValue('');
   		var gridcount=Ext.getCmp("grid4501_2").getStore().getCount();
   		if(gridcount>0){
			Ext.getCmp('grid4501_2').getSelectionModel().select(0);
   		}

  	 },
  	 //单据列表选择
  	 grid4501_2Selectionchange:function(){
		var data = Ext.getCmp('grid4501_2').getSelectionModel().getSelection();
		if(data.length!=0){
			var listDetail1  =  [];
	 		var strDtl = {
	 			columnId:'a.owner_no',
	 			value:data[0].get('ownerNo')
	 		};
	 		listDetail1.push(strDtl);
	 		var strDt2 = {
	 				columnId:'a.po_no',
	 				value:data[0].get('poNo')
	 			};
	 		listDetail1.push(strDt2);
	 		var params={
	 				str:Ext.encode(listDetail1)
	 			};
	 		Ext.apply(Ext.getCmp('grid4501_1').getStore().proxy.extraParams,params);
	 		Ext.getCmp('grid4501_1').getStore().removeAll();
	 		Ext.getCmp('grid4501_1').getStore().load();
		}
  	 },
  	 //选择商品填充助记码/条码
  	 grid4501_1Selectionchange:function(){
		var data = Ext.getCmp('grid4501_1').getSelectionModel().getSelection();
		if(data.length!=0){
		/*	if(data[0].get('noCheckQty')==0){
				Ext.example.msg($i18n.prompt,$i18n_prompt.noCheckQty);
				Ext.getCmp("articleIdentifier4501").setValue('');
				Ext.getCmp('articleIdentifier4501').focus(false, 10);
				Ext.getCmp("dateProduceDate4501").setValue('');
				Ext.getCmp("dateExpireDate4501").setValue('');
				Ext.getCmp("txtLotNo4501").setValue('');
				Ext.getCmp("temperature4501").setValue('');
				Ext.getCmp("numplanBox4501").setValue('');
				Ext.getCmp("numPoPsc4501").setValue('');
				return;
			}else{*/
				Ext.getCmp("articleIdentifier4501").setValue(data[0].get('articleText'));
				Ext.getCmp("dateProduceDate4501").setValue(data[0].get('produceDateText'));
				Ext.getCmp("dateExpireDate4501").setValue(data[0].get('expireDateText'));
				Ext.getCmp("txtLotNo4501").setValue(data[0].get('lotNo'));
				Ext.getCmp("numplanBox4501").setValue('');
				Ext.getCmp("numplanQmin4501").setValue('');
				Ext.getCmp("numplanDis4501").setValue('');
				this.quality4501BeforeQuery();
				Ext.getCmp("qpalette4501").setValue(data[0].get('qpaletteText'));
				if(data[0].get("lotType")=='1'){//只管批次
					commonSetMsterReadOnlyByArray(
							new Array('dateProduceDate4501','dateExpireDate4501'),true);
					commonSetMsterReadOnlyByArray(
							new Array('txtLotNo4501'),false);
					Ext.getCmp('txtLotNo4501').focus(false, 5);
				}else if(data[0].get("lotType")=='2'){//只管生产日期
					commonSetMsterReadOnlyByArray(
							new Array('txtLotNo4501'),true);
					commonSetMsterReadOnlyByArray(
							new Array('dateProduceDate4501','dateExpireDate4501'),false);
					Ext.getCmp('dateProduceDate4501').focus(false, 3);
				}else if(data[0].get("lotType")=='4'){//都不管
					commonSetMsterReadOnlyByArray(
							new Array('dateProduceDate4501','dateExpireDate4501',
									'txtLotNo4501'),true);
					if(data[0].get("temperatureFlag")=='1'){
						Ext.getCmp('temperature4501').focus(false, 6);
					}else{
						Ext.getCmp('numplanBox4501').focus(false, 7);
					}
					
				}else{
					commonSetMsterReadOnlyByArray(
							new Array('dateProduceDate4501','dateExpireDate4501',
									'txtLotNo4501'),false);
					Ext.getCmp('dateProduceDate4501').focus(false, 3);
				}
				if(data[0].get("temperatureFlag")=='1'){
					commonSetMsterReadOnlyByArray(
							new Array('temperature4501'),false);
					Ext.getCmp('temperature4501').setVisible(true);
					
				}else {
					commonSetMsterReadOnlyByArray(
							new Array('temperature4501'),true);
					Ext.getCmp('temperature4501').setVisible(false);
				}
				disableButtonFunc(0,'#save4501',$i18n.save);
			//}
		}
  	 },
  	//生产日期选择
  	dateProduceDate4501Change:function(th)
 	{
  		var data = Ext.getCmp('grid4501_1').getSelectionModel().getSelection();
  		if(data.length>0){
  			if(data[0].get("lotType")=='2'||data[0].get("lotType")=='3'){
  				if(Ext.Date.format(th.getValue(),'Y-m-d')>Ext.Date.format(new Date(),'Y-m-d'))
  				{
  	  				Ext.getCmp('dateProduceDate4501').setValue(null);
  	  				Ext.getCmp('dateProduceDate4501').focus(false, 3);
  					Ext.example.msg($i18n.prompt,$i18n_prompt.productTimeCannotMoreThanToday);
  					return;
  				}
  	  			if(!Ext.isEmpty(th.getValue()) && data[0].get('expiryDays')!=-1)
  	  	 		{
  	  	 			Ext.getCmp('dateExpireDate4501').setValue(Ext.Date.format(Ext.Date.add(new Date(th.getValue()), Ext.Date.DAY, data[0].get('expiryDays')*1),'Y-m-d'));	
  	  	 		}		
  	  	 		if(data[0].get('lotType') == '3'){
  	  	 			if(!Ext.isEmpty(Ext.getCmp('dateProduceDate4501').getValue())){
  	  	 				txtLotNo4501();
  	  	 		    }else{
  	  	 		        Ext.example.msg($i18n.prompt,$i18n_prompt.inputProductDate);
  	  	 	        }
  	  	 	     }
  	  	 		//checkDate4501(th,data);
  	  		}
  		} 		
 	},
 	//有效期至选择
 	dateExpireDate4501Change:function(th)
 	{
  		    var data = Ext.getCmp('grid4501_1').getSelectionModel().getSelection();
  		    if(data.length>0){
  		    	if(data[0].get("lotType")=='2'||data[0].get("lotType")=='3'){
  		    		if(Ext.Date.format(Ext.Date.add(new Date(th.getValue()), Ext.Date.DAY, data[0].get('expiryDays')*-1),'Y-m-d')
  							>Ext.Date.format(new Date(),'Y-m-d'))
  						{
  	  		    		    Ext.getCmp('dateProduceDate4501').setValue(null);
  	  		    		    Ext.getCmp('dateExpireDate4501').setValue(null);
  	  	  				    Ext.getCmp('dateExpireDate4501').focus(false, 3);
  							Ext.example.msg($i18n.prompt, $i18n_prompt.productTimeCannotMoreThanToday);
  						}
  	  		    	if(!Ext.isEmpty(th.getValue()) && data[0].get('expiryDays')!=-1)
  	  	 			{
  	  	 				Ext.getCmp('dateProduceDate4501').setValue(Ext.Date.format(Ext.Date.add(new Date(th.getValue()), Ext.Date.DAY, data[0].get('expiryDays')*-1),'Y-m-d'));
  	  	 			}
  	  	 			if(!Ext.isEmpty(Ext.getCmp('dateProduceDate4501').getValue()) && data[0].get('lotType') == '3'){
  	  	 				txtLotNo4501();
  	  	 			}
  	  	 			//checkDate4501(th,data);
  	  		    }
  		    }
 	},
 	//箱数校验
 	numplanBox4501Check:function(){
 		var record  = Ext.getCmp('grid4501_1').getSelectionModel().getSelection();
//
//		if(Ext.isEmpty(Ext.getCmp('numplanBox4501').getValue()))
//		{
//			Ext.getCmp('numplanBox4501').setValue('0');
//			Ext.getCmp('numplanBox4501').focus(false, 7);
//		}
		if(g_ICCheckPickCell4501==1){
			if(!Ext.isEmpty(Ext.getCmp('numplanBox4501').getValue()))
			{
				if(Ext.getCmp('numplanBox4501').getValue()>0 || Ext.getCmp('numplanQmin4501').getValue()>0 || Ext.getCmp('numplanDis4501').getValue()>0)
				{
					if(record[0].get('boxPickType')=='' && record[0].get('disPickType')=='')
					{
						Ext.getCmp('numplanBox4501').setValue(0);
						Ext.getCmp('numplanQmin4501').setValue(0);
						Ext.getCmp('numplanDis4501').setValue(0);
						Ext.example.msg($i18n.prompt,$i18n_prompt.notSetPickLocateAndNotAllowAcceptance);
					}
				}
			}
		}
		if(g_ICCheckQpalette4501==1)
		{
			if(!Ext.isEmpty(Ext.getCmp('numplanBox4501').getValue()))
			{
				if(Ext.isEmpty(Ext.getCmp('numplanBox4501').getValue())>0)
				{
					if(record[0].get('qpalette')==0)
					{
						Ext.getCmp('numplanBox4501').setValue(0);
						Ext.getCmp('numplanQmin4501').setValue(0);
						Ext.getCmp('numplanDis4501').setValue(0);
						Ext.example.msg($i18n.prompt, $i18n_prompt.notSetStandardsStackNotAllowAcceptance);
					}
				}
			}
		}
		
 	},
 	
 	numplanQmin4501Check:function(){

 		var record  = Ext.getCmp('grid4501_1').getSelectionModel().getSelection();
/*
		if(Ext.isEmpty(Ext.getCmp('numplanQmin4501').getValue()))
		{
			Ext.getCmp('numplanQmin4501').setValue('0');
			Ext.getCmp('numplanQmin4501').focus(false, 7);
		}*/
		if(g_ICCheckPickCell4501==1){
			if(!Ext.isEmpty(Ext.getCmp('numplanQmin4501').getValue()))
			{
				if(Ext.getCmp('numplanBox4501').getValue()>0 || Ext.getCmp('numplanQmin4501').getValue()>0 || Ext.getCmp('numplanDis4501').getValue()>0)
				{
					if(record[0].get('boxPickType')=='' && record[0].get('disPickType')=='')
					{
						Ext.getCmp('numplanBox4501').setValue(0);
						Ext.getCmp('numplanQmin4501').setValue(0);
						Ext.getCmp('numplanDis4501').setValue(0);
						Ext.example.msg($i18n.prompt,$i18n_prompt.notSetPickLocateAndNotAllowAcceptance);
					}
				}
			}
		}
		if(g_ICCheckQpalette4501==1)
		{
			if(!Ext.isEmpty(Ext.getCmp('numplanQmin4501').getValue()))
			{
				if(Ext.isEmpty(Ext.getCmp('numplanQmin4501').getValue())>0)
				{
					if(record[0].get('qpalette')==0)
					{
						Ext.getCmp('numplanBox4501').setValue(0);
						Ext.getCmp('numplanQmin4501').setValue(0);
						Ext.getCmp('numplanDis4501').setValue(0);
						Ext.example.msg($i18n.prompt, $i18n_prompt.notSetStandardsStackNotAllowAcceptance);
					}
				}
			}
		}
		
 	
 	},
 	
 	//散数校验
 	numplanDis4501Check:function(){
 		var record  = Ext.getCmp('grid4501_1').getSelectionModel().getSelection();

		
 		if(g_ICCheckPickCell4501==1){
			if(!Ext.isEmpty(Ext.getCmp('numplanDis4501').getValue()))
			{
				if(Ext.getCmp('numplanBox4501').getValue()>0 || Ext.getCmp('numplanQmin4501').getValue()>0 || Ext.getCmp('numplanDis4501').getValue()>0)
				{
					if(record[0].get('boxPickType')=='' && record[0].get('disPickType')=='')
					{
						Ext.getCmp('numplanBox4501').setValue(0);
						Ext.getCmp('numplanQmin4501').setValue(0);
						Ext.getCmp('numplanDis4501').setValue(0);
						Ext.example.msg($i18n.prompt,$i18n_prompt.notSetPickLocateAndNotAllowAcceptance);
					}
				}
			}
		}
		if(g_ICCheckQpalette4501==1)
		{
			if(!Ext.isEmpty(Ext.getCmp('numplanDis4501').getValue()))
			{
				if(Ext.isEmpty(Ext.getCmp('numplanDis4501').getValue())>0)
				{
					if(record[0].get('qpalette')==0)
					{
						Ext.getCmp('numplanBox4501').setValue(0);
						Ext.getCmp('numplanQmin4501').setValue(0);
						Ext.getCmp('numplanDis4501').setValue(0);
						Ext.example.msg($i18n.prompt, $i18n_prompt.notSetStandardsStackNotAllowAcceptance);
					}
				}
			}
		}
		
 	},
	//保存
 	 save:function(){
 		if(Ext.isEmpty(workSpaceNo)){
			Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
			return;
		}
 		if(!commonCheckIsInputAll('form_01_4501'))
		{
			return;
		}
 		var data = Ext.getCmp('grid4501_2').getSelectionModel().getSelection();
    	if(data.length==0){
    		Ext.example.msg($i18n.prompt,$i18n_prompt.tableCannotBeNull);
    		return;
    	}
 		var strSImportNo= data[0].get('SImportNo');
		var strOwnerNo=data[0].get('ownerNo');
		
		var record  = Ext.getCmp('grid4501_1').getSelectionModel().getSelection();	
		var articleNo=record[0].get('articleNo');
		var IdentifierBarcode=Ext.getCmp('articleIdentifier4501').getValue();
		var packingQty=record[0].get('packingQty');
		var qminOperatePacking=record[0].get('qminOperatePacking');
		var produceDate=Ext.getCmp('dateProduceDate4501').getValue();
		var expireDate=Ext.getCmp('dateExpireDate4501').getValue();
		var lotNo=Ext.getCmp('txtLotNo4501').getValue();
		var checkQty1=Ext.getCmp('numplanBox4501').getValue()*packingQty+Ext.getCmp('numplanQmin4501').getValue()*qminOperatePacking+Ext.getCmp('numplanDis4501').getValue();
	     var   checkQty=checkQty1.toFixed(5);
		if(Ext.isEmpty(IdentifierBarcode)){
			Ext.example.msg($i18n.prompt,$i18n_prompt.inputIdentifierOrBarcode);
            return;
        }
		if(Ext.isEmpty(produceDate)){
			Ext.example.msg($i18n.prompt,$i18n_prompt.inputProductDate);
            return;
        }
        if(Ext.isEmpty(expireDate)){
        	Ext.example.msg($i18n.prompt,$i18n_prompt.inputEndDate);
            return;
        }
        if(Ext.isEmpty(lotNo)){
        	Ext.example.msg($i18n.prompt,$i18n_prompt.inputLotNo);
            return;
        }
        if(checkQty==0||Ext.isEmpty(lotNo)){
        	Ext.example.msg($i18n.prompt,$i18n_prompt.inputQty);
            return;
        }
        var d=
			{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.get('warehouseNo').getValue(),
				ownerNo:strOwnerNo,
				articleNo:articleNo,
				packingQty:packingQty,
				barcode:(record[0].get('barcode')=="" ||record[0].get('barcode')==undefined)?'N':record[0].get('barcode'),
				produceDate:produceDate==undefined? '1900-01-01':produceDate,
				expireDate:expireDate==undefined? '1900-01-01':expireDate,
				checkQty:checkQty,
			};
	        var strJsonDetail1 = Ext.encode(d);
			var str = 
			{
				strSImportNo:strSImportNo,	
				strJsonDetail1:strJsonDetail1,
			};
			var s=record[0].get('qpalette');
			   //校验验收数量是否大于板堆叠量，给提示
	        if(checkQty>parseFloat(record[0].get('qpalette'))){
	        	Ext.Msg.confirm($i18n.prompt,"验收量大于板堆叠量,是否强制验收？",function(button, text){
	    			if (button == 'no') {
	    				return;
	    			}else if (button == 'yes') {
						//校验报警天数和冻结比率、超量等
						Ext.Ajax.request({
							method:'POST',
							url:'idata_CheckAction_tscCheckExists.action',
							params:str,
							success:function(response){
								var data = Ext.decode(response.responseText);
								if(data.isSucc){
									//promptType;提示类型1：报警 2冻结 3超量
								    //promptFlag;处理方式1：提示类型（0:成功 ;1：拦截，不允许验入；2：提醒，但可强制验入；3：授权 ）
								  if (data.obj.promptFlag == '1')
					                {
					                    if (data.obj.promptType == '1')
					                    {
					                    	Ext.Msg.alert($i18n.prompt,"商品处于报警状态!");
					                        return;
					                    }
					                    else if (data.obj.promptType == '2')
					                    {
					                        Ext.Msg.alert($i18n.prompt,"商品处于冻结状态!");
					                        return;
					                    }
					                    else if(data.obj.promptType == '3')
					                    {
					                    	Ext.Msg.alert($i18n.prompt,"商品验收量已超出商品的验收超量比率!");
					                        return;
					                    }
					                }
					                else if (data.obj.promptFlag == '2')
					                {
					                    if (data.obj.promptType == '1')
					                    {
					                    	Ext.Msg.confirm($i18n.prompt,"商品处于报警状态,是否强制验收？",function(button, text){
					                			if (button == 'yes') {
					                				txtsave4501();
					                			}else if (button == 'no') {
					                				return;
					                			}
					                		});
					                    }
					                    else if (data.obj.promptType == '2')
					                    {
					                    	Ext.Msg.confirm($i18n.prompt,"商品处于冻结状态,是否强制验收？",function(button, text){
					                			if (button == 'yes') {
					                				txtsave4501();
					                			}else if (button == 'no') {
					                				return;
					                			}
					                		});
					                    }
					                    else if(data.obj.promptType == '3')
					                    {
					                    	Ext.Msg.confirm($i18n.prompt,"商品超量验收,是否强制验收？",function(button, text){
					                			if (button == 'yes') {
					                				txtsave4501();
					                			}else if (button == 'no') {
					                				return;
					                			}
					                		});
					                    }
					                }
					                else if (data.obj.promptFlag == '3')
					                {
					                	Ext.example.msg($i18n.prompt,"暂时不支持授权");
					                    return;
					                }else if(data.obj.promptFlag == '0'){
					                	txtsave4501();
					                }
								}else{
									Ext.Msg.alert($i18n.prompt,data.msg);
									return;
								}
							}
						});			
	    			}
	    		});
	        }else{
	        	//校验报警天数和冻结比率、超量等
				Ext.Ajax.request({
					method:'POST',
					url:'idata_CheckAction_tscCheckExists.action',
					params:str,
					success:function(response){
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							//promptType;提示类型1：报警 2冻结 3超量
						    //promptFlag;处理方式1：提示类型（0:成功 ;1：拦截，不允许验入；2：提醒，但可强制验入；3：授权 ）
						  if (data.obj.promptFlag == '1')
			                {
			                    if (data.obj.promptType == '1')
			                    {
			                    	Ext.example.msg($i18n.prompt,"商品处于报警状态!");
			                        return;
			                    }
			                    else if (data.obj.promptType == '2')
			                    {
			                        Ext.example.msg($i18n.prompt,"商品处于冻结状态!");
			                        return;
			                    }
			                    else if(data.obj.promptType == '3')
			                    {
			                    	Ext.example.msg($i18n.prompt,"商品验收量已超出商品的验收超量比率!");
			                        return;
			                    }
			                }
			                else if (data.obj.promptFlag == '2')
			                {
			                    if (data.obj.promptType == '1')
			                    {
			                    	Ext.Msg.confirm($i18n.prompt,"商品处于报警状态,是否强制验收？",function(button, text){
			                			if (button == 'yes') {
			                				txtsave4501();
			                			}else if (button == 'no') {
			                				return;
			                			}
			                		});
			                    }
			                    else if (data.obj.promptType == '2')
			                    {
			                    	Ext.Msg.confirm($i18n.prompt,"商品处于冻结状态,是否强制验收？",function(button, text){
			                			if (button == 'yes') {
			                				txtsave4501();
			                			}else if (button == 'no') {
			                				return;
			                			}
			                		});
			                    }
			                    else if(data.obj.promptType == '3')
			                    {
			                    	Ext.Msg.confirm($i18n.prompt,"商品超量验收,是否强制验收？",function(button, text){
			                			if (button == 'yes') {
			                				txtsave4501();
			                			}else if (button == 'no') {
			                				return;
			                			}
			                		});
			                    }
			                }
			                else if (data.obj.promptFlag == '3')
			                {
			                	Ext.example.msg($i18n.prompt,"暂时不支持授权");
			                    return;
			                }else if(data.obj.promptFlag == '0'){
			                	txtsave4501();
			                }
						}else{
							Ext.example.msg($i18n.prompt,data.msg);
							return;
						}
					}
				});	
	        }	
			
	  },
  	 
  	 getFlag:function(){
  		 return flag;
  	 },
  	 
  	 setFlag:function(value){
  		 flag =value;
  	 },
  	 
  	 getArticleNoFlag:function(){
  		 return  articleNoFlag;
  	 },
  	 
  	 getPackingQtyFlag:function(){
  		return packingQtyFlag;
  	 },
  	 getProduceDateFlag:function(){
 		 return  produceDateFlag;
 	 },
 	 
 	 getExpireDateFlag:function(){
 		return expireDateFlag;
 	 },
 	 getTtemperatureFlag:function(){
  		 return  temperatureFlag;
  	 },
  	tabPidchange:function(tabPanel,newCard,oldCard,eOpts){
		if(newCard.itemId=='tabPId4501i'){
			bdef_RepairPrint = Ext.create('cms.view.common.bdef_RepairPrint');
			bdef_RepairPrint.items.items[3].items.items[0].getStore().removeAll();
			bdef_RepairPrint.items.items[3].items.items[1].getStore().removeAll();
			bdef_RepairPrint.items.items[4].items.items[0].getStore().removeAll();
			bdef_RepairPrint.items.items[4].items.items[1].getStore().removeAll();
			Ext.getCmp('tabPId4501i').add(bdef_RepairPrint);
			Ext.getCmp('tabPId4501i').doLayout();
			queryModuleId='4501';
			Ext.Ajax.request({
				url:'wms_PnfsetModuleReportQueryAction_getModuleReportQuery',
				method:'get',
				params:
				{
					strModuleId:queryModuleId
				},
				success:function(response)
				{
					var data=Ext.decode(response.responseText);
					for(var i=0;i<data.length;i++){
						getQueryPanel = Ext.create('cms.view.common.reportQueryPanel');
						getQueryPanel.items.items[1].getStore().add({
							columnid:data[i].columnid,
							columnname:data[i].columnname
					    });
						getQueryPanel.items.items[1].setValue(data[i].columnid);
						if(data[i].xtype=='datefield'){
							getQueryPanel.remove(getQueryPanel.items.items[3]);
							getQueryPanel.add({
						        xtype : 'datefield',
								fieldLabel : '值',							
								format : 'Y-m-d',
						        labelWidth : 20,
						        width : 195
							});
						}
						getQueryPanel.items.items[3].setValue('');
						getQueryPanel.items.items[1].setReadOnly(true);
						bdef_RepairPrint.items.items[2].add(getQueryPanel);					
					}
				}
			});
		}
	}
	 
});
function txtsave4501(){
	flag='1';//用于判断是否选择保存前的列
	var data = Ext.getCmp('grid4501_2').getSelectionModel().getSelection();
	var strSImportNo= data[0].get('SImportNo');
	var poType=data[0].get('poType');
	var classType=data[0].get('classType');
	var strDockNo= Ext.getCmp('cmbDockNo4501').getValue();		
	var strCheckWorker= Ext.getCmp('cmbWorkerNo4501').getValue();
	var strOwnerNo=data[0].get('ownerNo');
	
	var record  = Ext.getCmp('grid4501_1').getSelectionModel().getSelection();
	var articleNo=record[0].get('articleNo');
	var IdentifierBarcode=Ext.getCmp('articleIdentifier4501').getValue();
	var packingQty=record[0].get('packingQty');
	var qminOperatePacking=record[0].get('qminOperatePacking');

	var produceDate=Ext.getCmp('dateProduceDate4501').getValue();
	var expireDate=Ext.getCmp('dateExpireDate4501').getValue();
	var lotNo=Ext.getCmp('txtLotNo4501').getValue();
	var checkQty1=Ext.getCmp('numplanBox4501').getValue()*packingQty+Ext.getCmp('numplanQmin4501').getValue()*qminOperatePacking+Ext.getCmp('numplanDis4501').getValue();
	 var   checkQty=checkQty1.toFixed(5);
	if(record[0].get('temperatureFlag')=='1'){//管温度
		var temperature=Ext.getCmp('temperature4501').getValue()==null?'N':Ext.getCmp('temperature4501').getValue();
	}else{
		var temperature='N';
	}
	
	articleNoFlag=record[0].get('articleNo');
	packingQtyFlag=record[0].get('packingQty');
    produceDateFlag=Ext.getCmp('dateProduceDate4501').getValue();
    expireDateFlag=Ext.getCmp('dateExpireDate4501').getValue();
	temperatureFlag=Ext.getCmp('temperature4501').getValue();

	var detail1 = [];
	var master=
	{
		enterpriseNo:Ext.get('enterpriseNo').getValue(),
		warehouseNo:Ext.get('warehouseNo').getValue(),
		SImportNo:strSImportNo,	
		poType:poType,
		classType:classType,
		dockNo:strDockNo,				
		checkWorker:strCheckWorker,
		ownerNo:strOwnerNo,
		CheckTools:'1'
	};
    var d=
	{
		enterpriseNo:Ext.get('enterpriseNo').getValue(),
		warehouseNo:Ext.get('warehouseNo').getValue(),
		checkNo:'',
		ownerNo:strOwnerNo,
		rowId:0,
	
		articleNo:articleNo,
		packingQty:packingQty,
		qcNo:'',
		barcode:(record[0].get('barcode')=="" ||record[0].get('barcode')==undefined)?'N':record[0].get('barcode'),
		produceDate:produceDate==undefined? '1900-01-01':produceDate,
		expireDate:expireDate==undefined? '1900-01-01':expireDate,
		quality:Ext.getCmp('quality4501').getValue(),
		lotNo:lotNo==undefined?  'N':lotNo,
		rsvBatch1:record[0].get('rsvBatch1')==undefined?  'N':record[0].get('rsvBatch1'),
		rsvBatch2:record[0].get('rsvBatch2')==undefined?  'N':record[0].get('rsvBatch2'),
		rsvBatch3:record[0].get('rsvBatch3')==undefined?  'N':record[0].get('rsvBatch3'),
		rsvBatch4:record[0].get('rsvBatch4')==undefined?  'N':record[0].get('rsvBatch4'),
		rsvBatch5:record[0].get('rsvBatch5')==undefined?  'N':record[0].get('rsvBatch5'),
		rsvBatch6:record[0].get('rsvBatch6')==undefined?  'N':record[0].get('rsvBatch6'),
		rsvBatch7:record[0].get('rsvBatch7')==undefined?  'N':record[0].get('rsvBatch7'),
		rsvBatch8:record[0].get('rsvBatch8')==undefined?  'N':record[0].get('rsvBatch8'),
	    temperature:temperature==undefined?  'N':temperature,		
		stockType:'',
		stockValue:'',
		deptNo:'',
		checkQty:checkQty,
		checkWorker1:'',
		qcWorker:'',
		checkStartDate:'',
		checkEndDate:'',
		iqcStatus:'',
		unloadWorker:'',
		authorizedWorker:'',
		checkWorker2:''
	};
	if(checkQty!=0)
	{
		detail1.push(d);
	}else{
		Ext.example.msg($i18n.prompt,$i18n_prompt.inputQty);
        return;
	}
	if(detail1.length!=0)
	{
		var strJsonMaster = Ext.encode(master);
		var strJsonDetail1 = Ext.encode(detail1);
		var params = 
		{
			strJsonMaster:strJsonMaster,
			strJsonDetail1:strJsonDetail1,
			strFlag:Ext.getCmp('radiogroup4501').getValue().rb
		};
	}
	Ext.Ajax.request({
		method:'POST',
		url:'idata_CheckAction_saveCheck3.action',
		params:params,
		success:function(response){
			var data = Ext.decode(response.responseText);
			if(data.isSucc){
				Ext.example.msg($i18n.prompt,data.msg);
				Ext.getCmp('grid4501_1').getStore().removeAll();
				Ext.getCmp('grid4501_1').getStore().reload();
				Ext.getCmp('articleIdentifier4501').focus(false, 10);
				Ext.getCmp("articleIdentifier4501").setValue('');
				Ext.getCmp("numplanBox4501").setValue('');
				Ext.getCmp("numplanQmin4501").setValue('');
				Ext.getCmp("numplanDis4501").setValue('');
			}else{
				Ext.Msg.alert($i18n.prompt,data.msg+data.obj);
				return;
			}
		}
   }); 	
}
//根据商品编码和生产日期获取批号
function txtLotNo4501(){
	var data = Ext.getCmp('grid4501_1').getSelectionModel().getSelection();
	var articleNo = data[0].get('articleNo');
	var produceDate = Ext.getCmp('dateProduceDate4501').getValue();
	var strWheresql={
			articleNo:articleNo,
			produceDate:Ext.Date.format(produceDate,'Y/m/d')
	};
	Ext.Ajax.request({
		method:'POST',
		url:'stock_AdjustAccountsAction_queryLot',
		params:strWheresql,
		success:function(response){
			var data = Ext.decode(response.responseText);
			Ext.getCmp('dateExpireDate4501').setReadOnly(false);
			Ext.getCmp('txtLotNo4501').setReadOnly(false);
			if(data.length!=0){
				Ext.getCmp('txtLotNo4501').setValue(data[0].lotNo);
			}else{
				Ext.getCmp('txtLotNo4501').setValue('');
			}
			
		}
    }); 	
	

};
/**
 * 根据货主获取相应的系统参数
 * @param strOwnerNo
 */
function getSystemPara4501(strOwnerNo)
{
	g_ICAcrossAreaBox4501
		=commonGetSystemParams(strOwnerNo,'IC_AcrossAreaBox','I','IC')[0].sdefine;//不同储区的商品是否验收到同一物流箱容器上: 1允许;0：不允许]
	//g_ICFreezeRate4501
	//	=commonGetSystemParams(strOwnerNo,'IC_FreezeRate','I','IC')[0].sdefine;//达到报警比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
	//g_ICAlarmRate4501
	//	=commonGetSystemParams(strOwnerNo,'IC_AlarmRate','I','IC')[0].sdefine;//达到冻结比率的商品的系统处理方式：1：拦截，不允许验入；2：提醒，但可强制验入；3：授权
	g_ICCheckPickCell4501
		=commonGetSystemParams(strOwnerNo,'IC_CheckPickCell','I','IC')[0].sdefine;//存储验收时是否需要校验拣货位;0----不校验；1----校验
	g_ICCheckQpalette4501
		=commonGetSystemParams(strOwnerNo,'IC_CheckQpalette','I','IC')[0].sdefine;//进货验收是否要判断商品标准堆叠:1----需要; 0---- 不需要
}
/**
 * 校验报警天数和冻结比率（该方法暂不使用，改成保存时调用底层）
 */
function checkDate4501(th,data){
	var intProduce=Math.round(//计算输入的日期与今天的日期的相隔天数
			new Date(Ext.Date.format(th.getValue(),'Y-m-d')).getTime()
			-new Date(Ext.Date.format(new Date(),'Y-m-d')).getTime())
			/86400000*-1;
	var intFreezeRate=data[0].get('expiryDays')*data[0].get('freezerate')*0.01;//计算冻结比率的天数
	var intICAlarmRate=data[0].get('expiryDays')*data[0].get('alarmrate')*0.01;//计算报警比率的天数
	if(g_ICAlarmRate4501==1)
	{
		if(intProduce>intFreezeRate && intFreezeRate!=0)
		{
			Ext.getCmp('dateProduceDate4501').setValue(null);
			Ext.getCmp('dateExpireDate4501').setValue(null);
			Ext.example.msg($i18n.prompt, $i18n_prompt.theGoodsHasToFreezeRate);
		}else
		{
			if(g_ICFreezeRate4501==1)
			{
				if(intProduce>intICAlarmRate && intICAlarmRate!=0)
				{
					Ext.getCmp('dateProduceDate4501').setValue(null);
					Ext.getCmp('dateExpireDate4501').setValue(null);
					Ext.example.msg($i18n.prompt, $i18n_prompt.theGoodsHasToAlarmRate);
				}
			}else if(g_ICFreezeRate4501==2)
			{
				if(intProduce>intICAlarmRate && intICAlarmRate!=0)
				{
					Ext.example.msg($i18n.prompt, $i18n_prompt.theGoodsHasToAlarmRate);
				}
			}
		}
	}else if(g_ICAlarmRate4501==2)
	{
		if(intProduce>intFreezeRate && intFreezeRate!=0)
		{
			Ext.example.msg($i18n.prompt, $i18n_prompt.theGoodsHasToFreezeRate);
		}else
		{
			if(g_ICFreezeRate4501==1)
			{
				if(intProduce>intICAlarmRate && intICAlarmRate!=0)
				{
					Ext.getCmp('dateProduceDate4501').setValue(null);
					Ext.getCmp('dateExpireDate4501').setValue(null);
					Ext.example.msg($i18n.prompt, $i18n_prompt.theGoodsHasToAlarmRate);
				}
			}else if(g_ICFreezeRate4501==2)
			{
				if(intProduce>intICAlarmRate && intICAlarmRate!=0)
				{
					Ext.example.msg($i18n.prompt, $i18n_prompt.theGoodsHasToAlarmRate);
				}
			}
		}
	}
}
/**
 * 校验商品是否超量(该方法暂不使用，现在是在保存的时候在调用底层控制)
 * @param articleNo
 * @returns {Boolean}
 */
/*function checkQty4501(){
	var rec  = Ext.getCmp('grid4501_1').getSelectionModel().getSelection();
	var packingQty=rec[0].get('packingQty');
	var checkQty=Ext.getCmp('numplanBox4501').getValue()*packingQty+Ext.getCmp('numPoPsc4501').getValue();
	var canCheckQty=rec[0].get('canCheckQty');
	if(canCheckQty-checkQty<0)
	{
		Ext.example.msg($i18n.prompt,$i18n_prompt.theExcessValueOfCommodity+rec[0].get('checkExcess')+"%"+$i18n_prompt.theAcceptanceRatioHasExceededOverInputAgain);
		return false;
	}
	return true;
}*/