/**
 *库存调账确认
 *创建：hcx
 */
Ext.define('cms.store.stock.stock_ConfirmDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.stock.stock_ConfirmDModel',
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url:'stock_ConfirmAction_getStockConfirmDList',
		reader:{
			type:'json',
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});