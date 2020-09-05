/**
 * 模块名称：打印机群组与打印机组关系维护
 * 模块编码：1T01
 * 创建：hcx 
 */
Ext.define('cms.view.bdef.window.pntdef_PrinterGrpgatherAddorEditWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.pntdef_PrinterGrpgatherAddorEditWindow',
	id : 'pntdef_PrinterGrpgatherAddorEditWindow',
	width : 500,
	height : 160,
	modal:true,
	layout:'border',
	items:[
	{
		xtype:'form',
		region:'center',
		id:'form_01_1T01',
		frame : true,
		layout:'form',
		defaults : {
			xtype : 'textfield',
			margin : '5 5 5 5',
			labelAlign:'right',
			allowBlank: true,
			labelWidth : 120
		},
		items:[
		{
			fieldLabel: $i18n.warehouse,//仓别
			id : 'warehouse1T01',
			readOnly:true,
			allowBlank: false,
			beforeLabelTextTpl : required
		},{
			fieldLabel: $i18n.grpgather_no,//打印机群组代码
			id : 'grpgather_no1T01',
			allowBlank: false,
			beforeLabelTextTpl : required
		},{
			fieldLabel: $i18n.grpgather_name,//打印机群组名称
			id : 'grpgather_name1T01',
			allowBlank: false,
			beforeLabelTextTpl : required
		}]
	},{
		region:'south',
		xtype:'commMenuWidget5',
		border:0,
		id:'menuWidget51T01'
	}]
});