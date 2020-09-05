/**
 * 模块名称：货主参数配置
 * 模块编码：1M01
 * 创建：chensr
 */
Ext.define('cms.model.bdef.bdef_WnsOwnerBaseModel',{
	extend:'Ext.data.Model',
	idProperty:'ownerNo,colname',
	fields:[
	        {name:'ownerNo'},
			{name:'colname'},
			{name:'groupNo'},
			{name:'subGroupNo'},
			{name:'ndefine'},
			{name:'sdefine'},
			{name:'memo'},
			{name:'sdefineText'},
			{name:'groupNoText'},
			{name:'subGroupNoText'},
			{name:'ownerNameText'}  //8-4
			
	       ]
});