/**
 * 模块名称：计费取值方式维护-计费类型列表
 * 模块编码：B1003
 * 创建：czh 
 */
Ext.define('cms.store.cost.cost_BillingRuleTypeListStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cost.cost_BillingTypeModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'cost_BillingRuleAction_getRuleTypeList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});