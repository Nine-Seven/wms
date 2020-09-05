Ext.define('cms.model.odata.odata_WmsWavePlanMModel', {
    extend: 'Ext.data.Model',
    fields: [
//    {name:'enterpriseNo'},
//  	{name:'expType'},
//	{name:'strategyId'},
//	{name:'strategyName'},
//	{name:'betweenTimes'},
//	{name:'rgstName'},
//	{name:'rgstDate'},
//	{name:'dropValue'},
//	{name:'expTypeText'},
	
	
	//6-21添加
	{name:'enterpriseNo'},
	{name:'strategyName'},
	{name:'rgstDateText'},
	{name:'batchStrategyId'},
	{name:'rgstName'},
	{name:'rgstDate'}
	
	
	
	],
    idProperty:'enterpriseNo,batchStrategyId'
});

















