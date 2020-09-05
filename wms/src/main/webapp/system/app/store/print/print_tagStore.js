/**
 * 补印中心报表类型
 */
Ext.define('cms.store.print.print_tagStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.bdef_DefarticleModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'printerTagAction_getDefarticle',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});