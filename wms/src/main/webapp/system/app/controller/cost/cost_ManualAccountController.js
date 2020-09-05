/**
 * 模块名称：手工出账
 * 模块编码：B603
 * 创建：hcx 
 */
var menuType='1';
Ext.define('cms.controller.cost.cost_ManualAccountController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.cost.cost_ManualAccountUI'],
	init:function(){
		this.control({
			//查询
			'cost_ManualAccountUI button[name=btnQueryB603]':{
				click:this.btnQueryB603Click
			},//货主选择
			'cost_ManualAccountUI combo[id=ownerNoB603]':{
				change:this.selectAndGetBillingProject
			},//科目代码选择
			'cost_ManualAccountUI combo[id=accountB603]':{
				change:this.accountB603Change
			},//开始日期离开
			'cost_ManualAccountUI datefield[id=dtBeginDateExpB603]':{
				blur:this.dtBeginDateExpB603Blur
			},//结束日期
			'cost_ManualAccountUI datefield[id=dtEndDateExpB603]':{
				blur:this.dtEndDateExpB603Blur
			},//刷新
			'cost_ManualAccountUI button[id=refreshB603_1]':{
				click:this.refreshClick
			},//查找
			'cost_ManualAccountUI button[id=queryB603_1]':{
				click:this.queryB603_1Query
			},//导出
			'cost_ManualAccountUI button[id=exportB603_1]':{
				click:this.exportClick
			},//生成账单
			'cost_ManualAccountUI button[id=retryB603_1]':{
				click:this.retryB603_1Click
			},//查看清单》生成日期选择
			'cost_ExpensesListWindow datefield[id=builDdateB403]':{
				change:this.builDdateB403Select
			},//查看清单》来源单号选择
			'remoteCombo[id=sourceNoB403]':{
				beforequery:this.sourceNoBeforeQuery,
				select:this.sourceNoSelect
			},//查看清单》刷新
			'cost_ExpensesListWindow button[id=refreshB403]':{
				click:this.refreshB403Click
			},//查看清单》导出
			'cost_ExpensesListWindow button[id=exportB403]':{
				click:this.exportB403Click
			}
		});
	},
	//初始化界面
	initializtion:function(){
		
		var strDetail1 = [];
		var d1={
				columnId:'t1.status',
				value:'10'
		};
		strDetail1.push(d1);
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				strQuery  : jsonDetail
		};
		Ext.apply(Ext.getCmp('costOtherListB603').getStore().proxy.extraParams,str);
		Ext.getCmp('costOtherListB603').getStore().removeAll();
		Ext.getCmp('costOtherListB603').getStore().load();
		disableButtonFunc(1,'#retryB603_1',$i18n.createBill);
	},
	//查询
	btnQueryB603Click:function(){		
        //加载费用明细信息列表
		costCostListSelect();
		//加载杂项费用信息列表
		costOtherListSelect();
		//判断生成账单按钮是否可用
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB603').getValue())
				&& Ext.getCmp('ownerNoB603').getValue() != 'ALL'
				&& !Ext.isEmpty(Ext.getCmp('dtEndDateExpB603').getValue())
				&& !Ext.isEmpty(Ext.getCmp('dtBeginDateExpB603').getValue())){
			disableButtonFunc(0,'#retryB603_1',$i18n.createBill);
		}else{
			disableButtonFunc(1,'#retryB603_1',$i18n.createBill);
		}
	},
	//货主选择
	selectAndGetBillingProject: function(){		
		var strDetail1 = [];
		var d1={
			columnId:'a.owner_no',
			value:Ext.getCmp('ownerNoB603').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB603').getValue())&&
				Ext.getCmp('ownerNoB603').getValue()!='ALL'){
			strDetail1.push(d1);
		}
		var d2={
				columnId:'a.warehouse_no',
				value:Ext.get('warehouseNo').getValue()
		};
		strDetail1.push(d2);
		var d3={
				columnId:'a.status',
				value:'10'
		};
		strDetail1.push(d3);
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				strQuery  : jsonDetail
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB603').getValue())){
			Ext.apply(Ext.getCmp('accountB603').getStore().proxy.extraParams,str);
			Ext.getCmp('accountB603').setValue('');
			Ext.getCmp('accountB603').getStore().removeAll();
			Ext.getCmp('accountB603').getStore().load();
			Ext.apply(Ext.getCmp('billingProjectB603').getStore().proxy.extraParams,str);
			Ext.getCmp('billingProjectB603').setValue('');
			Ext.getCmp('billingProjectB603').getStore().removeAll();
			Ext.getCmp('billingProjectB603').getStore().load();
		}else{
			Ext.getCmp('accountB603').setValue(null);
			Ext.getCmp('accountB603').getStore().removeAll();
			Ext.getCmp('billingProjectB603').setValue(null);
			Ext.getCmp('billingProjectB603').getStore().removeAll();
		}
	},
	//科目代码选择
	accountB603Change: function(){
		var strDetail1 = [];
		var d1={
			columnId:'a.owner_no',
			value:Ext.getCmp('ownerNoB603').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB603').getValue())&&
				Ext.getCmp('ownerNoB603').getValue()!='ALL'){
			strDetail1.push(d1);
		}
		if(!Ext.isEmpty(Ext.getCmp('accountB603').getValue()))
		{
			var strDtl={
					columnId:'e.account_no',
					value:Ext.getCmp('accountB603').getValue()
				};
			strDetail1.push(strDtl);
		}
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				strQuery  : jsonDetail
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB603').getValue())){
			Ext.apply(Ext.getCmp('billingProjectB603').getStore().proxy.extraParams,str);
			Ext.getCmp('billingProjectB603').setValue('');
			Ext.getCmp('billingProjectB603').getStore().removeAll();
			Ext.getCmp('billingProjectB603').getStore().load();
			Ext.getCmp("dtBeginDateExpB603").setValue('');
			Ext.getCmp("dtEndDateExpB603").setValue('');
		}
	},	
	//开始日期选择
	dtBeginDateExpB603Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtBeginDateExpB603').getValue()))
		{
			Ext.getCmp('dtBeginDateExpB603').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB603').getValue()))
		{
			if(Ext.getCmp('dtBeginDateExpB603').getValue()>new Date()){
				Ext.example.msg($i18n.prompt,$i18n_prompt.beginDateCanNotMoreThanDoday);
				Ext.getCmp('dtBeginDateExpB603').setValue('');
				return;
			}
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.beginDateCanNotMoreThanEndDate);
				Ext.getCmp('dtBeginDateExpB603').setValue('');
			}
		}
	},
	//结束日期选择
	dtEndDateExpB603Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtEndDateExpB603').getValue()))
		{
			Ext.getCmp('dtEndDateExpB603').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtEndDateExpB603').getValue()))
		{
			if(Ext.getCmp('dtEndDateExpB603').getValue()>new Date()){
				Ext.example.msg($i18n.prompt,$i18n_prompt.endDateCanNotMoreThanDoday);
				Ext.getCmp('dtEndDateExpB603').setValue('');
				return;
			}
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.EndDateCanNotLessThanbeginDate);
				Ext.getCmp('dtEndDateExpB603').setValue('');
			}
		}
	},	
	//开始结束日期校验
	compareExpDate:function(){
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB603').getValue()) 
				&& !Ext.isEmpty(Ext.getCmp('dtEndDateExpB603').getValue()))
		{
			if(Ext.Date.format(Ext.getCmp('dtBeginDateExpB603').getValue(),'Ymd')>Ext.Date.format(Ext.getCmp('dtEndDateExpB603').getValue(),'Ymd'))
			{
				return false;
			}
		}
		return true;
	},
	//刷新
	refreshClick:function(){
		Ext.getCmp("ownerNoB603").setValue('');
		Ext.getCmp("billingProjectB603").setValue('');
		Ext.getCmp("dtBeginDateExpB603").setValue('');
		Ext.getCmp("dtEndDateExpB603").setValue('');
		//加载费用明细信息列表
		costCostListSelect();
		//加载杂项费用信息列表
		costOtherListSelect();
		disableButtonFunc(1,'#retryB603_1',$i18n.createBill);
	},
	//查找
	queryB603_1Query:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		Ext.getCmp('ownerNoB603').setValue(null);
		Ext.getCmp('accountB603').setValue(null);
		Ext.getCmp('billingProjectB603').setValue(null);
		Ext.getCmp('dtBeginDateExpB603').setValue(null);
		Ext.getCmp('dtEndDateExpB603').setValue(null);
		queryModuleId='B603';
		queryGrid='grid_Exp_B603';
	},
	//导出
	exportClick:function(){
		commExport('grid_Exp_B603');
	},
	//生成账单
	retryB603_1Click:function(){
		if(Ext.isEmpty(Ext.getCmp('ownerNoB603').getValue())
				|| Ext.getCmp('ownerNoB603').getValue()=='ALL')
		{
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiceOwnerNo);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('dtBeginDateExpB603').getValue()))
		{
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiceBeginDate);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('dtEndDateExpB603').getValue()))
		{
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiceEndDate);
			return;
		}
		 var gridcount=Ext.getCmp('grid_Exp_B603').getSelectionModel().getSelection();
		 var gridcount2=Ext.getCmp('costOtherListB603').getSelectionModel().getSelection();
		 if(gridcount.length==0 && gridcount2.length==0){
			 Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiseCost);
			 return;
		 }
		 Ext.Msg.confirm($i18n_prompt.prompt,$i18n_prompt.surecreateBill,function(button,text)
		{
			 if(button=='yes'){
				 var objdata=Ext.getCmp('grid_Exp_B603').getSelectionModel().getSelection();
				 var detail=[];
				 if(objdata.length!=0){
					 for(var i=0; i<objdata.length; i++){
						 var no={
								ownerNo:objdata[i].get('ownerNo'),
								billingProject:objdata[i].get('billingProject'),
								buildDate:objdata[i].get('buildDate'),
								beginDate:objdata[i].get('beginDate'),
								endDate:objdata[i].get('endDate'),
								beginDateText:objdata[i].get('beginDateText'),
								endDateText:objdata[i].get('endDateText')
							};
							detail.push(no);
					 }
						
				   }
				var objdata2=Ext.getCmp('costOtherListB603').getSelectionModel().getSelection();
			    var detail2=[];
				if(objdata2.length!=0){
					for(var j=0; j<objdata2.length; j++){
						var no2={
								ownerNo:objdata2[j].get('ownerNo'),
								costNo:objdata2[j].get('costNo'),
								costDateText:objdata2[j].get('costDateText')
							};
							detail2.push(no2);
					   }
				   }
				 var detail3=[];
				 var no3={
							ownerNo:Ext.getCmp('ownerNoB603').getValue(),
							buildDate:new Date(),
							beginDate:Ext.util.Format.date(Ext.getCmp('dtBeginDateExpB603').getValue(), 'Y-m-d'),
							endDate:Ext.util.Format.date(Ext.getCmp('dtEndDateExpB603').getValue(), 'Y-m-d'),
							accountNo:'',
							checkNo:'',
							flag:'0',
							discountFlag:Ext.getCmp('rdoCheckTypeB603').getValue().rd,
							rgstName:Ext.get('workerNo').getValue()
						};
						detail3.push(no3);
			       var join1=Ext.encode(detail);
				   var join2=Ext.encode(detail2);
				   var join3=Ext.encode(detail3);
				   var params = {
						str:join1,
						strWheresql:join2,
						strJoin:join3
				   };
				   Ext.Ajax.request({
						method:'POST',
						url:'cost_ManualAccountAction_saveManualAccount',
						timeout:1800000,
						params:params,
						success:function(response){
							var data = Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n_prompt.prompt,data.msg);
			            		Ext.getCmp('grid_Exp_B603').getStore().reload();
			            		Ext.getCmp('costOtherListB603').getStore().reload();

							}else{
								Ext.example.msg($i18n_prompt.prompt,data.msg+data.obj);
							}				
						}
				   });	
			 }
		});
	},
	builDdateB403Select:function(){
		
		var detail = [];
		var a={
				columnId:'a.owner_no',
				value:Ext.getCmp('ownerNoB403_2').getValue()
			};
		detail.push(a);
		var b={
				columnId:'a.billing_project',
				value:Ext.getCmp('billingProjectB403_2').getValue()
			};
		detail.push(b);
		var jsonStr = Ext.encode(detail);
		if(!Ext.isEmpty(Ext.getCmp('builDdateB403').getValue())){
			var buildDate=Ext.Date.format( Ext.getCmp('builDdateB403').getValue(),'Y-m-d');
		}else{
			var buildDate=null;
		}
		var wheresql = {
			strQuery : jsonStr,
			buildDate:buildDate
		};
		Ext.apply(Ext.getCmp('gridExpensesListB403').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('gridExpensesListB403').getStore().removeAll();
		Ext.getCmp('gridExpensesListB403').getStore().load();
	},
	//查看清单》来源单号选择前加载
	sourceNoBeforeQuery:function(){
 		var listDetail1  =  [];
		var strDtl = {
			columnId:'a.owner_no',
			value:Ext.getCmp("ownerNoB403_2").getValue()
		};
		listDetail1.push(strDtl);
		if(!Ext.isEmpty(Ext.getCmp('billingProjectB403_2').getValue())){
			var strDt2 = {
					columnId:'a.billing_project',
					value:Ext.getCmp("billingProjectB403_2").getValue()
				};
				listDetail1.push(strDt2);
		}
		if(!Ext.isEmpty(Ext.getCmp('builDdateB403').getValue())){
			var buildDate=Ext.Date.format( Ext.getCmp('builDdateB403').getValue(),'Y-m-d');
		}else{
			var buildDate=null;
		}
		var params={
			strWheresql:Ext.getCmp("sourceNoB403").getValue(),   
			strQuery:Ext.encode(listDetail1),
			buildDate:buildDate
		};
		Ext.apply(Ext.getCmp('sourceNoB403').getStore().proxy.extraParams,params);
		Ext.getCmp('sourceNoB403').getStore().removeAll();
		Ext.getCmp('sourceNoB403').getStore().load();
 	 },
 	//查看清单》来源单号选择
 	sourceNoSelect:function(){
 		var listDetail1  =  [];
		var strDtl = {
			columnId:'a.owner_no',
			value:Ext.getCmp("ownerNoB403_2").getValue()
		};
		listDetail1.push(strDtl);
		if(!Ext.isEmpty(Ext.getCmp('billingProjectB403_2').getValue())){
			var strDt2 = {
					columnId:'a.billing_project',
					value:Ext.getCmp("billingProjectB403_2").getValue()
				};
				listDetail1.push(strDt2);
		}
		if(!Ext.isEmpty(Ext.getCmp('builDdateB403').getValue())){
			var buildDate=Ext.Date.format( Ext.getCmp('builDdateB403').getValue(),'Y-m-d');
		}else{
			var buildDate=null;
		}
		if(!Ext.isEmpty(Ext.getCmp('sourceNoB403').getValue())){
			var strDt3 = {
					columnId:'a.source_no',
					value:Ext.getCmp("sourceNoB403").getValue()
				};
				listDetail1.push(strDt3);
		}
		var params={
			strQuery:Ext.encode(listDetail1),
			buildDate:buildDate
		};
		Ext.apply(Ext.getCmp('gridExpensesListB403').getStore().proxy.extraParams,params);
		Ext.getCmp('gridExpensesListB403').getStore().removeAll();
		Ext.getCmp('gridExpensesListB403').getStore().load();

	 },
	//查看清单》刷新
	 refreshB403Click:function(){
			var listDetail = [];
			var a={
					columnId:'a.owner_no',
					value:Ext.getCmp('ownerNoB403_2').getValue()
				};
			listDetail.push(a);
			var b={
					columnId:'a.billing_project',
					value:Ext.getCmp('billingProjectB403_2').getValue()
				};
			listDetail.push(b);
			var c={
					columnId:'a.status',
					value:'13'
				};
			listDetail.push(c);
			var strJson = Ext.encode(listDetail);
			var wheresql = {
				strQuery : strJson,
				buildDate:null
			};
			Ext.getCmp('builDdateB403').setValue('');
			Ext.getCmp('sourceNoB403').setValue('');
			Ext.apply(Ext.getCmp('gridExpensesListB403').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('gridExpensesListB403').getStore().removeAll();
			Ext.getCmp('gridExpensesListB403').getStore().load();
	 },
	//查看清单》导出
	 exportB403Click:function(){
			commExport('gridExpensesListB403');
	 }
});
//加载费用明细信息列表
function costCostListSelect(){
	var listDetail = [];
	if(!Ext.isEmpty(Ext.getCmp('ownerNoB603').getValue()))
	{
		var strDtl={
				columnId:'a.owner_no',
				value:Ext.getCmp('ownerNoB603').getValue()
			};
		listDetail.push(strDtl);
	}
	if(!Ext.isEmpty(Ext.getCmp('accountB603').getValue()))
	{
			var strDtl = {
					columnId:'e.account_no',
					value:Ext.getCmp("accountB603").getValue()
				};
			listDetail.push(strDtl);
	}
	if(!Ext.isEmpty(Ext.getCmp('billingProjectB603').getValue()))
	{
		var strDtl={
				columnId:'a.billing_project',
				value:Ext.getCmp('billingProjectB603').getValue()
			};
		listDetail.push(strDtl);
	}
	if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB603').getValue()))
	{
		var strDtl={
				columnId:'a.build_date',
				condition:4,
				valueType:3,
				value:Ext.Date.format(Ext.getCmp('dtBeginDateExpB603').getValue(),'Y-m-d')
			};
		listDetail.push(strDtl);
	}
	if(!Ext.isEmpty(Ext.getCmp('dtEndDateExpB603').getValue()))
	{
		var strDtl={
				columnId:'a.build_date',
				condition:5,
				valueType:3,
				value:Ext.Date.format(Ext.getCmp('dtEndDateExpB603').getValue(),'Y-m-d')
			};
		listDetail.push(strDtl);
	}
//	var strDtl={
//			columnId:'a.status',
//			value:'10'
//		};
//	listDetail.push(strDtl);
	var strJson = Ext.encode(listDetail);
	var wheresql = {
		strQuery : strJson,
		strMenuType:menuType
	};
	//费用明细信息列表
	Ext.apply(Ext.getCmp('grid_Exp_B603').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('grid_Exp_B603').getStore().removeAll();
	Ext.getCmp('grid_Exp_B603').getStore().load();	
}
//加载杂项费用信息列表
function costOtherListSelect(){
	var listDetail = [];
	if(!Ext.isEmpty(Ext.getCmp('ownerNoB603').getValue()))
	{
		var strDtl={
				columnId:'t1.owner_no',
				value:Ext.getCmp('ownerNoB603').getValue()
			};
		listDetail.push(strDtl);
	}
	var strDtl={
			columnId:'t1.status',
			value:'10'
		};
	listDetail.push(strDtl);
	if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB603').getValue()))
	{
		var strDtl={
				columnId:'t1.cost_date',
				condition:4,
				valueType:3,
				value:Ext.Date.format(Ext.getCmp('dtBeginDateExpB603').getValue(),'Y-m-d')
			};
		listDetail.push(strDtl);
	}
	if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB603').getValue()))
	{
		var strDtl={
				columnId:'t1.cost_date',
				condition:5,
				valueType:3,
				value:Ext.Date.format(Ext.getCmp('dtEndDateExpB603').getValue(),'Y-m-d')
			};
		listDetail.push(strDtl);
	}	
	var strJson = Ext.encode(listDetail);
	var wheresql = {
		strQuery : strJson
	};
	//杂项费用信息列表
	Ext.apply(Ext.getCmp('costOtherListB603').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('costOtherListB603').getStore().removeAll();
	Ext.getCmp('costOtherListB603').getStore().load();	
}
