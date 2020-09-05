/**
 * 模块名称：监控日志查询bdef_ControlLogController.js
 * 模块编码：I401
 * 创建：HKL 
 */
Ext.define('cms.controller.bdef.bdef_ControlLogController',{
	extend:'Ext.app.Controller',
	requires:[
	          'cms.view.bdef.bdef_ControlLogUI'
	          ],
	model:'',
	store:'',
	init:function(){
		this.control({
			///刷新
			'bdef_ControlLogUI button[name=refresh]':{
				click:this.refresh
			}
		
		
		});
	},
	
	refresh:function(){
		Ext.apply(Ext.getCmp('grid_01_I401').getStore().proxy.extraParams);
		Ext.getCmp('grid_01_I401').getStore().removeAll();
		Ext.getCmp('grid_01_I401').getStore().reload();
		Ext.apply(Ext.getCmp('grid_02_I401').getStore().proxy.extraParams);
		Ext.getCmp('grid_02_I401').getStore().removeAll();
		Ext.getCmp('grid_02_I401').getStore().reload();
	}
	
});
