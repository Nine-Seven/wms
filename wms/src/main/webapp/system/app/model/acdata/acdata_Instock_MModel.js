/**
 * 模块名称：分拨入库
 * 模块编码：C101
 * 创建：hcx
 */
Ext.define('cms.model.acdata.acdata_Instock_MModel', {
    extend: 'Ext.data.Model',
    idProperty:'warehouseNo,instockNo',
    fields: [
		{name:'warehouseNo'},
		{name:'instockNo'},
		{name:'orderNo'},
		{name:'sourceNo'},
		{name:'ownerAlias'},
		{name:'custAlias'},
		{name:'status'},	
		{name:'statusText'},
		{name:'remark'},
		{name:'rgstName'},	
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},		
    ]
});