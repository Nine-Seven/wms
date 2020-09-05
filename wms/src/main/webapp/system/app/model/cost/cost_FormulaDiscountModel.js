/**
 * 模块名称：计费公式管理Model-优惠阶梯
 * 模块编码：B103
 * 创建：hcx 
 */
Ext.define('cms.model.cost.cost_FormulaDiscountModel', {
    extend: 'Ext.data.Model',
    idProperty:'enterpriseNo,warehouseNo,ownerNo,billingProject,ladder',
    fields: [
        {name:'enterpriseNo'},
		{name:'warehouseNo'},
		{name:'ownerNo'},
		{name:'billingProject'},
		{name:'ladder'},
		{name:'billingType'},
		{name:'discountFlag'},	
		{name:'value1'},
		{name:'value2'},
		{name:'remark'},	
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		{name:'discountFlagText'}
    ]
});