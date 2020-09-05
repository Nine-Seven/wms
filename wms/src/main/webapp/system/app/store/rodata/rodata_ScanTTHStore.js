/**
 *退厂扫描
 *创建：chensr
 */
Ext.define('cms.store.rodata.rodata_ScanTTHStore',{
	extend:'Ext.data.Store',
	pageSize : appConfig.getPageSize(),
	model:'cms.model.bdef.comboModel',
	proxy:{
		type:'ajax',
		method:'post',
		url:'rodata_ScanTTHAction_getRodata_ScanM',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});
