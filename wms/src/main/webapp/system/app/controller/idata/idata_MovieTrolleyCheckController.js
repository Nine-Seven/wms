/**
 * 模块名称：移动台车验收
 * 模块编码：4902
 * 创建：hcx
 */
var g_strIsCanEdit4902=false;
var flag='0'; //用于选择行号，0为不选择，1为要选择
var articleNo='';
var packingQty='';
Ext.define('cms.controller.idata.idata_MovieTrolleyCheckController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.idata.idata_MovieTrolleyCheckUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//设置光标跳到下一输入框
			'idata_MovieTrolleyCheckUI field':{
				specialkey:this.boxkeydown
			},//刷新
			'idata_MovieTrolleyCheckUI button[id=refresh4902]':{
				click:this.refresh
			},//货主选择
			'combo[id=owner4902]':{
				select:this.ownerSelect
			},//供应商选择
			'wms_DefFieldValCombo[id=suppliers4902]':{
				select:this.suppliersSelect
			},//扫描码头号
			'idata_MovieTrolleyCheckUI textfield[id=cmbDockNo4902]':{
				blur:this.cmbDockNo4902Blur
			},//采购单号选择
			'remoteCombo[id=poNo4902]':{
				beforequery:this.poNoBeforeQuery,
				select:this.poNoSelect
			},//助记码/条码选择加载单据列表
			'remoteCombo[id=identifierOrBarcode4902]':{
				beforequery:this.identifierOrBarcode1BeforeQuery,
				select:this.identifierOrBarcode1Select
			},//助记码/条码选择
			'remoteCombo[id=articleIdentifier4902]':{
				beforequery:this.articleIdentifierBeforeQuery,
				select:this.articleIdentifierSelect
			},//单据 列表Grid单击
			'idata_MovieTrolleyCheckUI grid[id=grid4902_2]':{
				selectionchange:this.grid4902_2Selectionchange			
			},//商品信息 Grid单击
			'idata_MovieTrolleyCheckUI grid[id=grid4902_1]':{
				selectionchange:this.grid4902_1Selectionchange			
			},//保存
			'idata_MovieTrolleyCheckUI button[id=save4902]':{
				click:this.save
			},//TAB页切换
			'idata_MovieTrolleyCheckUI tabpanel[id=tabPid4902]':{
				tabchange:this.tabPidchange
			},//品质选择
			'idata_MovieTrolleyCheckUI wms_DefFieldValCombo[id=quality4902]':{
				beforequery:this.quality4902BeforeQuery
			}
			
		});
	},
	//界面初始化
	initializtion:function(){
		disableButtonFunc(1,'#save4902',$i18n.save);
		Ext.getCmp('cmbDockNo4902').focus(false, 10);
		Ext.getCmp('cmbPrintFlag4902').setValue('0');
		
	},
	//设置光标跳到下一输入框
	boxkeydown:function(th,e,eOpts){
	  	if (e.getKey() == e.ENTER) {
	  		if(th.id=='cmbWorkerNo4902'){
				Ext.getCmp('owner4902').focus();
	  		}else{
		  		th.nextSibling().focus();	  		
	  		}
        }
	},
	 //刷新
	 refresh:function(){
	    disableButtonFunc(1,'#save4902',$i18n.save);
	    Ext.getCmp('owner4902').setValue('');
	    Ext.getCmp('owner4902').getStore().removeAll();
	    Ext.getCmp('owner4902').getStore().reload();
	    Ext.getCmp('suppliers4902').setValue('');
	    Ext.getCmp('suppliers4902').getStore().removeAll();
	    Ext.getCmp('suppliers4902').getStore().reload();
	    Ext.getCmp("poNo4902").setValue('');
		Ext.getCmp('poNo4902').focus(false, 10);
		Ext.getCmp("identifierOrBarcode4902").setValue('');
		Ext.getCmp("articleIdentifier4902").setValue('');
		Ext.getCmp("checkQty4902").setValue('');
 	  	Ext.getCmp('grid4902_1').getStore().removeAll();
 	  	Ext.getCmp('grid4902_2').getStore().removeAll();
 	  },
 	 quality4902BeforeQuery:function(){
  		var data = Ext.getCmp('grid4902_2').getSelectionModel().getSelection();
  		var str = {
 				strOwnerNo : data[0].data.ownerNo,
 				str : data[0].data.importType
 		};
 		Ext.apply(Ext.getCmp('quality4902').getStore().proxy.extraParams,str);
 		Ext.getCmp('quality4902').getStore().removeAll();
 		Ext.getCmp('quality4902').getStore().load();
  	 },
 	 //货主选择
 	 ownerSelect:function(){
 		var listDetail1  =  [];
		var strDtl = {
			columnId:'a.owner_no',
			value:Ext.getCmp("owner4902").getValue()
		};
		listDetail1.push(strDtl);
		
		var wheresql={
				str:Ext.encode(listDetail1)
			};
		Ext.apply(Ext.getCmp('suppliers4902').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('suppliers4902').getStore().removeAll();
 		Ext.getCmp('suppliers4902').getStore().load();
		
  	  }, 
  	//供应商选择
  	suppliersSelect:function(){
  		var listDetail1  =  [];
		var strDtl = {
			columnId:'a.owner_no',
			value:Ext.getCmp("owner4902").getValue()
		};
		listDetail1.push(strDtl);
		if(!Ext.isEmpty(Ext.getCmp('suppliers4902').getValue())){
			var strDt2 = {
					columnId:'a.supplier_no',
					value:Ext.getCmp("suppliers4902").getValue()
				};
				listDetail1.push(strDt2);
		}
		
		var params={
				strWheresql:Ext.getCmp("poNo4902").getValue(),
				str:Ext.encode(listDetail1)
		};
		var wheresql={
				strWheresql:Ext.getCmp("identifierOrBarcode4902").getValue(),   
				str:Ext.encode(listDetail1)
			};
		var str={
				strWheresql:null,
				str:Ext.encode(listDetail1)
			};
		Ext.getCmp('poNo4902').setValue('');
		Ext.apply(Ext.getCmp('poNo4902').getStore().proxy.extraParams,params);
		Ext.getCmp('poNo4902').getStore().removeAll();
		Ext.getCmp('poNo4902').getStore().load();
		
		Ext.getCmp('identifierOrBarcode4902').setValue('');
		Ext.apply(Ext.getCmp('identifierOrBarcode4902').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('identifierOrBarcode4902').getStore().removeAll();
		Ext.getCmp('identifierOrBarcode4902').getStore().load();
		
		Ext.apply(Ext.getCmp('grid4902_2').getStore().proxy.extraParams,str);
		Ext.getCmp('grid4902_2').getStore().removeAll();
		Ext.getCmp('grid4902_2').getStore().load();
		
		Ext.getCmp('grid4902_1').getStore().removeAll();
		
		Ext.getCmp('articleIdentifier4902').setValue('');
		Ext.getCmp('checkQty4902').setValue('');

  	},
  	//扫描扫描台号
  	cmbDockNo4902Blur:function(){
  		if(Ext.isEmpty(Ext.getCmp('cmbDockNo4902').getValue()))
  		{
  			return;
  		}
  		//校验扫描台号
  		var params = {
  				str : Ext.getCmp('cmbDockNo4902').getValue(),
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
  					Ext.getCmp('cmbDockNo4902').setValue("");
  					Ext.getCmp('cmbDockNo4902').focus(false, 10);
  				}
  			}
  		});		
  	},
  	 //助记码/条码选择
  	articleIdentifierBeforeQuery:function(){
  		var data = Ext.getCmp('grid4902_2').getSelectionModel().getSelection();
		if(data.length!=0){
			var listDetail1  =  [];
	 		var strDtl = {
	 			columnId:'a.owner_no',
	 			value:Ext.getCmp("owner4902").getValue()
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
	 		Ext.apply(Ext.getCmp('articleIdentifier4902').getStore().proxy.extraParams,params);
	 		Ext.getCmp('articleIdentifier4902').getStore().removeAll();
	 		Ext.getCmp('articleIdentifier4902').getStore().load();
		}
   	 },
   	articleIdentifierSelect:function(){
   		var gridcount=Ext.getCmp("grid4902_1").getStore().getCount();
   		if(gridcount>0){
   			for(var i=0;i<gridcount;i++){
   	   	   		var data = Ext.getCmp('grid4902_1').getStore().getAt(i);
   	   	        if(data.length!=0){
                    if(data.get('barcode')==Ext.getCmp("articleIdentifier4902").getValue()){
            	        if(data.get('noCheckQty')==0){
        				    Ext.example.msg($i18n.prompt,$i18n_prompt.noCheckQty);
        				    Ext.getCmp("articleIdentifier4902").setValue('');
        				    Ext.getCmp('articleIdentifier4902').focus(false, 10);
        				    return;
        			     }else{
        				    Ext.getCmp('grid4902_1').getSelectionModel().select(i);
        				    Ext.getCmp('checkQty4902').focus(false, 10);
        				    disableButtonFunc(0,'#save4902',$i18n.save);

        			     }
                    }
  		        }
   			 }
   		}
   		
  	 },
 	  //采购单号选择
  	 poNoBeforeQuery:function(){
  		var listDetail1  =  [];
		var strDtl = {
			columnId:'a.owner_no',
			value:Ext.getCmp("owner4902").getValue()
		};
		listDetail1.push(strDtl);
		if(!Ext.isEmpty(Ext.getCmp('suppliers4902').getValue())){
			var strDt2 = {
					columnId:'a.supplier_no',
					value:Ext.getCmp("suppliers4902").getValue()
				};
				listDetail1.push(strDt2);
		}
		
		var params={
			strWheresql:Ext.getCmp("poNo4902").getValue(),   
			str:Ext.encode(listDetail1)
		};
		Ext.apply(Ext.getCmp('poNo4902').getStore().proxy.extraParams,params);
		Ext.getCmp('poNo4902').getStore().removeAll();
		Ext.getCmp('poNo4902').getStore().load();
  	 },
 	 poNoSelect:function(){
 		var listDetail1  =  [];
		var strDtl = {
			columnId:'a.owner_no',
			value:Ext.getCmp("owner4902").getValue()
		};
		listDetail1.push(strDtl);
		var strDt2 = {
				columnId:'a.po_no',
				value:Ext.getCmp("poNo4902").getValue()
			};
		listDetail1.push(strDt2);
		if(!Ext.isEmpty(Ext.getCmp('suppliers4902').getValue())){
			var strDt3 = {
					columnId:'a.supplier_no',
					value:Ext.getCmp("suppliers4902").getValue()
				};
			listDetail1.push(strDt3);
		}
		
		var params={
				strWheresql:'',   
				str:Ext.encode(listDetail1)
			};
		Ext.apply(Ext.getCmp('grid4902_2').getStore().proxy.extraParams,params);
		Ext.getCmp('grid4902_2').getStore().removeAll();
		Ext.getCmp('grid4902_2').getStore().load();
		Ext.getCmp("identifierOrBarcode4902").setValue('');
		Ext.getCmp('grid4902_1').getStore().removeAll();
		Ext.getCmp("articleIdentifier4902").setValue('');
		Ext.getCmp("checkQty4902").setValue('');

 	 },
  	 //助记码/条码选择加载单据列表  
 	identifierOrBarcode1BeforeQuery:function(){
 		var listDetail1  =  [];
		var strDtl = {
			columnId:'a.owner_no',
			value:Ext.getCmp("owner4902").getValue()
		};
		listDetail1.push(strDtl);
		if(!Ext.isEmpty(Ext.getCmp('suppliers4902').getValue())){
			var strDt2 = {
					columnId:'a.supplier_no',
					value:Ext.getCmp("suppliers4902").getValue()
				};
			listDetail1.push(strDt2);
		}
		
		var params={
				strWheresql:Ext.getCmp("identifierOrBarcode4902").getValue(),   
				str:Ext.encode(listDetail1)
			};
		Ext.apply(Ext.getCmp('identifierOrBarcode4902').getStore().proxy.extraParams,params);
		Ext.getCmp('identifierOrBarcode4902').getStore().removeAll();
		Ext.getCmp('identifierOrBarcode4902').getStore().load();
  	  },
  	identifierOrBarcode1Select:function(){
  		var listDetail1  =  [];
		var strDtl = {
			columnId:'a.owner_no',
			value:Ext.getCmp("owner4902").getValue()
		};
		listDetail1.push(strDtl);
		if(!Ext.isEmpty(Ext.getCmp('suppliers4902').getValue())){
			var strDt2 = {
					columnId:'a.supplier_no',
					value:Ext.getCmp("suppliers4902").getValue()
				};
			listDetail1.push(strDt2);
		}
		
		var params={
				strWheresql:Ext.getCmp("identifierOrBarcode4902").getValue(),   
				str:Ext.encode(listDetail1)
			};
		
		Ext.apply(Ext.getCmp('grid4902_2').getStore().proxy.extraParams,params);
		Ext.getCmp('grid4902_2').getStore().removeAll();
		Ext.getCmp('grid4902_2').getStore().load();
		Ext.getCmp("poNo4902").setValue('');
		Ext.getCmp('grid4902_1').getStore().removeAll();
		Ext.getCmp("articleIdentifier4902").setValue('');
		Ext.getCmp("checkQty4902").setValue('');
   		var gridcount=Ext.getCmp("grid4902_2").getStore().getCount();
   		if(gridcount>0){
			Ext.getCmp('grid4902_2').getSelectionModel().select(0);
   		}

  	 },
  	 //单据列表选择
  	 grid4902_2Selectionchange:function(){
		var data = Ext.getCmp('grid4902_2').getSelectionModel().getSelection();
		if(data.length!=0){
			var listDetail1  =  [];
	 		var strDtl = {
	 			columnId:'a.owner_no',
	 			value:Ext.getCmp("owner4902").getValue()
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
	 		Ext.apply(Ext.getCmp('grid4902_1').getStore().proxy.extraParams,params);
	 		Ext.getCmp('grid4902_1').getStore().removeAll();
	 		Ext.getCmp('grid4902_1').getStore().load();
		}
  	 },
  	 //选择商品填充助记码/条码
  	 grid4902_1Selectionchange:function(){
		var data = Ext.getCmp('grid4902_1').getSelectionModel().getSelection();
		if(data.length!=0){
			if(data[0].get('noCheckQty')==0){
				Ext.example.msg($i18n.prompt,$i18n_prompt.noCheckQty);
				Ext.getCmp("articleIdentifier4902").setValue('');
				Ext.getCmp('articleIdentifier4902').focus(false, 10);
				return;
			}else{
				Ext.getCmp("articleIdentifier4902").setValue(data[0].get('articleText'));
				Ext.getCmp("checkQty4902").setValue('');
				Ext.getCmp('checkQty4902').focus(false, 10);
				disableButtonFunc(0,'#save4902',$i18n.save);
			}
			
			//填充品质
			this.quality4902BeforeQuery();
		}
  	 },
	//保存
 	 save:function(){
 		 debugger;
 		if(!commonCheckIsInputAll('form_01_4902'))
		{
			return;
		}
 		var rec = Ext.getCmp('grid4902_2').getSelectionModel().getSelection();
    	if(rec.length==0){
    		Ext.Msg.alert($i18n.prompt,$i18n_prompt.tableCannotBeNull);
    		return;
    	}
    	if(!commonCheckIsInputAll('form_03_4902'))
		{
			return;
		}
    	flag='1';//用于判断是否选择保存前的列
		var data = Ext.getCmp('grid4902_2').getSelectionModel().getSelection();
 		var strSImportNo= data[0].get('SImportNo');
		var strDockNo= Ext.getCmp('cmbDockNo4902').getValue();		
		var strCheckWorker= Ext.getCmp('cmbWorkerNo4902').getValue();
		var strOwnerNo=Ext.getCmp('owner4902').getValue();
 		var master=
		{
			enterpriseNo:Ext.get('enterpriseNo').getValue(),
			warehouseNo:Ext.get('warehouseNo').getValue(),
			SImportNo:strSImportNo,			
			dockNo:strDockNo,				
			checkWorker:strCheckWorker,
			ownerNo:strOwnerNo,
			CheckTools:'1'
		};
		var detail1 = [];
		var record  = Ext.getCmp('grid4902_1').getSelectionModel().getSelection();
		if(Ext.getCmp('checkQty4902').getValue()>record[0].get('noCheckQty')){
			Ext.example.msg($i18n.prompt,$i18n_prompt.checkQtyMoreThanNoCheckQty);
			Ext.getCmp('checkQty4902').setValue('');
			Ext.getCmp('checkQty4902').focus(false, 10);
			return;
		}
		articleNo=record[0].get('articleNo');
		packingQty=record[0].get('packingQty');
		var d=
		{		
			id:
			{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.get('warehouseNo').getValue(),
				checkNo:'',
				ownerNo:strOwnerNo,
				rowId:i
			},
			articleNo:record[0].get('articleNo'),
			packingQty:record[0].get('packingQty'),
			qcNo:'',
			barcode:record[0].get('barcode'),
			produceDate:record[0].get('produceDate')==undefined || record[0].get('produceDate')==''? '1900-01-01':record[0].get('produceDate'),
			expireDate:record[0].get('expireDate')==undefined || record[0].get('expireDate')==''? '1900-01-01':record[0].get('expireDate'),
			quality:record[0].get('quality')==undefined || record[0].get('quality')==''?  '0':record[0].get('quality'),
			lotNo:record[0].get('lotNo')==undefined || record[0].get('lotNo')==''?  'N':record[0].get('lotNo'),
			rsvBatch1:record[0].get('rsvBatch1')==undefined || record[0].get('rsvBatch1')==''?  'N':record[0].get('rsvBatch1'),
			rsvBatch2:record[0].get('rsvBatch2')==undefined || record[0].get('rsvBatch2')==''?  'N':record[0].get('rsvBatch2'),
			rsvBatch3:record[0].get('rsvBatch3')==undefined || record[0].get('rsvBatch3')==''?  'N':record[0].get('rsvBatch3'),
			rsvBatch4:record[0].get('rsvBatch4')==undefined || record[0].get('rsvBatch4')==''?  'N':record[0].get('rsvBatch4'),
			rsvBatch5:record[0].get('rsvBatch5')==undefined || record[0].get('rsvBatch5')==''?  'N':record[0].get('rsvBatch5'),
			rsvBatch6:record[0].get('rsvBatch6')==undefined || record[0].get('rsvBatch6')==''?  'N':record[0].get('rsvBatch6'),
			rsvBatch7:record[0].get('rsvBatch7')==undefined || record[0].get('rsvBatch7')==''?  'N':record[0].get('rsvBatch7'),
			rsvBatch8:record[0].get('rsvBatch8')==undefined || record[0].get('rsvBatch8')==''?  'N':record[0].get('rsvBatch8'),
			stockType:'',
			stockValue:'',
			deptNo:'',
			checkQty:Ext.getCmp('checkQty4902').getValue(),
			checkWorker1:'',
			qcWorker:'',
			checkStartDate:'',
			checkEndDate:'',
			iqcStatus:'',
			unloadWorker:'',
			authorizedWorker:'',
			checkWorker2:'',
			printFlag:Ext.getCmp('cmbPrintFlag4902').getValue()==undefined?  'N':Ext.getCmp('cmbPrintFlag4902').getValue()	
		};
		if(Ext.getCmp('checkQty4902').getValue()!=0 && Ext.getCmp('checkQty4902').getValue()!=undefined)
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
				strJsonDetail1:strJsonDetail1
			};
		}
		var wheresql = 
		{
		    importNo: data[0].get('importNo'),
		    articleNo:record[0].get('articleNo')
		};
		Ext.Ajax.request({
			method:'POST',
			url:'idata_MovieTrolleyCheckAction_checkDpsCellNo.action',
			params:wheresql,
			success:function(response){
				var re = Ext.decode(response.responseText);
				if(re.isSucc){
					if(re.msg='Y'){
						Ext.Msg.confirm($i18n.prompt, $i18n_prompt.saveOrNot,function(button, text){
							if (button == 'yes') {
								Ext.Ajax.request({
									method:'POST',
									url:'idata_MovieTrolleyCheckAction_save.action',
									params:params,
									success:function(response){
										var data = Ext.decode(response.responseText);
										if(data.isSucc){
											Ext.example.msg($i18n.prompt,data.msg);
											Ext.getCmp('grid4902_1').getStore().removeAll();
											Ext.getCmp('grid4902_1').getStore().reload();
											Ext.getCmp('articleIdentifier4902').focus(false, 10);
											Ext.getCmp("articleIdentifier4902").setValue('');
											Ext.getCmp("checkQty4902").setValue('');
										}else{
											Ext.Msg.alert($i18n.prompt,data.msg+data.obj);
											return;
										}
									}
								});			
							}
							if (button == 'no') {
								return;
							}
						});   
					}
				}else{
					Ext.Msg.alert($i18n.prompt,re.msg);
					return;
				}
			}
		});		
		
	  },
  	 
  	 getFlag:function(){
  		 return flag;
  	 },
  	 
  	 setFlag:function(value){
  		 flag =value;
  	 },
  	 
  	 getArticleNo:function(){
  		 return  articleNo;
  	 },
  	 
  	 getPackingQty:function(){
  		return packingQty;
  	 },
 	tabPidchange:function(tabPanel,newCard,oldCard,eOpts){
		if(newCard.itemId=='tabPId4902i'){
			bdef_RepairPrint = Ext.create('cms.view.common.bdef_RepairPrint');
			Ext.getCmp('tabPId4902i').add(bdef_RepairPrint);
			Ext.getCmp('tabPId4902i').doLayout();
			queryModuleId='4902';
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

