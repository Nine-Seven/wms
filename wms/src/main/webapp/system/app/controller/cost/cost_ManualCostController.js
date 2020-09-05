/**
 * 模块名称：手工生成费用明细
 * 模块编码：B903
 * 创建：hcx 
 */
var menuType='0';
Ext.define('cms.controller.cost.cost_ManualCostController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.cost.cost_ManualCostUI'],
	init:function(){
		this.control({//查询
			'cost_ManualCostUI button[name=btnQueryB903]':{
				click:this.btnQueryB903Click
			},//货主选择
			'cost_ManualCostUI combo[id=ownerNoB903]':{
				change:this.selectAndGetBillingProject
			},//科目代码选择
			'cost_ManualCostUI combo[id=accountB903]':{
				change:this.accountB903Change
			},//计费项目选择
			'cost_ManualCostUI combo[id=billingProjectB903]':{
				change:this.billingProjectB903Select
			},//刷新
			'cost_ManualCostUI button[id=refreshB903_1]':{
				click:this.refreshClick
			},//查找
			'cost_ManualCostUI button[id=queryB903_1]':{
				click:this.queryB903_1Query
			},//开始日期离开
			'cost_ManualCostUI datefield[id=dtBeginDateExpB903]':{
				blur:this.dtBeginDateExpB903Blur
			},//结束日期
			'cost_ManualCostUI datefield[id=dtEndDateExpB903]':{
				blur:this.dtEndDateExpB903Blur
			},//来源单号选择
			'remoteCombo[id=sourceNoB903]':{
				beforequery:this.sourceNoBeforeQuery
			},//生成费用
			'cost_ManualCostUI button[id=retryB903_1]':{
				click:this.retryB903Click
			}
		});
	},
	//初始化界面
	initializtion:function(){
		disableButtonFunc(1,'#retryB903_1',$i18n.createCost);
	},
	//查询
	btnQueryB903Click:function(th){		
		var listDetail = [];
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB903').getValue())&&
				Ext.getCmp('ownerNoB903').getValue()!='ALL')
		{
			var strDtl={
					columnId:'a.owner_no',
					value:Ext.getCmp('ownerNoB903').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('accountB903').getValue()))
		{
 			var strDtl = {
 					columnId:'e.account_no',
 					value:Ext.getCmp("accountB903").getValue()
 				};
 			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('billingProjectB903').getValue()))
		{
			var strDtl={
					columnId:'a.billing_project',
					value:Ext.getCmp('billingProjectB903').getValue()
				};
			listDetail.push(strDtl);
		}
//		var strDtl={
//				columnId:'a.status',
//				value:'10'
//			};
//		listDetail.push(strDtl);
		if(!Ext.isEmpty(Ext.getCmp('sourceNoB903').getValue()))
		{
			var strDtl={
					columnId:'a.source_no',
					value:Ext.getCmp('sourceNoB903').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB903').getValue()))
		{
			var strDtl={
					columnId:'a.build_date',
					condition:4,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('dtBeginDateExpB903').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('dtEndDateExpB903').getValue()))
		{
			var strDtl={
					columnId:'a.build_date',
					condition:5,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('dtEndDateExpB903').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}	
		var strJson = Ext.encode(listDetail);
		
		var wheresql = {
			strQuery : strJson
		};
		Ext.apply(Ext.getCmp('grid_Exp_B903').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_Exp_B903').getStore().removeAll();
		Ext.getCmp('grid_Exp_B903').getStore().load();
		//判断生成费用按钮是否可用
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB903').getValue())
				&& Ext.getCmp('ownerNoB903').getValue() != 'ALL'
				&& !Ext.isEmpty(Ext.getCmp('billingProjectB903').getValue())
				&& !Ext.isEmpty(Ext.getCmp('dtEndDateExpB903').getValue())
				&& !Ext.isEmpty(Ext.getCmp('dtBeginDateExpB903').getValue())){
			disableButtonFunc(0,'#retryB903_1',$i18n.createCost);
		}else{
			disableButtonFunc(1,'#retryB903_1',$i18n.createCost);
		}
	},
	//货主选择
	selectAndGetBillingProject: function(){		
		var strDetail1 = [];
		var d1={
			columnId:'a.owner_no',
			value:Ext.getCmp('ownerNoB903').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB903').getValue())&&
				Ext.getCmp('ownerNoB903').getValue()!='ALL'){
			strDetail1.push(d1);
		}
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				strQuery  : jsonDetail
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB903').getValue())){
			Ext.apply(Ext.getCmp('accountB903').getStore().proxy.extraParams,str);
			Ext.getCmp('accountB903').setValue('');
			Ext.getCmp('accountB903').getStore().removeAll();
			Ext.getCmp('accountB903').getStore().load();
			Ext.apply(Ext.getCmp('billingProjectB903').getStore().proxy.extraParams,str);
			Ext.getCmp('billingProjectB903').setValue('');
			Ext.getCmp('billingProjectB903').getStore().removeAll();
			Ext.getCmp('billingProjectB903').getStore().load();
			
			Ext.getCmp("dtBeginDateExpB903").setValue('');
			Ext.getCmp("dtEndDateExpB903").setValue('');
			Ext.getCmp("sourceNoB903").setValue('');
		}else{
			Ext.getCmp('accountB903').setValue(null);
			Ext.getCmp('accountB903').getStore().removeAll();
			Ext.getCmp('billingProjectB903').setValue(null);
			Ext.getCmp('billingProjectB903').getStore().removeAll();
		}
	},
	//科目代码选择
	accountB903Change: function(){
		var strDetail1 = [];
		var d1={
			columnId:'a.owner_no',
			value:Ext.getCmp('ownerNoB903').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB903').getValue())&&
				Ext.getCmp('ownerNoB903').getValue()!='ALL'){
			strDetail1.push(d1);
		}
		if(!Ext.isEmpty(Ext.getCmp('accountB903').getValue()))
		{
			var strDtl={
					columnId:'e.account_no',
					value:Ext.getCmp('accountB903').getValue()
				};
			strDetail1.push(strDtl);
		}
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				strQuery  : jsonDetail
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB903').getValue())){
			Ext.apply(Ext.getCmp('billingProjectB903').getStore().proxy.extraParams,str);
			Ext.getCmp('billingProjectB903').setValue('');
			Ext.getCmp('billingProjectB903').getStore().removeAll();
			Ext.getCmp('billingProjectB903').getStore().load();
			Ext.getCmp("dtBeginDateExpB903").setValue('');
			Ext.getCmp("dtEndDateExpB903").setValue('');
			Ext.getCmp("sourceNoB903").setValue('');
		}
	},		
	//计费项目选择
	billingProjectB903Select: function(){		
		var strDetail1 = [];
		var d1={
			columnId:'a.owner_no',
			value:Ext.getCmp('ownerNoB903').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB903').getValue())&&
				Ext.getCmp('ownerNoB903').getValue()!='ALL'){
			strDetail1.push(d1);
		}
		if(!Ext.isEmpty(Ext.getCmp('billingProjectB903').getValue()))
		{
			var strDtl={
					columnId:'a.billing_project',
					value:Ext.getCmp('billingProjectB903').getValue()
				};
			strDetail1.push(strDtl);
		}
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				strQuery  : jsonDetail
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB903').getValue())){
			
			Ext.getCmp("dtBeginDateExpB903").setValue('');
			Ext.getCmp("dtEndDateExpB903").setValue('');
			Ext.getCmp("sourceNoB903").setValue('');
		}
	},
	//刷新
	refreshClick:function(){
		Ext.getCmp("ownerNoB903").setValue('');
		Ext.getCmp("billingProjectB903").setValue('');
		Ext.getCmp("dtBeginDateExpB903").setValue('');
		Ext.getCmp("dtEndDateExpB903").setValue('');
		Ext.getCmp("sourceNoB903").setValue('');
		var listDetail  =  [];
//		var strDtl={
//				columnId:'a.status',
//				value:'10'
//			};
//		listDetail.push(strDtl);
		var wheresql = {
				strQuery : Ext.encode(listDetail)
		};
		Ext.apply(Ext.getCmp('grid_Exp_B903').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_Exp_B903').getStore().removeAll();
		Ext.getCmp('grid_Exp_B903').getStore().reload();	
		disableButtonFunc(1,'#retryB903_1',$i18n.createCost);

	},	
	//查找
	queryB903_1Query:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		Ext.getCmp('ownerNoB903').setValue(null);
		Ext.getCmp('accountB903').setValue(null);
		Ext.getCmp('billingProjectB903').setValue(null);
		Ext.getCmp('dtBeginDateExpB903').setValue(null);
		Ext.getCmp('dtEndDateExpB903').setValue(null);
		Ext.getCmp('sourceNoB903').setValue(null);
		queryModuleId='B903';
		queryGrid='grid_Exp_B903';
	},
	//开始日期选择
	dtBeginDateExpB903Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtBeginDateExpB903').getValue()))
		{
			Ext.getCmp('dtBeginDateExpB903').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB903').getValue()))
		{
			if(Ext.getCmp('dtBeginDateExpB903').getValue()>new Date()){
				Ext.example.msg($i18n.prompt,$i18n_prompt.beginDateCanNotMoreThanDoday);
				Ext.getCmp('dtBeginDateExpB903').setValue('');
				return;
			}
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.beginDateCanNotMoreThanEndDate);
				Ext.getCmp('dtBeginDateExpB903').setValue('');
			}
		}else{
			Ext.getCmp("sourceNoB903").setValue('');
		}
	},
	//结束日期选择
	dtEndDateExpB903Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtEndDateExpB903').getValue()))
		{
			Ext.getCmp('dtEndDateExpB903').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtEndDateExpB903').getValue()))
		{
			if(Ext.getCmp('dtEndDateExpB903').getValue()>new Date()){
				Ext.example.msg($i18n.prompt,$i18n_prompt.endDateCanNotMoreThanDoday);
				Ext.getCmp('dtEndDateExpB903').setValue('');
				return;
			}
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.EndDateCanNotLessThanbeginDate);
				Ext.getCmp('dtEndDateExpB903').setValue('');
			}
		}else{
			Ext.getCmp("sourceNoB903").setValue('');
		}
	},	
	//开始结束日期校验
	compareExpDate:function(){
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB903').getValue()) 
				&& !Ext.isEmpty(Ext.getCmp('dtEndDateExpB903').getValue()))
		{
			if(Ext.Date.format(Ext.getCmp('dtBeginDateExpB903').getValue(),'Ymd')>Ext.Date.format(Ext.getCmp('dtEndDateExpB903').getValue(),'Ymd'))
			{
				return false;
			}
		}
		return true;
	},
	 //来源单号选择
	sourceNoBeforeQuery:function(){
 		var listDetail  =  [];
 		if(!Ext.isEmpty(Ext.getCmp('ownerNoB903').getValue())&&
				Ext.getCmp('ownerNoB903').getValue()!='ALL')
		{
 			var strDtl = {
 					columnId:'a.owner_no',
 					value:Ext.getCmp("ownerNoB903").getValue()
 				};
 			listDetail.push(strDtl);
		}
 		if(!Ext.isEmpty(Ext.getCmp('accountB903').getValue()))
		{
 			var strDtl = {
 					columnId:'e.account_no',
 					value:Ext.getCmp("accountB903").getValue()
 				};
 			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('billingProjectB903').getValue())){
			var strDt2 = {
					columnId:'a.billing_project',
					value:Ext.getCmp("billingProjectB903").getValue()
				};
				listDetail.push(strDt2);
		}
		var strDtl={
				columnId:'a.status',
				value:'10'
			};
		listDetail.push(strDtl);
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB903').getValue()))
		{
			var strDtl={
					columnId:'a.build_date',
					condition:4,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('dtBeginDateExpB903').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('dtEndDateExpB903').getValue()))
		{
			var strDtl={
					columnId:'a.build_date',
					condition:5,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('dtEndDateExpB903').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}	
		var params={
			strWheresql:Ext.getCmp("sourceNoB903").getValue(),   
			strQuery:Ext.encode(listDetail),
			buildDate:null
		};
		Ext.apply(Ext.getCmp('sourceNoB903').getStore().proxy.extraParams,params);
		Ext.getCmp('sourceNoB903').getStore().removeAll();
		Ext.getCmp('sourceNoB903').getStore().load();
 	 },
	 //手工生成费用明细
	 retryB903Click:function(th){
		if(Ext.isEmpty(Ext.getCmp('ownerNoB903').getValue()) 
				|| Ext.getCmp('ownerNoB903').getValue()=='ALL')
		{
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiceOwnerNo);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('billingProjectB903').getValue()))
		{
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.select_bset_formulaset);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('dtBeginDateExpB903').getValue()))
		{
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiceBeginDate);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('dtEndDateExpB903').getValue()))
		{
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiceEndDate);
			return;
		}
		var gridcount=Ext.getCmp('grid_Exp_B903').getSelectionModel().getSelection();
		if(gridcount<=0){
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiseExpensesList);
			return;
		}
		Ext.Msg.confirm($i18n_prompt.prompt,$i18n_prompt.sureCreateCost,function(button,text)
		{
			 if(button=='yes'){
				 var objdata=Ext.getCmp('grid_Exp_B903').getSelectionModel().getSelection();
				 var detail=[];
				 if(objdata.length!=0){
					 for(var i=0; i<objdata.length; i++){
						 var no={
								ownerNo:objdata[i].get('ownerNo'),
								billingProject:objdata[i].get('billingProject'),
								buildDate:objdata[i].get('buildDate'),
								buildDateText:objdata[i].get('buildDateText'),
								sourceNo:objdata[i].get('sourceNo')
							};
							detail.push(no);
					 }
				  }

				 var detail2=[];
				 var no2={
							ownerNo:Ext.getCmp('ownerNoB903').getValue(),
							buildDate:new Date(),
							beginDate:Ext.util.Format.date(Ext.getCmp('dtBeginDateExpB903').getValue(), 'Y-m-d'),
							endDate:Ext.util.Format.date(Ext.getCmp('dtEndDateExpB903').getValue(), 'Y-m-d'),
							billingProject:Ext.getCmp('billingProjectB903').getValue(),
							billingType:'',
							flag:'0',
							discountFlag:Ext.getCmp('rdoCheckTypeB903').getValue().rd,
							rgstName:Ext.get('workerNo').getValue()
						};
						detail2.push(no2);
			       var join1=Ext.encode(detail);
				   var join2=Ext.encode(detail2);
				   var params = {
						str:join1,
						strJoin:join2
				   };
				   Ext.Ajax.request({
						method:'POST',
						url:'cost_ManualCostAction_saveManualCost',
						timeout:1800000,
						params:params,
						success:function(response){
							var data = Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n_prompt.prompt,data.msg);
			            		Ext.getCmp('grid_Exp_B903').getStore().reload();
			            		disableButtonFunc(1,'#retryB903_1',$i18n.createCost);

							}else{
								Ext.example.msg($i18n_prompt.prompt,data.msg+data.obj);
							}				
						}
				   });	
			 }
		});
	 }
});

