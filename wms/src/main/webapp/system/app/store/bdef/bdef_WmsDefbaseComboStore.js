Ext.define('cms.store.bdef.bdef_WmsDefbaseComboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.common.comboModel',
	autoLoad:false,
    proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_WmsDefbaseAction_queryWmsDefbaseCombo',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});