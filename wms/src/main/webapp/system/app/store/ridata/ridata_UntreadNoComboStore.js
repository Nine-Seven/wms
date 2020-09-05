Ext.define('cms.store.ridata.ridata_UntreadNoComboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.comboModel',
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'ridata_UntreadAction_getUntreadNoList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});