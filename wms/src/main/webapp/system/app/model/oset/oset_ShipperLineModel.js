 Ext.define('cms.model.oset.oset_ShipperLineModel', {
    extend: 'Ext.data.Model',
    fields: [
    	{name:'warehouseNo'},
    	{name:'shipperNo'},
    	{name:'lineNo'},
    	{name:'rgstName'},
    	{name:'rgstDate'},
    	{name:'updtName'},
    	{name:'updtDate'},
    	
    	{name:'shipperName'},
    	{name:'lineName'}
    ],
    idProperty:'warehouseNo,shipperNo,lineNo'
 });