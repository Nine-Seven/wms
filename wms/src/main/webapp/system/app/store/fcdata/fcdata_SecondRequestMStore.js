Ext.define('cms.store.fcdata.fcdata_SecondRequestMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.fcdata.fcdata_CheckMModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'fcdata_RequestAction_querySecondFcdataCheckM',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});