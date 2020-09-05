Ext.define('cms.view.odata.window.odata_outLocateStrategyDAddOrEditWindow', {
	extend:'Ext.window.Window',
	alias:'widget.odata_outLocateStrategyDAddOrEditWindow',
	layout : 'border',
	id : 'odata_outLocateStrategyDAddOrEditWindow',
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
		id:'odata_outLocateStrategyDAddOrEditWindowI802',
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
	 	    	fieldLabel:$i18n.locateStrategyId,//定位策略Id
	 	    	id:'locateStrategyIdI802_2',
	 	    	beforeLabelTextTpl : required

			},{
	 	    	xtype:'textfield',
	 	    	fieldLabel:$i18n.locateRuleIdI802,//规则id
	 	    	id:'locateRuleIdI802_2',
	 	    	beforeLabelTextTpl : required
			}
			//,{
			//	xtype:'textfield',
			//	fieldLabel: $i18n.enterpriseNoI802,//企业编号
			//	id : 'enterpriseNoI802_2',
			//	allowBlank: false,
			//	beforeLabelTextTpl : required
			// }
			,
			{
				xtype:'cdef_DefStockCombo',
				fieldLabel: $i18n.locateRuleNameI802,//规则名称
				id : 'cmblocateRuleNameI802',
				store:Ext.create("cms.store.common.comboStore",
				{
					proxy:{
						type:'ajax',
						method:'post',
						url:'bdef_WmsDefstrategyAction_getRuleIdQuery', //这个要改
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
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.deliverObjLevelI802,//配送对象级别
	 	    	id:'deliverObjLevelI802',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"OUT_LOCATE_STRATEGY,DELIVER_OBJ_LEVEL"}
				})
	 	    },{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.PFlagI802,//P型定位
	 	    	id:'PFlagI802',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	    		store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"OUT_LOCATE_STRATEGY,P_FLAG"}
				})
	 	    },{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.MFlagI802,//M型定位
	 	    	id:'MFlagI802',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"OUT_LOCATE_STRATEGY,M_FLAG"}
				})
	 	    },{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.WFlagI802,//W型定位
	 	    	id:'WFlagI802',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"OUT_LOCATE_STRATEGY,W_FLAG"}
				})
	 	    },{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.SFlagI802,//S型定位
	 	    	id:'SFlagI802',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"OUT_LOCATE_STRATEGY,S_FLAG"}
				})
	 	    },{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.DFlagI802,//D型定位
	 	    	id:'DFlagI802',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"OUT_LOCATE_STRATEGY,D_FLAG"}
				})
	 	    },
	 	   /* {
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.BDivideFlagI802,//B型拣货方式
	 	    	id:'BDivideFlagI80201',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"OUT_LOCATE_STRATEGY,LIMIT"}
				})
	 	    },{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.CDivideFlagI802,//C型拣货方式
	 	    	id:'CDivideFlagI80201',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"OUT_LOCATE_STRATEGY,LIMIT"}
				})
	 	    }, */
	 	    {
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.shortqtyTypeI802,//出货定位缺量处理方式
	 	    	id:'shortqtyTypeI802',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"OUT_LOCATE_STRATEGY,SHORTQTY_TYPE"}
				})
	 	    },{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.commitTypeI802,//出货定位提交方式
	 	    	id:'commitTypeI802',
	 	    	allowBlank:false,
	 	    	beforeLabelTextTpl : required,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"OUT_LOCATE_STRATEGY,COMMIT_TYPE"}
				})
	 	    },
			{
	 	    	xtype:'wms_DefFieldValCombo',
	 	    	fieldLabel:$i18n.Rsv_Control1I802,//'预留控制属性1',
				id:'Rsv_Control1I802',
				hidden:true,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
	 					{
	 							params:{str:"OUT_LOCATE_STRATEGY,LIMIT"}
	 					})
			},
			{
				xtype:'wms_DefFieldValCombo',
				fieldLabel:$i18n.Rsv_Control2I802,//'预留控制属性2',
				id:'Rsv_Control2I802',
				hidden:true,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
	 					{
	 							params:{str:"OUT_LOCATE_STRATEGY,LIMIT"}
	 					})
			},
			{
				xtype:'wms_DefFieldValCombo',
				fieldLabel:$i18n.Rsv_Control3I802,//'预留控制属性3',
				id:'Rsv_Control3I802',
				hidden:true,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
	 					{
	 							params:{str:"OUT_LOCATE_STRATEGY,LIMIT"}
	 					})
			},
			{
				xtype:'wms_DefFieldValCombo',
				fieldLabel:$i18n.Rsv_Control4I802,//'预留控制属性4',
				id:'Rsv_Control4I802',
				hidden:true,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
	 					{
	 							params:{str:"OUT_LOCATE_STRATEGY,LIMIT"}
	 					})
			},
			{
				xtype:'wms_DefFieldValCombo',
				fieldLabel:$i18n.Rsv_Control5I802,//'预留控制属性5',
				id:'Rsv_Control5I802',
				hidden:true,
	 	    	store:Ext.create("cms.store.common.comboStore").load(
	 					{
	 							params:{str:"OUT_LOCATE_STRATEGY,LIMIT"}
	 					})
			}
			//,{
			//	fieldLabel:$i18n.remark,//备注
			//	id:'memoI802',
			//	colspan:2,
			//	height : 60,
			//	width:558
		    //}
			]
		} ]},{
 			region:'south',
			xtype:'commMenuWidget5',
			border:0,
			id:'menuWidgetI802_2'
 	}
	]
});