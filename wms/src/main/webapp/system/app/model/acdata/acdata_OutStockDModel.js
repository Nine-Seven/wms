/**
 * 模块名称：分拨出库
 * 模块编码：C201
 * 创建：hkl
 */
Ext.define('cms.model.acdata.acdata_OutStockDModel',{
	extend:'Ext.data.Model',
	idProperty:'warehouseNo, outstockNo,articleName',
	fields:[
	        {name:'warehouseNo'},
			{name:'outstockNo'},
			{name:'articleName'},
			{name:'barcodeNo'},
			{name:'inQty'},
			{name:'inWt'},
			{name:'inVl'},
			{name:'alreadyQty'},
			{name:'alreadyWt'},
			{name:'alreadyVl'},
			{name:'ostockQty'},
			{name:'ostockWt'},
			{name:'ostockVl'},
			{name:'remark'}
	       ]
});