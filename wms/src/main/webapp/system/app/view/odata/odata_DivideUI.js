/**
 * 模块名称：分播回单
 * 模块编码：3701
 * 创建：周欢
 */
var gridDivideM3701store = Ext.create('cms.store.odata.odata_DivideMStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_DivideAction_getOdata_DivideM.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		},
		extraParams:{
			strFlag : "1"
		}
	},
	listeners:{ 	 
				'load':function(th,records,successful,eOpts ){
					if(Ext.getCmp('gridDivideM3701').getStore().count()>0){
						Ext.getCmp('gridDivideM3701').getSelectionModel().select(0);
							Ext.getCmp('menu3701').items.items[1].setDisabled(false);
					}else
					{
						Ext.getCmp('menu3701').items.items[1].setDisabled(true);
					}
				}
			}
});

var gridDivideMCheck3701store = Ext.create('cms.store.odata.odata_DivideMStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_DivideAction_getOdata_DivideM.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		},
		extraParams:{
			strFlag : "2"
		}
	},
	listeners:{ 	 
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('gridDivideMCheck3701').getStore().count()>0){
				Ext.getCmp('gridDivideMCheck3701').getSelectionModel().select(0);
			}
		}
	}
});

var gridDivide_d3701store = Ext.create('cms.store.odata.odata_DivideDStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_DivideAction_getOdata_DivideD.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		},		
		extraParams:{
			strFlag : "1"
		}
	} 
});
var gridDivideDCheck3701Store = Ext.create('cms.store.odata.odata_DivideDStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_DivideAction_getOdata_DivideD.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		},		
		extraParams:{
			strFlag : "2"
		}
	} 
});
Ext.define('cms.view.odata.odata_DivideUI',{
	alias:'widget.odata_DividUI',
	title:$i18n.divide_d,//分播回单
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	         'cms.view.common.wms_DefFieldValCombo',
	         'cms.view.common.commMenu4',
	         'cms.view.common.bdef_DefWorkerCombo',
	         'cms.view.common.remoteCombo'
	          ],
	items:[{
	    	   xtype:'commMenuWidget4',
	    	   id:'menu3701',
	   		   region:'north'/*,
		       items : [ {
					text : '新增',
					iconCls : 'add',
					name : 'add'
				},{
					text : '修改',
					iconCls : 'edit',
					name : 'edit'
				},{
					text : '删除',
					iconCls : 'delete',
					name : 'delete'
				},{
					text : '撤销',
					iconCls : 'undo',
					disabled:true,
					name : 'undo'
				},{
					text : '保存',
					iconCls : 'save',
					disabled:true,
					name : 'save'
				},{
					text : '发单',
					iconCls : 'send',
					name : 'send'
				},{
					text : '查找',
					iconCls : 'query',
					name : 'query'
				},{
					text : '刷新',
					iconCls : 'refresh',
				 	//hidden:false,
					name : 'refresh'
				},{
					text : '导出',
					iconCls : 'export',
					name : 'export'
				},{
					text : '打印',
					iconCls : 'print',
					name : 'print'
				},{
					text : '取消',
					iconCls : 'undo',
					name : 'cancle'
				}]*/
	       },
	       {
	    	   xtype : 'tabpanel',
			   id : 'tabpanel3701',
			   region : 'center',
			   items:[
			    {
			    	title:$i18n.divide_d,//分播回单
                    id:'tabDivide3701',
                    layout:'border',
                    items:[
					{
					    xtype:'panel',
					    region : 'north',
						layout : 'border',
						height:250,
						border : false,
						items:[{
								xtype:'panel',
								region : 'center',
								layout : 'border',
								border : false,
							    items:[
									  {
										xtype : 'form',
										id:'formFilterCondition3701',
										layout:{
											type : 'table',
											columns : 3
										},
										region : 'north',
										frame : true,
										defaults:{
											labelWidth : 70,
										       labelAlign:'right',
										       margin : '0 2 2 2'
								        },
										items:[
										{
											xtype:'remoteCombo',
											fieldLabel : $i18n.locate_no,// 波次号
											id:'cmbWave_no3701',
											store:Ext.create("cms.store.common.comboStore",
													{
														proxy:{
															type:'ajax',
															method:'post',
															url:'odata_DivideAction_getOdata_DivideCombo.action',
															reader:{
																root:'rootList',
																totalProperty:'totalCount'
															},
															extraParams:{
																strFlag : "1",
																strCheckFlag:"normal"
															}
														}
									   				})/*,
											beforeLabelTextTpl : required*/
										},{
											xtype:'wms_DefFieldValCombo',
											fieldLabel : $i18n.m_batch_no,//批次
											id:'cmbBatch_no3701',
											allowBlank:false,
											store:Ext.create("cms.store.common.comboStore",
											{
												proxy:{
													type:'ajax',
													method:'post',
													url:'odata_DivideAction_getOdata_DivideCombo.action',
													reader:{
														root:'rootList',
														totalProperty:'totalCount'
													},
													extraParams:{
														strFlag : "2",
														strCheckFlag:"normal"
													}
												}
												//autoLoad:true,
												/*listeners:{  
												'load':function(th,records,successful,eOpts ){
													if(th.count()>0){
														Ext.getCmp('cmbBatch_no3701').setValue(th.getAt(0).data.value);
														_myAppGlobal.getController('cms.controller.odata.odata_DivideController')
			    										.mBatchNoChange();
													}
												}
												}*/
							   				})/*,
											beforeLabelTextTpl : required*/
										},{
											xtype:'remoteCombo',
											fieldLabel : $i18n.barcode,//商品条码
											id:'barcode3701',
											store:Ext.create("cms.store.common.comboStore",
											{
												proxy:{
													type:'ajax',
													method:'post',
													url:'odata_DivideAction_getBarcodeCombo.action',
													reader:{
														root:'rootList',
														totalProperty:'totalCount'
													}
												}
												
							   				}).load()
										}]
								   },{
									xtype : 'grid',
									height : 250,
									store:gridDivideM3701store,
									id : 'gridDivideM3701',
									region : 'center',
									columns : [ {
										xtype : 'rownumberer',
										width : 30
									}, {
										width : 140,
										text : $i18n.divide_no,//分播单号
										//id:'cmbDivide_no3701',
										dataIndex : 'divideNo'
									}, {
										width : 80,
										text : $i18n.status2,//操作状态
										//id:'cmbStatus3701',
										dataIndex : 'statusText'
									}, {
										width : 130,
										text : $i18n.operate_date,//操作日期
										dataIndex : 'operateDate'
									}],
									dockedItems : [
					    	        {
						    	        xtype : 'pagingtoolbar',
						    	        dock : 'bottom',
						    	        store:gridDivideM3701store,
						    	        displayInfo : true
						    	    }] 
								}]
						},{
						    xtype:'form',
						    height : 250,
						    width:400,
								region : 'east',
								border : false,
								frame:true,
								items : [
							{
					           xtype:'fieldset',  
					           autoHeight:false,  
					           margin:'5 10 10 10',
					           id:'formDivideM3701',
					           title:$i18n.master_info,  
					           /*layout: {
						   	   		type: 'table',
						   	        columns: 2
					   	   	   },*/
					           defaults:{
					    	   		xtype:'textfield',
					    	   		margin : '0 2 2 2',
					    	   		labelAlign:'right',
					    	   		labelWidth:90
					       	   },
					           items : [{
										   xtype : 'textfield',
										   id:'cmbDivide_no3701',
										   fieldLabel : $i18n.divide_no,//分播单号
										   beforeLabelTextTpl : required
									   },{
										   xtype : 'textfield',
										   id:'cmbStatus3701',
										   fieldLabel : $i18n.status2,//操作状态
										   beforeLabelTextTpl : required
									   },{
										   xtype : 'textfield',
										   id:'cmbDivide_type3701',
										   fieldLabel : $i18n.divide_type,//分播类型
										   beforeLabelTextTpl : required
									   },{
										   xtype : 'textfield',
										   id:'cmbOperateDate3701',
										   fieldLabel : $i18n.operate_date,//操作日期
										   beforeLabelTextTpl : required
									   }]
					        },{					           
					           xtype:'fieldset',  
					           autoHeight:false,  
					           //width:'70%',
					           margin:'5 10 10 10%',
					           id:'cmbDivide_m3701',
					           title:$i18n.workerinfo,//操作人员  
					           defaults:{
					    	   		xtype:'textfield',
					    	   		margin:'0 4 1 4'
					    	   		//labelAlign:'right',
					    	   		//labelWidth:90
					       	   },
					           items : [{
								   	   //width:'90%',
									   xtype : 'bdef_DefWorkerCombo',
									   id:'cmbDivide_name3701',
									   store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore'), 
									   fieldLabel : $i18n.divide_name,//实际分播人员
									   beforeLabelTextTpl : required
								   },{
									   xtype : 'button',
			    					   margin:'0 0 0 110',
			    					   id:'butCancelDivide3701',
			    					   disabled:true,
			    					   text : $i18n.cancel_divide//取消分播
								   }]
					        }]
							}
						]
					},
					{
						xtype:'grid',
						region : 'center',
						store:gridDivide_d3701store,
						id:'gridDivide_d3701',
						width : '100%',
						loadMask : true, // 加载时有加载的图标
						//selType : 'cellmodel',
	    				plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
	    					clicksToEdit : 1,
	    					onSpecialKey:function(ed,field,e){
	    						commEnterGridStatEdit(this.grid,false,'',e.getKey());
	    					}
	    				})],
						columns : [ {
							xtype : 'rownumberer',
							width : 20
						},{
							width : 100,
							text : $i18n.label_no,//标签号
							dataIndex : 'labelNo'
						},{
							width:110,
							text :$i18n.article_no,//商品编码
							//id:'colArticle_no3701',
							dataIndex : 'articleNo'
						},
						{
							width : 100,
							text : $i18n.owner_article_no,//货主商品编码
							dataIndex : 'ownerArticleNo'
						},
						{
							width : 110,
							text : $i18n.barcode,//商品条码
							//id:'colBarcode3701',
							dataIndex : 'barcode'
						},{
							width:160,
							text :$i18n.article_name,//商品名称
							//id:'colArticle_name3701',
							dataIndex : 'articleName'
						},{
							width:80,
							text :$i18n.div_pal_condition,//配送对象
							//hidden:true,
							//id:'colDeliverObj3701',
							dataIndex : 'deliverObj'
						},{
							width:80,
							text :$i18n.cust_no,//客户编码
							id:'colCust_no3701',
							dataIndex : 'custNo'
						},{
							width:180,
							text :$i18n.cust_name,//客户名称
							//id:'colCust_name3701',
							dataIndex : 'custName'
						},{
							width : 60,
							text : $i18n.packing_qty,//包装数量
							dataIndex : 'packingQty'
						},{
	    					width : 60,
	    					text : $i18n.packingUnit,//包装单位
	    				    id:'packingUnit3701_1',
	    					dataIndex : 'packingUnit'
	    				},
	    				{
	    				    width:80,
	    				    text:$i18n.packingSpec,//规格
	    				    id:'packingSpec3701_1',
	    				    dataIndex:'packingSpec'
	    	    	    },
	    				{
	    					width : 80,
	    					text : '计划数量',//计划数量
	    				    id:'articleQty3701_1',
	    					dataIndex : 'articleQty'
	    				},
	    				{
	    					width : 80,
	    					text : $i18n.planBox,//计划箱数
	    					dataIndex : 'planBox',
	    					id:'planBox3701_1'
	    				},{
	    					width : 80,
	    					text : $i18n.planQmin,//计划中包数
	    					dataIndex : 'planQmin',
	    					id:'planQmin3701_1'
	    				},{
	    					width : 80,
	    					text : $i18n.planDis,//计划散数
	    					dataIndex : 'planDis',
	    					id:'planDis3701_1'
	    				},{
							width:100,
							text :'目的标签号码',//目的标签号码
							cls:'notnull',
							//id:'colDContainerNo3701_4',
							field : {
								xtype : 'textfield'
						    },
							dataIndex : 'custContainerNo'
						},{
							xtype:'actioncolumn',
					    	width:50,
					    	align:'center',
							text:'取号',
				            icon: 'system/images/print.png',  
				            tooltip: '目的标签取号',
				            id:'queryDContainerNo3701',
				            handler: function(grid, rowIndex, colIndex) {
				            	Ext.getCmp('gridDivide_d3701').getSelectionModel().select(rowIndex);
				            }
						},{
	    					width : 80,
	    					text : '实际数量',//实际数
	    				    id:'realQty3701_1',
	    					dataIndex : 'realQty'
	    				},
	    				{
	    					width : 80,
	    					text : $i18n.realBox,//实际箱数
	    					dataIndex : 'realBox',
	    					id:'realBox3701_1',
	    					cls : 'notnull',
	    					field : {
	    			    		xtype : 'numberfield',
	    			    		minValue:0,
	    			    		listeners:{  
	    							'change': function(obj, newValue, oldValue, eOpts) {
	    								if(newValue!=oldValue){
	    									var data = Ext.getCmp('gridDivide_d3701').getSelectionModel()
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
	    					id:'realQmin3701_1',
	    					cls : 'notnull',
	    					field : {
	    			    		xtype : 'numberfield',
	    			    		minValue:0,
	    			    		listeners:{  
	    							'change': function(obj, newValue, oldValue, eOpts) {
	    								debugger;
	    								if(newValue!=oldValue){
	    									var data = Ext.getCmp('gridDivide_d3701').getSelectionModel()
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
	    					id:'realDis3701_1',
	    					cls : 'notnull',
	    					field : {
	    			    		xtype : 'numberfield',
	    			    		minValue:0,
	    			    		listeners:{  
	    							'change': function(obj, newValue, oldValue, eOpts) {
	    								if(newValue!=oldValue){
	    									var data = Ext.getCmp('gridDivide_d3701').getSelectionModel()
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
						/*{
	    					width:80,
	    					text :$i18n.real_qty,//总数量
	    					dataIndex : 'realQty',
	    					hidden:true,
	    					cls:'notnull',
	    					field: {
	    		            	xtype: 'numberfield',
	    		            	listeners:{  
	    							'change': function(obj, newValue, oldValue, eOpts) {
	    								if(newValue!=oldValue){
	    									var data = Ext.getCmp('gridDivide_d3701').getSelectionModel()
	    										.getSelection();
	    									data[0].set('realWholenum', newValue
	    										/  data[0].get('packingQty') );
	    									data[0].set('realScatterednum',  newValue
	    										%  data[0].get('packingQty') );
	    								}
	    							}
	    		      			}
	    		            }
	    				},*/
						{
  							width : 80,
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
  							width : 80,
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
  						},{
							width : 80,
							text : $i18n.locate_no,//波次号
							dataIndex : 'waveNo'
						},{
							width : 50,
							text : $i18n.m_batch_no,//批次号
							dataIndex : 'batchNo'
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
  						}]/*,
						dockedItems : [
					    {
					        xtype : 'pagingtoolbar',
					        dock : 'bottom',
					        store: gridDivide_d3701store,
					        displayInfo : true
					    }] */
					},{
					xtype : 'panel',
					id : 'msterForm3701_4',
					region:'south',
					html : '<div class="view_footer" style="margin:0; padding: 8px 20px 8px 20px;width:100% ;'
							+ 'background-color:#C1D5ED; text-align: left;">'
							+ '<span><label>新增人:</label><label id="rgstName3701"></label> </span> '
							+ '<span><label>&nbsp;&nbsp;&nbsp;新增时间：</label><label id="rgstDate3701"></label></span>'
							+ '<span><label>&nbsp;&nbsp;&nbsp;修改人：</label><label id="updtName3701"></label> </span> '
							+ '<span><label>&nbsp;&nbsp;&nbsp;修改时间：</label><label id="updtDate3701"></label> </span></div>'
					}  
                    ]
			    }, {
			    	   title:"历史单据查询",
		               id:'tabDivideCheck3701',
		               layout:'border',
		               items:[
		                    {
		                    	xtype : 'form',
								id:'formCheckFilterCondition3701',
								layout:'column',
								region : 'north',
								frame : true,
								defaults:{
							       labelAlign:'right'
							       //width:200
						        },
				                items:[
								{
											xtype:'remoteCombo',
											fieldLabel : $i18n.locate_no,// 波次号
											id:'cmbWave_noCheck3701',
											store:Ext.create("cms.store.common.comboStore",
													{
														proxy:{
															type:'ajax',
															method:'post',
															url:'odata_DivideAction_getOdata_DivideCombo.action',
															reader:{
																root:'rootList',
																totalProperty:'totalCount'
															},
															extraParams:{
																strFlag : "1",
																strCheckFlag : "history"
															}
														}
									   				}),
											beforeLabelTextTpl : required
										},{
											xtype:'wms_DefFieldValCombo',
											fieldLabel : $i18n.m_batch_no,//批次
											id:'cmbBatch_noCheck3701',
											allowBlank:false,
											store:Ext.create("cms.store.common.comboStore",
											{
												proxy:{
													type:'ajax',
													method:'post',
													url:'odata_DivideAction_getOdata_DivideCombo.action',
													reader:{
														root:'rootList',
														totalProperty:'totalCount'
													},
													extraParams:{
														strFlag : "2",
														strCheckFlag : "history"
													}
												},
												//autoLoad:true,
												listeners:{  
												'load':function(th,records,successful,eOpts ){
													if(th.count()>0){
														Ext.getCmp('cmbBatch_noCheck3701').setValue(th.getAt(0).data.value);
														_myAppGlobal.getController('cms.controller.odata.odata_DivideController')
			    										.cmbBatch_noCheck3701Change();
													}
												}
												}
							   				}),
											beforeLabelTextTpl : required
										},
										{
										xtype:'remoteCombo',
										fieldLabel : $i18n.divide_no,// 分播单号
										id:'cmbDivide_noCheck3701',
										store:Ext.create("cms.store.common.comboStore",
										{
											proxy:{
												type:'ajax',
												method:'post',
												url:'odata_DivideAction_getOdata_DivideCombo.action',
												reader:{
													root:'rootList',
													totalProperty:'totalCount'
												},
												extraParams:{
													strFlag : "4",
													strCheckFlag:"history"
												}
											}
						   				})
									/*beforeLabelTextTpl : required*/
								}]
		                    },
		                    {
								xtype : 'grid',
								height : 250,
								store:gridDivideMCheck3701store,
								id : 'gridDivideMCheck3701',
								region : 'center',
								columns : [ {
									xtype : 'rownumberer',
									width : 30
								}, {
									width : 140,
									text : $i18n.divide_no,//分播单号
									dataIndex : 'divideNo'
								}, {
									width : 80,
									text : $i18n.status2,//操作状态
									//id:'cmbStatus3701',
									dataIndex : 'statusText'
								}, {
									width : 130,
									text : $i18n.operate_date,//操作日期
									dataIndex : 'operateDate'
								}]
							},
		          	        {

		  				    	xtype : 'grid',
		  						region : 'south',
		  						id:'gridDivideDCheck3701',
		  						store:gridDivideDCheck3701Store,
		  						height:300,
		  						width:'100%',
		  						columns : [ 
		  						{
		  							xtype : 'rownumberer',
		  							width : 30
		  						}, 
		  						{
									width:120,
									text :$i18n.article_no,//商品编码
									dataIndex : 'articleNo'
								},{
									width : 120,
									text : $i18n.barcode,//商品条码
									dataIndex : 'barcode'
								},{
									width:140,
									text :$i18n.article_name,//商品名称
									dataIndex : 'articleName'
								},{
									width:120,
									text :$i18n.div_pal_condition,//配送对象
									hidden:true,
									dataIndex : 'deliverObj'
								},{
									width:80,
									text :$i18n.cust_no,//客户编码
									dataIndex : 'custNo'
								},{
									width:120,
									text :$i18n.cust_name,//客户名称
									dataIndex : 'custName'
								},{
									width : 80,
									text : $i18n.packing_qty,//包装数量
									dataIndex : 'packingQty'
								},
								{
									width : 60,
									text : $i18n.packingUnit,//包装单位
								    id:'packingUnit3701_2',
									dataIndex : 'packingUnit'
								},
								{
								    width:80,
								    text:$i18n.packingSpec,//规格
								    id:'packingSpec3701_2',
								    dataIndex:'packingSpec'
					    	    },
								{
									width : 80,
									text : '计划数量',//计划数量
								    id:'articleQty3701_2',
									dataIndex : 'articleQty'
								},
								{
									width : 80,
									text : $i18n.planBox,//计划箱数
									dataIndex : 'planBox',
									id:'planBox3701_2'
								},{
									width : 80,
									text : $i18n.planQmin,//计划中包数
									dataIndex : 'planQmin',
									id:'planQmin3701_2'
								},{
									width : 80,
									text : $i18n.planDis,//计划散数
									dataIndex : 'planDis',
									id:'planDis3701_2'
								},{
									width : 80,
									text : '实际数量',//实际数
								    id:'realQty3701_2',
									dataIndex : 'realQty'
								},{
									width : 80,
									text : $i18n.realBox,//实际箱数
									dataIndex : 'realBox',
									id:'realBox3701_2'
								},{
									width : 80,
									text : $i18n.realQmin,//实际中包数
									dataIndex : 'realQmin',
									id:'realQmin3701_2'
								},{
									width : 80,
									text : $i18n.realDis,//实际散数
									dataIndex : 'realDis',
									id:'realDis3701_2'
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