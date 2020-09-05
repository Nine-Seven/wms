/**
 * 模块名称：租仓策略设置
 * 模块编码：B201
 * 创建：zhm
 */
Ext.define('cms.model.bset.bset_ValuePolicySetModel',{
	extend:'Ext.data.Model',
	idProperty:'warehouseNo,ownerNo,billingProject',
	fields:[
	        {name:'warehouseNo'},
			{name:'ownerNo'},
			{name:'billingProject'},
			{name:'billingUnit'},
			{name:'ruleId'},
			{name:'warehouseNoText'},
			{name:'ownerNoText'},
			{name:'billingProjectText'},
			{name:'billingUnitText'},
			{name:'valueFlagText'},
			{name:'remark'},
			{name:'rgstName'},
			{name:'rgstDate'},
			{name:'updtName'},
			{name:'updtDate'}
	       ]
});