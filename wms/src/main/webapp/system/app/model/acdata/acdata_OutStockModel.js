/**
 * 模块名称：分拨出库
 * 模块编码：C201
 * 创建：hkl
 */
Ext.define('cms.model.acdata.acdata_OutStockModel',{
	extend:'Ext.data.Model',
	idProperty:'warehouseNo, outstockNo',
	fields:[
	        {name:'warehouseNo'},
			{name:'outstockNo'},
			{name:'orderNo'},
			{name:'sourceNo'},
			{name:'custAlias'},
			{name:'ownerAlias'},
			{name:'status'},
			{name:'remark'},
			{name:'statusText'}
	       ]
});