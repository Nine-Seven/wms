/**
 * 模块名称：根据选择的科目，显示相应的未分配的计费项目
 * 模块编码：B301
 * 创建：chensr 
 */
Ext.define('cms.store.bset.bill_Account_FormulasetStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bset.bill_FormulasetModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bill_AccountAction_getFormulasetList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});