Ext.define('cms.store.wms.wms_DefStrategyComboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.common.comboModel',
	autoLoad:false,
    proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_ArticleGroupAction_queryWmsDefStrategyCombo',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});