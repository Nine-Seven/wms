Ext.define('cms.model.rodata.rodata_OutstockMModel', {
    extend: 'Ext.data.Model',
    fields: [			
		{name:'outstockNo'},
		{name:'warehouseNo'},
		{name:'ownerNo'},
		{name:'operateDate'},
		{name:'operateType'},
		{name:'status'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		
		{name:'poNo'},
		{name:'statusText'},
		{name:'supplierNo'},
		{name:'supplierName'},
		{name:'recedeNo'},
		{name:'classType'}
		
		
 	],
    idProperty:'outstockNo,warehouseNo,ownerNo'
});