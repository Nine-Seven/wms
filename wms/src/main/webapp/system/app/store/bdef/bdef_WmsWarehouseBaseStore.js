Ext.define('cms.store.bdef.bdef_WmsWarehouseBaseStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_WmsWarehouseBaseModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
	    type:'ajax',
	    method:'post',
	    url:'bdef_WmsWarehouseBaseAction_getWmsWarehouseBaseList.action',
	    reader:{
	        type:'json',
	        root:'rootList',
	        totalProperty:'totalCount'
	    }
	}
});
