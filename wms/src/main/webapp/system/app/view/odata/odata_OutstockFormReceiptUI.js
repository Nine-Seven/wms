/**
 * 模块名称：拣货表单回单
 * 模块编码：3601
 * 创建：周欢
 */

var gridOutstockM3601store = Ext.create('cms.store.odata.odata_OutstockMStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_OutstockMReceiptAction_getOdata_OutstockM.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		},
		extraParams:{
			strFlag : "1",
			strSendFlag : "form"
		}
	},listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(th.count()>0){
				Ext.getCmp('gridOutstockM3601').getSelectionModel().select(0);
			}
		}
	}
});
var gridOutstockMCheck3601store = Ext.create('cms.store.odata.odata_OutstockMStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_OutstockMReceiptAction_getOdata_OutstockM.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		},
		extraParams:{
			strFlag : "2",
			strSendFlag : "form"
		}
	},listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(th.count()>0){
				Ext.getCmp('gridOutstockMCheck3601').getSelectionModel().select(0);
			}
		}
	}
});
var gridOutstockD3601store = Ext.create('cms.store.odata.odata_OutstockDStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_OutstockMReceiptAction_getOdata_OutstockD.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		},
		extraParams:{
			strFlag : "1",
			strSendFlag : "form"
		}
	}
});
var gridOutstockDCheck3601store = Ext.create('cms.store.odata.odata_OutstockDStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_OutstockMReceiptAction_getOdata_OutstockD.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		},
		extraParams:{
			strFlag : "2",
			strSendFlag : "form"
		}
	}
});
Ext.define('cms.view.odata.odata_OutstockFormReceiptUI',{
	alias:'widget.odata_OutstockFormReceiptUI',
	title:$i18n.outstockFormReceipt,//拣货任务标签回单
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:['cms.view.common.commMenu4','cms.view.common.wms_DefFieldValCombo',
		      'cms.view.common.bdef_DefWorkerCombo',
		      'cms.view.common.cdef_DefCellCombo',
		      'cms.view.common.bdef_DefOwnerCombo',
		  	  'cms.view.common.remoteCombo'
	],
	items:[
	{
	   xtype : 'commMenuWidget4',
	   id:'menu3601',
	   region:'north'
	},
    {
		xtype : 'tabpanel',
	    id:'tabpanel3601',
	    region:'center',
	    items:[
	    {
	    	id:'outstockFormReceiptTab3601',
	    	layout:'border',
	    	title:$i18n.formReceipt,
	    	items:[{
	    				xtype : 'form',
	    				id:'formSelectCondition3601',
	    				layout : {
							 type : 'table',
							 columns :5
						},
						//layout:column,
	    				region : 'north',
	    				frame : true,
	    				defaults:{
	    			       labelAlign:'right',
	    			       labelWidth : 60
	    		        },
	    				items:[
	    				   {
	    					xtype:'bdef_DefOwnerCombo',
	    					fieldLabel:$i18n.owner,//货主编号
	    					queryMode:'local',
	    				    allowBlank:true,
	    				    id:'cmbOwnerNo3601',
	    				    displayField: 'dropValue',
	    					valueField: 'value',
	    					store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',
	    					 {
	    						 proxy:{
	    								type:'ajax',
	    								method:'post',
	    								url:'odata_OutstockMReceiptAction_getOdata_OutstockMReceiptCombo',
	    								reader:{
	    									root:'rootList',
	    									totalProperty:'totalCount'
	    								},
	    								extraParams:{
	    									strFlag : "4",
	    									strSendFlag : "form",
	    									strCheckFlag : "normal"
	    								}
	    							},	 
	    						listeners:{  
	    								'load':function(th,records,successful,eOpts ){
	    									if(th.count()>0){
	    										Ext.getCmp('cmbOwnerNo3601').setValue(th.getAt(0).data.value);
	    										_myAppGlobal.getController('cms.controller.odata.odata_OutstockFormReceiptController')
	    										.cmbOwnerNo3601Select();
	    									}else
	    									{
	    										Ext.getCmp('cmbWave_no3601').getStore().removeAll();
	    										Ext.getCmp('cmbWave_no3601').setValue(null);
	    										Ext.getCmp('cmbBatch_no3601').getStore().removeAll();
	    										Ext.getCmp('cmbBatch_no3601').setValue(null);
	    										Ext.getCmp('cmbOperate_type3601').getStore().removeAll();
	    										Ext.getCmp('cmbOperate_type3601').setValue(null);
	    										Ext.getCmp('cmbOutstockNo3601').getStore().removeAll();
	    										Ext.getCmp('cmbOutstockNo3601').setValue(null);
	    										Ext.getCmp('gridOutstockM3601').getStore().removeAll();
	    										Ext.getCmp('gridOutstockD3601').getStore().removeAll();
	    									}
	    								}
	    							}
	    						}),
	    				    beforeLabelTextTpl:required
	    				},
	    				{
	    					xtype:'combo',
	    					fieldLabel : $i18n.locate_no,// 波次号
	    					id:'cmbWave_no3601',
	    					displayField: 'dropValue',
	    					valueField: 'value',
	    				    queryMode: 'local',				  
	    					store:Ext.create("cms.store.common.comboStore",
	    					{
	    						proxy:{
	    							type:'ajax',
	    							method:'post',
	    							url:'odata_OutstockMReceiptAction_getOdata_OutstockMReceiptCombo',
	    							reader:{
	    								root:'rootList',
	    								totalProperty:'totalCount'
	    							},
	    							extraParams:{
	    								strFlag : "1",
	    								strSendFlag : 'form',
	    								strCheckFlag : "normal"
	    							}
	    						},
	    						listeners:{  
	    							'load':function(th,records,successful,eOpts ){
	    								if(th.count()>0){
	    									Ext.getCmp('cmbWave_no3601').setValue(th.getAt(0).data.value);
	    									_myAppGlobal.getController('cms.controller.odata.odata_OutstockFormReceiptController')
	    									.cmbWave_no3601change();
	    								}else
	    								{
	    									Ext.getCmp('cmbBatch_no3601').getStore().removeAll();
	    									Ext.getCmp('cmbBatch_no3601').setValue(null);
	    									Ext.getCmp('cmbOperate_type3601').getStore().removeAll();
	    									Ext.getCmp('cmbOperate_type3601').setValue(null);
	    									Ext.getCmp('cmbOutstockNo3601').getStore().removeAll();
	    									Ext.getCmp('cmbOutstockNo3601').setValue(null);
	    									Ext.getCmp('gridOutstockM3601').getStore().removeAll();
	    									Ext.getCmp('gridOutstockD3601').getStore().removeAll();
	    								}
	    							}
	    						}
	    	   				}),
	    					beforeLabelTextTpl : required
	    				},
	    				{
	    					xtype:'wms_DefFieldValCombo',
	    					fieldLabel : $i18n.m_batch_no,//批次
	    					id:'cmbBatch_no3601',
	    					store:Ext.create("cms.store.common.comboStore",
							{
								proxy:{
									type:'ajax',
									method:'post',
									url:'odata_OutstockMReceiptAction_getOdata_OutstockMReceiptCombo.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									},
									extraParams:{
										strFlag : "2",
										strSendFlag : 'form',
										strCheckFlag : "normal"
									}
								},
							listeners:{  
								'load':function(th,records,successful,eOpts ){
									if(th.count()>0){
										Ext.getCmp('cmbBatch_no3601').setValue(th.getAt(0).data.value);
										_myAppGlobal.getController('cms.controller.odata.odata_OutstockFormReceiptController')
										.cmbBatch_no3601change();
									}else
									{
										Ext.getCmp('cmbOperate_type3601').getStore().removeAll();
										Ext.getCmp('cmbOperate_type3601').setValue(null);
										Ext.getCmp('cmbOutstockNo3601').getStore().removeAll();
										Ext.getCmp('cmbOutstockNo3601').setValue(null);
										Ext.getCmp('gridOutstockM3601').getStore().removeAll();
										Ext.getCmp('gridOutstockD3601').getStore().removeAll();
									}
								}
								}
			   				}),
	    					beforeLabelTextTpl : required
	    				},
	    				{
	    					xtype:'wms_DefFieldValCombo',
	    					fieldLabel : $i18n.operate_type,// 作业类型
	    					id:'cmbOperate_type3601',
	    					store:Ext.create("cms.store.common.comboStore",
	    							{
	    								proxy:{
	    									type:'ajax',
	    									method:'post',
	    									url:'odata_OutstockMReceiptAction_getOdata_OutstockMReceiptCombo.action',
	    									reader:{
	    										root:'rootList',
	    										totalProperty:'totalCount'
	    									},
	    									extraParams:{
	    										strFlag : "3",
	    										strSendFlag : 'form',
	    										strCheckFlag : "normal"
	    									}
	    								},
	    								listeners:{  
	    								'load':function(th,records,successful,eOpts ){
	    									if(th.count()>0){
	    										Ext.getCmp('cmbOperate_type3601').setValue(th.getAt(0).data.value);
	    										_myAppGlobal.getController('cms.controller.odata.odata_OutstockFormReceiptController')
	    										.cmbOperate_type3601change();
	    									}else
	    									{
	    										Ext.getCmp('cmbOutstockNo3601').getStore().removeAll();
	    										Ext.getCmp('cmbOutstockNo3601').setValue(null);
	    										Ext.getCmp('gridOutstockM3601').getStore().removeAll();
	    										Ext.getCmp('gridOutstockD3601').getStore().removeAll();
	    									}
	    								}
	    								}
	    			   				}),
	    					beforeLabelTextTpl : required
	    				},
	    				{
	    					xtype:'remoteCombo',
	    					fieldLabel : $i18n.outstock_no,//下架单号
	    					id:'cmbOutstockNo3601',
	    					store:Ext.create("cms.store.common.comboStore",
	    					{
	    						proxy:{
	    							type:'ajax',
	    							method:'post',
	    							url:'odata_OutstockMReceiptAction_getOdata_OutstockMReceiptCombo.action',
	    							reader:{
	    								root:'rootList',
	    								totalProperty:'totalCount'
	    							},
	    							extraParams:{
	    								strFlag : "5",
	    								strSendFlag : 'form',
	    								strCheckFlag : "normal"
	    							}
	    						}
	    	   				})
	    				}]
	    		   },{
	    				xtype:'grid',
	    				region : 'center',
	    				store:gridOutstockM3601store,
	    		        id: 'gridOutstockM3601',
	    				columns:[{			
	    					xtype : 'rownumberer',
	    					width : 30
	    				},
	    				{
	    					width : 150,
	    					text : $i18n.outstock_no,//下架单号
	    					dataIndex : 'outstockNo'
	    				},{
	    					width:80,
	    					text :$i18n.operate_type,//作业类型
	    					dataIndex : 'operateType'
	    				},{
	    					width:100,
	    					text :$i18n.status2,//操作状态
	    					dataIndex : 'statusText'
	    				},{
	    					width:150,
	    					text :$i18n.operate_date,//操作日期
	    					dataIndex : 'operateDate'
	    				}],
	    				dockedItems : [{
	    					   xtype : 'pagingtoolbar',
	    					   dock : 'bottom',
	    					   store:gridOutstockM3601store,
	    					   displayInfo : true
	    	    	  	}]
	    			},
	    			{
	    				xtype:'grid',
	    				region : 'south',
	    				store:gridOutstockD3601store,
	    		        id: 'gridOutstockD3601',
	    				height:250,
	    				selType : 'cellmodel',
	    				plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
	    					clicksToEdit : 1,
	    					onSpecialKey:function(ed,field,e){
	    						commEnterGridStatEdit(this.grid,false,'',e.getKey());
	    					}
	    				})],
	    				columns:[{			
	    					xtype : 'rownumberer',
	    					width : 20
	    				},
	    				{
	    					width : 80,
	    					text : $i18n.s_cell_no,//下架储位
	    					dataIndex : 'SCellNo'
	    				},
	    				{
	    					width:110,
	    					text :$i18n.article_no,//商品编码
	    					dataIndex : 'articleNo'
	    				},
	    				{
	    					width : 100,
	    					text : $i18n.owner_article_no,//货主商品编码
	    					dataIndex : 'ownerArticleNo'
	    				},

	    				{
	    					width:180,
	    					text :$i18n.article_name,//商品名称
	    					dataIndex : 'articleName'
	    				},
	    				{
	    					width : 110,
	    					text : $i18n.barcode,//商品条码
	    					dataIndex : 'barcode'
	    				},
	    				{
	    					width : 60,
	    					text : $i18n.packing_qty,//包装数量
	    					dataIndex : 'packingQty'
	    				},
	    				{
	    					width : 60,
	    					text : $i18n.packing_unit,//包装单位
	    					dataIndex : 'unit'
	    				},
	    				{
	    					width : 60,
	    					text : $i18n.plan_box,//计划数量
	    					dataIndex : 'planBox'
	    				},
	    				{
	    					width : 60,
	    					text : $i18n.box_s,//实际箱数
	    					dataIndex : 'realBox',
	    					cls:'notnull',
	    					field: {
	    		            	xtype: 'numberfield',
	    		            	minValue:0,
	    		            	listeners:{  
	    							'change': function(obj, newValue, oldValue, eOpts) {
	    								if(newValue!=oldValue){
	    									var data = Ext.getCmp('gridOutstockD3601').getSelectionModel()
	    										.getSelection();
	    									data[0].set('realQty', parseInt(newValue * parseInt(data[0].get('packingQty'))));
	    								}
	    							}
	    		      			}
	    		            }
	    				},
	    				{
	    					width:80,
	    					text :$i18n.label_nos,//标签号码
	    					dataIndex : 'custContainerNo',
	    					cls:'notnull',
	    					field:{
	    						xtype: 'textfield'
	    					}
	    				},{
	    					width:80,
	    					text :$i18n.real_qty,//总数量
	    					dataIndex : 'realQty',
	    					cls:'notnull',
	    					field: {
	    		            	xtype: 'numberfield',
	    		            	listeners:{  
	    							'change': function(obj, newValue, oldValue, eOpts) {
	    								console.log('12345');
	    								if(newValue!=oldValue){
	    									var data = Ext.getCmp('gridOutstockD3601').getSelectionModel()
	    										.getSelection();
	    									data[0].set('realBox', parseInt(newValue
	    										/ parseInt(data[0].get('packingQty'))));
	    									data[0].set('realDis', parseInt(newValue
	    										% parseInt(data[0].get('packingQty'))));
	    								}
	    							}
	    		      			}
	    		            }
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
	    				}]
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
	    						fieldLabel : $i18n.outstock_worker,//  
	    						id : 'cmbWorkerNo3601',
	    						store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore'),
	    						beforeLabelTextTpl : required
	    					},
	    					{
	    						xtype : 'button',
	    						margin:'0 0 0 10',
	    						id:'butReceipt3601',
	    						disabled:true,
	    						text : $i18n.allreceipt//整单回单
	    					}]
	    			 }]
	    },
	    {
	    	id:'outstockFormCheckTab3601',
	    	layout:'border',
	    	title:$i18n.historyDateCheck,
	    	items:[
	        {
           	xtype : 'form',
				id:'formCheckFilterCondition3601',
				layout:'column',
				region : 'north',
				frame : true,
				defaults:{
			       labelAlign:'right',
			       labelWidth : 60
		        },
                items:[
                       {
    					xtype:'bdef_DefOwnerCombo',
    					fieldLabel:$i18n.owner,//货主编号
    					queryMode:'local',
    				    allowBlank:true,
    				    id:'cmbOwnerNoCheck3601',
    				    displayField: 'dropValue',
    					valueField: 'value',
    					store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',
    					 {
    						 proxy:{
    								type:'ajax',
    								method:'post',
    								url:'odata_OutstockMReceiptAction_getOdata_OutstockMReceiptCombo',
    								reader:{
    									root:'rootList',
    									totalProperty:'totalCount'
    								},
    								extraParams:{
    									strFlag : "4",
    									strSendFlag : "form",
    									strCheckFlag : "history"
    								}
    							}
    						})
    				},
    				{
    					xtype:'remoteCombo',
    					fieldLabel : $i18n.locate_no,// 波次号
    					id:'cmbWave_noCheck3601',
    					store:Ext.create("cms.store.common.comboStore",
    					{
    						proxy:{
    							type:'ajax',
    							method:'post',
    							url:'odata_OutstockMReceiptAction_getOdata_OutstockMReceiptCombo',
    							reader:{
    								root:'rootList',
    								totalProperty:'totalCount'
    							},
    							extraParams:{
    								strFlag : "1",
    								strSendFlag : 'form',
    								strCheckFlag : "history"
    							}
    						}
    	   				})
    				},{
    					xtype:'remoteCombo',
    					fieldLabel : $i18n.m_batch_no,//批次
    					id:'cmbBatch_noCheck3601',
    					store:Ext.create("cms.store.common.comboStore",
						{
							proxy:{
								type:'ajax',
								method:'post',
								url:'odata_OutstockMReceiptAction_getOdata_OutstockMReceiptCombo.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								},
								extraParams:{
									strFlag : "2",
									strSendFlag : 'form',
									strCheckFlag : "history"
								}
							}
		   				})
    				},
    				{
    					xtype:'wms_DefFieldValCombo',
    					fieldLabel : $i18n.operate_type,// 作业类型
    					id:'cmbOperate_typeCheck3601',
    					store:Ext.create("cms.store.common.comboStore",
    							{
    								proxy:{
    									type:'ajax',
    									method:'post',
    									url:'odata_OutstockMReceiptAction_getOdata_OutstockMReceiptCombo.action',
    									reader:{
    										root:'rootList',
    										totalProperty:'totalCount'
    									},
    									extraParams:{
    										strFlag : "3",
    										strSendFlag : 'form',
    										strCheckFlag : "history"
    									}
    								}
    			   				})
    			   				
    				},
    				{
    					xtype:'remoteCombo',
    					fieldLabel : $i18n.outstock_no,//下架单号
    					id:'cmbOutstockNoCheck3601',
    					store:Ext.create("cms.store.common.comboStore",
    					{
    						proxy:{
    							type:'ajax',
    							method:'post',
    							url:'odata_OutstockMReceiptAction_getOdata_OutstockMReceiptCombo.action',
    							reader:{
    								root:'rootList',
    								totalProperty:'totalCount'
    							},
    							extraParams:{
    								strFlag : "5",
    								strSendFlag : 'form',
    								strCheckFlag : "history"
    							}
    						}
    	   				})
    				}]
           },
			{
				xtype:'grid',
				region : 'center',
				store:gridOutstockMCheck3601store,
			    id: 'gridOutstockMCheck3601',
				columns:[{			
					xtype : 'rownumberer',
					width : 30
				},
				{
					width : 150,
					text : $i18n.outstock_no,//下架单号
					dataIndex : 'outstockNo'
				},{
					width:80,
					text :$i18n.operate_type,//作业类型
					dataIndex : 'operateType'
				},{
					width:100,
					text :$i18n.status2,//操作状态
					dataIndex : 'statusText'
				},{
					width:150,
					text :$i18n.operate_date,//操作日期
					dataIndex : 'operateDate'
				}],
				dockedItems : [{
					   xtype : 'pagingtoolbar',
					   dock : 'bottom',
					   store:gridOutstockMCheck3601store,
					   displayInfo : true
			  	}]
			},
			{
				xtype:'grid',
				region : 'south',
				store:gridOutstockDCheck3601store,
			    id: 'gridOutstockDCheck3601',
				height:250,
				selType : 'cellmodel',
				plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
					clicksToEdit : 1
				})],
				columns:[{			
					xtype : 'rownumberer',
					width : 20
				},
				{
					width : 80,
					text : $i18n.s_cell_no,//下架储位
					dataIndex : 'SCellNo'
				},
				{
					width:110,
					text :$i18n.article_no,//商品编码
					dataIndex : 'articleNo'
				},{
					width : 100,
					text : $i18n.owner_article_no,//货主商品编码
					dataIndex : 'ownerArticleNo'
				},
				{
					width:180,
					text :$i18n.article_name,//商品名称
					dataIndex : 'articleName'
				},
				{
					width : 110,
					text : $i18n.barcode,//商品条码
					dataIndex : 'barcode'
				},
				{
					width : 60,
					text : $i18n.packing_qty,//包装数量
					dataIndex : 'packingQty'
				},
				{
					width : 60,
					text : $i18n.packing_unit,//包装单位
					dataIndex : 'unit'
				},
				{
					width : 60,
					text : $i18n.plan_box,//计划数量
					dataIndex : 'planBox'
				},
				{
					width : 60,
					text : $i18n.plan_pcs,//计划数量
					dataIndex : 'planDis'
				},
				{
					width : 60,
					text : $i18n.box_s,//实际箱数
					dataIndex : 'realBox'
				},
				{
					width : 60,
					text : $i18n.pcs_s,//实际散数
					dataIndex : 'realDis'
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
				}]
			}  
	    	]
	    }
	    ]
    }
	]
});