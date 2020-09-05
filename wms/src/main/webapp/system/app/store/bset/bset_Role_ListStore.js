/**
 *角色表
 *创建：lich
 */
Ext.define('cms.store.bset.bset_Role_ListStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bset.bset_Role_ListModel',
	autoLoad:false,
	storeId:'bset_Role_ListStore',
	proxy: {
	    type: 'ajax',
		method: 'post',
		url: 'authAction_getBset_Role_List.action',
		reader : {
			root : 'rootList',
			totalProperty : 'totalCount'
			}
	}
 	
});