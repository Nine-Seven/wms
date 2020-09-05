/**
 * 模块名称：打印机群组与打印机组关系维护
 * 模块编码：1T01
 * 创建：hcx 
 */
Ext.define('cms.store.bdef.pntset_GrpgatherPrinterGroupStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.pntset_GrpgatherPrinterGroupModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'pntdef_PrinterGrpgatherAction_getPntsetGrpgatherPrinterGroupList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});