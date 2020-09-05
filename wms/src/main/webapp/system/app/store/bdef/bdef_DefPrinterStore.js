/**
 * 模块名称：打印机维护
 * 模块编码：200004
 * 创建：Jun
 */
Ext.define('cms.store.bdef.bdef_DefPrinterStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_DefPrinterModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_DefPrinterAction_getPrinterList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});