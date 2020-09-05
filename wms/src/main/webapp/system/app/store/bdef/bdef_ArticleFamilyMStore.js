/**
 * 模块名称：商品与商品群组关系
 * 模块编码：1R01
 * 创建：hkl
 */
Ext.define('cms.store.bdef.bdef_ArticleFamilyMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_ArticleFamilyMModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_ArticleFamilyAction_getArticleFamily_MList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});