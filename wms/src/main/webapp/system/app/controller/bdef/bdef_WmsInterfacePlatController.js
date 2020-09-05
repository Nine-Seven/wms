/*
 * 模块名称：接口配置
 * 模块编码：I301
 * 创建：czh
 */
Ext.define('cms.controller.bdef.bdef_WmsInterfacePlatController',{
	extend:'Ext.app.Controller',
	requires:[
			  'cms.view.bdef.bdef_WmsInterfacePlatUI',
			  ],
	model:'cms.model.bdef.bdef_WmsInterfacePlatModel',
	store:'cms.store.bdef.bdef_WmsInterfacePlatStore',
	init:function(){
	    this.control({	    	
			'bdef_WmsInterfacePlatUI button[name=refresh]':{
				click:this.refresh
			},

	    });
	},
	refresh:function(){
		Ext.getCmp('interfacePlatGridI301').getStore().reload();	
	},
	
	
	
});

