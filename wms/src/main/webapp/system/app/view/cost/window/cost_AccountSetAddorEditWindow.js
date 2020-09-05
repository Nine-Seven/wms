/**
 * 模块名称：科目代码维护
 * 模块编码：303
 * 创建：hcx 
 */
Ext.define('cms.view.cost.window.cost_AccountSetAddorEditWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.cost_AccountSetAddorEditWindow',
	id : 'cost_AccountSetAddorEditWindow',
	width : 400,
	height : 150,
	modal:true,
	layout:'border',
	items:[
	{
		xtype:'form',
		region:'center',
		id:'form_01_B303',
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
			fieldLabel: $i18n.account_no,//科目代码
			id : 'accountNo_1',
			allowBlank: false,
			beforeLabelTextTpl : required
		},{
			fieldLabel: $i18n.account_name,//科目名称
			id : 'accountName_1',
			allowBlank: false,
			beforeLabelTextTpl : required
		},{
			fieldLabel : $i18n.remark,  //备注
			id:'remark_1'		
		}]
	},{
		region:'south',
		xtype:'commMenuWidget5',
		border:0,
		id:'cost_AccountSetB303'
	}]
});