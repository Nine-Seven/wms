/**
 * 模块名称：科目代码维护
 * 模块编码：B303
 * 创建：hcx 
 */
Ext.define('cms.model.cost.cost_AccountSetModel', {
    extend: 'Ext.data.Model',
    idProperty:'enterpriseNo,',
    fields: [
        {name:'enterpriseNo'},
		{name:'accountNo'},
		{name:'accountName'},
		{name:'createFlag'},
		{name:'remark'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'}
    ]
});