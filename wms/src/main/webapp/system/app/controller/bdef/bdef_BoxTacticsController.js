/**
 * 模块名称：返配策略配置 模块代码：I801
 * 
 * @author hezf
 */
var g_intRowIndexI801 = 0;
var g_isCanEditI801 = false;
Ext.define('cms.controller.bdef.bdef_BoxTacticsController', {
	extend : 'Ext.app.Controller',
	requires : ['cms.view.bdef.bdef_BoxTacticsController',
	// 'cms.view.odata.window.odata_TacticsAddOrEditWindow'
	],
	init : function() {
		this.control({
					// 点击新单
					'bdef_BoxTacticsUI button[name=userAddButton]' : {
						click : this.userAddButtonClick
					},
					// 保存
					'bdef_BoxTacticsUI button[name=userSaveButton]' : {
						click : this.userSaveButtonClick
					},
					// 双击进入返配明细
					'bdef_BoxTacticsUI grid[id=grid_01_I801]' : {
						itemdblclick : this.grid_01_I801Dblclick
					},
					// 选择波次规则号，带出波规则信息
					'bdef_BoxTacticsUI form combo[id=cmbstrategyIdI801]' : {
						select : this.cmbstrategyIdI801select
						,
					},
					// 导航栏的切换
					'bdef_BoxTacticsUI tabpanel[id=tabPIdI801]' : {
						tabchange : this.tabPidchange
					},
					// 返配单别的判断
					'bdef_BoxTacticsUI wms_DefFieldValCombo[id=cmbExpTypeI801]' : {
						select : this.cmbExpTypeI801Select
					},// 返配单别的判断
					'bdef_BoxTacticsUI wms_DefFieldValCombo[id=cmbPriorityI801]' : {
						select : this.cmbExpTypeI801Select
					},

					// 编辑
					'bdef_BoxTacticsUI button[name=userEditButton]' : {
						click : this.userEditButtonClick
					},
					// 撤退
					'bdef_BoxTacticsUI button[name=userUndoButton]' : {
						click : this.userUndoButtonClick
					},
					// 上一条信息
					'bdef_BoxTacticsUI button[name=userPrevButton]' : {
						click : this.userPrevButtonClick
					},
					// 下一条信息
					'bdef_BoxTacticsUI button[name=userNextButton]' : {
						click : this.userNextButtonClick
					},// 策略-删除
					'bdef_BoxTacticsUI button[id=deleteI801]' : {
						click : this.deleteClick
					}
				});
	},

	deleteClick : function() {
		var rec = Ext.getCmp('grid_01_I801').getSelectionModel().getSelection();
		if (rec.length == 0) {
			Ext.example.msg($i18n.prompt,
					$i18n.prompt_please_select_the_rows_to_delete);
		} else {
			Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(
					button, text) {
				if (button == 'yes') {
					Ext.Ajax.request({
								url : 'bdef_WmsLogiboxRuleAction_deleteWmsLogiboxRule',
								params : {
									// strQuery:rec[0].get('expType')
									enterpriseNo : Ext.get('enterpriseNo')
											.getValue(),
											str : rec[0].get('ruleId')
								},
								success : function(response) {
									var data = Ext
											.decode(response.responseText);
									if (data.isSucc) {
										Ext.getCmp('grid_01_I801').getStore()
												.load();
										Ext.example.msg($i18n.prompt, data.msg);
									} else {
										Ext.example.msg($i18n.prompt, data.msg
														+ data.obj);
									}
								}
							});
				}
			});
		}
	},

	// 点击新单
	userAddButtonClick : function() {
		addTacticsI801();
		commonEditButton('menuI801', 'add');
		commonSetMsterReadOnly('form_01_I801', false);
		commonSetMsterReadOnlyByArray(new Array('txtRuleIDI801',
				'txtRuleValueI801', 'cmbRuleI801'), false);
	},

	// 保存
	userSaveButtonClick : function() {
		if (!commonCheckIsInputAll('tabPIdI801_T2')) {
			return;
		} else {
			saveExpTypeI801();
			commonEditButton('menuI801', 'save');
		}
	},

	// 双击进入返配明细
	grid_01_I801Dblclick : function() {
		Ext.getCmp('tabPIdI801').items.items[1].setVisible(true);
		// isCanEdit3101=false;
		commonEditButton('menuI801', 'dbclick');
	},

	tabPidchange : function(tabPanel, newCard, oldCard, eOpts) {
		if (newCard.id == 'tabPIdI801_T2') {
			var data = Ext.getCmp('grid_01_I801').getSelectionModel()
					.getSelection();
			if (data.length != 0) {
				loadTacticsI801(data[0].index);
				g_intRowIndexI801 = data[0].index;

				var wheresql = {
					strExpType : data[0].data.expType,
					strType : '0'
				};
				commonEditButton('menuI801', 'dbclick');
			}
		}
	},

	// 返配单别的判断
	cmbExpTypeI801Select : function(combo) {
		if (Ext.getCmp('cmbExpTypeI801').getValue() != null
				&& Ext.getCmp('cmbPriorityI801').getValue() != null) {
			Ext.Ajax.request({
						url : 'ridata_TacticsAction_checkExpType',
						params : {
							strExpType : Ext.getCmp('cmbExpTypeI801')
									.getValue(),
							strPriority : Ext.getCmp('cmbPriorityI801')
									.getValue(),
							strQuality : Ext.getCmp('cmbQualityI801')
									.getValue(),
							wheresql : 1
						},
						success : function(response) {
							var data = Ext.decode(response.responseText);
							if (data.isSucc) {
								// Ext.example.msg($i18n.prompt,data.msg);
							} else {
								Ext.example.msg($i18n.prompt, data.msg);
								Ext.getCmp('cmbExpTypeI801').setValue(null);
								Ext.getCmp('cmbQualityI801').setValue(null);
								Ext.getCmp('cmbPriorityI801').setValue(null);
							}
						}
					});

			// 加载波次规则号
			var wheresql = {
				strExpType : Ext.getCmp('cmbExpTypeI801').getValue()
			};
		}
	},

	// 选择波次规则号，带出波次规则信息
	cmbstrategyIdI801select : function(combo, records, eOpts) {
		Ext.getCmp("strategyNameI801").setValue(records[0].data.strategyName);
		Ext.getCmp("betweenTimesI801").setValue(records[0].data.betweenTimes);
		commonSetMsterReadOnlyByArray(new Array('strategyNameI801',
						'betweenTimesI801'), true);

	},

	// 修改
	userEditButtonClick : function() {
		// isCanEdit3101=true;
		commonEditButton('menuI801', 'edit');
		commonSetMsterReadOnly('form_01_I801', false);
		commonSetMsterReadOnlyByArray(new Array('txtRuleIDI801'), true);
		commonSetMsterReadOnlyByArray(new Array('cmbRuleI801',
				'txtMemoI801','txtRuleValueI801'), false);
	},
	// 撤退
	userUndoButtonClick : function() {
		var data = Ext.getCmp('grid_01_I801').getSelectionModel()
				.getSelection();
		if (data.length != 0) {
			loadTacticsI801(data[0].index);
			g_intRowIndexI801 = data[0].index;
		}

		commonSetMsterReadOnly('form_01_I801', true);
		commonSetMsterReadOnlyByArray(new Array('txtRuleIDI801', 'cmbRuleI801',
						'txtRuleValueI801'), true);
		commonEditButton('menuI801', 'undo');
	},

	// 上一条信息
	userPrevButtonClick : function() {
		g_intRowIndexI801 = g_intRowIndexI801 - 1;
		loadTacticsI801(g_intRowIndexI801);
	},

	// 下一条信息
	userNextButtonClick : function() {
		g_intRowIndexI801 = g_intRowIndexI801 + 1;
		loadTacticsI801(g_intRowIndexI801);
	},

	close : function() {
		// Ext.getCmp('odata_TacticsAddOrEditWindow').close();
	}

});

function saveExpTypeI801() {
	if (Ext.getCmp('form_01_I801').getForm().isValid()) {
		var detail = {
			id : {
				enterpriseNo : Ext.get('enterpriseNo').getValue(),
				ruleId : Ext.getCmp('txtRuleIDI801').getValue()
			},

			allotRule : Ext.getCmp('cmbRuleI801').getValue(),
			allotRuleValue : Ext.getCmp('txtRuleValueI801').getValue(),
			areaRule : Ext.getCmp('cmbArea_ruleI801').getValue(),
			volFlag : Ext.getCmp('cmbVol_flagI801').getValue(),
			weightFlag : Ext.getCmp('cmbWeight_flagI801').getValue(),
			boxSkuLimit : Ext.getCmp('cmbBox_sku_limitI801').getValue(),
			splitboxFlag : Ext.getCmp('cmbSplitbox_flagI801').getValue(),
			onedeliveronepick : Ext.getCmp('cmbOnedeliveronepickI801')
					.getValue(),
			memo : Ext.getCmp('txtMemoI801').getValue(),
			rgstName : Ext.get('workerNo').getValue(),
			rgstDate : new Date()
		};
		var strJsonDetail = Ext.encode(detail);
		Ext.Ajax.request({
			url : 'bdef_WmsLogiboxRuleAction_saveOrUpdateWmsLogiboxRule.action',
			method : 'post',
			params : {
				str : strJsonDetail
			},
			success : function(response) {
				var data = Ext.decode(response.responseText);
				if (data.isSucc) {
					Ext.getCmp('grid_01_I801').getStore().load();
					/*
					 * var wheresql = { strExpType : Ext.getCmp('txtRuleIDI801')
					 * .getValue(), strType : '0' };
					 */
					Ext.example.msg($i18n.prompt, data.msg);
				} else {
					Ext.example.msg($i18n.prompt, data.msg);
				}
			}
		});
	}
};

function addTacticsI801() {
	Ext.getCmp('txtRuleIDI801').setValue(null);
	Ext.getCmp('cmbRuleI801').setValue(null);
	Ext.getCmp('txtRuleValueI801').setValue(null);
	Ext.getCmp('cmbArea_ruleI801').setValue(null);
	Ext.getCmp('cmbVol_flagI801').setValue(null);
	Ext.getCmp('cmbWeight_flagI801').setValue(null);
	Ext.getCmp('cmbBox_sku_limitI801').setValue(null);
	Ext.getCmp('cmbSplitbox_flagI801').setValue(null);
	Ext.getCmp('cmbOnedeliveronepickI801').setValue(null);
	Ext.getCmp('txtMemoI801').setValue(null);
};

function loadTacticsI801(g_intRowIndexI801) {

	if (g_intRowIndexI801 == 0) {
		Ext.getCmp('menuI801').items.items[0].disable(true);
	} else {
		Ext.getCmp('menuI801').items.items[0].enable(true);
	}
	if (g_intRowIndexI801 == Ext.getCmp('grid_01_I801').getStore().getCount()
			- 1) {
		Ext.getCmp('menuI801').items.items[1].disable(true);
	} else {
		Ext.getCmp('menuI801').items.items[1].enable(true);
	}
	var record = Ext.getCmp('grid_01_I801').getStore().getAt(g_intRowIndexI801
			- (Ext.getCmp('grid_01_I801').getStore().currentPage - 1)
			* appConfig.getPageSize());
	Ext.getCmp('txtRuleIDI801').setValue(record.data.ruleId);
	Ext.getCmp('cmbRuleI801').setValue(record.data.allotRule);
	Ext.getCmp('txtRuleValueI801').setValue(record.data.allotRuleValue);
	Ext.getCmp('cmbArea_ruleI801').setValue(record.data.areaRule);
	Ext.getCmp('cmbVol_flagI801').setValue(record.data.volFlag);
	Ext.getCmp('cmbWeight_flagI801').setValue(record.data.weightFlag);
	Ext.getCmp('cmbBox_sku_limitI801').setValue(record.data.boxSkuLimit);
	Ext.getCmp('cmbSplitbox_flagI801').setValue(record.data.splitboxFlag);
	Ext.getCmp('cmbOnedeliveronepickI801')
			.setValue(record.data.onedeliveronepick);
	Ext.getCmp('txtMemoI801').setValue(record.data.memo);

	commonSetMsterReadOnlyByArray(new Array('cmbRuleI801'), true);

	/*
	 * var wheresql = { strExpType : Ext.getCmp('cmbExpTypeI801').getValue(),
	 * strType : '0' };
	 */
}
