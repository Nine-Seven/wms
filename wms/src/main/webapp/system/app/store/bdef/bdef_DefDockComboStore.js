/**
 * 模块名称：码头下拉Combo
 * @author JUN
 */
Ext.define('cms.store.bdef.bdef_DefDockComboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.common.comboModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_DefDockAction_query_BdefDefDock',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});
