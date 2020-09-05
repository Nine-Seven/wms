/**
 * 模块名称：商品下拉
 * @author JUN
 */
Ext.define('cms.store.bdef.bdef_DefArticleComboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.common.comboModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_DefarticleAction_queryBdefArticleCombo',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});