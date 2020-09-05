/**
 * 模块名称：租仓消费清单查询Controller
 * 模块编码：B601
 * 创建：lich 
 */
Ext.define('cms.controller.bset.bill_Cost_ListController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.bset.bill_Cost_List_QueryUI'],
	init:function(){
		this.control({
			//查询
			'bill_Cost_List_QueryUI button[name=btnQueryB601]':{
				click:this.btnQueryB601Click
			},//货主选择
			'bill_Cost_List_QueryUI combo[id=ownerNoB601]':{
				change:this.selectAndGetBillingProject
			},	
			//计费项目选择
			'bill_Cost_List_QueryUI combo[id=billingProjectB601]':{
				change:this.selectbillingProjectB601
			},
			//开始日期离开
			'bill_Cost_List_QueryUI datefield[id=dtBeginDateCostB601]':{
				blur:this.dtBeginDateCostB601Blur
			},
			//结束日期
			'bill_Cost_List_QueryUI datefield[id=dtEndDateCostB601]':{
				blur:this.dtEndDateCostB601Blur
			},		
			//刷新
			'bill_Cost_List_QueryUI button[id=refresh]':{
				click:this.refreshClick
			},	
			//导出
			'bill_Cost_List_QueryUI button[id=export]':{
				click:this.exportClick
			},
			//修改
			'bill_Cost_List_QueryUI button[id=edit]':{
				click:this.editClick
			},
			//撤销
			'bill_Cost_List_QueryUI button[id=undo]':{
				click:this.undoClick
			},
			//上缴
			'bill_Cost_List_QueryUI button[id=paid]':{
				click:this.paidClick
			},//取消
			'bill_Cost_List_QueryUI button[id=undoCost]':{
				click:this.undoCostClick
			}
		});
	},
	//界面初始
	initializtion:function(){
		disableButtonFunc(1,'#undo',$i18n.undo);
		disableButtonFunc(1,'#paid','上缴');
		disableButtonFunc(1,'#undoCost',$i18n.cancel1);

	},
	
	//查询
	btnQueryB601Click:function(th){		
		if(!commonCheckIsInputAll(th.ownerCt.id))
		{
			return;
		}	
		var listDetail = [];
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB601').getValue()))
		{
			var strDtl={
					columnId:'a.owner_no',
					value:Ext.getCmp('ownerNoB601').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('billingProjectB601').getValue()))
		{
			var strDtl={
					columnId:'a.billing_project',
					value:Ext.getCmp('billingProjectB601').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('statusB601').getValue()))
		{
			var strDtl2={
					columnId:'a.status',
					value:Ext.getCmp('statusB601').getValue()
				};
			listDetail.push(strDtl2);
		}
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateCostB601').getValue()))
		{
			var strDtl={
					columnId:'a.billing_date',
					condition:4,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('dtBeginDateCostB601').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('dtEndDateCostB601').getValue()))
		{
			var strDtl={
					columnId:'a.billing_date',
					condition:5,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('dtEndDateCostB601').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}		
		var strJson = Ext.encode(listDetail);
		var wheresql = {
			str : strJson
		};
		Ext.apply(Ext.getCmp('grid_Cost_B601').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('grid_Cost_B601').getStore().removeAll();
		Ext.getCmp('grid_Cost_B601').getStore().load();		
	},	
	//货主选择
	selectAndGetBillingProject: function(){		
		//获取计费项目
		var strDetail1 = [];
		var d1={
			columnId:'t1.owner_no',
			value:Ext.getCmp('ownerNoB601').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB601').getValue()) ){
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
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB601').getValue())){
			Ext.apply(Ext.getCmp('billingProjectB601').getStore().proxy.extraParams,str);
			Ext.getCmp('billingProjectB601').setValue('');
			Ext.getCmp('billingProjectB601').getStore().removeAll();
			Ext.getCmp('billingProjectB601').getStore().load();
			Ext.apply(Ext.getCmp('statusB601').getStore().proxy.extraParams,str);
			Ext.getCmp('statusB601').setValue('');
			Ext.getCmp('statusB601').getStore().removeAll();
			Ext.getCmp('statusB601').getStore().reload();
		}else{
			Ext.getCmp('billingProjectB601').setValue(null);
			Ext.getCmp('billingProjectB601').getStore().removeAll();
		}
	},
	selectbillingProjectB601:function(){
		var strDetail1 = [];
		var d1={
			columnId:'t1.owner_no',
			value:Ext.getCmp('ownerNoB601').getValue()
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB601').getValue()) ){
			strDetail1.push(d1);
		}
		var d2={
				columnId:'t1.warehouse_no',
				value:Ext.get('warehouseNo').getValue()
		};
		strDetail1.push(d2);
		if(!Ext.isEmpty(Ext.getCmp('billingProjectB601').getValue())){
			var d3={
					columnId:'t1.billing_project',
					value:Ext.getCmp('billingProjectB601').getValue()
			};
			strDetail1.push(d3);
		}
		
		var jsonDetail = Ext.encode(strDetail1);
		var str = {
				str  : jsonDetail
		};
		if(!Ext.isEmpty(Ext.getCmp('ownerNoB601').getValue())){
			Ext.apply(Ext.getCmp('statusB601').getStore().proxy.extraParams,str);
			Ext.getCmp('statusB601').setValue('');
			Ext.getCmp('statusB601').getStore().removeAll();
			Ext.getCmp('statusB601').getStore().reload();
		}else{
			Ext.getCmp('statusB601').setValue(null);
			Ext.getCmp('statusB601').getStore().removeAll();
		}
	},
	dtBeginDateCostB601Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtBeginDateCostB601').getValue()))
		{
			Ext.getCmp('dtBeginDateCostB601').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateCostB601').getValue()))
		{
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,'开始日期不能大于结束日期！');
				Ext.getCmp('dtBeginDateCostB601').setValue('');
			}
		}
	},
	dtEndDateCostB601Blur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('dtEndDateCostB601').getValue()))
		{
			Ext.getCmp('dtEndDateCostB601').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('dtEndDateCostB601').getValue()))
		{
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,'结束日期不能小于开始日期！');
				Ext.getCmp('dtEndDateCostB601').setValue('');
			}
		}
	},	
	compareExpDate:function(){
		if(!Ext.isEmpty(Ext.getCmp('dtBeginDateCostB601').getValue()) 
				&& !Ext.isEmpty(Ext.getCmp('dtEndDateCostB601').getValue()))
		{
			if(Ext.Date.format(Ext.getCmp('dtBeginDateCostB601').getValue(),'Ymd')>Ext.Date.format(Ext.getCmp('dtEndDateCostB601').getValue(),'Ymd'))
			{
				return false;
			}
		}
		return true;
	},
	//刷新
	refreshClick:function(){
		Ext.getCmp('grid_Cost_B601').getStore().removeAll();
		Ext.getCmp('grid_Cost_B601').getStore().load();
		disableButtonFunc(0,'#edit',$i18n.titleupdate);
		disableButtonFunc(1,'#undo',$i18n.undo);
		disableButtonFunc(1,'#paid','上缴');
		disableButtonFunc(1,'#undoCost',$i18n.cancel1);
	},
	//导出
	exportClick:function(){
		commExport('grid_Cost_B601');
	},
	//修改 
	editClick:function(){
		disableButtonFunc(1,'#edit',$i18n.titleupdate);
		disableButtonFunc(0,'#undo',$i18n.undo);
		disableButtonFunc(0,'#paid','上缴');
		disableButtonFunc(0,'#undoCost',$i18n.cancel1);
	},
	//撤销
	undoClick:function(){
		disableButtonFunc(0,'#edit',$i18n.titleupdate);
		disableButtonFunc(1,'#undo',$i18n.undo);
		disableButtonFunc(1,'#paid','上缴');
		disableButtonFunc(1,'#undoCost',$i18n.cancel1);
	},
	//上缴
	paidClick:function(){
		var record = Ext.getCmp('grid_Cost_B601').getSelectionModel()
		.getSelection();
		if (record.length == 0) {
			Ext.example.msg($i18n.prompt,"请先选择您要操作的行!");
		    return;
	    } else {
	    	var detail=[];
	    	var a=0;
	    	for(var i=0;i<record.length;i++ ){
	    		if(record[i].data.status =='10'){
	    			var Bill_Cost_List={
							enterpriseNo:Ext.get('enterpriseNo').getValue(),
							warehouseNo:record[i].data.warehouseNo,
							ownerNo:record[i].data.ownerNo,
							billingNo:record[i].data.billingNo,
							billingDate:record[i].data.billingDate
						};
		    		detail.push(Bill_Cost_List);
	    		}
	    		
	    		if(record[i].data.status >'10'){
	    			a=a+1;
	    		}
	    	}
	    	if(a>0){
	    		Ext.example.msg($i18n.prompt,"所选费用中不全是初始状态的费用，请重新选择!");
			    return;
	    	}
			var str=Ext.encode(detail);
		
			Ext.Ajax.request({
				url : 'bill_ResetBillAction_paidCost',
				params : {
					jsonStr:str
				},
				success : function(response) {
					var data = Ext.decode(response.responseText);
			    	if(data){
						Ext.example.msg($i18n.prompt,data.msg);
			    		Ext.getCmp('grid_Cost_B601').getStore().removeAll();
			    		Ext.getCmp('grid_Cost_B601').getStore().load();
			    		disableButtonFunc(0,'#edit',$i18n.titleupdate);
			    		disableButtonFunc(1,'#undo',$i18n.undo);
			    		disableButtonFunc(1,'#paid','上缴');
			    		disableButtonFunc(1,'#undoCost',$i18n.cancel1);
			    	}else{
						Ext.example.msg($i18n.prompt,data.msg);
			    	}
				}
			});
	    }
		
	},
	undoCostClick:function(){

		var record = Ext.getCmp('grid_Cost_B601').getSelectionModel()
		.getSelection();
		if (record.length == 0) {
			Ext.example.msg($i18n.prompt,"请先选择您要操作的行!");
		    return;
	    } else {
	    	var detail=[];
	    	var a=0;
	    	for(var i=0;i<record.length;i++ ){
	    		if(record[i].data.status =='10'){
	    			var Bill_Cost_List={
							enterpriseNo:Ext.get('enterpriseNo').getValue(),
							warehouseNo:record[i].data.warehouseNo,
							ownerNo:record[i].data.ownerNo,
							billingNo:record[i].data.billingNo,
							billingDate:record[i].data.billingDate
						};
		    		detail.push(Bill_Cost_List);
	    		}
	    		if(record[i].data.status >'10'){
	    			a=a+1;
	    		}
	    	}
	    	if(a>0){
	    		Ext.example.msg($i18n.prompt,"所选费用中不全是初始状态的费用，请重新选择!");
			    return;
	    	}
			var str=Ext.encode(detail);
		
			Ext.Ajax.request({
				url : 'bill_ResetBillAction_undoCost',
				params : {
					jsonStr:str
				},
				success : function(response) {
					var data = Ext.decode(response.responseText);
			    	if(data){
						Ext.example.msg($i18n.prompt,data.msg);
			    		Ext.getCmp('grid_Cost_B601').getStore().removeAll();
			    		Ext.getCmp('grid_Cost_B601').getStore().load();
			    		disableButtonFunc(0,'#edit',$i18n.titleupdate);
			    		disableButtonFunc(1,'#undo',$i18n.undo);
			    		disableButtonFunc(1,'#paid','上缴');
			    		disableButtonFunc(1,'#undoCost',$i18n.cancel1);
			    	}else{
						Ext.example.msg($i18n.prompt,data.msg);
			    	}
				}
			});
	    }
	}
	
});

