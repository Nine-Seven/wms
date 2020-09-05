/**
 * 模块名称：承运商资料维护
 * 模块编码：1E01
 * 创建：周欢
 */
Ext.define('cms.store.bdef.bdef_DefShipperStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_DefShipperModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'bdef_DefShipperAction_getDefShipperList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});