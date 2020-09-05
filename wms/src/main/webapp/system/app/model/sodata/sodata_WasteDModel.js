
Ext.define('cms.model.sodata.sodata_WasteDModel', {
    extend: 'Ext.data.Model',
    fields: [
    {name:'enterpriseNo'},  
    {name:'warehouseNo'},        
	{name:'wasteNo'},
	{name:'poId'},
	{name:'ownerNo'},
	{name:'articleNo'},
	{name:'packingQty'},
	{name:'lotNo'},
	{name:'quality'},
	{name:'produceDate'},
	{name:'expireDate'},
	{name:'wasteQty'},
	{name:'realQty'},
	{name:'locateQty'},
	
	{name:'poBox'},
	{name:'poPcs'},
	{name:'realPcs'},
	{name:'ownerArticleNo'},
	{name:'barcode'},
	{name:'articleName'},
	
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
    idProperty:'enterpriseNo,wasteNo,poId,warehouseNo'
});