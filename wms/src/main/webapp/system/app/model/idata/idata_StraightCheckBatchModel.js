/**
 * 直通验收批次管理
 * 创建人:hcx
 */
 Ext.define('cms.model.idata.idata_StraightCheckBatchModel', {
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