Ext.define('cms.store.bdef.bdef_ControlLogStore',{
	extend:'Ext.data.Store',
	model:'cms.model.wms.Wms_InterfaceLogModel',
	pageSize : appConfig.getPageSize(),
    proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_ControlLogAction_getInterfaceLogList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});