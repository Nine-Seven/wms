/*
 * 退厂扫描（天天惠）
 * chensr
 * 7304
 */
var rowindex7302=0;
var isCanEdit7304=false;
Ext.getCmp('menu7304').items.items[3].setDisabled(true);
Ext.getCmp('menu7304').items.items[4].setDisabled(true);
Ext.define('cms.controller.rodata.rodata_ScanTTHController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.rodata.rodata_ScanTTHUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({
			
			//确认保存
			'rodata_ScanTTHUI button[id=btnSaveConfirm7304]':{
				click:this.btnSaveConfirm7304
			},
			//刷新
			'rodata_ScanTTHUI button[name=refresh]':{
				click:this.refresh
			},
			//输入商品条码
			'rodata_ScanTTHUI textfield[id=barcode7304]':{
				blur:this.barcode7304Blur
			},
			'rodata_ScanTTHUI field':{
				specialkey:this.boxkeydown
			},
			//扫描下架单号
			'rodata_ScanTTHUI textfield[id=OutstockNo7304]' : {
				blur : this.selectionchange7304
			},
			//封箱,打印标签
			'rodata_ScanTTHUI button[id=btnCloseBox7304]':{
				click:this.closeBox
			},//选择箱号
			'rodata_ScanTTHUI grid[id=gridPackLabel7304]':
			{
				selectionchange:this.gridPackLabel7304Selectionchange
			},
		});
	},
	//初始化界面
	initializtion:function()
	{
		var packingUnit7304_3=commonGetModuleField('7304','packingUnit')[0].flag;
		var packingSpec7304_3=commonGetModuleField('7304','packingSpec')[0].flag;
		var packingUnit7304_4=commonGetModuleField('7304','packingUnit')[0].flag;
		var packingSpec7304_4=commonGetModuleField('7304','packingSpec')[0].flag;
		if(packingUnit7304_3==0){
			Ext.getCmp('packingUnit7304_3').setVisible(false);
		}
		if(packingSpec7304_3==0){
			Ext.getCmp('packingSpec7304_3').setVisible(false);
		}
		if(packingUnit7304_4==0){
			Ext.getCmp('packingUnit7304_4').setVisible(false);
		}
		if(packingSpec7304_4==0){
			Ext.getCmp('packingSpec7304_4').setVisible(false);
		}
		Ext.getCmp('scanNo7304').setValue('');
		Ext.getCmp('scanNo7304').focus(false,10);
	},
	//保存确认
	btnSaveConfirm7304:function(){
		if(Ext.getCmp('scanNo7304').getValue()=='' || Ext.getCmp('scanNo7304').getValue()==null){
			Ext.example.msg($i18n.prompt,"请选择扫描人！");
			return;
		}
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
			return;
		}
		//保存确认
		saveConfirm();
		
	},
	
	//选择事件
	rodataScan7304Beforeselect:function(){
		var strWheresql  =  {
				strQuery:Ext.getCmp('OutstockNo7304').getValue()
			};
			Ext.apply(Ext.getCmp('OutstockNo7304').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('OutstockNo7304').getStore().removeAll();
			Ext.getCmp('OutstockNo7304').getStore().load();
	},
	
	//扫描下架单号
	selectionchange7304:function(){
		Ext.getCmp('barcode7304').setValue('');
		Ext.getCmp('loadBoxs7304').setValue('');
		Ext.getCmp('articleNo7304').setValue('');
		Ext.getCmp('articleName7304').setValue('');
		
		if(Ext.isEmpty(Ext.getCmp('OutstockNo7304').getValue()))
		{
			return;
		}
		//校验下架单号
		var params = {
				strQuery : Ext.getCmp('OutstockNo7304').getValue(),
			};
		Ext.Ajax.request({
			method:'POST',
			url:'rodata_ScanTTHAction_getRodata_ScanM',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.length>0){
					var listDetail1 = [];
					var strDtl={
							columnId:'a.enterprise_no',
							value:Ext.get('enterpriseNo').getValue()
					};
					listDetail1.push(strDtl);
					var strDtl={
						columnId:'a.warehouse_no',
						value:Ext.get('warehouseNo').getValue()
					};
					listDetail1.push(strDtl);
					var strDtl={
						columnId:'a.outstock_no',
						value:Ext.getCmp('OutstockNo7304').getValue() 
					};
					listDetail1.push(strDtl);
					var jsonlistDetail1 = Ext.encode(listDetail1);
					
					var str = {
						strQuery: Ext.getCmp('OutstockNo7304').getValue() 
					};
					Ext.apply(Ext.getCmp('gridPackLabel7304').getStore().proxy.extraParams,str);
					Ext.getCmp('gridPackLabel7304').getStore().removeAll();
					Ext.getCmp('gridPackLabel7304').getStore().load();
				
					var strWheresql = {
							strQuery: jsonlistDetail1,
						};
					Ext.apply(Ext.getCmp('gridrodata_ScanTTH7304_3').getStore().proxy.extraParams,strWheresql);
					Ext.getCmp('gridrodata_ScanTTH7304_3').getStore().removeAll();
					Ext.getCmp('gridrodata_ScanTTH7304_3').getStore().load();
					
					Ext.apply(Ext.getCmp('gridrodata_ScanTTH7304_4').getStore().proxy.extraParams,strWheresql);
					Ext.getCmp('gridrodata_ScanTTH7304_4').getStore().removeAll();
					Ext.getCmp('gridrodata_ScanTTH7304_4').getStore().load();
				
					
					commonMenu4Button('menu7304','undo');
					isCanEdit7304=false;
					
				}else{
					var audio = document.createElement("audio");
					audio.src="system/music/a.mp3";
					audio.play();
					Ext.example.msg('提示',"该下架单不存在");
					Ext.getCmp('OutstockNo7304').setValue("");
					Ext.getCmp('OutstockNo7304').focus(false, 10);
				}
			}
		});		

		
		
		
			
	},
	
	barcode7304Blur:function(){
		if(Ext.getCmp('scanNo7304').getValue()=='' || Ext.getCmp('scanNo7304').getValue()==null){
			Ext.example.msg($i18n.prompt,"请选择扫描人！");
			Ext.getCmp('barcode7304').setValue('');
			return;
		}
		if(Ext.getCmp('OutstockNo7304').getValue()=='' || Ext.getCmp('OutstockNo7304').getValue()==null){
			Ext.example.msg($i18n.prompt,"请扫描下架单！");
			Ext.getCmp('barcode7304').setValue('');
			return;
		}
		
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
			Ext.getCmp('barcode7304').setValue('');
			return;
		}
		
		if(Ext.getCmp('barcode7304').getValue()!='' && Ext.getCmp('barcode7304').getValue()!=null){
			var params = {
					barcode:Ext.getCmp('barcode7304').getValue(),
					outstockNo:Ext.getCmp('OutstockNo7304').getValue()
			};
			Ext.Ajax.request({
				method:'POST',
				async :  false,
				url:'rodata_ScanTTHAction_checkArticleNo.action',
				params:params,
				success:function(response){
					var res = Ext.decode(response.responseText);
			    	if(!res.isSucc){
			    		var audio = document.createElement("audio");
						audio.src="system/music/a.mp3";
						audio.play();
			    		Ext.example.msg($i18n.prompt,"该下架单没有该商品");
			    		Ext.getCmp('articleNo7304').setValue('');
			    		Ext.getCmp('articleName7304').setValue(''); 
			    		Ext.getCmp('barcode7304').setValue(''); 		
			    	}else{
			    		Ext.getCmp('ownerNo7304').setValue(res.obj[0][0]);
			    		Ext.getCmp('articleNo7304').setValue(res.obj[0][1]);
			    		Ext.getCmp('articleName7304').setValue(res.obj[0][2]);
			    		Ext.getCmp('barcode7304').setValue(''); 

			    		if(Ext.getCmp('loadBoxs7304').getValue()=='' ||Ext.getCmp('loadBoxs7304').getValue()==null){
			    			var params1 = {
			    				scanNo:Ext.getCmp('scanNo7304').getValue()	
				    		};
			    			
			    			Ext.Ajax.request({
				    			method:'POST',
				    			async :  false,
				    			url:'rodata_ScanTTHAction_getLoadBox.action',
				    			params:params1,
				    			success:function(response){
				    				var res = Ext.decode(response.responseText);
				    				if(res.isSucc){
				    					Ext.getCmp('loadBoxs7304').setValue(res.msg);
				    				}else{
				    					var audio = document.createElement("audio");
										audio.src="system/music/a.mp3";
										audio.play();
				    					Ext.example.msg($i18n.prompt,res.msg);
				    				}
				    			}
				    		});	
			    			
			    		}
			    			
			    	/////////////////////////////////////////////////////////////////////
			    		var params = {
			    				articleNo:Ext.getCmp('articleNo7304').getValue(),
			    				outstockNo:Ext.getCmp('OutstockNo7304').getValue(),
			    				labelNo:Ext.getCmp('loadBoxs7304').getValue()
			    		};
			    		if(Ext.getCmp('loadBoxs7304').getValue()!='' ||Ext.getCmp('loadBoxs7304').getValue()!=null){
			    			
			    			Ext.Ajax.request({
				    			method:'POST',
				    			async :  false,
				    			url:'rodata_ScanTTHAction_saveOutstock.action',
				    			params:params,
				    			success:function(response){
				    				var res = Ext.decode(response.responseText);
				    				if(!res.isSucc){
				    					var audio = document.createElement("audio");
										audio.src="system/music/a.mp3";
										audio.play();
				    					Ext.example.msg($i18n.prompt,"该上架单的该商品已扫描完成，请重新选择！");
				    				}else{
				    					//取该标签的扫描数量
										Ext.Ajax.request({
											method:'POST',
											url:'rodata_ScanTTHAction_tscRodataBoxQty',
											params:params,
											//async:false,	
											success:function(response){
												var data = Ext.decode(response.responseText);
												if(data.isSucc){
													Ext.getCmp('nQty7304').setText(data.obj);
												}else{
													var audio = document.createElement("audio");
													audio.src="system/music/a.mp3";
													audio.play();
							    					Ext.example.msg($i18n.prompt,"获取扫描数量失败！");
												}		
											}
										});
										var audio = document.createElement("audio");
										audio.src="system/music/123.mp3";
										audio.play();
				    					
				    					var str = {
				    							strQuery: Ext.getCmp('OutstockNo7304').getValue() 
				    						};
			    						Ext.apply(Ext.getCmp('gridPackLabel7304').getStore().proxy.extraParams,str);
			    						Ext.getCmp('gridPackLabel7304').getStore().removeAll();
			    						Ext.getCmp('gridPackLabel7304').getStore().load();
			    						
				    					//如果已经扫描完成自动确认
				    					var param = {
							    				outstockNo:Ext.getCmp('OutstockNo7304').getValue(),
							    		};
				    					Ext.Ajax.request({
							    			method:'POST',
							    			async :  true,
							    			url:'rodata_ScanTTHAction_checkIsFinish.action',
							    			params:param,
							    			success:function(response){
							    				var res = Ext.decode(response.responseText);
							    				if(res.isSucc){
							    					//自动确认
								    				saveConfirm();
							    				}else{
							    					return;
							    				}
							    				
							    			}
				    					});
				    				}
				    			}
				    		});	
			    		}	
			    ////////////////////////////////////////////////////////////////////
			    	}
				}
			});	
			
			///////////////////////////////////////////////////////////////
		
				
				var listDetail1 = [];
				var strDtl={
						columnId:'a.enterprise_no',
						value:Ext.get('enterpriseNo').getValue()
				};
				listDetail1.push(strDtl);
				var strDtl={
					columnId:'a.warehouse_no',
					value:Ext.get('warehouseNo').getValue()
				};
				listDetail1.push(strDtl);
				var strDtl={
					columnId:'a.outstock_no',
					value:Ext.getCmp('OutstockNo7304').getValue() 
				};
				listDetail1.push(strDtl);
				var jsonlistDetail1 = Ext.encode(listDetail1);
				
				
				listDetail1.push(strDtl);
				var jsonlistDetail1 = Ext.encode(listDetail1);
				var strWheresql = {
					strQuery: jsonlistDetail1,
				};
		
				Ext.apply(Ext.getCmp('gridrodata_ScanTTH7304_3').getStore().proxy.extraParams,strWheresql);
				Ext.getCmp('gridrodata_ScanTTH7304_3').getStore().removeAll();
				Ext.getCmp('gridrodata_ScanTTH7304_3').getStore().load();
				
				Ext.apply(Ext.getCmp('gridrodata_ScanTTH7304_4').getStore().proxy.extraParams,strWheresql);
				Ext.getCmp('gridrodata_ScanTTH7304_4').getStore().removeAll();
				Ext.getCmp('gridrodata_ScanTTH7304_4').getStore().load();
			}
		
	     
	},
	boxkeydown:function(th,e,eOpts){
		if (e.getKey() == e.ENTER) {
	  		if(th.id=="OutstockNo7304"){
	  			Ext.getCmp('barcode7304').focus();
	  		}else if(th.id=="barcode7304"){
	  			this.barcode7304Blur();
	  		}else{
				th.nextSibling().focus();
			}
        }
	},
	 //刷新
	  refresh:function(){
	    Ext.getCmp('nQty7304').setText(0);
		Ext.getCmp('OutstockNo7304').setValue('');
		Ext.getCmp('articleNo7304').setValue('');
		Ext.getCmp('articleName7304').setValue('');
		Ext.getCmp('loadBoxs7304').setValue('');
		Ext.getCmp('gridPackLabel7304').getStore().removeAll();
 	 	Ext.getCmp('gridPackLabel7304').getStore().reload();
 	  	Ext.getCmp('gridrodata_ScanTTH7304_3').getStore().removeAll();
 	 	Ext.getCmp('gridrodata_ScanTTH7304_3').getStore().reload();
 		Ext.getCmp('gridrodata_ScanTTH7304_4').getStore().removeAll();
 	 	Ext.getCmp('gridrodata_ScanTTH7304_4').getStore().reload();
 	  },
 	  
 	//选取箱号
   	gridPackLabel7304Selectionchange:function(){
		var flag=0;
		var data = Ext.getCmp('gridPackLabel7304').getSelectionModel().getSelection();
		if(data.length!=0){
			if(data[0].get('status')=="12"){
				Ext.example.msg($i18n.prompt,'该箱号已封箱，不能继续扫描该箱！');
				return;
			}else{
				var count=Ext.getCmp('gridrodata_ScanTTH7304_3').getStore().getCount();
				for(var i=0;i<count;i++){
					var r=Ext.getCmp('gridrodata_ScanTTH7304_3').getStore().getAt(i);
					if(data[0].get('labelNo')==r.get('lableNo')){	
						flag=1;
					}		
				}
				if(flag==1){
					Ext.example.msg($i18n.prompt,'该箱号为来源箱号');
					return;
				}
			}			
			Ext.getCmp('loadBoxs7304').setValue(data[0].get('labelNo'));
			//取该标签的扫描数量
			Ext.Ajax.request({
				method:'POST',
				url:'rodata_ScanTTHAction_tscRodataBoxQty',
				params:{
					outstockNo:Ext.getCmp('OutstockNo7304').getValue(),
					labelNo:Ext.getCmp('loadBoxs7304').getValue()
				},
				//async:false,	
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						Ext.getCmp('nQty7304').setText(data.obj);
					}else{
						var audio = document.createElement("audio");
						audio.src="system/music/a.mp3";
						audio.play();
    					Ext.example.msg($i18n.prompt,"获取扫描数量失败！");
					}		
				}
			});
			
			var listDetail1 = [];
			var strDtl={
					columnId:'a.enterprise_no',
					value:Ext.get('enterpriseNo').getValue()
			};
			listDetail1.push(strDtl);
			var strDtl={
				columnId:'a.warehouse_no',
				value:Ext.get('warehouseNo').getValue()
			};
			listDetail1.push(strDtl);
			var strDtl={
				columnId:'a.outstock_no',
				value:Ext.getCmp('OutstockNo7304').getValue() 
			};
			listDetail1.push(strDtl);
			var strDtl={
					columnId:'a.label_no',
					value:Ext.getCmp('loadBoxs7304').getValue()
			};
			listDetail1.push(strDtl);
			var jsonlistDetail1 = Ext.encode(listDetail1);
			
			var strWheresql = {
					strQuery: jsonlistDetail1,
				};
			Ext.apply(Ext.getCmp('gridrodata_ScanTTH7304_4').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('gridrodata_ScanTTH7304_4').getStore().removeAll();
			Ext.getCmp('gridrodata_ScanTTH7304_4').getStore().load();
		
			
			
		}	
	},
 		
 	  //封箱
 	 closeBox:function(){
 		if(Ext.getCmp('loadBoxs7304').getValue()=='' ||Ext.getCmp('loadBoxs7304').getValue()==null){
 			Ext.example.msg($i18n.prompt,"没有标签号！");
 			return;
 		}
		Ext.Msg.confirm($i18n.prompt, "是否打印？",
			function(button, text) {
				if (button == 'yes') {			
					var params = {
						ownerNo:Ext.getCmp('ownerNo7304').getValue(),
						scanNo:Ext.getCmp('scanNo7304').getValue(),
						labelNo:Ext.getCmp('loadBoxs7304').getValue()
		    		};	
					Ext.Ajax.request({
			    		method:'POST',
			    		async :  false,
			    		url:'rodata_ScanTTHAction_closeBox.action',
			    		params:params,
			    		success:function(response){
			    			var res = Ext.decode(response.responseText);
			    			if(res.isSucc){
			    				Ext.getCmp('barcode7304').focus();
			    				Ext.getCmp('loadBoxs7304').setValue('');
			    				Ext.getCmp('nQty7304').setText(0);
			    				Ext.example.msg($i18n.prompt,"打印成功！");
			    			}else{
			    				Ext.example.msg($i18n.prompt,res.msg);
			    			}
			    		}
			    	});		
				}
			});
 	 }
});

//确认保存
function saveConfirm(){
	var gridcount=Ext.getCmp('gridrodata_ScanTTH7304_3').getStore().getCount();
	var ownerNo = Ext.getCmp('ownerNo7304').getValue();
	var flag=0;
		if(gridcount!=0){
			flag=1;
		}
	
	if(flag==1){
		Ext.Msg.confirm($i18n.prompt, "扫描数量与拣货数量存在差异，是否保存？",
			function(button, text) {
				if (button == 'yes') {
					var params = {
							outstockNo:Ext.getCmp('OutstockNo7304').getValue(),
							ownerNo:ownerNo,
							scanNo:Ext.getCmp('scanNo7304').getValue()
					};
					
					Ext.Ajax.request({
		    			method:'POST',
		    			async :  false,
		    			url:'rodata_ScanTTHAction_save.action',
		    			params:params,
		    			success:function(response){
		    				var res = Ext.decode(response.responseText);
		    				if(res.isSucc){
		    					Ext.example.msg($i18n.prompt,$i18n.savesuccess);
		    			 	 	Ext.getCmp('OutstockNo7304').setValue('');	
		    			 	 	Ext.getCmp('OutstockNo7304').focus();
		    			 	 	Ext.getCmp('nQty7304').setText(0);	
		    			 	 	
		    			 	 	Ext.getCmp('gridrodata_ScanTTH7304_3').getStore().removeAll();
		    				}else{
		    					var audio = document.createElement("audio");
								audio.src="system/music/a.mp3";
								audio.play();
		    					Ext.example.msg($i18n.prompt,res.msg);
		    				}
		    			}
		    		});	
					
					
				}
		});
	}else{
		var params = {
				outstockNo:Ext.getCmp('OutstockNo7304').getValue(),
				ownerNo:ownerNo,
				scanNo:Ext.getCmp('scanNo7304').getValue()
		};
		
		Ext.Ajax.request({
			method:'POST',
			async :  false,
			url:'rodata_ScanTTHAction_save.action',
			params:params,
			success:function(response){
				var res = Ext.decode(response.responseText);
				if(res.isSucc){
					Ext.example.msg($i18n.prompt,$i18n.savesuccess);
			 	 	Ext.getCmp('OutstockNo7304').setValue('');	
			 	 	Ext.getCmp('gridrodata_ScanTTH7304_3').getStore().removeAll();
				}else{
					var audio = document.createElement("audio");
					audio.src="system/music/a.mp3";
					audio.play();
					Ext.example.msg($i18n.prompt,res.msg);
				}
			}
		});		

	  }
}
