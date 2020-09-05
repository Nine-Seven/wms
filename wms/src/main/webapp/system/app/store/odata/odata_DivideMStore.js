Ext.define('cms.store.odata.odata_DivideMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.odata.odata_DivideMModel',
	autoLoad:false,
	
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_DivideAction_getOdata_DivideM.action',
		reader:{
			type:'json',
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});