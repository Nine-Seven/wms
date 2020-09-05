/**
 * 退货回单明细
 * zhouhuan
 */
Ext.define('cms.model.rodata.rodata_DeliverDModel',{
	extend:'Ext.data.Model',
	fields:[
		{name: 'deliverNo'},
		{name: 'warehouseNo'},
		{name: 'ownerNo'},
		{name: 'recedeNo'},
		{name: 'poId'},
		{name: 'articleNo'},
		{name: 'articleId'},
		{name: 'cellNo'},
		{name: 'cellId'},
		{name: 'barcode'},
		{name: 'packingQty'},
		{name: 'lotNo'},
		{name: 'produceDate'},
		{name: 'expireDate'},
		{name: 'quality'},
		{name: 'importBatchNo'},
		{name: 'articleQty'},
		{name: 'realQty'},
		{name: 'recedeName'},
		{name: 'recedeDate'},
		{name: 'itemType'},
		{name: 'batchSerialNo'},
		
		{name: 'wholenum'},
		{name: 'scatterednum'},
		{name: 'articleName'},
		{name: 'SCellNo'},
		{name: 'DCellNo'},
		
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
	idProperty:'deliverNo,warehouseNo,ownerNo,recedeNo,poId,articleNo,articleId,cellNo,cellId'
});