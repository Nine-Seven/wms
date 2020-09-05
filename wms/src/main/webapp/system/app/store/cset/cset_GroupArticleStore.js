/**
 * 模块名称：商品类别储位对照关系
 * @author hekl
 */
Ext.define('cms.store.cset.cset_GroupArticleStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cset.cset_CellArticleModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'cset_GroupArticleAction_getCset_Cell_ArticleList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});