/**
 *手键退厂单明细
 *创建：zhouhuan
 */
Ext.define('cms.store.rodata.rodata_RecedeDStore',{
	extend:'Ext.data.Store',
	pageSize : appConfig.getPageSize(),
	model:'cms.model.rodata.rodata_RecedeDModel',
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'rodata_RecedeMAction_getRodata_RecedeD',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});
