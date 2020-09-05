/**
 * 线路主档
 * 创建人:Jun
 */
 Ext.define('cms.model.oset.oset_Line_CustModel', {
    extend: 'Ext.data.Model',
    fields: [
    	{name:'warehouseNo'},
    	{name:'lineNo'},
    	{name:'custNo'},
    	{name:'lineSeqNo'},
    	{name:'distance'},
    	{name:'charge'},
    	{name:'tollNoArray'},
    	{name:'speedLimit'},
    	{name:'rgstName'},
    	{name:'rgstDate'},
    	{name:'updtName'},
    	{name:'updtDate'},
    	
    	{name:'lineName'},
    	{name:'custName'}
    ],
    idProperty:'warehouseNo,custNo,lineNo'
 });