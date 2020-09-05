/**
 * 模块名称：商品信息
 * @author JUN
 */
Ext.define('cms.store.bdef.bdef_DefarticleStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_DefarticleModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_DefarticleAction_queryBdefDefarticleList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});