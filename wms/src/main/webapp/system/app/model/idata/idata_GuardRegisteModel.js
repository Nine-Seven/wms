Ext.define('cms.model.idata.idata_GuardRegisteModel', {
    extend: 'Ext.data.Model',
    fields: [			
		{name:'enterpriseNo'},
		{name:'warehouseNo'},
		{name:'ownerNo'},
		{name:'orderSerial'},
		{name:'supplierNo'},

		{name:'dockNo'},
		{name:'registeDate'},
		{name:'carNo'},
		{name:'driverNo'},
		{name:'driverLicenseNo'},
		{name:'guestCardNumber'},
		{name:'beginUnloadDate'},
		{name:'endUnloadDate'},
		{name:'leaveDate'},
		{name:'status'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},

		{name:'dockText'},
		{name:'registeDateText'},
		{name:'beginUnloadDateText'},
		{name:'endUnloadDateText'},
		{name:'leaveDateText'}
		
		
    ],
    idProperty:'enterpriseNo,warehouseNo,ownerNo,orderSerial,supplierNo'
});