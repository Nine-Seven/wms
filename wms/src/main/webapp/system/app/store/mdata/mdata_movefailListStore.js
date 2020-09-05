/*
 * 人工移库导入失败列表查询
 */
Ext.define('cms.store.mdata.mdata_movefailListStore',{
	extend:'Ext.data.Store',
	model:'cms.model.mdata.mdata_PlanMTmpModel',
	autoLoad:true,
	proxy:{
		type:'ajax',
		method:'post',
		url:'mdata_PlanMAction_movefailList.action',
		reader:{
			type:'json',
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});