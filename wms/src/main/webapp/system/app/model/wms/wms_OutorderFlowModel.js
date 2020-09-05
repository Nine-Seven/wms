Ext.define('cms.model.wms.wms_OutorderFlowModel', {
	extend : 'Ext.data.Model',
	fields : [
	    {name:'enterpriseNo'},
		{name:'expType'},
		{name:'flowOrder'},
		{name:'flowValue'},
		{name:'flowFlag'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		
		{name:'flag'},
		{name:'flowvalueText'}
	],
	idProperty : 'expType,flowOrder'
});