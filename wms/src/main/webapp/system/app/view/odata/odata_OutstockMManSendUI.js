/**
 * 模块名称：拣货按客户发单
 * 模块编码：3401
 * 创建：周欢
 */
 
var gridOutstockMManSend3401Store=Ext.create('cms.store.odata.odata_OutstockDirectStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_OutstockMAction_getOdata_OutstockDirect.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		},
		extraParams:{
			strFlag : "4",
			industryFlag : "1"
		}
	}/*,
	listeners:{
		'load':function(th,records,successful,eOpts){
			Ext.getCmp('gridOutstockMManSend3401').getSelectionModel().selectAll();
		}
	}*/
});
var gridOutstockDManSend3401Store=Ext.create('cms.store.odata.odata_OutstockDirectStore',{
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
var gridOutstockDManSend3401NotSendStore=Ext.create('cms.store.odata.odata_OutstockDirectStore',{
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
var gridOutstockMObjSend3401Store=Ext.create('cms.store.odata.odata_OutstockDirectStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_OutstockMAction_getOdata_OutstockDirect.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		},
		extraParams:{
			strFlag : "5",
			industryFlag : "1"
		}
	},
	listeners:{
		'load':function(th,records,successful,eOpts){
			Ext.getCmp('gridOutstockMObjSend3401').getSelectionModel().selectAll();
		}
	}
});
//获得未发单界面波次号
var gridOutstockMWaveNoSend3401Store=Ext.create('cms.store.odata.odata_OutstockDirectStore',{
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
			industryFlag : "1"
		}
	},
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(th.count()>0 && Ext.getCmp('tabpanel3401').getActiveTab().id=='tabManCheck3401')
			{
				Ext.getCmp('gridOutstockMLocateNoCheck3401').getSelectionModel().select(0);
			}
		}
	}
});

Ext.define('cms.view.odata.odata_OutstockMManSendUI',{
	alias:'widget.odata_OutstockMManSendUI',
	title:$i18n.manSend,//拣货手动成单发单
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
				id:'menu3401',
				region:'north'
			},
	       {
	    	    xtype : 'tabpanel',
				id : 'tabpanel3401',
				region : 'center',
				items:[
				{
				title:$i18n.manSend,
                id:'tabManSend3401',
                layout:'border',
                items:[{
					xtype : 'form',
					id:'formSendCondition3401',
					layout:{
						type : 'table',
						columns : 2
					},
					region : 'west',
					frame : true,
					width : 480,
					defaults:{
						labelAlign : 'right',
						xtype:'textfield' ,
						labelWidth : 60
					},
					/*items:[
					{
						xtype:'fieldset',
				        autoHeight:false,  
				        width : 480,
				        title:$i18n.filterCondition,
		       	        layout: {
					        type: 'table',
					        columns: 2
					    },
		    		    defaults : {
							xtype : 'textfield',
							margin : '3 3 3 3',
							labelAlign:'right',
							allowBlank: true,
							labelWidth : 60
						},*/
				       items:[
						{
							xtype:'bdef_DefOwnerCombo',
							fieldLabel:$i18n.owner,//货主编号
							queryMode:'local',
						    allowBlank:true,
						    id:'cmbOwnerNoSend3401',
						    displayField: 'dropValue',
							valueField: 'value',
							store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',
							 {
								proxy:{
									type:'ajax',
									method:'post',
									async:false,
									url:'odata_OutstockMAction_getOdataGetCombo',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									},
									extraParams:{
										strFlag : "1",
										strSendFlag : "man",
										industryFlag : "1"
									}
								},	 
							    listeners:{  
									'load':function(th,records,successful,eOpts ){
										if(th.count()>0){
											Ext.getCmp('cmbOwnerNoSend3401').setValue(th.getAt(0).data.value);
											_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendController').cmbOwnerNoSend3401Select();
										}else{
											Ext.getCmp('cmbExp_typeSend3401').getStore().removeAll();
											Ext.getCmp('cmbExp_typeSend3401').setValue(null);
											
											Ext.getCmp('cmbLocate_no3401').getStore().removeAll();
											Ext.getCmp('cmbLocate_no3401').setValue(null);
											Ext.getCmp('cmbm_batch_no3401').setValue(null);
								      	    Ext.getCmp('cmbm_batch_no3401').getStore().removeAll();
								      
											Ext.getCmp('cmbOutStockType3401').getStore().removeAll();
											Ext.getCmp('cmbOutStockType3401').setValue(null);
											
											Ext.getCmp('cmbArea_no3401').getStore().removeAll();
											Ext.getCmp('cmbArea_no3401').setValue(null);
											
											Ext.getCmp('cmbOperate_type3401').getStore().removeAll();
											Ext.getCmp('cmbOperate_type3401').setValue(null);
											
											Ext.getCmp('gridOutstockMManSend3401').getStore().removeAll();
											Ext.getCmp('gridOutstockDManSend3401').getStore().removeAll();
										}
									}
								}
							}),
					    beforeLabelTextTpl:required
					},
					{
					xtype:'wms_DefFieldValCombo',
					fieldLabel : $i18n.exp_type,// 出货单别
					id : 'cmbExp_typeSend3401',
					displayField: 'dropValue',
					valueField: 'value',
					queryMode: 'local',
					store:Ext.create("cms.store.common.comboStore",
					{
						proxy:{
							type:'ajax',
							method:'post',
							async:false,
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
								Ext.getCmp('cmbExp_typeSend3401').setValue(th.getAt(0).data.value);
								_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendController').cmbExp_typeSend3401Select();
							}else{
								Ext.getCmp('cmbLocate_no3401').setValue(null);
					      	    Ext.getCmp('cmbLocate_no3401').getStore().removeAll();
					      		Ext.getCmp('cmbm_batch_no3401').setValue(null);
					      	    Ext.getCmp('cmbm_batch_no3401').getStore().removeAll();
					      
							    Ext.getCmp('cmbOutStockType3401').getStore().removeAll();
							    Ext.getCmp('cmbOutStockType3401').setValue(null);
								
							    Ext.getCmp('cmbArea_no3401').getStore().removeAll();
							    Ext.getCmp('cmbArea_no3401').setValue(null);
								
							    Ext.getCmp('cmbOperate_type3401').getStore().removeAll();
							    Ext.getCmp('cmbOperate_type3401').setValue(null);
							    
							    Ext.getCmp('gridOutstockMManSend3401').getStore().removeAll();
								Ext.getCmp('gridOutstockDManSend3401').getStore().removeAll();
							}
						}
						}
	   				}),
					beforeLabelTextTpl : required
				},
			/*	{
					xtype:'wms_DefFieldValCombo',
					fieldLabel : $i18n.source_type,// 波次类型
					id:'cmbSource_type3401',
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
								strFlag : "7"
							}
						},
						listeners:{  
						'load':function(th,records,successful,eOpts ){
							if(th.count()>0){
								Ext.getCmp('cmbSource_type3401').setValue(th.getAt(0).data.value);
								_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendController').cmbSource_type3401change();
							}
						}
						}
	   				}),
					beforeLabelTextTpl : required
				},*/
				{
					xtype:'wms_DefFieldValCombo',
					fieldLabel : $i18n.locate_no,// 波次号
					id:'cmbLocate_no3401',
					store:Ext.create("cms.store.common.comboStore",
					{
						proxy:{
							type:'ajax',
							method:'post',
							async:false,
							url:'odata_OutstockMAction_getOdataGetCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							},
							extraParams:{
								strFlag : "3",
								industryFlag : "1"
							}
						},
						listeners:{  
						'load':function(th,records,successful,eOpts ){
							if(th.count()>0){
								Ext.getCmp('cmbLocate_no3401').setValue(th.getAt(0).data.value);
								_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendController').cmbLocate_no3401Select();
							}else{
								Ext.getCmp('cmbm_batch_no3401').setValue(null);
					      	    Ext.getCmp('cmbm_batch_no3401').getStore().removeAll();
					      	 
								Ext.getCmp('cmbOutStockType3401').setValue(null);
					      	    Ext.getCmp('cmbOutStockType3401').getStore().removeAll();
					      	   
					      	    Ext.getCmp('cmbArea_no3401').getStore().removeAll();
							    Ext.getCmp('cmbArea_no3401').setValue(null);
								
							    Ext.getCmp('cmbOperate_type3401').getStore().removeAll();
							    Ext.getCmp('cmbOperate_type3401').setValue(null);
							    
							    Ext.getCmp('gridOutstockMManSend3401').getStore().removeAll();
								Ext.getCmp('gridOutstockDManSend3401').getStore().removeAll();
							}
						}
						}
	   				}),
					beforeLabelTextTpl : required
				},
				{
					xtype:'wms_DefFieldValCombo',
					fieldLabel : $i18n.m_batch_no,//批次
					id:'cmbm_batch_no3401',
					store:Ext.create("cms.store.common.comboStore",
					{
						proxy:{
							type:'ajax',
							method:'post',
							async:false,
							url:'odata_OutstockMAction_getOdataGetCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							},
							extraParams:{
								strFlag : "9"
							}
						},
						listeners:{  
							'load':function(th,records,successful,eOpts ){
								if(th.count()>0){
									Ext.getCmp('cmbm_batch_no3401').setValue(th.getAt(0).data.value);
									_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendController').cmbm_batch_no3401Select();
								}else{
									Ext.getCmp('cmbOutStockType3401').setValue(null);
						      	    Ext.getCmp('cmbOutStockType3401').getStore().removeAll();
						      	   
						      	    Ext.getCmp('cmbArea_no3401').getStore().removeAll();
								    Ext.getCmp('cmbArea_no3401').setValue(null);
									
								    Ext.getCmp('cmbOperate_type3401').getStore().removeAll();
								    Ext.getCmp('cmbOperate_type3401').setValue(null);
								    
								    Ext.getCmp('gridOutstockMManSend3401').getStore().removeAll();
									Ext.getCmp('gridOutstockDManSend3401').getStore().removeAll();
								}
							}
						}
	   				}),
				    beforeLabelTextTpl:required

				},
				{
					xtype:'wms_DefFieldValCombo',
					fieldLabel : '下架类型',//下架类型
					id:'cmbOutStockType3401',
					store:Ext.create("cms.store.common.comboStore",
					{
						proxy:{
							type:'ajax',
							method:'post',
							async:false,
							url:'odata_OutstockMAction_getOdataGetCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							},
							extraParams:{
								strFlag : "4",
								industryFlag : "1"
							}
						},
						listeners:{  
							'load':function(th,records,successful,eOpts ){
								if(th.count()>0){
									Ext.getCmp('cmbOutStockType3401').setValue(th.getAt(0).data.value);
									_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendController').cmbOutStockType3401Select();
								}else{
									Ext.getCmp('cmbArea_no3401').getStore().removeAll();
								    Ext.getCmp('cmbArea_no3401').setValue(null);
									
								    Ext.getCmp('cmbOperate_type3401').getStore().removeAll();
								    Ext.getCmp('cmbOperate_type3401').setValue(null);
								    
								    Ext.getCmp('gridOutstockMManSend3401').getStore().removeAll();
									Ext.getCmp('gridOutstockDManSend3401').getStore().removeAll();
								}
							}
						}
	   				}),
				    beforeLabelTextTpl:required

				},
				{
					xtype:'wms_DefFieldValCombo',
					fieldLabel : $i18n.area_no,// 储区
					id:'cmbArea_no3401',
					store:Ext.create("cms.store.common.comboStore",
					{
						proxy:{
							type:'ajax',
							method:'post',
							async:false,
							url:'odata_OutstockMAction_getOdataGetCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							},
							extraParams:{
								strFlag : "5",
								industryFlag : "1"
							}
						},
						listeners:{  
						'load':function(th,records,successful,eOpts ){
							if(th.count()>1){
								Ext.getCmp('cmbArea_no3401').setValue(th.getAt(0).data.value);
								_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendController').cmbArea_no3401Select();
							}else{
								Ext.getCmp('cmbOperate_type3401').getStore().removeAll();
		   						Ext.getCmp('cmbOperate_type3401').setValue(null);
		   						
		   						Ext.getCmp('gridOutstockMManSend3401').getStore().removeAll();
								Ext.getCmp('gridOutstockDManSend3401').getStore().removeAll();
							}
						}
						}
	   				}),
					beforeLabelTextTpl : required
				},{
					xtype:'wms_DefFieldValCombo',
					fieldLabel : $i18n.operate_type,// 作业类型
					id:'cmbOperate_type3401',
					store:Ext.create("cms.store.common.comboStore",
					{
						proxy:{
							type:'ajax',
							method:'post',
							async:false,
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
							if(th.count()>1){
								Ext.getCmp('cmbOperate_type3401').setValue(th.getAt(0).data.value);
								_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendController').cmbOperate_type3401Select();
							}else{
								Ext.getCmp('gridOutstockMManSend3401').getStore().removeAll();
								Ext.getCmp('gridOutstockDManSend3401').getStore().removeAll();
							}
						}
						}
	   				}),
					beforeLabelTextTpl : required
				},
				{
					xtype : 'checkboxfield',
					id : 'checkBoxPrint_type3401',
					boxLabel : $i18n.print_report,//打印报表
					margin : '0 0 2 65',
					colspan: 2
				}
				]/*}
			  ]*/},
			   {
					xtype:'grid',
					//width : '50%',
					//height:250,
					region : 'center',
					store:gridOutstockMManSend3401Store,
			        id: 'gridOutstockMManSend3401',
					multiSelect: true,  
					viewConfig : {   
		                 forceFit : true,   
		                 getRowClass : function(record,rowIndex,rowParams,store){   
		                    //禁用数据显示红色   
		                    if(record.data.flag=='1'){   
		                        return 'x-grid-record-red';   
		                     }else{   
		                        return '';   
		                     }   
		                 }   
				    },  					
					selModel: {  
					    selType:'checkboxmodel',
					    checkOnly:true
					}, 
			        columns:[{			
						xtype : 'rownumberer',
						width : 30
					},{
						width : 100,
						text : $i18n.cust_no,//客户编号
						dataIndex : 'custNo'
					},{
						width:160,
						text :$i18n.cust_name,//客户名称
						dataIndex : 'custName'
					}]
				},
				{
					xtype:'grid',
					region : 'south',
					height : 300,
					store:gridOutstockDManSend3401Store,
			        id: 'gridOutstockDManSend3401',
					columns:[{			
						xtype : 'rownumberer',
						width : 30
					},
					{
						width : 120,
						text : $i18n.s_cell_no,//来源储位
						dataIndex : 'SCellNo'
					},{
						width:120,
						text :$i18n.article_no,//商品编码
						dataIndex : 'articleNo'
					},{
						width : 100,
						text : $i18n.owner_article_no,//货主商品编码
						dataIndex : 'ownerArticleNo'
					},{
						width:120,
						text :$i18n.barcode,//商品条码
						dataIndex : 'barcode'
					},{
						width:180,
						text :$i18n.article_name,//商品名称
						dataIndex : 'articleName'
					},{
						width:100,
						text :$i18n.articleqty,//计划数量
						dataIndex : 'locateQty'
					},{
						width : 60,
						text : $i18n.packing_unit,
					    id:'packingUnit3401_1',
						dataIndex:'packingUnit'
					},
					//add by huangcx at 20160528
					{
	    			    width:80,
	    			    text : $i18n.packingSpec,
					    id:'packingSpec3401_1',
	    			    dataIndex:'packingSpec'			
	    		    },
	    		    //end add
	    		    {
						width:80,
						text :$i18n.new_packing_qty,//包装数量
						dataIndex : 'packingQty'
					},{
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
					}]
			},{
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
							fieldLabel : $i18n.dispatch_worker,//  员工代码
							id : 'cmbWorkerNo3401',
							store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore'),
							beforeLabelTextTpl : required
					},
					{
						xtype : 'button',
						margin:'0 0 0 10',
						id:'butSend3401',
						//disabled:true,
						text : $i18n.send//发单
					}]
			}]
				 },{

						title:$i18n.manDeliverObjSend,
		                id:'tabObjSend3401',
		                layout:'border',
		                items:[{
							xtype : 'form',
							id:'formObjCondition3401',
							layout:{
								type : 'table',
								columns : 2
							},
							region : 'west',
							frame : true,
							width : 480,
							defaults:{
								labelAlign : 'right',
								xtype:'textfield' ,
								labelWidth : 60,
								margin:'10 0 0 10',
							},
						
						       items:[
								{
									xtype:'bdef_DefOwnerCombo',
									fieldLabel:$i18n.owner,//货主编号
									queryMode:'local',
								    allowBlank:true,
								    id:'cmbOwnerNoObj3401',
								    displayField: 'dropValue',
									valueField: 'value',
									store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',
									 {
										proxy:{
											type:'ajax',
											method:'post',
											async:false,
											url:'odata_OutstockMAction_getOdataGetCombo',
											reader:{
												root:'rootList',
												totalProperty:'totalCount'
											},
											extraParams:{
												strFlag : "1",
												strSendFlag : "man",
												industryFlag : "1"
											}
										},	 
									    listeners:{  
											'load':function(th,records,successful,eOpts ){
												if(th.count()>0){
													Ext.getCmp('cmbOwnerNoObj3401').setValue(th.getAt(0).data.value);
													_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendController').cmbOwnerNoObj3401Select();
												}else{
													Ext.getCmp('cmbExp_typeObj3401').getStore().removeAll();
													Ext.getCmp('cmbExp_typeObj3401').setValue(null);
													
													Ext.getCmp('cmbLocate_no3401_Obj').getStore().removeAll();
													Ext.getCmp('cmbLocate_no3401_Obj').setValue(null);
													Ext.getCmp('cmbm_batch_no3401_Obj').setValue(null);
										      	    Ext.getCmp('cmbm_batch_no3401_Obj').getStore().removeAll();
										      	 
													Ext.getCmp('cmbOutStockType3401_Obj').getStore().removeAll();
													Ext.getCmp('cmbOutStockType3401_Obj').setValue(null);
													
													Ext.getCmp('gridOutstockMObjSend3401').getStore().removeAll();
													Ext.getCmp('gridOutstockDObjSend3401').getStore().removeAll();
												}
											}
										}
									}),
							    beforeLabelTextTpl:required
							},
							{
							xtype:'wms_DefFieldValCombo',
							fieldLabel : $i18n.exp_type,// 出货单别
							id : 'cmbExp_typeObj3401',
							displayField: 'dropValue',
							valueField: 'value',
							queryMode: 'local',
							store:Ext.create("cms.store.common.comboStore",
							{
								proxy:{
									type:'ajax',
									method:'post',
									async:false,
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
										Ext.getCmp('cmbExp_typeObj3401').setValue(th.getAt(0).data.value);
										_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendController').cmbExp_typeObj3401Select();
									}else{
										Ext.getCmp('cmbLocate_no3401_Obj').setValue(null);
							      	    Ext.getCmp('cmbLocate_no3401_Obj').getStore().removeAll();
							      		Ext.getCmp('cmbm_batch_no3401_Obj').setValue(null);
							      	    Ext.getCmp('cmbm_batch_no3401_Obj').getStore().removeAll();
							      	 
									    Ext.getCmp('cmbOutStockType3401_Obj').getStore().removeAll();
									    Ext.getCmp('cmbOutStockType3401_Obj').setValue(null);
										
									    Ext.getCmp('gridOutstockMObjSend3401').getStore().removeAll();
										Ext.getCmp('gridOutstockDObjSend3401').getStore().removeAll();
									}
								}
								}
			   				}),
							beforeLabelTextTpl : required
						},
					
						{
							xtype:'wms_DefFieldValCombo',
							fieldLabel : $i18n.locate_no,// 波次号
							id:'cmbLocate_no3401_Obj',
							store:Ext.create("cms.store.common.comboStore",
							{
								proxy:{
									type:'ajax',
									method:'post',
									async:false,
									url:'odata_OutstockMAction_getOdataGetCombo.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									},
									extraParams:{
										strFlag : "3",
										industryFlag : "1"
									}
								},
								listeners:{  
								'load':function(th,records,successful,eOpts ){
									if(th.count()>0){
										Ext.getCmp('cmbLocate_no3401_Obj').setValue(th.getAt(0).data.value);
										_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendController').cmbLocate_noObj3401Select();
									}else{
										Ext.getCmp('cmbm_batch_no3401_Obj').setValue(null);
							      	    Ext.getCmp('cmbm_batch_no3401_Obj').getStore().removeAll();
							      	   
										Ext.getCmp('cmbOutStockType3401_Obj').setValue(null);
							      	    Ext.getCmp('cmbOutStockType3401_Obj').getStore().removeAll();
							   
									    Ext.getCmp('gridOutstockMObjSend3401').getStore().removeAll();
										Ext.getCmp('gridOutstockMObjSend3401').getStore().removeAll();
									}
								}
								}
			   				}),
							beforeLabelTextTpl : required
						},
					    {

							xtype:'wms_DefFieldValCombo',
							fieldLabel : $i18n.m_batch_no,//批次
							id:'cmbm_batch_no3401_Obj',
							store:Ext.create("cms.store.common.comboStore",
							{
								proxy:{
									type:'ajax',
									method:'post',
									async:false,
									url:'odata_OutstockMAction_getOdataGetCombo.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									},
									extraParams:{
										strFlag : "9"
									}
								},
								listeners:{  
									'load':function(th,records,successful,eOpts ){
										if(th.count()>0){
											Ext.getCmp('cmbm_batch_no3401_Obj').setValue(th.getAt(0).data.value);
											_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendController').cmbm_batch_no3401_ObjSelect();
										}else{
											Ext.getCmp('cmbOutStockType3401_Obj').setValue(null);
								      	    Ext.getCmp('cmbOutStockType3401_Obj').getStore().removeAll();
								      	   
								      	    Ext.getCmp('gridOutstockMObjSend3401').getStore().removeAll();
											Ext.getCmp('gridOutstockMObjSend3401').getStore().removeAll();
										}
									}
								}
			   				}),
						    beforeLabelTextTpl:required

						
					    },
						{
							xtype:'wms_DefFieldValCombo',
							fieldLabel : '下架类型',//下架类型
							id:'cmbOutStockType3401_Obj',
							store:Ext.create("cms.store.common.comboStore",
							{
								proxy:{
									type:'ajax',
									method:'post',
									async:false,
									url:'odata_OutstockMAction_getOdataGetCombo.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									},
									extraParams:{
										strFlag : "4",
										industryFlag : "1"
									}
								},
								listeners:{  
									'load':function(th,records,successful,eOpts ){
										if(th.count()>0){
											Ext.getCmp('cmbOutStockType3401_Obj').setValue(th.getAt(0).data.value);
											_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendController').cmbOutStockTypeObj3401Select();
										}else{
										   
										    Ext.getCmp('gridOutstockMObjSend3401').getStore().removeAll();
											Ext.getCmp('gridOutstockDObjSend3401').getStore().removeAll();
										}
									}
								}
			   				}),
						    beforeLabelTextTpl:required

						},
						{
							xtype : 'checkboxfield',
							id : 'checkBoxPrint_type3401_Obj',
							boxLabel : $i18n.print_report,//打印报表
							margin : '0 0 2 65',
							colspan: 2
						}
						]},
					   {
							xtype:'grid',
							//width : '50%',
							//height:250,
							region : 'center',
							store:gridOutstockMObjSend3401Store,
					        id: 'gridOutstockMObjSend3401',
							multiSelect: true,  
							viewConfig : {   
				                 forceFit : true,   
				                 getRowClass : function(record,rowIndex,rowParams,store){   
				                    //禁用数据显示红色   
				                    if(record.data.flag=='1'){   
				                        return 'x-grid-record-red';   
				                     }else{   
				                        return '';   
				                     }   
				                 }   
						    },  					
							selModel: {  
							    selType:'checkboxmodel',
							    checkOnly:true
							}, 
					        columns:[{			
								xtype : 'rownumberer',
								width : 30
							},{
								width : 100,
								text : '配送对象',
								dataIndex : 'deliverObj'
							},{
								width:300,
								text :'对象名称【当配送对象为客户时，该显示为客户名称】',
								dataIndex : 'custName'
							}]
						},
						{
							xtype:'grid',
							region : 'south',
							height : 300,
							store:gridOutstockDManSend3401Store,
					        id: 'gridOutstockDObjSend3401',
							columns:[{			
								xtype : 'rownumberer',
								width : 30
							},
							{
								width : 120,
								text : $i18n.s_cell_no,//来源储位
								dataIndex : 'SCellNo'
							},{
								width:120,
								text :$i18n.article_no,//商品编码
								dataIndex : 'articleNo'
							},{
								width : 100,
								text : $i18n.owner_article_no,//货主商品编码
								dataIndex : 'ownerArticleNo'
							},{
								width:120,
								text :$i18n.barcode,//商品条码
								dataIndex : 'barcode'
							},{
								width:180,
								text :$i18n.article_name,//商品名称
								dataIndex : 'articleName'
							},{
								width:100,
								text :$i18n.exp_qty,//计划数量
								dataIndex : 'locateQty'
							},{
								width : 60,
								text : $i18n.packing_unit,
							    id:'packingUnit3201_2',
								dataIndex:'packingUnit'
							},
							//add by huangcx at 20160528
							{
			    			    width:80,
			    			    text : $i18n.packingSpec,
							    id:'packingSpec3401_2',
			    			    dataIndex:'packingSpec'			
			    		    },
			    		    //end add
			    		    {
								width:80,
								text :$i18n.new_packing_qty,//包装数量
								dataIndex : 'packingQty'
							},{
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
							}]
					},{
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
									fieldLabel : $i18n.dispatch_worker,//  员工代码
									id : 'cmbWorkerNo3401_Obj',
									store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore'),
									beforeLabelTextTpl : required
							},
							{
								xtype : 'button',
								margin:'0 0 0 10',
								id:'butSend3401_Obj',
								//disabled:true,
								text : $i18n.send//发单
							}]
					}]
						  
				 },
				 {
	        	    	title:$i18n.noSendDirect,//未发单指示
	        	        layout:'border',
	        	        id:'tabManCheck3401',
	        	        items:[
	        	        {
					    	xtype : 'grid',
							region : 'north',
							width:'100%',
							id:'gridOutstockMLocateNoCheck3401',
							store:gridOutstockMWaveNoSend3401Store,
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
								text : $i18n.outstocktype,//单据类型
								dataIndex:'outstockTypeText'
							}
						    ]
	        	        },
	        	        {

					    	xtype : 'grid',
							region : 'center',
							id:'gridOutstockDCheck3401',
							//store:gridOutstockDManSend3401Store,
							store:gridOutstockDManSend3401NotSendStore,
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
							    id:'packingUnit3401_3',
								dataIndex:'packingUnit'
							},
							//add by huangcx at 20160528
							{
			    			    width:80,
			    			    text : $i18n.packingSpec,
							    id:'packingSpec3401_3',
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
	       /*
	       
		   */
	       ]
});