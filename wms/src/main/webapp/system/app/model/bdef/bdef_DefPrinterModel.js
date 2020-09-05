/**
 * 模块名称：客户主档
 * 模块编码：200004
 * 创建：Jun
 */
Ext.define('cms.model.bdef.bdef_DefPrinterModel',{
	extend:'Ext.data.Model',
	idProperty:'warehouseNo,printerNo',
	fields:[
		{name:'warehouseNo'},
		{name:'printerNo'},
		{name:'printerName'},
		{name:'paperTypeNo'},
		{name:'printerDriver'},
		{name:'printerPort'},
		{name:'status'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		
		{name:'statusText'},
		{name:'printertypeText'}
		]
});