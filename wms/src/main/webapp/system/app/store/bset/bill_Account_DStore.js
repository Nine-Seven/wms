/**
 * 模块名称：科目和计费项目的关系维护bill_Account_DStore
 * 模块编码：B301
 * 创建：chensr 
 */
Ext.define('cms.store.bset.bill_Account_DStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bset.bill_Account_DModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bill_AccountAction_getAccount_DListWithCondition',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});