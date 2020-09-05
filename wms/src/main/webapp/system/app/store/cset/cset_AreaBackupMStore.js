Ext.define('cms.store.cset.cset_AreaBackupMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cset.cset_AreaBackupMModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'cset_AreaBackupAction_getCset_AreaBackupMList.action',
		reader:{
			type:'json',
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});