Ext.define('cms.store.wms.wms_DefModuleQueryColumnStore',{
extend:'Ext.data.Store',
model:'cms.model.wms.wms_DefModuleQueryColumnModel',
pageSize : appConfig.getPageSize(),
proxy:{
	type:'ajax',
	method:'post',
	url : 'wms_DefModuleQueryColumnmAction_getModuleQueryColumn.action',
    reader: {
		type:'json',
        root: 'rootList',    
        totalProperty: 'totalCount'
    }
},
autoLoad: false	
});