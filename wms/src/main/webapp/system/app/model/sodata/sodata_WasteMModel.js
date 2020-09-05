
Ext.define('cms.model.sodata.sodata_WasteMModel', {
    extend: 'Ext.data.Model',
    fields: [
    {name:'updtDate'},        
	{name:'updtName'},
	{name:'rgstDate'},
	{name:'rgstName'},
	{name:'orgNo'},
	{name:'stockValue'},
	{name:'stockType'},
	{name:'sendFlag'},
	{name:'createFlag'},
	{name:'status'},
	{name:'requestDate'},
	{name:'wasteDate'},
	{name:'poNo'},
	{name:'poType'},
	{name:'wasteType'},
	{name:'ownerNo'},

	{name:'enterpriseNo'},
	{name:'warehouseNo'},
	{name:'wasteNo'},
	{name:'statusText'},
	{name:'orgNoText'}
	
 	],			
    idProperty:'enterpriseNo,warehouseNo,wasteNo'
});