Ext.define('cms.store.ridata.ridata_CheckMStore2',{
	extend:'Ext.data.Store',
	model:'cms.model.ridata.ridata_CheckMModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'ridata_CheckAction2_getRidata_Check_MList',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});