/**
 * fcdata_CheckMModel
 * @author JUN
 */
Ext.define('cms.model.fcdata.fcdata_CheckMModel', {
    extend: 'Ext.data.Model',
    fields: [
         	{name:'warehouseNo'},
        	{name:'checkNo'},
        	{name:'ownerNo'},
        	{name:'planNo'},
        	{name:'requestNo'},
        	{name:'checkDate'},
        	{name:'requestDate'},
        	{name:'assignNo'},
        	{name:'realNo'},
        	{name:'status'},
        	{name:'checkRemark'},
        	{name:'serialNo'},
        	{name:'fcdataType'},
        	{name:'checkType'},
        	{name:'rgstName'},
        	{name:'rgstDate'},
        	{name:'updtName'},
        	{name:'updtDate'},
        	
        	{name:'statusText'},
        	{name:'checktypeText'},
			{name:'fcdatatypeText'},
			{name:'ware'}
    ],
    idProperty:'warehouseNo,checkNo'
});