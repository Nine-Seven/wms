/**
 * 模块名称：商品包装
 * @author JUN
 */
Ext.define('cms.store.bdef.bdef_ArticlePackingStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_ArticlePackingModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_DefarticleAction_queryBdefArticlePackingList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});