Ext.define('cms.store.odata.odata_PackageDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.odata.odata_PackageDModel', 
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'odata_PackageAction_queryPackageD',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});