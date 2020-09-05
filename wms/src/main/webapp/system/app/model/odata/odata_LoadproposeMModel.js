/**
 * 装车建议单
 * 创建人:JUN
 */
Ext.define('cms.model.odata.odata_LoadproposeMModel', {
    extend: 'Ext.data.Model',
    fields: [
	{name:'warehouseNo'},
	{name:'loadproposeNo'},
	{name:'operateDate'},
	{name:'cartypeNo'},
	{name:'lineNo'},
	{name:'dockNo'},
	{name:'status'},
	{name:'carPlate'},
	{name:'sealNo'},
	{name:'custNo'},
	{name:'divideTruck'},
	{name:'sendFlag'},
	{name:'shipperNo'},
	{name:'realShipperNo'},
	{name:'expDate'},
	{name:'carPlanNo'},
	{name:'rgstName'},
	{name:'rgstDate'},
	{name:'updtName'},
	{name:'updtDate'},
	{name:'loadtype'},
	
	{name:'custName'},
	{name:'labelNo'},
	{name:'currArea'},
	{name:'deliverObj'},
	{name:'loadOrder'},
	{name:'carPlanNo'},
	{name:'deliverBox'},
	{name:'carNo'}
    ],
    idProperty:'warehouseNo,loadproposeNo'
});
