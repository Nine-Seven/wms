/**
 * 模块名称：分拨入库
 * 模块编码：C101
 * 创建：hcx
 */
Ext.define('cms.store.acdata.acdata_Order_MStore',{
	extend:'Ext.data.Store',
	model:'cms.model.acdata.acdata_Order_MModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'acdata_OrderAction_getOrderMList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});