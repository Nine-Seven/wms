/**
 * 模块名称：复核策略配置
 * 模块编码：I804
 * @author liucl 20160812
 */
Ext.define('cms.store.odata.odata_CheckStrategyStore',{
	
	extend:'Ext.data.Store',
	model:'cms.model.odata.odata_CheckStrategyModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
    	 
        type: 'ajax',
        method: 'post',
        url: 'Odata_CheckStrategyAction_getCheckStrategyList',//URl要改
    	reader : {                      
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});