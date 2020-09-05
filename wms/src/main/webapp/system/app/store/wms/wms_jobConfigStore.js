Ext.define('cms.store.wms.wms_jobConfigStore',{
extend:'Ext.data.Store',
model:'cms.model.wms.wms_JobConfigModel',
pageSize : appConfig.getPageSize(),
proxy:{
	type:'ajax',
	method:'post',
	url : 'wmsJobConfigAction_getWms_JobConfugList.action',
    reader: {
		type:'json',
        root: 'rootList',    
        totalProperty: 'totalCount'
    }
}
});