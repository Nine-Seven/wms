Ext.define('cms.store.idata.idata_ImPortAllotStore',{
	extend:'Ext.data.Store',
	model:'cms.model.idata.idata_ImPort_DModel',
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'idata_ImPortAction_getImPortAllot.action',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});