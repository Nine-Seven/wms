/**
 * 创建人:JUN
 */
Ext.define('cms.model.odata.odata_LocateMModel', {
    extend: 'Ext.data.Model',
    fields: [
	{name:'warehouseNo'},
	{name:'waveNo'},
	{name:'ownerNo'},
	{name:'expType'},
	{name:'status'},
	{name:'locateName'},
	{name:'locateDate'},
	{name:'divideFlag'},
	{name:'specifyCell'},
	{name:'expDate'},
	{name:'taskBatch'},
	{name:'sourceType'},
	{name:'orderType'},
	{name:'taskAllotRuleID'}
    ],
    idProperty:'warehouseNo,waveNo'
});
