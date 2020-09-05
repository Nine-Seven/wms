/**
 * 模块名称：电商按客户发单
 * 模块编码：3303
 * 创建：huangb 20160706
 */
 
var gridOutstockMManSend3303Store=Ext.create('cms.store.odata.odata_OutstockDirectStore',{
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
			industryFlag : "2"
		}
	}/*,
	listeners:{
		'load':function(th,records,successful,eOpts){
			Ext.getCmp('gridOutstockMManSend3303').getSelectionModel().selectAll();
		}
	}*/
});
var gridOutstockDManSend3303Store=Ext.create('cms.store.odata.odata_OutstockDirectStore',{
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
			industryFlag : "2"
		}
	}
});
//获取未发单下架指示 huangb 20160805
var gridOutstockDManSend3303NotSendStore=Ext.create('cms.store.odata.odata_OutstockDirectStore',{
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
			industryFlag : "2"
		}
	}
});
var gridOutstockMObjSend3303Store=Ext.create('cms.store.odata.odata_OutstockDirectStore',{
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
			industryFlag : "2"
		}
	},
	listeners:{
		'load':function(th,records,successful,eOpts){
			Ext.getCmp('gridOutstockMObjSend3303').getSelectionModel().selectAll();
		}
	}
});
//获得未发单界面波次号
var gridOutstockMWaveNoSend3303Store=Ext.create('cms.store.odata.odata_OutstockDirectStore',{
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
			industryFlag : "2"
		}
	},
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(th.count()>0 && Ext.getCmp('tabpanel3303').getActiveTab().id=='tabManCheck3303')
			{
				Ext.getCmp('gridOutstockMLocateNoCheck3303').getSelectionModel().select(0);
			}
		}
	}
});

Ext.define('cms.view.odata.odata_OutstockMManSendECUI',{
	alias:'widget.odata_OutstockMManSendECUI',
	title:$i18n.manSendEc,//电商按客户发单
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
				id:'menu3303',
				region:'north'
			},
	       {
	    	    xtype : 'tabpanel',
				id : 'tabpanel3303',
				region : 'center',
				items:[
				{
				title:$i18n.manSendEc,
                id:'tabManSend3303',
                layout:'border',
                items:[{
					xtype : 'form',
					id:'formSendCondition3303',
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
						    id:'cmbOwnerNoSend3303',
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
										industryFlag : "2"
									}
								},	 
							    listeners:{  
									'load':function(th,records,successful,eOpts ){
										if(th.count()>0){
											Ext.getCmp('cmbOwnerNoSend3303').setValue(th.getAt(0).data.value);
											_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendECController').cmbOwnerNoSend3303Select();
										}else{
											Ext.getCmp('cmbExp_typeSend3303').getStore().removeAll();
											Ext.getCmp('cmbExp_typeSend3303').setValue(null);
											
											Ext.getCmp('cmbLocate_no3303').getStore().removeAll();
											Ext.getCmp('cmbLocate_no3303').setValue(null);
											Ext.getCmp('cmbm_batch_no3303').setValue(null);
								      	    Ext.getCmp('cmbm_batch_no3303').getStore().removeAll();
								      	  
											Ext.getCmp('cmbOutStockType3303').getStore().removeAll();
											Ext.getCmp('cmbOutStockType3303').setValue(null);
											
											Ext.getCmp('cmbArea_no3303').getStore().removeAll();
											Ext.getCmp('cmbArea_no3303').setValue(null);
											
											Ext.getCmp('cmbOperate_type3303').getStore().removeAll();
											Ext.getCmp('cmbOperate_type3303').setValue(null);
											
											Ext.getCmp('gridOutstockMManSend3303').getStore().removeAll();
											Ext.getCmp('gridOutstockDManSend3303').getStore().removeAll();
										}
									}
								}
							}),
					    beforeLabelTextTpl:required
					},
					{
					xtype:'wms_DefFieldValCombo',
					fieldLabel : $i18n.exp_type,// 出货单别
					id : 'cmbExp_typeSend3303',
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
								industryFlag : "2"
							}
						},
						listeners:{  
						'load':function(th,records,successful,eOpts ){
							if(th.count()>0){
								Ext.getCmp('cmbExp_typeSend3303').setValue(th.getAt(0).data.value);
								_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendECController').cmbExp_typeSend3303Select();
							}else{
								Ext.getCmp('cmbLocate_no3303').setValue(null);
					      	    Ext.getCmp('cmbLocate_no3303').getStore().removeAll();
					      		Ext.getCmp('cmbm_batch_no3303').setValue(null);
					      	    Ext.getCmp('cmbm_batch_no3303').getStore().removeAll();
					      	  
							    Ext.getCmp('cmbOutStockType3303').getStore().removeAll();
							    Ext.getCmp('cmbOutStockType3303').setValue(null);
								
							    Ext.getCmp('cmbArea_no3303').getStore().removeAll();
							    Ext.getCmp('cmbArea_no3303').setValue(null);
								
							    Ext.getCmp('cmbOperate_type3303').getStore().removeAll();
							    Ext.getCmp('cmbOperate_type3303').setValue(null);
							    
							    Ext.getCmp('gridOutstockMManSend3303').getStore().removeAll();
								Ext.getCmp('gridOutstockDManSend3303').getStore().removeAll();
							}
						}
						}
	   				}),
					beforeLabelTextTpl : required
				},
			/*	{
					xtype:'wms_DefFieldValCombo',
					fieldLabel : $i18n.source_type,// 波次类型
					id:'cmbSource_type3303',
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
								Ext.getCmp('cmbSource_type3303').setValue(th.getAt(0).data.value);
								_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendECController').cmbSource_type3303change();
							}
						}
						}
	   				}),
					beforeLabelTextTpl : required
				},*/
				{
					xtype:'wms_DefFieldValCombo',
					fieldLabel : $i18n.locate_no,// 波次号
					id:'cmbLocate_no3303',
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
								industryFlag : "2"
							}
						},
						listeners:{  
						'load':function(th,records,successful,eOpts ){
							if(th.count()>0){
								Ext.getCmp('cmbLocate_no3303').setValue(th.getAt(0).data.value);
								_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendECController').cmbLocate_no3303Select();
							}else{
								Ext.getCmp('cmbm_batch_no3303').setValue(null);
					      	    Ext.getCmp('cmbm_batch_no3303').getStore().removeAll();
					      	    
								Ext.getCmp('cmbOutStockType3303').setValue(null);
					      	    Ext.getCmp('cmbOutStockType3303').getStore().removeAll();
					      	   
					      	    Ext.getCmp('cmbArea_no3303').getStore().removeAll();
							    Ext.getCmp('cmbArea_no3303').setValue(null);
								
							    Ext.getCmp('cmbOperate_type3303').getStore().removeAll();
							    Ext.getCmp('cmbOperate_type3303').setValue(null);
							    
							    Ext.getCmp('gridOutstockMManSend3303').getStore().removeAll();
								Ext.getCmp('gridOutstockDManSend3303').getStore().removeAll();
							}
						}
						}
	   				}),
					beforeLabelTextTpl : required
				},
                {
					xtype:'wms_DefFieldValCombo',
					fieldLabel : $i18n.m_batch_no,//批次
					id:'cmbm_batch_no3303',
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
									Ext.getCmp('cmbm_batch_no3303').setValue(th.getAt(0).data.value);
									_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendECController').cmbm_batch_no3303Select();
								}else{
									Ext.getCmp('cmbOutStockType3303').setValue(null);
						      	    Ext.getCmp('cmbOutStockType3303').getStore().removeAll();
						      	   
						      	    Ext.getCmp('cmbArea_no3303').getStore().removeAll();
								    Ext.getCmp('cmbArea_no3303').setValue(null);
									
								    Ext.getCmp('cmbOperate_type3303').getStore().removeAll();
								    Ext.getCmp('cmbOperate_type3303').setValue(null);
								    
								    Ext.getCmp('gridOutstockMManSend3303').getStore().removeAll();
									Ext.getCmp('gridOutstockDManSend3303').getStore().removeAll();
								}
							}
						}
	   				}),
				    beforeLabelTextTpl:required

				},
				{
					xtype:'wms_DefFieldValCombo',
					fieldLabel : '下架类型',//下架类型
					id:'cmbOutStockType3303',
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
								industryFlag : "2"
							}
						},
						listeners:{  
							'load':function(th,records,successful,eOpts ){
								if(th.count()>0){
									Ext.getCmp('cmbOutStockType3303').setValue(th.getAt(0).data.value);
									_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendECController').cmbOutStockType3303Select();
								}else{
									Ext.getCmp('cmbArea_no3303').getStore().removeAll();
								    Ext.getCmp('cmbArea_no3303').setValue(null);
									
								    Ext.getCmp('cmbOperate_type3303').getStore().removeAll();
								    Ext.getCmp('cmbOperate_type3303').setValue(null);
								    
								    Ext.getCmp('gridOutstockMManSend3303').getStore().removeAll();
									Ext.getCmp('gridOutstockDManSend3303').getStore().removeAll();
								}
							}
						}
	   				}),
				    beforeLabelTextTpl:required

				},
				{
					xtype:'wms_DefFieldValCombo',
					fieldLabel : $i18n.area_no,// 储区
					id:'cmbArea_no3303',
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
								industryFlag : "2"
							}
						},
						listeners:{  
						'load':function(th,records,successful,eOpts ){
							if(th.count()>1){
								Ext.getCmp('cmbArea_no3303').setValue(th.getAt(0).data.value);
								_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendECController').cmbArea_no3303Select();
							}else{
								Ext.getCmp('cmbOperate_type3303').getStore().removeAll();
		   						Ext.getCmp('cmbOperate_type3303').setValue(null);
		   						
		   						Ext.getCmp('gridOutstockMManSend3303').getStore().removeAll();
								Ext.getCmp('gridOutstockDManSend3303').getStore().removeAll();
							}
						}
						}
	   				}),
					beforeLabelTextTpl : required
				},{
					xtype:'wms_DefFieldValCombo',
					fieldLabel : $i18n.operate_type,// 作业类型
					id:'cmbOperate_type3303',
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
								industryFlag : "2"
							}
						},
						listeners:{  
						'load':function(th,records,successful,eOpts ){
							if(th.count()>1){
								Ext.getCmp('cmbOperate_type3303').setValue(th.getAt(0).data.value);
								_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendECController').cmbOperate_type3303Select();
							}else{
								Ext.getCmp('gridOutstockMManSend3303').getStore().removeAll();
								Ext.getCmp('gridOutstockDManSend3303').getStore().removeAll();
							}
						}
						}
	   				}),
					beforeLabelTextTpl : required
				},
				{
					xtype : 'checkboxfield',
					id : 'checkBoxPrint_type3303',
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
					store:gridOutstockMManSend3303Store,
			        id: 'gridOutstockMManSend3303',
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
					store:gridOutstockDManSend3303Store,
			        id: 'gridOutstockDManSend3303',
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
					    id:'packingUnit3303_1',
						dataIndex:'packingUnit'
					},
					//add by huangcx at 20160528
					{
	    			    width:80,
	    			    text : $i18n.packingSpec,
					    id:'packingSpec3303_1',
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
							id : 'cmbWorkerNo3303',
							store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore'),
							beforeLabelTextTpl : required
					},
					{
						xtype : 'button',
						margin:'0 0 0 10',
						id:'butSend3303',
						//disabled:true,
						text : $i18n.send//发单
					}]
			}]
				 },{

						title:$i18n.manDeliverObjSendEc,
		                id:'tabObjSend3303',
		                layout:'border',
		                items:[{
							xtype : 'form',
							id:'formObjCondition3303',
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
								    id:'cmbOwnerNoObj3303',
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
												industryFlag : "2"
											}
										},	 
									    listeners:{  
											'load':function(th,records,successful,eOpts ){
												if(th.count()>0){
													Ext.getCmp('cmbOwnerNoObj3303').setValue(th.getAt(0).data.value);
													_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendECController').cmbOwnerNoObj3303Select();
												}else{
													Ext.getCmp('cmbExp_typeObj3303').getStore().removeAll();
													Ext.getCmp('cmbExp_typeObj3303').setValue(null);
													
													Ext.getCmp('cmbLocate_no3303_Obj').getStore().removeAll();
													Ext.getCmp('cmbLocate_no3303_Obj').setValue(null);
													Ext.getCmp('cmbm_batch_no3303_Obj').setValue(null);
										      	    Ext.getCmp('cmbm_batch_no3303_Obj').getStore().removeAll();
										   
													Ext.getCmp('cmbOutStockType3303_Obj').getStore().removeAll();
													Ext.getCmp('cmbOutStockType3303_Obj').setValue(null);
													
													Ext.getCmp('gridOutstockMObjSend3303').getStore().removeAll();
													Ext.getCmp('gridOutstockDObjSend3303').getStore().removeAll();
												}
											}
										}
									}),
							    beforeLabelTextTpl:required
							},
							{
							xtype:'wms_DefFieldValCombo',
							fieldLabel : $i18n.exp_type,// 出货单别
							id : 'cmbExp_typeObj3303',
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
										industryFlag : "2"
									}
								},
								listeners:{  
								'load':function(th,records,successful,eOpts ){
									if(th.count()>0){
										Ext.getCmp('cmbExp_typeObj3303').setValue(th.getAt(0).data.value);
										_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendECController').cmbExp_typeObj3303Select();
									}else{
										Ext.getCmp('cmbLocate_no3303_Obj').setValue(null);
							      	    Ext.getCmp('cmbLocate_no3303_Obj').getStore().removeAll();
							      		Ext.getCmp('cmbm_batch_no3303_Obj').setValue(null);
							      	    Ext.getCmp('cmbm_batch_no3303_Obj').getStore().removeAll();
							   
									    Ext.getCmp('cmbOutStockType3303_Obj').getStore().removeAll();
									    Ext.getCmp('cmbOutStockType3303_Obj').setValue(null);
										
									    Ext.getCmp('gridOutstockMObjSend3303').getStore().removeAll();
										Ext.getCmp('gridOutstockDObjSend3303').getStore().removeAll();
									}
								}
								}
			   				}),
							beforeLabelTextTpl : required
						},
					
						{
							xtype:'wms_DefFieldValCombo',
							fieldLabel : $i18n.locate_no,// 波次号
							id:'cmbLocate_no3303_Obj',
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
										industryFlag : "2"
									}
								},
								listeners:{  
								'load':function(th,records,successful,eOpts ){
									if(th.count()>0){
										Ext.getCmp('cmbLocate_no3303_Obj').setValue(th.getAt(0).data.value);
										_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendECController').cmbLocate_noObj3303Select();
									}else{
										Ext.getCmp('cmbm_batch_no3303_Obj').setValue(null);
							      	    Ext.getCmp('cmbm_batch_no3303_Obj').getStore().removeAll();
							   
										Ext.getCmp('cmbOutStockType3303_Obj').setValue(null);
							      	    Ext.getCmp('cmbOutStockType3303_Obj').getStore().removeAll();
							   
									    Ext.getCmp('gridOutstockMObjSend3303').getStore().removeAll();
										Ext.getCmp('gridOutstockMObjSend3303').getStore().removeAll();
									}
								}
								}
			   				}),
							beforeLabelTextTpl : required
						},
					    {


							xtype:'wms_DefFieldValCombo',
							fieldLabel : $i18n.m_batch_no,//批次
							id:'cmbm_batch_no3303_Obj',
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
											Ext.getCmp('cmbm_batch_no3303_Obj').setValue(th.getAt(0).data.value);
											_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendECController').cmbm_batch_no3303_ObjSelect();
										}else{
											Ext.getCmp('cmbOutStockType3303_Obj').setValue(null);
								      	    Ext.getCmp('cmbOutStockType3303_Obj').getStore().removeAll();
								   
										    Ext.getCmp('gridOutstockMObjSend3303').getStore().removeAll();
											Ext.getCmp('gridOutstockMObjSend3303').getStore().removeAll();
										}
									}
								}
			   				}),
						    beforeLabelTextTpl:required

					    
					    },
						{
							xtype:'wms_DefFieldValCombo',
							fieldLabel : '下架类型',//下架类型
							id:'cmbOutStockType3303_Obj',
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
										industryFlag : "2"
									}
								},
								listeners:{  
									'load':function(th,records,successful,eOpts ){
										if(th.count()>0){
											Ext.getCmp('cmbOutStockType3303_Obj').setValue(th.getAt(0).data.value);
											_myAppGlobal.getController('cms.controller.odata.odata_OutstockMManSendECController').cmbOutStockTypeObj3303Select();
										}else{
										   
										    Ext.getCmp('gridOutstockMObjSend3303').getStore().removeAll();
											Ext.getCmp('gridOutstockDObjSend3303').getStore().removeAll();
										}
									}
								}
			   				}),
						    beforeLabelTextTpl:required

						},
						{
							xtype : 'checkboxfield',
							id : 'checkBoxPrint_type3303_Obj',
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
							store:gridOutstockMObjSend3303Store,
					        id: 'gridOutstockMObjSend3303',
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
							store:gridOutstockDManSend3303Store,
					        id: 'gridOutstockDObjSend3303',
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
							    id:'packingSpec3303_2',
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
									id : 'cmbWorkerNo3303_Obj',
									store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore'),
									beforeLabelTextTpl : required
							},
							{
								xtype : 'button',
								margin:'0 0 0 10',
								id:'butSend3303_Obj',
								//disabled:true,
								text : $i18n.send//发单
							}]
					}]
						  
				 },
				 {
	        	    	title:$i18n.noSendDirect,//未发单指示
	        	        layout:'border',
	        	        id:'tabManCheck3303',
	        	        items:[
	        	        {
					    	xtype : 'grid',
							region : 'north',
							width:'100%',
							id:'gridOutstockMLocateNoCheck3303',
							store:gridOutstockMWaveNoSend3303Store,
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
							id:'gridOutstockDCheck3303',
							//store:gridOutstockDManSend3303Store,
							store:gridOutstockDManSend3303NotSendStore,
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
							    id:'packingUnit3303_3',
								dataIndex:'packingUnit'
							},
							//add by huangcx at 20160528
							{
			    			    width:80,
			    			    text : $i18n.packingSpec,
							    id:'packingSpec3303_3',
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