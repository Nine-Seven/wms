/**
 * 模块名称：拣货批量发单
 * 模块编码：3301
 * 创建：周欢
 */
 //获得拣货发单自动发单界面下架单头档信息
var gridOutstockM3301Store=Ext.create('cms.store.odata.odata_OutstockDirectStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_OutstockMAction_getOdata_OutstockDirect.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		},
		extraParams:{
			strFlag : "1",
			industryFlag : "1"
		}
	}
});
//获得拣货发单自动发单界面明细信息
var gridOutstockD3301Store=Ext.create('cms.store.odata.odata_OutstockDirectStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_OutstockMAction_getOdata_OutstockDirect.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		},
		extraParams:{
			strFlag : "2",
			industryFlag : "1"
		}
	}
});
//获取未发单下架指示 huangb 20160805
var gridOutstockD3301NotSendStore=Ext.create('cms.store.odata.odata_OutstockDirectStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_OutstockMAction_getOdata_OutstockDirect.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		},
		extraParams:{
			strFlag : "7",
			industryFlag : "1"
		}
	}
});

//获得拣货批量发单界面和未发单界面的波次号
var gridOutstockMWaveNoSend3301Store=Ext.create('cms.store.odata.odata_OutstockDirectStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_OutstockMAction_getOdata_OutstockDirect.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		},
		extraParams:{
			strFlag : "3",
			strSendFlag : "auto",
			industryFlag : "1"
		}
	},
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(th.count()>0 && Ext.getCmp('tabpanel3301').getActiveTab().id=='tabAutoSend3301'){
				Ext.getCmp('gridOutstockMWaveNoSend3301').getSelectionModel().select(0);
			}else if(th.count()>0 && Ext.getCmp('tabpanel3301').getActiveTab().id=='tabAutoCheck3301')
			{
				Ext.getCmp('gridOutstockMWaveNoCheck3301').getSelectionModel().select(0);
			}
		}
	}
});

/**
 * 拣货发单自动成单界面
 * 周欢
 */
Ext.define('cms.view.odata.odata_OutstockMAutoSendUI',{
	alias:'widget.odata_OutstockMAutoSendUI',
	title:$i18n.oustockAutoSend,//拣货发单自动成单
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:['cms.view.common.commMenu4',
	'cms.view.common.wms_DefFieldValCombo',
	'cms.view.common.bdef_DefWorkerCombo',
	'cms.view.common.bdef_DefOwnerCombo'],
	items:[
			{
				xtype : 'commMenuWidget4',
				id:'menu3301',
				region:'north'
			},
			{
				xtype : 'tabpanel',
				id : 'tabpanel3301',
				region : 'center',
                items:[
                {
                   title:$i18n.autoSend,
                   id:'tabAutoSend3301',
                   layout:'border',
                   items:[
                   {
                	   xtype : 'form',
						region : 'north',
						layout : {
							 type : 'table',
							 columns :6
						 },
						width:'100%',
						frame : true,
						defaults : {
							labelWidth : 80,
							margin:'0 15 2 15 ',
//							width:200,
							labelAlign : 'right'
						},
						items:[
							{
								xtype:'bdef_DefOwnerCombo',
								fieldLabel:$i18n.owner,//货主编号
								queryMode:'local',
							    allowBlank:true,
							    labelWidth : 50,
							    id:'cmbOwnerNoSend3301',
							    displayField: 'dropValue',
								valueField: 'value',
								store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',
								 {
									 proxy:{
											type:'ajax',
											method:'post',
											url:'odata_OutstockMAction_getOdataGetCombo',
											reader:{
												root:'rootList',
												totalProperty:'totalCount'
											},
											extraParams:{
												strFlag : "1",
												strSendFalg : "auto",
												industryFlag : "1"
											}
										},	 
									listeners:{  
											'load':function(th,records,successful,eOpts ){
												if(th.count()>0){
													Ext.getCmp('cmbOwnerNoSend3301').setValue(th.getAt(0).data.value);
												}
											}
										}
									}),
							    beforeLabelTextTpl:required
							},
							{
							xtype:'wms_DefFieldValCombo',
							fieldLabel : $i18n.exp_type,// 出货单别
							id : 'cmbExp_typeSend3301',
							displayField: 'dropValue',
							valueField: 'value',
							queryMode: 'local',
							store:Ext.create("cms.store.common.comboStore",
							{
								proxy:{
									type:'ajax',
									method:'post',
									url:'odata_OutstockMAction_getOdataGetCombo.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									},
									extraParams:{
										strFlag : "2",
										industryFlag : "1"
									}
								},
								listeners:{  
								'load':function(th,records,successful,eOpts ){
									if(th.count()>0){
										Ext.getCmp('cmbExp_typeSend3301').setValue(th.getAt(0).data.value);
									}
								}
								}
			   				}),
							beforeLabelTextTpl : required
						},
						{
							xtype : 'checkboxfield',
							id : 'checkBoxPrint_type3301',
							boxLabel : $i18n.print_report//打印报表
						
						}/*,   //8-4屏蔽
						{
							xtype : 'checkboxfield',
							id : 'printFaceSheet3301',
							boxLabel : '打印面单',//打印面单
						    margin : '0 -2 0 0'
						},
						{
							xtype : 'checkboxfield',
							id : 'printBuilt3301',
							boxLabel : '打印内置清单'//打印内置清单
						},
						{
							xtype : 'checkboxfield',
							id : 'printInvoice3301',
							boxLabel : '打印发票'//打印发票
						}*/
					]
					},
					{
				    	xtype : 'grid',
						region : 'west',
						id:'gridOutstockMWaveNoSend3301',
						store : gridOutstockMWaveNoSend3301Store,
						width:'40%',
						columns : [ 
						{
							xtype : 'rownumberer',
							width : 30 
						}, 
						{
							width : 140,
							text : $i18n.locate_no,//波次号
							dataIndex:'waveNo'
						}, 
						{
							width : 140,
							text : $i18n.batch_no2,//批次号
							dataIndex:'batchNo'
						}, 
						{
							width : 140,
							text : $i18n.outstocktype,//单据类型
							dataIndex:'outstockTypeText'
						}
					    ]
			        },
			        {
						xtype : 'form',
						region : 'center',
						layout:'column',
						border:false,
						frame : true,
						defaults : {
							labelWidth : 60,
							margin : '2 2 2 2',
							labelAlign : 'right',
							width:200
						},
						items:[
						{
							xtype:'wms_DefFieldValCombo',
							fieldLabel : $i18n.operate_type,// 作业类型
							align : 'center', 
							id:'cmbOperate_type3301',
							store:Ext.create("cms.store.common.comboStore",
							{
								proxy:{
									type:'ajax',
									method:'post',
									url:'odata_OutstockMAction_getOdataGetCombo.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									},
									extraParams:{
										strFlag : "6",
										industryFlag : "1"
									}
								},
								listeners:{  
								'load':function(th,records,successful,eOpts ){
									if(th.count()>0){
										Ext.getCmp('cmbOperate_type3301').setValue(th.getAt(0).data.value);
									}
								}
								}
			   				}),
							beforeLabelTextTpl : required
						}
						]
					},
					{
				    	xtype : 'grid',
						region : 'east',
						id:'gridOutstockMAreaNoSend3301',
						multiSelect: true,  
						selModel: {  
						    selType:'checkboxmodel',
						    checkOnly:true
						}, 
						
						store:gridOutstockM3301Store,
						//同一物流箱有不同状态,不允许发单 huangb 20160805
						viewConfig : {
			                 forceFit : true,   
			                 getRowClass : function(record,rowIndex,rowParams,store){
			                    //禁用数据显示红色   
			                    if(record.data.isSengflag=='不可发单'){   
			                        return 'x-grid-record-red';   
			                     }else{   
			                        return '';   
			                     }   
			                 }   
					    },
						width:'40%',
						columns : [ 
						{
							xtype : 'rownumberer',
							width : 30
						}, 
						{
							width : 140,
							text : $i18n.s_area_no,//储区代码
							dataIndex:'wareaNo'
						}, 
						{
							width : 140,
							text : $i18n.s_area_name,//储区名称
							dataIndex:'areaName'
						}
					    ]
			        },
					{
						region : 'south',
						//xtype : 'panel',
						height:300,
						layout:'border',
						items:[
						 {
						    xtype : 'grid',
						    region:'center',
							id:'gridOutstockDSend3301',
							store:gridOutstockD3301Store,
							width:'100%',
							columns : [ 
							{
								xtype : 'rownumberer',
								width : 30
							}, 
							{
								width : 100,
								text : $i18n.s_cell_no,//来源储位
								dataIndex:'SCellNo'
							}, 
							{
								width : 110,
								text : $i18n.article_no,//商品编码
								dataIndex:'articleNo'
							}, 
							{
								width : 100,
								text : $i18n.owner_article_no,//货主商品编码
								dataIndex : 'ownerArticleNo'
							},

							{
								width : 180,
								text : $i18n.article_name,//商品名称
								dataIndex:'articleName'
							}, 
							{
								width : 110,
								text : $i18n.barcode,//商品条码
								dataIndex:'barcode'
							}, 
							{
								width : 60,
								text : $i18n.articleqty,//计划数量
								dataIndex:'locateQty'
							},{
								width : 60,
								text : $i18n.packing_unit,
							    id:'packingUnit3301_1',
								dataIndex:'packingUnit'
							},
							//add by huangcx at 20160528
							{
			    			    width:80,
			    			    text : $i18n.packingSpec,
							    id:'packingSpec3301_1',
			    			    dataIndex:'packingSpec'			
			    		    },
			    		    //end add
							{
								width : 60,
								text : $i18n.new_packing_qty,//包装数量
								dataIndex:'packingQty'
							},
							{
								width : 90,
								text : $i18n.produce_date,//生产日期
								dataIndex:'produceDate',
								hidden:$i18n.produceDateHidden,
					            renderer:function(value){   
								    if(value instanceof Date){   				 
								        return Ext.Date.format(value,'Y-m-d');   
								    }else{				        
								        return value;   
								    }  
								}
							},
							{
								width : 90,
								text : $i18n.expire_date,//有效期至
								dataIndex:'expireDate',
								hidden:$i18n.expireDateHidden,
					            renderer:function(value){   
								    if(value instanceof Date){   				 
								        return Ext.Date.format(value,'Y-m-d');   
								    }else{				        
								        return value;   
								    }  
								}
							},
							{
								width:40,
								text:$i18n.quality,//'品质',
								hidden:$i18n.qualityHidden,
								dataIndex:'textQuality'
							},
							{
								width:80,
								text:$i18n.import_batch_no,//验收批次
								hidden:$i18n.importBatchNoHidden,
								dataIndex:'importBatchNo'
							},
							{
								width:120,
								text:$i18n.lot_no,//'批号',
								hidden:$i18n.lotNoHidden,
								dataIndex:'lotNo'
							},
							{
								width:90,
								text:$i18n.reserved1,//'预留字段1',
								hidden:$i18n.rsvBatch1Hidden,
								dataIndex:'rsvBatch1'
							},
							{
								width:90,
								text:$i18n.reserved2,//'预留字段2',
								hidden:$i18n.rsvBatch2Hidden,
								dataIndex:'rsvBatch2'
							},
							{
								width:90,
								text:$i18n.reserved3,//'预留字段3',
								hidden:$i18n.rsvBatch3Hidden,
								dataIndex:'rsvBatch3'
							},
							{
								width:90,
								text:$i18n.reserved4,//'预留字段4',
								hidden:$i18n.rsvBatch4Hidden,
								dataIndex:'rsvBatch4'
							},
							{
								width:90,
								text:$i18n.reserved5,//'预留字段5',
								hidden:$i18n.rsvBatch5Hidden,
								dataIndex:'rsvBatch5'
							},
							{
								width:90,
								text:$i18n.reserved6,//'预留字段6',
								hidden:$i18n.rsvBatch6Hidden,
								dataIndex:'rsvBatch6'
							},
							{
								width:100,
								text:$i18n.reserved7,//'预留字段7',
								hidden:$i18n.rsvBatch7Hidden,
								dataIndex:'rsvBatch7'
							},
							{
								width:90,
								text:$i18n.reserved8,//'预留字段8',
								hidden:$i18n.rsvBatch8Hidden,
								dataIndex:'rsvBatch8'
							}
						    ]
						 },
						 {
							 xtype : 'form',
							 frame : true,
							 region:'south',
							 height: 40,
							 layout : {
								 type : 'table',
								 columns :2
							 },
							width:'100%',
							defaults : {
								labelWidth : 80,
								margin:'0 0 0 500',
								labelAlign:'right'
							},
							items:[
								{
									xtype : 'bdef_DefWorkerCombo',
									fieldLabel : $i18n.dispatch_worker,//  
									id : 'cmbWorkerNo3301',
									store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore'),
									beforeLabelTextTpl : required
								},
								{
									xtype : 'button',
									margin:'0 0 0 10',
									id:'butSend3301',
									//disabled:true,
									text : $i18n.send//发单
								}]
						 }
						]
			        }
                   ]
                },
                {
        	    	title:$i18n.noSendDirect,//未发单指示
        	        layout:'border',
        	        id:'tabAutoCheck3301',
        	        items:[
        	        {
				    	xtype : 'grid',
						region : 'north',
						width:'100%',
						id:'gridOutstockMWaveNoCheck3301',
						store:gridOutstockMWaveNoSend3301Store,
						height:260,
						columns : [ 
						{
							xtype : 'rownumberer',
							width : 30
						}, 
						{
							width : 140,
							text : $i18n.locate_no,//波次号
							dataIndex:'waveNo'
						}, 
						{
							width : 140,
							text : $i18n.source_type,//单据类型
							dataIndex:'sourceTypeText'
						}
					    ]
        	        },
        	        {

				    	xtype : 'grid',
						region : 'center',
						id:'gridOutstockDCheck3301',
						//store:gridOutstockD3301Store,
						store:gridOutstockD3301NotSendStore,
						width:'100%',
						columns : [ 
						{
							xtype : 'rownumberer',
							width : 30
						}, 
						{
							width : 60,
							text : $i18n.operate_type,// 作业类型
							dataIndex:'operateType'
						}, 
						{
							width : 60,
							text : $i18n.exp_type,// 出货单别
							dataIndex:'expType'
						}, 
						{
							width : 130,
							text : $i18n.exp_no,// 出货单号
							dataIndex:'expNo'
						},
						{
							width : 80,
							text : $i18n.s_cell_no,//来源储位
							dataIndex:'SCellNo'
						},
						{
							width : 80,
							text : $i18n.status,//是否可发单状态 huangb 20160805
							dataIndex:'isSengflag'
						}, 
						{
							width : 110,
							text : $i18n.article_no,//商品编码
							dataIndex:'articleNo'
						},{
							width : 100,
							text : $i18n.owner_article_no,//货主商品编码
							dataIndex : 'ownerArticleNo'
						},
						{
							width : 180,
							text : $i18n.article_name,//商品名称
							dataIndex:'articleName'
						}, 
						{
							width : 110,
							text : $i18n.barcode,//商品条码
							dataIndex:'barcode'
						}, 
						{
							width : 60,
							text : $i18n.articleqty,//计划数量
							dataIndex:'locateQty'
						},{
							width : 60,
							text : $i18n.packing_unit,
						    id:'packingUnit3301_2',
							dataIndex:'packingUnit'
						},
						//add by huangcx at 20160528
						{
		    			    width:80,
		    			    text : $i18n.packingSpec,
						    id:'packingSpec3301_2',
		    			    dataIndex:'packingSpec'			
		    		    },
		    		    //end add
						{
							width : 60,
							text : $i18n.new_packing_qty,//包装数量
							dataIndex:'packingQty'
						},
						{
							width : 90,
							text : $i18n.produce_date,//生产日期
							dataIndex:'produceDate',
							hidden:$i18n.produceDateHidden,
				            renderer:function(value){   
							    if(value instanceof Date){   				 
							        return Ext.Date.format(value,'Y-m-d');   
							    }else{				        
							        return value;   
							    }  
							}
						},
						{
							width : 90,
							text : $i18n.expire_date,//有效期至
							dataIndex:'expireDate',
							hidden:$i18n.expireDateHidden,
				            renderer:function(value){   
							    if(value instanceof Date){   				 
							        return Ext.Date.format(value,'Y-m-d');   
							    }else{				        
							        return value;   
							    }  
							}
						},
						{
							width:40,
							text:$i18n.quality,//'品质',
							hidden:$i18n.qualityHidden,
							dataIndex:'textQuality'
						},
						{
							width:80,
							text:$i18n.import_batch_no,//验收批次
							hidden:$i18n.importBatchNoHidden,
							dataIndex:'importBatchNo'
						},
						{
							width:80,
							text:$i18n.lot_no,//'批号',
							hidden:$i18n.lotNoHidden,
							dataIndex:'lotNo'
						},
						{
							width:90,
							text:$i18n.reserved1,//'预留字段1',
							hidden:$i18n.rsvBatch1Hidden,
							dataIndex:'rsvBatch1'
						},
						{
							width:90,
							text:$i18n.reserved2,//'预留字段2',
							hidden:$i18n.rsvBatch2Hidden,
							dataIndex:'rsvBatch2'
						},
						{
							width:90,
							text:$i18n.reserved3,//'预留字段3',
							hidden:$i18n.rsvBatch3Hidden,
							dataIndex:'rsvBatch3'
						},
						{
							width:90,
							text:$i18n.reserved4,//'预留字段4',
							hidden:$i18n.rsvBatch4Hidden,
							dataIndex:'rsvBatch4'
						},
						{
							width:90,
							text:$i18n.reserved5,//'预留字段5',
							hidden:$i18n.rsvBatch5Hidden,
							dataIndex:'rsvBatch5'
						},
						{
							width:90,
							text:$i18n.reserved6,//'预留字段6',
							hidden:$i18n.rsvBatch6Hidden,
							dataIndex:'rsvBatch6'
						},
						{
							width:100,
							text:$i18n.reserved7,//'预留字段7',
							hidden:$i18n.rsvBatch7Hidden,
							dataIndex:'rsvBatch7'
						},
						{
							width:90,
							text:$i18n.reserved8,//'预留字段8',
							hidden:$i18n.rsvBatch8Hidden,
							dataIndex:'rsvBatch8'
						}
					    ]
        	        }
        	        ]
        	    }
                ]
			}
	       ]
});