/**
 * 模块名称：货主参数配置 window
 * 模块编码：1M01
 * 创建：chensr
 */
Ext.define('cms.view.bdef.window.bdef_WmsOwnerBaseAddOrEditWindow',{
	extend:'Ext.window.Window',
	alias:'widget.bdef_WmsOwnerBaseAddOrEditWindow',
	layout:'border',
	id:'bdef_WmsOwnerBaseAddOrEditWindow',
	width:600,
	height:180,
	modal:true,
	items : [
	     	{
	     		xtype : 'form',
	     		region : 'north',
	     		id:'IdForm1M01',
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
	     				    xtype:'bdef_DefOwnerCombo',
	     				    fieldLabel:$i18n.owner_no, //货主编号
	     				    id:'ownerBaseNo1M01',
	     				    store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
	     				    displayField : 'dropValue',
	     				    valueField : 'value',
	     				    allowBlank : false,
	     				    beforeLabelTextTpl : required
	     				},{
	     					fieldLabel:$i18n.group_no_text,  //主业务
	     					id:'groupNo1M01',
	     					xtype:'wms_DefFieldValCombo',
	     					 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		     			    	    proxy:{
		     							type:'ajax',
		     							async:false,
		     							method:'post',
		     							url:'bdef_WmsOwnerBaseAction_getGroupNoComboList',
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
	     					fieldLabel:$i18n.sub_group_no_text, //子业务
	     					id:'subGroupNo1M01',
	     					xtype:'wms_DefFieldValCombo',
	     					store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		     			   	    proxy:{
		     						type:'ajax',
		     						async:false,
		     						method:'post',
		     						url:'bdef_WmsOwnerBaseAction_getSubGroupNoComboList',
		   							reader:{
		   								root:'rootList',
	    								totalProperty:'totalCount'
		   							}						
	     						},
								listeners:{
									'load':function(th,records,successful,eOpts){
										var itemType=_myAppGlobal.getController('cms.controller.bdef.bdef_WmsOwnerBaseController').getItemType();
										if(itemType=='edit'){
												var data = Ext.getCmp('wmsOwnerBase1M01').getSelectionModel().getSelection();
												Ext.getCmp('subGroupNo1M01').setValue(data[0].data.subGroupNo);
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
	     					fieldLabel:$i18n.field_name,    //字段名称
	     					id:'colName1M01',	     					
	     					 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		     			    	    proxy:{
		     							type:'ajax',
		     							method:'post',
		     							url:'bdef_WmsOwnerBaseAction_getColNameComboList',
		     							reader:{
		     								root:'rootList',
		     								totalProperty:'totalCount'
		     							}
		     						},
									listeners:{
										'load':function(th,records,successful,eOpts){
											var itemType=_myAppGlobal.getController('cms.controller.bdef.bdef_WmsOwnerBaseController').getItemType();
											if(itemType=='edit'){
												var data = Ext.getCmp('wmsOwnerBase1M01').getSelectionModel().getSelection();
												Ext.getCmp('colName1M01').setValue(data[0].data.colname);
											}							
										}
									}
		     				    }),
		     				    allowBlank : false,
		     				    beforeLabelTextTpl : required
	     				},{
	     				    xtype:'wms_DefFieldValCombo',
	     					fieldLabel:$i18n.sdefine,   //值1（字符）
	     					id:'sdefine1M01',
	     					beforeLabelTextTpl : required,
	     					store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		     			        proxy:{
		     						type:'ajax',
		     						method:'post',
		     						url:'bdef_WmsOwnerBaseAction_getSdefineComboList',
		     						reader:{
		     							root:'rootList',
		   								totalProperty:'totalCount'
		   							}
		   						},
								listeners:{
									'load':function(th,records,successful,eOpts){
										var itemType=_myAppGlobal.getController('cms.controller.bdef.bdef_WmsOwnerBaseController').getItemType();
											if(itemType=='edit'){
												var data = Ext.getCmp('wmsOwnerBase1M01').getSelectionModel().getSelection();
												Ext.getCmp('sdefine1M01').setValue(String(data[0].data.sdefine));
											}
									}
								}
	     				    })
	     				   
	     				},{
	     				    xtype:'numberfield',
	     					fieldLabel:$i18n.ndefine,   //值2（数字）
	     					id:'ndefine1M01',
	     					beforeLabelTextTpl : required
	     			        
	     				},{
	     					colspan:2,
	     					width:550,
	     				    xtype:'textareafield', 
	     					fieldLabel:$i18n.memo,    //参数说明
	     					id:'memo1M01',
	     			        allowBlank : false
	     			        
	     				} ]
	     	},	
	     	{
	     		region:'south',
	     		xtype:'commMenuWidget5',
	     		border:0,
	     		id:'bdef_ownerBaseNo1M01'
	     	}]
	     });