Ext.define('cms.store.odata.odata_ArrangeCusLabelStore',{
	extend:'Ext.data.Store',
	model:'cms.model.stock.stock_LabelMModel',
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_ArrangePackAction_getOdata_CusLabel.action',
		reader:{
			type:'json',
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});