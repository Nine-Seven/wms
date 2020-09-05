/**
 * 返配表单验收Pal
 * 创建人:Jun
 */
 Ext.define('cms.model.ridata.ridata_CheckPalModel', {
    extend: 'Ext.data.Model',
    fields: [
    	{name:'warehouseNo'},
    	{name:'ownerNo'},
    	{name:'labelNo'},
    	{name:'checkNo'},
    	{name:'checkRowId'},
    	{name:'articleNo'},
    	{name:'packingQty'},
    	{name:'checkQty'},
    	{name:'status'},
    	{name:'printerGroupNo'},
    	{name:'dockNo'},
    	{name:'containerNo'},
    	{name:'fixpalFlag'},
    	{name:'seasonFlag'},
    	{name:'quality'},
    	{name:'scanLabelNo'},
    	{name:'businessType'},
    	{name:'cellNo'},
    	{name:'firstcheckLabelNo'},
    	{name:'ownerArticleNo'},
    	{name:'checkBox'},
    	{name:'checkDis'},
    	
    	{name:'articleName'},
    	//{name:'spec'},
    	{name:'packingQty'},
    	//{name:'packingUnit'},
    	{name:'barCode'},
    	
    	{name:'produceDate'},
    	{name:'expireDate'},
    	
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
    idProperty:'labelNo,warehouseNo,ownerNo,checkNo,checkRowId,containerNo,cellNo,firstcheckLabelNo'
 });
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 