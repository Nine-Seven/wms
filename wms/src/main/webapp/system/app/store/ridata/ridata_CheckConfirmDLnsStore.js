Ext.define('cms.store.ridata.ridata_CheckConfirmDLnsStore',{
	extend:'Ext.data.Store',
	model:'cms.model.ridata.ridata_CheckDModel', 
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'ridata_CheckConfirmLnsAction_queryCheckD',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});