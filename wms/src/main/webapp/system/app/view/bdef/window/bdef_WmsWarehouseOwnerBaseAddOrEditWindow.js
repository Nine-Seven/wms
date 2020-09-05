/**
 * 模块名称：货主仓别参数配置 window
 * 模块编码：1N01
 * 创建：chensr
 */
Ext.define('cms.view.bdef.window.bdef_WmsWarehouseOwnerBaseAddOrEditWindow',{
	extend:'Ext.window.Window',
	alias:'widget.bdef_WmsWarehouseOwnerBaseAddOrEditWindow',
	layout:'border',
	id:'bdef_WmsWarehouseOwnerBaseAddOrEditWindow',
	width:600,
	height:240,
	modal:true,
	items : [
	     	{
	     		xtype : 'form',
	     		region : 'north',
	     		id:'IdForm1N01',
	     		layout:'border',
	     		height:240,
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
	     				    xtype:'bdef_DefOwnerCombo',
	     				    fieldLabel:$i18n.owner_no,  //货主编号
	     				    id:'ownerBaseNo1N01',
	     				    store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
	     				    displayField : 'dropValue',
	     				    valueField : 'value',
	     				    allowBlank : false,
	     				    beforeLabelTextTpl : required
	     				},{
			     			fieldLabel:$i18n.warehouse,  
			     			id:'warehouseNo1N01',
			     			xtype:'textfield',
			     			beforeLabelTextTpl : required
			     		},{
	     					fieldLabel:$i18n.group_no_text,   //业务
	     					id:'groupNo1N01',
	     					xtype:'wms_DefFieldValCombo',
	     					 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		     			    	    proxy:{
		     							type:'ajax',
		     							async:false,
		     							method:'post',
		     							url:'bdef_WmsWarehouseOwnerBaseAction_getGroupNoComboList',
		     							reader:{
		     								root:'rootList',
		     								totalProperty:'totalCount'
		     							}
		     						}
		     				    }).load(),
		     				    displayField : 'dropValue',
		     				    valueField : 'value',
		     				    allowBlank : false,
		     				    beforeLabelTextTpl : required
	     				},
	     				{
	     					fieldLabel:$i18n.sub_group_no_text,  //子业务
	     					id:'subGroupNo1N01',
	     					xtype:'wms_DefFieldValCombo',
	     					store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		     			   	    proxy:{
		     						type:'ajax',
		     						async:false,
		     						method:'post',
		     						url:'bdef_WmsWarehouseOwnerBaseAction_getSubGroupNoComboList',
		   							reader:{
		   								root:'rootList',
	    								totalProperty:'totalCount'
		   							}						
	     						},listeners:{
									'load':function(th,records,successful,eOpts){
										var itemType=_myAppGlobal.getController('cms.controller.bdef.bdef_WmsWarehouseOwnerBaseController').getItemType();
										if(itemType=='edit'){
												var data = Ext.getCmp('wmsWarehouseOwnerBase1N01').getSelectionModel().getSelection();
												Ext.getCmp('subGroupNo1N01').setValue(data[0].data.subGroupNo);
											}
									}
								}
	     					}),
		     			    displayField : 'dropValue',
		     			    valueField : 'value',
		     			    allowBlank : false,
		     			    beforeLabelTextTpl : required
	     				},{
	     					xtype:'wms_DefFieldValCombo',
	     					fieldLabel:$i18n.field_name,     //字段名称
	     					id:'colName1N01',	     					
	     					 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		     			    	    proxy:{
		     							type:'ajax',
		     							method:'post',
		     							url:'bdef_WmsWarehouseOwnerBaseAction_getColNameComboList',
		     							reader:{
		     								root:'rootList',
		     								totalProperty:'totalCount'
		     							}
		     						},listeners:{
										'load':function(th,records,successful,eOpts){
											var itemType=_myAppGlobal.getController('cms.controller.bdef.bdef_WmsWarehouseOwnerBaseController').getItemType();
											if(itemType=='edit'){
													var data = Ext.getCmp('wmsWarehouseOwnerBase1N01').getSelectionModel().getSelection();
													Ext.getCmp('colName1N01').setValue(data[0].data.colname);
												}
										}
									}
		     				    }),
		     				    allowBlank : false,
		     				    beforeLabelTextTpl : required
	     				},{
	     				    xtype:'wms_DefFieldValCombo',
	     					fieldLabel:$i18n.sdefine,  //值1（字符）
	     					id:'sdefine1N01',
	     					beforeLabelTextTpl : required,
	     					store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		     			        proxy:{
		     						type:'ajax',
		     						method:'post',
		     						url:'bdef_WmsWarehouseOwnerBaseAction_getSdefineComboList',
		     						reader:{
		     							root:'rootList',
		   								totalProperty:'totalCount'
		   							}
		   						},listeners:{
									'load':function(th,records,successful,eOpts){
										var itemType=_myAppGlobal.getController('cms.controller.bdef.bdef_WmsWarehouseOwnerBaseController').getItemType();
										if(itemType=='edit'){
												var data = Ext.getCmp('wmsWarehouseOwnerBase1N01').getSelectionModel().getSelection();
												Ext.getCmp('sdefine1N01').setValue(data[0].data.sdefine);
											}
									}
								}
	     				    }),
	     				},{
	     				    xtype:'numberfield',
	     					fieldLabel:$i18n.ndefine,  //值2（数字）
	     					id:'ndefine1N01',
	     					beforeLabelTextTpl : required
	     				},{
	     	   				xtype:'hidden',
		     			},{
	     					colspan:2,
	     					width:550,
	     				    xtype:'textareafield', 
	     					fieldLabel:$i18n.memo,     //参数说明
	     					id:'memo1N01',
	     			        allowBlank : false,
	     			        
	     				} ]
	     	},	
	     	{
	     		region:'south',
	     		xtype:'commMenuWidget5',
	     		border:0,
	     		id:'bdef_WmsWarehouseOwner1N01'
	     	}]
	     });