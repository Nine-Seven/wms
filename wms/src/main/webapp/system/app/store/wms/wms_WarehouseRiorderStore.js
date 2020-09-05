Ext.define('cms.store.wms.wms_WarehouseRiorderStore', {
	extend : 'Ext.data.Store',
	model : 'cms.model.wms.wms_WarehouseRiorderModel',
	pageSize : appConfig.getPageSize(),
	proxy : {
		type : 'ajax',
		method : 'post',
		url : 'ridata_TacticsAction_queryWmsWarehouseOutorderList.action',
		reader : {
			type : 'json',
			root : 'rootList',
			totalProperty : 'totalCount'
		}
	},
	autoLoad : false
});