/**
 * 模块名称：客户与电子标签储位对应关系
 * @author hcx
 */
Ext.define('cms.store.bdef.bdef_CsetCustDpscellStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_CsetCustDpscellModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_CsetCustDpscellAction_getCdef_DefCell',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});