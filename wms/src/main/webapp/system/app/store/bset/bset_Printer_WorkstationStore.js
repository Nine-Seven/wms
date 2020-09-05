Ext.define('cms.store.bset.bset_Printer_WorkstationStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bset.bset_Printer_WorkstationModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bset_GroupAction_getBset_Printer_WorkstationList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});