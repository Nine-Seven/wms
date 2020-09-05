Ext.define('cms.store.bset.bset_Group2Store',{
	extend:'Ext.data.Store',
	model:'cms.model.bset.bset_GroupModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bset_GroupAction_getBset_GroupList2',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});