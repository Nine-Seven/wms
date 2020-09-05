/**
 * 模块名称：移库手键单
 * 模块编码：5101
 * 创建：zhouhuan
 */
var ReplenishmentStore=Ext.create('cms.store.stock.stock_ContentStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'mdata_PlanMAction_getCset_CellArticleList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});
var gridMdata_PlanMForMan5101Store=Ext.create('cms.store.stock.stock_ContentStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});
var gridMdata_movefailList5101Store=Ext.create('cms.store.mdata.mdata_movefailListStore');
Ext.define('cms.view.mdata.mdata_PlanMUI',{
	alias:'widget.mdata_PlanMUI',
	title:$i18n.title5101,//移库手建单
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
			  'cms.view.common.commMenu10',
              'cms.view.common.bdef_DefOwnerCombo',	          
	          'cms.view.common.cdef_DefCellCombo',
	          'cms.view.common.cdef_DefAreaCombo',
	          'cms.view.common.cdef_DefWareCombo',
	          'cms.view.common.remoteCombo',
			  'cms.view.common.cdef_DefStockCombo',
	          'cms.view.common.wms_DefFieldValCombo',
 				 'Ext.grid*'
	          ],
	items:[
	       {
	    	   xtype:'commMenuWidget10',
	    	   id:'menu5101',
	    	   region:'north'
	    		   
	       },{
	   		xtype : 'tabpanel',
			id:'mdata_Plan_MUI5101',
			region:'center',
			flex : 4,
			items : [{
				xtype:'panel',
				title: $i18n.replenishment,
				id:'mdata_Plan_MUI5101_1',
				layout:'border',
			    items: [{
					xtype : 'form',
					id : 'mdata_Plan_MForm5101_1a',
					region : 'north',
					layout:'column',
					width:'100%',
					frame : true,
					items : [ {
						layout : {
						type : 'table',
						columns : 3
						},
						xtype : 'container',
						defaults : {
							labelWidth : 90,
							margin : '2 2 2 2',
							labelAlign : 'right',
							width : 220
						},
						items : [ {
							xtype : 'combo',
							fieldLabel : $i18n.owner,// 委托业主
							id:'owner5101',
							displayField: 'dropValue',
							valueField: 'value',
							beforeLabelTextTpl : required,
							store:Ext.create("cms.store.bdef.bdef_DefOwnerComboStore",{
							listeners:{  
									'load':function(th,records,successful,eOpts ){
										if(th.count()>0){
												Ext.getCmp('owner5101').setValue(th.getAt(0).data.value);
										}
								      }
									}
						   	}).load(),
					   		beforeLabelTextTpl : required
						}, {
							xtype:'wms_DefFieldValCombo',
			 	    		fieldLabel:$i18n.pick_type,//拣货类型
			 	    		id:'pick_type5101',
			 	    		beforeLabelTextTpl : required,
			 	    		colspan:2,
			    		    store:Ext.create("cms.store.common.comboStore",
							{
								proxy:{
									type:'ajax',
									method:'post',
									url:'mdata_PlanMAction_getOdata_GetCombo.action',
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
												Ext.getCmp('pick_type5101').setValue(th.getAt(0).data.value);
										}
								      }
									}
			   				})
						}, {
							xtype : 'checkbox',
							boxLabel:$i18n.replenishment,//安全补货
							id:'replenishment5101',
							hidden:true
						}, {
							xtype : 'combo',
							displayField: 'dropValue',
							valueField: 'value',
							fieldLabel : $i18n.ware_no,//仓区
							id:'ware_no5101',
				            store:Ext.create("cms.store.common.comboStore",
							{
								proxy:{
									type:'ajax',
									method:'post',
									url:'mdata_PlanMAction_getOdata_GetCombo.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									},
									extraParams:{
										flag : "3"
									}
								}
			   				})
						}, {
							xtype : 'combo',
							displayField: 'dropValue',
							valueField: 'value',
							fieldLabel: $i18n.area_no,//储区
							id:'area_no5101',
				           store:Ext.create("cms.store.common.comboStore",
							{
								proxy:{
									type:'ajax',
									method:'post',
									url:'mdata_PlanMAction_getOdata_GetCombo.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									},
									extraParams:{
										flag : "4"
									}
								}
			   				})
						}, {
							xtype : 'combo',
							fieldLabel: $i18n.stock,//通道
							id:'stock_no5101',
							store:Ext.create("cms.store.common.comboStore",
							{
								proxy:{
									type:'ajax',
									method:'post',
									url:'mdata_PlanMAction_getOdata_GetCombo.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									},
									extraParams:{
										flag : "5"
									}
								}
			   				})
						} ]
					},{
						xtype : 'button',
						margin:'28 0 0 15',
						name:'query5101_1',
						text : $i18n.query
					},{
						xtype : 'button',
						name:'create5101_1',
						margin:'28 0 0 15',
						text : $i18n.create
					} ]

				},{
		   			  region:'center',
		        	  xtype:'grid',
		        	  id:'mdata_Plan_MUI5101_1b',
		        	  store:ReplenishmentStore,
		        	  multiSelect: true,  
					  selModel: {  
						   selType:'checkboxmodel'  
					  },
		        	  width:'100%',
		        	  height:'100%',
		        	  columns:[ {
									xtype : 'rownumberer',
									width : 30
								},{
		        	        	   width:90,
		        	        	   text:$i18n.addcell_no,//储位
		        	        	   align:'center',
		        	        	   dataIndex:'cellNo'
		        	           },{
		        	        	   width:90,
		        	        	   text:$i18n.owner_article_no,//货主商品编码
		        	        	    align:'center',
		        	        	   dataIndex:'ownerArticleNo'
		        	           },{
		        	        	   width:130,
		        	        	   text:$i18n.article_name,//商品名称
		        	        	    align:'center',
		        	        	   dataIndex:'articleName'
		        	           },{
		        	        	   width:80,
		        	        	   text:$i18n.packing_qty,//包装数量
		        	        	    align:'center',
		        	        	   dataIndex:'packingQty'
		        	           },{
		       						width:80,
		       						text:$i18n.packingSpec,//规格
		       						id:'packingSpec_5101',
		       						dataIndex:'packingSpec'
		       				   },{
		        	        	   width:80,
		        	        	   text:$i18n.packing_unit,//单位
		        	        	   id:'packingUnit_5101',
		        	        	   align:'center',
		        	        	   dataIndex:'packingUnit'
		        	           },{
		        	        	   width:80,
		        	        	   text:$i18n.qty,//实际储位库存
		        	        	    align:'center',
		        	        	   dataIndex:'qty'
		        	           },{
		        	        	   width:80,
		        	        	   text:$i18n.demand_qty,//需补货量
		        	        	    align:'center',
		        	        	   dataIndex:'demandQty'
		        	           },{
		        	        	   width:80,
		        	        	   text:$i18n.available_qty,//可用数量
		        	        	    align:'center',
		        	        	   dataIndex:'availableQty'
		        	           },{
		        	        	   width:80,
		        	        	   text:$i18n.alert_qty,//补货警示量
		        	        	    align:'center',
		        	        	   dataIndex:'alertQty'
		        	           },{
		        	        	   width:80,
		        	        	   text:$i18n.keep_cells,//可用储位数
		        	        	    align:'center',
		        	        	   dataIndex:'keepCell'
		        	           },{
		        	        	   width:80,
		        	        	   text:$i18n.max_qtys,//最大库存量
		        	        	    align:'center',
		        	        	   dataIndex:'maxQty'
		        	           }],
		        		       dockedItems:[{
		        					xtype : 'pagingtoolbar',
		        					store:ReplenishmentStore,					
		        					dock : 'bottom',
		        					displayInfo : true
		        				}]
							},
							{
								region:'south'
						}]
			       },{
						xtype:'panel',
						title: $i18n.movelibrary,//人工移库
						id:'tabMdata_PlanMForMan5101',
						layout:'border',
					    items: [{
							xtype : 'form',
							id : 'formMdata_PlanMForMan5101',
							region : 'north',
							width:'100%',
							frame : true,
							items : [ {
								layout : {
								type : 'table',
								columns : 6
								},
								xtype : 'container',
								defaults : {
									xtype : 'textfield',
									margin : '2 2 2 0',
									labelAlign : 'right'
									//width : 180
								},
								items : [ {
									xtype : 'bdef_DefOwnerCombo',
									fieldLabel : $i18n.owner,// 委托业主
									id:'cmbOwnerForMan5101',
									displayField: 'dropValue',
									valueField: 'value',
									beforeLabelTextTpl : required,
					 	    	    store:Ext.create("cms.store.bdef.bdef_DefOwnerComboStore",
											{
												listeners:{  
												'load':function(th,records,successful,eOpts ){
													if(th.count()>0){
														Ext.getCmp('cmbOwnerForMan5101').setValue(th.getAt(0).data.value);
													}
												}
												}
							   				}),
							   				beforeLabelTextTpl : required
								}, {
									labelWidth : 90,
					 	    		fieldLabel:$i18n.owner_article_no,//业主商品编码
					 	    		id:'txtOwnerArticleNoForMan5101',
					 	    		//beforeLabelTextTpl : required,
					 	    		width:190	
								}, {
									labelWidth : 70,
									fieldLabel : $i18n.barcode,//商品条码
									id:'txtBarcodeForMan5101',
									//beforeLabelTextTpl : required,
									width : 170
								}, {
									labelWidth : 50,
									fieldLabel: $i18n.cell_no,//储位
									id:'txtCellNoForMan5101',
									//beforeLabelTextTpl : required,
									width : 150
								},{
									xtype : 'button',
									margin:'3 3 0 2',
									name:'butQueryForMan5101',
									text : $i18n.query //查询
								},{
									xtype : 'button',
									name:'butCreateForMan5101',
									margin:'3 0 0 3',
									text : $i18n.create //建单
								}]
								} ]
		
						},{
				   			  region:'center',
				        	  xtype:'grid',
				        	  id:'gridMdata_PlanMForMan5101',
				        	  store:gridMdata_PlanMForMan5101Store,
				        	  plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
				        		  clicksToEdit : 1
				        	  })],
				        	  width:'100%',
				        	  height:'100%',
				        	  columns:[{
				        	  	xtype: 'checkcolumn',
								width : 50,
								columnHeaderCheckbox: true,
								store: Ext.data.StoreManager.lookup('stock_ContentStore'),
							    sortable: false,
							    hideable: false,
							    menuDisabled: true,
								dataIndex:'checkColumn'
				        	  },{
				        	        width:120,
				        	        text:$i18n.article_no,//商品编码
				        	        dataIndex:'articleNo'
				        	  },{
				        		  	width : 100,
				        		    text : $i18n.owner_article_no,//货主商品编码
				        		    dataIndex : 'ownerArticleNo'
				        	  },{
				        	        width:180,
				        	        text:$i18n.article_name,//商品名称
				        	        dataIndex:'articleName'
				        	  },{
				        	        width:130,
				        	        text:$i18n.qty,//实际储位库存
				        	        dataIndex:'qty'
				        	  },{
				        	        width:80,
				        	        text:$i18n.available_qty,//可用数量
				        	        dataIndex:'availableQty'
				        	  },{
				        	        width:100,
				        	        text:$i18n.s_cell_no,//来源储位
				        	        dataIndex:'cellNo'
				        	  },{
				        	        width:100,
				        	        text:$i18n.a_cell_no,//目的储位
				        	        cls:'notnull',
									field: {
							            xtype: 'cdef_DefCellCombo',
							            id:'D_CellNo',
								        displayField : 'value',
								    	valueField : 'value',
								    	store:Ext.create('cms.store.cdef.cdef_DefCellComboStore',{
								    		proxy:{
												type:'ajax',
												method:'post',
												url:'mdata_PlanMAction_getCdef_DefCellCombo.action',
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
								    	allowBlank :false,
								    	//enableKeyEvents开启键盘事件监听，select事件
						    	        enableKeyEvents: true,
						    	        	listeners:{
						    	        		//监听键盘
						    	        		specialkey:function (field, e) {
						    	        			keyFunc = function(){
						    	        				if (field.getValue() != "" && (e.getKey() == e.ENTER || e.getKey() == e.TAB)) {
						    	        					var index = _myAppGlobal.getController('mdata.mdata_PlanMController').getGridSelIndex();
							    	        	           	startGridCell('gridMdata_PlanMForMan5101',index,7);
							    	        	           }
						    	        			};
						    	        			//因为先会进入specialkey所以线程休眠100毫秒
						    	        			setTimeout(keyFunc,100);
						    	        	    },
						    	        		select:function(combo, record,index){
						    	                try{
						    	                      //获取选中行
						    	                      var index = _myAppGlobal.getController('mdata.mdata_PlanMController').getGridSelIndex();
						    	                      //开启下一个跳转输入框
						    	                      startGridCell('gridMdata_PlanMForMan5101',index,7);
						    	                }catch(ex){
						    	                      Ext.MessageBox.alert("错误","数据加载失败。");
						    	                } 
						    	        	  }
						    	           }
							           },
							               dataIndex:'DCellNo'
				        	           },{
											width : 85,
											text : $i18n.planBox,//计划箱数
											dataIndex : 'planBox',
											id:'planBox5101_1',
											cls : 'notnull',
											field : {
									    		xtype : 'numberfield',
									    		minValue:0
									    	}
										},{
											width : 85,
											text : $i18n.planQmin,//计划中包装数
											dataIndex : 'planQmin',
											id:'planQmin5101_1',
											cls : 'notnull',
											field : {
									    		xtype : 'numberfield',
									    		minValue:0
									    	}
										},{
											width : 85,
											text : $i18n.planDis,//计划零散数
											dataIndex : 'planDis',
											id:'planDis5101_1',
											cls : 'notnull',
											field : {
									    		xtype : 'numberfield',
									    		minValue:0
									    	}
										}/*,{
				        	        	   width:80,
				        	        	   text:$i18n.qty1,//整件数
				        	        	   cls:'notnull',
				        	        	   dataIndex:'pkQty',
				        	        	   field : {
												xtype: 'numberfield',
												minValue:0,
												enableKeyEvents: true,
						    	        		listeners:{
						    	        			keydown: function (Text,e,ept) {
						    	        				if(e.getKey() == 13){
						    	        					var index = _myAppGlobal.getController('mdata.mdata_PlanMController').getGridSelIndex();
						    	        					startGridCell('gridMdata_PlanMForMan5101',index,8);
						    	        				}
						    	        			},
						    	  					select:function(combo, record,index){
						    	                        try{
						    	                        	//获取选中行
						    	                        	var index = _myAppGlobal.getController('mdata.mdata_PlanMController').getGridSelIndex();
						    	                        	//开启下一个跳转输入框
						    	                        	startGridCell('gridMdata_PlanMForMan5101',index,8);
						    	                        }
						    	                        catch(ex){
						    	                            Ext.MessageBox.alert("错误","数据加载失败。");
						    	                        } 
						    	        			}
						    	        		}

										   }
				        	           }*/,{
				       						width:80,
				       						text:$i18n.packingSpec,//规格
				       						id:'packingSpec_5101_1',
				       						dataIndex:'packingSpec'
				       				   },{
				        	        	   width:80,
				        	        	   text:$i18n.packing_unit,//单位
				        	        	   id:'packingUnit_5101_1',
				        	        	   align:'center',
				        	        	   dataIndex:'packingUnit'
				        	           },{
				        	        	   width:100,
				        	        	   text:$i18n.barcode,//商品条码
				        	        	   dataIndex:'barcode'
				        	           },{
				        	        	   width:80,
				        	        	   text:$i18n.packing_qty,//包装数量
				        	        	   dataIndex:'packingQty'
				        	           },/*{
				        	        	   width:80,
				        	        	   text:$i18n.quality,//品质
				        	        	   dataIndex:'quality'
				        	           },{
				        	        	   width:80,
				        	        	   text:$i18n.container_no,//内部容器号
				        	        	   id:'container_no5101_2b',
				        	        	   dataIndex:'containerNo'
				        	           },{
				        	        	   width:80,
				        	        	   text:$i18n.porduce_date,//生产日期
				        	        	   dataIndex:'produceDate'
				        	           },{
				        	        	   width:80,
				        	        	   text:$i18n.expire_date,//有效期至
				        	        	   dataIndex:'expireDate'
				        	           }*/{
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
										},{
											width:60,
											text:$i18n.lot_no,//'批号',
											//hidden:$i18n.lotNoHidden,
											dataIndex:'lotNo'
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
						xtype:'panel',
						title: $i18n.movefailList,//移库导入失败数据查询
						id:'tabMdata_movefailList5101',
						layout:'border',
					    items: [{
				   			  region:'center',
				        	  xtype:'grid',
				        	  id:'gridMdata_movefailList5101',
				        	  store:gridMdata_movefailList5101Store,
				        	  width:'100%',
				        	  height:'100%',
				        	  columns:[	{
									xtype : 'rownumberer',
									width : 30
								},{
				        		  	width : 100,
				        		    text : $i18n.owner_article_no,//货主商品编码
				        		    dataIndex : 'ownerArticleNo'
				        	  },{
		        	        	   width:80,
		        	        	   text:$i18n.packing_qty,//包装数量
		        	        	   dataIndex:'packingQty'
		        	          },{
		       						width:80,
		       						text:$i18n.packingSpec,//规格
		       						id:'packingSpec_5101_2',
		       						dataIndex:'packingSpec'
		       				   },{
		        	        	   width:80,
		        	        	   text:$i18n.packing_unit,//单位
		        	        	   id:'packingUnit_5101_2',
		        	        	   align:'center',
		        	        	   dataIndex:'packingUnit'
		        	           },{
				        	        width:80,
				        	        text:'数量',
				        	        dataIndex:'originQty'
				        	  },{
				        	        width:80,
				        	        text:$i18n.boxQty,
				        	        dataIndex:'planBox',
				        	        id:'planBox5101_3'
				        	  },{
				        	        text:$i18n.qminQty,
				        	        width:80,
				        	        dataIndex:'planQmin',
				        	        id:'planQmin5101_3'
				        	  },{
				        	        width:80,
				        	        text:$i18n.disQty,
				        	        dataIndex:'planDis',
				        	        id:'planDis5101_3'
				        	  },{
				        	        width:100,
				        	        text:$i18n.s_cell_orgNo,//来源储位机构号
				        	        dataIndex:'orgNo'
				        	  },{
				        	        width:100,
				        	        text:$i18n.s_cell_no,//来源储位
				        	        dataIndex:'SCellNo'
				        	  },
								{
									width:100,
									text:$i18n.d_cell_no,//目的储位
									dataIndex:'DCellNo'
								},
								{
									width:100,
									text:$i18n.transform_des,//备注
									dataIndex:'planRemark'
								}]
						}]
				         	
				   }
	         	]
	       },{
			/*xtype : 'panel',
			id : 'msterForm5101',*/
			region:'south'
			/*html : '<div class="view_footer" style="margin:0; padding: 8px 20px 8px 20px;width:100% ;'
					+ 'background-color:#C1D5ED; text-align: left;">'
					+ '<span><label>新增人:</label><label id="Editor5101"></label> </span> '
					+ '<span><label>&nbsp;&nbsp;&nbsp;新增时间：</label><label id="EditDate5101"></label></span>'
					+ '<span><label>&nbsp;&nbsp;&nbsp;修改人：</label><label id="Checker5101"></label> </span> '
					+ '<span><label>&nbsp;&nbsp;&nbsp;修改时间：</label><label id="Checkdate5101"></label> </span></div>'*/
		}]
});

