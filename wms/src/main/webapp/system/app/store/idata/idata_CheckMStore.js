Ext.define('cms.store.idata.idata_CheckMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.idata.idata_CheckMModel', 
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'idata_CheckAction_queryCheckM.action',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});