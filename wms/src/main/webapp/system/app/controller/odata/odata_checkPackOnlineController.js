/**
 * 模块名称：容器整理打包（电商，按单复核）
 * 模块代码：3916
 * chensr
 */
var strCheckNo;
var strFinishFlag = 'N';

Ext.define('cms.controller.odata.odata_checkPackOnlineController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.odata.odata_checkPackOnlineUI',
	          'cms.view.odata.window.odata_checkPackMeteWindow'
	],
	init:function(){
		this.control({//刷新		
			'odata_checkPackOnlineUI commMenuWidget4[id=menu3916] [name=refresh]':{
				click:this.menu3916RefreshClick
			},			
			//扫描码头号
			'odata_checkPackOnlineUI textfield[id=checkPlatNo3916]':{
				keypress:this.checkPlatNo3916Blur
			},//扫描工号
			'odata_checkPackOnlineUI textfield[id=cmbCheckWorker3916]':{
				keypress:this.cmbCheckWorker3916Keypress
			},//输入任务号/箱号
			'odata_checkPackOnlineUI textfield[id=checkNo3916]':{
				keypress:this.checkNo3916Keypress
			},//快递单号
			'odata_checkPackOnlineUI textfield[id=checkNo3916_2]':{
				//blur:this.checkNo3916_2Blur
				keypress:this.checkNo3916Keypress
			},/*//选择箱号
			'odata_checkPackOnlineUI grid[id=gridPackLabel3916]':
			{
				selectionchange:this.gridPackLabel3916Selectionchange
			},*///箱号改变，获取箱明细
			/*'odata_checkPackOnlineUI textfield[id=loadBoxs3916]':
			{
				change:this.boxChange
			},*///输入条码
			'odata_checkPackOnlineUI textfield[id=barcode3916]':{
				//blur:this.barcode3916Blur
				keypress:this.barcode3916Keypress
			},/*//回车事件
			'odata_checkPackOnlineUI form field':{
				specialkey:this.boxkeydown
			},*///封箱
			/*'odata_checkPackOnlineUI button[id=btnCloseBox3916]':{
				//click:this.btnCloseBox3916Click
				click:this.loadPackMeteWindow
				
			}*//*,//修改商品基准量按钮
			'odata_checkPackOnlineUI button[id=editNum3916]':{
				click:this.btnEditNum3916Click
			}*//*,//输入扫描数量
			'odata_checkPackOnlineUI textfield[id=sacnNum3916]':{
				blur:this.btnSacnNum3916Click
			},*/
			//改变复核类型
			'odata_checkPackOnlineUI radiogroup[id=rdoCheckType3916]':
			{
				change:this.rdoCheckType3916
			},//点击未复核列表(弹出框)
			'odata_checkPackOnlineUI button[id=unCheck3916]':
			{
				click:this.clickunCheck3916
			},//点击未复核列表(弹出框)的转病单
			'odata_checkPackOnlineWindow button[id=illCheck3916]':
			{
				click:this.illCheck39163916
			},//TAB页切换
			'odata_checkPackOnlineUI tabpanel[id=tabPId3916]':{
				tabchange:this.tabPidchange
			},//扫描包材条码   8-9
			'odata_checkPackMeteWindow textfield[id=packMeteBarcode]':{
				//blur:this.packMeteBarcode3916Blur
				keypress:this.packMeteBarcode3916Blur
			},//打印面单
			'odata_checkPackOnlineUI button[id=checkExpressNo3916]':
			{
				click:this.clickcheckExpressNo3916
			},
		}
		);
	},
	
	//初始化界面
	initializtion:function()
	{
		Ext.getCmp('checkPlatNo3916').setValue('');
		Ext.getCmp('checkNo3916').setValue('');
		Ext.getCmp('barcode3916').setValue('');
		Ext.getCmp('loadBoxs3916').setValue('');
		//Ext.getCmp('gridPackLabel3916').getStore().removeAll();
		Ext.getCmp('gridCheckD3916').getStore().removeAll();
		Ext.getCmp('gridDlabelD3916').getStore().removeAll();
		Ext.getCmp('sacnNum3916').setValue('1');
		Ext.getCmp('checkPlatNo3916').focus(false,10);
		
		//显示变量0为不显示，1为显示
		var packingUnit_3916=commonGetModuleField('3916','packingUnit')[0].flag;
		var packingSpec_3916=commonGetModuleField('3916','packingSpec')[0].flag;
		
		var planBox3916=commonGetModuleField('3916','planBox')[0].flag;
		var planQmin3916=commonGetModuleField('3916','planQmin')[0].flag;
		var planDis3916=commonGetModuleField('3916','planDis')[0].flag;
		var realBox3916=commonGetModuleField('3916','realBox')[0].flag;
		var realQmin3916=commonGetModuleField('3916','realQmin')[0].flag;
		var realDis3916=commonGetModuleField('3916','realDis')[0].flag;
		
		if(planBox3916==0){
			Ext.getCmp('planBox3916').setVisible(false);
		}if(planQmin3916==0){
			Ext.getCmp('planQmin3916').setVisible(false);
		}if(planDis3916==0){
			Ext.getCmp('planDis3916').setVisible(false);
		}if(realBox3916==0){
			Ext.getCmp('realBox3916').setVisible(false);
		}if(realQmin3916==0){
			Ext.getCmp('realQmin3916').setVisible(false);
		}if(realDis3916==0){
			Ext.getCmp('realDis3916').setVisible(false);
		}
		if(packingUnit_3916==0){
			Ext.getCmp('packingUnit_3916').setVisible(false);
			Ext.getCmp('packingUnit_3916_1').setVisible(false);
		}
		if(packingSpec_3916==0){
			Ext.getCmp('packingSpec_3916').setVisible(false);
			Ext.getCmp('packingSpec_3916_1').setVisible(false);
		}
		
	},
	
	//扫描包材条码  8-9
	packMeteBarcode3916Blur:function(th,e,eOpts){
		if(e.keyCode!=13){
			return;
		}
		var checkDTest={					
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.get('warehouseNo').getValue(),
				checkNo:Ext.getCmp('odata_checkNo3916').getValue(),
				barcode:Ext.getCmp('packMeteBarcode').getValue(),
				deliverObj:Ext.getCmp('deliverObj3916').getValue()
		};		
		var str2 = Ext.encode(checkDTest);
		
		var params={
				str2:str2,
				labelNo:Ext.getCmp('packLabelNo').getValue(),//包裹号
				strWorkerNo:Ext.getCmp('cmbCheckWorker3916').getValue(),
				strPackMeteQTY:Ext.getCmp('packMeteQTY').getValue(),
			};
		Ext.Ajax.request({
			method:'post',
			async:false,
			url:'odata_CheckPackOnlineAction_tscOutPackMete',
			params:params,
		    success:function(response){
		    	var res = Ext.decode(response.responseText);
		    	if(res.isSucc){
		    		Ext.getCmp('odata_checkPackMeteWindow').close();
		    	}else{
		    		Ext.Msg.alert($i18n.prompt,res.msg,
							function(){
								Ext.getCmp('packMeteBarcode').setValue("");
								Ext.getCmp('packMeteBarcode').focus(false, 10);
							}
					);
		    	}
		    }
		});	
	},
	
	menu3916RefreshClick:function(){
		Ext.getCmp('checkNo3916').setValue('');
		Ext.getCmp('barcode3916').setValue('');
		Ext.getCmp('loadBoxs3916').setValue('');
		
		//Ext.getCmp('gridPackLabel3916').getStore().removeAll();
		
		Ext.getCmp('gridCheckD3916').getStore().removeAll();
		
		Ext.getCmp('gridDlabelD3916').getStore().removeAll();	
	},
	cmbCheckWorker3916Keypress:function(th,e,eOpts){
		if(e.keyCode == 13){
			if(Ext.isEmpty(Ext.getCmp('cmbCheckWorker3916').getValue()))
			{
				Ext.example.msg('提示',"复核台姓名不能为空！");
				Ext.getCmp('cmbCheckWorker3916').setValue("");
				Ext.getCmp('cmbCheckWorker3916').focus(false,10);
				return;
			}
			Ext.Ajax.request({
				method:'POST',
				async:false,
				url:'odata_DivideWallAction_checkWorkerNo.action',
				params:{
					str:Ext.getCmp('cmbCheckWorker3916').getValue()
				},
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						
						if(data.msg == '该员工不存在'){
							Ext.example.msg($i18n_prompt.prompt,data.msg);
							Ext.getCmp('cmbCheckWorker3916').setValue('');
							Ext.getCmp('cmbCheckWorker3916').focus(true,10);
						}else if(data.msg == '该员工的状态为停用'){
							Ext.example.msg($i18n_prompt.prompt,data.msg);
							Ext.getCmp('cmbCheckWorker3916').setValue('');
							Ext.getCmp('cmbCheckWorker3916').focus(true,10);
						}else{
							if(Ext.getCmp('rdoCheckType3916').getValue().rd==1){
								Ext.getCmp('checkNo3916').setValue("");
								Ext.getCmp('checkNo3916').focus(false,10);
							}else{
								Ext.getCmp('checkNo3916_2').setValue("");
								Ext.getCmp('checkNo3916_2').focus(false,10);
							}
						}
					}
				}
			});						
		}
		},
	
	checkPlatNo3916Blur:function(th,e,eOpts){
		if(e.keyCode == 13){
			if(Ext.isEmpty(Ext.getCmp('checkPlatNo3916').getValue()))
			{
				Ext.example.msg('提示',"复核台代码不能为空！");
				Ext.getCmp('checkPlatNo3916').setValue("");
				Ext.getCmp('checkPlatNo3916').focus(false,10);
				return;
			}
			//校验扫描台号
			var params = {
					str : Ext.getCmp('checkPlatNo3916').getValue()
				};
			Ext.Ajax.request({
				method:'POST',
				url:'odata_ArrangePackAction_checkDock',
				params:params,
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(!data.isSucc){
						var audio = document.createElement("audio");
						audio.src="system/music/a.mp3";
						audio.play();
						Ext.example.msg('提示',data.msg);
						Ext.getCmp('checkPlatNo3916').setValue("");
						Ext.getCmp('checkPlatNo3916').focus(false, 10);
					}else{
						Ext.getCmp('cmbCheckWorker3916').setValue("");
						Ext.getCmp('cmbCheckWorker3916').focus(false,10);
					}
				}
			});
		}		
	
	},
	
	//扫描快递面单
	checkNo3916_2Blur:function(){
		if(Ext.isEmpty(Ext.getCmp('checkNo3916_2').getValue()))
		{
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('cmbCheckWorker3916').getValue()))
		{
			return;
		}
		Ext.getCmp('loadBoxs3916').setValue('');
		Ext.getCmp('articleName3916').setText('');
		Ext.getCmp('3916Rsv_varod3').setText('');
		//校验任务号或者快递面单
		var params = {
			checkNo : Ext.getCmp('checkNo3916_2').getValue(),
			strWorkerNo:Ext.getCmp('cmbCheckWorker3916').getValue(),
			strPrinterGroupNo:Ext.getCmp('checkPlatNo3916').getValue(),
			str:Ext.getCmp('rdoCheckType3916').getValue().rd,
			strAutoOutstock:Ext.getCmp('checkNoOut3916').getValue()==true?'Y':'N'
		};
		Ext.Ajax.request({
			method:'POST',
			url:'odata_CheckPackOnlineAction_CheckTaskNo',
			params:params,
			async:false,	
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.getCmp('odata_checkNo3916').setValue(data.obj);
					Ext.getCmp('nQty3916').setText('1');//将未复核单数置为1
					
				/*	//如果校码通过，则查询该任务号的配送对象对应的所有标签数据
					var params = {
					    checkNo : data.obj,
					};
					
					Ext.apply(Ext.getCmp('gridPackLabel3916').getStore().proxy.extraParams,params);
					Ext.getCmp('gridPackLabel3916').getStore().removeAll();
					Ext.getCmp('gridPackLabel3916').getStore().load();
*/
					//刷新未复核商品信息列表
					var params = {
							checkNo : Ext.getCmp('odata_checkNo3916').getValue()
				    };
					Ext.apply(Ext.getCmp('gridCheckD3916').getStore().proxy.extraParams,params);
					Ext.getCmp('gridCheckD3916').getStore().removeAll();
					Ext.getCmp('gridCheckD3916').getStore().load();
					
				}else
				{	
					Ext.Msg.alert('提示', data.msg,
							function(){					
								Ext.getCmp('checkNo3916_2').setValue("");
								Ext.getCmp('loadBoxs3916').setValue("");
								Ext.getCmp('checkNo3916_2').focus(false, 10);
								Ext.getCmp('gridCheckD3916').getStore().removeAll();
										});
				}
			}
		});	
	
	},
	//扫描任务号
	checkNo3916Keypress:function()
	{
		if(Ext.isEmpty(Ext.getCmp('checkPlatNo3916').getValue()))
		{
			Ext.example.msg($i18n.prompt,'请输入复核台代码！');
			Ext.getCmp('checkPlatNo3916').focus(false, 10);
			return;
		}
		
		if(Ext.isEmpty(Ext.getCmp('cmbCheckWorker3916').getValue()))
		{
			Ext.example.msg($i18n.prompt,'请输入复核员姓名！');
			Ext.getCmp('cmbCheckWorker3916').focus(false, 10);
			return;
		}
		
		Ext.getCmp('loadBoxs3916').setValue('');
		Ext.getCmp('articleName3916').setText('');
		Ext.getCmp('3916Rsv_varod3').setText('');
		
		if(e.keyCode != 13){
			return;
		}
		
		strCheckType=Ext.getCmp('rdoCheckType3916').getValue().rd;
		
		if (strCheckType=="1")
		{
			if( Ext.isEmpty(Ext.getCmp('checkNo3916').getValue()))
			{
				return;
			}
			strScanNo = Ext.getCmp('checkNo3916').getValue();//任务号
		}
		
		if (strCheckType=="2")
		{
			if( Ext.isEmpty(Ext.getCmp('checkNo3916_2').getValue()))
			{
				return;
			}
			strScanNo = Ext.getCmp('checkNo3916_2').getValue();//任务号
		}
		
			//校验任务号
			var params = {
				strScanNo : strScanNo,//任务号
				strWorkerNo:Ext.getCmp('cmbCheckWorker3916').getValue(),
				strPrinterGroupNo:Ext.getCmp('checkPlatNo3916').getValue(),
				strCheckType:Ext.getCmp('rdoCheckType3916').getValue().rd,
				strAutoOutstock:Ext.getCmp('checkNoOut3916').getValue()==true?'Y':'N'
			};
			Ext.Ajax.request({
				method:'POST',
				url:'odata_CheckPackOnlineAction_CheckTaskNo',
				params:params,
				async:false,	
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.getCmp('barcode3916').setValue("");
						Ext.getCmp('barcode3916').focus(false, 10);
						
						Ext.getCmp('odata_checkNo3916').setValue(data.obj);
						
						var params={CheckNo :Ext.getCmp('odata_checkNo3916').getValue(),
								strCheckType:Ext.getCmp('rdoCheckType3916').getValue().rd,
								strScanNo:strScanNo};
						//取未复核单数
						Ext.Ajax.request({
							method:'POST',
							url:'odata_CheckPackOnlineAction_getUnCheckObj',
							params:params,
							async:false,	
							success:function(response){
								var data = Ext.decode(response.responseText);
								if(data.isSucc){
									Ext.getCmp('nQty3916').setText(data.obj);
								}else{
									Ext.Msg.alert($i18n.prompt,'获取未复核订单数失败！');
								}
							}
						});
						
						//清除未复核列表   8-12添加
						Ext.getCmp('gridCheckD3916').getStore().removeAll();					
						Ext.getCmp('gridDlabelD3916').getStore().removeAll();
						//获取已复核的商品明细
						Ext.Ajax.request({
							method:'POST',
							url:'odata_CheckPackOnlineAction_getStockLabel',						     
							params:params,
							async:false,	
							success:function(response){
								var data = Ext.decode(response.responseText);
								if(data.length>0){
									
									Ext.apply(Ext.getCmp('gridDlabelD3916').getStore().proxy.extraParams,params);
									Ext.getCmp('gridDlabelD3916').getStore().removeAll();
									Ext.getCmp('gridDlabelD3916').getStore().load();
								}
							}
						});
						
						//获取未复核明细
						Ext.Ajax.request(
						{
							method:'POST',
							url:'odata_CheckPackOnlineAction_getCheckD',
							params:params,
							async:false,	
							success:function(response)
							{
								var data = Ext.decode(response.responseText);
								if(data.length>0)
								{
									
									Ext.apply(Ext.getCmp('gridCheckD3916').getStore().proxy.extraParams,params);
									Ext.getCmp('gridCheckD3916').getStore().removeAll();
									Ext.getCmp('gridCheckD3916').getStore().load();
								}
							}
						});					
					}else
					{
						var duang = document.createElement("audio");
				 		duang.src="system/music/a.mp3";
				 		duang.play();
						Ext.Msg.alert($i18n.prompt,data.msg,
								function(){
							       if(strCheckType==1){
							    	   Ext.getCmp('checkNo3916').setValue("");
							    	   Ext.getCmp('checkNo3916').focus(false, 10);							    	   
							       }else{
							    	   Ext.getCmp('checkNo3916_2').setValue("");
							    	   Ext.getCmp('checkNo3916_2').focus(false, 10);
							       }
									Ext.getCmp('loadBoxs3916').setValue("");
									Ext.getCmp('gridCheckD3916').getStore().removeAll();
								}
						);
					}
				}
			});
		
			
	},
	
	/*//选取箱号
	gridPackLabel3916Selectionchange:function(){
		var flag=0;
		var data = Ext.getCmp('gridPackLabel3916').getSelectionModel().getSelection();
		if(data.length!=0){
			if(data[0].get('status')!="6A"){
				Ext.example.msg($i18n.prompt,'该箱号不符合复核');
				return;
			}else{
				var count=Ext.getCmp('gridCheckD3916').getStore().getCount();
				for(var i=0;i<count;i++){
					var r=Ext.getCmp('gridCheckD3916').getStore().getAt(i);
					if(data[0].get('labelNo')==r.get('lableNo')){	
						flag=1;
					}		
				}
				if(flag==1){
					Ext.example.msg($i18n.prompt,'该箱号为来源箱号');
					return;
				}
			}			
			Ext.getCmp('loadBoxs3916').setValue(data[0].get('labelNo'));
		}	
	},*/
//	//箱号改变
//	boxChange:function(){
//		var params = {
//				labelNo: Ext.getCmp('loadBoxs3916').getValue()
//			};
//			Ext.apply(Ext.getCmp('gridDlabelD3916').getStore().proxy.extraParams,params);
//			Ext.getCmp('gridDlabelD3916').getStore().removeAll();
//			Ext.getCmp('gridDlabelD3916').getStore().load();	
//	},
	//扫描条码
	barcode3916Keypress:function(th,e,eOpts)
	{
		var rdoCheckType = Ext.getCmp('rdoCheckType3916').getValue().rd;
		
		if(Ext.isEmpty(Ext.getCmp('barcode3916').getValue()))
		{
			return;
		}
		//7-28修改
		if(rdoCheckType==1){
			if(Ext.isEmpty(Ext.getCmp('checkNo3916').getValue())){
				Ext.example.msg($i18n.prompt,"任务号/箱号不能为空！");
				Ext.getCmp('checkNo3916').focus(false,10);
				return;
			}
			var strScanNo=Ext.getCmp('checkNo3916').getValue();
		}else if(rdoCheckType==2){
			if(Ext.isEmpty(Ext.getCmp('checkNo3916_2').getValue())){
				Ext.example.msg($i18n.prompt,"快递单号不能为空！");
				Ext.getCmp('checkNo3916_2').focus(false,10);
				return;
			}
			var strScanNo=Ext.getCmp('checkNo3916_2').getValue();
		}
		
		if(Ext.isEmpty(Ext.getCmp('checkPlatNo3916').getValue()))
		{
			Ext.example.msg($i18n.prompt,"复核台不能为空！");
			Ext.getCmp('barcode3916').setValue('');
			Ext.getCmp('barcode3916').focus(false,10);
			return;
		}	
		if(Ext.isEmpty(Ext.getCmp('cmbCheckWorker3916').getValue())){
			Ext.example.msg($i18n.prompt,"复核人员不能为空！");
			Ext.getCmp('barcode3916').setValue('');
			Ext.getCmp('barcode3916').focus(false,10);
			return;	
		}
		var flag=checkTheArticleNum();
		if(flag==1 || flag==3){
			Ext.example.msg($i18n.prompt,'扫描基准量大于商品剩余量');
			Ext.getCmp('barcode3916').setValue('');
			Ext.getCmp('barcode3916').focus(false,10);
			return;
		}/*else if(flag==2){
			Ext.example.msg($i18n.prompt,'扫描条码和选择商品明细不符合');
			Ext.getCmp('barcode3916').setValue('');
			Ext.getCmp('barcode3916').focus(false,20);
			return;
		}else if(flag==4){
			Ext.Msg.alert($i18n.prompt,'扫描条码不存在',
					function(){
				Ext.getCmp('barcode3916').setValue("");
				Ext.getCmp('barcode3916').focus(false, 10);						
			});			
			return;
		}*/
		if(e.keyCode == 13){
			
		
		var checkD={					
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					checkNo:Ext.getCmp('odata_checkNo3916').getValue(),
					barcode:Ext.getCmp('barcode3916').getValue(),
					deliverObj:Ext.getCmp('deliverObj3916').getValue()
		};		
		
		var str=Ext.encode(checkD);
		
		var params={
			str:str,
			sacnNum : Ext.getCmp('sacnNum3916').getValue(),
			strCheckType:Ext.getCmp('rdoCheckType3916').getValue().rd,			
			strScanNo:strScanNo,
			strWorkerNo:Ext.getCmp('cmbCheckWorker3916').getValue(),
			strPrinterGroupNo:Ext.getCmp('checkPlatNo3916').getValue(),
			strPrintWayBill:1,
			strPrintPackList:1,
			strPrintInVoice:0
		};
		Ext.Ajax.request({ 
			method:'POST',
			url:'odata_CheckPackOnlineAction_tscCheckBarcode',
			params:params,
			async:false,	
			success:function(response){
				var data1 = Ext.decode(response.responseText);
				if(data1.isSucc)
				{
					Ext.getCmp('deliverObj3916').setValue(data1.obj.deliverObj);
					Ext.getCmp('loadBoxs3916').setValue(data1.obj.lableNo);
					Ext.getCmp('articleName3916').setText(data1.obj.articleName);
					
						var params = {
								strDeliverObj : data1.obj.deliverObj,
								checkNo:Ext.getCmp('odata_checkNo3916').getValue()
						};
						Ext.Ajax.request({
							method:'POST',
							url:'odata_CheckPackOnlineAction_getECbukong',
							params:params,
							async:false,	
							success:function(response){
								var data2 = Ext.decode(response.responseText);
								if(data2.isSucc){									
									Ext.getCmp('3916Rsv_varod3').setText(data2.obj);
									if(data2.obj=='布控')
									{										
										//Ext.getCmp('3916Rsv_varod3').addCls('classDiv5');
										Ext.getCmp('3916Rsv_varod3').addCls('classDiv6');
										//Ext.getCmp('3916Rsv_varod3').setBodyStyle('background','#FF0000');
										var audio = document.createElement("audio");
										audio.src="system/music/a.mp3";
										audio.play();
									}
									else
									{									
										Ext.getCmp('3916Rsv_varod3').addCls('classDiv6');
										//8-9屏蔽测试
										//Ext.getCmp('3916Rsv_varod3').setBodyStyle('background','#0000FF');
									}
								}
								else
								{
									Ext.Msg.alert($i18n.prompt,'获取布控状态失败！');
								}
							}
						});
						Ext.getCmp('barcode3916').setValue("");
						Ext.getCmp('barcode3916').focus(false, 10);
					//}
					var params={CheckNo :Ext.getCmp('odata_checkNo3916').getValue(),
								strCheckType:Ext.getCmp('rdoCheckType3916').getValue().rd,
								strScanNo:Ext.getCmp('rdoCheckType3916').getValue().rd==1?
										Ext.getCmp('checkNo3916').getValue():Ext.getCmp('checkNo3916_2').getValue()};
					//显示包材
					Ext.getCmp('packmet_name').setText(data1.obj.strPackMateName);
					
					if(data1.obj.strCloseFlag=='Y')
					{
						Ext.getCmp('investReportImage').setVisible(true);   
						
						loadPackMeteWindow(data1.obj.lableNo ,data1.obj.strPackMateName);
						Ext.getCmp('packMeteBarcode').focus(false,10);
						//刷新未复核单数
						//取未复核单数
						Ext.Ajax.request({
							method:'POST',
							url:'odata_CheckPackOnlineAction_getUnCheckObj',
							params:params,
							async:false,	
							success:function(response){
								var data3 = Ext.decode(response.responseText);
								if(data3.isSucc){
									Ext.getCmp('nQty3916').setText(data3.obj);
									if(data3.obj==0){
										if(rdoCheckType==1){
											//Ext.getCmp('checkNo3916').setValue('');
											//光标停留在包材窗口     8-11
											Ext.getCmp('packMeteBarcode').focus(false,10);
										}else{
											//Ext.getCmp('checkNo3916_2').setValue('');
											//光标停留在包材窗口     8-11
											Ext.getCmp('packMeteBarcode').focus(false,10);
										}
									}
								}else{
									Ext.Msg.alert($i18n.prompt,'获取未复核订单数失败！');
									//Ext.example.msg();
								}
							}
						});
					    //该配送对象扫描完成，清空配送对象框和目的箱号					
						Ext.getCmp('deliverObj3916').setValue('');					
					}
					
					Ext.apply(Ext.getCmp('gridDlabelD3916').getStore().proxy.extraParams,params);
					Ext.getCmp('gridDlabelD3916').getStore().removeAll();
					Ext.getCmp('gridDlabelD3916').getStore().load();
					Ext.apply(Ext.getCmp('gridCheckD3916').getStore().proxy.extraParams,params);
					Ext.getCmp('gridCheckD3916').getStore().removeAll();
					Ext.getCmp('gridCheckD3916').getStore().load();
					
					//获取光标定位标识
					strFinishFlag = data1.obj.strFinishFlag;					
					
				}
				else
				{
		 			var duang = document.createElement("audio");
			 		duang.src="system/music/a.mp3";
			 		duang.play();
					Ext.Msg.alert($i18n.prompt,data1.msg,function(){
						Ext.getCmp('barcode3916').setValue("");
						Ext.getCmp('barcode3916').focus(false, 10);						
					});					
				}
			}
		});}
	},

	boxkeydown:function(th,e,eOpts){
		var rdoCheckType = Ext.getCmp('rdoCheckType3916').getValue().rd;
	  	if (e.getKey() == e.ENTER) {
	  		if(th.id=="checkPlatNo3916"){
	  			Ext.getCmp('cmbCheckWorker3916').focus();
	  		}else if(th.id=="cmbCheckWorker3916"){
	  			if(rdoCheckType==1){
	  				Ext.getCmp('checkNo3916').focus();
	  			}else if(rdoCheckType==2){
	  				Ext.getCmp('checkNo3916_2').focus();
	  			}
	  		}else if(th.id=="checkNo3916"){
	  			Ext.getCmp('barcode3916').focus();
	  		}else if(th.id=="checkNo3916_2"){
	  			Ext.getCmp('barcode3916').focus();
	  		}else if(th.id=="barcode3916"){
	  			//this.barcode3916Blur();
	  		}else if(th.id=="sacnNum3916"){
	  			Ext.getCmp('barcode3916').focus(false,10);
	  		}/*else{
				th.nextSibling().focus();
			}*/
        }
	},
	
	rdoCheckType3916:function(){
		var rdoCheckType = Ext.getCmp('rdoCheckType3916').getValue().rd;
		//1任务号；2箱号/快递单号
		if(rdoCheckType==1){
			Ext.getCmp('checkNo3916').setVisible(true);   
			Ext.getCmp('checkNo3916_2').setVisible(false);  
			Ext.getCmp('unCheck3916').setDisabled(false);
			Ext.getCmp('checkNo3916').setValue("");
			Ext.getCmp('checkNo3916').focus(false, 10);
		}else if(rdoCheckType==2){
			Ext.getCmp('checkNo3916_2').setVisible(true);  
			Ext.getCmp('checkNo3916').setVisible(false);   
			Ext.getCmp('unCheck3916').setDisabled(false); //8-12修改
			Ext.getCmp('checkNo3916_2').setValue("");
			Ext.getCmp('checkNo3916_2').focus(false, 10);
		}

	},

	//打印面单
	clickcheckExpressNo3916:function(){
		//ip和端口
		var ip = "127.0.0.1";
		var port = "12345";
		if(ip==""||port==""){alert("ip,port不能为空"); return false;}
		//获取任务号或者快递单号
		var flagCheckType=Ext.getCmp('rdoCheckType3916').getValue().rd;
		if (flagCheckType=="1")
		{
			if( Ext.isEmpty(Ext.getCmp('checkNo3916').getValue()))
			{
				Ext.example.msg('提示',"任务号不能为空！");
				return;
			}
			var strScan=Ext.getCmp('checkNo3916').getValue();
		//	alert("任务号:"+strScan);//测试一下
		}

		if (flagCheckType=="2")
		{
			if( Ext.isEmpty(Ext.getCmp('checkNo3916_2').getValue()))
			{
				Ext.example.msg('提示',"快递单号不能为空！");
				return;
			}
			var strScan=Ext.getCmp('checkNo3916_2').getValue();
		//	alert("快递单号:"+strScan);//测试一下
		}
		//获取订单数据
		$.ajax({
			url : "localPrinterAciton_printWaybill.action",
			type : "POST",
			dataType : "json",
			data : {labelNo:strScan},
			success: function(data){
				var list = data[1];
				//console.log(data[0].shipperNo);
				var	msg = '{'
					+'"method":"' + 'printreport' +'"'  /*报表类型 gridreport fastreport reportmachine 为空 将默认为gridreport  */

					+',"ReportType":"' + 'gridreport' +'"'  /*报表类型 gridreport fastreport reportmachine 为空 将默认为gridreport  */
					+',"ReportName":"' +data[0].shipperNo+'.grf"' /*报表文件名 快递单套打 */
				//	+',"ReportName":"shentong.grf"'
					+',"ReportVersion":"' + '1' +'"' /*可选。报表版本, 为空则默认1  如果本地报表的版本过低 将从 ReportUrl 地址进行下载更新*/
					+',"ReportUrl":"' + '' +'"' /*可选。为空 将不更新本地报表 , 如果本地报表不存在可以从该地址自动下载*/
					//+',"ReportUrl":"' + 'http://localhost/test.grf' +'"' /*可选。为空 将不更新本地报表 , 如果本地报表不存在可以从该地址自动下载*/
					+',"Copies":"' + '1' +'"' /*可选。打印份数，支持指定打印份数。默认1份,如果为零,不打印,只返回报表生成的pdf,jpg等文件*/
					+',"PrinterName":""' /*可选。指定打印机，为空的话 使用默认打印机, 请在 控制面板 -> 设备和打印机 中查看您的打印机的名称 */
					+',"PrintOffsetX":"' + '0' +'"' /*可选。打印右偏移，单位厘米。报表的水平方向上的偏移量，向右为正，向左为负。*/
					+',"PrintOffsetY":"' + '0' +'"' /*可选。打印下偏移，单位厘米。 报表的垂直方向上的偏移量，向下为正，向上为负。*/
					+',"Preview":"' + '0' +'"'  /*可选。是否预览，和主界面设置的效果一样 为空默认不预览,   0：不预览，1：预览(弹出导出的pdf,jpg等文件)。*/
					+',"token":"' + 'aa' +'"' /*可选。只要token值在列表中 方可打印*/
					+',"taskId":"' + '1234567' +'"' /*可选。多个打印任务同时打印时，根据该id确定返回的是哪个打印任务。 */
					+',"exportfilename":"' + '' +'"'  /*可选。自定义 导出 文件名称 为空 或者 自定义名称 如 test */
					+',"exportfiletype":"' + '' +'"'  /*可选。自定义 导出 文件格式 为空 或者 自定义名称 如 pdf  */

					+',"Parameter": ['  ///*参数，type 默认为空即可,已经在报表端设置了 备用字段

					+'{"type": "", "name": "contactorName","value": "'+data[0].contactorName+'","required": false},'
					+'{"type": "", "name": "custPhone","value": "'+data[0].custPhone+'","required": false},'
					+'{"type": "", "name": "receiveCity","value": "'+data[0].receiveCity+'","required": false},'
					+'{"type": "", "name": "receiveCountry","value": "'+data[0].receiveCountry+'","required": false},'
					+'{"type": "", "name": "receiveProvince","value": "'+data[0].receiveProvince+'","required": false},'
					+'{"type": "", "name": "receiveZone","value": "'+data[0].receiveZone+'","required": false},'
					+'{"type": "", "name": "custAddress","value": "'+data[0].custAddress+'","required": false},'
					+'{"type": "", "name": "sendName","value": "'+data[0].sendName+'","required": false},'
					+'{"type": "", "name": "sendCity","value": "'+data[0].sendCity+'","required": false},'
					+'{"type": "", "name": "sendCountry","value": "'+data[0].sendCountry+'","required": false},'
					+'{"type": "", "name": "sendProvince","value": "'+data[0].sendProvince+'","required": false},'
					+'{"type": "", "name": "sendZone","value": "'+data[0].sendZone+'","required": false},'
					+'{"type": "", "name": "sendAddress","value": "'+data[0].sendAddress+'","required": false},'
					+'{"type": "", "name": "sendCompanyName","value": "'+data[0].sendCompanyName+'","required": false},'
					+'{"type": "", "name": "shipperDeliverNo","value": "'+data[0].shipperDeliverNo+'","required": false},'
					+'{"type": "", "name": "sendMobilePhone","value": "'+data[0].sendMobilePhone+'","required": false},'
					+'{"type": "", "name": "deliverAddress","value": "'+data[0].deliverAddress+'","required": false},'
					+'{"type": "", "name": "expRemark","value": "'+data[0].expRemark+'","required": false},'
					+'{"type": "", "name": "article","value": "';

					for (var i=0;i<list.length;i++)
					{

						msg +=list[i].articleName+' * '+list[i].planBox+'\r\n';

					}

					msg += '","required": false},] }';
			//	console.log(msg);
				//发请求到本地打印组件
				jQuery.support.cors = true;         //写到$.ajax $.get $.post 前面 解决 jQuery.Ajax IE8,9 无效（CORS跨域）
				$.ajax({
					async : false, //循环打印时,　async 必须设置为 false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
					url : "http://"+ip+":"+port+"/",
					type : "POST",
					contentType: "application/x-www-form-urlencoded", //要这样设置
					//contentType: "application/json", //错误方式
					dataType : "json", //设置为 json 格式
					//dataType : "text", //设置为 text 格式 也可以,但是返回的结果需要自己解析判断
					//crossDomain: true,  //crossDomain true 或者false 无所谓,不用设置的
					data : msg,
					beforeSend: function (XMLHttpRequest) {
						// beforeSend 函数里 不允许添加任何 东西
						//XMLHttpRequest.setRequestHeader("token", 'abcd1234'); //不允许 使用jQuery发送AJAX请求时在header中添加Token
					},
					success: function(data){
						//	console.log(JSON.stringify(data));
						if(data.status=="ok"){
							alert("打印成功:"+data.data);
						}else{
							alert("打印失败:"+data.data);
						}
					},
					error: function(data){
						//console.log(status, response);
						console.log(JSON.stringify(data));
						alert("连接HttpPrinter失败:"+JSON.stringify(data));
					}
				});
			}
		});


	},
	clickunCheck3916:function(){
    var flagCheckType=Ext.getCmp('rdoCheckType3916').getValue().rd;
		
		if (flagCheckType=="1")
		{
			if( Ext.isEmpty(Ext.getCmp('checkNo3916').getValue()))
			{
				Ext.example.msg('提示',"任务号不能为空！");
				return;
			}
			var strScan=Ext.getCmp('checkNo3916').getValue();
		}
		
		if (flagCheckType=="2")
		{
			if( Ext.isEmpty(Ext.getCmp('checkNo3916_2').getValue()))
			{
				Ext.example.msg('提示',"快递单号不能为空！");
				return;
			}
			var strScan=Ext.getCmp('checkNo3916_2').getValue();
		}

		Ext.create('cms.view.odata.window.odata_checkPackOnlineWindow',{
			title:'未复核列表'
		}).show();
		var params={CheckNo : Ext.getCmp('odata_checkNo3916').getValue(),
				strCheckType:Ext.getCmp('rdoCheckType3916').getValue().rd,
				strScanNo:strScan};
		Ext.apply(Ext.getCmp('unCheck_list_3916').getStore().proxy.extraParams,params);
		Ext.getCmp('unCheck_list_3916').getStore().removeAll();
		Ext.getCmp('unCheck_list_3916').getStore().load();
	},
	
	//8-12  hj  修改
	illCheck39163916:function(){
		var grid01=Ext.getCmp('unCheck_list_3916').getSelectionModel().getSelection();
		if(grid01.length==0){
			Ext.example.msg($i18n.prompt,'请勾选列表数据！');
            return;
		}
		
		//将选中的面单号和配送对象存进arrData1,arrData2数组里面
		var arrData1 = new Array(grid01.length);   //面单号
		var arrData2 = new Array(grid01.length);   //配送对象
		for(var i=0; i<grid01.length; i++){
			arrData1[i] = grid01[i].data.shipperDeliverNo;
			arrData2[i] = grid01[i].data.deliverObj;
		}
		//数组去除重复项
		function unique(array){
			  array.sort(); 
			  var re=[array[0]];
			  for(var i = 1; i < array.length; i++){
			    if( array[i] !== re[re.length-1])
			    {
			      re.push(array[i]);
			    }
			  }
			  return re;
		}
		var test1 = unique(arrData1);
		var test2 = unique(arrData2);
		var showStr = test1.join(",");
		
		Ext.Msg.buttonText.yes='否';
		Ext.Msg.buttonText.no="是";
		Ext.Msg.show({
		     title:'温馨提示',
		     msg: showStr+',以上快递单号确定要转病单？',
		     buttons: Ext.Msg.YESNO,			//按钮设置
		     icon: Ext.Msg.WARNING,				//提示框的图标设置
		     fn:function callback(btn,text){
					if(btn=="no"){
						for(var i = 0; i < test2.length; i++){
							var params={
								strDeliverObj:test2[i],	
								checkNo:Ext.getCmp('odata_checkNo3916').getValue(),
								strWorkerNo:Ext.getCmp('cmbCheckWorker3916').getValue()
							};
							Ext.Ajax.request({
								method:'POST',
								url:'odata_CheckPackOnlineAction_changeSickOrder',
								params:params,
								async:false,	
								success:function(response){
									var data = Ext.decode(response.responseText);
										//Ext.example.msg($i18n.prompt,'转病单成功！');
										//Ext.getCmp('unCheck_list_3916').getStore().removeAll();	
										//Ext.getCmp('unCheck_list_3916').getStore().reload();
								}
							});
							if(i == test2.length-1){
								Ext.example.msg($i18n.prompt,'转病单成功！');
								Ext.getCmp('unCheck_list_3916').getStore().removeAll();	
								Ext.getCmp('unCheck_list_3916').getStore().reload();
							}
						}
					}
				}
		});
	},
	
	//封箱
	btnCloseBox3916Click:function(){
		if(Ext.isEmpty(Ext.getCmp('checkPlatNo3916').getValue()))
		{
			Ext.example.msg($i18n.prompt,"复核台不能为空！");
			return;
		}	
		
		if(Ext.isEmpty(Ext.getCmp('cmbCheckWorker3916').getValue()))
		{
			Ext.example.msg($i18n.prompt,"复核人员不能为空！");
			return;
		}	
		
		if(Ext.isEmpty(Ext.getCmp('loadBoxs3916').getValue()))
		{
			Ext.example.msg($i18n.prompt,"封箱标签不能为空！");
			return;
		}	
		
		var a = Ext.getCmp('gridCheckD3916').getStore().getCount();
		if(a==0)
		{
			Ext.example.msg($i18n.prompt,"未复核列表为空，没有需要封箱的数据！");
			return;
		}	
		
		var params={
			strDlabel:Ext.getCmp('loadBoxs3916').getValue(),
			strWorkerNo:Ext.getCmp('cmbCheckWorker3916').getValue(),
			strPrinterGroupNo:Ext.getCmp('checkPlatNo3916').getValue()
		};
		Ext.Ajax.request({
			method:'POST',
			url:'odata_CheckPackOnlineAction_tscArrangeCutbox',
			params:params,
			async:false,	
			success:function(response){
				var data = Ext.decode(response.responseText);
				//Ext.example.msg($i18n.prompt,data.msg);
				Ext.Msg.alert($i18n.prompt,data.msg);
				if(data.isSucc){
					Ext.getCmp('loadBoxs3916').setValue("");
			
				//	Ext.getCmp('gridPackLabel3916').getStore().reload();
					Ext.getCmp('gridDlabelD3916').getStore().removeAll();
				}	       
			}
		});
	},
	
	tabPidchange:function(tabPanel,newCard,oldCard,eOpts ){
		if(newCard.itemId=='tabPId3916i'){
			bdef_RepairPrint = Ext.create('cms.view.common.bdef_RepairPrint');
			bdef_RepairPrint.items.items[3].items.items[0].getStore().removeAll();
			bdef_RepairPrint.items.items[3].items.items[1].getStore().removeAll();
			bdef_RepairPrint.items.items[4].items.items[0].getStore().removeAll();
			bdef_RepairPrint.items.items[4].items.items[1].getStore().removeAll();
			Ext.getCmp('tabPId3916i').add(bdef_RepairPrint);
			Ext.getCmp('tabPId3916i').doLayout();
			queryModuleId='3916';
			Ext.Ajax.request({
				url:'wms_PnfsetModuleReportQueryAction_getModuleReportQuery',
				method:'get',
				params:
				{
					strModuleId:queryModuleId
				},
				success:function(response)
				{
					var data=Ext.decode(response.responseText);
					for(var i=0;i<data.length;i++){
						getQueryPanel = Ext.create('cms.view.common.reportQueryPanel');
						getQueryPanel.items.items[1].getStore().add({
							columnid:data[i].columnid,
							columnname:data[i].columnname
					    });
						getQueryPanel.items.items[1].setValue(data[i].columnid);
						if(data[i].xtype=='datefield'){
							getQueryPanel.remove(getQueryPanel.items.items[3]);
							getQueryPanel.add({
						        xtype : 'datefield',
								fieldLabel : '值',							
								format : 'Y-m-d',
						        labelWidth : 20,
						        width : 195
							});
						}
						getQueryPanel.items.items[3].setValue('');
						getQueryPanel.items.items[1].setReadOnly(true);
						bdef_RepairPrint.items.items[2].add(getQueryPanel);					
					}
				}
			});
			//var wheresql={
			//		strModuleId:'3916'
			//};
			//Ext.apply(Ext.getCmp('unCheck_list_3916').getStore().proxy.extraParams,wheresql);
			//Ext.getCmp('unCheck_list_3916').getStore().removeAll();
			//Ext.getCmp('unCheck_list_3916').getStore().load();
			
			//Ext.getCmp('repairPrint1AAA').setValue('3916');
		}
	}

});

//显示包材窗口    8-9
function loadPackMeteWindow(strLabel_No,strPackMeteName){
	Ext.create('cms.view.odata.window.odata_checkPackMeteWindow',{
		title:'请扫描包材(按Esc可退出)',
		listeners:{
			beforeclose:function(){
				if(strFinishFlag=='Y')
				{
					Ext.getCmp('checkNo3916').setValue("");
					Ext.getCmp('checkNo3916_2').setValue("");
					
					var rdoCheckType = Ext.getCmp('rdoCheckType3916').getValue().rd;
					if(rdoCheckType==1){
						
		  				Ext.getCmp('checkNo3916').focus(false, 10);
		  			}
					else
					{
		  				Ext.getCmp('checkNo3916_2').focus(false, 10);
		  			}
				}else{
					Ext.getCmp('barcode3916').setValue("");
					Ext.getCmp('barcode3916').focus(false, 10);
				}
	    		Ext.getCmp('articleName3916').setText('');
	    		Ext.getCmp('3916Rsv_varod3').setText('');
	    		Ext.getCmp('packmet_name').setText('');
			}
		}
	}).show(); 
	
	Ext.getCmp('packLabelNo').setValue(strLabel_No);//包裹号
	Ext.getCmp('packMeteName').setValue(strPackMeteName);//建议包材名称
	
}

function checkTheArticleNum(){
	var data = Ext.getCmp('gridCheckD3916').getSelectionModel().getSelection();
	
	if(data.length==0){
		var gridcount=Ext.getCmp('gridCheckD3916').getStore().getCount();
		if(gridcount!=0){
			for(var i=0;i<gridcount;i++){
				var dataD=Ext.getCmp('gridCheckD3916').getStore().getAt(i);
				if(dataD.get('barcode')==Ext.getCmp('barcode3916').getValue()){
					if(dataD.get('uncheckQty')< Ext.getCmp('sacnNum3916').getValue()){
						return 1;
					}else{
						return 5;
					}
				}	
			}
			if(i==gridcount && dataD.get('barcode')!=Ext.getCmp('barcode3916').getValue()){
				return 4;
			}
		}else{
			//return 4;    8-8屏蔽
			return 5;
		}	
	}else{
		if(data[0].get('barcode')!=Ext.getCmp('barcode3916').getValue()){
			return 2;
		}else{
			if(data[0].get('uncheckQty')< Ext.getCmp('sacnNum3916').getValue()){
				return 3;
			}
		}
	}
	return 5;
}

