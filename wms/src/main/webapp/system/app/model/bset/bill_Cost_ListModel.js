/**
 * 模块名称：仓租费用明细Model
 * 创建：lich 
 */
Ext.define('cms.model.bset.bill_Cost_ListModel', {
    extend: 'Ext.data.Model',
    idProperty:'serialNo',
    fields: [{name:'enterpriseNo'},
			{name:'warehouseNo'},
			{name:'ownerNo'},
			{name:'billingNo'},
			{name:'billingProject'},
			{name:'billingUnit'},
			{name:'unitPrice'},
			{name:'billingDate'},
			{name:'amount'},
			{name:'flag'},
			{name:'serialNo'},
			{name:'appendCondition'},
			{name:'appendValue1'},
			{name:'appendValue2'},
			{name:'qty'},
			{name:'value'},
			{name:'status'},
			{name:'statusText'}
    ]
});