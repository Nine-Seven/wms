Ext.define('cms.store.bdef.bdef_DefWorkerOwnerStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_DeflocModel',
	autoLoad:false,
	proxy: {
	    type: 'ajax',
		method: 'post',
		url: 'bdef_DefWorkerAction_getWarehouseListByWorkerNo',
		reader : {
			root : 'rootList',
			totalProperty : 'totalCount'
			}
	},
    id:'bdef_DefWorkerLocStore'
});