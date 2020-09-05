/**
 * 模块名称：出货定位策略配置-头档
 * 模块编码：3910
 * @author liucl 20160727
 */
Ext.define('cms.model.odata.odata_WmsOutlocateStrategyMModel',{
	extend:'Ext.data.Model',
	idProperty:'locateStrategyId',
	fields:[
        {name:'enterpriseNo'},
        {name:'locateStrategyId'},
        {name:'defalutFlag'},
        {name:'strategyName'}, 
        {name:'rgstName'},
        {name:'rgstDate'},
        
        {name:'defalutFlagText'}
        
	]
});