Ext.define('cms.store.bdef.bdef_WmsLogiboxRuleStore', {
	extend : 'Ext.data.Store',
	model : 'cms.model.bdef.bdef_WmsLogiboxRuleModel',
	pageSize : appConfig.getPageSize(),
	proxy : {
		type : 'ajax',
		method : 'post',
		url : 'bdef_WmsLogiboxRuleAction_getWmsLogiboxRule.action',
		reader : {
			type : 'json',
			root : 'rootList',
			totalProperty : 'totalCount'
		}
	},
	autoLoad : false
});