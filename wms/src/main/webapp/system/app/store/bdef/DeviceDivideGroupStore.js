/**
 * 模块名称：设备组
 * 模块编码：1S01
 * 创建：chensr
 */
Ext.define('cms.store.bdef.DeviceDivideGroupStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.DeviceDivideGroupModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'Divice_DivideAction_getDeviceDivideGroup',
        reader:{
	        type:'json',
	        root:'rootList',
	        totalProperty:'totalCount'
	    }
    }
});
