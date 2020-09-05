Ext.define('cms.model.bset.bset_Article_Barcode_MModel', {
    extend: 'Ext.data.Model',
    fields: [
		{name:'warehouseNo'},
		{name:'ownerNo'},
		{name:'ownerName'},
		{name:'paperNo'},
		{name:'serialNo'},
		{name:'status'},
		{name:'statusText'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'}
    ],
    idProperty:'serialNo'
});