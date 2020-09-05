/**
 * 模块名称：商品储位对照关系
 * @author JUN
 */
Ext.define('cms.store.cset.cset_CellArticleStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cset.cset_CellArticleModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'cset_CellArticleAction_getCset_Cell_ArticleList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});