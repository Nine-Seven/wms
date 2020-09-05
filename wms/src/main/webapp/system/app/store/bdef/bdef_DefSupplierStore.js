Ext.define('cms.store.bdef.bdef_DefSupplierStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_DefSupplierModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
	    type:'ajax',
	    method:'post',
	    url:'bdef_DefSupplierAction_getBdef_defSupplierList.action',
	    reader:{
	        type:'json',
	        root:'rootList',
	        totalProperty:'totalCount'
	    }
	}
});