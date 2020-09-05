var cdef_DefWareStore=Ext.create('cms.store.cdef.cdef_DefWareStore',{
	autoLoad:true,
	listeners:{  
		'beforeload':function(store,operation,eOpts ){
			store.proxy.extraParams.str="[{'columnId':'a.warehouse_no','value':'"+Ext.get('warehouseNo').getValue()+"'}]";
		},
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('cdef_DefWareGrid2101').getStore().count()>0){
				Ext.getCmp('cdef_DefWareGrid2101').getSelectionModel().select(0);
			}
		}
	}
});

var cdef_DefAreaStore=Ext.create('cms.store.cdef.cdef_DefAreaStore',{autoLoad:false,
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('cdef_DefAreaGrid2101').getStore().count()>0){
				Ext.getCmp('cdef_DefAreaGrid2101').getSelectionModel().select(0);
			}
		}
	}
});

Ext.define('cms.view.cdef.cdef_DefWareUI',{
	alias:'widget.cdef_DefWareUI',
	title:$i18n.title2101,
	layout:'border',
	requires:['cms.view.common.commMenu2','cms.view.common.commMenu5',
	'cms.view.common.cdef_DefAreaCombo','cms.view.common.cdef_DefWareCombo',
	'cms.view.common.cdef_DefStockCombo','cms.view.common.wms_DefFieldValCombo',
	'cms.view.common.bdef_DefOwnerCombo','cms.view.common.cdef_DefCellCombo'],
	extend:'Ext.panel.Panel',
	items:[{
			xtype : 'commMenuWidget2',
			region:'north',
			id:'menu2101'
		},{
		xtype:'tabpanel',
		id:'tab2101',
		region:'center',
		items : [{//库区
				xtype : 'grid',
				id : 'cdef_DefWareGrid2101',
				title : $i18n.title2101tab1,
				store : cdef_DefWareStore,
				columns : [ {
					xtype : 'rownumberer',
					width : 30
				},{
					width : 100,
					text : $i18n.warehouse_no,
					dataIndex : 'warehouseNo'
				},{
					width : 120,
					text : $i18n.ware_no,
					dataIndex : 'wareNo'
				},{
					width : 120,
					text : $i18n.ware_name,
					dataIndex : 'wareName'
				},{
					width : 160,
					text : $i18n.ware_remark,
					dataIndex : 'wareRemark'
				}],
				dockedItems : [{
					xtype : 'pagingtoolbar',
					store : cdef_DefWareStore,
					dock : 'bottom',
					displayInfo : true
				}]
			},{//储区
				xtype : 'panel',
				id : 'd3_tap2101',
				title : $i18n.title2101tab2,
				layout:'border',
			    items: [{
			        xtype:'form',
			       	region:'north',
					frame : true,
			        layout: {
				        type: 'table',
				        columns: 3
				    },
					defaults : {
						xtype : 'textfield',
						margin : '3 3 3 3',
						labelAlign:'right',
						labelWidth : 90
					},
					items:[{
						xtype:'cdef_DefWareCombo',
						fieldLabel: $i18n.ware_no,
						id : 'd3_wareno2101',
						store:Ext.create('cms.store.cdef.cdef_DefWareComboStore',{
						}),
						displayField : 'dropValue',
					    valueField : 'value'
					}]
			    
			    },{
			    xtype : 'grid',
		    	region:'center',
				id : 'cdef_DefAreaGrid2101',
				store : cdef_DefAreaStore,
				columns : [ {
					xtype : 'rownumberer',
					width : 30
				},{
					width : 70,
					text : $i18n.ware_no,
					dataIndex : 'wareNo'
				},{
					width : 80,
					text : $i18n.area_no,
					dataIndex : 'areaNo'
				},{
					width : 160,
					text : $i18n.area_name,
					dataIndex : 'areaName'
				},{
					width : 160,
					text : $i18n.area_remark,
					dataIndex : 'areaRemark'
				},{//下架方式
					width : 100,
					text : $i18n.o_type,
					dataIndex : 'OTypeText'
				},{//混载标识
					width : 100,
					text : $i18n.mix_flag,
					dataIndex : 'mixFlagText'
				},{//储区类型
					width : 100,
					text : $i18n.area_type,
					dataIndex : 'areaTypeText'
				},{//拆零播种标识
					width : 120,
					text : $i18n.b_divide_flag_s,
					dataIndex : 'BDivideFlagText'
				},{//储区用途
					width : 80,
					text : $i18n.area_usetype,
					dataIndex : 'areaUsetypeText'
				},{//储区属性
					width : 80,
					text : $i18n.area_attribute,
					dataIndex : 'areaAttributeText'
				},{//属性类型
					width : 80,
					text : $i18n.attribute_type,
					dataIndex : 'attributeTypeText'
				},{//储区品质
					width : 80,
					text : $i18n.area_quality,
					dataIndex : 'areaQualityText'
				},{
					width : 100,
					text : $i18n.max_qty,
					dataIndex : 'maxQty'
				}],
				dockedItems : [{
					xtype : 'pagingtoolbar',
					store : cdef_DefAreaStore,
					dock : 'bottom',
					displayInfo : true
				}]
			  }]
			},
			{//货位
			xtype:'panel',
			title: $i18n.title2101tab4,
			id:'d4_tap2101',
			layout:'border',
		    items: [{
		        xtype:'form',
		       	region:'north',
				frame : true,
				height:'20%',
		        layout: {
			        type: 'table',
			        columns: 4
			    },
				defaults : {
					xtype : 'textfield',
					margin : '3 3 3 3',
					labelAlign:'right',
					allowBlank: true,
					labelWidth : 80
				},
				items:[{
						xtype:'cdef_DefWareCombo',
						fieldLabel: $i18n.ware_no,
						id : 'd4_wareno2101',
						store:Ext.create('cms.store.cdef.cdef_DefWareComboStore',{
							 proxy:{
									type:'ajax',
									method:'post',
									url:'cdef_DefWareAction_getCdef_DefWareCombo',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
							},
							listeners:{  
								'load':function(th,records,successful,eOpts ){
									if(th.count()>0){
										Ext.getCmp('d4_wareno2101').setValue(th.getAt(0).data.value);
										_myAppGlobal.getController('cms.controller.cdef.cdef_DefWareController').d4_wareno2101change(Ext.getCmp('d4_wareno2101'));
									}
								}
								}
						}),
						beforeLabelTextTpl : required
					},{
						xtype:'cdef_DefAreaCombo',
						fieldLabel: '储区',
						id : 'd4_areano2101',
						store:Ext.create('cms.store.cdef.cdef_DefAreaComboStore',{
							 proxy:{
									type:'ajax',
									method:'post',
									url:'cdef_DefWareAction_getCdef_DefAreaCombo',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
							},
							listeners:{  
							'load':function(th,records,successful,eOpts ){
								if(th.count()>0){
									Ext.getCmp('d4_areano2101').setValue(th.getAt(0).data.value);
									_myAppGlobal.getController('cms.controller.cdef.cdef_DefWareController').d4_areano2101change(Ext.getCmp('d4_areano2101'));
								}
							}
							}
						}),
						beforeLabelTextTpl : required
					},{
						xtype:'cdef_DefStockCombo',
						fieldLabel: '通道',
						id : 'd4_stock2101',
						store:Ext.create('cms.store.cdef.cdef_DefStockComboStore',{
							 proxy:{
									type:'ajax',
									method:'post',
									url:'cdef_DefWareAction_getCdef_DefStockCombo',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
							},		
							listeners:{  
							'load':function(th,records,successful,eOpts ){
								if(th.count()>0){
									Ext.getCmp('d4_stock2101').setValue(th.getAt(0).data.value);
									_myAppGlobal.getController('cms.controller.cdef.cdef_DefWareController').d4_stock2101change(Ext.getCmp('d4_stock2101'));
								}
							}
							}
						}),
						beforeLabelTextTpl : required
					},{
						xtype: 'button',
		            	text: '使用储位',
		            	width:100,
		            	id:'useBut2101'
					},{
						xtype:'combo',
						fieldLabel: '格',
						id : 'd4_stockX2101',
						store:Ext.create('cms.store.common.comboStore',{
							 proxy:{
									type:'ajax',
									method:'post',
									url:'cdef_DefWareAction_getCdef_DefStockXCombo',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
							}
						})				
					},{
						xtype:'combo',
						fieldLabel: '位',
						id : 'd4_bayX2101',
						store:Ext.create('cms.store.cdef.cdef_DefStockComboStore',{
							 proxy:{
									type:'ajax',
									method:'post',
									url:'cdef_DefWareAction_getCdef_DefbayXCombo',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
							}
						})															
					},{
						xtype:'combo',
						fieldLabel: '层',
						id : 'd4_stockY2101',
						store:Ext.create('cms.store.common.comboStore',{
							 proxy:{
									type:'ajax',
									method:'post',
									url:'cdef_DefWareAction_getCdef_DefStockYCombo',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
							}
						})														
					},{
						xtype: 'button',
		            	text: '禁用储位',
		            	width:100, colspan:2,
		            	id:'forbidBut2101'						
					},{
						 xtype: 'label',
		    	    	 colspan:3,
		    	    	 margin : '3 3 3 30',
		    	 	   	 labelWidth:600,//<font  color="#FF0000 ">[红色]</font >代表该货位已设拣货位,
					     html: '说明：<font  color="#42E61A">[绿色]</font >代表该货位有库存,<font color="#F3939">[橙色]</font>代表该货位已禁用,<font color="#0000EE">[蓝色]</font>代表该位置无货位,<font>[白色]</font>代表该货位无库存。'
					},{
						xtype: 'button',
		            	text: '冻结储位',
		            	width:100,
		            	id:'freezeBut2101'
					}]
		    }, {
		    	xtype : 'panel',
		    	height:'40%',
				id : 'cdef_DefCellGrid2101',
				region:'center',
				layout:'fit',
				frame:false,
				items: []
		    },{
		    	xtype : 'tabpanel',
			    height:'40%',
		    	region:'south',
			    id:'tap2101d_d1',
			    items : [{
			    	//货位商品库存信息
			    	xtype : 'grid',
			    	title:'库存信息',
					id : 'stock_contentGrid2101',
					store : Ext.create('cms.store.stock.stock_ContentStore'),
					columns : [ {
						xtype : 'rownumberer',
						width : 30
					},{
						width : 100,
						text : $i18n.cell,
						dataIndex : 'cellNo'
					},{
						width : 110,
						text : $i18n.article_no,
						dataIndex : 'articleNo'
					},{
						width : 200,
						text : $i18n.article_name,
						dataIndex : 'articleName'
					},{
						width : 120,
						text : $i18n.barcode,
						dataIndex : 'barcode'
					},{
						width : 60,
						text : $i18n.packing_qty,
						dataIndex : 'packingQty'
					},{
						width : 60,
						text : $i18n.packing_unit,
						dataIndex : 'packingUnit'
					},{
						width : 60,
						text : $i18n.spec,
						dataIndex : 'spec'
					},{
						width : 80,
						text : $i18n.qty,
						dataIndex : 'pkQty'
					},{
						width : 80,
						text : $i18n.instock_article_qty,
						dataIndex : 'pkInstockQty'
					},{
						width : 80,
						text : $i18n.outstock_article_qty,
						dataIndex : 'pkOutstockQty'
					},{
						width : 90,
						text : $i18n.porduce_date,
						dataIndex : 'produceDate'
					},{
						width : 90,
						text : $i18n.expire_date,
						dataIndex : 'expireDate'
					}]
			    	
			    	
			    },{
			    	xtype : 'grid',
			    	title:'拣货位信息',
					id : 'cset_article_cellGrid2101',
					store : Ext.create('cms.store.cset.cset_CellArticleStore'),
					columns : [ {
						xtype : 'rownumberer',
						width : 30
					}, {
						width : 80,
						text : $i18n.cell_no,//货位
						dataIndex : 'cellNo'
					}, {
						width : 120,
						text : $i18n.article_no,//商品编码
						dataIndex : 'articleNo'
					}, {
						width : 180,
						text : $i18n.article_name,//商品名称
						dataIndex : 'articleName'
					}, {
						width : 80,
						text : $i18n.line_no,//线路编号
						dataIndex : 'lineId'
					}, {
						width : 80,
						text : $i18n.packing_qty,//包装数量
						dataIndex : 'packingQty'
					}, {
						width : 80,
						text : $i18n.pick_type,//拣货类型
						dataIndex : 'pickType'
					} ,{
						width : 100,
						text : $i18n.max_qty_box,//最大存储量（箱）
						dataIndex : 'maxQtyBox'
					}, {
						width : 100,
						text : $i18n.alert_qty_box,//补货警示量（箱）
						dataIndex : 'alertQtyBox'
					}, {
						width : 130,
						text : $i18n.supp_qty_box, //'循环补货触发量（箱）
						dataIndex : 'suppQtyBox'
					}, {
						width : 90,
						text : $i18n.keep_cells,//可用货位数
						dataIndex : 'keepCells'
					}]
			    
			    }]
		    }]
		},{
			//货位查询
			xtype : 'panel',
			id : 'd5_tap2101',
			title : '货位信息查询',
			layout:'border',
        	items:[
	        {
        		xtype : 'form',
        		id : 'form_d5_2101',
				region:'center',
				frame : true,
				items :[
		        {
					xtype:'fieldset',
					title:'筛选',
				    layout: 
				    {
				        type: 'table',
				        columns: 1
				    },
				    defaults : 
				    {
				        xtype : 'textfield',
		                labelWidth : 110,
		                margin:'3 4 1 4',
			            labelAlign:'right'			
				    },
				    items:[
	       	        {
			 	    	fieldLabel:$i18n.cell_no,//货位
			 	    	id:'cell_no2101_d5',
			 	    	maxLength:24,
			 	    	xtype: 'cdef_DefCellCombo',
		                displayField : 'value',
		    			valueField : 'value',
		    			store:Ext.create('cms.store.cdef.cdef_DefCellComboStore',{
		    			   proxy:{
								type:'ajax',
								method:'post',
								url:'cdef_DefWareAction_getCdef_DefCellAllCombo.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								}
							},
						   listConfig: {
					           loadingText: '查询中...',
					           emptyText: '没有找到相应的数据！' ,
					           getInnerTpl: function() {
					        	   return '{value}';
					           }
					       }
		    			}),
			 	    	allowBlank:false,
			 	    	beforeLabelTextTpl : required
			 	    }]
		        },{

                	xtype:'fieldset',
                	title:'货位信息',
                	layout:
                	{
                		type:'table',
                		columns:3
                	},
                	defaults : 
	  	       	    {
	  	       	        xtype : 'textfield',
	  	       	        labelWidth : 110,
	  	       	        //width:230,
  	       	        	margin:'3 4 5 4',
	  	       	        labelAlign:'right'			
	  	       	    },
                	items:[{
						fieldLabel: $i18n.ware_no,readOnly:true,
						id : 'd5_wareno2101'
					},{
						fieldLabel: '储区',readOnly:true,
						id : 'd5_areano2101'
					},{
						fieldLabel: '通道',readOnly:true,
						id : 'd5_stock2101'
						
					},{
						fieldLabel: '格',readOnly:true,
						id : 'd5_stockX2101'
										
					},{
						fieldLabel: '位',readOnly:true,
						id : 'd5_bayX2101'
																					
					},{
						fieldLabel: '层',readOnly:true,
						id : 'd5_stockY2101'
																
					},{
        				fieldLabel: $i18n.cell,
        				readOnly:true,
        				id : 'd5_cell2101'
        			},{
        				fieldLabel: '货位展示',
        				readOnly:true,
        				id : 'd5_dispCellNo2101'
        			},{
        				fieldLabel: $i18n.max_qty,
        				readOnly:true,
        				id : 'd5_max_qty2101',
        			},{
        				fieldLabel: $i18n.max_volume,
        				readOnly:true,
        				id : 'd5_max_volume2101',
        			},{
        				fieldLabel: $i18n.max_weight,
        				readOnly:true,
        				id : 'd5_max_weight2101',
        			},{
        				fieldLabel: $i18n.max_case,
        				readOnly:true,
        				id : 'd5_max_case2101',
        			},{
        				xtype:'wms_DefFieldValCombo',
        				xtype:'wms_DefFieldValCombo',
        				fieldLabel: $i18n.cell_status,
        				readOnly:true,
        				store:Ext.create("cms.store.common.comboStore").load(
        						{
        								params:{str:"CDEF_DEFCELL,CELL_STATUS"}
        		   				}),
        				id : 'd5_cell_status2101'
        			},{
        				xtype:'wms_DefFieldValCombo',
        				fieldLabel: $i18n.check_status,
        				readOnly:true,
        				store:Ext.create("cms.store.common.comboStore").load(
        						{
        								params:{str:"CDEF_DEFCELL,CHECKSTATUS"}
        		   				}),
        				id : 'd5_check_status2101'
        			},{
        				xtype:'wms_DefFieldValCombo',
        				fieldLabel: $i18n.mix_flag,
        				readOnly:true,
        				store:Ext.create("cms.store.common.comboStore").load(
        						{
        								params:{str:"N,MIX_FLAG"}
        		   				}),
        				id : 'd5_mix_flag2101'
        			},{
        				xtype:'wms_DefFieldValCombo',
        				fieldLabel: $i18n.b_pick,
        				readOnly:true,
        				store:Ext.create("cms.store.common.comboStore").load(
        						{
        								params:{str:"N,B_PICK"}
        		   				}),
        				id : 'd5_b_pick2101'
        			},{
        				xtype:'wms_DefFieldValCombo',
        				fieldLabel: $i18n.limit_type,
        				readOnly:true,
        				store:Ext.create("cms.store.common.comboStore").load(
        						{
        								params:{str:"N,MIX_FLAG"}
        		   				}),
        				id : 'd5_limit_type2101'
        			},{
        				fieldLabel: $i18n.limit_rate,
        				readOnly:true,
        				id : 'd5_limit_rate2101',
        			},{
        				xtype:'wms_DefFieldValCombo',
        				fieldLabel: $i18n.a_flag,
        				readOnly:true,
        				store:Ext.create("cms.store.common.comboStore").load(
        						{
        								params:{str:"N,A_FLAG"}
        		   				}),
        				id : 'd5_a_flag2101'
        			},{
        				xtype:'wms_DefFieldValCombo',
        				fieldLabel: $i18n.mix_supplier,
        				readOnly:true,
        				store:Ext.create("cms.store.common.comboStore").load(
        						{
        								params:{str:"N,MIX_SUPPLIER"}
        		   				}),
        				id : 'd5_mix_supplier2101'
        			},{
        				xtype:'wms_DefFieldValCombo',
        				fieldLabel: $i18n.pick_flag,
        				readOnly:true,
        				store:Ext.create("cms.store.common.comboStore").load(
        						{
        								params:{str:"CDEF_DEFAREA,PICK_FLAG"}
        		   				}),
        				id : 'd5_pick_flag2101'
        			},{
        				xtype:'wms_DefFieldValCombo',
        				fieldLabel: $i18n.mix_owner,//混货主标识
        				readOnly:true,
        				store:Ext.create("cms.store.common.comboStore").load(
        						{
        								params:{str:"N,MIX_OWNER"}
        		   				}),
        				id : 'd5_mix_owner2101'
        			}]
                
                }
				]	
	        }
			]
	    
		
		},{
			//转区
			xtype : 'panel',
			id : 'd6_tap2101',
			title : '转区',
			layout:'border',
        	items:[
	        {
        		xtype : 'form',
        		id : 'form_d6_2101_1',
        		region : 'north',
        		width : '100%',
				height:'20%',
				frame : true,
				items :[
		        {
					xtype:'fieldset',
					title:'筛选',
				    layout: 
				    {
				        type: 'table',
				        columns: 3
				    },
				    defaults : 
				    {
				        xtype : 'textfield',
		                labelWidth : 80,
		                margin:'3 4 1 0',
			            labelAlign:'right'			
				    },
				    items:[{
						xtype:'cdef_DefWareCombo',
						fieldLabel: $i18n.ware_no,
						id : 'd6_wareno2101',
						store:Ext.create('cms.store.cdef.cdef_DefWareComboStore',{
							 proxy:{
									type:'ajax',
									method:'post',
									url:'cdef_DefWareAction_getCdef_DefWareCombo',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
							},
							listeners:{  
								'load':function(th,records,successful,eOpts ){
									if(th.count()>0){
										Ext.getCmp('d6_wareno2101').setValue(th.getAt(0).data.value);
										_myAppGlobal.getController('cms.controller.cdef.cdef_DefWareController').d6_wareno2101change(Ext.getCmp('d6_wareno2101'));
									}
								}
								}
						}),
						beforeLabelTextTpl : required
					},{
						xtype:'cdef_DefAreaCombo',
						fieldLabel: '储区',
						id : 'd6_areano2101',
						store:Ext.create('cms.store.cdef.cdef_DefAreaComboStore',{
							 proxy:{
									type:'ajax',
									method:'post',
									url:'cdef_DefWareAction_getCdef_DefAreaCombo',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
							},
							listeners:{  
							'load':function(th,records,successful,eOpts ){
								if(th.count()>0){
									//Ext.getCmp('d6_areano2101').setValue(th.getAt(0).data.value);
									_myAppGlobal.getController('cms.controller.cdef.cdef_DefWareController').d6_areano2101change(Ext.getCmp('d6_areano2101'));
								}
							}
							}
						}),
						beforeLabelTextTpl : required
					},{
						xtype:'combo',
						fieldLabel: '通道',
						id : 'd6_stock2101',
						store:Ext.create('cms.store.cdef.cdef_DefStockComboStore',{
							 proxy:{
									type:'ajax',
									method:'post',
									url:'cdef_DefWareAction_getCdef_DefStockCombo',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
							},		
						
						})
					},{
						xtype:'combo',
						fieldLabel: '格',
						id : 'd6_stockX2101',
						store:Ext.create('cms.store.common.comboStore',{
							 proxy:{
									type:'ajax',
									method:'post',
									url:'cdef_DefWareAction_getCdef_DefStockXCombo',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
							}
						})				
					},{
						xtype:'combo',
						fieldLabel: '位',
						id : 'd6_bayX2101',
						store:Ext.create('cms.store.cdef.cdef_DefStockComboStore',{
							 proxy:{
									type:'ajax',
									method:'post',
									url:'cdef_DefWareAction_getCdef_DefbayXCombo',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
							}
						})															
					},{
						xtype:'combo',
						fieldLabel: '层',
						id : 'd6_stockY2101',
						store:Ext.create('cms.store.common.comboStore',{
							 proxy:{
									type:'ajax',
									method:'post',
									url:'cdef_DefWareAction_getCdef_DefStockYCombo',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
							}
						})														
					}]
		        }
				]	
	        },{


				xtype : 'grid',
				title:'货位勾选',
				id:'d6_grid_2101',
				store:Ext.create('cms.store.cdef.cdef_DefCellStore'),
				region:'west',
				width:'37%',
				multiSelect: true,  
			    selModel: {  
		        	selType:'checkboxmodel'  
			    },
				columns : [ {
					xtype : 'rownumberer',
					width : 40
				},{
					width : 100,
					text : $i18n.addcell_no,
					dataIndex : 'cellNo'
				},{
					width : 100,
					text : '显示货位',
					dataIndex : 'dispCellNo'
					
				},{
					width : 80,
					text : $i18n.stock,
					dataIndex : 'stockNo'
				},{
					width : 60,
					text : '层',
					dataIndex : 'stockY'
				}]
        
	        
	        },{
	        	xtype : 'form',
	        	title:'转区选择',
        		id : 'form_d6_2101_2',
        		region:'east',
		    	width : '63%',
				frame : true,
				items :[
		        {
		        	
		    	xtype:'fieldset',
		    	title:'请选择转移的区域',
			    layout: 
			    {
			        type: 'table',
			        columns: 1
			    },
			    defaults : 
			    {
			        xtype : 'textfield',
	                labelWidth : 110,
	                margin:'10 4 1 4',
	                //allowBlank: true,
		            labelAlign:'right'			
			    },
			    items:[{
					xtype:'cdef_DefWareCombo',
					fieldLabel: $i18n.ware_no,
					id : 'd6_wareno2101_2',
					store:Ext.create('cms.store.cdef.cdef_DefWareComboStore',{
						 proxy:{
								type:'ajax',
								method:'post',
								url:'cdef_DefWareAction_getCdef_DefWareComboforWindow',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								}
						},
						listeners:{  
							'load':function(th,records,successful,eOpts ){
								if(th.count()>0){
									_myAppGlobal.getController('cms.controller.cdef.cdef_DefWareController').d6_wareno2101_2change(Ext.getCmp('d6_wareno2101_2'));
								}
							}
							},
						
						autoLoad:true
					}),
					beforeLabelTextTpl : required
				},{
					xtype:'cdef_DefAreaCombo',
					fieldLabel: '储区',
					id : 'd6_areano2101_2',
					store:Ext.create('cms.store.cdef.cdef_DefAreaComboStore',{
						 proxy:{
								type:'ajax',
								method:'post',
								url:'cdef_DefWareAction_getCdef_DefAreaComboforWindow',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								}
						}
					}),
					beforeLabelTextTpl : required
				},{
					xtype: 'button',
	            	text: '确认转区',
	            	margin:'10 4 1 110',
	            	width:150,
	            	id:'updateBut2101'
				}]
	        
		        }]
	        }
			]
	    
		
		
		}]
	}]
});