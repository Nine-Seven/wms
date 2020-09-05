Ext.define('cms.store.common.countStore',{
	extend:'Ext.data.Store',
	model:'cms.model.common.countModel',
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'authAction_getCount.action',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});