/**
 * 模块名称：杂项费用维护
 * 模块编码：B203
 * 创建：hcx 
 */
Ext.define('cms.view.cost.window.cost_OtherSetAddorEditWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.cost_OtherSetAddorEditWindow',
	id : 'cost_OtherSetAddorEditWindow',
	width : 400,
	height : 120,
	modal:true,
	layout:'border',
	items:[
	{
		xtype:'form',
		region:'center',
		id:'form_02_B203',
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
			fieldLabel: $i18n.cost_no,//费用代码
			id : 'costNo_2',
			allowBlank: false,
			beforeLabelTextTpl : required
		},{
			fieldLabel: $i18n.cost_name,//费用名称
			id : 'costName_2',
			allowBlank: false,
			beforeLabelTextTpl : required
		}]
	},{
		region:'south',
		xtype:'commMenuWidget5',
		border:0,
		id:'cost_OtherSetB203'
	}]
});