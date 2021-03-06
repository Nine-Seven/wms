Ext.define('cms.view.bdef.window.bdef_WmsTaskallotRuleDAddOrEditWindow', 
	{
		extend:'Ext.window.Window',
		alias:'widget.bdef_WmsTaskallotRuleDAddOrEditWindow',
		id:'bdef_WmsTaskallotRuleDAddOrEditWindow',
		width:650,
		height:280,
		modal:true,
		layout:'border',
		requires:[
		    'cms.view.common.commMenu5',
		    'cms.view.common.wms_DefFieldValCombo',
		    'cms.store.common.comboStore'
		],
		items:[
			{
				xtype:'form',
				region:'center',
				id:'bdef_WmsTaskallotRuleDAddOrEditWindowI201',
				frame:true,
				layout:'column',
				items:[{
					frame:true,
					layout:{
						type:'table',
						columns:2
				    },
					defaults:{
						xtype:'textfield',
						margin:'3 3 3 3',
						labelWidth:120,  
						width:300,
						labelAlign:'right'
					},
			
					items:[
					    //下架类型
					    {
					    	xtype:'wms_DefFieldValCombo',
							fieldLabel: $i18n.outstock_type,
							id:'cmbOutstockTypeI201_2',
							store:Ext.create("cms.store.common.comboStore").load({params:{str:"N,OUTSTOCK_TYPE"}}),
							allowBlank:false,
							beforeLabelTextTpl:required
					    },
					    //作业类型
					    {
					    	xtype:'wms_DefFieldValCombo',
							fieldLabel: $i18n.operate_type,
							id:'cmbOperateTypeI201_2',
							store:Ext.create("cms.store.common.comboStore").load({params:{str:"N,OPERATE_TYPE"}}),
							allowBlank:false,
							beforeLabelTextTpl:required
					    },
					    //切单范围
					    {
					    	xtype:'wms_DefFieldValCombo',
							fieldLabel: $i18n.allot_rule,
							id:'cmbAllotRuleI201_2',
							colspan:2,
							store:Ext.create("cms.store.common.comboStore").load({params:{str:"WMS_TASKALLOT_RULE,ALLOT_RULE"}}),
							allowBlank:false,
							beforeLabelTextTpl:required
					    },
					    //切单规则
					    {
					    	xtype:'wms_DefFieldValCombo',
							fieldLabel: $i18n.box_flag,
							id:'cmbBoxFlagI201_2',
							colspan:2,
							width:610,
							store:Ext.create("cms.store.common.comboStore").load({params:{str:"WMS_TASKALLOT_RULE,BOX_FLAG"}}),
							allowBlank:false,
							beforeLabelTextTpl:required
					    },
					    //切单方式对应的值
					    {
					    	xtype:'numberfield',
				 	    	fieldLabel:$i18n.para_value,
				 	    	id:'textParaValueI201_2',
				 	    	maxLength:3,
				 	    	minValue:0,
				 	    	maxValue:999, 
				 	    	allowBlank:false,
				 	    	beforeLabelTextTpl:required
					    },
					    //发单方式
					    {
					    	xtype:'wms_DefFieldValCombo',
							fieldLabel: $i18n.task_type,
							id:'cmbTaskTypeI201_2',
							store:Ext.create("cms.store.common.comboStore").load({params:{str:"ODATA_OUTSTOCK_M,TASK_TYPE"}}),
							allowBlank:false,
							beforeLabelTextTpl:required
					    },
					    //打印类型
					    {
					    	xtype:'wms_DefFieldValCombo',
							fieldLabel: $i18n.print_type,
							id:'cmbPrintTypeI201_2',
							colspan:2,
							width:610,
							store:Ext.create("cms.store.common.comboStore").load({params:{str:"WMS_TASKALLOT_RULE,PRINT_TYPE"}}),
							allowBlank:false,
							beforeLabelTextTpl:required
					    },
					    //发单设备
					    {
					    	xtype:'wms_DefFieldValCombo',
							fieldLabel: $i18n.task_get_type,
							id:'cmbTaskGetTypeI201_2',
							colspan:2,
							store:Ext.create("cms.store.common.comboStore").load({params:{str:"N,TASK_GET_TYPE"}}),
							allowBlank:false,
							beforeLabelTextTpl:required
					    },
					    //备注
					    {
					    	xtype:'textfield',
				 	    	fieldLabel:$i18n.memo,
				 	    	id:'textMemoI201_2',
				 	    	maxLength:100,
				 	    	width:610,
				 	    	colspan:2
					    }
					] 
			    }]
			},
			{
				region:'south',
				xtype:'commMenuWidget5',
				border:0,
				id:'menuWidget5I201_2'
			}
		]
    }
);