/**
 * 模块名称：设备组
 * 模块编码：1S01
 * 创建：chensr
 */
Ext.define('cms.store.bdef.DeviceDivideDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.DeviceDivideDModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'Divice_DivideAction_getDeviceDivideD',
        reader:{
	        type:'json',
	        root:'rootList',
	        totalProperty:'totalCount'
	    }
    }
});
