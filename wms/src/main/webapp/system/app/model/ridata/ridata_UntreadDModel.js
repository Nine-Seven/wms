/**
 * 返配手建单D
 * 创建人:Jun
 */
 Ext.define('cms.model.ridata.ridata_UntreadDModel', {
    extend: 'Ext.data.Model',
    fields: [
    	{name:'warehouseNo'},
    	{name:'ownerNo'},
    	{name:'untreadNo'},
    	{name:'poId'},
    	{name:'supplierNo'},
    	{name:'articleNo'},
    	{name:'packingQty'},
    	{name:'untreadQty'},
    	{name:'checkQty'},
    	{name:'unitCost'},
    	{name:'checkName'},
    	{name:'checkDate'},
    	{name:'status'},
    	{name:'lotNo'},
    	{name:'quality'},
    	{name:'produceDate'},
    	{name:'expireDate'},
    	
    	{name:'articleName'},
    	{name:'packingQty'},
    	{name:'barcode'},
    	
    	{name:'pobox'},
		{name:'popcs'},
		{name:'ownerArticleNo'},		
		
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
		{name:'spec'}
		
		
		
    ],
    idProperty:'warehouseNo,ownerNo,untreadNo,poId'
 });
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 