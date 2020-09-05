/**
 * 模块名称：策略配置-明细
 * 模块编码：I101
 * @author hcx 20160610
 */
Ext.define('cms.model.bdef.bdef_WmsDefstrategyDModel',{
	extend:'Ext.data.Model',
	idProperty:'strategyType,strategyId,ruleOrder',
	fields:[
        {name:'strategyType'},
        {name:'strategyId'},
        {name:'ruleOrder'},
        {name:'ruleId'},
        {name:'limmitMaxqty'},
        {name:'limmitMixbatch'},
        {name:'limmitMixarticle'},
        {name:'limmitMaxcase'},
        {name:'limmitCelluse'},
        {name:'limmitMaxweight'},
        {name:'limmitMaxgroupno'},
        {name:'limmitRsv01'},
        {name:'limmitRsv02'},
        {name:'limmitRsv03'},
        {name:'limmitRsv04'},
        {name:'limmitRsv05'},
        {name:'limmitRsv06'},
        {name:'rgstName'},
        {name:'rgstDate'},
        {name:'memo'},
        {name:'limmitMixbatchText'},
        {name:'limmitMixarticleText'},
        {name:'limmitMaxqtyText'},
        {name:'limmitMaxcaseText'},
        {name:'limmitMaxweightText'},
        {name:'limmitMaxgroupnoText'},
        {name:'limmitCelluseText'},
        {name:"ruleName"},
        {name:'limmitRsv01Text'},
        {name:'limmitRsv02Text'},
        {name:'limmitRsv03Text'},
        {name:'limmitRsv04Text'},
        {name:'limmitRsv05Text'},
        {name:"limmitRsv06Text"}
        
	]
});