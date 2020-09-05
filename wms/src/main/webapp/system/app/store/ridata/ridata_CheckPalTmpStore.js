Ext.define('cms.store.ridata.ridata_CheckPalTmpStore',{
	extend:'Ext.data.Store',
	model:'cms.model.ridata.ridata_CheckPalTmpModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'ridata_CheckAction_queryCheckPalTmp',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});