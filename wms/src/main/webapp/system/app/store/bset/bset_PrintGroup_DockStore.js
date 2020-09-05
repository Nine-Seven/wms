Ext.define('cms.store.bset.bset_PrintGroup_DockStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bset.bset_PrintGroup_DockModel',
	pageSize : appConfig.getPageSize(),
	storeId:'bset_PrintGroup_DockStore',
	autoLoad:false,
	proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bset_DocGroupAction_QueryPrintDockRelList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});