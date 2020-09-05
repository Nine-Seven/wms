Ext.define('cms.model.bset.bset_User_ListModel', {
    extend: 'Ext.data.Model',
    idProperty:'bset_User_ListModel',
    fields: [
        {name:'workerNo'},
		{name:'workerName'},
		{name:'status'},
		{name:'pwd'},
		{name:'ownerNo'},
		{name:'warehouseNo'},
		{name:'warehouseName'},
		{name:'sex'},
		{name:'tel'},
		{name:'address'},
		{name:'title'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		{name:'workerRfid'},
		{name:'deptName'},
		{name:'operateStatus'}
    ]
});