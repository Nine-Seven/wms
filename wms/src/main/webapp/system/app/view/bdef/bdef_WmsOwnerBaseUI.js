/**
 * 模块名称：货主参数配置
 * 模块编码：1M01
 * 创建：chensr
 */
var bdef_WnsOwnerBaseStore=Ext.create('cms.store.bdef.bdef_WnsOwnerBaseStore',{autoLoad:true});
Ext.define('cms.view.bdef.bdef_WmsOwnerBaseUI',{
	alias:'widget.bdef_WmsOwnerBaseUI',
	title: $i18n.title1M01,//"货主参数配置",
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:['cms.view.common.commMenu2',
	          'cms.view.common.commMenu5',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.wms_DefFieldValCombo',
	          'cms.view.common.wms_DefStrategyCombo'
	          ],	          
	          items:[
	         	   	{
	         	   	  xtype:'commMenuWidget2',
	             	    id:'menu1M01',
	             	    region:'north'
	         		},	
	         		{
	         			xtype:'panel',
	         			region:'north',
	         			height: 35,
	         			layout: {
	         			    type: 'table',
	         		        columns: 3
	         			},
	         			  defaults : {
	         					xtype : 'textfield',
	         					margin : '3 3 3 3',
	         					labelAlign:'right',
	         					allowBlank: true,
	         					width : 280,
	         					labelWidth : 90
	         				},
	         			items:[{
	         				fieldLabel : $i18n.w_group_no,   //业务
	         				id:'groupNoUI1M01',
	     					xtype:'wms_DefFieldValCombo',
	     					 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		     			    	    proxy:{
		     							type:'ajax',
		     							async:false,
		     							method:'post',
		     							url:'bdef_WmsOwnerBaseAction_getGroupNoComboListForUI',
		     							reader:{
		     								root:'rootList',
		     								totalProperty:'totalCount'
		     							}
		     						}
		     				    }).load(),
		     				    displayField : 'dropValue',
		     				    valueField : 'value'
	         			},{
	         				fieldLabel :$i18n.w_sub_group_no,    //子业务
	         				id:'subGroupNoUI1M01',
	     					xtype:'wms_DefFieldValCombo',
	     					 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		     			    	    proxy:{
		     							type:'ajax',
		     							async:false,
		     							method:'post',
		     							url:'bdef_WmsOwnerBaseAction_getSubGroupNoComboListForUI',
		     							reader:{
		     								root:'rootList',
		     								totalProperty:'totalCount'
		     							}
		     						}
		     				    }).load(),
		     				    displayField : 'dropValue',
		     				    valueField : 'value'
	         			}]
	         		},{
	         			xtype:'grid',
		           	    id:'wmsOwnerBase1M01',
		           	    region:'center',
		           	    store: bdef_WnsOwnerBaseStore,
	       				columns:[{			
		           	        xtype : 'rownumberer',
	            		    width : 30
	            		},{
		             	    width:160,
		           		    text : $i18n.owner_no,  //货主编号
		           		    //dataIndex:'ownerNo'			
		           		    dataIndex:'ownerNameText'
		           	    },{
		           	    	width:80,
		           	    	text: $i18n.w_group_no,  //业务
		           	    	dataIndex:'groupNoText'
		           	    },{
		           		    width:100,
		           		    text : $i18n.w_sub_group_no,  //子业务
		           		    dataIndex:'subGroupNoText'			
		           	    },{
		           		    width:120,
		           		    text : $i18n.colname,  //字段名称
		           		    dataIndex:'colname'			
		           	    },{
		           		    width:80,
		           		    text : $i18n.sdefine,     //值1（字符）
		           		    dataIndex:'sdefineText'			
		           	    },{
		           		    width : 80,
		           		    text : $i18n.ndefine,     //值2（数字）
		           		    dataIndex : 'ndefine'
		           	    },{
	             		    width:560,
	             		    text : $i18n.memo,         //参数说明
		           		    dataIndex:'memo'			
		           	    }],
	       			dockedItems : [{
	       				xtype : 'pagingtoolbar',
           			    store : bdef_WnsOwnerBaseStore,
           			    dock : 'bottom',
           			    displayInfo : true
	       			}] 
	       	   },
         	 	{
	       		   region:'south'
	         	}]
	         });