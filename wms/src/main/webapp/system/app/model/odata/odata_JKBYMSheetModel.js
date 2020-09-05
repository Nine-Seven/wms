/**
 * 模块名称：BYM出货订单接口Model
 * 模块编码：B101
 * 创建：chensr 
 */
Ext.define('cms.model.odata.odata_JKBYMSheetModel', {
    extend: 'Ext.data.Model',
    idProperty:'sheetNo,warehouseNo,rgstName,rgstDate',
    fields: [
		{name:'sheetNo'},
		{name:'warehouseNo'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'dateText'},
		{name:'corpkey'},
		{name:'sheetType'}
    ]
});