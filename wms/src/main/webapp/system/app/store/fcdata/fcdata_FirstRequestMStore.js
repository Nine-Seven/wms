Ext.define('cms.store.fcdata.fcdata_FirstRequestMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.fcdata.fcdata_CheckMModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'fcdata_RequestAction_queryFcdataCheckM',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});