/**
 * 模块名称：库存调账确认
 * 模块编码：D301
 * 创建：hcx
 */
var stock_ConfirmMStore=Ext.create('cms.store.stock.stock_ConfirmMStore',{
	autoLoad:true,
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(th.count()>0){
				Ext.getCmp('grid_01_D301').getSelectionModel().select(0);
			}
		}
	}
});
var stock_ConfirmDStore=Ext.create('cms.store.stock.stock_ConfirmDStore',{autoLoad:false});
Ext.define('cms.view.stock.stock_ConfirmUI',{
	alias:'widget.stock_ConfirmUI',
	title:'库存调帐确认',//库存调帐确认
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
	    id:'menuD301',
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
			id:'cmbOwnerNoD301_d1',
			displayField : 'dropValue',
		    valueField : 'value',
	        allowBlank : false,
	        editable:false,
			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				proxy:{
					type:'ajax',
					method:'post',
					url:'stock_ConfirmAction_queryOwnerCombo',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					}
				}
			}).load()
		},
		{
			xtype:'wms_DefFieldValCombo',
			id:'cmbPlanTypeTypeD301_d1',
			fieldLabel : $i18n.planType,//调整类型
	        editable:false,
	        store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				proxy:{
					type:'ajax',
					method:'post',
					url:'stock_ConfirmAction_queryPlanTypeCombo',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					}
				}
			}).load(),
	        allowBlank : false
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
			id:'grid_01_D301',
			store:stock_ConfirmMStore,
			columns : [ 
			{
				xtype : 'rownumberer',
				width : 30
			}, 
			{
    	  		width:140,
    	 		 text:$i18n.stockPoNo,//原调账单号
    	  		dataIndex:'poNo'
      		},	
      		{
    	  		width:140,
    	 		 text:$i18n.po_no2,//调账单号
    	  		dataIndex:'planNo'
      		},	
			{
				width : 180,
				text : $i18n.confirmNo,//确认单号
				dataIndex:'confirmNo'
			}, 
			{
    	  		width:130,
    	  		text:$i18n.planType,//调整类型
    	  		dataIndex:'planTypeText'
      		},
			{
				width : 130,
				text : $i18n.status,//状态
				dataIndex:'statusText'
			}
			]/*,
		    dockedItems : 
		    [
		    {
		        xtype : 'pagingtoolbar',
		        store : stock_ConfirmMStore,
		        dock : 'bottom',
		        displayInfo : true
		    }
		    ]*/
        },
        {
        	xtype : 'form',
        	id:'form_01_D301',
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
					id:'cmbOwnerNoD301_d2',
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
					id:'poNoD301',
					readOnly:true,
					fieldLabel :$i18n.stockPoNo//原调帐单号
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
					{
			    	    xtype : 'button',
			    	    margin:'0 0 0 20',
			    	    id:'btnConfirmD301',
			    	    text : $i18n.exp_confirm //整单确认
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
	    id:'tabPidD301',
	    flex : 4,
	    items : 
    	[
		{
			xtype : 'grid',
			title:$i18n.article_all,//商品明细
			id:'grid_02_D301',
			store:stock_ConfirmDStore,
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
				width : 120,
				text : $i18n.barcode,//商品条码
				dataIndex:'barcode'
			},
			{
				width : 250,
				text : $i18n.article_name,//商品名称
				dataIndex:'articleName'
			},
			{
				width : 70,
				text : $i18n.new_packing_qty,//包装数量
				dataIndex:'packingQty'
			},
			{
			    width:80,
			    text:$i18n.packingUnit,//包装单位
			    id:'packingUnit_D301',
			    dataIndex:'packingUnit'
			},{
				width:80,
				text:$i18n.packingSpec,//规格
				id:'packingSpec_D301',
				dataIndex:'packingSpec'
			},{
				width:70,
				text:$i18n.articleqty,//计划数量
				dataIndex:'articleQty',
			},
			{
				width : 85,
				text : $i18n.planBox,	//计划箱数
				dataIndex : 'planBox',
				//hidden:true,
				id:'planBox_D301',
			},{
				width : 85,
				text : $i18n.planQmin,//计划中包数
				dataIndex : 'planQmin',
				id:'planQmin_D301',
			},{
				width : 85,
				text : $i18n.planDis,//计划散数
				dataIndex : 'planDis',
				id:'planDis_D301',
			},
			{
				width : 90,
				text : $i18n.realQty,//实际调账数
				dataIndex:'articleQty'
			},
			{
				width : 85,
				text : $i18n.realBox,	//实际调账箱数
				dataIndex : 'realBox',
				//hidden:true,
				id:'planBox_D301_1',
			},{
				width : 85,
				text : $i18n.realQmin,//实际调账中包数
				dataIndex : 'realQmin',
				id:'planQmin_D301_1',
			},{
				width : 85,
				text : $i18n.realDis,//实际调账散数
				dataIndex : 'realDis',
				id:'planDis_D301_1',
			},
			{
				width : 80,
				text : $i18n.produce_date,//生产日期
				hidden:true,
				dataIndex:'produceDate',
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
				hidden:true,
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
				dataIndex:'qualityText'
			},{
				width:60,
				text:$i18n.lot_no,//'批号',
				hidden:true,
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
		}]
	},
	{
		region:'south'
	}
	]
});
