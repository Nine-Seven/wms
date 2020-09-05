/**
 *移库发单
 *创建：zhouhuan
 */
Ext.define('cms.store.odata.odata_OutstockDirectStore',{
	extend:'Ext.data.Store',
	model:'cms.model.odata.odata_OutstockDirectModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_OutstockDirectAction_getOdata_OutstockDirect.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});