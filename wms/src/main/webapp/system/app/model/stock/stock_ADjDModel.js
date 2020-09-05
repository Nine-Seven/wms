
Ext.define('cms.model.stock.stock_ADjDModel', {
    extend: 'Ext.data.Model',
    fields: [
    {name:'enterpriseNo'},        
	{name:'adjNo'},
	{name:'rowId'},
	{name:'warehouseNo'},
	{name:'ownerNo'},
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
	{name:'planQty'},
	{name:'realQty'},
	{name:'adjDes'},
	{name:'barcode'},
	{name:'stockType'},
	{name:'stockValue'},
	{name:'labelNo'},
	{name:'status'},
	
	{name:'articleName'},
	{name:'ownerArticleNo'},
	{name:'packingUnit'},
	{name:'guarantee'},
	{name:'lotType'},
	
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
    idProperty:'enterpriseNo,adjNo,rowId,warehouseNo'
});