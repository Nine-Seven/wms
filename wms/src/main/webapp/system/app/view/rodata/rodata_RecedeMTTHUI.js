/**
 * 模块名称：手建退厂单(服装行业，目前用于天天惠)
 * 模块编码：7102
 * 创建：chensr
 */
 var gridRodata_RecedeM7102store=Ext.create('cms.store.rodata.rodata_RecedeMTTHStore');
 var gridArticleInfo7102store=Ext.create('cms.store.stock.stock_ContentStore',{
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
				arrayObj[3]='budegtQty';
				countList('gridArticleInfo7102',arrayObj,'labelNo');
		}
	}
});
Ext.define('cms.view.rodata.rodata_RecedeMTTHUI',{
	alias:'widget.rodata_RecedeMTTHUI',
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
	          'cms.view.common.wms_DefFieldValCombo',
	          'cms.view.common.cdef_DefCellCombo'
	          ],
	items:[
	       {
	    	   xtype:'tabpanel',
	    	   id:'tabPId7102',
	           region:'center',
	           items : [
	                    {
	                      title: '单据列表',//主档
	        			  id:'rodata_RecedeMPanel7102',
	        			  layout:'border',
	        			  items: [
	        			  {
						  xtype : 'commMenuWidget3',
						  id:'commMenuWidget7102',
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
								name : 'detailSend',
								hidden:true
							}]
					      },{
			         			xtype:'panel',
			         			region:'north',
			         			height: 65,
			         			layout: {
			         			    type: 'table',
			         		        columns: 4
			         			},
			         			  defaults : {
			         					xtype : 'textfield',
			         					margin : '3 3 3 3',
			         					labelAlign:'right',
			         					allowBlank: true,
			         					width : 270,
			         					labelWidth : 80
			         				},
			         			items:[{
				    	         	 xtype:'wms_DefFieldValCombo',
				    	        	 fieldLabel:$i18n.recede_type,//退货单类型
				    	        	 id:'cmbRecede_type7102',
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
			     								if(Ext.getCmp('cmbRecede_type7102').getStore().count()>0){
			     									Ext.getCmp('cmbRecede_type7102').setValue(Ext.getCmp('cmbRecede_type7102').getStore().getAt(0).data.value);
			    									_myAppGlobal.getController('cms.controller.rodata.rodata_RecedeMTTHController').selectRecede_type7102(Ext.getCmp('cmbRecede_type7102'));

			     								}
			     							}
			     						},
			     						autoLoad:true
				    	        	 }),
				    	        	 dataIndex:'recedeType'
				    	         },{
			         				fieldLabel : $i18n.suppliers,   //供应商
			         				id:'suppliers7102',
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
				     						}
				     				    }).load(),
				     				    displayField : 'dropValue',
				     				    valueField : 'value'
			         			},{
			        				xtype : 'datefield',
			        				fieldLabel : $i18n.date,//日期
			        				id : 'date_recede7102',
			        				format : 'Y-m-d',
			        			},{
			         				fieldLabel : '定位次数',   //定位次数
			         				id:'locateTime7102',
			     					xtype:'wms_DefFieldValCombo',
			     					 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				     			    	    proxy:{
				     							type:'ajax',
				     							async:false,
				     							method:'post',
				     							url:'rodata_RecedeMTTHAction_getLocateTime',
				     							reader:{
				     								root:'rootList',
				     								totalProperty:'totalCount'
				     							}
				     						}
				     				    }).load(),				     				    
				     				    displayField : 'dropValue',
				     				    valueField : 'value'			         			
			         			},{
			         				xtype:'textfield',
			    		  			fieldLabel:'去向储位',//储位
			    		  			id:'cell7102'
			         			},{
			         				xtype: 'button',
			         				text:$i18n.locate_start,
			        				id:'locate7102'	,
			        				width : 120,
			        				algin:'left'
			         			},{
			         				xtype: 'button',
			         				text:'退货确认',
			        				id:'review7102'	,
			        				width : 120,
			        				algin:'left'
			         			}]
			         		},{
							region:'center',
				        	xtype:'grid',
				        	store:gridRodata_RecedeM7102store,
				        	id:'gridRodata_RecedeM7102',
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
			        	        	 id:'recede_no7102_1',
			        	        	 dataIndex:'recedeNo'
			        	        	 
			        	         },{
			        	        	 width:160,
			        	        	 text:$i18n.po_nos,//原退货单号
			        	        	 id:'po_no7102',
			        	        	 dataIndex:'poNo'
			        	         },{
			        	        	 width:80,
			        	        	 text:$i18n.recede_type,//退货单类型
			        	        	 id:'recede_type7102',
			        	        	 dataIndex:'recedeType'
			        	         },{
			        	        	 width:140,
			        	        	 text:$i18n.recede_date,//退货发单日期
			        	        	 id:'recede_date7102',
			        	        	 dataIndex:'recedeDate'
			        	         },{		        	        	 
			        	        	 width:100,
			        	        	 text:'定位次数',
			        	        	 id:'locateTimes7102',
			        	        	 dataIndex:'locateTimes'
			        	         },{
			        	        	 width:100,
			        	        	 text:$i18n.status2,//操作状态
			        	        	 id:'status7102',
			        	        	 dataIndex:'statusText'
			        	         }]						  
						},{
							xtype: 'grid',
							id : 'gridArticleInfo7102',
							region:'south',
					        margins: '5 0 0 5',
					        height:200,
					        store:gridArticleInfo7102store,
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
								width : 80,
								text : $i18n.articleqty,//计划数量
								dataIndex : 'recedeQty'
							},{
								width : 80,
								text : $i18n.available_qty,//可用数量
								dataIndex : 'availableQty'
							},{
								width : 80,
								text : $i18n.no_enough_qty,//缺量
								dataIndex : 'noEnoughQty'
							},{
								 width:180,
		        	        	 text:'已扫描数量',
		        	        	 id:'budgetQty7102_3',
		        	        	            
		        	        	 dataIndex:'budegtQty'									
							},{
								width : 80,
								text : '包装数量',
								dataIndex : 'packingQty'
								
							},{
		        	        	 width:80,
		        	        	 text:$i18n.packingUnit,//包装单位
		        	        	 dataIndex:'packingUnit',
		        	        	 id:'packingUnit7102_1'
		        	         },{
								width : 85,
								text : $i18n.packingSpec,//规格
								dataIndex : 'packingSpec',
								id:'packingSpec7102_1'
							}]
		                }]
	                },
	                {
	                   title: '单据明细',//明细
					   itemId:'tabPId7102_2',
					   layout:'border',
					   items: [{
			    	        xtype:'commMenuWidget',
			    	        id:'menu7102',
			    	        region:'north'
			    	    },{
						xtype : 'form',
						id : 'fromRodata_RecedeM7102x',
						store:gridRodata_RecedeM7102store,
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
				           	     id:'txtRecede_no7102_2',
				           	     allowBlank:false,
				           	     readOnly:true
				             },
				             {
					        	 xtype:'bdef_DefOwnerCombo',
				            	 fieldLabel:$i18n.owner_no,//委托业主编号
					             allowBlank:false,
					             id:'cmbOwner_no7102_2',
					             store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
					             beforeLabelTextTpl:required
					         }, 
					         {
			    	         	 xtype:'wms_DefFieldValCombo',
			    	        	 fieldLabel:$i18n.recede_type,//退货单类型
			    	        	 id:'cmbRecede_type7102_2',
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
					             id:'txtPo_no7102_2',
					             beforeLabelTextTpl:required,
					             dataIndex:'poNo'
					         },
					          {
					        	 xtype:'bdef_DefSupplierCombo',
				            	 fieldLabel:$i18n.suppliers,//供应商
					             allowBlank:false,
					             id:'cmbSuppliers7102_2',
					             store:Ext.create('cms.store.bdef.bdef_DefSupplierComboStore'),
					             beforeLabelTextTpl:required,
					             dataIndex:'supplierNo'
					         },
				             {
				            	 fieldLabel:$i18n.po_types,//原退货单别
					             allowBlank:false,
					             id:'txtPo_type7102_2',
					             hidden:true,
					             dataIndex:'poType'
					         },
				             {
					        	 xtype:'datefield',
				            	 fieldLabel:$i18n.recede_date,//退货发单日期
					             allowBlank:false,
					             id:'dateRecede_date7102_2',
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
					             id:'dateRequestDate27102_2',
					             beforeLabelTextTpl:required,
					             dataIndex:'requestDate'
				         },{
							 	xtype:'wms_DefFieldValCombo',
				       	      	fieldLabel : $i18n.orgNo,// 机构代码
								id : 'orgNo7102',	
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
									id : 'classType7102',	
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
									id : 'takeType7102',	
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
					             id:'recede_remark7102_2',
					             dataIndex:'recedeRemark'
					         }]
				 			}]
						},
						{
							region:'center',
				        	xtype:'grid',
				        	id:'gridRodata_RecedeD7102',
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
		    	        					 id:'cmbArticle_no7102_3',
		    	        					 store:Ext.create('cms.store.bdef.bdef_DefArticleComboStore'),
		    	        					 allowBlank : false
	    	        				     }
				        	         },{
				        	        	 width:120,
				        	        	 text:$i18n.owner_article_no,//业主商品编码
				        	        	 id:'owner_cmbArticle_no7102_3',
				        	        	 dataIndex:'ownerArticleNo'
				        	         },{
				        	        	 width:120,
				        	        	 text:$i18n.barcode,//商品条码
				        	        	 id:'txtBarcode7102_3',
				        	        	 dataIndex:'barcode'
				        	         },{
				        	        	 width:180,
				        	        	 text:$i18n.article_name,//商品名称
				        	        	 id:'txtArticle_name7102_3',
				        	        	 dataIndex:'articleName'
				        	         },
				        	         {
				        	        	 width:80,
				        	        	 text:$i18n.packing_qty,//包装数量
				        	        	 cls:'notnull',
				        	        	 field:{
											 xtype : 'bdef_PackingQtyCombo',
											 id : 'cmbPacking_qty7102_3',
											 store:Ext.create('cms.store.bdef.bdef_PackingQtyComboStore'),
											 editable:false,
											 allowBlank :false
										 },
				        	        	 dataIndex:'packingQty'
				        	         },{
				        	        	 width:80,
				        	        	 text:$i18n.packingUnit,//包装单位
				        	        	 dataIndex:'packingUnit',
				        	        	 id:'packingUnit7102'
				        	         }/*,{
										width : 100,
										text : '实际数量',//实际箱数
										cls:'notnull',
										id:'recedeWholeNum7102_3',
										field : {
				    						xtype : 'numberfield',
				    						minValue:0
				    				    },
				    				    dataIndex:'recedeWholeNum'
									}*/,{
										width : 85,
										text : $i18n.packingSpec,//规格
										dataIndex : 'packingSpec',
										id:'packingSpec7102'
									},{
										width : 85,
										text : $i18n.planBox,//计划箱数
										dataIndex : 'planBox',
										//hidden:true,
										id:'planBox7102',
										cls : 'notnull',
										field : {
								    		xtype : 'numberfield',
								    		minValue:0
								    	}
									},{
										width : 85,
										text : $i18n.planQmin,//计划中包数
										dataIndex : 'planQmin',
										id:'planQmin7102',
										cls : 'notnull',
										field : {
								    		xtype : 'numberfield',
								    		minValue:0
								    	}
									},{
										width : 85,
										text : $i18n.planDis,//计划散数
										dataIndex : 'planDis',
										id:'planDis7102',
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
	        						id:'commMenuWidget67102',
	        				    	region:'north'
	        					}]
							}
					      ]
                    }]
	       }]          
});