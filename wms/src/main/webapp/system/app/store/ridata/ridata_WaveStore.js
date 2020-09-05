Ext.define('cms.store.ridata.ridata_WaveStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bset.bset_WaveManageModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'ridata_WaveAction_getRidata_WaveList',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});