/**
 * 模块名称：出货策略配置 模块编码：3910 创建：Jun 修改：hekl
 */
var wms_OutorderStore = Ext.create('cms.store.wms.wms_OutorderStore', {
			autoLoad : true
		});
var girdTypeStore = Ext.create('cms.store.wms.wms_OutorderStore', {
			autoLoad : true,
			proxy : {
				type : 'ajax',
				method : 'post',
				url : 'odata_TacticsAction_getStrategyTypeList',
				reader : {
					type : 'json',
					root : 'rootList',
					totalProperty : 'totalCount'
				}
			}
		});
var strategyMStore = Ext.create('cms.store.wms.wms_strategyMStore', {
			autoLoad : false,
			listeners : {
				'load' : function(th, records, successful, eOpts) {
					if (th.count() > 0) {
						Ext.getCmp('girdM3910').getSelectionModel().select(0);
					}
				}
			}
		});
var strategyDStore = Ext.create('cms.store.wms.wms_strategyDStore', {
			autoLoad : false
		});
Ext.define('cms.view.odata.odata_TacticsConfigureUI', {
	alias : 'widget.odata_TacticsConfigureUI',
	title : $i18n.title3910,// 出货策略配置
	width : '100%',
	height : '100%',
	layout : 'border',
	extend : 'Ext.panel.Panel',
	requires : ['cms.view.common.commMenu', 'cms.view.common.commMenu2',
			'cms.view.common.commMenu3', 'cms.view.common.commMenu5',
			'cms.view.common.wms_DefFieldValCombo'],
	items : [{
		xtype : 'tabpanel',
		id : 'tabPId3910',
		region : 'center',
		items : [{
			title : '策略单据',
			layout : 'border',
			id : 'tabPId3910_T1',
			items : [{
				xtype : 'toolbar',
				region : 'north',
				items : [{
							text : $i18n.find,// 查找
							iconCls : 'query',
							id : 'query3910'
						}, {
							text : $i18n.delete_1,// 删除
							iconCls : 'delete',
							id : 'delete3910'
						}]
			}, {
				xtype : 'panel',
				region : 'north',
				height : 35,
				layout : {
					type : 'table',
					columns : 3
				},
				defaults : {
					xtype : 'textfield',
					margin : '3 3 3 3',
					labelAlign : 'right',
					allowBlank : true,
					width : 280,
					labelWidth : 90
				},
				items : [{
					fieldLabel : $i18n.exp_type,// 出货单别				
					id : 'cmbSearchExpType3910',
					xtype : 'wms_DefFieldValCombo',
					store : Ext.create(
							'cms.store.bdef.bdef_DefOwnerComboStore', {
								proxy : {
									type : 'ajax',
									method : 'post',
									url : 'odata_TacticsAction_getExptypeForUI',
									reader : {
										root : 'rootList',
										totalProperty : 'totalCount'
									}
								}
							}).load(),
					displayField : 'dropValue',
					valueField : 'value'
				},{
					fieldLabel : "渠道标识",// 渠道标识
					id : 'cmbSearchIndustryFlag3910',
					xtype : 'wms_DefFieldValCombo',
					forceSelection : false,
					store : Ext.create(
							'cms.store.bdef.bdef_DefOwnerComboStore', {
								proxy : {
									type : 'ajax',
									method : 'post',
									url : 'odata_TacticsAction_getIndustryFlagForUI',
									reader : {
										root : 'rootList',
										totalProperty : 'totalCount'
									}
								}
							}).load(),
					displayField : 'dropValue',
					valueField : 'value'
				}]
			}, {
				xtype : 'grid',
				region : 'center',// center
				height : 220,
				id : 'grid_01_3910',
				store : wms_OutorderStore,
				columns : [{
							xtype : 'rownumberer',
							width : 30
						}, {
							width : 100,
							text : $i18n.outstocktype,// 单据类型
							dataIndex : 'expType'
						}, {
							width : 100,
							text : $i18n.exptypeText,// 单据名称
							dataIndex : 'exptypeText'
						}, {
							width : 100,
							text : $i18n.priority,// 订单优先级
							dataIndex : 'priority'
						}, {
							width : 160,
							text : '自动批次规则策略',// 自动批次规则策略
							dataIndex : 'autobatchStrategyIdText'
						}, {
							width : 160,
							text : '手工批次规则策略',// 手工批次规则策略
							dataIndex : 'manualbatchStrategyIdText'
						}, {
							width : 120,
							text : '分配策略',// 分配策略
							dataIndex : 'locateStrategyIdText'
						}, {
							width : 120,
							text : '试算策略',// 试算策略
							dataIndex : 'computeStrategyIdText'
						}/*
							 * ,{ width : 120, text : '拣货策略',//拣货策略
							 * dataIndex:'pickStrategyIdText' }
							 */, {
							width : 120,
							text : '工作流策略',// 工作流策略
							dataIndex : 'workflowStrategyIdText'
						}, {
							width : 120,
							text : '打印内置清单',// 打印内置清单
							dataIndex : 'printPacklistText'
						}, {
							width : 120,
							text : '打印发票',// 打印发票
							dataIndex : 'printEnvoiceText'
						}, {
							width : 120,
							text : '打印快递面单',// 打印快递面单
							dataIndex : 'printWaybillText'
						}, {
							width : 120,
							text : '渠道标识',// 渠道标识
							dataIndex : 'industryFlagText'
						}/*
							 * ,{ width : 120, text : '分播策略',//分播策略
							 * dataIndex:'divideStrategyIdText' },{ width : 120,
							 * text : '复核策略',//复核策略
							 * dataIndex:'BCheckStrategyIdText' },{ width : 120,
							 * text : '外复核策略',//外复核策略
							 * dataIndex:'CCheckStrategyIdText' },{ width : 140,
							 * text : '称重打包(整理确认)策略',//称重打包(整理确认)策略
							 * dataIndex:'comfireStrategyIdText' },{ width :
							 * 120, text : '装车策略',//装车策略
							 * dataIndex:'loadStrategyIdText' }
							 */],
				dockedItems : [{
							xtype : 'pagingtoolbar',
							dock : 'bottom',
							store : wms_OutorderStore,
							displayInfo : true
						}]
			}]
		}, {
			title : '策略明细',
			layout : 'border',
			id : 'tabPId3910_T2',
			items : [{
						xtype : 'commMenuWidget',
						region : 'north',
						id : 'menu3910'
					}, {
						xtype : 'form',
						region : 'north',
						frame : true,
						layout : {
							type : 'table',
							columns : 3
						},
						defaults : {
							xtype : 'textfield',
							labelWidth : 110,
							margin : '2 2 4 2',
							labelAlign : 'right'
						},
						items : [{
							xtype : 'wms_DefFieldValCombo',
							fieldLabel : $i18n.exp_type,// 出货单别
							id : 'cmbExpType3910',
							store : Ext.create("cms.store.common.comboStore")
									.load({
												params : {
													str : "N,EXP_TYPE"
												}
											}),
							editable : false,
							allowBlank : false,
							readOnly : true,
							beforeLabelTextTpl : required
						}, {
							xtype : 'wms_DefFieldValCombo',
							fieldLabel : '订单优先级',
							id : 'cmbPriority3910',
							store : Ext.create("cms.store.common.comboStore")
									.load({
												params : {
													str : "ODATA_EXP_M,PRIORITY"
												}
											}),
							editable : false,
							allowBlank : false,
							readOnly : true,
							beforeLabelTextTpl : required
						}/*
							 * , { xtype:'wms_DefFieldValCombo', fieldLabel:
							 * '是否支持勾选', //是否支持勾选 id:'cmbSelectFlagl3910',
							 * store:Ext.create("cms.store.common.comboStore").load( {
							 * params:{str:"N,SUPPORT"} }), editable:false,
							 * allowBlank:false, readOnly:true,
							 * beforeLabelTextTpl:required }
							 */
						]
					}, {
						xtype : 'grid',
						id : 'girdType3910',
						title : '策略类型',// 策略类型
						region : 'west',
						width : '30%',
						store : girdTypeStore,
						selModel : {
							selType : 'cellmodel'
						},
						plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
							clicksToEdit : 1,
							onSpecialKey : function(ed, field, e) {
								commEnterGridStatEdit(
										this.grid,
										true,
										'cms.controller.odata.odata_TacticsConfigureController',
										e.getKey());
							}
						})],
						columns : [{
									xtype : 'rownumberer',
									width : 30
								}, {
									width : 150,
									text : '策略类型', // 策略名称
									dataIndex : 'strategyName'
								}, {
									width : 150,
									text : '策略id', // 规则id
									dataIndex : 'strategyid',
									field : {
										xtype : 'wms_DefFieldValCombo',
										id : 'strategyid3910_1',
										// 7-19添加
										listeners : {
											beforeedit : function(editor, e,
													eOpts) {
												var editFlag = _myAppGlobal
														.getController('cms.controller.odata.odata_TacticsConfigureController')
														.getEditFlag();
												if (editFlag == '1') {
													return false;// 不可编辑
												} else if (editFlag == '0') {
													return true;// 可编辑
												}
											}
										},
										store : Ext.create(
												"cms.store.common.comboStore",
												{
													proxy : {
														type : 'ajax',
														method : 'post',
														url : 'odata_TacticsAction_getStrategyidCom',
														reader : {
															root : 'rootList',
															totalProperty : 'totalCount'
														}
													}
												}),
										allowBlank : false
									},
									renderer : function(value, metadata, record) {
										if (!Ext.isEmpty(value)) {
											var params = {
												strategyFlag : record
														.get('strategyFlag')
											};
											var a = [];
											Ext.Ajax.request({
												url : 'odata_TacticsAction_getStrategyidCom',
												params : params,
												async : false,
												success : function(response) {
													a = Ext
															.decode(response.responseText);
												}
											});
											for (var i = 0; i < a.length; i++) {
												if (value == a[i].value) {
													return a[i].dropValue;
												}
											}
										}
									}
								}]
					}, {
						xtype : 'panel',
						region : 'center',
						border : false,
						frame : true,
						items : [{
									xtype : 'grid',
									id : 'girdM3910',
									title : '策略头档',// 策略头档
									height : 200,
									store : strategyMStore,
									columns : [{
												xtype : 'rownumberer',
												width : 30
											}, {
												width : 100,
												text : '策略id', // 规则id
												dataIndex : 'strategyId'
											}, {
												width : 150,
												text : '策略名称', // 规则名称
												dataIndex : 'strategyName'
											}, {
												width : 100,
												text : '默认标识', // 默认标识
												dataIndex : 'defalutFlagText'
											}]
								}, {
									xtype : 'grid',
									id : 'girdD3910',
									title : '策略明细',// 策略明细
									height : 270,
									store : strategyDStore,
									selModel : {
										selType : 'cellmodel'
									},
									columns : [{
												xtype : 'rownumberer',
												width : 30
											}, {
												width : 90,
												text : '规则id', // 规则id
												id : 'batchRuleId3910',
												dataIndex : 'ruleId'
											}, {
												width : 90,
												text : '规则名称', // 规则名称
												id : 'batchRuleName3910',
												dataIndex : 'ruleName'
											}, {
												width : 90,
												text : '执行顺序', // 执行顺序
												id : 'seqOrder3910',
												dataIndex : 'seqOrder'
											}, {
												width : 90,
												text : '状态', // 状态
												id : 'statusText3910',
												dataIndex : 'statusText'
											}, {
												width : 120,
												text : '批次切分模式', // 批次切分模式
												id : 'batchComputeTypeText3910',
												dataIndex : 'batchComputeTypeText'
											}, {
												width : 120,
												text : '批次切分方式', // 批次切分方式
												id : 'batchComputeText3910',
												dataIndex : 'batchComputeText'
											}, {
												width : 120,
												text : '每任务订单数上线', // 每任务订单数上线
												id : 'taskOrder3910',
												dataIndex : 'taskOrder'
											}, {
												width : 120,
												text : '每任务订单数下线', // 每任务订单数下线
												id : 'minOrder3910',
												dataIndex : 'minOrder'
											}, {
												width : 120,
												text : '平台', // 平台
												id : 'orderSourceText3910',
												dataIndex : 'orderSourceText'
											}, {
												width : 120,
												text : '商品重复度', // 商品重复度
												id : 'repeatTimes3910',
												dataIndex : 'repeatTimes'
											}, {
												width : 120,
												text : '配送方式', // 配送方式
												id : 'deliverTypeText3910',
												dataIndex : 'deliverTypeText'
											}, {
												width : 120,
												text : '配送站点 ', // 配送站点
												id : 'deliverAddress3910',
												dataIndex : 'deliverAddress'
											}, {
												width : 120,
												text : '管理线路 ', // 管理线路
												id : 'lineFlagText3910',
												dataIndex : 'lineFlagText'
											}, {
												width : 120,
												text : '承运商 ', // 承运商
												id : 'shipperNoText3910',
												dataIndex : 'shipperNoText'
											}, {
												width : 120,
												text : '品项数限制 ', // 品项数限制
												id : 'skuLimmit3910',
												dataIndex : 'skuLimmit'
											}, {
												width : 120,
												text : '最长等待时间(秒)', // 最长等待时间(秒)
												id : 'waitTimes3910',
												dataIndex : 'waitTimes'
											}, {
												width : 120,
												text : '间隔时间(秒)', // 间隔时间(秒)
												id : 'intervalTimes3910',
												dataIndex : 'intervalTimes'
											}, {
												width : 120,
												text : '是否整箱限制', // 是否整箱限制
												id : 'CLimmitText3910',
												dataIndex : 'CLimmitText'
											}, {
												width : 120,
												text : '是否允许跨区', // 是否允许跨区
												id : 'areaLimmitText3910',
												dataIndex : 'areaLimmitText'
											}, {
												width : 120,
												text : '是否含免检商品', // 是否含免检商品
												id : 'itemTypeFlagText3910',
												dataIndex : 'itemTypeFlagText'
											}, {
												width : 120,
												text : '快递面单打印类型', // 快递面单打印类型
												id : 'printTypeText3910',
												dataIndex : 'printTypeText'
											}, {
												width : 120,
												text : '打印发票', // 打印发票
												id : 'printEnvoiceText3910',
												dataIndex : 'printEnvoiceText'
											}, {
												width : 120,
												text : '打印快递面单', // 打印快递面单
												id : 'printWaybillText3910',
												dataIndex : 'printWaybillText'
											}, {
												width : 120,
												text : '打印装箱清单', // 打印装箱清单
												id : 'printPacklistText3910',
												dataIndex : 'printPacklistText'
											}, {
												width : 120,
												text : '摘果物流箱试算规则', // 摘果物流箱试算规则
												id : 'custLogiboxRuleIdText3910',
												dataIndex : 'custLogiboxRuleIdText'
											}, {
												width : 120,
												text : '任务分配规则', // 任务分配规则
												id : 'taskRuleIdText3910',
												dataIndex : 'taskRuleIdText'
											}, {
												width : 120,
												text : '配送对象级别 ', // 配送对象级别
												id : 'deliverObjLevelText3910',
												dataIndex : 'deliverObjLevelText'
											}, {
												width : 120,
												text : 'P型定位 ', // P型定位
												id : 'PFlagText3910',
												dataIndex : 'PFlagText'
											}, {
												width : 120,
												text : 'M型定位 ', // M型定位
												id : 'MFlagText3910',
												dataIndex : 'MFlagText'
											}, {
												width : 120,
												text : 'W型定位 ', // W型定位
												id : 'WFlagText3910',
												dataIndex : 'WFlagText'
											}, {
												width : 120,
												text : 'S型定位 ', // S型定位
												id : 'SFlagText3910',
												dataIndex : 'SFlagText'
											}, {
												width : 120,
												text : 'D型定位 ', // D型定位
												id : 'DFlagText3910',
												dataIndex : 'DFlagText'
											}, {
												width : 120,
												text : 'B型拣货方式 ', // B型拣货方式
												id : 'BDivideFlagText3910',
												dataIndex : 'BDivideFlagText'
											}, {
												width : 120,
												text : 'C型拣货方式 ', // C型拣货方式
												id : 'CDivideFlagText3910',
												dataIndex : 'CDivideFlagText'
											}, {
												width : 120,
												text : '出货定位提交方式 ', // 出货定位提交方式
												id : 'commitTypeText3910',
												dataIndex : 'commitTypeText'
											}, {
												width : 120,
												text : '出货定位缺量处理方式 ', // 出货定位缺量处理方式
												id : 'shortqtyTypeText3910',
												dataIndex : 'shortqtyTypeText'
											}, {
												width : 120,
												text : '工作流顺序', // 工作流顺序
												id : 'floworder13910',
												dataIndex : 'floworder'
											}, {
												width : 120,
												text : '工作流内容', // 工作流内容
												id : 'flowvalueText13910',
												dataIndex : 'flowvalueText'
											}, {
												width : 120,
												text : '标志', // 标志
												id : 'flowflagText13910',
												dataIndex : 'flowflagText'
											}, {
												width : 120,
												text : '复核台资源试算', // 复核台资源试算
												id : 'checkcomputeflagText13910',
												dataIndex : 'checkcomputeflagText'
											}, {
												width : 120,
												text : '复核台资源试算级别', // 复核台资源试算级别
												id : 'checkcomputelevelText13910',
												dataIndex : 'checkcomputelevelText'
											}, {
												width : 120,
												text : '复核台资源值', // 复核台资源值
												id : 'checkcomputevalueText13910',
												dataIndex : 'checkcomputevalueText'
											}, {
												width : 120,
												text : '月台资源试算', // 月台资源试算
												id : 'sendbufcomputeflagText13910',
												dataIndex : 'sendbufcomputeflagText'
											}, {
												width : 120,
												text : '月台资源试算级别', // 月台资源试算级别
												id : 'sendbufcomputelevelText13910',
												dataIndex : 'sendbufcomputelevelText'
											}, {
												width : 120,
												text : '月台资源试算值', // 月台资源试算值
												id : 'sendbufcomputevalueText13910',
												dataIndex : 'sendbufcomputevalueText'
											}, {
												width : 120,
												text : '预留控制属性 1', // 预留控制属性
												// 1
												id : 'rsvControl13910',
												dataIndex : 'rsvControl1'
											}, {
												width : 120,
												text : '预留控制属性 2', // 预留控制属性
												// 2
												id : 'rsvControl23910',
												dataIndex : 'rsvControl2'
											}, {
												width : 120,
												text : '预留控制属性 3', // 预留控制属性
												// 3
												id : 'rsvControl33910',
												dataIndex : 'rsvControl3'
											}, {
												width : 120,
												text : '预留控制属性 4', // 预留控制属性
												// 4
												id : 'rsvControl43910',
												dataIndex : 'rsvControl4'
											}, {
												width : 120,
												text : '预留控制属性 5', // 预留控制属性5
												id : 'rsvControl53910',
												dataIndex : 'rsvControl5'
											}, {
												width : 120,
												text : '预留属性 1', // 预留属性
												// 1
												id : 'rsvValue13910',
												dataIndex : 'rsvValue1'
											}, {
												width : 120,
												text : '预留属性 2', // 预留属性
												// 2
												id : 'rsvValue23910',
												dataIndex : 'rsvValue2'
											}, {
												width : 120,
												text : '预留属性 3', // 预留属性
												// 3
												id : 'rsvValue33910',
												dataIndex : 'rsvValue3'
											}, {
												width : 120,
												text : '预留属性 4', // 预留属性
												// 4
												id : 'rsvValue43910',
												dataIndex : 'rsvValue4'
											}, {
												width : 120,
												text : '预留属性 5', // 预留属性
												// 5
												id : 'rsvValue53910',
												dataIndex : 'rsvValue5'
											}]
								}]
					}/*
						 * , { xtype : 'grid', id : 'grid_03_3910', region :
						 * 'east', width : '30%', // region:'center', store :
						 * Ext.create( 'cms.store.wms.wms_OutorderFlowStore', {
						 * autoLoad : false }), columns : [{ xtype :
						 * 'checkcolumn', width : 50, columnHeaderCheckbox :
						 * true,// 必须定义如下store store : Ext.data.StoreManager
						 * .lookup('wms_OutorderFlowStore'), dataIndex : 'flag' }, {
						 * width : 120, text : '工作流顺序', dataIndex : 'flowOrder' }, {
						 * width : 200, text : '工作流内容', dataIndex :
						 * 'flowvalueText' }] }
						 */, {
						region : 'south'
					}]
		}]

	}]
});