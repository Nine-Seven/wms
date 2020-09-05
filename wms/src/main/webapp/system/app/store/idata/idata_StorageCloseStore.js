Ext.define('cms.store.idata.idata_StorageCloseStore',{
	extend:'Ext.data.Store',
	model:'cms.model.idata.idata_ImportSdModel', 
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'idata_StorageCloseAction_queryIdataImportSd',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});