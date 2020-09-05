/**
 * 模块名称：电商按客户发单
 * 模块编码：3303
 * 创建：huangb 20160706
 */
Ext.define('cms.controller.odata.odata_OutstockMManSendECController',{
	extend:'Ext.app.Controller',
	requires:[
          'cms.view.odata.odata_OutstockMManSendECUI'
	],
	model:'',
	store:'',
	init:function(){
		this.control({
			//拣货发单手动成单手动成单》货主选择
			'odata_OutstockMManSendECUI bdef_DefOwnerCombo[id=cmbOwnerNoSend3303]':{
				select:this.cmbOwnerNoSend3303Select
			},
			//拣货发单手动成单》出货单别下拉
			'odata_OutstockMManSendECUI combo[id = cmbExp_typeSend3303]':{
				select:this.cmbExp_typeSend3303Select
			},
			//拣货发单手动成单》波次类型下拉
			/*'odata_OutstockMManSendECUI combo[id = cmbSource_type3303]':{
				select:this.cmbSource_type3303change
			},*/
			//拣货发单手动成单》波次下拉
			'odata_OutstockMManSendECUI combo[id = cmbLocate_no3303]':{
				select:this.cmbLocate_no3303Select
			},//拣货发单手动成单》批次下拉
			'odata_OutstockMManSendECUI combo[id = cmbm_batch_no3303]':{
				select:this.cmbm_batch_no3303Select
			},
			//拣货发单手动成单》下架类型
			'odata_OutstockMManSendECUI combo[id = cmbOutStockType3303]':{
				select:this.cmbOutStockType3303Select
			},
			//拣货发单手动成单》储区代码
			'odata_OutstockMManSendECUI combo[id = cmbArea_no3303]':{
				select:this.cmbArea_no3303Select
			},
			//拣货发单手动成单》作业类型
			'odata_OutstockMManSendECUI combo[id = cmbOperate_type3303]':{
				select:this.cmbOperate_type3303Select
			},
			//拣货发单手动成单》客户选择
			'odata_OutstockMManSendECUI grid[id = gridOutstockMManSend3303]':{
				beforeselect:this.gridOutstockMManSend3303beforeselect,
				selectionchange:this.gridOutstockMManSend3303selectionchange
			},
			//拣货发单手动成单未发单指示》波次号选择
			'odata_OutstockMManSendECUI grid[id = gridOutstockMLocateNoCheck3303]':{
				selectionchange:this.gridOutstockMLocateNoCheck3303change
			},
			//拣货发单手动成单》发单
			'odata_OutstockMManSendECUI button[id = butSend3303]':{
				click:this.menu3303sendclick
			},
			//拣货发单手动成单》刷新
			'odata_OutstockMManSendECUI commMenuWidget4[id = menu3303] [name = refresh]':{
				click:this.menu3303RefreshClick
			},//拣货发单手动成单》关闭前事件
			'odata_OutstockMManSendECUI':{
				beforeclose:this.odata_OutstockMManSendECUIBeforeclose
			},
			//TAB页切换
			'odata_OutstockMManSendECUI tabpanel[id=tabpanel3303]':{
				tabchange:this.TabpanelChange
			},//拣货发单按配送对象发单》货主选择
			'odata_OutstockMManSendECUI bdef_DefOwnerCombo[id=cmbOwnerNoObj3303]':{
				select:this.cmbOwnerNoObj3303Select
			},
			//拣货发单按配送对象发单》出货单别下拉
			'odata_OutstockMManSendECUI combo[id = cmbExp_typeObj3303]':{
				select:this.cmbExp_typeObj3303Select
			},
			//拣货发单按配送对象发单》波次下拉
			'odata_OutstockMManSendECUI combo[id = cmbLocate_no3303_Obj]':{
				select:this.cmbLocate_noObj3303Select
			},//拣货发单按配送对象发单》批次下拉
			'odata_OutstockMManSendECUI combo[id = cmbm_batch_no3303_Obj]':{
				select:this.cmbm_batch_no3303_ObjSelect
			},
			//拣货发单按配送对象发单》下架类型
			'odata_OutstockMManSendECUI combo[id = cmbOutStockType3303_Obj]':{
				select:this.cmbOutStockTypeObj3303Select
			},//拣货发单按配送对象发单》配送对象选择
			'odata_OutstockMManSendECUI grid[id = gridOutstockMObjSend3303]':{
				beforeselect:this.gridOutstockMObjSend3303beforeselect,
				selectionchange:this.gridOutstockMObjSend3303selectionchange
			},
			//拣货发单按配送对象发单》发单
			'odata_OutstockMManSendECUI button[id = butSend3303_Obj]':{
				click:this.menu3303sendclick_obj
			}
		});	
		/*Ext.getCmp('gridOutstockMManSend3303').getStore().addListener({
			beforeload:function(store,records,options){
				store.proxy.extraParams.flag  =  "1";
			}
		});*/
	},
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		Ext.getCmp('cmbOwnerNoSend3303').setValue();
		Ext.getCmp('cmbOwnerNoSend3303').getStore().load();
		Ext.getCmp('cmbOwnerNoObj3303').setValue();
		Ext.getCmp('cmbOwnerNoObj3303').getStore().load();
		//显示变量0为不显示，1为显示  add by huangcx at 20160528
		var packingUnit3303_1=commonGetModuleField('3303','packingUnit')[0].flag;
		var packingSpec3303_1=commonGetModuleField('3303','packingSpec')[0].flag;
		var packingUnit3303_2=commonGetModuleField('3303','packingUnit')[0].flag;
		var packingSpec3303_2=commonGetModuleField('3303','packingSpec')[0].flag;
		var packingUnit3303_3=commonGetModuleField('3303','packingUnit')[0].flag;
		var packingSpec3303_3=commonGetModuleField('3303','packingSpec')[0].flag;

		if(packingUnit3303_1==0){
			Ext.getCmp('packingUnit3303_1').setVisible(false);
		}
		if(packingSpec3303_1==0){
			Ext.getCmp('packingSpec3303_1').setVisible(false);
		}
		if(packingUnit3303_2==0){
			Ext.getCmp('packingUnit3303_2').setVisible(false);
		}
		if(packingSpec3303_2==0){
			Ext.getCmp('packingSpec3303_2').setVisible(false);
		}
		if(packingUnit3303_3==0){
			Ext.getCmp('packingUnit3303_3').setVisible(false);
		}
		if(packingSpec3303_3==0){
			Ext.getCmp('packingSpec3303_3').setVisible(false);
		}
		//end add
	},
	odata_OutstockMManSendECUIBeforeclose:function(){
		Ext.getCmp('gridOutstockMManSend3303').store.removeAll();
	},
	
	//波次号选择
	gridOutstockMLocateNoCheck3303change:function()
	{
		//debugger;
		var objRecord = Ext.getCmp('gridOutstockMLocateNoCheck3303').getSelectionModel().getSelection();
        if(objRecord.length == 0)
        {
    		Ext.getCmp('gridOutstockDCheck3303').getStore().removeAll();
        }else{
            var listDetail = [];
			var strDtl={
				columnId:'ood.wave_no',
				value:objRecord[0].data.waveNo
			};
			listDetail.push(strDtl);
			var strJson = Ext.encode(listDetail);
			var wheresql = {
				str : strJson,
				strSendFlag:"man"//按客户发单标识
			};
			//loadgridOutstockMManSend3303();
			Ext.apply(Ext.getCmp('gridOutstockDCheck3303')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('gridOutstockDCheck3303').getStore()
					.removeAll();
			Ext.getCmp('gridOutstockDCheck3303').getStore()
					.load();
        }
	},
	
	
	//TAB页切换
	TabpanelChange:function(th,newValue,oldValue,eOpts)
	{
		if(Ext.getCmp('tabpanel3303').getActiveTab().id=='tabManSend3303' )
		{
			Ext.getCmp('gridOutstockMManSend3303').getStore().removeAll();
			Ext.getCmp('gridOutstockDManSend3303').getStore().removeAll();
			Ext.getCmp('formSendCondition3303').getForm().reset();
			Ext.getCmp('cmbOwnerNoSend3303').setValue(null);
			Ext.getCmp('cmbOwnerNoSend3303').getStore().load();
		}else if(Ext.getCmp('tabpanel3303').getActiveTab().id=='tabObjSend3303' ){
			Ext.getCmp('gridOutstockMObjSend3303').getStore().removeAll();
			Ext.getCmp('gridOutstockDObjSend3303').getStore().removeAll();
			Ext.getCmp('formObjCondition3303').getForm().reset();
			Ext.getCmp('cmbOwnerNoObj3303').setValue(null);
			Ext.getCmp('cmbOwnerNoObj3303').getStore().load();
		}
		else if(Ext.getCmp('tabpanel3303').getActiveTab().id=='tabManCheck3303')
		{
			var strWheresql = {
					str : null,
					strSendFlag : "man",
	      		    strOwnerNo:Ext.getCmp('cmbOwnerNoSend3303').getValue()

			};
			Ext.apply(Ext.getCmp('gridOutstockMLocateNoCheck3303')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('gridOutstockMLocateNoCheck3303').getStore()
					.removeAll();
			Ext.getCmp('gridOutstockMLocateNoCheck3303').getStore()
					.load();
		}
		
	},
	cmbOwnerNoObj3303Select:function()
    {
		var listDetail   =  [];
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNoObj3303').getValue()))
		{
			//有混合业主的情况 如果 为ALL 则不传委托业主 huangb 20160803
			if(Ext.getCmp('cmbOwnerNoObj3303').getValue()!='ALL'){
				var strDtl  =  {
					    columnId:'ood.owner_no',
						value:Ext.getCmp('cmbOwnerNoObj3303').getValue()
					};
				listDetail.push(strDtl);
			}
			
			var strJson  =  Ext.encode(listDetail);
			var strWheresql  =  {
				str : strJson,
				strSendFlag : "man"
			};
			Ext.apply(Ext.getCmp('cmbExp_typeObj3303')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('cmbExp_typeObj3303').getStore()
					.removeAll();
			Ext.getCmp('cmbExp_typeObj3303').getStore()
					.load();
		}else
		{
			
		}
	
    },
	//拣货发单手动成单》货主选择
	cmbOwnerNoSend3303Select:function()
    {
		var listDetail   =  [];
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNoSend3303').getValue()))
		{
			//有混合业主的情况 如果 为ALL 则不传委托业主 huangb 20160803
			if(Ext.getCmp('cmbOwnerNoSend3303').getValue()!='ALL'){
				var strDtl  =  {
					    columnId:'ood.owner_no',
						value:Ext.getCmp('cmbOwnerNoSend3303').getValue()
					};
			}
			
			listDetail.push(strDtl);
			var strJson  =  Ext.encode(listDetail);
			var strWheresql  =  {
				str : strJson,
				strSendFlag : "man"
			};
			Ext.apply(Ext.getCmp('cmbExp_typeSend3303')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('cmbExp_typeSend3303').getStore()
					.removeAll();
			Ext.getCmp('cmbExp_typeSend3303').getStore()
					.load();
				//loadgridOutstockMManSend3303();
		}else
		{
			
		}
	
		//loadgridOutstockMManSend3303();
    },
    //拣货发单按配送对象发单》出货单别下拉
    cmbExp_typeObj3303Select:function()
    {
    	 var listDetail  =  [];
         listDetail  =  Ext.decode(Ext.getCmp('cmbExp_typeObj3303').getStore().getProxy().extraParams.str);
         if(!Ext.isEmpty(Ext.getCmp('cmbExp_typeObj3303').getValue()))
         {
      	   var strdtl = {
      				columnId:'ood.exp_type',
      				value:Ext.getCmp('cmbExp_typeObj3303').getValue()
      			};
      	       listDetail.push(strdtl);
  			var strJson  =  Ext.encode(listDetail);
  			var strWheresql  =  {
  				str : strJson/*,
  				strOwnerNo : Ext.getCmp('cmbOwnerNoSend3303').getValue()*/
  			};
  			Ext.apply(Ext.getCmp('cmbLocate_no3303_Obj')
  					.getStore().proxy.extraParams,
  					strWheresql);
  			Ext.getCmp('cmbLocate_no3303_Obj').getStore()
  					.removeAll();
  			Ext.getCmp('cmbLocate_no3303_Obj').getStore()
  					.load();
         }
	},
  //拣货发单手动成单》出货单别下拉
    cmbExp_typeSend3303Select:function()
    {
    	 var listDetail  =  [];
         listDetail  =  Ext.decode(Ext.getCmp('cmbExp_typeSend3303').getStore().getProxy().extraParams.str);
         if(!Ext.isEmpty(Ext.getCmp('cmbExp_typeSend3303').getValue()))
         {
      	   var strdtl = {
      				columnId:'ood.exp_type',
      				value:Ext.getCmp('cmbExp_typeSend3303').getValue()
      			};
      	       listDetail.push(strdtl);
  			var strJson  =  Ext.encode(listDetail);
  			var strWheresql  =  {
  				str : strJson/*,
  				strOwnerNo : Ext.getCmp('cmbOwnerNoSend3303').getValue()*/
  			};
  			Ext.apply(Ext.getCmp('cmbLocate_no3303')
  					.getStore().proxy.extraParams,
  					strWheresql);
  			Ext.getCmp('cmbLocate_no3303').getStore()
  					.removeAll();
  			Ext.getCmp('cmbLocate_no3303').getStore()
  					.load();
  			//loadgridOutstockMManSend3303();
         }else
         {
           
         }
	},
	
	//拣货发单手动成单》波次类型下拉
	/*cmbSource_type3303Select:function()
    {
    	 var listDetail  =  [];
         listDetail  =  Ext.decode(Ext.getCmp('cmbSource_type3303').getStore().getProxy().extraParams.str);
         if(!Ext.isEmpty(Ext.getCmp('cmbSource_type3303').getValue()))
         {
      	   var strdtl = {
      				columnId:'ood.source_type',
      				value:Ext.getCmp('cmbSource_type3303').getValue()
      			};
      	       listDetail.push(strdtl);
      			var strJson  =  Ext.encode(listDetail);
      			var strWheresql  =  {
      				str : strJson
      			};
      			Ext.apply(Ext.getCmp('cmbLocate_no3303')
      					.getStore().proxy.extraParams,
      					strWheresql);
      			Ext.getCmp('cmbLocate_no3303').getStore()
      					.removeAll();
      			Ext.getCmp('cmbLocate_no3303').getStore()
      					.load();
      			loadgridOutstockMManSend3303();
         }else
         {
           Ext.getCmp('cmbLocate_no3303').setValue(null);
      	   Ext.getCmp('cmbLocate_no3303').getStore().removeAll();
         }
	},*/
	//拣货发单按配送对象发单》波次下拉
	cmbLocate_noObj3303Select:function(){
		 var listDetail  =  [];
         listDetail  =  Ext.decode(Ext.getCmp('cmbLocate_no3303_Obj').getStore().getProxy().extraParams.str);
         if(!Ext.isEmpty(Ext.getCmp('cmbLocate_no3303_Obj').getValue()))
         {
      	   var strdtl = {
      				columnId:'ood.WAVE_No',
      				value:Ext.getCmp('cmbLocate_no3303_Obj').getValue()
      			};
      	       listDetail.push(strdtl);
      			var strJson  =  Ext.encode(listDetail);
      			var strWheresql  =  {
      				str : strJson
      			};
      			Ext.apply(Ext.getCmp('cmbm_batch_no3303_Obj').getStore().proxy.extraParams,
      					strWheresql);
      			Ext.getCmp('cmbm_batch_no3303_Obj').getStore()
      					.removeAll();
      			Ext.getCmp('cmbm_batch_no3303_Obj').getStore()
      					.load();
         }
	},
	
	//拣货发单按配送对象发单》批次下拉
	cmbm_batch_no3303_ObjSelect:function(){
		 var listDetail  =  [];
         listDetail  =  Ext.decode(Ext.getCmp('cmbm_batch_no3303_Obj').getStore().getProxy().extraParams.str);
         if(!Ext.isEmpty(Ext.getCmp('cmbm_batch_no3303_Obj').getValue()))
         {
      	   var strdtl = {
      				columnId:'ood.batch_no',
      				value:Ext.getCmp('cmbm_batch_no3303_Obj').getValue()
      			};
      	       listDetail.push(strdtl);
      			var strJson  =  Ext.encode(listDetail);
      			var strWheresql  =  {
      				str : strJson
      			};
      			Ext.apply(Ext.getCmp('cmbOutStockType3303_Obj').getStore().proxy.extraParams,
      					strWheresql);
      			Ext.getCmp('cmbOutStockType3303_Obj').getStore()
      					.removeAll();
      			Ext.getCmp('cmbOutStockType3303_Obj').getStore()
      					.load();
         }
	},
	//拣货发单手动成单》波次下拉
	cmbLocate_no3303Select:function(){
		 var listDetail  =  [];
         listDetail  =  Ext.decode(Ext.getCmp('cmbLocate_no3303').getStore().getProxy().extraParams.str);
         if(!Ext.isEmpty(Ext.getCmp('cmbLocate_no3303').getValue()))
         {
      	   var strdtl = {
      				columnId:'ood.WAVE_No',
      				value:Ext.getCmp('cmbLocate_no3303').getValue()
      			};
      	       listDetail.push(strdtl);
      			var strJson  =  Ext.encode(listDetail);
      			var strWheresql  =  {
      				str : strJson
      			};
      			Ext.apply(Ext.getCmp('cmbm_batch_no3303').getStore().proxy.extraParams,
      					strWheresql);
      			Ext.getCmp('cmbm_batch_no3303').getStore()
      					.removeAll();
      			Ext.getCmp('cmbm_batch_no3303').getStore()
      					.load();
      			//loadgridOutstockMManSend3303();
         }
	},
	
	//拣货发单手动成单》批次下拉
	cmbm_batch_no3303Select:function(){
		 var listDetail  =  [];
         listDetail  =  Ext.decode(Ext.getCmp('cmbm_batch_no3303').getStore().getProxy().extraParams.str);
         if(!Ext.isEmpty(Ext.getCmp('cmbm_batch_no3303').getValue()))
         {
      	   var strdtl = {
      				columnId:'ood.batch_no',
      				value:Ext.getCmp('cmbm_batch_no3303').getValue()
      			};
      	       listDetail.push(strdtl);
      			var strJson  =  Ext.encode(listDetail);
      			var strWheresql  =  {
      				str : strJson
      			};
      			Ext.apply(Ext.getCmp('cmbOutStockType3303').getStore().proxy.extraParams,
      					strWheresql);
      			Ext.getCmp('cmbOutStockType3303').getStore()
      					.removeAll();
      			Ext.getCmp('cmbOutStockType3303').getStore()
      					.load();
      			//loadgridOutstockMManSend3303();
         }
	},
	//拣货发单按配送对象发单》下架类型
	cmbOutStockTypeObj3303Select:function(th,newValue,oldValue,eOpts){
         if(!Ext.isEmpty(Ext.getCmp('cmbOutStockType3303_Obj').getValue()))
         {
        	 loadgridOutstockMObjSend3303();
         }
	},
	//拣货发单手动成单》下架类型
	cmbOutStockType3303Select:function(th,newValue,oldValue,eOpts){
		 var listDetail  =  [];
         listDetail  =  Ext.decode(Ext.getCmp('cmbOutStockType3303').getStore().getProxy().extraParams.str);
         if(!Ext.isEmpty(Ext.getCmp('cmbOutStockType3303').getValue()))
         {
      	   var strdtl = {
  				columnId:'ood.outstock_type',
  				value:Ext.getCmp('cmbOutStockType3303').getValue()
  			};
  	       listDetail.push(strdtl);
  			var strJson  =  Ext.encode(listDetail);
  			var strWheresql  =  {
  				str : strJson
  			};
  			Ext.apply(Ext.getCmp('cmbArea_no3303')
  					.getStore().proxy.extraParams,
  					strWheresql);
  			Ext.getCmp('cmbArea_no3303').getStore()
  					.removeAll();
  			Ext.getCmp('cmbArea_no3303').getStore()
  					.load();
  			//loadgridOutstockMManSend3303();
         }else
         {
         }
	},
	
	//拣货发单手动成单》储区代码
	cmbArea_no3303Select:function()
	{
		var listDetail  =  [];
		listDetail = Ext.decode(Ext.getCmp('cmbArea_no3303').getStore().getProxy().extraParams.str);
		if(!Ext.isEmpty(Ext.getCmp('cmbArea_no3303').getValue())){
			var wheresql;
			if(Ext.getCmp('cmbArea_no3303').getValue()!='ALL'){
				var strdtl = {
						columnId:'cda.ware_no||cda.area_no',
						value:Ext.getCmp('cmbArea_no3303').getValue()
				};
				listDetail.push(strdtl);
			
			}
			var strJson  =  Ext.encode(listDetail);
			wheresql  =  {
				str : strJson
			};
			Ext.apply(Ext.getCmp('cmbOperate_type3303')
					.getStore().proxy.extraParams,
					wheresql);
			Ext.getCmp('cmbOperate_type3303').setValue(null);
			Ext.getCmp('cmbOperate_type3303').getStore()
					.removeAll();
			Ext.getCmp('cmbOperate_type3303').getStore()
					.load();
		}
		
		//loadgridOutstockMManSend3303();
	},
	
	//拣货发单手动成单》作业类型
	cmbOperate_type3303Select:function(th,newValue,oldValue,eOpts){
		if(!Ext.isEmpty(Ext.getCmp('cmbOperate_type3303').getValue())){
			loadgridOutstockMManSend3303();
		}else{
			
		}
	},
	
	gridOutstockMObjSend3303beforeselect:function(th,record,index,eOpts){
		if(record.data.flag=='1')
		{
			Ext.example.msg('提示',"该配送对象需补货，请先补货！");
			return false;
		}
	},
	//拣货发单按配送对象发单》客户选择
	gridOutstockMObjSend3303selectionchange:function(){
		var objrecord  =  Ext.getCmp('gridOutstockMObjSend3303').getSelectionModel().getSelection();
        if(objrecord.length  ==  0){
    		Ext.getCmp('gridOutstockDObjSend3303').getStore().removeAll();
        }else{
            var varIds  =  "";
            for(var i  =  0; i < objrecord.length; i++){
            	varIds +=  objrecord[i].get("deliverObj");
                if(i<objrecord.length-1){
                	varIds  =  varIds + "','";
                }
            }
           
            var listDetail1  =  [];
            listDetail1 = Ext.decode(Ext.getCmp('gridOutstockMObjSend3303').getStore().getProxy().extraParams.str);
			var strDtl = {
				columnId:'ood.deliver_obj',
				condition:8,
				value:varIds
			};
			listDetail1.push(strDtl);
			var jsonStr  =  Ext.encode(listDetail1);
			var strwheresql  =  {
				str : jsonStr,
				strSendFlag:"man"
			};
			Ext.apply(Ext.getCmp('gridOutstockDObjSend3303')
					.getStore().proxy.extraParams,
					strwheresql);
			Ext.getCmp('gridOutstockDObjSend3303').getStore()
					.removeAll();
			Ext.getCmp('gridOutstockDObjSend3303').getStore()
					.load();
        }
	},
	gridOutstockMManSend3303beforeselect:function(th,record,index,eOpts){
		if(record.data.flag=='1')
		{
			Ext.example.msg('提示',"该客户需补货，请先补货！");
			return false;
		}
	},
	//拣货发单手动成单》客户选择
	gridOutstockMManSend3303selectionchange:function(){
		var objrecord  =  Ext.getCmp('gridOutstockMManSend3303').getSelectionModel().getSelection();
        if(objrecord.length  ==  0){
    		Ext.getCmp('gridOutstockDManSend3303').getStore().removeAll();
        }else{
            var varIds  =  "";
            for(var i  =  0; i < objrecord.length; i++){
            	varIds +=  objrecord[i].get("custNo");
                if(i<objrecord.length-1){
                	varIds  =  varIds + "','";
                }
            }
            /*Ext.MessageBox.show({
                title:"所选ID列表",
                msg:ids
                //icon: Ext.MessageBox.INFO
            });*/
            var listDetail1  =  [];
            listDetail1 = Ext.decode(Ext.getCmp('gridOutstockMManSend3303').getStore().getProxy().extraParams.str);
			var strDtl = {
				columnId:'ood.cust_no',
				condition:8,
				value:varIds
			};
			listDetail1.push(strDtl);
			var jsonStr  =  Ext.encode(listDetail1);
			var strwheresql  =  {
				str : jsonStr,
				strSendFlag:"man"
			};
			Ext.apply(Ext.getCmp('gridOutstockDManSend3303')
					.getStore().proxy.extraParams,
					strwheresql);
			Ext.getCmp('gridOutstockDManSend3303').getStore()
					.removeAll();
			Ext.getCmp('gridOutstockDManSend3303').getStore()
					.load();
        }
	},
	menu3303sendclick_obj:function(){
		var record  =  Ext.getCmp('gridOutstockMObjSend3303').getSelectionModel().getSelection();
		var intOutstockD = Ext.getCmp('gridOutstockDObjSend3303').getStore().count();
        if(intOutstockD  ==  0){
    		Ext.example.msg('提示',"请要发的单据！");
    		return;
        }else{
        	if(Ext.isEmpty(workSpaceNo))
			{
				Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
				return;
			}
        	if(Ext.isEmpty(Ext.getCmp('cmbWorkerNo3303_Obj').getValue())){
        		Ext.example.msg('提示',"请选择下架人员！");
        		return;
        	}
			var worker_no =  Ext.getCmp('cmbWorkerNo3303_Obj').getValue();
			Ext.Msg.confirm("提示", "确定发单？",
			function(button, text) {
				if (button  ==  'yes') {
					Ext.getCmp('butSend3303_Obj').setDisabled(true);
			       	var msgShow  =  commonMegShow("正在发单,请稍等...");
					var ids  =  "";
			       	var detail1  =  [];
			       	detail1 = Ext.decode(Ext.getCmp('gridOutstockMObjSend3303').getStore().getProxy().extraParams.str);
		            for(var i  =  0; i < record.length; i++){
		                ids +=  record[i].get("deliverObj");
		                if(i<record.length-1){
		                    ids  =  ids + "','";
		                }
		            }
					var d = {
						columnId:'ood.deliver_obj',
						condition:8,
						value:ids
					};
					detail1.push(d);
					var jsonStr  =  Ext.encode(detail1);
					var wheresql  =  {
						str : jsonStr
					};
					var jsonStr  =  Ext.encode(detail1);		
					var params  =  {
							//dockNo:dockNo,
							strPrintPaperType:Ext.getCmp('checkBoxPrint_type3303_Obj').getValue()==true ?1:0,
							workerNo:worker_no,
							strOutStockType:Ext.getCmp('cmbOutStockType3303_Obj').getValue(),
							str:jsonStr
					};
					Ext.Ajax.request({
						method:'POST',
						url:'odata_OutstockMAction_sendObj.action',
						params:params,
						success:function(response){
							Ext.getCmp('butSend3303_Obj').setDisabled(false);
							msgShow.hide();
							var data  =  Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg('提示',data.msg);
								Ext.getCmp('gridOutstockDObjSend3303').store.removeAll();
								Ext.getCmp('cmbExp_typeObj3303').setValue(null);
								Ext.getCmp('cmbOwnerNoObj3303').setValue(null);
								Ext.getCmp('cmbLocate_no3303_Obj').setValue(null);
								Ext.getCmp('cmbm_batch_no3303_Obj').setValue(null);
								Ext.getCmp('cmbOutStockType3303_Obj').setValue(null);
								Ext.getCmp('cmbOwnerNoObj3303').getStore().load();
								Ext.getCmp('cmbExp_typeObj3303').getStore().reload();
								commonEditButton('menu3303','save');
						        isCanEdit3101=false;
							}else{
								Ext.Msg.alert($i18n.prompt,data.msg+data.obj);

								//Ext.example.msg('提示',data.msg+data.obj);
							}				
						}
//					,
//						failure:function(response){
//							Ext.getCmp('butSend3303_Obj').setDisabled(false);
//							msgShow.hide();
//							Ext.example.msg('提示',"提交不上,可能是网络问题");
//						}
					});	
				}
			});
        }
	},
	menu3303sendclick:function(){
		var record  =  Ext.getCmp('gridOutstockMManSend3303').getSelectionModel().getSelection();
		var intOutstockD = Ext.getCmp('gridOutstockDManSend3303').getStore().count();
        if(intOutstockD  ==  0){
    		Ext.example.msg('提示',"请要发的单据！");
    		return;
        }else{
        	if(Ext.isEmpty(workSpaceNo))
			{
				Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
				return;
			}
        	if(Ext.isEmpty(Ext.getCmp('cmbWorkerNo3303').getValue())){
        		Ext.example.msg('提示',"请选择下架人员！");
        		return;
        	}
			
			Ext.Msg.confirm("提示", "确定发单？",
			function(button, text) {
				if (button  ==  'yes') {
					Ext.getCmp('butSend3303').setDisabled(true);
			       	var msgShow  =  commonMegShow("正在发单,请稍等...");
					var ids  =  "";
			       	var detail1  =  [];
			       //	detail1 = Ext.decode(Ext.getCmp('gridOutstockMManSend3303').getStore().getProxy().extraParams.str);
		            for(var i  =  0; i < record.length; i++){
		                ids +=  record[i].get("custNo");
		                if(i<record.length-1){
		                    ids  =  ids + "','";
		                }
		            }
					var d = {
						columnId:'ood.cust_no',
						condition:8,
						value:ids
					};
					detail1.push(d);
					var jsonStr  =  Ext.encode(detail1);
					var wheresql  =  {
						str : jsonStr
					};
					var jsonStr  =  Ext.encode(detail1);		
					var params  =  {
							//dockNo:dockNo,
							strPrintPaperType:Ext.getCmp('checkBoxPrint_type3303').getValue()==true ?1:0,
							workerNo:Ext.getCmp('cmbWorkerNo3303').getValue(),
							strOutStockType:Ext.getCmp('cmbOutStockType3303').getValue(),
						    ownerNo :Ext.getCmp('cmbOwnerNoSend3303').getValue(),
							expType: Ext.getCmp('cmbExp_typeSend3303').getValue(),
							waveNo :Ext.getCmp('cmbLocate_no3303').getValue(),
							batchNo:Ext.getCmp('cmbm_batch_no3303').getValue(),
							areaNo:Ext.getCmp('cmbArea_no3303').getValue(),//储区ware_no||area_no
							operateType :Ext.getCmp('cmbOperate_type3303').getValue(),//作业类型
							str:jsonStr
					};
					Ext.Ajax.request({
						method:'POST',
						url:'odata_OutstockMAction_send.action',
						params:params,
						success:function(response){
							Ext.getCmp('butSend3303').setDisabled(false);
							msgShow.hide();
							var data  =  Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg('提示',data.msg);
								//Ext.getCmp('gridOutstockMManSend3303').store.reload();
								//Ext.getCmp('gridOutstockMManSend3303').getForm().reset();
								Ext.getCmp('gridOutstockMManSend3303').store.removeAll();
								Ext.getCmp('gridOutstockDManSend3303').store.removeAll();
								Ext.getCmp('cmbExp_typeSend3303').setValue(null);
								Ext.getCmp('cmbOwnerNoSend3303').setValue(null);
								Ext.getCmp('cmbLocate_no3303').setValue(null);
								Ext.getCmp('cmbm_batch_no3303').setValue(null);
								Ext.getCmp('cmbOutStockType3303').setValue(null);
								Ext.getCmp('cmbArea_no3303').setValue(null);
								Ext.getCmp('cmbOperate_type3303').setValue(null);
								Ext.getCmp('cmbOwnerNoSend3303').getStore().load();
								Ext.getCmp('cmbExp_typeSend3303').getStore().reload();
								commonEditButton('menu3303','save');
						        isCanEdit3101=false;
							}else{
								Ext.Msg.alert($i18n.prompt,data.msg+data.obj);

								//Ext.example.msg('提示',data.msg+data.obj);
							}				
						}
						
					});	
				}
			});
        }
	},
	
	
    //拣货发单手动成单》刷新
	menu3303RefreshClick:function(){
		this.TabpanelChange();
	}
});
/**
 * 加载拣货发单按配送对象发单》配送对象信息
 */
function loadgridOutstockMObjSend3303(){
	var cmbOwnerNoObjSend3303 = Ext.getCmp('cmbOwnerNoObj3303').getValue();
	var cmbExp_typeObjSend3303 = Ext.getCmp('cmbExp_typeObj3303').getValue();
	var cmbLocate_noObj3303 = Ext.getCmp('cmbLocate_no3303_Obj').getValue();
	var cmbOutStockTypeObj3303 = Ext.getCmp('cmbOutStockType3303_Obj').getValue();
	var cmbm_batch_no3303_Obj = Ext.getCmp('cmbm_batch_no3303_Obj').getValue();
	
	if( !Ext.isEmpty(cmbOwnerNoObjSend3303) &&
		!Ext.isEmpty(cmbExp_typeObjSend3303) &&
		!Ext.isEmpty(cmbLocate_noObj3303) &&
		!Ext.isEmpty(cmbOutStockTypeObj3303) &&
		!Ext.isEmpty(cmbm_batch_no3303_Obj)){
		var listDetail  =  [];
		//增加cmbOwnerNoObjSend3303!='ALL'条件 huangb 20160803
		if(!Ext.isEmpty(cmbOwnerNoObjSend3303) && cmbOwnerNoObjSend3303 != 'ALL')
		{
			var strDtl = {
			columnId:'ood.owner_no',
			value:cmbOwnerNoObjSend3303
			};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(cmbExp_typeObjSend3303))
		{
			var strDtl = {
			columnId:'ood.exp_type',
			value:cmbExp_typeObjSend3303
			};
			listDetail.push(strDtl);
		}
	
		if(!Ext.isEmpty(cmbLocate_noObj3303))
		{
			var strDtl = {
			columnId:'ood.wave_no',
			value:cmbLocate_noObj3303
			};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(cmbm_batch_no3303_Obj))
		{
			var strDtl = {
			columnId:'ood.batch_no',
			value:cmbm_batch_no3303_Obj
			};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(cmbOutStockTypeObj3303))
		{
			var strDtl = {
			columnId:'ood.outstock_type',
			value:cmbOutStockTypeObj3303
			};
			listDetail.push(strDtl);
		}
		
		var jsonStr  =  Ext.encode(listDetail);
		var strwheresql  =  {
			str : jsonStr
		};
		Ext.apply(Ext.getCmp('gridOutstockMObjSend3303')
				.getStore().proxy.extraParams,
				strwheresql);
		Ext.getCmp('gridOutstockMObjSend3303').getStore()
				.removeAll();
		Ext.getCmp('gridOutstockMObjSend3303').getStore()
				.load();
	}

}
/**
 * 加载拣货发单手动成单》客户信息
 */
function loadgridOutstockMManSend3303()
{
	var cmbOwnerNoSend3303 = Ext.getCmp('cmbOwnerNoSend3303').getValue();
	var cmbExp_typeSend3303 = Ext.getCmp('cmbExp_typeSend3303').getValue();
	//var cmbSource_type3303 = Ext.getCmp('cmbSource_type3303').getValue();
	var cmbLocate_no3303 = Ext.getCmp('cmbLocate_no3303').getValue();
	var cmbm_batch_no3303 = Ext.getCmp('cmbm_batch_no3303').getValue();
	var cmbArea_no3303 = Ext.getCmp('cmbArea_no3303').getValue();
	var operate_type3303 = Ext.getCmp('cmbOperate_type3303').getValue();
	//formSendCondition
	
	if( !Ext.isEmpty(cmbOwnerNoSend3303) &&
		!Ext.isEmpty(cmbExp_typeSend3303) &&
		!Ext.isEmpty(cmbLocate_no3303) &&
		!Ext.isEmpty(cmbm_batch_no3303) &&
		!Ext.isEmpty(cmbArea_no3303) &&
		!Ext.isEmpty(cmbOperate_type3303)){
		var listDetail  =  [];
		//增加cmbOwnerNoSend3303!='ALL'条件 huangb 20160803
		if(!Ext.isEmpty(cmbOwnerNoSend3303) && cmbOwnerNoSend3303 != 'ALL')
		{
			var strDtl = {
			columnId:'ood.owner_no',
			value:cmbOwnerNoSend3303
			};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(cmbExp_typeSend3303))
		{
			var strDtl = {
			columnId:'ood.exp_type',
			value:cmbExp_typeSend3303
			};
			listDetail.push(strDtl);
		}
		/*if(!Ext.isEmpty(cmbSource_type3303))
		{
			var strDtl = {
			columnId:'ood.source_type',
			value:cmbSource_type3303
			};
			listDetail.push(strDtl);
		}*/
		if(!Ext.isEmpty(cmbLocate_no3303))
		{
			var strDtl = {
			columnId:'ood.wave_no',
			value:cmbLocate_no3303
			};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(cmbm_batch_no3303))
		{
			var strDtl = {
			columnId:'ood.batch_no',
			value:cmbm_batch_no3303
			};
			listDetail.push(strDtl);
		}
		
		if(!Ext.isEmpty(Ext.getCmp('cmbOutStockType3303').getValue()))
		{
			var strDtl = {
			columnId:'ood.outstock_type',
			value:Ext.getCmp('cmbOutStockType3303').getValue()
			};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(cmbArea_no3303))
		{
			if(cmbArea_no3303!='ALL'){
				var strDtl = {
				columnId:'cdc.ware_no||cdc.area_no',
				value:cmbArea_no3303
				};
				listDetail.push(strDtl);
			}
			
		}
		if(!Ext.isEmpty(operate_type3303))
		{
			if(operate_type3303!='ALL'){
				var strDtl = {
				columnId:'ood.operate_type',
				value:operate_type3303
				};
				listDetail.push(strDtl);
			}
			
		}
		var jsonStr  =  Ext.encode(listDetail);
		var strwheresql  =  {
			str : jsonStr
		};
		Ext.apply(Ext.getCmp('gridOutstockMManSend3303')
				.getStore().proxy.extraParams,
				strwheresql);
		Ext.getCmp('gridOutstockMManSend3303').getStore()
				.removeAll();
		Ext.getCmp('gridOutstockMManSend3303').getStore()
				.load();
	}
}