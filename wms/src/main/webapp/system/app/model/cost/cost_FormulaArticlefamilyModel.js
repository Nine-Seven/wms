/**
 * 模块名称：计费公式管理Model-计费项目与商品群组关系
 * 模块编码：B103
 * 创建：hcx 
 */
Ext.define('cms.model.cost.cost_FormulaArticlefamilyModel', {
    extend: 'Ext.data.Model',
    idProperty:'enterpriseNo,warehouseNo,ownerNo,billingProject,familyNo',
    fields: [
        {name:'enterpriseNo'},
		{name:'warehouseNo'},
		{name:'ownerNo'},
		{name:'billingProject'},
		{name:'familyNo'},
		{name:'billingType'},	
		{name:'remark'},	
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		{name:'familyName'}
    ]
});