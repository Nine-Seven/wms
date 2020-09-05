/*
 * 分播墙拣货
 * 3915
 * lich
 */
var selectDpsCellNo3915;
var surplusQty3915;
var noDivideQty3;		//未播量
var setInterval1;		//定时器
var setInterval2;
var divideNoTest;       //用于接收分播单号


Ext.define('cms.controller.odata.odata_DivideWallController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.odata.odata_DivideWallUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//选择任务号/标签号
			'odata_DivideWallUI textfield[id=divideNo3915]':{
				//focus:this.divideNo3915Focus,
				//blur:this.divideNo3915Blur,
				keypress:this.divideNo3915Keypress
			},//条码选择
			'odata_DivideWallUI textfield[id=barcode3915]':{
				//focus:this.barcode3915Focus,
				keydown:this.barcode3915Keydown
			},//储位选择
			'odata_DivideWallUI textfield[id=cellCheck3915]':{
				focus:this.cellCheck3915Focus,
				keydown:this.cellCheck3915Keydown			
			},//释放单号
			'odata_DivideWallUI button[name=releaseNo]':{
				click:this.releaseNoClick
			},//分播明细  7-1
			'odata_DivideWallUI button[name=divideDetail]':{
				click:this.divideDetailClick
			},//刷新
			'odata_DivideWallUI button[name=refresh]':{
				click:this.refreshClick
			},//分播人员
			'odata_DivideWallUI textfield[id=workerNo3915]':{
				//focus:this.workerNo3915Focus,
				keypress:this.workerNo3915Keypress
				//blur:this.workerNo3915Blur
			},//扫描工作站  6-25
			/*'odata_DivideWallUI textfield[id=workspaceStation3915]':{
				keypress:this.workspaceStation3915Keypress,
				blur:this.workspaceStation3915Blur
			},*///分播墙号校验
			'odata_DivideWallUI textfield[id=deviceNo3915]':{
				//focus:this.deviceNo3915Focus,
				//blur:this.deviceNo3915Blur,
				keypress:this.deviceNo3915Keypress
			},//TAB页切换
			'odata_DivideWallUI tabpanel[id=tabPId3915]':{
				tabchange:this.tabPidchange
			}
			
		});
	},	
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		selectDpsCellNo3915=null;
		surplusQty3915=0;
		Ext.getCmp('workerNo3915').focus(true,10);
		this.getOdata_DivideWallCell();		
	},
	refreshClick:function(){
		if(!Ext.isEmpty(Ext.getCmp('divideNo3915').getValue())){		
			this.divideNo3915Keypress(Ext.getCmp('divideNo3915'));
		}else{
			this.getOdata_DivideWallCell();
		}
	},
	divideNo3915Focus:function(th,newValue,oldValue,eOpts){
		
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
			return;
		}
	},
	
	/*workerNo3915Focus:function(th,newValue,oldValue,eOpts){
			
			var workSpace = Ext.getCmp('workspaceStation3915').getValue();
			if(Ext.isEmpty(workSpace))
			{
				Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
				//setWorkSpace();
				Ext.getCmp('workspaceStation3915').focus(true,10);
				return;
			}
	},*/
	
	//tab页切换  6-27
	tabPidchange:function(tabPanel,newCard,oldCard,eOpts ){
		if(newCard.itemId=='tabPId3915i'){
			Ext.getCmp('tabPId3915i').add(Ext.create('cms.view.common.bdef_RepairPrint'));
			Ext.getCmp('tabPId3915i').doLayout();
			queryModuleId='3915';
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
						var getQueryPanel = Ext.create('cms.view.common.reportQueryPanel');
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
						Ext.getCmp('bdef_RepairPrintPanel').add(getQueryPanel);					
					}
				}
			});			
		}
	},
	
	//扫描工作站  6-25
	workspaceStation3915Keypress:function(th,e,eOpts){
		if(e.keyCode!=13){
			return;
		}
		if(Ext.isEmpty(th.value)){
			return;
		}
  		if(Ext.isEmpty(Ext.getCmp('workspaceStation3915').getValue()))
  		{
  			return;
  		}
  		//校验工作站
  		var params = {
  				str : Ext.getCmp('workspaceStation3915').getValue(),
  			};
  		Ext.Ajax.request({
  			method:'POST',
  			//url:'odata_ArrangePackAction_checkDock',
  			url: 'bdef_DefWorkstationAction_checkWorkStation.action',
  			async:false,
  			params:params,
  			success:function(response){
  				var data = Ext.decode(response.responseText);
  				if(!data.isSucc){
  					var audio = document.createElement("audio");
  					audio.src="system/music/a.mp3";
  					audio.play();
					Ext.Msg.alert($i18n.prompt,data.msg);

  					//Ext.example.msg('提示',data.msg);
  					Ext.getCmp('workspaceStation3915').setValue("");
  					Ext.getCmp('workspaceStation3915').focus(false, 10);
  				}else{
  					//Ext.getCmp('workspaceStation3915').setValue("");
  					Ext.getCmp('workerNo3915').focus(false, 10);
  				}
  			}
  		});		
  	},
  	deviceNo3915Blur:function(){
  		if(Ext.isEmpty(Ext.getCmp('deviceNo3915').getValue()))
  		{
  			return;
  		}
  		var params = {
  				str : Ext.getCmp('deviceNo3915').getValue(),
  			};
  		Ext.Ajax.request({
  			method:'POST',
  			url: 'odata_DivideWallAction_checkdeviceNo.action',
  			async:false,
  			params:params,
  			success:function(response){
  				var data = Ext.decode(response.responseText);
  				if(data.isSucc == true){
  					//光标锁定到标签号
  					Ext.getCmp('divideNo3915').focus(true,10);
  				}
  				else
  				{  					
  					Ext.example.msg('提示',"该分播墙号不存在，请重新输入！");
  					Ext.getCmp('deviceNo3915').setValue('');
					Ext.getCmp('deviceNo3915').focus(false, 10);
  				}
  			}
  		});
  	},
    //分播墙号校验  6-25
  	deviceNo3915Keypress:function(th,e,eOpts){
		if(e.keyCode!=13){
			return;
		}
		if(Ext.isEmpty(th.value)){
			Ext.example.msg('提示',"分播墙号不能为空，请重新输入！");
			Ext.getCmp('deviceNo3915').focus(false, 10);
			return;
		} 		
  		//加载该分播墙下的格子 6-27
  		var params = {
  				str : Ext.getCmp('deviceNo3915').getValue(),
  			};
  		Ext.Ajax.request({
  			method:'POST',
  			url: 'odata_DivideWallAction_checkdeviceNo.action',
  			async:false,
  			params:params,
  			success:function(response){
  				var data = Ext.decode(response.responseText);
  				if(data.isSucc == true){
  					//光标锁定到标签号
  					Ext.getCmp('divideNo3915').focus(true,10);
  				}
  				else
  				{  					
  					Ext.example.msg('提示',"该分播墙号不存在，请重新输入！");
  					Ext.getCmp('deviceNo3915').setValue('');
					Ext.getCmp('deviceNo3915').focus(false, 10);
  				}
  			}
  		});	
  	},
  	workerNo3915Blur:function(){
  		if(Ext.isEmpty(Ext.getCmp('workerNo3915').getValue())){
			return;
		}
		Ext.Ajax.request({
			method:'POST',
			async:false,
			url:'odata_DivideWallAction_checkWorkerNo.action',
			params:{
				str:Ext.getCmp('workerNo3915').getValue()
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){					
					
					if(data.msg == '该员工不存在'){
						Ext.example.msg($i18n_prompt.prompt,data.msg);
						Ext.getCmp('workerNo3915').setValue('');
						Ext.getCmp('workerNo3915').focus(true,10);
					}else if(data.msg == '该员工的状态为停用'){
						Ext.example.msg($i18n_prompt.prompt,data.msg);
						Ext.getCmp('workerNo3915').setValue('');
						Ext.getCmp('workerNo3915').focus(true,10);
					}else{
						Ext.getCmp('deviceNo3915').focus(true,10);
					}
				}else{
					 //Ext.example.msg($i18n_prompt.prompt,data.msg);
					//Ext.getCmp('divideNo3915').setValue('');
				}
			}
		});
  	},
	//分播人员
	workerNo3915Keypress:function(th,e,eOpts){
		if(e.keyCode!=13){
			return;
		}
		if(Ext.isEmpty(th.value)){
			Ext.example.msg('提示',"分播人员不能为空，请重新输入！");
			Ext.getCmp('workerNo3915').focus(true,10);
			return;
		}
		Ext.Ajax.request({
			method:'POST',
			async:false,
			url:'odata_DivideWallAction_checkWorkerNo.action',
			params:{
				str:th.value
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){					
					
					if(data.msg == '该员工不存在'){
						Ext.example.msg($i18n_prompt.prompt,data.msg);
						Ext.getCmp('workerNo3915').setValue('');
						Ext.getCmp('workerNo3915').focus(true,10);
					}else if(data.msg == '该员工的状态为停用'){
						Ext.example.msg($i18n_prompt.prompt,data.msg);
						Ext.getCmp('workerNo3915').setValue('');
						Ext.getCmp('workerNo3915').focus(true,10);
					}else{
						Ext.getCmp('deviceNo3915').focus(true,10);
					}
				}else{
					 //Ext.example.msg($i18n_prompt.prompt,data.msg);
					//Ext.getCmp('divideNo3915').setValue('');
				}
			}
		});
	},
	divideNo3915Blur:function(){
		if(Ext.isEmpty(Ext.getCmp('divideNo3915').getValue())){
			return;
		}
		//获得分播墙号
		 var deviceNo = Ext.getCmp('deviceNo3915').getValue();
		Ext.Ajax.request({
			method:'POST',
			async:true,
			url:'odata_DivideWallAction_tscSelectEquipmentNo.action',
			params:{
				strEquipmentNo:Ext.getCmp('divideNo3915').getValue(),
				strDeviceNo:deviceNo,
				strWorkSpace:workSpaceNo
			},
			success:function(response)
			{
				var data = Ext.decode(response.responseText);
				if(data.isSucc)
				{
					clearPanMain3915();
					//赋值分播单号变量  7-27
					//divideNoTest = data.obj[2].divideNo;
					divideNoTest = data.obj[0].divideNo;
					
					//window.clearInterval(setInterval1);
					//window.clearInterval(setInterval2);
					
					//未播量总数的计算
					var noDivideQty1 = 0;
					var noDivideQty2 = 0;
					for(var i=0;i<data.obj.length;i++){
						var d=Ext.ComponentQuery.query('odata_DivideWallUI panel[name=3915'+data.obj[i].dpsCellNo+']')[0];
						var qty1 = data.obj[i].realQty / data.obj[i].qminOperatePacking;//换算已播量
						var qty2 = data.obj[i].articleQty / data.obj[i].qminOperatePacking;//换算应播量
						//d.items.items[5].setText(data.obj[i].expNo);     //编号
						d.items.items[5].setText(data.obj[i].shipperDeliverNo);     //编号
						d.items.items[2].setText(qty1);   //已播量
						d.items.items[4].setText(qty2);//应播量
						//待分播的标为黄色
						d.setBodyStyle('background','#FFFF00');
						if(qty1==qty2){
							d.setBodyStyle('background','#DC143C');
						}
						if(qty1<qty2 && qty1>0){
							d.setBodyStyle('background','#0000FF');//部分分播
						}
						noDivideQty1=parseInt(noDivideQty1)+parseInt(qty2);
						noDivideQty2=parseInt(noDivideQty2)+parseInt(qty1);
					}
					noDivideQty3 = noDivideQty1 - noDivideQty2;
					Ext.getCmp('unQty3916').setText(noDivideQty3);
					Ext.getCmp('barcode3915').setValue('');
					Ext.getCmp('barcode3915').focus(true,10);
				}
				else
				{
					//Ext.Msg.alert($i18n.prompt,data.msg);
					Ext.Msg.alert($i18n.prompt,data.msg,					
							function(){   
								Ext.getCmp('divideNo3915').setValue('');
								Ext.getCmp('divideNo3915').focus(false, 10);
							});
							//Ext.getCmp('divideNo3915').focus(true, 10);
					//Ext.getCmp('divideNo3915').setValue('');
					clearPanMain3915();
				}
			}
		});
	},
	//分播任务号
	divideNo3915Keypress:function(th,e,eOpts){		
		if(e.keyCode == 13){
			if(Ext.isEmpty(workSpaceNo))
			{
				Ext.example.msg('提示',"工作站不能为空，请填写工作站！");
				Ext.getCmp('divideNo3915').setValue('');
				Ext.getCmp('divideNo3915').focus(true,10);
				return;
			}if(Ext.isEmpty(Ext.getCmp('workerNo3915').getValue()))
			{
				Ext.example.msg('提示',"分播人员不能为空！");
				Ext.getCmp('divideNo3915').setValue('');
				Ext.getCmp('workerNo3915').setValue('');
				Ext.getCmp('workerNo3915').focus(true,10);
				return;
			}if(Ext.isEmpty(Ext.getCmp('deviceNo3915').getValue()))
			{
				Ext.example.msg('提示',"分播墙号不能为空！");
				Ext.getCmp('divideNo3915').setValue('');
				Ext.getCmp('divideNo3915').setValue('');
				Ext.getCmp('divideNo3915').focus(true,10);
				return;
			}if(Ext.isEmpty(Ext.getCmp('divideNo3915').getValue()))
			{
				Ext.example.msg('提示',"任务号/标签号不能为空！");
				Ext.getCmp('divideNo3915').setValue('');
				Ext.getCmp('divideNo3915').focus(true,10);
				return;
			}Ext.Ajax.request({
				method:'POST',
				async:true,
				url:'odata_DivideWallAction_checkWorkerNo.action',
				params:{
					str:Ext.getCmp('workerNo3915').getValue()
				},
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data.isSucc){					
						
						if(data.msg == '该员工不存在'){
							Ext.example.msg($i18n_prompt.prompt,data.msg);
							Ext.getCmp('divideNo3915').setValue('');
							Ext.getCmp('workerNo3915').setValue('');
							Ext.getCmp('workerNo3915').focus(true,10);
							return;
						}else if(data.msg == '该员工的状态为停用'){
							Ext.example.msg($i18n_prompt.prompt,data.msg);
							Ext.getCmp('divideNo3915').setValue('');
							Ext.getCmp('workerNo3915').setValue('');
							Ext.getCmp('workerNo3915').focus(true,10);
							return;
						}
						var params = {
				  				str : Ext.getCmp('deviceNo3915').getValue(),
			  			};
				  		Ext.Ajax.request({
					  			method:'POST',
					  			url: 'odata_DivideWallAction_checkdeviceNo.action',
					  			async:false,
					  			params:params,
					  			success:function(response){
					  				var data = Ext.decode(response.responseText);
					  				if(data.isSucc == true){
					  				//获得分播墙号
										 var deviceNo = Ext.getCmp('deviceNo3915').getValue();
											Ext.Ajax.request({
												method:'POST',
												async:false,
												url:'odata_DivideWallAction_tscSelectEquipmentNo.action',
												params:{
													strEquipmentNo:Ext.getCmp('divideNo3915').getValue(),
													strDeviceNo:deviceNo,
													strWorkSpace:workSpaceNo
												},
												success:function(response)
												{
													var data = Ext.decode(response.responseText);
													if(data.isSucc)
													{
														clearPanMain3915();
														//赋值分播单号变量  7-27
														//divideNoTest = data.obj[2].divideNo;
														divideNoTest = data.obj[0].divideNo;
														
														//window.clearInterval(setInterval1);
														//window.clearInterval(setInterval2);
														
														//未播量总数的计算
														var noDivideQty1 = 0;
														var noDivideQty2 = 0;
														for(var i=0;i<data.obj.length;i++){
															var d=Ext.ComponentQuery.query('odata_DivideWallUI panel[name=3915'+data.obj[i].dpsCellNo+']')[0];
															var qty1 = data.obj[i].realQty / data.obj[i].qminOperatePacking;//换算已播量
															var qty2 = data.obj[i].articleQty / data.obj[i].qminOperatePacking;//换算应播量
															//d.items.items[5].setText(data.obj[i].expNo);     //编号
															d.items.items[5].setText(data.obj[i].shipperDeliverNo);     //编号
															d.items.items[2].setText(qty1);   //已播量
															d.items.items[4].setText(qty2);//应播量
															//待分播的标为黄色
															d.setBodyStyle('background','#FFFF00');
															if(qty1==qty2){
																d.setBodyStyle('background','#DC143C');
															}
															if(qty1<qty2 && qty1>0){
																d.setBodyStyle('background','#0000FF');//部分分播
															}
															noDivideQty1=parseInt(noDivideQty1)+parseInt(qty2);
															noDivideQty2=parseInt(noDivideQty2)+parseInt(qty1);
														}
														noDivideQty3 = noDivideQty1 - noDivideQty2;
														Ext.getCmp('unQty3916').setText(noDivideQty3);
														Ext.getCmp('barcode3915').setValue('');
														Ext.getCmp('barcode3915').focus(true,10);
													}
													else
													{
														//Ext.Msg.alert($i18n.prompt,data.msg);
														Ext.Msg.alert($i18n.prompt,data.msg,					
																function(){   
																	Ext.getCmp('divideNo3915').setValue('');
																	Ext.getCmp('divideNo3915').focus(false, 10);
																});
																//Ext.getCmp('divideNo3915').focus(true, 10);
														//Ext.getCmp('divideNo3915').setValue('');
														clearPanMain3915();
													}
												}
											});
					  				}
					  				else
					  				{  					
					  					Ext.example.msg('提示',"该分播墙号不存在，请重新输入！");
					  					Ext.getCmp('divideNo3915').setValue('');
					  					Ext.getCmp('deviceNo3915').setValue('');
										Ext.getCmp('deviceNo3915').focus(false, 10);
										return;
					  				}
					  			}
					  		});
						
					}else{
						 Ext.example.msg($i18n_prompt.prompt,data.msg);
						//Ext.getCmp('divideNo3915').setValue('');
					}
				}
			});		
			
		}
	},
	
	
	
	barcode3915Focus:function(th,newValue,oldValue,eOpts){
		if(Ext.isEmpty(Ext.getCmp('divideNo3915').getValue())){			
			Ext.example.msg($i18n_prompt.prompt,'标签号为空，请输入标签号！');
			Ext.getCmp('divideNo3915').focus(true,10);
			return;
		}
	},
	//扫描商品条码
	barcode3915Keydown:function(th,e,eOpts){
		/*if(e.keyCode!=13){
			Ext.getCmp('barcode3915').setValue('');
			Ext.getCmp('barcode3915').focus(true,20);
		}*//*
		if(Ext.isEmpty(th.value)){
			return;
		}*/		
		//8-10添加  
		if(e.keyCode == 13){
			if(Ext.isEmpty(workSpaceNo))
			{
				Ext.example.msg('提示',"工作站不能为空，请填写工作站！");
				Ext.getCmp('barcode3915').setValue('');
				Ext.getCmp('barcode3915').focus(true,10);
				return;
			}if(Ext.isEmpty(Ext.getCmp('workerNo3915').getValue()))
			{
				Ext.example.msg('提示',"分播人员不能为空！");
				Ext.getCmp('barcode3915').setValue('');
				Ext.getCmp('workerNo3915').setValue('');
				Ext.getCmp('workerNo3915').focus(true,10);
				return;
			}if(Ext.isEmpty(Ext.getCmp('deviceNo3915').getValue()))
			{
				Ext.example.msg('提示',"分播墙号不能为空！");
				Ext.getCmp('barcode3915').setValue('');
				Ext.getCmp('deviceNo3915').setValue('');
				Ext.getCmp('deviceNo3915').focus(true,10);
				return;
			}if(Ext.isEmpty(Ext.getCmp('divideNo3915').getValue()))
			{
				Ext.example.msg('提示',"任务号/标签号不能为空！");
				Ext.getCmp('barcode3915').setValue('');
				Ext.getCmp('divideNo3915').setValue('');
				Ext.getCmp('divideNo3915').focus(true,10);
				return;
			}if(Ext.isEmpty(Ext.getCmp('divideNo3915').getValue()))
			{
				Ext.example.msg('提示',"商品条码不能为空！");
				Ext.getCmp('barcode3915').setValue('');
				Ext.getCmp('divideNo3915').setValue('');
				Ext.getCmp('divideNo3915').focus(true,10);
				return;
			}

			
			var deviceNoTest = Ext.getCmp('deviceNo3915').getValue();  //获得分播墙号
			var equipmentNoTest = Ext.getCmp('divideNo3915').getValue();  //获得任务标签号
			Ext.Ajax.request({
				method:'POST',
				async:false,
				url:'odata_DivideWallAction_tscSelectEquipmentNo.action',
				params:{
					strEquipmentNo:equipmentNoTest,
					strDeviceNo:deviceNoTest,
					strWorkSpace:workSpaceNo
				},
				success:function(response)
				{
					var data = Ext.decode(response.responseText);
					if(data.isSucc)
					{
						clearPanMain3915();
						//赋值分播单号变量  7-27
						divideNoTest = data.obj[0].divideNo;
						//未播量总数的计算
						var noDivideQty1 = 0;
						var noDivideQty2 = 0;
						for(var i=0;i<data.obj.length;i++){
							var d=Ext.ComponentQuery.query('odata_DivideWallUI panel[name=3915'+data.obj[i].dpsCellNo+']')[0];
							var qty1 = data.obj[i].realQty / data.obj[i].qminOperatePacking;//换算已播量
							var qty2 = data.obj[i].articleQty / data.obj[i].qminOperatePacking;//换算应播量
							//d.items.items[5].setText(data.obj[i].expNo);     //编号
							d.items.items[5].setText(data.obj[i].shipperDeliverNo);     //编号
							d.items.items[2].setText(qty1);   //已播量
							d.items.items[4].setText(qty2);//应播量
							//待分播的标为黄色
							d.setBodyStyle('background','#FFFF00');
							if(qty1==qty2){
								d.setBodyStyle('background','#DC143C');
							}
							if(qty1<qty2 && qty1>0){
								d.setBodyStyle('background','#0000FF');
							}
							noDivideQty1=parseInt(noDivideQty1)+parseInt(qty2);
							noDivideQty2=parseInt(noDivideQty2)+parseInt(qty1);
						}
						noDivideQty3 = noDivideQty1 - noDivideQty2;
						Ext.getCmp('unQty3916').setText(noDivideQty3);
					}
				}
			});
			
			Ext.Ajax.request({
				method:'POST',
				//async:true,
				async:false,
				url:'odata_DivideWallAction_getOdata_Divide.action',
				params:{
					strEquipmentNo:Ext.getCmp('deviceNo3915').getValue(),
					str:Ext.getCmp('barcode3915').getValue()
				},
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						if(data.obj.length>0)
						{														
							surplusQty3915=data.obj[0].articleQty-data.obj[0].realQty;
							Ext.getCmp('lblArtNo3915').setText(data.obj[0].articleNo);
							Ext.getCmp('lblArtName3915').setText(data.obj[0].articleName);
							//debugger;
							//6-29
							Ext.Ajax.request({
								method:'POST',
								//async:true,
								async:false,
								url:'odata_DivideWallAction_tscSaveDivide.action',
								params:{
									//strEquipmentNo:data.obj[0].divideNo,
									strEquipmentNo:divideNoTest,	//7-27修改
									strArticleNo:Ext.getCmp('lblArtNo3915').text,
									strLabelNo:Ext.getCmp('divideNo3915').getValue()   //7-18添加
								},
								success:function(response){
									var data2 = Ext.decode(response.responseText);
									if(data2.isSucc)
									{
										//Ext.example.msg($i18n.prompt,data2.msg);
										Ext.getCmp('barcode3915').setValue('');
										
										//语音播报格子号
										var sound = Ext.getCmp('sound3915').getValue();
										if(sound == true){
											//var sound = data.obj[0].dpsCellNo;
											var sound = data2.obj[0];
											var sound1 = sound.toLowerCase();
											var arr = sound1.split("");
											function soundListen(test){
												if(test == 'a'){
													var t = document.createElement("audio");
													t.src="system/music/a1.mp3";
													t.play();
												}else{
													var t = document.createElement("audio");
													t.src="system/music/" + test +".mp3";
													t.play();
												}
											} 
											var i=0; 
											function al(){ 
												soundListen(arr[i]);
												if(i<arr.length)
													setTimeout(function(){al();},600);
												i++; 
											} 
											al();
										}
										selectDpsCellNo3915 = data2.obj[0];
										strFinishFlag= data2.obj[1];
										
										//正分播的为橙色
										Ext.ComponentQuery.query('odata_DivideWallUI panel[name=3915'+selectDpsCellNo3915+']')[0].setBodyStyle('background','#FFA500');
										
										//显示状态为正在分播
										var d2=Ext.ComponentQuery.query('odata_DivideWallUI panel[name=3915'+selectDpsCellNo3915+']')[0];
										//格子中数量加1
										var nReal_QTY=parseInt(d2.items.items[2].text)+parseInt(1);
										var nArticle_QTY=parseInt(d2.items.items[4].text);
										d2.items.items[2].setText(nReal_QTY);
										//未播量减1
										Ext.getCmp('unQty3916').setText(parseInt(noDivideQty3)-parseInt(1));
										//noDivideQty3 = parseInt(noDivideQty3)-parseInt(1);
										//判断格子是否分播完毕
										if(nReal_QTY==nArticle_QTY){
											//格子橙色
											d2.setBodyStyle('background','#FFA500');
										}
										
										//是否整单完成
										if (strFinishFlag=='Y')
										{
											Ext.Msg.alert($i18n.prompt,data2.msg,
											function(){   
											Ext.getCmp('divideNo3915').setValue('');
											Ext.getCmp('divideNo3915').focus(true,10);
											});	
										}
										else
										{
											Ext.getCmp('barcode3915').setValue('');
											Ext.getCmp('barcode3915').focus(true,10);
										}
																						
									}
									else
									{									
										Ext.Msg.alert($i18n.prompt,data2.msg,
												function(){   
													Ext.getCmp('barcode3915').setValue('');
													Ext.getCmp('barcode3915').focus(false, 10);
												});											
									}
								}
							});
						}
				 	}else{
				 		//播放duang的错误提醒音效    8-5添加
				 		var soundTest = Ext.getCmp('sound3915').getValue();
				 		if(soundTest == true){
				 			var duang = document.createElement("audio");
					 		duang.src="system/music/a.mp3";
					 		duang.play();
				 		}
						Ext.Msg.alert($i18n.prompt,data.msg,					
						function(){   
							Ext.getCmp('barcode3915').setValue('');
							Ext.getCmp('barcode3915').focus(false, 10);
						});
				 	}
				}
			});		
		}
			
	},
	
	
	cellCheck3915Focus:function(th,newValue,oldValue,eOpts){
		if(Ext.isEmpty(Ext.getCmp('divideNo3915').getValue())){			
			Ext.example.msg($i18n_prompt.prompt,'标签号为空，请输入标签号！');
			Ext.getCmp('divideNo3915').focus(true,10);
			return false;
		}
		if(Ext.isEmpty(Ext.getCmp('barcode3915').getValue())){			
			Ext.example.msg($i18n_prompt.prompt,'商品条码为空，请输入商品条码！');
			Ext.getCmp('barcode3915').focus(true,10);
			return false;
		}				
	},
	cellCheck3915Keydown:function(th,e,eOpts){
		if(e.keyCode!=13){
			return;
		}
		if(Ext.isEmpty(th.value)){
			return;
		}
		if(th.value!=selectDpsCellNo3915){
			th.setValue('');
			Ext.example.msg($i18n_prompt.prompt,'扫描储位与应投放储位不一致，请重新扫描！');
			return;
		}
		
		//?
		if(Ext.getCmp('scanQty3915').getValue()>surplusQty3915){			
			Ext.example.msg($i18n_prompt.prompt,'扫描基准数大于未分播数量['+surplusQty3915+']，请修改扫描基准数！');
			th.setValue('');
			Ext.getCmp('scanQty3915').focus(true,10);
			return;
		}		
		
		Ext.Ajax.request({
			method:'POST',
			async:true,
			url:'odata_DivideWallAction_tscSaveDivide.action',			
			params:{
				strEquipmentNo:Ext.getCmp('divideNo3915').getValue(),
				strArticleNo:Ext.getCmp('lblArtNo3915').text,
				realQty:Ext.getCmp('scanQty3915').getValue(),
				strDpsCellNo:th.value,				
			},
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc)
				{
					//Ext.example.msg($i18n.prompt,data.msg);
					Ext.getCmp('barcode3915').setValue('');
					Ext.getCmp('cellCheck3915').setValue('');
					
					
					var d=Ext.ComponentQuery.query('odata_DivideWallUI panel[name=3915'+selectDpsCellNo3915+']')[0];
					var qty3915=parseInt(d.items.items[1].text)+parseInt(Ext.getCmp('scanQty3915').getValue());
					d.items.items[1].setText(qty3915);
					if(qty3915==d.items.items[3].text){
						d.setBodyStyle('background','#DC143C');
					}
				
					Ext.getCmp('barcode3915').focus(true,10);
				}
				else
				{
					Ext.Msg.alert($i18n.prompt,data.msg);

					//Ext.example.msg($i18n.prompt,data.msg);
					Ext.getCmp('cellCheck3915').setValue('');
					Ext.getCmp('cellCheck3915').focus(true,10);
				}
			}
		});
	},
	
	releaseNoClick:function(){
		if(Ext.isEmpty(Ext.getCmp('divideNo3915').getValue())){			
			Ext.example.msg($i18n_prompt.prompt,'标签号为空，请输入标签号！');
			Ext.getCmp('divideNo3915').focus(true,10);
			return;
		}
		Ext.Msg.confirm($i18n.prompt, '确定分播取消并转病单？',function(button, text){
			if (button == 'yes'){
				Ext.Ajax.request({
					method:'POST',
					async:false,
					url:'odata_DivideWallAction_tscSureDivide.action',
					params:{
						strEquipmentNo:Ext.getCmp('divideNo3915').getValue()						
					},
					success:function(response){
						var data = Ext.decode(response.responseText);
						Ext.example.msg($i18n_prompt.prompt,data.msg);
						if(data.isSucc)
						{
							Ext.getCmp('divideNo3915').setValue('');
							Ext.getCmp('barcode3915').setValue('');
							Ext.getCmp('cellCheck3915').setValue('');
							clearPanMain3915();//_myAppGlobal.getController('cms.controller.odata.odata_DivideWallController').getOdata_DivideWallCell();
							Ext.getCmp('divideNo3915').focus(true,10);
						}				
					}
				});
			}
		});							
	},
	
	//点击分播明细  7-1
	divideDetailClick:function(){
		
		//var divideNo = Ext.getCmp('divideNo3915').getValue();
		var divideNo = divideNoTest;
		Ext.create('cms.view.odata.window.odata_divideDetailWindow',{
			listeners:{
				beforeclose:function(){
					Ext.getCmp('barcode3915').setValue('');
					Ext.getCmp('barcode3915').focus(true,10);
				}
			}		
		}).show();
		var wheresql={
				str : divideNo
		};
		Ext.apply(Ext.getCmp('gridDivideDetail').getStore().proxy.extraParams,wheresql);//提交store
		Ext.getCmp('gridDivideDetail').store.load();
	},
	
	getOdata_DivideWallCell:function(){		
		//请求从device_divide_m 获得 device_no
		Ext.Ajax.request({
			method:'POST',
			async:false,
			url:'odata_DivideWallAction_getOdata_DivideWallDeviceNo.action',
			success:function(response){
				var data = Ext.decode(response.responseText);
				
				var params = {
						str : data.obj[0].deviceNo,
				};
				Ext.Ajax.request({
					method:'POST',
					async:false,
					url:'odata_DivideWallAction_getOdata_DivideWallCell.action',
					params:params,
					success:function(response){
						var data = Ext.decode(response.responseText);
						for(var i=0;i<data.length;i++){
							Ext.getCmp('panMain3915').layout.columns=parseInt(data[0].countBayX);
							var pan=Ext.create(cms.view.odata.window.odataDivideCellPanel);
							pan.items.items[0].text=data[i].deviceCellNo;
							pan.height=Ext.getCmp('panMain3915').getSize().height/parseInt(data[0].countStockY)-5;
							pan.width=Ext.getCmp('panMain3915').getSize().width/parseInt(data[0].countBayX)-5;
							pan.name='3915'+data[i].deviceCellNo;
							Ext.getCmp('panMain3915').add(pan);
						}
					}
				});
		
			}
		});
	}
});

function clearPanMain3915(){	
	//将数量及单号恢复初始状态
	for(var i=0;i<Ext.getCmp('panMain3915').items.items.length;i++){
		Ext.getCmp('panMain3915').items.items[i].items.items[2].setText('0');
		Ext.getCmp('panMain3915').items.items[i].items.items[4].setText('0');
		Ext.getCmp('panMain3915').items.items[i].items.items[5].setText('');
		Ext.getCmp('panMain3915').items.items[i].items.items[6].setText('');
		Ext.getCmp('panMain3915').items.items[i].setBodyStyle('background','');
	}
}
