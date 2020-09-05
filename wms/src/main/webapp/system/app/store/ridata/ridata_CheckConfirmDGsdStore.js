Ext.define('cms.store.ridata.ridata_CheckConfirmDGsdStore',{
	extend:'Ext.data.Store',
	model:'cms.model.ridata.ridata_CheckPalTmpModel', 
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'ridata_CheckConfirmGsdAction_queryCheckD',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});