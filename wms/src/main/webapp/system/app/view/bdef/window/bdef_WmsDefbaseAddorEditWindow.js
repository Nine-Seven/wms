

Ext.define('cms.view.bdef.window.bdef_WmsDefbaseAddorEditWindow',{
	extend:'Ext.window.Window',
	alias:'widget.bdef_WmsDefbaseAddorEditWindow',
	layout:'border',
	id:'bdef_WmsDefbaseAddorEditWindow',
	width:680,
	height:180,
	modal:true,
	items : [
	     	{
	     		xtype : 'form',
	     		region : 'north',
	     		id:'IdForm1K01',
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
	     	   		items:[
	     				{
	     	   				xtype:'combo',
	     	   				width:'50',
	     	   				readOnly:true,
	     				    fieldLabel:$i18n.w_group_no,//模块
	     				    id:'txtGroupNo1K01',
	     				    displayField : 'dropValue',
	     				    valueField : 'value',
	     				    beforeLabelTextTpl : required,
	     				    allowBlank : false,
	     				    store:Ext.create("cms.store.common.comboStore",{
	     						proxy:{
	     							type:'ajax',
	     							method:'post',
	     							url : 'bdef_WmsDefbaseAction_getGroupNoCombo.action',
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
	     				    beforeLabelTextTpl : required,
	     				    id:'txtSubGroupNo1K01',
	     				    displayField : 'dropValue',
	     				    valueField : 'value',
	     				    allowBlank : false,
	     				     store:Ext.create("cms.store.common.comboStore",{
	     						proxy:{
	     							type:'ajax',
	     							method:'post',
	     							url : 'bdef_WmsDefbaseAction_getSubGroupNoCombo.action',
	     					        reader: {
	     					    		type:'json',
	     					            root: 'rootList',    
	     					            totalProperty: 'totalCount'
	     					        }
	     					    }
	     						})
	     				},
	     				{
	     	   				xtype:'textfield',
	     	   				width:'50',
	     	   				readOnly:true,
	     				    fieldLabel:$i18n.colname,//参数名称
	     				    id:'txtColum1K01',
	     				    displayField : 'dropValue',
	     				    valueField : 'value',
	     				    beforeLabelTextTpl : required,
	     				    allowBlank : false
	     				},
	     				{
	     	   				xtype:'textfield',
	     	   				width:'50',
	     	   				readOnly:true,
	     				    fieldLabel:$i18n.user_level,//适用范围
	     				    id:'txtUserLevel1K01',
	     				    displayField : 'dropValue',
	     				    valueField : 'value',
	     				    beforeLabelTextTpl : required,
	     				    allowBlank : false
	     				},
	     				{
	     					xtype:'combo',
	     		 	    	fieldLabel:$i18n.sdefine,//值1（字符）
	     		 	    	id:'cmbSdefine1K01',
	     		 	    	allowBlank:false,
	     		 	    	displayField : 'dropValue',
	     				    valueField : 'value',
	     					store:Ext.create("cms.store.common.comboStore",{
	     						proxy:{
	     							type:'ajax',
	     							method:'post',
	     							url : 'bdef_WmsDefbaseAction_getSdefineCombo.action',
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
	     				    id:'txtNdefine1K01',
	     				    displayField : 'dropValue',
	     				    valueField : 'value',
	     				    allowBlank : false
	     				},
	     				{
	     	   				xtype:'textareafield',
	     	   				width:550,
	     	   				readOnly:true,
	     	   				colspan:2,
	     				    fieldLabel:$i18n.memo,//参数说明
	     				    id:'txtMemo1K01',
	     				    displayField : 'dropValue',
	     				    valueField : 'value',
	     				    allowBlank : false
	     				}]
	     	},
	     	{
	     		region:'south',
	     		xtype:'commMenuWidget5',
	     		border:0,
	     		id:'bdef_MenuWidget1K01'
	     	}]
	     });
