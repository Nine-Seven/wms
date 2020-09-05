/**
 * 模块名称：货主仓别参数配置
 * 模块编码：1N01
 * 创建：chensr
 */
Ext.define('cms.store.bdef.bdef_WmsWarehouseOwnerBaseStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_WmsWarehouseOwnerBaseModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
     proxy: {
        type: 'ajax',
        method: 'post',
        url: 'bdef_WmsWarehouseOwnerBaseAction_getWarehouseOwnerBaselist',
        reader:{
	        type:'json',
	        root:'rootList',
	        totalProperty:'totalCount'
	    }
    }
 
});
