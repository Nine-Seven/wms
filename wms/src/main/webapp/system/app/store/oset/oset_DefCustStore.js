Ext.define('cms.store.oset.oset_DefCustStore',{
	extend:'Ext.data.Store',
	model:'cms.model.oset.oset_Line_CustModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'oset_DefLineAction_getCust',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});