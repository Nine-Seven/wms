/**
 * 模块名称：收款
 * 模块编码：B703
 * 创建：hcx 
 */
Ext.define('cms.controller.cost.cost_receivingController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.cost.cost_receivingUI'],
	init:function(){
		this.control({
			//查询
			'cost_receivingUI button[name=btnQueryB703]':{
				click:this.btnQueryB703Click
			},//刷新
			'cost_receivingUI button[id=refreshB703_1]':{
				click:this.refreshClick
			},//查找
			'cost_receivingUI button[id=queryB703_1]':{
				click:this.queryB703_1Query
			},//导出
			'cost_receivingUI button[id=exportB703_1]':{
				click:this.exportClick
			},//货主选择
			'cost_receivingUI combo[id=ownerNoB703]':{
				change:this.selectAndGetBillingProject
			},//开始日期离开
			'cost_receivingUI datefield[id=dtBeginDateExpB703]':{
				blur:this.dtBeginDateB703Blur
			},//结束日期
			'cost_receivingUI datefield[id=dtEndDateExpB703]':{
				blur:this.dtEndDateB703Blur
			},//查看对账》科目选择
			'wms_DefFieldValCombo[id=accountNoB703_2]':{
				select:this.accountNoB703_2Select
			},//查看对账》刷新
			'cost_FinancialWindow button[id=refreshB703]':{
				click:this.refreshB703Click
			},//回款-保存
			'cost_ReturnAmountWindow button[name=save]':{
				click:this.saveReturnAmount
			},//回款-关闭
			'cost_ReturnAmountWindow button[name=close]':{
				click:this.colse
			}	
		});
	},
	
	//查询
	btnQueryB703Click:function(th){		
		if(!commonCheckIsInputAll(th.ownerCt.id))
		{
			return;
		}	
		var listDetail = [];
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB703').getValue()))
		{
			var strDtl={
					columnId:'a.owner_no',
					value:Ext.getCmp('ownerNoB703').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('checkNoB703').getValue()))
		{
			var strDtl={
					columnId:'a.check_no',
					value:Ext.getCmp('checkNoB703').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('statusB703').getValue()))
		{
			var strDtl={
					columnId:'a.status',
					value:Ext.getCmp('statusB703').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB703').getValue()))
		{
			var strDtl={
					columnId:'a.build_date',
					condition:4,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('dtBeginDateExpB703').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('dtEndDateExpB703').getValue()))
		{
			var strDtl={
					columnId:'a.build_date',
					condition:5,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('dtEndDateExpB703').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}		
		var strJson = Ext.encode(listDetail);
		var wheresql = {
			strQuery : strJson
		};
		Ext.apply(Ext.getCmp('grid_Financial_B703').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_Financial_B703').getStore().removeAll();
		Ext.getCmp('grid_Financial_B703').getStore().load();		
	},	
	//开始日期选择
	dtBeginDateB703Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtBeginDateExpB703').getValue()))
		{
			Ext.getCmp('dtBeginDateExpB703').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB703').getValue()))
		{
			if(Ext.getCmp('dtBeginDateExpB703').getValue()>new Date()){
				Ext.example.msg($i18n.prompt,$i18n_prompt.beginDateCanNotMoreThanDoday);
				Ext.getCmp('dtBeginDateExpB703').setValue('');
				return;
			}
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.beginDateCanNotMoreThanEndDate);
				Ext.getCmp('dtBeginDateExpB703').setValue('');
			}
		}
	},
	//结束日期选择
	dtEndDateB703Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtEndDateExpB703').getValue()))
		{
			Ext.getCmp('dtEndDateExpB703').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtEndDateExpB703').getValue()))
		{
			if(Ext.getCmp('dtEndDateExpB703').getValue()>new Date()){
				Ext.example.msg($i18n.prompt,$i18n_prompt.endDateCanNotMoreThanDoday);
				Ext.getCmp('dtEndDateExpB703').setValue('');
				return;
			}
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.EndDateCanNotLessThanbeginDate);
				Ext.getCmp('dtEndDateExpB703').setValue('');
			}
		}
	},	
	//开始结束日期校验
	compareExpDate:function(){
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB703').getValue()) 
				&& !Ext.isEmpty(Ext.getCmp('dtEndDateExpB703').getValue()))
		{
			if(Ext.Date.format(Ext.getCmp('dtBeginDateExpB703').getValue(),'Ymd')>Ext.Date.format(Ext.getCmp('dtEndDateExpB703').getValue(),'Ymd'))
			{
				return false;
			}
		}
		return true;
	},
	//刷新
	refreshClick:function(){
		Ext.getCmp('ownerNoB703').setValue('');
		Ext.getCmp('checkNoB703').setValue('');
		Ext.getCmp('statusB703').setValue('');
		Ext.getCmp('dtBeginDateExpB703').setValue('');
		Ext.getCmp('dtEndDateExpB703').setValue('');
		var wheresql = {
			strQuery : null
		};
		Ext.apply(Ext.getCmp('grid_Financial_B703').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_Financial_B703').getStore().removeAll();
		Ext.getCmp('grid_Financial_B703').getStore().load();
	},
	//查找
	queryB703_1Query:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		Ext.getCmp('ownerNoB703').setValue(null);
		Ext.getCmp('checkNoB703').setValue(null);
		Ext.getCmp('statusB703').setValue(null);
		Ext.getCmp('dtBeginDateExpB703').setValue(null);
		Ext.getCmp('dtEndDateExpB703').setValue(null);
		queryModuleId='B703';
		queryGrid='grid_Financial_B703';
	},
	//导出
	exportClick:function(){
		commExport('grid_Financial_B703');
	},
	//货主选择
	selectAndGetBillingProject: function(){		
		var strDetail1 = [];
		var d1={
			columnId:'a.owner_no',
			value:Ext.getCmp('ownerNoB703').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB703').getValue()) ){
			strDetail1.push(d1);
		}
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				strQuery  : jsonDetail
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB703').getValue())){
			Ext.apply(Ext.getCmp('checkNoB703').getStore().proxy.extraParams,str);
			Ext.getCmp('checkNoB703').getStore().removeAll();
			Ext.getCmp('checkNoB703').getStore().load();
		}else{
			Ext.getCmp('checkNoB703').setValue(null);
			Ext.getCmp('checkNoB703').getStore().removeAll();
		}
	},
	//查看对账-科目选择
	accountNoB703_2Select: function(){		
		var strDetail1 = [];
		var d1={
			columnId:'a.owner_no',
			value:Ext.getCmp('ownerNoB703_2').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB703_2').getValue()) ){
			strDetail1.push(d1);
		}
		if(!Ext.isEmpty(Ext.getCmp('accountNoB703_2').getValue()) ){
			var d2={
					columnId:'a.account_no',
					value:Ext.getCmp('accountNoB703_2').getValue()
			};
			strDetail1.push(d2);
		}
		if(!Ext.isEmpty(Ext.getCmp('checkNoB703_2').getValue()) ){
			var d3={
					columnId:'a.check_no',
					value:Ext.getCmp('checkNoB703_2').getValue()
			};
			strDetail1.push(d3);
		}
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				str  : jsonDetail
		};
		Ext.apply(Ext.getCmp('gridFinanciaListB703').getStore().proxy.extraParams,str);
		Ext.getCmp('gridFinanciaListB703').getStore().removeAll();
		Ext.getCmp('gridFinanciaListB703').getStore().load();
	},
	//查看对账》刷新
	refreshB703Click:function(){
		var listDetail = [];
    	var a={
				columnId:'a.owner_no',
				value:Ext.getCmp('ownerNoB703_2').getValue()
			};
		listDetail.push(a);
		var b={
				columnId:'a.check_no',
				value:Ext.getCmp('checkNoB703_2').getValue()
			};
		listDetail.push(b);
//		var c={
//				columnId:'a.status',
//				value:'13'
//			};
//		listDetail.push(c);
		var d={
				columnId:'a.account_no',
				value:Ext.getCmp('accountNoB703_2').getValue()
			};
		listDetail.push(d);
		var strJson = Ext.encode(listDetail);
		var wheresql = {
			str : strJson,
			buildDate:null,
			beginDate : null,
			endDate : null
		};
		Ext.getCmp('accountNoB703_2').setValue('');
		Ext.apply(Ext.getCmp('gridFinanciaListB703').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('gridFinanciaListB703').getStore().removeAll();
		Ext.getCmp('gridFinanciaListB703').getStore().load();
	},
	//回款-保存
	saveReturnAmount:function(){
		if(Ext.isEmpty(Ext.getCmp('checkNo_2').getValue())){
    		Ext.example.msg($i18n.prompt,$i18n_prompt.inputCheckNo);
    		return;
    	}
		if(Ext.isEmpty(Ext.getCmp('realAmountB703').getValue())){
    		Ext.example.msg($i18n.prompt,$i18n_prompt.inputRealAmount);
    		return;
    	}
		if(Ext.isEmpty(Ext.getCmp('rgstNameB703').getValue())){
    		Ext.example.msg($i18n.prompt,$i18n_prompt.inputPayee);
    		return;
    	}
		var rowId='0';
		var detail = [];
		var a={
				columnId:'t1.owner_no',
				value:Ext.getCmp('ownerNoB703_2').getValue()
			};
		detail.push(a);
		var b={
				columnId:'t1.check_no',
				value:Ext.getCmp('checkNo_2').getValue()
			};
		detail.push(b);
		var jsonDetail = Ext.encode(detail);
		Ext.Ajax.request({
			url:'cost_ReceivingAction_getRowId',
			method:'post',
			async:false,
			params:{
				str:jsonDetail
			},
			success:function(response){
				var rec2=Ext.decode(response.responseText);
				rowId=rec2+1;
			}
		});
		var returnAmount={
			id:{
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.get('warehouseNo').getValue(),
				ownerNo:Ext.getCmp('ownerNoB703_2').getValue(),
				checkNo:Ext.getCmp('checkNo_2').getValue(),
				rowid:rowId
			},
			realAmount:Ext.getCmp('realAmountB703').getValue(),
		    rgstName:Ext.getCmp('rgstNameB703').getValue(),
			rgstDate:new Date(),
			costFlag:Ext.getCmp('costFlag_2').getValue()
		};
		var str=Ext.encode(returnAmount);
		Ext.Ajax.request({
			url:'cost_ReceivingAction_saveReturnAmount',
			async:false,
			method:'post',
			params:{
				str:str
			},
			success:function(response){
				var data=Ext.decode(response.responseText);
				if(data.isSucc){
            		Ext.getCmp('grid_return_amount').getStore().reload();
					var gridcount=Ext.getCmp('grid_return_amount').getStore().getCount();
					var a=0;
					if(gridcount>0){
						for(var j=0;j<gridcount;j++){
							var record=Ext.getCmp('grid_return_amount').getStore().getAt(j);
							a+=record.get('realAmount');
						}
					}
					if(Ext.getCmp('planAmountB703').getValue()-a-Ext.getCmp('realAmountB703').getValue()<=0){
						commonSetMsterReadOnlyByArray(
								new Array('checkNo_2','realAmountB703','rgstNameB703'),true);
	            		Ext.Ajax.request({
	            			url:'cost_ReceivingAction_update',
	            			method:'post',
	            			async:false,
	            			params:{
	            				strCheckNo:Ext.getCmp('checkNo_2').getValue(),
	            				strCostFlag:Ext.getCmp('costFlag_2').getValue(),
	            				strStatus:'13'
	            			},
	            			success:function(response){
	            				var re=Ext.decode(response.responseText);
	            				if(re.isSucc){
	            					Ext.example.msg($i18n.prompt,data.msg);
	            				}else{
	            					Ext.example.msg($i18n.prompt,re.msg);
	            				}
	            			}
	            		});
					}else{
						Ext.Ajax.request({
	            			url:'cost_ReceivingAction_update',
	            			method:'post',
	            			async:false,
	            			params:{
	            				strCheckNo:Ext.getCmp('checkNo_2').getValue(),
	            				strCostFlag:Ext.getCmp('costFlag_2').getValue(),
	            				strStatus:'11'
	            			},
	            			success:function(response){
	            				var re2=Ext.decode(response.responseText);
	            				if(re2.isSucc){
	            					commonSetMsterReadOnlyByArray(
	        								new Array('realAmountB703','rgstNameB703'),false);
	        						Ext.getCmp('realAmountB703').setValue('');
	        						Ext.getCmp('realAmountB703').focus(false, 3);
	            				}else{
	            					Ext.example.msg($i18n.prompt,re2.msg);
	            				}
	            			}
	            		});
					}
				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	},
	//关闭回款窗口
	colse:function(){
		Ext.getCmp('cost_ReturnAmountWindow').close();
		Ext.getCmp('grid_Financial_B703').getStore().reload();
	},
});

