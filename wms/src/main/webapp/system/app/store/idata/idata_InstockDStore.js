Ext.define('cms.store.idata.idata_InstockDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.idata.idata_InstockDModel',
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'idata_InstockAction_getInstockDList.action',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});