Ext.define('cms.store.cset.cset_deflocStore',{
	extend:'Ext.data.Store',
	model:'cms.model.cset.cset_deflocModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'cset_deflocAction_getLoc.action',
		reader:{
			type:'json',
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});