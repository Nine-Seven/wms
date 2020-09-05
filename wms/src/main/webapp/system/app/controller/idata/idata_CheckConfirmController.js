/**
 * 模块名称：验收确认
 * 模块编码：4601
 * 创建：Jun
 */
var printFlag=1;
Ext.define('cms.controller.idata.idata_CheckConfirmController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.idata.idata_CheckConfirmUI'
	         ],
	model:'',
	store:'',
	init:function()
	{
		this.control({
			//货主筛选
			'idata_CheckConfirmUI bdef_DefOwnerCombo[id=cmbOwnerNo4601_d1]':
			{
				change:this.cmbOwnerNo4601_d1Change
			},//进货单别筛选
			'idata_CheckConfirmUI wms_DefFieldValCombo[id=cmbImportType4601_d1]':
			{
				change:this.cmbImportType4601_d1Change
			},//供应商筛选
			'idata_CheckConfirmUI bdef_DefSupplierCombo[id=supplierNo4601]':
			{
				select:this.supplierNo4601Change
			},
			//单头选择事件
			'idata_CheckConfirmUI grid[id=grid_01_4601]':
			{
				selectionchange:this.grid_01_4601Selectionchange
			},
			'idata_CheckConfirmUI button[name=refresh]':
			{
				click:this.refreshClick
			},
			//导出 huangb 20160718
			'idata_CheckConfirmUI button[name=export]':{
				click:this.detailExport
				
			},
			//单品取消
			'idata_CheckConfirmUI button[id=btnItemCancel4601]':
			{
				click:this.btnItemCancel4601Click
			},//整单确认
			'idata_CheckConfirmUI button[id=btnConfirm4601]':
			{
				click:this.btnConfirm4601Click
			},
			//整单取消
			'idata_CheckConfirmUI button[id=btnCancle4601]':
			{
				click:this.btnCancle4601Click
			},//TAB页切换
			'idata_CheckConfirmUI tabpanel[id=tabPid4601]':{
				tabchange:this.tabPIdtabchange
			},
			//查询
			'idata_CheckConfirmUI commMenuWidget4[id=menu4601] button[name=query]':{
				click:this.query
			},///打印验收单
			'idata_CheckConfirmUI button[id=btnPrintCheck4601]':{
				click:this.printCheckNo
			}
		});
	},
	
	/** 导出验收结果
	 *  huangb 20160718
	 */
	detailExport:function(){
		var data =  Ext.getCmp('grid_01_4601').getSelectionModel().getSelection();
		//是否有差异需要根据进货单据明细判断,不能根据总量判断 huangb 20160815
		//if(data[0].get("sumPoQty") == data[0].get("sumImportQty")){
		if(data[0].get("isDiffFlag") == "0"){
			Ext.example.msg($i18n.prompt, "当前进货单没有差异,不允许导出！");
			return;
		}
		if(data[0].get("sendFlagText") == "已导出"){
			Ext.Msg.confirm($i18n.prompt,'当前单据已经导出,确定要再导出数据吗?',function(button){
				if(button == 'yes'){
					var queryStr = "";
					var proxy =  Ext.getCmp('grid_CheckResult_4601').getStore().proxy;
					var url = proxy.url;
					if(proxy.url.indexOf("./")!=-1){
						url = proxy.url.substring(2,proxy.url.length);
					}
					var params = proxy.extraParams;
					var paramsStr = "";
					var values = Object.keys(params);
					for (var i = 0; i < values.length; i++) {
						if(!Ext.isEmpty(params[values[i]]))
						{
							paramsStr += values[i]+"="+params[values[i]]+"&";
						}
					}			
					window.open(url+"?"+paramsStr+"requestFlag=2&page=1&start=0&limit=20");
					Ext.getCmp("grid_01_4601").getStore().reload();
				}else{
					return;
				}
			});
		}else{
			var queryStr = "";
			var proxy =  Ext.getCmp('grid_CheckResult_4601').getStore().proxy;
			var url = proxy.url;
			if(proxy.url.indexOf("./")!=-1){
				url = proxy.url.substring(2,proxy.url.length);
			}
			var params = proxy.extraParams;
			var paramsStr = "";
			var values = Object.keys(params);
			for (var i = 0; i < values.length; i++) {
				if(!Ext.isEmpty(params[values[i]]))
				{
					paramsStr += values[i]+"="+params[values[i]]+"&";
				}
			}			
			window.open(url+"?"+paramsStr+"requestFlag=2&page=1&start=0&limit=20");
			Ext.getCmp("grid_01_4601").getStore().reload();
		}	
	},

	initializtion:function()
	{
		disableButtonFunc(1,'#btnCancle4601',$i18n.exp_cancel);
		
		//显示变量0为不显示，1为显示
		var planBox4601_1=commonGetModuleField('4601','planBox')[0].flag;
		var planQmin4601_1=commonGetModuleField('4601','planQmin')[0].flag;
		var planDis4601_1=commonGetModuleField('4601','planDis')[0].flag;
		var packingUnit4601_1=commonGetModuleField('4601','packingUnit')[0].flag;
		var packingSpec4601_1=commonGetModuleField('4601','packingSpec')[0].flag;

		
		if(planBox4601_1==0){
			Ext.getCmp('planBox4601').setVisible(false);
			Ext.getCmp('planBox4601_2').setVisible(false);
			Ext.getCmp('planBox4601_3').setVisible(false);
		}
		if(planQmin4601_1==0){
			Ext.getCmp('planQmin4601').setVisible(false);
			Ext.getCmp('planQmin4601_2').setVisible(false);
			Ext.getCmp('planQmin4601_3').setVisible(false);
		}
		if(planDis4601_1==0){
			Ext.getCmp('planDis4601').setVisible(false);
			Ext.getCmp('planDis4601_2').setVisible(false);
			Ext.getCmp('planDis4601_3').setVisible(false);
		}
		if(packingUnit4601_1==0){
			Ext.getCmp('packingUnit4601').setVisible(false);
			Ext.getCmp('packingUnit4601_2').setVisible(false);
			Ext.getCmp('packingUnit4601_3').setVisible(false);
		}
		if(packingSpec4601_1==0){
			Ext.getCmp('packingSpec4601').setVisible(false);
			Ext.getCmp('packingSpec4601_2').setVisible(false);
			Ext.getCmp('packingSpec4601_3').setVisible(false);
		}
	
		/*Ext.getCmp('grid_01_4601').getStore().addListener({
			'load':function(th,records,successful,eOpts ){
				debugger
			if(th.count()>0){
				debugger
				Ext.getCmp('grid_01_4601').getSelectionModel().select(0);
			
			}
		 }
		});*/
		
	},
	
	//查询
	query:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=4601;
		queryGrid='grid_01_4601';	
	},
	
	cmbOwnerNo4601_d1Change:function(combo)
	{
		screenMaster4601();
	},
	
	cmbImportType4601_d1Change:function(combo)
	{
		screenMaster4601();
	},
	supplierNo4601Change:function(combo)
	{
		screenMaster4601();
	},
	grid_01_4601Selectionchange:function()
	{
		var data = Ext.getCmp('grid_01_4601').getSelectionModel().getSelection();
		if(data.length!=0)
		{
			Ext.getCmp('cmbOwnerNo4601_d2').getStore().add({
				value:String(data[0].get('ownerNo')),
				dropValue:'['+data[0].get('ownerNo')+']'+data[0].get('ownerName'),
				text:String(data[0].get('ownerName'))
		    });
			Ext.getCmp('cmbOwnerNo4601_d2').setValue(data[0].get('ownerNo'));
			
			Ext.getCmp('cmbImportType4601_d2').setValue(data[0].get('importType'));
			Ext.getCmp('txtSImportNo4601').setValue(data[0].get('SImportNo'));

			Ext.getCmp('cmbCheckWorker4601').getStore().add({
				value:String(data[0].get('checkWorker')),
				dropValue:'['+data[0].get('checkWorker')+']'+data[0].get('workerName'),
				text:String(data[0].get('workerName'))
		    });
			Ext.getCmp('cmbCheckWorker4601').setValue(data[0].get('checkWorker'));
			
			
			var strWheresql={
				strWheresql:data[0].get('checkNo')
			};
			Ext.apply(Ext.getCmp('grid_02_4601').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('grid_02_4601').getStore().removeAll();
			Ext.getCmp('grid_02_4601').getStore().load();
			
			var strWheresql={
				strWheresql:data[0].get('checkNo')
			};
			Ext.apply(Ext.getCmp('grid_03_4601').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('grid_03_4601').getStore().removeAll();
			Ext.getCmp('grid_03_4601').getStore().load();
			
			var strWheresql={
				strWheresql:data[0].get('SImportNo')
			};
			Ext.apply(Ext.getCmp('grid_04_4601').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('grid_04_4601').getStore().removeAll();
			Ext.getCmp('grid_04_4601').getStore().load();
			
			//进货验收结果 导出用 不显示在界面 huangb 20160719
			var strWheresql={
				strOwnerNo:data[0].get('ownerNo'),
			    strPoNo:data[0].get('poNo')
			};
			Ext.apply(Ext.getCmp('grid_CheckResult_4601').getStore().proxy.extraParams,strWheresql);
		}
	},
	
	refreshClick:function()
	{
//		Ext.getCmp('supplierNo4601').setValue('');
//		var strWheresql = {
//				strQuery : ''
//		};
//		Ext.apply(Ext.getCmp('grid_01_4601').getStore().proxy.extraParams,strWheresql);
//		Ext.getCmp('grid_01_4601').getStore().removeAll();
//		Ext.getCmp('grid_01_4601').getStore().reload();
		var strDetail1 = [];
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNo4601_d1').getValue()))
		{
			var d={
				columnId:'a.owner_no',
				value:Ext.getCmp('cmbOwnerNo4601_d1').getValue()
			};
			strDetail1.push(d);
		}
		if(!Ext.isEmpty(Ext.getCmp('cmbImportType4601_d1').getValue()))
		{
			var d={
				columnId:'a.import_type',
				value:Ext.getCmp('cmbImportType4601_d1').getValue()
			};
			strDetail1.push(d);
		}
		if(!Ext.isEmpty(Ext.getCmp('supplierNo4601').getValue()))
		{
			var d={
				columnId:'a.supplier_no',
				value:Ext.getCmp('supplierNo4601').getValue()
			};
			strDetail1.push(d);
		}
		var jsonDetail = Ext.encode(strDetail1);
		var params = {
				strQuery : jsonDetail
		};
		Ext.Ajax.request({
			url : 'idata_CheckAction_checkNoCheck',
			params : params,
			success : function(response) {
				var res = Ext.decode(response.responseText);
			    if(res!=''){
			    	Ext.getCmp('cmbOwnerNo4601_d1').getStore().removeAll();
					Ext.getCmp('cmbOwnerNo4601_d1').getStore().reload();
			    	Ext.apply(Ext.getCmp('grid_01_4601').getStore().proxy.extraParams,params);
			    	Ext.getCmp('grid_01_4601').getStore().removeAll();
			    	Ext.getCmp('grid_01_4601').getStore().load();
				}else{
					Ext.getCmp('cmbOwnerNo4601_d1').setValue('');
					Ext.getCmp('cmbOwnerNo4601_d1').getStore().removeAll();
					Ext.getCmp('cmbOwnerNo4601_d1').getStore().reload();
					Ext.getCmp('cmbImportType4601_d1').setValue('');
					Ext.getCmp('supplierNo4601').setValue('');
					var strWheresql = {
							strQuery : ''
					};
					Ext.apply(Ext.getCmp('grid_01_4601').getStore().proxy.extraParams,strWheresql);
					Ext.getCmp('grid_01_4601').getStore().removeAll();
					Ext.getCmp('grid_01_4601').getStore().reload();
				}
			}
		});
		
	},
	
	btnItemCancel4601Click:function()
	{
		var data = Ext.getCmp('grid_03_4601').getSelectionModel().getSelection();
		if(data.length!='0'){
			Ext.Msg.confirm($i18n_prompt.prompt,$i18n_prompt.deleteOrNot,function(button,text)
			{
				if(button=='yes')
				{
					Ext.getCmp('grid_03_4601').getStore().remove(data);					
				}
			});
		}else{
			Ext.example.msg($i18n_prompt.prompt, $i18n_prompt.choseDeleteRecord);
			return;
		}
	},
	
	btnConfirm4601Click:function()
	{
		if(!commonCheckIsInputAll('form_01_4601'))
		{
			return;
		}
		var gridcount=Ext.getCmp("grid_02_4601").getStore().getCount();
		if(gridcount>0)
		{
			if(!commonCheckdetailgrid('grid_02_4601',0,gridcount-1))
			{
				return;
			}	
		}else{			
			Ext.example.msg($i18n.prompt,$i18n_prompt.tableCannotBeNull);
			return;
		}
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
			return;
		}
		var params={
			strSImportNo:Ext.getCmp('txtSImportNo4601').getValue()
		};
		Ext.Ajax.request({
			method:'post',
			url:'idata_CheckAction_checkPalTmp',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				
				if(data.isSucc){	
					Ext.MessageBox.confirm = function(title, msg, fn, scope) { 
						this.show({ 
					    	title : title, 
						    msg : msg, 
					    	buttons : this.YESNOCANCEL, 
						    fn : fn, 
						    scope : scope, 
						    icon : this.QUESTION 
						    }); 
						return this; 
					};
						
					Ext.Msg.confirm($i18n.prompt,'是否打印？(是：打印验收清单  | 否：不打印)',function(button, text) 
					{
						if (button == 'yes') 
						{
							printFlag='1';
							confirm4601();
						}else if(button=='no'){
							printFlag='0';
							confirm4601();
						}
					});
					
					
					Ext.MessageBox.confirm = function(title, msg, fn, scope) { 
						this.show({ 
					    	title : title, 
						    msg : msg, 
					    	buttons : this.YESNO, 
						    fn : fn, 
						    scope : scope, 
						    icon : this.QUESTION 
						    }); 
						return this; 
					};
				}else{
					Ext.example.msg($i18n.prompt,$i18n.withoutAcceptanceOfGoods);
					return;
				}
			}
		});
	},
	btnCancle4601Click:function()
	{
		if(!commonCheckIsInputAll('form_01_4601'))
		{
			return;
		}
		var gridcount=Ext.getCmp("grid_04_4601").getStore().getCount();
		if(gridcount>0)
		{
			if(!commonCheckdetailgrid('grid_04_4601',0,gridcount-1))
			{
				return;
			}	
		}else{			
			Ext.example.msg($i18n.prompt,$i18n_prompt.tableCannotBeNull);
			return;
		}
		var gridcount01=Ext.getCmp("grid_01_4601").getStore().getCount();
		var checkNo = '';
		if(gridcount01>0){
			var rec = Ext.getCmp('grid_01_4601').getSelectionModel().getSelection();
			if(rec.length!=0){
				var checkNo = rec[0].get('checkNo');
			}
		}
		var params={
			strWheresql:checkNo,
			strSImportNo:Ext.getCmp('txtSImportNo4601').getValue()
		};
		Ext.Msg.confirm($i18n.prompt,$i18n_prompt.delOrNot,function(button, text) 
				{
					if (button == 'yes') 
					{
						Ext.Ajax.request({
							method:'post',
							url:'idata_CheckAction_delcheckPalTmp',
							params:params,
							success:function(response){
								var data = Ext.decode(response.responseText);
								if(data.isSucc){
									Ext.example.msg($i18n.prompt,data.msg);
									Ext.getCmp("grid_01_4601").getStore().reload();
								}else{
									Ext.Msg.alert($i18n.prompt,data.msg);
								}
							}
						});
					}
				});
	},
	tabPIdtabchange:function(tabPanel,newCard,oldCard,eOpts ){
		if(newCard.itemId=='tabPId4601_03i'){
			var gridcount=Ext.getCmp("grid_04_4601").getStore().getCount();
			if(gridcount==0){
				disableButtonFunc(1,'#btnCancle4601',$i18n.exp_cancel);
			}else{
				disableButtonFunc(0,'#btnCancle4601',$i18n.exp_cancel);
			}
		}else{
			disableButtonFunc(1,'#btnCancle4601',$i18n.exp_cancel);
		}
	},
	//打印验收单
	printCheckNo:function(){
		if(Ext.isEmpty(workSpaceNo)){
			Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
			return;
		}
		
		var data = Ext.getCmp('grid_01_4601').getSelectionModel().getSelection();
		if(data.length==0){ 
			Ext.example.msg('提示',"请选择要打印的单据！");
		}else{
			Ext.Ajax.request({
				url:'idata_CheckAction_printCheckNo',
				method:'post',
				params:{
					strWheresql:data[0].data.SCheckNo
				},
				success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.example.msg($i18n.prompt,data.msg);
					}
					else{
						Ext.Msg.alert($i18n.prompt,data.msg);
					}
					
				}
			});	
			
		}		
	},

});

/**
 * 筛选单头
 */
function screenMaster4601()
{
	debugger;
	var strDetail1 = [];
	if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNo4601_d1').getValue()))
	{
		var d={
			columnId:'a.owner_no',
			value:Ext.getCmp('cmbOwnerNo4601_d1').getValue()
		};
		strDetail1.push(d);
	}
	if(!Ext.isEmpty(Ext.getCmp('cmbImportType4601_d1').getValue()))
	{
		var d={
			columnId:'a.import_type',
			value:Ext.getCmp('cmbImportType4601_d1').getValue()
		};
		strDetail1.push(d);
	}
	if(!Ext.isEmpty(Ext.getCmp('supplierNo4601').getValue()))
	{
		var d={
			columnId:'a.supplier_no',
			value:Ext.getCmp('supplierNo4601').getValue()
		};
		strDetail1.push(d);
	}
	var jsonDetail = Ext.encode(strDetail1);
	var strWheresql = {
			strQuery : jsonDetail
	};
	Ext.apply(Ext.getCmp('grid_01_4601').getStore().proxy.extraParams,strWheresql);
	Ext.getCmp('grid_01_4601').getStore().removeAll();
	Ext.getCmp('grid_01_4601').getStore().load();
}

function confirm4601(){

	var msgShow = commonMegShow($i18n_prompt.saving_wait);
	var data = Ext.getCmp('grid_01_4601').getSelectionModel().getSelection();
	var strMaster=
	{
		enterpriseNo:Ext.get('enterpriseNo').getValue(),
		warehouseNo:Ext.get('warehouseNo').getValue(),
		SImportNo:data[0].get('SImportNo'),			
		SCheckNo:data[0].get('SCheckNo'),	
		poNo:data[0].get('poNo'),	
		checkWorker:Ext.get('workerNo').getValue(),
		unloadWorker:Ext.getCmp('cmbCheckWorker4601').getValue(),
		memo:Ext.getCmp('memo4601').getValue(),
		dockNo:workSpaceNo
	};
				
	var strJsonMaster = Ext.encode(strMaster);
	var params = 
	{
		strJsonMaster:strJsonMaster,
		strFlag:printFlag
	};
	Ext.Ajax.request({
		method:'POST',
		url:'idata_CheckAction_checkConfirm.action',
		params:params,
		success:function(response)
		{
			msgShow.hide();
			var data = Ext.decode(response.responseText);
			if(data.isSucc)
			{
				Ext.example.msg($i18n.prompt,data.msg);
				Ext.getCmp('grid_01_4601').store.reload();
				Ext.getCmp('grid_02_4601').getStore().removeAll();
				Ext.getCmp('grid_03_4601').getStore().removeAll();
			}else
			{
				Ext.Msg.alert($i18n.prompt,data.msg+data.obj);
			}			
		}
	});
}

