/**
 *获取报损建单头档
 *创建：hkl
 */
Ext.define('cms.store.sodata.sodata_WasteDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.sodata.sodata_WasteDModel',
	pageSize : appConfig.getPageSize(),
	storeId:'sodata_WasteDStore',
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'sodata_WasteAction_getWaste_DList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});