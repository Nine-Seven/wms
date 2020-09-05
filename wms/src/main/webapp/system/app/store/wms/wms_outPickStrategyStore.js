Ext.define('cms.store.wms.wms_outPickStrategyStore',{
extend:'Ext.data.Store',
model:'cms.model.wms.wms_outPickStrategyModel',
pageSize : appConfig.getPageSize(),
proxy:{
	type:'ajax',
	method:'post',
	url : 'wms_outPickStrategyAction_getoutPickStrategy.action',
    reader: {
		type:'json',
        root: 'rootList',    
        totalProperty: 'totalCount'
    }
},
autoLoad: false	
});