/**
 * 模块名称：分拨订单管理
 * 模块编码：C301
 * 创建：csr
 */
var acdata_Order_M = Ext.create('cms.store.acdata.acdata_Order_MStore',{autoLoad: true});
Ext.define('cms.view.acdata.acdata_OrderUI',{
	alias:'widget.acdata_OrderUI',
	title:'分拨订单管理',//分拨订单管理
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
             'cms.view.common.commMenu3',
             'cms.view.common.remoteCombo',
             'cms.view.common.commMenu7'
	],
	items:[
	{
		xtype:'tabpanel',
		id:'tabPIdC301',
	    region:'center',
	    items:[
	    {
	    	title:$i18n.titleM,
	    	layout:'border',
	    	items:[
		    	{
		    		xtype:'commMenuWidget3',
		    		items:[
		    		{
					text : '查找',
					iconCls : 'query',
					name : 'detailQuery'
				}],
	    	    region:'north'
	    	},{
	    		xtype : 'grid',
				region:'center',
				id : 'grid_01_C301',
				width : '100%',
				height : '100%',
				store : acdata_Order_M,
				columns : [ 
				{
					xtype : 'rownumberer',
					width : 30
				},{
					width : 150,
					text : $i18n.order_no,//接单单号
					dataIndex : 'orderNo'
				},{
					width:150,
					text:$i18n.source_no1,//货主单号
					dataIndex:'sourceNo'
				},{
					width:150,
					text:'发单日期',//货主单号
					dataIndex:'sdateText'
				},{
					width : 150,
					text : $i18n.owner_alias1,//发货人简称
					dataIndex : 'ownerAlias'
				},{
					width : 150,
					text : $i18n.acdata_cust_ALIAS,//发货人简称
					dataIndex : 'custAlias'
				},{
					width : 80,
					text : $i18n.status,//状态	
					dataIndex : 'statusText'
				}],
				dockedItems : [{
					xtype : 'pagingtoolbar',
					store : acdata_Order_M,
					dock : 'bottom',
					displayInfo : true
				}]
		     }]
	    },{
	    	title:$i18n.titleD,
	    	layout:'border',
	    	itemId:'tabPIdC301i',
	    	items:[
	    	{
				xtype:'commMenuWidget',
				region:'north',
				id:'menuC301',
				
				items : [{
					text : '上单',
					name : 'userPrevButton',
					iconCls : 'prev'//新增
				},{ 
					text : '下单',
					name : 'userNextButton',
					iconCls : 'next' //修改
				},{ 
					text : '修改',
					name : 'userEditButton',
					iconCls : 'edit' //修改userUndoButton
				},{ 
					text : '保存',
					name : 'userSaveButton',
					iconCls : 'save'
				},{ 
					text : '撤销',
					name : 'userUndoButton',
					iconCls : 'undo'
				},{ 
					text : '接单确认',
					name : 'orderCheck',
					iconCls : 'send'
				},{ 
					text : '回单确认',
					name : 'orderOver',
					iconCls : 'send'
				}]	
			}
	    	,{
				xtype : 'form',
	    	    id : 'form_01_C301',
	    	    region:'north',
	    	    frame : true,
	    	    layout:{
    	    		type:'table',
    	    		columns:1
    	    	},
    	    	defaults : {
					xtype:'textfield',
					labelWidth : 90,
					margin : '2 2 4 2',
					labelAlign : 'right'
		  		},
	    	    items :[
	    	    {
		            xtype:'fieldset',
		     		layout: {
		         	type: 'table',
		                 columns: 4
		       	    },
		       	    defaults:{
		       		xtype:'textfield',
		       	   	margin:'5 4 1 4',
		       	   	labelAlign:'right',
		       	   	labelWidth:120
		            },
		            items:[{
		            	xtype:'textfield',
		     			fieldLabel:$i18n.source_no1, //货主单号
		     			id:'sourceNoC301'
		     		},{
		   				xtype:'textfield',
		     			fieldLabel:$i18n.order_no,    //接单单号
		     			id:'orderNoC301'
		     		},{

		   				xtype:'textfield',
		     			fieldLabel:'发单日期',    //发单日期
		     			id:'sdateC301'
		     		}]
		   	   },{
		   		    title:"发货人信息",
		            xtype:'fieldset',
		     		layout: {
		         	type: 'table',
		                 columns: 4
		       	    },
		       	    defaults:{
		       		xtype:'textfield',
		       	   	margin:'5 4 1 4',
		       	   	labelAlign:'right',
		       	   	labelWidth:120
		            },
		            items:[{
		   				xtype:'textfield',
		     			fieldLabel:'发货人简称',    //收货人简称
		     			id:'ownerAliasC301'
		     		},{
		   				xtype:'textfield',
		     			fieldLabel:'发货人地址',    //收货人地址
		     			id:'ownerAddrC301'
		     		},{
		   				xtype:'textfield',
		     			fieldLabel:'联系人',    //联系人
		     			id:'ownerLinkmanC301'
		     		
		     		},{
		     			xtype:'textfield',
		     			fieldLabel:'联系电话',    //联系电话
		     			id:'ownerPhoneC301'
		     		}]
		   	   
		   	   },{

		   		    title:"收货人信息",
		            xtype:'fieldset',
		     		layout: {
		         	type: 'table',
		                 columns: 4
		       	    },
		       	    defaults:{
		       		xtype:'textfield',
		       	   	margin:'5 4 1 4',
		       	   	labelAlign:'right',
		       	   	labelWidth:120
		            },
		            items:[{
		   				xtype:'textfield',
		     			fieldLabel:'收货人简称',    //收货人简称
		     			id:'custAliasC301'
		     		},{
		   				xtype:'textfield',
		     			fieldLabel:'收货人地址',    //收货人地址
		     			id:'custAddrC301'
		     		},{
		   				xtype:'textfield',
		     			fieldLabel:'联系人',    //联系人
		     			id:'custLinkmanC301'
		     		},{
		     			xtype:'textfield',
		     			fieldLabel:'联系电话',    //联系电话
		     			id:'custPhoneC301'
		     		}]
		   	   
		   	   
		   	   }]
			},{
	        	xtype:'grid',
    		    id:'grid_02_C301',
    		    region:'center',
    		    loadMask : true, // 加载时有加载的图标
	    	    store : Ext.create('cms.store.acdata.acdata_Order_D1Store'),
    		    selType : 'cellmodel',
				plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
					clicksToEdit : 1,
					onSpecialKey:function(ed,field,e){
						commEnterGridStatEdit(this.grid,true,'cms.controller.acdata.acdata_OrderController',e.getKey());
					}
				})],
    		    columns:[
    		    {			
    		        xtype : 'rownumberer',
    			    width : 30
    		    },
    		    {
    			    width:200,
					text : $i18n.acdata_article_Name,//货物名称
    			    dataIndex:'articleName'			
    		    },
    		    {
				    width:150,
				    text:$i18n.acdata_barCode_NO,//条码
				    dataIndex:'barcodeNo'
    		    },
    		    {
				    width:100,
				    text:'订单件数',//订单件数
				    dataIndex:'orderQty'
    		    },   		    
    		    {
    				width : 100,
    				text : '订单体积',
    				dataIndex:'orderVl'
    			},
    		    {
				    width:100,
				    text : '订单重量',
				    dataIndex:'orderWt'
    		    },{
    		    	width:100,
 				    text : '接单数量',
 				    dataIndex:'inQty',
 				    cls : 'notnull',
 					field : {
 	    	        	xtype : 'numberfield',
 	    	        	minValue:0
 	    	        }
    		    },{
    		    	width:100,
 				    text : '签收数量',
 				    dataIndex:'signQty',
 				    cls : 'notnull',
				    field : {
				        xtype : 'numberfield',
   	        		    minValue:0
				    }
    		    }
    		    ]
	        }]
	    }]
	}]
});