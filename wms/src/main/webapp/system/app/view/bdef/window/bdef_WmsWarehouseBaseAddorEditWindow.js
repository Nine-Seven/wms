/** 模块名称：1、仓别下的参数配置
 * 模块编码：1L01
 * 创建：zhm
 */

Ext.define('cms.view.bdef.window.bdef_WmsWarehouseBaseAddorEditWindow',{
	extend:'Ext.window.Window',
	alias:'widget.bdef_WmsWarehouseBaseAddorEditWindow',
	layout:'border',
	id:'bdef_WmsWarehouseBaseAddorEditWindow',
	width:680,
	height:180,
	modal:true,
	items : [
	     	{
	     		xtype : 'form',
	     		region : 'north',
	     		id:'IdForm1L01',
	     		layout:'border',
	     		height:200,
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
	     	   		items:[/*{
	     	   				xtype:'textfield',
	     	   				width:'50',
	     	   				readOnly:true,
	     				    fieldLabel:$i18n.warehouse,//仓别名称
	     				    id:'txtWarehouse1L01',
	     				    displayField : 'dropValue',
	     				    valueField : 'value',
	     				    allowBlank : false
	     				},*/
	     				{
	     	   				xtype:'combo',
	     	   				width:'50',
	     	   				readOnly:true,
	     				    fieldLabel:$i18n.w_group_no,//模块
	     				    id:'cmbGroupNo1L01_2', //txtGroupNo1L01
	     				    displayField : 'dropValue',
	     				    valueField : 'value',
	     				    allowBlank : false,
	     				    beforeLabelTextTpl : required,
	     				    store:Ext.create("cms.store.common.comboStore",{
	     						proxy:{
	     							type:'ajax',
	     							method:'post',
	     							url : 'bdef_WmsWarehouseBaseAction_getAllGroupNoCombo.action',
	     					        reader: {
	     					    		type:'json',
	     					            root: 'rootList',    
	     					            totalProperty: 'totalCount'
	     					        }
	     					    }
	     						}).load()
	     				},
	     				{
	     	   				xtype:'combo',
	     	   				width:'50',
	     	   				readOnly:true,
	     				    fieldLabel:$i18n.w_sub_group_no,//子模块
	     				    id:'cmbSubGroupNo1L01_2', //txtSubGroupNo1L01
	     				    displayField : 'dropValue',
	     				    valueField : 'value',
	     				    allowBlank : false,
	     				    beforeLabelTextTpl : required,
	     				     store:Ext.create("cms.store.common.comboStore",{
	     						proxy:{
	     							type:'ajax',
	     							method:'post',
	     							url : 'bdef_WmsWarehouseBaseAction_getAllSubGroupNoCombo.action',
	     					        reader: {
	     					    		type:'json',
	     					            root: 'rootList',    
	     					            totalProperty: 'totalCount'
	     					        }
	     					    }
	     						})
	     				},
	     				{
	     	   				xtype:'combo',
	     	   				width:'50',
	     	   				readOnly:true,
	     				    fieldLabel:$i18n.colname,//参数名称
	     				    id:'txtColum1L01',
	     				    displayField : 'dropValue',
	     				    valueField : 'value',
	     				    allowBlank : false,
	     				    beforeLabelTextTpl : required,
	     				    store:Ext.create("cms.store.common.comboStore",{
	     						proxy:{
	     							type:'ajax',
	     							method:'post',
	     							url : 'bdef_WmsWarehouseBaseAction_getColnameCombo.action',
	     					        reader: {
	     					    		type:'json',
	     					            root: 'rootList',    
	     					            totalProperty: 'totalCount'
	     					        }
	     					    }
	     						})
	     				},
	     				{
	     					xtype:'combo',
	     		 	    	fieldLabel:$i18n.sdefine,//值1（字符）
	     		 	    	id:'cmbSdefine1L01',
	     		 	    	displayField : 'dropValue',
	     				    valueField : 'value',
	     					store:Ext.create("cms.store.common.comboStore",{
	     						proxy:{
	     							type:'ajax',
	     							method:'post',
	     							url : 'bdef_WmsWarehouseBaseAction_getSdefineCombo.action',
	     					        reader: {
	     					    		type:'json',
	     					            root: 'rootList',    
	     					            totalProperty: 'totalCount'
	     					        }
	     					    }
	     						}).load()
	     				},
	     				{
	     	   				xtype:'numberfield',
	     	   				width:'50',
	     				    fieldLabel:$i18n.ndefine,//值2（数字）
	     				    id:'txtNdefine1L01',
	     				    displayField : 'dropValue',
	     				    valueField : 'value'
	     				},
     					{
     	   				xtype:'hidden',
     	   				width:'50'
	     				},
	     				{
	     	   				xtype:'textareafield',
	     	   				width:550,
	     	   				readOnly:true,
	     	   				colspan:2,
	     				    fieldLabel:$i18n.memo,//参数说明
	     				    id:'txtMemo1L01',
	     				    allowBlank : false
	     				}]
	     	},
	     	{
	     		region:'south',
	     		xtype:'commMenuWidget5',
	     		border:0,
	     		id:'bdef_MenuWidget1L01'
	     	}]
	     });
