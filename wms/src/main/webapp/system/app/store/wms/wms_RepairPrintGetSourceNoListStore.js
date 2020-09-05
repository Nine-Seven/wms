Ext.define('cms.store.wms.wms_RepairPrintGetSourceNoListStore',{
extend:'Ext.data.Store',
model:'cms.model.wms.wms_PntsetmoduleReportModel',
pageSize : appConfig.getPageSize(),
proxy:{
	type:'ajax',
	method:'post',
	url : 'wms_PnfsetModuleReportQueryAction_getSourceNoList.action',
    reader: {
		type:'json',
        root: 'rootList',    
        totalProperty: 'totalCount'
    }
},
autoLoad: false	
});