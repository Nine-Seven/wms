Ext.define('cms.store.idata.dock_SheetStore',{
	extend:'Ext.data.Store',
	model:'cms.model.idata.dock_SheetModel',
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'idata_Order_TimeAction_getDock_Sheet.action',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});