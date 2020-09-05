/**
 * 模块名称：分拨入库
 * 模块编码：C101
 * 创建：hcx
 */
Ext.define('cms.model.acdata.acdata_Instock_DModel', {
    extend: 'Ext.data.Model',
    idProperty:'warehouseNo,instockNo,articleName',
    fields: [
		{name:'warehouseNo'},
		{name:'instockNo'},
		{name:'articleName'},
		{name:'barcodeNo'},
		{name:'inQty'},
		{name:'inWt'},
		{name:'inVl'},	
		{name:'istockQty'},
		{name:'istockWt'},
		{name:'istockVl'},	
		{name:'remark'},	
    ]
});