/**
 * 返配表单验收D
 * 创建人:Jun
 */
 Ext.define('cms.model.ridata.ridata_BatchModel', {
    extend: 'Ext.data.Model',
    fields: [
    	{name:'warehouseNo'},
    	{name:'batchNo'},
    	{name:'operateDate'},
    	{name:'startTime'},
    	{name:'endTime'},
    	{name:'rgstName'},
    	{name:'rgstDate'},
    	{name:'status'},
    	{name:'currRecord'},
    	{name:'batchType'},
    	{name:'warehouseText'},
    	{name:'statusText'},
    	{name:'operateDateText'},
    	{name:'dropValue'},
    	{name:'value'}
    	
    ],
    idProperty:'warehouseNo,batchNo,operateDate'
 });