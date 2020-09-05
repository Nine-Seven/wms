Ext.define('cms.store.ridata.ridata_BatchStore',{
	extend:'Ext.data.Store',
	model:'cms.model.ridata.ridata_BatchModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'ridata_BatchAction_getRidata_BatchList',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});