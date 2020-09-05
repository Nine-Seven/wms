Ext.define('cms.store.odata.odata_LoadproposeMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.odata.odata_LoadproposeMModel', 
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'odata_CarPlanAction_queryLoadproposeM.action',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});