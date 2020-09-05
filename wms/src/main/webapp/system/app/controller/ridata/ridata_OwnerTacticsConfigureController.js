/**
 * 模块名称：返配策略配置 模块代码：I502
 * 
 * @author hezf
 */
var g_intRowIndexI502 = 0;
var g_isCanEditI502 = false;
Ext.define('cms.controller.ridata.ridata_OwnerTacticsConfigureController', {
	extend : 'Ext.app.Controller',
	requires : ['cms.view.ridata.ridata_OwnerTacticsConfigureUI',
	// 'cms.view.odata.window.odata_TacticsAddOrEditWindow'
	],
	init : function() {
		this.control({
			// 点击新单
			'ridata_OwnerTacticsConfigureUI button[name=userAddButton]' : {
				click : this.userAddButtonClick
			},
			// 保存
			'ridata_OwnerTacticsConfigureUI button[name=userSaveButton]' : {
				click : this.userSaveButtonClick
			},
			// 双击进入返配明细
			'ridata_OwnerTacticsConfigureUI grid[id=grid_01_I502]' : {
				itemdblclick : this.grid_01_I502Dblclick
			},
			// 选择波次规则号，带出波规则信息
			'ridata_OwnerTacticsConfigureUI form combo[id=cmbstrategyIdI502]' : {
				select : this.cmbstrategyIdI502select
				,
			},
			// 导航栏的切换
			'ridata_OwnerTacticsConfigureUI tabpanel[id=tabPIdI502]' : {
				tabchange : this.tabPidchange
			},
			// 返配单别的判断
			'ridata_OwnerTacticsConfigureUI wms_DefFieldValCombo[id=cmbExpTypeI502]' : {
				select : this.cmbExpTypeI502Select
			},// 返配单别的判断
			'ridata_OwnerTacticsConfigureUI wms_DefFieldValCombo[id=cmbPriorityI502]' : {
				select : this.cmbExpTypeI502Select
			},

			// 编辑
			'ridata_OwnerTacticsConfigureUI button[name=userEditButton]' : {
				click : this.userEditButtonClick
			},
			// 撤退
			'ridata_OwnerTacticsConfigureUI button[name=userUndoButton]' : {
				click : this.userUndoButtonClick
			},
			// 上一条信息
			'ridata_OwnerTacticsConfigureUI button[name=userPrevButton]' : {
				click : this.userPrevButtonClick
			},
			// 下一条信息
			'ridata_OwnerTacticsConfigureUI button[name=userNextButton]' : {
				click : this.userNextButtonClick
			},// 策略-删除
			'ridata_OwnerTacticsConfigureUI button[id=deleteI502]' : {
				click : this.deleteClick
			}
		});
	},
	deleteClick : function() {
		var rec = Ext.getCmp('grid_01_I502').getSelectionModel().getSelection();
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
						var strJson = Ext.JSON.encode(listDetail);
						
						var wheresql = {
							strQuery : strJson
						};
					Ext.Ajax.request({
								url : 'ridata_TacticsAction_deleteTactics',
								params : {
									levelFlag:"2",//返配策略配置
									wheresql : wheresql
								},
								success : function(response) { 
									var data = Ext
									.JSON.decode(response.responseText);
									if (data.isSucc) {
										Ext.getCmp('grid_01_I502').getStore()
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
		addTacticsI502();
		commonEditButton('menuI502', 'add');
		commonSetMsterReadOnly('form_01_I502', false);
		commonSetMsterReadOnlyByArray(new Array('owner_noI502',
						'cmbExpTypeI502', 'cmbPriorityI502', 'cmbQualityI502'),
				false);
	},

	// 保存
	userSaveButtonClick : function() {
		if (!commonCheckIsInputAll('tabPIdI502_T2')) {
			return;
		} else {
			saveExpTypeI502();
			commonEditButton('menuI502', 'save');
		}
	},

	// 双击进入返配明细
	grid_01_I502Dblclick : function() {
		Ext.getCmp('tabPIdI502').items.items[1].setVisible(true);
		// isCanEdit3101=false;
		commonEditButton('menuI502', 'dbclick');
	},

	tabPidchange : function(tabPanel, newCard, oldCard, eOpts) {
		debugger;
		if (newCard.id == 'tabPIdI502_T2') {
			var data = Ext.getCmp('grid_01_I502').getSelectionModel()
					.getSelection();
			if (data.length != 0) {
				loadTacticsI502(data[0].index);
				g_intRowIndexI502 = data[0].index;

				var wheresql = {
					strExpType : data[0].data.expType,
					strType : '0'
				};
				commonEditButton('menuI502', 'dbclick');
			}
		}
	},

	// 返配单别的判断
	cmbExpTypeI502Select : function(combo) {
		if (Ext.getCmp('cmbExpTypeI502').getValue() != null
				&& Ext.getCmp('cmbPriorityI502').getValue() != null) {
			Ext.Ajax.request({
						url : 'ridata_TacticsAction_checkExpType',
						params : {
							strExpType : Ext.getCmp('cmbExpTypeI502')
									.getValue(),
							strPriority : Ext.getCmp('cmbPriorityI502')
									.getValue(),
							strQuality : Ext.getCmp('cmbQualityI502')
									.getValue(),
							wheresql : 1
						},
						success : function(response) {
							var data = Ext.decode(response.responseText);
							if (data.isSucc) {
								// Ext.example.msg($i18n.prompt,data.msg);
							} else {
								Ext.example.msg($i18n.prompt, data.msg);
								Ext.getCmp('cmbExpTypeI502').setValue(null);
								Ext.getCmp('cmbQualityI502').setValue(null);
								Ext.getCmp('cmbPriorityI502').setValue(null);
							}
						}
					});

			// 加载波次规则号
			var wheresql = {
				strExpType : Ext.getCmp('cmbExpTypeI502').getValue()
			};
		}
	},

	// 选择波次规则号，带出波次规则信息
	cmbstrategyIdI502select : function(combo, records, eOpts) {
		Ext.getCmp("strategyNameI502").setValue(records[0].data.strategyName);
		Ext.getCmp("betweenTimesI502").setValue(records[0].data.betweenTimes);
		commonSetMsterReadOnlyByArray(new Array('strategyNameI502',
						'betweenTimesI502'), true);

	},

	// 修改
	userEditButtonClick : function() {
		// isCanEdit3101=true;
		commonEditButton('menuI502', 'edit');
		commonSetMsterReadOnly('form_01_I502', false);
		commonSetMsterReadOnlyByArray(new Array('cmbExpTypeI502',/*'owner_noI502',*/
						'cmbPriorityI502', 'strategyNameI502',
						'betweenTimesI502'), true);
	},
	// 撤退
	userUndoButtonClick : function() {
		var data = Ext.getCmp('grid_01_I502').getSelectionModel()
				.getSelection();
		if (data.length != 0) {
			loadTacticsI502(data[0].index);
			g_intRowIndexI502 = data[0].index;

			var wheresql = {
				strExpType : data[0].data.expType,
				strType : '0'
			};
		}

		commonSetMsterReadOnly('form_01_I502', true);
		commonSetMsterReadOnlyByArray(new Array('cmbExpTypeI502','owner_noI502',
						'cmbQualityI502','cmbPriorityI502'), true);
		commonEditButton('menuI502', 'undo');
	},

	// 上一条信息
	userPrevButtonClick : function() {
		g_intRowIndexI502 = g_intRowIndexI502 - 1;
		loadTacticsI502(g_intRowIndexI502);
	},

	// 下一条信息
	userNextButtonClick : function() {
		g_intRowIndexI502 = g_intRowIndexI502 + 1;
		loadTacticsI502(g_intRowIndexI502);
	},

	close : function() {
		// Ext.getCmp('odata_TacticsAddOrEditWindow').close();
	}

});

function saveExpTypeI502() {
	if (Ext.getCmp('form_01_I502').getForm().isValid()) {
		var detail = {
			id : {
				enterpriseNo : Ext.get('enterpriseNo').getValue(),
				ownerNo : Ext.getCmp('owner_noI502').getValue(),
				untreadType : Ext.getCmp('cmbExpTypeI502').getValue(),
				classType : Ext.getCmp('cmbPriorityI502').getValue(),
				qualityFlag : Ext.getCmp('cmbQualityI502').getValue()
				,
			},

			overQtyFlag : Ext.getCmp('cmbMflagI502').getValue(),
			autoInstock : Ext.getCmp('cmbWflagI502').getValue(),
			advanceLocate : Ext.getCmp('cmbDflagI502').getValue(),
			deviceCompute : Ext.getCmp('cmbSflagI502').getValue(),
			mixOrdercheck : Ext.getCmp('cmbBdivideFlagI502').getValue(),
			rsvLabelFlag : Ext.getCmp('cmbRSVLabelI502').getValue(),
			directCellFlag : Ext.getCmp('cmbDirectCellI502').getValue(),
			autoCheckComfirFlag : Ext.getCmp('cmbCdivideFlagI502').getValue(),
			printCheckFlag : Ext.getCmp('cmbSendbufComputeI502').getValue(),

			orderRsv01 : Ext.getCmp('txtOrderRsv01_I502').getValue(),
			orderRsv02 : Ext.getCmp('txtOrderRsv02_I502').getValue(),
			orderRsv03 : Ext.getCmp('txtOrderRsv03_I502').getValue(),
			orderRsv04 : Ext.getCmp('txtOrderRsv04_I502').getValue(),
			orderRsv05 : Ext.getCmp('txtOrderRsv05_I502').getValue(),
			orderRsv06 : Ext.getCmp('txtOrderRsv06_I502').getValue(),
			orderRsv07 : Ext.getCmp('txtOrderRsv07_I502').getValue(),
			orderRsv08 : Ext.getCmp('txtOrderRsv08_I502').getValue(),
			rgstName : Ext.get('workerNo').getValue(),
			rgstDate : new Date()
		};
		var strJsonDetail = Ext.encode(detail);
		Ext.Ajax.request({
					url : 'ridata_TacticsAction_saveOrUpdateOwnerTactics.action',
					method : 'post',
					params : {
						strJsonDetail : strJsonDetail
					},
					success : function(response) {
						var data = Ext.decode(response.responseText);
						if (data.isSucc) {
							Ext.getCmp('grid_01_I502').getStore().load();
							var wheresql = {
								strExpType : Ext.getCmp('cmbExpTypeI502')
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

function addTacticsI502() {

	Ext.getCmp('owner_noI502').setValue(null);
	Ext.getCmp('cmbExpTypeI502').setValue(null);
	Ext.getCmp('cmbPriorityI502').setValue(null);
	Ext.getCmp('cmbQualityI502').setValue(null);

	Ext.getCmp('cmbMflagI502').setValue('1');
	Ext.getCmp('cmbWflagI502').setValue('1');
	Ext.getCmp('cmbSflagI502').setValue('1');
	Ext.getCmp('cmbDflagI502').setValue('1');
	Ext.getCmp('cmbDirectCellI502').setValue('0');
	Ext.getCmp('cmbRSVLabelI502').setValue('0');
	Ext.getCmp('cmbBdivideFlagI502').setValue('0');
	Ext.getCmp('cmbCdivideFlagI502').setValue('0');
	Ext.getCmp('cmbSendbufComputeI502').setValue('0');

};

function loadTacticsI502(g_intRowIndexI502) {
	if (g_intRowIndexI502 == 0) {
		Ext.getCmp('menuI502').items.items[0].disable(true);
	} else {
		Ext.getCmp('menuI502').items.items[0].enable(true);
	}
	if (g_intRowIndexI502 == Ext.getCmp('grid_01_I502').getStore().getCount()
			- 1) {
		Ext.getCmp('menuI502').items.items[1].disable(true);
	} else {
		Ext.getCmp('menuI502').items.items[1].enable(true);
	}
	var record = Ext.getCmp('grid_01_I502').getStore().getAt(g_intRowIndexI502
			- (Ext.getCmp('grid_01_I502').getStore().currentPage - 1)
			* appConfig.getPageSize());
	Ext.getCmp('owner_noI502').setValue(record.data.ownerNo);
	Ext.getCmp('cmbExpTypeI502').setValue(record.data.untreadType);
	Ext.getCmp('cmbPriorityI502').setValue(record.data.classType);
	Ext.getCmp('cmbQualityI502').setValue(record.data.qualityFlag);

	Ext.getCmp('cmbMflagI502').setValue(record.data.overQtyFlag);
	Ext.getCmp('cmbWflagI502').setValue(record.data.autoInstock);
	Ext.getCmp('cmbDflagI502').setValue(record.data.advanceLocate);
	Ext.getCmp('cmbSflagI502').setValue(record.data.deviceCompute);
	Ext.getCmp('cmbDirectCellI502').setValue(record.data.directCellFlag);
	Ext.getCmp('cmbRSVLabelI502').setValue(record.data.rsvLabelFlag);
	Ext.getCmp('cmbCdivideFlagI502').setValue(record.data.autoCheckComfirFlag);
	Ext.getCmp('cmbSendbufComputeI502').setValue(record.data.printCheckFlag);
	Ext.getCmp('cmbBdivideFlagI502').setValue(record.data.mixOrdercheck);

	Ext.getCmp('txtOrderRsv01_I502').setValue(record.data.orderRsv01);
	Ext.getCmp('txtOrderRsv02_I502').setValue(record.data.orderRsv02);
	Ext.getCmp('txtOrderRsv03_I502').setValue(record.data.orderRsv03);
	Ext.getCmp('txtOrderRsv04_I502').setValue(record.data.orderRsv04);
	Ext.getCmp('txtOrderRsv05_I502').setValue(record.data.orderRsv05);
	Ext.getCmp('txtOrderRsv06_I502').setValue(record.data.orderRsv06);
	Ext.getCmp('txtOrderRsv07_I502').setValue(record.data.orderRsv07);
	Ext.getCmp('txtOrderRsv08_I502').setValue(record.data.orderRsv08);
	commonSetMsterReadOnlyByArray(new Array('cmbExpTypeI502','owner_noI502',
					'cmbPriorityI502', 'cmbQualityI502'), true);

/*	var wheresql = {
		strExpType : Ext.getCmp('cmbExpTypeI502').getValue(),
		strType : '0'
	};*/
}
