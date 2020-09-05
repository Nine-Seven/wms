/**
 * 模块名称：计费类型列表
 * 模块编码：B1003
 * 创建：czh 
 */
Ext.define('cms.model.cost.cost_BillingTypeModel', {
    extend: 'Ext.data.Model',
    idProperty:'',
    fields: [
        {name:'enterpriseNo'},
		{name:'billingType'},
		{name:'billingTypeText'},
		{name:'billingTypeName'},
		{name:'status'},
		{name:'statusText'},
		{name:'memo'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'}		

    ]
});