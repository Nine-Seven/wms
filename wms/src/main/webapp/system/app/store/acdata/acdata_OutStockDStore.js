/**
 * 模块名称：分拨出库
 * 模块编码：C201
 * 创建：hkl
 */
Ext.define('cms.store.acdata.acdata_OutStockDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.acdata.acdata_OutStockDModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'acdata_OutStockAction_getOut_DList',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
 
});
