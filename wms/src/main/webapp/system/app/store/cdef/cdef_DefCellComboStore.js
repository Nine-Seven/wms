Ext.define('cms.store.cdef.cdef_DefCellComboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.common.comboModel',
	pageSize : appConfig.getPageSize(),
	proxy: {
	    type: 'ajax',
		method: 'post',
		url: 'fcdata_PlanAction_getCdef_DefCellCombo.action',
		reader : {
			type:'json',
			root : 'rootList',
			totalProperty : 'totalCount'
			}
	},
	autoLoad:false
});