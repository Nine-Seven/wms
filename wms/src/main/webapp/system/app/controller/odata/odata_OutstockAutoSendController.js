/**
 * 模块名称：自动发单
 * 模块编码：3930
 * 创建：czh
 */
Ext.define('cms.controller.odata.odata_OutstockAutoSendController',{
	extend:'Ext.app.Controller',
	requires:[
          'cms.view.odata.odata_OutstockAutoSendUI'
	],
	model:'',
	store:'',
	init:function(){
		this.control({
			//自动发单》货主选择
			'odata_OutstockAutoSendUI bdef_DefOwnerCombo[id=cmbOwnerNoSend3930]':{
				change:this.cmbOwnerNoSend3930Select
			},
			//自动发单》出货单别下拉
			'odata_OutstockAutoSendUI combo[id=cmbExp_typeSend3930]':{
				change:this.cmbExp_typeSendChange
			},
			//自动发单》波次号下拉
			'odata_OutstockAutoSendUI combo[id=cmbWaveNoSend3930]':{
				change:this.cmbWaveNoSend3930Change
			},
			//自动发单》刷新
			'odata_OutstockAutoSendUI commMenuWidget4[id=menu3930] [name=refresh]':{
				click:this.menu3930RefreshClick
			}
		});	
	},
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		Ext.getCmp('gridOutstockInfo3930').getStore().removeAll();
		Ext.getCmp('cmbOwnerNoSend3930').getStore().load();
		//显示变量0为不显示，1为显示  add by huangcx at 20160528
		var packingUnit3930_1=commonGetModuleField('3930','packingUnit')[0].flag;
		var packingSpec3930_1=commonGetModuleField('3930','packingSpec')[0].flag;
		var packingUnit3930_2=commonGetModuleField('3930','packingUnit')[0].flag;
		var packingSpec3930_2=commonGetModuleField('3930','packingSpec')[0].flag;

		if(packingUnit3930_1==0){
			Ext.getCmp('packingUnit3930_1').setVisible(false);
		}
		if(packingSpec3930_1==0){
			Ext.getCmp('packingSpec3930_1').setVisible(false);
		}
		if(packingUnit3930_2==0){
			Ext.getCmp('packingUnit3930_2').setVisible(false);
		}
		if(packingSpec3930_2==0){
			Ext.getCmp('packingSpec3930_2').setVisible(false);
		}
		//end add
	},
	
	
	//拣货发单自动成单》货主选择
	cmbOwnerNoSend3930Select:function()
    {
		var listDetail  = [];
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNoSend3930').getValue()))
		{
			//有混合业主的情况 如果 为ALL 则不传委托业主 huangb 20160803
			if(Ext.getCmp('cmbOwnerNoSend3930').getValue()!='ALL'){
				var strDtl = {
					    columnId:'ood.owner_no',
						value:Ext.getCmp('cmbOwnerNoSend3930').getValue()
					};
				listDetail.push(strDtl);
			}
			var strJson = Ext.encode(listDetail);
			var strWheresql = {
				str : strJson,
				strSendFlag : "auto"
			};
			Ext.apply(Ext.getCmp('cmbExp_typeSend3930')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('cmbExp_typeSend3930').getStore()
					.removeAll();
			Ext.getCmp('cmbExp_typeSend3930').getStore()
					.load();
	        Ext.apply(Ext.getCmp('cmbWaveNoSend3930')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('cmbWaveNoSend3930').getStore()
					.removeAll();
			Ext.getCmp('cmbWaveNoSend3930').getStore()
					.load();
				
		}else
		{
			Ext.getCmp('cmbExp_typeSend3930').getStore().removeAll();
			Ext.getCmp('cmbExp_typeSend3930').setValue(null);
			Ext.getCmp('cmbWaveNoSend3930').getStore().removeAll();
			Ext.getCmp('cmbWaveNoSend3930').setValue(null);
		}
	
    },  
	//波次号下拉
	cmbWaveNoSend3930Change:function(){
		var listDetail = [];
       if(!Ext.isEmpty(Ext.getCmp('cmbExp_typeSend3930').getValue()))
       {
    	   var strdtl={
    				columnId:'ood.exp_type',
    				value:Ext.getCmp('cmbExp_typeSend3930').getValue()
    			};
    	       listDetail.push(strdtl);
    	       if(Ext.getCmp('cmbWaveNoSend3930').getValue()!='ALL'){
    	    	   var strdt2={
    	    				columnId:'ood.wave_no',
    	    				value:Ext.getCmp('cmbWaveNoSend3930').getValue()
    	    			};
	    	       listDetail.push(strdt2);
    	       }
    			var strJson = Ext.encode(listDetail);
    			var strWheresql = {
    				str : strJson,
    				strSendFlag:'auto',
    				strOwnerNo:Ext.getCmp('cmbOwnerNoSend3930').getValue()
    			};  			
    			Ext.apply(Ext.getCmp('cmbBatchNoSend3930')
						.getStore().proxy.extraParams,
						strWheresql);
				Ext.getCmp('cmbBatchNoSend3930').getStore()
						.removeAll();
				Ext.getCmp('cmbBatchNoSend3930').getStore()
						.load();				
       }else
       {
    	   Ext.getCmp('cmbBatchNoSend3930').getStore().removeAll();
    	   Ext.getCmp('cmbWaveNoSend3930').getStore()
			.removeAll();
       }
	},
	
	//拣货发单自动成单》出货单别下拉选择
	cmbExp_typeSendChange:function()
	{
       var listDetail = [];
       //listDetail = Ext.decode(Ext.getCmp('cmbExp_typeSend3930').getStore().getProxy().extraParams.str);
       if(!Ext.isEmpty(Ext.getCmp('cmbExp_typeSend3930').getValue()))
       {
    	   var strdtl={
    				columnId:'ood.exp_type',
    				value:Ext.getCmp('cmbExp_typeSend3930').getValue()
    			};
    	       listDetail.push(strdtl);
    			var strJson = Ext.encode(listDetail);
    			var strWheresql = {
    				str : strJson,
    				strSendFlag:'auto',
    				strOwnerNo:Ext.getCmp('cmbOwnerNoSend3930').getValue()
    			};
    			Ext.apply(Ext.getCmp('cmbWaveNoSend3930')
						.getStore().proxy.extraParams,
						strWheresql);
				Ext.getCmp('cmbWaveNoSend3930').getStore()
						.removeAll();
				Ext.getCmp('cmbWaveNoSend3930').getStore()
						.load();
				Ext.apply(Ext.getCmp('cmbBatchNoSend3930')
						.getStore().proxy.extraParams,
						strWheresql);
				Ext.getCmp('cmbBatchNoSend3930').getStore()
						.removeAll();
				Ext.getCmp('cmbBatchNoSend3930').getStore()
						.load();
       }else
       {
    	   Ext.getCmp('cmbBatchNoSend3930').getStore().removeAll();
    	   Ext.getCmp('cmbWaveNoSend3930').getStore()
			.removeAll();
       }
		
	},
	//刷新
	menu3930RefreshClick:function()
	{
		Ext.getCmp('gridOutstockInfo3930').getStore().removeAll();
		Ext.getCmp('cmbOwnerNoSend3930').getStore().load();
		Ext.getCmp('cmbExp_typeSend3930').getStore().load();
		Ext.getCmp('cmbWaveNoSend3930').getStore().load();
		Ext.getCmp('cmbBatchNoSend3930').getStore().load();
		Ext.getCmp('cmbWorkerNo3930').setValue("");
	}
});

