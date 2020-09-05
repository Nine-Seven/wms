Ext.define('cms.store.wms.wms_DefsearchDSetStore',{
extend:'Ext.data.Store',
model:'cms.model.wms.wms_DefsearchDSetModel',
pageSize : appConfig.getPageSize(),
proxy:{
	type:'ajax',
	method:'post',
	url : 'report_Action_getWmsDefsearchDSetList.action',
    reader: {
		type:'json',
        root: 'rootList',    
        totalProperty: 'totalCount'
    }
},
autoLoad: false	
});