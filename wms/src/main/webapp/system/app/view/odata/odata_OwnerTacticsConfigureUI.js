/**
 * 模块名称：货主出货策略配置
 * 模块代码：3921
 * @author hekl
 */
var wms_OwnerOutorderStore = Ext.create('cms.store.wms.wms_OwnerOutorderStore',{autoLoad:true});
Ext.define('cms.view.odata.odata_OwnerTacticsConfigureUI',{
	alias:'widget.odata_OwnerTacticsConfigureUI',
	title:$i18n.title3921,//货主出货策略配置
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu',
	          'cms.view.common.commMenu2',
	          'cms.view.common.commMenu3',
	          'cms.view.common.commMenu5',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.wms_DefFieldValCombo'
	          ],
	items:[
	{
		xtype:'tabpanel',
		id:'tabPId3921',
	    region:'center',
	    items:[
	    {
	    	title:'策略单据',
	    	layout:'border',
	    	id:'tabPId3921_T1',
	    	items:[
	    	{
	    		xtype:'commMenuWidget3',
	    	    region:'north'
	    	},{
			    xtype:'grid',
			    region:'center',
			    height:220,
			    id:'grid_01_3921',
			    store:wms_OwnerOutorderStore,
			    columns:[
			    {			
					xtype : 'rownumberer',
					width : 30
			    },{
					width : 80,
					text : $i18n.ownerno,//货主
					dataIndex:'ownerNo'
			    },{
					width : 100,
					text : $i18n.owner_name,//货主名称
					dataIndex:'ownerName'
			    },{
					width : 100,
					text : $i18n.outstocktype,//单据类型
					dataIndex:'expType'
			    },{
					width : 100,
					text : $i18n.exptypeText,//单据名称
					dataIndex:'exptypeText'
			    },{
					width : 100,
					text : $i18n.priority,//订单优先级
					dataIndex:'priority'
			    },{
					width : 100,
					text : $i18n.deliver_obj,//配送对象
					dataIndex:'deliverObjLevelText'
			    },{
					width : 120,
					text : $i18n.mflagText,//是否支持M类型定位
					dataIndex:'mflagText'
			    },{
					width : 120,
					text : $i18n.wflagText,//是否支持W类型定位
					dataIndex:'wflagText'
			    },{
					width : 120,
					text : $i18n.sflagText,//是否支持S类型定位
					dataIndex:'sflagText'
			    },{
					width : 120,
					text : $i18n.dflagText,//是否支持D类型定位
					dataIndex:'dflagText'
			    },{
					width : 120,
					text : $i18n.bdivideflagText,//B是否在可分播区域采
					dataIndex:'bdivideflagText'
			    },{
					width : 120,
					text : $i18n.cdivideflagText,//C是否在可分播区域
					dataIndex:'cdivideflagText'
			    }],
			    dockedItems : [{
					xtype : 'pagingtoolbar',
					dock : 'bottom',
					store : wms_OwnerOutorderStore,
					displayInfo : true
			    }]
			}
	    	]
	    },
	    {
	    	title:'策略明细',
	    	layout:'border',
	    	id:'tabPId3921_T2',
	    	items:[
	    	{
				xtype:'commMenuWidget',
				region:'north',
				id:'menu3921'
			},
			{
				xtype : 'form',
			    region:'north',
			    frame : true,
			    layout:{
		    		type:'table',
		    		columns:4
		    	},
		    	defaults : {
					xtype:'textfield',
					labelWidth : 110,
					margin : '2 2 4 2',
					labelAlign : 'right'
		  		},
			    items :[
			    {
					xtype:'bdef_DefOwnerCombo',
					fieldLabel : $i18n.owner_no,//委托业主编号
					id : 'owner_no3921',					
	        		readOnly:true,
	        		displayField : 'dropValue',
	    		    valueField : 'value',
	    	        allowBlank : false,
	    	        editable:false,
	    			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
	        		beforeLabelTextTpl : required
		  		},{
			    	xtype:'wms_DefFieldValCombo',
		    		fieldLabel:$i18n.exp_type,//出货单别
		    		id:'cmbExpType3921',
		    		store:Ext.create("cms.store.common.comboStore").load(
		    		{
		       	 		params:{str:"N,EXP_TYPE"}
		    		}),
		    		editable:false,
		    		allowBlank:false,
		    		readOnly:true,
		    		beforeLabelTextTpl:required
			    },
			    {
			    	xtype:'wms_DefFieldValCombo',
			    	fieldLabel:'订单优先级',
			    	id:'cmbPriority3921',
		    		store:Ext.create("cms.store.common.comboStore").load(
		    		{
		       	 		params:{str:"ODATA_EXP_M,PRIORITY"}
		    		}),
		    		editable:false,
		    		allowBlank:false,
		    		readOnly:true,
		    		beforeLabelTextTpl:required
			    },
			    {
			    	xtype:'wms_DefFieldValCombo',
			    	fieldLabel: $i18n.deliver_obj, //配送对象
			    	id:'cmbDeliverObjLevel3921',
		    		store:Ext.create("cms.store.common.comboStore").load(
		    		{
		       	 		params:{str:"WMS_OUTORDER,DELIVER_OBJ_LEVEL"}
		    		}),
		    		editable:false,
		    		allowBlank:false,
		    		readOnly:true,
		    		beforeLabelTextTpl:required
			    }
			    ]
			},
	    	{
				xtype : 'form',
			    id : 'form_01_3921',
			    region:'west',
			    width:'70%',
			    frame : true,
			    layout:{
		    		type:'table',
		    		columns:1
		    	},
		    	defaults : {
					xtype:'textfield',
					labelWidth : 100,
					margin : '1 2 2 1',
					labelAlign : 'right'
		  		},
			    items :[
		        {
		        	xtype:'fieldset',
		            layout: 
		            {
		            	type: 'table',
		            	columns: 3
		            },
		            defaults : 
		            {
		            	xtype : 'textfield',
		            	labelWidth : 100,
		            	margin:'2 0 0 0 ',
		            	labelAlign:'right'			
		       	    },
		   	        items:[
		   	        {
				    	xtype:'wms_DefFieldValCombo',
				    	fieldLabel: $i18n.mflagText,//'多门店整板拣货,M型定位
				    	id:'cmbMflag3921',
			    		store:Ext.create("cms.store.common.comboStore").load(
			    		{
			       	 		params:{str:"WMS_OUTORDER,M_FLAG"}
			    		}),
			    		editable:false,
			    		allowBlank:false,
			    		readOnly:true,
			    		beforeLabelTextTpl:required
				    },
				    {
				    	xtype:'wms_DefFieldValCombo',
				    	fieldLabel: $i18n.wflagText,//'多门店补拣合一 W型定位
				    	id:'cmbWflag3921',
			    		store:Ext.create("cms.store.common.comboStore").load(
			    		{
			       	 		params:{str:"WMS_OUTORDER,W_FLAG"}
			    		}),
			    		editable:false,
			    		allowBlank:false,
			    		readOnly:true,
			    		beforeLabelTextTpl:required
				    },
				    {
				    	xtype:'wms_DefFieldValCombo',
				    	fieldLabel: $i18n.dflagText,//'多门店合箱拣货' D型定位
				    	id:'cmbDflag3921',
			    		store:Ext.create("cms.store.common.comboStore").load(
			    		{
			       	 		params:{str:"WMS_OUTORDER,D_FLAG"}
			    		}),
			    		editable:false,
			    		allowBlank:false,
			    		readOnly:true,
			    		beforeLabelTextTpl:required
				    },
			    	{
			    		xtype:'wms_DefFieldValCombo',
				    	fieldLabel: $i18n.sflagText,//'单门店补拣合一' S型定位
				    	id:'cmbSflag3921',
			    		store:Ext.create("cms.store.common.comboStore").load(
			    		{
			       	 		params:{str:"WMS_OUTORDER,S_FLAG"}
			    		}),
			    		editable:false,
			    		allowBlank:false,
			    		readOnly:true,
			    		beforeLabelTextTpl:required
				    }
		   	        ]
		        },
		        {
		        	xtype:'fieldset',
		            layout: 
		            {
		            	type: 'table',
		            	columns: 3
		            },
		            defaults : 
		            {
		            	xtype : 'textfield',
		            	labelWidth : 100,
		            	margin:'2 0 0 0 ',
		            	labelAlign:'right'			
		       	    },
		   	        items:[
		   	        {
			    		xtype:'wms_DefFieldValCombo',
				    	fieldLabel: $i18n.bdivideflagText,//'拆零分播',
				    	id:'cmbBdivideFlag3921',
			    		store:Ext.create("cms.store.common.comboStore").load(
			    		{
			       	 		params:{str:"WMS_OUTORDER,B_DIVIDE_FLAG"}
			    		}),
			    		editable:false,
			    		allowBlank:false,
			    		readOnly:true,
			    		beforeLabelTextTpl:required
				    },
				    {
				    	xtype:'wms_DefFieldValCombo',
				    	fieldLabel: $i18n.cdivideflagText,//'整箱分播',
				    	id:'cmbCdivideFlag3921',
			    		store:Ext.create("cms.store.common.comboStore").load(
			    		{
			       	 		params:{str:"WMS_OUTORDER,C_DIVIDE_FLAG"}
			    		}),
			    		editable:false,
			    		allowBlank:false,
			    		readOnly:true,
			    		beforeLabelTextTpl:required
				    },
				    {
				    	xtype:'wms_DefFieldValCombo',
				    	fieldLabel:'发货暂存区',
				    	id:'cmbSendbufCompute3921',
			    		store:Ext.create("cms.store.common.comboStore").load(
			    		{
			       	 		params:{str:"WMS_OUTORDER,SENDBUF_COMPUTE"}
			    		}),
			    		editable:false,
			    		allowBlank:false,
			    		readOnly:true,
			    		beforeLabelTextTpl:required
				    },
				    {
				    	xtype:'wms_DefFieldValCombo',
				    	fieldLabel:'定位提交方式',
				    	id:'cmbCommitType3921',
			    		store:Ext.create("cms.store.common.comboStore").load(
			    		{
			       	 		params:{str:"WMS_OUTORDER,COMMIT_TYPE"}
			    		}),
			    		editable:false,
			    		allowBlank:false,
			    		readOnly:true,
			    		beforeLabelTextTpl:required
				    },
				    {
				    	xtype:'wms_DefFieldValCombo',
				    	fieldLabel:'缺量处理方式',
				    	id:'cmbShortqtyType3921',
			    		store:Ext.create("cms.store.common.comboStore").load(
			    		{
			       	 		params:{str:"WMS_OUTORDER,SHORTQTY_TYPE"}
			    		}),
			    		editable:false,
			    		allowBlank:false,
			    		readOnly:true,
			    		beforeLabelTextTpl:required
				    },
				    {
				    	xtype:'wms_DefFieldValCombo',
				    	fieldLabel:'线路',
				    	id:'cmbLineFlag3921',
			    		store:Ext.create("cms.store.common.comboStore").load(
			    		{
			       	 		params:{str:"WMS_OUTORDER,LINE_FLAG"}
			    		}),
			    		editable:false,
			    		allowBlank:false,
			    		readOnly:true,
			    		beforeLabelTextTpl:required
				    },
				    {
				    	xtype:'wms_DefFieldValCombo',
				    	fieldLabel:'定位异常区',
				    	id:'cmbAbnormalFlag3921',
			    		store:Ext.create("cms.store.common.comboStore").load(
			    		{
			       	 		params:{str:"WMS_OUTORDER,ABNORMAL_FLAG"}
			    		}),
			    		editable:false,
			    		allowBlank:false,
			    		readOnly:true,
			    		beforeLabelTextTpl:required
				    }
		   	        ]
		        },
		        {
		        	xtype:'fieldset',
		            layout: 
		            {
		            	type: 'table',
		            	columns: 3
		            },
		            defaults : 
		            {
		            	xtype : 'textfield',
		            	labelWidth : 100,
		            	margin:'2 0 0 0 ',
		            	labelAlign:'right'			
		       	    },
		   	        items:[
		   	        {
		   	        	xtype:'wms_DefFieldValCombo',
				    	fieldLabel:'复核台滑道',
				    	id:'cmbRecheckCompute3921',
			    		store:Ext.create("cms.store.common.comboStore").load(
			    		{
			       	 		params:{str:"WMS_OUTORDER,RECHECK_COMPUTE"}
			    		}),
			    		editable:false,
			    		allowBlank:false,
			    		readOnly:true,
			    		beforeLabelTextTpl:required
		   	        },
		   	        {
		   	        	xtype:'wms_DefFieldValCombo',
				    	fieldLabel:'分拣机滑道',
				    	id:'cmbSorterCompute3921',
			    		store:Ext.create("cms.store.common.comboStore").load(
			    		{
			       	 		params:{str:"WMS_OUTORDER,SORTER_COMPUTE"}
			    		}),
			    		editable:false,
			    		allowBlank:false,
			    		readOnly:true,
			    		beforeLabelTextTpl:required
		   	        },
		   	        {
		   	        	xtype:'wms_DefFieldValCombo',
				    	fieldLabel:'物流批次',
				    	id:'cmbBatchCompute3921',
			    		store:Ext.create("cms.store.common.comboStore").load(
			    		{
			       	 		params:{str:"WMS_OUTORDER,BATCH_COMPUTE"}
			    		}),
			    		editable:false,
			    		allowBlank:false,
			    		readOnly:true,
			    		beforeLabelTextTpl:required
		   	        }
		   	        ]
		        },
		        {
		        	xtype:'fieldset',
		            layout: 
		            {
		            	type: 'table',
		            	columns: 3
		            },
		            defaults : 
		            {
		            	xtype : 'textfield',
		            	labelWidth : 100,
		            	margin:'2 0 0 0 ',
		            	labelAlign:'right'			
		       	    },
		   	        items:[
		   	        {
		   	        	xtype:'wms_DefFieldValCombo',
				    	fieldLabel:$i18n.strategyId,//'波次规则号',
				    	id:'cmbstrategyId3921',
				    	displayField : 'dropValue',
						valueField : 'strategyId',
						store:Ext.create("cms.store.odata.odata_WmsWavePlanMStore",{
							proxy:{
								type:'ajax',
								method:'post',
								url:'odata_TacticsAction_queryStrategy',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								}
							}
						}),
			    		readOnly:true
		   	        },
		   	        {
				    	fieldLabel:$i18n.strategyName,//规则名称
				    	id:'strategyName3921',			    
			    		readOnly:true
		   	        },
		   	        {
				    	fieldLabel:$i18n.betweenTimes,//间隔时间（分）
				    	id:'betweenTimes3921',
			    		readOnly:true
		   	        }
		   	        ]
		        },{
		        	xtype:'fieldset',
		            layout: 
		            {
		            	type: 'table',
		            	columns: 3
		            },
		            defaults : 
		            {
		            	xtype : 'textfield',
		            	labelWidth : 100,
		            	margin:'2 0 0 0 ',
		            	labelAlign:'right'			
		       	    },
		   	        items:[
		   	        {
		   	        	xtype:'wms_DefFieldValCombo',
				    	fieldLabel:$i18n.p_flag,//支持P型定位
				    	id:'pFlag_3921',
	     				forceSelection : false,
				    	store:Ext.create("cms.store.common.comboStore").load(
					    		{
					       	 		params:{str:"N,ALLOW"}
					    		}),
			    		readOnly:true
		   	        },
		   	        {
		   	        	xtype:'wms_DefFieldValCombo',
				    	fieldLabel:$i18n.batch_compute_type,//批次切分方式
				    	id:'batchComputeType_3921',
				    	forceSelection : false,
				    	store:Ext.create("cms.store.common.comboStore").load(
					    		{
					       	 		params:{str:"WMS_OUTORDER,BATCH_COMPUTE_TYPE"}
					    		}),
			    		readOnly:true
		   	        },
		   	        {
		   	        	xtype:'wms_DefFieldValCombo',
				    	fieldLabel:$i18n.send_compute_level,//月台货位试算级别
				    	id:'sendComputeLevel_3921',
				    	forceSelection : false,
				    	store:Ext.create("cms.store.common.comboStore").load(
					    		{
					       	 		params:{str:"WMS_OUTORDER,SEND_COMPUTE_LEVEL"}
					    		}),
			    		readOnly:true
		   	        },
		   	        {
		   	        	xtype:'wms_DefFieldValCombo',
				    	fieldLabel:$i18n.B_work_flow_flag,//B型摘果拣货回单调出货工作流
				    	id:'bWorkFlowFlag_3921',
				    	forceSelection : false,
				    	store:Ext.create("cms.store.common.comboStore").load(
					    		{
					       	 		params:{str:"N,CALL"}
					    		}),
			    		readOnly:true
		   	        },
		   	        {
		   	        	xtype:'wms_DefFieldValCombo',
				    	fieldLabel:$i18n.C_work_flow_flag,//C型摘果拣货回单调出货工作流
				    	id:'cWorkFlowFlag_3921',
				    	forceSelection : false,
				    	store:Ext.create("cms.store.common.comboStore").load(
					    		{
					       	 		params:{str:"N,CALL"}
					    		}),
			    		readOnly:true
		   	        },
		   	        {
		   	        	xtype:'wms_DefFieldValCombo',
				    	fieldLabel:$i18n.autodivideflag,//分播自动回单标识
				    	id:'autodivideflag_3921',
				    	forceSelection : false,
				    	store:Ext.create("cms.store.common.comboStore").load(
					    		{
					       	 		params:{str:"N,AUTO"}
					    		}),
			    		readOnly:true
		   	        },
		   	        {
		   	        	xtype:'wms_DefFieldValCombo',
				    	fieldLabel:$i18n.autooutstockflag,//拣货自动回单标识
				    	id:'autooutstockflag_3921',
				    	forceSelection : false,
				    	store:Ext.create("cms.store.common.comboStore").load(
					    		{
					       	 		params:{str:"N,AUTO"}
					    		}),
			    		readOnly:true
		   	        }
		   	        ]
		        },
		        {
		        	xtype:'fieldset',
		            layout: 
		            {
		            	type: 'table',
		            	columns: 3
		            },
		            defaults : 
		            {
		            	xtype : 'textfield',
		            	labelWidth : 100,
		            	margin:'2 0 0 0 ',
		            	labelAlign:'right'			
		       	    },
		   	        items:[
		   	        {
				    	fieldLabel:'预留字段1',
				    	id:'txtOrderRsv01_3921'
		   	        },
		   	        {
				    	fieldLabel:'预留字段2',
				    	id:'txtOrderRsv02_3921'
		   	        },
		   	        {
				    	fieldLabel:'预留字段3',
				    	id:'txtOrderRsv03_3921'
		   	        },
		   	        {
				    	fieldLabel:'预留字段4',
				    	id:'txtOrderRsv04_3921'
		   	        },
		   	        {
				    	fieldLabel:'预留字段5',
				    	id:'txtOrderRsv05_3921'
		   	        },
		   	        {
				    	fieldLabel:'预留字段6',
				    	id:'txtOrderRsv06_3921'
		   	        },
		   	        {
				    	fieldLabel:'预留字段7',
				    	id:'txtOrderRsv07_3921'
		   	        },
		   	        {
				    	fieldLabel:'预留字段8',
				    	id:'txtOrderRsv08_3921'
		   	        }
		   	        ]
		        }
		    	]
		    },{
			    xtype:'grid',
			    id:'grid_02_3921',
			    region:'east',
			    width:'30%',
			    region:'center',
			    store:Ext.create('cms.store.wms.wms_OwnerOutorderFlowStore',{autoLoad:false}),
			    columns:[
		    	{
		    	  	xtype: 'checkcolumn',
					width : 50,
					columnHeaderCheckbox: true,//必须定义如下store
					store: Ext.data.StoreManager.lookup('wms_OwnerOutorderFlowStore'),
					dataIndex:'flag'
		        },{
					width:120,
					text:'工作流顺序',
					dataIndex:'flowOrder'
			    },{
					width:200,
					text:'工作流内容',
					dataIndex:'flowvalueText'
			    }]
			},
			{
		    	region:'south'
			}]
	    }]
		
	    }]
});