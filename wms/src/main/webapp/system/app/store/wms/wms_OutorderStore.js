Ext.define('cms.store.wms.wms_OutorderStore', {
	extend : 'Ext.data.Store',
	model : 'cms.model.wms.wms_OutorderModel',
	pageSize : appConfig.getPageSize(),
	proxy : {
		type : 'ajax',
		method : 'post',
		url : 'odata_TacticsAction_queryWmsOutOrderList.action',
		reader : {
			type : 'json',
			root : 'rootList',
			totalProperty : 'totalCount'
		}
	},
	autoLoad : false
});