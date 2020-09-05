/**
 * 模块名称：工作站维护
 * 模块编码：1H01
 * 创建：zhouhuan
 */
Ext.define('cms.view.bdef.window.bdef_DefWorkstationAddorEditWindow',{
	extend:'Ext.window.Window',
	alias:'widget.bdef_DefWorkstationAddorEditWindow',
	layout:'border',
	id:'bdef_DefWorkstationAddorEditWindow',
	width:500,
	height:180,
	modal:true,
	items:[
	{
 	    xtype:'form',
 	    region:'center',
 	    id:'form_01_1H01',
 	  	frame : true,
		layout : 'form',
		defaults : {
 	    	xtype : 'textfield',
 	    	margin : '5 5 5 5',
			labelWidth :100,
			labelAlign:'right'	
 	    },
		items:[
    	{
	    	fieldLabel:$i18n.warehouse,
	    	id:'warehouse_no1H01',
	    	readOnly:true,
	    	allowBlank:false,
	    	beforeLabelTextTpl : required
	    },{
	    	fieldLabel:$i18n.workstation_no,
	    	id:'workstation_no1H01',
	    	allowBlank:false,
	    	beforeLabelTextTpl : required
	    },{
	    	fieldLabel:$i18n.workstation_name,
	    	id:'workstation_name1H01',
	    	allowBlank:false,
	    	beforeLabelTextTpl : required
	    },{
	    	xtype:'bset_GroupCombo',
	    	fieldLabel:$i18n.printer_group,//打印机群组
	    	id:'printer_group1H01',
	    	store:Ext.create('cms.store.bset.bset_GroupComboStore').load(),
	    	allowBlank:false,
	    	editable:false,
	    	beforeLabelTextTpl : required
	    }]
	},{
		region:'south',
		xtype:'commMenuWidget5',
		border:0,
		id:'menuWidget1H01'
	}]
});