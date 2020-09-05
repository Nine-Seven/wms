Ext.define('cms.store.wms.wms_RepairPrintgetReportDataStore',{
	extend:'Ext.data.Store',
	model:'cms.model.wms.wms_PntsetmoduleReportModel',
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'wms_PnfsetModuleReportQueryAction_getReportData.action',
	    reader: {
			type:'json',
	        root: 'rootList',    
	        totalProperty: 'totalCount'
	    }
	},
	autoLoad: false	
	});