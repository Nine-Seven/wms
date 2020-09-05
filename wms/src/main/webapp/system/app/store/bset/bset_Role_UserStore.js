/**
 *用户角色表
 *创建：czh
 */
Ext.define('cms.store.bset.bset_Role_UserStore',{
	extend:'Ext.data.Store',
	id:'bset_Role_UserStore',
	model:'cms.model.bset.bset_Role_ListModel',
	pageSize:appConfig.getPageSize(),
	proxy : {
		type : 'ajax',
		method : "post",
		url : 'authAction_getBset_Role_User.action',
		reader : {
			root : 'rootList',
			totalProperty : 'totalCount'
		}
	},
	autoLoad : false
});