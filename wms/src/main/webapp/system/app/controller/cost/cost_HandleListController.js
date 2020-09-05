/**
 * 模块名称：手工录入消费清单
 * 模块编码：B905
 * 创建：hcx 20160707
 */
var typeB905='';               //用于判断点击save按钮时是用于保存还是修改
var rowindexB905=0;
Ext.define('cms.controller.cost.cost_HandleListController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.cost.cost_HandleListUI'],
	
	init:function(){
		this.control({//查询
			'cost_HandleListUI button[name=btnQueryB905]':{
				click:this.btnQueryB905Click
			},//设置光标跳到下一输入框
			'cost_HandleListAddOrEditWindow field':{
				specialkey:this.boxkeydown
			},//新增
			'cost_HandleListUI button[name=detailAdd]':{
				click:this.detailAdd
			},//货主编码选择
			'cost_HandleListUI combo[id=ownerNoUIB905]':{
				change:this.ownerNoUIB905Change
			},//费用标识选择
			'cost_HandleListUI combo[id=costFlagUIB905]':{
				change:this.costFlagUIB905Change
			},//状态选择
			'cost_HandleListUI combo[id=statusUIB905]':{
				change:this.statusUIB905Change
			},//计费项目选择
			'cost_HandleListUI combo[id=billingProjectUIB905]':{
				change:this.billingProjectUIB905Change
			},//结算日期选择
			'cost_HandleListUI datefield[id=amountDateUIB905]':{
				change:this.amountDateUIB905Change
			},//来源单号选择
			'remoteCombo[id=sourceNoUIB905]':{
				beforequery:this.sourceNoUIB905BeforeQuery
			},//修改
			'cost_HandleListUI button[name=detailEdit]':{
				click:this.detailEdit
			},//浏览
			'cost_HandleListUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},//删除
			'cost_HandleListUI button[name=detailDelete]':{
				click:this.detailDelete
			},//查找
			'cost_HandleListUI button[name=detailQuery]':{
				click:this.detailQuery
			},//导出
			'cost_HandleListUI button[name=detailExport]':{
				click:this.detailExport
			},//双击
			'cost_HandleListUI grid[id=gridB905]':{
					itemdblclick:this.detailBrowse
			},//新增--》根据货主和仓别加载计费项目
			'cost_HandleListAddOrEditWindow combo[id=owner_noB905]':{
				change:this.windowGetBillingProject
			},//新增--》计费项目选择
			'cost_HandleListAddOrEditWindow combo[id=billingProjectB905]':{
				select:this.billingProjectB905Select
			},//上一条记录
			'cost_HandleListAddOrEditWindow button[name=prev]':{
				click:this.prev
			},//下一条记录
			'cost_HandleListAddOrEditWindow button[name=next]':{
				click:this.next
			},//新增--》新增
			'cost_HandleListAddOrEditWindow button[name=add]':{
				click:this.add
			},//新增--》保存基础费用清单
			'cost_HandleListAddOrEditWindow button[name=save]':{
				click:this.save
			},//关闭窗口
			'cost_HandleListAddOrEditWindow button[name=close]':{
				click:this.colse
			}
		});
	},
	//初始化界面
	initializtion:function(){
		//加载货主下拉
		Ext.apply(Ext.getCmp('ownerNoUIB905').getStore().proxy.extraParams.strSelectFlag='1');
		Ext.getCmp('ownerNoUIB905').getStore().removeAll();
		Ext.getCmp('ownerNoUIB905').getStore().load();	
		//加载费用标识下拉
		Ext.apply(Ext.getCmp('costFlagUIB905').getStore().proxy.extraParams.strSelectFlag='2');
		Ext.getCmp('costFlagUIB905').getStore().removeAll();
		Ext.getCmp('costFlagUIB905').getStore().load();	
		//加载状态下拉
		Ext.apply(Ext.getCmp('statusUIB905').getStore().proxy.extraParams.strSelectFlag='3');
		Ext.getCmp('statusUIB905').getStore().removeAll();
		Ext.getCmp('statusUIB905').getStore().load();	
		//加载计费项目下拉
		Ext.apply(Ext.getCmp('billingProjectUIB905').getStore().proxy.extraParams.strSelectFlag='4');
		Ext.getCmp('billingProjectUIB905').getStore().removeAll();
		Ext.getCmp('billingProjectUIB905').getStore().load();	
		//加载来源单号下拉
		Ext.apply(Ext.getCmp('sourceNoUIB905').getStore().proxy.extraParams.strSelectFlag='6');
		Ext.getCmp('sourceNoUIB905').getStore().removeAll();
		Ext.getCmp('sourceNoUIB905').getStore().load();	
	},
	//查询
	btnQueryB905Click:function(){
		queryB905(7);
	},
	//设置光标跳到下一输入框
	boxkeydown:function(th,e,eOpts){
	  	if (e.getKey() == e.ENTER) {
	  		if(th.id=='amount_DateB905'){
				Ext.getCmp('valueB905').focus();
			}else if(th.id=='valueB905'){
				Ext.getCmp('sourceNoB905').focus();
			}else{
				th.nextSibling().focus();
			}
        }
	},
	//调用新增页面
	detailAdd:function(){
		Ext.create('cms.view.cost.window.cost_HandleListAddOrEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		addCommMenu5('menuWidgetB905');
		Ext.getCmp('serial_noB905').setValue('保存时自动生成');
		Ext.getCmp('owner_noB905').getStore().reload();
		Ext.getCmp('amount_DateB905').setValue(new Date());
		Ext.getCmp('statusB905').setValue('10');
		typeB905='add'; 
		Ext.getCmp('billingProjectB905').focus(false,3);

	},
	//货主选择
	ownerNoUIB905Change: function(){
		queryB905(1);
	},
	//费用标识选择
	costFlagUIB905Change:function(){
		queryB905(2);
	},
	//状态选择
	statusUIB905Change:function(){
		queryB905(3);
	},
	//计费项目选择
	billingProjectUIB905Change:function(){
		queryB905(4);
	},
	//结算日期选择
	amountDateUIB905Change:function(){
		queryB905(5);
	},
	//加载来源单号下拉
	sourceNoUIB905BeforeQuery:function(){
		queryB905(6);
	},
	//调用编辑窗口
	detailEdit:function(){
		var data = Ext.getCmp('gridB905').getSelectionModel().getSelection();
		
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{
			if(data[0].data.status != '10'){
				Ext.example.msg($i18n.prompt,'该消费清单不是新建状态，不能修改');
	            return;
			}
			Ext.create('cms.view.cost.window.cost_HandleListAddOrEditWindow',{
				title:$i18n.titleupdate
		    }).show();
			this.loadDataB905();
			commonSetMsterReadOnlyByArray(
					new Array('owner_noB905','billingProjectB905','amount_DateB905'),true);
			commonMenu5PrevOrNext('menuWidgetB905','gridB905',0);
			updateCommMenu5('menuWidgetB905');
			typeB905='edit';
			Ext.getCmp('valueB905').focus(false,4);
			rowindexB905=data[0].index;

		}
	},
	//浏览
	detailBrowse:function(){
		var objBrowseData = Ext.getCmp('gridB905').getSelectionModel().getSelection();
		if (objBrowseData.length != 0) {
			Ext.create('cms.view.cost.window.cost_HandleListAddOrEditWindow',{
				title:$i18n.titlebrowse
		    }).show();
			typeB905='browse';
			browseCommMenu5('menuWidgetB905');
			commonMenu5PrevOrNext('menuWidgetB905','gridB905',0);
			commonSetFieldReadOnly('cost_HandleListAddOrEditForm',true);
			this.loadDataB905();
        }else{
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
        }
	},
	//查找
	detailQuery:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		Ext.getCmp('ownerNoUIB905').setValue(null);
		Ext.getCmp('billingProjectUIB905').setValue(null);
		queryModuleId='B905';
		queryGrid='gridB905'; 
	},
	//导出
	detailExport:function(){
		commExport('gridB905');
	},
	//删除
	detailDelete:function(){
		typeB905='delete';
		var cust=Ext.getCmp('gridB905').getSelectionModel().getSelection();
		if(cust.length>0)
		{
			if(cust[0].data.status != '10'){
				Ext.example.msg($i18n.prompt,$i18n_prompt.expensesListCanNotDelete);
	            return;
			}
			Ext.Msg.confirm($i18n.prompt, $i18n_prompt.deleteOrNot,function(button, text){
				if (button == 'yes') {
					
					var params = {
							strOwnerNo : cust[0].data.ownerNo, 
							strSerialNo : cust[0].data.serialNo,
					};
					Ext.Ajax.request({
						method:'post',
						url:'cost_HandleListAction_deleteHandleList',
						params:params,
					    success:function(response){
							var data=Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n.prompt,data.msg);
								Ext.getCmp('gridB905').getStore().reload();
								Ext.getCmp('ownerNoUIB905').setValue('');
								Ext.getCmp('ownerNoUIB905').store.removeAll();
								Ext.getCmp('ownerNoUIB905').store.reload();
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
	//根据货主查找相应的数据，并加载计费项目
	windowGetBillingProject: function(){		
		var strDetail1 = [];
		var d1={
			columnId:'t1.owner_no',
			value:Ext.getCmp('owner_noB905').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('owner_noB905').getValue())){
			strDetail1.push(d1);
		}
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				strQuery  : jsonDetail
		};
		Ext.apply(Ext.getCmp('billingProjectB905').getStore().proxy.extraParams,str);
		Ext.getCmp('billingProjectB905').getStore().removeAll();
		Ext.getCmp('billingProjectB905').getStore().load();
	},
	//新增-计费项目选择
	billingProjectB905Select:function(){
		var strDetail1 = [];
		var d1={
			columnId:'a.owner_no',
			value:Ext.getCmp('owner_noB905').getValue()
		};
		strDetail1.push(d1);
		
		var d2={
				columnId:'a.billing_project',
				value:Ext.getCmp('billingProjectB905').getValue()
		};
		strDetail1.push(d2);
		
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				strQuery  : jsonDetail
		};
		Ext.Ajax.request({
			url:'cost_HandleListAction_getUnitPriceAndcostFlag',
			method:'post',
			params:str,
			success:function(response){
				var data=Ext.decode(response.responseText);
				Ext.getCmp('unitPriceB905').setValue(data[0].unitPrice);
				Ext.getCmp('costFlagB905').setValue(data[0].costFlag);
			}
		});		
	},	
	//实现上一页功能
	prev:function(){		
		commonMenu5PrevOrNext('menuWidgetB905','gridB905',-1);
		this.loadDataB905();
		if(typeB905='edit'){
			Ext.getCmp('valueB905').focus(false,4);
		}else if(typeB905='browse'){
			Ext.getCmp('valueB905').focus(true,4);
		}
		rowindexB905=rowindexB905-1;
	},
	
	//实现下一页功能
	next:function(){
		commonMenu5PrevOrNext('menuWidgetB905','gridB905',1);
		this.loadDataB905();
		if(typeB905='edit'){
			Ext.getCmp('valueB905').focus(false,4);
		}else if(typeB905='browse'){
			Ext.getCmp('valueB905').focus(true,4);
		}
		rowindexB905=rowindexB905+1;
	},
	//新增窗口点击新增
	add:function(){
		Ext.getCmp('serial_noB905').setValue('保存时自动生成');
		Ext.getCmp('billingProjectB905').setValue('');
		Ext.getCmp('unitPriceB905').setValue('');
		Ext.getCmp('valueB905').setValue('');
		Ext.getCmp('costFlagB905').setValue('');
		Ext.getCmp('sourceNoB905').setValue('');
		Ext.getCmp('billingProjectB905').focus(false,3);
	},
	//保存手工录入消费清单
	save:function(){
		debugger;
		if(!commonCheckIsInputAll('cost_HandleListAddOrEditForm')){
			return;
		}
		var amountDate ='';
		var billingProject = Ext.getCmp('billingProjectB905').getValue();
		amountDate = Ext.getCmp('amount_DateB905').getValue();

		if(Ext.getCmp('cost_HandleListAddOrEditForm').getForm().isValid()){
		var cust=Ext.getCmp('gridB905').getSelectionModel().getSelection()[0];
		if(!Ext.isEmpty(Ext.getCmp('serial_noB905').getValue())){
			if(Ext.getCmp('serial_noB905').getValue()=='保存时自动生成'){
				//校验计费项目与结算日期是否唯一
				Ext.Ajax.request({
					url : 'cost_HandleListAction_billingProjectCheck',
					params : {
						strOwnerNo:Ext.getCmp('owner_noB905').getValue(),
						strBillingProject:Ext.getCmp('billingProjectB905').getValue(),
						strAmountDate:Ext.Date.format(Ext.getCmp('amount_DateB905').getValue(),'Y-m-d')
					},
					async : false,
					success : function(response) {
						var res = Ext.decode(response.responseText);
				    	if(res!=''){
				    		Ext.example.msg($i18n_prompt.prompt,'该计费项目所选结算日期已经存在，请重新选择！');
				    		Ext.getCmp('amount_DateB905').setValue(null);
				    		Ext.getCmp('amount_DateB905').focus();
				    	}else{
				    		Ext.Ajax.request({
								url:'cost_HandleListAction_getSerialNo',
								method:'post',
								async : false,
								success:function(response){
									var data=Ext.decode(response.responseText);
									cost_HandleListStr={
											id:{
												enterpriseNo:Ext.get('enterpriseNo').getValue(),
												warehouseNo:Ext.get('warehouseNo').getValue(),
												ownerNo:Ext.getCmp('owner_noB905').getValue(),
												serialNo:data.obj,
											},	
											billingProject:Ext.getCmp('billingProjectB905').getValue(),
											amountDate:new Date(Ext.Date.format(Ext.getCmp('amount_DateB905').getValue(),'Y/m/d')),
											value:Ext.getCmp('valueB905').getValue(),
											sourceNo:Ext.getCmp('sourceNoB905').getValue(),
											costFlag:Ext.getCmp('costFlagB905').getValue(),
											status:Ext.getCmp('statusB905').getValue(),
											rgstDate:typeB905=='add'?new Date():cust.data.rgstDate.time,
											rgstName:typeB905=='add'?Ext.get('workerNo').getValue():cust.data.rgstName,
											updtDate:typeB905=='add'?'':new Date(),
											updtName:typeB905=='add'?'':Ext.get('workerNo').getValue()
										};								
										Ext.Ajax.request({
											url:'cost_HandleListAction_saveCostHandleList',
											method:'post',
											async : false,
											params:{
												str:Ext.encode(cost_HandleListStr)
											},
											success:function(response){
												var re=Ext.decode(response.responseText);
												if(re.isSucc){
													Ext.example.msg($i18n.prompt,re.msg);
													Ext.getCmp('serial_noB905').setValue(data.obj);
													commonSetMsterReadOnlyByArray(
															new Array('owner_noB905','billingProjectB905','amount_DateB905'),true);
													if(typeof(Ext.getCmp('gridB905'))!=='undefined'){
														Ext.getCmp('gridB905').store.reload();
													}
													Ext.getCmp('ownerNoUIB905').store.removeAll();
													Ext.getCmp('ownerNoUIB905').store.reload();
												}else{
													Ext.example.msg($i18n.prompt,data.msg+re.obj);
												}
												
											}
										});
								}
							});
				    	}
					}
				});
				
			}else{
				cost_HandleListStr={
						id:{
							enterpriseNo:Ext.get('enterpriseNo').getValue(),
							warehouseNo:Ext.get('warehouseNo').getValue(),
							ownerNo:Ext.getCmp('owner_noB905').getValue(),
							serialNo:Ext.getCmp('serial_noB905').getValue(),
						},
						billingProject:Ext.getCmp('billingProjectB905').getValue(),
						amountDate:new Date(Ext.Date.format(Ext.getCmp('amount_DateB905').getValue(),'Y/m/d')),
						value:Ext.getCmp('valueB905').getValue(),
						sourceNo:Ext.getCmp('sourceNoB905').getValue(),
						costFlag:Ext.getCmp('costFlagB905').getValue(),
						status:Ext.getCmp('statusB905').getValue(),
						rgstDate:typeB905=='add'?new Date():cust.data.rgstDate.time,
						rgstName:typeB905=='add'?Ext.get('workerNo').getValue():cust.data.rgstName,
						updtDate:typeB905=='add'?'':new Date(),
						updtName:typeB905=='add'?'':Ext.get('workerNo').getValue()
					};								
					Ext.Ajax.request({
						url:'cost_HandleListAction_saveCostHandleList',
						method:'post',
						params:{
							str:Ext.encode(cost_HandleListStr)
						},
						success:function(response){
							var data=Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.example.msg($i18n.prompt,data.msg);
								Ext.getCmp('gridB905').store.reload();
							}else{
								Ext.example.msg($i18n.prompt,data.msg+data.obj);
							}
						}
					});
			  }
		  }
	   }
	},
	//关闭窗口
	colse:function(){
		Ext.getCmp('cost_HandleListAddOrEditWindow').close();
	},
	//判断项目编号与结算日期是否唯一
	billingProjectCheck: function(){
		Ext.Ajax.request({
			url : 'cost_HandleListAction_billingProjectCheck',
			params : {
				strOwnerNo:Ext.getCmp('owner_noB905').getValue(),
				strBillingProject:Ext.getCmp('billingProjectB905').getValue(),
				strAmountDate:Ext.getCmp('amount_DateB905').getValue()
			},
			success : function(response) {
				var res = Ext.decode(response.responseText);
		    	if(res!=''){
		    		Ext.example.msg($i18n_prompt.prompt,$i18n_prompt.billingProjectIsExist);
		    		Ext.getCmp('amount_DateB905').setValue(null);
		    	}
			}
		});
	},
	//加载修改页面的数据
	loadDataB905:function(){
		var cust=Ext.getCmp('gridB905').getSelectionModel().getSelection();
		if(cust.length>0)
		{
			Ext.getCmp('serial_noB905').setValue(cust[0].data.serialNo);
			Ext.getCmp('owner_noB905').setValue(cust[0].data.ownerNo);
			Ext.getCmp('billingProjectB905').setValue(cust[0].data.billingProject);
			Ext.getCmp('amount_DateB905').setValue(cust[0].data.amountDateText);
			Ext.getCmp('unitPriceB905').setValue(cust[0].data.unitPrice);	
			Ext.getCmp('valueB905').setValue(cust[0].data.value);	
			Ext.getCmp('costFlagB905').setValue(cust[0].data.costFlag);	
			Ext.getCmp('sourceNoB905').setValue(cust[0].data.sourceNo);	
			Ext.getCmp('statusB905').setValue(cust[0].data.status);	
			if(cust[0].data.status!='10'){
				commonSetMsterReadOnlyByArray(
						new Array('valueB905','sourceNoB905'),true);
            }else{
            	commonSetMsterReadOnlyByArray(
						new Array('valueB905','sourceNoB905'),false);
            }
		}
		
	},
	getRowindexB905:function(){
 		return rowindexB905;
 	 },
 	getTypeB905:function(){
 		return typeB905;
 	 },
});
function queryB905(selectFlag){
	debugger;
	var strDetail1 = [];
	if(!Ext.isEmpty(Ext.getCmp('ownerNoUIB905').getValue()) &&
			Ext.getCmp('ownerNoUIB905').getValue()!='ALL'){
		var d={
				columnId:'a.owner_no',
				value:Ext.getCmp('ownerNoUIB905').getValue()
			};
		strDetail1.push(d);
	}
	if(selectFlag==2||selectFlag==3||selectFlag==4
			||selectFlag==5||selectFlag==6||selectFlag==7){
		if(!Ext.isEmpty(Ext.getCmp('costFlagUIB905').getValue())){
			var d={
					columnId:'a.cost_flag',
					value:Ext.getCmp('costFlagUIB905').getValue()
				};
			strDetail1.push(d);
		}
	}
	if(selectFlag==3||selectFlag==4||selectFlag==5
			||selectFlag==6||selectFlag==7){
		if(!Ext.isEmpty(Ext.getCmp('statusUIB905').getValue())){
			var d={
					columnId:'a.status',
					value:Ext.getCmp('statusUIB905').getValue()
				};
			strDetail1.push(d);
		}
	}
	if(selectFlag==4||selectFlag==5||selectFlag==6||selectFlag==7){
		if(!Ext.isEmpty(Ext.getCmp('billingProjectUIB905').getValue())){
			var d={
					columnId:'a.billing_project',
					value:Ext.getCmp('billingProjectUIB905').getValue()
				};
			strDetail1.push(d);
		}
	}
	if(selectFlag==5||selectFlag==6||selectFlag==7){
		if(!Ext.isEmpty(Ext.getCmp('amountDateUIB905').getValue()))
		{
			var strDtl={
					columnId:'a.amount_date',
					condition:3,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('amountDateUIB905').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}
	}
	if(selectFlag==6||selectFlag==7){
		if(!Ext.isEmpty(Ext.getCmp('sourceNoUIB905').getValue())){
			var d={
					columnId:'a.source_no',
					value:Ext.getCmp('sourceNoUIB905').getValue()
				};
			strDetail1.push(d);
		}
	}
	
	var jsonDetail = Ext.encode(strDetail1);
	var str=null;
	if(selectFlag==6){
		 str = {
			strWheresql	 : Ext.getCmp('sourceNoUIB905').getValue(),
			strQuery  : jsonDetail
		};
	}else{
		 str = {
			strQuery  : jsonDetail
		};
	}
	
	if(selectFlag==1){
		//加载费用标识下拉
		Ext.getCmp('costFlagUIB905').setValue('');
		Ext.apply(Ext.getCmp('costFlagUIB905').getStore().proxy.extraParams,str);
		Ext.getCmp('costFlagUIB905').getStore().removeAll();
		Ext.getCmp('costFlagUIB905').getStore().load();
	}
	if(selectFlag==1||selectFlag==2){
		//加载状态下拉
		Ext.getCmp('statusUIB905').setValue('');
		Ext.apply(Ext.getCmp('statusUIB905').getStore().proxy.extraParams,str);
		Ext.getCmp('statusUIB905').getStore().removeAll();
		Ext.getCmp('statusUIB905').getStore().load();
	}
	if(selectFlag==1||selectFlag==2||selectFlag==3){
		//加载计费项目下拉
		Ext.getCmp('billingProjectUIB905').setValue('');
		Ext.apply(Ext.getCmp('billingProjectUIB905').getStore().proxy.extraParams,str);
		Ext.getCmp('billingProjectUIB905').getStore().removeAll();
		Ext.getCmp('billingProjectUIB905').getStore().load();
	}
	if(selectFlag==1||selectFlag==2||selectFlag==3
			||selectFlag==4||selectFlag==5||selectFlag==6){
		//加载来源单号
		if(selectFlag!=6){
			Ext.getCmp('sourceNoUIB905').setValue('');
		}
		Ext.apply(Ext.getCmp('sourceNoUIB905').getStore().proxy.extraParams,str);
		Ext.getCmp('sourceNoUIB905').getStore().removeAll();
		Ext.getCmp('sourceNoUIB905').getStore().load();	
	}
	if(selectFlag==7){
		//加载手工录入消费清单
		Ext.apply(Ext.getCmp('gridB905').getStore().proxy.extraParams,str);
		Ext.getCmp('gridB905').getStore().removeAll();
		Ext.getCmp('gridB905').getStore().load();	
	}
}
