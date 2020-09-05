Ext.define('cms.store.oset.oset_TaskAllotMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.oset.oset_TaskAllotMModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'oset_TaskAllotAction_getTaskAllotM',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});