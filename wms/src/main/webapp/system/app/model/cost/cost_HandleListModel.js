/**
 * 模块名称：手工录入消费清单Model
 * 创建：hcx
 */
Ext.define('cms.model.cost.cost_HandleListModel', {
    extend: 'Ext.data.Model',
    idProperty:'enterpriseNo,warehouseNo,ownerNo,serialNo',
    fields: [
 			{name:'enterpriseNo'},
			{name:'warehouseNo'},
			{name:'ownerNo'},
			{name:'serialNo'},
			{name:'billingProject'},
			{name:'amountDate'},
			{name:'value'},
			{name:'status'},
			{name:'costFlag'},
			{name:'sourceNo'},
			{name:'rgstName'},
			{name:'rgstDate'},
			{name:'updtName'},
			{name:'updtDate'},
			{name:'amountDateText'},
			{name:'costFlagText'},
			{name:'statusText'},
			{name:'projectText'},
			{name:'unitPrice'},
			{name:'billingType'},
			{name:'billingTypeText'},
			{name:'accountNoText'},
			{name:'ownerNoText'}
    ]
});