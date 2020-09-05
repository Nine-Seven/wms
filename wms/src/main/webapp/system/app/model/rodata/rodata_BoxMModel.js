/**
 * 退厂箱头档
 */
Ext.define('cms.model.rodata.rodata_BoxMModel',{
	extend:'Ext.data.Model',
	fields:[
		{name: 'enterpriseNo'},
		{name: 'warehouseNo'},
		{name: 'ownerNo'},
		{name: 'labelNo'},
		{name: 'status'},
		{name: 'recedeNo'},
		{name: 'rgstName'},
		{name: 'rgstDate'},
		{name: 'updtName'},
		{name: 'updtDate'},
		{name: 'ownerCellNo'}
		
	
		],
	idProperty:'enterpriseNo,warehouseNo,ownerNo,labelNo'
});