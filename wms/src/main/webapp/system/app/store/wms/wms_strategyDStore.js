Ext.define('cms.store.wms.wms_strategyDStore',{
extend:'Ext.data.Store',
model:'cms.model.wms.wms_strategyDModel',
pageSize : appConfig.getPageSize(),
proxy:{
	type:'ajax',
	method:'post',
	url : 'odata_TacticsAction_getStrategyDList.action',
    reader: {
		type:'json',
        root: 'rootList',    
        totalProperty: 'totalCount'
    }
},
autoLoad: false	
});