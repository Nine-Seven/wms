Ext.define('cms.store.idata.idata_CheckDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.idata.idata_CheckDModel', 
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'idata_CheckAction_queryCheckD',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});