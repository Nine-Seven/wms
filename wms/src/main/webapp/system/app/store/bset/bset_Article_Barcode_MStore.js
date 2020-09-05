/**
 * 模块名称：条码信息采集
 * 模块编码：1O01
 * 创建：lich
 */
Ext.define('cms.store.bset.bset_Article_Barcode_MStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bset.bset_Article_Barcode_MModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
    proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bset_Article_BarcodeAction_getBset_Article_Barcode_MList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});