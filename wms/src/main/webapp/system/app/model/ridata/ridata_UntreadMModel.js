/**
 * 返配手建单M
 * 创建人:Jun
 */
 Ext.define('cms.model.ridata.ridata_UntreadMModel', {
    extend: 'Ext.data.Model',
    fields: [
    	{name:'warehouseNo'},
    	{name:'ownerNo'},
    	{name:'untreadType'},
    	{name:'untreadNo'},
    	{name:'poType'},
    	{name:'quality'},
    	{name:'poNo'},
    	{name:'classType'},
    	{name:'custNo'},
    	{name:'untreadDate'},
    	{name:'requestDate'},
    	{name:'status'},
    	{name:'createFlag'},
    	{name:'untreadRemark'},
    	{name:'untreadFlag'},
    	{name:'custAddressCode'},
    	{name:'stockType'},
    	{name:'stockValue'},
    	{name:'deptNo'},
    	{name:'sendFlag'},
    	{name:'rgstName'},
    	{name:'rgstDate'},
    	{name:'updtName'},
    	{name:'updtDate'},
    	
    	{name:'custName'},
    	{name:'statusText'},
    	{name:'SUntreadNo'},
    	{name:'serialNo'},
    	{name:'orgNo'},
    	{name:'qualityText'},
    	{name:'takeType'},
    	{name:'carPlanNo'}
    	
    ],
    idProperty:'warehouseNo,untreadNo'
 });