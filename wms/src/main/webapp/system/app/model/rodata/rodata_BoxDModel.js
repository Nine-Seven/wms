/**
 * 退厂箱明细
 */
Ext.define('cms.model.rodata.rodata_BoxDModel',{
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
		
		{name: 'rowId'},
		{name: 'outstockNo'},
		{name: 'poId'},
		{name: 'articleNo'},
		{name: 'articleName'},
		{name: 'barcode'},
		
		{name: 'articleId'},
		{name: 'packingQty'},
		{name: 'articleQty'},
		{name: 'subLabelNo'},
	
		{name: 'sLabelNo'},
		{name: 'dLabelNo'},
		{name: 'produceDate'},
		{name: 'expireDate'},
		{name: 'quality'},
		{name: 'lotNo'},
		
		{name: 'qminOperatePacking'},
		{name: 'unitPacking'},
		{name: 'packingUnit'},
		{name: 'packingUnitQmin'},
		{name: 'Unit'},
		{name: 'packingSpec'},
		{name:'packingSpecQmin'},
		{name: 'spec'},
		{name: 'planBox'},
		{name: 'planDis'},
		{name: 'planQmin'},
		{name: 'realBox'},
		{name: 'realDis'},
		{name:'realQmin'}
		],
	idProperty:'enterpriseNo,warehouseNo,recedeNo,labelNo'
});