/**
 * 模块名称：退厂扫描
 * 模块编码：7304
 * 创建 ： chensr
 */
//var rodata_ScanTTH7304_1=Ext.create('cms.store.rodata.rodata_ScanTTHStore');                                  
Ext.define('cms.view.rodata.rodata_ScanTTHUI',{
	alias:'widget.rodata_ScanTTHUI',
	title:'退厂扫描',//退厂扫描
	id:'rodata_ScanTTHUI7304',
	width:'100%',
	layout:'border',
	extend : 'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu4',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.bdef_DefWorkerCombo',
	          'cms.view.common.remoteCombo',
	          'cms.view.common.ridata_UntreadNoCombo',
	          ],
	items:[
	       {
	    	   xtype:'commMenuWidget4',
	   		   region:'north',
	   		   id:'menu7304'
	       },{
				xtype : 'form',
				layout:'column',
				region : 'east',
				frame : true,
				width : '51%',
				items:[
				       {
					layout:{
					type : 'table',
					columns : 2
					},
					xtype:'container',
					margin:'20 0 0 0',
					defaults:{
						labelWidth : 90,
						margin : '2 0 5 0',
						labelAlign : 'right',
						xtype:'textfield'
					},
					items:[
					{
						xtype : 'bdef_DefWorkerCombo',
						fieldLabel : '扫描人',// 扫描人
						width:240,
						id:'scanNo7304',
						store : Ext.create("cms.store.bdef.bdef_DefworkerComboStore"),
						beforeLabelTextTpl : required
					},{
						fieldLabel :$i18n.outstock_no,// 下架单号
						id:'OutstockNo7304',
						margin:'5 0 0 50',
						beforeLabelTextTpl : required
					},
					{
						xtype : 'textfield',
						width:360,
						hidden:true,
						fieldLabel : $i18n.owner_no,// 货主
						id:'ownerNo7304',	
						beforeLabelTextTpl:required
						
					},
					{
						xtype : 'textfield',
						width:240,
						fieldLabel : $i18n.barcode,// 商品条码
						id:'barcode7304',	
						colspan:2,
						beforeLabelTextTpl:required
						
					},{
						xtype : 'textfield',
						width:240,
						fieldLabel : $i18n.article_no,//商品编码
						id:'articleNo7304',
						//colspan:2,
						readOnly:true
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
							id:'nQty7304',
							margin:'0 0 0 10',
							cls:'classDiv1',
							text:'0'
						}]
					},{
						xtype : 'textfield',
						fieldLabel : $i18n.article_name,//商品名称
						width:360,
						id:'articleName7304',
						colspan:2,
						//disabled:true,
						readOnly:true
					},{
						fieldLabel:$i18n.close_label,//封箱标签
						width:240,
						readOnly:true,
						id:'loadBoxs7304'
					},{
						xtype: 'button',
						//region:'east',
		            	text: '封箱',
		            	margin : '3 3 3 20',
		            	width:100,
		            	id:'btnCloseBox7304'
					},{
						xtype: 'button',
						//region:'east',
		            	text: '确认保存',
		            	margin : '3 3 3 35',
		            	width:100,
		            	id:'btnSaveConfirm7304'
					}]
				}]
	         }, {
	         	xtype:'grid',
	        	region:'east',
	        	width:'49%',
	        	id:'gridPackLabel7304',
	        	store:Ext.create('cms.store.rodata.rodata_ScanDTTHStore',{
	    			  proxy:{
	    					type:'ajax',
	    					method:'post',
	    					url:'rodata_ScanTTHAction_getScanPackLabel.action',
	    					reader:{
	    						root:'rootList',
	    						totalProperty:'totalCount'
	    					}
	    			  },
	    			  listeners:{  
							'load':function(th,records,successful,eOpts ){
								if(Ext.getCmp('gridPackLabel7304').getStore().count()>0){
									Ext.getCmp('ownerNo7304').setValue(Ext.getCmp('gridPackLabel7304').getStore().getAt(0).data.ownerNo);
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
	    		    text : $i18n.outstock_no,//下架单号
	    		    dataIndex:'outstockNo'			
	    	    },
	    	    {
	    		    width:105,
	    		    text : '箱号',//标签号码
	    		    dataIndex:'labelNo'			
	    	    },{
	    		    width:105,
	    		    text : $i18n.status_name,//状态
	    		    dataIndex:'statusText'			
	    	    }]
	        },{

	        	xtype:'panel',
	        	region:'south',
	        	layout:'border',
	        	height:320,
	        	items:[
	        	{
	    	    	xtype:'grid',
	    	    	region:'west',
	    	    	width:'51%',
	    	    	id:'gridrodata_ScanTTH7304_3',
	    	    	title:'未扫描商品信息',
	    	    	store:Ext.create('cms.store.rodata.rodata_ScanDTTHStore'),	    
	    		    columns:[
	    		    {			
	    		        xtype : 'rownumberer',
	    			    width : 30
	    		    },{
						width : 100,
						text : 'ERP退货单号',//ERP退货单号
						id:'po_no7304_2',
						dataIndex:'poNo'
					},
	    		    {
	    			    width:110,
	    			    text : $i18n.barcode,//商品条码
	    			    dataIndex:'barcode'			
	    		    },
	    		    {
	    			    width:260,
	    			    text : $i18n.article_name,//商品名称
	    			    dataIndex:'articleName'			
	    		    },
	    		    {
	    			    width:60,
	    			    text : $i18n.qty1,//未扫描数量
	    			    dataIndex:'outstockQty'			
	    		    },
	    			{
	    				width : 60,
	    				text : $i18n.packing_qty,//包装数量
	    				dataIndex : 'packingQty',
	    				hidden:true
	    			},{
						width : 80,
						text : $i18n.packing_unit,//包装单位
						dataIndex:'packingUnit',
						id:'packingUnit7304_3'
					},{
						width : 85,
						text : $i18n.packingSpec,//规格
						dataIndex : 'packingSpec',
						id:'packingSpec7304_3'
					}]
	    	    },
	    	    {
	    	    	xtype:'grid',
	    	    	region:'east',
	    	    	width:'49%',
	    	    	id:'gridrodata_ScanTTH7304_4',
	    	    	title:'已扫描商品信息',
	    			store:Ext.create('cms.store.rodata.rodata_ScanDTTHStore',{
	    				  autoLoad:false,
	    				  proxy:{
	    						type:'ajax',
	    						method:'post',
	    						url:'rodata_ScanTTHAction_getRodata_ScanDNot.action',
	    						reader:{
	    							root:'rootList',
	    							totalProperty:'totalCount'
	    						}
	    				  }
	        		}),	    	
	    		    columns:[
	    		    {			
	    		        xtype : 'rownumberer',
	    			    width : 30
	    		    },{
						width : 100,
						text : '箱号',//箱号
						id:'label_no7304_2',
						dataIndex:'labelNo'
					}, 
	    		    {
	    			    width:110,
	    			    text : $i18n.barcode,//商品条码
	    			    dataIndex:'barcode'			
	    		    },
	    		    {
	    			    width:250,
	    			    text : $i18n.article_name,//商品名称
	    			    dataIndex:'articleName'			
	    		    },
	    		    {
	    			    width:60,
	    			    text : $i18n.qty1,//数量
	    			    dataIndex:'realQty'			
	    		    },
	    			{
	    				width : 60,
	    				text : $i18n.packing_qty,//包装数量
	    				dataIndex : 'packingQty',
	    				hidden:true
	    			},
	    		    {
						width : 80,
						text : $i18n.packing_unit,//包装单位
						dataIndex:'packingUnit',
						id:'packingUnit7304_4'
					},{
						width : 85,
						text : $i18n.packingSpec,//规格
						dataIndex : 'packingSpec',
						id:'packingSpec7304_4'
					}
	    			
	    		]
	    	    }
	            ]
	        
	        	
	        	
	        	
	        	
	        	
	        	/*
					xtype : 'grid',
					id:'gridrodata_ScanTTH7304_3',
					region : 'south',
		    	    selModel: {  
		            	selType:'cellmodel' 
		    	    },
					store:gridrodata_ScanTTH7304_3,
					height : 320,
					columns : [ 
					 {
						xtype : 'rownumberer',
						width : 30
					}, {
						width : 140,
						text : $i18n.recede_no,//退货单号
						id:'recede_no7304_2',
						dataIndex:'sourceNo'
					}, 
					{
						width : 120,
						text : $i18n.barcode,//商品条码
						id:'barcode7304_2',
						dataIndex:'barcode'
					}, {
						width : 120,
						text : $i18n.article_no,//商品编码
						id:'article_no7304_2',
						dataIndex:'articleNo'
					}, {
						width : 100,
						text : $i18n.owner_article_no,//货主商品编码
						id:'ownerArticleNo7304_2',
						dataIndex : 'ownerArticleNo'
					},{
						width : 210,
						text : $i18n.article_name,//商品名称
						id:'article_name7304_2',
						dataIndex:'articleName'
					},{
						width : 80,
						text : '计划数量',//计划数量
						id:'scanNum7304_2',
						dataIndex:'outstockQty'
					},{
						width : 80,
						text : '扫描数量',//扫描数量
						id:'realQty7304_2',
						dataIndex:'realQty'
					}]
				*/}
	       ]          
});