Ext.define('cms.store.wms.wms_WarehouseOutorderFlowStore', {
	extend : 'Ext.data.Store',
	model : 'cms.model.wms.wms_OutorderFlowModel',
	pageSize : appConfig.getPageSize(),
	proxy : {
		type : 'ajax',
		method : 'post',
		url : 'odata_TacticsAction_queryWmsWarehouseOutOrderFlow.action',
		extraParams:{
			strType : "0"
		},
		reader : {
			type : 'json',
			root : 'rootList',
			totalProperty : 'totalCount'
		}
	},
	autoLoad : false,
    storeId:'wms_WarehouseOutorderFlowStore'
});