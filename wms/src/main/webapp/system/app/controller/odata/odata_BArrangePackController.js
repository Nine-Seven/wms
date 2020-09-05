/**
 * 模块名称：拆零容器整理打包（小嘴，按客户复核）
 * 模块代码：3928
 * @author hkl
 */
Ext.define('cms.controller.odata.odata_BArrangePackController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.odata.odata_BArrangePackUI'
	],
	init:function(){
		this.control({//刷新		
			'odata_BArrangePackUI commMenuWidget10[id=menu3928] [name=refresh]':{
				click:this.menu3928RefreshClick
			},//扫描码头号
			'odata_BArrangePackUI textfield[id=checkPlatNo3928]':{
				blur:this.checkPlatNo3928Blur
			},/*//扫描标签号码
			'odata_BArrangePackUI textfield[id=labelNo3928]':{
				blur:this.labelNo3928Blur
			},*///选择箱号
			'odata_BArrangePackUI grid[id=gridCusLabel3928]':
			{
				selectionchange:this.gridCusLabel3928Selectionchange
			},//箱号改变，获取箱明细
			'odata_BArrangePackUI textfield[id=loadBoxs3928]':
			{
				change:this.boxChange
			},//封箱
			'odata_BArrangePackUI button[id=btnCloseBox3928]':{
				click:this.btnCloseBox3928Click
			},//界面关闭前
			'odata_BArrangePackUI':{
				beforeclose:this.odata_BArrangePackUIBeforeclose
			},//enter键事件
			'odata_BArrangePackUI form field':{
				specialkey:this.boxkeydown
			},//回单
			'odata_BArrangePackUI button[id=btnReceipt3928]':{
				click:this.btnReceipt3928Click
			},//重扫
			'odata_BArrangePackUI button[id=btnAnew3928]':{
				click:this.btnAnewScan3928
			},//未复核商品选择
			'odata_BArrangePackUI grid[id=gridSlabelD3928]':{
				selectionchange:this.gridSlabelD3928change
			},//选择扫描方式
			'odata_BArrangePackUI radiogroup[id=rdoCheckType3928]':{
				change:this.rdoCheckType3928change
			}
		});
	},
	
	//初始化界面
	initializtion:function()
	{
		Ext.getCmp('checkPlatNo3928').setValue('');
		Ext.getCmp('labelNo3928').setValue('');
		Ext.getCmp('custNo3928').setValue('');
		Ext.getCmp('barcode3928').setValue('');
		Ext.getCmp('loadBoxs3928').setValue('');
		Ext.getCmp('nQty3928').setText('0');
		Ext.getCmp('sacnNum3928').setValue('1');
		Ext.getCmp('gridCusLabel3928').getStore().removeAll();
		Ext.getCmp('gridSlabelD3928').getStore().removeAll();
		Ext.getCmp('gridDlabelD3928').getStore().removeAll();
		
		Ext.getCmp('checkPlatNo3928').focus(false,10);
	},
	menu3928RefreshClick:function(){
		Ext.getCmp('loadBoxs3928').setValue('');
		Ext.getCmp('nQty3928').setText(0);
		
		Ext.getCmp('gridCusLabel3928').getStore().removeAll();
		Ext.getCmp('gridCusLabel3928').getStore().load();
		
		Ext.getCmp('gridSlabelD3928').getStore().removeAll();
		Ext.getCmp('gridSlabelD3928').getStore().load();
		
		Ext.getCmp('gridDlabelD3928').getStore().removeAll();
		Ext.getCmp('gridDlabelD3928').getStore().load();	
		
		Ext.getCmp('gridNoCheckBox3928').getStore().removeAll();
		Ext.getCmp('gridNoCheckBox3928').getStore().load();
	},
	//扫描扫描台号
	checkPlatNo3928Blur:function(){
		if(Ext.isEmpty(Ext.getCmp('checkPlatNo3928').getValue()))
		{
			return;
		}
		//校验扫描台号
		var params = {
				str : Ext.getCmp('checkPlatNo3928').getValue()
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
					Ext.getCmp('checkPlatNo3928').setValue("");
					Ext.getCmp('checkPlatNo3928').focus(false, 10);
				}
			}
		});		
	},
	//扫描标签
	labelNo3928Blur:function()
	{
		if(Ext.isEmpty(Ext.getCmp('labelNo3928').getValue()))
		{
			return;
		}
	
		//校验标签号码
		var params = {
			strQuery : Ext.getCmp('checkPlatNo3928').getValue(),
			str : Ext.getCmp('labelNo3928').getValue(),
			strWorkerNo: Ext.getCmp('cmbCheckWorker3928').getValue()
		};
		Ext.Ajax.request({
			method:'POST',
			url:'odata_BArrangePackAction_CheckSLabelNo',
			params:params,
			async:false,	
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					if(data.obj.batchNo==1){//借用BatchNo字段标识是否有其他未封箱的客户
						Ext.Msg.confirm($i18n.prompt,'您已经更换当前扫描的客户，但还有其他客户的箱子未封箱，是否封箱',function(button, text) 
							{
								if (button == 'yes')//不刷新页面
								{
									Ext.getCmp('labelNo3928').setValue("");
									return;
								}else if(button=='no'){
									labelNoBox3928(data);
								}
							});
					}else{
						labelNoBox3928(data);
					}
				}else
				{
					var audio = document.createElement("audio");
					audio.src="system/music/a.mp3";
					audio.play();
					Ext.Msg.alert('提示', data.msg);
					//Ext.example.msg('提示',data.msg);
					Ext.getCmp('nQty3928').setText(0);
					Ext.getCmp('loadBoxs3928').setValue("");
					Ext.getCmp('labelNo3928').setValue("");
					Ext.getCmp('labelNo3928').focus(false, 10);
				}
			}
		});		
	},
	//选择箱标签
	gridCusLabel3928Selectionchange:function(){

		var flag=0;
		var data = Ext.getCmp('gridCusLabel3928').getSelectionModel().getSelection();
		if(data.length!=0){
			if(data[0].get('labelNo')!=Ext.getCmp('labelNo3928').getValue()){
				Ext.getCmp('loadBoxs3928').setValue(data[0].get('labelNo'));	
				//取该标签的扫描数量
				var param = {
						str : Ext.getCmp('loadBoxs3928').getValue()
				};
				Ext.Ajax.request({
					method:'POST',
					url:'odata_BArrangePackAction_tscBoxQty',
					params:param,
					//async:false,	
					success:function(response){
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							Ext.getCmp('nQty3928').setText(data.obj);
						}		
					}
				});
			}else{
				Ext.example.msg($i18n.prompt,'该箱号不符合复核条件');
				return;
			}				
		}		
	},
	//跟新箱标签明细
	boxChange:function(){
		var params = {
				str : Ext.getCmp('loadBoxs3928').getValue()
			};
			Ext.apply(Ext.getCmp('gridDlabelD3928').getStore().proxy.extraParams,params);
			Ext.getCmp('gridDlabelD3928').getStore().removeAll();
			Ext.getCmp('gridDlabelD3928').getStore().load();
	},
	//条码回车
	barcode3928Blur:function(){
		if(Ext.isEmpty(Ext.getCmp('barcode3928').getValue()))
		{
			Ext.example.msg($i18n.prompt,'条码不能为空');
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('sacnNum3928').getValue()))
		{
			Ext.example.msg($i18n.prompt,'数量不能为空');
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('labelNo3928').getValue()))
		{
			Ext.example.msg($i18n.prompt,'标签号不能为空');
			return;
		}
		//判断选择方式,type:1为选择商品修改数量；0逐渐扫描
		var type =Ext.getCmp('rdoCheckType3928').getValue().rd;
		if(type==0){//0逐渐扫描
			//在条码的输入框进行保存
			var checkD="";
			var data = Ext.getCmp('gridSlabelD3928').getSelectionModel().getSelection();
			var record=Ext.getCmp('gridSlabelD3928').getStore().getAt(0);
			var strFlag="";
			if(data.length==0){
				strFlag=0;
			    checkD={					
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					ownerNo:record.get('ownerNo'),
					barcode:Ext.getCmp('barcode3928').getValue()
				};		
			}else{	
				//如果选择的商品和扫描的商品不一致，拦截
				if(Ext.getCmp('barcode3928').getValue() != data[0].get('barcode')){
					Ext.example.msg($i18n.prompt,'您选择的条码和扫描的条码不一致，请重新选择！、');
					return;
				}
				strFlag=1;
				checkD={					
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					ownerNo:record.get('ownerNo'),
					
					articleNo:data[0].get('articleNo'),
					packingQty:data[0].get('packingQty'),
					barcode:Ext.getCmp('barcode3928').getValue(),
					qminOperatePacking:data[0].get('qminOperatePacking'),
					
					produceDate:data[0].get('produceDate')==undefined? '1900-01-01':data[0].get('produceDate'),
					expireDate:data[0].get('expireDate')==undefined? '1900-01-01':data[0].get('expireDate'),
					quality:data[0].get('quality')==undefined?  '0':data[0].get('quality'),
					lotNo:data[0].get('lotNo')==undefined?  'N':data[0].get('lotNo'),
					rsvBatch1:data[0].get('rsvBatch1')==undefined?  'N':data[0].get('rsvBatch1'),
					rsvBatch2:data[0].get('rsvBatch2')==undefined?  'N':data[0].get('rsvBatch2'),
					rsvBatch3:data[0].get('rsvBatch3')==undefined?  'N':data[0].get('rsvBatch3'),
					rsvBatch4:data[0].get('rsvBatch4')==undefined?  'N':data[0].get('rsvBatch4'),
					rsvBatch5:data[0].get('rsvBatch5')==undefined?  'N':data[0].get('rsvBatch5'),
					rsvBatch6:data[0].get('rsvBatch6')==undefined?  'N':data[0].get('rsvBatch6'),
					rsvBatch7:data[0].get('rsvBatch7')==undefined?  'N':data[0].get('rsvBatch7'),
					rsvBatch8:data[0].get('rsvBatch8')==undefined?  'N':data[0].get('rsvBatch8')
				};		

			}		
			var str=Ext.encode(checkD);
				
			//  校验条码，并保存
			var params={
				str:str,
				sacnNum : Ext.getCmp('sacnNum3928').getValue(),
				strSlabel:Ext.getCmp('labelNo3928').getValue(),
				strDlabel:Ext.getCmp('loadBoxs3928').getValue(),
				strWorkerNo:Ext.getCmp('cmbCheckWorker3928').getValue(),
				strFlag:strFlag,
				strPrinterGroupNo:Ext.getCmp('checkPlatNo3928').getValue()
			};
			Ext.Ajax.request({
				method:'POST',
				url:'odata_BArrangePackAction_tscCheckBarcodeAndSave.action',
				params:params,
				async:false,	
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						//刷新商品数据
						if(Ext.getCmp('loadBoxs3928').getValue()!=data.obj)
						{
							Ext.getCmp('loadBoxs3928').setValue(data.obj);
							var params = {
								str : Ext.getCmp('labelNo3928').getValue()
								//str : Ext.getCmp('loadBoxs3928').getValue()
							};
							Ext.apply(Ext.getCmp('gridCusLabel3928').getStore().proxy.extraParams,params);
							Ext.getCmp('gridCusLabel3928').getStore().removeAll();
							Ext.getCmp('gridCusLabel3928').getStore().load();
						}
						
						//取该标签的扫描数量
						var param = {
								str : Ext.getCmp('loadBoxs3928').getValue()
						};
						Ext.Ajax.request({
							method:'POST',
							url:'odata_BArrangePackAction_tscBoxQty',
							params:param,
							//async:false,	
							success:function(response){
								var data = Ext.decode(response.responseText);
								if(data.isSucc){
									Ext.getCmp('nQty3928').setText(data.obj);
								}		
							}
						});
						
						var params = {
							str : Ext.getCmp('labelNo3928').getValue()
						};
						Ext.apply(Ext.getCmp('gridSlabelD3928').getStore().proxy.extraParams,params);
						Ext.getCmp('gridSlabelD3928').getStore().removeAll();
						Ext.getCmp('gridSlabelD3928').getStore().load();
						
						var params = {
							str : Ext.getCmp('loadBoxs3928').getValue()
						};
						Ext.apply(Ext.getCmp('gridDlabelD3928').getStore().proxy.extraParams,params);
						Ext.getCmp('gridDlabelD3928').getStore().removeAll();
						Ext.getCmp('gridDlabelD3928').getStore().load();
					}else{
						var audio = document.createElement("audio");
						audio.src="system/music/a.mp3";
						audio.play();
						Ext.Msg.alert('提示', data.msg);
					//	Ext.example.msg('提示',data.msg);
					}		
				
					Ext.getCmp('barcode3928').setValue('');
					Ext.getCmp('barcode3928').focus(false,10);
				}
			});
			
		}else if(type==1){//1为选择商品修改数量
			//在数量的输入框才能保存
			Ext.getCmp('sacnNum3928').focus();
		}
		
	},
	//修改数量
	sacnNum3928Blur:function(th,e,eOpts)
	{
		if(Ext.isEmpty(Ext.getCmp('barcode3928').getValue()))
		{
			Ext.example.msg($i18n.prompt,'条码不能为空');
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('sacnNum3928').getValue()))
		{
			Ext.example.msg($i18n.prompt,'数量不能为空');
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('labelNo3928').getValue()))
		{
			Ext.example.msg($i18n.prompt,'标签号不能为空');
			return;
		}
		//判断选择方式,type:1为选择商品修改数量；0逐渐扫描
		var type =Ext.getCmp('rdoCheckType3928').getValue().rd;
		if(type==1){//1为选择商品修改数量
			//在数量的输入框才能保存
			var checkD="";
			var data = Ext.getCmp('gridSlabelD3928').getSelectionModel().getSelection();
			var record=Ext.getCmp('gridSlabelD3928').getStore().getAt(0);
			var strFlag="";
			if(data.length==0){
				strFlag=0;
			    checkD={					
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					ownerNo:record.get('ownerNo'),
					barcode:Ext.getCmp('barcode3928').getValue()
				};		
			}else{	
				strFlag=1;
				checkD={					
					enterpriseNo:Ext.get('enterpriseNo').getValue(),
					warehouseNo:Ext.get('warehouseNo').getValue(),
					ownerNo:record.get('ownerNo'),
					
					articleNo:data[0].get('articleNo'),
					packingQty:data[0].get('packingQty'),
					barcode:Ext.getCmp('barcode3928').getValue(),
					qminOperatePacking:data[0].get('qminOperatePacking'),
					
					produceDate:data[0].get('produceDate')==undefined? '1900-01-01':data[0].get('produceDate'),
					expireDate:data[0].get('expireDate')==undefined? '1900-01-01':data[0].get('expireDate'),
					quality:data[0].get('quality')==undefined?  '0':data[0].get('quality'),
					lotNo:data[0].get('lotNo')==undefined?  'N':data[0].get('lotNo'),
					rsvBatch1:data[0].get('rsvBatch1')==undefined?  'N':data[0].get('rsvBatch1'),
					rsvBatch2:data[0].get('rsvBatch2')==undefined?  'N':data[0].get('rsvBatch2'),
					rsvBatch3:data[0].get('rsvBatch3')==undefined?  'N':data[0].get('rsvBatch3'),
					rsvBatch4:data[0].get('rsvBatch4')==undefined?  'N':data[0].get('rsvBatch4'),
					rsvBatch5:data[0].get('rsvBatch5')==undefined?  'N':data[0].get('rsvBatch5'),
					rsvBatch6:data[0].get('rsvBatch6')==undefined?  'N':data[0].get('rsvBatch6'),
					rsvBatch7:data[0].get('rsvBatch7')==undefined?  'N':data[0].get('rsvBatch7'),
					rsvBatch8:data[0].get('rsvBatch8')==undefined?  'N':data[0].get('rsvBatch8')
				};		

			}		
			var str=Ext.encode(checkD);
				
			//  校验条码，并保存
			var params={
				str:str,
				sacnNum : Ext.getCmp('sacnNum3928').getValue(),
				strSlabel:Ext.getCmp('labelNo3928').getValue(),
				strDlabel:Ext.getCmp('loadBoxs3928').getValue(),
				strWorkerNo:Ext.getCmp('cmbCheckWorker3928').getValue(),
				strFlag:strFlag,
				strPrinterGroupNo:Ext.getCmp('checkPlatNo3928').getValue()
			};
			Ext.Ajax.request({
				method:'POST',
				url:'odata_BArrangePackAction_tscCheckBarcodeAndSave.action',
				params:params,
				async:false,	
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						//刷新商品数据
						if(Ext.getCmp('loadBoxs3928').getValue()!=data.obj)
						{
							Ext.getCmp('loadBoxs3928').setValue(data.obj);
							var params = {
								str : Ext.getCmp('labelNo3928').getValue()
								//str : Ext.getCmp('loadBoxs3928').getValue()
							};
							Ext.apply(Ext.getCmp('gridCusLabel3928').getStore().proxy.extraParams,params);
							Ext.getCmp('gridCusLabel3928').getStore().removeAll();
							Ext.getCmp('gridCusLabel3928').getStore().load();
						}
						
						//取该标签的扫描数量
						var param = {
								str : Ext.getCmp('loadBoxs3928').getValue()
						};
						Ext.Ajax.request({
							method:'POST',
							url:'odata_BArrangePackAction_tscBoxQty',
							params:param,
							//async:false,	
							success:function(response){
								var data = Ext.decode(response.responseText);
								if(data.isSucc){
									Ext.getCmp('nQty3928').setText(data.obj);
								}		
							}
						});
						
						var params = {
							str : Ext.getCmp('labelNo3928').getValue()
						};
						Ext.apply(Ext.getCmp('gridSlabelD3928').getStore().proxy.extraParams,params);
						Ext.getCmp('gridSlabelD3928').getStore().removeAll();
						Ext.getCmp('gridSlabelD3928').getStore().load();
						
						var params = {
							str : Ext.getCmp('loadBoxs3928').getValue()
						};
						Ext.apply(Ext.getCmp('gridDlabelD3928').getStore().proxy.extraParams,params);
						Ext.getCmp('gridDlabelD3928').getStore().removeAll();
						Ext.getCmp('gridDlabelD3928').getStore().load();
						Ext.getCmp('barcode3928').setValue('');
						Ext.getCmp('sacnNum3928').setValue('1');
					}else{
						var audio = document.createElement("audio");
						audio.src="system/music/a.mp3";
						audio.play();
						Ext.Msg.alert('提示', data.msg);
						//Ext.example.msg('提示',data.msg);
					}		
				
					
					
				}
			});
		}else if(type==0){//0逐渐扫描
			//在条码的输入框进行保存
			Ext.getCmp('barcode3928').focus();
		}

		
	},
	boxkeydown:function(th,e,eOpts){
	    //判断选择方式,type:1为选择商品修改数量；0逐渐扫描
		var type =Ext.getCmp('rdoCheckType3928').getValue().rd;
	  	if (e.getKey() == e.ENTER) {
	  		if(th.id=="labelNo3928"){
	  			this.labelNo3928Blur();
	  			Ext.getCmp('barcode3928').focus();
	  		}else if(th.id=="barcode3928"){
	  			if(type==1){
	  				Ext.getCmp('sacnNum3928').focus();
	  			}else{
	  				this.barcode3928Blur();
	  			}
	  		}else if(th.id=="sacnNum3928"){
	  			if(type==0){
	  				Ext.getCmp('barcode3928').focus();
	  			}else{
	  				this.sacnNum3928Blur();
	  			}
	  		}else if(th.id=="weight3928"){
	  			Ext.getCmp('weight3928').focus();
	  		}
	  		else{
				th.nextSibling().focus();
			}
        }
	},
	btnCloseBox3928Click:function(){
		if(Ext.isEmpty(Ext.getCmp('checkPlatNo3928').getValue()))
		{
			Ext.example.msg('提示',"复核台号不能为空，请扫描复核台！");
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('loadBoxs3928').getValue()))
		{
			Ext.example.msg('提示',"封箱标签不能为空！");
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('weight3928').getValue()))
		{
			Ext.example.msg('提示',"请输入该箱的重量！");
			Ext.getCmp('weight3928').focus();
			return;
		}
		var params={
			strDlabel:Ext.getCmp('loadBoxs3928').getValue(),
			strWorkerNo:Ext.getCmp('cmbCheckWorker3928').getValue(),
			strPrinterGroupNo:Ext.getCmp('checkPlatNo3928').getValue(),
			strWeight:Ext.getCmp('weight3928').getValue()
		};
		Ext.Ajax.request({
			method:'POST',
			url:'odata_BArrangePackAction_tscArrangeCutbox',
			params:params,
			async:false,	
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					//刷新商品数据
					Ext.getCmp('nQty3928').setText(0);
					Ext.getCmp('loadBoxs3928').setValue("");
					Ext.getCmp('barcode3928').setValue("");
					Ext.getCmp('weight3928').setValue('');

					Ext.getCmp('gridCusLabel3928').getStore().removeAll();
					Ext.getCmp('gridCusLabel3928').getStore().load();
					
					Ext.getCmp('gridDlabelD3928').getStore().removeAll();
					Ext.getCmp('gridDlabelD3928').getStore().load();	
					
					if(Ext.getCmp('gridSlabelD3928').getStore().count()==0){
						Ext.getCmp('labelNo3928').setValue("");
						Ext.getCmp('custNo3928').setValue("");
						Ext.getCmp('labelNo3928').focus();
					}else{
						Ext.getCmp('barcode3928').focus();
					}
					Ext.example.msg('提示',data.msg);
				}else{
					var audio = document.createElement("audio");
					audio.src="system/music/a.mp3";
					audio.play();
					Ext.Msg.alert('提示', data.msg);
					//Ext.example.msg('提示',data.msg);
				}		
			}
		});
	},
	odata_BArrangePackUIBeforeclose:function(){
		if(!Ext.isEmpty(Ext.getCmp('loadBoxs3928').getValue()))
		{
			Ext.example.msg('提示','未封箱，请先封箱再退出界面！');
			return false;
		}
	},
	//重扫
	btnAnewScan3928:function(){
		if(Ext.getCmp('loadBoxs3928').getValue()==null || Ext.getCmp('loadBoxs3928').getValue()==""){
			Ext.example.msg('提示','请选择重扫的标签号！');
			return
		}
		if(Ext.getCmp('cmbCheckWorker3928').getValue()==null || Ext.getCmp('cmbCheckWorker3928').getValue()==""){
			Ext.example.msg('提示','复核人不能为空！');
			return
		}
		Ext.Msg.confirm($i18n.prompt,'确定重新扫描未封箱的数据么？',function(button,text){
			if(button=='yes'){
				Ext.Ajax.request({
					url:'odata_BArrangePackAction_tscAnewScan.action',
					params:{
						strDlabel:Ext.getCmp('loadBoxs3928').getValue(),
						strWorkerNo:Ext.getCmp('cmbCheckWorker3928').getValue()
					},
					success:function(response){
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							//刷新商品数据
							Ext.getCmp('nQty3928').setText(0);
							
							Ext.getCmp('gridDlabelD3928').getStore().removeAll();
							Ext.getCmp('gridSlabelD3928').getStore().load();	
						   
							Ext.getCmp('barcode3928').focus();
						    Ext.example.msg('提示',data.msg);
						}else{
							Ext.Msg.alert('提示', data.msg);
							//Ext.example.msg('提示',data.msg);
						}		
					}				
				});		
			}
		});
	},
	
	//回单
	btnReceipt3928Click:function(){		
		if(Ext.getCmp('labelNo3928').getValue()==null || Ext.getCmp('labelNo3928').getValue()==""){
			Ext.example.msg('提示','标签号为空，不能回单');
			return
		}
		
		//log20151113 modi by lizhiping 不同源标签允许向同一目的标签扫描,即不用封箱也可以回单
		/*if(Ext.getCmp('loadBoxs3928').getValue()!=null && Ext.getCmp('loadBoxs3928').getValue()!=""){
			Ext.example.msg('提示','请先封箱');
			return
		}*/
		//end log20151113 modi
		
		Ext.Msg.confirm($i18n.prompt,'是否确定回单？',function(button,text){
			if(button=='yes'){
				Ext.Ajax.request({
					url:'odata_BArrangePackAction_receipt.action',
					params:{
						strSlabel:Ext.getCmp('labelNo3928').getValue()
					},
					success:function(response){
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							//刷新商品数据
							Ext.getCmp('nQty3928').setText(0);
							//Ext.getCmp('loadBoxs3928').setValue("");
							Ext.getCmp('labelNo3928').setValue("");
						    Ext.getCmp('custNo3928').setValue("");
						    Ext.getCmp('labelNo3928').focus();
						    Ext.example.msg('提示',data.msg);
						}else{
							Ext.Msg.alert('提示', data.msg);
							//Ext.example.msg('提示',data.msg);
						}		
					}				
				});		
			}
		});
	},
	//选择未复核的商品列表
	gridSlabelD3928change:function(){
		var data = Ext.getCmp('gridSlabelD3928').getSelectionModel().getSelection();
		var type =Ext.getCmp('rdoCheckType3928').getValue().rd;
		//如果选择的是逐渐扫描，则选择无效，不填充
		if(type==1){//选择商品修改数量
			if(data.length != 0){
				Ext.getCmp('barcode3928').setValue(data[0].get('barcode'));
				Ext.getCmp('sacnNum3928').setValue(data[0].get('uncheckQty'));
				//
				 
				Ext.getCmp('sacnNum3928').focus(false,10);
				Ext.getCmp('sacnNum3928').selectText();
				
			}
		}

	},
	rdoCheckType3928change:function(){
		Ext.getCmp('barcode3928').setValue('');
		Ext.getCmp('sacnNum3928').setValue('1');
	}
	
});

function labelNoBox3928(data){

	Ext.getCmp('custNo3928').setValue(data.obj.custNo);
	Ext.getCmp('boxsQty3928').setValue(data.obj.boxsQty);
	Ext.getCmp('nQty3928').setText(0);
	Ext.getCmp('loadBoxs3928').setValue("");
	//该单的状态是未封箱已回单的单据
	if(data.obj.status=="12"){
		Ext.Msg.confirm($i18n.prompt,'该标签已回单但还有箱未封箱，是否继续扫描？【不继续扫描则要将未封箱的箱号封箱】',function(button, text) 
		{
			if (button == 'yes') //继续扫描则改变复合单头档状态为10
			{
				var param = {
						str : Ext.getCmp('labelNo3928').getValue()
				};
				Ext.Ajax.request({
					method:'POST',
					url:'odata_BArrangePackAction_updateCheckM',
					params:param,
					//async:false,	
					success:function(response){
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							var params = {
									str : Ext.getCmp('labelNo3928').getValue(),
									strPrinterGroupNo:Ext.getCmp('checkPlatNo3928').getValue()
							};
							Ext.apply(Ext.getCmp('gridCusLabel3928').getStore().proxy.extraParams,params);
							Ext.getCmp('gridCusLabel3928').getStore().removeAll();
							Ext.getCmp('gridCusLabel3928').getStore().load();
							
							Ext.apply(Ext.getCmp('gridSlabelD3928').getStore().proxy.extraParams,params);
							Ext.getCmp('gridSlabelD3928').getStore().removeAll();
							Ext.getCmp('gridSlabelD3928').getStore().load();
							
							//Ext.getCmp('gridCusLabel3928').getSelectionModel().select(0);
							Ext.getCmp('barcode3928').focus(false, 10);
						}else{
							var audio = document.createElement("audio");
							audio.src="system/music/a.mp3";
							audio.play();
							//Ext.example.msg('提示',data.msg);
							Ext.Msg.alert('提示', data.msg);
							Ext.getCmp('labelNo3928').setValue("");
							Ext.getCmp('labelNo3928').focus(false, 10);
						}		
					}
				});
			}else if(button=='no'){
				var params = {
						str : Ext.getCmp('labelNo3928').getValue(),
						strPrinterGroupNo:Ext.getCmp('checkPlatNo3928').getValue()
				};
				Ext.apply(Ext.getCmp('gridCusLabel3928').getStore().proxy.extraParams,params);
				Ext.getCmp('gridCusLabel3928').getStore().removeAll();
				Ext.getCmp('gridCusLabel3928').getStore().load();
				
				Ext.apply(Ext.getCmp('gridSlabelD3928').getStore().proxy.extraParams,params);
				Ext.getCmp('gridSlabelD3928').getStore().removeAll();
				Ext.getCmp('gridSlabelD3928').getStore().load();
				
				Ext.getCmp('labelNo3928').setValue('');

				//Ext.getCmp('gridCusLabel3928').getSelectionModel().select(0);
			}
		});
	}else{
		//如果校码通过，则查询标签对应客户的该码头的标签数据
		var params = {
			str : Ext.getCmp('labelNo3928').getValue(),
			strPrinterGroupNo:Ext.getCmp('checkPlatNo3928').getValue()
		};
		Ext.apply(Ext.getCmp('gridCusLabel3928').getStore().proxy.extraParams,params);
		Ext.getCmp('gridCusLabel3928').getStore().removeAll();
		Ext.getCmp('gridCusLabel3928').getStore().load();
		
		Ext.apply(Ext.getCmp('gridSlabelD3928').getStore().proxy.extraParams,params);
		Ext.getCmp('gridSlabelD3928').getStore().removeAll();
		Ext.getCmp('gridSlabelD3928').getStore().load();
	}
		
}