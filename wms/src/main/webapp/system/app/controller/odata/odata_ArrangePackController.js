/**
 * 模块名称：容器整理打包（天天惠，按客户复核）
 * 模块代码：3913
 * @author hkl
 */
Ext.define('cms.controller.odata.odata_ArrangePackController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.odata.odata_ArrangePackUI'
	],
	init:function(){
		this.control({//刷新		
			'odata_ArrangePackUI commMenuWidget4[id=menu3913] [name=refresh]':{
				click:this.menu3913RefreshClick
			},//扫描码头号
			'odata_ArrangePackUI textfield[id=checkPlatNo3913]':{
				blur:this.checkPlatNo3913Blur
			},//扫描标签号码
			'odata_ArrangePackUI textfield[id=labelNo3913]':{
				blur:this.labelNo3913Blur
			},//选择箱号
			'odata_ArrangePackUI grid[id=gridCusLabel3913]':
			{
				selectionchange:this.gridCusLabel3913Selectionchange
			},//箱号改变，获取箱明细
			'odata_ArrangePackUI textfield[id=loadBoxs3913]':
			{
				change:this.boxChange
			},//输入条码
			'odata_ArrangePackUI textfield[id=barcode3913]':{
				blur:this.barcode3913Blur
			},//封箱
			'odata_ArrangePackUI button[id=btnCloseBox3913]':{
				click:this.btnCloseBox3913Click
			},//界面关闭前
			'odata_ArrangePackUI':{
				beforeclose:this.odata_ArrangePackUIBeforeclose
			},//enter键事件
			'odata_ArrangePackUI form field':{
				specialkey:this.boxkeydown
			},//回单
			'odata_ArrangePackUI button[id=btnReceipt3913]':{
				click:this.btnReceipt3913Click
			},//重扫
			'odata_ArrangePackUI button[id=btnAnew3913]':{
				click:this.btnAnewScan3913
			}
		});
	},
	
	//初始化界面
	initializtion:function()
	{
		Ext.getCmp('checkPlatNo3913').setValue('');
		Ext.getCmp('labelNo3913').setValue('');
		Ext.getCmp('custNo3913').setValue('');
		Ext.getCmp('barcode3913').setValue('');
		Ext.getCmp('loadBoxs3913').setValue('');
		Ext.getCmp('nQty3913').setText('0');
		Ext.getCmp('sacnNum3913').setValue('1');
		Ext.getCmp('gridCusLabel3913').getStore().removeAll();
		Ext.getCmp('gridSlabelD3913').getStore().removeAll();
		Ext.getCmp('gridDlabelD3913').getStore().removeAll();
		
		Ext.getCmp('checkPlatNo3913').focus(false,10);
		
		//显示变量0为不显示，1为显示
		var packingUnit_3913=commonGetModuleField('3913','packingUnit')[0].flag;
		var packingSpec_3913=commonGetModuleField('3913','packingSpec')[0].flag;
		
		if(packingUnit_3913==0){
			Ext.getCmp('packingUnit_3913').setVisible(false);
			Ext.getCmp('packingUnit_3913_1').setVisible(false);
		}
		if(packingSpec_3913==0){
			Ext.getCmp('packingSpec_3913').setVisible(false);
			Ext.getCmp('packingSpec_3913_1').setVisible(false);
		}
		
	},
	menu3913RefreshClick:function(){
		Ext.getCmp('loadBoxs3913').setValue('');
		Ext.getCmp('nQty3913').setText(0);
		
		Ext.getCmp('gridCusLabel3913').getStore().removeAll();
		Ext.getCmp('gridCusLabel3913').getStore().load();
		
		Ext.getCmp('gridSlabelD3913').getStore().removeAll();
		Ext.getCmp('gridSlabelD3913').getStore().load();
		
		Ext.getCmp('gridDlabelD3913').getStore().removeAll();
		Ext.getCmp('gridDlabelD3913').getStore().load();		
	},
	//扫描扫描台号
	checkPlatNo3913Blur:function(){
		if(Ext.isEmpty(Ext.getCmp('checkPlatNo3913').getValue()))
		{
			return;
		}
		//校验扫描台号
		var params = {
				str : Ext.getCmp('checkPlatNo3913').getValue()
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
					Ext.Msg.alert('提示',data.msg);
					//Ext.example.msg('提示',data.msg);
					Ext.getCmp('checkPlatNo3913').setValue("");
					Ext.getCmp('checkPlatNo3913').focus(false, 10);
				}
			}
		});		
	},
	//扫描标签
	labelNo3913Blur:function()
	{
		if(Ext.isEmpty(Ext.getCmp('labelNo3913').getValue()))
		{
			return;
		}
		Ext.getCmp('nQty3913').setText(0);
		Ext.getCmp('loadBoxs3913').setValue("");
		//校验标签号码
		var params = {
			strQuery : Ext.getCmp('checkPlatNo3913').getValue(),
			str : Ext.getCmp('labelNo3913').getValue(),
			strWorkerNo: Ext.getCmp('cmbCheckWorker3913').getValue()
		};
		Ext.Ajax.request({
			method:'POST',
			url:'odata_ArrangePackAction_CheckSLabelNo',
			params:params,
			async:false,	
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.getCmp('custNo3913').setValue(data.obj.custNo);

					//该单的状态是未封箱已回单的单据
					if(data.obj.status=="12"){
						Ext.Msg.confirm($i18n.prompt,'该标签已回单但还有箱未封箱，是否继续扫描？【不继续扫描则要将未封箱的箱号封箱】',function(button, text) 
						{
							if (button == 'yes') //继续扫描则改变复合单头档状态为10
							{
								var param = {
										str : Ext.getCmp('labelNo3913').getValue()
								};
								Ext.Ajax.request({
									method:'POST',
									url:'odata_ArrangePackAction_updateCheckM',
									params:param,
									//async:false,	
									success:function(response){
										var data = Ext.decode(response.responseText);
										if(data.isSucc){
											var params = {
													str : Ext.getCmp('labelNo3913').getValue(),
													strPrinterGroupNo:Ext.getCmp('checkPlatNo3913').getValue()
											};
											Ext.apply(Ext.getCmp('gridCusLabel3913').getStore().proxy.extraParams,params);
											Ext.getCmp('gridCusLabel3913').getStore().removeAll();
											Ext.getCmp('gridCusLabel3913').getStore().load();
											
											Ext.apply(Ext.getCmp('gridSlabelD3913').getStore().proxy.extraParams,params);
											Ext.getCmp('gridSlabelD3913').getStore().removeAll();
											Ext.getCmp('gridSlabelD3913').getStore().load();
											
											//Ext.getCmp('gridCusLabel3913').getSelectionModel().select(0);
											Ext.getCmp('barcode3913').focus(false, 10);
										}else{
											var audio = document.createElement("audio");
											audio.src="system/music/a.mp3";
											audio.play();
											//Ext.example.msg('提示',data.msg);
											Ext.Msg.alert('提示',data.msg);
											Ext.getCmp('labelNo3913').setValue("");
											Ext.getCmp('labelNo3913').focus(false, 10);
										}		
									}
								});
							}else if(button=='no'){
								var params = {
										str : Ext.getCmp('labelNo3913').getValue(),
										strPrinterGroupNo:Ext.getCmp('checkPlatNo3913').getValue()
								};
								Ext.apply(Ext.getCmp('gridCusLabel3913').getStore().proxy.extraParams,params);
								Ext.getCmp('gridCusLabel3913').getStore().removeAll();
								Ext.getCmp('gridCusLabel3913').getStore().load();
								
								Ext.apply(Ext.getCmp('gridSlabelD3913').getStore().proxy.extraParams,params);
								Ext.getCmp('gridSlabelD3913').getStore().removeAll();
								Ext.getCmp('gridSlabelD3913').getStore().load();
								
								Ext.getCmp('labelNo3913').setValue('');

								//Ext.getCmp('gridCusLabel3913').getSelectionModel().select(0);
							}
						});
					}else{
						//如果校码通过，则查询标签对应客户的该码头的标签数据
						var params = {
							str : Ext.getCmp('labelNo3913').getValue(),
							strPrinterGroupNo:Ext.getCmp('checkPlatNo3913').getValue()
						};
						Ext.apply(Ext.getCmp('gridCusLabel3913').getStore().proxy.extraParams,params);
						Ext.getCmp('gridCusLabel3913').getStore().removeAll();
						Ext.getCmp('gridCusLabel3913').getStore().load();
						
						Ext.apply(Ext.getCmp('gridSlabelD3913').getStore().proxy.extraParams,params);
						Ext.getCmp('gridSlabelD3913').getStore().removeAll();
						Ext.getCmp('gridSlabelD3913').getStore().load();
					}
					
				}else
				{
					var audio = document.createElement("audio");
					audio.src="system/music/a.mp3";
					audio.play();
					Ext.Msg.alert('提示',data.msg);
					//Ext.example.msg('提示',data.msg);
					Ext.getCmp('labelNo3913').setValue("");
					Ext.getCmp('labelNo3913').focus(false, 10);
				}
			}
		});		
	},
	//选择箱标签
	gridCusLabel3913Selectionchange:function(){

		var flag=0;
		var data = Ext.getCmp('gridCusLabel3913').getSelectionModel().getSelection();
		if(data.length!=0){
			if(data[0].get('labelNo')!=Ext.getCmp('labelNo3913').getValue()){
				Ext.getCmp('loadBoxs3913').setValue(data[0].get('labelNo'));	
				//取该标签的扫描数量
				var param = {
						str : Ext.getCmp('loadBoxs3913').getValue()
				};
				Ext.Ajax.request({
					method:'POST',
					url:'odata_ArrangePackAction_tscBoxQty',
					params:param,
					//async:false,	
					success:function(response){
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							Ext.getCmp('nQty3913').setText(data.obj);
						}		
					}
				});
			}else{
				//Ext.example.msg($i18n.prompt,'该箱号不符合复核条件');
				Ext.Msg.alert('提示','该箱号不符合复核条件');
				return;
			}				
		}		
	},
	//跟新箱标签明细
	boxChange:function(){
		var params = {
				str : Ext.getCmp('loadBoxs3913').getValue()
			};
			Ext.apply(Ext.getCmp('gridDlabelD3913').getStore().proxy.extraParams,params);
			Ext.getCmp('gridDlabelD3913').getStore().removeAll();
			Ext.getCmp('gridDlabelD3913').getStore().load();
	},
	
	//扫描条码
	barcode3913Blur:function()
	{
		if(Ext.isEmpty(Ext.getCmp('barcode3913').getValue()))
		{
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('labelNo3913').getValue()))
		{
			Ext.example.msg($i18n.prompt,'标签号不能为空');
			return;
		}
		//判断商品的数据量是否小于基准量
		/*var flag=checkTheArticleNum();
		if(flag!=5){
			var audio = document.createElement("audio");
			audio.src="system/music/a.mp3";
			audio.play();
		}
		
		if(flag==1 || flag==3){
			Ext.example.msg($i18n.prompt,'扫描基准量大于商品剩余量');
			Ext.getCmp('barcode3913').setValue('');
			Ext.getCmp('barcode3913').focus(false,10);
			return;
		}else if(flag==2){
			Ext.example.msg($i18n.prompt,'扫描条码和选择商品明细不符合');
			Ext.getCmp('barcode3913').setValue('');
			Ext.getCmp('barcode3913').focus(false,10);
			return;
		}else if(flag==4){
			Ext.example.msg($i18n.prompt,'扫描条码不存在');
			Ext.getCmp('barcode3913').setValue('');
			Ext.getCmp('barcode3913').focus(false,10);
			return;
		}*/
		var checkD="";
		var data = Ext.getCmp('gridSlabelD3913').getSelectionModel().getSelection();
		var record=Ext.getCmp('gridSlabelD3913').getStore().getAt(0);
		var strFlag="";
		if(data.length==0){
			strFlag=0;
		    checkD={					
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.get('warehouseNo').getValue(),
				ownerNo:record.get('ownerNo'),
				barcode:Ext.getCmp('barcode3913').getValue()
			};		
		}else{	
			strFlag=1;
			checkD={					
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:Ext.get('warehouseNo').getValue(),
				ownerNo:record.get('ownerNo'),
				
				articleNo:data[0].get('articleNo'),
				packingQty:data[0].get('packingQty'),
				barcode:Ext.getCmp('barcode3913').getValue(),
				
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
			
		//  校验条码
		var params={
			str:str,
			sacnNum : Ext.getCmp('sacnNum3913').getValue(),
			strSlabel:Ext.getCmp('labelNo3913').getValue(),
			strDlabel:Ext.getCmp('loadBoxs3913').getValue(),
			strWorkerNo:Ext.getCmp('cmbCheckWorker3913').getValue(),
			strFlag:strFlag,
			strPrinterGroupNo:Ext.getCmp('checkPlatNo3913').getValue()
		};
		Ext.Ajax.request({
			method:'POST',
			url:'odata_ArrangePackAction_tscCheckBarcode',
			params:params,
			async:false,	
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					//刷新商品数据
					if(Ext.getCmp('loadBoxs3913').getValue()!=data.obj)
					{
						Ext.getCmp('loadBoxs3913').setValue(data.obj);
						var params = {
							str : Ext.getCmp('labelNo3913').getValue()
							//str : Ext.getCmp('loadBoxs3913').getValue()
						};
						Ext.apply(Ext.getCmp('gridCusLabel3913').getStore().proxy.extraParams,params);
						Ext.getCmp('gridCusLabel3913').getStore().removeAll();
						Ext.getCmp('gridCusLabel3913').getStore().load();
					}
					
					//取该标签的扫描数量
					var param = {
							str : Ext.getCmp('loadBoxs3913').getValue()
					};
					Ext.Ajax.request({
						method:'POST',
						url:'odata_ArrangePackAction_tscBoxQty',
						params:param,
						//async:false,	
						success:function(response){
							var data = Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.getCmp('nQty3913').setText(data.obj);
							}		
						}
					});
					
					var params = {
						str : Ext.getCmp('labelNo3913').getValue()
					};
					Ext.apply(Ext.getCmp('gridSlabelD3913').getStore().proxy.extraParams,params);
					Ext.getCmp('gridSlabelD3913').getStore().removeAll();
					Ext.getCmp('gridSlabelD3913').getStore().load();
					
					var params = {
						str : Ext.getCmp('loadBoxs3913').getValue()
					};
					Ext.apply(Ext.getCmp('gridDlabelD3913').getStore().proxy.extraParams,params);
					Ext.getCmp('gridDlabelD3913').getStore().removeAll();
					Ext.getCmp('gridDlabelD3913').getStore().load();
				}else{
					var audio = document.createElement("audio");
					audio.src="system/music/a.mp3";
					audio.play();
					//Ext.example.msg('提示',data.msg);
					Ext.Msg.alert('提示',data.msg);
				}		
			
				Ext.getCmp('barcode3913').setValue('');
				Ext.getCmp('barcode3913').focus(false,10);
			}
		});
	},
	boxkeydown:function(th,e,eOpts){
	  	if (e.getKey() == e.ENTER) {
	  		if(th.id=="labelNo3913"){
	  			Ext.getCmp('barcode3913').focus();
	  		}else if(th.id=="barcode3913"){
	  			this.barcode3913Blur();
	  		}else{
				th.nextSibling().focus();
			}
        }
	},
	btnCloseBox3913Click:function(){
		if(Ext.isEmpty(Ext.getCmp('checkPlatNo3913').getValue()))
		{
			Ext.example.msg('提示',"复核台号不能为空，请扫描复核台！");
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('loadBoxs3913').getValue()))
		{
			Ext.example.msg('提示',"封箱标签不能为空！");
			return;
		}		
		var params={
			strDlabel:Ext.getCmp('loadBoxs3913').getValue(),
			strWorkerNo:Ext.getCmp('cmbCheckWorker3913').getValue(),
			strPrinterGroupNo:Ext.getCmp('checkPlatNo3913').getValue()
		};
		Ext.Ajax.request({
			method:'POST',
			url:'odata_ArrangePackAction_tscArrangeCutbox',
			params:params,
			async:false,	
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					//刷新商品数据
					Ext.getCmp('nQty3913').setText(0);
					Ext.getCmp('loadBoxs3913').setValue("");
					Ext.getCmp('gridCusLabel3913').getStore().removeAll();
					Ext.getCmp('gridCusLabel3913').getStore().load();
					
					Ext.getCmp('gridDlabelD3913').getStore().removeAll();
					Ext.getCmp('gridDlabelD3913').getStore().load();	
					
					if(Ext.getCmp('gridSlabelD3913').getStore().count()==0){
						Ext.getCmp('labelNo3913').setValue("");
						Ext.getCmp('custNo3913').setValue("");
						Ext.getCmp('labelNo3913').focus();
					}else{
						Ext.getCmp('barcode3913').focus();
					}
					Ext.example.msg('提示',data.msg);
				}else{
					var audio = document.createElement("audio");
					audio.src="system/music/a.mp3";
					audio.play();
					//Ext.example.msg('提示',data.msg);
					Ext.Msg.alert('提示',data.msg);
				}		
			}
		});
	},
	odata_ArrangePackUIBeforeclose:function(){
		if(!Ext.isEmpty(Ext.getCmp('loadBoxs3913').getValue()))
		{
			Ext.example.msg('提示','未封箱，请先封箱再退出界面！');
			return false;
		}
	},
	//重扫
	btnAnewScan3913:function(){
		if(Ext.getCmp('loadBoxs3913').getValue()==null || Ext.getCmp('loadBoxs3913').getValue()==""){
			Ext.example.msg('提示','请选择重扫的标签号！');
			return
		}
		if(Ext.getCmp('cmbCheckWorker3913').getValue()==null || Ext.getCmp('cmbCheckWorker3913').getValue()==""){
			Ext.example.msg('提示','复核人不能为空！');
			return
		}
		Ext.Msg.confirm($i18n.prompt,'确定重新扫描未封箱的数据么？',function(button,text){
			if(button=='yes'){
				Ext.Ajax.request({
					url:'odata_ArrangePackAction_tscAnewScan.action',
					params:{
						strDlabel:Ext.getCmp('loadBoxs3913').getValue(),
						strWorkerNo:Ext.getCmp('cmbCheckWorker3913').getValue()
					},
					success:function(response){
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							//刷新商品数据
							Ext.getCmp('nQty3913').setText(0);
							
							Ext.getCmp('gridDlabelD3913').getStore().removeAll();
							//Ext.getCmp('gridDlabelD3913').getStore().load();	
						   
							Ext.getCmp('barcode3913').focus();
						    Ext.example.msg('提示',data.msg);
						}else{
							//Ext.example.msg('提示',data.msg);
							Ext.Msg.alert('提示',data.msg);
						}		
					}				
				});		
			}
		});
	},
	
	//回单
	btnReceipt3913Click:function(){		
		if(Ext.getCmp('labelNo3913').getValue()==null || Ext.getCmp('labelNo3913').getValue()==""){
			Ext.example.msg('提示','标签号为空，不能回单');
			return
		}
		
		//log20151113 modi by lizhiping 不同源标签允许向同一目的标签扫描,即不用封箱也可以回单
		/*if(Ext.getCmp('loadBoxs3913').getValue()!=null && Ext.getCmp('loadBoxs3913').getValue()!=""){
			Ext.example.msg('提示','请先封箱');
			return
		}*/
		//end log20151113 modi
		
		Ext.Msg.confirm($i18n.prompt,'是否确定回单？',function(button,text){
			if(button=='yes'){
				Ext.Ajax.request({
					url:'odata_ArrangePackAction_receipt.action',
					params:{
						strSlabel:Ext.getCmp('labelNo3913').getValue()
					},
					success:function(response){
						var data = Ext.decode(response.responseText);
						if(data.isSucc){
							//刷新商品数据
							Ext.getCmp('nQty3913').setText(0);
							Ext.getCmp('loadBoxs3913').setValue("");
							Ext.getCmp('labelNo3913').setValue("");
						    Ext.getCmp('custNo3913').setValue("");
						    Ext.getCmp('labelNo3913').focus();
						    Ext.example.msg('提示',data.msg);
						}else{
							Ext.Msg.alert('提示',data.msg);
							//Ext.example.msg('提示',data.msg);
						}		
					}				
				});		
			}
		});
	}
});
/*function checkTheArticleNum(){
	var data = Ext.getCmp('gridSlabelD3913').getSelectionModel().getSelection();
	
	if(data.length==0){
		var gridcount=Ext.getCmp('gridSlabelD3913').getStore().getCount();
		if(gridcount!=0){
			for(var i=0;i<gridcount;i++){
				var dataD=Ext.getCmp('gridSlabelD3913').getStore().getAt(i);
				if(dataD.get('barcode')==Ext.getCmp('barcode3913').getValue()){
					if(dataD.get('uncheckQty')< 1){
						return 1;
					}else{
						return 5;
					}
				}	
			}
			if(i==gridcount && dataD.get('barcode')!=Ext.getCmp('barcode3913').getValue()){
				return 4;
			}
		}else{
			return 4;
		}	
	}else{
		if(data[0].get('barcode')!=Ext.getCmp('barcode3913').getValue()){
			return 2;
		}else{
			if(data[0].get('uncheckQty')< 1){
				return 3;
			}
		}
	}
	return 5;
}*/
