Ext.define('cms.store.idata.idata_PoNoStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.comboModel', 
	pageSize : appConfig.getPageSize(),
	proxy:{

        type: 'ajax',
        method: 'post',
        url: 'idata_MovieTrolleyCheckAction_getPoNoList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
	}
});