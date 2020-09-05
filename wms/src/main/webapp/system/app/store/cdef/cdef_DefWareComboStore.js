Ext.define('cms.store.cdef.cdef_DefWareComboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.comboModel',
	pageSize : appConfig.getPageSize(),
	proxy: {
	    type: 'ajax',
		method: 'post',
		url: 'fcdata_PlanAction_getCdef_DefWareCombo.action',
		reader : {
			type:'json',
			root : 'rootList',
			totalProperty : 'totalCount'
		}
	},
	autoLoad:false
});