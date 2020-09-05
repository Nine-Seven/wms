/**
 * 模块名称：退厂手建单（标准版本）
 * 模块编码：7101
 * 创建：hkl
 */
 var gridRodata_RecedeM7101store=Ext.create('cms.store.rodata.rodata_RecedeMTTHStore');
 var gridArticleInfo7101store=Ext.create('cms.store.stock.stock_ContentStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'rodata_RecedeMTTHAction_getRodateRecedeList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	},
	listeners:{
		'load':function(th,records,successful,eOpts ){
			var arrayObj = new Array();
				arrayObj[0]='recedeQty';
				arrayObj[1]='availableQty';
				arrayObj[2]='noEnoughQty';
				countList('gridArticleInfo7101',arrayObj,'labelNo');
				
			
		}
	}
});
Ext.define('cms.view.rodata.rodata_RecedeMUI',{
	alias:'widget.rodata_RecedeMUI',
	title:$i18n.title7101,//手建退厂单
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
			  'cms.view.common.commMenu',
			  'cms.view.common.bdef_DefSupplierCombo',
	          'cms.view.common.commMenu3',
	          'cms.view.common.bdef_DefArticleCombo',
	          'cms.view.common.bdef_PackingQtyCombo',
	          'cms.view.common.commMenu6',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.wms_DefFieldValCombo'
	          ],
	items:[
	       {
	    	   xtype:'tabpanel',
	    	   id:'tabPId7101',
	           region:'center',
	           items : [
	                    {
	                      title: '单据列表',//主档
	        			  id:'rodata_RecedeMPanel7101',
	        			  layout:'border',
	        			  items: [
	        			  {
						  xtype : 'commMenuWidget3',
						  id:'commMenuWidget7101',
						  region:'north',
						  items : [ {
								text : '刷新',
								iconCls : 'refresh',
								name : 'refresh',
							},{
								text : '导入',
								icon: 'system/extjs/resources/icons/fam/application_split.png',
								name : 'upload'
							},{
								text : '定位',
								iconCls : 'send',
								name : 'detailSend'
							},{					//7-14添加
								text : $i18n.cancel,
								iconCls : 'undo',
								name : 'undoOrder'
							}]
					      },{
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
				    	         	 xtype:'wms_DefFieldValCombo',
				    	        	 fieldLabel:$i18n.recede_type,//退货单类型
				    	        	 id:'cmbRecede_type7101',
				    	        	store:Ext.create('cms.store.common.comboStore',{
				    	        	    proxy:{
				     							type:'ajax',
				     							method:'post',
				     							url:'rodata_RecedeMTTHAction_getRecede_type',
				     							reader:{
				     								root:'rootList',
				     								totalProperty:'totalCount'
				     							}
				     						},
			     				    	listeners:{  
			     							'load':function(th,records,successful,eOpts ){
			     								if(Ext.getCmp('cmbRecede_type7101').getStore().count()>0){
			     									Ext.getCmp('cmbRecede_type7101').setValue(Ext.getCmp('cmbRecede_type7101').getStore().getAt(0).data.value);
			    									_myAppGlobal.getController('cms.controller.rodata.rodata_RecedeMController').selectRecede_type7101(Ext.getCmp('cmbRecede_type7101'));

			     								}
			     							}
			     						},
			     						autoLoad:true
				    	        	 }),
				    	        	 dataIndex:'recedeType'
				    	         },{
			         				fieldLabel : $i18n.suppliers,   //供应商
			         				id:'suppliers7101',
			     					xtype:'wms_DefFieldValCombo',
			     					 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				     			    	    proxy:{
				     							type:'ajax',
				     							async:false,
				     							method:'post',
				     							url:'rodata_RecedeMTTHAction_getSupplierNo',
				     							reader:{
				     								root:'rootList',
				     								totalProperty:'totalCount'
				     							}
				     						}/*,
				     				    	listeners:{  
				     							'load':function(th,records,successful,eOpts ){
				     								if(Ext.getCmp('suppliers7101').getStore().count()>0){
				     									Ext.getCmp('suppliers7101').setValue(Ext.getCmp('suppliers7101').getStore().getAt(0).data.value);
				     								}
				     							}
				     						}*/
				     				    }).load(),
				     				    
				     				    displayField : 'dropValue',
				     				    valueField : 'value'
			         			},{
			        				xtype : 'datefield',
			        				fieldLabel : $i18n.date,//日期
			        				id : 'date_recede7101',
			        				format : 'Y-m-d',
			        				}]
			         		},{
							region:'center',
				        	xtype:'grid',
				        	store:gridRodata_RecedeM7101store,
				        	id:'gridRodata_RecedeM7101',
				        	width:'100%',
				        	multiSelect: true,  
				    	    selModel: {  
				            	selType:'checkboxmodel'  
				    	    },
				        	columns:[{
				    	       	  xtype:'rownumberer',
				    	       	  width:30 
				    	         },{
			        	        	 width:160,
			        	        	 text:$i18n.suppliers,//供应商
			        	        	 dataIndex:'supplierName'
			        	         },{
			        	        	 width:160,
			        	        	 text:$i18n.wmpono,//退货单号
			        	        	 id:'recede_no7101_1',
			        	        	 dataIndex:'recedeNo'
			        	        	 
			        	         },{
			        	        	 width:160,
			        	        	 text:$i18n.po_nos,//原退货单号
			        	        	 id:'po_no7101',
			        	        	 dataIndex:'poNo'
			        	         },{
			        	        	 width:80,
			        	        	 text:$i18n.recede_type,//退货单类型
			        	        	 id:'recede_type7101',
			        	        	 dataIndex:'recedeType'
			        	         },{
			        	        	 width:140,
			        	        	 text:$i18n.recede_date,//退货发单日期
			        	        	 id:'recede_date7101',
			        	        	 dataIndex:'recedeDate'
			        	         },{
			        	        	 width:100,
			        	        	 text:$i18n.status2,//操作状态
			        	        	 id:'status7101',
			        	        	 dataIndex:'statusText'
			        	         },{
			        	        	 width:100,				//8-16添加
			        	        	 text:'波次号',//操作状态
			        	        	 id:'waveNo7101',
			        	        	 dataIndex:'waveNo',
			        	        	 hidden:true
			        	         }]
			        	        
							  /*   dockedItems : [{
				      					xtype : 'pagingtoolbar',
				      					store:gridRodata_RecedeM7101store,
				      					dock : 'bottom',
				      					displayInfo : true
				      			  }]*/
						},{
							xtype: 'grid',
							id : 'gridArticleInfo7101',
							region:'south',
					        margins: '5 0 0 5',
					        height:200,
					        store:gridArticleInfo7101store,
					        columns : [ {
								xtype : 'rownumberer',
								width : 30
							},{
								width : 120,
								text : $i18n.wmpono,
								dataIndex : 'labelNo'
							},{
								width : 120,
								text : $i18n.article_no,
								dataIndex : 'articleNo'
							},{
								width : 100,
								text : $i18n.owner_article_no,//货主商品编码
								dataIndex : 'ownerArticleNo'
							},{
								width : 170,
								text : $i18n.article_name,
								dataIndex : 'articleName'
							},
							{
								width : 85,
								text : $i18n.planBox,//计划箱数
								dataIndex : 'planBox',
								id:'planBox7101_2'
							},{
								width : 85,
								text : $i18n.planQmin,//计划中包数
								dataIndex : 'planQmin',
								id:'planQmin7101_2'
							},{
								width : 85,
								text : $i18n.planDis,//计划散数
								dataIndex : 'planDis',
								id:'planDis7101_2'
							},{
								width : 80,
								text : $i18n.articleqty,//计划总量
								dataIndex : 'recedeQty'
							},{
								width : 85,
								text : '可用箱数',//可用箱数
								dataIndex : 'realBox',
								id:'realBox7101_2'
							},{
								width : 85,
								text : '可用包数',//可用包数
								dataIndex : 'realQmin',
								id:'realQmin7101_2'
							},{
								width : 85,
								text : '可用散数',//可用散数
								dataIndex : 'realDis',
								id:'realDis7101_2'
							},{
								width : 80,
								text : $i18n.available_qty,//可用数量
								dataIndex : 'availableQty'
							},{
								width : 80,
								text : $i18n.no_enough_qty,//缺量
								dataIndex : 'noEnoughQty'
							},{
								width : 80,
								text : '包装数量',
								dataIndex : 'packingQty'
							},{
		        	        	 width:80,
		        	        	 text:$i18n.packingUnit,//包装单位
		        	        	 dataIndex:'packingUnit',
		        	        	 id:'packingUnit7101_1'
		        	         },{
								width : 85,
								text : $i18n.packingSpec,//规格
								dataIndex : 'packingSpec',
								id:'packingSpec7101_1'
							}]
		                }]
	                },
	                {
	                   title: '单据明细',//明细
					   itemId:'tabPId7101_2',
					   layout:'border',
					   items: [{
			    	        xtype:'commMenuWidget',
			    	        id:'menu7101',
			    	        region:'north'
			    	    },{
						xtype : 'form',
						id : 'fromRodata_RecedeM7101x',
						store:gridRodata_RecedeM7101store,
						region : 'north',
						layout:'column',
						frame : true,
						items : [ {
							layout : {
							type : 'table',
							columns : 3
							},
							xtype : 'container',
							defaults : {
								xtype:'textfield',
								labelWidth : 90,
								margin : '2 2 4 2',
								labelAlign : 'right'
							},
					items : [
					         {
				           	     fieldLabel:$i18n.wmpono,//退货单号
				           	     id:'txtRecede_no7101_2',
				           	     allowBlank:false,
				           	     readOnly:true
				             },
				             {
					        	 xtype:'bdef_DefOwnerCombo',
				            	 fieldLabel:$i18n.owner_no,//委托业主编号
					             allowBlank:false,
					             id:'cmbOwner_no7101_2',
					             store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
					             beforeLabelTextTpl:required
					         }, 
					         {
			    	         	 xtype:'wms_DefFieldValCombo',
			    	        	 fieldLabel:$i18n.recede_type,//退货单类型
			    	        	 id:'cmbRecede_type7101_2',
			    	        	 beforeLabelTextTpl:required,
			    	        	 store:Ext.create("cms.store.common.comboStore").load(
					       	     {
					       	          params:{str:"RODATA_RECEDE_M,RECEDE_TYPE"}
					       	    }),
			    	        	 dataIndex:'recedeType'
			    	         },
				             {
				            	 fieldLabel:$i18n.po_nos,//原退货单号
					             allowBlank:false,
					             id:'txtPo_no7101_2',
					             beforeLabelTextTpl:required,
					             dataIndex:'poNo'
					         },
					          {
					        	 xtype:'bdef_DefSupplierCombo',
				            	 fieldLabel:$i18n.suppliers,//供应商
					             allowBlank:false,
					             id:'cmbSuppliers7101_2',
					             store:Ext.create('cms.store.bdef.bdef_DefSupplierComboStore'),
					             beforeLabelTextTpl:required,
					             dataIndex:'supplierNo'
					         },
				             {
				            	 fieldLabel:$i18n.po_types,//原退货单别
					             allowBlank:false,
					             id:'txtPo_type7101_2',
					             hidden:true,
					             dataIndex:'poType'
					         },
				             {
					        	 xtype:'datefield',
				            	 fieldLabel:$i18n.recede_date,//退货发单日期
					             allowBlank:false,
					             id:'dateRecede_date7101_2',
					             format : 'Y-m-d',
					             beforeLabelTextTpl:required,
					             listeners:{  
										'select': function(field,value,eOpts ) {
											console.log('df');
										},
									renderer:function(value){   
								    if(value instanceof Date){   				 
								        return Ext.Date.format(value,'Y-m-d');   
								    }else{				        
								        return value;   
								    }  
								}
					      		},
					             dataIndex:'recedeDate'
					         },
				             {
					        	 xtype:'datefield',
				            	 fieldLabel:$i18n.request_date2,//预定退货日期
				            	 format : 'Y-m-d',
					             allowBlank:false,
					             id:'dateRequestDate27101_2',
					             beforeLabelTextTpl:required,
					             dataIndex:'requestDate'
				         },{
							 	xtype:'wms_DefFieldValCombo',
				       	      	fieldLabel : $i18n.orgNo,// 机构代码
								id : 'orgNo7101',	
					       	    //editable:false,
					       	    readOnly:true,
					       	    store:Ext.create("cms.store.common.comboStore").load(
					       	    {
					       	         params:{str:"N,ORG_NO"}
					       	    }),
					       	    allowBlank : false,
					       	    beforeLabelTextTpl : required
								},{
								 	xtype:'wms_DefFieldValCombo',
					       	      	fieldLabel : $i18n.classType,//退货类型
									id : 'classType7101',	
						       	    readOnly:true,
						       	    store:Ext.create("cms.store.common.comboStore").load(
						       	    {
						       	         params:{str:"N,CLASS_TYPE"}
						       	    }),
						       	    allowBlank : false,
						       	    beforeLabelTextTpl : required
									},{
									 	xtype:'wms_DefFieldValCombo',
						       	      	fieldLabel : $i18n.take_type,// 提货类型
										id : 'takeType7101',	
							       	    store:Ext.create("cms.store.common.comboStore").load(
							       	    {
							       	         params:{str:"N,TAKE_TYPE"}
							       	    }),
							       	    allowBlank : false,
							       	    readOnly:true,
							       	    beforeLabelTextTpl : required
									},{
				            	 fieldLabel:$i18n.recede_remark,//退货单备注
					             allowBlank:true,
					             id:'recede_remark7101_2',
					             dataIndex:'recedeRemark'
					         }]
				 			}]
						},
						{
							region:'center',
				        	xtype:'grid',
				        	id:'gridRodata_RecedeD7101',
				        	store:Ext.create('cms.store.rodata.rodata_RecedeDStore'),
				        	width:'100%',
	    	        		selModel : {
								selType : 'cellmodel'
							},
							plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
								clicksToEdit : 1,
								onSpecialKey:function(ed,field,e){
								commEnterGridStatEdit(this.grid,true,'cms.controller.rodata.rodata_RecedeMController',e.getKey());
								}
							})],
				        	columns:[
				        	         {
				        	        	 xtype:'rownumberer',
				        	        	 width:30
				        	         },{
				        	        	 width:120,
				        	        	 text:$i18n.article_no,//商品编码
				        	        	 cls:'notnull',
				        	        	 dataIndex:'articleNo',
				        	        	 field : {
					        	        	 displayField : 'value',
											 valueField : 'value',
		    	        					 xtype : 'bdef_DefArticleCombo',
		    	        					 id:'cmbArticle_no7101_3',
		    	        					 store:Ext.create('cms.store.bdef.bdef_DefArticleComboStore'),
		    	        					 allowBlank : false
	    	        				     }
				        	         },{
				        	        	 width:120,
				        	        	 text:$i18n.owner_article_no,//业主商品编码
				        	        	 id:'owner_cmbArticle_no7101_3',
				        	        	 dataIndex:'ownerArticleNo'
				        	         },{
				        	        	 width:120,
				        	        	 text:$i18n.barcode,//商品条码
				        	        	 id:'txtBarcode7101_3',
				        	        	 dataIndex:'barcode'
				        	         },{
				        	        	 width:180,
				        	        	 text:$i18n.article_name,//商品名称
				        	        	 id:'txtArticle_name7101_3',
				        	        	 dataIndex:'articleName'
				        	         },
				        	         {
				        	        	 width:80,
				        	        	 text:$i18n.packing_qty,//包装数量
				        	        	 cls:'notnull',
				        	        	 field:{
											 xtype : 'bdef_PackingQtyCombo',
											 id : 'cmbPacking_qty7101_3',
											 store:Ext.create('cms.store.bdef.bdef_PackingQtyComboStore'),
											 editable:false,
											 allowBlank :false
										 },
				        	        	 dataIndex:'packingQty'
				        	         },{
				        	        	 width:80,
				        	        	 text:$i18n.packingUnit,//包装单位
				        	        	 dataIndex:'packingUnit',
				        	        	 id:'packingUnit7101'
				        	         },/*{
										width : 100,
										text : '实际数量',//实际箱数
										cls:'notnull',
										id:'recedeWholeNum7101_3',
										field : {
				    						xtype : 'numberfield',
				    						minValue:0
				    				    },
				    				    dataIndex:'recedeWholeNum'
									},*/{
											width : 85,
											text : $i18n.packingSpec,//规格
											dataIndex : 'packingSpec',
											id:'packingSpec7101'
										},{
											width : 85,
											text : $i18n.planBox,//计划箱数
											dataIndex : 'planBox',
											//hidden:true,
											id:'planBox7101',
											cls : 'notnull',
											field : {
									    		xtype : 'numberfield',
									    		minValue:0
									    	}
										},{
											width : 85,
											text : $i18n.planQmin,//计划中包数
											dataIndex : 'planQmin',
											id:'planQmin7101',
											cls : 'notnull',
											field : {
									    		xtype : 'numberfield',
									    		minValue:0
									    	}
										},{
											width : 85,
											text : $i18n.planDis,//计划散数
											dataIndex : 'planDis',
											id:'planDis7101',
											cls : 'notnull',
											field : {
									    		xtype : 'numberfield',
									    		minValue:0
									    	}
										},{
										width : 50,
										text:$i18n.unit_cost,//单价
										dataIndex : 'unitCost',
										cls : 'notnull',
										field : {
					    	        		xtype : 'numberfield',
					    	        		minValue:0
					    	        	}
									}],
								     dockedItems : [{
	        						xtype:'commMenuWidget6',
	        						id:'commMenuWidget67101',
	        				    	region:'north'
	        					}]
							}
					      ]
                    }]
	       }]          
});