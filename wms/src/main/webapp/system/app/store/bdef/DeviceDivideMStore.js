/**
 * 模块名称：设备组
 * 模块编码：1S01
 * 创建：chensr
 */
Ext.define('cms.store.bdef.DeviceDivideMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.DeviceDivideMModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'Divice_DivideAction_getDeviceDivideM',
        reader:{
	        type:'json',
	        root:'rootList',
	        totalProperty:'totalCount'
	    }
    }
});
