/**
 * 模块名称：仓租账单
 * 创建：hcx 
 */
Ext.define('cms.store.cost.cost_FinancialStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cost.cost_FinancialModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'cost_FinancialAction_getFinList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});