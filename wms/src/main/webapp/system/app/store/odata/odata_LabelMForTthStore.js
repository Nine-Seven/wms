Ext.define('cms.store.odata.odata_LabelMForTthStore',{
	extend:'Ext.data.Store',
	model:'cms.model.stock.stock_LabelMModel', 
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'odata_CarPlanForTthAction_queryStockLabelM',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});