/**
 * 模块名称:科目设置维护-优惠策略
 * 模块编码：B303
 * 创建：hcx 
 */
Ext.define('cms.model.cost.cost_AccountDiscountModel', {
    extend: 'Ext.data.Model',
    idProperty:'warehouseNo,ownerNo,accountNo,enterpriseNo,accountLadder',
    fields: [
        {name:'enterpriseNo'},
		{name:'warehouseNo'},
		{name:'ownerNo'},
		{name:'accountNo'},
		{name:'accountName'},
		{name:'accountType'},
		{name:'accountLadder'},
		{name:'discountFlag'},
		{name:'value1'},	
		{name:'value2'},
		{name:'billingProject'},
		{name:'remark'},	
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		{name:'accountLadderText'},
		{name:'accountTypeText'},
		{name:'discountFlagText'},	
		{name:'billingProjectText'}
    ]
});