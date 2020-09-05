/**
 * 模块名称：仓租账单
 * 创建：lich 
 */
Ext.define('cms.store.bset.bill_FinancialStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bset.bill_FinancialModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bill_ResetBillAction_getFinList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});