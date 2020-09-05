Ext.define('cms.store.cset.cset_OwnerArticleComboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.comboModel',
	pageSize : appConfig.getPageSize(),
	proxy: {
	    type: 'ajax',
		method: 'post',
		url: 'cset_CellArticleAction_getCset_OwnerArticleCombo.action',
		reader : {
			type:'json',
			root : 'rootList',
			totalProperty : 'totalCount'
		}
	}/*,
	autoLoad:true*/
});