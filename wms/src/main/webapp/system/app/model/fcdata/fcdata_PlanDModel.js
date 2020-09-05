/**
 * 盘点单明细
 * 创建人:周欢
 */
Ext.define('cms.model.fcdata.fcdata_PlanDModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name:'enterpriseNo'},
        {name:'warehouseNo'},
		{name:'ownerNo'},
		{name:'planNo'},
		{name:'wareNo'},
		{name:'areaNo'},
		{name:'stockNo'},
		{name:'articleNo'},
		{name:'ownerArticleNo'},
		{name:'cellNo'},
		{name:'planId'},
		{name:'groupNo'},
		
		{name:'articleName'},
		{name:'groupNo'},
		{name:'groupName'},
		{name:'wareName'},
		{name:'areaName'},
		{name:'stockName'}
    ],
    idProperty:'enterpriseNo,warehouseNo,ownerNo,planNo,wareNo,areaNo,stockNo,articleNo,cellNo'
});