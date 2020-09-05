Ext.define('cms.store.fcdata.fcdata_PlanMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.fcdata.fcdata_PlanMModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'./fcdata_PlanAction_getPlanMList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});