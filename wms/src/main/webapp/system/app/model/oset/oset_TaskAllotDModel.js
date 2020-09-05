/**
 * 切单规则明细
 * 创建人:chensr
 */
 Ext.define('cms.model.oset.oset_TaskAllotDModel', {
    extend: 'Ext.data.Model',
    idProperty:'warehouseNo,taskId,outstockType,operateType,sourceType',
    fields: [
    	{name:'warehouseNo'},
    	{name:'taskId'},
    	{name:'outstockType'},
    	{name:'operateType'},
    	{name:'sourceType'},
    	{name:'allotRule'},
    	{name:'boxFlag'},
    	{name:'paraValue'},  	
    	{name:'taskType'},
    	{name:'memo'},
    	{name:'rgstName'},
    	{name:'rgstDate'},
    	{name:'updtName'},
    	{name:'updtDate'},	
    	
    	{name:'allotRuleText'},
    	{name:'boxFalgText'},
    	{name:'operateTypeText'},
    	{name:'outstockTypeText'},
    	{name:'taskTypeText'},
    	{name:'sourceTypeText'}	
    ]
});