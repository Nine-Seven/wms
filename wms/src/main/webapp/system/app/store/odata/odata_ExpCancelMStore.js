Ext.define('cms.store.odata.odata_ExpCancelMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.odata.odata_ExpCancelMModel', 
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'odata_ExpCancelAction_getExpCancelMList',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});