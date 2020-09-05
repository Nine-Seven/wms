/**
 * 模块名称：返配验收确认
 * 模块编码：6702
 * 创建：hcx
 */
var ridata_CheckConfirmMStore=Ext.create('cms.store.ridata.ridata_CheckConfirmMLnsStore',{
	autoLoad:true,
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(th.count()>0){
				Ext.getCmp('grid_01_6702').getSelectionModel().select(0);
			}
		}
	}
});
var ridataCheckDStore=Ext.create('cms.store.ridata.ridata_CheckConfirmDLnsStore');

Ext.define('cms.view.ridata.ridata_CheckConfirmLnsUI',{
	alias:'widget.ridata_CheckConfirmLnsUI',
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
	          'cms.view.common.cdef_DefCellCombo',
	          'cms.view.common.bdef_PackingQtyCombo',
	          ],
    items:[
    {
        xtype:'commMenuWidget4',
	    id:'menu6702',
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
			id:'cmbOwnerNo6702_d1',
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
			id:'cmbUntreadType6702_d1',
			fieldLabel : $i18n.untread_type1,//返配单别
	        editable:false,
	        store:Ext.create("cms.store.common.comboStore").load(
	        {
	        	params:{str:"RIDATA_UNTREAD_M,CLASS"}
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
   		height:250,
   		items:[
        {
	    	xtype : 'grid',
			region : 'center',//'west',
			id:'grid_01_6702',
			store:ridata_CheckConfirmMStore,
			columns : [ 
			{
				xtype : 'rownumberer',
				width : 30
			}, 
			{
    	  		width:140,
    	 		 text:$i18n.untread_id,//返配单号
    	  		dataIndex:'untreadNo'
      		},		
			{
				width : 140,
				text : $i18n.check_no1,//验收单号
				dataIndex:'checkNo'
			}, 
			{
    	  		width:100,
    	  		text:$i18n.cust_no,//客户编号
    	  		dataIndex:'custNo'
      		},
      		{
    	 		 width:140,
    	  		text:$i18n.cust_name,//客户名称
    	  		dataIndex:'custName'
            },
			{
				width:80,
    	  		text:$i18n.u_type,//返配类型
    	  		dataIndex:'untreadType'
			}, 
			{
				width : 100,
				text : $i18n.status,//状态
				dataIndex:'statusText'
			}
			],
		    dockedItems : 
		    [
		    {
		        xtype : 'pagingtoolbar',
		        store : ridata_CheckConfirmMStore,
		        dock : 'bottom',
		        displayInfo : true
		    }
		    ]
        },
        {
        	xtype : 'form',
        	id:'form_01_6702',
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
					id:'cmbOwnerNo6702_d2',
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
					id:'txtUntreadNo6702',
					readOnly:true,
					fieldLabel :$i18n.untread_id//返配单号
				},
				{
					xtype : 'bdef_DefWorkerCombo',
					id:'cmbCheckWorker6702',
					fieldLabel : $i18n.worker_name2,//验收人员
					store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore').load(),
					beforeLabelTextTpl : required
				},
				
				{
					id:'txtSUntreadNo6702',
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
						labelAlign : 'right'
					},
					items:[
					{
			    	    xtype : 'button',
			    	    margin:'0 0 0 20',
			    	    id:'btnConfirm6702',
			    	    text : $i18n.exp_confirm //整单确认
					}]
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
	    id:'tabPid6702',
	    flex : 4,
	    items : 
    	[
		{
			xtype : 'grid',
			title:$i18n.article_all,//商品明细
			id:'grid_02_6702',
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
			},/*{
				width : 60,
				text : $i18n.pal,//板号
				dataIndex:'labelNo'
			},*/{
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
				dataIndex:'packingUnit'
			},
			{
				width : 65,
				text : $i18n.check_number,//验收数量
				dataIndex:'checkQty'
			}/*,
			{
				width : 55,
				text : $i18n.pack_qty,//箱数
				dataIndex:'checkBox',
				cls : 'notnull',
				field : {
	        		xtype : 'numberfield',
	        		minValue:0
	        	}
			},
			{
				width : 55,
				text : $i18n.oldper_qty,//零散数
				dataIndex:'checkpcs',
				cls : 'notnull',
				field : {
	        		xtype : 'numberfield',
	        		minValue:0
	        	}
			}*/,
			{
				width : 80,
				text : $i18n.produce_date,//生产日期
				dataIndex:'produceDate',
				field : {
					xtype:'datefield',
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
				width : 80,
				text : $i18n.expire_date,//有效期至
				dataIndex:'expireDate',
				hidden:$i18n.expireDateHidden,
				field : {
					xtype:'datefield',
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
				text:$i18n.quality,//'品质',
				hidden:$i18n.qualityHidden,
				dataIndex:'qualityText',
//				cls : 'allownull',
				field : {
					xtype : 'bdef_PackingQtyCombo',
					displayField : 'dropValue',
				    valueField : 'value',
					store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
						proxy:{
							type:'ajax',
							method:'post',
							url:'ridata_CheckConfirmGsdAction_getQuatity',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
						}
					}).load(),
					editable:false,
					allowBlank :false
				}
			},
			{
				width:80,
				text:$i18n.lot_no,//'批号',
//				hidden:$i18n.lotNoHidden,
				dataIndex:'lotNo',
//				cls : 'allownull',
				field : {
					xtype:'textfield'
				}
			},{
				width : 80,
				text : '验收人',//验收数量
				dataIndex:'workerNo'
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
		}]
	},
	{
		region:'south'
	}
	]
});
