/**
 * 模块名称：盘点发单
 * 模块编码：8201
 * 创建:周欢
 */
var g_fcdataCheckDirect8201="";//界面计划单标识：cut:派单界面的计划单输入框，send:成单条件界面的计划单输入框
var g_fcdataType8201="";//盘点类别标识：cut:派单界面的盘点类别输入框，send:成单条件界面的盘点类别输入框
var g_strCheckTyle = "";//盘点类型标识：0：商品盘；1：储位盘

Ext.define('cms.controller.fcdata.fcdata_CheckController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.fcdata.fcdata_CheckUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({
			//盘点发单》成单条件》选择计划单号
			'fcdata_CheckUI wms_DefFieldValCombo[id=cmbFcdataType8201cut]':{
				change:this.FcdataType8201cutSelect
			},
			//盘点发单》成单条件》选中盘点类型加载计划单号,获取盘点类型（ 0：商品盘；1：储位盘）
			'fcdata_CheckUI combo[id=cmbPlan_no8201cut]':{
				select:this.cmbPlan_no8201cutSelect
			},
			//盘点发单》派单》选择计划单号
			'fcdata_CheckUI wms_DefFieldValCombo[id=cmbFcdataType8201send]':{
				change:this.FcdataType8201sendSelect
			},
			//盘点发单》派单》选中盘点类型加载计划单号
			'fcdata_CheckUI combo[id=cmbPlan_no8201send]':{
				select:this.cmbPlan_no8201sendSelect
			},
			//盘点发单》成单条件》切单
			'fcdata_CheckUI button[id=cut8201]':{
				click:this.cut8201Click
			},
			//盘点发单》派单》发单
			'fcdata_CheckUI button[id=butSend_8201]':{
				click:this.butSend_8201
			},
			//盘点发单》刷新
			'fcdata_CheckUI button[name=refresh]':{
				click:this.refreshClick
			},
			//盘点发单》关闭前
			'fcdata_CheckUI':{
				beforeclose:this.fcdata_CheckUIBeforeclose
			},
			//Tab切换
			'fcdata_CheckUI tabpanel[id=tabPId8201]':{
				tabchange:this.tabPIdtabchange
			}
		});
	},
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		//Ext.getCmp('cmbPlan_no8201send').getStore().load();
		Ext.getCmp('date_scope8201').setVisible(false);
		Ext.getCmp('cmbFcdataType8201cut').getStore().load();
	},
	//tab页面切换
	tabPIdtabchange:function(tabPanel,newCard,oldCard,eOpts )
	{
		if(Ext.getCmp('tabPId8201').getActiveTab().title == $i18n.singleconditions)
		{
			Ext.getCmp('cmbFcdataType8201cut').getStore().load();
		}else{
			Ext.getCmp('cmbFcdataType8201send').getStore().load();
		}
	},
	//界面关闭前清理数据
	fcdata_CheckUIBeforeclose:function(){
		Ext.getCmp('gridFcdataCheckDirect8201cut_cell').store.removeAll();
		Ext.getCmp('gridFcdataCheckDirect8201send').store.removeAll();
	},
	/**
	 * 成单条件》选中盘点类型
	 */
	FcdataType8201cutSelect:function()
	{
		g_fcdataType8201 = 'cut';
		this.FcdataTypeChange();
	},
	/**
	 * 派单》选中盘点类型
	 */
	FcdataType8201sendSelect:function()
	{
		g_fcdataType8201 = 'send';
		this.FcdataTypeChange();
	},
	
	//改变盘点类型加载盘点计划单号数据
	FcdataTypeChange:function()
	{
		
		if(g_fcdataType8201 == 'cut')
		{
			var strDetail1 = [];
			var d={
				columnId:'a.fcdata_type',
				value:Ext.getCmp('cmbFcdataType8201cut').getValue()
			};
			strDetail1.push(d);
			var jsonDetail = Ext.encode(strDetail1);
			var strWheresql = {
				strFcdataType : jsonDetail
			};
			Ext.apply(Ext.getCmp('cmbPlan_no8201cut')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('cmbPlan_no8201cut').getStore()
					.removeAll();
			Ext.getCmp('cmbPlan_no8201cut').getStore()
					.load();
		}else if(g_fcdataType8201 == 'send')
		{
			
			var strDetail2 = [];
			var d={
				columnId:'a.fcdata_type',
				value:Ext.getCmp('cmbFcdataType8201send').getValue()
			};
			strDetail2.push(d);
			var jsonDetail2 = Ext.encode(strDetail2);
			var strWheresql = {
				strFcdataType : jsonDetail2
			};
			Ext.apply(Ext.getCmp('cmbPlan_no8201send')
					.getStore().proxy.extraParams,
					strWheresql);
			Ext.getCmp('cmbPlan_no8201send').getStore()
					.removeAll();
			Ext.getCmp('cmbPlan_no8201send').getStore()
					.load();
		}
	},
	
	/**
	 * 选中盘点类型加载计划单号,获取盘点类型（ 0：商品盘；1：储位盘）
	 */
	cmbPlan_no8201cutSelect:function(combo,records,eOpts){
		g_fcdataCheckDirect8201 = 'cut';
		var params = {
				strPlanNo:combo.value	
			};
			Ext.Ajax.request({
				method:'POST',
				url:'fcdata_CheckAction_CheckType.action',
				params:params,
				success:function(response){
					var data = Ext.decode(response.responseText);
					var strCheckTyle = '';
					if(Ext.getCmp('cmbFcdataType8201cut').getValue() == '3')//盘点类型
					{
						Ext.getCmp('date_scope8201').setVisible(true);
					}else
					{
						Ext.getCmp('date_scope8201').setVisible(false);
					}
					if(data=='0'){
						g_strCheckTyle = '0';
						Ext.getCmp('gridFcdataCheckDirect8201cut_cell').hide();
						Ext.getCmp('gridFcdataCheckDirect8201cut_article').show();
						
						loadFcdataCheckDirect(combo.value,records,eOpts);
					}else{
						g_strCheckTyle = '1';
						Ext.getCmp('gridFcdataCheckDirect8201cut_cell').show();
						Ext.getCmp('gridFcdataCheckDirect8201cut_article').hide();
						loadFcdataCheckDirect(combo.value,records,eOpts);
					}				
				},
				failure:function(response){
					Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.CannotSubForWeb);
				}
			});	
		
	},
	
	/**
	 * 派单》加载
	 */
	cmbPlan_no8201sendSelect:function(combo,records,eOpts){
		g_fcdataCheckDirect8201 = 'send';
		loadFcdataCheckDirect(combo.value,records,eOpts);
	},
	
	
	//切单
	cut8201Click:function(){
		if(!Ext.isEmpty(Ext.getCmp('gridFcdataCheckDirect8201cut_cell').getSelectionModel()
			      .getSelection()) || !Ext.isEmpty(Ext.getCmp('gridFcdataCheckDirect8201cut_article').getSelectionModel()
					      .getSelection()))
		{
			if(Ext.getCmp('cmbFcdataType8201cut').getValue() == '3' && Ext.isEmpty(Ext.getCmp('date_scope8201').getValue()))
			{
				Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choseScopeDate);
				return
			}
			if(Ext.getCmp('conditionForm8201').getForm().isValid())
			{
				Ext.Msg.confirm($i18n_prompt.prompt, $i18n_prompt.cutOrNot,
				function(button, text) {
				if (button == 'yes') {
					var record = '';
					if(g_strCheckTyle == '0')
					{
						record = Ext.getCmp('gridFcdataCheckDirect8201cut_article').getSelectionModel()
					      .getSelection();
					}else if(g_strCheckTyle == '1')
					{
						record = Ext.getCmp('gridFcdataCheckDirect8201cut_cell').getSelectionModel()
					      .getSelection();
					}
					var dtBeginDate = '';
					if(Ext.getCmp('cmbFcdataType8201cut').getValue() == '3')
					{
						dtBeginDate = Ext.getCmp('date_scope8201').getValue();
					}else
					{
						dtBeginDate = new Date();
					}
					var intCount = record.length;
					var detail = [];
					for(var i=0;i<intCount;i++){
						var d=
						{
							enterpriseNo:Ext.get('enterpriseNo').getValue(),
							warehouseNo:record[i].get('warehouseNo'),
							ownerNo:record[i].get('ownerNo'),
							requestNo:record[i].get('requestNo'),
							rgstName:Ext.get('workerNo').getValue(),
							cutFlag:Ext.getCmp('radioCutFlag8101').getValue().cf,
							moveType:Ext.getCmp('strMove_type8101').getValue().mt,
							beginDate:dtBeginDate,
							endDate:new Date()
						};
						detail.push(d);
					};
					
					var josonDetail = Ext.encode(detail);
					var params = {
						str:josonDetail
					};
					Ext.Ajax.request({
						method:'POST',
						url:'fcdata_CheckAction_tscCut8201.action',
						params:params,
						success:function(response){
							var data = Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n_prompt.prompt,data.msg);
								Ext.getCmp('gridFcdataCheckDirect8201cut_cell').getStore().reload();
								Ext.getCmp('gridFcdataCheckDirect8201cut_article').getStore().reload();
							}else{
								Ext.example.msg($i18n_prompt.prompt,data.msg+data.obj);
							}				
						}
					
					});	
				}
				});
			}
		}else{
        	Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.selectCutRecord);
        }
	},
	
	/**
	 * 发单
	 */
	butSend_8201:function(){
        if(!Ext.isEmpty(Ext.getCmp('gridFcdataCheckDirect8201send').getSelectionModel()
			      .getSelection())){
        	if(Ext.isEmpty(Ext.getCmp('cmbWorkerNo8201').getValue())){
        		Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.inWorkerNo);
        		return;
        	}
			if(Ext.isEmpty(workSpaceNo))
			{
				Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.setWorkSpace);
				return;
			}
        	Ext.Msg.confirm($i18n_prompt.prompt, $i18n_prompt.sendOrNot,
			function(button, text) {
			if (button == 'yes') {
				var msgShow = commonMegShow($i18n_prompt.sending_wait);
				var varSelectCheckM = Ext.getCmp('gridFcdataCheckDirect8201send').getSelectionModel()
			      .getSelection();
				var detail = [];
				for(var i=0;i<varSelectCheckM.length;i++)
				{
					var d=
					{
						enterpriseNo:Ext.get('enterpriseNo').getValue(),
						warehouseNo:varSelectCheckM[i].get('warehouseNo'),
						ownerNo:varSelectCheckM[i].get('ownerNo'),
						rgstName:Ext.getCmp('cmbWorkerNo8201').getValue(),
						checkNo:varSelectCheckM[i].get('checkNo'),
						checkType:'1',
						fcdataType:varSelectCheckM[i].get('fcdataType'),
						printType:Ext.getCmp('radioPrint_type8201').getValue().pt
					};
					detail.push(d);
				};
				var josonDetail = Ext.encode(detail);
				var params = {
						str:josonDetail
				};
				Ext.Ajax.request({
					method:'POST',
					url:'fcdata_CheckAction_send.action',
					params:params,
					success:function(response){
						msgShow.hide();
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							Ext.example.msg($i18n_prompt.prompt,data.msg);
							Ext.getCmp('gridFcdataCheckDirect8201send').getStore().removeAll();
							Ext.getCmp('cmbPlan_no8201send').getStore().reload();
						}else{
							Ext.example.msg($i18n_prompt.prompt,data.msg+data.obj);
						}				
					},
					failure:function(response){
						msgShow.hide();
						Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.CannotSubForWeb);
					}
				});	
			}
			});
        }else{
        	Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.selectNodeForSend);
        }
	},
	/**
	 * 刷新
	 */
	refreshClick:function(){
		if(Ext.getCmp('tabPId8201').getActiveTab().title == $i18n.singleconditions)
		{
			Ext.getCmp('cmbFcdataType8201cut').setValue(null);
			Ext.getCmp('cmbFcdataType8201cut').getStore().reload();
		}else{
			Ext.getCmp('cmbFcdataType8201send').setValue(null);
			Ext.getCmp('cmbFcdataType8201send').getStore().reload();
		}
	}
	
});

/**
 * 加载
 */
function loadFcdataCheckDirect(combo,records,eOpts){
	var detail1 = [];
	var d={
		columnId:'fcd.plan_no',
		value:combo
	};
	detail1.push(d);
	var jsonDetail1 = Ext.encode(detail1);
	var strWheresql = {
		str : jsonDetail1
	};
	if(g_fcdataCheckDirect8201 == 'cut')
	{
		if(g_strCheckTyle == '1')
		{
			Ext.apply(Ext.getCmp('gridFcdataCheckDirect8201cut_cell').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('gridFcdataCheckDirect8201cut_cell').getStore().removeAll();
			Ext.getCmp('gridFcdataCheckDirect8201cut_cell').getStore().load();
		}
		else if(g_strCheckTyle == '0')
		{
			Ext.apply(Ext.getCmp('gridFcdataCheckDirect8201cut_article').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('gridFcdataCheckDirect8201cut_article').getStore().removeAll();
			Ext.getCmp('gridFcdataCheckDirect8201cut_article').getStore().load();
		}
	}else if(g_fcdataCheckDirect8201 == 'send')
	{
		Ext.apply(Ext.getCmp('gridFcdataCheckDirect8201send').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('gridFcdataCheckDirect8201send').getStore().removeAll();
		Ext.getCmp('gridFcdataCheckDirect8201send').getStore().load();
	}
	
};
