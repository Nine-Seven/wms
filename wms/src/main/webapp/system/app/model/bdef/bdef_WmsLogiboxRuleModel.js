/**
 * 商品资料
 * 模块编码：
 * @author JUN
 */
Ext.define('cms.model.bdef.bdef_WmsLogiboxRuleModel', {
    extend: 'Ext.data.Model',
    fields: [ 
        {name:'enterpriseNo'},
        {name:'ruleId'},
		{name:'splitboxFlagText'},
		{name:'onedeliveronepickText'},
		{name:'boxSkuLimit'},
		{name:'weightFlagText'},
		{name:'volFlagText'},
		{name:'allotRuleText'},
		{name:'areaRuleText'},  
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'splitboxFlagText'},
		{name:'onedeliveronepickText'},
		{name:'memo'},
		{name:'boxSkuLimit'},
		{name:'weightFlag'},
		{name:'volFlag'},
		{name:'areaRule'},
		{name:'allotRuleValue'},
		{name:'allotRule'},	
		{name:'splitboxFlag'},
		{name:'onedeliveronepick'},	 
    ],
    idProperty:'enterpriseNo,ruleId'
});