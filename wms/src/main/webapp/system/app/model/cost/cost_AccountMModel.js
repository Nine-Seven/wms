/**
 * 模块名称：科目设置维护
 * 模块编码：B303
 * 创建：hcx 
 */
Ext.define('cms.model.cost.cost_AccountMModel', {
    extend: 'Ext.data.Model',
    idProperty:'accountGroupNo',
    fields: [
        {name:'enterpriseNo'},
		{name:'warehouseNo'},
		{name:'ownerNo'},
		{name:'accountGroupNo'},
		{name:'accountCycle'},
		{name:'balanceDay'},
		{name:'status'},
		{name:'remark'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		{name:'accountCycleText'},
		{name:'balanceDayText'},
		{name:'statusText'},
		{name:'rgstDateText'}
    ]
});