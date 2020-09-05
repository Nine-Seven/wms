Ext.define('cms.store.bdef.bdef_WmsInterfacePlatStore',{
extend:'Ext.data.Store',
model:'cms.model.bdef.bdef_WmsInterfacePlatModel',
pageSize : appConfig.getPageSize(),
proxy:{
	type:'ajax',
	method:'post',
	url : 'bdef_WmsInterfacePlatAction_getWmsInterfacePlatList.action',
    reader: {
		type:'json',
        root: 'rootList',    
        totalProperty: 'totalCount'
    }
},
autoLoad: false	
});