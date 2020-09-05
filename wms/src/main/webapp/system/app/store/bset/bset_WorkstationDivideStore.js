/**
 * 模块名称：工作站与设备组关系维护
 * 模块编码：1U01
 * 创建：hcx 
 */
Ext.define('cms.store.bset.bset_WorkstationDivideStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bset.bset_WorkstationDivideModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bset_workstationDivideAction_getBsetWorkstationDivideList',
    	reader : {
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});