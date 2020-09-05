Ext.define('cms.store.bdef.bdef_DefcarComboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.common.comboModel',
	autoLoad:false,
    proxy: {
        type: 'ajax',
        method: 'post',
        url: 'odata_CarPlanAction_queryCarCombo',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});