/**
 * @author panzhenxing
 */
Ext.define('cms.store.cset.cset_BufferStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cset.cset_BufferModel', 
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'cset_BufferAction_getBufferList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});