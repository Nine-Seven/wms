/**
 * 模块名称：策略配置-头档
 * 模块编码：I101
 * @author hcx 20160610
 */
Ext.define('cms.model.bdef.bdef_WmsDefstrategyModel',{
	extend:'Ext.data.Model',
	idProperty:'strategyType,strategyId',
	fields:[
        {name:'strategyType'},
        {name:'strategyId'},
        {name:'defaultFlag'},
        {name:'strategyName'},
        {name:'rgstName'},
        {name:'rgstDate'},
        {name:'defaultFlagText'}
        
	]
});