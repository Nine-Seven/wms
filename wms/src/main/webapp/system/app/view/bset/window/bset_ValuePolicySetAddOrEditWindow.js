/** 模块名称：1、租仓策略配置
 * 模块编码：B201
 * 创建：zhm
 */

Ext.define('cms.view.bset.window.bset_ValuePolicySetAddOrEditWindow',{
	extend:'Ext.window.Window',
	alias:'widget.bset_ValuePolicySetAddOrEditWindow',
	layout:'border',
	id:'bset_ValuePolicySetAddOrEditWindow',
	width:680, 
	height:200,
	modal:true,
	items : [
	     	{
	     		xtype : 'form',
	     		region : 'north',
	     		id:'IdFormB201',
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
	     	   				beforeLabelTextTpl : required,
	     				    fieldLabel:$i18n.owner,//货主
	     				    id:'cmbOwnerB201_2', 
	     				    displayField : 'dropValue',
	     				    valueField : 'value',
	     				    allowBlank : false,
	     				    store:Ext.create("cms.store.common.comboStore",{
	     						proxy:{
	     							type:'ajax',
	     							method:'post',
	     							url : 'bset_ValuePolicySetAction_getOwnerComboList.action',
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
	     	   				beforeLabelTextTpl : required,
	     				    fieldLabel:$i18n.billingProject,//计费项目
	     				    id:'cmbBillingProjectB201',
	     				    displayField : 'dropValue',
	     				    valueField : 'value',
	     				    allowBlank : true,
	     				    store:Ext.create("cms.store.common.comboStore",{
	     						proxy:{
	     							type:'ajax',
	     							method:'post',
	     							url : 'bset_ValuePolicySetAction_getBillingProjectCombo.action',
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
	     		 	    	fieldLabel:$i18n.billingUnit,//计费单位
	     		 	    	id:'cmbBillingUnitB201',
	     		 	    	allowBlank:false,
	     		 	    	beforeLabelTextTpl : required,
	     		 	    	displayField : 'dropValue',
	     				    valueField : 'value',
	     					store:Ext.create("cms.store.common.comboStore",{
	     						proxy:{
	     							type:'ajax',
	     							method:'post',
	     							url : 'bset_ValuePolicySetAction_getBillingUnitCombo.action',
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
	     		 	    	fieldLabel:$i18n.valueFlag,//取值方式
	     		 	    	id:'cmbValueFlagB201',
	     		 	    	allowBlank:false,
	     		 	    	beforeLabelTextTpl : required,
	     		 	    	displayField : 'dropValue',
	     				    valueField : 'value',
	     					store:Ext.create("cms.store.common.comboStore",{
	     						proxy:{
	     							type:'ajax',
	     							method:'post',
	     							url : 'bset_ValuePolicySetAction_getValueFlagCombo.action',
	     					        reader: {
	     					    		type:'json',
	     					            root: 'rootList',    
	     					            totalProperty: 'totalCount'
	     					        }
	     					    }
	     						})
	     				},	     				
	     				{
	     	   				xtype:'textareafield',
	     	   				width:550,
	     	   				readOnly:true,
	     	   				colspan:2,
	     				    fieldLabel:$i18n.memo,//参数说明
	     				    id:'txtRemarkB201',
	     				    allowBlank : false
	     				}]
	     	},
	     	{
	     		region:'south',
	     		xtype:'commMenuWidget5',
	     		border:0,
	     		id:'bdef_MenuWidgetB201'
	     	}]
	     });
