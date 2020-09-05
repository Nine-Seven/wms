/**
 * 模块名称：计费取值方式维护UI
 * 模块编码：B904
 * 创建：czh 
 */

var cost_BillingRuleType_ListStore=Ext.create('cms.store.cost.cost_BillingRuleTypeListStore',{autoLoad:true,
	listeners:{
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('cost_BillingRuleType_ListGrid').getStore().count()>0){
				Ext.getCmp('cost_BillingRuleType_ListGrid').getSelectionModel().select(0);
			}
		}
	}}
		);
var cost_BillingRuleStore=Ext.create('cms.store.cost.cost_Billing_RuleStore',{autoLoad:true});

Ext.define('cms.view.cost.cost_BillingRuleUI',{
	alias:'widget.cost_BillingRuleUI',
	title: "计费取值方式维护",//计费取值方式维护
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	id:'cost_BillingRuleUI',
	requires:['cms.view.common.commMenu2',
	          'cms.view.common.commMenu4',
			  'cms.view.common.commMenu5',
			  'cms.view.common.wms_DefFieldValCombo'
	         ],
	         items:[
	                {
	 					xtype:'grid',
						id:'cost_BillingRuleType_ListGrid',
						title : $i18n.billingRuleTypeList,//计费类型列表
						store : cost_BillingRuleType_ListStore,
						region:'west',
						width : '30%',
						columns:[{
							width : 100,
							text : $i18n.billingType1,//计费类型编码
							dataIndex : 'billingTypeText'
							},/*{
							width : 100,
							text : $i18n.billingTypeName,//计费类型名称
							dataIndex : 'billingTypeName'
							},*/{
							width : 50,
							text : $i18n.status,//计费类型状态
							dataIndex : 'statusText'
							},{
							width : 190,
							text : $i18n.memo,//计费类型说明
							dataIndex : 'memo'
							}],
							dockedItems : [ {
								xtype : 'commMenuWidget4',
								id:'menuB904'
							},{
	             		        xtype : 'pagingtoolbar',
	             			    store : cost_BillingRuleType_ListStore,
	             			    dock : 'bottom',
	             			    displayInfo : true
	             			}]
					},{//计费取值方式
						xtype : 'grid',
						id : 'cost_Billing_Rule_Grid',
						title : $i18n.billingRule,
						region:'center',
						store : cost_BillingRuleStore,
						columns : [ {
							width : 40,
							text : $i18n.row_id,
							dataIndex : 'ruleId'
							}, {
							width : 100,
							text : $i18n.unit1,
							dataIndex : 'billingUnitText'
							},{
								width : 100,
								text : $i18n.unit1,
								dataIndex : 'billingUnit',
								hidden:true
								},{
							width : 500,
							text : $i18n.valueFlag,
							dataIndex : 'strategyName'
							},/*{
								width : 150,
								text : $i18n.standardSql,
								dataIndex : 'standardSql'
							},{
								width : 150,
								text : $i18n.nonStandardSql,
								dataIndex : 'nonstandardSql'
							},*/{
								width : 80,
								text : $i18n.status,
								dataIndex : 'statusText'
							},{
								width : 300,
								text : $i18n.memo,
								dataIndex : 'memo'
							}],
							dockedItems : [ {
								xtype:'toolbar',
			   				    items : [{
									text : $i18n.titleadd,//新增
									iconCls : 'add',
									name : 'detailAdd'
								},{
									text : $i18n.titleupdate,//修改
									iconCls : 'edit',
									name : 'detailEdit'
								},{
									text : $i18n.delete_1,//删除
									iconCls : 'delete',
									//hidden:true,
									name : 'detailDelete'
								},{
									text : $i18n.find,//查找
									iconCls : 'query',
									name : 'detailQuery'
								},{
									text : $i18n.refresh,//刷新
									iconCls : 'refresh',
									name : 'DetailRefresh'
								}]
							},{
							    xtype:'form',
							    id:'formBillingRuleB904',
							    region:'north',
							    right: 0,
								frame : true,
							    layout:{
						   			type:'table',
						   			columns:1
							    },
							    defaults : {
							   		labelWidth : 65,
							   		labelAlign:'right'			
							   	},
							    items:[{
							   		xtype:'textfield',
									xtype:'wms_DefFieldValCombo',
									fieldLabel:$i18n.unit1,//单位
								    id:'cmbBillingUnitB904',
								    store:Ext.create("cms.store.common.comboStore").load(
										    {
													params:{str:"COST_FORMULASET,BILLING_UNIT"}
										    }),
									displayField : 'dropValue',
									valueField : 'value'
								}]
						    },{
	             		        xtype : 'pagingtoolbar',
	             		        store : cost_BillingRuleStore,
	             			    dock : 'bottom',
	             			    displayInfo : true
	             			}]
					}]          
});