Ext.define('cms.store.cdef.cdef_DefAreaStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cdef.cdef_DefAreaModel',
	pageSize : appConfig.getPageSize(),
	proxy: {
	    type: 'ajax',
		method: 'post',
		url: 'cdef_DefWareAction_getCdef_DefArea.action',
		reader : {
			type:'json',
			root : 'rootList',
			totalProperty : 'totalCount'
			}
	},
	autoLoad:false
});