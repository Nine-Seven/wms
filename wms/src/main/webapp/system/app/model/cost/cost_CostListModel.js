/**
 * 模块名称：未出账清单查询
 * 模块编码：B403
 * 创建：hcx 
 */
Ext.define('cms.model.cost.cost_CostListModel', {
    extend: 'Ext.data.Model',
    idProperty:'',
    fields: [
        {name:'enterpriseNo'},
		{name:'warehouseNo'},
		{name:'ownerNo'},
		{name:'billingProject'},
		{name:'billingType'},
		{name:'buildDate'},
		{name:'serialNo'},
		{name:'beginDate'},
		{name:'endDate'},	
		{name:'qty'},
		{name:'amount'},
		{name:'favourableAmount'},
		{name:'status'},
		{name:'flag'},
		{name:'accountNo'},
		{name:'checkNo'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		
		{name:'ownerNoText'},
		{name:'statusText'},
		{name:'flagText'},
		{name:'billingProjectText'},
		{name:'buildDateText'},
		{name:'beginDateText'},
		{name:'endDateText'},
		{name:'days'},
		{name:'area'},
		{name:'tray'},
		{name:'qty2'},
		{name:'volume'},
		{name:'weigth'},
		{name:'cell'},
		{name:'otherCost1'},
		{name:'otherCost2'},
		{name:'otherCost3'},
		{name:'otherCost4'},
		{name:'otherCost5'},
		{name:'total'},
		{name:'operate'},
		{name:'createFlag'},
		{name:'costPrice'},
		{name:'accountNoText'},
		{name:'unitPrice'},
		{name:'costFlag'},
		{name:'costFlagText'}

    ]
});