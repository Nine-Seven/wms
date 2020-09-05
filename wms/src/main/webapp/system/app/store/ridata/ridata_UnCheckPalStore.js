Ext.define('cms.store.ridata.ridata_UnCheckPalStore',{
	extend:'Ext.data.Store',
	model:'cms.model.ridata.ridata_CheckPalModel', 
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'ridata_CheckConfirmAction_queryUnCheckPal',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});