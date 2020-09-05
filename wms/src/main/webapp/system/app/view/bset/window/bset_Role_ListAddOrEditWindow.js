Ext.define('cms.view.bset.window.bset_Role_ListAddOrEditWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.bset_Role_ListAddOrEditWindow',
	layout : 'fit',
	id : 'bset_Role_ListAddOrEditWindow',
	width : 450,
	height : 160,
	modal:true,
	layout:'border',
	items:[{
		xtype:'form',
		region:'center',
		id:'bset_Role_ListAddOrEditForm',
		frame : true,
		layout:'form',
		defaults : {
			xtype : 'textfield',
			margin : '5 5 5 5',
			labelAlign:'right',
			allowBlank: true,
			labelWidth : 90
		},
		items:[{
				fieldLabel: $i18n.partno,
				id : 'partno1101',
				readOnly:true,
				allowBlank: false,
				beforeLabelTextTpl : required
			},{
				fieldLabel: $i18n.partname,
				id : 'partname1101',
				allowBlank: false,
				beforeLabelTextTpl : required
			}]
		},{
			region:'south',
			xtype:'commMenuWidget5',
			border:0,
			id:'menuWidget51101'
	}]
});
