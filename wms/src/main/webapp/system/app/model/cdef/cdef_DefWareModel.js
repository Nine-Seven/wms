Ext.define('cms.model.cdef.cdef_DefWareModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name:'warehouseNo'},
		{name:'wareNo'},
		{name:'wareName'},
		{name:'wareRemark'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		{name:'orgNo'}
    ],
    idProperty:'warehouseNo,wareNo'
});