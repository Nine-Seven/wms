/**
 * 模块名称：返配验收确认
 * 模块编码：6701
 * 创建：csr
 */
var isCanEdit6701=false;
Ext.define('cms.controller.ridata.ridata_CheckConfirmGsdController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.ridata.ridata_CheckConfirmGsdUI'
	         ],
	model:'',
	store:'',
	init:function()
	{
		this.control({//货主筛选
			'ridata_CheckConfirmGsdUI bdef_DefOwnerCombo[id=cmbOwnerNo6701_d1]':
			{
				change:this.cmbOwnerNo6701_d1Change
			},//进货单别筛选
			'ridata_CheckConfirmGsdUI wms_DefFieldValCombo[id=cmbUntreadType6701_d1]':
			{
				change:this.cmbUntreadType6701_d1Change
			},//选择头档
			'ridata_CheckConfirmGsdUI grid[id=grid_01_6701]':
			{
				selectionchange:this.grid_01_6701Selectionchange
			},//刷新
			'ridata_CheckConfirmGsdUI button[name=refresh]':
			{
				click:this.refreshClick
			},//整单确认
			'ridata_CheckConfirmGsdUI button[id=btnConfirm6701]':
			{
				click:this.btnConfirm6701Click
			},//商品Grid编辑
			'ridata_CheckConfirmGsdUI grid[id=grid_02_6701]':{
				beforeedit:this.grid_02_6701beforeedit
			},//修改
			'ridata_CheckConfirmGsdUI button[name=edit]':
			{
				click:this.edit
			},//保存
			'ridata_CheckConfirmGsdUI button[name=save]':
			{
				click:this.save
			},//查询
			'ridata_CheckConfirmGsdUI commMenuWidget4[id=menu6701] button[name=query]':{
				click:this.query
			}
		});
	},
	
	initializtion:function(){
		isCanEdit6701=false;
		
		//显示变量0为不显示，1为显示
		var planBox_6701=commonGetModuleField('6701','planBox')[0].flag;	//计划
		var planQmin_6701=commonGetModuleField('6701','planQmin')[0].flag;
		var planDis_6701=commonGetModuleField('6701','planDis')[0].flag;
		var packingUnit_6701=commonGetModuleField('6701','packingUnit')[0].flag;
		var packingSpec_6701=commonGetModuleField('6701','packingSpec')[0].flag;
		
		if(planBox_6701==0){
			Ext.getCmp('planBox_6701').setVisible(false);
		}
		if(planQmin_6701==0){
			Ext.getCmp('planQmin_6701').setVisible(false);
		}
		if(planDis_6701==0){
			Ext.getCmp('planDis_6701').setVisible(false);
		}
		if(packingUnit_6701==0){
			Ext.getCmp('packingUnit_6701').setVisible(false);
		}
		if(packingSpec_6701==0){
			Ext.getCmp('packingSpec_6701').setVisible(false);
		}
		
	},
	
	grid_02_6701beforeedit:function(e){
		if(!isCanEdit6701){
	        e.cancel = true;
	        return  false;  
	    }	
	},
	
	edit:function(){
		isCanEdit6701=true;
		disableButtonFunc(0,'#menu6701 [name=save]','保存');
		disableButtonFunc(1,'#menu6701 [name=edit]','修改');
	},
	
	save:function(){
		isCanEdit6701=false;
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg('提示',"工作站不能为空，请设置工作站！");
			return;
		}
		saveIdataPalTmp(workSpaceNo);
		disableButtonFunc(1,'#menu6701 [name=save]','保存');
		disableButtonFunc(0,'#menu6701 [name=edit]','修改');
	},
	
	query:function(){
		Ext.create('cms.view.common.queryWindow',{
		}).show();
		Ext.getCmp('queryWindow').add(Ext.create('cms.view.common.queryPanel'));
		queryModuleId=6701;
		queryGrid='grid_01_6701';	
	},
	
	cmbOwnerNo6701_d1Change:function(combo)
	{
		var strDetail1 = [];
		var d={
			columnId:'a.owner_no',
			value:combo.getValue()
		};
		strDetail1.push(d);
		var jsonDetail = Ext.encode(strDetail1);
		var strWheresql = {
			strQuery : jsonDetail
		};
		Ext.apply(Ext.getCmp('grid_01_6701').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_6701').getStore().removeAll();
		Ext.getCmp('grid_01_6701').getStore().load();
	},
	
	cmbUntreadType6701_d1Change:function(combo)
	{
		var strDetail1 = [];
		var d={
			columnId:'a.untread_type',
			value:combo.getValue()
		};
		strDetail1.push(d);
		var jsonDetail = Ext.encode(strDetail1);
		var strWheresql = {
			strQuery : jsonDetail
		};
		Ext.apply(Ext.getCmp('grid_01_6701').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('grid_01_6701').getStore().removeAll();
		Ext.getCmp('grid_01_6701').getStore().load();
	},
	
	grid_01_6701Selectionchange:function()
	{
		var data = Ext.getCmp('grid_01_6701').getSelectionModel().getSelection();
		
		if(data.length!=0)
		{
			Ext.getCmp('cmbOwnerNo6701_d2').getStore().add({
				value:String(data[0].get('ownerNo')),
				dropValue:'['+data[0].get('ownerNo')+']'+data[0].get('ownerName'),
				text:String(data[0].get('ownerName'))
		    });
			Ext.getCmp('cmbOwnerNo6701_d2').setValue(data[0].get('ownerNo'));			
			Ext.getCmp('txtUntreadNo6701').setValue(data[0].get('untreadNo'));

			var strWheresql={
				strWheresql:data[0].get('SCheckNo')
			};
			Ext.apply(Ext.getCmp('grid_02_6701').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('grid_02_6701').getStore().removeAll();
			Ext.getCmp('grid_02_6701').getStore().load();			
		}
	},
	
	refreshClick:function()
	{
		Ext.getCmp('grid_01_6701').getStore().reload();
		isCanEdit6701=false;
		disableButtonFunc(1,'#menu6701 [name=save]','保存');
		disableButtonFunc(0,'#menu6701 [name=edit]','修改');
	},
	
	
	btnConfirm6701Click:function()
	{
		if(!commonCheckIsInputAll('form_01_6701'))
		{
			return;
		}
		var gridcount=Ext.getCmp("grid_02_6701").getStore().getCount();
		if(gridcount>0)
		{
			if(!commonCheckdetailgrid('grid_02_6701',0,gridcount-1))
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
		var data = Ext.getCmp('grid_01_6701').getSelectionModel().getSelection();
		
		var params={
				SUntreadNo:data[0].get('SUntreadNo'),			
				SCheckNo:data[0].get('SCheckNo'),	
				ownerNo:data[0].get('ownerNo'),
				workerNo:Ext.getCmp('cmbCheckWorker6701').getValue(),
				dockNo:workSpaceNo
			
		};
		Ext.Ajax.request({
			method:'post',
			url:'ridata_CheckConfirmGsdAction_checkPalTmp',
			params:params,
			success:function(response){
				var data = Ext.decode(response.responseText);
				if(data.isSucc){
					Ext.example.msg($i18n.prompt,data.msg);
					Ext.getCmp('grid_01_6701').getStore().removeAll();
					Ext.getCmp('grid_01_6701').getStore().load();
					Ext.getCmp('grid_02_6701').getStore().removeAll();

				}else{
					Ext.example.msg($i18n.prompt,data.msg);
				}
			}
		});
	}
});

function saveIdataPalTmp(workSpaceNo){
	var grid=Ext.getCmp('grid_02_6701');
	var gridcount=Ext.getCmp('grid_02_6701').getStore().getCount();
	
		if(gridcount>0){
			for(var i=0;i<gridcount;i++){
				if(Ext.isEmpty(grid.getStore().getAt(i).get(grid.columns[8].dataIndex))){
					Ext.example.msg('提示','请输入箱数');
					return;
				}else if(Ext.isEmpty(grid.getStore().getAt(i).get(grid.columns[9].dataIndex))){
					Ext.example.msg('提示','请输入；零散数');
					return;
				}else if(Ext.isEmpty(grid.getStore().getAt(i).get(grid.columns[10].dataIndex))){
					Ext.example.msg('提示','请输入生产日期');
					return;
				}else if(Ext.isEmpty(grid.getStore().getAt(i).get(grid.columns[11].dataIndex))){
					Ext.example.msg('提示','请输入有效期');
					return;
				}else if(Ext.isEmpty(grid.getStore().getAt(i).get(grid.columns[12].dataIndex))){
					Ext.example.msg('提示','请输入商品品质');
					return;
				}else if(Ext.isEmpty(grid.getStore().getAt(i).get(grid.columns[13].dataIndex))){
					Ext.example.msg('提示','请输入批号');
					return;
				}
			}
		}else{			
			Ext.example.msg('提示',"表体不允许为空，请输入表体！");
			return;
		}
	
	
	
	
	var detail=[];
	for(var i=0;i<gridcount;i++){
		var record=Ext.getCmp('grid_02_6701').getStore().getAt(i);
		
		var qualityText='';
		if(record.get('qualityText')=='良品'){
			qualityText='0';
		}else if(record.get('qualityText')=='QA'){
			qualityText='A';
		}else if(record.get('qualityText')=='DM'){
			qualityText='B';
		}
		
		var d={
				enterpriseNo:Ext.get('enterpriseNo').getValue(),
				warehouseNo:record.get('warehouseNo'),
				ownerNo:record.get('ownerNo'),
				SCheckNo:record.get('SCheckNo'),
				SUntreadNo:record.get('SUntreadNo'),
				articleNo:record.get('articleNo'),
				barcode:record.get('barcode'),
				packingQty:record.get('packingQty'),
				checkQty:record.get('checkBox')*record.get('packingQty'),
				printerGroupNo:record.get('printerGroupNo'),
				dockNo:'N',
				workerNo:record.get('workerNo'),
				checkTools:record.get('checkTools'),
				quality:qualityText,
				produceDate:record.get('produceDate'),
				expireDate:record.get('expireDate'),
				lotNo:record.get('lotNo'),
				rsvBatch1:record.get('rsvBatch1'),
				rsvBatch2:record.get('rsvBatch2'),
				rsvBatch3:record.get('rsvBatch3'),
				rsvBatch4:record.get('rsvBatch4'),
				rsvBatch5:record.get('rsvBatch5'),
				rsvBatch6:record.get('rsvBatch6'),
				rsvBatch7:record.get('rsvBatch7'),
				rsvBatch8:record.get('rsvBatch8'),
				labelNo:record.get('labelNo'),
				subLabelNo:record.get('subLabelNo'),
				supplierNo:record.get('supplierNo'),
				fixpalFlag:record.get('fixpalFlag'),
				businessType:record.get('businessType')
		};
		
		detail.push(d);
	};
	
	var chenkPalTmp=Ext.encode(detail);

	var params={
		jsonDetail:chenkPalTmp,
	};
	Ext.Ajax.request({
		method:'post',
		url:'ridata_CheckConfirmGsdAction_saveChenkPalTmp',
		params:params,
		success:function(response){
			var data = Ext.decode(response.responseText);
			if(data.isSucc){
				Ext.example.msg($i18n.prompt,data.msg);
				Ext.getCmp('grid_02_6701').getStore().removeAll();
				Ext.getCmp('grid_02_6701').getStore().load();
			}else{
				Ext.example.msg($i18n.prompt,data.msg);
			}
		}
	});

}

