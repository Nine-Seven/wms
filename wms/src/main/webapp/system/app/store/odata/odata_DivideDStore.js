Ext.define('cms.store.odata.odata_DivideDStore',{
	extend:'Ext.data.Store',
	model:'cms.model.odata.odata_DivideDModel',
	autoLoad:false,
	
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_DivideAction_getOdata_DivideD.action',
		reader:{
			type:'json',
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});