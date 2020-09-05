Ext.define('cms.store.cset.cset_LineComboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.comboModel',
	pageSize : appConfig.getPageSize(),
	proxy: {
	    type: 'ajax',
		method: 'post',
		url: 'cset_CellArticleAction_queryLineCombo.action',
		reader : {
			type:'json',
			root : 'rootList',
			totalProperty : 'totalCount'
		}
	}/*,
	autoLoad:true*/
});