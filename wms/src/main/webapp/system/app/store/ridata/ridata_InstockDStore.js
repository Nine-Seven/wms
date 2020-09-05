Ext.define('cms.store.ridata.ridata_InstockDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.ridata.ridata_InstockDModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'ridata_InstockAction_getRidata_InstockDList',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});