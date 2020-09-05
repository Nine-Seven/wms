Ext.define('cms.store.oset.oset_DefareaWhitoutAllotStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cdef.cdef_DefAreaModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'oset_TaskAllotAction_getDefarea',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});