Ext.define('cms.view.odata.window.odata_outLocateStrategyAddOrEditWindow', {
	extend:'Ext.window.Window',
	alias:'widget.odata_outLocateStrategyAddOrEditWindow',  //
	layout : 'border',
	id : 'odata_outLocateStrategyAddOrEditWindow',
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
		id:'odata_outLocateStrategyAddOrEditI802',
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
 	    	fieldLabel:$i18n.locateStrategyId,//定位策略id
 	    	id:'locateStrategyIdI802',
 	    	beforeLabelTextTpl : required
		},{
			xtype:'textfield',
			fieldLabel: $i18n.strategyNameI802,//策略名称
			id : 'strategyNameI802',
			allowBlank: false,
			beforeLabelTextTpl : required
		},{
 	    	xtype:'bdef_DefDockCombo',  //不知是否要改
 	    	fieldLabel:$i18n.DefaultFlagI802,//是否默认
 	    	id:'cmbDefaultFlagI802',
 	    	store:Ext.create("cms.store.common.comboStore").load(
			{
					params:{str:"OUT_LOCATE_STRATEGY,DEFALUT_FLAG"}
			}),
 	    	beforeLabelTextTpl : required
 	    }
		//,{
		//	xtype:'textfield',
		//	fieldLabel: $i18n.enterpriseNoI802,//企业编号
		//	id : 'enterpriseNoI802',
		//	allowBlank: false,
		//	beforeLabelTextTpl : required
		//}
 	    ]},{
 			region:'south',
			xtype:'commMenuWidget5',
			border:0,
			id:'menuWidgetI802_1'
 	}
	]
});