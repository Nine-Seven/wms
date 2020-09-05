/**
 * 模块名称：计费公式管理Store
 * 模块编码：B101
 * 创建：chensr 
 */
Ext.define('cms.store.cost.cost_FormulasetStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cost.cost_FormulasetModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'cost_FormulasetAction_getFormulasetList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});