/**
 * 模块名称：拣货任务标签回单
 * 模块编码：3501
 * 创建：周欢
 */
var gridOutstockM3501store=Ext.create('cms.store.odata.odata_OutstockMStore',{
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
			strSendFlag : "label",
			strCheckFlag : "normal"
		}
	},listeners:{  
		'load':function(th,records,successful,eOpts ){
		if(th.count()>0){
			Ext.getCmp('gridOutstockM3501').getSelectionModel().select(0);
		}
	  }
	}
});
var gridOutstockMCheck3501store=Ext.create('cms.store.odata.odata_OutstockMStore',
{
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
			strSendFlag : "label",
			strCheckFlag : "history"
		}
	},listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(th.count()>0){
				Ext.getCmp('gridOutstockMCheck3501').getSelectionModel().select(0);
			}
		}
	}
});
var gridOutstockD3501store=Ext.create('cms.store.odata.odata_OutstockDStore',{
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
			strSendFlag : "label",
			strCheckFlag : "normal"
		}
	}/*,
	listeners:{
		'load':function(th,records,successful,eOpts ){
			var arrayObj = new Array();
				arrayObj[0]='planBox';
				arrayObj[1]='planDis';
				countList('gridOutstockD3501',arrayObj,'custName');
		}
	}*/
});
var gridOutstockDCheck3501store=Ext.create('cms.store.odata.odata_OutstockDStore',{
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
			strSendFlag : "label",
			strCheckFlag : "history"
		}
	}
});
Ext.define('cms.view.odata.odata_OutstockLabelReceiptUI',{
	alias:'widget.odata_OutstockLabelReceiptUI',
	title:$i18n.outstockLabelReceipt,//拣货回单标签回单
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:['cms.view.common.commMenu4',
	          'cms.view.common.wms_DefFieldValCombo',
		      'cms.view.common.bdef_DefWorkerCombo',
		      'cms.view.common.bdef_DefOwnerCombo',
		  	  'cms.view.common.remoteCombo'
	],
	items:[
	{
	   xtype : 'commMenuWidget4',
	   id:'menu3501',
	   region:'north'
	},
	{xtype : 'tabpanel',
	    id:'tabpanel3501',
	    region:'center',
	    items:[{
	    	id:'outstockAutoReceiptTab3501',
	    	layout:'border',
	    	title:$i18n.outstockLabelReceipt,
	    	items:[
	    		   {
	    				xtype : 'form',
	    				id:'formSelectCondition3501',
	    				layout : {
							 type : 'table',
							 columns :5
						 },
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
	    				    id:'cmbOwnerNo3501',
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
	    									strSendFlag : "label",
	    									strCheckFlag : "normal"
	    								}
	    							},	 
	    						listeners:{  
	    								'load':function(th,records,successful,eOpts ){
	    									if(th.count()>0){
	    										Ext.getCmp('cmbOwnerNo3501').setValue(th.getAt(0).data.value);
	    										_myAppGlobal.getController('cms.controller.odata.odata_OutstockLabelReceiptController').cmbOwnerNo3501Select();
	    									}else
    										{
	    										Ext.getCmp('cmbWave_no3501').getStore().removeAll();
	    										Ext.getCmp('cmbWave_no3501').setValue(null);
	    										Ext.getCmp('cmbBatch_no3501').getStore().removeAll();
	    										Ext.getCmp('cmbBatch_no3501').setValue(null);
	    										Ext.getCmp('cmbOperate_type3501').getStore().removeAll();
	    										Ext.getCmp('cmbOperate_type3501').setValue(null);
	    										Ext.getCmp('cmbOutstockNo3501').getStore().removeAll();
	    										Ext.getCmp('cmbOutstockNo3501').setValue(null);
	    										Ext.getCmp('gridOutstockM3501').getStore().removeAll();
	    										Ext.getCmp('gridOutstockD3501').getStore().removeAll();
    										}
	    								}
	    							}
	    						}),
	    				    beforeLabelTextTpl:required
	    				},
	    				{
	    					xtype:'wms_DefFieldValCombo',
	    					fieldLabel : $i18n.locate_no,// 波次号
	    					id:'cmbWave_no3501',
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
	    								strB2CYesOrNo:"1",
	    								strFlag : "1",
	    								strSendFlag : 'label',
	    								strCheckFlag : "normal"
	    							}
	    						},
	    						listeners:{  
		    						'load':function(th,records,successful,eOpts ){
		    							if(th.count()>0){
		    								Ext.getCmp('cmbWave_no3501').setValue(th.getAt(0).data.value);
		    								_myAppGlobal.getController('cms.controller.odata.odata_OutstockLabelReceiptController').cmbWave_no3501change();
		    							}else
		    							{
		    								Ext.getCmp('cmbBatch_no3501').getStore().removeAll();
		    								Ext.getCmp('cmbBatch_no3501').setValue(null);
		    								Ext.getCmp('cmbOperate_type3501').getStore().removeAll();
		    								Ext.getCmp('cmbOperate_type3501').setValue(null);
		    								Ext.getCmp('cmbOutstockNo3501').getStore().removeAll();
		    								Ext.getCmp('cmbOutstockNo3501').setValue(null);
		    								Ext.getCmp('gridOutstockM3501').getStore().removeAll();
		    								Ext.getCmp('gridOutstockD3501').getStore().removeAll();
		    							}
		    						}
	    						}
	    	   				}),
	    					beforeLabelTextTpl : required
	    				},
	    				{
	    					xtype:'wms_DefFieldValCombo',
	    					fieldLabel : $i18n.m_batch_no,//批次
	    					id:'cmbBatch_no3501',
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
	    								strSendFlag : 'label',
	    								strCheckFlag : "normal"
	    							}
	    						},
	    						listeners:{  
	    							'load':function(th,records,successful,eOpts ){
	    								if(th.count()>0){
	    									Ext.getCmp('cmbBatch_no3501').setValue(th.getAt(0).data.value);
	    									_myAppGlobal.getController('cms.controller.odata.odata_OutstockLabelReceiptController').cmbBatch_no3501change();
	    								}else
	    								{
	    									Ext.getCmp('cmbOperate_type3501').getStore().removeAll();
	    							   		Ext.getCmp('cmbOperate_type3501').setValue(null);
	    							   		Ext.getCmp('cmbOutstockNo3501').getStore().removeAll();
	    									Ext.getCmp('cmbOutstockNo3501').setValue(null);
	    									Ext.getCmp('gridOutstockM3501').getStore().removeAll();
	    									Ext.getCmp('gridOutstockD3501').getStore().removeAll();
	    								}
	    							}
	    						}
	    	   				}),
	    					beforeLabelTextTpl : required
	    				},
	    				{
	    					xtype:'wms_DefFieldValCombo',
	    					fieldLabel : $i18n.operate_type,// 作业类型
	    					id:'cmbOperate_type3501',
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
	    								strSendFlag : 'label',
	    								strCheckFlag : "normal"
	    							}
	    						},
	    						listeners:{  
	    						'load':function(th,records,successful,eOpts ){
	    							if(th.count()>0){
	    								Ext.getCmp('cmbOperate_type3501').setValue(th.getAt(0).data.value);
	    								_myAppGlobal.getController('cms.controller.odata.odata_OutstockLabelReceiptController').cmbOperate_type3501change();
	    							}else
	    							{
	    								Ext.getCmp('cmbOutstockNo3501').getStore().removeAll();
	    								Ext.getCmp('cmbOutstockNo3501').setValue(null);
	    								Ext.getCmp('gridOutstockM3501').getStore().removeAll();
	    								Ext.getCmp('gridOutstockD3501').getStore().removeAll();
	    							}
	    						}
	    						}
	    	   				}),
	    					beforeLabelTextTpl : required
	    				},
	    				{
	    					xtype:'remoteCombo',
	    					fieldLabel : $i18n.outstock_no,//下架单号
	    					id:'cmbOutstockNo3501',
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
	    								strSendFlag : 'label',
	    								strCheckFlag : "normal"
	    							}
	    						}
	    	   				})
	    				}]
	    		   },{
	    				xtype:'grid',
	    				region : 'center',
	    				store:gridOutstockM3501store,
	    		        id: 'gridOutstockM3501',
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
	    				},{
	    					width:110,
	    					text :'索单人',
	    					dataIndex : 'workerName'
	    				},{
	    					width:150,
	    					text : '索单时间',
	    					dataIndex : 'handoutDate'
	    				}]
	    				
	    			},
	    			{
	    				xtype:'grid',
	    				region : 'south',
	    				store:gridOutstockD3501store,
	    		        id: 'gridOutstockD3501',
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
	    					width : 30
	    				},{
	    					width:100,
	    					text :'客户名称',
	    					dataIndex : 'custName'
	    				},
	    				{
	    					width:100,
	    					text :$i18n.label_nos,//标签号码
	    					dataIndex : 'labelNo'
	    				},{
	    					width : 60,
	    					text : $i18n.d_cell_no ,//目的储位
	    					dataIndex : 'DCellNo'
	    				},
	    				{
	    					width : 60,
	    					text : $i18n.s_cell_no ,//来源储位
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
	    				    hidden:true,
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
	    					text : $i18n.packingUnit,//包装单位
	    				    id:'packingUnit3501_1',
	    					dataIndex : 'packingUnit'
	    				},
	    				{
	    				    width:80,
	    				    text:$i18n.packingSpec,//规格
	    				    id:'packingSpec3501_1',
	    				    dataIndex:'packingSpec'
	    	    	    },
	    				{
	    					width : 80,
	    					text : '计划数量',//计划数量
	    				    id:'articleQty3501_1',
	    					dataIndex : 'articleQty'
	    				},
	    				{
	    					width : 80,
	    					text : $i18n.planBox,//计划箱数
	    					dataIndex : 'planBox',
	    					id:'planBox3501_1'
	    				},{
	    					width : 80,
	    					text : $i18n.planQmin,//计划中包数
	    					dataIndex : 'planQmin',
	    					id:'planQmin3501_1'
	    				},{
	    					width : 80,
	    					text : $i18n.planDis,//计划散数
	    					dataIndex : 'planDis',
	    					id:'planDis3501_1'
	    				},
	    				{
	    					width : 80,
	    					text : '实际数量',//实际数
	    				    id:'realQty3501_1',
	    					dataIndex : 'realQty'
	    				},
	    				{
	    					width : 80,
	    					text : $i18n.realBox,//实际箱数
	    					dataIndex : 'realBox',
	    					id:'realBox3501_1',
	    					cls : 'notnull',
	    					field : {
	    			    		xtype : 'numberfield',
	    			    		minValue:0,
	    			    		listeners:{  
	    							'change': function(obj, newValue, oldValue, eOpts) {
	    								if(newValue!=oldValue){
	    									var data = Ext.getCmp('gridOutstockD3501').getSelectionModel()
	    										.getSelection();
	    									data[0].set('realQty', newValue
	    										* data[0].get('packingQty')
	    										+ data[0].get('realQmin') * data[0].get('qminOperatePacking')
	    										+ data[0].get('realDis'));
	    								}
	    							}
	    		      			}
	    			    	}
	    				},{
	    					width : 80,
	    					text : $i18n.realQmin,//实际中包数
	    					dataIndex : 'realQmin',
	    					id:'realQmin3501_1',
	    					cls : 'notnull',
	    					field : {
	    			    		xtype : 'numberfield',
	    			    		minValue:0,
	    			    		listeners:{  
	    							'change': function(obj, newValue, oldValue, eOpts) {
	    								debugger;
	    								if(newValue!=oldValue){
	    									var data = Ext.getCmp('gridOutstockD3501').getSelectionModel()
	    										.getSelection();
	    									data[0].set('realQty', 
	    											data[0].get('realBox')* data[0].get('packingQty')
	    										  + newValue * data[0].get('qminOperatePacking')
	    										  + data[0].get('realDis'));
	    								}
	    							}
	    		      			}
	    			    	}
	    				},{
	    					width : 80,
	    					text : $i18n.realDis,//实际散数
	    					dataIndex : 'realDis',
	    					id:'realDis3501_1',
	    					cls : 'notnull',
	    					field : {
	    			    		xtype : 'numberfield',
	    			    		minValue:0,
	    			    		listeners:{  
	    							'change': function(obj, newValue, oldValue, eOpts) {
	    								if(newValue!=oldValue){
	    									var data = Ext.getCmp('gridOutstockD3501').getSelectionModel()
	    										.getSelection();
	    									data[0].set('realQty', 
	    											data[0].get('realBox')* data[0].get('packingQty')
	    										  + data[0].get('realQmin') * data[0].get('qminOperatePacking')
	    										  + newValue);
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
	    				},{
	    					width:80,
	    					text:'暂存区编码',
	    					dataIndex:'deliverArea'
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
	    						id : 'cmbWorkerNo3501',
	    						store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore'),
	    						beforeLabelTextTpl : required
	    					},
	    					{
	    						xtype : 'button',
	    						margin:'0 0 0 20',
	    						id:'butReceipt3501',
	    						disabled:true,
	    						text : $i18n.allreceipt//整单回单
	    					}]
	    			 }
	    		]
	    },
	    {
	    	id:'outstockAutoCheckTab3501',
	    	layout:'border',
	    	title:$i18n.historyDateCheck,
	    	items:[{
				xtype : 'form',
				id:'formSelectConditionCheck3501',
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
					fieldLabel:$i18n.owner,//历史单据》货主编号
					queryMode:'local',
				    allowBlank:true,
				    id:'cmbOwnerNoCheck3501',
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
									strSendFlag : "label",
									strCheckFlag : "history"
								}
							}
						})
				},
				{
					xtype:'remoteCombo',
					fieldLabel : $i18n.locate_no,// 波次号
					id:'cmbWave_noCheck3501',
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
								strSendFlag : 'label',
								strCheckFlag : "history"
							}
						}
	   				})
				},
				{
					xtype:'wms_DefFieldValCombo',
					fieldLabel : $i18n.m_batch_no,//批次
					id:'cmbBatch_noCheck3501',
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
								strSendFlag : 'label',
								strCheckFlag : "history"
							}
						}
	   				})
				},
				{
					xtype:'wms_DefFieldValCombo',
					fieldLabel : $i18n.operate_type,// 作业类型
					id:'cmbOperate_typeCheck3501',
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
								strSendFlag : 'label',
								strCheckFlag : "history"
							}
						}
	   				})
				},
				{
					xtype:'remoteCombo',
					fieldLabel : $i18n.outstock_no,//下架单号
					id:'cmbOutstockNoCheck3501',
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
								strSendFlag : 'label',
								strCheckFlag : "history"
							}
						}
	   				})
				}]
		   },{
				xtype:'grid',
				region : 'center',
				store:gridOutstockMCheck3501store,
		        id: 'gridOutstockMCheck3501',
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
				}]
				
			},
			{
				xtype:'grid',
				region : 'south',
				store:gridOutstockDCheck3501store,
		        id: 'gridOutstockDCheck3501',
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
					text : $i18n.s_cell_no ,//来源储位
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
				},{
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
					text : $i18n.packingUnit,//包装单位
				    id:'packingUnit3501_2',
					dataIndex : 'packingUnit'
				},
				{
				    width:80,
				    text:$i18n.packingSpec,//规格
				    id:'packingSpec3501_2',
				    dataIndex:'packingSpec'
	    	    },
				{
					width : 80,
					text : '计划数量',//计划数量
				    id:'articleQty3501_2',
					dataIndex : 'articleQty'
				},
				{
					width : 80,
					text : $i18n.planBox,//计划箱数
					dataIndex : 'planBox',
					id:'planBox3501_2'
				},{
					width : 80,
					text : $i18n.planQmin,//计划中包数
					dataIndex : 'planQmin',
					id:'planQmin3501_2'
				},{
					width : 80,
					text : $i18n.planDis,//计划散数
					dataIndex : 'planDis',
					id:'planDis3501_2'
				},
				{
					width : 80,
					text : '实际数量',//实际数
				    id:'realQty3501_2',
					dataIndex : 'realQty'
				},
				{
					width : 80,
					text : $i18n.realBox,//实际箱数
					dataIndex : 'realBox',
					id:'realBox3501_2'
				},{
					width : 80,
					text : $i18n.realQmin,//实际中包数
					dataIndex : 'realQmin',
					id:'realQmin3501_2'
				},{
					width : 80,
					text : $i18n.realDis,//实际散数
					dataIndex : 'realDis',
					id:'realDis3501_2'
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
			}]
	    },{
	    	title:'补印中心',
	    	layout:'border',
	    	itemId:'tabPId3501i',
	    	id:'tabPId3501i',
	    }]
		
	}]
});