Ext.define('cms.store.bdef.bdef_DeflocStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_DeflocModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	storeId:'bdef_DeflocStore',
	proxy:{
	    type:'ajax',
	    method:'post',
	    url:'bdef_DefWorkerAction_getWarehouseList',
	    reader:{
	        type:'json',
	        root:'rootList',
	        totalProperty:'totalCount'
	    }
	}
});