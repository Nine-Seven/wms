Ext.define('cms.store.bdef.bdef_DefcarStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_DefcarModel',
	autoLoad:false,
    proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_DefCarAction_getCarList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});