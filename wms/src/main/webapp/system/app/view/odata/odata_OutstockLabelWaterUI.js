/**
 * 模块名称：流水标签回单
 * 模块编码：3927
 * 创建：潘振兴
 */

var gridOutstockM3927store=Ext.create('cms.store.odata.odata_OutstockMStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_OutstockMWaterAction_getWaterM.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
		
	},listeners:{  
		'load':function(th,records,successful,eOpts ){
		if(th.count()>0){
			Ext.getCmp('gridOutstockM3927').getSelectionModel().select(0);
		}
	  }
	}
});
var gridOutstockD3927store=Ext.create('cms.store.odata.odata_OutstockDStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_OutstockMWaterAction_getWaterD.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});
var gridOutstockDLabel3927store=Ext.create('cms.store.odata.odata_OutstockDStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_OutstockMWaterAction_getWaterDLabel.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});
Ext.define('cms.view.odata.odata_OutstockLabelWaterUI',{
	alias:'widget.odata_OutstockLabelWaterUI',
	title:$i18n.outstockLabelWater,//流水标签回单
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
	   id:'menu3927',
	   region:'north'
	},
	{
		xtype:'panel',
		region:'north',
		frame : true,
		layout: {
		    type: 'table',
	        columns: 2
		},
	    defaults : {
			xtype : 'textfield',
			margin : '10 3 3 3',
			labelAlign:'right',
			allowBlank: true,
			width : 280,
			labelWidth : 90
		},
	    	    items:[
	    				{
	    					xtype:'wms_DefFieldValCombo',
	    					fieldLabel : $i18n.locate_no,// 波次号
	    					id:'cmbWave_no3927',
	    					displayField: 'dropValue',
	    					valueField: 'value',
	    				    queryMode: 'local',				  
	    					store:Ext.create("cms.store.common.comboStore",
	    					{
	    						proxy:{
	    							type:'ajax',
	    							method:'post',
	    							url:'odata_OutstockMWaterAction_getWaveWaterCombo',
	    							reader:{
	    								root:'rootList',
	    								totalProperty:'totalCount'
	    							},
	    							extraParams:{
	    								strB2CYesOrNo:"1"
	    							}
	    							
	    						},
	    						listeners:{  
		    						'load':function(th,records,successful,eOpts ){
		    							if(th.count()>0){
		    								Ext.getCmp('cmbWave_no3927').setValue(th.getAt(0).data.value);
		    								_myAppGlobal.getController('cms.controller.odata.odata_OutstockLabelWaterController').cmbWave_no3927change();
		    							}else
		    							{
		    								Ext.getCmp('cmbBatch_no3927').getStore().removeAll();
		    								Ext.getCmp('cmbBatch_no3927').setValue(null);
		    								Ext.getCmp('gridOutstockM3927').getStore().removeAll();
		    								Ext.getCmp('gridOutstockDlabel3927').getStore().removeAll();
		    								Ext.getCmp('gridOutstockD3927').getStore().removeAll();
		    							}
		    						}
	    						}
	    	   				}),
	    					beforeLabelTextTpl : required
	    				},
	    				{
	    					xtype:'wms_DefFieldValCombo',
	    					fieldLabel : $i18n.m_batch_no,//批次
	    					id:'cmbBatch_no3927',
	    					store:Ext.create("cms.store.common.comboStore",
	    					{
	    						proxy:{
	    							type:'ajax',
	    							method:'post',
	    							url:'odata_OutstockMWaterAction_getBatchWaterCombo.action',
	    							reader:{
	    								root:'rootList',
	    								totalProperty:'totalCount'
	    							}
	    							
	    						},
	    						listeners:{  
	    							'load':function(th,records,successful,eOpts ){
	    								if(th.count()>0){
	    									Ext.getCmp('cmbBatch_no3927').setValue(th.getAt(0).data.value);
	    									_myAppGlobal.getController('cms.controller.odata.odata_OutstockLabelWaterController').cmbBatch_no3927change();
	    								}else
	    								{
	    									Ext.getCmp('gridOutstockM3927').getStore().removeAll();
	    							   		Ext.getCmp('gridOutstockDlabel3927').getStore().removeAll();
	    									Ext.getCmp('gridOutstockD3927').getStore().removeAll();
	    								}
	    							}
	    						}
	    	   				}),
	    					beforeLabelTextTpl : required
	    				}]
	    				}
	    		   ,{
	    				xtype:'grid',
	    				region : 'center',
	    				height:'220',
	    				store:gridOutstockM3927store,
	    		        id: 'gridOutstockM3927',
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
	    				xtype:'panel',
	    				region:'south',
	    				layout:'column',
	    				items:[
	    				{
		    				xtype:'grid',
		    				region : 'west',
		    				id: 'gridOutstockDlabel3927',
		    				store:gridOutstockDLabel3927store,
		    				height:250,
		    				width:'15%',
		    				columns:[{			
		    					xtype : 'rownumberer',
		    					width : 40
		    				},
		    				{
		    					width:100,
		    					text :$i18n.label_nos,//标签号码
		    					dataIndex : 'labelNo'
		    				},
		    				]
		    			},
	    			{
	    				xtype:'grid',
	    				region : 'east',
	    				store:gridOutstockD3927store,
	    		        id: 'gridOutstockD3927',
	    				height:250,
						width:'85%',
	    				selType : 'cellmodel',
	    				plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
	    					clicksToEdit : 1,
	    					onSpecialKey:function(ed,field,e){
	    						commEnterGridStatEdit(this.grid,false,'',e.getKey());
	    					}
	    				})],
	    				columns:[
	    				         
	    				         {			
	    					xtype : 'rownumberer',
	    					width : 40
	    				},
	    				{
	    					width:100,
	    					text :'客户名称',
	    					dataIndex : 'custName'
	    				},
	    				{
	    					width : 100,
	    					text : $i18n.s_cell_no ,//来源储位
	    					dataIndex : 'SCellNo'
	    				},
	    				{
	    					width:110,
	    					text :$i18n.article_no,//商品编码
	    					dataIndex : 'articleNo'
	    				},
	    				{
	    					width : 110,
	    					text : $i18n.barcode,//商品条码
	    					dataIndex : 'barcode'
	    				},
	    				{
	    					width:180,
	    					text :$i18n.article_name,//商品名称
	    					dataIndex : 'articleName'
	    				},
	    				{
	    					width : 60,
	    					text : $i18n.packing_qty,//包装数量
	    					dataIndex : 'packingQty'
	    				},
	    				{
	    					width : 110,
	    					text : $i18n.packing_unit,//包装单位
	    				    id:'packingUnit3927',
	    					dataIndex : 'packingUnit'
	    				},
	    				{
	    				    width:80,
	    				    text:$i18n.packingSpec,//规格
	    				    id:'packingSpec3927',
	    				    dataIndex:'packingSpec'
	    	    	    },
	    				{
	    					width : 80,
	    					text : '计划数量',//计划数量
	    				    id:'articleQty3927',
	    					dataIndex : 'articleQty'
	    				},
	    				{
	    					width : 80,
	    					text : $i18n.planBox,//计划箱数
	    					dataIndex : 'planBox',
	    					id:'planBox3927'
	    				},{
	    					width : 80,
	    					text : $i18n.planQmin,//计划中包数
	    					dataIndex : 'planQmin',
	    					id:'planQmin3927'
	    				},{
	    					width : 80,
	    					text : $i18n.planDis,//计划散数
	    					dataIndex : 'planDis',
	    					id:'planDis3927'
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
	    			}]}, 
	    			{
	    				 xtype : 'form',
	    				 frame : true,
	    				 region:'south',
	    				 height: 40,
	    				 layout : {
	    					 type : 'table',
	    					 columns :3
	    				 },
	    				width:'100%',
	    				defaults : {
	    					labelWidth : 80,
	    					margin:'0 0 0 300',
	    					labelAlign:'right'
	    				},
	    				items:[
	    					{
	    						xtype : 'bdef_DefWorkerCombo',
	    						fieldLabel : $i18n.outstock_worker,//  
	    						id : 'cmbWorkerNo3927',
	    						store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore'),
	    						beforeLabelTextTpl : required
	    					},
	    					{
	    						xtype : 'button',
	    						margin:'0 0 0 20',
	    						id:'butReceipt3927',
	    						disabled:true,
	    						text : $i18n.allreceipt//整单回单
	    					},{
		    						xtype : 'button',
		    						margin:'0 0 0 40',
		    						id:'butReceiptZero3927',
		    						disabled:true,
		    						text : '整单零回'
	    					}]
	    			 }
	    		]
	    }
  );
		
