Ext.define('cms.store.ridata.ridata_CheckConfirmDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.ridata.ridata_CheckDModel', 
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'ridata_CheckConfirmAction_queryCheckD',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});