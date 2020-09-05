/**
 * 模块名称：切单规则
 * 模块编码：3912
 * 创建：chensr
 */
Ext.define('cms.view.oset.window.oset_TaskAllotAddorEditWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.oset_TaskAllotAddorEditWindow',
	layout : 'border',
	id : 'oset_TaskAllotAddorEditWindow',
	width:600,
	height:180,
	modal:true,
	items : [
	     	{
	     		xtype : 'form',
	     		region : 'north',
	     		id:'IdForm3912M',
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
	     					xtype:'textfield',
			     			fieldLabel:"仓别",    //仓别
			     			id:'warehouseNoM3912',	
			     			allowBlank : false,
			     			beforeLabelTextTpl : required
	     			        
	     				},{
	     					xtype:'textfield',
			     			fieldLabel:"任务id",    //任务分配ID
			     			id:'taskIdM3912',	  
			     			allowBlank : false,
			     			beforeLabelTextTpl : required
	     			        
	     				},{
	     					xtype:'textfield',
			     			fieldLabel:"任务名称",    //项任务分配名称
			     			id:'taskNameM3912',	  
			     			allowBlank : false,
			     			beforeLabelTextTpl : required
	     			        
	     				},{
	     					xtype:'bdef_DefOwnerCombo',
			     			fieldLabel:"默认配置",    //默认配置
			     			id:'defaultFlagm3912',	  
			     			  store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		     			    	    proxy:{
		     							type:'ajax',
		     							method:'post',
		     							url:'oset_TaskAllotAction_getDefaultFlagmComboList',
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
	     					colspan:2,
	     					width:550,
	     				    xtype:'textareafield', 
	     					fieldLabel:'备注',    //备注
	     					id:'memom3912'	     			     	     			        
	     				} ]
	     	},	
	     	{
	     		region:'south',
	     		xtype:'commMenuWidget5',
	     		border:0,
	     		id:'oset_taskAllotM3912'
	     	}]
	     });