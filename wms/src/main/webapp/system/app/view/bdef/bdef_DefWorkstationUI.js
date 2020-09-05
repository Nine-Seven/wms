/**
 * 模块名称：打印机组与工作站关系维护
 * 模块编码：1H01
 * 创建：zhouhuan
 */
var workstationStore=Ext.create('cms.store.bdef.bdef_DefWorkstationStore',{autoLoad:true});
Ext.define('cms.view.bdef.bdef_DefWorkstationUI',{
	alias:'widget.bdef_DefWorkstationUI',
	title:$i18n.title1H01,//工作站与打印机组关系维护
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
		'cms.view.common.commMenu2',
		'cms.view.common.commMenu5',
		'cms.view.common.wms_DefFieldValCombo',
		'cms.view.common.bset_GroupCombo'
		],
	items:[
	{
		xtype : 'commMenuWidget2',
		id:'menu1H01',
    	region:'north'
	},{
		xtype:'grid',		
		region:'center',
		id:'grid_01_1H01',
		store:workstationStore,
		columns:[{			
			xtype : 'rownumberer',
			width : 30
		},{
			width : 90,
			text : $i18n.workstation_no,
			dataIndex : 'workstationNo'
		},{
			width : 120,
			text : $i18n.workstation_name,
			dataIndex : 'workstationName'
		},{
			width:120,
			text:$i18n.printer_group_no,
			dataIndex:'printerGroupNo'
		},{
			width : 120,
			text : $i18n.printer_group_name,
			dataIndex : 'printerGroupName'
		}],
		dockedItems : [{
			xtype : 'pagingtoolbar',
			store : workstationStore,
			dock : 'bottom',
			displayInfo : true
		}] 
	},{
		region:'south'
	}]
});