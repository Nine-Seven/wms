/**
 *员工表
 *创建：lich
 */
Ext.define('cms.store.bset.bset_User_ListStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bset.bset_User_ListModel',
	autoLoad:false,
	proxy: {
	    type: 'ajax',
		method: 'post',
		url: 'authAction_getBset_User_List.action',
		reader : {
			root : 'rootList',
			totalProperty : 'totalCount'
			}
	}
 
});