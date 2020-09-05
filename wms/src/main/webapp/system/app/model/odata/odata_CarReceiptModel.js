Ext.define('cms.model.odata.odata_CarReceiptModel', {
    extend: 'Ext.data.Model',
    fields: [
    {name:'enterpriseNo'},
  	{name:'warehouseNo'},
	{name:'ownerNo'},
	{name:'waveNo'},
	{name:'deliverNo'},
	{name:'custNo'},
	{name:'deliverBox'},
	{name:'backBox'},
  	{name:'carNo'},
	{name:'driverWorker'},
	{name:'status'},
	{name:'rgstName'},
	{name:'rgstDate'},
	{name:'updtName'},
	{name:'updtDate'},
	
	{name:'statusText'},
	{name:'ownerName'},
	{name:'custNoText'},
	{name:'custName'}
	],
    idProperty:'enterpriseNo,warehouseNo,ownerNo,waveNo,deliverNo'
});