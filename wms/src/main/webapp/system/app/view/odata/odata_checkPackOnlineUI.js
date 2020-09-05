/**
 * 模块名称：出货整理打包-电商
 * 模块代码：3916
 * chensr
 */ 
Ext.define('cms.view.odata.odata_checkPackOnlineUI',{
	alias:'widget.odata_checkPackOnlineUI',
	title:'电商复核打包',//出货整理打包
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu10',
	          'cms.view.common.bdef_DefWorkerCombo',
	          'cms.view.common.remoteCombo',
	          'cms.view.common.commMenu5'
	          
	          ],
	items:[
	{
		xtype:'commMenuWidget10',
	    id:'menu3916',
	    region:'north'
	},
	{   xtype:'tabpanel',
		id:'tabPId3916',
	    region:'center',
	    items:[
	    {
	    	title:'复核作业',
	    	layout:'border',
	    	items:[
		    	{
		    		xtype : 'form',
		    		region:'west',
		    		width:'52%',
		    		layout: 
		    		{
		    			type: 'table',
		    			columns: 2
		    		},
		    		defaults : 
		    		{
		    			xtype : 'textfield',
		    			labelWidth : 90,
		    			margin:'5 2 1 1 ',
		    			labelAlign:'right',
		    			width:220
		    	    },
		    	    items :[{
		            		//xtype : 'remoteCombo',
		    				fieldLabel:$i18n.check_platno,//复核台号
		    				id : 'checkPlatNo3916',	
		    				enableKeyEvents:true,
		    				/*store:Ext.create("cms.store.ridata.ridata_DockComboStore",
		    				{
		    					proxy:{
		    						type:'ajax',
		    						method:'post',
		    						url:'ridata_CheckAction_queryDockCombo.action',
		    						reader:{
		    							root:'rootList',
		    							totalProperty:'totalCount'
		    						}
		    					}
		    				}),*/
		    		        beforeLabelTextTpl : required
		    			},{
		    				//xtype : 'bdef_DefWorkerCombo',
		    				id:'cmbCheckWorker3916',
		    				enableKeyEvents:true,
		    				//colspan:2,
		    				fieldLabel : $i18n.worker_name1,//复核员姓名
		    				//store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore').load(),
		    				beforeLabelTextTpl : required
		    			},{
		        			xtype : 'radiogroup',
		        			id : 'rdoCheckType3916',
		        			//margins: '0 0 0 10',
		        			fieldLabel :'复核类型',//colspan:2,
		        			width : 300,
		        			columns : 2,
		        			items : [
		        	        {
		        				boxLabel : '任务号/箱号',
		        				name : 'rd',
		        				inputValue : '1',
		        				checked:true
		        			},
		        			{
		        				boxLabel : '快递单号',
		        				name : 'rd',
		        				inputValue : '2'
		        			}
		        			]
		    			},{
		    				xtype: 'button',
		                	text: '未复核列表',
		                	margin : '0 0 0 20',
		                	width:100,
		    	        	id:'unCheck3916'
		    			},{
		    				fieldLabel:'任务号/箱号',//任务号
		    				id:'checkNo3916',
		    				enableKeyEvents:true,
		    				beforeLabelTextTpl:required
		    			},{
		    				fieldLabel:'箱号', //箱号
		    				hidden:true,
		    				id:'loadBoxs3916'
		    			},{
		    				fieldLabel:'快递单号',
		    				enableKeyEvents:true,
		    				id:'checkNo3916_2',
		    				hidden:true,
		    				beforeLabelTextTpl:required
		    			},
		    			{
	    					xtype:'label',
		    				readOnly:true,
		    				id:'3916Rsv_varod3',
		    				cls:'classDiv6',
		    				margin: '0 0 0 25',
		    				//text:'不布控'
	    				},
		    			{
		    				fieldLabel:$i18n.barcode,//条码
		    				id:'barcode3916',
		    				//colspan:2,
		    				//width:320,
		    				beforeLabelTextTpl:required,
		    				enableKeyEvents:true
		    			},{
		    				fieldLabel:'扫描基准量',//扫描基准量
		    				id:'sacnNum3916',
		    				xtype : 'numberfield',
		    				width:160,colspan:2,
		    				beforeLabelTextTpl:required
		    				//readOnly:true		
		    			},{
		    				xtype: 'button',
		                	text: '修改基准量',hidden:true,
		                	margin : '0 0 0 5',
		                	width:100,
		                	id:'editNum3916'			
		    			},{
		    				xtype:'container',
		    				margin:'10 0 10 10',
		    				colspan:3,width:510,
		    				items:[
		    				{
		    					xtype:'label',
		    					readOnly:true,
		    					cls:'classDiv1',
		    					text:'商品名称：'
		    				},
		    				{
		    					xtype:'label',
		    					readOnly:true,
		    					id:'articleName3916',//width:310,
		    					margin:'0 0 0 10',
		    					cls:'classDiv1',
		    					//text:'[702034]迪麦特级越南平阳八婆腰果500g'
		    				}]
		    			},
		    			{
		    				xtype: 'button',
		                	text: '测试',
		                	margin : '3 3 3 80',
		                	height:40,
		                	width:150,
		                	hidden : true,
		                	id:'btnCloseBox3916'
		    			},{
		    				xtype:'container',
		    				colspan:2,width:310,
		    				margin:'0 0 0 16',
		    				//width:510,
		    				items:[
							{
								xtype:'label',
								readOnly:true,
								margin: '0 0 0 -6',
								cls:'classDiv1',
								text:'建议包材：'
							},
		    				{
	    					xtype:'label',
	    					readOnly:true,
	    					id:'packmet_name',
	    					margin:'0 0 0 10',
	    					cls:'classDiv1',
	    					//text:'包材名称:'
	    				},]
		    			
			    	    
		    			},{

		    				fieldLabel:'复核单号',//为了控制器判断用
		    				id:'odata_checkNo3916',
		    				hidden:true
		    			},{
		    				fieldLabel:'配送对象',//为了控制器判断用
		    				id:'deliverObj3916',
		    				hidden:true
		    			},		
		            ]
		        },
		        {
		        	region:'east',
		        	width:'48%',
		        	layout: 
		    		{
		    			type: 'table',
		    			columns: 1
		    		},
		    		defaults : 
		    		{
		    			//labelWidth : 200,
		    			margin:'5 2 1 1 ',
		    			labelAlign:'right',
		    			width:500
		    	    },
		    	    items :[{
						xtype:'fieldset',
						//title:'打印选择',
					    layout: 
					    {
					        type: 'table',
					        columns: 4
					    },
					    defaults : 
					    {
			                width:100,
				            labelAlign:'right'			
					    },
					    items:[{

							xtype: 'button',
							text: '打印快递面单',
							margin : '0 0 0 20',
							width:100,
							id:'checkExpressNo3916'
					    },{
							xtype: 'button',
							text: '打印发票',
							margin : '0 0 0 20',
							width:100,
							id:'checkInvoiceNo3916'
						},
			            {
				        	xtype: 'button',
							text: '打印装箱单',
							margin : '0 0 0 20',
							width:100,
							id:'checkBoxNo3916'
				        },{
				        	xtype: 'checkboxfield',
				        	fieldLabel: '跳过拣货',
				        	id:'checkNoOut3916'
				        }] 
					  
		    	    },{
		    	    	xtype:'container',
		    			margin:'0 0 0 10',//width:150,
		    			items:[
		    			{
		    				xtype:'label',
		    				readOnly:true,
		    				cls:'classDiv2',
		    				text:'未复核单数：'
		    			},
		    			{
		    				xtype:'label',
		    				readOnly:true,
		    				id:'nQty3916',
		    				margin:'0 0 0 5',
		    				cls:'classDiv3',
		    				text:'0'
		    			}]
		    	    },{

		    			xtype:'container',
		    	        height:90,
		            	width:100,	margin:'5 0 0 20',
		    	        id:'investReportImage',
		    	        hidden:true,
		    	        autoEl: {  
		                     tag: 'img',    //指定为img标签  
		                     src: 'system/images/yes2.png'    //指定url路径 ,一般为相对路径 
		                }  
		    		
		    	    }]
		        
		        },
		        {
		        	xtype:'panel',
		        	region:'south',
		        	layout:'border',
		        	height:300,
		        	items:[
		        	{
		    	    	xtype:'grid',
		    	    	region:'west',
		    	    	width:'50%',
		    	    	id:'gridCheckD3916',
		    	    	title:'未复核商品信息',
		    	    	store:Ext.create('cms.store.odata.odata_CheckDStore',{
		    				  proxy:{
		    						type:'ajax',
		    						method:'post',
		    						url:'odata_CheckPackOnlineAction_getCheckD.action',
		    						reader:{
		    							root:'rootList',
		    							totalProperty:'totalCount'
		    						}
		    				  }/*,listeners:{
		    						'load':function(th,records,successful,eOpts){
		    							var gridcount=Ext.getCmp('gridPackLabel3916').getStore().getCount();
		    							for(var i=0;i<gridcount;i++){
		    								var f=0;
		    								var count=0;
		    								var record=Ext.getCmp('gridPackLabel3916').getStore().getAt(i);
		    								if(record.get('status')=="6A"){	
		    									
		    									count=Ext.getCmp('gridCheckD3916').getStore().getCount();
		    									for(var j=0;j<count;j++){
		    										var r=Ext.getCmp('gridCheckD3916').getStore().getAt(j);
		    										if(record.get('labelNo')==r.get('lableNo')){	
		    											f=1;
		    										}		
		    									}
		    									if(f==0 && gridcount!=0 && count!=0){
		    										Ext.getCmp('loadBoxs3916').setValue(record.get('labelNo'));
		    										return;
		    									}				
		    								}		
		    							}
		    						}
		    					}*/
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
		    			    width:180,
		    			    text : $i18n.article_name,//商品名称
		    			    dataIndex:'articleName'			
		    		    },{
		    			    width:60,
		    			    text : '拣货货位',
		    			    dataIndex:'advanceCellNo'			
		    		    },
		    		    {
		    			    width:60,
		    			    text : $i18n.qty1,//数量
		    			    hidden:true,
		    			    dataIndex:'uncheckQty'			
		    		    },{
							width : 75,
							text : $i18n.boxQty,
							dataIndex : 'planBox',
							id:'planBox3916'
						},{
							width : 75,
							text : $i18n.qminQty,
							dataIndex : 'planQmin',
							id:'planQmin3916'
						},{
							width : 75,
							text : $i18n.disQty,
							dataIndex : 'planDis',
							id:'planDis3916'
						},
		    			{
		    				width : 60,
		    				text : $i18n.packing_qty,//包装数量
		    				dataIndex : 'packingQty'
		    			},
		    			{
		    				width : 60,
		    				text : $i18n.packing_qty1,//包装单位
		    				id:'packingUnit_3916',
		    				dataIndex:'packingUnit'
		    			},
		    			{
		    				width:80,
		    				text:$i18n.packingSpec,//规格
		    				id:'packingSpec_3916',
		    				dataIndex:'packingSpec'
		    			}]
		    	    },
		    	    {
		    	    	xtype:'grid',
		    	    	region:'east',
		    	    	width:'50%',
		    	    	id:'gridDlabelD3916',
		    	    	title:'已复核商品信息',
		    			store:Ext.create('cms.store.odata.odata_ArrangeLabelDStore',{
		    				  proxy:{
		    						type:'ajax',
		    						method:'post',
		    						url:'odata_CheckPackOnlineAction_getStockLabel.action',
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
		    			    width:220,
		    			    text : $i18n.article_name,//商品名称
		    			    dataIndex:'articleName'			
		    		    },
		    		    {
		    			    width:60,
		    			    text : $i18n.qty1,//数量
		    			    hidden:true,
		    			    dataIndex:'qty'			
		    		    },{
							width : 75,
							text :$i18n.boxQty,
							dataIndex : 'realBox',
							id:'realBox3916'
						},{
							width : 75,
							text :$i18n.qminQty,
							dataIndex : 'realQmin',
							id:'realQmin3916'
						},{
							width : 75,
							text : $i18n.disQty,
							dataIndex : 'realDis',
							id:'realDis3916'
						},
		    			{
		    				width : 60,
		    				text : $i18n.packing_qty,//包装数量
		    				dataIndex : 'packingQty',
		    				//hidden:true
		    			}
		    		/*	{
		    				width : 60,
		    				text : $i18n.packing_qty1,//包装单位
		    				id:'packingUnit_3916_1',
		    				dataIndex:'packingUnit'
		    			},
		    			{
		    				width:80,
		    				text:$i18n.packingSpec,//规格
		    				id:'packingSpec_3916_1',
		    				dataIndex:'packingSpec'
		    			}*/]
		    	    }
		            ]
		    	}]
	    },{
	    	title:'补印中心',
	    	layout:'border',
	    	itemId:'tabPId3916i',
	    	id:'tabPId3916i',
	    }]
		
    }
    ]
});