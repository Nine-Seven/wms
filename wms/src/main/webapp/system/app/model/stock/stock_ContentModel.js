/**
 *库存信息
 *创建：lich
 */
Ext.define('cms.model.stock.stock_ContentModel', {
    extend: 'Ext.data.Model',
    fields: [			
	{name:'warehouseNo'},
	{name:'cellNo'},
	{name:'cellId'},
	{name:'ownerNo'},
	{name:'deptNo'},
	{name:'containerNo'},
	{name:'articleNo'},
	{name:'articleId'},
	{name:'packingQty'},
	{name:'qty'},
	{name:'outstockQty'},
	{name:'instockQty'},
	{name:'unusualQty'},
	{name:'status'},
	{name:'flag'},
	{name:'instockType'},
	{name:'hmManualFlag'},
	{name:'labelNo'},
	{name:'stockType'},
	{name:'stockValue'},
	{name:'rgstName'},
	{name:'rgstDate'},
	{name:'updtName'},
	{name:'updtDate'},
	
	{name:'noEnoughQty'},
	{name:'ownerName'},
	{name:'deptName'},
	{name:'containerName'},
	{name:'articleName'},
	{name:'barcode'},
	{name:'packingQty'},
	{name:'unit'},
	{name:'spec'},
	{name:'factory'},
	{name:'boxQty'},
	{name:'outstockQty'},
	{name:'lotNo'},
	{name:'productDate'},
	{name:'expireDate'},
	{name:'itemType'},
	
	{name:'mvHandFlag'},
	//{name:'scatterednum'},
	{name:'availableQty'},
	{name:'SCellNo'},
	{name:'DCellNo'},
	//{name:'wholenum'},
	{name:'quality'},
	{name:'produceDate'},
	{name:'demandQty'},
	{name:'alertQty'},
	{name:'keepCell'},
	{name:'maxQty'},
	{name:'custAlias'},	
	{name:'articleName'},
	{name:'recedeQty'},	
	{name:'subLabelNo'},
	{name:'ownerArticleNo'},
	{name:'checkColumn',type:'boolean',defaultValue: false},
	{name:'labelNo'},//单号
	{name:'quality'},
	{name:'textQuality'},
	{name:'budegtQty'},
	{name:'pkQty'},//数量
	{name:'pkOutstockQty'},//预下数量
	{name:'pkInstockQty'},//预上数量
	{name:'packingUnit'},//包装单位
	{name:'wareNo'},
	{name:'wareName'},
	{name:'areaNo'},
	{name:'areaName'},
	
	{name:'realBox'},//可用箱数
	{name:'realDis'},//可用散数
	{name:'realQmin'},//可用中包数
	{name:'planBox'},//计划箱数
	{name:'planDis'},//计划散数
	{name:'planQmin'},//计划中包数
	{name:'qminOperatePacking'},//最小操作包装数量
	
	{name:'packingSpec'}
	
	
	
	
	
    ],
    idProperty:'warehouseNo,cellNo,cellId'
});