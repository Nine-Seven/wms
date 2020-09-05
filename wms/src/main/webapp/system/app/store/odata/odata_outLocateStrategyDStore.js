/**
 * 模块名称：出货定位策略配置-明细
 * 模块编码：3910
 * @author liucl 20160727
 */
Ext.define('cms.store.odata.odata_outLocateStrategyDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.odata.odata_WmsOutlocateStrategyDModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'Odata_outLocateStrategyAction_getOutLocateStrategyDList1',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});