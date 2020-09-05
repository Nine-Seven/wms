/**
 * 模块名称：商品类别
 * 模块编码：200008
 * 创建：周欢
 */
Ext.define('cms.store.bdef.bdef_ArticleGroupStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_ArticleGroupModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_ArticleGroupAction_getGroupList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});