Ext.define('cms.store.fcdata.fcdata_CheckMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.fcdata.fcdata_CheckMModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'fcdata_CheckAction_getCheckM.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});