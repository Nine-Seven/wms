Ext.define('cms.store.idata.idata_InstockMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.idata.idata_InstockMModel',
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'idata_InstockAction_getInstockMList.action',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});