/**
 * 模块名称：计费取值方式维护Controller
 * 模块编码：B904
 * 创建：czh 
 */
typeB904_1='';
var checkFlag='0';
Ext.define('cms.controller.cost.cost_BillingRuleController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.cost.cost_BillingRuleUI'],
	
	init:function(){
		this.control({//计费类型新增
			'cost_BillingRuleUI commMenuWidget4[id=menuB904] button[name=add]':{
				click:this.detailAdd
			},//计费类型修改
			'cost_BillingRuleUI button[name=edit]':{
				click:this.ruleTypeEditClick
			},//计费类型删除
			'cost_BillingRuleUI button[name=delete]':{
				click:this.ruleTypedelete
			},//计费类型查找
			'cost_BillingRuleUI button[name=query]':{
				click:this.ruleTypeQuery
			},//计费类型刷新
			'cost_BillingRuleUI button[name=refresh]':{
				click:this.ruleTypeRefresh
			},//计费类型上一条记录
			'cost_BillingRuleTypeAddOrEditWindow button[name=prev]':{
				click:this.ruleTypePrev
			},//计费类型下一条记录
			'cost_BillingRuleTypeAddOrEditWindow button[name=next]':{
				click:this.ruleTypeNext
			},//计费类型关闭前事件
			'cost_BillingRuleTypeAddOrEditWindow':{
				beforeclose:this.cost_BillingRuleTypeAddOrEditWindowBeforeclose
			},//计费类型选择刷新计费取值方式
			'cost_BillingRuleUI grid[id=cost_BillingRuleType_ListGrid]':{
				selectionchange:this.cost_BillingRuleType_ListGridselectionchange
			},//计费类型-关闭窗口
			'cost_BillingRuleTypeAddOrEditWindow button[name=close]':{
				click:this.cost_BillingRuleTypeAddOrEditWindowColse
			},//计费类型-保存
			'cost_BillingRuleTypeAddOrEditWindow button[name=save]':{
				click:this.billingRuleTypeSave
			},//计费类型-验证计费类型代码唯一性
			'cost_BillingRuleTypeAddOrEditWindow form textfield[id=billingType_1]':{
				blur:this.billingType_1Blur
			},//计费类型-重新加载添加窗口
			'cost_BillingRuleTypeAddOrEditWindow button[name=add]':{
				click:this.billingRuleTypeadd
			},//计费取值方式新增
			'cost_BillingRuleUI button[name=detailAdd]':{
				click:this.billingRuleDetailAdd
			},//计费取值方式-关闭窗口
			'cost_BillingRuleAddOrEditWindow button[name=close]':{
				click:this.cost_BillingRuleAddOrEditWindowColse
			},//计费取值方式-保存
			'cost_BillingRuleAddOrEditWindow button[name=save]':{
				click:this.billingRuleSave
			},//计费取值方式-重新加载添加窗口
			'cost_BillingRuleAddOrEditWindow button[name=add]':{
				click:this.billingRuleadd
			},//计费取值方式关闭前事件
			'cost_BillingRuleAddOrEditWindow':{
				beforeclose:this.cost_BillingRuleAddOrEditWindowBeforeclose
			},//计费取值方式修改
			'cost_BillingRuleUI button[name=detailEdit]':{
				click:this.billingRuleEditClick
			},//计费取值方式删除
			'cost_BillingRuleUI button[name=detailDelete]':{
				click:this.billingRuleDelete
			},//计费取值方式查找
			'cost_BillingRuleUI button[name=detailQuery]':{
				click:this.billingRuleQuery
			},//计费取值方式刷新
			'cost_BillingRuleUI button[name=DetailRefresh]':{
				click:this.billingRuleRefresh
			},//计费取值方式上一条记录
			'cost_BillingRuleAddOrEditWindow button[name=prev]':{
				click:this.billingRulePrev
			},//计费取值方式下一条记录
			'cost_BillingRuleAddOrEditWindow button[name=next]':{
				click:this.billingRuleNext
			},//根据单位查找取值方式
			'cost_BillingRuleUI combo[id=cmbBillingUnitB904]':{
				select:this.selectRuleByUnit
			}
		});
	},
	//根据单位查找取值方式
	selectRuleByUnit:function(){
		var strDetail1 = [];
		var d1={
				columnId:'a.billing_unit',
				value:Ext.getCmp('cmbBillingUnitB904').getValue()
		};
		strDetail1.push(d1);
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				strQuery:jsonDetail
		};
		Ext.apply(Ext.getCmp('cost_Billing_Rule_Grid').getStore().proxy.extraParams,str);
		Ext.getCmp('cost_Billing_Rule_Grid').getStore().removeAll();
		Ext.getCmp('cost_Billing_Rule_Grid').getStore().load();	
	},
	
	//新增弹出窗口
	detailAdd:function(){
		Ext.create('cms.view.cost.window.cost_BillingRuleTypeAddOrEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		addCommMenu5('cost_BillingRuleTypeB904');
		typeB904_1='add';
		Ext.getCmp('billingType_1').focus(false,1);
		checkFlag='1';
	},
	cost_BillingRuleType_ListGridselectionchange:function(emodel,selected){
		if(selected.length>0){					
			var wheresql = {
				billingType : selected[0].get('billingType')
			};
			Ext.apply(Ext.getCmp('cost_Billing_Rule_Grid').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('cost_Billing_Rule_Grid').getStore().removeAll();
			Ext.getCmp('cost_Billing_Rule_Grid').getStore().load();
		}
	},
	//计费类型窗口关闭前事件
	cost_BillingRuleTypeAddOrEditWindowBeforeclose:function(){
		Ext.getCmp('cost_BillingRuleType_ListGrid').getStore().load();
	},
	//计费类型-关闭窗口
	cost_BillingRuleTypeAddOrEditWindowColse:function(){
		Ext.getCmp('cost_BillingRuleTypeAddOrEditWindow').close();
	},
	//计费类型-保存
	billingRuleTypeSave:function(){
		if(Ext.getCmp('form_01_B904').getForm().isValid()){
			var cust=Ext.getCmp('cost_BillingRuleType_ListGrid').getSelectionModel().getSelection()[0];
			var group={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					billingType:Ext.getCmp('billingType_1').getValue()
				},
				billingTypeName:Ext.getCmp('billingTypeName_1').getValue(),
				status:Ext.getCmp('statusB904').getValue(),
				memo:Ext.getCmp('memo_1').getValue(),
				rgstDate:new Date(),//typeB904_1=='add'?new Date():cust.data.rgstDate,
			    rgstName:typeB904_1=='add'?Ext.get('workerNo').getValue():cust.data.rgstName,
			    updtDate:typeB904_1=='add'?'':new Date(),
				updtName:typeB904_1=='add'?'':Ext.get('workerNo').getValue()        
			};
			var str=Ext.encode(group);
			Ext.Ajax.request({
				url:'cost_BillingRuleAction_saveCostRuleType',
				method:'post',
				params:{
					str:str
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.example.msg($i18n.prompt,data.msg);
						commonSetMsterReadOnlyByArray(
								new Array('billingType_1'),true);
						checkFlag='0';
					}else{
						Ext.example.msg($i18n.prompt,data.msg);
					}
				}
			});
		};
	},
	//验证计费类型代码
	billingType_1Blur:function(){
		if(typeB904_1=='add'){
			if(checkFlag=='1'){
				Ext.Ajax.request({
					method:'post',
					url:'cost_BillingRuleAction_checkRuleTypeNo',
					params:{
						str:Ext.getCmp('billingType_1').getValue()
				    },
				    success:function(response){
				    	var res = Ext.decode(response.responseText);
				    	if(res!=''){
				    		Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.accountNoIsExist);
				    		Ext.getCmp('billingType_1').setValue('');
				    		Ext.getCmp('billingType_1').focus();
				    	}
				    }
				});
			}
		}
	},
	//计费类型录入重新加载添加窗口
	billingRuleTypeadd:function(){
		commonSetMsterReadOnlyByArray(
				new Array('billingType_1'),false);
		Ext.getCmp('billingType_1').setValue('');
		Ext.getCmp('billingTypeName_1').setValue('');
		Ext.getCmp('statusB904').setValue('');
		Ext.getCmp('memo_1').setValue('');
		Ext.getCmp('billingType_1').focus();
	},
	//计费类型修改
	ruleTypeEditClick:function(){
		var data = Ext.getCmp('cost_BillingRuleType_ListGrid').getSelectionModel().getSelection();
		
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{		
			Ext.create('cms.view.cost.window.cost_BillingRuleTypeAddOrEditWindow',{
				title:$i18n.titleupdate
		    }).show();
			typeB904_1='edit';
			updateCommMenu5('cost_BillingRuleTypeB904');
			commonMenu5PrevOrNext('cost_BillingRuleTypeB904','cost_BillingRuleType_ListGrid',0);
			commonSetMsterReadOnlyByArray(
				new Array('billingType_1'),true);
			this.loadDatabB904_1();
		}
	},
	//加载计费类型修改页面的数据
	loadDatabB904_1:function (){
		var cust=Ext.getCmp('cost_BillingRuleType_ListGrid').getSelectionModel().getSelection();
		
		if(cust.length>0)
		{	
			Ext.getCmp('billingType_1').setValue(cust[0].data.billingType);
			Ext.getCmp('billingTypeName_1').setValue(cust[0].data.billingTypeName);	
			Ext.getCmp('statusB904').setValue(cust[0].data.status);
			Ext.getCmp('memo_1').setValue(cust[0].data.memo);
			Ext.getCmp('billingType_1').focus(false,2);
		}
	},
	//计费类型删除
	ruleTypedelete:function(){
		var cust=Ext.getCmp('cost_BillingRuleType_ListGrid').getSelectionModel().getSelection();
		if(cust.length>0)
		{
			Ext.Msg.confirm($i18n.prompt, $i18n_prompt.deleteOrNot,function(button, text){
				if (button == 'yes') {
					var params = {
							str  : cust[0].data.billingType
					};
					Ext.Ajax.request({
						method:'post',
						url:'cost_BillingRuleAction_deleteCostRuleType',
						params:params,
					    success:function(response){
							var data=Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n.prompt,data.msg);
								Ext.getCmp('cost_BillingRuleType_ListGrid').getStore().reload();
							}else{
								Ext.example.msg($i18n.prompt,data.msg+data.obj);
							}
						}
					});
				}
				if (button == 'no') {
					return;
				}
			});   
		}else{
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete);
            return;
		}
	},
	//计费类型刷新
	ruleTypeRefresh:function(){
		var wheresql = {
				strQuery : null
			};
		Ext.apply(Ext.getCmp('cost_BillingRuleType_ListGrid').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('cost_BillingRuleType_ListGrid').getStore().removeAll();
		Ext.getCmp('cost_BillingRuleType_ListGrid').getStore().reload();
	},
	//计费类型-实现上一页功能
	ruleTypePrev:function(){
		commonMenu5PrevOrNext('cost_BillingRuleTypeB904','cost_BillingRuleType_ListGrid',-1);
		this.loadDatabB904_1();
	},
	
	//计费类型-实现下一页功能
	ruleTypeNext:function(){
		commonMenu5PrevOrNext('cost_BillingRuleTypeB904','cost_BillingRuleType_ListGrid',1);
		this.loadDatabB904_1();
	},
	//计费类型-查找
	ruleTypeQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='B904_1';
		queryGrid='cost_BillingRuleType_ListGrid';
	},
	//新增弹出窗口
	billingRuleDetailAdd:function(){
		Ext.create('cms.view.cost.window.cost_BillingRuleAddOrEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		addCommMenu5('cost_BillingRuleB904');
		typeB904_1='add';
		Ext.getCmp('ruleIdB904').setValue($i18n_prompt.autogenerationWhenSave);
		Ext.getCmp('billingUnitB904').focus(false,1);
	},
	//计费取值方式-关闭窗口
	cost_BillingRuleAddOrEditWindowColse:function(){
		Ext.getCmp('cost_BillingRuleAddOrEditWindow').close();
	},
	//计费取值方式-保存
	billingRuleSave:function(){
		if(Ext.getCmp('form_02_B904').getForm().isValid()){
			var cust=Ext.getCmp('cost_Billing_Rule_Grid').getSelectionModel().getSelection()[0];
			var obj=Ext.getCmp('cost_BillingRuleType_ListGrid').getSelectionModel().getSelection()[0];
			var ruleId=null;
			if(typeB904_1=='edit'){
				ruleId=Ext.getCmp('ruleIdB904').getValue();
			}
			var group={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					billingType:obj.data.billingType,
					billingUnit:Ext.getCmp('billingUnitB904').getValue(),
					ruleId:ruleId,//Ext.getCmp('ruleIdB904').getValue(),
				},
				strategyName:Ext.getCmp('strategyNameB904').getValue(),
				useType:Ext.getCmp('status_1B904').getValue(),
				standardSql:Ext.getCmp('standardSqlB904').getValue(),
				nonstandardSql:Ext.getCmp('nonstandardSqlB904').getValue(),
				sqlOrder:'1',
				memo:Ext.getCmp('memo_1B904').getValue(),
				rgstDate:new Date(),//typeB904_1=='add'?new Date():cust.data.rgstDate,
			    rgstName:typeB904_1=='add'?Ext.get('workerNo').getValue():cust.data.rgstName,
			    updtDate:typeB904_1=='add'?'':new Date(),
				updtName:typeB904_1=='add'?'':Ext.get('workerNo').getValue()        
			};
			var str=Ext.encode(group);
			Ext.Ajax.request({
				url:'cost_BillingRuleAction_saveCostRule',
				method:'post',
				params:{
					str:str
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.example.msg($i18n.prompt,data.msg);
						commonSetMsterReadOnlyByArray(
								new Array('billingUnitB904'),true);
					}else{
						Ext.example.msg($i18n.prompt,data.msg);
					}
				}
			});
		};
	},
	//计费取值方式录入重新加载添加窗口
	billingRuleadd:function(){
		commonSetMsterReadOnlyByArray(
				new Array('ruleIdB904'),false);
		Ext.getCmp('ruleIdB904').setValue($i18n_prompt.autogenerationWhenSave);
		Ext.getCmp('billingUnitB904').setValue('');
		Ext.getCmp('strategyNameB904').setValue('');
		Ext.getCmp('standardSqlB904').setValue('');
		Ext.getCmp('nonstandardSqlB904').setValue('');
		Ext.getCmp('status_1B904').setValue('');
		Ext.getCmp('memo_1B904').setValue('');
		Ext.getCmp('billingUnitB904').focus();
	},
	//计费取值方式窗口关闭前事件
	cost_BillingRuleAddOrEditWindowBeforeclose:function(){
		Ext.getCmp('cost_Billing_Rule_Grid').getStore().load();
	},
	//计费取值方式删除
	billingRuleDelete:function(){
		var cust=Ext.getCmp('cost_Billing_Rule_Grid').getSelectionModel().getSelection();
		var obj=Ext.getCmp('cost_BillingRuleType_ListGrid').getSelectionModel().getSelection()[0];
		if(cust.length>0)
		{
			Ext.Msg.confirm($i18n.prompt, $i18n_prompt.deleteOrNot,function(button, text){
				if (button == 'yes') {
					var group={
							id:{
								enterpriseNo:Ext.get('enterpriseNo').getValue(),
								billingType:obj.data.billingType,
								billingUnit:cust[0].data.billingUnit,
								ruleId:cust[0].data.ruleId,
							}       
						};
						var str=Ext.encode(group);
					var params = {
							str  : str
					};
					Ext.Ajax.request({
						method:'post',
						url:'cost_BillingRuleAction_deleteCostRule',
						params:params,
					    success:function(response){
							var data=Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n.prompt,data.msg);
								Ext.getCmp('cost_Billing_Rule_Grid').getStore().reload();
							}else{
								Ext.example.msg($i18n.prompt,data.msg+data.obj);
							}
						}
					});
				}
				if (button == 'no') {
					return;
				}
			});   
		}else{
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_delete);
            return;
		}
	},
	//计费取值方式修改
	billingRuleEditClick:function(){
		var data = Ext.getCmp('cost_Billing_Rule_Grid').getSelectionModel().getSelection();
		
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{		
			Ext.create('cms.view.cost.window.cost_BillingRuleAddOrEditWindow',{
				title:$i18n.titleupdate
		    }).show();
			typeB904_1='edit';
			updateCommMenu5('cost_BillingRuleB904');
			commonMenu5PrevOrNext('cost_BillingRuleB904','cost_Billing_Rule_Grid',0);
			commonSetMsterReadOnlyByArray(
				new Array('ruleIdB904'),true);
			commonSetMsterReadOnlyByArray(
					new Array('billingUnitB904'),true);
			this.loadDatabB904_2();
		}
	},
	//加载计费取值方式修改页面的数据
	loadDatabB904_2:function (){
		var cust=Ext.getCmp('cost_Billing_Rule_Grid').getSelectionModel().getSelection();
		
		if(cust.length>0)
		{	
			Ext.getCmp('ruleIdB904').setValue(cust[0].data.ruleId);
			Ext.getCmp('billingUnitB904').setValue(cust[0].data.billingUnit);	
			Ext.getCmp('strategyNameB904').setValue(cust[0].data.strategyName);
			Ext.getCmp('standardSqlB904').setValue(cust[0].data.standardSql);
			Ext.getCmp('nonstandardSqlB904').setValue(cust[0].data.nonstandardSql);
			Ext.getCmp('status_1B904').setValue(cust[0].data.useType);
			Ext.getCmp('memo_1B904').setValue(cust[0].data.memo);
			Ext.getCmp('status_1B904').focus(false,2);
		}
	},
	//计费取值方式刷新
	billingRuleRefresh:function(){
		var wheresql = {
				strQuery : null
			};
		Ext.apply(Ext.getCmp('cost_Billing_Rule_Grid').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('cost_Billing_Rule_Grid').getStore().removeAll();
		Ext.getCmp('cost_Billing_Rule_Grid').getStore().reload();
	},
	//计费取值方式-实现上一页功能
	billingRulePrev:function(){
		commonMenu5PrevOrNext('cost_BillingRuleB904','cost_Billing_Rule_Grid',-1);
		this.loadDatabB904_2();
	},
	
	//计费取值方式-实现下一页功能
	billingRuleNext:function(){
		commonMenu5PrevOrNext('cost_BillingRuleB904','cost_Billing_Rule_Grid',1);
		this.loadDatabB904_2();
	},
	//计费取值方式-查找
	billingRuleQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='B904_2';
		queryGrid='cost_Billing_Rule_Grid';
	},
			
});


