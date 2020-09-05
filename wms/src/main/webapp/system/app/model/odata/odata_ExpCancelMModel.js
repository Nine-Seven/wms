Ext.define('cms.model.odata.odata_ExpCancelMModel', {
    extend: 'Ext.data.Model',
    fields: [
    {name:'enterpriseNo'},
	{name:'warehouseNo'},
	{name:'cancelNo'},
	{name:'ownerNo'},
	{name:'expType'},
	{name:'expNo'},
	{name:'expDate'},
	{name:'custNo'},
	{name:'subCustNo'},
	{name:'operateDate'},
	{name:'status'},
	{name:'handleFlag'},
	{name:'sendFlag'},
	{name:'rgstName'},
	{name:'rgstDate'},
	{name:'updtName'},
	{name:'updtDate'},
	{name:'sourceType'},
	
	{name:'sourceexpNo'},
	{name:'statusText'},
	{name:'handleFlagText'},//处理方式
	{name:'suorceTypeText'}//病单来源
	],
    idProperty:'enterpriseNo,warehouseNo,cancelNo'
});