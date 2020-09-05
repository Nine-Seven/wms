Ext.define('cms.view.bset.window.bset_GroupAddorEditWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.bset_GroupAddorEditWindow',
	id : 'bset_GroupAddorEditWindow',
	width : 500,
	height : 160,
	modal:true,
	layout:'border',
	items:[
	{
		xtype:'form',
		region:'center',
		id:'form_01_1G01',
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
			id : 'warehouse1G01',
			readOnly:true,
			allowBlank: false,
			beforeLabelTextTpl : required
		},{
			fieldLabel: $i18n.printer_group_no,//打印机群组代码
			id : 'printer_group_no1G01',
			allowBlank: false,
			beforeLabelTextTpl : required
		},{
			fieldLabel: $i18n.printer_group_name,//打印机群组名称
			id : 'printer_group_name1G01',
			allowBlank: false,
			beforeLabelTextTpl : required
		}]
	},{
		region:'south',
		xtype:'commMenuWidget5',
		border:0,
		id:'menuWidget51G01'
	}]
});