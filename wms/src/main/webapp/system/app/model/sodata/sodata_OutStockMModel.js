
Ext.define('cms.model.sodata.sodata_OutStockMModel', {
    extend: 'Ext.data.Model',
    fields: [
             

	{name:'enterpriseNo'},
	{name:'warehouseNo'},
	{name:'ownerNo'},
    {name:'outstockNo'},
	{name:'operateDate'},
	{name:'status'},
	{name:'rgstName'},
	{name:'rgstDate'},
	{name:'updtName'},
	{name:'updtDate'},
	{name:'statusText'},
	{name:'poNo'},
	{name:'orgNoText'}
	
	
	
	
 	],			
    idProperty:'enterpriseNo,warehouseNo,ownerNo,outstockNo'
});