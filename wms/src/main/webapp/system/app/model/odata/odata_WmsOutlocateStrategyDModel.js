/**
 * 模块名称：出货定位策略配置-明细
 * 模块编码：3910
 * @author liucl 20160727
 */
Ext.define('cms.model.odata.odata_WmsOutlocateStrategyDModel',{
	extend:'Ext.data.Model',
	idProperty:'enterpriseNo,locateStrategyId,locateRuleId',
	fields:[
        {name:'enterpriseNo'},
        {name:'locateStrategyId'},
        {name:'locateRuleName'},
        {name:'locateRuleId'},
        {name:'deliverObjLevel'},
        {name:'PFlag'},
        {name:'MFlag'},
        {name:'WFlag'},
        {name:'SFlag'},
        {name:'DFlag'},
        {name:'commitType'},
        {name:'shortqtyType'},
       
        {name:'rsvControl1'},
        {name:'rsvControl2'},
        {name:'rsvControl3'},
        {name:'rsvControl4'},
        {name:'rsvControl5'},
        {name:'rsvValue1'},
        {name:'rsvValue2'},
        {name:'rsvValue3'},
        {name:'rsvValue4'},
        {name:'rsvValue5'},
        {name:'rgstName'},
        {name:'rgstDate'},
        {name:'updtName'},
        {name:'updtDate'},
        
        
        {name:'deliverObjLevelText'},
        {name:'PFlagText'},
        {name:'MFlagText'},
        {name:'WFlagText'},
        {name:'SFlagText'},
        {name:'DFlagText'},
        {name:'rgstName'},
        {name:'rgstDate'},
        {name:'commitTypeText'},
        {name:'shortqtyTypeText'}
        
	]
});