Ext.define('cms.model.idata.dock_SheetModel', {
    extend: 'Ext.data.Model',
    fields: [			
	       	{name:'rgstName'},
			{name:'startTime'},
			{name:'endTime'},
			{name:'TDock'},
			{name:'UDock'},
			{name:'FDock'},
			{name:'carsCount'}
    ],
    idProperty:'rgstName,startTime,endTime'
});