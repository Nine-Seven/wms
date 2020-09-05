/**
 * 返配上架回单D
 * 创建人:Jun
 */
 Ext.define('cms.model.ridata.ridata_InstockDModel', {
    extend: 'Ext.data.Model',
    fields: [
    	{name:'warehouseNo'},
    	{name:'ownerNo'},
    	{name:'instockNo'},
    	{name:'instockId'},
    	{name:'cellNo'},
    	{name:'cellId'},
    	{name:'containerNo'},
    	{name:'articleNo'},
    	{name:'articleId'},
    	{name:'packingQty'},
    	{name:'destCellNo'},
    	{name:'destCellId'},
    	{name:'articleQty'},
    	{name:'realCellNo'},
    	{name:'realContainerNo'},
    	{name:'realQty'},
    	{name:'sourceNo'},
    	{name:'status'},
    	{name:'instockName'},
    	{name:'instockDate'},
    	{name:'assignName'},
    	{name:'labelNo'},
    	{name:'scanLabelNo'},
    	{name:'businessType'},
    	
    	{name:'articleName'},
    	{name:'barcode'},
    	{name:'produceDate'},
    	{name:'userId'},
    	{name:'lotNo'},
    	{name:'qualityText'},
    	
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
    idProperty:'instockNo,warehouseNo,ownerNo,instockId'
 });