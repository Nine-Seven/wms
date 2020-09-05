Ext.define('cms.store.bdef.bdef_DefOwnerComboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.common.comboModel',
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_DefOwnerAction_queryOwnerCombo',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});