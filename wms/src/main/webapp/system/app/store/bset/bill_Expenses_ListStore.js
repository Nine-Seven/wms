/**
 * 模块名称：消费清单bill_Account_DStore
 * 创建：lich 
 */
Ext.define('cms.store.bset.bill_Expenses_ListStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bset.bill_Expenses_ListModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bill_ResetBillAction_getExpList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});