/**
 * 模块名称：电商自动发单
 * 模块编码：3304
 * 创建：huangb 20160706
 */
Ext.define('cms.controller.odata.odata_OutstockAutoSendECController',{
	extend:'Ext.app.Controller',
	requires:[
          'cms.view.odata.odata_OutstockAutoSendECUI'
	],
	model:'',
	store:'',
	init:function(){
		this.control({
			//自动发单》货主选择
			'odata_OutstockAutoSendECUI bdef_DefOwnerCombo[id=cmbOwnerNoSend3304]':{
				change:this.cmbOwnerNoSend3304Select
			},
			//自动发单》出货单别下拉
			'odata_OutstockAutoSendECUI combo[id=cmbExp_typeSend3304]':{
				change:this.cmbExp_typeSendChange
			},
			//自动发单》波次号下拉
			'odata_OutstockAutoSendECUI combo[id=cmbWaveNoSend3304]':{
				change:this.cmbWaveNoSend3304Change
			},
			//自动发单》刷新
			'odata_OutstockAutoSendECUI commMenuWidget4[id=menu3304] [name=refresh]':{
				click:this.menu3304RefreshClick
			}
		});	
	},
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		Ext.getCmp('gridOutstockInfo3304').getStore().removeAll();
		Ext.getCmp('cmbOwnerNoSend3304').getStore().load();
		//显示变量0为不显示，1为显示  add by huangcx at 20160528
		var packingUnit3304_1=commonGetModuleField('3304','packingUnit')[0].flag;
		var packingSpec3304_1=commonGetModuleField('3304','packingSpec')[0].flag;
		var packingUnit3304_2=commonGetModuleField('3304','packingUnit')[0].flag;
		var packingSpec3304_2=commonGetModuleField('3304','packingSpec')[0].flag;

		if(packingUnit3304_1==0){
			Ext.getCmp('packingUnit3304_1').setVisible(false);
		}
		if(packingSpec3304_1==0){
			Ext.getCmp('packingSpec3304_1').setVisible(false);
		}
		if(packingUnit3304_2==0){
			Ext.getCmp('packingUnit3304_2').setVisible(false);
		}
		if(packingSpec3304_2==0){
			Ext.getCmp('packingSpec3304_2').setVisible(false);
		}
		//end add
	},
	
	
	//拣货发单自动成单》货主选择
	cmbOwnerNoSend3304Select:function()
    {
		var listDetail  = [];
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNoSend3304').getValue()))
		{
			//有混合业主的情况 如果 为ALL 则不传委托业主 huangb 20160803
			if(Ext.getCmp('cmbOwnerNoSend3304').getValue()!='ALL'){
				var strDtl = {
					    columnId:'ood.owner_no',
						value:Ext.getCmp('cmbOwnerNoSend3304').getValue()
					};
				listDetail.push(strDtl);
			}
			var strJson = Ext.encode(listDetail);
			var strWheresql = {
				str : strJson,
				strSendFlag : "auto"
			};
			Ext.apply(Ext.getCmp('cmbExp_typeSend3304')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('cmbExp_typeSend3304').getStore()
					.removeAll();
			Ext.getCmp('cmbExp_typeSend3304').getStore()
					.load();
	        Ext.apply(Ext.getCmp('cmbWaveNoSend3304')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('cmbWaveNoSend3304').getStore()
					.removeAll();
			Ext.getCmp('cmbWaveNoSend3304').getStore()
					.load();
				
		}else
		{
			Ext.getCmp('cmbExp_typeSend3304').getStore().removeAll();
			Ext.getCmp('cmbExp_typeSend3304').setValue(null);
			Ext.getCmp('cmbWaveNoSend3304').getStore().removeAll();
			Ext.getCmp('cmbWaveNoSend3304').setValue(null);
		}
	
    },  
	//波次号下拉
	cmbWaveNoSend3304Change:function(){
		var listDetail = [];
       if(!Ext.isEmpty(Ext.getCmp('cmbExp_typeSend3304').getValue()))
       {
    	   var strdtl={
    				columnId:'ood.exp_type',
    				value:Ext.getCmp('cmbExp_typeSend3304').getValue()
    			};
    	       listDetail.push(strdtl);
    	       if(Ext.getCmp('cmbWaveNoSend3304').getValue()!='ALL'){
    	    	   var strdt2={
    	    				columnId:'ood.wave_no',
    	    				value:Ext.getCmp('cmbWaveNoSend3304').getValue()
    	    			};
	    	       listDetail.push(strdt2);
    	       }
    			var strJson = Ext.encode(listDetail);
    			var strWheresql = {
    				str : strJson,
    				strSendFlag:'auto',
    				strOwnerNo:Ext.getCmp('cmbOwnerNoSend3304').getValue()
    			};  			
    			Ext.apply(Ext.getCmp('cmbBatchNoSend3304')
						.getStore().proxy.extraParams,
						strWheresql);
				Ext.getCmp('cmbBatchNoSend3304').getStore()
						.removeAll();
				Ext.getCmp('cmbBatchNoSend3304').getStore()
						.load();				
       }else
       {
    	   Ext.getCmp('cmbBatchNoSend3304').getStore().removeAll();
    	   Ext.getCmp('cmbWaveNoSend3304').getStore()
			.removeAll();
       }
	},
	
	//拣货发单自动成单》出货单别下拉选择
	cmbExp_typeSendChange:function()
	{
       var listDetail = [];
       //listDetail = Ext.decode(Ext.getCmp('cmbExp_typeSend3304').getStore().getProxy().extraParams.str);
       if(!Ext.isEmpty(Ext.getCmp('cmbExp_typeSend3304').getValue()))
       {
    	   var strdtl={
    				columnId:'ood.exp_type',
    				value:Ext.getCmp('cmbExp_typeSend3304').getValue()
    			};
    	       listDetail.push(strdtl);
    			var strJson = Ext.encode(listDetail);
    			var strWheresql = {
    				str : strJson,
    				strSendFlag:'auto',
    				strOwnerNo:Ext.getCmp('cmbOwnerNoSend3304').getValue()
    			};
    			Ext.apply(Ext.getCmp('cmbWaveNoSend3304')
						.getStore().proxy.extraParams,
						strWheresql);
				Ext.getCmp('cmbWaveNoSend3304').getStore()
						.removeAll();
				Ext.getCmp('cmbWaveNoSend3304').getStore()
						.load();
				Ext.apply(Ext.getCmp('cmbBatchNoSend3304')
						.getStore().proxy.extraParams,
						strWheresql);
				Ext.getCmp('cmbBatchNoSend3304').getStore()
						.removeAll();
				Ext.getCmp('cmbBatchNoSend3304').getStore()
						.load();
       }else
       {
    	   Ext.getCmp('cmbBatchNoSend3304').getStore().removeAll();
    	   Ext.getCmp('cmbWaveNoSend3304').getStore()
			.removeAll();
       }
		
	},
	//刷新
	menu3304RefreshClick:function()
	{
		Ext.getCmp('gridOutstockInfo3304').getStore().removeAll();
		Ext.getCmp('cmbOwnerNoSend3304').getStore().load();
		Ext.getCmp('cmbExp_typeSend3304').getStore().load();
		Ext.getCmp('cmbWaveNoSend3304').getStore().load();
		Ext.getCmp('cmbBatchNoSend3304').getStore().load();
		Ext.getCmp('cmbWorkerNo3304').setValue("");
	}
});

