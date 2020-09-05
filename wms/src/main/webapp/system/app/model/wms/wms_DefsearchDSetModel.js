Ext.define('cms.model.wms.wms_DefsearchDSetModel', {
	extend : 'Ext.data.Model',
	fields : [
		{name:'orderType'},
		{name:'statisticsFlag'},
		{name:'width'},
		{name:'rgstDate'},
		{name:'updtDate'},
		{name:'showFlag'},
		{name:'pgmId'},
		{name:'name'},
		{name:'fieldId'},
		{name:'rgstName'},
		{name:'fieldName'},
		{name:'statisticsFormulae'},
		{name:'statisticsFlagText'},
		{name:'seq'},
		{name:'fieldType'}
	],
	idProperty:'pgmId,name,fieldId,rgstName'
});