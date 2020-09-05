Ext.define('cms.store.bdef.bdef_ArticleFamilyDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_ArticleFamilyDModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_ArticleFamilyAction_getArticleFamily_DList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});