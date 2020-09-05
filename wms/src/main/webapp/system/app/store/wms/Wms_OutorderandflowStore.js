Ext.define('cms.store.wms.Wms_OutorderandflowStore', {
	extend : 'Ext.data.Store',
	model : 'cms.model.wms.wms_OutorderFlowModel',
	pageSize : appConfig.getPageSize(),
	proxy : {
		type : 'ajax',
		method : 'post',
		url : 'odata_TacticsAction_queryWmsOutOrderFlow.action',
		reader : {
			type : 'json',
			root : 'rootList',
			totalProperty : 'totalCount'
		}
	},
	autoLoad : false
	
	
});