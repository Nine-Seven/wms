Ext.define('cms.store.wms.wms_OwnerRiorderStore', {
	extend : 'Ext.data.Store',
	model : 'cms.model.wms.wms_OwnerRiorderModel',
	pageSize : appConfig.getPageSize(),
	proxy : {
		type : 'ajax',
		method : 'post',
		url : 'ridata_TacticsAction_queryWmsOwnerOutOrderList.action',
		reader : {
			type : 'json',
			root : 'rootList',
			totalProperty : 'totalCount'
		}
	},
	autoLoad : false
});