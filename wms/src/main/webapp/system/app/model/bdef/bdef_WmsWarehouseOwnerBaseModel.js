/**
 * 模块名称：货主仓别参数配置
 * 模块编码：1N01
 * 创建：chensr
 */
Ext.define('cms.model.bdef.bdef_WmsWarehouseOwnerBaseModel',{
	extend:'Ext.data.Model',
	idProperty:'ownerNo,colname',
	fields:[
	        {name:'ownerNo'},
	        {name:'warehouseNo'},
			{name:'colname'},
			{name:'groupNo'},
			{name:'subGroupNo'},
			{name:'ndefine'},
			{name:'sdefine'},
			{name:'memo'},
			{name:'sdefineText'},
			{name:'groupNoText'},
			{name:'subGroupNoText'},
			{name:'ownerNameText'},
			{name:'wareHouseNameText'}
	       ]
});