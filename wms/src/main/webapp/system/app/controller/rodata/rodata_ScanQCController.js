/*
 * 清场退货扫描
 * chensr
 * 7305
 */
var isCanEdit7305=false;
Ext.getCmp('menu7305').items.items[3].setDisabled(true);
Ext.getCmp('menu7305').items.items[4].setDisabled(true);
Ext.define('cms.controller.rodata.rodata_ScanQCController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.rodata.rodata_ScanQCUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({
			//刷新
			'rodata_ScanQCUI button[name=refresh]':{
				click:this.refresh
			},//扫描下架单号
			'rodata_ScanQCUI textfield[id=poNo7305]' : {
				select : this.selectionchange7305
			},//输入商品条码
			'rodata_ScanQCUI textfield[id=barcode7305]':{
				blur:this.barcode7305Blur
			},//修改扫描人
			'rodata_ScanQCUI bdef_DefWorkerCombo[id=scanNo7305]':{
				select:this.changeScanNo
			},
			'rodata_ScanQCUI field':{
				specialkey:this.boxkeydown
			},//重计数 
			'rodata_ScanQCUI [id=recede7305]':{
				click:this.recede7305Clikc
			},//重扫描
			'rodata_ScanQCUI [id=recedeAgain7305]':{
				click:this.recedeAgain7305Clikc
			}
			
		});
	},
	//初始化界面
	initializtion:function()
	{
		var packingUnit7305_3=commonGetModuleField('7305','packingUnit')[0].flag;
		var packingSpec7305_3=commonGetModuleField('7305','packingSpec')[0].flag;
		var packingUnit7305_4=commonGetModuleField('7305','packingUnit')[0].flag;
		var packingSpec7305_4=commonGetModuleField('7305','packingSpec')[0].flag;
		if(packingUnit7305_3==0){
			Ext.getCmp('packingUnit7305_3').setVisible(false);
		}
		if(packingSpec7305_3==0){
			Ext.getCmp('packingSpec7305_3').setVisible(false);
		}
		if(packingUnit7305_4==0){
			Ext.getCmp('packingUnit7305_4').setVisible(false);
		}
		if(packingSpec7305_4==0){
			Ext.getCmp('packingSpec7305_4').setVisible(false);
		}
		Ext.getCmp('scanNo7305').setValue('');
		Ext.getCmp('scanNo7305').focus(false,10);
		Ext.getCmp('sacnNum7305').setValue(1); 
	},
	
	//选择事件
	rodataScan7305Beforeselect:function(){
		var strWheresql  =  {
				strQuery:Ext.getCmp('poNo7305').getValue()
			};
			Ext.apply(Ext.getCmp('poNo7305').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('poNo7305').getStore().removeAll();
			Ext.getCmp('poNo7305').getStore().load();
	},
	
	//扫描下架单号
	selectionchange7305:function(){
		if(Ext.getCmp('scanNo7305').getValue()=='' || Ext.getCmp('scanNo7305').getValue()==null){
			Ext.example.msg($i18n.prompt,"请选择扫描人！");
			Ext.getCmp('poNo7305').setValue("");
			Ext.getCmp('scanNo7305').focus(false, 10);
			return;
		}
		
		if(Ext.isEmpty(Ext.getCmp('poNo7305').getValue()))
		{
			return;
		}
		Ext.getCmp('barcode7305').setValue('');
		Ext.getCmp('articleNo7305').setValue('');
		Ext.getCmp('articleName7305').setValue('');
		
		
		//校验下架单号
		var params = {
				strQuery : Ext.getCmp('poNo7305').getValue()
			};
		Ext.Ajax.request({
			method:'POST',
			url:'rodata_ScanQCAction_getRodata_ScanM',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
								
					var strWheresql = {
						strQuery : Ext.getCmp('poNo7305').getValue()
						};
					Ext.apply(Ext.getCmp('gridrodata_ScanTTH7305_3').getStore().proxy.extraParams,strWheresql);
					Ext.getCmp('gridrodata_ScanTTH7305_3').getStore().removeAll();
					Ext.getCmp('gridrodata_ScanTTH7305_3').getStore().load();
					
					var strWheresql = {
							strQuery : Ext.getCmp('poNo7305').getValue(),
							strWorker: Ext.getCmp('scanNo7305').getValue()
					};
					Ext.apply(Ext.getCmp('gridrodata_ScanTTH7305_4').getStore().proxy.extraParams,strWheresql);
					Ext.getCmp('gridrodata_ScanTTH7305_4').getStore().removeAll();
					Ext.getCmp('gridrodata_ScanTTH7305_4').getStore().load();
				
					
					commonMenu4Button('menu7305','undo');
					isCanEdit7305=false;
					Ext.getCmp('recedeNo7305').setValue(data.obj[0][0]);						
		    		Ext.getCmp('ownerNo7305').setValue(data.obj[0][1]);
		    		Ext.getCmp('suppelier7305').setValue(data.obj[0][2]);
					
				}else{
					var audio = document.createElement("audio");
					audio.src="system/music/a.mp3";
					audio.play();
					Ext.example.msg('提示',"该下架单不存在");
					Ext.getCmp('poNo7305').setValue("");
					Ext.getCmp('poNo7305').focus(false, 10);
				}
			}
		});				
	},
	
	barcode7305Blur:function(){
		if(Ext.getCmp('scanNo7305').getValue()=='' || Ext.getCmp('scanNo7305').getValue()==null){
			Ext.example.msg($i18n.prompt,"请选择扫描人！");
			Ext.getCmp('barcode7305').setValue('');
			return;
		}
		if(Ext.getCmp('poNo7305').getValue()=='' || Ext.getCmp('poNo7305').getValue()==null){
			Ext.example.msg($i18n.prompt,"请扫描退厂单号！");
			Ext.getCmp('barcode7305').setValue('');
			return;
		}
				
		if(Ext.getCmp('barcode7305').getValue()!='' && Ext.getCmp('barcode7305').getValue()!=null){
			var params = {
					barcode:Ext.getCmp('barcode7305').getValue(),
					poNo:Ext.getCmp('poNo7305').getValue()
			};
			Ext.Ajax.request({
				method:'POST',
				async :  false,
				url:'rodata_ScanQCAction_checkArticleNo.action',
				params:params,
				success:function(response){
					var res = Ext.decode(response.responseText);
			    	if(!res.isSucc){
			    		var audio = document.createElement("audio");
						audio.src="system/music/a.mp3";
						audio.play();
			    		Ext.example.msg($i18n.prompt,"该下架单没有该商品");
			    		Ext.getCmp('articleNo7305').setValue('');
			    		Ext.getCmp('articleName7305').setValue(''); 
			    		Ext.getCmp('barcode7305').setValue(''); 		
			    	}else{
//			    		Ext.getCmp('suppelier7305').setValue(res.obj[0][0]);
			    		Ext.getCmp('articleNo7305').setValue(res.obj[0][1]);
			    		Ext.getCmp('articleName7305').setValue(res.obj[0][2]);
//			    		Ext.getCmp('ownerNo7305').setValue(res.obj[0][3]);
//			    		Ext.getCmp('recedeNo7305').setValue(res.obj[0][4]);			 			    		
			    		Ext.getCmp('barcode7305').setValue(''); 

			    					    			
			    	/////////////////////////////////////////////////////////////////////
			    		var params = {
			    				articleNo:Ext.getCmp('articleNo7305').getValue(),
			    				recedeNo:Ext.getCmp('recedeNo7305').getValue(),
			    				ownerNo:Ext.getCmp('ownerNo7305').getValue(),
			    				sacnNum:Ext.getCmp('sacnNum7305').getValue()*res.obj[0][5],//乘以改商品的包装数
			    				strWorker:Ext.getCmp('scanNo7305').getValue()
			    		};
			    		Ext.Ajax.request({
			    			method:'POST',
			    			async :  false,
			    			url:'rodata_ScanQCAction_tscScan.action',
			    			params:params,
			    			success:function(response){
			    				var res = Ext.decode(response.responseText);
			    				if(!res.isSucc){
			    					var audio = document.createElement("audio");
									audio.src="system/music/a.mp3";
									audio.play();
			    					Ext.example.msg($i18n.prompt,res.msg);
			    				}
			    			}
			    		});				    					    		    	
			    	}
				}
			});	
					
			var strWheresql = {
					strQuery : Ext.getCmp('poNo7305').getValue()
				};
				Ext.apply(Ext.getCmp('gridrodata_ScanTTH7305_3').getStore().proxy.extraParams,strWheresql);
				Ext.getCmp('gridrodata_ScanTTH7305_3').getStore().removeAll();
				Ext.getCmp('gridrodata_ScanTTH7305_3').getStore().load();
				
				var strWheresql = {
						strQuery : Ext.getCmp('poNo7305').getValue(),
						strWorker: Ext.getCmp('scanNo7305').getValue()
				};
				Ext.apply(Ext.getCmp('gridrodata_ScanTTH7305_4').getStore().proxy.extraParams,strWheresql);
				Ext.getCmp('gridrodata_ScanTTH7305_4').getStore().removeAll();
				Ext.getCmp('gridrodata_ScanTTH7305_4').getStore().load();
			}
		
	     
	},
	boxkeydown:function(th,e,eOpts){
		if (e.getKey() == e.ENTER) {
	  		if(th.id=="poNo7305"){
	  			Ext.getCmp('barcode7305').focus();
	  		}else if(th.id=="barcode7305"){
	  			this.barcode7305Blur();
	  		}else{
				th.nextSibling().focus();
			}
        }
	},
	 //刷新
	  refresh:function(){
		Ext.getCmp('unCheckQty7305').setText('0'); 
	    Ext.getCmp('checkQty7305').setText('0');
		Ext.getCmp('poNo7305').setValue('');
		Ext.getCmp('articleNo7305').setValue('');
		Ext.getCmp('articleName7305').setValue('');
 	  	Ext.getCmp('gridrodata_ScanTTH7305_3').getStore().removeAll();
 	 	Ext.getCmp('gridrodata_ScanTTH7305_3').getStore().reload();
 		Ext.getCmp('gridrodata_ScanTTH7305_4').getStore().removeAll();
 	 	Ext.getCmp('gridrodata_ScanTTH7305_4').getStore().reload();
 	  } ,
	
	//修改扫描人
	changeScanNo:function(){
		Ext.getCmp('unCheckQty7305').setText('0'); 
	    Ext.getCmp('checkQty7305').setText('0');
		Ext.getCmp('poNo7305').setValue('');
		Ext.getCmp('articleNo7305').setValue('');
		Ext.getCmp('articleName7305').setValue('');
		Ext.getCmp('unCheckQty7305').setText(0);
		Ext.getCmp('checkQty7305').setText(0);
		Ext.getCmp('sacnNum7305').setValue(1); 
		Ext.getCmp('gridrodata_ScanTTH7305_3').getStore().removeAll();
		Ext.getCmp('gridrodata_ScanTTH7305_4').getStore().removeAll();
	},
	//换箱
	recede7305Clikc:function(){
		if(Ext.getCmp('recedeNo7305').getValue()=="" || Ext.getCmp('recedeNo7305').getValue()==null){
			Ext.example.msg($i18n.prompt,"请输入退厂单号！");
			return;
		}
		
		Ext.Msg.confirm('提示','确定换箱？',function(button,text){
			if(button=='yes'){
				
				var params={
	    				recedeNo:Ext.getCmp('recedeNo7305').getValue(),
	    				ownerNo:Ext.getCmp('ownerNo7305').getValue(),
	    				strWorker:Ext.getCmp('scanNo7305').getValue()
				};
				Ext.Ajax.request({
					method:'post',
					url:'rodata_ScanQCAction_recede',
					params:params,
					success:function(response){
						Ext.getCmp('checkQty7305').setText(0);
						var data=Ext.decode(response.responseText);
						if(data.isSucc){
							var strWheresql = {
									strQuery : Ext.getCmp('poNo7305').getValue()
								};
								Ext.apply(Ext.getCmp('gridrodata_ScanTTH7305_3').getStore().proxy.extraParams,strWheresql);
								Ext.getCmp('gridrodata_ScanTTH7305_3').getStore().removeAll();
								Ext.getCmp('gridrodata_ScanTTH7305_3').getStore().load();
								
								var strWheresql = {
										strQuery : Ext.getCmp('poNo7305').getValue(),
										strWorker: Ext.getCmp('scanNo7305').getValue()
								};
								Ext.apply(Ext.getCmp('gridrodata_ScanTTH7305_4').getStore().proxy.extraParams,strWheresql);
								Ext.getCmp('gridrodata_ScanTTH7305_4').getStore().removeAll();
								Ext.getCmp('gridrodata_ScanTTH7305_4').getStore().load();
							
						};
					}
				});
			};
		});
		
	},
	
	//重扫描
	recedeAgain7305Clikc:function(){
		if(Ext.getCmp('recedeNo7305').getValue()=="" || Ext.getCmp('recedeNo7305').getValue()==null){
			Ext.example.msg($i18n.prompt,"请输入退厂单号！");
			return;
		}
		
		Ext.Msg.confirm('提示','确定重扫描？',function(button,text){
			if(button=='yes'){
				
				var params={
	    				recedeNo:Ext.getCmp('recedeNo7305').getValue(),
	    				ownerNo:Ext.getCmp('ownerNo7305').getValue(),
	    				strWorker:Ext.getCmp('scanNo7305').getValue()
				};
				Ext.Ajax.request({
					method:'post',
					url:'rodata_ScanQCAction_recedeAgain',
					params:params,
					success:function(response){
						Ext.getCmp('checkQty7305').setText(0);
						var data=Ext.decode(response.responseText);
						if(data.isSucc){
							var strWheresql = {
									strQuery : Ext.getCmp('poNo7305').getValue()
								};
								Ext.apply(Ext.getCmp('gridrodata_ScanTTH7305_3').getStore().proxy.extraParams,strWheresql);
								Ext.getCmp('gridrodata_ScanTTH7305_3').getStore().removeAll();
								Ext.getCmp('gridrodata_ScanTTH7305_3').getStore().load();
								
								var strWheresql = {
										strQuery : Ext.getCmp('poNo7305').getValue(),
										strWorker: Ext.getCmp('scanNo7305').getValue()
								};
								Ext.apply(Ext.getCmp('gridrodata_ScanTTH7305_4').getStore().proxy.extraParams,strWheresql);
								Ext.getCmp('gridrodata_ScanTTH7305_4').getStore().removeAll();
								Ext.getCmp('gridrodata_ScanTTH7305_4').getStore().load();
							
						};
					}
				});
			};
		});
	}
});

