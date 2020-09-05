/**
 * 模块名称：已出账账单查询
 * 模块编码：B503
 * 创建：hcx 
 */
Ext.define('cms.controller.cost.cost_FinancialController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.cost.cost_financialUI',
	          'cms.view.cost.window.cost_ExpensesListWindow'
	          ],
	init:function(){
		this.control({
			//查询
			'cost_financialUI button[name=btnQueryB503]':{
				click:this.btnQueryB503Click
			},//刷新
			'cost_financialUI button[id=refreshB503_1]':{
				click:this.refreshClick
			},//查找
			'cost_financialUI button[id=queryB503_1]':{
				click:this.queryB503_1Query
			},//导出
			'cost_financialUI button[id=exportB503_1]':{
				click:this.exportClick
			},//重算账单
			'cost_financialUI button[id=retryB503_1]':{
				click:this.retryB503_1Click
			},//账单回退
			'cost_financialUI button[id=undoB503_1]':{
				click:this.undoB503_1Click
			},//货主选择
			'cost_financialUI combo[id=ownerNoB503]':{
				change:this.selectAndGetBillingProject
			},//状态选择
			'cost_financialUI combo[id=statusB503]':{
				change:this.statusB503Select
			},//生成日期选择
			'cost_financialUI datefield[id=builDdateB503]':{
				change:this.builDdateB503Select
			},//对账单号选择
			'cost_financialUI combo[id=checkNoB503]':{
				beforequery:this.checkNoBeforeQuery,
				select:this.checkNoB503Slect
			},//查看对账》计费项目选择
			'wms_DefFieldValCombo[id=billingProjectB503_2]':{
				select:this.billingProjectSelect
			},//查看对账》刷新
			'cost_CostListWindow button[id=refreshB503]':{
				click:this.refreshB503Click
			},//查看对账》导出
			'cost_CostListWindow button[id=exportB503]':{
				click:this.exportB503Click
			},//杂项费用查看对账》刷新
			'cost_OtherListWindow button[id=refreshB503_3]':{
				click:this.refreshB503_3Click
			},//杂项费用查看对账》导出
			'cost_OtherListWindow button[id=exportB503_3]':{
				click:this.exportB503_3Click
			},//杂项费用查看对账》费用代码选择
			'wms_DefFieldValCombo[id=costNoB503_3]':{
				select:this.costNoB503_3Select
			},//查看清单》刷新
			'cost_ExpensesListWindow button[id=refreshB403]':{
				click:this.refreshB403Click
			},//查看清单》导出
			'cost_ExpensesListWindow button[id=exportB403]':{
				click:this.exportB403Click
			},//查看清单》生成日期选择
			'cost_ExpensesListWindow datefield[id=builDdateB403]':{
				change:this.builDdateB403Select
			},//查看清单》来源单号选择
			'remoteCombo[id=sourceNoB403]':{
				beforequery:this.sourceNoBeforeQuery,
				select:this.sourceNoSelect
			}	
		});
	},
	
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
	
	exportB403Click:function(){
		commExport('gridExpensesListB403');
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
	//查询
	btnQueryB503Click:function(th){	
		var listDetail = [];
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB503').getValue())
				&& Ext.getCmp('ownerNoB503').getValue()!='ALL')
		{
			var strDtl={
					columnId:'a.owner_no',
					value:Ext.getCmp('ownerNoB503').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('accountB503').getValue()))
		{
			var strDtl={
					columnId:'a.account_no',
					value:Ext.getCmp('accountB503').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('statusB503').getValue()))
		{
			var strDtl={
					columnId:'a.status',
					value:Ext.getCmp('statusB503').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('builDdateB503').getValue()))
		{
			var buildDate=Ext.Date.format(Ext.getCmp('builDdateB503').getValue(),'Y-m-d');

		}else{
			var buildDate=null;
		}
		if(!Ext.isEmpty(Ext.getCmp('checkNoB503').getValue()))
		{
			var strDtl={
					columnId:'a.check_no',
					value:Ext.getCmp('checkNoB503').getValue()
				};
			listDetail.push(strDtl);
		}
		var strJson = Ext.encode(listDetail);
		var wheresql = {
			strQuery : strJson,
			buildDate:buildDate
		};
		Ext.apply(Ext.getCmp('grid_Financial_B503').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_Financial_B503').getStore().removeAll();
		Ext.getCmp('grid_Financial_B503').getStore().load();	
//		if(Ext.getCmp('statusB503').getValue()=='10'){
//			disableButtonFunc(0,'#retryB503_1',$i18n.retry);
//		}else{
//			disableButtonFunc(1,'#retryB503_1',$i18n.retry);
//		}
	},	
	//开始日期选择
	dtBeginDateCostB503Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtBeginDateCostB503').getValue()))
		{
			Ext.getCmp('dtBeginDateCostB503').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateCostB503').getValue()))
		{
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.beginDateCanNotMoreThanEndDate);
				Ext.getCmp('dtBeginDateCostB503').setValue('');
			}
		}
	},
	//结束日期选择
	dtEndDateCostB503Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtEndDateCostB503').getValue()))
		{
			Ext.getCmp('dtEndDateCostB503').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtEndDateCostB503').getValue()))
		{
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.EndDateCanNotLessThanbeginDate);
				Ext.getCmp('dtEndDateCostB503').setValue('');
			}
		}
	},	
	//开始结束日期校验
	compareExpDate:function(){
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateCostB503').getValue()) 
				&& !Ext.isEmpty(Ext.getCmp('dtEndDateCostB503').getValue()))
		{
			if(Ext.Date.format(Ext.getCmp('dtBeginDateCostB503').getValue(),'Ymd')>Ext.Date.format(Ext.getCmp('dtEndDateCostB503').getValue(),'Ymd'))
			{
				return false;
			}
		}
		return true;
	},
	//刷新
	refreshClick:function(){
		var listDetail = [];
		var strJson = Ext.encode(listDetail);
		var wheresql = {
			strQuery : strJson,
			buildDate:null
		};
		Ext.getCmp('ownerNoB503').setValue('');
		Ext.getCmp('accountB503').setValue('');
		Ext.getCmp('statusB503').setValue('');
		Ext.getCmp('builDdateB503').setValue('');
		Ext.getCmp('checkNoB503').setValue('');
		Ext.apply(Ext.getCmp('grid_Financial_B503').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_Financial_B503').getStore().removeAll();
		Ext.getCmp('grid_Financial_B503').getStore().load();
	},
	//查找
	queryB503_1Query:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		Ext.getCmp('ownerNoB503').setValue(null);
		Ext.getCmp('accountB503').setValue(null);
		Ext.getCmp('statusB503').setValue(null);
		Ext.getCmp('builDdateB503').setValue(null);
		Ext.getCmp('checkNoB503').setValue(null);
		Ext.getCmp('accountGroupNoB503').setValue(null);
		queryModuleId='B503';
		queryGrid='grid_Financial_B503';
	},
	//导出
	exportClick:function(){
		commExport('grid_Financial_B503');
	},
	//重算账单
	retryB503_1Click:function(th){
		if(Ext.isEmpty(Ext.getCmp('ownerNoB503').getValue())
				|| Ext.getCmp('ownerNoB503').getValue()=='ALL')
		{
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiceOwnerNo);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('statusB503').getValue()))
		{
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiceStayBackStatus);
			return;
		}
		if(Ext.getCmp('statusB503').getValue()!='10')
		{
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.onlyCanRetryStayBackStatus);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('builDdateB503').getValue()))
		{
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiceBuildDate);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('checkNoB503').getValue()))
		{
			Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.choiceCheckNo);
			return;
		}
		var strDetail1 = [];
		if(!Ext.isEmpty(Ext.getCmp('checkNoB503').getValue()) ){
			var d1={
					columnId:'a.check_no',
					value:Ext.getCmp('checkNoB503').getValue()
				};
			strDetail1.push(d1);
		}
		var jsonDetail = Ext.encode(strDetail1);
		var params = {
			strQuery  : jsonDetail
		};
	    Ext.Ajax.request({
				method:'POST',
				url:'cost_FinancialAction_getCreateFlag',
				timeout:1800000,
				async:false,
				params:params,
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data!=='A'){
						if(data=='1'){
							 Ext.example.msg($i18n_prompt.prompt,$i18n.check_no+Ext.getCmp('checkNoB503').getValue()+$i18n_prompt.isManualAccount);
							 return;
						}else{
							Ext.Msg.confirm($i18n_prompt.prompt,$i18n_prompt.sureRetry,function(button,text)
									{
										 if(button=='yes'){
											 var detail = [];
											 var no={
													    ownerNo:Ext.getCmp('checkNoB503').getValue(),
														buildDate:Ext.util.Format.date(Ext.getCmp('builDdateB503').getValue(), 'Y-m-d'),
														accountGroupNo:Ext.getCmp('accountGroupNoB503').getValue(),
														checkNo:Ext.getCmp('checkNoB503').getValue(),
														Flag:'1',
														rgstName:Ext.get('workerNo').getValue()							};
												detail.push(no);
											   var join=Ext.encode(detail);
											   var params = {
													str:join
											   };
											   Ext.Ajax.request({
													method:'POST',
													url:'cost_FinancialAction_tscResetFin',
													timeout:1800000,
													params:params,
													success:function(response){
														var data = Ext.decode(response.responseText);
														if(data.isSucc){
															Ext.example.msg($i18n_prompt.prompt,data.msg);
										            		Ext.getCmp('grid_Financial_B503').getStore().reload();
														}else{
															Ext.example.msg($i18n_prompt.prompt,data.msg+data.obj);
														}				
													}
											   });	
										 }
									});
						}
					}else{
						 Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.takeFlagError);
						 return;
					}
				}
		});	
		
	 },
	//账单回退
	undoB503_1Click:function(){
		if(Ext.isEmpty(Ext.getCmp('ownerNoB503').getValue()))
		{
			Ext.example.msg($i18n_prompt.prompt,'请选择货主');
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('statusB503').getValue()))
		{
			Ext.example.msg($i18n_prompt.prompt,'请选择待回款状态');
			return;
		}
		if(Ext.getCmp('statusB503').getValue()!='10')
		{
			Ext.example.msg($i18n_prompt.prompt,'只能回退待回款状态账单，请重新选择');
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('checkNoB503').getValue()))
		{
			Ext.example.msg($i18n_prompt.prompt,'请选择对账单号');
			return;
		}
		 Ext.Msg.confirm($i18n_prompt.prompt,'确定回退所选账单？',function(button,text)
		{
			 if(button=='yes'){
				 var detail=[];
				 var no={
							ownerNo:Ext.getCmp('ownerNoB503').getValue(),
							checkNo:Ext.getCmp('checkNoB503').getValue()
						};
						detail.push(no);
				   var no=Ext.encode(detail);
				   var params = {
						str:no
				   };
				   Ext.Ajax.request({
						method:'POST',
						url:'cost_FinancialAction_tscUndoFinancial',
						timeout:1800000,
						params:params,
						success:function(response){
							var data = Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n_prompt.prompt,data.msg);
								Ext.getCmp('checkNoB503').setValue('');
			            		Ext.getCmp('grid_Financial_B503').getStore().reload();
							}else{
								Ext.example.msg($i18n_prompt.prompt,data.msg+data.obj);
							}				
						}
				   });	
			 }
		});
	},
	//货主选择
	selectAndGetBillingProject: function(){		
		//获取计费项目
		var strDetail1 = [];
		var d1={
			columnId:'a.owner_no',
			value:Ext.getCmp('ownerNoB503').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB503').getValue()) ){
			strDetail1.push(d1);
		}
//		var d2={
//				columnId:'a.status',
//				value:'10'
//		};
//		strDetail1.push(d2);
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
			strQuery  : jsonDetail
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB503').getValue())){
			Ext.apply(Ext.getCmp('accountB503').getStore().proxy.extraParams,str);
			Ext.getCmp('accountB503').setValue('');
			Ext.getCmp('accountB503').getStore().removeAll();
			Ext.getCmp('accountB503').getStore().load();
		}else{
			Ext.getCmp('accountB503').setValue(null);
			Ext.getCmp('accountB503').getStore().removeAll();
		}
		//加载状态下拉列表
		statusSelect();
		Ext.getCmp('builDdateB503').setValue('');
		//加载对账单下拉列表
		checkNoSelect();
	},
	//状态选择
	statusB503Select:function(){
		Ext.getCmp('builDdateB503').setValue('');
		//加载对账单下拉列表
		checkNoSelect();
	},
	//生成日期选择
	builDdateB503Select:function(){
		//加载对账单下拉列表
		checkNoSelect();
	},
    //对账单号选择前
	checkNoBeforeQuery:function(){
		var detail = [];
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB503').getValue())){
			var a={
					columnId:'a.owner_no',
					value:Ext.getCmp('ownerNoB503').getValue()
				};
			detail.push(a);
		}
		if(!Ext.isEmpty(Ext.getCmp('accountB503').getValue())){
			var a={
					columnId:'a.account_no',
					value:Ext.getCmp('accountB503').getValue()
				};
			detail.push(a);
		}
		if(!Ext.isEmpty(Ext.getCmp('statusB503').getValue())){
			var a={
					columnId:'a.status',
					value:Ext.getCmp('statusB503').getValue()
				};
			detail.push(a);
		}
		if(!Ext.isEmpty(Ext.getCmp('builDdateB503').getValue())){
			var a={
					columnId:'a.build_date',
					valueType:3,
					value:Ext.Date.format( Ext.getCmp('builDdateB503').getValue(),'Y-m-d')
				};
			detail.push(a);
		}
		var jsonDetail = Ext.encode(detail);
		var wheresql = {
			strWheresql:Ext.getCmp("checkNoB503").getValue(),   	
			strQuery : jsonDetail
		};
		Ext.getCmp('checkNoB503').setValue('');
		Ext.apply(Ext.getCmp('checkNoB503').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('checkNoB503').getStore().removeAll();
		Ext.getCmp('checkNoB503').getStore().reload();
    },
    //对账单号选择
	checkNoB503Slect:function(){
		var strDetail1 = [];
		if(!Ext.isEmpty(Ext.getCmp('checkNoB503').getValue()) ){
			var d1={
					columnId:'a.check_no',
					value:Ext.getCmp('checkNoB503').getValue()
				};
			strDetail1.push(d1);
		}
		var jsonDetail = Ext.encode(strDetail1);
		var params = {
				strQuery  : jsonDetail
		};
	    Ext.Ajax.request({
				method:'POST',
				url:'cost_FinancialAction_getAccountGroupNo',
				timeout:1800000,
				params:params,
				success:function(response){
					
//					var data = Ext.decode(response.responseText);
					if(response.responseText!=='A'){
						Ext.getCmp('accountGroupNoB503').setValue(response.responseText);
					}else{
						Ext.getCmp('accountGroupNoB503').setValue('');
					}
				}
		   });	
	},
	//查看对账-计费项目选择
	billingProjectSelect: function(){		
		var strDetail1 = [];
		var d1={
			columnId:'a.owner_no',
			value:Ext.getCmp('ownerNoB503_2').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB503_2').getValue()) ){
			strDetail1.push(d1);
		}
		var d2={
				columnId:'a.billing_project',
				value:Ext.getCmp('billingProjectB503_2').getValue()
		};
		strDetail1.push(d2);
		var d3={
				columnId:'a.account_no',
				value:Ext.getCmp('accountNoB503_2').getValue()
		};
		strDetail1.push(d3);
		var d4={
				columnId:'a.check_no',
				value:Ext.getCmp('checkNoB503_2').getValue()
		};
		strDetail1.push(d4);
		var d5={
				columnId:'a.status',
				value:'13'
		};
		strDetail1.push(d5);
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
			strQuery  : jsonDetail
		};
		Ext.apply(Ext.getCmp('gridCostListB503').getStore().proxy.extraParams,str);
		Ext.getCmp('gridCostListB503').getStore().removeAll();
		Ext.getCmp('gridCostListB503').getStore().load();
	},
	//查看对账-刷新
	refreshB503Click:function(){
		var listDetail = [];
    	var a={
				columnId:'a.owner_no',
				value:Ext.getCmp('ownerNoB503_2').getValue()
			};
		listDetail.push(a);
		var b={
				columnId:'a.check_no',
				value:Ext.getCmp('checkNoB503_2').getValue()
			};
		listDetail.push(b);
		var c={
				columnId:'a.status',
				value:'13'
			};
		listDetail.push(c);
		var d={
				columnId:'a.account_no',
				value:Ext.getCmp('accountNoB503_2').getValue()
			};
		listDetail.push(d);
		var strJson = Ext.encode(listDetail);
		var wheresql = {
			strQuery : strJson,
			buildDate:null,
			beginDate : null,
			endDate : null
		};
		Ext.getCmp('billingProjectB503_2').setValue('');
		Ext.apply(Ext.getCmp('gridCostListB503').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('gridCostListB503').getStore().removeAll();
		Ext.getCmp('gridCostListB503').getStore().load();
	},
	//查看对账-导出
	exportB503Click:function(){
		commExport('gridCostListB503');
	},
	//杂项费用查看对账》刷新
	refreshB503_3Click:function(){
		var listDetail = [];
    	var a={
				columnId:'t1.owner_no',
				value:Ext.getCmp('ownerNoB503_3').getValue()
			};
		listDetail.push(a);
		var b={
				columnId:'t1.check_no',
				value:Ext.getCmp('checkNoB503_3').getValue()
			};
		listDetail.push(b);
		var c={
				columnId:'t1.status',
				value:'13'
			};
		listDetail.push(c);
		var strJson = Ext.encode(listDetail);
		var wheresql = {
			strQuery : strJson
		};
		Ext.getCmp('costNoB503_3').setValue('');
		Ext.apply(Ext.getCmp('gridCostOtherListB503').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('gridCostOtherListB503').getStore().removeAll();
		Ext.getCmp('gridCostOtherListB503').getStore().load();
	},
	//杂项费用查看对账》导出
	exportB503_3Click:function(){
		commExport('gridCostOtherListB503');
	},
	//杂项费用查看对账》费用代码选择
	costNoB503_3Select: function(){	
		var strDetail1 = [];
		var d1={
			columnId:'t1.owner_no',
			value:Ext.getCmp('ownerNoB503_3').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB503_3').getValue()) ){
			strDetail1.push(d1);
		}
		var d2={
				columnId:'t1.cost_no',
				value:Ext.getCmp('costNoB503_3').getValue()
		};
		strDetail1.push(d2);
		var d4={
				columnId:'t1.check_no',
				value:Ext.getCmp('checkNoB503_3').getValue()
		};
		strDetail1.push(d4);
		var d5={
				columnId:'t1.status',
				value:'13'
		};
		strDetail1.push(d5);
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
			strQuery  : jsonDetail
		};
		Ext.apply(Ext.getCmp('gridCostOtherListB503').getStore().proxy.extraParams,str);
		Ext.getCmp('gridCostOtherListB503').getStore().removeAll();
		Ext.getCmp('gridCostOtherListB503').getStore().load();
	}
});
//加载状态下拉列表
function statusSelect(){
	var detail = [];
	if(!Ext.isEmpty(Ext.getCmp('ownerNoB503').getValue())){
		var a={
				columnId:'a.owner_no',
				value:Ext.getCmp('ownerNoB503').getValue()
			};
		detail.push(a);
	}
	if(!Ext.isEmpty(Ext.getCmp('accountB503').getValue())){
		var a={
				columnId:'a.account_no',
				value:Ext.getCmp('accountB503').getValue()
			};
		detail.push(a);
	}
	var jsonDetail = Ext.encode(detail);
	var wheresql = {
		strQuery : jsonDetail
	};
	Ext.getCmp('statusB503').setValue('');
	Ext.apply(Ext.getCmp('statusB503').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('statusB503').getStore().removeAll();
	Ext.getCmp('statusB503').getStore().reload();
}
//加载对账单下拉列表
function checkNoSelect(){
	var detail = [];
	if(!Ext.isEmpty(Ext.getCmp('ownerNoB503').getValue())){
		var a={
				columnId:'a.owner_no',
				value:Ext.getCmp('ownerNoB503').getValue()
			};
		detail.push(a);
	}
	if(!Ext.isEmpty(Ext.getCmp('accountB503').getValue())){
		var a={
				columnId:'a.account_no',
				value:Ext.getCmp('accountB503').getValue()
			};
		detail.push(a);
	}
	if(!Ext.isEmpty(Ext.getCmp('statusB503').getValue())){
		var a={
				columnId:'a.status',
				value:Ext.getCmp('statusB503').getValue()
			};
		detail.push(a);
	}
	if(!Ext.isEmpty(Ext.getCmp('builDdateB503').getValue())){
		var a={
				columnId:'a.build_date',
				valueType:3,
				value:Ext.Date.format( Ext.getCmp('builDdateB503').getValue(),'Y-m-d')
			};
		detail.push(a);
	}
	var jsonDetail = Ext.encode(detail);
	var wheresql = {
		strWheresql:'',	
		strQuery : jsonDetail
	};
	Ext.getCmp('checkNoB503').setValue('');
	Ext.apply(Ext.getCmp('checkNoB503').getStore().proxy.extraParams,wheresql);
	Ext.getCmp('checkNoB503').getStore().removeAll();
	Ext.getCmp('checkNoB503').getStore().reload();
}