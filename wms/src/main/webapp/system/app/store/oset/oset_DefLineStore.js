Ext.define('cms.store.oset.oset_DefLineStore',{
	extend:'Ext.data.Store',
	model:'cms.model.oset.oset_DefLineModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'oset_DefLineAction_getOset_DefLine',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});