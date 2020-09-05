/*
 * 退厂确认
 * lich
 * 7303
 */
Ext.define('cms.controller.rodata.rodata_OutstockComfirmController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.rodata.rodata_OutstockComfirmUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//退厂确认》选中单头，加载商品明细信息
			'rodata_OutstockComfirmUI grid[id=gridRodata_MComfirm7303]' : {
				selectionchange : this.gridRodata_MComfirm7303Selectionchange
			},//退厂确认》刷新
			'rodata_OutstockComfirmUI button[name=refresh]':{
				click:this.refresh
			},//退厂确认》确认退厂
			'rodata_OutstockComfirmUI button[id=btnConfirm7303]':{
				click:this.btnConfirm7303Click
			},//退厂确认》供应商选择 过滤数据
			'rodata_OutstockComfirmUI combo[id=cmbSuppliers7303]':{
				change:this.cmbSuppliers7303Select
			}
		});
	},
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		//显示变量0为不显示，1为显示
		var planBox7303=commonGetModuleField('7303','planBox')[0].flag;
		var planDis7303=commonGetModuleField('7303','planDis')[0].flag;
		var planQmin7303=commonGetModuleField('7303','planQmin')[0].flag;

		var packingUnit7303=commonGetModuleField('7303','packingUnit')[0].flag;
		var packingSpec7303=commonGetModuleField('7303','packingSpec')[0].flag;
		if(planBox7303==0){
			Ext.getCmp('planBox7303').setVisible(false);
		}
		if(planDis7303==0){
			Ext.getCmp('planDis7303').setVisible(false);
		}
		if(planQmin7303==0){
			Ext.getCmp('planQmin7303').setVisible(false);
		}
		if(packingUnit7303==0){
			Ext.getCmp('packingUnit7303').setVisible(false);
		}
		if(packingSpec7303==0){
			Ext.getCmp('packingSpec7303').setVisible(false);
		}
		Ext.getCmp('gridRodata_MComfirm7303').getStore().removeAll();
		Ext.getCmp('gridRodata_MComfirm7303').getStore().reload();
		Ext.getCmp('cmbSuppliers7303').getStore().removeAll();
		Ext.getCmp('cmbSuppliers7303').getStore().load();
	},
	
	gridRodata_MComfirm7303Selectionchange:function(){
		var data = Ext.getCmp('gridRodata_MComfirm7303').getSelectionModel().getSelection();
		if(data.length!=0)
		{
			
			var strWheresql={
				strPoNo:data[0].get('poNo')
			};
			Ext.apply(Ext.getCmp('gridRodata_DComfirm7303').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('gridRodata_DComfirm7303').getStore().removeAll();
			Ext.getCmp('gridRodata_DComfirm7303').getStore().load();			
			//赋值
			Ext.get('rgstName7303').dom.innerHTML=data[0].get('rgstName');
			Ext.get('rgstDate7303').dom.innerHTML=data[0].get('rgstDate');
			Ext.get('updtName7303').dom.innerHTML=data[0].get('updtName');
			Ext.get('updtDate7303').dom.innerHTML=data[0].get('updtDate');
		}else{
			//清空明细
			Ext.getCmp('gridRodata_DComfirm7303').getStore().removeAll();
			//赋值
			Ext.get('rgstName7303').dom.innerHTML='';
			Ext.get('rgstDate7303').dom.innerHTML='';
			Ext.get('updtName7303').dom.innerHTML='';
			Ext.get('updtDate7303').dom.innerHTML='';
		}
		
	},

	/**
	 * 刷新
	 */
	refresh:function(){
		 Ext.getCmp('cmbSuppliers7303').setValue('');
	  	Ext.getCmp('gridRodata_MComfirm7303').getStore().removeAll();
		Ext.getCmp('gridRodata_MComfirm7303').getStore().reload();
	  	Ext.getCmp('cmbSuppliers7303').getStore().removeAll();
		Ext.getCmp('cmbSuppliers7303').getStore().reload();
		 Ext.getCmp('txtGrossWeight7303').setValue('');//刷新清理毛重
	  },
	  
	btnConfirm7303Click:function(){
		var data = Ext.getCmp('gridRodata_MComfirm7303').getSelectionModel().getSelection();
		if(data.length==0)
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.selectoutstockDirect);
			return;
		}
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
			return;
		}		
    	Ext.Msg.confirm($i18n.prompt,$i18n_prompt.outstockDirectOrNot, function(button, text) 
		{
			if (button == 'yes') 
			{	
				var strGrossWeight =Ext.getCmp('txtGrossWeight7303').getValue('');
				if(Ext.isEmpty(strGrossWeight)){
					Ext.example.msg($i18n.prompt,'毛重不能为空');
					return;
				}
				Ext.Ajax.request({
					method:'POST',
					url:'rodata_OutstockComfirmAction_tscRoComfirm.action',
					params:params={
						strOwnerNo:data[0].get('ownerNo'),
						strRecedeNo:data[0].get('recedeNo'),
						strGrossWeight: strGrossWeight
					},
					success:function(response){
						var data1 = Ext.decode(response.responseText);
						if(data1.isSucc){
							Ext.example.msg($i18n.prompt,data1.msg);
						  	Ext.getCmp('gridRodata_MComfirm7303').getStore().removeAll();
							Ext.getCmp('gridRodata_MComfirm7303').getStore().reload();
							Ext.getCmp('txtGrossWeight7303').setValue('');
							Ext.getCmp('cmbSuppliers7303').getStore().reload();
							
						}else{
							Ext.example.msg($i18n.prompt,data1.msg+data1.obj);
						}				
					},
					failure:function(response){
						Ext.example.msg($i18n.prompt,$i18n_prompt.CannotSubForWeb);
					}
				});
			}
		});
	},
	cmbSuppliers7303Select:function(){
		
		var strWheresql={
			strSuppliersNo: Ext.getCmp('cmbSuppliers7303').getValue()
		};
		Ext.apply(Ext.getCmp('gridRodata_MComfirm7303').getStore().proxy.extraParams,strWheresql);
		Ext.getCmp('gridRodata_MComfirm7303').getStore().removeAll();
		Ext.getCmp('gridRodata_MComfirm7303').getStore().load();		
	}
});