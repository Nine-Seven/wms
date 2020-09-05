Ext.define('cms.store.odata.odata_ExpCancelDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.odata.odata_ExpCancelDModel', 
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'odata_ExpCancelAction_getExpCancelDList',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});