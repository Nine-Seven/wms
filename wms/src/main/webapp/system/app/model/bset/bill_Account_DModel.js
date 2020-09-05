/**
 * 模块名称：科目和计费项目的关系维护bill_Account_DModel
 * 模块编码：B301
 * 创建：chensr 
 */
Ext.define('cms.model.bset.bill_Account_DModel', {
    extend: 'Ext.data.Model',
    idProperty:'warehouseNo,ownerNo,accountNo,billingProject',
    fields: [
		{name:'warehouseNo'},
		{name:'ownerNo'},
		{name:'accountNo'},
		{name:'billingProject'},
		{name:'projectName'},
		{name:'accountName'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'}		
    ]
});