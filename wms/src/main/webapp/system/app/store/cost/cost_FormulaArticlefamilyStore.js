/**
 * 模块名称：计费公式管理Store
 * 模块编码：B101
 * 创建：chensr 
 */
Ext.define('cms.store.cost.cost_FormulaArticlefamilyStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cost.cost_FormulaArticlefamilyModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'cost_FormulasetAction_getFormulaArticlefamilyList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});