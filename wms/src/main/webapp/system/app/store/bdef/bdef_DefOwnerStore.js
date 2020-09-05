Ext.define('cms.store.bdef.bdef_DefOwnerStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_DefOwnerModel',
	autoLoad:false,
	pageSize:appConfig.getPageSize(),
    proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_DefOwnerAction_getBdef_DefOwner',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});