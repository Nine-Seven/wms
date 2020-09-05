/**
 * 模块名称：出库扫描审核
 * 模块编码：3928
 * 创建：hj
 */
var odata_Exp_MStore=Ext.create('cms.store.odata.odata_ExpMStore',{
	autoLoad : true,
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_ExpCheckAction_getOdata_NoCkeckOrderList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});

var odata_Exp_DStore=Ext.create('cms.store.odata.odata_ExpDStore',{
	//autoLoad : true,
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_ExpAction_getOrder_GoodsDetailList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});

var odata_Exp_DStore2=Ext.create('cms.store.odata.odata_ExpDStore',{
	autoLoad : true,
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_ExpAction_getAllOrderList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});

Ext.define('cms.view.odata.odata_OutstockScanCheckUI', {
	alias : 'widget.odata_OutstockScanCheckUI',
	title : $i18n.title3928,//出库扫描审核
	layout:'border',
	width:'100%',
	height:'100%',
	layout:'border',
	requires : ['cms.view.common.commMenu2',
	            'cms.view.common.commMenu4',
		'cms.view.common.commMenu5',
		'cms.view.common.wms_DefFieldValCombo',
		'cms.view.common.remoteCombo'],
	extend : 'Ext.panel.Panel',
	items : [{
		xtype : 'tabpanel',
		id:'outstockCkeck1101',
		region:'center',
		flex : 4,
		items : [{	//出库审核界面
			title : $i18n.outCheckTab,//出库审核界面
			itemId : 'outStockScanTab',
			id:'outStockScanTab',
			layout:'border',
			items : [{
            	xtype : 'form',
				id:'outStockCheckFilterCondition3701',
				layout:'column',
				region : 'north',
				layout: 
                {
                	type: 'table',
                	columns: 3
                },
				defaults:{
			       labelAlign:'right',
			       allowBlank: true,
				   width : 280,
				   labelWidth : 90,
				   margin : '3 3 3 3',
		        },
                items:[
				{
			        xtype : 'datefield',
					fieldLabel : $i18n.scanBeginDate,	//扫描开始日期						
					format : 'Y-m-d',
					id : 'scanBeginDate'
				},
				{
					xtype : 'datefield',
					fieldLabel : $i18n.scanEndDate,//扫描结束日期							
					format : 'Y-m-d',
					id : 'scanEndDate'
				},
				{
					xtype:'container',
					items:[
			        {
						xtype: 'button',
		            	text: $i18n.checkTest,//审核
		            	margin : '0 0 0 45',
		            	id:'checkButton',
		            	width : 80
					}]
				},
				{
					xtype:'remoteCombo',
					fieldLabel : $i18n.customNumber,	//客户编码
					id:'customNumber3701',
					store:Ext.create("cms.store.common.comboStore",
							{
								proxy:{
									type:'ajax',
									method:'post',
									url:'odata_ExpCheckAction_getCustomNumberList.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
								}
					   		})
				},
				{
					xtype:'remoteCombo',
					fieldLabel : $i18n.outStockOrder,	//出货申请单
					id:'outStockCheckFirst3701',
					store:Ext.create("cms.store.common.comboStore",
							{
								proxy:{
									type:'ajax',
									method:'post',
									url:'odata_ExpCheckAction_getOrderNumberList.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
								}
					   		})
				},{
					xtype:'container',
					items:[
					{
						xtype: 'button',
				        text: $i18n.find, //查询
				        margin : '0 0 0 45',
				        id:'findButton',
				        width : 80
					}],       
				}]
			},{
				xtype : 'grid',
				id : 'gridOutStockScan1101',
				width : '45%',
				title : $i18n.outCheck,//出库审核
				region:'center',
				store:odata_Exp_MStore,
				columns : [{
    				xtype : 'rownumberer',
    				width : 30
				},{
					width : 155,
					text : $i18n.outStockOrder,//出货申请单
					dataIndex : 'sourceexpNo'
				},{
					width : 80,
					text : $i18n.customNumber,//客户编码
					dataIndex : 'custNo'
				},{
					width : 150,
					text : $i18n.customName,//客户名称
					dataIndex : 'custName'
				},{
					width : 80,
					text : $i18n.scanDate,//扫描日期
					dataIndex:'strRgstDate',
				}],
				dockedItems : [{
					xtype : 'pagingtoolbar',
					store : odata_Exp_MStore,
					dock : 'bottom',
					displayInfo : true
				} ]
			},{
				xtype:'grid',
				id:'out_Stock_ScanGrid',
				title : $i18n.detailMsg,//详细信息
				store:odata_Exp_DStore,
				width : '55%',
				region:'east',
				columns:[{
					width : 80,
					text : $i18n.customGoodsNumber,//货主商品编号
					dataIndex : 'articleNo'	
				},{
					width : 225,
					text : $i18n.article_name,//商品名称
					dataIndex : 'articleName'
				},{
					width : 130,
					text : $i18n.barcode,//商品条码
					dataIndex : 'barcode'
				},{
					width : 50,
					text : $i18n.planQty,//单据量
					dataIndex : 'planQty'
				},{
					width : 50,
					text : $i18n.scanQty,//扫描量
					dataIndex : 'scanQty'
				},{
					width : 50,
					text : $i18n.compare_qty,//差异量
					dataIndex : 'diffQty'
				}],
				dockedItems : [{
					xtype : 'pagingtoolbar',
					store : odata_Exp_DStore,
					dock : 'bottom',
					displayInfo : true
				} ]
			}]
		},{			//出库查询界面
			title : $i18n.outStockFindTab,	//出库查询界面
			itemId : 'outStockFindTab',
			id : 'outStockFindTab',
			layout:'border',
			items : [{
            	xtype : 'form',
				id:'outStockFindFilterCondition3701',
				layout:'column',
				region : 'north',
				layout: 
                {
                	type: 'table',
                	columns: 4
                },
				defaults:{
				    labelAlign:'right',
				    allowBlank: true,
					width : 260,
					labelWidth : 90,
					margin : '3 3 3 3',
			     },
                items:[{
			        xtype : 'datefield',
					fieldLabel : $i18n.scanBeginDate,	//扫描开始日期					
					format : 'Y-m-d',
					id : 'scanBeginDate2'
				},
				{
					xtype : 'datefield',
					fieldLabel : $i18n.scanEndDate,	//扫描结束日期					
					format : 'Y-m-d',
					id : 'scanEndDate2'
				},
				{
					xtype:'remoteCombo',
					fieldLabel : $i18n.customNumber,	//客户编码
					id:'customNumber3702',
					store:Ext.create("cms.store.common.comboStore",
							{
								proxy:{
									type:'ajax',
									method:'post',
									url:'odata_ExpCheckAction_getCustomNumberList.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
								}
					   		})
				},
				{
					xtype:'remoteCombo',
					fieldLabel : $i18n.outStockOrder,	//出货申请单
					id:'outStockCheckSecond3701',
					store:Ext.create("cms.store.common.comboStore",
							{
								proxy:{
									type:'ajax',
									method:'post',
									url:'odata_ExpCheckAction_getOrderNumberList.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
								}
					   		})
				},{
					xtype:'remoteCombo',
					fieldLabel : $i18n.customGoodsNumber,	//货主商品编号
					id:'customGoodsNumber3701',
					store:Ext.create("cms.store.common.comboStore",
							{
								proxy:{
									type:'ajax',
									method:'post',
									url:'odata_ExpCheckAction_getCustomGoodsNumberList.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									}
								}
							}),
				},{
					xtype:'wms_DefFieldValCombo',
					fieldLabel : $i18n.manage_status,   //状态
					id:'cmbStatus3701',
					allowBlank:false,
					store:Ext.create("cms.store.common.comboStore",
					{
						proxy:{
							type:'ajax',
							method:'post',
							url:'odata_ExpCheckAction_getStatusList.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
						}
	   				}).load()
				},{
					xtype:'container',
					items:[
					{
						xtype: 'button',
				        text: $i18n.query,  //查询
				        margin : '0 0 0 45',
				        id:'findButton1',
				        width : 80
					}],       
				}]
            },{
					xtype:'grid',				
					id:'out_Stock_FindGrid2',
					title : $i18n.outStockQuery,	//出库查询
					store:odata_Exp_DStore2,
					region:'west',
					width : '100%',
					columns:[{
						width : 170,
						text : $i18n.outStockOrder,//出货申请单
						dataIndex : 'sourceexpNo'
					},{
						width : 80,
						text : $i18n.customNumber,//客户编码
						dataIndex : 'custNo'
					},{
						width : 130,
						text : $i18n.customName, //客户名称
						dataIndex : 'custName'
					},{
						width : 80,
						text : $i18n.customGoodsNumber,//货主商品编号
						dataIndex : 'articleNo'
					},{
						width : 210,
						text : $i18n.article_name,//商品名称
						dataIndex : 'articleName'
					},{
						width : 180,
						text : $i18n.barcode,//商品条码
						dataIndex : 'barcode'
					},{
						width : 60,
						text : $i18n.planQty,//单据量
						dataIndex : 'planQty'
					},{
						width : 60,
						text : $i18n.scanQty,//扫描量
						dataIndex : 'scanQty'
					},{
						width : 60,
						text : $i18n.compare_qty,//差异量
						dataIndex : 'diffQty'
					},{
						width : 80,
						text : $i18n.scanDate,//扫描日期
						dataIndex:'strRgstDate2',
					},{
						width : 80,
						text : $i18n.manage_status,//状态
						dataIndex : 'statusDesc'
					}],
					dockedItems : [{
						xtype : 'pagingtoolbar',
						store : odata_Exp_DStore2,
						dock : 'bottom',
						displayInfo : true
					} ]
				}]
		}]
	} ]
});








