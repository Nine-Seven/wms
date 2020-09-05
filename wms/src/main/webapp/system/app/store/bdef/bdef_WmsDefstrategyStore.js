/**
 * 模块名称：策略配置-头档
 * 模块编码：I101
 * @author hcx 20160610
 */
Ext.define('cms.store.bdef.bdef_WmsDefstrategyStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_WmsDefstrategyModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_WmsDefstrategyAction_getWmsDefstrategyList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});