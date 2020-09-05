/**
 * 模块名称：商品类别下拉
 * @author JUN
 */
Ext.define('cms.store.bdef.bdef_ArticleGroupComboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.common.comboModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_ArticleGroupAction_queryGroupCombo',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});