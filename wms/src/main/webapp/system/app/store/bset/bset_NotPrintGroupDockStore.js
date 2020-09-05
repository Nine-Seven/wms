Ext.define('cms.store.bset.bset_NotPrintGroupDockStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_DefDockModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
	    type:'ajax',
	    method:'post',
	    url:'bset_DocGroupAction_QueryDockListNotInPrintGroup',
	    reader:{
	        type:'json',
	        root:'rootList',
	        totalProperty:'totalCount'
	    }
	}
});