/**
 * 模块名称：上架回单
 * 模块编码：4701
 * 创建：周欢
 */
var idata_InStock_MStore=Ext.create('cms.store.idata.idata_InstockMStore',{
	//autoLoad:true,
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('tabPIdInfo4701').getActiveTab().id=='InstockInfoTab4701' 
					&& Ext.getCmp('gridIdata_instockMOP4701').getStore().count()>0)
		    {
				Ext.getCmp('gridIdata_instockMOP4701').getSelectionModel().select(0);
			}else if(Ext.getCmp('tabPIdInfo4701').getActiveTab().id=='InstockCheckTab4701'
					&& Ext.getCmp('gridIdata_instockMCheck4701').getStore().count()>0)
			{
				Ext.getCmp('gridIdata_instockMCheck4701').getSelectionModel().select(0);
			}
		}
	}});
Ext.define('cms.view.idata.idata_InstockUI',{
	alias:'widget.idata_InstockUI',
	title:$i18n.title4701,//进货上架回单
	id:'idata_CheckUI4701',
	width:'100%',
	layout:'border',
	extend : 'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu4',
	          'cms.view.common.cdef_DefCellCombo',
	          'cms.view.common.bdef_DefWorkerCombo',
	          'cms.view.common.remoteCombo',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.wms_DefFieldValCombo'

	          
	          ],
	items:[
	       {
	    	   xtype:'commMenuWidget4',
	    	   id:'menu4701',
	   		   region:'north'
	       },
	       {
	    	   xtype:'panel',
	    	   layout:'border',
	   		   region:'center',
	   		   items:[{
		    	   xtype : 'tabpanel',
		   		   id:'tabPIdInfo4701',
				   region:'center',
				   items:[
				          {
				        	  title : $i18n.titleD,//明细信息
						      id:'InstockInfoTab4701',
				        	  width:'100%',
						      layout:'border',
				        	  items:[{
				        				xtype : 'grid',
				        				region : 'west',
				        				width : '65%',
				        				store:idata_InStock_MStore,
				        				id:'gridIdata_instockMOP4701',
				        				columns : [ {
				        					xtype : 'rownumberer',
				        					width : 30
				        				},{
				        					width : 230,
				        					text : $i18n.instock_no,
				        					dataIndex : 'instockNo'
				        				},{
				        					width : 110,
				        					text : $i18n.dispatch_date,
				        					dataIndex : 'dispatchDate'
				        				},
				        				{
				        					width : 80,
				        					text : $i18n.status,	
				        					dataIndex : 'statusText'
				        				}]
				        			},{
				        				xtype : 'form',
				        				id:'formInstockMOP4701',
				        				layout:'column',
				        				region : 'east',
				        				frame : true,
				        				width : '35%',
				        				items:[
				        				{
				        					xtype:'fieldset',  
			        			            autoHeight:false,  
			        			            margin:'5 10 0 10%',
			        			            title:'查询条件',
			        			            defaults:{
			        				    	   	xtype:'textfield',
			        				    	   	margin:'0 4 1 4',
			        				    	   	labelAlign : 'right'
			        				       	},
			        				       	items : [{
				        				       	 xtype:'wms_DefFieldValCombo',
				        			        	 fieldLabel:'上架类型',//质量类型
				        			        	 id:'cmbQ_type4701',
				        			        	 store:Ext.create("cms.store.common.comboStore").load(
				        			       	     {
				        			       	          params:{str:"RIDATA_INSTOCK_M,LOCATE_TYPE"}
				        			       	     })
			        				       	},{
			        							xtype : 'remoteCombo',
			        						    id:'cmbInstock_noM4701',
			        							fieldLabel : $i18n.instock_no,//上架单号
			        							displayField: 'instockNo',
			        			    			valueField: 'instockNo',
			        							store:Ext.create('cms.store.idata.idata_InstockMStore',{
	    										   proxy:{
	    											   type:'ajax',
	    												method:'post',
	    												url:'idata_InstockAction_getInstockNoCombo',
	    												reader:{
	    													root:'rootList',
	    													totalProperty:'totalCount'
	    												},
	    												extraParams:{
	    													strFlag : "1"
	    												}
	    											} 
	        					   				  }),
	        					   				  listConfig: {
		        					   				   loadingText : $i18n_prompt.checking,
		        					   				   emptyText : $i18n_prompt.noFindRelationData,
		        					   		           getInnerTpl: function() {
		        					   		        	   return '{instockNo}';
		        					   		           }
	        					   		          }
			        						   }]
				        					},
				        					{
				        					   xtype:'fieldset',  
			        			               autoHeight:false,  
			        			               margin:'0 10 10 10%',
			        			               id:'formWorker4701',
			        			               title:$i18n.baseInfo,  
			        			               defaults:
			        			               {
			        				    	   		xtype:'textfield',
			        				    	   		margin:'0 4 1 4',
			        				    	   		labelAlign : 'right'
			        				       	   },
			        				       	items:[{

			        							 xtype:'bdef_DefOwnerCombo',
			        							 fieldLabel:$i18n.owner,//货主编号
			        			            	 queryMode:'local',
			        				             allowBlank:true,
			        				             id:'comOwnerNo4701',
			        				             displayField: 'dropValue',
			        							 valueField: 'value',
			        							 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',
			        							 {
			        								 proxy:{
			        										type:'ajax',
			        										method:'post',
			        										url:'idata_InstockAction_getOwnerComboList',
			        										reader:{
			        											root:'rootList',
			        											totalProperty:'totalCount'
			        										}
			        									},	 
			        								listeners:{  
			        										'load':function(th,records,successful,eOpts ){
			        											if(th.count()>0){
			        												Ext.getCmp('comOwnerNo4701').setValue(th.getAt(0).data.value);
			        												_myAppGlobal.getController('cms.controller.idata.idata_InstockController').comOwnerNo4701Select();
			        											}
			        										}
			        									}
			        			   				}),
			        				             beforeLabelTextTpl:required
			        				         
			        				       	},
				        						{
				        							xtype : 'bdef_DefWorkerCombo',
				        							fieldLabel : $i18n.instock_worker, 
				        							id : 'cmbWorkerNo4701',
				        							store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore'),
				        							beforeLabelTextTpl : required
				        						}]
				        					}]
				        		         },{
				        		        	    xtype : 'grid',
				        						id : 'gridInstockDOP4701',
				        						region:'south',
				        						height : 300,
				        						loadMask : true, // 加载时有加载的图标
				        						store : Ext.create('cms.store.idata.idata_InstockDStore',{
				        							 proxy:{
		    												type:'ajax',
		    												method:'post',
		    												url:'idata_InstockAction_getInstockDList',
		    												reader:{
		    													root:'rootList',
		    													totalProperty:'totalCount'
		    												},
		    												extraParams:{
		    													strFlag : "1"
		    												}
		    											}
				        						}),
				        						selType : 'cellmodel',
				        	    				plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
				        	    					clicksToEdit : 1,
				        	    					onSpecialKey:function(ed,field,e){
				        	    						commEnterGridStatEdit(this.grid,false,'',e.getKey());
				        	    					}
				        	    				})],
				        						columns : [
				        						{
				        					    	xtype : 'rownumberer',
				        					    	width : 25
				        						},
				        						{
				        		    	            xtype:'actioncolumn',
				        		    	            text:$i18n.split,
				        		    	            width:32,
				        		    	            items: [{
				        		    	                icon: 'system/extjs/resources/icons/fam/application_split.png',  // Use a URL in the icon config
				        		    	                tooltip: 'split'
				        		    	            }]
				        		    	        },
				        						{
				        							width : 90,
				        							text : $i18n.label_no,//标签号
				        							dataIndex : 'labelNo'
				        						},
				        						{
				        							width : 110,
				        							text : $i18n.article_no,//商品编码
				        							dataIndex : 'articleNo'
				        						},{
				        							width : 120,
				        							text : $i18n.owner_article_no,//商品编码
				        							dataIndex : 'ownerArticleNo'
				        						},
				        						{
				        							width : 180,
				        							text : $i18n.article_name,//商品名称
				        							dataIndex : 'articleName'
				        						},
				        						{
				        							width : 110,
				        							text : $i18n.barcode,//条码
				        							dataIndex : 'barcode'
				        						},
				        						{
			        							width : 80,
			        							text : $i18n.dcellNo,// 
			        							dataIndex : 'destCellNo'
			        						    },
				        						{
				        							width : 60,
				        							text : $i18n.packing_qty,//包装数
				        							dataIndex : 'packingQty'
				        						},
				        						{
				        							width : 60,
				        							text : $i18n.packing_unit,//单位
				        							id:'packingUnit4701',
				        							dataIndex : 'packingUnit'
				        						},{
				        		    				width : 60,
				        		    				text : $i18n.packingSpec,
				        		    				id:'packingSpec4701',
				        		    				dataIndex:'packingSpec'
				        		    			},
				        						{
				        							width : 70,
				        							text : $i18n.articleqty,//计划数量 
				        							dataIndex : 'articleQty'
				        						},  
				        						{
				        							width : 80,
				        							text : $i18n.real_cell_no,// 
				        							dataIndex : 'realCellNo',
				        							cls:'notnull',
				        							field: {
				        			            		xtype: 'cdef_DefCellCombo',
				        			                    id:'cmbRealCellNo4701',
				        				                displayField : 'value',
				        				    			valueField : 'value',
				        				    			store : Ext.create("cms.store.cdef.cdef_DefCellComboStore",
		        				    					{
				        									 proxy:{
				        											type:'ajax',
				        											method:'post',
				        											url:'idata_InstockAction_getCellNoComboList',
				        											reader:{
				        												root:'rootList',
				        												totalProperty:'totalCount'
				        											}
				        										}
		        				    					}),
				        				    			allowBlank :false
				        			            	}
				        						},
				        						{
				        						width : 50,
				        						text : $i18n.boxQty,//验收件数
				        						dataIndex : 'planBox', 
				        						id:'planBox4701',
				        						cls:'notnull',
				        						field: {
				        			            	xtype: 'numberfield',
				        			            	minValue:0,
				        			            	listeners:{  
				        								'change': function(obj, newValue, oldValue, eOpts) {
				        									if(newValue!=oldValue){
				        										var data = Ext.getCmp('gridInstockDOP4701').getSelectionModel()
				        											.getSelection();
				        										data[0].set('realQty', newValue * data[0].get('packingQty')+data[0].get('planQmin')*data[0].get('qminOperatePacking')+data[0].get('planDis'));
				        									}
				        								}
				        			      			}
				        			            }
				        						},{


				        						    width:50,
				        						    text:$i18n.qminQty,//中包数
				        						    dataIndex:'planQmin',
				        						    id:'planQmin4701',
				        						    cls:'notnull',
				        						    field: {
				        				            	xtype: 'numberfield',
				        				            	minValue:0,
				        				            	listeners:{  
				        									'change': function(obj, newValue, oldValue, eOpts) {
				        										if(newValue!=oldValue){
				        											var data = Ext.getCmp('gridInstockDOP4701').getSelectionModel()
				        												.getSelection();
				        											data[0].set('realQty', newValue * data[0].get('qminOperatePacking')+
				        													data[0].get('planBox')*data[0].get('packingQty')+data[0].get('planDis'));
				        										}
				        									}
				        				      			}
				        				            }
				        		    		    
				        		    		    
				        						},
				        						{
				        							width : 50,
				        							text : $i18n.disQty,//验收散数 
				        							dataIndex : 'planDis',  
				        							id:'planDis4701',
				        							cls:'notnull',
				        							field: {
				        				            	xtype: 'numberfield',
				        				            	minValue:0,
				        				            	listeners:{  
				        									'change': function(obj, newValue, oldValue, eOpts) {
				        										if(newValue!=oldValue){
				        											var data = Ext.getCmp('gridInstockDOP4701').getSelectionModel()
				        												.getSelection();
				        											data[0].set('realQty',newValue + data[0].get('planQmin')*data[0].get('qminOperatePacking')+
				        													data[0].get('planBox')*data[0].get('packingQty'));							
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
				        							width:160,
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
				        					xtype : 'panel',
				        					id : 'msterForm4701',
				        					region:'south',
				        					html : '<div class="view_footer" style="margin:0; padding: 8px 20px 8px 20px;width:100% ;'
				        						+ 'background-color:#C1D5ED; text-align: left;">'
				        						+ '<span><label>'+$i18n.addPeople+'：</label><label id="rgstName4701"></label> </span> '
				        						+ '<span><label>&nbsp;&nbsp;&nbsp;'+$i18n.addTime+'：</label><label id="rgstDate4701"></label></span>'
				        						+ '<span><label>&nbsp;&nbsp;&nbsp;'+$i18n.local_path+'：</label><label id="updtName4701"></label> </span> '
				        						+ '<span><label>&nbsp;&nbsp;&nbsp;'+$i18n.editTime+'：</label><label id="updtDate4701"></label> </span></div>'
				        				}
				        		       ]
				          },
				          {
				        	  title : $i18n.titleS,//单据查询
						      width:'100%',
						      height:'100%',
						      id:'InstockCheckTab4701',
						      layout:'border',
				        	  items:[
				        		      {
				        					xtype : 'grid',
				        					region : 'west',
				        					width : '60%',
				        					store:idata_InStock_MStore,
				        					id:'gridIdata_instockMCheck4701',
				        					columns : [ {
				        						xtype : 'rownumberer',
				        						width : 30
				        					},{
				        						width : 130,
				        						text : $i18n.instock_no,//上架单号
				        						dataIndex : 'instockNo'
				        					}, {
				        						width : 80,
				        						text : $i18n.dispatch_date,//发单日期
				        						dataIndex : 'dispatchDate'
				        					}, {
				        						width : 80,
				        						text : $i18n.instock_worker,
				        						dataIndex : 'instockWorker',
				        						hidden : true
				        					}, {
				        						width : 80,
				        						text : $i18n.instock_date,
				        						dataIndex : 'instockDate',
				        						hidden : true
				        					},{
				        						width : 80,
				        						text : $i18n.status,//状态	
				        						dataIndex : 'statusText'
				        					}],
				        					dockedItems : [{
				        						xtype : 'pagingtoolbar',
				        						store : idata_InStock_MStore,
				        						dock : 'bottom',
				        						displayInfo : true
				        					}]
				        				},{
				        					xtype : 'form',
				        					id:'formInstockMCheck4701',
				        					layout:'column',
				        					region : 'east',
				        					frame : true,
				        					width : '40%',
				        					items:[
				        					    {
				        						layout:{
				        						type : 'table',
				        						columns : 2
				        						},
				        						xtype:'container',
				        						margin:'20 0 0 0',
				        						defaults:{
				        							labelWidth : 70,
				        							margin : '5 0 5 0',
				        							labelAlign : 'right',
				        							xtype:'textfield'
				        						},
				        						items:[
				        						{
				        							xtype : 'remoteCombo',
				        							fieldLabel : $i18n.instock_no,// 上架单号 
				        							displayField: 'instockNo',
				        			    			valueField: 'instockNo',
				        							store:Ext.create('cms.store.idata.idata_InstockMStore',
		        									 {
	        										 proxy:{
	        												type:'ajax',
	        												method:'post',
	        												url:'idata_InstockAction_getInstockNoCombo',
	        												reader:{
	        													root:'rootList',
	        													totalProperty:'totalCount'
	        												},
	        												extraParams:{
	        													strFlag : "2"
	        												}
	        											}
		        					   				}),
		        					   			  listConfig: {
		        					   				   loadingText : $i18n_prompt.checking,
		        					   				   emptyText : $i18n_prompt.noFindRelationData,
		        					   		           getInnerTpl: function() {
		        					   		        	   return '{instockNo}';
		        					   		           }
	        					   		          },
				        							id : 'cmbInstock_noCheck4701'
				        						},{
				        							xtype : 'datefield',
				        							fieldLabel : $i18n.begin_date,// 开始日期
				        							id : 'dateBegin_date4701',	
				        							format : 'Y-m-d'
				        						},{
				        							xtype : 'datefield',
				        							fieldLabel : $i18n.end_time,//  结束日期
				        							id : 'dateEnd_date4701',	
				        							format : 'Y-m-d',
				        							value:new Date()
				        						},{
				        							xtype : 'button',
				        							margin:'8 0 0 100',
				        							id:'butCheckDate4701',
				        							text : $i18n.query
				        						}]
				        					}]
				        		         },{
				        		        	    xtype : 'grid',
				        						id : 'gridInstockDCheck4701',//
				        						region:'south',
				        						height : 300,
				        						loadMask : true, // 加载时有加载的图标
				        						store : Ext.create('cms.store.idata.idata_InstockDStore',{
				        							 proxy:{
		    												type:'ajax',
		    												method:'post',
		    												url:'idata_InstockAction_getInstockDList',
		    												reader:{
		    													root:'rootList',
		    													totalProperty:'totalCount'
		    												},
		    												extraParams:{
		    													strFlag : "2"
		    												}
		    											}
				        						}),
				        						selModel : {
				        							selType : 'cellmodel'
				        						},
				        						plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
				        									clicksToEdit : 1
				        								})],
				        						columns : [
							        						{
							        					    	xtype : 'rownumberer',
							        					    	width : 30
							        						},
							        						{
							        							width : 120,
							        							text : $i18n.label_no,//标签号
							        							dataIndex : 'labelNo'
							        						},
							        						{
							        							width : 120,
							        							text : $i18n.article_no,//商品编码
							        							dataIndex : 'articleNo'
							        						},{
							        							width : 120,
							        							text : $i18n.owner_article_no,//商品编码
							        							dataIndex : 'ownerArticleNo'
							        						},
							        						{
							        							width : 180,
							        							text : $i18n.article_name,//商品名称
							        							dataIndex : 'articleName'
							        						},
							        						{
							        							width : 120,
							        							text : $i18n.barcode,//条码
							        							dataIndex : 'barcode'
							        						},
							        						{
						        							width : 110,
						        							text : $i18n.dest_cell_no,// 
						        							dataIndex : 'destCellNo'
						        						    },
							        						{
							        							width : 60,
							        							text : $i18n.packing_qty,//包装数
							        							dataIndex : 'packingQty'
							        						},
							        						{
							        							width : 60,
							        							text : $i18n.packing_unit,//单位
							        							dataIndex : 'packingUnit'
							        						},
							        					    {
							        							width : 80,
							        							text : $i18n.articleqty,// 
							        							dataIndex : 'articleQty'
							        						},  
							        						{
							        							width : 110,
							        							text : $i18n.real_cell_no,// 
							        							dataIndex : 'realCellNo'							 
							        						},
							        						{
								        						width : 50,
								        						text : $i18n.boxQty,//验收件数
								        						dataIndex : 'planBox', 
								        						id:'planBox4701_2',
								        					},{
							        						    width:50,
							        						    text:$i18n.qminQty,//中包数
							        						    dataIndex:'planQmin',
							        						    id:'planQmin4701_2',
								        					},{
							        							width : 50,
							        							text : $i18n.disQty,//验收散数 
							        							dataIndex : 'planDis',  
							        							id:'planDis4701_2',
								        					},
							        						{
							        							width : 100,
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
							        							width : 100,
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
							        							width:100,
							        							text:$i18n.quality,//'品质',
							        							hidden:$i18n.qualityHidden,
							        							dataIndex:'quality'
							        						},
							        						{
							        							width:160,
							        							text:$i18n.lot_no,//'批号',
							        							hidden:$i18n.lotNoHidden,
							        							dataIndex:'lotNo'
							        						},
							        						{
							        							width:100,
							        							text:$i18n.reserved1,//'预留字段1',
							        							hidden:$i18n.rsvBatch1Hidden,
							        							dataIndex:'rsvBatch1'
							        						},
							        						{
							        							width:100,
							        							text:$i18n.reserved2,//'预留字段2',
							        							hidden:$i18n.rsvBatch2Hidden,
							        							dataIndex:'rsvBatch2'
							        						},
							        						{
							        							width:100,
							        							text:$i18n.reserved3,//'预留字段3',
							        							hidden:$i18n.rsvBatch3Hidden,
							        							dataIndex:'rsvBatch3'
							        						},
							        						{
							        							width:100,
							        							text:$i18n.reserved4,//'预留字段4',
							        							hidden:$i18n.rsvBatch4Hidden,
							        							dataIndex:'rsvBatch4'
							        						},
							        						{
							        							width:100,
							        							text:$i18n.reserved5,//'预留字段5',
							        							hidden:$i18n.rsvBatch5Hidden,
							        							dataIndex:'rsvBatch5'
							        						},
							        						{
							        							width:100,
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
							        							width:100,
							        							text:$i18n.reserved8,//'预留字段8',
							        							hidden:$i18n.rsvBatch8Hidden,
							        							dataIndex:'rsvBatch8'
							        						}]
				        			   } ]
				          } ]   
		       }]
	       }
	        ]   
});