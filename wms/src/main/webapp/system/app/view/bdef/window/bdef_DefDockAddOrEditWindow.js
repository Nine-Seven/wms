Ext.define('cms.view.bdef.window.bdef_DefDockAddOrEditWindow',{
 	extend:'Ext.window.Window',
 	alias:'widget.bdef_DefDockAddOrEditWindow',
 	layout:'border',
    width : 450,
	height : 200,
 	modal:true,
 	id:'bdef_DefDockAddOrEditWindow',
 	items:[{
 	    xtype:'form',
 	    region:'center',
 	    id:'bdef_DefDockAddOrEditForm1I01',
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
  		 	//xtype:'bdef_DefDockCombo',
 	    	fieldLabel:$i18n.dock_no3,
 	    	id:'dock_no1I01',
 	    	maxLength:3,
 	    	allowBlank:false,
 	    	beforeLabelTextTpl : required
 	    	//store:Ext.create('cms.store.bdef.bdef_DefDockComboStore').load()
 	    },{
 	    	fieldLabel:$i18n.dock_name3,
 	    	id:'dock_name1I01',
 	    	maxLength:10,
 	    	allowBlank:false,
 	    	beforeLabelTextTpl : required
 	    },{
 	    	xtype:'wms_DefFieldValCombo',
 	    	fieldLabel:$i18n.dock_type,
 	    	id:'dock_type1I01',
 	    	store:Ext.create("cms.store.common.comboStore").load(
			{
					params:{str:"N,DOCK_TYPE"}
			}),
 	    	allowBlank:false,
 	    	beforeLabelTextTpl : required
 	    },{ 
 	    	xtype:'wms_DefFieldValCombo',
 	    	fieldLabel:$i18n.adjust_board,
 	    	id:'adjust_board1I01',
 	    	store:Ext.create("cms.store.common.comboStore").load(
			{
					params:{str:"BDEF_DEFDOCK,ADJUST_BOARD"}
			})
 	    },{
 	    	xtype:'wms_DefFieldValCombo',
 	    	fieldLabel:$i18n.locate_type,
 	    	id:'locate_type1I01',
 	    	store:Ext.create("cms.store.common.comboStore").load(
			{
					params:{str:"BDEF_DEFDOCK,LOCATE_TYPE"}
			})
 	    }]
 	},{
 			region:'south',
			xtype:'commMenuWidget5',
			border:0,
			id:'menuWidget1I01'
 	}]
});