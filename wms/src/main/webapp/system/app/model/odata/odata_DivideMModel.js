Ext.define('cms.model.odata.odata_DivideMModel', {
    extend: 'Ext.data.Model',
    fields: [
  	{name:'divideNo'},
	{name:'warehouseNo'},
	{name:'operateDate'},
	{name:'divideType'},
	{name:'batchNo'},
	{name:'status'},
	{name:'expDate'},
	{name:'rgstName'},
	{name:'rgstDate'},
	{name:'updtName'},
	{name:'updtDate'},
	
	{name:'statusText'},
	{name:'waveNo'},
	{name:'ownerArticleNo'}
	],
    idProperty:'divideNo,warehouseNo'
});