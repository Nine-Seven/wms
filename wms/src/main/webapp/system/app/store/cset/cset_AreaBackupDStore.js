Ext.define('cms.store.cset.cset_AreaBackupDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cset.cset_AreaBackupDModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'cset_AreaBackupAction_getCset_AreaBackupDList.action',
		reader:{
			type:'json',
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});