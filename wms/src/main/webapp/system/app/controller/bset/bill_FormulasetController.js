/**
 * 模块名称：计费公式管理Controller
 * 模块编码：B101
 * 创建：chensr 
 */
var typeB101='';               //用于判断点击save按钮时是用于保存还是修改
var rowindexB101=0;
var remarkTmpB101='';                //存放取值策略的参数说明
Ext.define('cms.controller.bset.bill_FormulasetController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.bset.bill_FormulasetUI'],
	
	init:function(){
		this.control({
			//新增
			'bill_FormulasetUI button[name=detailAdd]':{
				click:this.detailAdd
			},
			//重新加载添加窗口
			'bill_FormulasetAddOrEditWindow button[name=add]':{
				click:this.add
			},
			
			//保存计费公式
			'bill_FormulasetAddOrEditWindow button[name=save]':{
				click:this.save
			},
			
			//用于判断附加值1和附加值2是否可以为空
			'bill_FormulasetAddOrEditWindow combo[id=appendConditionB101]':{
				select:this.appendValueIsNull
			},
			
			//判断项目编号是否唯一（用于选择填写编号判断）
			'bill_FormulasetAddOrEditWindow textfield[id=billingProjectB101]':{
				blur:this.billingProjectCheck
			},
			
			//判断项目编号是否唯一（用于选择货主下拉判断）
			'bill_FormulasetAddOrEditWindow combo[id=ownerNoB101]':{
				change:this.billingProjectCheck
			},
			
			//根据货主和仓别加载计费项目
			'bill_FormulasetUI combo[id=ownerNoUIB101]':{
				change:this.selectAndGetBillingProject
			},
			
			//根据货主、仓别和计费项目查找对应的计费公式
			'bill_FormulasetUI combo[id=billingProjectUIB101]':{
				select:this.getFormulasetWithCondition
			},
			
			//修改
			'bill_FormulasetUI button[name=detailEdit]':{
				click:this.detailEdit
			},
			
			//浏览
			'bill_FormulasetUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},
			
			//双击
			'bill_FormulasetUI grid[id=formulasetUIB101]':{
					itemdblclick:this.detailEdit
			},
			
			//上一条记录
			'bill_FormulasetAddOrEditWindow button[name=prev]':{
				click:this.prev
			},
			
			//下一条记录
			'bill_FormulasetAddOrEditWindow button[name=next]':{
				click:this.next
			},
			
			//关闭窗口
			'bill_FormulasetAddOrEditWindow button[name=close]':{
				click:this.colse
			},
			
			//关闭前事件
			'bill_FormulasetAddOrEditWindow':{
				beforeclose:this.bill_FormulasetAddOrEditWindowBeforeclose
			},//新增--》根据货主和仓别加载商品群组
			'bill_FormulasetAddOrEditWindow combo[id=ownerNoB101]':{
				change:this.windowGetArticleFamilyNo
			},
			//选择计费方式
			'bill_FormulasetAddOrEditWindow combo[id=billingFlagB101]':{
				select:this.seletcBillingFlag
			},
			//选择计费周期
			'bill_FormulasetAddOrEditWindow wms_DefFieldValCombo[id=billingCycleB101]':{
				select:this.billingCycleSelect
			},
			//周期为‘月’时校验结算日期
			'bill_FormulasetAddOrEditWindow form textfield[id=balanceDayB101_2]':{
				blur:this.balanceDayBlur
			},
			//验证截止日期
			'bill_FormulasetAddOrEditWindow form datefield[id=endDateB101]':{
				blur:this.endDateBlur
			},
			//根据项目类型 和计费单位，加载取值策略
			'bill_FormulasetAddOrEditWindow combo[id=billingTypeB101]':{
				select:this.valueFlagSelect
			},
			//根据项目类型 和计费单位，加载取值策略
			'bill_FormulasetAddOrEditWindow combo[id=billingUnitB101]':{
				select:this.valueFlagSelect
			},
			
			//获取参数说明
			'bill_FormulasetAddOrEditWindow combo[id=valueFlagB101]':{
				select:this.loadRemark
			},
			//导入
			'bill_FormulasetUI button[name=detailImport]':{
				click:this.detailImportClick
			}
		});
	},
	
	
	//获取参数说明
	loadRemark:function(){

		Ext.Ajax.request({
			url : 'bill_FormulasetAction_getRemark',
			params : {
				billingType:Ext.getCmp('billingTypeB101').getValue(),
				billingUnit:Ext.getCmp('billingUnitB101').getValue(),
				ruleId:Ext.getCmp('valueFlagB101').getValue()
			},
			success : function(response) {
				var res = Ext.decode(response.responseText);
		    	if(res){
		    		remarkTmpB101=res[0];
		    	}
			}
		});
	
	},
	
	//根据项目类型和计费单位加载取值方式
	valueFlagSelect:function(){
		if(!Ext.isEmpty(Ext.getCmp('billingTypeB101').getValue()) &&
		   !Ext.isEmpty(Ext.getCmp('billingUnitB101').getValue()))
		{
			var wheresql=
			{
					billingType:Ext.getCmp('billingTypeB101').getValue(),
					billingUnit:Ext.getCmp('billingUnitB101').getValue()
			};
			Ext.apply(Ext.getCmp('valueFlagB101').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('valueFlagB101').getStore().removeAll();
			Ext.getCmp('valueFlagB101').setValue(null);
			Ext.getCmp('valueFlagB101').getStore().load();
		}
	},
	//根据货主加载商品群组
	windowGetArticleFamilyNo:function(){
		var strDetail1 = [];
		var d1={
			columnId:'a.owner_no',
			value:Ext.getCmp('ownerNoB101').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB101').getValue())){
			strDetail1.push(d1);
		}
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				str  : jsonDetail
		};
		Ext.getCmp('articleFamilyNo').setValue('');
		Ext.apply(Ext.getCmp('articleFamilyNo').getStore().proxy.extraParams,str);
		Ext.getCmp('articleFamilyNo').getStore().removeAll();
		Ext.getCmp('articleFamilyNo').getStore().load();
	},
	//选择计费方式
	seletcBillingFlag:function(){
		if(Ext.getCmp('billingFlagB101').getValue()=='1'){
//			commonSetMsterReadOnlyByArray(
//					new Array('valueFlagB101'),true);
//			commonSetMsterReadOnlyByArray(
//					new Array('valueB101'),false);
//			Ext.getCmp('valueFlagB101').setValue('');
//			
			Ext.getCmp('valueB101').setDisabled(false);
			Ext.getCmp('billingUnitB101').setDisabled(true);
			Ext.getCmp('valueFlagB101').setDisabled(true);
			Ext.getCmp('unitPriceB101').setDisabled(true);
			Ext.getCmp('valueFlagB101').setValue('');
			Ext.getCmp('unitPriceB101').setValue('1');
			
		}else{
//			commonSetMsterReadOnlyByArray(
//					new Array('valueB101'),true);
//			commonSetMsterReadOnlyByArray(
//					new Array('valueFlagB101'),false);
//			Ext.getCmp('valueB101').setValue('');
			
			Ext.getCmp('billingUnitB101').setDisabled(false);
			Ext.getCmp('valueFlagB101').setDisabled(false);
			Ext.getCmp('unitPriceB101').setDisabled(false);
			Ext.getCmp('valueB101').setDisabled(true);
			Ext.getCmp('valueB101').setValue('');
			Ext.getCmp('unitPriceB101').setValue('');
		}
	},
	//选择计费周期
	billingCycleSelect:function(){
//		debugger;
		if(Ext.getCmp('billingCycleB101').getValue()=='2'){	
			Ext.getCmp('balanceDayB101_1').setVisible(true);
			Ext.getCmp('balanceDayB101_2').setVisible(false);

		}else if(Ext.getCmp('billingCycleB101').getValue()=='3'){
			Ext.getCmp('balanceDayB101_2').setVisible(true);
			Ext.getCmp('balanceDayB101_1').setVisible(false);			
		}else{
			Ext.getCmp('balanceDayB101_1').setVisible(false);
			Ext.getCmp('balanceDayB101_2').setVisible(false);	
		}
	},
	//周期为‘月’时校验结算日期
	balanceDayBlur:function(){
		if(Ext.getCmp('billingCycleB101').getValue()=='3'){
			var balanceDay=Ext.getCmp('balanceDayB101_2').getValue();
			if(balanceDay>31){
				Ext.example.msg($i18n.prompt, $i18n_prompt.balanceDayCannotMoreThan31);
				Ext.getCmp('balanceDayB101_2').setValue('');
			}
		}
	},
	//校验截止日期是否小于今天
	endDateBlur:function(){
		if(Ext.util.Format.date(Ext.getCmp('endDateB101').getValue(), 'Y-m-d')
				<Ext.Date.format(new Date(),'Y-m-d')){
			Ext.example.msg($i18n.prompt, $i18n_prompt.endDateCannotLessThanToday);
		}
	},
	//调用新增窗口
	detailAdd:function(){
		Ext.create('cms.view.bset.window.bill_FormulasetAddOrEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		addCommMenu5('bill_FormulasetB101');
		typeB101='add';
		Ext.getCmp('statusB101').setValue('0');
		Ext.getCmp('ownerNoB101').focus(false,5);
		Ext.getCmp('balanceDayB101_1').setVisible(false);
		Ext.getCmp('balanceDayB101_2').setVisible(false);
	},
	
	//实现新增功能（清空窗口的内容）
	add:function(){
		Ext.getCmp('IdFormB101').getForm().reset();
		bindEnterSkip($('IdFormB101'));//调用键盘处理方法
	},
	
	//判断附加值1和附加值2是否可以为空
	appendValueIsNull:function(){
		var condition =Ext.getCmp('appendConditionB101').getValue();
		if(condition=='3')
		{
			Ext.getCmp('appendValue1B101').setDisabled(false);
			Ext.getCmp('appendValue2B101').setDisabled(false);
			
		}else if (condition=='1'||condition=='2')
		{
			Ext.getCmp('appendValue1B101').setDisabled(false);
			Ext.getCmp('appendValue2B101').setDisabled(true);
		}else
		{
			Ext.getCmp('appendValue1B101').setDisabled(true);
			Ext.getCmp('appendValue2B101').setDisabled(true);
		}
		
		Ext.getCmp('appendValue1B101').setValue('');
		Ext.getCmp('appendValue2B101').setValue('');
	},
	
	//判断项目编号是否唯一
	billingProjectCheck: function(){
		if(typeB101=='add' && Ext.getCmp('billingProjectB101').getValue()!="" && Ext.getCmp('billingProjectB101').getValue()!=null
				&& Ext.getCmp('ownerNoB101').getValue()!="" && Ext.getCmp('ownerNoB101').getValue()!=null){
			Ext.Ajax.request({
				url : 'bill_FormulasetAction_billingProjectCheck',
				params : {
					str:Ext.getCmp('billingProjectB101').getValue(),
					ownerNo:Ext.getCmp('ownerNoB101').getValue()
				},
				success : function(response) {
					var res = Ext.decode(response.responseText);
			    	if(res!=''){
			    		Ext.example.msg('提示',"项目编号已经存在");
			    		Ext.getCmp('billingProjectB101').setValue(null);
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
	//	Ext.getCmp('warehouseUIB101').setValue(Ext.get('warehouseNo').getValue());
		//获取计费项目
		var strDetail1 = [];
		var d1={
			columnId:'t1.owner_no',
			value:Ext.getCmp('ownerNoUIB101').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoUIB101').getValue()) &&
				Ext.getCmp('ownerNoUIB101').getValue()!='ALL'){
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
		if(!Ext.isEmpty(Ext.getCmp('ownerNoUIB101').getValue()) &&
				Ext.getCmp('ownerNoUIB101').getValue()!='ALL'){
			Ext.apply(Ext.getCmp('billingProjectUIB101').getStore().proxy.extraParams,str);
			Ext.getCmp('billingProjectUIB101').getStore().removeAll();
			Ext.getCmp('billingProjectUIB101').getStore().load();
		}else{
			Ext.getCmp('billingProjectUIB101').setValue(null);
			Ext.getCmp('billingProjectUIB101').getStore().removeAll();
		}
		
		//查询计费公式
		Ext.apply(Ext.getCmp('formulasetUIB101').getStore().proxy.extraParams,str);
		Ext.getCmp('formulasetUIB101').getStore().removeAll();
		Ext.getCmp('formulasetUIB101').getStore().load();	
	},
	
	//根据货主、仓别和计费项目查找计费公式
	getFormulasetWithCondition:function(){	
		var strDetail1 = [];
		var d1={
			columnId:'t1.owner_no',
			value:Ext.getCmp('ownerNoUIB101').getValue()
		};
		strDetail1.push(d1);
		
		var d2={
				columnId:'t1.warehouse_no',
				value:Ext.get('warehouseNo').getValue()
		};
		strDetail1.push(d2);
		
		var d3={
				columnId:'t1.billing_project',
				value:Ext.getCmp('billingProjectUIB101').getValue()
		};
		strDetail1.push(d3);
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				str  : jsonDetail
		};
		Ext.apply(Ext.getCmp('formulasetUIB101').getStore().proxy.extraParams,str);
		Ext.getCmp('formulasetUIB101').getStore().removeAll();
		Ext.getCmp('formulasetUIB101').getStore().load();		
	},
	
	//调用编辑窗口
	detailEdit:function(){
		var data = Ext.getCmp('formulasetUIB101').getSelectionModel().getSelection();
		
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{		
			Ext.create('cms.view.bset.window.bill_FormulasetAddOrEditWindow',{
				title:$i18n.titleupdate
		    }).show();
			this.loadDataB101();
			commonSetMsterReadOnlyByArray(
					new Array('ownerNoB101','billingProjectB101','billingTypeB101','articleFamilyNo'),true);
			commonMenu5PrevOrNext('bill_FormulasetB101','formulasetUIB101',0);
			updateCommMenu5('bill_FormulasetB101');
			typeB101='edit';
		}
	},
	
	/**
	 * 浏览
	 */
	detailBrowse:function(){
		var objBrowseData = Ext.getCmp('formulasetUIB101').getSelectionModel().getSelection();
		if (objBrowseData.length != 0) {
			Ext.create('cms.view.bset.window.bill_FormulasetAddOrEditWindow',{
				title:$i18n.titlebrowse
		    }).show();
			typeB101='browse';
			browseCommMenu5('bill_FormulasetB101');
			commonMenu5PrevOrNext('bill_FormulasetB101','formulasetUIB101',0);
			commonSetFieldReadOnly('IdFormB101',true);
			this.loadDataB101();
        }else{
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
        }
	},	
	
	//实现上一页功能
	prev:function(){		
		commonMenu5PrevOrNext('bill_FormulasetB101','formulasetUIB101',-1);
		this.loadDataB101();
	},
	
	//实现下一页功能
	next:function(){
		commonMenu5PrevOrNext('bill_FormulasetB101','formulasetUIB101',1);
		this.loadDataB101();
	},
	
	//关闭窗口
	colse:function(){
		Ext.getCmp('bill_FormulasetAddOrEditWindow').close();
	},
	//加载修改页面的数据
	loadDataB101:function(){
//		debugger
		var cust=Ext.getCmp('formulasetUIB101').getSelectionModel().getSelection();
		if(cust.length>0)
		{
			Ext.getCmp('ownerNoB101').setValue(cust[0].data.ownerNo);
			Ext.getCmp('billingProjectB101').setValue(cust[0].data.billingProject);
			Ext.getCmp('projectNameB101').setValue(cust[0].data.projectName);
			Ext.getCmp('billingTypeB101').setValue(cust[0].data.billingType);	
			Ext.getCmp('articleFamilyNo').setValue(cust[0].data.familyNo);	
			Ext.getCmp('billingCycleB101').setValue(cust[0].data.billingCycle);
			Ext.getCmp('billingFlagB101').setValue(cust[0].data.billingFlag);
			Ext.getCmp('billingFlagB101').fireEvent("select");
			Ext.getCmp('billingUnitB101').setValue(cust[0].data.billingUnit);
			Ext.getCmp('billingUnitB101').fireEvent("select");
			Ext.getCmp('unitPriceB101').setValue(cust[0].data.unitPrice);
			if(cust[0].data.billingCycle=='2'){
				Ext.getCmp('balanceDayB101_1').setValue(cust[0].data.balanceDay);
				Ext.getCmp('balanceDayB101_1').fireEvent("select");	
				Ext.getCmp('balanceDayB101_2').setVisible(false);
				Ext.getCmp('balanceDayB101_1').setVisible(true);
			}else if(cust[0].data.billingCycle=='3'){
				Ext.getCmp('balanceDayB101_2').setValue(cust[0].data.balanceDayText);
				Ext.getCmp('balanceDayB101_1').setVisible(false);
				Ext.getCmp('balanceDayB101_2').setVisible(true);

			}else{
				Ext.getCmp('balanceDayB101_1').setValue('');
				Ext.getCmp('balanceDayB101_2').setValue('');
				Ext.getCmp('balanceDayB101_1').setVisible(false);
				Ext.getCmp('balanceDayB101_2').setVisible(false);
			}
			Ext.getCmp('endDateB101').setValue(cust[0].data.endDateText);
			Ext.getCmp('appendConditionB101').setValue(cust[0].data.appendCondition.toString());
			Ext.getCmp('appendConditionB101').fireEvent("select");
			Ext.getCmp('appendValue1B101').setValue(cust[0].data.appendValue1);
			Ext.getCmp('appendValue2B101').setValue(cust[0].data.appendValue2);
			Ext.getCmp('remarkB101').setValue(cust[0].data.remark);	
			Ext.getCmp('statusB101').setValue(cust[0].data.status);	
			Ext.getCmp('valueFlagB101').setValue(cust[0].data.valueFlag);
			Ext.getCmp('valueB101').setValue(cust[0].data.fixedValue);
		}
	},
	//根据优惠方式，判断值1、值2和优惠代码是否可以为空
	checkValue1OrValue2IsNull: function(){	
		var flag =Ext.getCmp('appendConditionB101').getValue();
		if (flag=='1' || flag=='2')
		{
			if(Ext.isEmpty(Ext.getCmp('appendValue1B101').getValue()))
			{
				Ext.example.msg('提示',"值1不能为空！");
				return false;
			}
		}else if(flag=='3')
		{
			if(Ext.isEmpty(Ext.getCmp('appendValue1B101').getValue()))
			{
				Ext.example.msg('提示',"值1不能为空！");
				return false;
			}
			
			if(Ext.isEmpty(Ext.getCmp('appendValue2B101').getValue()))
			{
				Ext.example.msg('提示',"值2不能为空！");
				return false;
			}
		}
		return true;
	},
	saveFormulaset:function(){
		
		if(!commonCheckIsInputAll('IdFormB101')){
			return;
		}
		
		if(!this.checkValue1OrValue2IsNull())
		{
			return;
		}	
		if(Ext.getCmp('IdFormB101').getForm().isValid()){
			var cust=Ext.getCmp('formulasetUIB101').getSelectionModel().getSelection()[0];
			var balanceDay=null;
			if(Ext.getCmp('billingCycleB101').getValue()=='2'){
				balanceDay=Ext.getCmp('balanceDayB101_1').getValue()==undefined?null:Ext.getCmp('balanceDayB101_1').getValue();
			}else if(Ext.getCmp('billingCycleB101').getValue()=='3'){
				balanceDay=Ext.getCmp('balanceDayB101_2').getValue()==undefined?null:Ext.getCmp('balanceDayB101_2').getValue();
			}
			bill_FormulasetStr={
				id:{
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					ownerNo:Ext.getCmp('ownerNoB101').getValue(),
					billingProject:Ext.getCmp('billingProjectB101').getValue(),
					billingType:Ext.getCmp('billingTypeB101').getValue()
				},	
				status:Ext.getCmp('statusB101').getValue(),
				fixedValue:Ext.getCmp('valueB101').getValue(),
				valueFlag:Ext.getCmp('valueFlagB101').getValue(),
				projectName:Ext.getCmp('projectNameB101').getValue(),
				billingCycle:Ext.getCmp('billingCycleB101').getValue(),
				billingFlag:Ext.getCmp('billingFlagB101').getValue(),
				billingUnit:Ext.getCmp('billingUnitB101').getValue(),
				unitPrice:Ext.getCmp('unitPriceB101').getValue(),
				endDate:Ext.getCmp('endDateB101').getValue()==undefined?null:Ext.util.Format.date(Ext.getCmp('endDateB101').getValue(), 'Y-m-d'),
			    balanceDay:balanceDay,
			    appendCondition:Ext.getCmp('appendConditionB101').getValue(),					
				appendValue1:Ext.getCmp('appendValue1B101').getValue(),
				appendValue2:Ext.getCmp('appendValue2B101').getValue(),
				remark:Ext.getCmp('remarkB101').getValue() ,
				rgstDate:typeB101=='add'?new Date():cust.data.rgstDate.time,
				rgstName:typeB101=='add'?Ext.get('workerNo').getValue():cust.data.rgstName,
				updtDate:typeB101=='add'?'':new Date(),
				updtName:typeB101=='add'?'':Ext.get('workerNo').getValue()
			};								
			Ext.Ajax.request({
				url:'bill_FormulasetAction_saveFormulaset',
				method:'post',
				params:{
					str:Ext.encode(bill_FormulasetStr)
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){					
						Ext.example.msg($i18n.prompt,data.msg);
					}else{
						Ext.example.msg($i18n.prompt,data.msg);
					}
				}
			});
			if(!Ext.isEmpty(Ext.getCmp('articleFamilyNo').getValue())){
				bill_FamilyUnitPriceStr={
						id:{
							warehouseNo:Ext.get('warehouseNo').getValue(),
							ownerNo:Ext.getCmp('ownerNoB101').getValue(),
							familyNo:Ext.getCmp('articleFamilyNo').getValue(),
							billingProject:Ext.getCmp('billingProjectB101').getValue(),
							enterpriseNo:Ext.get('enterpriseNo').getValue(),
						},	
						
						billingUnit:Ext.getCmp('billingUnitB101').getValue(),
						unitPrice:Ext.getCmp('unitPriceB101').getValue(),
						appendCondition:Ext.getCmp('appendConditionB101').getValue(),					
						appendValue1:Ext.getCmp('appendValue1B101').getValue(),
						appendValue2:Ext.getCmp('appendValue2B101').getValue(),
						rgstDate:typeB101=='add'?new Date():cust.data.rgstDate.time,
						rgstName:typeB101=='add'?Ext.get('workerNo').getValue():cust.data.rgstName,
						updtDate:typeB101=='add'?'':new Date(),
						updtName:typeB101=='add'?'':Ext.get('workerNo').getValue()
					};
				Ext.Ajax.request({
					url:'bill_FormulasetAction_saveFamilyUnitPrice',
					method:'post',
					params:{
						str:Ext.encode(bill_FamilyUnitPriceStr)
					},
					success:function(response){
						
					}
				});
			}
			
		}
	},
	bill_FormulasetAddOrEditWindowBeforeclose:function(){
		Ext.getCmp('formulasetUIB101').getStore().load();
	},
	//导入
	detailImportClick:function(){
		Ext.create('cms.view.bset.window.billFormulasetUploadWindow',
		{
			title:'上传'
		}).show();
	},
	
});

