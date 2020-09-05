Ext.define('cms.store.cdef.cdef_DefWareStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cdef.cdef_DefWareModel',
	pageSize : appConfig.getPageSize(),
	proxy: {
	    type: 'ajax',
		method: 'post',
		url: 'cdef_DefWareAction_getCdef_DefWare.action',
		reader : {
			type:'json',
			root : 'rootList',
			totalProperty : 'totalCount'
			}
	},
	autoLoad:false
});