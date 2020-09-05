Ext.define('cms.store.ridata.ridata_CheckConfirmMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.ridata.ridata_CheckMModel', 
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'ridata_CheckConfirmAction_queryCheckM',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});