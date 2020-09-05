/**
 *员工表
 *创建：lich
 */
Ext.define('cms.store.bdef.bdef_DefWorkerStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_DefWorkerModel',
	autoLoad:false,
	proxy: {
	    type: 'ajax',
		method: 'post',
		url: 'bdef_DefWorkerAction_getWorkerList.action',
		reader : {
			root : 'rootList',
			totalProperty : 'totalCount'
			}
	},
    storeId:'bdef_DefWorkerStore'
});