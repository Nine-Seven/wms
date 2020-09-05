Ext.define('cms.model.odata.odata_LocateShortLogModel', {
    extend: 'Ext.data.Model',
    fields: [
	{name:'enterpriseNo'},
	{name:'warehouseNo'},
	{name:'waveNo'},
	{name:'logSerial'},
	{name:'ownerNo'},
	{name:'expNo'},
	{name:'custNo'},
	{name:'subCustNo'},	
	{name:'articleNo'},
	{name:'articleId'},	
	{name:'packingQty'},
	{name:'cellNo'},
	{name:'stockQty'},
	{name:'locateQty'},
	{name:'operateType'},
	{name:'shortReason'},
	{name:'transGroupNo'}
    ],
    idProperty:'enterpriseNo,warehouseNo,waveNo'
});
