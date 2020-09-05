/**
 * 模块名称：策略配置-明细
 * 模块编码：I101
 * @author hcx 20160610
 */
Ext.define('cms.store.bdef.bdef_WmsDefstrategyDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_WmsDefstrategyDModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_WmsDefstrategyAction_getWmsDefstrategyDList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});