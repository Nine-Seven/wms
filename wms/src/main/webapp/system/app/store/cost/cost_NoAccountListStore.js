/**
 * 模块名称：未出账清单查询
 * 模块编码：B403
 * 创建：hcx 
 */
Ext.define('cms.store.cost.cost_NoAccountListStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cost.cost_CostListModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'cost_NoAccountListAction_getNoAccountList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});