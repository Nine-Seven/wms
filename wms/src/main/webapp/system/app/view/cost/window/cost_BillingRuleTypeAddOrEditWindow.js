/**
 * 模块名称：计费取值方式维护
 * 模块编码：B904
 * 创建：czh
 */
Ext.define('cms.view.cost.window.cost_BillingRuleTypeAddOrEditWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.cost_BillingRuleTypeAddOrEditWindow',
	id : 'cost_BillingRuleTypeAddOrEditWindow',
	width : 400,
	height : 180,
	modal:true,
	layout:'border',
	items:[
	{
		xtype:'form',
		region:'center',
		id:'form_01_B904',
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
			fieldLabel: $i18n.billingType1,//计费类型编码
			id : 'billingType_1',
			allowBlank: false,
			beforeLabelTextTpl : required
		},{
			fieldLabel: $i18n.billingTypeName,//科目名称
			id : 'billingTypeName_1',
			allowBlank: false,
			beforeLabelTextTpl : required
		},{
			xtype:'wms_DefFieldValCombo',
		    fieldLabel:$i18n.status,
		    id:'statusB904',
		    store:Ext.create("cms.store.common.comboStore").load(
		    {
					params:{str:"COST_BILLING_TYPE,STATUS"}
		    }),
		    editable:false,
		    allowBlank:false,
		    beforeLabelTextTpl:required
		},{
			fieldLabel : $i18n.memo,  //备注
			id:'memo_1'		
		}]
	},{
		region:'south',
		xtype:'commMenuWidget5',
		border:0,
		id:'cost_BillingRuleTypeB904'
	}]
});