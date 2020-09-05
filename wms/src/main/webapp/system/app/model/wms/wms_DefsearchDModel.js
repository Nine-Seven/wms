Ext.define('cms.model.wms.wms_DefsearchDModel', {
	extend : 'Ext.data.Model',
	fields : [
		{name:'orderType'},
		{name:'statisticsFlag'},
		{name:'width'},
		{name:'rgstDate'},
		{name:'updtDate'},
		{name:'pgmId'},
		{name:'name'},
		{name:'fieldId'},
		{name:'rgstName'},
		{name:'fieldName'},
		{name:'statisticsFlagText'},
		{name:'seq'},
		{name:'fieldType'}
	],
	idProperty:'pgmId,name,fieldId,rgstName'
});