/**
 * 盘点单主档
 * 创建人:周欢
 */
Ext.define('cms.model.fcdata.fcdata_PlanMModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name:'enterpriseNo'},
        {name:'warehouseNo'},
		{name:'ownerNo'},
		{name:'planType'},
		{name:'fcdataType'},
		{name:'planNo'},
		{name:'planDate'},
		{name:'beginDate'},
		{name:'endDate'},
		{name:'status'},
		{name:'createFlag'},
		{name:'planRemark'},
		{name:'sourcePlanNo'},
		{name:'sendFlag'},
		{name:'rgstName'},
		{name:'rgstDate'},
		{name:'updtName'},
		{name:'updtDate'},
		{name:'statusText'},
		{name:'orgNo'}
    ],
    idProperty:'enterpriseNo,warehouseNo,planNo'
});