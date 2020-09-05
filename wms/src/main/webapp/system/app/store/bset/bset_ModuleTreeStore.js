/**
 *模块菜单
 *创建：lich
 */
Ext.define('cms.store.bset.bset_ModuleTreeStore',{
	extend:'Ext.data.TreeStore',
	model:'cms.model.common.treeModel',
	pageSize:appConfig.getPageSize(),
	root:{
		text : '模块列表',
		id : '0',
		expanded : true
	},
	proxy:{
		type:'ajax',
		method:'post',
		url:'authAction_getBset_ModuleTree.action'
	},
	autoLoad:false//
});