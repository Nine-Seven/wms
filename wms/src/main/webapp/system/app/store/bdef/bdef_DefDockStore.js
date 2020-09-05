Ext.define('cms.store.bdef.bdef_DefDockStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_DefDockModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
	    type:'ajax',
	    method:'post',
	    url:'bdef_DefDockAction_getBdef_defDockList.action',
	    reader:{
	        type:'json',
	        root:'rootList',
	        totalProperty:'totalCount'
	    }
	}
});