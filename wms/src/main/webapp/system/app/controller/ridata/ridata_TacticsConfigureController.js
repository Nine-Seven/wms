/**
 * 模块名称：返配策略配置 模块代码：I501
 * 
 * @author hezf
 */
var g_intRowIndexI501 = 0;
var g_isCanEditI501 = false;
Ext.define('cms.controller.ridata.ridata_TacticsConfigureController', {
	extend : 'Ext.app.Controller',
	requires : ['cms.view.ridata.ridata_TacticsConfigureUI',
			//'cms.view.odata.window.odata_TacticsAddOrEditWindow'
	            ],
	init : function() {
		this.control({
			// 点击新单
			'ridata_TacticsConfigureUI button[name=userAddButton]' : {
				click : this.userAddButtonClick
			},
			// 保存
			'ridata_TacticsConfigureUI button[name=userSaveButton]' : {
				click : this.userSaveButtonClick
			},
			// 双击进入返配明细
			'ridata_TacticsConfigureUI grid[id=grid_01_I501]' : {
				itemdblclick : this.grid_01_I501Dblclick
			},
			// 选择波次规则号，带出波规则信息
			'ridata_TacticsConfigureUI form combo[id=cmbstrategyIdI501]' : {
				select : this.cmbstrategyIdI501select
				,
			},
			// 导航栏的切换
			'ridata_TacticsConfigureUI tabpanel[id=tabPIdI501]' : {
				tabchange : this.tabPidchange
			},
			// 返配单别的判断
			'ridata_TacticsConfigureUI wms_DefFieldValCombo[id=cmbExpTypeI501]' : {
				select : this.cmbExpTypeI501Select
			},// 返配单别的判断
			'ridata_TacticsConfigureUI wms_DefFieldValCombo[id=cmbPriorityI501]' : {
				select : this.cmbExpTypeI501Select
			},// 返配单别的判断
			'ridata_TacticsConfigureUI wms_DefFieldValCombo[id=cmbQualityI501]' : {
				select : this.cmbExpTypeI501Select
			},
			// 编辑
			'ridata_TacticsConfigureUI button[name=userEditButton]' : {
				click : this.userEditButtonClick
			},
			// 撤退
			'ridata_TacticsConfigureUI button[name=userUndoButton]' : {
				click : this.userUndoButtonClick
			},
			// 上一条信息
			'ridata_TacticsConfigureUI button[name=userPrevButton]' : {
				click : this.userPrevButtonClick
			},
			// 下一条信息
			'ridata_TacticsConfigureUI button[name=userNextButton]' : {
				click : this.userNextButtonClick
			},// 策略-删除
			'ridata_TacticsConfigureUI button[id=deleteI501]' : {
				click : this.deleteClick
			}
		});
	},
	deleteClick : function() {
		var rec = Ext.getCmp('grid_01_I501').getSelectionModel().getSelection();
		if (rec.length == 0) {
			Ext.example.msg($i18n.prompt,
					$i18n.prompt_please_select_the_rows_to_delete);
		} else {
			Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(
					button, text) {
				if (button == 'yes') {debugger;
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
						
						var strJson = Ext.JSON.encode(listDetail);
						
						var wheresql = {
							strQuery : strJson
						};
					Ext.Ajax.request({
								url : 'ridata_TacticsAction_deleteTactics',
								params : {
									levelFlag:"1",//返配策略配置
									wheresql : wheresql
								},
								success : function(response) { 
									var data = Ext
									.JSON.decode(response.responseText);
									if (data.isSucc) {
										Ext.getCmp('grid_01_I501').getStore()
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
		addTacticsI501();
		commonEditButton('menuI501', 'add');
		commonSetMsterReadOnly('form_01_I501', false);
		commonSetMsterReadOnlyByArray(new Array('cmbExpTypeI501',
						'cmbPriorityI501', 'cmbQualityI501'), false);
	},

	// 保存
	userSaveButtonClick : function() {
		if (!commonCheckIsInputAll('tabPIdI501_T2')) {
			return;
		} else {
			saveExpTypeI501();			 
			commonEditButton('menuI501', 'save');
		}
	},

	// 双击进入返配明细
	grid_01_I501Dblclick : function() {
		Ext.getCmp('tabPIdI501').items.items[1].setVisible(true);
		// isCanEdit3101=false;
		commonEditButton('menuI501', 'dbclick');
	},

	tabPidchange : function(tabPanel, newCard, oldCard, eOpts) {
		if (newCard.id == 'tabPIdI501_T2') {
			var data = Ext.getCmp('grid_01_I501').getSelectionModel()
					.getSelection();
			if (data.length != 0) {
				loadTacticsI501(data[0].index);
				g_intRowIndexI501 = data[0].index;
 
				var wheresql = {
					strExpType : data[0].data.expType,
					strType : '0'
				};
				commonEditButton('menuI501', 'dbclick');
			}
		}
	},

	// 返配单别的判断
	cmbExpTypeI501Select : function(combo) {
		if (Ext.getCmp('cmbExpTypeI501').getValue() != null
				&& Ext.getCmp('cmbPriorityI501').getValue() != null
				&& Ext.getCmp('cmbQualityI501').getValue() != null) {
			Ext.Ajax.request({
						url : 'ridata_TacticsAction_checkExpType',
						params : {
							strExpType : Ext.getCmp('cmbExpTypeI501').getValue(),
							strPriority : Ext.getCmp('cmbPriorityI501').getValue(),
							strQuality : Ext.getCmp('cmbQualityI501').getValue(),
							wheresql : 1
						},
						success : function(response) {
							var data = Ext.decode(response.responseText);
							if (data.isSucc) {
								// Ext.example.msg($i18n.prompt,data.msg);
							} else {
								Ext.example.msg($i18n.prompt, data.msg);
								Ext.getCmp('cmbExpTypeI501').setValue(null);
								Ext.getCmp('cmbQualityI501').setValue(null);
								Ext.getCmp('cmbPriorityI501').setValue(null);
							}
						}
					});

			// 加载波次规则号
			var wheresql = {
				strExpType : Ext.getCmp('cmbExpTypeI501').getValue()
			};
		}
	},

	// 选择波次规则号，带出波次规则信息
	cmbstrategyIdI501select : function(combo, records, eOpts) {
		Ext.getCmp("strategyNameI501").setValue(records[0].data.strategyName);
		Ext.getCmp("betweenTimesI501").setValue(records[0].data.betweenTimes);
		commonSetMsterReadOnlyByArray(new Array('strategyNameI501',
						'betweenTimesI501'), true);

	},

	// 修改
	userEditButtonClick : function() {
		// isCanEdit3101=true;
		commonEditButton('menuI501', 'edit');
		commonSetMsterReadOnly('form_01_I501', false);
		commonSetMsterReadOnlyByArray(new Array('cmbExpTypeI501',
						'cmbPriorityI501', 'strategyNameI501',
						'betweenTimesI501'), true);
	},
	// 撤退
	userUndoButtonClick : function() {
		var data = Ext.getCmp('grid_01_I501').getSelectionModel()
				.getSelection();
		if (data.length != 0) {
			loadTacticsI501(data[0].index);
			g_intRowIndexI501 = data[0].index;

			var wheresql = {
				strExpType : data[0].data.expType,
				strType : '0'
			};
		}

		commonSetMsterReadOnly('form_01_I501', true);
		commonSetMsterReadOnlyByArray(new Array('cmbExpTypeI501',
						'cmbPriorityI501'), true);
		commonEditButton('menuI501', 'undo');
	},

	// 上一条信息
	userPrevButtonClick : function() {
		g_intRowIndexI501 = g_intRowIndexI501 - 1;
		loadTacticsI501(g_intRowIndexI501);
	},

	// 下一条信息
	userNextButtonClick : function() {		
		g_intRowIndexI501 = g_intRowIndexI501 + 1;
		loadTacticsI501(g_intRowIndexI501);
	},

	close : function() {
		//Ext.getCmp('odata_TacticsAddOrEditWindow').close();
	}

});

function saveExpTypeI501() {
	if (Ext.getCmp('form_01_I501').getForm().isValid()) {
		var detail = {
			id : {
				enterpriseNo : Ext.get('enterpriseNo').getValue(),
				untreadType : Ext.getCmp('cmbExpTypeI501').getValue(),
				classType : Ext.getCmp('cmbPriorityI501').getValue(),
				qualityFlag : Ext.getCmp('cmbQualityI501').getValue(),				
			},

			overQtyFlag : Ext.getCmp('cmbMflagI501').getValue(),
			autoInstock : Ext.getCmp('cmbWflagI501').getValue(),
			advanceLocate : Ext.getCmp('cmbDflagI501').getValue(),
			deviceCompute : Ext.getCmp('cmbSflagI501').getValue(),
			mixOrdercheck : Ext.getCmp('cmbBdivideFlagI501').getValue(),
			rsvLabelFlag : Ext.getCmp('cmbRSVLabelI501').getValue(),
			directCellFlag : Ext.getCmp('cmbDirectCellI501').getValue(),
			autoCheckComfirFlag : Ext.getCmp('cmbCdivideFlagI501').getValue(),
			printCheckFlag : Ext.getCmp('cmbSendbufComputeI501').getValue(),

			orderRsv01 : Ext.getCmp('txtOrderRsv01_I501').getValue(),
			orderRsv02 : Ext.getCmp('txtOrderRsv02_I501').getValue(),
			orderRsv03 : Ext.getCmp('txtOrderRsv03_I501').getValue(),
			orderRsv04 : Ext.getCmp('txtOrderRsv04_I501').getValue(),
			orderRsv05 : Ext.getCmp('txtOrderRsv05_I501').getValue(),
			orderRsv06 : Ext.getCmp('txtOrderRsv06_I501').getValue(),
			orderRsv07 : Ext.getCmp('txtOrderRsv07_I501').getValue(),
			orderRsv08 : Ext.getCmp('txtOrderRsv08_I501').getValue(),
			rgstName : Ext.get('workerNo').getValue(),
			rgstDate : new Date()
		};
		var strJsonDetail = Ext.encode(detail);
		Ext.Ajax.request({
					url : 'ridata_TacticsAction_saveOrUpdateTactics.action',
					method : 'post',
					params : {
						strJsonDetail : strJsonDetail
					},
					success : function(response) {
						var data = Ext.decode(response.responseText);
						if (data.isSucc) {
							Ext.getCmp('grid_01_I501').getStore().load();
							var wheresql = {
								strExpType : Ext.getCmp('cmbExpTypeI501')
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
 
function addTacticsI501() {
	Ext.getCmp('cmbExpTypeI501').setValue(null);
	Ext.getCmp('cmbPriorityI501').setValue(null);
	Ext.getCmp('cmbQualityI501').setValue(null);

	Ext.getCmp('cmbMflagI501').setValue('1');
	Ext.getCmp('cmbWflagI501').setValue('1');
	Ext.getCmp('cmbSflagI501').setValue('1');
	Ext.getCmp('cmbDflagI501').setValue('1');
	Ext.getCmp('cmbDirectCellI501').setValue('0');
	Ext.getCmp('cmbRSVLabelI501').setValue('0');
	Ext.getCmp('cmbBdivideFlagI501').setValue('0');
	Ext.getCmp('cmbCdivideFlagI501').setValue('0');
	Ext.getCmp('cmbSendbufComputeI501').setValue('0');

};

function loadTacticsI501(g_intRowIndexI501) {
 
	if (g_intRowIndexI501 == 0) {
		Ext.getCmp('menuI501').items.items[0].disable(true);
	} else {
		Ext.getCmp('menuI501').items.items[0].enable(true);
	}
	if (g_intRowIndexI501 == Ext.getCmp('grid_01_I501').getStore().getCount()
			- 1) {
		Ext.getCmp('menuI501').items.items[1].disable(true);
	} else {
		Ext.getCmp('menuI501').items.items[1].enable(true);
	}
	var record = Ext.getCmp('grid_01_I501').getStore().getAt(g_intRowIndexI501
			- (Ext.getCmp('grid_01_I501').getStore().currentPage - 1)
			* appConfig.getPageSize());
	Ext.getCmp('cmbExpTypeI501').setValue(record.data.untreadType);
	Ext.getCmp('cmbPriorityI501').setValue(record.data.classType);
	Ext.getCmp('cmbQualityI501').setValue(record.data.qualityFlag);

	Ext.getCmp('cmbMflagI501').setValue(record.data.overQtyFlag);
	Ext.getCmp('cmbWflagI501').setValue(record.data.autoInstock);
	Ext.getCmp('cmbDflagI501').setValue(record.data.advanceLocate);
	Ext.getCmp('cmbSflagI501').setValue(record.data.deviceCompute);
	Ext.getCmp('cmbDirectCellI501').setValue(record.data.directCellFlag);
	Ext.getCmp('cmbRSVLabelI501').setValue(record.data.rsvLabelFlag);
	Ext.getCmp('cmbCdivideFlagI501').setValue(record.data.autoCheckComfirFlag);
	Ext.getCmp('cmbSendbufComputeI501').setValue(record.data.printCheckFlag);
	Ext.getCmp('cmbBdivideFlagI501').setValue(record.data.mixOrdercheck);

	Ext.getCmp('txtOrderRsv01_I501').setValue(record.data.orderRsv01);
	Ext.getCmp('txtOrderRsv02_I501').setValue(record.data.orderRsv02);
	Ext.getCmp('txtOrderRsv03_I501').setValue(record.data.orderRsv03);
	Ext.getCmp('txtOrderRsv04_I501').setValue(record.data.orderRsv04);
	Ext.getCmp('txtOrderRsv05_I501').setValue(record.data.orderRsv05);
	Ext.getCmp('txtOrderRsv06_I501').setValue(record.data.orderRsv06);
	Ext.getCmp('txtOrderRsv07_I501').setValue(record.data.orderRsv07);
	Ext.getCmp('txtOrderRsv08_I501').setValue(record.data.orderRsv08);
	commonSetMsterReadOnlyByArray(new Array('cmbExpTypeI501',
					'cmbPriorityI501', 'cmbQualityI501'), true);

	var wheresql = {
		strExpType : Ext.getCmp('cmbExpTypeI501').getValue(),
		strType : '0'
	};
}
