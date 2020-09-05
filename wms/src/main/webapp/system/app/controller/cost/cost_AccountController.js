/**
 * 模块名称：科目设置维护Controller
 * 模块编码：B303
 * 创建：hcx 
 */
rowindexB303=0;
typeB303='';
typeB303_1='';
windowType='';
windowType2='';
saveType='';
var checkFlag='0';
Ext.define('cms.controller.cost.cost_AccountController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.cost.cost_AccountUI'
	          ],
	init:function(){
		this.control({//科目代码维护-新增
			'cost_AccountUI button[name=add]':{
				click:this.accountSetAddB303_1
			},//科目代码维护-修改
			'cost_AccountUI button[name=edit]':{
				click:this.accountSetEditClike
			},//科目代码维护-删除
			'cost_AccountUI button[name=delete]':{
				click:this.accountSetdelete
			},//科目代码维护-查找
			'cost_AccountUI button[name=query]':{
				click:this.accountSetQuery
			},//科目代码维护-刷新
			'cost_AccountUI button[name=refresh]':{
				click:this.accountSetRefresh
			},//科目代码维护-上一条记录
			'cost_AccountSetAddorEditWindow button[name=prev]':{
				click:this.accountSetPrev
			},//科目代码维护-下一条记录
			'cost_AccountSetAddorEditWindow button[name=next]':{
				click:this.accountSetNext
			},//科目代码维护-关闭前事件
			'cost_AccountSetAddorEditWindow':{
				beforeclose:this.cost_AccountSetAddorEditWindowBeforeclose
			},//科目代码维护-关闭窗口
			'cost_AccountSetAddorEditWindow button[name=close]':{
				click:this.cost_AccountSetAddorEditWindowColse
			},//科目代码维护-保存
			'cost_AccountSetAddorEditWindow button[name=save]':{
				click:this.accountSetSave
			},//科目代码维护-验证科目代码唯一性
			'cost_AccountSetAddorEditWindow form textfield[id=accountNo_1]':{
				blur:this.accountNo_1Blur
			},//科目代码维护-重新加载添加窗口
			'cost_AccountSetAddorEditWindow button[name=add]':{
				click:this.accountSetadd
			},//科目代码维护-设置光标跳到下一输入框
			'cost_AccountSetAddorEditWindow field':{
				specialkey:this.boxkeydown
			},//科目设置维护-新增
			'cost_AccountUI button[id=addB303]':{
				click:this.detailAdd
			},//科目设置维护-周期为‘月’时校验结算日期
			'cost_AccountAddOrEditWindow  textfield[id=balanceDayB303_2]':{
				blur:this.balanceDayBlur
			},//科目设置维护-判断科目编码是否唯一（用于选择填写编号判断）
			'cost_AccountAddOrEditWindow combo[id=accountNoB303]':{
				select:this.accountNoCheck
			},//科目设置维护-窗口-货主下拉选择
			'cost_AccountAddOrEditWindow combo[id=ownerNoB303]':{
				change:this.ownerNoB303Change
			},//科目设置维护-重新加载添加窗口
			'cost_AccountAddOrEditWindow button[name=add]':{
				click:this.accountNoAdd
			},//科目设置维护-用于判断值1、值2和优惠计费项目是否可以为空
			/*'cost_AccountAddOrEditWindow combo[id=discountFlagB303]':{
				change:this.valueIsNull
			},*/
			//科目设置维护-保存科目信息
			'cost_AccountAddOrEditWindow button[name=save]':{
				click:this.save
			},//科目设置维护-修改
			'cost_AccountUI button[id=editB303]':{
				click:this.detailEdit
			},//科目设置维护-删除
			'cost_AccountUI button[id=deleteB303]':{
				click:this.deleteB303Click
			},//科目设置维护-刷新
			'cost_AccountUI button[id=refreshBB303]':{
				click:this.refreshBB303Click
			},//科目设置维护-关闭窗口
			'cost_AccountAddOrEditWindow button[name=close]':{
				click:this.colse
			},//科目设置维护-上一条记录
			'cost_AccountAddOrEditWindow button[name=prev]':{
				click:this.prev
			},//科目设置维护-下一条记录
			'cost_AccountAddOrEditWindow button[name=next]':{
				click:this.next
			},//科目设置维护-根据货主和仓别加载科目，并且查找相应的信息
			'cost_AccountUI combo[id=ownerNoUIB303]':{
				change:this.selectAndGetAccount
			},//科目设置维护-根据货主、仓别和科目查找对应的科目信息
			'cost_AccountUI combo[id=accountUIB303]':{
				select:this.getAccountWithCondition
			},//科目设置维护-科目信息列表数据选择
			'cost_AccountUI grid[id=account_MUIB303]':{	
				selectionchange:this.showRelaction
			},//科目设置维护-选择结账周期
			'cost_AccountAddOrEditWindow wms_DefFieldValCombo[id=accountCycle3B03]':{
				select:this.accountCycleSelect
			},//科目设置维护-选择结算日期
			'cost_AccountAddOrEditWindow wms_DefFieldValCombo[id=balanceDayB303_1]':{
				select:this.balanceDayB303_1Select
			},//科目设置维护-输入结算日期
			'cost_AccountAddOrEditWindow textfield[id=balanceDayB303_2]':{
				blur:this.balanceDayB303_2Select
			},//科目设置维护-科目组下拉选择
			'cost_AccountAddOrEditWindow bdef_DefOwnerCombo[id=accountGroupNoB303]':{
				select:this.accountGroupNoB303Select
			},//科目设置维护-科目明细信息列表数据选择
			'cost_AccountAddOrEditWindow grid[id=grid_01]':{
				selectionchange:this.grid_01Selectionchange
			},//科目设置维护-添加科目和计费项目的关系
			'cost_AccountAddOrEditWindow button[id=rightB303]':{
				click:this.rightB303
			},//科目设置维护-删除科目和计费项目的关系
			'cost_AccountAddOrEditWindow button[id=leftB303]':{
				click:this.leftB303
			},//科目设置维护-关闭前事件
			'cost_AccountAddOrEditWindow':{
				beforeclose:this.cost_AccountAddOrEditWindowBeforeclose
			},//科目设置维护-TAB页切换
			'cost_AccountAddOrEditWindow tabpanel[id=tabB303]':
			{
				tabchange:this.tabchange
			},//科目设置维护-阶梯选择
			'cost_AccountAddOrEditWindow grid[id=grid_01_B303]':{
				selectionchange:this.grid_01_B303Selectionchange
			},//科目设置维护-验证优惠阶梯
			'cost_AccountAddOrEditWindow textfield[id=accountLadder_2]':{
				blur:this.accountLadderBlur
			},//科目设置维护-阶梯优惠-优惠方式选择
			'cost_AccountAddOrEditWindow combo[id=discountFlagB303_2]':{
				select:this.discountFlagB303_2Select
			},//科目设置维护-设置光标跳到下一输入框
			'cost_AccountAddOrEditWindow field':{
				specialkey:this.boxkeydown
			}
		});
	},
	//科目代码维护-新增
	accountSetAddB303_1:function(){
		Ext.create('cms.view.cost.window.cost_AccountSetAddorEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		addCommMenu5('cost_AccountSetB303');
		typeB303_1='add';
		Ext.getCmp('accountNo_1').focus(false,1);
		checkFlag='1';
	},
	//科目代码维护-修改
	accountSetEditClike:function(){
		var data = Ext.getCmp('gridAccountSetB303').getSelectionModel().getSelection();
		
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{		
			Ext.create('cms.view.cost.window.cost_AccountSetAddorEditWindow',{
				title:$i18n.titleupdate
		    }).show();
			typeB303_1='edit';
			updateCommMenu5('cost_AccountSetB303');
			commonMenu5PrevOrNext('cost_AccountSetB303','gridAccountSetB303',0);
			commonSetMsterReadOnlyByArray(
				new Array('accountNo_1'),true);
			this.loadDatabB303_1();
		}
	},
	//科目代码维护-删除
	accountSetdelete:function(){
		var cust=Ext.getCmp('gridAccountSetB303').getSelectionModel().getSelection();
		if(cust.length>0)
		{
			Ext.Msg.confirm($i18n.prompt, $i18n_prompt.deleteOrNot,function(button, text){
				if (button == 'yes') {
					var params = {
							str  : cust[0].data.accountNo
					};
					Ext.Ajax.request({
						method:'post',
						url:'cost_AccountAction_deleteCostAccountSet',
						params:params,
					    success:function(response){
							var data=Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n.prompt,data.msg);
								Ext.getCmp('gridAccountSetB303').getStore().reload();
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
	//科目代码维护-查找
	accountSetQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId='B303_1';
		queryGrid='gridAccountSetB303';
	},
	//科目代码维护-刷新
	accountSetRefresh:function(){
		var wheresql = {
				strQuery : null
			};
		Ext.apply(Ext.getCmp('gridAccountSetB303').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('gridAccountSetB303').getStore().removeAll();
		Ext.getCmp('gridAccountSetB303').getStore().reload();
	},
	//科目代码维护-实现上一页功能
	accountSetPrev:function(){
		commonMenu5PrevOrNext('cost_AccountSetB303','gridAccountSetB303',-1);
		this.loadDatabB303_1();
	},
	
	//科目代码维护-实现下一页功能
	accountSetNext:function(){
		commonMenu5PrevOrNext('cost_AccountSetB303','gridAccountSetB303',1);
		this.loadDatabB303_1();
	},
	//加载科目代码维护修改页面的数据
	loadDatabB303_1:function (){
		var cust=Ext.getCmp('gridAccountSetB303').getSelectionModel().getSelection();
		
		if(cust.length>0)
		{	
			Ext.getCmp('accountNo_1').setValue(cust[0].data.accountNo);
			Ext.getCmp('accountName_1').setValue(cust[0].data.accountName);	
			Ext.getCmp('remark_1').setValue(cust[0].data.accountName);	
			Ext.getCmp('accountName_1').focus(false,2);
		}
	},
	//科目代码维护-窗口关闭前事件
	cost_AccountSetAddorEditWindowBeforeclose:function(){
		Ext.getCmp('gridAccountSetB303').getStore().load();
	},
	//科目代码维护-关闭窗口
	cost_AccountSetAddorEditWindowColse:function(){
		Ext.getCmp('cost_AccountSetAddorEditWindow').close();
	},
	
	//科目代码维护-保存
	accountSetSave:function(){
		if(Ext.getCmp('form_01_B303').getForm().isValid()){
			var cust=Ext.getCmp('gridAccountSetB303').getSelectionModel().getSelection()[0];
			var group={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					accountNo:Ext.getCmp('accountNo_1').getValue()
				},
				accountName:Ext.getCmp('accountName_1').getValue(),
				remark:Ext.getCmp('remark_1').getValue(),
				rgstDate:typeB303_1=='add'?new Date():cust.data.rgstDate,
			    rgstName:typeB303_1=='add'?Ext.get('workerNo').getValue():cust.data.rgstName,
			    updtDate:typeB303_1=='add'?'':new Date(),
				updtName:typeB303_1=='add'?'':Ext.get('workerNo').getValue(),
				createFlag:typeB303_1=='add'?'0':cust.data.createFlag           
			};
			var str=Ext.encode(group);
			Ext.Ajax.request({
				url:'cost_AccountAction_saveCostAccountSet',
				method:'post',
				params:{
					str:str
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.example.msg($i18n.prompt,data.msg);
						commonSetMsterReadOnlyByArray(
								new Array('accountNo_1'),true);
						checkFlag='0';
					}else{
						Ext.example.msg($i18n.prompt,data.msg);
					}
				}
			});
		};
	},
	//科目代码维护-验证科目代码
	accountNo_1Blur:function(){
		if(typeB303_1=='add'){
			if(checkFlag=='1'){
				Ext.Ajax.request({
					method:'post',
					url:'cost_AccountAction_checkAccountNo',
					params:{
						str:Ext.getCmp('accountNo_1').getValue()
				    },
				    success:function(response){
				    	var res = Ext.decode(response.responseText);
				    	if(res!=''){
				    		Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.accountNoIsExist);
				    		Ext.getCmp('accountNo_1').setValue('');
				    		Ext.getCmp('accountNo_1').focus();
				    	}
				    }
				});
			}
		}
	},
	//科目代码维护-科目代码录入重新加载添加窗口
	accountSetadd:function(){
		commonSetMsterReadOnlyByArray(
				new Array('accountNo_1'),false);
		Ext.getCmp('accountNo_1').setValue('');
		Ext.getCmp('accountName_2').setValue('');
		Ext.getCmp('remark_1').setValue('');
		Ext.getCmp('accountNo_1').focus();
	},
	//调用新增页面
	detailAdd:function(){
		Ext.create('cms.view.cost.window.cost_AccountAddOrEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		addCommMenu5('cost_AccountB303');
		typeB303='add'; 
		windowType='add';
		windowType2='add';
		saveType='0';
		Ext.getCmp('accountCycle3B03').setValue('1');
		Ext.getCmp('statusB303').setValue('0');
		Ext.getCmp('ownerNoB303').focus(false,5);
		Ext.getCmp('balanceDayB303_1').setVisible(false);
		Ext.getCmp('balanceDayB303_2').setVisible(false);
		Ext.getCmp('grid_01').getStore().removeAll();
		Ext.getCmp('grid_01_B303').getStore().removeAll();
		billingProjectSelect();
		Ext.getCmp('grid_03_B303').getStore().removeAll();
		Ext.getCmp('accountGroupNoB303').getStore().removeAll();
		//加载窗口科目下拉列表
		accountForWid();
	},
	//周期为‘月’时校验结算日期
	balanceDayBlur:function(){
		if(Ext.getCmp('accountCycle3B03').getValue()=='3'){
			var balanceDay=Ext.getCmp('balanceDayB303_2').getValue();
			if(balanceDay>31||balanceDay<1||isNaN(Ext.getCmp('balanceDayB303_2').getValue())){
				Ext.example.msg($i18n.prompt, $i18n_prompt.balanceDayCannotMoreThan31);
				Ext.getCmp('balanceDayB303_2').setValue('');
			}
		}
	},
	//判断科目代码是否唯一
	accountNoCheck:function(){
		if(typeB303=='add' && windowType2=='add'	
		&& !Ext.isEmpty(Ext.getCmp('accountNoB303').getValue())
		&& !Ext.isEmpty(Ext.getCmp('ownerNoB303').getValue()) )
		{
			Ext.Ajax.request({
				url : 'cost_AccountAction_accountNoCheck',
				params : {
					str:Ext.getCmp('accountNoB303').getValue(),
					ownerNo:Ext.getCmp('ownerNoB303').getValue()
				},
				success : function(response) {
					var res = Ext.decode(response.responseText);
			    	if(res!=''){
			    		Ext.example.msg($i18n.prompt,$i18n_prompt.accountNoIsExist);
			    		Ext.getCmp('accountNoB303').setValue(null);
			    	}
				}
			});
		}
	},
	
	//根据优惠方式，判断值1、值2和优惠代码是否可以为空
	valueIsNull: function(){	
		var flag =Ext.getCmp('discountFlagB303_2').getValue();
		if(flag=='1')
		{
			
			if(Ext.isEmpty(Ext.getCmp('value1B303_2').getValue()))
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.value1IsNotEmpty);
				return false;
			}
			if(Ext.isEmpty(Ext.getCmp('value2B303_2').getValue()))
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.value2IsNotEmpty);
				return false;
			}
		}else if (flag=='2')
		{
			if(Ext.isEmpty(Ext.getCmp('value1B303_2').getValue()))
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.value1IsNotEmpty);
				return false;
			}
			if(Ext.isEmpty(Ext.getCmp('value2B303_2').getValue()))
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.value2IsNotEmpty);
				return false;
			}
		}else if (flag=='3')
		{
			if(Ext.isEmpty(Ext.getCmp('value1B303_2').getValue()))
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.value1IsNotEmpty);
				return false;
			}
			
			if(Ext.isEmpty(Ext.getCmp('billingProjectB303_2').getValue()))
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.billingProjectIsNotEmpty);
				return false;
			}
			
		}else if(flag=='4')
		{
			if(Ext.isEmpty(Ext.getCmp('value1B303_2').getValue()))
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.value1IsNotEmpty);
				return false;
			}
			
			if(Ext.isEmpty(Ext.getCmp('billingProjectB303_2').getValue()))
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.billingProjectIsNotEmpty);
				return false;
			}
			if(Ext.isEmpty(Ext.getCmp('value2B303_2').getValue()))
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.value2IsNotEmpty);
				return false;
			}
		}else if(flag=='5'){
			if(Ext.isEmpty(Ext.getCmp('value1B303_2').getValue()))
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.value1IsNotEmpty);
				return false;
			}		
			if(Ext.isEmpty(Ext.getCmp('value2B303_2').getValue()))
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.value2IsNotEmpty);
				return false;
			}
		}
		
		return true;
	},
	ownerNoB303Change:function(){
		if(typeB303=='add' 		
			&& !Ext.isEmpty(Ext.getCmp('ownerNoB303').getValue()) )
			{
			    //重新加载科目组编码下拉列表
			    accountGroupNoCombo();
			    //重新加载科目明细信息列表
			    accountDList();
			    //重新加载计费项目信息列表
			    billingProjectSelect();
			    Ext.getCmp('accountTypeB303').setValue('');
				Ext.getCmp('accountNoB303').setValue('');
//				Ext.getCmp('accountNameB303').setValue('');
				Ext.getCmp('otherCost1_B303').setValue('');
				Ext.getCmp('otherCost2_B303').setValue('');
				Ext.getCmp('otherCost3_B303').setValue('');
				Ext.getCmp('otherCost4_B303').setValue('');
				Ext.getCmp('otherCost5_B303').setValue('');
				//加载新增科目代码下拉
			    accountForWid();
			}
	},
	//实现新增功能（清空窗口的内容）
	accountNoAdd:function(){
		if(Ext.getCmp('tabB303').getActiveTab().id=="tabB303_1")
		{
			Ext.getCmp('accountTypeB303').setValue('');
			Ext.getCmp('accountNoB303').setValue('');
			//加载新增科目代码下拉
			accountForWid();
//			Ext.getCmp('accountNameB303').setValue('');
			Ext.getCmp('accountIdB303').setValue('');
			Ext.getCmp('otherCost1_B303').setValue('');
			Ext.getCmp('otherCost2_B303').setValue('');
			Ext.getCmp('otherCost3_B303').setValue('');
			Ext.getCmp('otherCost4_B303').setValue('');
			Ext.getCmp('otherCost5_B303').setValue('');
			Ext.getCmp('remarkB303').setValue('');
			Ext.getCmp('grid_01_B303').getStore().removeAll();
			Ext.getCmp('grid_02_B303').getStore().load();
			Ext.getCmp('grid_03_B303').getStore().removeAll();
			Ext.getCmp('accountLadder_2').setValue('');
			Ext.getCmp('discountFlagB303_2').setValue('');
			Ext.getCmp('value1B303_2').setValue('');
			Ext.getCmp('value2B303_2').setValue('');
			Ext.getCmp('billingProjectB303_2').setValue('');
			Ext.getCmp('remarkB303_2').setValue('');
    		commonSetMsterReadOnlyByArray(
    				new Array('accountTypeB303','accountNoB303'),false);
    		Ext.getCmp('accountTypeB303').focus();
//    		typeB303='add';
    		windowType2='add';
    		saveType='0';
    		
		}else if(Ext.getCmp('tabB303').getActiveTab().id=="tabB303_2")
		{
			commonSetMsterReadOnlyByArray(
					new Array('accountLadder_2'),false);
			Ext.getCmp('accountLadder_2').setValue('');
			Ext.getCmp('discountFlagB303_2').setValue('');
			Ext.getCmp('value1B303_2').setValue('');
			Ext.getCmp('value2B303_2').setValue('');
			Ext.getCmp('billingProjectB303_2').setValue('');
			Ext.getCmp('remarkB303_2').setValue('');
    		Ext.getCmp('accountLadder_2').focus();
    		windowType='add';
		}
	},
	
	//保存科目信息
	save: function(){
		if(!commonCheckIsInputAll('IdFormB303')){
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('accountLadder_2').getValue())
				&&!Ext.isEmpty(Ext.getCmp('discountFlagB303_2').getValue())){
			if(!this.valueIsNull())
			{
				return;
			}	
		}
		if(Ext.getCmp('IdFormB303').getForm().isValid()){
			var cust=Ext.getCmp('account_MUIB303').getSelectionModel().getSelection()[0];
			var accountDay=null;
			if(Ext.getCmp('accountCycle3B03').getValue()=='2'){
				accountDay=Ext.getCmp('balanceDayB303_1').getValue()==undefined?null:Ext.getCmp('balanceDayB303_1').getValue();
			}else if(Ext.getCmp('accountCycle3B03').getValue()=='3'){
				accountDay=Ext.getCmp('balanceDayB303_2').getValue()==undefined?null:Ext.getCmp('balanceDayB303_2').getValue();
			}
			var accountGroupNo=null;
			var rgstName='';
			var rgstDate='';
			var updtDate='';
			var updtName='';
			if(typeB303=='add'){
				if(!Ext.isEmpty(Ext.getCmp('accountGroupNoB303').getValue()))
				{
					var listDetail = [];
					if(!Ext.isEmpty(Ext.getCmp('ownerNoB303').getValue()))
					{
						var strDtl={
								columnId:'t1.owner_no',
								value:Ext.getCmp('ownerNoB303').getValue()
							};
						listDetail.push(strDtl);
					}
					if(!Ext.isEmpty(Ext.getCmp('accountCycle3B03').getValue()))
					{
						var strDtl={
								columnId:'t1.account_cycle',
								value:Ext.getCmp('accountCycle3B03').getValue()
							};
						listDetail.push(strDtl);
					}
					if(accountDay!=null)
					{
						var strDtl={
								columnId:'t1.balance_day',
								value:accountDay
							};
						listDetail.push(strDtl);
					}
					var strDtl={
							columnId:'t1.account_group_no',
							value:Ext.getCmp('accountGroupNoB303').getValue()
						};
					listDetail.push(strDtl);
					var strJson = Ext.encode(listDetail);
					Ext.Ajax.request({
						url:'cost_AccountAction_getAccountMList',
						method:'post',
						async:false,
						params:{
							str:strJson
						},
						success:function(response){
							 var rec3=Ext.decode(response.responseText);
							 if(rec3!=''){
								  accountGroupNo=Ext.getCmp('accountGroupNoB303').getValue();
								  rgstName=rec3[0][8];
								  rgstDate=Ext.Date.parse(rec3[0][12], "YmdHis");
								  updtName=Ext.get('workerNo').getValue();
							      updtDate=new Date();
							 }
						}
					});
				}else{
					Ext.Ajax.request({
						url:'cost_AccountAction_getMaxAccountGroupNo',
						method:'post',
						async:false,
						params:{
							str:null
						},
						success:function(response){
							var rec1=Ext.decode(response.responseText);
							 accountGroupNo=rec1+1;
							 rgstName=Ext.get('workerNo').getValue();
							 rgstDate=new Date();
							 updtName='';
							 updtDate='';
						}
				   });
				}
				
			}else{
				 accountGroupNo=Ext.getCmp('accountGroupNoB303').getValue();
			}
			cost_Account_MStr={
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					ownerNo:Ext.getCmp('ownerNoB303').getValue(),
					accountGroupNo:accountGroupNo,
					accountCycle:Ext.getCmp('accountCycle3B03').getValue(),
					balanceDay:accountDay,
					status:Ext.getCmp('statusB303').getValue(),					
					remark:Ext.getCmp('remarkB303_1').getValue(),
					rgstDate:typeB303=='add'?rgstDate:cust.data.rgstDate,
					rgstName:typeB303=='add'?rgstName:cust.data.rgstName,
					updtDate:typeB303=='add'?updtDate:new Date(),
					updtName:typeB303=='add'?updtName:Ext.get('workerNo').getValue()
				};
			 if(!Ext.isEmpty(Ext.getCmp('accountTypeB303').getValue())
				&& !Ext.isEmpty(Ext.getCmp('accountNoB303').getValue())/*
				&& !Ext.isEmpty(Ext.getCmp('accountNameB303').getValue())*/){
				var accountId='0';
				if(Ext.isEmpty(Ext.getCmp('accountIdB303').getValue())){
					var detail = [];
					var a={
							columnId:'t1.owner_no',
							value:Ext.getCmp('ownerNoB303').getValue()
						};
					detail.push(a);
					var b={
							columnId:'t1.account_group_no',
							value:accountGroupNo
						};
					detail.push(b);
					var jsonDetail = Ext.encode(detail);
					Ext.Ajax.request({
						url:'cost_AccountAction_getAccountId',
						method:'post',
						async:false,
						params:{
								str:jsonDetail
						},
						success:function(response){
							var rec2=Ext.decode(response.responseText);
							accountId=rec2+1;
						}
					});
				}else{
					accountId=Ext.getCmp('accountIdB303').getValue();
				}
				
				var cost_Account_DStr={
						id:{
							enterpriseNo:Ext.get('enterpriseNo').getValue(),
							warehouseNo:Ext.get('warehouseNo').getValue(),
							ownerNo:Ext.getCmp('ownerNoB303').getValue(),
							accountGroupNo:accountGroupNo,
							accountNo:Ext.getCmp('accountNoB303').getValue()
						},
						accountType:Ext.getCmp('accountTypeB303').getValue(),
						accountId:accountId,
//						accountName:'',
						otherCost1:Ext.getCmp('otherCost1_B303').getValue()==undefined?0:Ext.getCmp('otherCost1_B303').getValue(),
						otherCost2:Ext.getCmp('otherCost2_B303').getValue()==undefined?0:Ext.getCmp('otherCost2_B303').getValue(),
						otherCost3:Ext.getCmp('otherCost3_B303').getValue()==undefined?0:Ext.getCmp('otherCost3_B303').getValue(),
						otherCost4:Ext.getCmp('otherCost4_B303').getValue()==undefined?0:Ext.getCmp('otherCost4_B303').getValue(),		
						otherCost5:Ext.getCmp('otherCost5_B303').getValue()==undefined?0:Ext.getCmp('otherCost5_B303').getValue(),
						remark:Ext.getCmp('remarkB303').getValue(),
						rgstDate:typeB303=='add'?new Date():cust.data.rgstDate,
						rgstName:typeB303=='add'?Ext.get('workerNo').getValue():cust.data.rgstName,
						updtDate:typeB303=='add'?'':new Date(),
						updtName:typeB303=='add'?'':Ext.get('workerNo').getValue()
					};
				var imPortD=Ext.encode(cost_Account_DStr);
			}else{
				var imPortD=null;
			}
			var imPortM=Ext.encode(cost_Account_MStr);
			//保存科目策略
			Ext.Ajax.request({
				url:'cost_AccountAction_saveAccount',
				method:'post',
				async:false,
				params:{
					jsonMaster:imPortM,
					jsonDetail:imPortD
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){					
						Ext.example.msg($i18n.prompt,data.msg);
						if(Ext.isEmpty(Ext.getCmp('accountGroupNoB303').getValue())){
							var detail = [];
							var a={
									columnId:'t1.owner_no',
									value:Ext.getCmp('ownerNoB303').getValue()
								};
							detail.push(a);
							var b={
									columnId:'t1.account_group_no',
									value:accountGroupNo
								};
								detail.push(b);	
							var jsonDetail = Ext.encode(detail);
							var wheresql = {
								str : jsonDetail
							};
							//科目明细信息列表
							Ext.apply(Ext.getCmp('grid_01').getStore().proxy.extraParams,wheresql);
							Ext.getCmp('grid_01').getStore().removeAll();
							Ext.getCmp('grid_01').getStore().load();
							
							Ext.getCmp('accountGroupNoB303').getStore().removeAll();
							Ext.getCmp('accountGroupNoB303').getStore().add({
						    	value:accountGroupNo,
						    	dropValue:accountGroupNo,
						    	text:accountGroupNo
						    });
//							accountGroupNoCombo();
	            			Ext.getCmp('accountGroupNoB303').setValue(accountGroupNo);
						}else{
							Ext.getCmp('grid_01').getStore().reload();
						}
	            		commonSetMsterReadOnlyByArray(
	            				new Array('ownerNoB303','accountCycle3B03',
	            						'balanceDayB303_1','balanceDayB303_2',
	            						'accountGroupNoB303','accountTypeB303',
	            						'accountNoB303'),true);
	            		windowType2='edit';
	            		saveType='1';
	            		//保存优惠策略
						if(!Ext.isEmpty(Ext.getCmp('accountLadder_2').getValue())
								&&!Ext.isEmpty(Ext.getCmp('discountFlagB303_2').getValue())){
							
							cost_AccountDiscountStr={
									id:{
										enterpriseNo:Ext.get('enterpriseNo').getValue(),
										warehouseNo:Ext.get('warehouseNo').getValue(),
										ownerNo:Ext.getCmp('ownerNoB303').getValue(),
										accountLadder:Ext.getCmp('accountLadder_2').getValue(),
										accountNo:Ext.getCmp('accountNoB303').getValue()
									},	
									
									discountFlag:Ext.getCmp('discountFlagB303_2').getValue(),
									value1:Ext.getCmp('value1B303_2').getValue(),
									value2:Ext.getCmp('value2B303_2').getValue(),
									billingProject:Ext.getCmp('billingProjectB303_2').getValue(),					
									remark:Ext.getCmp('remarkB303_2').getValue(),
									rgstDate:typeB303=='add'?new Date():cust.data.rgstDate,
									rgstName:typeB303=='add'?Ext.get('workerNo').getValue():cust.data.rgstName,
									updtDate:typeB303=='add'?'':new Date(),
									updtName:typeB303=='add'?'':Ext.get('workerNo').getValue()
							};
							Ext.Ajax.request({
								url:'cost_AccountAction_saveAccountDiscount',
								method:'post',
								params:{
									str:Ext.encode(cost_AccountDiscountStr)
								},
								success:function(response){
									ownerNoB303=Ext.getCmp('ownerNoB303').getValue();
									accountNoB303=Ext.getCmp('accountNoB303').getValue();
									var detail1 = [];
									var a={
											columnId:'t1.owner_no',
											value:Ext.getCmp('ownerNoB303').getValue()
										};
									detail1.push(a);
								
									var b={
											columnId:'t1.account_no',
											value:Ext.getCmp('accountNoB303').getValue()
										};
										detail1.push(b);
										
									var jsonDetail1 = Ext.encode(detail1);
										
									var wheresql = {
										str : jsonDetail1
									};
									//窗口优惠阶梯信息列表
									Ext.apply(Ext.getCmp('grid_01_B303').getStore().proxy.extraParams,wheresql);
									Ext.getCmp('grid_01_B303').getStore().removeAll();
									Ext.getCmp('grid_01_B303').getStore().reload();
								}
							});
						}
					}else{
						Ext.example.msg($i18n.prompt,data.msg);
						Ext.getCmp('accountGroupNoB303').setValue('');
					}
				}
			});
		}
	},
	//修改科目信息
	detailEdit:function(){
		var data = Ext.getCmp('account_MUIB303').getSelectionModel().getSelection();
		
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{		
			Ext.create('cms.view.cost.window.cost_AccountAddOrEditWindow',{
				title:$i18n.titleupdate
		    }).show();
			typeB303='edit';
			windowType='edit';
			windowType2='edit';
			saveType='1';
			updateCommMenu5('cost_AccountB303');
			commonMenu5PrevOrNext('cost_AccountB303','account_MUIB303',0);
			commonSetMsterReadOnlyByArray(
				new Array('ownerNoB303','accountCycle3B03','balanceDayB303_1','balanceDayB303_2','accountGroupNoB303','balanceDayB303_1','accountTypeB303','accountNoB303','accountLadder_2'),true);
			this.loadDatabB303();
			setcost_MenuWidgetB303Tab2();
			
		}
	},
	//科目设置维护-删除
	deleteB303Click:function(){

		var cust=Ext.getCmp('account_MUIB303').getSelectionModel().getSelection();
		if(cust.length>0)
		{
			Ext.Msg.confirm($i18n.prompt, $i18n_prompt.deleteOrNot,function(button, text){
				if (button == 'yes') {
					var strDeleteType='';
					var str='';
					if(Ext.isEmpty(cust[0].data.accountNo)){
						strDeleteType='1';
						str =cust[0].data.accountGroupNo;
					}else{
						strDeleteType='2';
						str =cust[0].data.accountNo;
					}
					var params = {
							flag : strDeleteType,
							ownerNo : cust[0].data.ownerNo, 
							str  : str
					};
					Ext.Ajax.request({
						method:'post',
						url:'cost_AccountAction_deleteCostAccount',
						params:params,
					    success:function(response){
							var data=Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n.prompt,data.msg);
								Ext.getCmp('account_MUIB303').getStore().reload();
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
	//科目设置维护-刷新
	refreshBB303Click:function(){
		Ext.getCmp('ownerNoUIB303').getStore().removeAll();
		Ext.getCmp('ownerNoUIB303').getStore().reload();
	},
	//关闭窗口
	colse:function(){
		Ext.getCmp('cost_AccountAddOrEditWindow').close();
	},
	//实现上一页功能
	prev:function(){
		commonMenu5PrevOrNext('cost_AccountB303','account_MUIB303',-1);
		this.loadDatabB303();
	},
	
	//实现下一页功能
	next:function(){
		commonMenu5PrevOrNext('cost_AccountB303','account_MUIB303',1);
		this.loadDatabB303();
	},
	
	//根据货主和仓别加载科目，并且查找相应的信息
	selectAndGetAccount:function(){
		//获取科目
		var strDetail1 = [];
		var d1={
			columnId:'t1.owner_no',
			value:Ext.getCmp('ownerNoUIB303').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoUIB303').getValue()) &&
				Ext.getCmp('ownerNoUIB303').getValue()!='ALL'){
			strDetail1.push(d1);
		}
		var d2={
				columnId:'t1.warehouse_no',
				value:Ext.get('warehouseNo').getValue()
		};
		strDetail1.push(d2);
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				str  : jsonDetail
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoUIB303').getValue()) &&
				Ext.getCmp('ownerNoUIB303').getValue()!='ALL'){
			Ext.apply(Ext.getCmp('accountUIB303').getStore().proxy.extraParams,str);
			Ext.getCmp('accountUIB303').getStore().removeAll();
			Ext.getCmp('accountUIB303').getStore().load();
		}else{
			Ext.getCmp('accountUIB303').setValue(null);
			Ext.getCmp('accountUIB303').getStore().removeAll();
		}
		
		//查询计费公式
		Ext.apply(Ext.getCmp('account_MUIB303').getStore().proxy.extraParams,str);
		Ext.getCmp('account_MUIB303').getStore().removeAll();
		Ext.getCmp('account_MUIB303').getStore().load();		
	},
	
	//根据货主、仓别和科目查找对应的科目信息
	getAccountWithCondition:function(){
		var strDetail1 = [];
		if(!Ext.isEmpty(Ext.getCmp('ownerNoUIB303').getValue()))
		{
			var d1={
					columnId:'t1.owner_no',
					value:Ext.getCmp('ownerNoUIB303').getValue()
				};
				strDetail1.push(d1);
		}
		var d3={
				columnId:'t2.account_no',
				value:Ext.getCmp('accountUIB303').getValue()
		};
		strDetail1.push(d3);
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				str  : jsonDetail
		};
		Ext.apply(Ext.getCmp('account_MUIB303').getStore().proxy.extraParams,str);
		Ext.getCmp('account_MUIB303').getStore().removeAll();
		Ext.getCmp('account_MUIB303').getStore().load();		
	},
	
	//科目信息列表数据选择
	showRelaction:function(th,selected,eOpts){
		if(selected.length!=0){
			record=selected[0];
			var detail1 = [];
			var a={
					columnId:'t1.owner_no',
					value:record.data.ownerNo
				};
			detail1.push(a);
			var b={
					columnId:'t1.account_no',
					value:record.data.accountNo
				};
				detail1.push(b);
				
			var jsonDetail1 = Ext.encode(detail1);
				
			var wheresql = {
				str : jsonDetail1
			};
			
			Ext.apply(Ext.getCmp('accountUIB303_2').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('accountUIB303_2').getStore().removeAll();
			Ext.getCmp('accountUIB303_2').getStore().load();
			
			Ext.apply(Ext.getCmp('accountUIB303_3').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('accountUIB303_3').getStore().removeAll();
			Ext.getCmp('accountUIB303_3').getStore().load();
			
		}else{
			Ext.getCmp('accountUIB303_2').getStore().removeAll();
			Ext.getCmp('accountUIB303_3').getStore().removeAll();
		}
	},
	//选择结账周期
	accountCycleSelect:function(){
		if(Ext.getCmp('accountCycle3B03').getValue()=='2'){	
			Ext.getCmp('balanceDayB303_1').setVisible(true);
			Ext.getCmp('balanceDayB303_2').setVisible(false);

		}else if(Ext.getCmp('accountCycle3B03').getValue()=='3'){
			Ext.getCmp('balanceDayB303_2').setVisible(true);
			Ext.getCmp('balanceDayB303_1').setVisible(false);			
		}else{
			Ext.getCmp('balanceDayB303_1').setVisible(false);
			Ext.getCmp('balanceDayB303_2').setVisible(false);
			Ext.getCmp('balanceDayB303_1').setValue('');
			Ext.getCmp('balanceDayB303_2').setValue('');
		}
		accountGroupNoCombo();
	},
	//选择结算日期
	balanceDayB303_1Select:function(){
		accountGroupNoCombo();
	},
	//输入结算日期
	balanceDayB303_2Select:function(){
		if(typeB303=='add'){
			accountGroupNoCombo();
		}
	},
	//窗口-科目组下拉选择
	accountGroupNoB303Select:function(){
		accountDList();
		Ext.getCmp('accountTypeB303').setValue('');
		Ext.getCmp('accountNoB303').setValue('');
//		Ext.getCmp('accountNameB303').setValue('');
		Ext.getCmp('accountIdB303').setValue('');
		Ext.getCmp('otherCost1_B303').setValue('');
		Ext.getCmp('otherCost2_B303').setValue('');
		Ext.getCmp('otherCost3_B303').setValue('');
		Ext.getCmp('otherCost4_B303').setValue('');
		Ext.getCmp('otherCost5_B303').setValue('');
		Ext.getCmp('remarkB303').setValue('');
	},
	//科目明细信息列表数据选择
	grid_01Selectionchange:function(){
//		if(typeB303=='edit'){
			var rec=Ext.getCmp('grid_01').getSelectionModel().getSelection();
			if(rec.length>0)
			{	
				saveType='1';
				Ext.getCmp('accountTypeB303').setValue(rec[0].data.accountType);
				Ext.getCmp('accountTypeB303').fireEvent('select');
				
				Ext.getCmp('accountNoB303').getStore().removeAll();
				Ext.getCmp('accountNoB303').getStore().add({
			    	value:rec[0].data.accountNo,
			    	dropValue:"["+rec[0].data.accountNo+"]"+rec[0].data.accountName,
			    	text:rec[0].data.accountNo
			    });
				Ext.getCmp('accountNoB303').setValue(rec[0].data.accountNo);
				
//				Ext.getCmp('accountNameB303').setValue(rec[0].data.accountName);
				Ext.getCmp('accountIdB303').setValue(rec[0].data.accountId);
				Ext.getCmp('otherCost1_B303').setValue(rec[0].data.otherCost1);
				Ext.getCmp('otherCost2_B303').setValue(rec[0].data.otherCost2);	
				Ext.getCmp('otherCost3_B303').setValue(rec[0].data.otherCost3);
				Ext.getCmp('otherCost4_B303').setValue(rec[0].data.otherCost4);
				Ext.getCmp('otherCost5_B303').setValue(rec[0].data.otherCost5);
				Ext.getCmp('remarkB303').setValue(rec[0].data.remark);
				var detail1 = [];
				var detail2 = [];
				var a={
						columnId:'t1.owner_no',
						value:Ext.getCmp('ownerNoB303').getValue()
					};
				detail1.push(a);
				detail2.push(a);
				var b={
						columnId:'t1.account_no',
						value:Ext.getCmp('accountNoB303').getValue()
					};
					detail1.push(b);
				var jsonDetail1 = Ext.encode(detail1);
				var jsonDetail2 = Ext.encode(detail2);
				var wheresql = {
					str : jsonDetail1
				};
				var wheresql2 = {
						str : jsonDetail2
					};
				//窗口优惠阶梯信息列表
				Ext.apply(Ext.getCmp('grid_01_B303').getStore().proxy.extraParams,wheresql);
				Ext.getCmp('grid_01_B303').getStore().removeAll();
				Ext.getCmp('grid_01_B303').getStore().load();
				//计费项目信息列表
				Ext.apply(Ext.getCmp('grid_02_B303').getStore().proxy.extraParams,wheresql2);
				Ext.getCmp('grid_02_B303').getStore().removeAll();
				Ext.getCmp('grid_02_B303').getStore().reload();
				//计费项目与科目关系信息列表
				Ext.apply(Ext.getCmp('grid_03_B303').getStore().proxy.extraParams,wheresql);
				Ext.getCmp('grid_03_B303').getStore().removeAll();
				Ext.getCmp('grid_03_B303').getStore().load();
				//优惠计费项目下拉
				Ext.apply(Ext.getCmp('billingProjectB303_2').getStore().proxy.extraParams,wheresql);
				Ext.getCmp('billingProjectB303_2').getStore().removeAll();
				Ext.getCmp('billingProjectB303_2').getStore().reload();
			}
//		}
	},
	//添加科目和计费项目的关系
	rightB303:function(){
		if(!Ext.isEmpty(Ext.getCmp('accountNoB303').getValue())
				&& !Ext.isEmpty(Ext.getCmp('accountTypeB303').getValue())
				&& !Ext.isEmpty(Ext.getCmp('ownerNoB303').getValue()))
		{
			if(saveType=='0'){
				Ext.example.msg($i18n.prompt,$i18n_prompt.saveAccount);
				return;
			}else{
				var gridF=Ext.getCmp('grid_02_B303').getSelectionModel().getSelection();

				if(gridF.length!=0){
					var detail=[];
					for(var i=0;i<gridF.length;i++){
						var account_formula={
							id:{
								enterpriseNo:Ext.get('enterpriseNo').getValue(),
								warehouseNo:Ext.get('warehouseNo').getValue(),
								ownerNo:Ext.getCmp('ownerNoB303').getValue(),
								accountNo:Ext.getCmp('accountNoB303').getValue(),
								billingProject:gridF[i].get('billingProject')
							},
							billingType:gridF[i].get('billingType'),
							rgstName:Ext.get('workerNo').getValue(),
							rgstDate:new Date()
						};
					detail.push(account_formula);
					}
					var str=Ext.encode(detail);
					Ext.Ajax.request({
						url:'cost_AccountAction_saveAccountFormula',
						params:{
							str:str
						},
						success:function(response){
							var detail1 = [];
							var detail2 = [];
							var detail3 = [];
							var a={
									columnId:'t1.owner_no',
									value:Ext.getCmp('ownerNoB303').getValue()
								};
							detail1.push(a);
							detail2.push(a);
							var b={
									columnId:'t1.account_no',
									value:Ext.getCmp('accountNoB303').getValue()
								};
								detail1.push(b);
							var c={
								columnId:'t2.owner_no',
								value:Ext.getCmp('ownerNoB303').getValue()
								};
								detail3.push(c);
							var jsonDetail1 = Ext.encode(detail1);
							var jsonDetail2 = Ext.encode(detail2);
							var jsonDetail3 = Ext.encode(detail3);
							var wheresql = {
								str : jsonDetail1
							};
							var wheresql2 = {
									str : jsonDetail2,
									strWhereSql:jsonDetail3
								};
							//计费项目信息列表
							Ext.apply(Ext.getCmp('grid_02_B303').getStore().proxy.extraParams,wheresql2);
							Ext.getCmp('grid_02_B303').getStore().removeAll();
							Ext.getCmp('grid_02_B303').getStore().reload();
							//计费项目与科目关系信息列表
							Ext.apply(Ext.getCmp('grid_03_B303').getStore().proxy.extraParams,wheresql);
							Ext.getCmp('grid_03_B303').getStore().removeAll();
							Ext.getCmp('grid_03_B303').getStore().load();
							billingProjectSelectForWid();
						}
					});
				}else{
					Ext.example.msg($i18n.prompt,$i18n_prompt.select_bill_formulaset);
				}
				
			}
		}
	},
	
	//删除科目和计费项目的关系
	leftB303:function(){
		if(!Ext.isEmpty(Ext.getCmp('accountNoB303').getValue())
				&& !Ext.isEmpty(Ext.getCmp('accountTypeB303').getValue())
				&& !Ext.isEmpty(Ext.getCmp('ownerNoB303').getValue()))
		{
			var gridD =Ext.getCmp('grid_03_B303').getSelectionModel().getSelection();

			if(gridD.length!=0){
				var detail1=[];
				var detail2=[];
				var detail3=[];
				var detail4=[];
				for(var i=0;i<gridD.length;i++){
					detail1.push(gridD[i].get('warehouseNo'));
					detail2.push(gridD[i].get('ownerNo'));
					detail3.push(gridD[i].get('accountNo'));
					detail4.push(gridD[i].get('billingProject'));	
				}
				
				Ext.Ajax.request({
					url:'cost_AccountAction_deleteAccountFormula',
					params:{
						warehouseNo:detail1,
						ownerNo:detail2,
						accountNo:detail3,
						billingProject:detail4
					},
					success:function(response){
						var data=Ext.decode(response.responseText);					
						if(data.isSucc){
							var detail1 = [];
							var detail2 = [];
							var detail3 = [];
							var a={
									columnId:'t1.owner_no',
									value:Ext.getCmp('ownerNoB303').getValue()
								};
							detail1.push(a);
							detail2.push(a);
							var b={
									columnId:'t1.account_no',
									value:Ext.getCmp('accountNoB303').getValue()
								};
								detail1.push(b);
							var c={
								columnId:'t2.owner_no',
								value:Ext.getCmp('ownerNoB303').getValue()
								};
								detail3.push(c);
							var jsonDetail1 = Ext.encode(detail1);
							var jsonDetail2 = Ext.encode(detail2);
							var jsonDetail3 = Ext.encode(detail3);
							var wheresql = {
								str : jsonDetail1
							};
							var wheresql2 = {
									str : jsonDetail2,
									strWhereSql:jsonDetail3
								};
							//计费项目信息列表
							Ext.apply(Ext.getCmp('grid_02_B303').getStore().proxy.extraParams,wheresql2);
							Ext.getCmp('grid_02_B303').getStore().removeAll();
							Ext.getCmp('grid_02_B303').getStore().reload();
							//计费项目与科目关系信息列表
							Ext.apply(Ext.getCmp('grid_03_B303').getStore().proxy.extraParams,wheresql);
							Ext.getCmp('grid_03_B303').getStore().removeAll();
							Ext.getCmp('grid_03_B303').getStore().load();
							billingProjectSelectForWid();
						}else{
							Ext.example.msg($i18n.prompt,data.msg+data.obj);							
						}
						
					}
				});
			}else{
				Ext.example.msg($i18n.prompt,$i18n_prompt.select_bill_account_formulaset_relation);
			}
		}
	},
	//加载修改页面的数据
	loadDatabB303:function (){
		var cust=Ext.getCmp('account_MUIB303').getSelectionModel().getSelection();
		
		if(cust.length>0)
		{	
			Ext.getCmp('ownerNoB303').setValue(cust[0].data.ownerNo);
			Ext.getCmp('accountCycle3B03').setValue(cust[0].data.accountCycle);	
			Ext.getCmp('accountCycle3B03').fireEvent("select");	
			if(cust[0].data.accountCycle=='2'){
				Ext.getCmp('balanceDayB303_1').setValue(cust[0].data.balanceDay);
				Ext.getCmp('balanceDayB303_1').fireEvent("select");	
				Ext.getCmp('balanceDayB303_2').setVisible(false);
				Ext.getCmp('balanceDayB303_1').setVisible(true);
			}else if(cust[0].data.accountCycle=='3'){
				Ext.getCmp('balanceDayB303_2').setValue(cust[0].data.balanceDayText);
				Ext.getCmp('balanceDayB303_1').setVisible(false);
				Ext.getCmp('balanceDayB303_2').setVisible(true);
			}else{
				Ext.getCmp('balanceDayB303_1').setValue('');
				Ext.getCmp('balanceDayB303_2').setValue('');
				Ext.getCmp('balanceDayB303_1').setVisible(false);
				Ext.getCmp('balanceDayB303_2').setVisible(false);
			}
			Ext.getCmp('accountGroupNoB303').setValue(cust[0].data.accountGroupNo);
			Ext.getCmp('statusB303').setValue(cust[0].data.status);
			Ext.getCmp('remarkB303_1').setValue(cust[0].data.remark_m);
			Ext.getCmp('accountTypeB303').setValue(cust[0].data.accountType);
			Ext.getCmp('accountNoB303').getStore().removeAll();
			Ext.getCmp('accountNoB303').getStore().add({
		    	value:cust[0].data.accountNo,
		    	dropValue:"["+cust[0].data.accountNo+"]"+cust[0].data.accountName,
		    	text:cust[0].data.accountNo
		    });
			Ext.getCmp('accountNoB303').setValue(cust[0].data.accountNo);
			
//			Ext.getCmp('accountNameB303').setValue(cust[0].data.accountName);
			Ext.getCmp('accountIdB303').setValue(cust[0].data.accountId);
			Ext.getCmp('otherCost1_B303').setValue(cust[0].data.otherCost1);
			Ext.getCmp('otherCost2_B303').setValue(cust[0].data.otherCost2);
			Ext.getCmp('otherCost3_B303').setValue(cust[0].data.otherCost3);
			Ext.getCmp('otherCost4_B303').setValue(cust[0].data.otherCost4);
			Ext.getCmp('otherCost5_B303').setValue(cust[0].data.otherCost5);
			Ext.getCmp('remarkB303').setValue(cust[0].data.remark);
			
			var detail1 = [];
			var detail2 = [];
			var detail3 = [];
			var a={
					columnId:'t1.owner_no',
					value:cust[0].get('ownerNo')
				};
			detail1.push(a);
			detail2.push(a);
			var b={
					columnId:'t1.account_no',
					value:cust[0].get('accountNo')
				};
				detail1.push(b);
           var c={
				columnId:'t2.owner_no',
				value:cust[0].get('ownerNo')
		     	};
				detail3.push(c); 
			var jsonDetail1 = Ext.encode(detail1);
			var jsonDetail2 = Ext.encode(detail2);
			var jsonDetail3 = Ext.encode(detail3);
			var wheresql = {
				str : jsonDetail1
			};
			var wheresql2 = {
					str : jsonDetail2,
					strWhereSql:jsonDetail3
				};
			//加载科目明细信息列表
			accountDList();
			//窗口优惠阶梯信息列表
			Ext.apply(Ext.getCmp('grid_01_B303').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_01_B303').getStore().removeAll();
			Ext.getCmp('grid_01_B303').getStore().load();
			//计费项目信息列表
			Ext.apply(Ext.getCmp('grid_02_B303').getStore().proxy.extraParams,wheresql2);
			Ext.getCmp('grid_02_B303').getStore().removeAll();
			Ext.getCmp('grid_02_B303').getStore().reload();
			//计费项目与科目关系信息列表
			Ext.apply(Ext.getCmp('grid_03_B303').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_03_B303').getStore().removeAll();
			Ext.getCmp('grid_03_B303').getStore().load();
			//优惠计费项目下拉
			Ext.apply(Ext.getCmp('billingProjectB303_2').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('billingProjectB303_2').getStore().removeAll();
			Ext.getCmp('billingProjectB303_2').getStore().reload();
		}
	},
	cost_AccountAddOrEditWindowBeforeclose:function(){
		Ext.getCmp('account_MUIB303').getStore().load();
	},
	tabchange:function(tab)
	{
		if(tab.getActiveTab().id=="tabB303_1")
		{
			setcost_MenuWidgetB303Tab1();
			
		}else if(tab.getActiveTab().id=="tabB303_2")
		{
			setcost_MenuWidgetB303Tab2();
			
		}else if(tab.getActiveTab().id=="tabB303_3")
		{
			setcost_MenuWidgetB303Tab3();
		}
	},
	//优惠阶梯选择
	grid_01_B303Selectionchange:function(){
		
		var rec=Ext.getCmp('grid_01_B303').getSelectionModel().getSelection();
		if(rec.length>0)
		{	
			Ext.getCmp('accountLadder_2').setValue(rec[0].data.accountLadder);
			Ext.getCmp('discountFlagB303_2').setValue(rec[0].data.discountFlag);
			Ext.getCmp('discountFlagB303_2').fireEvent('select');
			Ext.getCmp('value1B303_2').setValue(rec[0].data.value1);
			Ext.getCmp('value2B303_2').setValue(rec[0].data.value2);	
			Ext.getCmp('billingProjectB303_2').setValue(rec[0].data.billingProject);
			Ext.getCmp('remarkB303_2').setValue(rec[0].data.remark);
			windowType='edit';
			commonSetMsterReadOnlyByArray(
    				new Array('accountLadder_2'),true);
		}
	},
	//验证优惠阶梯
	accountLadderBlur:function(editor,e,eOpts){
		if(windowType=='edit'){
			return
		}else if(windowType=='add'){
			var gridcount=Ext.getCmp('grid_01_B303').getStore().getCount();
	    	for(var i=0;i<gridcount;i++){
	    		var exp=Ext.getCmp('grid_01_B303').getStore().getAt(i);    		
	    		if(exp.get('accountLadder')==Ext.getCmp('accountLadder_2').getValue()){
	    			Ext.example.msg($i18n.prompt,$i18n_prompt.accountLadderAlreadyExists);
		    		Ext.getCmp('accountLadder_2').setValue('');
		    		Ext.getCmp('accountLadder_2').focus();
	    		}
	    	}
		}
	},
	//阶梯优惠—优惠方式选择
	discountFlagB303_2Select:function()
	{
		var s = Ext.getCmp('discountFlagB303_2').getValue();
		if(s.length>1){
		   var str = s;
		   var spstr = str.split("");
		   var discountFlag = spstr[1];
		}else{
		   var discountFlag = Ext.getCmp('discountFlagB303_2').getValue();  
		}
		//清空
		Ext.getCmp('value1B303_2').setValue('');
		Ext.getCmp('value2B303_2').setValue('');
		Ext.getCmp('billingProjectB303_2').setValue('');
		//设置只读属性
		if(discountFlag=='1' ||
				Ext.getCmp('discountFlagB303_2').getValue()=='2' ||
				Ext.getCmp('discountFlagB303_2').getValue()=='5')
		{
			Ext.getCmp('value1B303_2').setDisabled(false);
			Ext.getCmp('value2B303_2').setDisabled(false);
			Ext.getCmp('billingProjectB303_2').setDisabled(true);
		}else if(discountFlag=='3')
		{
			Ext.getCmp('value1B303_2').setDisabled(false);
			Ext.getCmp('value2B303_2').setDisabled(true);
			Ext.getCmp('billingProjectB303_2').setDisabled(false);			
		}else if(discountFlag=='4')
		{
			Ext.getCmp('value1B303_2').setDisabled(false);
			Ext.getCmp('value2B303_2').setDisabled(false);
			Ext.getCmp('billingProjectB303_2').setDisabled(false);			
		}
	},
	//设置光标跳到下一输入框
	boxkeydown:function(th,e,eOpts){
	  	if (e.getKey() == e.ENTER) {
		   th.nextSibling().focus();
        }
	}
 	
});
//加载科目组编码下拉
function accountGroupNoCombo(){
	var detail = [];
	if(!Ext.isEmpty(Ext.getCmp('ownerNoB303').getValue())){
		var a={
				columnId:'a.owner_no',
				value:Ext.getCmp('ownerNoB303').getValue()
			};
		detail.push(a);
	}
	if(!Ext.isEmpty(Ext.getCmp('accountCycle3B03').getValue())){
		var a={
				columnId:'a.account_cycle',
				value:Ext.getCmp('accountCycle3B03').getValue()
			};
		detail.push(a);
	}
	if(!Ext.isEmpty(Ext.getCmp('balanceDayB303_1').getValue())){
		var a={
				columnId:'a.balance_day',
				value:Ext.getCmp('balanceDayB303_1').getValue()
			};
		detail.push(a);
	}
	if(!Ext.isEmpty(Ext.getCmp('balanceDayB303_2').getValue())){
		var a={
				columnId:'a.balance_day',
				value:Ext.getCmp('balanceDayB303_2').getValue()
			};
		detail.push(a);
	}
	var jsonDetail = Ext.encode(detail);
	var wheresql = {
		str : jsonDetail
	};

	Ext.getCmp('accountGroupNoB303').setValue('');
	Ext.apply(Ext.getCmp('accountGroupNoB303').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('accountGroupNoB303').getStore().removeAll();
	Ext.getCmp('accountGroupNoB303').getStore().load();
}
//加载科目明细信息列表
function accountDList(){
	if(!Ext.isEmpty(Ext.getCmp('accountGroupNoB303').getValue())){
		var detail = [];
		var a={
				columnId:'t1.owner_no',
				value:Ext.getCmp('ownerNoB303').getValue()
			};
		detail.push(a);
		if(!Ext.isEmpty(Ext.getCmp('accountGroupNoB303').getValue())){
			var b={
					columnId:'t1.account_group_no',
					value:Ext.getCmp('accountGroupNoB303').getValue()
				};
				detail.push(b);
		}		
		var jsonDetail = Ext.encode(detail);
		var wheresql = {
			str : jsonDetail
		};
		//科目明细信息列表
		Ext.apply(Ext.getCmp('grid_01').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_01').getStore().removeAll();
		Ext.getCmp('grid_01').getStore().load();
	}
}
//加载计费项目信息列表
function billingProjectSelect(){
	var detail = [];
	var detail2 = [];
	if(!Ext.isEmpty(Ext.getCmp('ownerNoB303').getValue())){
		var a={
				columnId:'t1.owner_no',
				value:Ext.getCmp('ownerNoB303').getValue()
			};
		detail.push(a);
		var b={
				columnId:'t2.owner_no',
				value:Ext.getCmp('ownerNoB303').getValue()
			};
		detail2.push(b);
	}
	var jsonDetail = Ext.encode(detail);
	var jsonDetail2 = Ext.encode(detail2);
	var wheresql = {
		str : jsonDetail,
		strWhereSql:jsonDetail2
	};

	//计费项目信息列表
	Ext.apply(Ext.getCmp('grid_02_B303').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('grid_02_B303').getStore().removeAll();
	Ext.getCmp('grid_02_B303').getStore().reload();
}
//加载优惠计费项目下拉
function billingProjectSelectForWid(){
	var detail = [];
	var a={
			columnId:'t1.owner_no',
			value:Ext.getCmp('ownerNoB303').getValue()
		};
	detail.push(a);
	var a={
			columnId:'t1.account_no',
			value:Ext.getCmp('accountNoB303').getValue()
		};
	detail.push(a);
	var jsonDetail = Ext.encode(detail);
	var wheresql = {
		str : jsonDetail
	};

	//计费项目下拉
	Ext.apply(Ext.getCmp('billingProjectB303_2').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('billingProjectB303_2').getStore().removeAll();
	Ext.getCmp('billingProjectB303_2').getStore().reload();
}
//加载新增科目代码下拉
function accountForWid(){
	var detail = [];
	if(!Ext.isEmpty(Ext.getCmp('ownerNoB303').getValue())){
		var a={
				columnId:'a.owner_no',
				value:Ext.getCmp('ownerNoB303').getValue()
			};
		detail.push(a);
	}
	var jsonDetail = Ext.encode(detail);
	var wheresql = {
		str : jsonDetail
	};

	//科目代码下拉
	Ext.apply(Ext.getCmp('accountNoB303').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('accountNoB303').getStore().removeAll();
	Ext.getCmp('accountNoB303').getStore().reload();
}
/**
 * 设置科目策略下的按扭显示
 */
function setcost_MenuWidgetB303Tab1(){
	if(typeB303=='add')
	{
		Ext.getCmp('cost_AccountB303').items.items[1].setVisible(false);
		Ext.getCmp('cost_AccountB303').items.items[2].setVisible(false);
		Ext.getCmp('cost_AccountB303').items.items[3].setVisible(true);
		Ext.getCmp('cost_AccountB303').items.items[4].setVisible(true);
		Ext.getCmp('cost_AccountB303').items.items[5].setVisible(true);
	}else if(typeB303=='edit')
	{
		Ext.getCmp('cost_AccountB303').items.items[1].setVisible(true);
		Ext.getCmp('cost_AccountB303').items.items[2].setVisible(true);
		Ext.getCmp('cost_AccountB303').items.items[3].setVisible(true);
		Ext.getCmp('cost_AccountB303').items.items[4].setVisible(true);
		Ext.getCmp('cost_AccountB303').items.items[5].setVisible(true);
	}
}
/**
 * 设置优惠策略下的按扭显示
 */
function setcost_MenuWidgetB303Tab2(){

	if(typeB303=='add')
	{
		Ext.getCmp('cost_AccountB303').items.items[1].setVisible(false);
		Ext.getCmp('cost_AccountB303').items.items[2].setVisible(false);
		Ext.getCmp('cost_AccountB303').items.items[3].setVisible(true);
		Ext.getCmp('cost_AccountB303').items.items[4].setVisible(true);
		Ext.getCmp('cost_AccountB303').items.items[5].setVisible(true);
	}else if(typeB303=='edit')
	{
		Ext.getCmp('cost_AccountB303').items.items[1].setVisible(true);
		Ext.getCmp('cost_AccountB303').items.items[2].setVisible(true);
		Ext.getCmp('cost_AccountB303').items.items[3].setVisible(true);
		Ext.getCmp('cost_AccountB303').items.items[4].setVisible(true);
		Ext.getCmp('cost_AccountB303').items.items[5].setVisible(true);
	}
}
/**
 * 设置包含项目下的按扭显示
 */
function setcost_MenuWidgetB303Tab3(){

	if(typeB303=='add')
	{
		Ext.getCmp('cost_AccountB303').items.items[1].setVisible(false);
		Ext.getCmp('cost_AccountB303').items.items[2].setVisible(false);
		Ext.getCmp('cost_AccountB303').items.items[3].setVisible(false);
		Ext.getCmp('cost_AccountB303').items.items[4].setVisible(false);
		Ext.getCmp('cost_AccountB303').items.items[5].setVisible(true);
	}else if(typeB303=='edit')
	{
		Ext.getCmp('cost_AccountB303').items.items[1].setVisible(true);
		Ext.getCmp('cost_AccountB303').items.items[2].setVisible(true);
		Ext.getCmp('cost_AccountB303').items.items[3].setVisible(false);
		Ext.getCmp('cost_AccountB303').items.items[4].setVisible(false);
		Ext.getCmp('cost_AccountB303').items.items[5].setVisible(true);
	}
}