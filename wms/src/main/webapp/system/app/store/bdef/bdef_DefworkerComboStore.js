Ext.define('cms.store.bdef.bdef_DefworkerComboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.common.comboModel',
	autoLoad:false,
    proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_DefWorkerAction_queryWorkerCombo',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});