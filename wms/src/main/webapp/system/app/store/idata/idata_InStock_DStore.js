Ext.define('cms.store.idata.idata_InStock_DStore',{
	extend:'Ext.data.Store',
	model:'cms.model.idata.idata_InStock_DModel',
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'idata_InStockAction_getInStock_DList.action',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});