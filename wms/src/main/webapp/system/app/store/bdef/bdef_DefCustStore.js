/**
 * 模块名称：客户主档
 * 模块编码：200001
 * 创建：Jun
 */
Ext.define('cms.store.bdef.bdef_DefCustStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_DefCustModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'bdef_DefCustAction_getDefCustList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});