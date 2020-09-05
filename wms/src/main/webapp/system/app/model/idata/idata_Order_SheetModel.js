Ext.define('cms.model.idata.idata_Order_SheetModel', {
    extend: 'Ext.data.Model',
    fields: [			
		{name:'warehouseNo'},
		{name:'orderSerial'},
		{name:'importNo'},
		{name:'poType'},
		{name:'cancelStatus'},
		{name:'skuQty'},
		{name:'palQty'},
		{name:'totalVolumn'},
		{name:'totalWeight'},
		{name:'totalPacking'},
		{name:'cancelSerial'},
		{name:'printFlag'},
		{name:'rgstDate'},
		{name:'rgstName'},
		
		{name:'poNo'},
		{name:'statusText'},
		{name:'dockNo'},
		{name:'dockText'}
    ],
    idProperty:'warehouseNo,orderSerial,importNo,poType,cancelStatus'
});