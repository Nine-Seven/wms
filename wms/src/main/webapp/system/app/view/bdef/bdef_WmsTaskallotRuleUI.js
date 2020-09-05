/** 模块名称：任务切分规则配置
 * 模块编码：I201
 * 创建：huangb 20160608
 * 增加onedeliveronepickText(同一个配送对象一次性拣货：0-不一次拣货；1-要求一次拣货) huangb 20160801
 */
var bdef_WmsTaskallotRuleMInfo = Ext.create('cms.store.bdef.bdef_WmsTaskallotRuleMStore',
		{
			autoLoad: true,
			listeners:{  
				'load':function(th,records,successful,eOpts ){
					if(Ext.getCmp('bdef_WmsTaskallotRuleUIGridI201').getStore().count()>0){
						Ext.getCmp('bdef_WmsTaskallotRuleUIGridI201').getSelectionModel().select(0);
					}else{
						Ext.getCmp('bdef_WmsTaskallotRuleUIGridI201').getStore()
						.removeAll();
					}
				}
			}
	    }
    );
var bdef_WmsTaskallotRuleDetail = Ext.create('cms.store.bdef.bdef_WmsTaskallotRuleStore',{autoLoad: false});

Ext.define('cms.view.bdef.bdef_WmsTaskallotRuleUI',{
	alias:'widget.bdef_WmsTaskallotRuleUI',
	title:$i18n.titleI201,//任务切分规则配置
	width:'90%',
	height:'90%',
	layout:'border',
	requires:[
				'cms.view.common.commMenu2',
				'cms.view.common.commMenu5',
				'cms.view.common.commMenu6',
				'cms.view.common.cdef_DefAreaCombo',
				'cms.view.common.cdef_DefWareCombo',
				'cms.view.common.cdef_DefStockCombo',
				'cms.view.common.wms_DefFieldValCombo',
				'cms.view.common.bdef_DefDockCombo'
				],
	extend:'Ext.panel.Panel',
	//总页面布局开始
	items:[
	        //工具条
			{
				xtype : 'commMenuWidget2',
				region:'north',
				id:'menuI201'
			},
			//头档网格对象开始
			{
				xtype:'grid',
				id:'bdef_WmsTaskallotRuleUIGridI201',
				region:'center',
				store : bdef_WmsTaskallotRuleMInfo,
				columns:[
				    //序列号  
					{
						xtype : 'rownumberer',
					    width : 30
					},
					//任务切分规则
					{
						width : 300,
						text : $i18n.task_rule,
						dataIndex : 'taskRuleidText'
					},
					//切单方式
					{
						width : 200,
						text : $i18n.cutFlag,
						dataIndex : 'ruleTypeText'
					},
					//切单方式对应的值
					{
						width : 80,
						text : $i18n.para_value,
						dataIndex : 'paraValue'
					},
					//发单方式
					{
						width : 120,
						text : $i18n.task_type,
						dataIndex : 'taskTypeText'
					},
					//打印类型
					{
						width : 150,
						text : $i18n.print_type,
						dataIndex : 'printTypeText'
					},
					//拣货限制
					{
						width : 120,
						text : $i18n.onedeliveronepick,
						dataIndex : 'onedeliveronepickText'
					},
					//发单设备
					{
						width : 100,
						text : $i18n.task_get_type,
						dataIndex : 'taskGetTypeText'
					},
					//备注
					{
						width : 350,
						text : $i18n.memo,
						dataIndex : 'memo'
					}
				],
				//分页显示条
			    dockedItems : [
				    {
					xtype : 'pagingtoolbar',
					store : bdef_WmsTaskallotRuleMInfo,
					dock : 'bottom',
					displayInfo : true
				    }
			    ] 
			},//头档网格对象结束
			
			//页面下部分布局开始
            {
				height : 270,
		    	region:'south',
		    	layout:'border',
		    	//页面下部分明细布局开始
		    	items:[
                        //页面下部分工具条开始
						{
						    xtype:'commMenuWidget6',
						    id:'menuI201_2',
						    region:'north',
						    items:[ 
						            {
							    	   text : $i18n.additem,
							    	   name : 'detailAdd',
							    	   iconCls : 'add'//新增
							        }, 
							        {
							    	   text : $i18n.titleupdate,
							    	   name : 'detailEdit',
							    	   iconCls : 'edit' //修改
								    },
							        {
							    	   text : $i18n.delete_1,
							    	   name : 'detailDelete',
							    	   iconCls : 'delete'//删除
							        }
							]
						},//页面下部分工具条结束
						//明细网格对象开始
						{
							xtype:'grid',
							id:'bdef_WmsTaskallotRuleDUIGridI201',
							region:'center',
							store : bdef_WmsTaskallotRuleDetail,
							columns:[
								//序列号  
								{
									xtype : 'rownumberer',
								    width : 30
								},
								//下架类型
								{
									width : 120,
									text : $i18n.outstock_type,
									dataIndex : 'outstockTypeText'
								},
								//作业类型
								{
									width : 100,
									text : $i18n.operate_type,
									dataIndex : 'operateTypeText'
								},
								//切单范围
								{
									width : 100,
									text : $i18n.allot_rule,
									dataIndex : 'allotRuleText'
								},
								//切单规则
								{
									width : 200,
									text : $i18n.box_flag,
									dataIndex : 'boxFlagText'
								},
								//切单规则对应的值
								{
									width : 80,
									text : $i18n.para_value,
									dataIndex : 'paraValue'
								},
								//发单方式
								{
									width : 120,
									text : $i18n.task_type,
									dataIndex : 'taskTypeText'
								},
								//打印类型
								{
									width : 160,
									text : $i18n.print_type,
									dataIndex : 'printTypeText'
								},
								//发单设备
								{
									width : 100,
									text : $i18n.task_get_type,
									dataIndex : 'taskGetTypeText'
								},
								//备注
								{
									width : 300,
									text : $i18n.memo,
									dataIndex : 'memo'
								}
							]
						}//明细网格对象结束
		    	]//页面下部分明细布局结束
            }//页面下部分布局结束
          ]//总页面布局结束
});
