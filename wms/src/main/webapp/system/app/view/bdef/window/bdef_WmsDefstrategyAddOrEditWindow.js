Ext.define('cms.view.bdef.window.bdef_WmsDefstrategyAddOrEditWindow', {
	extend:'Ext.window.Window',
	alias:'widget.bdef_WmsDefstrategyAddOrEditWindow',
	layout : 'border',
	id : 'bdef_WmsDefstrategyAddOrEditWindow',
	height:150,
	width:610,	
	modal:true,
	requires:['cms.view.common.commMenu2',
			  'cms.view.common.commMenu5',
			  'cms.view.common.cdef_DefAreaCombo',
			  'cms.view.common.cdef_DefWareCombo',
			  'cms.view.common.cdef_DefStockCombo',
			  'cms.store.common.comboStore'
	],
	items:[{
		xtype:'form',
		region:'center',
		id:'bdef_WmsDefstrategyAddOrEditI101',
		frame:true,
		layout:'column',
		defaults:{
			xtype:'textfield',
			margin:'5 3 5 3',
			labelWidth:120,
			labelAlign:'right'
		},
		items:[{
 	    	xtype:'textfield',
 	    	fieldLabel:$i18n.strategy_id,//策略id
 	    	id:'strategyIdI101',
 	    	beforeLabelTextTpl : required
		},{
			xtype:'cdef_DefStockCombo',
			fieldLabel: $i18n.strategyType,//策略类型
			id : 'cmbStrategyTypeI101',
			store:Ext.create("cms.store.common.comboStore").load(
					{
							params:{str:"N,STRATEGY"}
					}),
//			store:Ext.create("cms.store.common.comboStore",
//			{
//				proxy:{
//					type:'ajax',
//					method:'post',
//					url:'bdef_WmsDefstrategyAction_getStrategyType',
//					reader:{
//						root:'rootList',
//						totalProperty:'totalCount'
//					} 
//				}
//			}),
			allowBlank: true
		},{
			xtype:'textfield',
			fieldLabel: $i18n.strategyTypeName,//策略名称
			id : 'strategyTypeNameI101',
			allowBlank: false,
			beforeLabelTextTpl : required
		},{
 	    	xtype:'bdef_DefDockCombo',
 	    	fieldLabel:$i18n.default_flag,//是否默认
 	    	id:'cmbDefaultFlagI101',
 	    	store:Ext.create("cms.store.common.comboStore").load(
			{
					params:{str:"N,DEFAULT_FLAG"}
			}),
 	    	beforeLabelTextTpl : required
 	    }]},{
 			region:'south',
			xtype:'commMenuWidget5',
			border:0,
			id:'menuWidgetI101_1'
 	}
	]
});
