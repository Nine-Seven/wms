/** 模块名称：仓别维护
 * 模块编码：2501
 */

Ext.define('cms.view.cset.window.cset_DeflocAddOrEditWindow',{
	extend:'Ext.window.Window',
	alias:'widget.cset_DeflocAddOrEditWindow',
	layout:'border',
	id:'cset_DeflocAddOrEditWindow',
	width:680, 
	height:260,
	modal:true,
	requires:['cms.view.common.commMenu5',
	          'cms.view.common.wms_DefFieldValCombo'
	       
	],
	items : [
	     	{
	     		xtype : 'form',
	     		region : 'north',
	     		id:'cset_DeflocAddOrEditWindow2501',
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
		     		 	    	xtype:'textfield',
		     		 	    	fieldLabel:$i18n.enterpriseNo,//企业
		     		 	    	id:'enterpriseNo2501',
		     		 	    	allowBlank:false,
		     		 	    	beforeLabelTextTpl : required,
		     		 	    	readOnly:true
		     		 	    },{
		     		 	    	xtype:'textfield',
		     		 	    	fieldLabel:$i18n.warehouse_no,//仓别代码,
		     		 	    	id:'warehouseNo2501',
		     		 	    	allowBlank:false,
		     		 	    	beforeLabelTextTpl : required
		     		 	    },{
		     		 	    	xtype:'textfield',
		     		 	    	fieldLabel:$i18n.warehouse_name,//仓别名称
		     		 	    	id:'warehouseName2501',
		     		 	    	allowBlank:false,
		     		 	    	beforeLabelTextTpl : required
		     		 	    },{
		     		 	    	xtype:'wms_DefFieldValCombo',
		     		 	    	fieldLabel:$i18n.paper_type,//单据类型
		     		 	    	id:'creatFlag2501',
		     		 	    	store:Ext.create("cms.store.common.comboStore").load(
			    				{
			    						params:{str:"N,CREATE_FLAG"}
			    		   		}),
			    				beforeLabelTextTpl : required
		     		 	    },{
		     		 	    	xtype:'textfield',
		     		 	    	fieldLabel:$i18n.linkman,//负责人
		     		 	    	id:'linkman2501'
		     		 	    },{
		     		 	    	xtype:'textfield',
		     		 	    	fieldLabel:$i18n.tel,//电话
		     		 	    	id:'tel2501'
		     		 	    }		,{
		     		 	    	xtype:'textfield',
		     		 	    	fieldLabel:$i18n.manageName,//管理员
		     		 	    	id:'manageName2501'
		     		 	    }		,{
		     		 	    	xtype:'textfield',
		     		 	    	fieldLabel:$i18n.province,//省
		     		 	    	id:'province2501'
		     		 	    }		,{
		     		 	    	xtype:'textfield',
		     		 	    	fieldLabel:$i18n.city,//市
		     		 	    	id:'city2501'
		     		 	    },{
		     		 	    	xtype:'textfield',
		     		 	    	fieldLabel : $i18n.zone,//区
		     		 	    	id:'zone2501'
		     		 	    },{
		     		 	    	xtype:'textfield',
		     		 	    	fieldLabel : $i18n.address,//地址
		     		 	    	id:'address2501'
		     		 	    },{
		     		 	    	xtype:'textfield',
		     		 	    	fieldLabel : $i18n.memo,//备注
		     		 	    	id:'memo2501'
		     		 	    }				     				
		     		]},
	     	{
	     		region:'south',
	     		xtype:'commMenuWidget5',
	     		border:0,
	     		id:'menuWidget52501'
	     	}]
	     });
