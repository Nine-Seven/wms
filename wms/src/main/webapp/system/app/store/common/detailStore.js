Ext.define('cms.store.common.detailStore',{
	extend:'Ext.data.Store',
	model:'cms.model.common.detailModel',
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'authAction_getGridData.action',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});