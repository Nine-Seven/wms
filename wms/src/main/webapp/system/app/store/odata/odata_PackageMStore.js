Ext.define('cms.store.odata.odata_PackageMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.odata.odata_PackageMModel', 
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'odata_PackageAction_queryPackageM.action',
		reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});