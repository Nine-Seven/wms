/**
 * 模块名称：杂项费用维护
 * 模块编码：B203
 * 创建：hcx 
 */
Ext.define('cms.model.cost.cost_OtherListModel', {
    extend: 'Ext.data.Model',
    idProperty:'enterpriseNo,warehouseNo,ownerNo,cost_no,costDate,sourceNo',
    fields: [
        {name:'enterpriseNo'},
        {name:'warehouseNo'},
		{name:'ownerNo'},
		{name:'costNo'},
		{name:'costName'},
		{name:'costDate'},
		{name:'costFlag'},
		{name:'costValue'},
		{name:'status'},
		{name:'checkNo'},
		{name:'remark'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		{name:'createFlag'},
		{name:'sourceNo'},
		
		{name:'ownerNoText'},
		{name:'costNoText'},
		{name:'costDateText'},
		{name:'costFlagText'},
		{name:'statusText'}
    ]
});