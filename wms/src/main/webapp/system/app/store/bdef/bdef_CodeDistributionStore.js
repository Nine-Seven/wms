/**
 * 模块名称：色码
 */
Ext.define('cms.store.bdef.bdef_CodeDistributionStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_ColourCodeModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_ColourCodeAction_getCodeDistribution',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});