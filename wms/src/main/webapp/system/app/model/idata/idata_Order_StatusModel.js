Ext.define('cms.model.idata.idata_Order_StatusModel', {
    extend: 'Ext.data.Model',
    fields: [			
		{name:'warehouseNo'},
		{name:'orderSerial'},
		{name:'startTime'},
		{name:'requestDate'},
		{name:'endTime'},
		{name:'sheetType'},
		{name:'dockNo'},
		{name:'lastBatch'},
		{name:'ownerNo'},
		{name:'supplierNo'},
		{name:'carsCount'},
		{name:'SImportNo'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		{name:'status'},
		
		{name:'supplierName'},
		{name:'dockText'},
		{name:'statusText'},
		{name:'suppliersText'},
		{name:'requestDateText'},
		
		{name:'articleItems'},
		{name:'volumn'},
		{name:'weight'},
		{name:'pkQty'},
		{name:'ownerNoText'}
		
    ],
    idProperty:'warehouseNo,orderSerial,startTime'
});