Ext.define('cms.model.wms.Wms_OutorderandflowModel', {
	extend : 'Ext.data.Model',
	fields : [
		{name:'flowValue'},
		{name:'flowText'}
	],
	idProperty : 'expType,flowText'
});