Ext.define('cms.store.idata.idata_UnCheckPalStore',{
	extend:'Ext.data.Store',
	model:'cms.model.idata.idata_CheckPalModel', 
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'idata_CheckAction_queryUnCheckPal.action',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});