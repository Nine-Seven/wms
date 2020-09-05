/**
 * 模块名称：库存调账确认
 * 模块编码：D301
 * 创建：hcx
 */
Ext.define('cms.controller.stock.stock_ConfirmController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.stock.stock_ConfirmUI'
	         ],
	model:'',
	store:'',
	init:function()
	{
		this.control({//查询
			'stock_ConfirmUI button[name=query]':{
				click:this.query
			},//刷新
			'stock_ConfirmUI button[name=refresh]':
		    {
			    click:this.refreshClick
		    },//货主筛选
			'stock_ConfirmUI bdef_DefOwnerCombo[id=cmbOwnerNoD301_d1]':
			{
				change:this.cmbOwnerNoD301_d1Change
			},//调整类型筛选
			'stock_ConfirmUI wms_DefFieldValCombo[id=cmbPlanTypeTypeD301_d1]':
			{
				change:this.cmbPlanTypeTypeD301_d1Change
			},
			//选择单据
			'stock_ConfirmUI grid[id=grid_01_D301]':{
				selectionchange : this.selectionchangeD301
			},//整单确认
		    'stock_ConfirmUI button[id=btnConfirmD301]':
		    {
			    click:this.btnConfirmD301Click
		    }
		});
	},
	
	initializtion:function()
	{
		//显示变量0为不显示，1为显示
		var planBox_D301=commonGetModuleField('D301','planBox')[0].flag;
		var planQmin_D301=commonGetModuleField('D301','planQmin')[0].flag;
		var planDis_D301=commonGetModuleField('D301','planDis')[0].flag;
		var planBox_D301_1=commonGetModuleField('D301','realBox')[0].flag;
		var planQmin_D301_1=commonGetModuleField('D301','realQmin')[0].flag;
		var planDis_D301_1=commonGetModuleField('D301','realDis')[0].flag;
		
		var packingUnit_D301=commonGetModuleField('D301','packingUnit')[0].flag;
		var packingSpec_D301=commonGetModuleField('D301','packingSpec')[0].flag;
		
		if(planBox_D301==0){
			Ext.getCmp('planBox_D301').setVisible(false);
		}
		if(planQmin_D301==0){
			Ext.getCmp('planQmin_D301').setVisible(false);
		}
		if(planDis_D301==0){
			Ext.getCmp('planDis_D301').setVisible(false);
		}
		
		if(planBox_D301_1==0){
			Ext.getCmp('planBox_D301_1').setVisible(false);
		}
		if(planQmin_D301_1==0){
			Ext.getCmp('planQmin_D301_1').setVisible(false);
		}
		if(planDis_D301_1==0){
			Ext.getCmp('planDis_D301_1').setVisible(false);
		}
		
		if(packingUnit_D301==0){
			Ext.getCmp('packingUnit_D301').setVisible(false);
		}
		if(packingSpec_D301==0){
			Ext.getCmp('packingSpec_D301').setVisible(false);
		}
		
	},
	//查询
	query:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=4601;
		queryGrid='grid_01_D301';	
	},
	//刷新
	refreshClick:function()
	{
		Ext.getCmp('cmbOwnerNoD301_d1').setValue('');
		Ext.getCmp('cmbPlanTypeTypeD301_d1').setValue('');
		Ext.getCmp('cmbOwnerNoD301_d2').setValue('');
		Ext.getCmp('poNoD301').setValue('');
		Ext.getCmp('grid_01_D301').getStore().removeAll();
		Ext.getCmp('grid_02_D301').getStore().removeAll();
		Ext.getCmp('grid_01_D301').store.reload();
		
	},
	cmbOwnerNoD301_d1Change:function(combo)
	{
		var detail=[];
		if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNoD301_d1').getValue())){
			var strDtl = {
	      			 columnId:'t1.owner_no',
	      			 value:Ext.getCmp('cmbOwnerNoD301_d1').getValue()
	      		 };
			detail.push(strDtl);	
		}
		var wheresql={
	 			 str:Ext.encode(detail)
	 		 };
	    Ext.apply(Ext.getCmp('cmbPlanTypeTypeD301_d1').getStore().proxy.extraParams,wheresql);
	    Ext.getCmp('cmbPlanTypeTypeD301_d1').getStore().removeAll();
	    Ext.getCmp('cmbPlanTypeTypeD301_d1').getStore().reload();	
		selectD301();
	},
	cmbPlanTypeTypeD301_d1Change:function(combo)
	{
		selectD301();
	},
	//选中调账确认单单据列表，加载商品信息
	selectionchangeD301:function(th,selected,eOpts){
		var objdata = Ext.getCmp('grid_01_D301').getSelectionModel().getSelection();
		var detail=[];
		if(objdata.length!=0){
			Ext.getCmp('cmbOwnerNoD301_d2').setValue(objdata[0].get('ownerNo'));
			Ext.getCmp('poNoD301').setValue(objdata[0].get('poNo'));

			var strDtl = {
	      			 columnId:'a.confirm_no',
	      			 value:objdata[0].get('confirmNo')
	      		 };
			detail.push(strDtl);	
			 var wheresql={
	      			 str:Ext.encode(detail)
	      		 };
			Ext.apply(Ext.getCmp('grid_02_D301').getStore().proxy.extraParams,wheresql);
			Ext.getCmp('grid_02_D301').getStore().removeAll();
			Ext.getCmp('grid_02_D301').getStore().load();	
		}
	},
	btnConfirmD301Click:function()
	{
		if(!commonCheckIsInputAll('form_01_D301'))
		{
			return;
		}
		var gridcount=Ext.getCmp("grid_02_D301").getStore().getCount();
		if(gridcount>0)
		{
			if(!commonCheckdetailgrid('grid_02_D301',0,gridcount-1))
			{
				return;
			}	
		}else{			
			Ext.example.msg($i18n.prompt,$i18n_prompt.tableCannotBeNull);
			return;
		}
		
		var data = Ext.getCmp('grid_01_D301').getSelectionModel().getSelection();
		if(data[0].get('status')=='13')
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.isConfirm);
			return;
		}	
		var strMaster=
		{
			enterpriseNo:Ext.get('enterpriseNo').getValue(),
			warehouseNo:Ext.get('warehouseNo').getValue(),
			ownerNo:data[0].get('ownerNo'),			
			confirmNo:data[0].get('confirmNo'),				
			rgstName:Ext.get('workerNo').getValue()
		};
					
		var strJsonMaster = Ext.encode(strMaster);
		var params = 
		{
			strJsonMaster:strJsonMaster
		};

		Ext.Msg.confirm($i18n.prompt,$i18n_prompt.confirmOrNot,function(button, text) 
		{
			if (button == 'yes') 
			{
				var msgShow = commonMegShow($i18n_prompt.saving_wait);
				
				Ext.Ajax.request({
					method:'POST',
					url:'stock_ConfirmAction_confirm.action',
					params:params,
					success:function(response)
					{
						msgShow.hide();
						var data = Ext.decode(response.responseText);
						if(data.isSucc)
						{
							Ext.example.msg($i18n.prompt,data.msg);
							Ext.getCmp('grid_01_D301').getStore().removeAll();
							Ext.getCmp('grid_02_D301').getStore().removeAll();
							Ext.getCmp('grid_01_D301').store.reload();
						}else
						{
							Ext.example.msg($i18n.prompt,data.msg+data.obj);
						}			
					}
				});
			}
		});
	
	}
	
});

/**
 * 筛选单头
 */
function screenMasterD301()
{
	var strDetail1 = [];
	if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNoD301_d1').getValue()))
	{
		var d={
			columnId:'a.owner_no',
			value:Ext.getCmp('cmbOwnerNoD301_d1').getValue()
		};
		strDetail1.push(d);
	}
	if(!Ext.isEmpty(Ext.getCmp('cmbUntreadTypeD301_d1').getValue()))
	{
		var d={
			columnId:'iim.class_type',
			value:Ext.getCmp('cmbUntreadTypeD301_d1').getValue()
		};
		strDetail1.push(d);
	}
	
	var jsonDetail = Ext.encode(strDetail1);
	var strWheresql = {
		strWheresql : jsonDetail
	};
	Ext.apply(Ext.getCmp('grid_01_D301').getStore().proxy.extraParams,strWheresql);
	Ext.getCmp('grid_01_D301').getStore().removeAll();
	Ext.getCmp('grid_01_D301').getStore().load();
}

function confirmD301(){
	var msgShow = commonMegShow("正在保存,请稍等...");
	var data = Ext.getCmp('grid_01_D301').getSelectionModel().getSelection();
	var strMaster=
	{
		enterpriseNo:Ext.get('enterpriseNo').getValue(),
		warehouseNo:Ext.get('warehouseNo').getValue(),
		SUntreadNo:data[0].get('SUntreadNo'),			
		SCheckNo:data[0].get('SCheckNo'),				
		checkWorker:Ext.getCmp('cmbCheckWorkerD301').getValue(),
		dockNo:workSpaceNo
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
				Ext.getCmp('grid_01_D301').store.reload();
				Ext.getCmp('grid_02_D301').getStore().removeAll();
				Ext.getCmp('grid_03_D301').getStore().removeAll();
			}else
			{
				Ext.example.msg('提示',data.msg+data.obj);
			}			
		}
	});
}
function selectD301()
{
	var detail=[];
	if(!Ext.isEmpty(Ext.getCmp('cmbOwnerNoD301_d1').getValue())){
		var strDtl = {
      			 columnId:'a.owner_no',
      			 value:Ext.getCmp('cmbOwnerNoD301_d1').getValue()
      		 };
		detail.push(strDtl);	
	}
	if(!Ext.isEmpty(Ext.getCmp('cmbPlanTypeTypeD301_d1').getValue())){
		 var strDt2 = {
     			 columnId:'b.plan_type',
     			 value:Ext.getCmp('cmbPlanTypeTypeD301_d1').getValue()
     		 };
		detail.push(strDt2);	
	 }
	 var wheresql={
 			 str:Ext.encode(detail)
 		 };
	Ext.getCmp('cmbOwnerNoD301_d2').setValue('');
	Ext.getCmp('poNoD301').setValue('');
		
    Ext.getCmp('grid_02_D301').getStore().removeAll();

    Ext.apply(Ext.getCmp('grid_01_D301').getStore().proxy.extraParams,wheresql);
    Ext.getCmp('grid_01_D301').getStore().removeAll();
    Ext.getCmp('grid_01_D301').getStore().reload();	
}

