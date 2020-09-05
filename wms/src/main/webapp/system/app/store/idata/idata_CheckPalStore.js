Ext.define('cms.store.idata.idata_CheckPalStore',{
	extend:'Ext.data.Store',
	model:'cms.model.idata.idata_CheckPalModel', 
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'idata_CheckAction_queryCheckPal.action',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});