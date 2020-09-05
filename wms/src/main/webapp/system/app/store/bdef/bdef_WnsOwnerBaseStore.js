/**
 * 模块名称：货主参数配置
 * 模块编码：1M01
 * 创建：chensr
 */
Ext.define('cms.store.bdef.bdef_WnsOwnerBaseStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_WnsOwnerBaseModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_WmsOwnerBaseAction_getWmsOwnerBaseList',
        reader:{
	        type:'json',
	        root:'rootList',
	        totalProperty:'totalCount'
	    }
    }
 
});
