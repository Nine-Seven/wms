Ext.define('cms.model.odata.odata_ExpCancelDModel', {
    extend: 'Ext.data.Model',
    fields: [
    {name:'enterpriseNo'},
	{name:'warehouseNo'},
	{name:'cancelNo'},
	{name:'expNo'},
	{name:'ownerNo'},
	{name:'expType'},
	{name:'operateDate'},
	{name:'expDate'},
	{name:'articleNo'},
	{name:'packingQty'},
	{name:'articleQty'},
	{name:'realQty'},
	{name:'status'},
	{name:'handleflag'},
	{name:'rgstName'},
	{name:'rgstDate'},
	{name:'updtName'},
	{name:'updtDate'},
	{name:'ownerArticleNo'},
	{name:'articleName'},
	{name:'barcode'},
	{name:'differenceQty'},//差异数量
	{name:'availableQty'},//可用数量
	{name:'noEnoughQty'},//缺少数量
	
	{name:'differenceBox'},		 
	{name:'differenceQmin'},
	{name:'differenceDis'},
	
	{name:'planBox'},		 
	{name:'planQmin'},
	{name:'planDis'},
	{name:'realBox'},
	{name:'realDis'},
	{name:'realQmin'},
	{name:'packingUnit'},
	{name:'packingUnitQmin'},
	{name:'unit'},
	{name:'packingSpec'},	
	{name:'qminOperatePacking'},
	{name:'unitPacking'},
	{name:'packingSpecQmin'},
	{name:'spec'}
	
	],
    idProperty:'enterpriseNo,warehouseNo,cancelNo,expNo'
});













