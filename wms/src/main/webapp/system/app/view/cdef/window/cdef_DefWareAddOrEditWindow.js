Ext.define('cms.view.cdef.window.cdef_DefWareAddOrEditWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.cdef_DefWareAddOrEditWindow',
	id : 'cdef_DefWareAddOrEditWindow',
	width : 500,
	height : 250,
	layout:'border',
	modal:true,
	items:[{
		xtype:'form',
		region:'center',
		id:'cdef_DefWareAddOrEditForm',
		frame : true,
		layout:'form',
		defaults : {
			xtype : 'textfield',
			margin : '5 5 5 5',
			labelAlign:'right',
			allowBlank: false,
			labelWidth : 90
		},
		items:[{
				fieldLabel: $i18n.warehouse_no,
				id : 'warehouse_no2101',
				readOnly : true,
				beforeLabelTextTpl : required
			},{
			 	xtype:'wms_DefFieldValCombo',
       	      	fieldLabel : $i18n.orgNo,// 机构代码
				id : 'orgNo2101',	
	       	    store:Ext.create("cms.store.common.comboStore").load(
	       	    {
	       	         params:{str:"N,ORG_NO"}
	       	    }),
	       	    allowBlank : false,
	       	    beforeLabelTextTpl : required
			},{
				fieldLabel: $i18n.ware_no,
				id : 'ware_No2101',
				maxLength:5,
				beforeLabelTextTpl : required
			},{
				fieldLabel: $i18n.ware_name,
				id : 'ware_Name2101',
				maxLength:30,
				beforeLabelTextTpl : required
			},{
				xtype:'textareafield',
				fieldLabel: $i18n.ware_remark,
				id : 'ware_Remark2101',
				maxLength:50,
				allowBlank: true
			}]
	},
	{
		region:'south',
		xtype:'commMenuWidget5',
		border:0,
		id:'menuWidget52101'
	}]
});
