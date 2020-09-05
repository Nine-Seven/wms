/**
 * 模块名称：附件管理
 */                        
Ext.define('cms.store.bdef.bdef_DefAppendixStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_DefAppndixModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_DefAppendixAction_getDefAppendixList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});