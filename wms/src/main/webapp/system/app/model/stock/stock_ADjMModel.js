
Ext.define('cms.model.stock.stock_ADJMModel', {
    extend: 'Ext.data.Model',
    fields: [
    {name:'enterpriseNo'},
	{name:'adjNo'},
	{name:'warehouseNo'},
	{name:'ownerNo'},
	{name:'adjType'},
	{name:'poNo'},
	{name:'adjDate'},
	{name:'status'},
	{name:'createFlag'},
	{name:'rgstName'},
	{name:'rgstDate'},
	{name:'updtName'},
	{name:'updtDate'},
	{name:'sendFlag'}
 	],			
    idProperty:'enterpriseNo,adjNo,warehouseNo,ownerNo'
});