/**
 * 模块名称：分拨出库
 * 模块编码：C201
 * 创建：hkl
 */
Ext.define('cms.store.acdata.acdata_LabelMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.acdata.acdata_Order_MModel', 
	pageSize : appConfig.getPageSize(),
	//arelt(123),
	proxy:{
		type:'ajax',
		method:'post',
		url : 'acdata_OutStockAction_getOrder_MList',
        reader: {
    		type:'json',
            root: 'rootList',    
            totalProperty: 'totalCount'
        }
    },
    autoLoad: false	
});