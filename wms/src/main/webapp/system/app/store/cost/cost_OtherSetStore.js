/**
 * 模块名称：杂项费用维护
 * 模块编码：B203
 * 创建：hcx 
 */
Ext.define('cms.store.cost.cost_OtherSetStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cost.cost_OtherSetModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'cost_OtherAction_getCostOtherSetList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});