/**
 *退厂拼箱打包标签列表
 */
Ext.define('cms.store.rodata.rodata_SlabelNoListStore',{
	extend:'Ext.data.Store',
	pageSize : appConfig.getPageSize(),
	model:'cms.model.rodata.rodata_BoxMModel',
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'rodata_LabelPackAction_getRodata_sLabelList',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});
