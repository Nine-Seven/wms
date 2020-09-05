/**
 * 模块名称：杂项费用维护
 * 模块编码：B203
 * 创建：hcx 
 */
Ext.define('cms.store.cost.cost_ReturnAmoutStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cost.cost_ReturnAmountModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'cost_ReceivingAction_getReturnAmountList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});