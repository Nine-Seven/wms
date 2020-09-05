/**
 * 模块名称：杂项费用维护
 * 模块编码：B203
 * 创建：hcx 
 */
Ext.define('cms.model.cost.cost_OtherSetModel', {
    extend: 'Ext.data.Model',
    idProperty:'enterpriseNo,cost_no',
    fields: [
        {name:'enterpriseNo'},
		{name:'costNo'},
		{name:'costName'},
		{name:'remark'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		{name:'createFlag'},

		{name:'costNoText'}
    ]
});