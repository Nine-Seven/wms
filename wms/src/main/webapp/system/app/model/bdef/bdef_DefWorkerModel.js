Ext.define('cms.model.bdef.bdef_DefWorkerModel', {
    extend: 'Ext.data.Model',
    idProperty:'bdef_DefWorkerModel',
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
		{name:'authorityType'},
		{name:'workerRfid'},
		{name:'deptName'},
		{name:'operateStatus'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		{name:'statusText'},
		{name:'authorityTypeText'},		
		{name:'flag'},
		{name:'enterpriseNo'}
    ]
});