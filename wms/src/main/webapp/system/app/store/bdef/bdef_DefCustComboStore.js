Ext.define('cms.store.bdef.bdef_DefCustComboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.comboModel',
	pageSize : appConfig.getPageSize(),
	proxy: {
	    type: 'ajax',
		method: 'post',
		url: 'bdef_DefCustAction_getBdef_DefCustCombo',
		reader : {
			type:'json',
			root : 'rootList',
			totalProperty : 'totalCount'
			}
	},
	autoLoad:false
});