Ext.define('cms.store.bset.bset_Printer_GroupStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bset.bset_Printer_GroupModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bset_GroupAction_getBset_Printer_GroupList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});