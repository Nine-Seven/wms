Ext.define('cms.model.odata.odata_PackageMModel', {
    extend: 'Ext.data.Model',
    fields: [			
	{name:'warehouseNo'},
	{name:'ownerNo'},
	{name:'poNo'},
	{name:'poType'},
	{name:'expDate'},
	{name:'statusText'},
	{name:'remark'},
	{name:'rgstName'},
	{name:'rgstDate'},
	{name:'updtName'},
	{name:'updtDate'}
 	],
    idProperty:'enterpriseNo,warehouseNo,poNo'
});