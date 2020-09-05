/**
 * 模块名称：打印机组与打印机关系维护
 * 模块编码：300002
 * 创建：Jun
 */
Ext.define('cms.store.bset.bset_GroupStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bset.bset_GroupModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bset_GroupAction_getBset_GroupList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});