Ext.define('cms.store.fcdata.fcdata_CheckDwindowStore', {
	extend : 'Ext.data.Store',
	model : 'cms.model.fcdata.fcdata_CheckDModel',
	pageSize : appConfig.getPageSize(),
	autoLoad : false,
	proxy : {
		type : 'ajax',
		method : 'post',
		url : 'fcdata_CheckAction_getCheckD.action'
	},
	
});