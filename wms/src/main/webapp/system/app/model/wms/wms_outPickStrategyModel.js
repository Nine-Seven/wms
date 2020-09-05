Ext.define('cms.model.wms.wms_outPickStrategyModel', {
	extend : 'Ext.data.Model',
	fields : [

        {name:'enterpriseNo'},
        {name:'pickStrategyId'},
		{name:'pickStrategyName'},
		{name:'pickDiffFlag'},
		{name:'pickAutoFlag'},
		{name:'autoGetdivideFlag'},
		{name:'autoDividesaveFlag'}, 
		{name:'pickDiffName'},
		{name:'pickAutoName'},
		{name:'autoGetdivideName'},
		{name:'autoDividesaveName'}, 
		{name:'rsvValue1'},
		{name:'rsvValue2'},
		{name:'rsvValue3'},
		{name:'rsvValue4'},
		{name:'rsvValue5'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'}  
	],
	idProperty : 'enterpriseNo,pickStrategyId'
});