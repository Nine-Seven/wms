Ext.define('cms.model.print.print_RepairReportInfoModel',{
	extend:'Ext.data.Model',
	idProperty:'sourceNo',
	fields:[{
		name:'checkColumn',type:'boolean',defaultValue: false
	},{
		name:'ownerNo'
	},{
		name:'sourceNo'
	},{
		name:'custNo'
	},{
		name:'labelNo'
	}]
});