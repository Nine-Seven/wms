/**
 * 模块名称：计费取值方式维护 window
 * 模块编码：B904
 * 创建：czh
 */

Ext.define('cms.view.cost.window.cost_BillingRuleAddOrEditWindow',{
	extend:'Ext.window.Window',
	alias:'widget.cost_BillingRuleAddOrEditWindow',
	layout:'border',
	id:'cost_BillingRuleAddOrEditWindow',
	width:850,
	height:550,
	modal:true,
	items:[
	   	{
	   		xtype:'form',
		    region:'center',
		    id:'form_02_B904',
		    layout: 
	        {
	        	type: 'table',
	        	columns: 3
	        },
		  	frame : true,
			defaults : {
		    	xtype : 'textfield',
		    	margin : '5 5 5 5',
				labelWidth :100,
				labelAlign:'right'	
		    },
			items:[
			{
				fieldLabel:$i18n.row_id,
				id:'ruleIdB904',
				allowBlank:false,
				readOnly:true,
				beforeLabelTextTpl : required
			},
			{
				xtype:'wms_DefFieldValCombo',
			    fieldLabel:$i18n.unit1,
			    id:'billingUnitB904',
			    store:Ext.create("cms.store.common.comboStore").load(
			    {
						params:{str:"COST_FORMULASET,BILLING_UNIT"}
			    }),
			    editable:false,
			    allowBlank:false,
			    beforeLabelTextTpl:required
			},{
				xtype:'wms_DefFieldValCombo',
			    fieldLabel:$i18n.status,
			    id:'status_1B904',
			    store:Ext.create("cms.store.common.comboStore").load(
			    {
						params:{str:"COST_BILLING_TYPE,STATUS"}
			    }),
			    editable:false,
			    allowBlank:false,
			    beforeLabelTextTpl:required
			}
			/*{
				fieldLabel:"单位",
				id:'billingUnitB904',
				allowBlank:false,
				beforeLabelTextTpl : required
			}*/,
			{
				fieldLabel:$i18n.valueFlag,
				id:'strategyNameB904',
				width:780,
				colspan:3
			},{
	    		xtype:'textareafield',
				fieldLabel:$i18n.standardSql,           //标准取值
				id:'standardSqlB904',
				height:150,
				width:780,
				colspan:3
	  		},{
	    		xtype:'textareafield',
				fieldLabel:$i18n.nonStandardSql,           //非标准取值
				id:'nonstandardSqlB904',
				height:150,
				width:780,
				colspan:3
	  		},
			{
				fieldLabel:"备注",
				id:'memo_1B904',
				width:780,
				height:50,
				colspan:3
			}]
		},		
		{
			region:'south',
			xtype:'commMenuWidget5',
			border:0,
     		id:'cost_BillingRuleB904'
	   	}
    ]
});