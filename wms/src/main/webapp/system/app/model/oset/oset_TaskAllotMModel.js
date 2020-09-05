/**
 * 线路主档
 * 创建人:Jun
 */
 Ext.define('cms.model.oset.oset_TaskAllotMModel', {
    extend: 'Ext.data.Model',
    idProperty:'warehouseNo,taskId',
    fields: [
    	{name:'warehouseNo'},
    	{name:'taskId'},
    	{name:'taskName'},
    	{name:'defaultFlag'},
    	{name:'memo'},
    	{name:'rgstName'},
    	{name:'rgstDate'},
    	{name:'updtName'},
    	{name:'updtDate'},	
    	
    	{name:'defaultFlagText'}
    ]
  
});