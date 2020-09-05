/**
 * 返配表单验收D
 * 创建人:Jun
 */
 Ext.define('cms.model.ridata.ridata_CheckDModel', {
    extend: 'Ext.data.Model',
    fields: [
    	{name:'warehouseNo'},
    	{name:'ownerNo'},
    	{name:'checkNo'},
    	{name:'rowId'},
    	{name:'supplierNo'},
    	{name:'ownerArticleNo'},
    	
    	{name:'articleNo'},
    	{name:'barcode'},
    	{name:'packingQty'},
    	{name:'lotNo'},
    	{name:'produceDate'},
    	{name:'expireDate'},
    	{name:'checkQty'},
    	{name:'checkWorker'},
    	{name:'checkStartDate'},
    	{name:'checkEndDate'},
    	{name:'quality'},
    	{name:'batchSerialNo'},
    	{name:'itemType'},
    	{name:'checkType'},
    	
    	{name:'articleName'},
    	{name:'pobox'},
    	{name:'popcs'},
    	{name:'checkbox'},
    	{name:'checkpcs'},
    	{name:'untreadQty'},
    	{name:'untreadNo'},
    	{name:'SUntreadNo'},
    	{name:'productDate1'},
    	{name:'expireDate1'},
    	
    	{name:'packingQty'},
    	{name:'expiryDays'},
    	{name:'printerGroupNo'},
    	{name:'dockNo'},
    	{name:'workerNo'},
    	{name:'checkTools'},
    	{name:'batchSerialNo'},
    	{name:'itemType'},
    	{name:'custNo'},
    	{name:'custName'},
    	{name:'qty'},
    	{name:'lotType'},
    	{name:'classType'},
    	{name:'qualityFlag'},
    	{name:'cellNo'},
    	{name:'qualityText'},
    	{name:'strPrintFlag'},
    	{name:'untreadType'},
    	
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
    idProperty:'warehouseNo,checkNo,ownerNo,rowId,supplierNo'
 });
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 