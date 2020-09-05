/**
 *员工仓别对应表
 *创建：lich
 */
Ext.define('cms.store.bset.bset_Worker_LocComboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.common.comboModel',
	pageSize : appConfig.getPageSize(),
	proxy: {
	    type: 'ajax',
		method: 'post',
		url: 'bset_Worker_LocAction_getBset_Worker_LocCombo.action',
		reader : {
			type:'json',
			root : 'rootList',
			totalProperty : 'totalCount'
			}
	},
	autoLoad:false
});