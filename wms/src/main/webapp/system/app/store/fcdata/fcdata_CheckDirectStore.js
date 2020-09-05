Ext.define('cms.store.fcdata.fcdata_CheckDirectStore',{
	extend:'Ext.data.Store',
	model:'cms.model.fcdata.fcdata_CheckDirectModel',
	pageSize : 20,
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'fcdata_CheckAction_getCheckDirect.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});