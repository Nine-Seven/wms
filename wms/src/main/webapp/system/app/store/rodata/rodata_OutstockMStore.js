Ext.define('cms.store.rodata.rodata_OutstockMStore',{
	extend:'Ext.data.Store',
	model:'cms.model.rodata.rodata_OutstockMModel',
	//autoLoad:true,
	proxy:{
		type:'ajax',
		method:'post',
		url:'rodata_OutstockMAction_getRodata_OutstockM',
		reader:{
			type:'json',
			root:'rootList',
			totalProperty:'totalCount'
		}
	},
	listeners:{
	load:function( th, records,  successful,  eOpts ){
		//debugger;
		console.log(records);
	}
	}
});