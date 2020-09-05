Ext.define('cms.store.wms.wms_WarehouseOutorderStore', {
	extend : 'Ext.data.Store',
	model : 'cms.model.wms.wms_WarehouseOutorderModel',
	pageSize : appConfig.getPageSize(),
	proxy : {
		type : 'ajax',
		method : 'post',
		url : 'odata_TacticsAction_queryWmsWarehouseOutorderList.action',
		reader : {
			type : 'json',
			root : 'rootList',
			totalProperty : 'totalCount'
		}
	},
});