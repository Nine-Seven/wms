/**
 * 模块名称：仓别返配策略配置 模块代码：I601
 * 
 * @author hezf
 */
var g_intRowIndexI601 = 0;
var g_isCanEditI601 = false;
Ext.define('cms.controller.ridata.ridata_WarehouseTacticsConfigureController',
		{
			extend : 'Ext.app.Controller',
			requires : ['cms.view.ridata.ridata_WarehouseTacticsConfigureUI',
			// 'cms.view.odata.window.odata_TacticsAddOrEditWindow'
			],
			init : function() {
				this.control({
					// 点击新单
					'ridata_WarehouseTacticsConfigureUI button[name=userAddButton]' : {
						click : this.userAddButtonClick
					},
					// 保存
					'ridata_WarehouseTacticsConfigureUI button[name=userSaveButton]' : {
						click : this.userSaveButtonClick
					},
					// 双击进入返配明细
					'ridata_WarehouseTacticsConfigureUI grid[id=grid_01_I601]' : {
						itemdblclick : this.grid_01_I601Dblclick
					},
					// 选择波次规则号，带出波规则信息
					'ridata_WarehouseTacticsConfigureUI form combo[id=cmbstrategyIdI601]' : {
						select : this.cmbstrategyIdI601select
						,
					},
					// 导航栏的切换
					'ridata_WarehouseTacticsConfigureUI tabpanel[id=tabPIdI601]' : {
						tabchange : this.tabPidchange
					},
					// 返配单别的判断
					'ridata_WarehouseTacticsConfigureUI wms_DefFieldValCombo[id=cmbExpTypeI601]' : {
						select : this.cmbExpTypeI601Select
					},// 返配单别的判断
					'ridata_WarehouseTacticsConfigureUI bdef_DefOwnerCombo[id=owner_noI601]' : {
						select : this.cmbExpTypeI601Select
					},
					// 编辑
					'ridata_WarehouseTacticsConfigureUI button[name=userEditButton]' : {
						click : this.userEditButtonClick
					},
					// 撤退
					'ridata_WarehouseTacticsConfigureUI button[name=userUndoButton]' : {
						click : this.userUndoButtonClick
					},
					// 上一条信息
					'ridata_WarehouseTacticsConfigureUI button[name=userPrevButton]' : {
						click : this.userPrevButtonClick
					},
					// 下一条信息
					'ridata_WarehouseTacticsConfigureUI button[name=userNextButton]' : {
						click : this.userNextButtonClick
					},// 策略-删除
					'ridata_WarehouseTacticsConfigureUI button[id=deleteI601]' : {
						click : this.deleteClick
					}
				});
			},
			deleteClick : function() {
				var rec = Ext.getCmp('grid_01_I601').getSelectionModel().getSelection();
				if (rec.length == 0) {
					Ext.example.msg($i18n.prompt,
							$i18n.prompt_please_select_the_rows_to_delete);
				} else {
					Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(
							button, text) {
						if (button == 'yes') { 
							var listDetail = [];					 
								var strDtl={
										columnId:'a.UNTREAD_TYPE',
										value:rec[0].get('untreadType')
									};
								listDetail.push(strDtl);
								strDtl={
										columnId:'a.CLASS_TYPE',
										value:rec[0].get('classType')
									};
								listDetail.push(strDtl);
								strDtl={
										columnId:'a.QUALITY_FLAG',
										value:rec[0].get('qualityFlag')
									};
								listDetail.push(strDtl);
								strDtl={
										columnId:'a.owner_no',
										value:rec[0].get('ownerNo')
									};
								listDetail.push(strDtl);
								strDtl={
										columnId:'a.WAREHOUSE_NO',
										value:rec[0].get('warehouseNo')
									};
								listDetail.push(strDtl);
								var strJson = Ext.JSON.encode(listDetail);
								
								var wheresql = {
									strQuery : strJson
								};
							Ext.Ajax.request({
										url : 'ridata_TacticsAction_deleteTactics',
										params : {
											levelFlag:"3",//返配策略配置
											wheresql : wheresql
										},
										success : function(response) { 
											var data = Ext
											.JSON.decode(response.responseText);
											if (data.isSucc) {
												Ext.getCmp('grid_01_I601').getStore()
														.load();
												Ext.example.msg($i18n.prompt, data.msg);
											} else {
												Ext.example.msg($i18n.prompt, "删除失败");
											}
										}
									});
						}
					});
				}
			},

			// 点击新单
			userAddButtonClick : function() {
				addTacticsI601();
				commonEditButton('menuI601', 'add');
				commonSetMsterReadOnly('form_01_I601', false);
				commonSetMsterReadOnlyByArray(new Array('owner_noI601',
								'cmbExpTypeI601', 'cmbPriorityI601',
								'cmbQualityI601'), false);
			},

			// 保存
			userSaveButtonClick : function() {
				if (!commonCheckIsInputAll('tabPIdI601_T2')) {
					return;
				} else {
					saveExpTypeI601();
					commonEditButton('menuI601', 'save');
				}
			},

			// 双击进入返配明细
			grid_01_I601Dblclick : function() {
				Ext.getCmp('tabPIdI601').items.items[1].setVisible(true);
				// isCanEdit3101=false;
				commonEditButton('menuI601', 'dbclick');
			},

			tabPidchange : function(tabPanel, newCard, oldCard, eOpts) {
				if (newCard.id == 'tabPIdI601_T2') {
					var data = Ext.getCmp('grid_01_I601').getSelectionModel()
							.getSelection();
					if (data.length != 0) {
						loadTacticsI601(data[0].index);
						g_intRowIndexI601 = data[0].index;

						var wheresql = {
							strExpType : data[0].data.expType,
							strType : '0'
						};
						commonEditButton('menuI601', 'dbclick');
					}
				}
			},

			// 返配单别的判断
			cmbExpTypeI601Select : function(combo) {
				if (Ext.getCmp('cmbExpTypeI601').getValue() != null
						&& Ext.getCmp('owner_noI601').getValue()) {
					Ext.Ajax.request({
								url : 'ridata_TacticsAction_checkExpType',
								params : {
									strExpType : Ext.getCmp('cmbExpTypeI601')
											.getValue(),
									// strPriority :
									// Ext.getCmp('cmbPriorityI601').getValue(),
									// strQuality :
									// Ext.getCmp('cmbQualityI601').getValue(),
									ownerNo : Ext.getCmp('owner_noI601')
											.getValue(),
									wheresql : 2
								},
								success : function(response) {
									var data = Ext
											.decode(response.responseText);
									if (data.isSucc) {
										// Ext.example.msg($i18n.prompt,data.msg);
									} else {
										Ext.example.msg($i18n.prompt, data.msg);
										Ext.getCmp('cmbExpTypeI601')
												.setValue(null);
										Ext.getCmp('owner_noI601')
												.setValue(null);
									}
								}
							});

					// 加载波次规则号
					var wheresql = {
						strExpType : Ext.getCmp('cmbExpTypeI601').getValue()
					};
				}
			},

			// 选择波次规则号，带出波次规则信息
			cmbstrategyIdI601select : function(combo, records, eOpts) {
				Ext.getCmp("strategyNameI601")
						.setValue(records[0].data.strategyName);
				Ext.getCmp("betweenTimesI601")
						.setValue(records[0].data.betweenTimes);
				commonSetMsterReadOnlyByArray(new Array('strategyNameI601',
								'betweenTimesI601'), true);

			},

			// 修改
			userEditButtonClick : function() {
				// isCanEdit3101=true;
				commonEditButton('menuI601', 'edit');
				commonSetMsterReadOnly('form_01_I601', false);
				commonSetMsterReadOnlyByArray(new Array('cmbExpTypeI601',
								'cmbPriorityI601', 'strategyNameI601',
								'betweenTimesI601'), true);
			},
			// 撤退
			userUndoButtonClick : function() {
				var data = Ext.getCmp('grid_01_I601').getSelectionModel()
						.getSelection();
				if (data.length != 0) {
					loadTacticsI601(data[0].index);
					g_intRowIndexI601 = data[0].index;

					var wheresql = {
						strExpType : data[0].data.expType,
						strType : '0'
					};
				}

				commonSetMsterReadOnly('form_01_I601', true);
				commonSetMsterReadOnlyByArray(new Array('cmbExpTypeI601',
								'cmbPriorityI601'), true);
				commonEditButton('menuI601', 'undo');
			},

			// 上一条信息
			userPrevButtonClick : function() {
				g_intRowIndexI601 = g_intRowIndexI601 - 1;
				loadTacticsI601(g_intRowIndexI601);
			},

			// 下一条信息
			userNextButtonClick : function() {
				g_intRowIndexI601 = g_intRowIndexI601 + 1;
				loadTacticsI601(g_intRowIndexI601);
			},

			close : function() {
				// Ext.getCmp('odata_TacticsAddOrEditWindow').close();
			}

		});

function saveExpTypeI601() {
	if (Ext.getCmp('form_01_I601').getForm().isValid()) {
		var detail = {
			id : {
				enterpriseNo : Ext.get('enterpriseNo').getValue(),
				warehouseNo : Ext.getCmp('cmbwarehouseNoI601').getValue(),
				ownerNo : Ext.getCmp('owner_noI601').getValue(),
				untreadType : Ext.getCmp('cmbExpTypeI601').getValue(),
				classType : Ext.getCmp('cmbPriorityI601').getValue(),
				qualityFlag : Ext.getCmp('cmbQualityI601').getValue()
				,
			},

			overQtyFlag : Ext.getCmp('cmbMflagI601').getValue(),
			autoInstock : Ext.getCmp('cmbWflagI601').getValue(),
			advanceLocate : Ext.getCmp('cmbDflagI601').getValue(),
			deviceCompute : Ext.getCmp('cmbSflagI601').getValue(),
			mixOrdercheck : Ext.getCmp('cmbBdivideFlagI601').getValue(),
			rsvLabelFlag : Ext.getCmp('cmbRSVLabelI601').getValue(),
			directCellFlag : Ext.getCmp('cmbDirectCellI601').getValue(),
			autoCheckComfirFlag : Ext.getCmp('cmbCdivideFlagI601').getValue(),
			printCheckFlag : Ext.getCmp('cmbSendbufComputeI601').getValue(),

			orderRsv01 : Ext.getCmp('txtOrderRsv01_I601').getValue(),
			orderRsv02 : Ext.getCmp('txtOrderRsv02_I601').getValue(),
			orderRsv03 : Ext.getCmp('txtOrderRsv03_I601').getValue(),
			orderRsv04 : Ext.getCmp('txtOrderRsv04_I601').getValue(),
			orderRsv05 : Ext.getCmp('txtOrderRsv05_I601').getValue(),
			orderRsv06 : Ext.getCmp('txtOrderRsv06_I601').getValue(),
			orderRsv07 : Ext.getCmp('txtOrderRsv07_I601').getValue(),
			orderRsv08 : Ext.getCmp('txtOrderRsv08_I601').getValue(),
			rgstName : Ext.get('workerNo').getValue(),
			rgstDate : new Date()
		};
		var strJsonDetail = Ext.encode(detail);
		Ext.Ajax.request({
					url : 'ridata_TacticsAction_saveOrUpdateWasehouseTactics.action',
					method : 'post',
					params : {
						strJsonDetail : strJsonDetail
					},
					success : function(response) {
						var data = Ext.decode(response.responseText);
						if (data.isSucc) {
							Ext.getCmp('grid_01_I601').getStore().load();
							var wheresql = {
								strExpType : Ext.getCmp('cmbExpTypeI601')
										.getValue(),
								strType : '0'
							};
							Ext.example.msg($i18n.prompt, data.msg);
						} else {
							Ext.example.msg($i18n.prompt, data.msg);
						}
					}
				});
	}
};

function addTacticsI601() {
	Ext.getCmp('cmbwarehouseNoI601')
			.setValue(Ext.get('warehouseNo').getValue());
	Ext.getCmp('cmbExpTypeI601').setValue(null);
	Ext.getCmp('cmbPriorityI601').setValue(null);
	Ext.getCmp('cmbQualityI601').setValue(null);

	Ext.getCmp('cmbMflagI601').setValue('1');
	Ext.getCmp('cmbWflagI601').setValue('1');
	Ext.getCmp('cmbSflagI601').setValue('1');
	Ext.getCmp('cmbDflagI601').setValue('1');
	Ext.getCmp('cmbDirectCellI601').setValue('0');
	Ext.getCmp('cmbRSVLabelI601').setValue('0');
	Ext.getCmp('cmbBdivideFlagI601').setValue('0');
	Ext.getCmp('cmbCdivideFlagI601').setValue('0');
	Ext.getCmp('cmbSendbufComputeI601').setValue('0');

};

function loadTacticsI601(g_intRowIndexI601) {

	if (g_intRowIndexI601 == 0) {
		Ext.getCmp('menuI601').items.items[0].disable(true);
	} else {
		Ext.getCmp('menuI601').items.items[0].enable(true);
	}
	if (g_intRowIndexI601 == Ext.getCmp('grid_01_I601').getStore().getCount()
			- 1) {
		Ext.getCmp('menuI601').items.items[1].disable(true);
	} else {
		Ext.getCmp('menuI601').items.items[1].enable(true);
	}
	var record = Ext.getCmp('grid_01_I601').getStore().getAt(g_intRowIndexI601
			- (Ext.getCmp('grid_01_I601').getStore().currentPage - 1)
			* appConfig.getPageSize());
	Ext.getCmp('cmbwarehouseNoI601').setValue(record.data.warehouseNo);
	Ext.getCmp('owner_noI601').setValue(record.data.ownerNo);
	Ext.getCmp('cmbExpTypeI601').setValue(record.data.untreadType);
	Ext.getCmp('cmbPriorityI601').setValue(record.data.classType);
	Ext.getCmp('cmbQualityI601').setValue(record.data.qualityFlag);

	Ext.getCmp('cmbMflagI601').setValue(record.data.overQtyFlag);
	Ext.getCmp('cmbWflagI601').setValue(record.data.autoInstock);
	Ext.getCmp('cmbDflagI601').setValue(record.data.advanceLocate);
	Ext.getCmp('cmbSflagI601').setValue(record.data.deviceCompute);
	Ext.getCmp('cmbDirectCellI601').setValue(record.data.directCellFlag);
	Ext.getCmp('cmbRSVLabelI601').setValue(record.data.rsvLabelFlag);
	Ext.getCmp('cmbCdivideFlagI601').setValue(record.data.autoCheckComfirFlag);
	Ext.getCmp('cmbSendbufComputeI601').setValue(record.data.printCheckFlag);
	Ext.getCmp('cmbBdivideFlagI601').setValue(record.data.mixOrdercheck);

	Ext.getCmp('txtOrderRsv01_I601').setValue(record.data.orderRsv01);
	Ext.getCmp('txtOrderRsv02_I601').setValue(record.data.orderRsv02);
	Ext.getCmp('txtOrderRsv03_I601').setValue(record.data.orderRsv03);
	Ext.getCmp('txtOrderRsv04_I601').setValue(record.data.orderRsv04);
	Ext.getCmp('txtOrderRsv05_I601').setValue(record.data.orderRsv05);
	Ext.getCmp('txtOrderRsv06_I601').setValue(record.data.orderRsv06);
	Ext.getCmp('txtOrderRsv07_I601').setValue(record.data.orderRsv07);
	Ext.getCmp('txtOrderRsv08_I601').setValue(record.data.orderRsv08);
	commonSetMsterReadOnlyByArray(new Array('cmbExpTypeI601', 'owner_noI601',
					'cmbPriorityI601', 'cmbQualityI601'), true);

	var wheresql = {
		strExpType : Ext.getCmp('cmbExpTypeI601').getValue(),
		strType : '0'
	};
}
