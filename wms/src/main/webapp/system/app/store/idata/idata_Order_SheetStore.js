Ext.define('cms.store.idata.idata_Order_SheetStore',{
	extend:'Ext.data.Store',
	model:'cms.model.idata.idata_Order_SheetModel',
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'idata_Order_TimeAction_getIdata_Order_SheetList.action',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});