/**
 *模块权限定义
 *创建：lich
 */
Ext.define('cms.store.bset.bset_RightListStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bset.bset_RightListModel',
	autoLoad:false,
	proxy: {
	    type: 'ajax',
		method: 'post',
		url: 'authAction_getBset_RightList.action',
		reader : {
			root : 'rootList',
			totalProperty : 'totalCount'
			}
	},
	storeId:'bset_RightListStore',
	remoteSort:true,
 	groupField: 'name'
});