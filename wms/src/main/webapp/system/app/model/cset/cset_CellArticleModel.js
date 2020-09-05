/**
 * 创建人:JUN
 */
Ext.define('cms.model.cset.cset_CellArticleModel', {
    extend: 'Ext.data.Model',
    fields: [
            {name:'warehouseNo'},
			{name:'articleNo'},
			{name:'ownerNo'},
			{name:'pickType'},
			{name:'wareNo'},
			{name:'areaNo'},
			{name:'stockNo'},
			{name:'AStockNo'},
			{name:'stockX'},
			{name:'cellNo'},
			{name:'packingQty'},
			{name:'lineId'},
			{name:'maxQtyA'},
			{name:'alertQtyA'},
			{name:'suppQtyA'},
			{name:'maxQtyNa'},
			{name:'alertQtyNa'},
			{name:'suppQtyNa'},
			{name:'keepCells'},
			{name:'keepCellsA'},
			{name:'rgstName'},
			{name:'rgstDate'},
			{name:'updtName'},
			{name:'updtDate'},
			
			{name:'barcode'},
			{name:'ownerArticleNo'},
			{name:'articleName'},
			{name:'ownerName'},
			{name:'warehouseNo'},
			{name:'ownerNo'},
			{name:'articleNo'},
			{name:'pickType'},
			{name:'primaryFlag'},
			{name:'qty'},
			{name:'outStockType'},
			{name:'demandQty'},
			{name:'availableQty'},
			{name:'maxQtys'},
			{name:'wareName'},
			{name:'areaName'},
			{name:'lineName'},
			{name:'maxQtyBox'},
			{name:'alertQtyBox'},
			{name:'suppQtyBox'},
			{name:'groupNo'},
			{name:'groupName'},
			{name:'pickTypeText'}
			
			
    ],
    idProperty:'warehouseNo,articleNo,ownerNo,pickType'
});
