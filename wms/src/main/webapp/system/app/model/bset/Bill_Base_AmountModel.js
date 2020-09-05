/**
 * 模块名称：消费清单Model
 * 创建：lich 
 */
Ext.define('cms.model.bset.Bill_Base_AmountModel', {
    extend: 'Ext.data.Model',
    idProperty:'warehouseNo,ownerNo,serialNo,enterpriseNo,billingProject',
    fields: [
 			{name:'enterpriseNo'},
			{name:'warehouseNo'},
			{name:'ownerNo'},
			{name:'serialNo'},
			{name:'billingProject'},
			{name:'amountDate'},
			{name:'value'},
			{name:'flag'},
			{name:'rgstName'},
			{name:'rgstDate'},
			{name:'updtName'},
			{name:'updtDate'},
			{name:'amountDateText'},
			{name:'flagText'},
			{name:'projectText'},
			{name:'fixedValue'},
			{name:'billingCycle'},
			{name:'billingCycleText'},
			{name:'unitPrice'}
    ]
});