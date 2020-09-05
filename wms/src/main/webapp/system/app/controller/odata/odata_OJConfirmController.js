/*
 * 出货自提确认
 * hkl
 * 3919
 */
Ext.define('cms.controller.odata.odata_OJConfirmController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.odata.odata_OJConfirmUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({//自提确认》选中单头，加载商品明细信息
			'odata_OJConfirmUI grid[id=gridOdata_OJComfirmM3919]' : {
				selectionchange : this.gridOdata_OJComfirmM3919Selectionchange
			},//自提确认》刷新
			'odata_OJConfirmUI button[id=refresh]':{
				click:this.refresh
			},//自提确认》确认自提
			'odata_OJConfirmUI button[id=confirm]':{
				click:this.btnConfirm3919Click
			}
		});
	},
	/**
	 * 初始化界面
	 */
	initializtion:function(){
		Ext.getCmp('gridOdata_OJComfirmM3919').getStore().removeAll();
		Ext.getCmp('gridOdata_OJComfirmM3919').getStore().reload();
		
		//显示变量0为不显示，1为显示
		var realBox_3919=commonGetModuleField('3919','realBox')[0].flag;	//实际
		var realQmin_3919=commonGetModuleField('3919','realQmin')[0].flag;
		var realDis_3919=commonGetModuleField('3919','realDis')[0].flag;
		var packingUnit_3919=commonGetModuleField('3919','packingUnit')[0].flag;
		var packingSpec_3919=commonGetModuleField('3919','packingSpec')[0].flag;
		var realqty_3919=commonGetModuleField('3919','realqty')[0].flag;
		
		if(realqty_3919==0){
			Ext.getCmp('realqty3919').setVisible(false);
		}
		if(packingUnit_3919==0){
			Ext.getCmp('packingUnit3919').setVisible(false);
		}
		if(packingSpec_3919==0){
			Ext.getCmp('packingSpec3919').setVisible(false);
		}
		
		if(realBox_3919==0){
			Ext.getCmp('realBox3919').setVisible(false);
		}
		if(realQmin_3919==0){
			Ext.getCmp('realQmin3919').setVisible(false);
		}
		if(realDis_3919==0){
			Ext.getCmp('realDis3919').setVisible(false);
		}
		
	},
	
	gridOdata_OJComfirmM3919Selectionchange:function(){
		var data = Ext.getCmp('gridOdata_OJComfirmM3919').getSelectionModel().getSelection();
		if(data.length!=0)
		{
			
			var strWheresql={
					strQuery:data[0].get('sourceexpNo')
			};
			Ext.apply(Ext.getCmp('gridOdata_DComfirm3919').getStore().proxy.extraParams,strWheresql);
			Ext.getCmp('gridOdata_DComfirm3919').getStore().removeAll();
			Ext.getCmp('gridOdata_DComfirm3919').getStore().load();			
			
		}else{
			//清空明细
			Ext.getCmp('gridOdata_DComfirm3919').getStore().removeAll();
			
		}
		
	},

	/**
	 * 刷新
	 */
	refresh:function(){
	  	Ext.getCmp('gridOdata_OJComfirmM3919').getStore().removeAll();
		Ext.getCmp('gridOdata_OJComfirmM3919').getStore().reload();
	  	Ext.getCmp('gridOdata_DComfirm3919').getStore().removeAll();
		Ext.getCmp('gridOdata_DComfirm3919').getStore().reload();
	  },
	  
	btnConfirm3919Click:function(){
		var data = Ext.getCmp('gridOdata_OJComfirmM3919').getSelectionModel().getSelection();
		if(data.length==0)
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.clooseOrder);
			return;
		}
		if(Ext.isEmpty(workSpaceNo))
		{
			Ext.example.msg($i18n.prompt,$i18n_prompt.setWorkSpace);
			return;
		}		
    	Ext.Msg.confirm($i18n.prompt,$i18n_prompt.confirmOrNot, function(button, text) 
		{
			if (button == 'yes') 
			{	
				Ext.Ajax.request({
					method:'POST',
					url:'odata_OJComfirmAction_tscOComfirm.action',
					params:params={
						strQuery:data[0].get('sourceexpNo')
					},
					success:function(response){
						var data1 = Ext.decode(response.responseText);
						if(data1.isSucc){
							Ext.example.msg($i18n.prompt,data1.msg);
						  	Ext.getCmp('gridOdata_OJComfirmM3919').getStore().removeAll();
							Ext.getCmp('gridOdata_OJComfirmM3919').getStore().reload();
							
							Ext.getCmp('gridOdata_DComfirm3919').getStore().removeAll();
							Ext.getCmp('gridOdata_DComfirm3919').getStore().reload();
							
						}else{
							Ext.Msg.alert($i18n.prompt,data1.msg+data1.obj);

							//Ext.example.msg($i18n.prompt,data1.msg+data1.obj);
						}				
					}
				});
			}
		});
	},
});