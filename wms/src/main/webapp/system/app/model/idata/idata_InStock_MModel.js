Ext.define('cms.model.idata.idata_InStock_MModel', {
    extend: 'Ext.data.Model',
    fields: [			
	       	{name:'instockNo'},
			{name:'warehouseNo'},
			{name:'ownerNo'},
			{name:'autoLocateFlag'},
			{name:'status'},
			{name:'dispatchWorker'},
			{name:'dispatchDate'},
			{name:'instockWorker'},
			{name:'instockDate'},
			{name:'operateType'},
			{name:'locateType'},
			{name:'containerLocateFlag'},  
			{name:'rgstName'},
			{name:'rgstDate'},
			{name:'updtName'},
			{name:'updtDate'},
			{name:'statusText'}
    ],
    idProperty:'instockNo,warehouseNo,ownerNo'
});