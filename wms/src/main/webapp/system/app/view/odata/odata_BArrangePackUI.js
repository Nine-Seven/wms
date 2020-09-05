/**
 * 模块名称：拆零出货整理打包（小嘴，按客户复核）
 * 模块代码：3928
 * @author hkl
 */ 
Ext.define('cms.view.odata.odata_BArrangePackUI',{
	alias:'widget.odata_BArrangePackUI',
	title:'拆零复核打包',//
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu10',
	          'cms.view.common.bdef_DefWorkerCombo',
	          'cms.view.common.remoteCombo'
	          ],
	items:[
	{
		xtype:'commMenuWidget10',
	    id:'menu3928',
	    region:'north'
	},
	{
		xtype:'tabpanel',
		id:'tabPId3928',
	    region:'center',
	    items:[
	    {
	    	title:'复核',
	    	layout:'border',
	    	items:[
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
		    			labelWidth : 100,
		    			margin:'5 10 0 5 ',
		    			labelAlign:'right',
		    			width:230
		    	    },
		    	    items :[{

		    			xtype : 'radiogroup',
		    			id : 'rdoCheckType3928',
		    			margins: '0 0 0 30',
		    			fieldLabel :'请选择扫描方式',
		    			colspan:2,
		    			width : 400,
		    			columns : 2,
		    			items : [
		    	        {
		    				boxLabel : '逐渐扫描',
		    				name : 'rd',
		    				inputValue : '0',
		    				checked:true
		    			},
		    			{
		    				boxLabel : '选择商品修改数量',
		    				name : 'rd',
		    				inputValue : '1'
		    			}
		    			]
		    		   
		    	    },{
		    				fieldLabel:$i18n.check_platno,//复核台号
		    				id : 'checkPlatNo3928',	
		    		        beforeLabelTextTpl : required
		    			},{
		    				xtype : 'bdef_DefWorkerCombo',
		    				id:'cmbCheckWorker3928',
		    				fieldLabel : $i18n.worker_name1,//复核员姓名
		    				store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore').load(),
		    				beforeLabelTextTpl : required
		    			},{
		    				fieldLabel:$i18n.label_no,//标签号码
		    				id:'labelNo3928',
		    				//colspan:2,
		    				beforeLabelTextTpl:required
		    			},{
		    				fieldLabel:'待复核总箱数',
		    				id:'boxsQty3928',
		    				disabled:true,
		    				fieldCls:'classDiv1'
		    			},{
		    				fieldLabel:$i18n.cust_no,//客户
		    				id:'custNo3928',
		    				width:485,
		    				disabled:true,
		    				fieldCls:'classDiv1',
		    				colspan:2
		    			},{
		    				fieldLabel:$i18n.barcode,//条码
		    				id:'barcode3928',
		    				beforeLabelTextTpl:required
		    			},{
		    				fieldLabel:'数量',//扫描基准量
		    				//xtype:'numberfield',
		    				id:'sacnNum3928',
		    				//minValue:1,
		    				regex: /^\d+$/,
		    				regexText: '只能复核最小操作包装的整数倍，不能输入小数点',
		    				beforeLabelTextTpl:required,	
		    			},{
		    				fieldLabel:$i18n.close_label,
		    				disabled:true,
		    				//colspan:2,
		    				id:'loadBoxs3928'
		    			},{
		    				fieldLabel:$i18n.maxweight,//重量
		    				//disabled:true,
		    				//colspan:2,
		    				id:'weight3928',
		    			    beforeLabelTextTpl:required
		    			},{
		    				xtype: 'button',
		                	text: '重扫',
		                	margin : '3 3 3 80',
		                	width:100,
		                	id:'btnAnew3928'
		    			},{
		    				xtype:'container',
		    				margin:'5 0 0 83',
		    				items:[
		    				{
		    					xtype:'label',
		    					readOnly:true,
		    					cls:'classDiv1',
		    					text:'已扫数量：'
		    				},
		    				{
		    					xtype:'label',
		    					readOnly:true,
		    					id:'nQty3928',
		    					margin:'0 0 0 10',
		    					cls:'classDiv1',
		    					text:'0'
		    				}]
		    			},{
		    				xtype: 'button',
		                	text: '回单',
		                	margin : '3 3 3 80',
		                	width:100,
		                	id:'btnReceipt3928'
		    			},{
		    				xtype: 'button',
		                	text: '封箱',
		                	margin : '3 3 3 80',
		                	width:100,
		                	id:'btnCloseBox3928'
		    			}		
		            ]
		        },
		        {
		        	xtype:'grid',
		        	region:'east',
		        	width:'49%',
		        	id:'gridCusLabel3928',
		        	store:Ext.create('cms.store.odata.odata_ArrangeCusLabelStore',{
		        		listeners:{  
		    				'load':function(th,records,successful,eOpts ){
		    					if(th.count()>0)
		    					{
		    						Ext.getCmp('loadBoxs3928').setValue(records[0].data.labelNo);
		    						Ext.getCmp('gridCusLabel3928').getSelectionModel().select(0);
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
		        	height:290,
		        	items:[
		        	{
		        		title:'未复合商品信息',
		    	    	xtype:'grid',
		    	    	region:'west',
		    	    	width:'50%',
		    	    	id:'gridSlabelD3928',
		    	    	store:Ext.create('cms.store.odata.odata_CheckDStore',{
		    				  proxy:{
		    						type:'ajax',
		    						async:false,
		    						method:'post',
		    						url:'odata_BArrangePackAction_getUnCheckLabelD.action',
		    						reader:{
		    							root:'rootList',
		    							totalProperty:'totalCount'
		    						}
		    				  },
		    					listeners:{  
		    						'load':function(th,records,successful,eOpts ){
		    							if(th.count()==0)
		    							{
		    						    	Ext.getCmp('barcode3928').setValue('');
		    						    	Ext.getCmp('labelNo3928').setValue("");
		    						    	Ext.getCmp('loadBoxs3928').setValue('');
		    						    	Ext.getCmp('gridCusLabel3928').getStore().removeAll();
		    								Ext.getCmp('gridCusLabel3928').getStore().load();
		    								Ext.getCmp('labelNo3928').focus(false,10);
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
		    				    },{
		    				    	width:60,
		    						text : '规格',
		    						dataIndex:'qminOperateSpec'		
		    				    },{
		    				    	width:60,
		    						text : '单位',
		    						dataIndex:'qminOperateUnit'		
		    				    },{
		    					    width:60,
		    					    text : '拣货货位',
		    					    dataIndex:'advanceCellNo'			
		    				    },
		    				    {
		    				    	width : 60,
		    						text : '最小操作包装数量',
		    						hidden:true,
		    						dataIndex : 'qminOperatePacking'
		    				    },
		    					{
		    						width : 60,
		    						text : '包装数量',
		    						hidden:true,
		    						dataIndex : 'packingQty'
		    					},
		    					{
		    						width : 90,
		    						text : $i18n.produce_date,//生产日期
		    						dataIndex:'produceDate',
		    						//hidden:true,
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
		    						width:40,
		    						text:$i18n.quality,//'品质',
		    						hidden:true,
		    						dataIndex:'textQuality'
		    					},
		    					{
		    						width:80,
		    						text:$i18n.import_batch_no,//验收批次
		    						hidden:true,
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
		    	    	id:'gridDlabelD3928',
		    			store:Ext.create('cms.store.odata.odata_CheckDStore',{
		    				  proxy:{
		    						type:'ajax',
		    						method:'post',
		    						url:'odata_BArrangePackAction_getStockLabelD.action',
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
		    			    dataIndex:'realQty'			
		    		    },{
		    		    	width:60,
		    				text : '规格',
		    				dataIndex:'qminOperateSpec'		
		    		    },{
		    		    	width:60,
		    				text : '单位',
		    				dataIndex:'qminOperateUnit'		
		    		    }]
		    	    }
		            ]
		    	}]
	    },{
	    	title:'未封箱箱号查询',
	    	layout:'border',
	    	items:[
		    	{
		    		region:'center',
		    	    xtype:'grid',
		    		id:'gridNoCheckBox3928',
	    	    	store:Ext.create('cms.store.odata.odata_CheckDStore',{
	    				  proxy:{
	    						type:'ajax',
	    						async:false,
	    						method:'post',
	    						url:'odata_BArrangePackAction_getCheckLabelDList.action',
	    						reader:{
	    							root:'rootList',
	    							totalProperty:'totalCount'
	    						}
	    				  },
	    				  autoLoad:true
	        		}),	    
		    	    columns:[{
		    	        xtype:'rownumberer',
		    	       	width:30 
		    	    },{
		    	    	width:130,
					    text : '复核单号',
					    dataIndex:'checkNo'			
		    	    },{
		    	    	width:110,
					    text : $i18n.cust_no,
					    dataIndex:'custNo'			
		    	    },{
					    width:130,
					    text : $i18n.cust_alias,
					    dataIndex:'custAlias'			
				    },{
					    width:80,
					    text : $i18n.check_platno,
					    dataIndex:'checkChuteNo'			
				    },{
		    	    	width:130,
					    text : '来源标签号',//来源标签号
					    dataIndex:'lableNo'			
		    	    },{
		    	    	width:110,
					    text : $i18n.d_label_no,
					    dataIndex:'dLabelNo'			
		    	    },{
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
					    text : '已复核数量',//数量
					    dataIndex:'realQminQty'			
				    },{
				    	width:60,
						text : '单位',
						dataIndex:'qminOperateUnit'		
				    }]
		    	
		    	}]
	    }]
	
    }
    ]
});