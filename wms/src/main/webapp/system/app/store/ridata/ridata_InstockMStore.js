Ext.define('cms.store.ridata.ridata_InstockMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.ridata.ridata_InstockMModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'ridata_InstockAction_getRidata_InstockMList',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});