Ext.define('cms.store.idata.idata_StraightCheckBatchStore',{
	extend:'Ext.data.Store',
	model:'cms.model.idata.idata_StraightCheckBatchModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'idata_StraightCheckBatchAction_getIdataStraightCheckBatchList',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});