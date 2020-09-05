Ext.define('cms.store.bdef.bdef_PackingQtyComboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.common.comboModel',
	autoLoad:false,
    proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_DefarticleAction_queryPackingCombo',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});