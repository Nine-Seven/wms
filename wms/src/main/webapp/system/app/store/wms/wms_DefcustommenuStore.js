Ext.define('cms.store.wms.wms_DefcustommenuStore',{
extend:'Ext.data.Store',
model:'cms.model.wms.wms_DefCustomMModel',
pageSize : appConfig.getPageSize(),
proxy:{
	type:'ajax',
	method:'post',
	url : 'auto_SetAction_getCustomformenu.action',
    reader: {
		type:'json',
        root: 'rootList',    
        totalProperty: 'totalCount'
    }
},
autoLoad: false	
});