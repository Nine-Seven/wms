/**
 * 模块名称：库存调账手建单
 * 模块编码：D201
 * 创建：hcx
 */
var stock_PlanMStore=Ext.create('cms.store.stock.stock_PlanMStore',{
	autoLoad:true,
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('stockPlanMGridD201').getStore().count()>0){
				Ext.getCmp('stockPlanMGridD201').getSelectionModel().select(0);
			}
		}
	}
});
var gridArticleInfoD201store=Ext.create('cms.store.stock.stock_ContentStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'stock_PlanImportAction_getStockPlanArticleList.action',
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
				countList('gridArticleInfoD201',arrayObj,'articleNo');
		}
	}
});
Ext.define('cms.view.stock.stock_PlanImportUI',{
	alias:'widget.stock_PlanImportUI',
	title:$i18n.titleD201,//库存调账手建单
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
			  'cms.view.common.commMenu',
	          'cms.view.common.commMenu3',
	          'cms.view.common.commMenu6',
		        'cms.view.common.remoteCombo',
	          'cms.view.common.bdef_DefArticleCombo',
	          'cms.view.common.bdef_DefCustCombo',
	          'cms.view.common.bdef_PackingQtyCombo',
	          'cms.view.common.wms_DefFieldValCombo',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.cdef_DefCellCombo'
	          ],
	items:[
    {
	    xtype:'tabpanel',
	    id:'tabPIdD201000',
	    region:'center',
	    items:[
		{
        	title: $i18n.titleM,//主档
		    id:'tabPidD201',
		    itemId:'tabPidD201i',
		    layout:'border',
		    items: [
		    {
	   	        xtype:'commMenuWidget3',
	   	        items: [{
					    text : '查找',
					    iconCls : 'query',
					    name : 'detailQuery'
				   },{
						text : $i18n.locate_start, //定位  update by sunl 2016年8月1日 把定位按钮移到上面
						iconCls : 'send',
						id : 'sendD201'
					},/*{
					   text : '打印',
					   iconCls : 'print',
					   name : 'detailPrint'
				 },*/{
					   text : '取消订单',
					   iconCls : 'undo',
					   id : 'undoOrderD201'
				 }],
	            region:'north'
   	        },{
   		   		xtype : 'form',
   		   		region : 'north',
   		   		layout:'column',
   		   		border:false,
   		   		width:'100%',
   		   		frame : true,
   		   		items:[
   		   		{
   					xtype : 'radiogroup',
   					id : 'rdoCheckTypeD201',
   					margins: '0 0 0 10',
   					fieldLabel :'是否打印调账单',
   					width : 320,
   					columns : 2,
   					items : [
   			        {
   						boxLabel : $i18n.no_print,//不打印
   						name : 'rd',
   						inputValue : '0'
   					},
   					{
   						boxLabel : $i18n.print,//打印
   						name : 'rd',
   						inputValue : '1',
   						checked:true
   					}
   					]
   	   		   }]
   		   	
   	        },{
   		        region:'north',
          		xtype:'grid',
          		id:'stockPlanMGridD201',
          		height: '50%',
          		store:stock_PlanMStore,
          		columns:[
          		{
              		xtype:'rownumberer',
        	  		width:30
          		},{
        	  		width:200,
        	 		 text:$i18n.po_no2,//调账单号
        	  		dataIndex:'planNo'
          		},{
        	  		width:130,
        	  		text:$i18n.stockPoNo,//原调帐单号
        	  		dataIndex:'poNo'
          		},{
        	  		width:130,
        	  		text:$i18n.planType,//调整类型
        	 		 dataIndex:'planTypeText'
          		},{
        	  		width:130,
        	  		text:$i18n.planDate,//调整日期
        	  		dataIndex:'planDate'
          		},{
        	 		 width:200,
        	  		text:$i18n.orgNo,//机构代码
        	  		dataIndex:'orgNoText'
                },
    			{
    				width : 130,
    				text : $i18n.status,//状态
    				dataIndex:'statusText'
    			}],
           		dockedItems:[{
        			xtype : 'pagingtoolbar',
        			dock : 'bottom',
        			store:stock_PlanMStore,
        			displayInfo : true
          		}]
    		},{

				xtype: 'grid',
				id : 'gridArticleInfoD201',
				region:'center',
		        margins: '5 0 0 5',
		        height:'40%',
		        store:gridArticleInfoD201store,
		        columns : [ {
					xtype : 'rownumberer',
					width : 30
				},{
					width : 120,
					text : $i18n.article_no,
					dataIndex : 'articleNo'
				},{
					width : 100,
					text : $i18n.owner_article_no,//货主商品编码
					dataIndex : 'ownerArticleNo'
				},{
				    width:120,
				    text:$i18n.barcode,//商品条码
				    dataIndex:'barcode'
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
				},/*{
					width : 80,
					text : $i18n.packing_unit,
					dataIndex : 'packingUnit'
					
				},*/{
				    width:80,
				    text:$i18n.packingUnit,//包装单位
				    id:'packingUnit_D201',
				    dataIndex:'packingUnit'
				},{
					width:80,
					text:$i18n.packingSpec,//规格
					id:'packingSpec_D201',
					dataIndex:'packingSpec'
				}]
            
    		},{

     			xtype:'panel',
     			region:'south',
     			frame : true,
//     			height: 35,
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
     			  }
//     				,
//     			items:[{
//     				fieldLabel : $i18n.cell,   //货位
//     				id:'cellNoD201',
// 					xtype:'cdef_DefCellCombo',
// 					 store:Ext.create('cms.store.cdef.cdef_DefCellComboStore',{
//     			    	    proxy:{
//     							type:'ajax',
//     							async:false,
//     							method:'post',
//								url:'stock_PlanImportAction_getCdef_DefCellCombo',
//     							reader:{
//     								root:'rootList',
//     								totalProperty:'totalCount'
//     							}
//     						}
//     				    }),
//     				    
//     				    displayField : 'value',
//     				    valueField : 'value',
//    	    	        beforeLabelTextTpl : required
//     			},{
//					xtype: 'button',
//	            	text:$i18n.locate_start, 
//	            	width:67,
//	            	id:'sendD201'
//				}]
     		
   	        }]
		},{
			title: $i18n.titleD,//明细
			id:'tabPidd2_D201',
			itemId:'tabPidd2_D201i',
			layout:'border',
			items:[
			{
				xtype:'commMenuWidget',
				region:'north',
				id:'menuD201'
			},{
			    xtype : 'form',
			    id : 'formStockPlanMD201',
			    region : 'north',
			    frame : true,
			    layout : {
				    type : 'table',
				    columns : 3
			    },
			    defaults : {
				  xtype:'textfield',
				  abelWidth : '25%',
				  margin : '2 2 4 2',
				  labelAlign : 'right'
			    },
			    items : [{
					fieldLabel:$i18n.po_no2,//调账单号
					id:'planNoD201',
					allowBlank:false,
					readOnly:true,
					beforeLabelTextTpl:required
			    },{
				  	xtype:'bdef_DefOwnerCombo',
				  	fieldLabel:$i18n.owner,//委托业主
				  	id:'cmbOwnerNoD201',
				  	displayField : 'dropValue',
	    		    valueField : 'value',
	    	        allowBlank : false,
	    	        editable:false,
	    			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
	    				proxy:{
							type:'ajax',
							method:'post',
							url:'bdef_DefOwnerAction_queryOwnerCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
	    				}
					}).load(),
	    	        readOnly:true,
	    	        allowBlank : false,
	    	        beforeLabelTextTpl : required
			    },{
				  	xtype:'wms_DefFieldValCombo',
				  	fieldLabel:$i18n.planType,//调整类型
				  	id:'planTypeD201',
				  	store:Ext.create("cms.store.common.comboStore").load(
					       	    {
					       	         params:{str:"STOCK_PLAN_M,PLAN_TYPE"}
					       	    }),
			        beforeLabelTextTpl : required
			    },{
					fieldLabel:$i18n.stockPoNo,//原调帐单号
					id:'poNoD201',
					maxLength:20,
					allowBlank:false,
					readOnly:true,
					beforeLabelTextTpl:required
			    },{
					xtype:'datefield',
					fieldLabel:$i18n.planDate,//调整日期
					id:'planDateD201',
					format : 'Y-m-d',
					allowBlank:false,
					readOnly:true,
					beforeLabelTextTpl:required
			    },{
				 	xtype:'wms_DefFieldValCombo',
	       	      	fieldLabel : $i18n.orgNo,// 机构代码
					id : 'orgNoD201',	
		       	    readOnly:true,
		       	    store:Ext.create("cms.store.common.comboStore").load(
		       	    {
		       	         params:{str:"N,ORG_NO"}
		       	    }),
		       	    allowBlank : false,
		       	    beforeLabelTextTpl : required
				},{
					fieldLabel:$i18n.memo,//备注
					id:'memoD201',
					readOnly:true
			    }]
			},{
		   		region:'center',
		        xtype:'grid',
		        id:'stockPlanDgridD201',
		        store:Ext.create('cms.store.stock.stock_PlanDStore'),
		        selModel : {
			        	selType : 'cellmodel'
			    },
			    plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
		        	clicksToEdit : 1,
			        onSpecialKey:function(ed,field,e){
						commEnterGridStatEdit(this.grid,true,'cms.controller.stock.stock_PlanImportController',e.getKey());
					}
			    })],
		        columns:[
		        {
				    xtype:'rownumberer',
				    width:30
				},{
				    width : 110,
			        text : $i18n.article_no,//商品编码
			        dataIndex:'articleNo',
			        cls : 'notnull',
			        field : {							
			        	id : 'article_noD201',
			        	xtype:'bdef_DefArticleCombo',
			        	store:Ext.create("cms.store.bdef.bdef_DefArticleComboStore"),
		        	   	displayField : 'value',
		        	   	valueField : 'value',	
			        	allowBlank :false	
			        }
				},{
				    width:110,
				    text:$i18n.owner_article_no,//货主商品编码
				    dataIndex:'ownerArticleNo'
				},{
				    width:120,
				    text:$i18n.barcode,//商品条码
				    dataIndex:'barcode'
				},{
				    width:250,
				    text:$i18n.article_name,//商品名称
				    dataIndex:'articleName'
				},{
				    width:60,
				    text:$i18n.new_packing_qty,//包装数量
				    dataIndex:'packingQty',
				    cls : 'notnull',
				    field:{
						xtype : 'bdef_PackingQtyCombo',
						id : 'packing_qtyD201',
						store:Ext.create('cms.store.bdef.bdef_PackingQtyComboStore'),
						editable:false,
						allowBlank :false
					}
				},/*{
				    width:60,
				    text:$i18n.packing_qty1,//包装单位
				    dataIndex:'unit'
				},*/
				{
				    width:80,
				    text:$i18n.packingUnit,//包装单位
				    id:'packingUnit_D201_1',
				    dataIndex:'packingUnit'
				},{
					width:80,
					text:$i18n.packingSpec,//规格
					id:'packingSpec_D201_1',
					dataIndex:'packingSpec'
				},{
					width:70,
					text:$i18n.articleqty,//计划数量
//					hidden:true,
					dataIndex:'articleQty',
				},{
					width : 85,
					text : $i18n.planBox,	//计划箱数
					dataIndex : 'planBox',
					cls:'notnull',
					//hidden:true,
					id:'planBox_D201',
					field : {
			    		xtype : 'numberfield',
			    		minValue:0,
			    		listeners:{  
							'change': function(obj, newValue, oldValue, eOpts) {
								if(newValue!=oldValue){
									var data = Ext.getCmp('stockPlanDgridD201').getSelectionModel()
										.getSelection();
									data[0].set('articleQty', newValue
										* data[0].get('packingQty')
										+ data[0].get('planQmin') * data[0].get('qminOperatePacking')
										+ data[0].get('planDis'));
								}
							}
		      			}
			    	}
				},{
					width : 85,
					text : $i18n.planQmin,//计划中包数
					dataIndex : 'planQmin',
				    cls:'notnull',
					id:'planQmin_D201',
					field : {
			    		xtype : 'numberfield',
			    		minValue:0,
			    		listeners:{  
							'change': function(obj, newValue, oldValue, eOpts) {
								debugger;
								if(newValue!=oldValue){
									var data = Ext.getCmp('stockPlanDgridD201').getSelectionModel()
										.getSelection();
									data[0].set('articleQty', 
											data[0].get('planBox')* data[0].get('packingQty')
										  + newValue * data[0].get('qminOperatePacking')
										  + data[0].get('planDis'));
								}
							}
		      			}
			    	}
				},{
					width : 85,
					text : $i18n.planDis,//计划散数
					dataIndex : 'planDis',
					cls:'notnull',
					id:'planDis_D201',
					field : {
			    		xtype : 'numberfield',
			    		minValue:0,
			    		listeners:{  
							'change': function(obj, newValue, oldValue, eOpts) {
								if(newValue!=oldValue){
									var data = Ext.getCmp('stockPlanDgridD201').getSelectionModel()
										.getSelection();
									data[0].set('articleQty', 
											data[0].get('planBox')* data[0].get('packingQty')
										  + data[0].get('planQmin') * data[0].get('qminOperatePacking')
										  + newValue);
								}
							}
		      			}
			    	}
				},
				{
				    width:80,
				    text:$i18n.newproduct_date,//生产日期
//				    hidden:true,
				    dataIndex:'produceDateText',
				    cls:'notnull',
					field: {
		                xtype: 'datefield',
		                id: 'produceDateD201',
		                format : 'Y-m-d'
		            },	                
		            renderer:function(value){   
					    if(value instanceof Date){   				 
					        return Ext.Date.format(value,'Y-m-d');   
					    }else{				        
					        return value;   
					    }  
					}
    		    },
    		    {
				    width:80,
				    text:$i18n.end_date,//到期日 
				    hidden:true,
				    dataIndex:'expireDateText',
				    cls:'notnull',
					field: {
		                xtype: 'datefield',
		                id: 'expireDateD201',
		                format : 'Y-m-d'
		            },	                
		            renderer:function(value){   
					    if(value instanceof Date){   				 
					        return Ext.Date.format(value,'Y-m-d');   
					    }else{				        
					        return value;   
					    }  
					}
    		    },
    		    {
				    width:60,
				    text: $i18n.lot_no,//生产批号
//				    hidden:true,
				    dataIndex:'lotNo',
				    cls:'notnull',
				    field: {
				    	xtype : 'cdef_DefCellCombo',
						id : 'lotNoD201',
						store:Ext.create('cms.store.odata.odata_LotNoStore',
								{
							proxy:{
								type:'ajax',
								method:'post',
								url:'stock_PlanImportAction_queryLot',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								}
							}
						}),
						displayField : 'value',
			    		valueField : 'value',
						forceSelection : false,
				        beforeLabelTextTpl : required,
						allowBlank :false
						
		            }
    		    },
				{
					width : 90,
					text : $i18n.realQty,//实际调账数
					dataIndex:'realQty'
				},
				{
					width : 85,
					text : $i18n.realBox,	//实际调账箱数
					dataIndex : 'realBox',
					//hidden:true,
					id:'realBox_D201',
				},{
					width : 85,
					text : $i18n.realQmin,//实际调账中包数
					dataIndex : 'realQmin',
					id:'realQmin_D201',
				},{
					width : 85,
					text : $i18n.realDis,//实际调账散数
					dataIndex : 'realDis',
					id:'realDis_D201',
				},
    		    {
					width : 80,
					text : $i18n.cell,// 货位
				    //hidden:true,
					dataIndex : 'cellNo',
					cls:'notnull',
					field: {
	            		xtype: 'cdef_DefCellCombo',
	                    id:'cmCellNoD201',
		                displayField : 'value',
		    			valueField : 'value',
		    			store : Ext.create("cms.store.cdef.cdef_DefCellComboStore",
    					{
							 proxy:{
									type:'ajax',
									method:'post',
									url:'stock_PlanImportAction_getCdef_DefCellCombo',
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
    				width:60,
    				text:$i18n.quality,//'品质',
    				hidden:true,
    				dataIndex:'quality',
    				cls:'notnull',
					field: {
						xtype:'wms_DefFieldValCombo',
				        editable:false,
				        id:'qualityD201',
				        store:Ext.create("cms.store.common.comboStore").load(
				        {
				        	params:{str:"N,QUALITY"}
				        }),
				        allowBlank : false,
				        beforeLabelTextTpl : required
		            },	                
		            renderer: function(value,metadata,record){  
		            	if(value=='0')
		            	{
		            		return $i18n.goods;
		            	}else if(value=='1')
		            	{
		            		return $i18n.rejects;
		            	}
	         		}
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
    			}],
		    	dockedItems : [{
					xtype:'commMenuWidget6',
					region:'north',
					id:'toolbarD201'
			        }]
			}]
		}]
	}]          
});
