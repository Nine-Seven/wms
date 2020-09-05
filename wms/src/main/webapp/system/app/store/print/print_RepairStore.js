/**
 * 补印中心报表类型
 */
Ext.define('cms.store.print.print_RepairStore',{
	extend:'Ext.data.Store',
	model:'cms.model.print.print_RepairModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'printerAction_AueryReportList',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});