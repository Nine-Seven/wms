Ext.define('cms.model.odata.odata_DivideDModel', {
    extend: 'Ext.data.Model',
    fields: [
    {name:'enterpriseNo'},
  	{name:'divideNo'},
	{name:'warehouseNo'},
	{name:'ownerNo'},
	{name:'divideId'},
	{name:'batchNo'},
	{name:'sourceNo'},
	{name:'operateDate'},
	{name:'custNo'},
	{name:'subCustNo'},
	{name:'expType'},
	{name:'expNo'},
	{name:'waveNo'},
	{name:'articleNo'},
	{name:'articleId'},
	{name:'packingQty'},
	{name:'articleQty'},
	{name:'realQty'},
	{name:'SCellNo'},
	{name:'SCellId'},
	{name:'SContainerNo'},
	{name:'DCellNo'},
	{name:'DCellId'},
	{name:'custContainerNo'},
	{name:'DContainerNo'},
	{name:'deliverArea'},
	{name:'status'},
	{name:'lineNo'},
	{name:'trunckCellNo'},
	{name:'checkChuteNo'},
	{name:'deliverObj'},
	{name:'outstockDate'},
	{name:'assignName'},
	{name:'divideName'},
	{name:'divideDate'},
	{name:'dpsCellNo'},
	{name:'ASorterChuteNo'},
	{name:'expDate'},
	{name:'shipperDeliverNo'},
	{name:'advanCellNO'},
	{name:'barcode'},
	{name:'articleName'},
	{name:'labelNo'},
	{name:'DlabelNo'},
	{name:'planWholenum'},
	{name:'planScatterednum'},
	{name:'realWholenum'},
	{name:'realScatterednum'},
	{name:'custName'},
	{name:'importBatchNo'},
	{name:'produceDate'},
	{name:'expireDate'},
	{name:'quality'},
	{name:'textQuality'},
	{name:'lotNo'},
	{name:'rsvBatch1'},
	{name:'rsvBatch2'},
	{name:'rsvBatch3'},
	{name:'rsvBatch4'},
	{name:'rsvBatch5'},
	{name:'rsvBatch6'},
	{name:'rsvBatch7'},
	{name:'rsvBatch8'},
	{name:'divideExcess'},
	{name:'qty'},
	{name:'ownerArticleNo'},
	//add by huangcx at 20160528
	{name:'qminOperatePacking'},
	{name:'unitPacking'},
	{name:'packingUnit'},
	{name:'packingUnitQmin'},
	{name:'Unit'},
	{name:'packingSpec'},
	{name:'packingSpecQmin'},
	{name:'spec'},
	{name:'planBox'},
	{name:'planDis'},
	{name:'planQmin'},
	{name:'realBox'},
	{name:'realDis'},
	{name:'realQmin'},
	//end add
	
	//add by huangcx at 20160704
	{name:'dpsCellNo'},
	{name:'articleQty'},
	{name:'realQty'},
	{name:'deliverObj'}
	//end add
	],
    idProperty:'divideNo,warehouseNo,ownerNo,divideId'
});























