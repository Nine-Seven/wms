/**
 * 模块名称：消费清单Model
 * 创建：lich 
 */
Ext.define('cms.model.bset.bill_Expenses_ListModel', {
    extend: 'Ext.data.Model',
    fields: [
			{name:'warehouseNo'},
			{name:'ownerNo'},
			{name:'billingProject'},
			{name:'groupNo'},
			{name:'beginDate'},
			{name:'endDate'},
			{name:'area'},
			{name:'tray'},
			{name:'qty'},
			{name:'volume'},
			{name:'weight'},
			{name:'reserved1'},
			{name:'reserved2'},
			{name:'reserved3'},
			{name:'reserved4'},
			{name:'reserved5'},
			{name:'reserved6'},
			{name:'serialNo'},
			{name:'billingType'},
			{name:'flag'},
			{name:'value'}
    ]
});