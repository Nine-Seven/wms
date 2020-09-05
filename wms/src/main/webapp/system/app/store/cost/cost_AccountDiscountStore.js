/**
 * 模块名称：科目设置维护-优惠策略
 * 模块编码：B303
 * 创建：hcx 
 */
Ext.define('cms.store.cost.cost_AccountDiscountStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cost.cost_AccountDiscountModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
		async:false,
        url: 'cost_AccountAction_getAccountDiscountList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});