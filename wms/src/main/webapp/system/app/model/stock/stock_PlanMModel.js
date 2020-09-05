
Ext.define('cms.model.stock.stock_PlanMModel', {
    extend: 'Ext.data.Model',
    fields: [
    {name:'enterpriseNo'},
	{name:'warehouseNo'},
	{name:'ownerNo'},
	{name:'planNo'},
	{name:'planType'},
	{name:'poNo'},
	{name:'planDate'},
	{name:'status'},
	{name:'createFlag'},
	{name:'sendFlag'},
	{name:'orgNo'},
	{name:'rgstName'},
	{name:'rgstDate'},
	{name:'updtName'},
	{name:'updtDate'},
	{name:'remark'},
	{name:'planTypeText'},
	{name:'orgNoText'},
	{name:'statusText'}
 	],			
    idProperty:'enterpriseNo,warehouseNo,ownerNo,planNo'
});