/**
 * 模块名称：供应商下拉
 * @author JUN
 */
Ext.define('cms.store.bdef.bdef_DefSupplierComboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.common.comboModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_DefSupplierAction_queryBdefDefSupplierCombo',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
 
});