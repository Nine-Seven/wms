/**
 * 线路主档
 * 创建人:Jun
 */
 Ext.define('cms.model.oset.oset_DefLineModel', {
    extend: 'Ext.data.Model',
    fields: [
    	{name:'warehouseNo'},
    	{name:'deliverType'},
    	{name:'transportType'},
    	{name:'lineNo'},
    	{name:'lineName'},
    	{name:'lineFname'},
    	{name:'lineRemark'},
    	{name:'rgstName'},
    	{name:'rgstDate'},
    	{name:'updtName'},
    	{name:'updtDate'},
    	
    	{name:'delivertypeText'},
    	{name:'transporttypeText'}
    ],
    idProperty:'warehouseNo,lineNo'
});