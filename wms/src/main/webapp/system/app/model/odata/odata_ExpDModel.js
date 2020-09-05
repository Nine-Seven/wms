/**
 * 创建人:JUN
 */
Ext.define('cms.model.odata.odata_ExpDModel', {
    extend: 'Ext.data.Model',
    fields: [
	{name:'warehouseNo'},
	{name:'ownerNo'},
	{name:'expNo'},
	{name:'rowId'},
	{name:'articleNo'},
	{name:'packingQty'},
	{name:'articleQty'},
	{name:'scheduleQty'},
	{name:'locateQty'},
	{name:'realQty'},
	{name:'unitCost'},
	{name:'ownerArticleNo'},
	{name:'produceCondition'},
	{name:'produceValue1'},
	{name:'produceValue2'},
	{name:'expireCondition'},
	{name:'expireValue1'},
	{name:'expireValue2'},
	{name:'qualityCondition'},
	{name:'qualityValue1'},
	{name:'qualityValue2'},
	{name:'lotnoCondition'},
	{name:'lotnoValue1'},
	{name:'lotnoValue2'},
	{name:'rsvbatch1Condition'},
	{name:'rsvbatch1Value1'},
	{name:'rsvbatch1Value2'},
	{name:'rsvbatch2Condition'},
	{name:'rsvbatch2Value1'},
	{name:'rsvbatch2Value2'},
	{name:'rsvbatch3Condition'},
	{name:'rsvbatch3Value1'},
	{name:'rsvbatch3Value2'},
	{name:'rsvbatch4Condition'},
	{name:'rsvbatch4Value1'},
	{name:'rsvbatch4Value2'},
	{name:'rsvbatch5Condition'},
	{name:'rsvbatch5Value1'},
	{name:'rsvbatch5Value2'},
	{name:'rsvbatch6Condition'},
	{name:'rsvbatch6Value1'},
	{name:'rsvbatch6Value2'},
	{name:'rsvbatch7Condition'},
	{name:'rsvbatch7Value1'},
	{name:'rsvbatch7Value2'},
	{name:'rsvbatch8Condition'},
	{name:'rsvbatch8Value1'},
	{name:'rsvbatch8Value2'},
	{name:'specifyField'},
	{name:'specifyCondition'},
	{name:'specifyValue1'},
	{name:'specifyValue2'},
	{name:'status'},
	{name:'errorStatus'},
	{name:'rgstDate'},
	{name:'expDate'},
	
	{name:'totalQty'},
	{name:'articleName'},
	{name:'barcode'},
	{name:'availableQty'},
	{name:'noEnoughQty'},
	
	{name:'ownerName'},
	{name:'condition'},
	{name:'specialBatch'},
	{name:'volumn'},
	{name:'weight'},
	{name:'boxs'},
	{name:'pobox'},
	{name:'popcs'},
	
	{name:'produceCond'},
	{name:'produceV1'},
	{name:'produceV2'},
	{name:'sourceexpNo'},
	{name:'labelNo'},
	{name:'planQty'},
	{name:'scanQty'},
	{name:'diffQty'},
	
	{name:'custNo'},
	{name:'custName'},
	{name:'strRgstDate2'},
	{name:'statusDesc'},
	
	//add by huangcx at 20160528
	{name:'qminOperatePacking'},
	{name:'unitPacking'},
	{name:'packingUnit'},
	{name:'packingUnitQmin'},
	{name:'Unit'},
	{name:'packingSpec'},
	{name:'packingSpecQmin'},
	{name:'spec'},
	{name:'planBox'},
	{name:'planDis'},
	{name:'planQmin'},
	{name:'realBox'},
	{name:'realDis'},
	{name:'realQmin'}
	//end add
    ]//,
    //idProperty:'warehouseNo,expNo,expType'
});













