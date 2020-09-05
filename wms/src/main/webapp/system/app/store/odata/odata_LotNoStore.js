Ext.define('cms.store.odata.odata_LotNoStore',{
	extend:'Ext.data.Store',
	model:'cms.model.common.comboModel',
	autoLoad:false,
    proxy: {
        type: 'ajax',
        method: 'post',
        url: 'idata_CheckAction_getlotNo.action',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});