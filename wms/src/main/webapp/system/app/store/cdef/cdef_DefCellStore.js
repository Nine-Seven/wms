//获取储位明细hkl
Ext.define('cms.store.cdef.cdef_DefCellStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cdef.cdef_DefCellModel',
	pageSize : appConfig.getPageSize(),
	proxy: {
	    type: 'ajax',
		method: 'post',
		url: 'cdef_DefWareAction_getCdef_DefCellDetails.action',
		reader : {
			type:'json',
			root : 'rootList',
			totalProperty : 'totalCount'
			}
	},
	autoLoad:false
});