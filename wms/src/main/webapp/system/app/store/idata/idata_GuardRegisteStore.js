Ext.define('cms.store.idata.idata_GuardRegisteStore',{
	extend:'Ext.data.Store',
	model:'cms.model.idata.idata_GuardRegisteModel',
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'idata_GuardRegisteAction_getGuardRegisteList.action',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});