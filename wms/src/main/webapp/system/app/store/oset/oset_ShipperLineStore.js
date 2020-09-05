Ext.define('cms.store.oset.oset_ShipperLineStore',{
	extend:'Ext.data.Store',
	model:'cms.model.oset.oset_ShipperLineModel',
	pageSize : appConfig.getPageSize(),
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'bdef_DefShipperAction_queryShipperLine',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});