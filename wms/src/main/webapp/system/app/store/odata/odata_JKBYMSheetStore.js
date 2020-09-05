/**
 * 模块名称：BYM出货订单接口Store
 * 模块编码：3911
 * 创建：chensr 
 */
Ext.define('cms.store.odata.odata_JKBYMSheetStore',{
	extend:'Ext.data.Store',
	model:'cms.model.odata.odata_JKBYMSheetModel',
	autoLoad:false,
	
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_BYMSheetAction_getBYMSheet.action',
		reader:{
			type:'json',
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});