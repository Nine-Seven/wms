Ext.define('cms.view.odata.window.odata_CheckStrategyAddOrEditWindow', {
	extend:'Ext.window.Window',
	alias:'widget.odata_CheckStrategyAddOrEditWindow',
	layout : 'border',
	id : 'odata_CheckStrategyAddOrEditWindow',
	height:310,
	width:600,	
	modal:true,
	requires:['cms.view.common.commMenu5',
	          'cms.view.common.cdef_DefStockCombo',
	          'cms.view.common.wms_DefFieldValCombo',
	          'cms.store.common.comboStore'],
	items:[{
		xtype:'form',
		region:'center',
		id:'odata_CheckStrategyAddOrEditI804',
		frame:true,
		layout:'column',
		items : [ {
			layout : {
			type : 'table',
			columns : 2
			},
			xtype : 'container',
			defaults:{
				xtype:'textfield',
				margin:'3 3 3 3',
				labelWidth:120,
				labelAlign:'right'
			},

			items:[
			{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.checkTypeI804,//复核类型
	 	    	id:'checkTypeI804',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"CHECK_STRATEGY,CHECK_TYPE"}//
				})
	 	    },{
	 	    	xtype:'textfield',
	 	    	fieldLabel:$i18n.checkStrategyIdI804,//策略id
	 	    	id:'checkStrategyIdI804',
	 	    	beforeLabelTextTpl : required
			}
			,{
				xtype:'textfield',
				fieldLabel: $i18n.checkStrategyNameI804,//策略名称
				id : 'checkStrategyNameI804',
				allowBlank: false,
				beforeLabelTextTpl : required
			 }
			,{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.skipPickFlagI804,//拣货标识
	 	    	id:'skipPickFlagI804',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"N,ALLOW"}
				})
	 	    },{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.checkLevelI804,//复核级别
	 	    	id:'checkLevelI804',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	    		store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"CHECK_STRATEGY,CHECK_LEVEL"}
				})
	 	    },{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.autoCloseBoxI804,//自动封箱
	 	    	id:'autoCloseBoxI804',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"N,AUTO"}
				})
	 	    },{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.packAdvanceI804,//包材标识
	 	    	id:'packAdvanceI804',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"CHECK_STRATEGY,PACK_ADVANCE"}
				})
	 	    },{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.weightFlagI804,//重量标识
	 	    	id:'weightFlagI804',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"N,SCAN_FLAG"}
				})
	 	    },{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.volumFlagI804,//材积标识
	 	    	id:'volumFlagI804',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"N,SCAN_FLAG"}
				})
	 	    },
			{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.rsvValue1I804,//'预留值属性1',
				id:'rsvValue1I804',
				hidden:true,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
	 					{
	 							params:{str:"OUT_LOCATE_STRATEGY,LIMIT"}
	 					})
			},
			{
				xtype:'wms_DefFieldValCombo',
				fieldLabel:$i18n.rsvValue2I804,//'预留值属性2',
				id:'rsvValue2I804',
				hidden:true,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
	 					{
	 							params:{str:"OUT_LOCATE_STRATEGY,LIMIT"}
	 					})
			},
			{
				xtype:'wms_DefFieldValCombo',
				fieldLabel:$i18n.rsvValue3I804,//'预留值属性3',
				id:'rsvValue3I804',
				hidden:true,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
	 					{
	 							params:{str:"OUT_LOCATE_STRATEGY,LIMIT"}
	 					})
			},
			{
				xtype:'wms_DefFieldValCombo',
				fieldLabel:$i18n.rsvValue4I804,//'预留值属性4',
				id:'rsvValue4I804',
				hidden:true,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
	 					{
	 							params:{str:"OUT_LOCATE_STRATEGY,LIMIT"}
	 					})
			},
			{
				xtype:'wms_DefFieldValCombo',
				fieldLabel:$i18n.rsvValue5I804,//'预留值属性5',
				id:'rsvValue5I804',
				hidden:true,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
	 					{
	 							params:{str:"OUT_LOCATE_STRATEGY,LIMIT"}
	 					})
			}]
		} ]},{
 			region:'south',
			xtype:'commMenuWidget5',
			border:0,
			id:'menuWidgetI804_1'
 	}
	]
});