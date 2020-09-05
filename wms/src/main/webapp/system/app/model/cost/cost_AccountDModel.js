/**
 * 模块名称：科目设置维护
 * 模块编码：B303
 * 创建：hcx 
 */
Ext.define('cms.model.cost.cost_AccountDModel', {
    extend: 'Ext.data.Model',
    idProperty:'enterpriseNo,warehouseNo,ownerNo,accountNo,accountGroupNo',
    fields: [
        {name:'enterpriseNo'},    
		{name:'warehouseNo'},
		{name:'ownerNo'},
		{name:'accountGroupNo'},
		{name:'accountNo'},
		{name:'accountType'},
		{name:'accountTypeText'},
		
		{name:'accountId'},
		{name:'accountName'},
		{name:'otherCost1'},
		{name:'otherCost2'},
		{name:'otherCost3'},
		{name:'otherCost4'},
		{name:'otherCost5'},
		{name:'remark'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		
		{name:'accountCycle'},
		{name:'balanceDay'},
		{name:'status'},
		{name:'remark_m'},
		{name:'accountCycleText'},
		{name:'balanceDayText'},
		{name:'statusText'},
		{name:'ownerNoText'}
    ]
});