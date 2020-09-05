Ext.define('cms.store.fcdata.fcdata_PlanDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.fcdata.fcdata_PlanDModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'./fcdata_PlanAction_getPlanDList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});