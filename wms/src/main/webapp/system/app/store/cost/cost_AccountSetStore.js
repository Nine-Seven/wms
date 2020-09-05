/**
 * 模块名称：科目代码维护
 * 模块编码：B303
 * 创建：hcx 
 */
Ext.define('cms.store.cost.cost_AccountSetStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cost.cost_AccountSetModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'cost_AccountAction_getCostAccountSetList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});