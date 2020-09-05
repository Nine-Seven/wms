
Ext.define('cms.store.bset.bset_Article_Barcode_DStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bset.bset_Article_Barcode_DModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bset_Article_BarcodeAction_getBset_Article_Barcode_DList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});