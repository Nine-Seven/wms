Ext.define('cms.store.common.homeStore',{
	extend:'Ext.data.Store',
	model:'cms.model.common.homeModel',
	pageSize : appConfig.getPageSize(),
	proxy:{
		async:false,
		type:'ajax',
		method:'post',
		url : 'authAction_getHomeData.action',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});