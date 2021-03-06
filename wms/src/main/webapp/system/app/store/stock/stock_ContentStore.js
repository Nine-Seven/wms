/**
 *获取储位商品库存信息
 *创建：lich
 */
Ext.define('cms.store.stock.stock_ContentStore',{
	extend:'Ext.data.Store',
	model:'cms.model.stock.stock_ContentModel',
	pageSize : appConfig.getPageSize(),
	storeId:'stock_ContentStore',
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'cdef_DefWareAction_getStock_Content.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});