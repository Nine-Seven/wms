Ext.define('cms.store.fcdata.fcdata_FirstRequestDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.fcdata.fcdata_CheckDModel',
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url:'fcdata_RequestAction_queryFcdataCheckD',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});