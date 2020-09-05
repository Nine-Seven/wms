Ext.define('cms.model.rodata.rodata_OutstockDModel', {
    extend: 'Ext.data.Model',
    fields: [			
    {name:'outstockNo'},
	{name:'warehouseNo'},
	{name:'ownerNo'},
	{name:'sourceNo'},
	{name:'divideId'},
	{name:'SCellNo'},
	{name:'SCellId'},
	{name:'outstockCellNo'},
	{name:'poId'},
	{name:'articleNo'},
	{name:'articleId'},
	{name:'packingQty'},
	{name:'articleQty'},
	{name:'realQty'},
	{name:'SLabelNo'},
	{name:'DCellNo'},
	{name:'DCellId'},
	{name:'DContainerNo'},
	{name:'outstockCellId'},
	{name:'outstockContainerNo'},
	{name:'status'},
	{name:'assignName'},
	{name:'outstockName'},
	{name:'outstockDate'},
	{name:'stockType'},
	{name:'stockValue'},
	
	{name:'recedeNo'},
	{name:'recedeQty'},
	{name:'quality'},
	{name:'qualityText'},
	{name:'planWholenum'},
	{name:'planScatterednum'},
	{name:'realWholenum'},
	{name:'realScatterednum'},
	{name:'articleName'},
	{name:'barcode'},
	{name:'labelNo'},
	{name:'subLabelNo'},
	{name:'ownerArticleNo'},
	{name:'poNo'},
	{name:'outstockQty'},
	{name:'statusText'},
	{name:'unitCost'},
	{name:'qtyPrice'},
    {name:'rsvBatch1'},
	{name:'rsvBatch2'},
	{name:'rsvBatch3'},
	{name:'rsvBatch4'},
	{name:'rsvBatch5'},
	{name:'rsvBatch6'},
	{name:'rsvBatch7'},
	{name:'rsvBatch8'},
	{name:'lotNo'},
	{name:'produceDate'},
	{name:'expireDate'},
	{name:'scanQty'},
	{name:'unCheckQty'},
	{name:'checkQty'},
	{name:'packingUnit'},
	{name:'spec'},
	{name:'unScanQty'},
	{name:'qminOperatePacking'},
	{name:'packingUnit'},
	{name:'packingSpec'},
	{name:'planBox'},
	{name:'planDis'},
	{name:'planQmin'},
	{name:'realBox'},
	{name:'realDis'},
	{name:'realQmin'}
    ],
    idProperty:'outstockNo,warehouseNo,ownerNo,sourceNo,divideId,SCellNo,SCellId,outstockCellNo'
});