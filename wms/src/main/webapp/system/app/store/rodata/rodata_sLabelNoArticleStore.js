/**
 *退厂拼箱打包源标签商品列表
 */
Ext.define('cms.store.rodata.rodata_sLabelNoArticleStore',{
	extend:'Ext.data.Store',
	pageSize : appConfig.getPageSize(),
	model:'cms.model.rodata.rodata_BoxDModel',
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'rodata_LabelPackAction_getRodata_sLabelNoArticleList',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});
