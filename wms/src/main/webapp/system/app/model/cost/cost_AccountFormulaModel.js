/**
 * 模块名称：科目设置维护-科目与项目关系
 * 模块编码：B303
 * 创建：hcx 
 */
Ext.define('cms.model.cost.cost_AccountFormulaModel', {
    extend: 'Ext.data.Model',
    idProperty:'enterpriseNo,warehouseNo,ownerNo,accountNo,billingProject',
    fields: [
        {name:'enterpriseNo'},
		{name:'warehouseNo'},
		{name:'ownerNo'},
		{name:'accountNo'},
		{name:'billingProject'},
		{name:'projectName'},
		{name:'accountName'},
		{name:'billingTypeText'},
		{name:'remark'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'}		
    ]
});