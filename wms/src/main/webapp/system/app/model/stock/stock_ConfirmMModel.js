
Ext.define('cms.model.stock.stock_ConfirmMModel', {
    extend: 'Ext.data.Model',
    fields: [
    {name:'enterpriseNo'},
	{name:'warehouseNo'},
	{name:'ownerNo'},
	{name:'confirmNo'},
	{name:'planNo'},
	{name:'confirmDate'},
	{name:'status'},
	{name:'sendFlag'},
	{name:'rgstName'},
	{name:'rgstDate'},
	{name:'updtName'},
	{name:'updtDate'},
	
	{name:'poNo'},
	{name:'planTypeText'},
	{name:'statusText'}
 	],			
    idProperty:'enterpriseNo,warehouseNo,ownerNo,confirmNo'
});