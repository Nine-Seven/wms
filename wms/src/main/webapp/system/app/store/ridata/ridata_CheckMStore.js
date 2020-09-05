Ext.define('cms.store.ridata.ridata_CheckMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.ridata.ridata_CheckMModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'ridata_CheckAction_getRidata_CheckMList',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});