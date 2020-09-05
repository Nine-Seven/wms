/**
 * 补印中心 单据信息Store
 */
Ext.define('cms.store.print.print_RepairReportInfoStore',{
	extend:'Ext.data.Store',
	model:'cms.model.print.print_RepairReportInfoModel',
	pageSize : appConfig.getPageSize(),
	storeId:'print_RepairReportInfoStore',
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'printerAction_QueryRepairSheetInfoBoList',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});