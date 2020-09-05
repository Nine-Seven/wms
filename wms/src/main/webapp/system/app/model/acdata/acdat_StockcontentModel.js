/**
 * 模块名称：库存
 * 创建：hkl
 */
Ext.define('cms.model.acdata.acdat_StockcontentModel', {
    extend: 'Ext.data.Model',
    idProperty:'warehouseNo,orderNo,sourceNo,articleName',
    fields: [
        {name:'warehouseNo'},
		{name:'orderNo'},
		{name:'sourceNo'},
		{name:'articleName'},
		{name:'barcodeNo'},
		{name:'inQty'},
		{name:'inWt'},
		{name:'inVl'},
		{name:'qty'},	
		{name:'wt'},
		{name:'vl'},
		{name:'stime'},	
		{name:'alreadyQty'},
		{name:'alreadyWt'},
		{name:'alreadyVl'},
		{name:'ostockQty'},
		{name:'ostockWt'},
		{name:'ostockVl'},
		
		
    ]
});