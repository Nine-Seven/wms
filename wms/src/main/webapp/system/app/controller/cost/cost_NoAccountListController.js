/**
 * 模块名称：未出账清单查询
 * 模块编码：B403
 * 创建：hcx 
 */
var menuType='0';
Ext.define('cms.controller.cost.cost_NoAccountListController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.cost.cost_NoAccountListUI',
	          'cms.view.cost.window.cost_ExpensesListWindow'],
	init:function(){
		this.control({
			//查询
			'cost_NoAccountListUI button[name=btnQueryB403]':{
				click:this.btnQueryB403Click
			},//货主选择
			'cost_NoAccountListUI combo[id=ownerNoB403]':{
				change:this.selectAndGetBillingProject
			},//开始日期离开
			'cost_NoAccountListUI datefield[id=dtBeginDateExpB403]':{
				blur:this.dtBeginDateExpB403Blur
			},//结束日期
			'cost_NoAccountListUI datefield[id=dtEndDateExpB403]':{
				blur:this.dtEndDateExpB403Blur
			},//刷新
			'cost_NoAccountListUI button[id=refreshB403_1]':{
				click:this.refreshClick
			},//查找
			'cost_NoAccountListUI button[id=queryB403_1]':{
				click:this.queryB403_1Query
			},//导出
			'cost_NoAccountListUI button[id=exportB403_1]':{
				click:this.exportClick
			},//重算费用明细
			'cost_NoAccountListUI button[id=retryB403_1]':{
				click:this.retryB403_1Click
			},//费用明细回退
			'cost_NoAccountListUI button[id=undoB403_1]':{
				click:this.undoB403_1Click
			},//查看清单》生成日期选择
			'cost_ExpensesListWindow datefield[id=builDdateB403]':{
				change:this.builDdateB403Select
			},//来源单号选择
			'remoteCombo[id=sourceNoB403]':{
				beforequery:this.sourceNoBeforeQuery,
				select:this.sourceNoSelect
			},//刷新
			'cost_ExpensesListWindow button[id=refreshB403]':{
				click:this.refreshB403Click
			},//导出
			'cost_ExpensesListWindow button[id=exportB403]':{
				click:this.exportB403Click
			}
		});
	},
	
	//查询
	btnQueryB403Click:function(th){		
//		if(!commonCheckIsInputAll(th.ownerCt.id))
//		{
//			return;
//		}	
		var listDetail = [];
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB403').getValue()))
		{
			var strDtl={
					columnId:'a.owner_no',
					value:Ext.getCmp('ownerNoB403').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('billingProjectB403').getValue()))
		{
			var strDtl={
					columnId:'a.billing_project',
					value:Ext.getCmp('billingProjectB403').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB403').getValue()))
		{
			var strDtl={
					columnId:'a.build_date',
					condition:4,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('dtBeginDateExpB403').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('dtEndDateExpB403').getValue()))
		{
			var strDtl={
					columnId:'a.build_date',
					condition:5,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('dtEndDateExpB403').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}
//		var strDtl={
//				columnId:'a.status',
//				value:'10'
//			};
//		listDetail.push(strDtl);
		var strJson = Ext.encode(listDetail);
		var wheresql = {
			strQuery : strJson,
			strMenuType:menuType
		};
		Ext.apply(Ext.getCmp('grid_Exp_B403').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_Exp_B403').getStore().removeAll();
		Ext.getCmp('grid_Exp_B403').getStore().load();		
	},
	//货主选择
	selectAndGetBillingProject: function(){		
		//获取计费项目
		var strDetail1 = [];
		var d1={
			columnId:'a.owner_no',
			value:Ext.getCmp('ownerNoB403').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB403').getValue())){
			strDetail1.push(d1);
		}
		var d2={
				columnId:'a.warehouse_no',
				value:Ext.get('warehouseNo').getValue()
		};
		strDetail1.push(d2);
//		var d3={
//				columnId:'a.status',
//				value:'10'
//		};
//		strDetail1.push(d3);
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				strQuery  : jsonDetail
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB403').getValue())){
			Ext.apply(Ext.getCmp('billingProjectB403').getStore().proxy.extraParams,str);
			Ext.getCmp('billingProjectB403').setValue('');
			Ext.getCmp('billingProjectB403').getStore().removeAll();
			Ext.getCmp('billingProjectB403').getStore().load();
		}else{
			Ext.getCmp('billingProjectB403').setValue(null);
			Ext.getCmp('billingProjectB403').getStore().removeAll();
		}
	},
	//开始日期选择
	dtBeginDateExpB403Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtBeginDateExpB403').getValue()))
		{
			Ext.getCmp('dtBeginDateExpB403').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB403').getValue()))
		{
			if(Ext.getCmp('dtBeginDateExpB403').getValue()>new Date()){
				Ext.example.msg($i18n.prompt,$i18n_prompt.beginDateCanNotMoreThanDoday);
				Ext.getCmp('dtBeginDateExpB403').setValue('');
				return;
			}
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.beginDateCanNotMoreThanEndDate);
				Ext.getCmp('dtBeginDateExpB403').setValue('');
			}
		}
	},
	//结束日期选择
	dtEndDateExpB403Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtEndDateExpB403').getValue()))
		{
			Ext.getCmp('dtEndDateExpB403').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtEndDateExpB403').getValue()))
		{
			if(Ext.getCmp('dtEndDateExpB403').getValue()>new Date()){
				Ext.example.msg($i18n.prompt,$i18n_prompt.endDateCanNotMoreThanDoday);
				Ext.getCmp('dtEndDateExpB403').setValue('');
				return;
			}
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.EndDateCanNotLessThanbeginDate);
				Ext.getCmp('dtEndDateExpB403').setValue('');
			}
		}
	},	
	//开始结束日期校验
	compareExpDate:function(){
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB403').getValue()) 
				&& !Ext.isEmpty(Ext.getCmp('dtEndDateExpB403').getValue()))
		{
			if(Ext.Date.format(Ext.getCmp('dtBeginDateExpB403').getValue(),'Ymd')>Ext.Date.format(Ext.getCmp('dtEndDateExpB403').getValue(),'Ymd'))
			{
				return false;
			}
		}
		return true;
	},
	//刷新
	refreshClick:function(){
		var listDetail = [];
//		var strDtl={
//				columnId:'a.status',
//				value:'10'
//			};
//		listDetail.push(strDtl);
		var strJson = Ext.encode(listDetail);
		var wheresql = {
			strQuery : strJson,
			strMenuType:menuType
		};
		Ext.getCmp('ownerNoB403').setValue('');
		Ext.getCmp('billingProjectB403').setValue('');
		Ext.getCmp('dtBeginDateExpB403').setValue('');
		Ext.getCmp('dtEndDateExpB403').setValue('');
		Ext.apply(Ext.getCmp('grid_Exp_B403').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_Exp_B403').getStore().removeAll();
		Ext.getCmp('grid_Exp_B403').getStore().load();
	},
	//查找
	queryB403_1Query:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		Ext.getCmp('ownerNoB403').setValue(null);
		Ext.getCmp('billingProjectB403').setValue(null);
		Ext.getCmp('dtBeginDateExpB403').setValue(null);
		Ext.getCmp('dtEndDateExpB403').setValue(null);
		queryModuleId='B403';
		queryGrid='grid_Exp_B403';
	},
	//导出
	exportClick:function(){
		commExport('grid_Exp_B403');
	},
	//重算费用明细
	retryB403_1Click:function(){
		if(Ext.isEmpty(Ext.getCmp('ownerNoB403').getValue())
				|| Ext.getCmp('ownerNoB403').getValue()=='ALL')
		{
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiceOwnerNo);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('dtBeginDateExpB403').getValue()))
		{
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiceBeginDate);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('dtEndDateExpB403').getValue()))
		{
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiceEndDate);
			return;
		}
		var gridcount=Ext.getCmp('grid_Exp_B403').getSelectionModel().getSelection();
		Ext.Msg.confirm($i18n_prompt.prompt,$i18n_prompt.sureRetry,function(button,text)
		{
			 if(button=='yes'){
					Ext.Ajax.request({
						url : 'cost_NoAccountListAction_tscResetCost',
						params : {
							strOwnerNo:Ext.getCmp('ownerNoB403').getValue(),
							billingProject:Ext.getCmp('billingProjectB403').getValue()==undefined?'':Ext.getCmp('billingProjectB403').getValue(),
							beginDate:Ext.getCmp('dtBeginDateExpB403').getValue(),
							endDate:Ext.getCmp('dtEndDateExpB403').getValue()
						},
						success : function(response) {
							var data = Ext.decode(response.responseText);
					    	if(data.isSucc){					
								Ext.example.msg($i18n.prompt,data.msg);
								Ext.getCmp('grid_Exp_B403').getStore().reload();
							}else{
								Ext.example.msg($i18n.prompt,data.msg+data.obj);
							}
						}
					});
				}
		});
	 },
	//费用明细回退
	undoB403_1Click:function(){
		 var gridcount=Ext.getCmp('grid_Exp_B403').getSelectionModel().getSelection();
		 if(gridcount.length==0){
			 Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiseCostList);
			 return;
		 }
		 Ext.Msg.confirm($i18n_prompt.prompt,$i18n_prompt.sureUndo,function(button,text)
		{
			 if(button=='yes'){
				 var objdata=Ext.getCmp('grid_Exp_B403').getSelectionModel().getSelection();
				 var detail=[];
				 if(objdata.length!=0){
					 for(var i=0; i<objdata.length; i++){
						 var no={
								ownerNo:objdata[i].get('ownerNo'),
								billingProject:objdata[i].get('billingProject'),
								billingType:objdata[i].get('billingType'),
								buildDate:objdata[i].get('buildDate'),
								serialNo:objdata[i].get('serialNo')
							};
							detail.push(no);
					 }
				   }
				   var no=Ext.encode(detail);
				   var params = {
						str:no
				   };
				   Ext.Ajax.request({
						method:'POST',
						url:'cost_NoAccountListAction_tscUndoCost',
						timeout:1800000,
						params:params,
						success:function(response){
							var data = Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n_prompt.prompt,data.msg);
			            		Ext.getCmp('grid_Exp_B403').getStore().reload();
							}else{
								Ext.example.msg($i18n_prompt.prompt,data.msg+data.obj);
							}				
						}
				   });	
			 }
		});
	},
	//查看清单-生成日期选择
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
	 //查看清单-来源单号选择前加载
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
		if(!Ext.isEmpty(Ext.getCmp('serialNoB403_2').getValue())){
			var strDt2 = {
					columnId:'a.serial_no',
					value:Ext.getCmp("serialNoB403_2").getValue()
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
 	//查看清单-来源单号选择
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
	//查看清单-刷新
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
	 //查看清单-导出
	 exportB403Click:function(){
			commExport('gridExpensesListB403');
	 }
});

