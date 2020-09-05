Ext.define('cms.store.wms.wms_strategyMStore',{
extend:'Ext.data.Store',
model:'cms.model.wms.wms_strategyMModel',
pageSize : appConfig.getPageSize(),
proxy:{
	type:'ajax',
	method:'post',
	url : 'odata_TacticsAction_getStrategyMList.action',
    reader: {
		type:'json',
        root: 'rootList',    
        totalProperty: 'totalCount'
    }
},
autoLoad: false	
});