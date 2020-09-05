/**
 *获取批次号
 *创建：czh
 */
Ext.define('cms.store.odata.odata_OutstockM_GetBatchSendOrderStore',{
	extend:'Ext.data.Store',
	model:'cms.model.odata.odata_OutstockDirectModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'odata_OutstockDirectAction_getBatchSendOrder',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});