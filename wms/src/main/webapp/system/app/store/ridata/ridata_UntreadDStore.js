Ext.define('cms.store.ridata.ridata_UntreadDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.ridata.ridata_UntreadDModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'ridata_UntreadAction_getRidata_UntreadDList',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});