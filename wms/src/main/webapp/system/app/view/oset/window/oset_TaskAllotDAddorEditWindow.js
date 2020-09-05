/**
 * 模块名称：切单规则
 * 模块编码：3912
 * 创建：chensr
 */
Ext.define('cms.view.oset.window.oset_TaskAllotDAddorEditWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.oset_TaskAllotDAddorEditWindow',
	layout : 'border',
	id : 'oset_TaskAllotDAddorEditWindow',
	width:660,
	height:280,
	modal:true,
	items : [
	     	{
	     		xtype : 'form',
	     		region : 'north',
	     		id:'IdForm3912D',
	     		layout:'border',
	     		height:200,
	     		frame : true,
	     	    	 layout:{
	     		   			type:'table',
	     		   			columns:2
	     	   		},
	     	   		defaults : {
	     	   			xtype : 'textfield',
	     	   			labelWidth : 150,
	     	   			labelAlign:'right'			
	     	   		},
	     	   		items:[	     	   			
	     				{
	     					xtype:'textfield',
			     			fieldLabel:"仓别",    //仓别
			     			id:'warehouseNoD3912',	
			     			allowBlank : false,
			     			beforeLabelTextTpl : required
	     			        
	     				},{
	     					xtype:'textfield',
			     			fieldLabel:"任务id",    //任务分配ID
			     			id:'taskIdD3912',	  
			     			allowBlank : false,
			     			beforeLabelTextTpl : required
	     			        
	     				},{
	     					xtype:'bdef_DefOwnerCombo',
			     			fieldLabel:"下架类型",    
			     			id:'outstockTypeD3912',	  
			     			 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		     			    	    proxy:{
		     							type:'ajax',
		     							method:'post',
		     							url:'oset_TaskAllotAction_getOutstockTypeComboList',
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
	     				},{
	     					xtype:'bdef_DefOwnerCombo',
			     			fieldLabel:"作业类型1",    //作业类型1
			     			id:'sourceTypeD3912',	  
			     			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
	     			    	    proxy:{
	     							type:'ajax',
	     							method:'post',
	     							url:'oset_TaskAllotAction_getSourceTypeComboList',
	     							reader:{
	     								root:'rootList',
	     								totalProperty:'totalCount'
	     							}
	     						},listeners:{
									'load':function(th,records,successful,eOpts){
										var itemType=_myAppGlobal.getController('cms.controller.oset.oset_TaskAllotController').getItemType();
										if(itemType=='edit'){
												var data = Ext.getCmp('oset_TaskAllotDUI3912').getSelectionModel().getSelection();
												Ext.getCmp('sourceTypeD3912').setValue(data[0].data.sourceType);
											}
									}
								}
	     				    }),
	     				    displayField : 'dropValue',
	     				    valueField : 'value',
	     				    allowBlank : false,
	     				    beforeLabelTextTpl : required    
	     			        
	     				},{
	     					xtype:'bdef_DefOwnerCombo',
			     			fieldLabel:"作业类型2",    //作业类型2
			     			id:'operateType3912',	  
			     			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
	     			    	    proxy:{
	     							type:'ajax',
	     							method:'post',
	     							url:'oset_TaskAllotAction_getOoperateTypeComboList',
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
	     			        
	     				},{
	     					xtype:'bdef_DefOwnerCombo',
			     			fieldLabel:"切单范围",   
			     			id:'allotRuleD3912',	  
			     			  store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		     			    	    proxy:{
		     							type:'ajax',
		     							method:'post',
		     							url:'oset_TaskAllotAction_getAllotRuleComboList',
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
	     			        
	     				},{
	     					xtype:'bdef_DefOwnerCombo',
			     			fieldLabel:"切单规则",   
			     			id:'boxFlagD3912',	  
			     			  store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		     			    	    proxy:{
		     							type:'ajax',
		     							method:'post',
		     							url:'oset_TaskAllotAction_getBoxFlagComboList',
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
	     				},{
	     					xtype:'numberfield',
			     			fieldLabel:"对应值",    
			     			id:'paraValueD3912',
			     			minValue:0,
			     			allowBlank : false,
			     			beforeLabelTextTpl : required     
	     				},{
	     					xtype:'bdef_DefOwnerCombo',
			     			fieldLabel:"拣货打单方式",   
			     			id:'taskTypeD3912',	  
			     			  store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		     			    	    proxy:{
		     							type:'ajax',
		     							method:'post',
		     							url:'oset_TaskAllotAction_getTaskTypeComboList',
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
	     				},{
	     				    xtype:'textfield', 
	     					fieldLabel:'备注',    //备注
	     					id:'memoD3912'	     			     	     			        
	     				} ]
	     	},	
	     	{
	     		region:'south',
	     		xtype:'commMenuWidget5',
	     		border:0,
	     		id:'oset_taskAllotD3912'
	     	}]
	     });