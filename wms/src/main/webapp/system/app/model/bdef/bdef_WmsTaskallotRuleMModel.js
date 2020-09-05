/**
 * 模块名称：任务切分规则配置界面MODEL
 * 模块编码：I201
 * 创建：huangb20160612
 */
Ext.define('cms.model.bdef.bdef_WmsTaskallotRuleMModel',{
	extend:'Ext.data.Model',
	idProperty:'enterpriseNo,expType,waveRule',
	fields:[
	        {name:'enterpriseNo'},
	        {name:'taskRuleid'},
			{name:'ruleType'},
			{name:'paraValue'},
			{name:'memo'},
			{name:'rgstName'},
			{name:'rgstDate'},
			{name:'taskType'},
			{name:'printType'},
			{name:'onedeliveronepick'},
			{name:'taskGetType'},
			{name:'taskRuleidText'},
			{name:'ruleTypeText'},
			{name:'taskTypeText'},
			{name:'printTypeText'},
			{name:'taskGetTypeText'},
			{name:'onedeliveronepickText'}
	       ]
});