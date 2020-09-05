/**
 * 模块名称：计费公式管理Controller
 * 模块编码：B103
 * 创建：chensr 
 */
var typeB103='';               //用于判断点击save按钮时是用于保存还是修改
var saveType=''; //0:未保存  1：已保存
var rowindexB103=0;
var remarkTmpB103='';                //存放取值策略的参数说明
var ownerNoB103='';
var billingProjectB103='';
var isCanEditB103=false;
Ext.define('cms.controller.cost.cost_FormulasetController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.cost.cost_FormulasetUI'],
	
	init:function(){
		this.control({//新增
			'cost_FormulasetUI button[name=detailAdd]':{
//				click:this.detailAdd,
				click:this.detailAdd2
			},//重新加载添加窗口
			'cost_FormulasetAddOrEditWindow button[name=add]':{
				click:this.add
			},//保存计费公式
			'cost_FormulasetAddOrEditWindow button[name=save]':{
				click:this.save
			},//判断项目编号是否唯一（用于选择填写编号判断）
			'cost_FormulasetAddOrEditWindow textfield[id=billingProjectB103]':{
				blur:this.billingProjectCheck
			},//判断项目编号是否唯一（用于选择货主下拉判断）
			'cost_FormulasetAddOrEditWindow combo[id=ownerNoB103]':{
				change:this.billingProjectCheck
			},//根据货主和仓别加载计费项目
			'cost_FormulasetUI combo[id=ownerNoUIB103]':{
				change:this.selectAndGetBillingProject
			},//根据货主、仓别和计费项目查找对应的计费公式
			'cost_FormulasetUI combo[id=billingProjectUIB103]':{
				select:this.getFormulasetWithCondition
			},//修改
			'cost_FormulasetUI button[name=detailEdit]':{
				click:this.detailEdit
			},//删除
			'cost_FormulasetUI button[name=detailDelete]':{
				click:this.detailDelete
			},//点击项目信息列表数据
			'cost_FormulasetUI grid[id=formulasetUIB103]':{
				itemdblclick:this.detailEdit,//双击
				selectionchange:this.formulasetUIB103Selectionchange//单击
			},//上一条记录
			'cost_FormulasetAddOrEditWindow button[name=prev]':{
				click:this.prev
			},//下一条记录
			'cost_FormulasetAddOrEditWindow button[name=next]':{
				click:this.next
			},//关闭窗口
			'cost_FormulasetAddOrEditWindow button[name=close]':{
				click:this.colse
			},//关闭前事件
			'cost_FormulasetAddOrEditWindow':{
				beforeclose:this.cost_FormulasetAddOrEditWindowBeforeclose
			},//选择计费方式
			'cost_FormulasetAddOrEditWindow combo[id=billingFlagB103]':{
				select:this.seletcBillingFlag
			},//选择计费周期
			'cost_FormulasetAddOrEditWindow wms_DefFieldValCombo[id=billingCycleB103]':{
				select:this.billingCycleSelect
			},//周期为‘月’时校验结算日期
			'cost_FormulasetAddOrEditWindow  textfield[id=balanceDayB103_2]':{
				blur:this.balanceDayBlur
			},//验证起始日期
			'cost_FormulasetAddOrEditWindow form datefield[id= beginDateB103]':{
				blur:this.beginDateBlur
			},//验证截止日期
			'cost_FormulasetAddOrEditWindow form datefield[id=endDateB103]':{
				blur:this.endDateBlur
			},//根据项目类型 和计费单位，加载取值策略
			'cost_FormulasetAddOrEditWindow combo[id=billingTypeB103]':{
				select:this.valueFlagSelect
			},//根据项目类型 和计费单位，加载取值策略
			'cost_FormulasetAddOrEditWindow combo[id=billingUnitB103]':{
				select:this.valueFlagSelect
			},//获取参数说明
			'cost_FormulasetAddOrEditWindow combo[id=valueFlagB103]':{
				select:this.loadRemark
			},//导入
			'cost_FormulasetUI button[name=detailImport]':{
				click:this.detailImportClick
			},//是否标准策略选择
			'cost_FormulasetAddOrEditWindow combo[id=standardFlagB103]':{
				select:this.standardFlagB103Select
			},//TAB页切换
			'cost_FormulasetAddOrEditWindow tabpanel[id=tabB103]':
			{
				tabchange:this.tabchange
			},//点击优惠阶梯列表数据
			'cost_FormulasetAddOrEditWindow grid[id=grid_01_B103]':{
				selectionchange:this.grid_01_B103Selectionchange//单击
			},//包含商品群组添加商品群组按扭
			'cost_FormulasetAddOrEditWindow button[id=rightB103]':{
				click:this.rightB103
			},//包含商品群组移除商品群组按扭
			'cost_FormulasetAddOrEditWindow button[id=leftB103]':{
				click:this.leftB103
			},//验证优惠阶梯
			'cost_FormulasetAddOrEditWindow textfield[id=accountLadder_2]':{
				blur:this.accountLadderBlur
			},//优惠方式选择
			'cost_FormulasetAddOrEditWindow combo[id=discountFlagB103_2]':{
				select:this.discountFlagB103Select
			},//设置光标跳到下一输入框
			'cost_FormulasetAddOrEditWindow field':{
				specialkey:this.boxkeydown
			}
			////////////////////////////////////
			,//刷新
			'cost_FormulasetAddWindow button[id=refreshB103_1]':{
				click:this.refreshB103_1Click
			},//货主选择
			'cost_FormulasetAddWindow combo[id=ownerNoB103_1]':{
				select:this.ownerNoB103_1select
			},//GRID编辑
			'cost_FormulasetAddWindow grid[id=formulasetWindB103_1]':{
				beforeedit:this.formulasetWindB103_1beforeedit,
				edit:this.formulasetWindB103_1edit
			},//保存计费公式
			'cost_FormulasetAddWindow button[name=save]':{
				click:this.save2
			},//关闭窗口
			'cost_FormulasetAddWindow button[name=close]':{
				click:this.colse2
			},//计费方式选择前加载
			'wms_DefFieldValCombo[id=billingFlagB103_1]':{
				focus:this.billingFlagB103_1Focus
			},//计费单位选择前加载
			'wms_DefFieldValCombo[id=billingUnitB103_1]':{
				focus:this.billingUnitB103_1Focus
			},//取值方式选择前加载
			'wms_DefFieldValCombo[id=valueFlagB103_1]':{
				focus:this.valueFlagB103_1Focus
			},//周结算日期选择前加载
			'wms_DefFieldValCombo[id=balanceDayTextB103_1]':{
				focus:this.balanceDayTextB103_1Focus
			}
		});
	},
	
	//设置光标跳到下一输入框
	boxkeydown:function(th,e,eOpts){
	  	if (e.getKey() == e.ENTER) {
		   th.nextSibling().focus();
        }
	},
	//获取参数说明
	loadRemark:function(){

		Ext.Ajax.request({
			url : 'cost_FormulasetAction_getRemark',
			params : {
				billingType:Ext.getCmp('billingTypeB103').getValue(),
				billingUnit:Ext.getCmp('billingUnitB103').getValue(),
				ruleId:Ext.getCmp('valueFlagB103').getValue()
			},
			success : function(response) {
				var res = Ext.decode(response.responseText);
		    	if(res){
		    		remarkTmpB103=res[0];
		    	}
			}
		});
	
	},
	
	//根据项目类型和计费单位加载取值方式
	valueFlagSelect:function(){
		if(!Ext.isEmpty(Ext.getCmp('billingTypeB103').getValue()) &&
		   !Ext.isEmpty(Ext.getCmp('billingUnitB103').getValue()))
		{
			var wheresql=
			{
					billingType:Ext.getCmp('billingTypeB103').getValue(),
					billingUnit:Ext.getCmp('billingUnitB103').getValue()
			};
			Ext.apply(Ext.getCmp('valueFlagB103').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('valueFlagB103').getStore().removeAll();
			Ext.getCmp('valueFlagB103').setValue(null);
			Ext.getCmp('valueFlagB103').getStore().load();
		}
	},
	
	//选择计费方式
	seletcBillingFlag:function(){
		if(Ext.getCmp('billingFlagB103').getValue()=='1'){
			Ext.getCmp('valueB103').setDisabled(false);
			Ext.getCmp('billingUnitB103').setDisabled(true);
			Ext.getCmp('valueFlagB103').setDisabled(true);
			Ext.getCmp('unitPriceB103').setDisabled(true);
			Ext.getCmp('valueFlagB103').setValue('');
			Ext.getCmp('unitPriceB103').setValue('1');
			
		}else{
			Ext.getCmp('billingUnitB103').setDisabled(false);
			Ext.getCmp('valueFlagB103').setDisabled(false);
			Ext.getCmp('unitPriceB103').setDisabled(false);
			Ext.getCmp('valueB103').setDisabled(true);
			Ext.getCmp('valueB103').setValue('');
			Ext.getCmp('unitPriceB103').setValue('');
		}
	},
	//选择计费周期
	billingCycleSelect:function(){
		if(Ext.getCmp('billingCycleB103').getValue()=='2'){	
			Ext.getCmp('balanceDayB103_1').setVisible(true);
			Ext.getCmp('balanceDayB103_2').setVisible(false);

		}else if(Ext.getCmp('billingCycleB103').getValue()=='3'){
			Ext.getCmp('balanceDayB103_2').setVisible(true);
			Ext.getCmp('balanceDayB103_1').setVisible(false);			
		}else{
			Ext.getCmp('balanceDayB103_1').setVisible(false);
			Ext.getCmp('balanceDayB103_2').setVisible(false);	
		}
	},
	//周期为‘月’时校验结算日期
	balanceDayBlur:function(){
		if(!Ext.isEmpty(Ext.getCmp('billingCycleB103').getValue())){
			if(Ext.getCmp('billingCycleB103').getValue()=='3'){
				var balanceDay=Ext.getCmp('balanceDayB103_2').getValue();
				if(balanceDay>31||balanceDay<1||isNaN(Ext.getCmp('balanceDayB103_2').getValue())){
					Ext.example.msg($i18n.prompt, $i18n_prompt.balanceDayCannotMoreThan31);
					Ext.getCmp('balanceDayB103_2').setValue('');
				}
			}
		}
	},
	//校验起始日期
	beginDateBlur:function(){
		if(!Ext.isEmpty(Ext.getCmp('beginDateB103').getValue())
				&& !Ext.isEmpty(Ext.getCmp('endDateB103').getValue())){
			if(Ext.util.Format.date(Ext.getCmp('beginDateB103').getValue(), 'Y-m-d')
					>Ext.util.Format.date(Ext.getCmp('endDateB103').getValue(), 'Y-m-d')){
				Ext.example.msg($i18n.prompt, $i18n_prompt.beginDayCannotMoreThanEndDate);
				Ext.getCmp('beginDateB103').setValue('');
			}
		}
	},
	//校验截止日期
	endDateBlur:function(){
		if(!Ext.isEmpty(Ext.getCmp('endDateB103').getValue())){
			if(Ext.util.Format.date(Ext.getCmp('endDateB103').getValue(), 'Y-m-d')
					<Ext.Date.format(new Date(),'Y-m-d')){
				Ext.example.msg($i18n.prompt, $i18n_prompt.endDateCannotLessThanToday);
				Ext.getCmp('endDateB103').setValue('');
			}else if(Ext.util.Format.date(Ext.getCmp('endDateB103').getValue(), 'Y-m-d')
					<Ext.util.Format.date(Ext.getCmp('beginDateB103').getValue(), 'Y-m-d')){
				Ext.example.msg($i18n.prompt, $i18n_prompt.endDateCannotLessThanbeginDay);
				Ext.getCmp('endDateB103').setValue('');
			}
		}
	},
	//调用新增窗口
	detailAdd:function(){
		Ext.create('cms.view.cost.window.cost_FormulasetAddOrEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		addCommMenu5('cost_FormulasetB103');
		typeB103='add';
		saveType='0';
		setcost_MenuWidgetB103Tab1();
		Ext.getCmp('standardFlagB103').setValue('1');
		Ext.getCmp('costFlagB103').setValue('0');
		Ext.getCmp('statusB103').setValue('0');
		Ext.getCmp('ownerNoB103').focus(false,5);
		Ext.getCmp('balanceDayB103_1').setVisible(false);
		Ext.getCmp('balanceDayB103_2').setVisible(false);
		Ext.getCmp('grid_01_B103').getStore().removeAll();
		var wheresql = {
				flag:'0',
				str : null,
				strWheresql : null
			};
			
		Ext.apply(Ext.getCmp('grid_02_B103').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_02_B103').getStore().removeAll();
		Ext.getCmp('grid_02_B103').getStore().load();
		
		Ext.getCmp('grid_03_B103').getStore().removeAll();
	},
	//调用新增窗口
	detailAdd2:function(grid){
		Ext.create('cms.view.cost.window.cost_FormulasetAddWindow',{
			title:$i18n.titleadd//新增
		}).show();
		addCommMenu5('cost_FormulasetB103_1');
		isCanEditB103=true;
		Ext.getCmp('ownerNoB103_1').focus(false,1);
		Ext.getCmp('formulasetWindB103_1').getStore().removeAll();
  		Ext.getCmp('formulasetWindB103_1').getStore().reload();
  		setCost_FormulasetB103_1();
	},
	//实现新增功能（清空窗口的内容）
	add:function(){
		if(Ext.getCmp('tabB103').getActiveTab().id=="tabB103_1")
		{
			Ext.getCmp('IdFormB103').getForm().reset();
			bindEnterSkip($('IdFormB103'));//调用键盘处理方法
		}else if(Ext.getCmp('tabB103').getActiveTab().id=="tabB103_2")
		{
			commonSetMsterReadOnlyByArray(
					new Array('accountLadder_2','discountFlagB103_2',
							'value1B103_2','value2B103_2','remarkB103_2'),false);
			Ext.getCmp('accountLadder_2').setValue('');
			Ext.getCmp('discountFlagB103_2').setValue('');
			Ext.getCmp('value1B103_2').setValue('');
			Ext.getCmp('value2B103_2').setValue('');
			Ext.getCmp('remarkB103_2').setValue('');
    		Ext.getCmp('accountLadder_2').focus();
		}
		typeB103='windowAdd'; 
		
	},
		
	//判断项目编号是否唯一
	billingProjectCheck: function(){
		if(typeB103=='add' && !Ext.isEmpty(Ext.getCmp('ownerNoB103').getValue()))
		{
			var detail1 = [];
			var a={
					columnId:'t2.owner_no',
					value:Ext.getCmp('ownerNoB103').getValue()
				};
			
			detail1.push(a);
			
			var jsonDetail1 = Ext.encode(detail1);
				
			var wheresql = {
					flag:'0',
					str : null,
					strWhereSql : jsonDetail1
				};
			//商品群组信息列表
			Ext.apply(Ext.getCmp('grid_02_B103').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_02_B103').getStore().removeAll();
			Ext.getCmp('grid_02_B103').getStore().load();
		}
		
		if(typeB103=='add' && Ext.getCmp('billingProjectB103').getValue()!="" && Ext.getCmp('billingProjectB103').getValue()!=null
				&& Ext.getCmp('ownerNoB103').getValue()!="" && Ext.getCmp('ownerNoB103').getValue()!=null){			
			Ext.Ajax.request({
				url : 'cost_FormulasetAction_billingProjectCheck',
				params : {
					str:Ext.getCmp('billingProjectB103').getValue(),
					ownerNo:Ext.getCmp('ownerNoB103').getValue()
				},
				success : function(response) {
					var res = Ext.decode(response.responseText);
			    	if(res!=''){
			    		Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.billingProjectIsExist);
			    		Ext.getCmp('billingProjectB103').setValue(null);
			    	}
				}
			});
			
		}
	},
	
	//保存计费公式
	save: function(){
		this.saveFormulaset(); 
	},
	
	//根据货主查找相应的数据，并加载计费项目
	selectAndGetBillingProject: function(){		
		//获取计费项目
		var strDetail1 = [];
		var d1={
			columnId:'t1.owner_no',
			value:Ext.getCmp('ownerNoUIB103').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoUIB103').getValue()) &&
				Ext.getCmp('ownerNoUIB103').getValue()!='ALL'){
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
		if(!Ext.isEmpty(Ext.getCmp('ownerNoUIB103').getValue()) &&
				Ext.getCmp('ownerNoUIB103').getValue()!='ALL'){
			Ext.apply(Ext.getCmp('billingProjectUIB103').getStore().proxy.extraParams,str);
			Ext.getCmp('billingProjectUIB103').getStore().removeAll();
			Ext.getCmp('billingProjectUIB103').getStore().load();
		}else{
			Ext.getCmp('billingProjectUIB103').setValue(null);
			Ext.getCmp('billingProjectUIB103').getStore().removeAll();
		}
		
		//查询计费公式
		Ext.apply(Ext.getCmp('formulasetUIB103').getStore().proxy.extraParams,str);
		Ext.getCmp('formulasetUIB103').getStore().removeAll();
		Ext.getCmp('formulasetUIB103').getStore().load();	
	},
	
	//根据货主、仓别和计费项目查找计费公式
	getFormulasetWithCondition:function(){	
		var strDetail1 = [];
		var d1={
			columnId:'t1.owner_no',
			value:Ext.getCmp('ownerNoUIB103').getValue()
		};
		strDetail1.push(d1);
		
		var d2={
				columnId:'t1.warehouse_no',
				value:Ext.get('warehouseNo').getValue()
		};
		strDetail1.push(d2);
		
		var d3={
				columnId:'t1.billing_project',
				value:Ext.getCmp('billingProjectUIB103').getValue()
		};
		strDetail1.push(d3);
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				str  : jsonDetail
		};
		Ext.apply(Ext.getCmp('formulasetUIB103').getStore().proxy.extraParams,str);
		Ext.getCmp('formulasetUIB103').getStore().removeAll();
		Ext.getCmp('formulasetUIB103').getStore().load();		
	},
	//删除
	detailDelete:function(){

		var cust=Ext.getCmp('formulasetUIB103').getSelectionModel().getSelection();
		if(cust.length>0)
		{
			Ext.Msg.confirm($i18n.prompt, $i18n_prompt.deleteOrNot,function(button, text){
				if (button == 'yes') {
					
					var params = {
							ownerNo : cust[0].data.ownerNo, 
							billingType : cust[0].data.billingType,
							strBillingProject  : cust[0].data.billingProject
					};
					Ext.Ajax.request({
						method:'post',
						url:'cost_FormulasetAction_deleteBillingProject',
						params:params,
					    success:function(response){
							var data=Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n.prompt,data.msg);
								Ext.getCmp('formulasetUIB103').getStore().reload();
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
	//调用编辑窗口
	detailEdit:function(){
		var data = Ext.getCmp('formulasetUIB103').getSelectionModel().getSelection();
		
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{		
			Ext.create('cms.view.cost.window.cost_FormulasetAddOrEditWindow',{
				title:$i18n.titleupdate
		    }).show();
			this.loadDataB103();
			commonSetMsterReadOnlyByArray(
					new Array('ownerNoB103','billingProjectB103','billingTypeB103',
							'standardFlagB103','accountLadder_2',
							'billingCycleB103','balanceDayB103_1','costFlagB103'),true);
			commonMenu5PrevOrNext('cost_FormulasetB103','formulasetUIB103',0);
			updateCommMenu5('cost_FormulasetB103');
			typeB103='edit';
			saveType='1';
			setcost_MenuWidgetB103Tab1();
		}
	},
	
	
	formulasetUIB103Selectionchange:function(th,selected,eOpts){
		if(selected.length!=0){
			record=selected[0];
			var detail1 = [];
			var a={
					columnId:'t1.owner_no',
					value:record.data.ownerNo
				};
			detail1.push(a);
			var b={
					columnId:'t1.billing_project',
					value:record.data.billingProject
				};
				detail1.push(b);
				
			var jsonDetail1 = Ext.encode(detail1);
				
			var wheresql = {
				str : jsonDetail1
			};
			
			Ext.apply(Ext.getCmp('formulasetUIB103_2').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('formulasetUIB103_2').getStore().removeAll();
			Ext.getCmp('formulasetUIB103_2').getStore().load();
			
			Ext.apply(Ext.getCmp('formulasetUIB103_3').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('formulasetUIB103_3').getStore().removeAll();
			Ext.getCmp('formulasetUIB103_3').getStore().load();
			
		}else{
			Ext.getCmp('formulasetUIB103_2').getStore().removeAll();
		}
	},
	//实现上一页功能
	prev:function(){		
		commonMenu5PrevOrNext('cost_FormulasetB103','formulasetUIB103',-1);
		this.loadDataB103();
	},
	
	//实现下一页功能
	next:function(){
		commonMenu5PrevOrNext('cost_FormulasetB103','formulasetUIB103',1);
		this.loadDataB103();
	},
	
	//关闭窗口
	colse:function(){
		Ext.getCmp('cost_FormulasetAddOrEditWindow').close();
	},
	//加载修改页面的数据
	loadDataB103:function(){
		var cust=Ext.getCmp('formulasetUIB103').getSelectionModel().getSelection();
		if(cust.length>0)
		{
			Ext.getCmp('ownerNoB103').setValue(cust[0].data.ownerNo);
			Ext.getCmp('billingTypeB103').setValue(cust[0].data.billingType);	
			Ext.getCmp('billingProjectB103').setValue(cust[0].data.billingProject);
			Ext.getCmp('projectNameB103').setValue(cust[0].data.projectName);
			Ext.getCmp('standardFlagB103').setValue(cust[0].data.standardFlag);	
			Ext.getCmp('beginDateB103').setValue(cust[0].data.beginDateText);
			Ext.getCmp('endDateB103').setValue(cust[0].data.endDateText);
			Ext.getCmp('costFlagB103').setValue(cust[0].data.costFlag);	
			Ext.getCmp('statusB103').setValue(cust[0].data.status);	
			Ext.getCmp('billingFlagB103').setValue(cust[0].data.billingFlag);
			Ext.getCmp('billingFlagB103').fireEvent("select");
			Ext.getCmp('billingUnitB103').setValue(cust[0].data.billingUnit);
			Ext.getCmp('billingUnitB103').fireEvent("select");
			Ext.getCmp('valueFlagB103').setValue(cust[0].data.valueFlag);
			Ext.getCmp('valueB103').setValue(cust[0].data.fixedValue);
			Ext.getCmp('unitPriceB103').setValue(cust[0].data.unitPrice);
			Ext.getCmp('billingCycleB103').setValue(cust[0].data.billingCycle);
			if(cust[0].data.billingCycle=='2'){
				Ext.getCmp('balanceDayB103_1').setValue(cust[0].data.balanceDay);
				Ext.getCmp('balanceDayB103_1').fireEvent("select");	
				Ext.getCmp('balanceDayB103_2').setVisible(false);
				Ext.getCmp('balanceDayB103_1').setVisible(true);
			}else if(cust[0].data.billingCycle=='3'){
				Ext.getCmp('balanceDayB103_2').setValue(cust[0].data.balanceDayText);
				Ext.getCmp('balanceDayB103_1').setVisible(false);
				Ext.getCmp('balanceDayB103_2').setVisible(true);
			}else{
				Ext.getCmp('balanceDayB103_1').setValue('');
				Ext.getCmp('balanceDayB103_2').setValue('');
				Ext.getCmp('balanceDayB103_1').setVisible(false);
				Ext.getCmp('balanceDayB103_2').setVisible(false);
			}
			Ext.getCmp('remarkB103_1').setValue(cust[0].data.remark);
			Ext.getCmp('otherCost1_B103').setValue(cust[0].data.otherCost1);
			Ext.getCmp('otherCost2_B103').setValue(cust[0].data.otherCost2);
			Ext.getCmp('otherCost3_B103').setValue(cust[0].data.otherCost3);
			Ext.getCmp('otherCost4_B103').setValue(cust[0].data.otherCost4);
			Ext.getCmp('otherCost5_B103').setValue(cust[0].data.otherCost5);
			
			var detail1 = [];
			var detail2 = [];
			var a={
					columnId:'t1.owner_no',
					value:cust[0].get('ownerNo')
				};
			detail1.push(a);
			var a2={
					columnId:'t2.owner_no',
					value:cust[0].get('ownerNo')
				};
			detail2.push(a2);
			var b={
					columnId:'t1.billing_project',
					value:cust[0].get('billingProject')
				};
				detail1.push(b);
				
			var jsonDetail1 = Ext.encode(detail1);
			var jsonDetail2 = Ext.encode(detail2);
				
			var wheresql = {
				str : jsonDetail1
			};
			var wheresql2 = {
					flag:'1',
					str : jsonDetail1,
					strWhereSql : jsonDetail2
				};
			//窗口优惠阶梯信息列表
			Ext.apply(Ext.getCmp('grid_01_B103').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_01_B103').getStore().removeAll();
			Ext.getCmp('grid_01_B103').getStore().load();
			//商品群组信息列表
			Ext.apply(Ext.getCmp('grid_02_B103').getStore().proxy.extraParams,wheresql2);
			Ext.getCmp('grid_02_B103').getStore().removeAll();
			Ext.getCmp('grid_02_B103').getStore().load();
			//包含商品群组信息列表
			Ext.apply(Ext.getCmp('grid_03_B103').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_03_B103').getStore().removeAll();
			Ext.getCmp('grid_03_B103').getStore().load();
		}
	},
	//根据优惠方式，判断值1、值2和优惠代码是否可以为空
	checkValue1OrValue2IsNull: function(){	
		var flag =Ext.getCmp('discountFlagB103_2').getValue();
		if (flag=='1' || flag=='2')
		{
			if(Ext.isEmpty(Ext.getCmp('value1B103_2').getValue()))
			{
				Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.value1IsNotEmpty);
				Ext.getCmp('value1B103_2').focus(false, 10);
				return false;
			}
		}else if(flag=='3'|| flag=='4'|| flag=='5')
		{
			if(Ext.isEmpty(Ext.getCmp('value1B103_2').getValue()))
			{
				Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.value1IsNotEmpty);
				Ext.getCmp('value1B103_2').focus(false, 10);

				return false;
			}
			
			if(Ext.isEmpty(Ext.getCmp('value2B103_2').getValue()))
			{
				Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.value2IsNotEmpty);
				Ext.getCmp('value2B103_2').focus(false, 10);
				return false;
			}
		}
		return true;
	},
	saveFormulaset:function(){
		
		if(!commonCheckIsInputAll('IdFormB103')){
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('billingFlagB103').getValue())){
			if(Ext.getCmp('billingFlagB103').getValue()=='2'){
				if(Ext.isEmpty(Ext.getCmp('valueFlagB103').getValue())){
					Ext.example.msg($i18n.prompt,$i18n_prompt.choiceBillingFlag);
					return;
				}
			}
		}
		if(!Ext.isEmpty(Ext.getCmp('accountLadder_2').getValue())
				&&!Ext.isEmpty(Ext.getCmp('discountFlagB103_2').getValue())){
			if(!this.checkValue1OrValue2IsNull())
			{
				return;
			}	
		}
		if(Ext.getCmp('IdFormB103').getForm().isValid()){
			var cust=Ext.getCmp('formulasetUIB103').getSelectionModel().getSelection()[0];
			var balanceDay=null;
			if(Ext.getCmp('billingCycleB103').getValue()=='2'){
				balanceDay=Ext.getCmp('balanceDayB103_1').getValue()==undefined?null:Ext.getCmp('balanceDayB103_1').getValue();
			}else if(Ext.getCmp('billingCycleB103').getValue()=='3'){
				balanceDay=Ext.getCmp('balanceDayB103_2').getValue()==undefined?null:Ext.getCmp('balanceDayB103_2').getValue();
			}
			cost_FormulasetStr={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					ownerNo:Ext.getCmp('ownerNoB103').getValue(),
					billingType:Ext.getCmp('billingTypeB103').getValue(),
					billingProject:Ext.getCmp('billingProjectB103').getValue()
					
				},	
				projectName:Ext.getCmp('projectNameB103').getValue(),
				standardFlag:Ext.getCmp('standardFlagB103').getValue(),
				endDate:Ext.getCmp('endDateB103').getValue()==undefined?null:Ext.util.Format.date(Ext.getCmp('endDateB103').getValue(), 'Y-m-d'),
				costFlag:Ext.getCmp('costFlagB103').getValue(),
				status:Ext.getCmp('statusB103').getValue(),
				billingFlag:Ext.getCmp('billingFlagB103').getValue(),
				billingUnit:Ext.getCmp('billingUnitB103').getValue(),
				valueFlag:Ext.getCmp('valueFlagB103').getValue(),
				fixedValue:Ext.getCmp('valueB103').getValue(),
				unitPrice:Ext.getCmp('unitPriceB103').getValue(),
				billingCycle:Ext.getCmp('billingCycleB103').getValue(),
			    balanceDay:balanceDay,
			    otherCost1:Ext.getCmp('otherCost1_B103').getValue()==undefined?0:Ext.getCmp('otherCost1_B103').getValue(),
			    otherCost2:Ext.getCmp('otherCost2_B103').getValue()==undefined?0:Ext.getCmp('otherCost2_B103').getValue(),
			    otherCost3:Ext.getCmp('otherCost3_B103').getValue()==undefined?0:Ext.getCmp('otherCost3_B103').getValue(),
			    otherCost4:Ext.getCmp('otherCost4_B103').getValue()==undefined?0:Ext.getCmp('otherCost4_B103').getValue(),		
			    otherCost5:Ext.getCmp('otherCost5_B103').getValue()==undefined?0:Ext.getCmp('otherCost5_B103').getValue(),
			    remark:Ext.getCmp('remarkB103_1').getValue() ,
				rgstDate:typeB103=='add'?new Date():cust.data.rgstDate.time,
				rgstName:typeB103=='add'?Ext.get('workerNo').getValue():cust.data.rgstName,
				updtDate:typeB103=='add'?'':new Date(),
				updtName:typeB103=='add'?'':Ext.get('workerNo').getValue(),
				beginDate:Ext.getCmp('beginDateB103').getValue()==undefined?null:Ext.util.Format.date(Ext.getCmp('beginDateB103').getValue(), 'Y-m-d')
						
			};								
			Ext.Ajax.request({
				url:'cost_FormulasetAction_saveFormulaset',
				method:'post',
				async:false,
				params:{
					str:Ext.encode(cost_FormulasetStr)
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){					
						Ext.example.msg($i18n.prompt,data.msg);
						saveType='1';
//						Ext.getCmp('formulasetUIB103').getStore().reload();
						if(!Ext.isEmpty(Ext.getCmp('accountLadder_2').getValue())
								&&!Ext.isEmpty(Ext.getCmp('discountFlagB103_2').getValue())){
							
							cost_formulaDiscountStr={
									id:{
										enterpriseNo:Ext.get('enterpriseNo').getValue(),
										warehouseNo:Ext.get('warehouseNo').getValue(),
										ownerNo:Ext.getCmp('ownerNoB103').getValue(),
										ladder:Ext.getCmp('accountLadder_2').getValue(),
										billingProject:Ext.getCmp('billingProjectB103').getValue()
									},	
									
									billingType:Ext.getCmp('billingTypeB103').getValue(),
									discountFlag:Ext.getCmp('discountFlagB103_2').getValue(),					
									value1:Ext.getCmp('value1B103_2').getValue(),
									value2:Ext.getCmp('value2B103_2').getValue(),
									remark:Ext.getCmp('remarkB103_2').getValue() ,
									rgstDate:typeB103=='add'?new Date():cust.data.rgstDate.time,
									rgstName:typeB103=='add'?Ext.get('workerNo').getValue():cust.data.rgstName,
									updtDate:typeB103=='add'?'':new Date(),
									updtName:typeB103=='add'?'':Ext.get('workerNo').getValue()
								};
							Ext.Ajax.request({
								url:'cost_FormulasetAction_saveFormulaDiscount',
								method:'post',
								params:{
									str:Ext.encode(cost_formulaDiscountStr)
								},
								success:function(response){
									ownerNoB103=Ext.getCmp('ownerNoB103').getValue();
									billingProjectB103=Ext.getCmp('billingProjectB103').getValue();
									Ext.getCmp('formulasetUIB103').getStore().reload();
									var detail1 = [];
									var a={
											columnId:'t1.owner_no',
											value:Ext.getCmp('ownerNoB103').getValue()
										};
									detail1.push(a);
								
									var b={
											columnId:'t1.billing_project',
											value:Ext.getCmp('billingProjectB103').getValue()
										};
										detail1.push(b);
										
									var jsonDetail1 = Ext.encode(detail1);
										
									var wheresql = {
										str : jsonDetail1
									};
									//窗口优惠阶梯信息列表
									Ext.apply(Ext.getCmp('grid_01_B103').getStore().proxy.extraParams,wheresql);
									Ext.getCmp('grid_01_B103').getStore().removeAll();
									Ext.getCmp('grid_01_B103').getStore().reload();
								}
							});
						}
					}else{
						Ext.example.msg($i18n.prompt,data.msg);
					}
				}
			});
		}
	},
	cost_FormulasetAddOrEditWindowBeforeclose:function(){
		Ext.getCmp('formulasetUIB103').getStore().load();
	},
	//导入
	detailImportClick:function(){
		Ext.create('cms.view.bset.window.billFormulasetUploadWindow',
		{
			title:$i18n.uplocad
		}).show();
	},
	//是否标准策略选择
	standardFlagB103Select:function(){
		if(!Ext.isEmpty(Ext.getCmp('standardFlagB103').getValue())){
			if(Ext.getCmp('standardFlagB103').getValue()=='1'){
				commonSetMsterReadOnlyByArray(
						new Array('accountLadder_2','discountFlagB103_2','value1B103_2',
								'value2B103_2','remarkB103_2'),true);
			}else{
				commonSetMsterReadOnlyByArray(
						new Array('accountLadder_2','discountFlagB103_2','value1B103_2',
								'value2B103_2','remarkB103_2'),false);
			}
		}
		
	},
	//TAB页切换
	tabchange:function(tab)
	{
		if(tab.getActiveTab().id=="tabB103_1")
		{
			setcost_MenuWidgetB103Tab1();
		}else if(tab.getActiveTab().id=="tabB103_2")
		{
			setcost_MenuWidgetB103Tab2();
			if(!Ext.isEmpty(Ext.getCmp('billingFlagB103').getValue())){
				if(Ext.getCmp('billingFlagB103').getValue()=='2'){
					commonSetMsterReadOnlyByArray(
							new Array('accountLadder_2','discountFlagB103_2','value1B103_2',
									'value2B103_2','remarkB103_2'),false);
				}else {
					commonSetMsterReadOnlyByArray(
							new Array('accountLadder_2','discountFlagB103_2','value1B103_2',
									'value2B103_2','remarkB103_2'),true);
				}
			}else {
				commonSetMsterReadOnlyByArray(
						new Array('accountLadder_2','discountFlagB103_2','value1B103_2',
								'value2B103_2','remarkB103_2'),true);
			}
		}else if(tab.getActiveTab().id=="tabB103_3")
		{
			setcost_MenuWidgetB103Tab3();
		}
	},
	//修改窗口-优惠阶梯信息列表数据选择
	grid_01_B103Selectionchange:function(){
		var data=Ext.getCmp('grid_01_B103').getSelectionModel().getSelection();
		if(data.length>0){
			Ext.getCmp('accountLadder_2').setValue(data[0].data.ladder);
			Ext.getCmp('discountFlagB103_2').setValue(data[0].data.discountFlag);
			Ext.getCmp('discountFlagB103_2').fireEvent("select");
			Ext.getCmp('value1B103_2').setValue(data[0].data.value1);
			Ext.getCmp('value2B103_2').setValue(data[0].data.value2);
			Ext.getCmp('remarkB103_2').setValue(data[0].data.remark);
		}
		
	},
	rightB103:function(){
		if(!Ext.isEmpty(Ext.getCmp('billingProjectB103').getValue())
				&& !Ext.isEmpty(Ext.getCmp('billingTypeB103').getValue())
				&& !Ext.isEmpty(Ext.getCmp('ownerNoB103').getValue())
				&& !Ext.isEmpty(Ext.getCmp('standardFlagB103').getValue()))
		{
			if(Ext.getCmp('standardFlagB103').getValue()=='0'){
				if(saveType=='0'){
					Ext.example.msg($i18n.prompt,$i18n_prompt.saveFormulaset);
					return;
				}else{
					var grid02=Ext.getCmp('grid_02_B103').getSelectionModel().getSelection();		
		            if(grid02.length!=0){
		            	var detail=[];
		    			for(var i=0;i<grid02.length;i++){
		    				var formulaArticlefamilyList={
		    					id:{
		    						enterpriseNo:Ext.get('enterpriseNo').getValue(),
		    						warehouseNo:Ext.get('warehouseNo').getValue(),
		    						ownerNo:Ext.getCmp('ownerNoB103').getValue(),
		    						billingProject:Ext.getCmp('billingProjectB103').getValue(),
		    						familyNo:grid02[i].get('familyNo')
		    					},
		    					billingType:Ext.getCmp('billingTypeB103').getValue(),
		    					rgstName:Ext.get('workerNo').getValue(),
		    					rgstDate:new Date()
		    				};
		    				detail.push(formulaArticlefamilyList);
		    				var str=Ext.encode(detail);
		    				Ext.Ajax.request({
		    					url:'cost_FormulasetAction_saveFormulaArticlefamilyList',
		    					async : false,
		    					params:{
		    						str:str
		    					},
		    					success:function(response){
		    						var data = Ext.decode(response.responseText);
									if(data.isSucc){
										var detail1 = [];
										var detail2 = [];
										var a={
												columnId:'t1.owner_no',
												value:Ext.getCmp('ownerNoB103').getValue()
											};
										detail1.push(a);
										var a2={
												columnId:'t2.owner_no',
												value:Ext.getCmp('ownerNoB103').getValue()
											};
										detail2.push(a2);
										var b={
												columnId:'t1.billing_project',
												value:Ext.getCmp('billingProjectB103').getValue()
											};
											detail1.push(b);
											
										var jsonDetail1 = Ext.encode(detail1);
										var jsonDetail2 = Ext.encode(detail2);
											
										var wheresql = {
											str : jsonDetail1
										};
										var wheresql2 = {
												flag:'1',
												str : jsonDetail1,
												strWhereSql : jsonDetail2
											};

										//商品群组信息列表
										Ext.apply(Ext.getCmp('grid_02_B103').getStore().proxy.extraParams,wheresql2);
										Ext.getCmp('grid_02_B103').getStore().removeAll();
										Ext.getCmp('grid_02_B103').getStore().load();
										//包含商品群组信息列表
										Ext.apply(Ext.getCmp('grid_03_B103').getStore().proxy.extraParams,wheresql);
										Ext.getCmp('grid_03_B103').getStore().removeAll();
										Ext.getCmp('grid_03_B103').getStore().load();

									}else{
										Ext.example.msg($i18n.prompt,data.msg+data.obj);
									}
		      					}
		    				});
		    			}				
					}
				}
			}else{
				Ext.example.msg($i18n.prompt,$i18n_prompt.isStandardFlag);
				return;
			}
		}
	},
	
	leftB103:function(){
		if(!Ext.isEmpty(Ext.getCmp('billingProjectB103').getValue())
				&& !Ext.isEmpty(Ext.getCmp('billingTypeB103').getValue())
				&& !Ext.isEmpty(Ext.getCmp('ownerNoB103').getValue())
				&& !Ext.isEmpty(Ext.getCmp('standardFlagB103').getValue()))
		{
			if(Ext.getCmp('standardFlagB103').getValue()=='0'){
				var grid03=Ext.getCmp('grid_03_B103').getSelectionModel().getSelection();
				if(grid03.length!=0){
					var detail1=[];
					var detail2=[];
					var detail3=[];
					var detail4=[];
					for(var i=0;i<grid03.length;i++){
						detail1.push(grid03[i].get('warehouseNo'));
						detail2.push(grid03[i].get('ownerNo'));
						detail3.push(grid03[i].get('billingProject'));
						detail4.push(grid03[i].get('familyNo'));
					}
					
					Ext.Ajax.request({
						url:'cost_FormulasetAction_deleteFormulaArticlefamilyList',
						params:{
							strWarehouseNo:detail1,
							sttrOwnerNo:detail2,
							strBillingProject:detail3,
							strFamilyNo:detail4
						},
						success:function(response){
							Ext.getCmp('grid_02_B103').getStore().load();
							Ext.getCmp('grid_03_B103').getStore().load();
						}
					});
				}else{
					Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
				}
			}		
		}
		
	},
	//验证优惠阶梯
	accountLadderBlur:function(editor,e,eOpts){
		if(typeB103=='edit'){
			return
		}else if(typeB103=='windowAdd'){
			var gridcount=Ext.getCmp('grid_01_B103').getStore().getCount();
	    	for(var i=0;i<gridcount;i++){
	    		var exp=Ext.getCmp('grid_01_B103').getStore().getAt(i);    		
	    		if(exp.get('ladder')==Ext.getCmp('accountLadder_2').getValue()){
	    			Ext.example.msg($i18n.prompt,$i18n_prompt.accountLadderAlreadyExists);
		    		Ext.getCmp('accountLadder_2').setValue('');
		    		Ext.getCmp('accountLadder_2').focus();
	    		}
	    	}
		}
	},
	//优惠方式选择
	discountFlagB103Select:function()
	{
		//清空
		Ext.getCmp('value1B103_2').setValue('');
		Ext.getCmp('value2B103_2').setValue('');
		//设置只读属性
		if(Ext.getCmp('discountFlagB103_2').getValue()=='1')
		{
			Ext.getCmp('value1B103_2').setDisabled(false);
			Ext.getCmp('value2B103_2').setDisabled(true);
		}else if(Ext.getCmp('discountFlagB103_2').getValue()=='2')
		{
			Ext.getCmp('value1B103_2').setDisabled(false);
			Ext.getCmp('value2B103_2').setDisabled(true);
		}else if(Ext.getCmp('discountFlagB103_2').getValue()=='3')
		{
			Ext.getCmp('value1B103_2').setDisabled(false);
			Ext.getCmp('value2B103_2').setDisabled(false);
		}else if(Ext.getCmp('discountFlagB103_2').getValue()=='4')
		{
			Ext.getCmp('value1B103_2').setDisabled(false);
			Ext.getCmp('value2B103_2').setDisabled(false);
		}
		else if(Ext.getCmp('discountFlagB103_2').getValue()=='5')
		{
			Ext.getCmp('value1B103_2').setDisabled(false);
			Ext.getCmp('value2B103_2').setDisabled(false);
		}
	},
	 getOwnerNoB103:function(){
  		 return  ownerNoB103;
  	 },
  	 
  	 getBillingProjectB103:function(){
  		return billingProjectB103;
  	 },
  	 /////////////////////////////////////
  	//刷新
  	refreshB103_1Click:function(){
  		Ext.getCmp('ownerNoB103_1').setValue('');
  		Ext.getCmp('ownerNoB103_1').getStore().removeAll();
  		Ext.getCmp('ownerNoB103_1').getStore().reload();
  		Ext.getCmp('formulasetWindB103_1').getStore().removeAll();
  		Ext.getCmp('formulasetWindB103_1').getStore().reload();
		Ext.getCmp('valueFlagB103_1').getStore().removeAll();
		Ext.getCmp('ownerNoB103_1').focus();
  	},
  	//货主选择
  	ownerNoB103_1select:function(){
  		Ext.getCmp('formulasetWindB103_1').getStore().removeAll();
		Ext.getCmp('formulasetWindB103_1').getStore().reload();
  	},
  	//网格编辑前
  	formulasetWindB103_1beforeedit:function(e){
 		if(!isCanEditB103)
 	    {
 	        e.cancel = true;
 	        return  false;  
 	    }	
 	},
 	//网格编辑
 	formulasetWindB103_1edit:function(editor,e,eOpts){
 		if(e.field=='billingProject')//计费项目
 		{
 			if(!Ext.isEmpty(e.value))
	 		{
 				if(!Ext.isEmpty(Ext.getCmp('ownerNoB103_1').getValue())
 						&& Ext.getCmp('ownerNoB103_1').getValue() != 'ALL'){
 					Ext.Ajax.request({
 		 				url : 'cost_FormulasetAction_billingProjectCheck',
 		 				params : {
 		 					str:editor.context.record.get('billingProject'),
 		 					ownerNo:Ext.getCmp('ownerNoB103_1').getValue()
 		 				},
 		 				success : function(response) {
 		 					var res = Ext.decode(response.responseText);
 		 				    if(res!=''){
 		 				    	Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.billingProjectIsExist);
 		 				    	editor.context.record.set('billingProject',null);
 		 				    	return;
 		 				    }else{
 		 				    	editor.context.record.set('choiceFlag','1');
 		 				    }
 		 				}
 		 			});
 				}else{
				    Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiceOwnerNo);
				    editor.context.record.set('billingProject',null);
					Ext.getCmp('ownerNoB103_1').focus();
					editor.context.record.set('choiceFlag','0');
				    return;
 				}
	 		}else{
	 			editor.context.record.set('choiceFlag','0');
	 		}
 		}else if(e.field=='billingFlagText'){//计费方式
 			if(!Ext.isEmpty(e.value)){
 				if(Ext.isEmpty(Ext.getCmp('ownerNoB103_1').getValue())
 						|| Ext.getCmp('ownerNoB103_1').getValue() == 'ALL'){
 					Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiceOwnerNo);
				    editor.context.record.set('billingFlagText',null);
					Ext.getCmp('ownerNoB103_1').focus();
				    return;
 				}else{
 					if(editor.context.record.get('billingFlagText')=='1'){
	 				    editor.context.record.set('billingUnitText',null);
	 				    editor.context.record.set('fixedValue',0);
	 				    editor.context.record.set('unitPrice',1);
 					}
 				}
 			}
 		}else if(e.field=='billingUnitText'){//计费单位
 			if(!Ext.isEmpty(e.value)){
 				if(!Ext.isEmpty(Ext.getCmp('ownerNoB103_1').getValue())
 						&& Ext.getCmp('ownerNoB103_1').getValue() != 'ALL'){
 					if(editor.context.record.get('billingFlagText')=='1'){
	 				    editor.context.record.set('billingUnitText',null);
	 				    Ext.getCmp('billingUnitB103_1').getStore().removeAll();
 					}
 				}else{
 					Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiceOwnerNo);
				    editor.context.record.set('billingUnitText',null);
					Ext.getCmp('ownerNoB103_1').focus();
				    return;
 				}
 			}
 		}else if(e.field=='fixedValue'){//固定值
 			if(!Ext.isEmpty(e.value)){
 				if(editor.context.record.get('endDateText')=='2'){
 					editor.context.record.set('fixedValue',1);
 				}
 			}
 		}else if(e.field=='unitPrice'){//默认单价
 			if(!Ext.isEmpty(e.value)){
 				if(editor.context.record.get('billingFlagText')=='1'){
 					editor.context.record.set('fixedValue',1);
 				}
 			}
 		}else if(e.field=='billingCycleText'){//计费周期
 			if(!Ext.isEmpty(e.value)){
 				if(!Ext.isEmpty(Ext.getCmp('ownerNoB103_1').getValue())
 						&& Ext.getCmp('ownerNoB103_1').getValue() != 'ALL'){
 					if(editor.context.record.get('billingCycleText')=='1'){
 	 					editor.context.record.set('weekBalanceDay',null);
 						editor.context.record.set('monthBalanceDay',null);
 	 				}else if(editor.context.record.get('billingCycleText')=='2'){
 	 					editor.context.record.set('monthBalanceDay',null);
 	 				}else if(editor.context.record.get('billingCycleText')=='3'){
 	 					editor.context.record.set('weekBalanceDay',null);
 	 				}
 				}
 			}
 		}else if(e.field=='monthBalanceDay'){//月截止日期
 			if(!Ext.isEmpty(e.value)){
 				if(!Ext.isEmpty(Ext.getCmp('ownerNoB103_1').getValue())
 						&& Ext.getCmp('ownerNoB103_1').getValue() != 'ALL'){
 					if(editor.context.record.get('billingCycleText')=='1' ||
 	 						editor.context.record.get('billingCycleText')=='2'){
 	 					editor.context.record.set('monthBalanceDay',null);
 	 				}else if(editor.context.record.get('billingCycleText')=='3'){
 	 					var monthBalanceDay=editor.context.record.get('monthBalanceDay');
 	 					if(monthBalanceDay>31||monthBalanceDay<1||isNaN(editor.context.record.get('monthBalanceDay'))){
 	 						Ext.example.msg($i18n.prompt, $i18n_prompt.balanceDayCannotMoreThan31);
 	 	 					editor.context.record.set('monthBalanceDay',null);
 	 					}
 	 				}
 				}
 			}
 		}else if(e.field=='beginDateText'){//起始日期
 			if(!Ext.isEmpty(e.value)
 					&&!Ext.isEmpty(editor.context.record.get('endDateText'))){
 				if(!Ext.isEmpty(Ext.getCmp('ownerNoB103_1').getValue())
 						&& Ext.getCmp('ownerNoB103_1').getValue() != 'ALL'){
 					if(Ext.util.Format.date(editor.context.record.get('endDateText'), 'Y-m-d')
 							<Ext.util.Format.date(editor.context.record.get('beginDateText'), 'Y-m-d')){
 						Ext.example.msg($i18n.prompt, $i18n_prompt.beginDayCannotMoreThanEndDate);
	 					editor.context.record.set('beginDateText',null);
 					}
 				}
 			}
 		}else if(e.field=='endDateText'){//截止日期
 			if(!Ext.isEmpty(e.value)){
 				if(!Ext.isEmpty(Ext.getCmp('ownerNoB103_1').getValue())
 						&& Ext.getCmp('ownerNoB103_1').getValue() != 'ALL'){
 					if(Ext.util.Format.date(editor.context.record.get('endDateText'), 'Y-m-d')
 		 					<Ext.Date.format(new Date(),'Y-m-d')){
 		 						Ext.example.msg($i18n.prompt, $i18n_prompt.endDateCannotLessThanToday);
 		 					    editor.context.record.set('endDateText',null);
 		 			}else if(Ext.util.Format.date(editor.context.record.get('endDateText'), 'Y-m-d')
 							<Ext.util.Format.date(editor.context.record.get('beginDateText'), 'Y-m-d')){
 						Ext.example.msg($i18n.prompt, $i18n_prompt.endDateCannotLessThanbeginDay);
	 					editor.context.record.set('endDateText',null);
 					}
 				}
 			}
 		}
 	},
 	//保存
 	save2:function(){
 		 if(Ext.isEmpty(Ext.getCmp('ownerNoB103_1').getValue())){
 			Ext.example.msg($i18n_prompt.prompt,'请选择货主！');
			 return;
 		 }
 		 var gridcount=Ext.getCmp('formulasetWindB103_1').getStore().getCount();
		 if(gridcount==0){
			Ext.example.msg($i18n.prompt,$i18n_prompt.tableCannotBeNull);
			return;
		 }
		 var count=0;
		 var detail=[];
		 for(var i=0;i<gridcount;i++){
			var record=Ext.getCmp('formulasetWindB103_1').getStore().getAt(i);
			if(record.get('choiceFlag')=='1'){
				if(!this.saveCheck(record)){
				    return;
				 }	
				count+=1;
				var balanceDay=null;
				if(record.get('billingCycleText')=='2'){
					balanceDay=record.get('weekBalanceDay')==undefined?null:record.get('weekBalanceDay');
				}else if(record.get('billingCycleText')=='3'){
					balanceDay=record.get('monthBalanceDay')==undefined?null:record.get('monthBalanceDay');
				}
				var beginDate=null;
				if(!Ext.isEmpty(record.get('beginDateText'))){
					beginDate=Ext.util.Format.date(record.get('beginDateText'), 'Y-m-d');
				}
				var endDate=null;
				if(!Ext.isEmpty(record.get('endDateText'))){
					endDate=Ext.util.Format.date(record.get('endDateText'), 'Y-m-d');
				}
				cost_FormulasetStr={
						id:{
							enterpriseNo:Ext.get('enterpriseNo').getValue(),
							warehouseNo:Ext.get('warehouseNo').getValue(),
							ownerNo:Ext.getCmp('ownerNoB103_1').getValue(),
							billingType:record.get('billingType'),
							billingProject:record.get('billingProject')
								
						},	
						projectName:record.get('projectName'),
						standardFlag:record.get('standardFlagText'),
						endDate:endDate,
						status:record.get('statusText'),
						billingFlag:record.get('billingFlagText'),
						billingUnit:record.get('billingUnitText'),
						valueFlag:record.get('valueFlagText'),
						fixedValue:record.get('fixedValue'),
						unitPrice:record.get('unitPrice'),
						billingCycle:record.get('billingCycleText'),
						balanceDay:balanceDay,
						otherCost1:record.get('otherCost1')==undefined?0:record.get('otherCost1'),
						otherCost2:record.get('otherCost2')==undefined?0:record.get('otherCost2'),
						otherCost3:record.get('otherCost3')==undefined?0:record.get('otherCost3'),
						otherCost4:record.get('otherCost4')==undefined?0:record.get('otherCost4'),		
						otherCost5:record.get('otherCost5')==undefined?0:record.get('otherCost5'),
						remark:record.get('remark') ,
					    rgstDate:new Date(),
						rgstName:Ext.get('workerNo').getValue(),
						updtDate:'',
						updtName:'',
						costFlag:record.get('costFlagText'),
						beginDate:beginDate
					};
				detail.push(cost_FormulasetStr);
			 }
		 };
		 if(count==0){
			 Ext.example.msg($i18n.prompt,'选择要保存的计费类型');
			 return;
		 }else{
			 Ext.Msg.confirm($i18n_prompt.prompt,$i18n_prompt.saveOrNot,function(button,text)
				{
					if(button=='yes'){
						var no=Ext.encode(detail);
						var params = {
							str:no
					    };
					    Ext.Ajax.request({
							method:'POST',
							url:'cost_FormulasetAction_saveFormulaset2',
							timeout:1800000,
							params:params,
							success:function(response){
								var data = Ext.decode(response.responseText);
								if(data.isSucc){
									Ext.example.msg($i18n_prompt.prompt,data.msg);
									Ext.getCmp('formulasetWindB103_1').getStore().removeAll();
									Ext.getCmp('formulasetWindB103_1').getStore().reload();
						            Ext.getCmp('formulasetUIB103').getStore().reload();
								}else{
									Ext.example.msg($i18n_prompt.prompt,data.msg+data.obj);
								}				
							}
						});	
					}
			 });
		 }	
 	},
 	//保存前校验
	saveCheck: function(record){	

		if(Ext.isEmpty(record.get('billingProject'))){
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.billingProjectIsNotNull);
			return false;
		}else if(Ext.isEmpty(record.get('projectName'))){
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.billingNameIsNotNull);
			return false;
		}else if(Ext.isEmpty(record.get('billingFlagText'))){
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.billingFlagIsNotNull);
			return false;
		}else if(record.get('billingFlagText')=='2' && Ext.isEmpty(record.get('billingUnitText'))){
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.billingUnitIsNotNull);
			return false;
		}else if(record.get('billingFlagText')=='2' && Ext.isEmpty(record.get('valueFlagText'))){
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.valueFlagIsNotNull);
			return false;
		}else if(record.get('billingFlagText')=='1' && Ext.isEmpty(record.get('fixedValue'))){
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.fixedValueIsNotNull);
			return false;
		}else if(Ext.isEmpty(record.get('unitPrice'))){
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.unitPriceIsNotNull);
			return false;
		}else if(Ext.isEmpty(record.get('billingCycleText'))){
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.billingCycleIsNotNull);
			return false;
		}else if(Ext.isEmpty(record.get('standardFlagText'))){
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.standardFlagIsNotNull);
			return false;
		}else if(Ext.isEmpty(record.get('statusText'))){
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.statusIsNotNull);
			return false;
		}
	
		return true;
	},
 	//关闭窗口
	colse2:function(){
		Ext.getCmp('cost_FormulasetAddWindow').close();
	},
	//计费方式选择前加载
	billingFlagB103_1Focus:function(){
		Ext.getCmp('billingFlagB103_1').getStore().reload();
	},
 	//计费单位选择前加载
 	billingUnitB103_1Focus:function(){
    	var data = Ext.getCmp('formulasetWindB103_1').getSelectionModel().getSelection();
 		if(data[0].get('billingFlagText')=='1'){
 			Ext.getCmp('billingUnitB103_1').getStore().removeAll();
 		}else{
 			Ext.getCmp('billingUnitB103_1').getStore().reload();
 		}
 	},
 	//取值方式选择前加载
 	valueFlagB103_1Focus:function(){
    	var data = Ext.getCmp('formulasetWindB103_1').getSelectionModel().getSelection();
 		var params=
		{
				billingType:data[0].get('billingType'),
				billingUnit:data[0].get('billingUnitText')
		};
 		Ext.apply(Ext.getCmp('valueFlagB103_1').getStore().proxy.extraParams,params);
		Ext.getCmp('valueFlagB103_1').getStore().removeAll();
	    Ext.getCmp('valueFlagB103_1').getStore().load();
 	},
 	//周结算日期选择前加载
 	balanceDayTextB103_1Focus:function(){
    	var data = Ext.getCmp('formulasetWindB103_1').getSelectionModel().getSelection();
    	if(data[0].get('billingCycleText')=='2'){
			Ext.getCmp('balanceDayTextB103_1').getStore().reload();
 		}else{
 			Ext.getCmp('balanceDayTextB103_1').getStore().removeAll();
 		}
 	}
});
/**
 * 设置新增窗口的按扭显示
 */
function setCost_FormulasetB103_1(){
	Ext.getCmp('cost_FormulasetB103_1').items.items[1].setVisible(false);
	Ext.getCmp('cost_FormulasetB103_1').items.items[2].setVisible(false);
	Ext.getCmp('cost_FormulasetB103_1').items.items[3].setVisible(false);
	Ext.getCmp('cost_FormulasetB103_1').items.items[4].setVisible(true);
	Ext.getCmp('cost_FormulasetB103_1').items.items[5].setVisible(true);
}
/**
 * 设置项目策略下的按扭显示
 */
function setcost_MenuWidgetB103Tab1(){
	if(typeB103=='add')
	{
		Ext.getCmp('cost_FormulasetB103').items.items[1].setVisible(false);
		Ext.getCmp('cost_FormulasetB103').items.items[2].setVisible(false);
		Ext.getCmp('cost_FormulasetB103').items.items[3].setVisible(false);
		Ext.getCmp('cost_FormulasetB103').items.items[4].setVisible(true);
		Ext.getCmp('cost_FormulasetB103').items.items[5].setVisible(true);
	}else if(typeB103=='edit')
	{
		Ext.getCmp('cost_FormulasetB103').items.items[1].setVisible(true);
		Ext.getCmp('cost_FormulasetB103').items.items[2].setVisible(true);
		Ext.getCmp('cost_FormulasetB103').items.items[3].setVisible(false);
		Ext.getCmp('cost_FormulasetB103').items.items[4].setVisible(true);
		Ext.getCmp('cost_FormulasetB103').items.items[5].setVisible(true);
	}
}
/**
 * 设置优惠策略下的按扭显示
 */
function setcost_MenuWidgetB103Tab2(){

	if(typeB103=='add')
	{
		Ext.getCmp('cost_FormulasetB103').items.items[1].setVisible(false);
		Ext.getCmp('cost_FormulasetB103').items.items[2].setVisible(false);
		Ext.getCmp('cost_FormulasetB103').items.items[3].setVisible(true);
		Ext.getCmp('cost_FormulasetB103').items.items[4].setVisible(true);
		Ext.getCmp('cost_FormulasetB103').items.items[5].setVisible(true);
	}else if(typeB103=='edit')
	{
		Ext.getCmp('cost_FormulasetB103').items.items[1].setVisible(true);
		Ext.getCmp('cost_FormulasetB103').items.items[2].setVisible(true);
		Ext.getCmp('cost_FormulasetB103').items.items[3].setVisible(true);
		Ext.getCmp('cost_FormulasetB103').items.items[4].setVisible(true);
		Ext.getCmp('cost_FormulasetB103').items.items[5].setVisible(true);
	}
}
/**
 * 设置商品群组下的按扭显示
 */
function setcost_MenuWidgetB103Tab3(){

	if(typeB103=='add')
	{
		Ext.getCmp('cost_FormulasetB103').items.items[1].setVisible(false);
		Ext.getCmp('cost_FormulasetB103').items.items[2].setVisible(false);
		Ext.getCmp('cost_FormulasetB103').items.items[3].setVisible(false);
		Ext.getCmp('cost_FormulasetB103').items.items[4].setVisible(false);
		Ext.getCmp('cost_FormulasetB103').items.items[5].setVisible(true);
	}else if(typeB103=='edit')
	{
		Ext.getCmp('cost_FormulasetB103').items.items[1].setVisible(true);
		Ext.getCmp('cost_FormulasetB103').items.items[2].setVisible(true);
		Ext.getCmp('cost_FormulasetB103').items.items[3].setVisible(false);
		Ext.getCmp('cost_FormulasetB103').items.items[4].setVisible(false);
		Ext.getCmp('cost_FormulasetB103').items.items[5].setVisible(true);
	}
}