/**
 * 模块名称：BYM出货订单接口 window
 * 模块编码：3911
 * 创建：chensr
 */
Ext.define('cms.view.odata.window.odata_JkBYMSheetAddOrEditWindow',{
	extend:'Ext.window.Window',
	alias:'widget.odata_JkBYMSheetAddOrEditWindow',
	layout:'border',
	id:'odata_JkBYMSheetAddOrEditWindow',
	width:600,
	height:180,
	modal:true,
	items : [
	     	{
	     		xtype : 'form',
	     		region : 'north',
	     		id:'IdForm3911',
	     		layout:'border',
	     		height:150,
	     		frame : true,
	     	    	 layout:{
	     		   			type:'table',
	     		   			columns:2
	     	   		},
	     	   		defaults : {
	     	   			xtype : 'textfield',
	     	   			labelWidth : 120,
	     	   			labelAlign:'right'			
	     	   		},
	     	   		items:[
	     	   		{
	     	   			fieldLabel:'订单',    //字段名称
     					id:'sheetNo3911',	     					
     					xtype:'textfield',
		     			maxLength:20,		
			     	    allowBlank : false,
	     			    beforeLabelTextTpl : required
	     	   		},{
	     	   			fieldLabel:$i18n.warehouse,  //仓别
	     	   			id:'warehouseNo3911',
	     	   			xtype:'textfield',
	     	   			beforeLabelTextTpl : required
	     			},{
	     				fieldLabel:'创建人',  //创建人
	     	   			id:'rgstName3911',
	     	   			xtype:'textfield',
	     	   			beforeLabelTextTpl : required
	     			},{
	     				fieldLabel:'创建时间',    //创建时间
	     				id:'rgstDate3911',	     					
	     				xtype:'textfield',		
			     	    allowBlank : false,
			     	    beforeLabelTextTpl : required
	     			}]
	     	},	
	     	{
	     		region:'south',
	     		xtype:'commMenuWidget5',
	     		border:0,
	     		id:'odata_JkBYMSheet3911'
	     	}]
	     });