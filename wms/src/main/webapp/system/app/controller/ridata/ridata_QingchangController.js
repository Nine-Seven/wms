/**
 * 模块名称：返配快速扫描验收
 * 模块编码：6902
 * 创建：hekl
 */
Ext.define('cms.controller.ridata.ridata_QingchangController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.ridata.ridata_QingchangUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({
			//刷新
			'ridata_QingchangUI button[name=refresh]':{
				click:this.refresh
			},
			//原返配单号选择
			'ridata_QingchangUI ridata_UntreadNoCombo[id=cmbUntreadNo6902]':{
				beforequery:this.cmbUntreadNo6902beforequery,
				select:this.untreadNoselect
				
			},
			//目的储位选择
			'ridata_QingchangUI remoteCombo[id=cmbCellNo6902]':{
				select:this.cmbCellNo6902
				
			},
			//扫描条码选择
			'ridata_QingchangUI textfield[id=txtBarcode6902]':{
				blur:this.txtBarcode6902Blur
			},
			'ridata_QingchangUI field':{
				specialkey:this.boxkeydown
			},
			//扫描码头号
			'ridata_QingchangUI textfield[id=cmbDockNo6902]':{
				blur:this.cmbDockNo6902Blur
			},
			// 封箱
			'ridata_QingchangUI button[id=btnClosePal6902]':{
				click:this.btnClosePal6902Click
			}
		});
	},
	
	//界面初始化
	initializtion:function()
	{
		Ext.getCmp('cmbOwnerNo6902').getStore().load();
		Ext.getCmp('sacnNum6902').setValue('1');
		Ext.getCmp('cmbDockNo6902').focus(false, 10);
		
		//显示变量0为不显示，1为显示
		var packingUnit_6902=commonGetModuleField('6902','packingUnit')[0].flag;
		var packingSpec_6902=commonGetModuleField('6902','packingSpec')[0].flag;
		
		if(packingUnit_6902==0){
			Ext.getCmp('packingUnit_6902').setVisible(false);
		}
		if(packingSpec_6902==0){
			Ext.getCmp('packingSpec_6902').setVisible(false);
		}
		
	},
	
	//扫描扫描台号
	cmbDockNo6902Blur:function(){
  		if(Ext.isEmpty(Ext.getCmp('cmbDockNo6902').getValue()))
  		{
  			return;
  		}
  		//校验扫描台号
  		var params = {
  				str : Ext.getCmp('cmbDockNo6902').getValue(),
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
  					Ext.getCmp('cmbDockNo6902').setValue("");
  					Ext.getCmp('cmbDockNo6902').focus(false, 10);
  				}
  			}
  		});		
  	},
  
  	
	//扫描条码选择
	txtBarcode6902Blur:function()
	{
		
		if(Ext.isEmpty(Ext.getCmp('cmbWorkerNo6902').getValue())){
			Ext.example.msg('提示',"请先输入验收人员");
			Ext.getCmp('txtBarcode6902').setValue(null);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('cmbDockNo6902').getValue())){
			Ext.example.msg('提示',"请先输入扫描台");
			Ext.getCmp('txtBarcode6902').setValue(null);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('cmbUntreadNo6902').getValue())){
			Ext.example.msg('提示',"请输入原单号");
			Ext.getCmp('cmbUntreadNo6902').setValue(null);
			return;
		}
		if( !Ext.isEmpty(Ext.getCmp('cmbUntreadNo6902').getValue()) &&
		    !Ext.isEmpty(Ext.getCmp('txtBarcode6902').getValue()) )
		{
		
			var params={
				strBarcode:Ext.getCmp('txtBarcode6902').getValue(),
				strOwnerNo:Ext.getCmp('cmbOwnerNo6902').getValue(),
				strUntreadNo:Ext.getCmp('cmbUntreadNo6902').getValue()
			};
			//  校验条码
			Ext.Ajax.request({
				method:'POST',
				url:'ridata_QingchangAction_queryBarcode',
				params:params,
				async:false,	
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						var record  = Ext.getCmp('grid_01_6902').getStore().getAt(0);
						var strSUntreadNo = record.data.SUntreadNo;
						var master={
							ownerNo:Ext.getCmp('cmbOwnerNo6902').getValue(),
							untreadNo:Ext.getCmp('cmbUntreadNo6902').getValue(),
							SUntreadNo:strSUntreadNo,
							untreadType:record.data.untreadType,
							articleNo:data.obj[0][0],
							barcode:data.obj[0][4],
							packingQty:data.obj[0][2],
							checkQty:Ext.getCmp('sacnNum6902').getValue()*data.obj[0][2],
							printerGroupNo:'',
							dockNo:Ext.getCmp('cmbDockNo6902').getValue(),
							workerNo:Ext.getCmp('cmbWorkerNo6902').getValue(),
							checkTools:'',
							isAdd:'1',
							supplierNo:data.obj[0][1],
							qualityFlag:record.data.qualityFlag,
							deviceNo:'N',
							labelId:'N',
							classType : record.data.classType,
							rsvAttr2 : data.obj[0][3],
							cellNo : 'N'
						};
						var par={
							jsonDetail:Ext.encode(master)
						};
						Ext.Ajax.request({
							method:'POST',
							url:'ridata_QingchangAction_save',
							params:par,
							async:false,
							success:function(response){
								var data = Ext.decode(response.responseText);
								if(data.isSucc){
									//取该单的扫描数量
									var param = {
											strDockNo:Ext.getCmp('cmbDockNo6902').getValue(),
											strUntreadNo:Ext.getCmp('cmbUntreadNo6902').getValue()
									};
									Ext.Ajax.request({
										method:'POST',
										url:'ridata_QingchangAction_tscBoxQty',
										params:param,
										//async:false,	
										success:function(response){
											var data = Ext.decode(response.responseText);
											if(data.isSucc){
												Ext.getCmp('nQty6902').setText(data.obj);
												Ext.getCmp('unQty6902').setText(data.msg);
											}		
										}
									});
									
									Ext.getCmp('grid_01_6902').getStore().removeAll();
									Ext.getCmp('grid_01_6902').getStore().load();
									Ext.getCmp('cmbOwnerNo6902').getStore().load();
									
									var strWhereSql  =  {
										strDockNo : Ext.getCmp('cmbDockNo6902').getValue(),
										strSUntreadNo2:strSUntreadNo,
									};
									Ext.apply(Ext.getCmp('grid_02_6902').getStore().proxy.extraParams,strWhereSql);
									Ext.getCmp('grid_02_6902').getStore().removeAll();
									Ext.getCmp('grid_02_6902').getStore().load();
									Ext.getCmp('txtBarcode6902').setValue('');
									Ext.getCmp('txtBarcode6902').focus();
								}else{
									var audio = document.createElement("audio");
									audio.src="system/music/a.mp3";
									audio.play();
									Ext.example.msg('提示',data.msg);
									Ext.getCmp('txtBarcode6902').setValue('');
									Ext.getCmp('txtBarcode6902').focus();
								}
							}
						});
			
					}else{
						Ext.example.msg('提示',data.msg);
						var audio = document.createElement("audio");
						audio.src="system/music/a.mp3";
						audio.play();
						Ext.getCmp('txtBarcode6902').setValue('');
						Ext.getCmp('txtBarcode6902').focus();
					}			
				}
			});
			
	
		}
	},
	
	boxkeydown:function(th,e,eOpts){
			if (e.getKey() == e.ENTER) {
		  		if(th.id=="cmbDockNo6902"){
		  			Ext.getCmp('cmbWorkerNo6902').focus();
		  		}else if(th.id=="cmbWorkerNo6902"){
		  			Ext.getCmp('cmbUntreadNo6902').focus();
		  		}else if(th.id=="cmbUntreadNo6902"){
		  			Ext.getCmp('txtBarcode6902').focus();
		  		}else if (th.id=="txtBarcode6902"){
		  			this.txtBarcode6902Blur();
		  		}else{
					th.nextSibling().focus();
				}
	        }
	},
	
	//封箱按扭
	btnClosePal6902Click:function(){
		if(Ext.isEmpty(Ext.getCmp('cmbCellNo6902').getValue())){
			Ext.example.msg('提示',"请输入目的上架货位");
			return;
		}
		var record = Ext.getCmp('grid_02_6902').getStore().getAt(0);
		var d=
		{	
			enterpriseNo:Ext.get('enterpriseNo').getValue(),
			warehouseNo:Ext.get('warehouseNo').getValue(),
			ownerNo:Ext.getCmp('cmbOwnerNo6902').getValue(),
			SUntreadNo:record.data.SUntreadNo,
			SCheckNo:record.data.SCheckNo,
			cellNo:Ext.getCmp('cmbCellNo6902').getValue(),
			workerNo:Ext.getCmp('cmbWorkerNo6902').getValue(),
			labelNo:record.data.labelNo,
			dockNo:Ext.getCmp('cmbDockNo6902').getValue()
		};
		
		var strDetail = Ext.encode(d);
		var params={
			jsonDetail:strDetail
		};
		Ext.Ajax.request({
			method:'POST',
			async:false,
			url:'ridata_QingchangAction_tscClosePal',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示',data.msg);
					Ext.getCmp('grid_01_6902').getStore().removeAll();
					Ext.getCmp('grid_02_6902').getStore().removeAll();
//					Ext.getCmp('cmbUntreadNo6902').setValue('');
				}else{
					Ext.example.msg('提示',data.msg);
				}
			}
		});
	    //刷新
		var strWhereSql  =  {
				strUntreadNo:Ext.getCmp('cmbUntreadNo6902').getValue()
			};
			Ext.apply(Ext.getCmp('grid_01_6902').getStore().proxy.extraParams,strWhereSql);
			Ext.getCmp('grid_01_6902').getStore().removeAll();
			Ext.getCmp('grid_01_6902').getStore().load();
		
	
	},

	
	//刷新
	refresh:function(){
		
			Ext.getCmp('cmbOwnerNo6902').getStore().load();
			Ext.getCmp('grid_01_6902').getStore().load();
			Ext.getCmp('cmbCellNo6902').setValue('');
			Ext.getCmp('txtBarcode6902').setValue('');
	},
	
	cmbUntreadNo6902beforequery:function(){
		var strWheresql  =  {
			strOwnerNo:Ext.getCmp('cmbOwnerNo6902').getValue()
		};
		Ext.apply(Ext.getCmp('cmbUntreadNo6902').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('cmbUntreadNo6902').getStore().removeAll();
		Ext.getCmp('cmbUntreadNo6902').getStore().load();
	},
	
	//原返配单号选择
	untreadNoselect:function(combo){
		//untreadNo6902Blur();
		var strWhereSql  =  {
			strUntreadNo:combo.getValue()
		};
		Ext.apply(Ext.getCmp('grid_01_6902').getStore().proxy.extraParams,strWhereSql);
		Ext.getCmp('grid_01_6902').getStore().removeAll();
		Ext.getCmp('grid_01_6902').getStore().load();

		var strparams  =  {
				strDockNo : Ext.getCmp('cmbDockNo6902').getValue(),
				strUntreadNo:combo.getValue()
			};
		Ext.apply(Ext.getCmp('grid_02_6902').getStore().proxy.extraParams,strparams);
		Ext.getCmp('grid_02_6902').getStore().removeAll();
		Ext.getCmp('grid_02_6902').getStore().load();
		
		var strparams2  =  {
				strDockNo : Ext.getCmp('cmbDockNo6902').getValue(),
				strUntreadNo:combo.getValue()
		};
		//校验是否还有未封箱的任务，有则提示拦截
		Ext.Ajax.request({
			method:'POST',
			url:'ridata_QingchangAction_checkCloseBox',
			params:strparams2,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(!data.isSucc){
					var audio = document.createElement("audio");
					audio.src="system/music/a.mp3";
					audio.play();
					Ext.example.msg('提示',data.msg);
					Ext.getCmp('cmbUntreadNo6902').setValue("");
					Ext.getCmp('cmbUntreadNo6902').focus(false, 10);
				}else{
					Ext.getCmp('cmbQuality6902').setValue(data.obj);
					//取该单的扫描数量
					var param = {
							strDockNo:Ext.getCmp('cmbDockNo6902').getValue(),
							strUntreadNo:combo.getValue()
					};
					Ext.Ajax.request({
						method:'POST',
						url:'ridata_QingchangAction_tscBoxQty',
						params:param,
						//async:false,	
						success:function(response){
							var data = Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.getCmp('nQty6902').setText(data.obj);
								Ext.getCmp('unQty6902').setText(data.msg);
							}		
						}
					});
				}
			}
		});		
		
	},
	cmbCellNo6902:function(combo){
		var record = Ext.getCmp('grid_02_6902').getStore().getAt(0);

		var params = {
				strOwnerNo:Ext.getCmp('cmbOwnerNo6902').getValue(),
				strWhereSql:combo.getValue(),
				strSUntreadNo2:record.data.SUntreadNo,
				strSCheckNo:record.data.SCheckNo,
		};
		Ext.Ajax.request({
			method:'POST',
			url:'ridata_QingchangAction_checkCellNo',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(!data.isSucc){
					var audio = document.createElement("audio");
					audio.src="system/music/a.mp3";
					audio.play();
					Ext.example.msg('提示',data.msg);
					Ext.getCmp('cmbCellNo6902').setValue("");
					Ext.getCmp('cmbCellNo6902').focus(false, 10);
				}
			}
		});		
	}
	
});
//校验码头与单号是否一致
function untreadNo6902Blur(){
		if(Ext.isEmpty(Ext.getCmp('cmbDockNo6902').getValue()))
		{
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('cmbUntreadNo6902').getValue()))
		{
			return;
		}
		var params = {
				strUntreadNo : Ext.getCmp('cmbUntreadNo6902').getValue(),
				strDockNo : Ext.getCmp('cmbDockNo6902').getValue(),
		};
		Ext.Ajax.request({
			method:'POST',
			url:'ridata_QingchangAction_checkDockNoUNo',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(!data.isSucc){
					var audio = document.createElement("audio");
					audio.src="system/music/a.mp3";
					audio.play();
					Ext.example.msg('提示',data.msg);
					Ext.getCmp('cmbUntreadNo6902').setValue("");
					Ext.getCmp('cmbUntreadNo6902').focus(false, 10);
				}
			}
		});		
	};
	
