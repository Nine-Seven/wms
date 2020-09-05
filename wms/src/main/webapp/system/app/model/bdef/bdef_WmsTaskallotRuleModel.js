/**
 * 模块名称：任务切分规则配置明细界面MODEL
 * 模块编码：I201
 * 创建：huangb20160612
 */
Ext.define('cms.model.bdef.bdef_WmsTaskallotRuleModel',{
	extend:'Ext.data.Model',
	idProperty:'enterpriseNo,expType,waveRule',
	fields:[
	        {name:'allotRule'},
	        {name:'boxFlag'},
			{name:'paraValue'},
			{name:'taskType'},
			{name:'memo'},
			{name:'rgstName'},
			{name:'rgstDate'},
			{name:'printType'},
			{name:'taskGetType'},
			{name:'operateType'},
			{name:'outstockType'},
			{name:'operateTypeText'},
			{name:'allotRuleText'},
			{name:'boxFlagText'},
			{name:'taskTypeText'},
			{name:'printTypeText'},
			{name:'outstockTypeText'},
			{name:'taskGetTypeText'}
	       ]
});