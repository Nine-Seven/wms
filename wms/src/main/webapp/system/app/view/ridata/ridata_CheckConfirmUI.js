/**
 * 模块名称：返配验收确认
 * 模块编码：6601
 * 创建：hkl
 */
var ridata_CheckConfirmMStore=Ext.create('cms.store.ridata.ridata_CheckConfirmMStore',{
	autoLoad:true,
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(th.count()>0){
				Ext.getCmp('grid_01_6601').getSelectionModel().select(0);
			}
		}
	}
});
var ridataCheckDStore=Ext.create('cms.store.ridata.ridata_CheckConfirmDStore',{
	autoLoad:false,
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(th.count()>0){
				var arrayObj = new Array();
				arrayObj[0]='untreadQty';
				arrayObj[1]='checkQty';
				arrayObj[2]='planBox';
				arrayObj[3]='planQmin';
				arrayObj[4]='planDis';
				arrayObj[5]='realBox';
				arrayObj[6]='realQmin';
				arrayObj[7]='realDis';
				countList('grid_02_6601',arrayObj,'articleNo');
			}
		}
	}
	});
var ridata_CheckPalStore=Ext.create('cms.store.ridata.ridata_CheckPalStore',{autoLoad:false});
var ridata_UnCheckPalStore=Ext.create('cms.store.ridata.ridata_UnCheckPalStore',{autoLoad:false});
Ext.define('cms.view.ridata.ridata_CheckConfirmUI',{
	alias:'widget.ridata_CheckConfirmUI',
	title:'返配验收确认',//验收确认
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
	          'cms.view.common.cdef_DefCellCombo'
	          ],
    items:[
    {
        xtype:'commMenuWidget4',
	    id:'menu6601',
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
			id:'cmbOwnerNo6601_d1',
			displayField : 'dropValue',
		    valueField : 'value',
	        allowBlank : false,
	        editable:false,
			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				proxy:{
					type:'ajax',
					method:'post',
					url:'ridata_CheckAction_getComboList',
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
			id:'cmbUntreadType6601_d1',
			fieldLabel : '质量类型',//返配单别
	        editable:false,
	        store:Ext.create("cms.store.common.comboStore").load(
	        {
	        	params:{str:"RIDATA_UNTREAD_M,QUALITY"}
	        }),
	        allowBlank : false,
	        beforeLabelTextTpl : required
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
			id:'grid_01_6601',
			store:ridata_CheckConfirmMStore,
			columns : [ 
			{
				xtype : 'rownumberer',
				width : 30
			}, 
			{
    	  		width:140,
    	 		 text:$i18n.p_no,//原返配单号
    	  		dataIndex:'poNo'
      		},		
			{
    	  		width:140,
    	 		text:$i18n.untread_no,//返配单号
    	  		dataIndex:'untreadNo'
      		},		
			{
				width : 120,
				text : $i18n.check_no1,//验收单号
				dataIndex:'checkNo'
			}, 
			{
    	  		width:80,
    	  		text:$i18n.cust_no,//客户编号
    	  		dataIndex:'custNo'
      		},
      		{
    	 		width:100,
    	  		text:$i18n.cust_name,//客户名称
    	  		dataIndex:'custName'
            },
			{
				width:65,
    	  		text:$i18n.u_type,//返配类型
    	  		dataIndex:'untreadType'
			}, 
			{
				width : 80,
				text : $i18n.status,//状态
				dataIndex:'statusText'
			}
			]/*,
		    dockedItems : 
		    [
		    {
		        xtype : 'pagingtoolbar',
		        store : ridata_CheckConfirmMStore,
		        dock : 'bottom',
		        displayInfo : true
		    }
		    ]*/
        },
        {
        	xtype : 'form',
        	id:'form_01_6601',
			layout:
			{
				type : 'table',
				columns : 1
			},
			region : 'east',
			frame : true,
			width : '35%',
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
					id:'cmbOwnerNo6601_d2',
					fieldLabel : $i18n.owner_no,// 货主编号
	    			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
	    			displayField : 'dropValue',
	    		    valueField : 'value',
	    	        editable:false,
	    	        readOnly:true,
	    	        allowBlank : false,
	    	        beforeLabelTextTpl : required
				},
				{
					id:'cmbUntreadType6601_d2',
					fieldLabel : $i18n.untread_type1,//返配单别
			        readOnly:true,
			        hidden:true,
			        beforeLabelTextTpl : required
				},
				{
					id:'txtUntreadNo6601',
					readOnly:true,
					fieldLabel :$i18n.untread_no//返配单号
				},
				{
					xtype : 'bdef_DefWorkerCombo',
					id:'cmbCheckWorker6601',
					fieldLabel : $i18n.worker_name2,//验收人员
					readOnly:true,
					store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore').load(),
					beforeLabelTextTpl : required
				},
				
				{
					id:'txtSUntreadNo6601',
					hidden:true,
					readOnly:true,
					fieldLabel :$i18n.untread_id//汇总返配单号
				},
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
						//disabled:true,
						labelAlign : 'right'
					},
					items:[
				    /*{
			    	    xtype : 'button',
			    	    margin:'0 0 0 10',
			    	    hidden:true,
			    	    id:'btnCancle6601',
			    	    text : $i18n.exp_cancel//整单取消
					},*/
					{
			    	    xtype : 'button',
			    	    margin:'0 0 0 20',
			    	    id:'btnConfirm6601',
			    	    text : $i18n.exp_confirm //整单确认
					},
					{
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
	    id:'tabPid6601',
	    flex : 4,
	    items : 
    	[
		{
			xtype : 'grid',
			title:$i18n.article_all,//商品明细
			id:'grid_02_6601',
			store:ridataCheckDStore,
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
				text : $i18n.packing_qty1,//包装单位
				id:'packingUnit_6601',
				dataIndex:'packingUnit'
			},
			{
				width:80,
				text:$i18n.packingSpec,//规格
				id:'packingSpec_6601',
				dataIndex:'packingSpec'
			},
			/*{
				width : 60,
				text : $i18n.spec,//规格
				dataIndex:'spec'
			},*/
			{
				width : 65,
				text : $i18n.articleqty,//计划数量
				dataIndex:'untreadQty'
			},
			{
				width : 85,
				text : $i18n.planBox,	//计划箱数
				dataIndex : 'planBox',
				//hidden:true,
				id:'planBox_6601'
			},{
				width : 85,
				text : $i18n.planQmin,//计划中包数
				dataIndex : 'planQmin',
				id:'planQmin_6601',
			},{
				width : 85,
				text : $i18n.planDis,//计划散数
				dataIndex : 'planDis',
				id:'planDis_6601',
			},
			{
				width : 65,
				text : $i18n.check_qty,//验收数量
				dataIndex:'checkQty'
			},
			{
				width : 85,
				text : $i18n.import_box,	//验收箱数
				dataIndex : 'realBox',
				//hidden:true,
				id:'realBox_6601',
			},{
				width : 85,
				text : $i18n.checkQmin,//验收中包数
				dataIndex : 'realQmin',
				id:'realQmin_6601',
			},{
				width : 85,
				text : $i18n.import_pcs,//验收散数
				dataIndex : 'realDis',
				id:'realDis_6601',
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
				text:$i18n.quality,//'品质',
				hidden:$i18n.qualityHidden,
				dataIndex:'qualityText'
			},
			/*{
				width:100,
				text:'验收批次',
				hidden:$i18n.importBatchNoHidden,
				dataIndex:'',
			},*/
			{
				width:60,
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
			}
			]
		},
		{
			title:'已验收封箱明细',
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
				    id:'btnItemCancel6601',
				    text : $i18n.exp_item_cancel//单品取消
				}
		        ]
			},
			{
				xtype : 'grid',
				id:'grid_03_6601',
				store:ridata_CheckPalStore,
				region:'center',
				multiSelect: true,  
				selModel: {  
					selType:'checkboxmodel'
					   //checkOnly:true
				},
				
				columns : [
			    {
					xtype : 'rownumberer',
					width : 30
				},
				{
					width : 85,
					text : $i18n.box_no,//箱号
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
					dataIndex:'barCode'
				},
				{
					width : 60,
					text : $i18n.new_packing_qty,//包装数量
					dataIndex:'packingQty'
				},
				{
					width : 60,
					text : $i18n.packing_qty1,//包装单位
					id:'packingUnit_6601_1',
					dataIndex:'packingUnit'
				},
				{
					width:80,
					text:$i18n.packingSpec,//规格
					id:'packingSpec_6601_1',
					dataIndex:'packingSpec'
				},
				{
					width : 65,
					text : $i18n.check_number,//验收数量
					dataIndex:'checkQty'
				},
				{
					width : 85,
					text : $i18n.import_box,	//验收箱数
					dataIndex : 'realBox',
					//hidden:true,
					id:'realBox_6601_1',
				},{
					width : 85,
					text : $i18n.checkQmin,//验收中包数
					dataIndex : 'realQmin',
					id:'realQmin_6601_1',
				},{
					width : 85,
					text : $i18n.import_pcs,//验收散数
					dataIndex : 'realDis',
					id:'realDis_6601_1',
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
					text:$i18n.quality,//'品质',
					hidden:true,
					dataIndex:'quality'
				},
				{
					width:60,
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
				}
				]
			}
	        ]
		},{
			title:'已验收未封箱明细',
			layout:'border',
			items:
			[{
				xtype : 'grid',
				id:'grid_04_6601',
				store:ridata_UnCheckPalStore,
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
					dataIndex:'barCode'
				},
				{
					width : 60,
					text : $i18n.new_packing_qty,//包装数量
					dataIndex:'packingQty'
				},
				{
					width : 60,
					text : $i18n.packing_qty1,//包装单位
					id:'packingUnit_6601_2',
					dataIndex:'packingUnit'
				},
				{
					width:80,
					text:$i18n.packingSpec,//规格
					id:'packingSpec_6601_2',
					dataIndex:'packingSpec'
				},
				/*{
					width : 55,
					text : $i18n.spec,//规格
					dataIndex:'spec'
				},*/
				/*{
					width : 65,
					text : $i18n.check_number,//验收数量
					dataIndex:'checkQty'
				},*/
				{
					width : 85,
					text : $i18n.import_box,	//验收箱数
					dataIndex : 'realBox',
					//hidden:true,
					id:'realBox_6601_2',
				},{
					width : 85,
					text : $i18n.checkQmin,//验收中包数
					dataIndex : 'realQmin',
					id:'realQmin_6601_2',
				},{
					width : 85,
					text : $i18n.import_pcs,//验收散数
					dataIndex : 'realDis',
					id:'realDis_6601_2',
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
					text:$i18n.quality,//'品质',
					hidden:true,
					dataIndex:'quality'
				},
				{
					width:60,
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
				}
				]
			}
	        ]
		
		}
		]
	},
	{
		region:'south'
	}
	]
});
