Ext.define('cms.store.odata.odata_WmsWavePlanMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.odata.odata_WmsWavePlanMModel',
	autoLoad:false,
	
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_WmsWavePlanAction_getWmsWavePlanM.action',
		reader:{
			type:'json',
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});