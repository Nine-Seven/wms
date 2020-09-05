/**
 * 模块名称：租仓消费清单查询Controller
 * 模块编码：B501
 * 创建：lich 
 */
Ext.define('cms.controller.bset.bill_Expenses_ListController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.bset.bill_Expenses_List_QueryUI'],
	init:function(){
		this.control({
			//查询
			'bill_Expenses_List_QueryUI button[name=btnQueryB501]':{
				click:this.btnQueryB501Click
			},//货主选择
			'bill_Expenses_List_QueryUI combo[id=ownerNoB501]':{
				change:this.selectAndGetBillingProject
			},			
			//开始日期离开
			'bill_Expenses_List_QueryUI datefield[id=dtBeginDateExpB501]':{
				blur:this.dtBeginDateExpB501Blur
			},
			//结束日期
			'bill_Expenses_List_QueryUI datefield[id=dtEndDateExpB501]':{
				blur:this.dtEndDateExpB501Blur
			},		
			//刷新
			'bill_Expenses_List_QueryUI button[name=refresh]':{
				click:this.refreshClick
			},	
			//导出
			'bill_Expenses_List_QueryUI button[name=export]':{
				click:this.exportClick
			}
		});
	},
	
	//查询
	btnQueryB501Click:function(th){		
		if(!commonCheckIsInputAll(th.ownerCt.id))
		{
			return;
		}	
		var listDetail = [];
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB501').getValue()))
		{
			var strDtl={
					columnId:'a.owner_no',
					value:Ext.getCmp('ownerNoB501').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('billingProjectB501').getValue()))
		{
			var strDtl={
					columnId:'a.billing_project',
					value:Ext.getCmp('billingProjectB501').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB501').getValue()))
		{
			var strDtl={
					columnId:'a.begin_date',
					condition:4,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('dtBeginDateExpB501').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('dtEndDateExpB501').getValue()))
		{
			var strDtl={
					columnId:'a.begin_date',
					condition:5,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('dtEndDateExpB501').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}		
		var strJson = Ext.encode(listDetail);
		var wheresql = {
			str : strJson
		};
		Ext.apply(Ext.getCmp('grid_Exp_B501').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_Exp_B501').getStore().removeAll();
		Ext.getCmp('grid_Exp_B501').getStore().load();		
	},
	//货主选择
	selectAndGetBillingProject: function(){		
		//获取计费项目
		var strDetail1 = [];
		var d1={
			columnId:'t1.owner_no',
			value:Ext.getCmp('ownerNoB501').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB501').getValue())){
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
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB501').getValue())){
			Ext.apply(Ext.getCmp('billingProjectB501').getStore().proxy.extraParams,str);
			Ext.getCmp('billingProjectB501').setValue('');
			Ext.getCmp('billingProjectB501').getStore().removeAll();
			Ext.getCmp('billingProjectB501').getStore().load();
		}else{
			Ext.getCmp('billingProjectB501').setValue(null);
			Ext.getCmp('billingProjectB501').getStore().removeAll();
		}
	},
	dtBeginDateExpB501Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtBeginDateExpB501').getValue()))
		{
			Ext.getCmp('dtBeginDateExpB501').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB501').getValue()))
		{
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,'开始日期不能大于结束日期！');
				Ext.getCmp('dtBeginDateExpB501').setValue('');
			}
		}
	},
	dtEndDateExpB501Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtEndDateExpB501').getValue()))
		{
			Ext.getCmp('dtEndDateExpB501').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtEndDateExpB501').getValue()))
		{
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,'结束日期不能小于开始日期！');
				Ext.getCmp('dtEndDateExpB501').setValue('');
			}
		}
	},	
	compareExpDate:function(){
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB501').getValue()) 
				&& !Ext.isEmpty(Ext.getCmp('dtEndDateExpB501').getValue()))
		{
			if(Ext.Date.format(Ext.getCmp('dtBeginDateExpB501').getValue(),'Ymd')>Ext.Date.format(Ext.getCmp('dtEndDateExpB501').getValue(),'Ymd'))
			{
				return false;
			}
		}
		return true;
	},
	refreshClick:function(){
		Ext.getCmp('grid_Exp_B501').getStore().removeAll();
		Ext.getCmp('grid_Exp_B501').getStore().load();
	},
	exportClick:function(){
		commExport('grid_Exp_B501');
	}
});

