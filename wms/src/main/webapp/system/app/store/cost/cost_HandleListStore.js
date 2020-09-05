/**
 * 模块名称：手工录入消费清单
 * 创建：hcx 
 */
Ext.define('cms.store.cost.cost_HandleListStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cost.cost_HandleListModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'cost_HandleListAction_getCostHandleList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});