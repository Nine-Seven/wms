Ext.define('cms.model.bdef.bdef_DefWorkstationModel',{
	extend:'Ext.data.Model',
	fields:[
			{name:'warehouseNo'},
			{name:'workstationNo'},
		    {name:'workstationName'},
			{name:'printerGroupNo'},
			{name:'rgstName'},
			{name:'rgstDate'},
			{name:'updtName'},
			{name:'updtDate'},
			
			{name:'printerGroupName'}
		],	
		idProperty:'warehouseNo,workstationNo'
});