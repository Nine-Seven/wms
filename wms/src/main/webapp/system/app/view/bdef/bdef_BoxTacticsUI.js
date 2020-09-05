/**
 * 模块名称：物流箱策略配置 模块编码：I801 创建：Hezf 修改：Hezf
 */

var wms_OutorderStore = Ext.create('cms.store.bdef.bdef_WmsLogiboxRuleStore', {
			autoLoad : true
		});
Ext.define('cms.view.bdef.bdef_BoxTacticsUI', {
	alias : 'widget.bdef_BoxTacticsUI',
	title : '物流箱试算规则配置',// 物流箱试算规则配置
	width : '100%',
	height : '100%',
	layout : 'border',
	extend : 'Ext.panel.Panel',
	requires : ['cms.view.common.commMenu', 'cms.view.common.commMenu2',
			'cms.view.common.commMenu3', 'cms.view.common.commMenu5',
			'cms.view.common.wms_DefFieldValCombo',
			'cms.view.common.bdef_DefOwnerCombo'],
	items : [{
		xtype : 'tabpanel',
		id : 'tabPIdI801',
		region : 'center',
		items : [{
					title : '策略单据',
					layout : 'border',
					id : 'tabPIdI801_T1',
					items : [{
								xtype : 'toolbar',
								region : 'north',
								items : [{
											text : $i18n.find,// 查找
											iconCls : 'query',
											id : 'queryI801'
										}, {
											text : $i18n.delete_1,// 删除
											iconCls : 'delete',
											id : 'deleteI801'
										}]
							}, {
								xtype : 'grid',
								region : 'center',
								height : 220,
								id : 'grid_01_I801',
								store : wms_OutorderStore,
								columns : [{
											xtype : 'rownumberer',
											width : 30
										}, {
											width : 100,
											text : '规则id',// 规则id
											dataIndex : 'ruleId'
										}, {
											width : 120,
											text : '规则名称',// 规则名称
											dataIndex : 'memo'
										}, {
											width : 100,
											text : '物流箱计算规则',// 物流箱计算规则
											dataIndex : 'allotRuleText'
										}, {
											width : 100,
											text : '物流箱计算规则值',// 物流箱计算规则值
											dataIndex : 'areaRule'
										}, {
											width : 120,
											text : '区域规则',// 区域规则
											dataIndex : 'areaRuleText'
										}, {
											width : 120,
											text : '是否参考物流箱材积',// 是否参考物流箱材积
											dataIndex : 'volFlagText'
										}, {
											width : 120,
											text : '是否参考物流箱重量',// 是否参考物流箱重量
											dataIndex : 'weightFlagText'
										}, {
											width : 120,
											text : '物流箱内品项数限制',// 物流箱内品项数限制
											dataIndex : 'boxSkuLimit'
										}, {
											width : 120,
											text : '同一个配送对象一次性拣货',// 同一个配送对象一次性拣货
											dataIndex : 'onedeliveronepickText'
										}, {
											width : 120,
											text : '同指示是否支持拆箱',// 同指示是否支持拆箱
											dataIndex : 'splitboxFlagText'
										}],
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
					id : 'tabPIdI801_T2',
					items : [{
								xtype : 'commMenuWidget',
								region : 'north',
								id : 'menuI801'
							}, {
								xtype : 'form',
								region : 'north',
								id : 'menuI801_1',
								frame : true,
								layout : {
									type : 'table',
									columns : 2
								},
								defaults : {
									xtype : 'textfield',
									labelWidth : 170,
									margin : '2 0 2 0',
									labelAlign : 'right'
								},
								items : [{
											fieldLabel : "物流箱计算规则值",// 物流箱计算规则值
											id : 'txtRuleIDI801',
											xtype : 'numberfield',
											anchor : '100%',
											name : 'bottles',
											fieldLabel : '规则ID',
											allowBlank : false,
											readOnly : true,
											minValue : 0,
											beforeLabelTextTpl : required
										}, {
											xtype : 'textfield',
											fieldLabel : '规则名称',// 规则名称
											id : 'txtMemoI801',
											editable : false,
											allowBlank : false,
											readOnly : true,
											beforeLabelTextTpl : required
										}, {
											xtype : 'wms_DefFieldValCombo',
											fieldLabel : "物流箱计算规则",// 物流箱计算规则
											id : 'cmbRuleI801',
											store : Ext
													.create("cms.store.common.comboStore")
													.load({
														params : {
															str : "WMS_LOGIBOX_RULE,ALLOT_RULE"
														}
													}),
											editable : false,
											allowBlank : false,
											readOnly : true,
											beforeLabelTextTpl : required
										}, {
											fieldLabel : "物流箱计算规则值",// 物流箱计算规则值
											id : 'txtRuleValueI801',
											xtype : 'numberfield',
											anchor : '100%',
											name : 'bottles',
											fieldLabel : '物流箱计算规则值',
											allowBlank : false,
											readOnly : true,
											minValue : 0,
											beforeLabelTextTpl : required
										}]
							}, {
								xtype : 'form',
								id : 'form_01_I801',
								region : 'west',
								width : '100%',
								frame : true,
								layout : {
									type : 'table',
									columns : 1
								},
								defaults : {
									xtype : 'textfield',
									labelWidth : 200,
									margin : '2 2 4 2',
									labelAlign : 'right'
								},
								items : [{
									xtype : 'fieldset',
									layout : {
										type : 'table',
										columns : 2
									},
									defaults : {
										xtype : 'textfield',
										labelWidth : 160,
										margin : '5 0 0 0 ',
										labelAlign : 'right'
									},
									items : [{
										xtype : 'wms_DefFieldValCombo',
										fieldLabel : '区域规则',// '区域规则
										id : 'cmbArea_ruleI801',
										store : Ext
												.create("cms.store.common.comboStore")
												.load({
													params : {
														str : "WMS_LOGIBOX_RULE,AREA_RULE"
													}
												}),
										editable : false,
										allowBlank : false,
										readOnly : true,
										beforeLabelTextTpl : required
									}, {
										xtype : 'numberfield',
										fieldLabel : '物流箱内品项数限制',// 物流箱内品项数限制
										id : 'cmbBox_sku_limitI801',
										store : Ext
												.create("cms.store.common.comboStore")
												.load({
													params : {
														str : "WMS_LOGIBOX_RULE,BOX_SKU_LIMIT"
													}
												}),
										editable : false,
										allowBlank : false,
										readOnly : true,
										minValue : 0,
										beforeLabelTextTpl : required
									}, {
										xtype : 'wms_DefFieldValCombo',
										fieldLabel : '是否参考物流箱材积',// 是否参考物流箱材积
										id : 'cmbVol_flagI801',
										store : Ext
												.create("cms.store.common.comboStore")
												.load({
													params : {
														str : "WMS_LOGIBOX_RULE,VOL_FLAG"
													}
												}),
										editable : false,
										allowBlank : true,
										readOnly : true
									}, {
										xtype : 'wms_DefFieldValCombo',
										fieldLabel : '是否参考物流箱重量',// 是否参考物流箱重量
										id : 'cmbWeight_flagI801',
										store : Ext
												.create("cms.store.common.comboStore")
												.load({
													params : {
														str : "WMS_LOGIBOX_RULE,WEIGHT_FLAG"
													}
												}),
										editable : false,
										allowBlank : true,
										readOnly : true
									}, {
										xtype : 'wms_DefFieldValCombo',
										fieldLabel : '同一个配送对象一次性拣货',// 同一个配送对象一次性拣货
										id : 'cmbOnedeliveronepickI801',
										store : Ext
												.create("cms.store.common.comboStore")
												.load({
													params : {
														str : "WMS_LOGIBOX_RULE,ONEDELIVERONEPICK"
													}
												}),
										editable : false,
										allowBlank : false,
										readOnly : true,
										beforeLabelTextTpl : required
									}, {
										xtype : 'wms_DefFieldValCombo',
										fieldLabel : '同指示是否支持拆箱',// 同指示是否支持拆箱
										id : 'cmbSplitbox_flagI801',
										store : Ext
												.create("cms.store.common.comboStore")
												.load({
													params : {
														str : "WMS_LOGIBOX_RULE,SPLITBOX_FLAG"
													}
												}),
										editable : false,
										allowBlank : false,
										readOnly : true,
										beforeLabelTextTpl : required
									}]
								}/*
									 * , { xtype : 'fieldset', layout : { type :
									 * 'table', columns : 3 }, defaults : {
									 * xtype : 'textfield', labelWidth : 100,
									 * margin : '5 0 0 0 ', labelAlign : 'right' },
									 * items : [{ fieldLabel : '预留字段1', id :
									 * 'txtOrderRsv01_I801' }, { fieldLabel :
									 * '预留字段2', id : 'txtOrderRsv02_I801' }, {
									 * fieldLabel : '预留字段3', id :
									 * 'txtOrderRsv03_I801' }, { fieldLabel :
									 * '预留字段4', id : 'txtOrderRsv04_I801' }, {
									 * fieldLabel : '预留字段5', id :
									 * 'txtOrderRsv05_I801' }, { fieldLabel :
									 * '预留字段6', id : 'txtOrderRsv06_I801' }, {
									 * fieldLabel : '预留字段7', id :
									 * 'txtOrderRsv07_I801' }, { fieldLabel :
									 * '预留字段8', id : 'txtOrderRsv08_I801' }] }
									 */]
							}]
				}]
	}]
});