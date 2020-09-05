Ext.define('cms.model.bset.bset_Printer_GroupModel', {
    extend: 'Ext.data.Model',
    idProperty:'warehouseNo,printerGroupNo,printerNo',
    fields: [
		{name:'warehouseNo'},
		{name:'printerGroupNo'},
		{name:'printerNo'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		
		{name:'printerName'},
		{name:'printerType'}
    ]
});