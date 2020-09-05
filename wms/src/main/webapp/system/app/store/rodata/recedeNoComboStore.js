Ext.define('cms.store.rodata.recedeNoComboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.comboModel',
	pageSize : appConfig.getPageSize(),
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'rodata_OutstockDirectAction_getRecedeNoComboList.action',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    },
	autoLoad:false
});