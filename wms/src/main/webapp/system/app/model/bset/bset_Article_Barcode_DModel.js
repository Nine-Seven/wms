Ext.define('cms.model.bset.bset_Article_Barcode_DModel', {
    extend: 'Ext.data.Model',
    fields: [
		{name:'warehouseNo'},
		{name:'ownerNo'},
		{name:'serialNo'},
		{name:'articleNo'},
		{name:'articleName'},
		{name:'packingQty'},
		{name:'barcode'},
		{name:'status'},
		{name:'barcodeType'},
		{name:'updtName'},
		{name:'updtDate'}	
    ],
    idProperty:'serialNo,articleNo'
});