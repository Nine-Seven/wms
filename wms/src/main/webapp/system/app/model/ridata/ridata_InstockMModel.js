/**
 * 返配上架回单m
 * 创建人:Jun
 */
 Ext.define('cms.model.ridata.ridata_InstockMModel', {
    extend: 'Ext.data.Model',
    fields: [
    	{name:'warehouseNo'},
    	{name:'ownerNo'},
    	{name:'instockNo'},
    	{name:'operateType'},
    	{name:'autoLocateFlag'},
    	{name:'status'},
    	{name:'rgstName'},
    	{name:'rgstDate'},
    	{name:'updtName'},
    	{name:'updtDate'},
    	
    	{name:'statusText'},
    	{name:'waveNo'}
    ],
    idProperty:'instockNo,warehouseNo,ownerNo'
 });