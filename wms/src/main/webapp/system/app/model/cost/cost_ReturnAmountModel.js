/**
 * 模块名称：收款-回款
 * 模块编码：B703
 * 创建：hcx 
 */
Ext.define('cms.model.cost.cost_ReturnAmountModel', {
    extend: 'Ext.data.Model',
    idProperty:'enterpriseNo,warehouseNo,ownerNo,checkNo,rowId',
    fields: [
        {name:'enterpriseNo'},
		{name:'warehouseNo'},
		{name:'ownerNo'},
		{name:'checkNo'},
		{name:'rowId'},
		{name:'realAmount'},
		{name:'rgstName'},
		{name:'rgstDate'},

		{name:'rgstNameText'},
		{name:'rgstDateText'},
		{name:'costFlagText'}
    ]
});