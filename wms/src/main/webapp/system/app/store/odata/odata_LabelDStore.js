Ext.define('cms.store.odata.odata_LabelDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.stock.stock_LabelDModel', 
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'odata_CarPlanAction_searchDetail',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});