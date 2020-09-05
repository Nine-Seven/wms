/**
 * 模块名称：设备组 window
 * 模块编码：1S01
 * 创建：chensr
 */
Ext.define('cms.view.bdef.window.DeviceDivideGroupAddOrEditWindow',{
	extend:'Ext.window.Window',
	alias:'widget.DeviceDivideGroupAddOrEditWindow',
	layout:'border',
	id:'DeviceDivideGroupAddOrEditWindow',
	width:600,
	height:160,
	modal:true,
	items : [{
	     		xtype : 'form',
	     		region : 'north',
	     		id:'DivideGroupForm1S01',
	     		layout:'border',
	     		height:100,
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
	     	   		items:[{
	    				fieldLabel: '设备组',
	    				id : 'deviceGroupNo1S01',
	    				allowBlank:false,
	    				beforeLabelTextTpl : required
	     	   		},{
		     	   	   	xtype: 'wms_DefFieldValCombo',
				 	    fieldLabel: '状态', 
				 	    id:'status1S01',
				 	    store:Ext.create("cms.store.common.comboStore").load(
						{
						 	params:{str:"DEVICE_DIVIDE_GROUP,STATUS"}
						}), 
						allowBlank:false,
						beforeLabelTextTpl : required					 
	     	   	    },{
	     	   			fieldLabel: '设备组名称',
	     	   			id : 'deviceGroupName1S01',
	     	   			allowBlank:false,
	     	   			beforeLabelTextTpl : required
	     	   		},{
	     	   	   	    xtype: 'wms_DefFieldValCombo',
			 	    	fieldLabel: '设备组类型', 
			 	    	id:'UseType1S01',
			 	    	store:Ext.create("cms.store.common.comboStore").load(
					 	{
					 	   params:{str:"DEVICE_DIVIDE_GROUP,USETYPE"}
					 	}), 
					 	allowBlank:false,
					 	beforeLabelTextTpl : required
					 },/*{
		     	   	   	xtype: 'wms_DefFieldValCombo',
				 	    fieldLabel: '是否资源试算', 
				 	    id:'computeFlag1S01',
				 	    store:Ext.create("cms.store.common.comboStore").load(
						 {
						 	params:{str:"N,COMPUTE_FLAG"}
						 }), 
						 allowBlank:false,
						 beforeLabelTextTpl : required				 
					 },*/{
						 xtype: 'wms_DefFieldValCombo',
					 	 fieldLabel: '默认组', 
					 	 id:'defaultFlag1S01',
					 	 store:Ext.create("cms.store.common.comboStore").load(
					     {
							 params:{str:"DEVICE_DIVIDE_GROUP,DEFAULTFLAG"}
						 }), 
						 allowBlank:false,
						 beforeLabelTextTpl : required						 
					 }
	     	   	]},	
	     		{ 
	     			region:'south',
	     			xtype:'commMenuWidget5',
	     			border:0,
	     			id:'menuWidget1S01'
	     		}]
	     });