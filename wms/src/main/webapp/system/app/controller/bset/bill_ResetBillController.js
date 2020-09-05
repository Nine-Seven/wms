/**
 * 模块名称：重置账单Controller
 * 模块编码：B401
 * 创建：lich 
 */
Ext.define('cms.controller.bset.bill_ResetBillController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.bset.bill_ResetBillUI'],
	
	init:function(){
		this.control({
			//重算消费清单
			'bill_ResetBillUI button[name=btnExpB401]':{
				click:this.btnExpB401Click
			},
			//重算费用明细
			'bill_ResetBillUI button[name=btnCostB401]':{
				click:this.btnCostB401Click
			},
			//重算账单
			'bill_ResetBillUI button[name=btnFinB401]':{
				click:this.btnFinB401Click
			},
			//开始日期离开
			'bill_ResetBillUI datefield[id=dtBeginDateExpB401]':{
				blur:this.dtBeginDateExpB401Blur
			},
			//结束日期
			'bill_ResetBillUI datefield[id=dtEndDateExpB401]':{
				blur:this.dtEndDateExpB401Blur
			},
			//费用明细》开始日期离开
			'bill_ResetBillUI datefield[id=dtBeginDateCostB401]':{
				blur:this.dtBeginDateCostB401Blur
			},
			//费用明细》结束日期
			'bill_ResetBillUI datefield[id=dtEndDateCostB401]':{
				blur:this.dtEndDateCostB401Blur
			},		
			//重算账单
			'bill_ResetBillUI monthfield[id=monthB401]':{
				change:this.monthB401Change
			}
		});
	},
	
	//消费清单重算
	btnExpB401Click:function(th){		
		if(!commonCheckIsInputAll(th.ownerCt.id))
		{
			return;
		}
		Ext.Msg.confirm('提示','确定重算消费清单？',function(button,text){
			if(button=='yes'){
				Ext.Ajax.request({
					url : 'bill_ResetBillAction_tscResetExp',
					params : {
						ownerNo:Ext.getCmp('ownerNoExpB401').getValue(),
						beginDate:Ext.getCmp('dtBeginDateExpB401').getValue(),
						endDate:Ext.getCmp('dtEndDateExpB401').getValue()
					},
					success : function(response) {
						var data = Ext.decode(response.responseText);
				    	if(data.isSucc){					
							Ext.example.msg($i18n.prompt,data.msg);
						}else{
							Ext.example.msg($i18n.prompt,data.msg);
						}
					}
				});
			}
		});		
	},
	
	//费用明细重算
	btnCostB401Click:function(th){
		if(!commonCheckIsInputAll(th.ownerCt.id))
		{
			return;
		}
		Ext.Msg.confirm('提示','确定重算费用明细？',function(button,text){
			if(button=='yes'){
				Ext.Ajax.request({
					url : 'bill_ResetBillAction_tscResetCost',
					params : {
						ownerNo:Ext.getCmp('ownerNoCostB401').getValue(),
						beginDate:Ext.getCmp('dtBeginDateCostB401').getValue(),
						endDate:Ext.getCmp('dtEndDateCostB401').getValue()
					},
					success : function(response) {
						var data = Ext.decode(response.responseText);
				    	if(data.isSucc){					
							Ext.example.msg($i18n.prompt,data.msg);
						}else{
							Ext.example.msg($i18n.prompt,data.msg);
						}
					}
				});
			}
		});		
	},
	
	//账单重算
	btnFinB401Click:function(th){
		if(!commonCheckIsInputAll(th.ownerCt.id))
		{
			return;
		}
		Ext.Msg.confirm('提示','确定重算账单？',function(button,text){
			if(button=='yes'){
				Ext.Ajax.request({
					url : 'bill_ResetBillAction_tscResetFin',
					params : {
						ownerNo:Ext.getCmp('ownerNoFinB401').getValue(),
						beginDate:Ext.Date.format(Ext.getCmp('monthB401').getValue(),'Ym')
					},
					success : function(response) {
						var data = Ext.decode(response.responseText);
				    	if(data.isSucc){					
							Ext.example.msg($i18n.prompt,data.msg);
						}else{
							Ext.example.msg($i18n.prompt,data.msg);
						}
					}
				});
			}
		});	
	},
	dtBeginDateExpB401Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtBeginDateExpB401').getValue()))
		{
			Ext.getCmp('dtBeginDateExpB401').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB401').getValue()))
		{
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,'开始日期不能大于结束日期！');
				Ext.getCmp('dtBeginDateExpB401').setValue('');
			}
		}
	},
	dtEndDateExpB401Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtEndDateExpB401').getValue()))
		{
			Ext.getCmp('dtEndDateExpB401').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtEndDateExpB401').getValue()))
		{
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,'结束日期不能小于开始日期！');
				Ext.getCmp('dtEndDateExpB401').setValue('');
			}
		}
	},	
	compareExpDate:function(){
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateExpB401').getValue()) 
				&& !Ext.isEmpty(Ext.getCmp('dtEndDateExpB401').getValue()))
		{
			if(Ext.Date.format(Ext.getCmp('dtBeginDateExpB401').getValue(),'Ymd')>Ext.Date.format(Ext.getCmp('dtEndDateExpB401').getValue(),'Ymd'))
			{
				return false;
			}
		}
		return true;
	},
	dtBeginDateCostB401Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtBeginDateCostB401').getValue()))
		{
			Ext.getCmp('dtBeginDateCostB401').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateCostB401').getValue()))
		{
			if(!this.compareCostDate())
			{
				Ext.example.msg($i18n.prompt,'开始日期不能大于结束日期！');
				Ext.getCmp('dtBeginDateCostB401').setValue('');
			}
		}
	},
	dtEndDateCostB401Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtEndDateCostB401').getValue()))
		{
			Ext.getCmp('dtEndDateCostB401').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtEndDateCostB401').getValue()))
		{
			if(!this.compareCostDate())
			{
				Ext.example.msg($i18n.prompt,'结束日期不能小于开始日期！');
				Ext.getCmp('dtEndDateCostB401').setValue('');
			}
		}
	},	
	compareCostDate:function(){
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateCostB401').getValue()) 
				&& !Ext.isEmpty(Ext.getCmp('dtEndDateCostB401').getValue()))
		{
			if(Ext.Date.format(Ext.getCmp('dtBeginDateCostB401').getValue(),'Ymd')>Ext.Date.format(Ext.getCmp('dtEndDateCostB401').getValue(),'Ymd'))
			{
				return false;
			}
		}
		return true;
	},	
	monthB401Change:function(field,value){
		if(!Ext.isEmpty(Ext.getCmp('monthB401').getValue()))
		{
			if(Ext.Date.format(Ext.getCmp('monthB401').getValue(),'Ym')>Ext.Date.format(new Date(),'Ym'))
			{
				Ext.example.msg($i18n.prompt,'月份不能大于当前年月！');
				Ext.getCmp('monthB401').setValue('');
			}
		}
	}
});

