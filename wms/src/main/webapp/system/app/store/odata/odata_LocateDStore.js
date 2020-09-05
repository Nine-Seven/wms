Ext.define('cms.store.odata.odata_LocateDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.odata.odata_ExpDModel', 
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'odata_LocateAction_queryLocateD',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});