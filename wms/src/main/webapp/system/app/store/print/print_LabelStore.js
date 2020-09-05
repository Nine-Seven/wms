/**
 * 补印中心标签打印》选择单据查询对应标签
 */
Ext.define('cms.store.print.print_LabelStore',{
	extend:'Ext.data.Store',
	model:'cms.model.print.print_RepairReportInfoModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'printerAction_QueryLabelList',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});