Ext.define('cms.model.bdef.bdef_WmsWarehouseBaseModel',{
	extend:'Ext.data.Model',
	fields:[
			{name:'warehouseNo'},
			{name:'colname'},
			{name:'groupNo'},
		    {name:'subGroupNo'},
			{name:'sdefine'},
			{name:'ndefine'},
			{name:'memo'},
			{name:'subGroupNoText'},
			{name:'sdefineText'},
			{name:'groupNoText'},
			{name:'wareHouseText'}  //8-4
		],	
		idProperty:'warehouseNo,colname'
});