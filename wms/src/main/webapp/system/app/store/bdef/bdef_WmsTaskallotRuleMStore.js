/** 模块名称：任务切分规则配置
 * 模块编码：I201
 * 创建：huangb 20160608
 */
Ext.define('cms.store.bdef.bdef_WmsTaskallotRuleMStore',
	{
		extend:'Ext.data.Store',
		model:'cms.model.bdef.bdef_WmsTaskallotRuleMModel',
		pageSize : appConfig.getPageSize(),
		autoLoad:false,
	    proxy: {
	        type: 'ajax',
	        method: 'post',
	        url: 'bdef_WmsTaskallotRuleMAction_getMasterInfo',
	    	reader : {
	    		root : 'rootList',
	    		totalProperty : 'totalCount'
	    	}
	    }
    }
);