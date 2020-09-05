/**
 * 模块名称：计费取值方式维护Store
 * 模块编码：B1003
 * 创建：czh 
 */
Ext.define('cms.store.cost.cost_Billing_RuleStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cost.cost_BillingRuleModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'cost_BillingRuleAction_getBillingRuleList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});