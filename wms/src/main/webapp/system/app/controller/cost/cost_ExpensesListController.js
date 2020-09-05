/**
 * 模块名称：消费清单查询
 * 模块编码：B803
 * 创建：hcx 
 */
var menuType='0';
Ext.define('cms.controller.cost.cost_ExpensesListController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.cost.cost_ExpensesListUI'],
	init:function(){
		this.control({//查询
			'cost_ExpensesListUI button[name=btnQueryB803]':{
				click:this.btnQueryB803Click
			},//货主选择
			'cost_ExpensesListUI combo[id=ownerNoB803]':{
				change:this.selectAndGetBillingProject
			},//计费项目选择
			'cost_ExpensesListUI combo[id=billingProjectB803]':{
				change:this.billingProjectB803Select
			},//状态选择
			'cost_ExpensesListUI combo[id=statusB803]':{
				change:this.statusB803Select
			},//刷新
			'cost_ExpensesListUI button[id=refreshB803_1]':{
				click:this.refreshClick
			},//查找
			'cost_ExpensesListUI button[id=queryB803_1]':{
				click:this.queryB803_1Query
			},//删除
			'cost_ExpensesListUI button[id=deleteB803_1]':{
				click:this.deleteB803_1Query
			},//导出
			'cost_ExpensesListUI button[id=exportB803_1]':{
				click:this.exportClick
			},//开始日期离开
			'cost_ExpensesListUI datefield[id=dtBeginDateExpB803]':{
				blur:this.dtBeginDateExpB803Blur
			},//结束日期
			'cost_ExpensesListUI datefield[id=dtEndDateExpB803]':{
				blur:this.dtEndDateExpB803Blur
			},//来源单号选择
			'remoteCombo[id=sourceNoB803]':{
				beforequery:this.sourceNoBeforeQuery
			},//重算
			'cost_ExpensesListUI button[id=retryB803_1]':{
				click:this.retryB803Click
			}
		});
	},
//	//初始化界面
//	initializtion:function(){
//		disableButtonFunc(1,'#retryB803_1',$i18n.retry);
//	},
	//查询
	btnQueryB803Click:function(th){		
//		if(!commonCheckIsInputAll(th.ownerCt.id))
//		{
//			return;
//		}	
		var listDetail = [];
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB803').getValue())&&
				Ext.getCmp('ownerNoB803').getValue()!='ALL')
		{
			var strDtl={
					columnId:'a.owner_no',
					value:Ext.getCmp('ownerNoB803').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('billingProjectB803').getValue()))
		{
			var strDtl={
					columnId:'a.billing_project',
					value:Ext.getCmp('billingProjectB803').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('statusB803').getValue()))
		{
			var strDtl={
					columnId:'a.status',
					value:Ext.getCmp('statusB803').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('sourceNoB803').getValue()))
		{
			var strDtl={
					columnId:'a.source_no',
					value:Ext.getCmp('sourceNoB803').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB803').getValue()))
		{
			var strDtl={
					columnId:'a.build_date',
					condition:4,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('dtBeginDateExpB803').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('dtEndDateExpB803').getValue()))
		{
			var strDtl={
					columnId:'a.build_date',
					condition:5,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('dtEndDateExpB803').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}	
		var strJson = Ext.encode(listDetail);
		
		var wheresql = {
			strQuery : strJson
		};
		Ext.apply(Ext.getCmp('grid_Exp_B803').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_Exp_B803').getStore().removeAll();
		Ext.getCmp('grid_Exp_B803').getStore().load();		
//		if(!Ext.isEmpty(Ext.getCmp('ownerNoB803').getValue())
////			&& Ext.getCmp('statusB803').getValue()=='10'
//			&& !Ext.isEmpty(Ext.getCmp('dtBeginDateExpB803').getValue())
//			&& !Ext.isEmpty(Ext.getCmp('dtEndDateExpB803').getValue())){
//			disableButtonFunc(0,'#retryB803_1',$i18n.retry);
//		}else{
//			disableButtonFunc(1,'#retryB803_1',$i18n.retry);
//		}
	},
	//货主选择
	selectAndGetBillingProject: function(){		
		//获取计费项目
		var strDetail1 = [];
		var d1={
			columnId:'a.owner_no',
			value:Ext.getCmp('ownerNoB803').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB803').getValue())&&
				Ext.getCmp('ownerNoB803').getValue()!='ALL'){
			strDetail1.push(d1);
		}
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
			strQuery  : jsonDetail
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB803').getValue())){
			Ext.apply(Ext.getCmp('billingProjectB803').getStore().proxy.extraParams,str);
			Ext.getCmp('billingProjectB803').setValue('');
			Ext.getCmp('billingProjectB803').getStore().removeAll();
			Ext.getCmp('billingProjectB803').getStore().load();
			
			Ext.getCmp('statusB803').setValue('');
			Ext.apply(Ext.getCmp('statusB803').getStore().proxy.extraParams,str);
			Ext.getCmp('statusB803').getStore().removeAll();
			Ext.getCmp('statusB803').getStore().load();
			
			Ext.getCmp("dtBeginDateExpB803").setValue('');
			Ext.getCmp("dtEndDateExpB803").setValue('');
			Ext.getCmp("sourceNoB803").setValue('');
		}else{
			Ext.getCmp('billingProjectB803').setValue(null);
			Ext.getCmp('billingProjectB803').getStore().removeAll();
		}
	},
	//计费项目选择
	billingProjectB803Select: function(){		
		var strDetail1 = [];
		var d1={
			columnId:'a.owner_no',
			value:Ext.getCmp('ownerNoB803').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB803').getValue())&&
				Ext.getCmp('ownerNoB803').getValue()!='ALL'){
			strDetail1.push(d1);
		}
		if(!Ext.isEmpty(Ext.getCmp('billingProjectB803').getValue()))
		{
			var strDtl={
					columnId:'a.billing_project',
					value:Ext.getCmp('billingProjectB803').getValue()
				};
			strDetail1.push(strDtl);
		}
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
			strQuery  : jsonDetail
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB803').getValue())){
			Ext.getCmp('statusB803').setValue('');
			Ext.apply(Ext.getCmp('statusB803').getStore().proxy.extraParams,str);
			Ext.getCmp('statusB803').getStore().removeAll();
			Ext.getCmp('statusB803').getStore().load();
			
			Ext.getCmp("dtBeginDateExpB803").setValue('');
			Ext.getCmp("dtEndDateExpB803").setValue('');
			Ext.getCmp("sourceNoB803").setValue('');
		}
	},
	//状态选择
	statusB803Select:function(){
		Ext.getCmp("dtBeginDateExpB803").setValue('');
		Ext.getCmp("dtEndDateExpB803").setValue('');
		Ext.getCmp("sourceNoB803").setValue('');
	},
	//刷新
	refreshClick:function(){
		Ext.getCmp("ownerNoB803").setValue('');
		Ext.getCmp("billingProjectB803").setValue('');
		Ext.getCmp("statusB803").setValue('');
		Ext.getCmp("dtBeginDateExpB803").setValue('');
		Ext.getCmp("dtEndDateExpB803").setValue('');
		Ext.getCmp("sourceNoB803").setValue('');
		var wheresql = {
			strQuery : null
		};
		Ext.apply(Ext.getCmp('grid_Exp_B803').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_Exp_B803').getStore().removeAll();
		Ext.getCmp('grid_Exp_B803').getStore().reload();	
//		disableButtonFunc(1,'#retryB803_1',$i18n.retry);
	},
	//查找
	queryB803_1Query:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		Ext.getCmp('ownerNoB803').setValue(null);
		Ext.getCmp('billingProjectB803').setValue(null);
		Ext.getCmp('statusB803').setValue(null);
		Ext.getCmp('dtBeginDateExpB803').setValue(null);
		Ext.getCmp('dtEndDateExpB803').setValue(null);
		Ext.getCmp('sourceNoB803').setValue(null);
		queryModuleId='B803';
		queryGrid='grid_Exp_B803';
	},
	//删除
	deleteB803_1Query:function(){
		var cust=Ext.getCmp('grid_Exp_B803').getSelectionModel().getSelection();
		if(cust.length>0)
		{
			if(cust[0].data.status != '10'){
				Ext.example.msg($i18n.prompt,$i18n_prompt.expensesListCanNotDelete);
	            return;
			}
			Ext.Msg.confirm($i18n.prompt, $i18n_prompt.deleteOrNot,function(button, text){
				if (button == 'yes') {
					var strDetail1 = [];
					var d1={
						columnId:'t1.enterprise_no',
						value:cust[0].data.enterpriseNo
					};
					strDetail1.push(d1);
					var d2={
							columnId:'t1.warehouse_no',
							value:cust[0].data.warehouseNo
					};
					strDetail1.push(d2);
					var d3={
							columnId:'t1.owner_no',
							value:cust[0].data.ownerNo
						};
					strDetail1.push(d3);
					var d4={
							columnId:'t1.billing_project',
							value:cust[0].data.billingProject
						};
					strDetail1.push(d4);	
					var d5={
							columnId:'t1.billing_type',
							value:cust[0].data.billingType
						};
					strDetail1.push(d5);	
					var d6={
							columnId:'t1.family_no',
							value:cust[0].data.familyNo
						};
					strDetail1.push(d6);
					var d7={
							columnId:'t1.source_no',
							value:cust[0].data.sourceNo
						};
					strDetail1.push(d7);	
					var jsonDetail = Ext.encode(strDetail1);
					var params = {
							strQuery  : jsonDetail,
							buildDate:cust[0].data.buildDate
					};
					Ext.Ajax.request({
						method:'post',
						url:'cost_ExpensesListAction_deleteExpensesList',
						params:params,
					    success:function(response){
							var data=Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n.prompt,data.msg);
								Ext.getCmp('grid_Exp_B803').getStore().reload();
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
	//导出
	exportClick:function(){
		commExport('grid_Exp_B803');
	},
	
	dtBeginDateExpB803Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtBeginDateExpB803').getValue()))
		{
			Ext.getCmp('dtBeginDateExpB803').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB803').getValue()))
		{
			if(Ext.getCmp('dtBeginDateExpB803').getValue()>new Date()){
				Ext.example.msg($i18n.prompt,$i18n_prompt.beginDateCanNotMoreThanDoday);
				Ext.getCmp('dtBeginDateExpB803').setValue('');
				return;
			}
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.beginDateCanNotMoreThanEndDate);
				Ext.getCmp('dtBeginDateExpB803').setValue('');
			}
		}else{
			Ext.getCmp("sourceNoB803").setValue('');
		}
	},
	dtEndDateExpB803Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtEndDateExpB803').getValue()))
		{
			Ext.getCmp('dtEndDateExpB803').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtEndDateExpB803').getValue()))
		{
			if(Ext.getCmp('dtEndDateExpB803').getValue()>new Date()){
				Ext.example.msg($i18n.prompt,$i18n_prompt.endDateCanNotMoreThanDoday);
				Ext.getCmp('dtEndDateExpB803').setValue('');
				return;
			}
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.EndDateCanNotLessThanbeginDate);
				Ext.getCmp('dtEndDateExpB803').setValue('');
			}
		}else{
			Ext.getCmp("sourceNoB803").setValue('');
		}
	},	
	compareExpDate:function(){
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB803').getValue()) 
				&& !Ext.isEmpty(Ext.getCmp('dtEndDateExpB803').getValue()))
		{
			if(Ext.Date.format(Ext.getCmp('dtBeginDateExpB803').getValue(),'Ymd')>Ext.Date.format(Ext.getCmp('dtEndDateExpB803').getValue(),'Ymd'))
			{
				return false;
			}
		}
		return true;
	},
	 //来源单号选择
	sourceNoBeforeQuery:function(){
 		var listDetail  =  [];
 		if(!Ext.isEmpty(Ext.getCmp('ownerNoB803').getValue())&&
				Ext.getCmp('ownerNoB803').getValue()!='ALL')
		{
 			var strDtl = {
 					columnId:'a.owner_no',
 					value:Ext.getCmp("ownerNoB803").getValue()
 				};
 			listDetail.push(strDtl);
		}
		
		if(!Ext.isEmpty(Ext.getCmp('billingProjectB803').getValue())){
			var strDt2 = {
					columnId:'a.billing_project',
					value:Ext.getCmp("billingProjectB803").getValue()
				};
				listDetail.push(strDt2);
		}
		if(!Ext.isEmpty(Ext.getCmp('statusB803').getValue()))
		{
			var strDtl={
					columnId:'a.status',
					value:Ext.getCmp('statusB803').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB803').getValue()))
		{
			var strDtl={
					columnId:'a.build_date',
					condition:4,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('dtBeginDateExpB803').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('dtEndDateExpB803').getValue()))
		{
			var strDtl={
					columnId:'a.build_date',
					condition:5,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('dtEndDateExpB803').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}	
		var params={
			strWheresql:Ext.getCmp("sourceNoB803").getValue(),   
			strQuery:Ext.encode(listDetail),
			buildDate:null
		};
		Ext.apply(Ext.getCmp('sourceNoB803').getStore().proxy.extraParams,params);
		Ext.getCmp('sourceNoB803').getStore().removeAll();
		Ext.getCmp('sourceNoB803').getStore().load();
 	 },
	 //重算
	 retryB803Click:function(th){
		if(Ext.isEmpty(Ext.getCmp('ownerNoB803').getValue())
				|| Ext.getCmp('ownerNoB803').getValue()=='ALL')
		{
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiceOwnerNo);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('dtBeginDateExpB803').getValue()))
		{
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiceBeginDate);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('dtEndDateExpB803').getValue()))
		{
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiceEndDate);
			return;
		}
		var gridcount=Ext.getCmp('grid_Exp_B803').getSelectionModel().getSelection();
		Ext.Msg.confirm($i18n_prompt.prompt,$i18n_prompt.sureRetry,function(button,text)
		{
			 if(button=='yes'){
					Ext.Ajax.request({
						url : 'cost_ExpensesListAction_tscResetExp',
						params : {
							strOwnerNo:Ext.getCmp('ownerNoB803').getValue(),
							billingProject:Ext.getCmp('billingProjectB803').getValue()==undefined?'':Ext.getCmp('billingProjectB803').getValue(),
							beginDate:Ext.getCmp('dtBeginDateExpB803').getValue(),
							endDate:Ext.getCmp('dtEndDateExpB803').getValue()
						},
						success : function(response) {
							var data = Ext.decode(response.responseText);
					    	if(data.isSucc){					
								Ext.example.msg($i18n.prompt,data.msg);
								Ext.getCmp('grid_Exp_B803').getStore().reload();
							}else{
								Ext.example.msg($i18n.prompt,data.msg+data.obj);
							}
						}
					});
				}
		});
	 }
});

