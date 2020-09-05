/**
 * 模块名称：商品条码
 * @author JUN
 */
Ext.define('cms.store.bdef.bdef_ArticleBarcodeStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_ArticleBarcodeModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_DefarticleAction_queryBdefArticleBarcodeList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});