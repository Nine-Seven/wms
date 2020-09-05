Ext.define('cms.store.idata.idata_InStock_MStore',{
	extend:'Ext.data.Store',
	model:'cms.model.idata.idata_InStock_MModel',
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'idata_InStockAction_getInStock_MList.action',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});