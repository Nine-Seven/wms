/**
 * 模块名称：集货作业
 * 模块编码：3931
 * 创建：czh
 */
var poNo3931 ='';
Ext.define('cms.controller.odata.odata_PackageIntoStockController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.odata.odata_PackageIntoStockUI'
	         ],
	model:'',
	store:'',
	init:function()
	{
		this.control({
			//刷新
			'odata_PackageIntoStockUI button[name=refresh]':
			{
				click:this.refreshClick
			},
			//单头选择事件
			'odata_PackageIntoStockUI grid[id=grid_01_3931]':
			{
				selectionchange:this.grid_01_3931Selectionchange
			},
			'odata_PackageIntoStockUI remoteCombo[id=packageNo3931]':
			{
				beforequery:this.packageNoBeforeQuery//提单号模糊查询
			},//货主选择
			'odata_PackageIntoStockUI combo[id=cmbOwnerNo3931]':{
				select:this.selectByOwnerNo
			},//状态选择
			'odata_PackageIntoStockUI combo[id=statusText3931]':{
				select:this.selectByStatus
			},//订单号ENTER
			'odata_PackageIntoStockUI field[id=sourceExpNo3931]':{
				specialkey:this.boxkeydown
			},//点击确认
			'odata_PackageIntoStockUI button[id=btnSave3931]':{
				click:this.save
			},//点击打印
			'odata_PackageIntoStockUI button[id=btnPrint3931]':{
				click:this.print
			},//点击单选框
			'odata_PackageIntoStockUI radiogroup[id=operateType3931]':{
				change:this.operateType3931Select
			},//导入
			'odata_PackageIntoStockUI button[name=upload]':{
				click:this.uploadClick
			}
		});
	},
	
	initializtion:function()
	{
	   Ext.getCmp("cmbOwnerNo3931").getStore().load();
	},
	getPoNo3931:function(){
 		 return poNo3931;
 	 },
 	 
 	 setPoNo3931:function(value){
 		poNo3931 =value;
 	 },
 	operateType3931Select:function(){
 		if(Ext.getCmp("printFaceSheet3931").disabled){
 			var data = Ext.getCmp('grid_01_3931').getSelectionModel().getSelection();
 			var listDetail1  =  [];
 			var strDtl = {
 					columnId:'a.status',
					value:'10'
 				};
 			listDetail1.push(strDtl);
 				var strWheresql={
 						strWheresql:data[0].get('poNo'),
 						strQuery:Ext.encode(listDetail1)
 					};
			Ext.apply(Ext.getCmp('grid_02_3931').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('grid_02_3931').getStore().removeAll();
			Ext.getCmp('grid_02_3931').getStore().load();
 			Ext.getCmp("printFaceSheet3931").enable(true);
 		}else{
 			var data = Ext.getCmp('grid_01_3931').getSelectionModel().getSelection();
 			var listDetail1  =  [];
 			var strDtl = {
 					columnId:'a.status',
					value:'12'
 				};
 			listDetail1.push(strDtl);
 				var strWheresql={
 						strWheresql:data[0].get('poNo'),
 						strQuery:Ext.encode(listDetail1)
 					};
			Ext.apply(Ext.getCmp('grid_02_3931').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('grid_02_3931').getStore().removeAll();
			Ext.getCmp('grid_02_3931').getStore().load();
 			Ext.getCmp("printFaceSheet3931").disable(true);
 			var hd_checker = Ext.getCmp("grid_02_3931").getEl().select('div.x-column-header-checkbox'); 
			var hd = hd_checker.first(); 
			//清空表格头的checkBox 
			if(hd.hasCls('x-grid-hd-checker-on')){ 
			hd.removeCls('x-grid-hd-checker-on'); 
			} 
 		}		
 	},
 	//打印
 	print:function(){
 		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
			return;
		}		
 		if(Ext.getCmp('printBuilt3931').getValue()){
 			var data = Ext.getCmp('grid_01_3931').getSelectionModel().getSelection();
 			if(data.length!=0){
 				var opType = Ext.getCmp('operateType3931').getValue();
 				var strWheresql={
	 	 					reportType:Ext.getCmp('printBuilt3931').inputValue,
	 	 					sourceExpNo:data[0].get('poNo'),
	 	 					paperType:Ext.getCmp('operateType3931').getValue().movetype==1?"RKJH":"CKJH",
	 	 				};
	 	 	 		Ext.Ajax.request({
	 	 				method:'POST',
	 	 				url:'odata_PackageAction_printPackageInfo',
	 	 				params:strWheresql,
	 	 				async:false,
	 	 				success:function(response)
	 	 				{
	 	 					var data = Ext.decode(response.responseText);
	 	 					if(data.isSucc)
	 	 					{
	 	 						Ext.example.msg('提示',data.obj+data.msg);
	 	 						
	 	 					}else
	 	 					{
	 	 						Ext.Msg.alert('提示',data.msg);											
	 	 						
	 	 					}
	 	 				}
	 	 			});	 			
 			}else{
 				Ext.example.msg('提示','请先选择头档！');
 	  			return;
 			}
 		}
 		if(Ext.getCmp('printFaceSheet3931').getValue()){
 			var data = Ext.getCmp('grid_02_3931').getSelectionModel().getSelection();
 	 		if(data.length!=0){
 	 			for(var i=0;i<data.length;i++){
 	 				var strWheresql={
 	 	 					reportType:Ext.getCmp('printFaceSheet3931').inputValue,
 	 	 					sourceExpNo:data[i].get('sourceexpNo'),
 	 	 					paperType:"JH",
 	 	 				};
 	 	 	 		Ext.Ajax.request({
 	 	 				method:'POST',
 	 	 				url:'odata_PackageAction_printPackageInfo',
 	 	 				params:strWheresql,
 	 	 				async:false,
 	 	 				success:function(response)
 	 	 				{
 	 	 					var data = Ext.decode(response.responseText);
 	 	 					if(data.isSucc)
 	 	 					{
 	 	 						Ext.example.msg('提示',data.obj+data.msg);
 	 	 						
 	 	 					}else
 	 	 					{
 	 	 						Ext.Msg.alert('提示',data.msg);											
 	 	 						
 	 	 					}
 	 	 				}
 	 	 			});
 	 			}
 	 			
 	 		}else{
 	 			Ext.example.msg('提示','请先选择打印包裹明细！');
 	  			return;
 	 		}
 		}				
 		
 	},
	//确认
	save:function(){
		if(Ext.isEmpty(Ext.getCmp('cmbOperateWorker3931').getValue())){
  			Ext.example.msg('提示','请先选择入库/出库人员');
  			return;
  		}
		var data = Ext.getCmp('grid_02_3931').getSelectionModel().getSelection();
		if(data.length!=0){
			for(var i=0;i<data.length;i++){
				poNo3931 = data[i].get('poNo');
				var strWheresql={
	  					ownerNo:data[i].get('ownerNo'),
						poNo:data[i].get('poNo'),
						sourceExpNo:data[i].get('sourceexpNo'),
						operateWorker:Ext.getCmp('cmbOperateWorker3931').getValue(),
						operateType:Ext.getCmp('operateType3931').getValue().movetype
						
					};
				Ext.Ajax.request({
					method:'POST',
					url:'odata_PackageAction_PackageIntoOrOutStock',
					params:strWheresql,
					async:false,
					success:function(response)
					{
						var data = Ext.decode(response.responseText);
						if(data.isSucc)
						{
							Ext.example.msg('提示',data.msg);
							
						}else
						{
							Ext.Msg.alert('提示',data.msg);											
							
						}
						Ext.getCmp('grid_01_3931').getStore().load();
					}
				});
			}
			
		}else{
			Ext.example.msg('提示','请先选择包裹明细！');
  			return;
		}
	},
	//设置光标跳到下一输入框
	boxkeydown:function(th,e,eOpts){
	  	if (e.getKey() == e.ENTER) {
	  		if(Ext.isEmpty(Ext.getCmp('cmbOperateWorker3931').getValue())){
	  			Ext.example.msg('提示','请先选择入库/出库人员');
	  			return;
	  		}
	  		var data = Ext.getCmp('grid_01_3931').getSelectionModel().getSelection();
	  		if(data.length!=0)
			{
	  			poNo3931 = data[0].get('poNo');
	  			var strWheresql={
	  					ownerNo:Ext.getCmp('cmbOwnerNo3931').getValue(),
						poNo:data[0].get('poNo'),
						sourceExpNo:Ext.getCmp('sourceExpNo3931').getValue(),
						operateWorker:Ext.getCmp('cmbOperateWorker3931').getValue(),
						operateType:Ext.getCmp('operateType3931').getValue()
						
					};
	  			Ext.Ajax.request({
					method:'POST',
					url:'odata_PackageAction_PackageIntoOrOutStock',
					params:strWheresql,
					async:false,
					success:function(response)
					{
						var data = Ext.decode(response.responseText);
						if(data.isSucc)
						{
							Ext.example.msg('提示',data.msg);
							
						}else
						{
							Ext.Msg.alert('提示',data.msg);											
							
						}
						Ext.getCmp('grid_01_3931').getStore().load();
					}
				});
			}
        }
	},
	getFlag:function(){
 		 return flag;
 	 },
 	 
 	 setFlag:function(value){
 		 flag =value;
 	 },
	//状态选择
	selectByStatus:function(){
		var strDetail11 = [];
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNo3931').getValue())&&
				Ext.getCmp('cmbOwnerNo3931').getValue()!="ALL"){
			var d2={
					columnId:'a.owner_no',
					value:Ext.getCmp('cmbOwnerNo3931').getValue()
				};
			strDetail11.push(d2);
		}		
		var d2={
				columnId:'a.status'	,
				value:Ext.getCmp('statusText3931').getValue()
					
		};
		strDetail11.push(d2);
		var jsonDetail1 = Ext.encode(strDetail11);
		var str1 = {
				strQuery:jsonDetail1
		};
		
		Ext.apply(Ext.getCmp('grid_01_3931').getStore().proxy.extraParams,str1);
		Ext.getCmp('grid_01_3931').getStore().removeAll();
		Ext.getCmp('grid_01_3931').getStore().load();		
	},
	//根据货主编码查找货主资料
	selectByOwnerNo:function(){	
		var strDetail11 = [];
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNo3931').getValue())&&Ext.getCmp('cmbOwnerNo3931').getValue()!="ALL"){
			var d2={
					columnId:'a.owner_no',
					value:Ext.getCmp('cmbOwnerNo3931').getValue()
					
			};
			strDetail11.push(d2);
		}		
		var jsonDetail1 = Ext.encode(strDetail11);
		var str1 = {
				strQuery:jsonDetail1,
		};
		Ext.apply(Ext.getCmp('grid_01_3931').getStore().proxy.extraParams,str1);
		Ext.getCmp('grid_01_3931').getStore().removeAll();
		Ext.getCmp('grid_01_3931').getStore().load();
		Ext.getCmp('statusText3931').setValue('');
		/*Ext.getCmp('statusText3931').getStore().removeAll();
		Ext.apply(Ext.getCmp('statusText3931').getStore().proxy.extraParams,str1);		
		Ext.getCmp('statusText3931').getStore().load();*/
	},
	//提单号模糊查询 
	packageNoBeforeQuery:function(){
		var strDetail11  =  [];
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNo3931').getValue())&&
				Ext.getCmp('cmbOwnerNo3931').getValue()!="ALL"){
			var d2={
					columnId:'a.owner_no',
					value:Ext.getCmp('cmbOwnerNo3931').getValue()
				};
			strDetail11.push(d2);
		}
		if(!Ext.isEmpty(Ext.getCmp('statusText3931').getValue())){
			var d2={
					columnId:'a.status'	,
					value:Ext.getCmp('statusText3931').getValue()
						
			};
			strDetail11.push(d2);
			
		}
		var params={
				strQuery:Ext.encode(strDetail11), 
				str:Ext.getCmp('packageNo3931').getValue()//提单号
			};
		
		Ext.apply(Ext.getCmp('packageNo3931').getStore().proxy.extraParams,params);
		Ext.getCmp('packageNo3931').getStore().removeAll();
		Ext.getCmp('packageNo3931').getStore().load();
  	  },
	grid_01_3931Selectionchange:function()
	{
		var data = Ext.getCmp('grid_01_3931').getSelectionModel().getSelection();
		if(data.length!=0)
		{
			var strWheresql={
					strWheresql:data[0].get('poNo'),
					strQuery:[]
				};
			Ext.apply(Ext.getCmp('grid_02_3931').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('grid_02_3931').getStore().removeAll();
			Ext.getCmp('grid_02_3931').getStore().load();
			var hd_checker = Ext.getCmp("grid_02_3931").getEl().select('div.x-column-header-checkbox'); 
			var hd = hd_checker.first(); 
			//清空表格头的checkBox 
			if(hd.hasCls('x-grid-hd-checker-on')){ 
			hd.removeCls('x-grid-hd-checker-on'); 
			} 
		}
	},
	
	refreshClick:function()
	{
		Ext.getCmp('grid_01_3931').getStore().removeAll();
		Ext.getCmp('grid_01_3931').getStore().reload();
		
	},
	//导入
	uploadClick:function(){
		
		Ext.create('cms.view.odata.window.odataPackageUploadWindow',
		{
			title:'上传'
		}).show();
	}
});

