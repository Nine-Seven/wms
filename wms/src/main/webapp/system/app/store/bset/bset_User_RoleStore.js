/**
 *用户角色表
 *创建：lich
 */
Ext.define('cms.store.bset.bset_User_RoleStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bset.bset_User_RoleModel',
	pageSize:appConfig.getPageSize(),
	proxy : {
		type : 'ajax',
		method : "post",
		//url : 'authAction_getBset_User_Role.action',  getWorkerListByWorkerNo
		url : 'authAction_getWorkerListByWorkerNo.action',
		reader : {
			root : 'rootList',
			totalProperty : 'totalCount'
		}
	},
	autoLoad : false
});