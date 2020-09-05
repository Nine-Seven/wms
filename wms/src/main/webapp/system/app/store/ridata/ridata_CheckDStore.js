Ext.define('cms.store.ridata.ridata_CheckDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.ridata.ridata_CheckDModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'ridata_CheckAction_getRidata_CheckDList',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});