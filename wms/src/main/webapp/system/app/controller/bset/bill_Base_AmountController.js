/**
 * 模块名称：费用清单维护管理Controller
 * 模块编码：B102
 * 创建：hcx 
 */
var typeB102='';               //用于判断点击save按钮时是用于保存还是修改
var rowindexB102=0;
var remarkTmpB102='';                //存放取值策略的参数说明
Ext.define('cms.controller.bset.bill_Base_AmountController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.bset.bill_Base_AmountUI'],
	
	init:function(){
		this.control({//设置光标跳到下一输入框
			'bill_Base_AmountAddOrEditWindow field':{
				specialkey:this.boxkeydown
			},//新增
			'bill_Base_AmountUI button[name=detailAdd]':{
				click:this.detailAdd
			},//根据货主和仓别加载计费项目
			'bill_Base_AmountUI combo[id=ownerNoUIB102]':{
				change:this.selectAndGetBillingProject
			},			
			//根据货主、仓别和计费项目查找对应的基础消费清单
			'bill_Base_AmountUI combo[id=billingProjectUIB102]':{
				select:this.getFormulasetWithCondition
			},//修改
			'bill_Base_AmountUI button[name=detailEdit]':{
				click:this.detailEdit
			},//浏览
			'bill_Base_AmountUI button[name=detailBrowse]':{
				click:this.detailBrowse
			},//双击
			'bill_Base_AmountUI grid[id=gridB102]':{
					itemdblclick:this.detailBrowse
			},//新增--》根据货主和仓别加载计费项目
			'bill_Base_AmountAddOrEditWindow combo[id=owner_noB102]':{
				change:this.windowGetBillingProject
			},//新增--》计费项目选择
			'bill_Base_AmountAddOrEditWindow combo[id=billingProjectB102]':{
				select:this.billingProjectB102Select
			},//上一条记录
			'bill_Base_AmountAddOrEditWindow button[name=prev]':{
				click:this.prev
			},//下一条记录
			'bill_Base_AmountAddOrEditWindow button[name=next]':{
				click:this.next
			},//新增--》新增
			'bill_Base_AmountAddOrEditWindow button[name=add]':{
				click:this.add
			},//新增--》保存基础费用清单
			'bill_Base_AmountAddOrEditWindow button[name=save]':{
				click:this.save
			},//关闭窗口
			'bill_Base_AmountAddOrEditWindow button[name=close]':{
				click:this.colse
			}
		});
	},
	
	//设置光标跳到下一输入框
	boxkeydown:function(th,e,eOpts){
	  	if (e.getKey() == e.ENTER) {
	  		if(th.id=='amount_DateB102'){
				Ext.getCmp('valueB102').focus();
			}else{
				th.nextSibling().focus();
			}
        }
	},
	//调用新增页面
	detailAdd:function(){
		Ext.create('cms.view.bset.window.bill_Base_AmountAddOrEditWindow',{
			title:$i18n.titleadd//新增
		}).show();
		addCommMenu5('menuWidgetB102');
		Ext.getCmp('serial_noB102').setValue('保存时自动生成');
		Ext.getCmp('owner_noB102').getStore().reload();
		Ext.getCmp('amount_DateB102').setValue(new Date());
		Ext.getCmp('flagB102').setValue('0');
		typeB102='add'; 
		Ext.getCmp('billingProjectB102').focus(false,3);

	},
	//根据货主查找相应的数据，并加载计费项目
	selectAndGetBillingProject: function(){		
		//获取计费项目
		var strDetail1 = [];
		var d1={
			columnId:'a.owner_no',
			value:Ext.getCmp('ownerNoUIB102').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoUIB102').getValue()) &&
				Ext.getCmp('ownerNoUIB102').getValue()!='ALL'){
			strDetail1.push(d1);
		}
		var d2={
				columnId:'a.warehouse_no',
				value:Ext.get('warehouseNo').getValue()
		};
		strDetail1.push(d2);
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				str  : jsonDetail
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoUIB102').getValue()) &&
				Ext.getCmp('ownerNoUIB102').getValue()!='ALL'){
			Ext.apply(Ext.getCmp('billingProjectUIB102').getStore().proxy.extraParams,str);
			Ext.getCmp('billingProjectUIB102').getStore().removeAll();
			Ext.getCmp('billingProjectUIB102').getStore().load();
		}else{
			Ext.getCmp('billingProjectUIB102').setValue(null);
			Ext.getCmp('billingProjectUIB102').getStore().removeAll();
		}
		
		//查询基础费用清单
		Ext.apply(Ext.getCmp('gridB102').getStore().proxy.extraParams,str);
		Ext.getCmp('gridB102').getStore().removeAll();
		Ext.getCmp('gridB102').getStore().load();	
	},
	//根据货主、仓别和计费项目查找基础消费清单
	getFormulasetWithCondition:function(){	
		var strDetail1 = [];
		var d1={
			columnId:'a.owner_no',
			value:Ext.getCmp('ownerNoUIB102').getValue()
		};
		strDetail1.push(d1);
		
		var d2={
				columnId:'a.warehouse_no',
				value:Ext.get('warehouseNo').getValue()
		};
		strDetail1.push(d2);
		
		var d3={
				columnId:'a.billing_project',
				value:Ext.getCmp('billingProjectUIB102').getValue()
		};
		strDetail1.push(d3);
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				str  : jsonDetail
		};
		Ext.apply(Ext.getCmp('gridB102').getStore().proxy.extraParams,str);
		Ext.getCmp('gridB102').getStore().removeAll();
		Ext.getCmp('gridB102').getStore().load();		
	},
	//调用编辑窗口
	detailEdit:function(){
		var data = Ext.getCmp('gridB102').getSelectionModel().getSelection();
		
		if(data.length==0){
			Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_update);
		}else{		
			Ext.create('cms.view.bset.window.bill_Base_AmountAddOrEditWindow',{
				title:$i18n.titleupdate
		    }).show();
			this.loadDataB102();
			commonSetMsterReadOnlyByArray(
					new Array('owner_noB102','billingProjectB102','unitPriceB102'),true);
			commonMenu5PrevOrNext('menuWidgetB102','gridB102',0);
			updateCommMenu5('menuWidgetB102');
			typeB102='edit';
			Ext.getCmp('valueB102').focus(false,4);

		}
	},
	//浏览
	detailBrowse:function(){
		var objBrowseData = Ext.getCmp('gridB102').getSelectionModel().getSelection();
		if (objBrowseData.length != 0) {
			Ext.create('cms.view.bset.window.bill_Base_AmountAddOrEditWindow',{
				title:$i18n.titlebrowse
		    }).show();
			typeB102='browse';
			browseCommMenu5('menuWidgetB102');
			commonMenu5PrevOrNext('menuWidgetB102','gridB102',0);
			commonSetFieldReadOnly('bill_Base_AmountAddOrEditForm',true);
			this.loadDataB102();
        }else{
        	Ext.example.msg($i18n.prompt,$i18n.prompt_please_select_the_rows_to_browse);
        }
	},
	//根据货主查找相应的数据，并加载计费项目
	windowGetBillingProject: function(){		
		var strDetail1 = [];
		var d1={
			columnId:'t1.owner_no',
			value:Ext.getCmp('owner_noB102').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('owner_noB102').getValue())){
			strDetail1.push(d1);
		}
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				str  : jsonDetail
		};
		Ext.apply(Ext.getCmp('billingProjectB102').getStore().proxy.extraParams,str);
		Ext.getCmp('billingProjectB102').getStore().removeAll();
		Ext.getCmp('billingProjectB102').getStore().load();
	},
	//新增-计费项目选择
	billingProjectB102Select:function(){
		var strDetail1 = [];
		var d1={
			columnId:'a.owner_no',
			value:Ext.getCmp('owner_noB102').getValue()
		};
		strDetail1.push(d1);
		
		var d2={
				columnId:'a.billing_project',
				value:Ext.getCmp('billingProjectB102').getValue()
		};
		strDetail1.push(d2);
		
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				str  : jsonDetail
		};
		Ext.Ajax.request({
			url:'bill_BaseAmountAction_getFixedValueAndBillingCycle',
			method:'post',
			params:str,
			success:function(response){
				var data=Ext.decode(response.responseText);
				Ext.getCmp('fixedValueB102').setValue(data[0].fixedValue);
				Ext.getCmp('unitPriceB102').setValue(data[0].unitPrice);
				Ext.getCmp('billingCycleB102').setValue(data[0].billingCycle);
			}
		});		
	},	
	//实现上一页功能
	prev:function(){		
		commonMenu5PrevOrNext('menuWidgetB102','gridB102',-1);
		this.loadDataB102();
		if(typeB102='edit'){
			Ext.getCmp('valueB102').focus(false,4);
		}else if(typeB102='browse'){
			Ext.getCmp('valueB102').focus(true,4);
		}
	},
	
	//实现下一页功能
	next:function(){
		commonMenu5PrevOrNext('menuWidgetB102','gridB102',1);
		this.loadDataB102();
		if(typeB102='edit'){
			Ext.getCmp('valueB102').focus(false,4);
		}else if(typeB102='browse'){
			Ext.getCmp('valueB102').focus(true,4);
		}
	},
	//新增窗口点击新增
	add:function(){
		Ext.getCmp('serial_noB102').setValue('保存时自动生成');
		Ext.getCmp('billingProjectB102').setValue('');
		Ext.getCmp('fixedValueB102').setValue('');
		Ext.getCmp('billingCycleB102').setValue('');
		Ext.getCmp('valueB102').setValue('');
		Ext.getCmp('billingProjectB102').focus(false,3);
	},
	//保存基础费用清单
	save:function(){
		if(!commonCheckIsInputAll('bill_Base_AmountAddOrEditForm')){
			return;
		}
		var amountDate ='';
		var billingProject = Ext.getCmp('billingProjectB102').getValue();
		var billingCycle = Ext.getCmp('billingCycleB102').getValue();
		amountDate = Ext.getCmp('amount_DateB102').getValue();
//        if(billingCycle=='1' || billingCycle=='2'){
//    	   amountDate = Ext.Date.format(Ext.getCmp('amount_DateB102').getValue(),'Ymd');
//        }
//        if(billingCycle=='3'){
//     	   amountDate = Ext.Date.format(Ext.getCmp('amount_DateB102').getValue(),'Ym');
//        }
		Ext.Ajax.request({
			url:'bill_BaseAmountAction_check',
			method:'post',
			params:{
				strOwnerNo:Ext.getCmp('owner_noB102').getValue(),
				billingProject:billingProject,
				amountDate:amountDate,
				billingCycle:billingCycle
			},
			success:function(response){
				var re = Ext.decode(response.responseText);
				if(re.isSucc){
					if(re.msg ='Y'){
			    		if(Ext.getCmp('bill_Base_AmountAddOrEditForm').getForm().isValid()){
						var cust=Ext.getCmp('gridB102').getSelectionModel().getSelection()[0];
						if(!Ext.isEmpty(Ext.getCmp('serial_noB102').getValue())){
							if(Ext.getCmp('serial_noB102').getValue()=='保存时自动生成'){
								Ext.Ajax.request({
									url:'bill_BaseAmountAction_getSerialNo',
									method:'post',
									success:function(response){
										var data=Ext.decode(response.responseText);
										bill_Base_AmountStr={
												id:{
													enterpriseNo:Ext.get('enterpriseNo').getValue(),
													warehouseNo:Ext.get('warehouseNo').getValue(),
													ownerNo:Ext.getCmp('owner_noB102').getValue(),
													serialNo:data.obj,
													billingProject:Ext.getCmp('billingProjectB102').getValue(),
												},	
												amountDate:new Date(Ext.Date.format(Ext.getCmp('amount_DateB102').getValue(),'Y/m/d')),
												value:Ext.getCmp('valueB102').getValue(),
												flag:Ext.getCmp('flagB102').getValue(),
												rgstDate:typeB102=='add'?new Date():cust.data.rgstDate.time,
												rgstName:typeB102=='add'?Ext.get('workerNo').getValue():cust.data.rgstName,
												updtDate:typeB102=='add'?'':new Date(),
												updtName:typeB102=='add'?'':Ext.get('workerNo').getValue()
											};								
											Ext.Ajax.request({
												url:'bill_BaseAmountAction_saveBillBaseAmount',
												method:'post',
												params:{
													str:Ext.encode(bill_Base_AmountStr)
												},
												success:function(response){
													var data=Ext.decode(response.responseText);
													Ext.example.msg($i18n.prompt,data.msg);
													if(typeof(Ext.getCmp('gridB102'))!=='undefined'){
														Ext.getCmp('gridB102').store.reload();
													}
												}
											});
									}
								});
							}else{
								bill_Base_AmountStr={
										id:{
											enterpriseNo:Ext.get('enterpriseNo').getValue(),
											warehouseNo:Ext.get('warehouseNo').getValue(),
											ownerNo:Ext.getCmp('owner_noB102').getValue(),
											serialNo:Ext.getCmp('serial_noB102').getValue(),
											billingProject:Ext.getCmp('billingProjectB102').getValue(),
										},	
										amountDate:new Date(Ext.Date.format(Ext.getCmp('amount_DateB102').getValue(),'Y/m/d')),
										value:Ext.getCmp('valueB102').getValue(),
										flag:Ext.getCmp('flagB102').getValue(),
										rgstDate:typeB102=='add'?new Date():cust.data.rgstDate.time,
										rgstName:typeB102=='add'?Ext.get('workerNo').getValue():cust.data.rgstName,
										updtDate:typeB102=='add'?'':new Date(),
										updtName:typeB102=='add'?'':Ext.get('workerNo').getValue()
									};								
									Ext.Ajax.request({
										url:'bill_BaseAmountAction_saveBillBaseAmount',
										method:'post',
										params:{
											str:Ext.encode(bill_Base_AmountStr)
										},
										success:function(response){
											var data=Ext.decode(response.responseText);
											Ext.example.msg($i18n.prompt,data.msg);
										}
									});
							}
						}
						
					}
			    	
					}
		    		
		    	}else{
		    		Ext.example.msg($i18n.prompt,re.msg);
		    		Ext.getCmp('amount_DateB102').setValue('');
		    		Ext.getCmp('amount_DateB102').focus(false,10);
		    		return;
		    	}
			}
		});
		

	},
	//关闭窗口
	colse:function(){
		Ext.getCmp('bill_Base_AmountAddOrEditWindow').close();
	},
	//加载修改页面的数据
	loadDataB102:function(){
		var cust=Ext.getCmp('gridB102').getSelectionModel().getSelection();
		if(cust.length>0)
		{
			Ext.getCmp('serial_noB102').setValue(cust[0].data.serialNo);
			Ext.getCmp('owner_noB102').setValue(cust[0].data.ownerNo);
			Ext.getCmp('billingProjectB102').setValue(cust[0].data.billingProject);
			Ext.getCmp('amount_DateB102').setValue(cust[0].data.amountDateText);
			Ext.getCmp('fixedValueB102').setValue(cust[0].data.fixedValue);	
			Ext.getCmp('unitPriceB102').setValue(cust[0].data.unitPrice);	
			Ext.getCmp('billingCycleB102').setValue(cust[0].data.billingCycle);	
			Ext.getCmp('valueB102').setValue(cust[0].data.value);	
			Ext.getCmp('flagB102').setValue(cust[0].data.flag);	
		}
	}
});

