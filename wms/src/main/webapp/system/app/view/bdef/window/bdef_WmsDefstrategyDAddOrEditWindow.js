Ext.define('cms.view.bdef.window.bdef_WmsDefstrategyDAddOrEditWindow', {
	extend:'Ext.window.Window',
	alias:'widget.bdef_WmsDefstrategyDAddOrEditWindow',
	layout : 'border',
	id : 'bdef_WmsDefstrategyDAddOrEditWindow',
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
		id:'bdef_WmsDefstrategyDEditI101',
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
	 	    	xtype:'textfield',
	 	    	fieldLabel:$i18n.strategyType,//策略类型
	 	    	id:'strategyTypeI101_2',
	 	    	beforeLabelTextTpl : required
			},{
	 	    	xtype:'textfield',
	 	    	fieldLabel:$i18n.strategy_id,//策略id
	 	    	id:'strategyIdI101_2',
	 	    	beforeLabelTextTpl : required
			},
			{
				xtype:'cdef_DefStockCombo',
				fieldLabel: $i18n.ruleId,//规则id
				id : 'cmbRuleIdI101',
				store:Ext.create("cms.store.common.comboStore",
				{
					proxy:{
						type:'ajax',
						method:'post',
						url:'bdef_WmsDefstrategyAction_getRuleIdQuery',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						} 
					}
				}),
				colspan:2,
				width:558,
				allowBlank: true,
	 	    	beforeLabelTextTpl : required
			},{
				xtype:'textfield',
		    	fieldLabel : $i18n.ruleOrder,//规则顺序
	   	        id : 'ruleOrderI101',
	   	        beforeLabelTextTpl : required
			},{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.limmit_mixbatch,//混批属性
	 	    	id:'climmitMixbatchI101',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"N,LIMIT"}
				})
	 	    },{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.limmit_mixarticle,//混商品
	 	    	id:'limmitMixarticleI101',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	    		store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"N,MIX"}
				})
	 	    },{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.limmit_maxqty,//最大限制量
	 	    	id:'limmitMaxqtyI101',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"N,LIMIT"}
				})
	 	    },{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.limmit_maxcase,//最大箱数
	 	    	id:'limmitMaxcaseI101',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"N,LIMIT"}
				})
	 	    },{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.limmit_maxweight,//最大重量
	 	    	id:'limmitMaxweightI101',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"N,LIMIT"}
				})
	 	    },{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.limmit_maxgroupno,//最大品类数
	 	    	id:'limmitMaxgroupnoI101',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"N,LIMIT"}
				})
	 	    },{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.limmit_celluse,//可用货位数
	 	    	id:'limmitCelluseI101',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"N,LIMIT"}
				})
	 	    },
			{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.limmit_rsv01,//'预留限制1',
				id:'limmitRsv01',
				hidden:true,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
	 					{
	 							params:{str:"N,LIMIT"}
	 					})
			},
			{
				xtype:'wms_DefFieldValCombo',
				fieldLabel:$i18n.limmit_rsv02,//'预留限制2',
				id:'limmitRsv02',
				hidden:true,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
	 					{
	 							params:{str:"N,LIMIT"}
	 					})
			},
			{
				xtype:'wms_DefFieldValCombo',
				fieldLabel:$i18n.limmit_rsv03,//'预留限制3',
				id:'limmitRsv03',
				hidden:true,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
	 					{
	 							params:{str:"N,LIMIT"}
	 					})
			},
			{
				xtype:'wms_DefFieldValCombo',
				fieldLabel:$i18n.limmit_rsv04,//'预留限制4',
				id:'limmitRsv04',
				hidden:true,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
	 					{
	 							params:{str:"N,LIMIT"}
	 					})
			},
			{
				xtype:'wms_DefFieldValCombo',
				fieldLabel:$i18n.limmit_rsv05,//'预留限制5',
				id:'limmitRsv05',
				hidden:true,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
	 					{
	 							params:{str:"N,LIMIT"}
	 					})
			},
			{
				xtype:'wms_DefFieldValCombo',
				fieldLabel:$i18n.limmit_rsv06,//'预留限制6',
				id:'limmitRsv06',
				hidden:true,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
	 					{
	 							params:{str:"N,LIMIT"}
	 					})
			},{
				fieldLabel:$i18n.remark,//备注
				id:'memoI101',
				colspan:2,
				height : 60,
				width:558
		    }]
		} ]},{
 			region:'south',
			xtype:'commMenuWidget5',
			border:0,
			id:'menuWidgetI101_2'
 	}
	]
});
