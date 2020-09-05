Ext.define('cms.store.oset.oset_TaskAllotDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.oset.oset_TaskAllotDModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'oset_TaskAllotAction_getTaskAllotD',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});