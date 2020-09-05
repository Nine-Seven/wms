/**
 * 模块名称：仓租费用明细bill_Account_DStore
 * 创建：lich 
 */
Ext.define('cms.store.bset.bill_Cost_ListStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bset.bill_Cost_ListModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bill_ResetBillAction_getCostList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});