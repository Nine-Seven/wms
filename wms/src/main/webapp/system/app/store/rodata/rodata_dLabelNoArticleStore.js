/**
 *退厂拼箱打包目的标签商品列表
 */
Ext.define('cms.store.rodata.rodata_dLabelNoArticleStore',{
	extend:'Ext.data.Store',
	pageSize : appConfig.getPageSize(),
	model:'cms.model.rodata.rodata_BoxDModel',
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'rodata_LabelPackAction_getRodata_dLabelNoArticleList',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});
