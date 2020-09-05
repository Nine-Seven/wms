/**
 * 模块名称：移库回单
 * 模块编码：5301
 * 创建：zhouhuan
 */
var gridOdata_OutstockM5301Store=Ext.create('cms.store.odata.odata_OutstockMStore',{
	autoLoad:true,
	listeners:{  
	'load':function(th,records,successful,eOpts ){
		if(th.count()>0){
			Ext.getCmp('gridOdata_OutstockM5301').getSelectionModel().select(0);
		}
	}
}});
Ext.define('cms.view.mdata.odata_OutstockDUI',{
	alias:'widget.odata_OutstockDUI',
	title:$i18n.odata_outstock_d,//移库回单
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu4','cms.view.common.bdef_DefWorkerCombo'
	          ],
	items:[{
			   xtype : 'commMenuWidget4',
			   id:'menu5301',
			   region:'north'
		   },
		   {
			    xtype:'panel',
 				region : 'north',
				layout : 'border',
				height:230,
				border : false,
				items:[{
						xtype:'panel',
		 				region : 'west',
		 				width : '60%',
						//layout : 'border'
						border : false,
						items:[{
						xtype : 'grid',
						height : 230,
						store:gridOdata_OutstockM5301Store,
						id : 'gridOdata_OutstockM5301',
						//region : 'west',
						columns : [ {
							xtype : 'rownumberer',
							width : 30
						}, {
							width : 160,
							text : $i18n.outstock_no,//下架单号
							align:'center',
							dataIndex : 'outstockNo'
						}, {
							width : 80,
							text : $i18n.outstock_type,//下架类型
							align:'center',
							dataIndex : 'outstockTypeText'
						}, {
							width : 130,
							text : $i18n.operate_date,//操作日期
							align:'center',
							dataIndex : 'operateDate'
						}, {
							width : 80,
							text : $i18n.status2,//操作状态
							align:'center',
							dataIndex : 'statusText'
						},{
			    	       	width:140,
			    	       	text:$i18n.locate_no,//波次号
			    	       	dataIndex:'waveNo'
			    	    }]/*,
						dockedItems : [
		    	        {
			    	        xtype : 'pagingtoolbar',
			    	        dock : 'bottom',
			    	        store:gridOdata_OutstockM5301,
			    	        displayInfo : true
			    	    } ]*/
					}]
				},{
				    xtype:'form',
				    height : 230,
	 				region : 'center',
	 				border : false,
	 				frame:true,
	 				items : [
					{
		               xtype:'fieldset',  
		               autoHeight:false,  
		               margin:'5 10 10 10%',
		               id:'formOdata_OutstockD5301',
		               title:$i18n.query,  
		               defaults:{
			    	   		xtype:'textfield',
			    	   		margin:'0 4 1 4',
			    	   		labelAlign : 'right'
			       	   },
		               items : [{
							   xtype : 'textfield',
							   id:'txtOutstock_no5301',
							   fieldLabel : $i18n.outstock_no,//下架单号
							   beforeLabelTextTpl : required
						   },{
						   xtype : 'container',
						   margin : '0 5 0 130',
						   items : [ {
							   xtype : 'button',
							   margin:'0 5 0 10',
							   id:'butQuery5301',
							   text : $i18n.query//查询
						   }, {
							   xtype : 'button',
							   margin:'0 5 0 10',
							   id:'butClear5301',
							   text : $i18n.clearinfo//清空
						   }]
					   }]
	                },{
		               xtype:'fieldset',  
		               autoHeight:false,  
		               margin:'5 10 10 10%',
		               id:'formMan5301',
		               title:$i18n.workerinfo,//操作人员  
		               defaults:{
			    	   		xtype:'textfield',
			    	   		margin:'0 4 1 4',
			    	   		readOnly:true,
			    	   		labelAlign : 'right'
			       	   },
		               items : [ {
							   xtype : 'bdef_DefWorkerCombo',
							   fieldLabel : $i18n.real_outstockworker,//实际下架人员
							   id:'cmbReal_outstockworker5301',
							   store : Ext.create("cms.store.bdef.bdef_DefworkerComboStore"),
							   beforeLabelTextTpl : required
						   },{
							   xtype : 'bdef_DefWorkerCombo',
							   fieldLabel : $i18n.instock_name,//上架人员
							   id:'cmbInstock_name5301',
							   store : Ext.create("cms.store.bdef.bdef_DefworkerComboStore"),
							   beforeLabelTextTpl : required
						   },{
							   xtype : 'button',
							   margin : '0 5 0 200',
							   id:'butReceipt5301',
							   disabled:true,
							   text : $i18n.receipt//回单
					   }]
	                }]
					}
				]
		},
		{
			xtype:'grid',
			region : 'center',
			store:Ext.create('cms.store.odata.odata_OutstockDStore'),
			id:'gridOdata_OutstockD5301',
			width : '100%',
			loadMask : true, // 加载时有加载的图标
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
			}, {
				width : 100,
				text : $i18n.s_cell_no,//来源储位	
				dataIndex : 'SCellNo'
			},{
				width : 130,
				text : $i18n.article_no,//商品编码
				dataIndex : 'articleNo'
			},{
				width : 100,
				text : $i18n.owner_article_no,//货主商品编码
				dataIndex : 'ownerArticleNo'
			},{
				width : 120,
				text : $i18n.barcode,//商品条码
				dataIndex : 'barcode'
			},{
				width : 150,
				text : $i18n.article_name,//商品名称
				dataIndex : 'articleName'
			},{
				width : 100,
				text : $i18n.a_cell_no,//目的储位
				dataIndex : 'DCellNo'
			},{
				width : 100,
				text : $i18n.articleqty,//计划数量
				dataIndex : 'articleQty'
			},{
				width : 85,
				text : $i18n.planBox,	//计划箱数
				dataIndex : 'planBox',
				id:'planBox_5301'
			},{
				width : 85,
				text : $i18n.planQmin,//计划中包数
				dataIndex : 'planQmin',
				id:'planQmin_5301'
			},{
				width : 85,
				text : $i18n.planDis,//计划散数
				dataIndex : 'planDis',
				id:'planDis_5301'
			},{
				width : 70,
				text : $i18n.realQty,//实际数量
				dataIndex : 'realQty',
				
				/*field: {
	            	xtype: 'numberfield',
	            	minValue:0,
	            	listeners:{  
						'change': function(obj, newValue, oldValue, eOpts) {
							if(newValue!=oldValue){
								var data = Ext.getCmp('gridOdata_OutstockD5301').getSelectionModel()
									.getSelection();
								data[0].set('realQty', parseInt(newValue
									* parseInt(data[0].get('packingQty'))));
							}
						}
	      			}
	            }*/
			},{
				width : 85,
				text : $i18n.realBox,	//实际箱数
				dataIndex : 'realBox',
				id:'realBox_5301',
				cls:'notnull',
				field : {
		    		xtype : 'numberfield',
		    		minValue:0,
		    		listeners:{  
						'change': function(obj, newValue, oldValue, eOpts) {
							if(newValue!=oldValue){
								var data = Ext.getCmp('gridOdata_OutstockD5301').getSelectionModel()
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
				width : 85,
				text : $i18n.realQmin,//实际中包数
				dataIndex : 'realQmin',
				id:'realQmin_5301',
				cls:'notnull',
				field : {
		    		xtype : 'numberfield',
		    		minValue:0,
		    		listeners:{  
						'change': function(obj, newValue, oldValue, eOpts) {
							debugger;
							if(newValue!=oldValue){
								var data = Ext.getCmp('gridOdata_OutstockD5301').getSelectionModel()
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
				width : 85,
				text : $i18n.realDis,//实际散数
				dataIndex : 'realDis',
				id:'realDis_5301',
				cls:'notnull',
				field : {
		    		xtype : 'numberfield',
		    		minValue:0,
		    		listeners:{  
						'change': function(obj, newValue, oldValue, eOpts) {
							if(newValue!=oldValue){
								var data = Ext.getCmp('gridOdata_OutstockD5301').getSelectionModel()
									.getSelection();
								data[0].set('realQty', 
										data[0].get('realBox')* data[0].get('packingQty')
									  + data[0].get('realQmin') * data[0].get('qminOperatePacking')
									  + newValue);
							}
						}
	      			}
		    	}
			},{
			    width:80,
			    text:$i18n.packingUnit,//包装单位
			    id:'packingUnit_5301',
			    dataIndex:'packingUnit'
			},{
				width:80,
				text:$i18n.packingSpec,//规格
				id:'packingSpec_5301',
				dataIndex:'packingSpec'
			},{
				width : 70,
				text : $i18n.packing_qty,//包装数量
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
				width:60,
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
			}/*, {
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
								var data = Ext.getCmp('gridOdata_OutstockD5301').getSelectionModel()
									.getSelection();
								data[0].set('realBox', parseInt(newValue
									/ parseInt(data[0].get('packingQty'))));
								data[0].set('realDis', parseInt(newValue
									% parseInt(data[0].get('packingQty'))));
							}
						}
	      			}
	            }
			},{
				width : 80,
				text : $i18n.operate_type,//作业类型
				dataIndex : 'operateType'
			}*/]
		},
		{
			xtype : 'panel',
			id : 'msterForm2220006',
			region:'south',
			html : '<div class="view_footer" style="margin:0; padding: 8px 20px 8px 20px;width:100% ;'
					+ 'background-color:#C1D5ED; text-align: left;">'
					+ '<span><label>新增人:</label><label id="Editor5301"></label> </span> '
					+ '<span><label>&nbsp;&nbsp;&nbsp;新增时间：</label><label id="EditDate5301"></label></span>'
					+ '<span><label>&nbsp;&nbsp;&nbsp;修改人：</label><label id="Checker5301"></label> </span> '
					+ '<span><label>&nbsp;&nbsp;&nbsp;修改时间：</label><label id="Checkdate5301"></label> </span></div>'
		}
	]
	       
});