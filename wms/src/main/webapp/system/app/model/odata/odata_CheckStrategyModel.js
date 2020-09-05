/**
 * 模块名称：复核策略配置
 * 模块编码：I804
 * @author liucl 20160812
 */
Ext.define('cms.model.odata.odata_CheckStrategyModel',{
	extend:'Ext.data.Model',
	idProperty:'enterpriseNo,checkType,checkStrategyId',
	fields:[
        {name:'enterpriseNo'},
        {name:'checkType'},
        {name:'checkStrategyId'},
        {name:'checkStrategyName'},
        {name:'skipPickFlag'},
        {name:'checkLevel'},
        {name:'autoCloseBox'},
        {name:'packAdvance'},
        {name:'weightFlag'},
        {name:'volumFlag'},
              
        {name:'rsvValue1'},
        {name:'rsvValue2'},
        {name:'rsvValue3'},
        {name:'rsvValue4'},
        {name:'rsvValue5'},
        {name:'rgstName'},
        {name:'rgstDate'},
        {name:'updtName'},
        {name:'updtDate'},
        
        
        {name:'checkTypeText'},
        {name:'skipPickFlagText'},
        {name:'checkLevelText'},
        {name:'autoCloseBoxText'},
        {name:'packAdvanceText'},
        {name:'weightFlagText'},
        {name:'volumFlagText'}
      
        
	]
});