Ext.define('cms.model.bset.bset_GroupModel', {
    extend: 'Ext.data.Model',
    idProperty:'warehouseNo,printerGroupNo',
    fields: [
		{name:'warehouseNo'},
		{name:'printerGroupNo'},
		{name:'printerGroupName'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'}
    ]
});