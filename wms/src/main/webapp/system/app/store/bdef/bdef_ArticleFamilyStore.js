/**
 * 模块名称:群组商品关系的商品列表
 * @author hkl
 */
Ext.define('cms.store.bdef.bdef_ArticleFamilyStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_DefarticleModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_ArticleFamilyAction_getBdefDefarticleList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});