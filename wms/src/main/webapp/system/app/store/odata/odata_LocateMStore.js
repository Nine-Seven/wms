Ext.define('cms.store.odata.odata_LocateMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.odata.odata_ExpMModel', 
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'odata_LocateAction_queryLocateM',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});