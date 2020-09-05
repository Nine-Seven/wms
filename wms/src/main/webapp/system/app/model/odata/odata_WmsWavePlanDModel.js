Ext.define('cms.model.odata.odata_WmsWavePlanDModel', {
    extend: 'Ext.data.Model',
    fields: [
             {name:'enterpriseNo'},
             {name:'expType'},
             {name:'strategyId'},
             {name:'strategyType'},
             {name:'seqOrder'},	
             {name:'maxOrder'},
             {name:'minOrder'},
             {name:'CDivideFlag'},	
             {name:'limmitShipperFlag'},
             {name:'divideSource'},
             {name:'divideCompute'},
             {name:'outTimes'},
             {name:'divideDeviceType'},
             {name:'divideDeliverAddress'},
             {name:'divideDeliverDate'},
             {name:'divideDeliverType'},
             {name:'rgstName'},
             {name:'rgstDate'},
             {name:'updtName'},
             {name:'updtDate'},
             {name:'updtDate'},
             {name:'pickEndDirectArea'},
             {name:'BDivideFlag'},
             
             {name:'strategyName'},
             
             //6-4添加
             {name:'ruleId'},
             {name:'ruleName'},
             {name:'rule_name'},
             {name:'skuLimmit'},
             {name:'taskOrder'},
             {name:'waitTimes'},
             {name:'status'},
             
             {name:'shipperNo'},
             {name:'devideSource'},
             {name:'itemTypeFlag'},
             {name:'areaLimmit'},
             {name:'CLimmit'},
             
             {name:'pickanddivide'},
             {name:'printType'},
             
             {name:'text'},		//6-22添加  试算配置   7-6
             {name:'batchStrategyId'},
             {name:'batchRuleId'},
             {name:'trialRuleId'},
             {name:'pickStrategyId'},
             {name:'pickStrategyName'},
             {name:'pickDiffFlag'},
             
             //{name:'ruleId'},
             {name:'memo'},
             {name:'allotRule'},
             {name:'batchRuleName'},
             {name:'orderSource'},     
             {name:'batchComputeType'},
             {name:'batchCompute'},
             {name:'deviceType'},
             {name:'custLogiboxRuleId'},
             {name:'divideLogiboxRuleId'},
             {name:'taskRuleId'},
             {name:'deliverType'},
             
             {name:'printEnvoice'},    //7-5,6添加 
             {name:'printWaybill'},
             {name:'printPacklist'},
             {name:'skucount'},
             {name:'printValue1'},
             {name:'printValue2'},
             {name:'intervalTimes'},
             {name:'skuCountMode'},
             {name:'printTypeTest'},
             
             //7-16添加   repeatTimes    deliverAddress
             {name:'repeatTimes'},
             {name:'deliverAddress'},
             {name:'lastPickFlag'},		//7-16添加
             //7-29添加
             {name:'CDivideFlag'},
             {name:'BDivideFlag'},
             //8-6添加  
             {name:'areaAllow'},
             {name:'areaLimmitValue'},
             //8-15添加
             {name:'ruleFlag'}
	],
    idProperty:'enterpriseNo,batchStrategyId,batchRuleId'
});






























