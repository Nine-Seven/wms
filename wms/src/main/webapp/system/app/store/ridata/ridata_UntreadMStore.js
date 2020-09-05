Ext.define('cms.store.ridata.ridata_UntreadMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.ridata.ridata_UntreadMModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'ridata_UntreadAction_getRidata_UntreadMList',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});