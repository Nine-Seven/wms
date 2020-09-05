/**
 * 模块名称：计费公式管理Model
 * 模块编码：B101
 * 创建：chensr 
 */
Ext.define('cms.model.bset.bill_FormulasetModel', {
    extend: 'Ext.data.Model',
    idProperty:'warehouseNo,ownerNo,billingProject,billingType',
    fields: [
		{name:'warehouseNo'},
		{name:'ownerNo'},
		{name:'billingProject'},
		{name:'projectName'},
		{name:'billingCycle'},
		{name:'billingFlag'},
		{name:'billingUnit'},	
		{name:'unitPrice'},
		{name:'appendCondition'},
		{name:'appendValue1'},
		{name:'appendValue2'},
		{name:'remark'},
		{name:'status'},
		{name:'statusText'},
		{name:'fixedValue'},
		{name:'valueFlag'},		
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		{name:'billingCycleText'},
		{name:'billingFlagText'},
		{name:'billingUnitText'},
		{name:'appendConditionText'},
		{name:'projectText'},
		{name:'billingType'},
		{name:'billingTypeText'},
		{name:'billingTypeText'},
		{name:'familyNo'},
		{name:'familyText'},
		{name:'endDate'},
		{name:'balanceDay'},
		{name:'endDateText'},
		{name:'balanceDayText'}
    ]
});