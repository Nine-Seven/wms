Ext.define('cms.model.odata.odata_CheckLabelDModel', {
    extend: 'Ext.data.Model',
    fields: [
    {name:'enterpriseNo'},
	{name:'warehouseNo'},
	{name:'ownerNo'},
	{name:'checkNo'},
	{name:'rowId'},
	{name:'lableNo'},
	{name:'divideId'},
	{name:'containerId'},
	{name:'containerNo'},
	{name:'expNo'},
	{name:'expType'},
	{name:'expDate'},
	{name:'articleNo'},
	{name:'packingQty'},
	{name:'articleId'},	
	{name:'articleQty'},
	{name:'realQty'},
	{name:'status'},
	{name:'checkName'},
	{name:'checkDate'},
	
	{name:'uncheckQty'},
	{name:'barcode'},
	{name:'articleName'},
	
	{name:'produceDate'},
	{name:'expireDate'},
	{name:'quality'},
	{name:'lotNo'},
	{name:'importBatchNo'},
	{name:'rsvBatch1'},
	{name:'rsvBatch2'},
	{name:'rsvBatch3'},	
	{name:'rsvBatch4'},
	{name:'rsvBatch5'},	
	{name:'rsvBatch6'},
	{name:'rsvBatch7'},
	{name:'rsvBatch8'},
	{name:'textQuality'},
	{name:'qminOperatePacking'},
	{name:'qminOperateSpec'},
	{name:'qminOperateUnit'},
	{name:'advanceCellNo'},
	{name:'custNo'},
	{name:'checkChuteNo'},
	{name:'custName'},
	{name:'custAlias'},
	{name:'dLabelNo'},
	{name:'realQminQty'},
	
	{name:'packingUnit'},
	{name:'packingSpec'},
	{name:'unitPacking'},
	{name:'packingUnitQmin'},
	{name:'Unit'},
	{name:'packingSpecQmin'},
	{name:'spec'},
	{name:'planBox'},
	{name:'planDis'},
	{name:'planQmin'},
	{name:'realBox'},
	{name:'realDis'},
	{name:'realQmin'}
	
	
	
	],
    idProperty:'enterpriseNo,warehouseNo,ownerNo,rowId,checkNo'
});












