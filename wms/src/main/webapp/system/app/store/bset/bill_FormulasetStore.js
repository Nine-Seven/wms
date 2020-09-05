/**
 * 模块名称：计费公式管理Store
 * 模块编码：B101
 * 创建：chensr 
 */
Ext.define('cms.store.bset.bill_FormulasetStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bset.bill_FormulasetModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bill_FormulasetAction_getFormulasetList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});