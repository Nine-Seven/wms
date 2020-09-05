Ext.define('cms.store.wms.wms_DefsearchDStore',{
extend:'Ext.data.Store',
model:'cms.model.wms.wms_DefsearchDModel',
pageSize : appConfig.getPageSize(),
proxy:{
	type:'ajax',
	method:'post',
	url : 'report_setAction_getWmsDefsearchD.action',
    reader: {
		type:'json',
        root: 'rootList',    
        totalProperty: 'totalCount'
    }
},
autoLoad: false	
});