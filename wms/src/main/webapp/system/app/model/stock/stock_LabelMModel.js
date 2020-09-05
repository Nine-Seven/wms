/**
 * 标签表
 * @author JUN
 */
Ext.define('cms.model.stock.stock_LabelMModel', {
    extend: 'Ext.data.Model',
    fields: [
	{name:'warehouseNo'},
	{name:'labelNo'},
	{name:'containerNo'},
	{name:'containerType'},
	{name:'batchNo'},
	{name:'sourceNo'},
	{name:'deliverArea'},
	{name:'status'},
	{name:'loadContainerNo'},
	{name:'ownerContainerNo'},
	{name:'ownerCellNo'},
	{name:'custNo'},
	{name:'trunckCellNo'},
	{name:'ASorterChuteNo'},
	{name:'checkChuteNo'},
	{name:'deliverObj'},
	{name:'useType'},
	{name:'lineNo'},
	{name:'currArea'},
	{name:'seqValue'},
	{name:'length'},
	{name:'width'},
	{name:'height'},
	{name:'equipmentNo'},
	{name:'reportId'},
	{name:'recheckNo'},
	{name:'midLabelNo'},
	{name:'bigExpNoFlag'},
	{name:'checkChuteInstatus'},
	{name:'stockType'},
	{name:'expDate'},
	{name:'chuteLabelFlag'},
	{name:'hmManualFlag'},
	{name:'rgstName'},
	{name:'rgstDate'},
	{name:'updtName'},
	{name:'updtDate'},
	
	{name:'custName'},
	{name:'statusText'},
	{name:'deliverObj'},
	{name:'countArticleNo'},
	
	{name:'articleName'},			//8-11添加
	{name:'articleNo'},
	{name:'articleQty'},
	{name:'realQty'},
	{name:'shipperDeliverNo'},
	{name:'barcode'}
	
	
 	],			
    idProperty:'warehouseNo,labelNo,containerNo,containerType'
});












