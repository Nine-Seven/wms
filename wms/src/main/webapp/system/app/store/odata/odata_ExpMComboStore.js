Ext.define('cms.store.odata.odata_ExpMComboStore',{
	extend:'Ext.data.Store',
	model:'cms.model.bdef.comboModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_ExpAction_getExp_MCombo',
		reader:{
			type:'json',
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});