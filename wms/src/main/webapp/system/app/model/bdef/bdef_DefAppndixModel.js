/**
 * 附件管理
 */
Ext.define('cms.model.bdef.bdef_DefAppndixModel', {
    extend: 'Ext.data.Model',
    fields: [
    	{name:'enterpriseNo'},
    	{name:'warehouseNo'},
    	{name:'ownerNo'},
    	{name:'fileName'},
    	{name:'type'},
    	{name:'filePath'},
    	{name:'relateOrderno'},
    	{name:'relateClass'},
    	{name:'rgstName'},
    	{name:'rgstDate'},
    	{name:'updtName'},
    	{name:'updtDate'},
    	{name:'meno'},
    	{name:'relateClassText'},
    	{name:'typeText'}
    ],
    idProperty:'enterpriseNo,warehouseNo,ownerNo,fileName'
});