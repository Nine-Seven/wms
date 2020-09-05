/**
 * 模块名称：消费清单bill_Account_DStore
 * 创建：lich 
 */
Ext.define('cms.store.cost.cost_ExpensesListStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cost.cost_ExpensesListModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'cost_ExpensesListAction_getExpList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});