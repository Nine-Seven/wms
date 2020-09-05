/**
 *模块：商品类别树 Store
 *创建：周欢
 */
Ext.define('cms.store.bdef.bdef_ArticleGroupTreeStore',{
	extend:'Ext.data.TreeStore',
	model:'cms.model.common.treeModel',
	//pageSize:appConfig.getPageSize(),
	root:
	{
		text : '全部',
		id : '0',
		expanded : true
	},
	proxy:
	{
		type:'ajax',
		method:'post',
		url:'bdef_ArticleGroupAction_getBdef_ArticleModuleTree'
	},
	autoLoad:false//
});

