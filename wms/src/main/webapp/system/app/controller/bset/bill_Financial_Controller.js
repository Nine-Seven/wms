/**
 * 模块名称：租仓消费清单查询Controller
 * 模块编码：B701
 * 创建：lich 
 */
Ext.define('cms.controller.bset.bill_Financial_Controller',{
	extend:'Ext.app.Controller',
	requires:['cms.view.bset.bill_Financial_QueryUI'],
	init:function(){
		this.control({
			//查询
			'bill_Financial_QueryUI button[name=btnQueryB701]':{
				click:this.btnQueryB701Click
			},						
			//刷新
			'bill_Financial_QueryUI button[name=refresh]':{
				click:this.refreshClick
			},	
			//导出
			'bill_Financial_QueryUI button[name=export]':{
				click:this.exportClick
			},//货主选择
			'bill_Financial_QueryUI combo[id=ownerNoB701]':{
				change:this.selectAndGetBillingProject
			}	
		});
	},
	
	//查询
	btnQueryB701Click:function(th){		
		if(!commonCheckIsInputAll(th.ownerCt.id))
		{
			return;
		}	
		var listDetail = [];
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB701').getValue()))
		{
			var strDtl={
					columnId:'a.owner_no',
					value:Ext.getCmp('ownerNoB701').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('accountB301').getValue()))
		{
			var strDtl={
					columnId:'a.account_no',
					value:Ext.getCmp('accountB301').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('monthB701').getValue()))
		{
			var strDtl={
					columnId:'a.billing_month',
					value:Ext.Date.format(Ext.getCmp('monthB701').getValue(),'Ym')
				};
			listDetail.push(strDtl);
		}		
		var strJson = Ext.encode(listDetail);
		var wheresql = {
			str : strJson
		};
		Ext.apply(Ext.getCmp('grid_Financial_B701').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_Financial_B701').getStore().removeAll();
		Ext.getCmp('grid_Financial_B701').getStore().load();		
	},	
	dtBeginDateCostB701Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtBeginDateCostB701').getValue()))
		{
			Ext.getCmp('dtBeginDateCostB701').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateCostB701').getValue()))
		{
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,'开始日期不能大于结束日期！');
				Ext.getCmp('dtBeginDateCostB701').setValue('');
			}
		}
	},
	dtEndDateCostB701Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtEndDateCostB701').getValue()))
		{
			Ext.getCmp('dtEndDateCostB701').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtEndDateCostB701').getValue()))
		{
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,'结束日期不能小于开始日期！');
				Ext.getCmp('dtEndDateCostB701').setValue('');
			}
		}
	},	
	compareExpDate:function(){
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateCostB701').getValue()) 
				&& !Ext.isEmpty(Ext.getCmp('dtEndDateCostB701').getValue()))
		{
			if(Ext.Date.format(Ext.getCmp('dtBeginDateCostB701').getValue(),'Ymd')>Ext.Date.format(Ext.getCmp('dtEndDateCostB701').getValue(),'Ymd'))
			{
				return false;
			}
		}
		return true;
	},
	refreshClick:function(){
		Ext.getCmp('ownerNoB701').setValue('');
		Ext.getCmp('accountB301').setValue('');
		Ext.getCmp('grid_Financial_B701').getStore().removeAll();
		Ext.getCmp('grid_Financial_B701').getStore().load();
	},
	exportClick:function(){
		commExport('grid_Financial_B701');
	},
	//货主选择
	selectAndGetBillingProject: function(){		
		//获取计费项目
		var strDetail1 = [];
		var d1={
			columnId:'t1.owner_no',
			value:Ext.getCmp('ownerNoB701').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB701').getValue()) ){
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
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB701').getValue())){
			Ext.apply(Ext.getCmp('accountB301').getStore().proxy.extraParams,str);
			Ext.getCmp('accountB301').setValue('');
			Ext.getCmp('accountB301').getStore().removeAll();
			Ext.getCmp('accountB301').getStore().load();
		}else{
			Ext.getCmp('accountB301').setValue(null);
			Ext.getCmp('accountB301').getStore().removeAll();
		}
	}
	
});

