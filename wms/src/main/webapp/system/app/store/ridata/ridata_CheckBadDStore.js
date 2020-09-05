Ext.define('cms.store.ridata.ridata_CheckBadDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.ridata.ridata_CheckDModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'ridata_CheckBadAction_getRidata_CheckBadDList',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});