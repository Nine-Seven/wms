/**
 * 模块名称：科目和计费项目的关系维护bill_Account_MModel
 * 模块编码：B301
 * 创建：chensr 
 */
Ext.define('cms.model.bset.bill_Account_MModel', {
    extend: 'Ext.data.Model',
    idProperty:'warehouseNo,ownerNo,accountNo',
    fields: [
		{name:'warehouseNo'},
		{name:'ownerNo'},
		{name:'accountNo'},
		{name:'accountName'},
		{name:'cycle'},
		{name:'nextAccountDate'},
		{name:'accountType'},
		{name:'discountFlag'},
		{name:'value1'},	
		{name:'value2'},
		{name:'discountAccountNo'},
		{name:'remark'},	
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		{name:'accountTypeText'},
		{name:'discountFlagText'}		
    ]
});