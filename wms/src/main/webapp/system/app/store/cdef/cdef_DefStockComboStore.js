Ext.define('cms.store.cdef.cdef_DefStockComboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.comboModel',
	pageSize : appConfig.getPageSize(),
	proxy: {
	    type: 'ajax',
		method: 'post',
		url: 'fcdata_PlanAction_getCdef_DefStockCombo.action',
		reader : {
			type:'json',
			root : 'rootList',
			totalProperty : 'totalCount'
		}
	},
	autoLoad:false
});