/**
 * 模块名称：出货审核
 * 模块编码：3928
 * 创建：hj 
 */
var flag = '';
var sourceexpNoTest = '';

Ext.define('cms.controller.odata.odata_OutstockScanCheckController',{
	extend:'Ext.app.Controller',
	requires:['cms.view.odata.odata_OutstockScanCheckUI'],
	model:'',
	store:'',
	init:function(){
		this.control({//出货申请单选择---审核界面
			'remoteCombo[id=outStockCheckFirst3701]':{
				beforequery:this.sourceNoBeforeQuery
			},//客户编码选择---审核界面
			'remoteCombo[id=customNumber3701]':{
				change:this.customNoChange
			},//tab切换---审核界面
			'odata_OutstockScanCheckUI tabpanel[id=outstockCkeck1101]':{
				tabchange:this.outstockCkeck1101Tabchange
			},//开始日期离开---审核界面
			'odata_OutstockScanCheckUI datefield[id=scanBeginDate]':{
				blur:this.scanBeginDateBlur
			},//结束日期离开---审核界面
			'odata_OutstockScanCheckUI datefield[id=scanEndDate]':{
				blur:this.scanEndDateBlur
			},//查询---审核界面
			'odata_OutstockScanCheckUI button[id=findButton]':{
				click:this.btnQueryClick
			},
			//选择出货申请单列表---审核界面
			'odata_OutstockScanCheckUI grid[id=gridOutStockScan1101]':{
				selectionchange:this.gridOutStockScan1101
			},//审核---审核界面
			'odata_OutstockScanCheckUI button[id=checkButton]':{
				click:this.btnCheckClick
			},//开始日期离开---查询界面
			'odata_OutstockScanCheckUI datefield[id=scanBeginDate2]':{
				blur:this.scanBeginDateBlur2
			},//结束日期离开---查询界面
			'odata_OutstockScanCheckUI datefield[id=scanEndDate2]':{
				blur:this.scanEndDateBlur2
			},//查询---查询界面
			'odata_OutstockScanCheckUI button[id=findButton1]':{
				click:this.btnQueryClick2
			},//状态选择---查询界面
			'odata_OutstockScanCheckUI combo[id=cmbStatus3701]':{
				change:this.cmbStatus3701Select
			},//出货申请单选择---查询界面
			'remoteCombo[id=outStockCheckSecond3701]':{
				beforequery:this.sourceNoBeforeQuery2
			},//客户编码选择---查询界面
			'remoteCombo[id=customNumber3702]':{
				change:this.customNoChange2
			},
			
			
		});
		
	},
	
	initializtion:function(){
		flag = 'outStockScanTab';
	},
	
	//出货申请单选择---审核界面
	sourceNoBeforeQuery:function(){
		var listDetail = [];
		if(!Ext.isEmpty(Ext.getCmp('customNumber3701').getValue()))
		{
			var strDtl={
					columnId:'m.cust_no',
					value:Ext.getCmp('customNumber3701').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('scanBeginDate').getValue()))
		{
			var strDtl={
					columnId:'m.rgst_date',
					condition:4,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('scanBeginDate').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('scanEndDate').getValue()))
		{
			var strDtl={
					columnId:'m.rgst_date',
					condition:5,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('scanEndDate').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}	
		//判断tab
		if(flag == 'outStockScanTab'){
			var strDtl={
					columnId : 'm.status',
					value : '12'
				};
			listDetail.push(strDtl);
		}
		var strJson = Ext.encode(listDetail);
		
		var params={
			strWheresql:Ext.getCmp("outStockCheckFirst3701").getValue(),
			strQuery : strJson,
			strTableName : 'odata_exp_check_m'
		};
		Ext.apply(Ext.getCmp('outStockCheckFirst3701').getStore().proxy.extraParams,params);
		Ext.getCmp('outStockCheckFirst3701').getStore().removeAll();
		Ext.getCmp('outStockCheckFirst3701').getStore().load();
 	 },
 	 
 	//出货申请单选择---查询界面
 	sourceNoBeforeQuery2:function(){
		var listDetail = [];
		if(!Ext.isEmpty(Ext.getCmp('customNumber3702').getValue()))
		{
			var strDtl={
					columnId:'m.cust_no',
					value:Ext.getCmp('customNumber3702').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('scanBeginDate2').getValue()))
		{
			var strDtl={
					columnId:'m.rgst_date',
					condition:4,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('scanBeginDate2').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('scanEndDate2').getValue()))
		{
			var strDtl={
					columnId:'m.rgst_date',
					condition:5,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('scanEndDate2').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}
		
		//判断tab
		if(flag == 'outStockFindTab'){
			/*var strDtl={
					columnId : 'm.status',
					value : '12'
				};
			listDetail.push(strDtl);*/
		}
		//var strJson = Ext.encode(listDetail);
		
		var params={
			strWheresql:Ext.getCmp("outStockCheckSecond3701").getValue(),
			strTableName : 'odata_exp_m'
		};
		Ext.apply(Ext.getCmp('outStockCheckSecond3701').getStore().proxy.extraParams,params);
		Ext.getCmp('outStockCheckSecond3701').getStore().removeAll();
		Ext.getCmp('outStockCheckSecond3701').getStore().load();
 	 },
	
 	//客户编码选择---审核界面
 	customNoChange:function(){
 		var listDetail = [];
 		//加的
 		if(!Ext.isEmpty(Ext.getCmp('outStockCheckFirst3701').getValue()))
		{
			var strDtl={
					columnId:'m.sourceexp_no',
					value:Ext.getCmp('outStockCheckFirst3701').getValue()
				};
			listDetail.push(strDtl);
		}
 		//
		if(!Ext.isEmpty(Ext.getCmp('scanBeginDate').getValue()))
		{
			var strDtl={
					columnId:'m.rgst_date',
					condition:4,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('scanBeginDate').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('scanEndDate').getValue()))
		{
			var strDtl={
					columnId:'m.rgst_date',
					condition:5,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('scanEndDate').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}
		//判断tab
		if(flag == 'outStockScanTab'){
			var strDtl={
					columnId : 'm.status',
					value : '12'
				};
			listDetail.push(strDtl);
		}
		var strJson = Ext.encode(listDetail);
 		
 		var params={
 			strWheresql:Ext.getCmp("customNumber3701").getValue(),
 			strQuery : strJson,
 			strTableName : 'odata_exp_check_m'
 		};
 		Ext.apply(Ext.getCmp('customNumber3701').getStore().proxy.extraParams,params);
 		Ext.getCmp('customNumber3701').getStore().removeAll();
 		Ext.getCmp('customNumber3701').getStore().load();
  	 },
  	 
  	//客户编码选择---查询界面
  	customNoChange2:function(){
  		var listDetail = [];
  		//加的
 		if(!Ext.isEmpty(Ext.getCmp('outStockCheckSecond3701').getValue()))
		{
			var strDtl={
					columnId:'m.sourceexp_no',
					value:Ext.getCmp('outStockCheckSecond3701').getValue()
				};
			listDetail.push(strDtl);
		}
 		//
 		if(!Ext.isEmpty(Ext.getCmp('scanBeginDate2').getValue()))
 		{
 			var strDtl={
 					columnId:'m.rgst_date',
 					condition:4,
 					valueType:3,
 					value:Ext.Date.format(Ext.getCmp('scanBeginDate2').getValue(),'Y-m-d')
 				};
 			listDetail.push(strDtl);
 		}
 		if(!Ext.isEmpty(Ext.getCmp('scanEndDate2').getValue()))
 		{
 			var strDtl={
 					columnId:'m.rgst_date',
 					condition:5,
 					valueType:3,
 					value:Ext.Date.format(Ext.getCmp('scanEndDate2').getValue(),'Y-m-d')
 				};
 			listDetail.push(strDtl);
 		}
 		//判断tab
 		if(flag == 'outStockScanTab'){
 			/*var strDtl={
 					columnId : 'm.status',
 					value : '12'
 				};
 			listDetail.push(strDtl);*/
 		}
 		//var strJson = Ext.encode(listDetail);
  		var params={
  			strWheresql:Ext.getCmp("customNumber3702").getValue(),
  			strTableName : 'odata_exp_m'
  		};
  		Ext.apply(Ext.getCmp('customNumber3702').getStore().proxy.extraParams,params);
  		Ext.getCmp('customNumber3702').getStore().removeAll();
  		Ext.getCmp('customNumber3702').getStore().load();
   	 },
	  
  	//tab切换 
  	outstockCkeck1101Tabchange:function(tabPanel,newCard, oldCard,eOpts){
		if(newCard.title == "出库审核界面"){
			flag = 'outStockScanTab';
			//alert('1');
			
		}else if(newCard.title == "出库查询界面"){
			flag = 'outStockFindTab';
			//alert('2');
		}
	},
	
	//开始结束时间判断---审核界面
	scanBeginDateBlur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('scanBeginDate').getValue()))
		{
			Ext.getCmp('scanBeginDate').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('scanBeginDate').getValue()))
		{
			if(Ext.getCmp('scanBeginDate').getValue()>new Date()){
				Ext.example.msg($i18n.prompt,$i18n_prompt.beginDateCanNotMoreThanDoday);
				Ext.getCmp('scanBeginDate').setValue('');
				return;
			}
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.beginDateCanNotMoreThanEndDate);
				Ext.getCmp('scanBeginDate').setValue('');
			}
		}else{
			Ext.getCmp("outStockCheckFirst3701").setValue('');
			Ext.getCmp("customNumber3701").setValue('');
		}
	},
	scanEndDateBlur:function(field,value){
		if(!Ext.isDate(Ext.getCmp('scanEndDate').getValue()))
		{
			Ext.getCmp('scanEndDate').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('scanEndDate').getValue()))
		{
			if(Ext.getCmp('scanEndDate').getValue()>new Date()){
				Ext.example.msg($i18n.prompt,$i18n_prompt.endDateCanNotMoreThanDoday);
				Ext.getCmp('scanEndDate').setValue('');
				return;
			}
			if(!this.compareExpDate())
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.EndDateCanNotLessThanbeginDate);
				Ext.getCmp('scanEndDate').setValue('');
			}
		}else{
			Ext.getCmp("outStockCheckFirst3701").setValue('');
			Ext.getCmp("customNumber3701").setValue('');
		}
	},	
	compareExpDate:function(){
		if(!Ext.isEmpty(Ext.getCmp('scanBeginDate').getValue()) 
				&& !Ext.isEmpty(Ext.getCmp('scanEndDate').getValue()))
		{
			if(Ext.Date.format(Ext.getCmp('scanBeginDate').getValue(),'Ymd')>Ext.Date.format(Ext.getCmp('scanEndDate').getValue(),'Ymd'))
			{
				return false;
			}
		}
		return true;
	},
	
	//开始结束时间判断---查询界面
	scanBeginDateBlur2:function(field,value){
		if(!Ext.isDate(Ext.getCmp('scanBeginDate2').getValue()))
		{
			Ext.getCmp('scanBeginDate2').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('scanBeginDate2').getValue()))
		{
			if(Ext.getCmp('scanBeginDate2').getValue()>new Date()){
				Ext.example.msg($i18n.prompt,$i18n_prompt.beginDateCanNotMoreThanDoday);
				Ext.getCmp('scanBeginDate2').setValue('');
				return;
			}
			if(!this.compareExpDate2())
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.beginDateCanNotMoreThanEndDate);
				Ext.getCmp('scanBeginDate2').setValue('');
			}
		}else{
			Ext.getCmp("outStockCheckSecond3701").setValue('');
			Ext.getCmp("customNumber3702").setValue('');
		}
	},
	scanEndDateBlur2:function(field,value){
		if(!Ext.isDate(Ext.getCmp('scanEndDate2').getValue()))
		{
			Ext.getCmp('scanEndDate2').setValue('');
			return;
		}
		if(!Ext.isEmpty(Ext.getCmp('scanEndDate2').getValue()))
		{
			if(Ext.getCmp('scanEndDate2').getValue()>new Date()){
				Ext.example.msg($i18n.prompt,$i18n_prompt.endDateCanNotMoreThanDoday);
				Ext.getCmp('scanEndDate2').setValue('');
				return;
			}
			if(!this.compareExpDate2())
			{
				Ext.example.msg($i18n.prompt,$i18n_prompt.EndDateCanNotLessThanbeginDate);
				Ext.getCmp('scanEndDate2').setValue('');
			}
		}else{
			Ext.getCmp("outStockCheckSecond3701").setValue('');
			Ext.getCmp("customNumber3702").setValue('');
		}
	},	
	compareExpDate2:function(){
		if(!Ext.isEmpty(Ext.getCmp('scanBeginDate2').getValue()) 
				&& !Ext.isEmpty(Ext.getCmp('scanEndDate2').getValue()))
		{
			if(Ext.Date.format(Ext.getCmp('scanBeginDate2').getValue(),'Ymd')>Ext.Date.format(Ext.getCmp('scanEndDate2').getValue(),'Ymd'))
			{
				return false;
			}
		}
		return true;
	},
	
	//查询---审核界面
	btnQueryClick:function(th){		
		var listDetail = [];
		if(!Ext.isEmpty(Ext.getCmp('customNumber3701').getValue()))
		{
			var strDtl={
					columnId:'m.cust_no',
					value:Ext.getCmp('customNumber3701').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('outStockCheckFirst3701').getValue()))
		{
			var strDtl={
					columnId:'m.sourceexp_no',
					value:Ext.getCmp('outStockCheckFirst3701').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('scanBeginDate').getValue()))
		{
			var strDtl={
					columnId:'m.rgst_date',
					condition:4,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('scanBeginDate').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('scanEndDate').getValue()))
		{
			var strDtl={
					columnId:'m.rgst_date',
					condition:5,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('scanEndDate').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}	
		var strJson = Ext.encode(listDetail);
		
		var wheresql = {
			strQuery : strJson
		};
		Ext.apply(Ext.getCmp('gridOutStockScan1101').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('gridOutStockScan1101').getStore().removeAll();
		Ext.getCmp('gridOutStockScan1101').getStore().load();
		Ext.getCmp('out_Stock_ScanGrid').getStore().removeAll();
		//清空查询条件
		Ext.getCmp("outStockCheckFirst3701").setValue('');
		Ext.getCmp("customNumber3701").setValue('');
		Ext.getCmp("scanBeginDate").setValue('');
		Ext.getCmp("scanEndDate").setValue('');
		
	},
	
	//审核---审核界面
	btnCheckClick:function(th,selected,eOpts){
		if(sourceexpNoTest != ''){
			var params = {
					strSourceexpNo : sourceexpNoTest
				};
			
			Ext.Ajax.request({
				method:'post',
				url:'odata_ExpCheckAction_checkOrder.action',
				params:params,
				
			    success:function(response){
					var data=Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.example.msg($i18n.prompt,data.msg);
						Ext.getCmp('gridOutStockScan1101').getStore().reload();
					}else{
						Ext.Msg.alert($i18n.prompt,data.msg+data.obj);

						//Ext.example.msg($i18n.prompt,data.msg+data.obj);
					}
				}
			});
			
			
		}else{
			Ext.example.msg($i18n.prompt,'请选择要审核的单号！');
			//alert('请选择要审核的单号！');
		}
	},
	
	//查询---查询界面
	btnQueryClick2:function(th){		
		var listDetail = [];
		if(!Ext.isEmpty(Ext.getCmp('outStockCheckSecond3701').getValue()))
		{
			var strDtl={
					columnId:'t.sourceexp_no',
					value:Ext.getCmp('outStockCheckSecond3701').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('customNumber3702').getValue()))
		{
			var strDtl={
					columnId:'t.cust_no',
					value:Ext.getCmp('customNumber3702').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('customGoodsNumber3701').getValue()))
		{
			var strDtl={
					columnId:'t.article_no',
					value:Ext.getCmp('customGoodsNumber3701').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('cmbStatus3701').getValue()))
		{
			var strDtl={
					columnId:'t.statusDesc',
					value:Ext.getCmp('cmbStatus3701').getValue()
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('scanBeginDate2').getValue()))
		{
			var strDtl={
					columnId:'t.scan_date',
					condition:4,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('scanBeginDate2').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}
		if(!Ext.isEmpty(Ext.getCmp('scanEndDate2').getValue()))
		{
			var strDtl={
					columnId:'t.scan_date',
					condition:5,
					valueType:3,
					value:Ext.Date.format(Ext.getCmp('scanEndDate2').getValue(),'Y-m-d')
				};
			listDetail.push(strDtl);
		}	
		var strJson = Ext.encode(listDetail);
		
		var wheresql = {
			strQuery : strJson
		};
		Ext.apply(Ext.getCmp('out_Stock_FindGrid2').getStore().proxy.extraParams,wheresql);
		Ext.getCmp('out_Stock_FindGrid2').getStore().removeAll();
		Ext.getCmp('out_Stock_FindGrid2').getStore().load();
		//清空查询条件
		Ext.getCmp("outStockCheckSecond3701").setValue('');
		Ext.getCmp("customNumber3702").setValue('');
		Ext.getCmp("customGoodsNumber3701").setValue('');
		Ext.getCmp("cmbStatus3701").setValue('');   
		Ext.getCmp("scanBeginDate2").setValue('');
		Ext.getCmp("scanEndDate2").setValue('');
	},
	
	//选择出货申请单列表
	gridOutStockScan1101:function(th,selected,eOpts){
		if(selected.length!=0){
			var record = selected[0];
			var t = record.data.sourceexpNo;
			sourceexpNoTest = t;
			var listDetail = [];
			var strDtl={
					columnId:'m.sourceexp_no',
					value:t
				};
			listDetail.push(strDtl);
			var strJson = Ext.encode(listDetail);
			var wheresql = {
					strQuery : strJson
				};
			Ext.apply(Ext.getCmp('out_Stock_ScanGrid').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('out_Stock_ScanGrid').getStore().removeAll();
			Ext.getCmp('out_Stock_ScanGrid').getStore().load();
		}else{
			Ext.getCmp('out_Stock_ScanGrid').getStore().removeAll();
		}
	},
	
	//状态选择---查询界面
	cmbStatus3701Select:function(){
		//alert('选择状态');
		//Ext.getCmp("dtBeginDateExpB803").setValue('');
		//Ext.getCmp("dtEndDateExpB803").setValue('');
		//Ext.getCmp("sourceNoB803").setValue('');
	},
	
});






































