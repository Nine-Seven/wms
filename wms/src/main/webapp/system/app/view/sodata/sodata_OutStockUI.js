/**
 * 模块名称：报损回单
 * 模块编码：E102
 * 创建 ： 贺康利
 */
var sodata_OutstockME102=Ext.create('cms.store.sodata.sodata_OutstockMStore',{
	listeners:{  
				'load':function(th,records,successful,eOpts ){
					if(Ext.getCmp('sodata_OutstockME102').getStore().count()>0){
						Ext.getCmp('sodata_OutstockME102').getSelectionModel().select(0);
					}
				}
			}
});
var sodata_OutstockDE102=Ext.create('cms.store.sodata.sodata_OutstockDStore');

Ext.define('cms.view.sodata.sodata_OutStockUI',{
	alias:'widget.sodata_OutStockUI',
	title:$i18n.titleE102,//退厂回单
	id:'sodata_OutStockUIE102',
	width:'100%',
	layout:'border',
	extend : 'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu4'
	          ],
	items:[
	       {
	    	   xtype:'commMenuWidget4',
	   		   region:'north',
	   		   id:'menuE102'
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
				id : 'rdoCheckTypeE102',
				margins: '0 0 0 10',
				fieldLabel :'是否打印报损清单',
				width : 320,
				columns : 2,
				items : [
		        {
					boxLabel : $i18n.no_print,//不打印
					name : 'rd',
					inputValue : '0',
					checked:true
				},
				{
					boxLabel : $i18n.print,//打印
					name : 'rd',
					inputValue : '1'
				}
				]
   		   }       
	   		]
	   	
	       },{
				xtype : 'grid',
				id:'sodata_OutstockME102',
				store:sodata_OutstockME102,
				region : 'center',
				columns : [ {
					xtype : 'rownumberer',
					width : 30
				}, {
	    	       	width:140,
	    	       	text:$i18n.original_no,//原单号
	    	       	dataIndex:'poNo'
	    	    },{
					width : 160,
					text : $i18n.outstock_no,//下架单号
					dataIndex : 'outstockNo'
				},{
					width : 140,
					text : $i18n.operate_date,//发单日期
					format:'Y-m-d H:i:s',
					dataIndex:'operateDate',
					field: {
		                xtype: 'datefield',
		                format : 'Y-m-d'
		            },	                
		            renderer:function(value){   
					    if(value instanceof Date){   				 
					        return Ext.Date.format(value,'Y-m-d');   
					    }else{				        
					        return value;   
					    }  
					}
					
				},{
	    	       	width:100,
	    	       	text:$i18n.orgNo,//机构号
	    	       	dataIndex:'orgNoText'
	    	    },{
					width : 120,
					text : $i18n.status2,//操作状态
					dataIndex:'statusText'
				}]
			 },{
					xtype : 'grid',
					id:'sodata_OutstockDE102',
					region : 'south',
					store:sodata_OutstockDE102,
					height : 320,
					selModel : {
			        	selType : 'cellmodel'
			        },
					plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
						clicksToEdit : 1,
						onSpecialKey:function(ed,field,e){
							commEnterGridStatEdit(this.grid,false,'',e.getKey());
						}
					})],
					columns : [ {
						xtype : 'rownumberer',
						width : 30
					},{
						width : 120,
						text : $i18n.barcode,//商品条码
						dataIndex:'barcode'
					}, {
						width : 120,
						text : $i18n.article_no,//商品编码
						dataIndex:'articleNo'
					},{
						width : 180,
						text : $i18n.article_name,//商品名称
						dataIndex:'articleName'
					}, {
						width : 80,
						text : $i18n.packing_qty,//包装数量
						dataIndex:'packingQty'
					},{
						width : 80,
						text : $i18n.packing_unit,//包装单位
						dataIndex:'packingUnit',
						id:'packingUnitE102'
					},{
						width : 85,
						text : $i18n.packingSpec,//包装规格
						dataIndex : 'packingSpec',
						id:'packingSpecE102'
					},{
						width : 85,
						text : $i18n.planBox,//计划箱数
						dataIndex : 'planBox',
						//hidden:true,
						id:'planBoxE102',
						cls : 'notnull',
						field : {
				    		xtype : 'numberfield',
				    		minValue:0
				    	}
					},{
						width : 85,
						text : $i18n.planQmin,//计划中包数
						dataIndex : 'planQmin',
						id:'planQminE102',
						cls : 'notnull',
						field : {
				    		xtype : 'numberfield',
				    		minValue:0
				    	}
					},{
						width : 85,
						text : $i18n.planDis,//计划散数
						dataIndex : 'planDis',
						id:'planDisE102',
						cls : 'notnull',
						field : {
				    		xtype : 'numberfield',
				    		minValue:0
				    	}
					},{
						width : 85,
						text : $i18n.realBox,//实际箱数
						dataIndex : 'realBox',
						//hidden:true,
						id:'realBoxE102',
						cls : 'notnull',
						field : {
				    		xtype : 'numberfield',
				    		minValue:0
				    	}
					},{
						width : 85,
						text : $i18n.realQmin,//实际中包数
						dataIndex : 'realQmin',
						id:'realQminE102',
						cls : 'notnull',
						field : {
				    		xtype : 'numberfield',
				    		minValue:0
				    	}
					},{
						width : 85,
						text : $i18n.realDis,//实际散数
						dataIndex : 'realDis',
						id:'realDisE102',
						cls : 'notnull',
						field : {
				    		xtype : 'numberfield',
				    		minValue:0
				    	}
					}/*,{
						width : 80,
						text : $i18n.exp_qty,//计划箱数
						dataIndex:'planWholenum'
					}, {
						width : 80,
						text : $i18n.new_real_qty3,//实际箱数
						cls:'notnull',
						field : {
    						xtype : 'numberfield',
    						minValue:0,
	    					listeners:{  
								'change': function(obj, newValue, oldValue, eOpts) {
									if(newValue!=oldValue){
										var data = Ext.getCmp('sodata_OutstockDE102').getSelectionModel().getSelection();
											Ext.getCmp('sodata_OutstockDE102').getSelectionModel().getSelection();
										data[0].set('realQty', newValue * data[0].get('packingQty'));
									}
								}
			      			}
    				    },
						dataIndex:'realWholenum'
					}*/]
					}
	       ]          
});