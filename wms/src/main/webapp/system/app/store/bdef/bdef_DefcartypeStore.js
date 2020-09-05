Ext.define('cms.store.bdef.bdef_DefcartypeStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_DefcartypeModel',
	autoLoad:false,
    proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_DefCarAction_getCartypeList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});