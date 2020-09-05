Ext.define('cms.store.bdef.bdef_DefGroupNoComboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.comboModel',
	pageSize : appConfig.getPageSize(),
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_ArticleGroupAction_getGroupNoComboList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    },
	autoLoad:false
});