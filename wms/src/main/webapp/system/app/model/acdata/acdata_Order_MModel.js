
Ext.define('cms.model.acdata.acdata_Order_MModel', {
    extend: 'Ext.data.Model',
    idProperty:'orderNo',
    fields: [
		{name:'orderNo'},
		{name:'sourceNo'},
		{name:'sdate'},
		{name:'ownerAlias'},
		{name:'ownerProvince'},
		{name:'ownerCity'},
		{name:'ownerZone'},	
		{name:'ownerAddr'},
		{name:'ownerLinkman'},
		{name:'ownerPhone'},
		{name:'custAlias'},	
		{name:'custProvince'},
		{name:'custCity'},
		{name:'custZone'},
		{name:'custAddr'},	
		{name:'custLinkman'},
		{name:'custPhone'},
		{name:'status'},	
		{name:'remark'},
		{name:'rgstName'},
		{name:'rgstDate'},	
		{name:'updtName'},
		{name:'updtDate'},	
		{name:'statusText'},
		{name:'sdateText'}
    ]
});