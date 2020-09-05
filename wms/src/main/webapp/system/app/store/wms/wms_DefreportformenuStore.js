Ext.define('cms.store.wms.wms_DefreportformenuStore',{
extend:'Ext.data.Store',
model:'cms.model.wms.wms_DefreportformenuModel',
pageSize : appConfig.getPageSize(),
proxy:{
	type:'ajax',
	method:'post',
	url : 'report_setAction_getReportformenu.action',
    reader: {
		type:'json',
        root: 'rootList',    
        totalProperty: 'totalCount'
    }
},
autoLoad: false	
});