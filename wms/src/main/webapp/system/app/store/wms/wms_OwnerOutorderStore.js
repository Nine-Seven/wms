Ext.define('cms.store.wms.wms_OwnerOutorderStore', {
	extend : 'Ext.data.Store',
	model : 'cms.model.wms.wms_OwnerOutorderModel',
	pageSize : appConfig.getPageSize(),
	proxy : {
		type : 'ajax',
		method : 'post',
		url : 'odata_TacticsAction_queryWmsOwnerOutOrderList.action',
		reader : {
			type : 'json',
			root : 'rootList',
			totalProperty : 'totalCount'
		}
	},
});