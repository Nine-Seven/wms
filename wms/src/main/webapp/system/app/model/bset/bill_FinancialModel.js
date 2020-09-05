/**
 * 模块名称：仓租账单Model
 * 创建：lich 
 */
Ext.define('cms.model.bset.bill_FinancialModel', {
    extend: 'Ext.data.Model',
    fields: [
			{name:'warehouseNo'},
			{name:'ownerNo'},
			{name:'billingMonth'},
			{name:'accountNo'},
			{name:'amount'},
			{name:'discountAmount'}
    ]
});