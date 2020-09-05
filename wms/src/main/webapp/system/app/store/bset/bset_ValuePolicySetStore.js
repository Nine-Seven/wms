Ext.define('cms.store.bset.bset_ValuePolicySetStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bset.bset_ValuePolicySetModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
	    type:'ajax',
	    method:'post',
	    url:'bset_ValuePolicySetAction_getValuePolicySetList.action',
	    reader:{
	        type:'json',
	        root:'rootList',
	        totalProperty:'totalCount'
	    }
	}
});
