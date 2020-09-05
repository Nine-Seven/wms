Ext.define('cms.store.common.comboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.common.comboModel',
	pageSize : appConfig.getPageSize(),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'bdef_ArticleGroupAction_getWmsDeffieldvalCombo.action',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});