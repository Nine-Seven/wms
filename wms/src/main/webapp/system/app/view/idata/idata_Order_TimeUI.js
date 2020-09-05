/**
 * 模块名称：预约进货
 * 模块编码：4201
 * 创建：LICH
 */
var grid_01_4201=Ext.create('cms.store.idata.idata_Order_StatusStore',{
	autoLoad:false,
	listeners:{  
		'load':function(th,records,successful,eOpts ){
		if(th.count()>0){
			Ext.getCmp('grid_01_4201').getSelectionModel().select(0);
			}
		}
	}
});
var grid_02_4201=Ext.create('cms.store.idata.idata_Order_StatusStore',{
	listeners:{  
		'load':function(th,records,successful,eOpts ){
		if(th.count()>0){
			Ext.getCmp('grid_02_4201').getSelectionModel().select(0);
			var arrayObj = new Array();
			arrayObj[0]='articleItems';
			arrayObj[1]='pkQty';
			arrayObj[2]='weight';
			arrayObj[3]='volumn';
			countList('grid_02_4201',arrayObj,'orderSerial');
			}
		}
	}
});
var grid_03_4201=Ext.create('cms.store.idata.idata_Order_SheetStore',{autoLoad:false});
var grid_06_4201=Ext.create('cms.store.idata.dock_SheetStore',{autoLoad:false});
var grid_05_4201 = Ext.create('cms.store.idata.idata_ImPort_MStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'idata_Order_TimeAction_getIdata_Import_MList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	},
	autoLoad: false
	});
Ext.define('cms.view.idata.idata_Order_TimeUI',{
	alias:'widget.idata_Order_TimeUI',
	title:$i18n.order_stock,//手工验收单
	id:'idata_Order_TimeUI',
	width:'100%',
	layout:'border',
	extend : 'Ext.panel.Panel',
	requires:[
		  'cms.view.common.commMenu',
		  'cms.view.common.commMenu4',
		  'cms.view.common.commMenu5',
		  'cms.view.common.remoteCombo',
		  'cms.view.common.bdef_DefOwnerCombo',
		  'cms.view.common.bdef_DefSupplierCombo',
		 	'cms.view.common.wms_DefFieldValCombo'
		  ],
	items:[
	{
	    xtype:'commMenuWidget4',
	    id:'menu4201',
	   	region:'north'
	},{
		xtype : 'tabpanel',
		id : 'tabPId4201',
		region : 'center',
		items : [{
			xtype:'panel',
			title:$i18n.order_condition,//预约状况
			layout:'border',
			items: [{
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
					xtype : 'datefield',
					fieldLabel : $i18n.appoint_date,//预约日期
					id : 'appoint_date4201',
					format : 'Y-m-d',
					beforeLabelTextTpl : required
				},{
					xtype : 'combo',
					fieldLabel : $i18n.owner,// 委托业主
					id : 'owner4201',
					displayField: 'dropValue',
					valueField: 'value',
					queryMode: 'local',
					store:Ext.create("cms.store.common.comboStore",
					{
						proxy:{
							type:'ajax',
							method:'post',
							url:'idata_Order_TimeAction_getIdata_Order_Time_GetCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							},
							extraParams:{
								flag : "4"
							}
						},
						listeners:{  
						'load':function(th,records,successful,eOpts ){
							if(th.count()>0){
								Ext.getCmp('owner4201').setValue(th.getAt(0).data.value);
								_myAppGlobal.getController('cms.controller.idata.idata_Order_TimeController').owner4201Select();
							}else{
								Ext.getCmp('owner4201').setValue('');
								Ext.getCmp('grid_01_4201').getStore().removeAll();
							}
						}
						}
	   				}),
					beforeLabelTextTpl : required
				},{
				 	xtype:'combo',
					fieldLabel : $i18n.dock,//码头
				 	id:'dockNo4201_1',
	    	        displayField: 'dropValue',
					valueField: 'value',
					store:Ext.create("cms.store.common.comboStore",
					{
						proxy:{
							type:'ajax',
							method:'post',
							url:'idata_Order_TimeAction_getDockNoCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
						}
	   				})
				}]
			},{
				//预约状况>>已约码头信息
		        xtype: 'grid',
		        region: 'west',
		        split: true,
		        store:grid_01_4201,
		        id: 'grid_01_4201',
		        columns : [ {
					xtype : 'rownumberer',
					width : 30
				},{
					width : 120,
					text : $i18n.owner,//货主
					dataIndex : 'ownerNoText'
				},{
					width : 120,
					text : $i18n.dock,//码头
					dataIndex : 'dockText'
				},{
				width : 90,
				text : $i18n.start_time,//开始时间
				dataIndex : 'startTime'
				},{
				width : 90,
				text : $i18n.endtime,
				dataIndex : 'endTime'
				}]
			},{
				xtype:'panel',
				region: 'center',
				layout:'border',
				items: [{
					//预约状况>>已约信息
			        xtype: 'grid',
			        region: 'north',
			        height:250,
			        split: true,
			        store:grid_02_4201,
			        id: 'grid_02_4201',
			        columns : [ {
						xtype : 'rownumberer',
						width : 30
					},{
						width : 90,
						text : $i18n.order_serial,
						dataIndex : 'orderSerial'
					},{
						width : 150,
						text : $i18n.s_import_no,
						dataIndex : 'SImportNo'
					},{
						width : 90,
						text : $i18n.supplier_no,
						dataIndex : 'supplierNo'
					},{
						width : 170,
						text : $i18n.supplier_name,
						dataIndex : 'supplierName'
					},{
						width : 60,
						text : '品项数',
						dataIndex : 'articleItems'
					},{
						width : 60,
						text : '计划数量',
						dataIndex : 'pkQty'
					},{
						width : 60,
						text : '重量',
						dataIndex : 'weight'
					},{
						width : 60,
						text : '体积',
						dataIndex : 'volumn'
					}]
				},{
				  	xtype: 'grid',
			        region: 'center',
			        split: true,
			        store:grid_03_4201,
			        id: 'grid_03_4201',
			        columns : [ {
						xtype : 'rownumberer',
						width : 30
						},{
						width : 160,
						text : $i18n.import_no,
						dataIndex : 'importNo'
						},{
						width : 170,
						text : $i18n.po_no1,
						dataIndex : 'poNo'
						}]
				}]
			}]
			},{
				xtype:'panel',
				title:$i18n.aboutwarehouse,//约仓
				layout:'border',
				items: [{
					xtype:'panel',
					region:'north',
					height: 87,
					layout: {
					    type: 'table',
				        columns: 2
					},
				    defaults : {
						xtype : 'textfield',
						margin : '3 3 3 3',
						labelAlign:'right',
						allowBlank: true,
						labelWidth : 90
					},
					items:[{
						xtype:'fieldset',
				        title: $i18n.filterCondition,//过滤条件
				        columnWidth: 0.5,
			         	width : 750,
		       	        layout: {
					        type: 'table',
					        columns: 3
					    },
					    defaults : {
							xtype : 'textfield',
							labelAlign:'right',
							allowBlank: true,
							width : 240,
							labelWidth : 90
						},
				        items :[{
							xtype : 'combo',
							fieldLabel : $i18n.owner,// 委托业主
							id : 'tab3_owner4201',
							displayField: 'dropValue',
							valueField: 'value',
							queryMode: 'local',
							store:Ext.create("cms.store.common.comboStore",
							{
								proxy:{
									type:'ajax',
									method:'post',
									url:'idata_Order_TimeAction_getIdata_Order_Time_GetCombo.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									},
									extraParams:{
										flag : "1"
									}
								},
								listeners:{  
								'load':function(th,records,successful,eOpts ){
									if(th.count()>0){
										Ext.getCmp('tab3_owner4201').setValue(th.getAt(0).data.value);
										_myAppGlobal.getController('cms.controller.idata.idata_Order_TimeController').tab3_owner4201Select();
									}
								}
								}
			   				}),
							beforeLabelTextTpl : required
						},{
						 	xtype:'wms_DefFieldValCombo',
							fieldLabel : $i18n.supplier_no,//供应商编号
						 	id:'tab3_supplierNo4201',
			    	        displayField: 'dropValue',
							valueField: 'value',
							queryMode: 'local',
							store:Ext.create("cms.store.common.comboStore",
							{
								proxy:{
									type:'ajax',
									method:'post',
									url:'idata_Order_TimeAction_getIdata_Order_Time_GetCombo.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									},
									extraParams:{
										flag : "2"
									}
								},
								listeners:{  
								'load':function(th,records,successful,eOpts ){
									if(th.count()>0){
										Ext.getCmp('tab3_supplierNo4201').setValue(th.getAt(0).data.value);
										_myAppGlobal.getController('cms.controller.idata.idata_Order_TimeController').tab3_supplierNo4201Select();
									}
								}
								}
			   				}),
				        	beforeLabelTextTpl : required
						},{
						 	xtype:'combo',
							fieldLabel : $i18n.outstocktype,
						 	id:'tab3_outStockType4201',
			    	        displayField: 'dropValue',
							valueField: 'value',
							queryMode: 'local',
							store:Ext.create("cms.store.common.comboStore",
							{
								proxy:{
									type:'ajax',
									method:'post',
									url:'idata_Order_TimeAction_getIdata_Order_Time_GetCombo.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									},
									extraParams:{
										flag : "3"
									}
								},
								listeners:{  
								'load':function(th,records,successful,eOpts ){
									if(th.count()>0){
										Ext.getCmp('tab3_outStockType4201').setValue(th.getAt(0).data.value);
										_myAppGlobal.getController('cms.controller.idata.idata_Order_TimeController').tab3_outStockType4201Select();
									}
								}
								}
			   				}),
				        	beforeLabelTextTpl : required
						}]
					},{			         	
						xtype : 'panel',
						border:0,
						layout: 
						{
							type: 'table',
							columns: 1
						},
						defaults : 
						{
							xtype : 'textfield',
							labelWidth : 75						
					    },
						items :[{
			        		id:'radiogroup4201',
			        		xtype:'radiogroup',
			        		width:280,
			                columns: 3,
			                border:1,
			                vertical: false,
			        		items:[{
			        			boxLabel: '不打印', name:  'rb',  inputValue: '1',checked:true
			        		},{
			        			boxLabel: '打印表单', name: 'rb',  inputValue: '2'
			        		},{
			        			boxLabel: '打印标签', name: 'rb',  inputValue: '3'
			        		}]
			        	
						},{
					        	xtype : 'datefield',
								fieldLabel : $i18n.appoint_date,//预约日期
								id : 'tab3_appoint_date4201',
								format : 'Y-m-d',
								value: new Date(),
								minValue:new Date(),
								beforeLabelTextTpl : required
					       },{
							 	xtype:'combo',
								fieldLabel : $i18n.dock,//码头
							 	id:'dockNo4201_2',
				    	        displayField: 'dropValue',
								valueField: 'value',
								
								store:Ext.create("cms.store.common.comboStore",
								{
									proxy:{
										type:'ajax',
										method:'post',
										url:'bdef_DefDockAction_getDockComboList.action',
										reader:{
											root:'rootList',
											totalProperty:'totalCount'
										}
									}
				   				}).load(),
				   				beforeLabelTextTpl : required
							}]		
					}]
				},{
					//进货预约>>待预约进货单
			        xtype: 'grid',
			        region: 'center',
			        split: true,
			        store:grid_05_4201,
			        multiSelect: true,  
					selModel: {  
					    selType:'checkboxmodel',
					    checkOnly:true
					},
			        id: 'grid_05_4201',
			        columns : [{
						width : 90,
						text : $i18n.owner_no,//委托业主编号
						dataIndex : 'ownerNo' 
						},{
						width:80,
						text:$i18n.outstocktype,//单据类型
						dataIndex:'potypeText'
						},{
						width : 135,
						text : $i18n.po_no1,//采购单号
						dataIndex : 'poNo'
						},{
						width : 135,
						text : $i18n.import_no,//手建单号
						dataIndex : 'importNo'
						},{
						width : 90,
						text : $i18n.request_date1,//预定到货日
						dataIndex : 'requestDate'
						},{
							width : 60,
							text : '品项数',//品项数
							dataIndex : 'articleItems'
						},{
							width : 60,
							text : '数量',
							dataIndex : 'pkQty'
						},{
						width : 80,
						text : $i18n.status,//状态	
						dataIndex : 'statusText'
					}]
				},{
					//预约状况>>已约信息
			        xtype: 'grid',
			        region: 'east',
			        width:350,
			        split: true,
			        margins: '0 0 0 5',
			        store:grid_06_4201,
			        id: 'grid_06_4201',
			        viewConfig : 
			        {   
		                 forceFit : true,   
		                 getRowClass : function(record,rowIndex,rowParams,store){   
		                    //禁用数据显示红色   
		                    if(record.data.endTime<Ext.Date.format(new Date(), 'H:i')){   
		                        return 'x-grid-record-red';   
		                     }else{   
		                        return '';   
		                     }   
		                 }   
		            },  
			        columns : [ {
						xtype : 'rownumberer',
						width : 30
						},{
						width : 120,
						text : $i18n.start_time,//开始时间
						dataIndex : 'startTime'
						},{
						width : 120,
						text : $i18n.endtime,
						dataIndex : 'endTime'
						}]
				}]
			}
		]
	}]          
});