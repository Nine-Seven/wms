Ext.define('cms.store.odata.odata_WmsWavePlanDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.odata.odata_WmsWavePlanDModel',
	autoLoad:false,
	
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_WmsWavePlanAction_getWmsWavePlanD.action',
		reader:{
			type:'json',
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});