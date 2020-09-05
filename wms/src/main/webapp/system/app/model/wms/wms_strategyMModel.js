Ext.define('cms.model.wms.wms_strategyMModel', {
	extend : 'Ext.data.Model',
	fields : [

        {name:'enterpriseNo'},
        {name:'strategyId'},
		{name:'strategyName'},
		{name:'defalutFlag'},
		{name:'defalutFlagText'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'}
		
	],
	idProperty : ''
});