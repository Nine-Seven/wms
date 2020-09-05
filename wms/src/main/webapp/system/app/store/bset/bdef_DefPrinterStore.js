Ext.define('cms.store.bset.bdef_DefPrinterStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_DefPrinterModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bset_GroupAction_getBdef_DefPrinterList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});