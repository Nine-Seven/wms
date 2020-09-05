Ext.define('cms.store.wms.wms_PntsetmodulereportqueryStore',{
extend:'Ext.data.Store',
model:'cms.model.wms.wms_PntsetModuleReportQueryModel',
pageSize : appConfig.getPageSize(),
proxy:{
	type:'ajax',
	method:'post',
	url : 'wms_PnfsetModuleReportQueryAction_getModuleReportQuery.action',
    reader: {
		type:'json',
        root: 'rootList',    
        totalProperty: 'totalCount'
    }
},
autoLoad: false	
});