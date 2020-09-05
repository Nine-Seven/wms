/**
 * 返配表单验收M
 * 创建人:Jun
 */
 Ext.define('cms.model.ridata.ridata_CheckMModel', {
    extend: 'Ext.data.Model',
    fields: [
    	{name:'warehouseNo'},
    	{name:'ownerNo'},
    	{name:'checkNo'},
    	{name:'SCheckNo'},
    	{name:'untreadType'},
    	{name:'untreadNo'},
    	{name:'dockNo'},
    	{name:'checkWorker'},
    	{name:'status'},
    	{name:'checkStartDate'},
    	{name:'checkEndDate'},
    	{name:'printerGroupNo'},
    	{name:'checkTools'},
    	{name:'sendFlag'},
    	{name:'printTimes'},
    	{name:'rgstName'},
    	{name:'rgstDate'},
    	{name:'updtName'},
    	{name:'updtDate'},
    	
    	{name:'custNo'},
    	{name:'custName'},
    	{name:'statusText'},
    	{name:'SUntreadNo'},
    	{name:'workerName'},
    	{name:'poNo'},
    	{name:'strPrintFlag'},
    	{name:'classType'},
    	{name:'quality'},
    	
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
    idProperty:'warehouseNo,checkNo,ownerNo'
 });
 
 
 
 
 
 
 
 
 
 
 