Ext.define('cms.store.wms.wms_RiorderStore', {
	extend : 'Ext.data.Store',
	model : 'cms.model.wms.wms_RiorderModel',
	pageSize : appConfig.getPageSize(),
	proxy : {
		type : 'ajax',
		method : 'post',
		url : 'ridata_TacticsAction_queryWmsRiOrderList.action',
		reader : {
			type : 'json',
			root : 'rootList',
			totalProperty : 'totalCount'
		}
	},
	autoLoad : false
});