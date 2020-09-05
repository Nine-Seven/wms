/**
 * 模块名称：科目设置维护-科目与项目关系
 * 模块编码：B303
 * 创建：hcx 
 */
Ext.define('cms.store.cost.cost_AccountFormulaStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cost.cost_AccountFormulaModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'cost_AccountAction_getAccountFormulaList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});