Ext.define('cms.view.odata.window.odata_WmsWavePlanMAddOrEditWindow', {
	extend:'Ext.window.Window',
	alias:'widget.odata_WmsWavePlanMAddOrEditWindow',
	layout : 'border',
	id : 'odata_WmsWavePlanMAddOrEditWindow',
	height:200,
	width:400,	
	modal:true,
	requires:['cms.view.common.commMenu5',
	          'cms.view.common.wms_DefFieldValCombo',
	          'cms.view.common.bdef_DefOwnerCombo'
	],
	items:[{
		xtype:'form',
		region:'center',
		width:'100%',
	    height:'100%',
		id:'odata_WmsWavePlanMAddOrEditWindow3914',
		frame:true,
		layout:'form',
		
		defaults:{
			xtype:'textfield',
			margin:'9 9 9 9',
			labelWidth:100,
			labelAlign:'right'
		},
		items:[{
 	    	xtype:'textfield',
 	    	fieldLabel:'波次规则号',//波次规则号
 	    	id:'strategyId3914',
 	    	allowBlank:false,
 	    	readOnly:true,
 	    	beforeLabelTextTpl : required
		},{
 	    	xtype:'textfield',
 	    	fieldLabel:'规则名称',//规则名称
 	    	id:'strategyName3914',
 	    	allowBlank:false,
 	    	beforeLabelTextTpl : required
 	    }/*,{
        	xtype: 'wms_DefFieldValCombo',
        	fieldLabel:'订单类型',//出货单别
        	id:'expType3914',
        	beforeLabelTextTpl : required,
        	store:Ext.create('cms.store.common.comboStore',{

				 proxy:{
						type:'ajax',
						method:'post',
						url:'odata_WmsWavePlanAction_queryExpTypeCombo',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						}
				}
//        	,	 
//				listeners:{  
//						'load':function(th,records,successful,eOpts ){
//							if(th.count()>0){
//								Ext.getCmp('expType3914').setValue(th.getAt(0).data.value);
//							}
//						}
//					}
			}).load()      
 	    },{
			xtype:'numberfield',
			fieldLabel:'间隔时间(分)',
			minValue:1,
			id : 'betweenTimes3914',
			beforeLabelTextTpl : required
	}*/]},{
 			region:'south',
			xtype:'commMenuWidget5',
			border:0,
			id:'menuWidgetPlanM3914'
 	}
	]
});
