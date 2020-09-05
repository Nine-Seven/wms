
Ext.define('cms.model.acdata.acdata_Order_DModel', {
    extend: 'Ext.data.Model',
    idProperty:'orderNo,articleName,barcodeNo',
    fields: [
		{name:'orderNo'},
		{name:'articleName'},
		{name:'barcodeNo'},
		{name:'orderQty'},
		{name:'orderWt'},
		{name:'orderVl'},
		{name:'inQty'},	
		{name:'inWt'},
		{name:'inVl'},
		{name:'signQty'},	
		{name:'signWt'},
		{name:'signVl'},
		{name:'remark'},
		{name:'istockQty'},	
		{name:'istockWt'},
		{name:'istockVl'},
    ]
});