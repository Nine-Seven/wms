/**
 * 模块名称：出货整理打包（天天惠，按客户复核）
 * 模块代码：3913
 * @author lich
 */ 
Ext.define('cms.view.odata.odata_ArrangePackUI',{
	alias:'widget.odata_ArrangePackUI',
	title:$i18n.title3913,//出货整理打包
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu4',
	          'cms.view.common.bdef_DefWorkerCombo',
	          'cms.view.common.remoteCombo'
	          ],
	items:[
	{
		xtype:'commMenuWidget4',
	    id:'menu3913',
	    region:'north'
	},
	{
		xtype : 'form',
		region:'west',
		width:'49%',
		layout: 
		{
			type: 'table',
			columns: 2
		},
		defaults : 
		{
			xtype : 'textfield',
			labelWidth : 80,
			margin:'5 10 0 5 ',
			labelAlign:'right',
			width:200
	    },
	    items :[{
				fieldLabel:$i18n.check_platno,//复核台号
				id : 'checkPlatNo3913',	
		        beforeLabelTextTpl : required
			},{
				xtype : 'bdef_DefWorkerCombo',
				id:'cmbCheckWorker3913',
				fieldLabel : $i18n.worker_name1,//复核员姓名
				store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore').load(),
				beforeLabelTextTpl : required
			},{
				fieldLabel:$i18n.label_no,//标签号码
				id:'labelNo3913',
				colspan:2,
				beforeLabelTextTpl:required
			},{
				fieldLabel:$i18n.cust_no,//客户
				id:'custNo3913',
				width:485,
				disabled:true,
				fieldCls:'classDiv1',
				colspan:2
			},{
				fieldLabel:$i18n.barcode,//条码
				id:'barcode3913',
				beforeLabelTextTpl:required
			},{
				fieldLabel:'扫描基准量',//扫描基准量
				xtype:'numberfield',
				id:'sacnNum3913',
				//minValue:1,
				beforeLabelTextTpl:required,	
			},{
				fieldLabel:$i18n.close_label,
				disabled:true,
				colspan:2,
				id:'loadBoxs3913'
			},{
				xtype:'container',
				margin:'5 0 0 83',
				items:[
				{
					xtype:'label',
					readOnly:true,
					cls:'classDiv1',
					text:'数量：'
				},
				{
					xtype:'label',
					readOnly:true,
					id:'nQty3913',
					margin:'0 0 0 10',
					cls:'classDiv1',
					text:'0'
				}]
			},{
				xtype: 'button',
            	text: '重扫',
            	margin : '3 3 3 80',
            	width:100,
            	id:'btnAnew3913'
			},{
				xtype: 'button',
            	text: '封箱',
            	margin : '3 3 3 80',
            	width:100,
            	id:'btnCloseBox3913'
			},{
				xtype: 'button',
            	text: '回单',
            	margin : '3 3 3 80',
            	width:100,
            	id:'btnReceipt3913'
			}		
        ]
    },
    {
    	xtype:'grid',
    	region:'east',
    	width:'49%',
    	id:'gridCusLabel3913',
    	store:Ext.create('cms.store.odata.odata_ArrangeCusLabelStore',{
    		listeners:{  
				'load':function(th,records,successful,eOpts ){
					if(th.count()>0)
					{
						Ext.getCmp('loadBoxs3913').setValue(records[0].data.labelNo);
						Ext.getCmp('gridCusLabel3913').getSelectionModel().select(0);
					}
				}
			}
    	}),
	    columns:[
	    {			
	        xtype : 'rownumberer',
		    width : 30
	    },{
		    width:105,
		    text : $i18n.check_platno,//复核台号
		    dataIndex:'checkChuteNo'			
	    },
	    {
		    width:105,
		    text : $i18n.label_no,//标签号码
		    dataIndex:'labelNo'			
	    },
	    {
		    width:105,
		    text : $i18n.status,//状态
		    dataIndex:'statusText'			
	    }]
    },
    {
    	xtype:'panel',
    	region:'south',
    	layout:'border',
    	height:300,
    	items:[
    	{
    		title:'未复合商品信息',
	    	xtype:'grid',
	    	region:'west',
	    	width:'50%',
	    	id:'gridSlabelD3913',
	    	store:Ext.create('cms.store.odata.odata_CheckDStore',{
				  proxy:{
						type:'ajax',
						async:false,
						method:'post',
						url:'odata_ArrangePackAction_getUnCheckLabelD.action',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						},
						extraParams:{
							strFlag : "2"
						}
				  },
					listeners:{  
						'load':function(th,records,successful,eOpts ){
							if(th.count()==0)
							{
						    	Ext.getCmp('barcode3913').setValue('');
						    	Ext.getCmp('labelNo3913').setValue("");
						    	Ext.getCmp('loadBoxs3913').setValue('');
						    	Ext.getCmp('gridCusLabel3913').getStore().removeAll();
								Ext.getCmp('gridCusLabel3913').getStore().load();
								Ext.getCmp('labelNo3913').focus(false,10);
							}
						}
					}
    		}),	    
		    columns:[
		 		    {			
				        xtype : 'rownumberer',
					    width : 30
				    },
				    {
					    width:130,
					    text : $i18n.barcode,//商品条码
					    dataIndex:'barcode'			
				    },
				    {
					    width:200,
					    text : $i18n.article_name,//商品名称
					    dataIndex:'articleName'			
				    },
				    {
					    width:60,
					    text : $i18n.qty1,//数量
					    dataIndex:'uncheckQty'			
				    },
					{
						width : 60,
						text : $i18n.packing_qty,//包装数量
						dataIndex : 'packingQty'
					},
					{
						width : 60,
						text : $i18n.packing_qty1,//包装单位
						id:'packingUnit_3913',
						dataIndex:'packingUnit'
					},
					{
						width:80,
						text:$i18n.packingSpec,//规格
						id:'packingSpec_3913',
						dataIndex:'packingSpec'
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
	    },
	    {
	    	title:'已复核商品信息',
	    	xtype:'grid',
	    	region:'east',
	    	width:'50%',
	    	id:'gridDlabelD3913',
			store:Ext.create('cms.store.odata.odata_ArrangeLabelDStore',{
				  proxy:{
						type:'ajax',
						method:'post',
						url:'odata_ArrangePackAction_getStockLabelD.action',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						},
						extraParams:{
							strFlag : "1"
						}
				  }
    		}),	    	
		    columns:[
		    {			
		        xtype : 'rownumberer',
			    width : 30
		    },
		    {
			    width:150,
			    text : $i18n.barcode,//商品条码
			    dataIndex:'barcode'			
		    },
		    {
			    width:200,
			    text : $i18n.article_name,//商品名称
			    dataIndex:'articleName'			
		    },
		    {
			    width:60,
			    text : $i18n.qty1,//数量
			    dataIndex:'qty'			
		    },{
		    	width : 60,
				text : $i18n.packing_qty,//包装数量
				dataIndex : 'packingQty'
		    },{
				width : 60,
				text : $i18n.packing_qty1,//包装单位
				id:'packingUnit_3913_1',
				dataIndex:'packingUnit'
			},
			{
				width:80,
				text:$i18n.packingSpec,//规格
				id:'packingSpec_3913_1',
				dataIndex:'packingSpec'
			}]
	    }
        ]
    }
    ]
});