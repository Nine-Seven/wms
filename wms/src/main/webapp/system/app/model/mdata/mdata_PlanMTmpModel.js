Ext.define('cms.model.mdata.mdata_PlanMTmpModel', {
    extend: 'Ext.data.Model',
    fields: [		
		{name:'enterpriseNo'},
		{name:'warehouseNo'},
		{name:'ownerNo'},
		{name:'ownerArticleNo'},
		{name:'originQty'},
		{name:'packingQty'},
		{name:'SCellNo'},
		{name:'DCellNo'},
		{name:'planRemark'},
		{name:'status'},
		{name:'rowId'},
		{name:'produceDate'},
		{name:'expireDate'},
		{name:'orgNo'},
		
		{name:'planBox'},
		{name:'planDis'},
		{name:'planQmin'},
		{name:'qminOperatePacking'},
		{name:'packingUnit'},
		{name:'packingSpec'}
    ]
});