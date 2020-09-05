Ext.define('cms.store.idata.idata_ImportSdStore',{
	extend:'Ext.data.Store',
	model:'cms.model.idata.idata_ImportSdModel', 
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'idata_CheckAction_queryIdataImportSd.action',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});