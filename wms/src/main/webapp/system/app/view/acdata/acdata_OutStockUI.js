/**
 * 模块名称： 分拨出库
 * 模块编码：C201
 * 创建：hkl
 */
var acdata_OutStock_MStore=Ext.create('cms.store.acdata.acdata_OutStockStore',{autoLoad:true});
Ext.define('cms.view.acdata.acdata_OutStockUI',{
	alias:'widget.acdata_OutStockUI',
	title: $i18n.acdata_outstock,//"分拨出库",
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[//'cms.view.common.commMenu',
			  'cms.view.common.commMenu3',
			  'cms.view.common.commMenu7',
			  'cms.view.common.remoteCombo',
		      'cms.view.common.bdef_DefOwnerCombo',
			  //'cms.view.common.bdef_DefArticleCombo',
			  //'cms.view.common.bdef_PackingQtyCombo',
			  //'cms.view.common.wms_DefFieldValCombo',
			  //'cms.view.common.odata_ExpMCombo'
			  ],
				items:[
				{
					xtype:'tabpanel',
					id:'tabPIdC201',
				    region:'center',
				    items:[{//列表
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
							},{
								text : '打印',
								iconCls : 'print',
								name : 'detailPrint'
							}],
				    	    region:'north'
				    	},{
				    		region:'center',
				    	    xtype:'grid',
				    	    id:'grid_01_C201',
				    	    store:acdata_OutStock_MStore,
				    	    columns:[
				    	    {
				    	        xtype:'rownumberer',
				    	       	width:30 
				    	    },{
				    	       	width:150,
				    	       	text:$i18n.acdata_outstock_NO,//出库单号
				    	       	dataIndex:'outstockNo'
				    	    },{
				    	       	width:100,
				    	       	text:$i18n.acdata_order_NO,//接单单号
				    	       	dataIndex:'orderNo'
				    	    },{
				    	       	width:140,
				    	       	text:$i18n.acdata_source_NO,//货主单号
				    	       	dataIndex:'sourceNo'
				    	    },{
				    	       	width:100,
				    	       	text:$i18n.acdata_cust_ALIAS,//收货人简称
				    	       	dataIndex:'custAlias'
				    	    },{
				    	       	width:120,
				    	       	text:$i18n.acdata_status,//状态
				    	       	dataIndex:'statusText'
				    	    }],
							dockedItems : [{
								xtype : 'pagingtoolbar',
								dock : 'bottom',
								store:acdata_OutStock_MStore,
								displayInfo : true
							}]
				    	    
				    	}]
				    },{
				    	//明细页面头档
				    	title:$i18n.titleD,
				    	layout:'border',
				    	itemId:'tabPIdC201i',
				    	items:[
				    	{
							xtype:'commMenuWidget',
							region:'north',
							id:'menuC201'
						},{
							xtype : 'form',
				    	    id : 'form_01_C201',
				    	    region:'north',
				    	    frame : true,
				    	    layout:{
			    	    		type:'table',
			    	    		columns:2
			    	    	},
			    	    	defaults : {
								xtype:'textfield',
								labelWidth : 150,
								margin : '2 2 4 2',
								labelAlign : 'right'
					  		},
				    	    items :[
			    	    	{
								fieldLabel:$i18n.acdata_outstock_NO,//出库单号
								id:'out_noC201',
								allowBlank:false,
								readOnly:true,
								beforeLabelTextTpl:required
					  		},{
								xtype:'combo',
								fieldLabel : $i18n.warehouse,//仓别
							 	id:'house_noC201',
							 	readOnly:true,
				    	        displayField: 'dropValue',
								valueField: 'value',
								queryMode: 'local',
								store:Ext.create("cms.store.common.comboStore",{
									proxy:{
										type:'ajax',
										method:'post',
										url : 'bset_ValuePolicySetAction_getHouseNameComboList.action',
								        reader: {
								    		type:'json',
								            root: 'rootList',    
								            totalProperty: 'totalCount'
								        }
								    },listeners:{  
											'load':function(th,records,successful,eOpts ){
												if(th.count()>0){
													Ext.getCmp('cmbWarehouseC101').setValue(th.getAt(0).data.value);
												}
											}
										}
									}).load(),
				        		beforeLabelTextTpl : required
					  		},{
					  			xtype:'remoteCombo',
			    	    		fieldLabel:$i18n.acdata_source_NO,//货主单号
			    	    		id:'source_noC201',
			    	    		allowBlank:false,
			    	    		readOnly:true,
			    	    		displayField: 'sourceNo',
			    				valueField: 'sourceNo',
			    	    		store:Ext.create("cms.store.acdata.acdata_LabelMStore",
			    						{
			    							proxy:{
			    								type:'ajax',
			    								method:'post',
			    								url:'acdata_OutStockAction_getsourceNo',
			    								reader:{
			    									root:'rootList',
			    									totalProperty:'totalCount'
			    								},
			    							}
			    						}),
			    						
			    						listConfig: {
			    				           loadingText: '查询中...',
			    				           emptyText: '没有找到相应的数据！' ,
			    				           getInnerTpl: function() {
			    				        	   return '{sourceNo}';
			    				           }
			    				        },
			    				        beforeLabelTextTpl : required
					  		},{
								xtype:'container',
								items:[
								{
									xtype: 'button',
							    	text: $i18n.acdata_select_No,//选择单号
							    	margin : '0 0 0 0',
							    	id:'btnSelectNoC201'
								}]
							},
					  		{
								fieldLabel:$i18n.acdata_order_NO,//接单单号
								id:'order_noC201',
								allowBlank:false,
								readOnly:true,
								maxLength:20,
								beforeLabelTextTpl:required
					  		},{
					  			fieldLabel:$i18n.acdata_owner,//发货人
					  			id:'ownerC201',
				       		 	displayField : 'dropValue',
				       			valueField : 'value',
				       			readOnly:true,
				       			beforeLabelTextTpl : required
					  		},{
								fieldLabel:$i18n.acdata_cust,//收货人
								readOnly:true,
								id:'custC201',
								maxLength:50
					  		},{
								fieldLabel:$i18n.acdata_reMark,//备注
								readOnly:true,
								id:'remarkC201',
								maxLength:100
					  		}]
						},{//明细页面列表
				    		xtype : 'grid',
				    	    region:'center',
				    	    id:'grid_02_C201',
				    	    loadMask : true, // 加载时有加载的图标
				    	    store : Ext.create('cms.store.acdata.acdata_OutStockDStore'),
				    	    selModel : {
				    	        selType : 'cellmodel'
				    	    },
				    	    plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
				    	        clicksToEdit : 1,
				    	        onSpecialKey:function(ed,field,e){
									commEnterGridStatEdit(this.grid,true,'cms.controller.acdata.acdata_OutStockController',e.getKey());
								}
				    	    })],
				    	    columns : [
				    	    {
				    	        xtype : 'rownumberer',
				    	        width : 30
				    	    },{
			    	    		width : 180,
								text : $i18n.acdata_article_Name,//货物名称
								dataIndex : 'articleName',
								//id:'articleName'
				    	    },{
							    width:100,
							    text:$i18n.acdata_barCode_NO,//条码
							    dataIndex:'barcodeNo'
				    	    },{
							    width:80,
							    text:$i18n.acdata_iN_Qty,//入库件数
							    dataIndex:'inQty'
				    	    },{
				    	    	width : 80,
								text : $i18n.acdata_iN_VL,//入库体积
								dataIndex : 'inWt',
							
				    	    },{
							    width:80,
							    text:$i18n.acdata_iN_wt,//入库重量
							    dataIndex:'inVl'
				    	    },{
							    width:80,
							    text:$i18n.acdata_already_Qty,//已出库件数
							    dataIndex:'alreadyQty'
				    	    },{
							    width:80,
							    text:$i18n.acdata_already_VL,//已出库体积
							    dataIndex:'alreadyWt'
				    	    },{
								width : 80,
								text:$i18n.acdata_already_wt,//已出库重量
								dataIndex : 'alreadyVl',
								
							},{
								width : 80,
								text:$i18n.acdata_oStock_Qty,//出库件数
								dataIndex : 'ostockQty',
								cls : 'notnull',
								field : {
			    	        		xtype : 'numberfield',
			    	        		minValue:0
			    	        	}
							},{
								width:80,
								text:$i18n.acdata_oStock_VL,//出库体积
								dataIndex:'ostockWt',
								cls : 'notnull',
								field : {
			    	        		xtype : 'numberfield',
			    	        		minValue:0
			    	        	}
							},
							{
								width:80,
								text:$i18n.acdata_oStock_wt,//出库重量
								dataIndex:'ostockVl',
								cls : 'notnull',
								field : {
			    	        		xtype : 'numberfield',
			    	        		minValue:0
			    	        	}
							}],
				    	   
				    	}
				    	]
				    }]
				}]
			});