Ext.define('cms.store.odata.odata_OutstockMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.odata.odata_OutstockMModel',
	autoLoad:false,
	
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_OutstockDAction_getOdata_OutstockM.action',
		reader:{
			type:'json',
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});