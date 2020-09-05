/**
 * 模块名称：出货策略配置 模块代码：3910
 * 
 * @author JUN
 */
var g_intRowIndex3910 = 0;
var g_isCanEdit3910 = false;
var editFlag = '1'; // 7-19 控制试算配置表格的编辑状态
Ext.define('cms.controller.odata.odata_TacticsConfigureController', {
	extend : 'Ext.app.Controller',
	requires : ['cms.view.odata.odata_TacticsConfigureUI',
			'cms.view.odata.window.odata_TacticsAddOrEditWindow'],
	init : function() {
		this.control({
			// 点击新单
			'odata_TacticsConfigureUI button[name=userAddButton]' : {
				click : this.userAddButtonClick
			},
			// 保存
			'odata_TacticsConfigureUI button[name=userSaveButton]' : {
				click : this.userSaveButtonClick
			},
			// 双击进入出货明细
			'odata_TacticsConfigureUI grid[id=grid_01_3910]' : {
				itemdblclick : this.grid_01_3910Dblclick
			},
			// 选择波次规则号，带出波规则信息
			'odata_TacticsConfigureUI form combo[id=cmbstrategyId3910]' : {
				select : this.cmbstrategyId3910select
				,
			},
			// 导航栏的切换
			'odata_TacticsConfigureUI tabpanel[id=tabPId3910]' : {
				tabchange : this.tabPidchange
			},
			// 出货单别的判断
			'odata_TacticsConfigureUI wms_DefFieldValCombo[id=cmbExpType3910]' : {
				select : this.cmbExpType3910Select
			},
			// 编辑
			'odata_TacticsConfigureUI button[name=userEditButton]' : {
				click : this.userEditButtonClick
			},
			// 撤退
			'odata_TacticsConfigureUI button[name=userUndoButton]' : {
				click : this.userUndoButtonClick
			},
			// 上一条信息
			'odata_TacticsConfigureUI button[name=userPrevButton]' : {
				click : this.userPrevButtonClick
			},
			// 下一条信息
			'odata_TacticsConfigureUI button[name=userNextButton]' : {
				click : this.userNextButtonClick
			},// 策略类型列表Grid单击
			'odata_TacticsConfigureUI grid[id=girdType3910]' : {
				selectionchange : this.girdType3910Selectionchange
			},// 策略ID选择前加载
			'wms_DefFieldValCombo[id=strategyid3910_1]' : {
				focus : this.strategyid3910_1Focus
			},// 策略头档列表Grid单击
			'odata_TacticsConfigureUI grid[id=girdM3910]' : {
				selectionchange : this.girdM3910Selectionchange
			},// 策略-删除
			'odata_TacticsConfigureUI button[id=delete3910]' : {
				click : this.deleteClick
			},// 出货单别查询选择
			'wms_DefFieldValCombo[id=cmbSearchExpType3910]' : {
				select : this.cmbSearchExpType3910Select
			},// 行业标识查询选择
			'wms_DefFieldValCombo[id=cmbSearchIndustryFlag3910]' : {
				select : this.cmbSearchIndustryFlag3910
			},// 策略头档-查找
			'odata_TacticsConfigureUI button[id=query3910]' : {
				click : this.strategyQuery
			}
		});
	},

	getEditFlag : function() {
		return editFlag;
	},

	setEditFlag : function(value) {
		editFlag = value;
	},
	// 查找
	strategyQuery : function() {
		Ext.create('cms.view.common.queryWindow', {}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId = '3910';
		queryGrid = 'grid_01_3910';
		Ext.getCmp('cmbSearchExpType3910').setValue('');
		Ext.getCmp('cmbSearchIndustryFlag3910').setValue('');
		// Ext.getCmp('strategyIdOrName3910').setValue('');
	},
	deleteClick : function() {
		var rec = Ext.getCmp('grid_01_3910').getSelectionModel().getSelection();
		if (rec.length == 0) {
			Ext.example.msg($i18n.prompt,
					$i18n.prompt_please_select_the_rows_to_delete);
		} else {
			Ext.Msg.confirm($i18n.prompt, $i18n.prompt_sure_delete, function(
					button, text) {
				if (button == 'yes') {
					Ext.Ajax.request({
								url : 'odata_TacticsAction_deleteTactics',
								params : {
									// strQuery:rec[0].get('expType')
									enterpriseNo : Ext.get('enterpriseNo')
											.getValue(),
									strExpType : rec[0].get('expType')
								},
								success : function(response) {
									var data = Ext
											.decode(response.responseText);
									if (data.isSucc) {
										Ext.getCmp('grid_01_3910').getStore()
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
	cmbSearchExpType3910Select : function() { 
		var listDetail = [];
		if(!Ext.isEmpty(Ext.getCmp('cmbSearchExpType3910').getValue())&&
				Ext.getCmp('cmbSearchExpType3910').getValue()!='ALL')
		{
			var strDtl={
					columnId:'wo.EXP_TYPE',
					value:Ext.getCmp('cmbSearchExpType3910').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('cmbSearchIndustryFlag3910').getValue())&&
				Ext.getCmp('cmbSearchIndustryFlag3910').getValue()!='ALL')
		{
			var strDtl={
					columnId:'wo.INDUSTRY_FLAG',
					value:Ext.getCmp('cmbSearchIndustryFlag3910').getValue()
				};
			listDetail.push(strDtl);
		}
		
		var strJson = Ext.encode(listDetail);
		
		var wheresql = {
			strQuery : strJson
		};
		/*var params = {
			strExpType : Ext.getCmp("cmbSearchExpType3910").getValue(),
			industryflag : Ext.getCmp("cmbSearchIndustryFlag3910").getValue()
		};*/
		Ext.apply(Ext.getCmp('grid_01_3910').getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('grid_01_3910').getStore().removeAll();
		Ext.getCmp('grid_01_3910').getStore().reload();
	},
	cmbSearchIndustryFlag3910 : function() {
		var listDetail = [];
		if(!Ext.isEmpty(Ext.getCmp('cmbSearchExpType3910').getValue())&&
				Ext.getCmp('cmbSearchExpType3910').getValue()!='ALL')
		{
			var strDtl={
					columnId:'wo.EXP_TYPE',
					value:Ext.getCmp('cmbSearchExpType3910').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('cmbSearchIndustryFlag3910').getValue())&&
				Ext.getCmp('cmbSearchIndustryFlag3910').getValue()!='ALL')
		{
			var strDtl={
					columnId:'wo.INDUSTRY_FLAG',
					value:Ext.getCmp('cmbSearchIndustryFlag3910').getValue()
				};
			listDetail.push(strDtl);
		}
		
		var strJson = Ext.encode(listDetail);
		
		var wheresql = {
			strQuery : strJson
		};
 
		Ext.apply(Ext.getCmp('grid_01_3910').getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('grid_01_3910').getStore().removeAll();
		Ext.getCmp('grid_01_3910').getStore().reload();
	},
	// 点击新单
	userAddButtonClick : function() {
		addTactics3910();
		commonEditButton('menu3910', 'add');
		commonSetMsterReadOnlyByArray(new Array('cmbExpType3910',
						'cmbPriority3910'), false);
		// Ext.getCmp('grid_03_3910').getStore().removeAll();
	},

	// 保存
	userSaveButtonClick : function() {

		if (!commonCheckIsInputAll('tabPId3910_T2')) {
			return;
		}
		if (!validInput()) {
			return;
		}
		/*
		 * var record = Ext.getCmp('grid_03_3910').getStore().query('flag',
		 * true);; if (record.length == 0) { Ext.example.msg("提示", "请选择工作流!");
		 * return; } else {
		 */

		saveExpType3910();
		// saveTactics3910();
		commonEditButton('menu3910', 'save');
		editFlag = '1';
		// }
	},

	// 双击进入出货明细
	grid_01_3910Dblclick : function() {
		Ext.getCmp('tabPId3910').items.items[1].setVisible(true);
		// isCanEdit3101=false;
		commonEditButton('menu3910', 'dbclick');
	},

	tabPidchange : function(tabPanel, newCard, oldCard, eOpts) {
		debugger;
		if (newCard.id == 'tabPId3910_T2') {
			var data = Ext.getCmp('grid_01_3910').getSelectionModel()
					.getSelection();
			if (data.length != 0) {
				loadTactics3910(data[0].index);
				g_intRowIndex3910 = data[0].index;

				// isCanEdit3101=false;
				/*
				 * var wheresql = { strExpType : data[0].data.expType, strType :
				 * '0' }; Ext .apply(
				 * Ext.getCmp('grid_03_3910').getStore().proxy.extraParams,
				 * wheresql); Ext.getCmp('grid_03_3910').getStore().removeAll();
				 * 
				 */
				commonEditButton('menu3910', 'dbclick');
			}
		}
		else
			{
			Ext.getCmp('grid_01_3910').getStore().removeAll();
			Ext.getCmp('grid_01_3910').getStore().reload();
			}
 
	},

	// 出货单别的判断
	cmbExpType3910Select : function(combo) {
		Ext.Ajax.request({
					url : 'odata_TacticsAction_checkExpType',
					params : {
						strExpType : combo.getValue(),
						wheresql : 1
					},
					success : function(response) {
						var data = Ext.decode(response.responseText);
						if (data.isSucc) {
							// Ext.example.msg($i18n.prompt,data.msg);
						} else {
							Ext.example.msg($i18n.prompt, data.msg);
							Ext.getCmp('cmbExpType3910').setValue(null);
						}
					}
				});

		/*
		 * // 加载波次规则号 var wheresql = { strExpType :
		 * Ext.getCmp('cmbExpType3910').getValue() };
		 * Ext.apply(Ext.getCmp('cmbstrategyId3910').getStore().proxy.extraParams,
		 * wheresql); Ext.getCmp('cmbstrategyId3910').getStore().removeAll();
		 * Ext.getCmp('cmbstrategyId3910').getStore().load();
		 */

	},

	// 选择波次规则号，带出波次规则信息
	cmbstrategyId3910select : function(combo, records, eOpts) {
		Ext.getCmp("strategyName3910").setValue(records[0].data.strategyName);
		Ext.getCmp("betweenTimes3910").setValue(records[0].data.betweenTimes);
		commonSetMsterReadOnlyByArray(new Array('strategyName3910',
						'betweenTimes3910'), true);

	},

	// 修改
	userEditButtonClick : function() {
		editFlag = '0';
		// isCanEdit3101=true;
		commonEditButton('menu3910', 'edit');
		// commonSetMsterReadOnly('form_01_3910', false);
		commonSetMsterReadOnlyByArray(new Array('cmbExpType3910',
						'cmbPriority3910', 'cmbDeliverObjLevel3910',
						'strategyName3910', 'betweenTimes3910'), true);
	},
	// 撤退
	userUndoButtonClick : function() {
		var data = Ext.getCmp('grid_01_3910').getSelectionModel()
				.getSelection();
		if (data.length != 0) {
			loadTactics3910(data[0].index);
			g_intRowIndex3910 = data[0].index;

			/*
			 * var wheresql = { strExpType : data[0].data.expType, strType : '0' };
			 * Ext.apply(Ext.getCmp('grid_03_3910').getStore().proxy.extraParams,
			 * wheresql); Ext.getCmp('grid_03_3910').getStore().removeAll();
			 * Ext.getCmp('grid_03_3910').getStore().load();
			 */
		}
		commonSetMsterReadOnlyByArray(new Array('cmbExpType3910',
						'cmbPriority3910'), true);
		commonEditButton('menu3910', 'undo');

		Ext.apply(Ext.getCmp('girdType3910').getStore().proxy.extraParams,
				wheresql);
		Ext.getCmp('girdType3910').getStore().removeAll();
		Ext.getCmp('girdType3910').getStore().load();

		Ext.getCmp('girdM3910').getStore().removeAll();
		Ext.getCmp('girdD3910').getStore().removeAll();
	},

	// 上一条信息
	userPrevButtonClick : function() {
		g_intRowIndex3910 = g_intRowIndex3910 - 1;
		loadTactics3910(g_intRowIndex3910);
	},

	// 下一条信息
	userNextButtonClick : function() {
		g_intRowIndex3910 = g_intRowIndex3910 + 1;
		loadTactics3910(g_intRowIndex3910);
	},

	close : function() {
		Ext.getCmp('odata_TacticsAddOrEditWindow').close();
	},
	// 策略类型列表选择
	girdType3910Selectionchange : function() {
		var data = Ext.getCmp('girdType3910').getSelectionModel()
				.getSelection();
		if (data.length != 0) {
			Ext.getCmp('girdD3910').getStore().removeAll();
			var listDetail = [];
			if (!Ext.isEmpty(data[0].get('strategyid'))) {
				var a = {
					columnId : 'a.strategyId',
					value : data[0].get('strategyid')
				};
				listDetail.push(a);
			}
			var strJson = Ext.encode(listDetail);

			var params = {
				strategyFlag : data[0].get('strategyFlag'),
				strQuery : strJson
			};
			Ext.apply(Ext.getCmp('girdM3910').getStore().proxy.extraParams,
					params);
			Ext.getCmp('girdM3910').getStore().removeAll();
			Ext.getCmp('girdM3910').getStore().load();

			// 控制策略明细显示字段
			gridDVisible3910(data[0].get('strategyFlag'));
		}
	},
	// 策略id选择前加载
	strategyid3910_1Focus : function() {
		var data = Ext.getCmp('girdType3910').getSelectionModel()
				.getSelection();
		if (data.length != 0) {
			var params = {
				strategyFlag : data[0].get('strategyFlag')
			};
			Ext
					.apply(
							Ext.getCmp('strategyid3910_1').getStore().proxy.extraParams,
							params);
			Ext.getCmp('strategyid3910_1').getStore().removeAll();
			Ext.getCmp('strategyid3910_1').getStore().load();
		}
	},
	// 策略头档列表选择
	girdM3910Selectionchange : function() {
		var data = Ext.getCmp('girdType3910').getSelectionModel()
				.getSelection();
		var rec = Ext.getCmp('girdM3910').getSelectionModel().getSelection();
		if (data.length != 0) {
			if (rec.length != 0) {
				var listDetail = [];
				var a = {
					columnId : 'a.strategyId',
					value : rec[0].get('strategyId')
				};
				listDetail.push(a);
				var strJson = Ext.encode(listDetail);

				var params = {
					strategyFlag : data[0].get('strategyFlag'),
					strQuery : strJson
				};
				Ext.apply(Ext.getCmp('girdD3910').getStore().proxy.extraParams,
						params);
				Ext.getCmp('girdD3910').getStore().removeAll();
				Ext.getCmp('girdD3910').getStore().load();

			}
		}
	}
});
function validInput() {
	var mygrid = Ext.getCmp('girdType3910');
	var flag = true;
	if (mygrid.getStore().getAt(0).data['strategyid'] == null) {
		Ext.example.msg($i18n.prompt, '请选择：自动批次规则策略！');
		flag = false;
	} else if (mygrid.getStore().getAt(1).data['strategyid'] == null) {
		Ext.example.msg($i18n.prompt, '请选择：手工批次规则策略！');
		flag = false;
	} else if (mygrid.getStore().getAt(2).data['strategyid'] == null) {
		Ext.example.msg($i18n.prompt, '请选择：分配策略！');
		flag = false;
	} else if (mygrid.getStore().getAt(3).data['strategyid'] == null) {
		Ext.example.msg($i18n.prompt, '请选择：试算策略！');
		flag = false;
	} else if (mygrid.getStore().getAt(4).data['strategyid'] == null) {
		Ext.example.msg($i18n.prompt, '请选择：工作流策略！');
		flag = false;
	} else if (mygrid.getStore().getAt(13).data['strategyid'] == null) {
		Ext.example.msg($i18n.prompt, '请选择：行业标识！');
		flag = false;
	}
	return flag;
}
function saveExpType3910() {
	// if (Ext.getCmp('form_01_3910').getForm().isValid()) {
	var mygrid = Ext.getCmp('girdType3910');

	var detail = {
		id : {
			enterpriseNo : Ext.get('enterpriseNo').getValue(),
			expType : Ext.getCmp('cmbExpType3910').getValue()
			,
		},
		priority : Ext.getCmp('cmbPriority3910').getValue(),
		autobatchStrategyId : mygrid.getStore().getAt(0).data['strategyid'],
		manualbatchStrategyId : mygrid.getStore().getAt(1).data['strategyid'],
		locateStrategyId : mygrid.getStore().getAt(2).data['strategyid'],
		computeStrategyId : mygrid.getStore().getAt(3).data['strategyid'],
		workflowStrategyId : mygrid.getStore().getAt(4).data['strategyid'],
		divideStrategyId : mygrid.getStore().getAt(5).data['strategyid'],
		BCheckStrategyId : mygrid.getStore().getAt(6).data['strategyid'],
		CCheckStrategyId : mygrid.getStore().getAt(7).data['strategyid'],
		comfireStrategyId : mygrid.getStore().getAt(8).data['strategyid'],
		loadStrategyId : mygrid.getStore().getAt(9).data['strategyid'],
		printPacklist : mygrid.getStore().getAt(10).data['strategyid'],
		printEnvoice : mygrid.getStore().getAt(11).data['strategyid'],
		printWaybill : mygrid.getStore().getAt(12).data['strategyid'],
		industryFlag : mygrid.getStore().getAt(13).data['strategyid'],

		rsv1StrategyId : mygrid.getStore().getAt(14).data['strategyid'],
		rsv2StrategyId : mygrid.getStore().getAt(15).data['strategyid'],
		rsv3StrategyId : mygrid.getStore().getAt(16).data['strategyid'],
		rsv4StrategyId : mygrid.getStore().getAt(17).data['strategyid'],
		rsv5StrategyId : mygrid.getStore().getAt(18).data['strategyid'],
		rsv6StrategyId : mygrid.getStore().getAt(19).data['strategyid'],
		rsv7StrategyId : mygrid.getStore().getAt(20).data['strategyid'],
		rsv8StrategyId : mygrid.getStore().getAt(21).data['strategyid'],
		rsv9StrategyId : mygrid.getStore().getAt(22).data['strategyid'],
		rsv10StrategyId : mygrid.getStore().getAt(23).data['strategyid'],

		rgstName : Ext.get('workerNo').getValue(),
		rgstDate : new Date()
	};
	var strJsonDetail = Ext.encode(detail);
	Ext.Ajax.request({
				url : 'odata_TacticsAction_saveOrUpdateTactics.action',
				method : 'post',
				params : {
					strJsonDetail : strJsonDetail
				},
				success : function(response) {
					var data = Ext.decode(response.responseText);
					if (data.isSucc) {
						Ext.example.msg($i18n.prompt, data.msg);
					} else {
						Ext.Msg.alert($i18n.prompt, data.msg);

						// Ext.example.msg($i18n.prompt,data.msg);
					}
				}
			});
	// }
};

function saveTactics3910() {
	// 判断是否有勾选工作流
	var detail1 = [];
	// 全部的
	var gridcount = Ext.getCmp('grid_03_3910').getStore().getCount();
	for (var i = 0; i < gridcount; i++) {
		var exp = Ext.getCmp('grid_03_3910').getStore().getAt(i);
		var d = {
			id : {
				enterpriseNo : Ext.get('enterpriseNo').getValue(),
				expType : Ext.getCmp('cmbExpType3910').getValue(),
				flowOrder : i
			},
			flowValue : exp.get('flowValue'),
			flowFlag : exp.get('flag') ? '1' : '0',
			rgstName : Ext.get('workerNo').getValue(),
			rgstDate : new Date()
		};
		detail1.push(d);
	}

	if (detail1.length != 0) {
		var jsonDetail1 = Ext.encode(detail1);
		Ext.Ajax.request({
			url : 'odata_TacticsAction_saveTactics',
			params : {
				strJsonDetail : jsonDetail1
			},
			success : function(response) {
				var data = Ext.decode(response.responseText);
				if (data.isSucc) {
					// Ext.example.msg($i18n.prompt,data.msg);
					var wheresql = {
						strExpType : Ext.getCmp('cmbExpType3910').getValue(),
						strType : '0'
					};
					Ext
							.apply(
									Ext.getCmp('grid_03_3910').getStore().proxy.extraParams,
									wheresql);
					Ext.getCmp('grid_03_3910').getStore().removeAll();
					Ext.getCmp('grid_03_3910').getStore().load();
				} else {
					// Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	} else {
		Ext.Ajax.request({
			url : 'odata_TacticsAction_deleteTactics',
			params : {
				strExpType : data[0].get('expType')
			},
			success : function(response) {
				var data = Ext.decode(response.responseText);
				if (data.isSucc) {
					// Ext.example.msg($i18n.prompt,data.msg);
					var wheresql = {
						strExpType : Ext.getCmp('cmbExpType3910').getValue(),
						strType : '0'
					};
					Ext
							.apply(
									Ext.getCmp('grid_03_3910').getStore().proxy.extraParams,
									wheresql);
					Ext.getCmp('grid_03_3910').getStore().removeAll();
					Ext.getCmp('grid_03_3910').getStore().load();
				} else {
					// Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	}
}

function addTactics3910() {
	editFlag = '0';
	Ext.getCmp('cmbExpType3910').setValue(null);
	Ext.getCmp('cmbPriority3910').setValue(null);
	// Ext.getCmp('cmbSelectFlagl3910').setValue('1');
	// 加载策略信息列表
	Ext.getCmp('girdType3910').getStore().removeAll();
	Ext.getCmp('girdType3910').getStore().reload();
	/*
	 * // 加载工作流 var wheresql = { strType : 1 }; Ext
	 * .apply(Ext.getCmp('grid_03_3910').getStore().proxy.extraParams,
	 * wheresql); Ext.getCmp('grid_03_3910').getStore().removeAll();
	 * Ext.getCmp('grid_03_3910').getStore().load();
	 */
};

function loadTactics3910(g_intRowIndex3910) {
	/*
	 * var article=Ext.getCmp('grid_01_1401').getStore().getAt(
	 * g_intRowIndex1401-(Ext.getCmp('grid_01_1401').getStore().currentPage-1)*appConfig.getPageSize());
	 */
	if (g_intRowIndex3910 == 0) {
		Ext.getCmp('menu3910').items.items[0].disable(true);
	} else {
		Ext.getCmp('menu3910').items.items[0].enable(true);
	}
	if (g_intRowIndex3910 == Ext.getCmp('grid_01_3910').getStore().getCount()
			- 1) {
		Ext.getCmp('menu3910').items.items[1].disable(true);
	} else {
		Ext.getCmp('menu3910').items.items[1].enable(true);
	}

	var record = Ext.getCmp('grid_01_3910').getStore().getAt(g_intRowIndex3910
			- (Ext.getCmp('grid_01_3910').getStore().currentPage - 1)
			* appConfig.getPageSize());
	Ext.getCmp('cmbExpType3910').setValue(record.data.expType);
	Ext.getCmp('cmbPriority3910').setValue(String(record.data.priority));

	debugger;
	var mygrid = Ext.getCmp('girdType3910');
	var total = mygrid.getStore().getCount();// 数据行数
	for (var i = 0; i < total; i++) {
		var eachRow = mygrid.getStore().getAt(i);
		if (eachRow.raw.strategyFlag == '01') {
			eachRow.set('strategyid', record.data.autobatchStrategyId);
		} else if (eachRow.raw.strategyFlag == '02') {
			eachRow.set('strategyid', record.data.manualbatchStrategyId);
		} else if (eachRow.raw.strategyFlag == '03') {
			eachRow.set('strategyid', record.data.locateStrategyId);
		} else if (eachRow.raw.strategyFlag == '04') {
			eachRow.set('strategyid', record.data.computeStrategyId);
		} else if (eachRow.raw.strategyFlag == '05') {
			eachRow.set('strategyid', record.data.computeStrategyId);
		} else if (eachRow.raw.strategyFlag == '12') {
			eachRow.set('strategyid', record.data.printPacklist);
		} else if (eachRow.raw.strategyFlag == '13') {
			eachRow.set('strategyid', record.data.printEnvoice);
		} else if (eachRow.raw.strategyFlag == '14') {
			eachRow.set('strategyid', record.data.printWaybill);
		} else if (eachRow.raw.strategyFlag == '15') {
			eachRow.set('strategyid', record.data.industryFlag);
		}
	}

	// Ext.getCmp('girdType3910').getStore().removeAll();
	/*
	 * Ext.getCmp('girdType3910').getStore().reload({ callback : function() {
	 * debugger; var mygrid = Ext.getCmp('girdType3910'); var total =
	 * mygrid.getStore().getCount();// 数据行数 for (var i = 0; i < total; i++) {
	 * var eachRow = mygrid.getStore().getAt(i); if (eachRow.raw.strategyFlag ==
	 * '01') { eachRow.set('strategyid', record.data.autobatchStrategyId); }
	 * else if (eachRow.raw.strategyFlag == '02') { eachRow .set('strategyid',
	 * record.data.manualbatchStrategyId); } else if (eachRow.raw.strategyFlag ==
	 * '03') { eachRow.set('strategyid', record.data.locateStrategyId); } else
	 * if (eachRow.raw.strategyFlag == '04') { eachRow.set('strategyid',
	 * record.data.computeStrategyId); } } } });
	 */

	/*
	 * Ext.getCmp('cmbDeliverObjLevel3910').setValue(record.data.deliverObjLevel);
	 * 
	 * Ext.getCmp('cmbMflag3910').setValue(record.data.MFlag);
	 * Ext.getCmp('cmbWflag3910').setValue(record.data.WFlag);
	 * Ext.getCmp('cmbSflag3910').setValue(record.data.SFlag);
	 * Ext.getCmp('cmbDflag3910').setValue(record.data.DFlag);
	 */

	/*
	 * commonSetMsterReadOnlyByArray(new Array('cmbExpType3910',
	 * 'strategyName3910', 'betweenTimes3910'), true);
	 */

};
// 控制策略明细显示字段
function gridDVisible3910(strategyFlag) {
	if (strategyFlag == '01' || strategyFlag == '02') {
		Ext.getCmp('batchRuleId3910').setVisible(true);
		Ext.getCmp('batchRuleName3910').setVisible(true);
		Ext.getCmp('seqOrder3910').setVisible(true);
		Ext.getCmp('statusText3910').setVisible(true);
		Ext.getCmp('batchComputeTypeText3910').setVisible(true);
		Ext.getCmp('batchComputeText3910').setVisible(true);
		Ext.getCmp('taskOrder3910').setVisible(true);
		Ext.getCmp('minOrder3910').setVisible(true);
		Ext.getCmp('orderSourceText3910').setVisible(true);
		Ext.getCmp('repeatTimes3910').setVisible(true);
		Ext.getCmp('deliverTypeText3910').setVisible(true);
		Ext.getCmp('deliverAddress3910').setVisible(true);
		Ext.getCmp('lineFlagText3910').setVisible(true);
		Ext.getCmp('shipperNoText3910').setVisible(true);
		Ext.getCmp('skuLimmit3910').setVisible(true);
		Ext.getCmp('waitTimes3910').setVisible(true);
		Ext.getCmp('intervalTimes3910').setVisible(true);
		Ext.getCmp('CLimmitText3910').setVisible(true);
		Ext.getCmp('areaLimmitText3910').setVisible(true);
		Ext.getCmp('itemTypeFlagText3910').setVisible(true);
		Ext.getCmp('printTypeText3910').setVisible(true);
		Ext.getCmp('printEnvoiceText3910').setVisible(true);
		Ext.getCmp('printWaybillText3910').setVisible(true);
		Ext.getCmp('printPacklistText3910').setVisible(true);
		Ext.getCmp('custLogiboxRuleIdText3910').setVisible(true);
		Ext.getCmp('taskRuleIdText3910').setVisible(true);

		Ext.getCmp('deliverObjLevelText3910').setVisible(false);
		Ext.getCmp('PFlagText3910').setVisible(false);
		Ext.getCmp('MFlagText3910').setVisible(false);
		Ext.getCmp('WFlagText3910').setVisible(false);
		Ext.getCmp('SFlagText3910').setVisible(false);
		Ext.getCmp('DFlagText3910').setVisible(false);
		Ext.getCmp('BDivideFlagText3910').setVisible(false);
		Ext.getCmp('CDivideFlagText3910').setVisible(false);
		Ext.getCmp('commitTypeText3910').setVisible(false);
		Ext.getCmp('shortqtyTypeText3910').setVisible(false);

		// 工作流策略
		Ext.getCmp('floworder13910').setVisible(false);
		Ext.getCmp('flowvalueText13910').setVisible(false);
		Ext.getCmp('flowflagText13910').setVisible(false);
		// 试算策略
		Ext.getCmp('checkcomputeflagText13910').setVisible(false);
		Ext.getCmp('checkcomputelevelText13910').setVisible(false);
		Ext.getCmp('checkcomputevalueText13910').setVisible(false);
		Ext.getCmp('sendbufcomputeflagText13910').setVisible(false);
		Ext.getCmp('sendbufcomputelevelText13910').setVisible(false);
		Ext.getCmp('sendbufcomputevalueText13910').setVisible(false);

	} else if (strategyFlag == '03') {
		Ext.getCmp('batchRuleId3910').setVisible(true);
		Ext.getCmp('batchRuleName3910').setVisible(true);
		Ext.getCmp('seqOrder3910').setVisible(false);
		Ext.getCmp('statusText3910').setVisible(false);
		Ext.getCmp('batchComputeTypeText3910').setVisible(false);
		Ext.getCmp('batchComputeText3910').setVisible(false);
		Ext.getCmp('taskOrder3910').setVisible(false);
		Ext.getCmp('minOrder3910').setVisible(false);
		Ext.getCmp('orderSourceText3910').setVisible(false);
		Ext.getCmp('repeatTimes3910').setVisible(false);
		Ext.getCmp('deliverTypeText3910').setVisible(false);
		Ext.getCmp('deliverAddress3910').setVisible(false);
		Ext.getCmp('lineFlagText3910').setVisible(false);
		Ext.getCmp('shipperNoText3910').setVisible(false);
		Ext.getCmp('skuLimmit3910').setVisible(false);
		Ext.getCmp('waitTimes3910').setVisible(false);
		Ext.getCmp('intervalTimes3910').setVisible(false);
		Ext.getCmp('CLimmitText3910').setVisible(false);
		Ext.getCmp('areaLimmitText3910').setVisible(false);
		Ext.getCmp('itemTypeFlagText3910').setVisible(false);
		Ext.getCmp('printTypeText3910').setVisible(false);
		Ext.getCmp('printEnvoiceText3910').setVisible(false);
		Ext.getCmp('printWaybillText3910').setVisible(false);
		Ext.getCmp('printPacklistText3910').setVisible(false);
		Ext.getCmp('custLogiboxRuleIdText3910').setVisible(false);
		Ext.getCmp('taskRuleIdText3910').setVisible(false);

		// 工作流
		Ext.getCmp('floworder13910').setVisible(false);
		Ext.getCmp('flowvalueText13910').setVisible(false);
		Ext.getCmp('flowflagText13910').setVisible(false);

		Ext.getCmp('deliverObjLevelText3910').setVisible(true);
		Ext.getCmp('PFlagText3910').setVisible(true);
		Ext.getCmp('MFlagText3910').setVisible(true);
		Ext.getCmp('WFlagText3910').setVisible(true);
		Ext.getCmp('SFlagText3910').setVisible(true);
		Ext.getCmp('DFlagText3910').setVisible(true);
		Ext.getCmp('BDivideFlagText3910').setVisible(true);
		Ext.getCmp('CDivideFlagText3910').setVisible(true);
		Ext.getCmp('commitTypeText3910').setVisible(true);
		Ext.getCmp('shortqtyTypeText3910').setVisible(true);
		// 试算策略
		Ext.getCmp('checkcomputeflagText13910').setVisible(false);
		Ext.getCmp('checkcomputelevelText13910').setVisible(false);
		Ext.getCmp('checkcomputevalueText13910').setVisible(false);
		Ext.getCmp('sendbufcomputeflagText13910').setVisible(false);
		Ext.getCmp('sendbufcomputelevelText13910').setVisible(false);
		Ext.getCmp('sendbufcomputevalueText13910').setVisible(false);
	} else if (strategyFlag == '04') {
		Ext.getCmp('batchRuleId3910').setVisible(true);
		Ext.getCmp('batchRuleName3910').setVisible(true);
		Ext.getCmp('seqOrder3910').setVisible(false);
		Ext.getCmp('statusText3910').setVisible(false);
		Ext.getCmp('seqOrder3910').setVisible(false);
		Ext.getCmp('statusText3910').setVisible(false);
		Ext.getCmp('batchComputeTypeText3910').setVisible(false);
		Ext.getCmp('batchComputeText3910').setVisible(false);
		Ext.getCmp('taskOrder3910').setVisible(false);
		Ext.getCmp('minOrder3910').setVisible(false);
		Ext.getCmp('orderSourceText3910').setVisible(false);
		Ext.getCmp('repeatTimes3910').setVisible(false);
		Ext.getCmp('deliverTypeText3910').setVisible(false);
		Ext.getCmp('deliverAddress3910').setVisible(false);
		Ext.getCmp('lineFlagText3910').setVisible(false);
		Ext.getCmp('shipperNoText3910').setVisible(false);
		Ext.getCmp('skuLimmit3910').setVisible(false);
		Ext.getCmp('waitTimes3910').setVisible(false);
		Ext.getCmp('intervalTimes3910').setVisible(false);
		Ext.getCmp('CLimmitText3910').setVisible(false);
		Ext.getCmp('areaLimmitText3910').setVisible(false);
		Ext.getCmp('itemTypeFlagText3910').setVisible(false);
		Ext.getCmp('printTypeText3910').setVisible(false);
		Ext.getCmp('printEnvoiceText3910').setVisible(false);
		Ext.getCmp('printWaybillText3910').setVisible(false);
		Ext.getCmp('printPacklistText3910').setVisible(false);
		Ext.getCmp('custLogiboxRuleIdText3910').setVisible(false);
		Ext.getCmp('taskRuleIdText3910').setVisible(false);

		// 工作流
		Ext.getCmp('floworder13910').setVisible(false);
		Ext.getCmp('flowvalueText13910').setVisible(false);
		Ext.getCmp('flowflagText13910').setVisible(false);

		Ext.getCmp('deliverObjLevelText3910').setVisible(false);
		Ext.getCmp('PFlagText3910').setVisible(false);
		Ext.getCmp('MFlagText3910').setVisible(false);
		Ext.getCmp('WFlagText3910').setVisible(false);
		Ext.getCmp('SFlagText3910').setVisible(false);
		Ext.getCmp('DFlagText3910').setVisible(false);
		Ext.getCmp('BDivideFlagText3910').setVisible(false);
		Ext.getCmp('CDivideFlagText3910').setVisible(false);
		Ext.getCmp('commitTypeText3910').setVisible(false);
		Ext.getCmp('shortqtyTypeText3910').setVisible(false);
		// 试算策略
		Ext.getCmp('checkcomputeflagText13910').setVisible(true);
		Ext.getCmp('checkcomputelevelText13910').setVisible(true);
		Ext.getCmp('checkcomputevalueText13910').setVisible(true);
		Ext.getCmp('sendbufcomputeflagText13910').setVisible(true);
		Ext.getCmp('sendbufcomputelevelText13910').setVisible(true);
		Ext.getCmp('sendbufcomputevalueText13910').setVisible(true);
	} else if (strategyFlag == '05') {
		Ext.getCmp('seqOrder3910').setVisible(false);
		Ext.getCmp('statusText3910').setVisible(false);
		Ext.getCmp('batchComputeTypeText3910').setVisible(false);
		Ext.getCmp('batchComputeText3910').setVisible(false);
		Ext.getCmp('taskOrder3910').setVisible(false);
		Ext.getCmp('minOrder3910').setVisible(false);
		Ext.getCmp('orderSourceText3910').setVisible(false);
		Ext.getCmp('repeatTimes3910').setVisible(false);
		Ext.getCmp('deliverTypeText3910').setVisible(false);
		Ext.getCmp('deliverAddress3910').setVisible(false);
		Ext.getCmp('lineFlagText3910').setVisible(false);
		Ext.getCmp('shipperNoText3910').setVisible(false);
		Ext.getCmp('skuLimmit3910').setVisible(false);
		Ext.getCmp('waitTimes3910').setVisible(false);
		Ext.getCmp('intervalTimes3910').setVisible(false);
		Ext.getCmp('CLimmitText3910').setVisible(false);
		Ext.getCmp('areaLimmitText3910').setVisible(false);
		Ext.getCmp('itemTypeFlagText3910').setVisible(false);
		Ext.getCmp('printTypeText3910').setVisible(false);
		Ext.getCmp('printEnvoiceText3910').setVisible(false);
		Ext.getCmp('printWaybillText3910').setVisible(false);
		Ext.getCmp('printPacklistText3910').setVisible(false);
		Ext.getCmp('custLogiboxRuleIdText3910').setVisible(false);
		Ext.getCmp('taskRuleIdText3910').setVisible(false);
		// 分配策略
		Ext.getCmp('deliverObjLevelText3910').setVisible(false);
		Ext.getCmp('PFlagText3910').setVisible(false);
		Ext.getCmp('MFlagText3910').setVisible(false);
		Ext.getCmp('WFlagText3910').setVisible(false);
		Ext.getCmp('SFlagText3910').setVisible(false);
		Ext.getCmp('DFlagText3910').setVisible(false);
		Ext.getCmp('BDivideFlagText3910').setVisible(false);
		Ext.getCmp('CDivideFlagText3910').setVisible(false);
		Ext.getCmp('commitTypeText3910').setVisible(false);
		Ext.getCmp('shortqtyTypeText3910').setVisible(false);

		// 工作流
		Ext.getCmp('batchRuleId3910').setVisible(false);
		Ext.getCmp('batchRuleName3910').setVisible(false);
		Ext.getCmp('floworder13910').setVisible(true);
		Ext.getCmp('flowvalueText13910').setVisible(true);
		Ext.getCmp('flowflagText13910').setVisible(true);
		// 试算策略
		Ext.getCmp('checkcomputeflagText13910').setVisible(false);
		Ext.getCmp('checkcomputelevelText13910').setVisible(false);
		Ext.getCmp('checkcomputevalueText13910').setVisible(false);
		Ext.getCmp('sendbufcomputeflagText13910').setVisible(false);
		Ext.getCmp('sendbufcomputelevelText13910').setVisible(false);
		Ext.getCmp('sendbufcomputevalueText13910').setVisible(false);
	}
};
