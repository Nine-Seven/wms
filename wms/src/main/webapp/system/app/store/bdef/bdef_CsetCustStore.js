/**
 * 模块名称：客户与电子标签储位对应关系
 * 模块编码：1P01
 * 创建：hcx
 */
Ext.define('cms.store.bdef.bdef_CsetCustStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_DefCustModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'bdef_CsetCustDpscellAction_getCsetCustList',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});