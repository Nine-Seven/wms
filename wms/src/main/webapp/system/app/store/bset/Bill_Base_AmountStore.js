/**
 * 模块名称：消费清单Bill_Base_AmountStore
 * 创建：hcx 
 */
Ext.define('cms.store.bset.Bill_Base_AmountStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bset.Bill_Base_AmountModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bill_BaseAmountAction_getBillBaseAmountList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});