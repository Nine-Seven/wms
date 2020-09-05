Ext.define('cms.store.bdef.bdef_DefWorkstationStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_DefWorkstationModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
	    type:'ajax',
	    method:'post',
	    url:'bdef_DefWorkstationAction_getWorkstationList.action',
	    reader:{
	        type:'json',
	        root:'rootList',
	        totalProperty:'totalCount'
	    }
	}
});