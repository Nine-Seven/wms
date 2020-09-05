/**
 * 模块名称：计费取值方式
 * 模块编码：B1003
 * 创建：czh 
 */
Ext.define('cms.model.cost.cost_BillingRuleModel', {
    extend: 'Ext.data.Model',
    idProperty:'',
    fields: [
        {name:'enterpriseNo'},
		{name:'billingType'},
		{name:'billingUnit'},
		{name:'billingUnitText'},
		{name:'ruleId'},
		{name:'strategyName'},
		{name:'useType'},
		{name:'statusText'},		
		{name:'standardSql'},
		{name:'nonstandardSql'},
		{name:'sqlOrder'},
		{name:'memo'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'}

    ]
});