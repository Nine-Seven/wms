
Ext.define('cms.model.stock.stock_ConfirmDModel', {
    extend: 'Ext.data.Model',
    fields: [
    {name:'enterpriseNo'},
	{name:'warehouseNo'},
	{name:'ownerNo'},
	{name:'confirmNo'},
	{name:'rowId'},
	{name:'sourceNo'},
	{name:'articleNo'},
	{name:'articleId'},
	{name:'packingQty'},
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
	{name:'cellNo'},
	{name:'articleQty'},
	{name:'realQty'},
	{name:'stockType'},
	{name:'stockValue'},
	{name:'labelNo'},
	{name:'status'},
	{name:'rgstName'},
	{name:'rgstDate'},
	{name:'updtName'},
	{name:'updtDate'},
	
	{name:'unit'},
	{name:'ownerArticleNo'},
	{name:'articleName'},
	{name:'barcode'},
	{name:'pobox'},
	{name:'popcs'},
	{name:'realPobox'},
	{name:'realPopcs'},
	{name:'guarantee'},
	
	{name:'planBox'},		 
	{name:'planQmin'},
	{name:'planDis'},
	{name:'realBox'},
	{name:'realDis'},
	{name:'realQmin'},
	{name:'packingUnit'},
	{name:'packingUnitQmin'},
	{name:'unit'},
	{name:'packingSpec'},	
	{name:'qminOperatePacking'},
	{name:'unitPacking'},
	{name:'packingSpecQmin'},
	{name:'spec'},
	{name:'articleQty'}
	
	
	
	
	
	
	
	
	
 	],			
    idProperty:'enterpriseNo,warehouseNo,ownerNo,confirmNo,rowId'
});