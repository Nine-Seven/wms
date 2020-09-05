Ext.define('cms.model.bset.bset_Printer_WorkstationModel', {
    extend: 'Ext.data.Model',
    idProperty:'warehouseNo,workstationNo',
    fields: [
		{name:'warehouseNo'},
		{name:'workstationNo'},
		{name:'workstationName'},
		{name:'printerGroupNo'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'}
    ]
});