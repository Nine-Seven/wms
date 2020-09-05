Ext.define('cms.view.bdef.window.bdef_WmsTaskallotRuleMAddOrEditWindow', 
	{
		extend:'Ext.window.Window',
		alias:'widget.bdef_WmsTaskallotRuleMAddOrEditWindow',
		id:'bdef_WmsTaskallotRuleMAddOrEditWindow',
		width:650,
		height:230,
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
				id:'bdef_WmsTaskallotRuleMAddOrEditWindowI201',
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
					    //任务切分规则
					    {
					    	xtype:'numberfield',
				 	    	fieldLabel:$i18n.task_rule,
				 	    	id:'textTaskRuleidI201',
				 	    	maxLength:4,
				 	    	minValue:0,
				 	    	maxValue:9999, 
				 	    	colspan:2,
				 	    	allowBlank:false,
				 	    	beforeLabelTextTpl:required
					    },
					    //波次规则
//					    {
//					    	xtype:'wms_DefFieldValCombo',
//				 	    	fieldLabel:$i18n.wava_rule,
//				 	    	id:'cmbWavaRuleI201',
//				 	    	store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
//			 					 proxy:{
//			     					type:'ajax',
//			     					method:'post',
//			     					url:'bdef_WmsTaskallotRuleMAction_getWaveRuleForUI',
//			   						reader:{
//			     						root:'rootList',
//			     						totalProperty:'totalCount'
//			    					}
//			   					}
//			   			    }).load(),
//			   			    displayField : 'dropValue',
//						    valueField : 'value',
//				 	    	colspan:2,
//							width:610,
//				 	    	allowBlank:false,
//				 	    	beforeLabelTextTpl:required
//					    },
					    //切单方式
					    {
					    	xtype:'wms_DefFieldValCombo',
							fieldLabel: $i18n.cutFlag,
							id:'cmbRuleTypeI201',
							store:Ext.create("cms.store.common.comboStore").load({params:{str:"WMS_TASKALLOT_RULE_M,RULE_TYPE"}}),
							allowBlank:false,
							beforeLabelTextTpl:required
					    },
					    //切单方式对应的值
					    {
					    	xtype:'numberfield',
				 	    	fieldLabel:$i18n.para_value,
				 	    	id:'textParaValueI201',
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
							id:'cmbTaskTypeI201',
							store:Ext.create("cms.store.common.comboStore").load({params:{str:"ODATA_OUTSTOCK_M,TASK_TYPE"}}),
							allowBlank:false,
							beforeLabelTextTpl:required
					    },
					    //打印类型
					    {
					    	xtype:'wms_DefFieldValCombo',
							fieldLabel: $i18n.print_type,
							id:'cmbPrintTypeI201',
							store:Ext.create("cms.store.common.comboStore").load({params:{str:"WMS_TASKALLOT_RULE,PRINT_TYPE"}}),
							allowBlank:false,
							beforeLabelTextTpl:required
					    },
					    //拣货限制
					    {
					    	xtype:'wms_DefFieldValCombo',
							fieldLabel: $i18n.onedeliveronepick,
							id:'cmbOnedeliveronepickI201',
							store:Ext.create("cms.store.common.comboStore").load({params:{str:"WMS_TASKALLOT_RULE_M,ONEDELIVERONEPICK"}}),
							allowBlank:false,
							beforeLabelTextTpl:required
					    },
					    //发单设备
					    {
					    	xtype:'wms_DefFieldValCombo',
							fieldLabel: $i18n.task_get_type,
							id:'cmbTaskGetTypeI201',
							store:Ext.create("cms.store.common.comboStore").load({params:{str:"N,TASK_GET_TYPE"}}),
							allowBlank:false,
							beforeLabelTextTpl:required
					    },
					    //备注
					    {
					    	xtype:'textfield',
				 	    	fieldLabel:$i18n.memo,
				 	    	id:'textMemoI201',
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
				id:'menuWidget5I201'
			}
		]
    }
);