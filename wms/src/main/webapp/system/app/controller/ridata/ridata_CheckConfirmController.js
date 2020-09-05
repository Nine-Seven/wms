/**
 * 模块名称：返配验收确认
 * 模块编码：6601
 * 创建：hkl
 */
Ext.define('cms.controller.ridata.ridata_CheckConfirmController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.ridata.ridata_CheckConfirmUI'
	         ],
	model:'',
	store:'',
	init:function()
	{
		this.control({
			//货主筛选
			'ridata_CheckConfirmUI bdef_DefOwnerCombo[id=cmbOwnerNo6601_d1]':
			{
				change:this.cmbOwnerNo6601_d1Change
			},//进货单别筛选
			'ridata_CheckConfirmUI wms_DefFieldValCombo[id=cmbUntreadType6601_d1]':
			{
				change:this.cmbUntreadType6601_d1Change
			},//单头选择事件
			'ridata_CheckConfirmUI grid[id=grid_01_6601]':
			{
				//beforeselect:this.grid_01_8301Beforeselect,
				selectionchange:this.grid_01_6601Selectionchange
			},
			'ridata_CheckConfirmUI button[name=refresh]':
			{
				click:this.refreshClick
			},//单品取消
			'ridata_CheckConfirmUI button[id=btnItemCancel6601]':
			{
				click:this.btnItemCancel6601Click
			},//整单确认
			'ridata_CheckConfirmUI button[id=btnConfirm6601]':
			{
				click:this.btnConfirm6601Click
			},
			//查询
			'ridata_CheckConfirmUI commMenuWidget4[id=menu6601] button[name=query]':{
				click:this.query
			}
		});
	},
	
	initializtion:function()
	{
		//显示变量0为不显示，1为显示
		var planBox_6601=commonGetModuleField('6601','planBox')[0].flag;	//计划
		var planQmin_6601=commonGetModuleField('6601','planQmin')[0].flag;
		var planDis_6601=commonGetModuleField('6601','planDis')[0].flag;
		var packingUnit_6601=commonGetModuleField('6601','packingUnit')[0].flag;
		var packingSpec_6601=commonGetModuleField('6601','packingSpec')[0].flag;
		
		var realBox_6601=commonGetModuleField('6601','realBox')[0].flag;	//验收
		var realQmin_6601=commonGetModuleField('6601','realQmin')[0].flag;
		var realDis_6601=commonGetModuleField('6601','realDis')[0].flag;
		
		if(planBox_6601==0){
			Ext.getCmp('planBox_6601').setVisible(false);
		}
		if(planQmin_6601==0){
			Ext.getCmp('planQmin_6601').setVisible(false);
		}
		if(planDis_6601==0){
			Ext.getCmp('planDis_6601').setVisible(false);
		}
		if(packingUnit_6601==0){
			Ext.getCmp('packingUnit_6601').setVisible(false);
			Ext.getCmp('packingUnit_6601_1').setVisible(false);
			Ext.getCmp('packingUnit_6601_2').setVisible(false);
		}
		if(packingSpec_6601==0){
			Ext.getCmp('packingSpec_6601').setVisible(false);
			Ext.getCmp('packingSpec_6601_1').setVisible(false);
			Ext.getCmp('packingSpec_6601_2').setVisible(false);
		}
		
		if(realBox_6601==0){
			Ext.getCmp('realBox_6601').setVisible(false);
			Ext.getCmp('realBox_6601_1').setVisible(false);
			Ext.getCmp('realBox_6601_2').setVisible(false);
		}
		if(realQmin_6601==0){
			Ext.getCmp('realQmin_6601').setVisible(false);
			Ext.getCmp('realQmin_6601_1').setVisible(false);
			Ext.getCmp('rrealQmin_6601_2').setVisible(false);
		}
		if(realDis_6601==0){
			Ext.getCmp('realDis_6601').setVisible(false);
			Ext.getCmp('realDis_6601_1').setVisible(false);
			Ext.getCmp('realDis_6601_2').setVisible(false);
		}
		
	},
	
	//查询
	query:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=6601;
		queryGrid='grid_01_6601';	
	},
	
	cmbOwnerNo6601_d1Change:function(combo)
	{
		/*var strDetail1 = [];
		var d={
			columnId:'a.owner_no',
			value:combo.getValue()
		};
		strDetail1.push(d);
		var jsonDetail = Ext.encode(strDetail1);
		var strWheresql = {
			strWheresql : jsonDetail
		};
		Ext.apply(Ext.getCmp('grid_01_6601').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_6601').getStore().removeAll();
		Ext.getCmp('grid_01_6601').getStore().load();*/
		screenMaster6601();
	},
	
	cmbUntreadType6601_d1Change:function(combo)
	{
		/*var strDetail1 = [];
		var d={
			columnId:'a.import_type',
			value:combo.getValue()
		};
		strDetail1.push(d);
		var jsonDetail = Ext.encode(strDetail1);
		var strWheresql = {
			strWheresql : jsonDetail
		};
		Ext.apply(Ext.getCmp('grid_01_6601').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_6601').getStore().removeAll();
		Ext.getCmp('grid_01_6601').getStore().load();*/
		screenMaster6601();
	},
	
	grid_01_6601Selectionchange:function()
	{
		var data = Ext.getCmp('grid_01_6601').getSelectionModel().getSelection();
		if(data.length!=0)
		{
			Ext.getCmp('cmbOwnerNo6601_d2').getStore().add({
				value:String(data[0].get('ownerNo')),
				dropValue:'['+data[0].get('ownerNo')+']'+data[0].get('ownerName'),
				text:String(data[0].get('ownerName'))
		    });
			Ext.getCmp('cmbOwnerNo6601_d2').setValue(data[0].get('ownerNo'));
			
			Ext.getCmp('cmbUntreadType6601_d2').setValue(data[0].get('untreadType'));
			Ext.getCmp('txtUntreadNo6601').setValue(data[0].get('untreadNo'));

			Ext.getCmp('cmbCheckWorker6601').getStore().add({
				value:String(data[0].get('checkWorker')),
				dropValue:'['+data[0].get('checkWorker')+']'+data[0].get('workerName'),
				text:String(data[0].get('workerName'))
		    });
			Ext.getCmp('cmbCheckWorker6601').setValue(data[0].get('checkWorker'));
			Ext.getCmp('txtSUntreadNo6601').setValue(data[0].get('SUntreadNo'));

			
			var strWheresql={
				strWheresql:data[0].get('checkNo')
			};
			Ext.apply(Ext.getCmp('grid_02_6601').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('grid_02_6601').getStore().removeAll();
			Ext.getCmp('grid_02_6601').getStore().load();
			
			var strWheresql={
				strWheresql:data[0].get('checkNo')
			};
			Ext.apply(Ext.getCmp('grid_03_6601').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('grid_03_6601').getStore().removeAll();
			Ext.getCmp('grid_03_6601').getStore().load();
			var strWheresql={
					strWheresql:data[0].get('SUntreadNo')
				};
				Ext.apply(Ext.getCmp('grid_04_6601').getStore().proxy.extraParams,strWheresql);
				Ext.getCmp('grid_04_6601').getStore().removeAll();
				Ext.getCmp('grid_04_6601').getStore().load();
			
		}
	},
	
	refreshClick:function()
	{
		Ext.getCmp('grid_01_6601').getStore().reload();
	},
	
	btnItemCancel6601Click:function()
	{
		var data = Ext.getCmp('grid_03_6601').getSelectionModel().getSelection();
		if(data.length!='0'){
			Ext.Msg.confirm($i18n_prompt.prompt,$i18n_prompt.deleteOrNot,function(button,text)
			{
				if(button=='yes')
				{
					Ext.getCmp('grid_03_6601').getStore().remove(data);					
				}
			});
		}else{
			Ext.example.msg($i18n_prompt.prompt, $i18n_prompt.choseDeleteRecord);
			return;
		}
	},
	
	btnConfirm6601Click:function()
	{
		if(!commonCheckIsInputAll('form_01_6601'))
		{
			return;
		}
		var gridcount=Ext.getCmp("grid_02_6601").getStore().getCount();
		if(gridcount>0)
		{
			if(!commonCheckdetailgrid('grid_02_6601',0,gridcount-1))
			{
				return;
			}	
		}else{			
			Ext.example.msg('提示',"表体不允许为空，请输入表体！");
			return;
		}
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
			return;
		}
	/*	
		var params1={
				strWheresql:Ext.getCmp('txtUntreadNo6601').getValue()
		};
		Ext.Ajax.request({
			method:'post',
			url:'ridata_CheckConfirmAction_checkQty',
			params:params1,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.Msg.confirm("提示", "计划量与验收数量不相等，确认确定？",function(button, text) 
					{
						if (button == 'yes') 
						{
							confirm6601();
						}
					});
				}else{
					confirm6601();
				}
			}
		});*/
		
		//判断临时表中是否还有数据，并且提示计划量与验收量不相等是否要做确认（因为天天惠判断临时表中是否还有数据不合理，所以暂时屏蔽掉）
		var params={
			strSUntreadNo:Ext.getCmp('txtSUntreadNo6601').getValue()
		};
	
		Ext.Ajax.request({
			method:'post',
			url:'ridata_CheckConfirmAction_checkPalTmp',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					var params1={
						strWheresql:Ext.getCmp('txtUntreadNo6601').getValue()
					};
					Ext.Ajax.request({
						method:'post',
						url:'ridata_CheckConfirmAction_checkQty',
						params:params1,
						success:function(response){
							var data = Ext.decode(response.responseText);
							if(data.isSucc){
								Ext.Msg.confirm("提示", "计划量与验收数量不相等，确认确定？",function(button, text) 
								{
									if (button == 'yes') 
									{
										confirm6601();
									}
								});
							}else{
								confirm6601();
							}
						}
					});
				}else{
					Ext.example.msg($i18n.prompt,$i18n.withoutAcceptanceOfGoods);
					return;
				}
			}
		});
	}
});

/**
 * 筛选单头
 */
function screenMaster6601()
{
	var strDetail1 = [];
	if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNo6601_d1').getValue()))
	{
		var d={
			columnId:'a.owner_no',
			value:Ext.getCmp('cmbOwnerNo6601_d1').getValue()
		};
		strDetail1.push(d);
	}
	if(!Ext.isEmpty(Ext.getCmp('cmbUntreadType6601_d1').getValue()))
	{
		var d={
			columnId:'iim.quality',
			value:Ext.getCmp('cmbUntreadType6601_d1').getValue()
		};
		strDetail1.push(d);
	}
	
	var jsonDetail = Ext.encode(strDetail1);
	var strWheresql = {
		strWheresql : jsonDetail
	};
	Ext.apply(Ext.getCmp('grid_01_6601').getStore().proxy.extraParams,strWheresql);
	Ext.getCmp('grid_01_6601').getStore().removeAll();
	Ext.getCmp('grid_01_6601').getStore().load();
}

function confirm6601(){
	var msgShow = commonMegShow("正在保存,请稍等...");
	var data = Ext.getCmp('grid_01_6601').getSelectionModel().getSelection();
	var strMaster=
	{
		enterpriseNo:Ext.get('enterpriseNo').getValue(),
		warehouseNo:Ext.get('warehouseNo').getValue(),
		SUntreadNo:data[0].get('SUntreadNo'),			
		SCheckNo:data[0].get('SCheckNo'),				
		checkWorker:Ext.getCmp('cmbCheckWorker6601').getValue(),
		dockNo:workSpaceNo,
		untreadType:data[0].get('untreadType'),
		classType:data[0].get('classType'),	
		ownerNo:data[0].get('ownerNo'),	
		quality:data[0].get('quality')
			
	};
				
	var strJsonMaster = Ext.encode(strMaster);
	var params = 
	{
		strJsonMaster:strJsonMaster
	};
	Ext.Ajax.request({
		method:'POST',
		url:'ridata_CheckConfirmAction_checkConfirm',
		params:params,
		success:function(response)
		{
			msgShow.hide();
			var data = Ext.decode(response.responseText);
			if(data.isSucc)
			{
				Ext.example.msg('提示',data.msg);
				Ext.getCmp('grid_01_6601').store.reload();
				Ext.getCmp('grid_02_6601').getStore().removeAll();
				Ext.getCmp('grid_03_6601').getStore().removeAll();
			}else
			{
				Ext.example.msg('提示',data.msg+data.obj);
			}			
		}
	});
}

