/**
 * 模块名称：返配策略配置 模块编码：I501 创建：hezf 修改：hezf
 */
var wms_OutorderStore = Ext.create('cms.store.wms.wms_RiorderStore', {
			autoLoad : true
		});
Ext.define('cms.view.ridata.ridata_TacticsConfigureUI', {
	alias : 'widget.ridata_TacticsConfigureUI',
	title : $i18n.title4930,// 返配策略配置
	width : '100%',
	height : '100%',
	layout : 'border',
	extend : 'Ext.panel.Panel',
	requires : ['cms.view.common.commMenu', 'cms.view.common.commMenu2',
			'cms.view.common.commMenu3', 'cms.view.common.commMenu5',
			'cms.view.common.wms_DefFieldValCombo'],
	items : [{
		xtype : 'tabpanel',
		id : 'tabPIdI501',
		region : 'center',
		items : [{
					title : '策略单据',
					layout : 'border',
					id : 'tabPIdI501_T1',
					items : [{
								xtype : 'toolbar',
								region : 'north',
								items : [{
											text : $i18n.find,// 查找
											iconCls : 'query',
											id : 'queryI501'
										}, {
											text : $i18n.delete_1,// 删除
											iconCls : 'delete',
											id : 'deleteI501'
										}]
							}, {
								xtype : 'grid',
								region : 'center',
								height : 220,
								id : 'grid_01_I501',
								store : wms_OutorderStore,
								columns : [{
											xtype : 'rownumberer',
											width : 30
										}, {
											width : 100,
											text : '返配单别',// 返配单别
											dataIndex : 'untreadTypeText'
										}, {
											width : 100,
											text : '分类',// 分类
											dataIndex : 'classTypeText'
										}, {
											width : 100,
											text : '品质分类',// 品质分类
											dataIndex : 'qualityFlagText'
										}, {
											width : 120,
											text : '是否超量验收',// '是否超量验收'
											dataIndex : 'overQtyFlagText'
										}, {
											width : 120,
											text : '是否自动上架',// '是否自动上架'
											dataIndex : 'autoInstockText'
										}, {
											width : 120,
											text : '是否预定位',// 是否预定位
											dataIndex : 'advanceLocateText'
										}, {
											width : 120,
											text : '是否资源试算',// 是否资源试算
											dataIndex : 'deviceComputeText'
										}, {
											width : 120,
											text : '是否指定储位',// '是否指定储位'
											dataIndex : 'directCellFlagText'
										}, {
											width : 120,
											text : '上架回单是否保留标签号',// '上架回单是否保留标签号'
											dataIndex : 'rsvLabelFlagText'
										}, {
											width : 120,
											text : '是否自动确认验收',// '是否自动确认验收'
											dataIndex : 'autoCheckComfirFlagText'
										}, {
											width : 120,
											text : '是否打印验收清单',// '是否打印验收清单'
											dataIndex : 'printCheckFlagText'
										}, {
											width : 120,
											text : '是否对应多张单',// '是否对应多张单'
											dataIndex : 'mixOrdercheckText'
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
					id : 'tabPIdI501_T2',
					items : [{
								xtype : 'commMenuWidget',
								region : 'north',
								id : 'menuI501'
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
									fieldLabel : $i18n.untread_type1,// 返配单别
									id : 'cmbExpTypeI501',
									store : Ext
											.create("cms.store.common.comboStore")
											.load({
												params : {
													str : "RIDATA_UNTREAD_M,UNTREAD_TYPE"
												}
											}),
									editable : false,
									allowBlank : false,
									readOnly : true,
									beforeLabelTextTpl : required
								}, {
									xtype : 'wms_DefFieldValCombo',
									fieldLabel : '类型',
									id : 'cmbPriorityI501',
									store : Ext
											.create("cms.store.common.comboStore")
											.load({
														params : {
															str : "N,CLASS_TYPE"
														}
													}),
									editable : false,
									allowBlank : false,
									readOnly : true,
									beforeLabelTextTpl : required
								}, {
									xtype : 'wms_DefFieldValCombo',
									fieldLabel : '品质类型',
									id : 'cmbQualityI501',
									store : Ext
											.create("cms.store.common.comboStore")
											.load({
												params : {
													str : "RIDATA_UNTREAD_M,QUALITY"
												}
											}),
									editable : false,
									allowBlank : false,
									readOnly : true,
									beforeLabelTextTpl : required
								}

								]
							}, {
								xtype : 'form',
								id : 'form_01_I501',
								region : 'west',
								width : '70%',
								frame : true,
								layout : {
									type : 'table',
									columns : 1
								},
								defaults : {
									xtype : 'textfield',
									labelWidth : 100,
									margin : '2 2 4 2',
									labelAlign : 'right'
								},
								items : [{
									xtype : 'fieldset',
									layout : {
										type : 'table',
										columns : 3
									},
									defaults : {
										xtype : 'textfield',
										labelWidth : 100,
										margin : '5 0 0 0 ',
										labelAlign : 'right'
									},
									items : [{
										xtype : 'wms_DefFieldValCombo',
										fieldLabel : '是否超量验收',// '是否超量验收
										id : 'cmbMflagI501',
										store : Ext
												.create("cms.store.common.comboStore")
												.load({
															params : {
																str : "N,YESORNO"
															}
														}),
										editable : false,
										allowBlank : false,
										readOnly : true,
										beforeLabelTextTpl : required
									}, {
										xtype : 'wms_DefFieldValCombo',
										fieldLabel : '是否自动上架',// 是否自动上架
										id : 'cmbWflagI501',
										store : Ext
												.create("cms.store.common.comboStore")
												.load({
															params : {
																str : "N,YESORNO"
															}
														}),
										editable : false,
										allowBlank : false,
										readOnly : true,
										beforeLabelTextTpl : required
									}, {
										xtype : 'wms_DefFieldValCombo',
										fieldLabel : '是否预定位',// 是否预定位
										id : 'cmbDflagI501',
										store : Ext
												.create("cms.store.common.comboStore")
												.load({
															params : {
																str : "N,YESORNO"
															}
														}),
										editable : false,
										allowBlank : false,
										readOnly : true,
										beforeLabelTextTpl : required
									}, {
										xtype : 'wms_DefFieldValCombo',
										fieldLabel : '是否资源试算',// 是否资源试算
										id : 'cmbSflagI501',
										store : Ext
												.create("cms.store.common.comboStore")
												.load({
															params : {
																str : "N,YESORNO"
															}
														}),
										editable : false,
										allowBlank : false,
										readOnly : true,
										beforeLabelTextTpl : required
									}, {
										xtype : 'wms_DefFieldValCombo',
										fieldLabel : '是否指定储位',// 是否指定储位
										id : 'cmbDirectCellI501',
										store : Ext
												.create("cms.store.common.comboStore")
												.load({
															params : {
																str : "N,YESORNO"
															}
														}),
										editable : false,
										allowBlank : true,
										readOnly : true
									}, {
										xtype : 'wms_DefFieldValCombo',
										fieldLabel : '上架回单是否保留标签号',// 上架回单是否保留标签号
										id : 'cmbRSVLabelI501',
										store : Ext
												.create("cms.store.common.comboStore")
												.load({
															params : {
																str : "N,YESORNO"
															}
														}),
										editable : false,
										allowBlank : true,
										readOnly : true
									}]
								}, {
									xtype : 'fieldset',
									layout : {
										type : 'table',
										columns : 3
									},
									defaults : {
										xtype : 'textfield',
										labelWidth : 100,
										margin : '5 0 0 0 ',
										labelAlign : 'right'
									},
									items : [{
										xtype : 'wms_DefFieldValCombo',
										fieldLabel : '验收时是否同一容器是否允许对应多张单',// 验收时是否同一容器是否允许对应多张单
										id : 'cmbBdivideFlagI501',
										store : Ext
												.create("cms.store.common.comboStore")
												.load({
															params : {
																str : "N,YESORNO"
															}
														}),
										editable : false,
										allowBlank : true,
										readOnly : true
									}, {
										xtype : 'wms_DefFieldValCombo',
										fieldLabel : '是否需要支持自动验收确认',// 是否需要支持自动验收确认
										id : 'cmbCdivideFlagI501',
										store : Ext
												.create("cms.store.common.comboStore")
												.load({
															params : {
																str : "N,YESORNO"
															}
														}),
										editable : false,
										allowBlank : true,
										readOnly : true
									}, {
										xtype : 'wms_DefFieldValCombo',
										fieldLabel : '验收确认时，是否打印验收清单',// 验收确认时，是否打印验收清单
										id : 'cmbSendbufComputeI501',
										store : Ext
												.create("cms.store.common.comboStore")
												.load({
															params : {
																str : "N,YESORNO"
															}
														}),
										editable : false,
										allowBlank : true,
										readOnly : true
									}

									]
								}, {
									xtype : 'fieldset',
									layout : {
										type : 'table',
										columns : 3
									},
									defaults : {
										xtype : 'textfield',
										labelWidth : 100,
										margin : '5 0 0 0 ',
										labelAlign : 'right'
									},
									items : [{
												fieldLabel : '预留字段1',
												id : 'txtOrderRsv01_I501'
											}, {
												fieldLabel : '预留字段2',
												id : 'txtOrderRsv02_I501'
											}, {
												fieldLabel : '预留字段3',
												id : 'txtOrderRsv03_I501'
											}, {
												fieldLabel : '预留字段4',
												id : 'txtOrderRsv04_I501'
											}, {
												fieldLabel : '预留字段5',
												id : 'txtOrderRsv05_I501'
											}, {
												fieldLabel : '预留字段6',
												id : 'txtOrderRsv06_I501'
											}, {
												fieldLabel : '预留字段7',
												id : 'txtOrderRsv07_I501'
											}, {
												fieldLabel : '预留字段8',
												id : 'txtOrderRsv08_I501'
											}]
								}]
							}]
				}]
	}]
});