/**
 * 模块名称：科目和计费项目的关系维护bill_Account_Model
 * 模块编码：B301
 * 创建：chensr 
 */
Ext.define('cms.model.bset.bill_Account_Model', {
    extend: 'Ext.data.Model',
    idProperty:'warehouseNo,ownerNo,accountNo,enterpriseNo,accountLadder',
    fields: [
        {name:'enterpriseNo'},
		{name:'warehouseNo'},
		{name:'ownerNo'},
		{name:'accountNo'},
		{name:'accountName'},
		{name:'accountType'},
		{name:'accountLadder'},
		{name:'discountFlag'},
		{name:'value1'},	
		{name:'value2'},
		{name:'discountAccountNo'},
		{name:'remark'},	
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		{name:'accountLadderText'},
		{name:'accountTypeText'},
		{name:'discountFlagText'}		
    ]
});