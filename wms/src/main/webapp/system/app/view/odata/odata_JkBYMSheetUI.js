/**
 * 模块名称：BYM订单出货接口
 * 模块编码：3911
 * 创建：chensr 
 */
var odata_JkBYMSheetStore=Ext.create('cms.store.odata.odata_JKBYMSheetStore',{autoLoad:true});
var odata_JkBYMSheetErrorStore=Ext.create('cms.store.odata.jk_Bym_Codeandsheet_ErrorStore',{autoLoad:false});
Ext.define('cms.view.odata.odata_JkBYMSheetUI',
	{
	alias:'widget.odata_JkBYMSheetUI',
	title:$i18n.title3911,//BYM订单出货接口
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:['cms.view.common.commMenu4',
	          'cms.view.common.commMenu5',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.wms_DefFieldValCombo',
	          'cms.view.common.wms_DefStrategyCombo'
	],	          
  	items:[{
		xtype:'tabpanel',
		id:'tabPId3911',
	    region:'center',
	    items:[{	    
	    	title:$i18n.title3911a,
	    	layout:'border',
	    	items:[{
				xtype:'commMenuWidget4',
				id:'menu3911',
				region:'north',
				items : [
					{
						text : $i18n.additem,
						name : 'add',
						iconCls : 'add'//新增
					},{ 
						text : '删除',
						name : 'delete',
						iconCls : 'delete' //修改
					},{ 
						text : '保存',
						name : 'save',
						iconCls : 'save' //浏览
					},{
						text : "生成待接收数据",
						name : 'BYMData',
						iconCls : 'add'//新增
					}]
		 		},{
				  xtype:'panel',
				  region:'north',
				  height: 35,
				  layout:{
					type: 'table',
					columns: 3
				  },
				  defaults :{
						xtype : 'textfield',
						margin : '3 3 3 3',
						labelAlign:'right',
						allowBlank: true,
						width : 280,
						labelWidth : 90
			      },
				  items:[{
						fieldLabel : $i18n.warehouse,   //仓别
						id:'warehouseUI3911',
						xtype:'textfield',
						readOnly:true		
				  },{
						fieldLabel:'所属公司',  //所属公司
						id:'company3911',
						xtype:'wms_DefFieldValCombo',
					  	store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				    	    proxy:{
									type:'ajax',
									async:false,
									method:'post',
									url:'odata_BYMSheetAction_getCompany',
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
						fieldLabel:$i18n.outstocktype,  //单据类型
						id:'sheetTypeM3911',
						xtype:'wms_DefFieldValCombo',
					  	store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				    	    proxy:{
									type:'ajax',
									async:false,
									method:'post',
									url:'odata_BYMSheetAction_getsheetTypeOfJK',
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
				   }]
				},{
					xtype:'grid',
				    id:'JkBYMSheet3911',
				    region:'center',
				  	store: odata_JkBYMSheetStore,
				  	selModel : {
				      selType : 'cellmodel'
				    },
				    plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
				        clicksToEdit : 1,
				        onSpecialKey:function(ed,field,e){
							commEnterGridStatEdit(this.grid,true,'cms.controller.odata.odata_JkBYMSheetController',e.getKey());
						}
				    })],
					columns:[{			
							xtype : 'rownumberer',
							width : 30
				 		},{
				 	    width:160,
					    text : $i18n.sourceexp_no,  //单号
					    dataIndex:'sheetNo',	
					    id:'sheetUINo3911',
					    cls : 'notnull',
				   		field : {							
					       	id : 'article_no3911',
					        xtype:'textfield',
					        allowBlank :false	
				     	} 
					    },{
				  		width:80,
				  		text: '所属公司',  //仓别
				  		dataIndex:'corpkey',
				  		id:'corpkey3911' 	    		
				 	  	},{
					  		width:80,
					  		text: $i18n.outstocktype,  //单据类型
					  		dataIndex:'sheetType',
					  		id:'sheettype3911' 	    		
					 	},{
				    	width:80,
				    	text: $i18n.warehouse,  //仓别
				    	dataIndex:'warehouseNo', 
				    	hidden:true
				    	},{
					    width:100,
					    text : $i18n.rgst_name,  //创建人
					    dataIndex:'rgstName',
					    hidden:true
				    	},{
					    width:180,
					    text : $i18n.rgst_date,  //创建时间
					    dataIndex:'dateText',
					    hidden:true
				    	}],
					dockedItems : [{
						xtype : 'pagingtoolbar',
					    store : odata_JkBYMSheetStore,
					    dock : 'bottom',
					    displayInfo : true
					}] 
		   		}
			]},{
				title:$i18n.title3911b,
		    	layout:'border',
		    	items:[{		    	
				  xtype:'panel',
				  region:'north',
				  height: 35,
				  layout:{
					type: 'table',
					columns: 3
				  },
				  defaults :{
						xtype : 'textfield',
						margin : '3 3 3 3',
						labelAlign:'right',
						allowBlank: true,
						labelWidth : 90
			      },
				  items:[{
					xtype : 'datefield',
					fieldLabel : $i18n.operaterDate,//操作日期
					id : 'date3911',								
					format : 'Y-m-d',
					value:new Date(),
					beforeLabelTextTpl : required			
				  },{
				  	xtype: 'button',
	            	text: $i18n.query,
	            	id:'btnQuery3911'
				  }]
		    	},{
					xtype:'grid',
				    id:'grid_error_3911',
				    region:'center',
				  	store: odata_JkBYMSheetErrorStore,				  	
					columns:[{			
							xtype : 'rownumberer',
							width : 30
				 		},{
					  		width:500,
					  		text: '失败原因描述',  //
					  		dataIndex:'dateText'
				 	  	}],
					dockedItems : [{
						xtype : 'pagingtoolbar',
					    store : odata_JkBYMSheetErrorStore,
					    dock : 'bottom',
					    displayInfo : true
					}] 		    		
		    	}]
			}
	    ]},  		
 	{
	   region:'south'
 	}]
 });