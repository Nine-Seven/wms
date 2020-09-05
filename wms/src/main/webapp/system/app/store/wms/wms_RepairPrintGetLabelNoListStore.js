Ext.define('cms.store.wms.wms_RepairPrintGetLabelNoListStore',{
	extend:'Ext.data.Store',
	model:'cms.model.wms.wms_PntsetmoduleReportModel',
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		async:false,
		url : 'wms_PnfsetModuleReportQueryAction_getLabelNoList.action',
	    reader: {
			type:'json',
	        root: 'rootList',    
	        totalProperty: 'totalCount'
	    }
	},
	autoLoad: false	
	});