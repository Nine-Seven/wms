Ext.define('cms.store.bdef.bdef_WmsDefbaseStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_WmsDefbaseModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
	    type:'ajax',
	    method:'post',
	    url:'bdef_WmsDefbaseAction_getWmsDefbaseList.action',
	    reader:{
	        type:'json',
	        root:'rootList',
	        totalProperty:'totalCount'
	    }
	}
});
