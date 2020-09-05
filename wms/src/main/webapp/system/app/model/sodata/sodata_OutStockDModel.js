
Ext.define('cms.model.sodata.sodata_OutStockDModel', {
    extend: 'Ext.data.Model',
    fields: [

    {name:'enterpriseNo'},    
    {name:'warehouseNo'},        
	{name:'ownerNo'},
	{name:'outstockNo'},
	{name:'divideId'},

	{name:'orgNo'},
	{name:'poId'},
	{name:'articleNo'},
	{name:'articleId'},
	{name:'packingQty'},
	{name:'articleQty'},
	{name:'realQty'},
	{name:'SCellNo'},
	{name:'SCellId'},
	{name:'DCellNo'},
	{name:'DCellId'},
	{name:'status'},
	{name:'assignName'},
	{name:'outstockName'},
	{name:'outstockDate'},
	

	{name:'planWholenum'},
	{name:'planScatterednum'},
	{name:'realWholenum'},
	{name:'realScatterednum'},
	{name:'articleName'},
	{name:'barcode'},
	
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
    idProperty:'enterpriseNo,warehouseNo,ownerNo,outstockNo,divideId'
});