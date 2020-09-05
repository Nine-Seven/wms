/**
 * 模块名称：返配次品扫描验收
 * 模块编码：6901
 * 创建：hekl
 */
var g_strIsCanEdit6901=false;
Ext.define('cms.controller.ridata.ridata_CheckBadController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.ridata.ridata_CheckBadUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({
			//刷新
			'ridata_CheckBadUI button[name=refresh]':{
				click:this.refresh
			},
			//原返配单号选择
			'ridata_CheckBadUI ridata_UntreadNoCombo[id=cmbUntreadNo6901]':{
				beforequery:this.cmbUntreadNo6901beforequery,
				select:this.untreadNoselect
				
			},
			//扫描条码选择
			'ridata_CheckBadUI textfield[id=txtBarcode6901]':{
				blur:this.txtBarcode6901Blur
			},
			'ridata_CheckBadUI field':{
				specialkey:this.boxkeydown
			},
			//扫描码头号
			'ridata_CheckBadUI textfield[id=cmbDockNo6901]':{
				blur:this.cmbDockNo6901Blur
			},
			// 确认按钮
			'ridata_CheckBadUI button[id=btnClosePal6901]':{
				click:this.btnClosePal6901Click
			}
		});
	},
	
	//界面初始化
	initializtion:function()
	{
		g_strIsCanEdit6901=false;
		Ext.getCmp('cmbOwnerNo6901').getStore().load();
		Ext.getCmp('cmbCellNo6901').getStore().load();
		
		//显示变量0为不显示，1为显示
		var packingUnit_6901=commonGetModuleField('6901','packingUnit')[0].flag;
		var packingSpec_6901=commonGetModuleField('6901','packingSpec')[0].flag;
		
		if(packingUnit_6901==0){
			Ext.getCmp('packingUnit_6901').setVisible(false);
		}
		if(packingSpec_6901==0){
			Ext.getCmp('packingSpec_6901').setVisible(false);
		}
		
	},
	
	//扫描扫描台号
	cmbDockNo6901Blur:function(){
  		if(Ext.isEmpty(Ext.getCmp('cmbDockNo6901').getValue()))
  		{
  			return;
  		}
  		//校验扫描台号
  		var params = {
  				str : Ext.getCmp('cmbDockNo6901').getValue(),
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
  					Ext.getCmp('cmbDockNo6901').setValue("");
  					Ext.getCmp('cmbDockNo6901').focus(false, 10);
  				}
  			}
  		});		
  	},
  
  	
	//扫描条码选择
	txtBarcode6901Blur:function()
	{
		if(!commonCheckIsInputAll('formCondition6901')){
			Ext.getCmp('txtBarcode6901').setValue(null);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('cmbWorkerNo6901').getValue())){
			Ext.example.msg('提示',"请先输入验收人员");
			Ext.getCmp('txtBarcode6901').setValue(null);
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('cmbDockNo6901').getValue())){
			Ext.example.msg('提示',"请先输入扫描台");
			Ext.getCmp('txtBarcode6901').setValue(null);
			return;
		}
		if( !Ext.isEmpty(Ext.getCmp('cmbUntreadNo6901').getValue()) &&
		    !Ext.isEmpty(Ext.getCmp('txtBarcode6901').getValue()) )
		{
		
			var params={
				strBarcode:Ext.getCmp('txtBarcode6901').getValue(),
				strOwnerNo:Ext.getCmp('cmbOwnerNo6901').getValue(),
				strUntreadNo:Ext.getCmp('cmbUntreadNo6901').getValue()
			};
			//  校验条码
			Ext.Ajax.request({
				method:'POST',
				url:'ridata_CheckBadAction_queryBarcode',
				params:params,
				async:false,	
				success:function(response){
					var data = Ext.decode(response.responseText);
					if(data.isSucc){
						var record  = Ext.getCmp('grid_01_6901').getStore().getAt(0);
						var strSUntreadNo = record.data.SUntreadNo;
					
						var master={
							ownerNo:Ext.getCmp('cmbOwnerNo6901').getValue(),
							untreadNo:Ext.getCmp('cmbUntreadNo6901').getValue(),
							SUntreadNo:strSUntreadNo,
							untreadType:record.data.untreadType,
							articleNo:data.obj[0][0],
							barcode:data.obj[0][5],
							packingQty:data.obj[0][2],
							checkQty:1*data.obj[0][2],
							printerGroupNo:'',
							dockNo:Ext.getCmp('cmbDockNo6901').getValue(),
							workerNo:Ext.getCmp('cmbWorkerNo6901').getValue(),
							checkTools:'',
							isAdd:'1',
							labelNo:'N',
							subLabelNo:'N',
							supplierNo:data.obj[0][1],
							qualityFlag:record.data.qualityFlag,
							deviceNo:'N',
							labelId:'N',
							classType : record.data.classType,
							rsvAttr2 : data.obj[0][4],
							cellNo : Ext.getCmp('cmbCellNo6901').getValue()
						};
						var par={
							jsonDetail:Ext.encode(master)
						};
						Ext.Ajax.request({
							method:'POST',
							url:'ridata_CheckBadAction_save',
							params:par,
							async:false,
							success:function(response){
								var data = Ext.decode(response.responseText);
								if(data.isSucc){
									Ext.getCmp('grid_01_6901').getStore().removeAll();
									Ext.getCmp('grid_01_6901').getStore().load();
									Ext.getCmp('cmbOwnerNo6901').getStore().load();
									
									var strWhereSql  =  {
										strDockNo : Ext.getCmp('cmbDockNo6901').getValue(),
										strSUntreadNo2:strSUntreadNo,
									};
									Ext.apply(Ext.getCmp('grid_02_6901').getStore().proxy.extraParams,strWhereSql);
									Ext.getCmp('grid_02_6901').getStore().removeAll();
									Ext.getCmp('grid_02_6901').getStore().load();
									Ext.getCmp('txtBarcode6901').setValue('');
									Ext.getCmp('txtBarcode6901').focus();
								}else{
									var audio = document.createElement("audio");
									audio.src="system/music/a.mp3";
									audio.play();
									Ext.example.msg('提示',data.msg);
								}
							}
						});
			
					}else{
						Ext.example.msg('提示',data.msg);
						var audio = document.createElement("audio");
						audio.src="system/music/a.mp3";
						audio.play();
						Ext.getCmp('txtBarcode6901').setValue('');
						Ext.getCmp('txtBarcode6901').focus();
					}			
				}
			});
			
	
		}
	},
	
	boxkeydown:function(th,e,eOpts){
			if (e.getKey() == e.ENTER) {
		  		if(th.id=="cmbDockNo6901"){
		  			Ext.getCmp('cmbWorkerNo6901').focus();
		  		}else if(th.id=="cmbWorkerNo6901"){
		  			Ext.getCmp('cmbUntreadNo6901').focus();
		  		}else if(th.id=="cmbUntreadNo6901"){
		  			Ext.getCmp('txtBarcode6901').focus();
		  		}else if(th.id=="txtBarcode6901"){
		  			this.txtBarcode6901Blur();
		  		}else{
					th.nextSibling().focus();
				}
	        }
	},
	
	//确认按扭
	btnClosePal6901Click:function(){
		var detail1 = [];
		var record = Ext.getCmp('grid_02_6901').getStore().getAt(0);
		var d=
		{	
			enterpriseNo:Ext.get('enterpriseNo').getValue(),
			warehouseNo:Ext.get('warehouseNo').getValue(),
			ownerNo:Ext.getCmp('cmbOwnerNo6901').getValue(),
			SUntreadNo:record.data.SUntreadNo,
			SCheckNo:record.data.SCheckNo,
			cellNo:Ext.getCmp('cmbCellNo6901').getValue(),
			workerNo:Ext.getCmp('cmbWorkerNo6901').getValue(),
			dockNo:Ext.getCmp('cmbDockNo6901').getValue()
		};
		detail1.push(d);
		
		var strDetail = Ext.encode(detail1);
		var params={
			jsonDetail:strDetail
		};
		Ext.Ajax.request({
			method:'POST',
			async:false,
			url:'ridata_CheckBadAction_tscClosePal',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg('提示',data.msg);
					Ext.getCmp('grid_01_6901').getStore().removeAll();
					Ext.getCmp('grid_02_6901').getStore().removeAll();
					Ext.getCmp('cmbUntreadNo6901').setValue('');
				}else{
					Ext.example.msg('提示',data.msg);
				}
			}
		});
	    //刷新
		var strWhereSql  =  {
				strUntreadNo:Ext.getCmp('cmbUntreadNo6901').getValue()
			};
			Ext.apply(Ext.getCmp('grid_01_6901').getStore().proxy.extraParams,strWhereSql);
			Ext.getCmp('grid_01_6901').getStore().removeAll();
			Ext.getCmp('grid_01_6901').getStore().load();
		
	
	},

	
	//刷新
	refresh:function(){
		
			Ext.getCmp('cmbOwnerNo6901').getStore().load();
			Ext.getCmp('grid_01_6901').getStore().load();
		
	},
	
	cmbUntreadNo6901beforequery:function(){
		var strWheresql  =  {
			strOwnerNo:Ext.getCmp('cmbOwnerNo6901').getValue(),
			poType:'B'
		};
		Ext.apply(Ext.getCmp('cmbUntreadNo6901').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('cmbUntreadNo6901').getStore().removeAll();
		Ext.getCmp('cmbUntreadNo6901').getStore().load();
	},
	
	//原返配单号选择
	untreadNoselect:function(combo){
		untreadNo6901Blur();
		
		var strWhereSql  =  {
			strUntreadNo:combo.getValue()
		};
		Ext.apply(Ext.getCmp('grid_01_6901').getStore().proxy.extraParams,strWhereSql);
		Ext.getCmp('grid_01_6901').getStore().removeAll();
		Ext.getCmp('grid_01_6901').getStore().load();

		var strparams  =  {
				strDockNo : Ext.getCmp('cmbDockNo6901').getValue(),
				strUntreadNo:combo.getValue()
			};
		Ext.apply(Ext.getCmp('grid_02_6901').getStore().proxy.extraParams,strparams);
		Ext.getCmp('grid_02_6901').getStore().removeAll();
		Ext.getCmp('grid_02_6901').getStore().load();
		
		
	}
});
//校验码头与单号是否一致
function untreadNo6901Blur(){
		if(Ext.isEmpty(Ext.getCmp('cmbDockNo6901').getValue()))
		{
			return;
		}
		if(Ext.isEmpty(Ext.getCmp('cmbUntreadNo6901').getValue()))
		{
			return;
		}
		var params = {
				strUntreadNo : Ext.getCmp('cmbUntreadNo6901').getValue(),
				strDockNo : Ext.getCmp('cmbDockNo6901').getValue(),
		};
		Ext.Ajax.request({
			method:'POST',
			url:'ridata_CheckBadAction_checkDockNoUNo',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(!data.isSucc){
					var audio = document.createElement("audio");
					audio.src="system/music/a.mp3";
					audio.play();
					Ext.example.msg('提示',data.msg);
					Ext.getCmp('cmbUntreadNo6901').setValue("");
					Ext.getCmp('cmbUntreadNo6901').focus(false, 10);
				}
			}
		});		
	};
	
