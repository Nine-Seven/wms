Ext.define('cms.store.idata.idata_ImPort_MStore',{
	extend:'Ext.data.Store',
	model:'cms.model.idata.idata_ImPort_MModel',
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'idata_ImPortAction_getImPort_MList.action',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});