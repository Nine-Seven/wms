/**
 * 模块名称：验收确认
 * 模块编码：4601
 * 创建：Jun
 */
var idata_CheckMStore=Ext.create('cms.store.idata.idata_CheckMStore',{
	autoLoad:true,
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(th.count()>0){
				Ext.getCmp('grid_01_4601').getSelectionModel().select(0);
				var arrayObj = new Array();
				arrayObj[0]='sumCheckQty';
				arrayObj[1]='sumPoQty';
				arrayObj[2]='sumImportQty';
				countList('grid_01_4601',arrayObj,'SImportNo');
			}
		}
	}
});
var idataCheckDStore=Ext.create('cms.store.idata.idata_CheckDStore',{
	autoLoad:false,
	listeners:{  
		'load':function(th,records,successful,eOpts ){
				var arrayObj = new Array();
				arrayObj[0]='planBox';
				arrayObj[1]='planQmin';
				arrayObj[2]='planDis';
				arrayObj[3]='checkQty';
				countList('grid_02_4601',arrayObj,'articleNo');
			}
	}
});
var idata_CheckPalStore=Ext.create('cms.store.idata.idata_CheckPalStore',{autoLoad:false});
var idata_UnCheckPalStore=Ext.create('cms.store.idata.idata_UnCheckPalStore',{
	 listeners:{  
			'load':function(th,records,successful,eOpts ){
				if(th.count()==0){
					disableButtonFunc(1,'#btnCancle4601',$i18n.exp_cancel);
				}else{
					disableButtonFunc(0,'#btnCancle4601',$i18n.exp_cancel);
				}
			}
		},
	autoLoad:false});
Ext.define('cms.view.idata.idata_CheckConfirmUI',{
	alias:'widget.idata_CheckConfirmUI',
	title:$i18n.check_confirm,//验收确认
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu4',
	          'cms.view.common.wms_DefFieldValCombo',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.remoteCombo',
	          'cms.view.common.bdef_DefSupplierCombo',
	          'cms.view.common.bdef_DefWorkerCombo',
	          'cms.view.common.bdef_DefDockCombo',
	          'cms.view.common.bdef_DefSupplierCombo',
	          'cms.view.common.cdef_DefCellCombo'
	          ],
    items:[
    {
        xtype:'commMenuWidget4',
	    id:'menu4601',
	    region:'north'
	},
	{
		xtype : 'form',
		region : 'north',
		layout:'column',
		border:false,
		width:'100%',
		frame : true,
		defaults : {
			labelWidth : 90,
			margin : '2 2 2 2',
			labelAlign : 'right'
		},
		items:[
		{
			xtype:'bdef_DefOwnerCombo',
			fieldLabel : $i18n.owner_no,//货主编号
			id:'cmbOwnerNo4601_d1',
			displayField : 'dropValue',
		    valueField : 'value',
	        allowBlank : false,
	        editable:false,
			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				proxy:{
					type:'ajax',
					method:'post',
					url:'idata_CheckAction_ownerForConfirmCombo.action',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					}
				}
			}).load(),
	        beforeLabelTextTpl : required
		},
		{
			xtype:'wms_DefFieldValCombo',
			id:'cmbImportType4601_d1',
			fieldLabel : $i18n.import_type,//进货单别
	        editable:false,
	        store:Ext.create("cms.store.common.comboStore").load(
	        {
	        	params:{str:"N,IMPORT_TYPE"}
	        }),
	        allowBlank : false,
	        beforeLabelTextTpl : required
		},{
		 	xtype:'bdef_DefSupplierCombo',
			fieldLabel : $i18n.supplier_no,//供应商编号
		 	id:'supplierNo4601',
		    store:Ext.create('cms.store.bdef.bdef_DefSupplierComboStore'),
        	displayField : 'dropValue',
        	valueField : 'value'
		}
		]
	},
	{
		xtype:'panel',
   		region:'north',
   		layout:'border',
   		height:180,
   		items:[
        {
	    	xtype : 'grid',
			region : 'center',//'west',
			id:'grid_01_4601',
			store:idata_CheckMStore,
			columns : [ 
			{
				xtype : 'rownumberer',
				width : 30
			}, 
		
			{
				width : 140,
				text : $i18n.s_import_no,//汇总进货单号
				//hidden:true,
				dataIndex:'SImportNo'
			}, 
			{
				width : 140,
				text : $i18n.s_check_no,//汇总验收单号
				//hidden:true,
				dataIndex:'SCheckNo'
			},
			{
				width : 140,
				text : $i18n.po_no1,//采购单号
				dataIndex:'poNo'
			}, 
			{
				width : 140,
				text : $i18n.check_no1,//验收单号
				dataIndex:'checkNo'
			}, 
		
			{
				width : 80,
				text : $i18n.supplier_no,//供应商编号
				dataIndex:'supplierNo'
			}, 
			{
				width : 120,
				text : $i18n.supplier_name,//供应商名称
				dataIndex:'supplierName'
			}, 
			{
				width : 80,
				text : $i18n.check_qty,//验收数量
				dataIndex:'sumCheckQty'
			}, 
			{
				width : 70,
				text : '采购总量',//采购商品计划量
				dataIndex:'sumPoQty'
			},
			{
				width :70,
				text : '已验总量',//采购商品验收量
				dataIndex:'sumImportQty'
			},
			{
				width : 80,
				text : $i18n.imclass,//进货类型
				hidden:true,
				dataIndex:'importType'
			}, 
			{
				width : 100,
				text : $i18n.status,//状态
				dataIndex:'statusText'
			},
			{
				width : 100,
				text : $i18n.export_status,//导出状态
				dataIndex:'sendFlagText'
			}]
        },
        {
        	//进货验收结果 导出用 不显示在界面 huangb 20160719
        	xtype : 'grid',
			region : 'west',
			id:'grid_CheckResult_4601',
			store: Ext.create("cms.store.idata.idata_CheckMStore",{
				proxy:{
					type:'ajax',
					method:'post',
					async:false,
					url:'idata_CheckAction_tscGetCheckResult.action',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					}
				}
			}),
			hidden:true,//隐藏
			columns : [ 
						{
							xtype : 'rownumberer',
							width : 30
						}
					  ]
					
        },
        {
        	xtype : 'form',
        	id:'form_01_4601',
			layout:
			{
				type : 'table',
				columns : 1
			},
			region : 'east',
			frame : true,
			width : '25%',
			items:[
	        {
		    	margin:'0 0 0 0',
			    xtype:'fieldset',  
				defaults:
				{
				  	xtype:'textfield',
				  	labelAlign:'right'
				},
			    items:[
				{
					xtype:'bdef_DefOwnerCombo',
					id:'cmbOwnerNo4601_d2',
					fieldLabel : $i18n.owner_no,// 货主编号
	    			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
	    			displayField : 'dropValue',
	    		    valueField : 'value',
	    	        editable:false,
	    	        readOnly:true,
	    	        hidden:true,
	    	        allowBlank : false,
	    	        beforeLabelTextTpl : required
				},
				{
					xtype:'wms_DefFieldValCombo',
					id:'cmbImportType4601_d2',
					fieldLabel : $i18n.import_type,//进货单别
			        editable:false,
			        store:Ext.create("cms.store.common.comboStore").load(
			        {
			        	params:{str:"N,IMPORT_TYPE"}
			        }),
			        allowBlank : false,
			        readOnly:true,
			        hidden:true,
			        beforeLabelTextTpl : required
				},
				{
					id:'txtSImportNo4601',
					readOnly:true,
					hidden:true,
					fieldLabel : $i18n.s_import_no//汇总进货单号
				},
				{
					xtype : 'bdef_DefWorkerCombo',
					id:'cmbCheckWorker4601',
					fieldLabel : $i18n.worker_name2,//验收人员
					readOnly:true,
					store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore').load(),
					beforeLabelTextTpl : required
				},
				{
					id:'memo4601',
					fieldLabel : $i18n.m_remark,//备注
				}
				]

	        },
			{
		        margin:'5 0 0 0',
				xtype:'fieldset',  
				autoHeight:true,  
				items:[
				{
					layout:
					{
					type : 'table',
					columns : 4
					},
					xtype:'container',
					margin:'5 0 0 15',
					defaults:
					{
						margin : '2 2 2 2',
						labelAlign : 'right'
					},
					items:[
				    {
			    	    xtype : 'button',
			    	    margin:'0 0 0 10',
			    	    hidden:false,
			    	    id:'btnCancle4601',
			    	    text : $i18n.exp_cancel//整单取消
					},
					{
			    	    xtype : 'button',
			    	    margin:'0 0 0 20',
			    	    id:'btnConfirm4601',
			    	    text : $i18n.exp_confirm //整单确认
					},{
			    	    xtype : 'button',
			    	    margin:'0 0 0 20',
			    	    id:'btnPrintCheck4601',
			    	    text : $i18n.print_report, //打印验收单
			    	    hidden:true
					},{
			    	    xtype : 'button',
			    	    margin:'0 0 0 20',
			    	    hidden:true,
			    	    id:'',
			    	    text : $i18n.create_list //建入库单
					}
					]
				}
				]
			}
	        ]
		}
        ]
	},
	{
		xtype : 'tabpanel',
	    region:'center',
	    id:'tabPid4601',
	    flex : 4,
	    items : 
    	[
		{
			xtype : 'grid',
			title:$i18n.article_all,//商品明细
			id:'grid_02_4601',
			store:idataCheckDStore,
			selType : 'cellmodel',
			plugins : [Ext.create('Ext.grid.plugin.CellEditing',{
				clicksToEdit : 1,
				onSpecialKey:function(ed,field,e)
				{
					commEnterGridStatEdit(this.grid,false,'',e.getKey());
				}
			})],
			columns : [
		    {
				xtype : 'rownumberer',
				width : 30
			},
			{
				width : 105,
				text : $i18n.article_no,//商品编码
				dataIndex:'articleNo'
			},
			{
				 width:105,
 			    text : $i18n.owner_article_no,//货主商品编码
 			    dataIndex:'ownerArticleNo'		
			},
			{
				width : 160,
				text : $i18n.article_name,//商品名称
				dataIndex:'articleName'
			},
			{
				width : 105,
				text : $i18n.barcode,//商品条码
				dataIndex:'barcode'
			},
			{
				width : 60,
				text : $i18n.new_packing_qty,//包装数量
				dataIndex:'packingQty'
			},
			 {
		    	width : 60,
				text : $i18n.packingSpec,
				id:'packingSpec4601',
				dataIndex:'packingSpec'
		    },
		    {
				width : 60,
				text : $i18n.packing_qty1,//包装单位
				id:'packingUnit4601',
				dataIndex:'packingUnit'
			},
			{
				width : 65,
				text : $i18n.check_qty,//验收数量
				dataIndex:'checkQty'
			},
			{
				width : 55,
				text : $i18n.boxQty,//箱数
				dataIndex:'planBox',
				id:'planBox4601'
			},{
				width : 55,
				text:$i18n.qminQty,//中包数
			    id:'planQmin4601',
				dataIndex:'planQmin'
			},
			{
				width : 55,
				text : $i18n.disQty,//零散数
				id:'planDis4601',
				dataIndex:'planDis'
			},
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
				width:60,
				text:$i18n.lot_no,//'批号',
				//hidden:$i18n.lotNoHidden,
				dataIndex:'lotNo'
			},{		
				width:60,
				text:'温度',
				dataIndex:'temperature'
			},{
				width:60,
				text:$i18n.quality,//'品质',
				hidden:$i18n.qualityHidden,
				dataIndex:'qualityText'
			},{
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
			}
			]
		},
		{
			title:$i18n.article_single,//板明细
			layout:'border',
			items:
			[
			{
				xtype:'form',
				frame:true,
				region:'north',
				hidden:true,
				items:
				[
				{
				    xtype : 'button',
				    id:'btnItemCancel4601',
				    text : $i18n.exp_item_cancel//单品取消
				}
		        ]
			},
			{
				xtype : 'grid',
				id:'grid_03_4601',
				store:idata_CheckPalStore,
				region:'center',
				
				columns : [
			    {
					xtype : 'rownumberer',
					width : 30
				},
				{
					width : 85,
					text : $i18n.pal,//板号
					dataIndex:'labelNo'
				},
				{
					width : 105,
					text : $i18n.article_no,//商品编码
					dataIndex:'articleNo'
				},
				{
					 width:105,
	 			    text : $i18n.owner_article_no,//货主商品编码
	 			    dataIndex:'ownerArticleNo'		
				},
				{
					width : 160,
					text : $i18n.article_name,//商品名称
					dataIndex:'articleName'
				},
				{
					width : 105,
					text : $i18n.barcode,//商品条码
					dataIndex:'barcode'
				},
				{
					width : 60,
					text : $i18n.new_packing_qty,//包装数量
					dataIndex:'packingQty'
				},
				 {
			    	width : 60,
					text : $i18n.packingSpec,
					id:'packingSpec4601_2',
					dataIndex:'packingSpec'
			    },
			    {
					width : 60,
					text : $i18n.packing_qty1,//包装单位
					id:'packingUnit4601_2',
					dataIndex:'packingUnit'
				},
				
				{
					width : 55,
					text : $i18n.boxQty,//箱数
					dataIndex:'planBox',
					id:'planBox4601_2'
				},{
					width : 55,
					text:$i18n.qminQty,//中包数
				    id:'planQmin4601_2',
					dataIndex:'planQmin'
				},
				{
					width : 55,
					text : $i18n.qminQty,//零散数
					id:'planDis4601_2',
					dataIndex:'planDis'
				},
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
				},{
					width:60,
					text:$i18n.lot_no,//'批号',
				//	hidden:$i18n.lotNoHidden,
					dataIndex:'lotNo'
				},{		
					width:60,
					text:'温度',
					dataIndex:'temperature'
				},{
					width:50,
					text:$i18n.quality,//'品质',
					hidden:$i18n.qualityHidden,
					dataIndex:'quality'
				},
				{
					width:60,
					text:$i18n.ct_port,//'验收码头',
					dataIndex:'dockNo'
				},
				{
					width:60,
					text:$i18n.dp_worker,//'验收人',
					dataIndex:'rgstName'
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
				}
				]
			}
	        ]
		},{
			title:$i18n.article_single1,//已确认未封板
			layout:'border',
			itemId:'tabPId4601_03i',
			items:
			[{
				xtype : 'grid',
				id:'grid_04_4601',
				store:idata_UnCheckPalStore,
				region:'center',
				multiSelect: true,  
				selType : 'cellmodel',	
				columns : [
			    {
					xtype : 'rownumberer',
					width : 30
				},
				{
					width : 85,
					text : $i18n.pal,//板号
					dataIndex:'labelNo'
				},
				{
					width : 105,
					text : $i18n.article_no,//商品编码
					dataIndex:'articleNo'
				},
				{
					width:105,
	 			    text : $i18n.owner_article_no,//货主商品编码
	 			    dataIndex:'ownerArticleNo'		
				},
				{
					width : 160,
					text : $i18n.article_name,//商品名称
					dataIndex:'articleName'
				},
				{
					width : 105,
					text : $i18n.barcode,//商品条码
					dataIndex:'barcode'
				},
				{
					width : 60,
					text : $i18n.new_packing_qty,//包装数量
					dataIndex:'packingQty'
				},
				 {
			    	width : 60,
					text : $i18n.packingSpec,
					id:'packingSpec4601_3',
					dataIndex:'packingSpec'
			    },
			    {
					width : 60,
					text : $i18n.packing_qty1,//包装单位
					id:'packingUnit4601_3',
					dataIndex:'packingUnit'
				},
				{
					width : 55,
					text : $i18n.boxQty,//箱数
					dataIndex:'planBox',
					id:'planBox4601_3'
				},{
					width : 55,
					text:$i18n.qminQty,//中包数
				    id:'planQmin4601_3',
					dataIndex:'planQmin'
				},
				{
					width : 55,
					text : $i18n.disQty,//零散数
					id:'planDis4601_3',
					dataIndex:'planDis'
				},
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
				},{
					width:60,
					text:$i18n.lot_no,//'批号',
					//hidden:$i18n.lotNoHidden,
					dataIndex:'lotNo'
				},{		
					width:60,
					text:'温度',
					dataIndex:'temperature'
				},{
					width:60,
					text:$i18n.quality,//'品质',
					hidden:$i18n.qualityHidden,
					dataIndex:'quality'
				},{
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
			}]	
		}]
	},
	{
		region:'south'
	}
	]
});
