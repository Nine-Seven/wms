/**
 * 模块名称：出货定位策略配置-头档
 * 模块编码：3910
 * @author liucl 20160727
 */
Ext.define('cms.store.odata.odata_outLocateStrategyMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.odata.odata_WmsOutlocateStrategyMModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'Odata_outLocateStrategyAction_getOutLocateStrategyMList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});