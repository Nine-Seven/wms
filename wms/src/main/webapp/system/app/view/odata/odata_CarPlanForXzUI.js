/**
 * 模块名称：出货装车(小嘴)
 * 模块编码：3802
 * 创建：hcx
 */
Ext.define('cms.view.odata.odata_CarPlanForXzUI',{
	alias:'widget.odata_CarPlanForXzUI',
	title:$i18n.title3801,//出货装车
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu4',
	          'cms.view.common.remoteCombo',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.bdef_DefWorkerCombo',
	          'cms.view.common.bdef_DefcarCombo'
	],
	items:[
	{
		xtype:'commMenuWidget4',
		id:'menu3802',
	    region:'north'
	},
	{
		xtype:'tabpanel',
		id:'tabPId3802',
	    region:'center',
	    items:[
        {
        	title:$i18n.advice,//建议单
        	id:'tabPId3802_T1',
	    	layout:'border',
	    	items:[
	        {
	        	xtype : 'form',
	    	    id : 'form_01_3802',
	    	    region:'north',
	    	    frame : true,
	    	    layout:{
    	    		type:'table',
    	    		columns:4
    	    	},
    	    	defaults : {
					xtype:'textfield',
					labelWidth : 90,
					margin : '2 2 4 2',
					labelAlign : 'right'
		  		},
	    	    items :[
	            {
			        xtype : 'combo',
					fieldLabel : $i18n.line,//线路
					id : 'cmbLineNo3802',	
					displayField: 'dropValue',
					valueField: 'value',
					hidden:true,
	 	    	    store:Ext.create("cms.store.common.comboStore",
					{
						proxy:{
							type:'ajax',
							method:'post',
							url:'odata_CarPlanAction_queryLineNoCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							},
							extraParams:{
								strFlag : 'ST'
							}
						}
	   				}),
	   				beforeLabelTextTpl : required
	            },
	            {
	            	xtype : 'combo',
	            	fieldLabel:$i18n.sanpl_no,//承运商
	            	id:'cmbShipperNo3802',	
					displayField: 'dropValue',
					valueField: 'value',
					hidden:true,
	 	    	    store:Ext.create("cms.store.common.comboStore",
					{
						proxy:{
							type:'ajax',
							method:'post',
							url:'odata_CarPlanAction_queryShipperNoCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							},
							extraParams:{
								strFlag : 'ST'
							}
						}
	   				}),
	   				beforeLabelTextTpl : required
	            },
	            {
	            	xtype : 'combo',
            		fieldLabel:$i18n.line,//线路
            		id:'cmbLineNo3802_d2',	
					displayField: 'dropValue',
					valueField: 'value',
					hidden:true,
	 	    	    store:Ext.create("cms.store.common.comboStore",
					{
						proxy:{
							type:'ajax',
							method:'post',
							url:'odata_CarPlanAction_queryLabelShipperLineNo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
						}
	   				}),
	   				beforeLabelTextTpl : required
	            },
	            {
	            	xtype : 'bdef_DefOwnerCombo',
            		fieldLabel:$i18n.deliver_obj,
            		id:'cmbDeliverObj3802',
					hidden:true,
	 	    	    store:Ext.create("cms.store.common.comboStore",
					{
						proxy:{
							type:'ajax',
							method:'post',
							url:'odata_CarPlanAction_queryDeliverCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
						},
						listeners:{
							'load':function(th,records,successful,eOpts ){
								if(th.count()>0){
									Ext.getCmp('cmbDeliverObj3802').setValue(th.getAt(0).data.value);
									_myAppGlobal.getController('cms.controller.odata.odata_CarPlanForXzController').cmbDeliverObj3802Select(Ext.getCmp('cmbDeliverObj3802'));
								}
							}
						}
	   				}),
	   				beforeLabelTextTpl : required
	            },
	            {
            		fieldLabel:$i18n.exp_no,
            		id:'cmbSourceExpNo3802',
            		readOnly:true
	            },{
					xtype:'container',
					items:[
			        {
						xtype: 'button',
		            	text: $i18n.cancel1,//取消
		            	margin : '3 3 3 20',
		                hidden:true,
		            	id:'btnCancel3802'
					}]
				}
	            ]
	        },
	        {
	        	xtype : 'grid',
	    	    region:'west',
	    	    id:'grid_01_3802',
	    	    width:'45%',
	    	    loadMask : true, // 加载时有加载的图标
	    	    multiSelect: true,  
				selModel: {  
				    selType:'checkboxmodel',
				    checkOnly:true
				},
				store:Ext.create('cms.store.odata.odata_LabelMStore',{
					proxy:{
	   					type:'ajax',
	    				method:'post',
	     				url:'odata_CarPlanAction_queryStockLabelM',
	     				reader:{
	   						root:'rootList',
	   						totalProperty:'totalCount'
							}
						},
					listeners:{
						'load':function(th,records,successful,eOpts ){
							Ext.getCmp('grid_01_3802').getEl().select('div.x-column-header-checkbox').first().removeCls('x-grid-hd-checker-on');
							if(th.count()>0){
					   			var flag=_myAppGlobal.getController('cms.controller.odata.odata_CarPlanForXzController').getFlag();
                                if(flag=='1'){
                                	for(var i=0;i<th.count();i++){
    									var listDetail = [];
    				                	var a={
    				        					columnId:'tmp.label_no',
    				        					value:th.getAt(i).data.labelNo
    				        				};
    				        			listDetail.push(a);
    				        			var b={
    				        					columnId:'tmp.container_no',
    				        					value:th.getAt(i).data.containerNo
    				        				};
    				        			listDetail.push(b);
    				            		var strJson = Ext.encode(listDetail);
    				            		var params = {
    				            			strQuery : strJson
    				            		};
    				            		Ext.Ajax.request({
    				            			method:'POST',
    				            			url:'odata_CarPlanForXzAction_labelTmpCheck.action',
    				            			params:params,
    				            			async:false,
    				            			success:function(response)
    				            			{
    				            				var res = Ext.decode(response.responseText);
    				        			    	if(res!=''){
    				            					Ext.getCmp('grid_01_3802').getSelectionModel().select(i);
    				        			    	}			
    				            			}
    				            		});
    								}
                                }
							}
						}
					}
				}),
	    	    columns : [
	    	    {
	    	        xtype : 'rownumberer',
	    	        width : 30
	    	    },
	    	    {
	    	    	width:100,
	    	    	text : $i18n.label_no,//标签号码
	    	    	dataIndex:'labelNo'
	    	    },
	    	    {
	    	    	width:70,
	    	    	text : $i18n.line_no,//线路代码
	    	    	dataIndex:'lineNo'
	    	    },
	    	    {
	    	    	width:70,
	    	    	text : $i18n.curr_area,//当前位置
	    	    	dataIndex:'currArea'
	    	    },
	    	    {
	    	    	width:80,
	    	    	text : $i18n.cust_no,//客户编号
	    	    	dataIndex:'custNo'
	    	    },
	    	    {
	    	    	width:100,
	    	    	text : $i18n.cust_name,//客户名称
	    	    	dataIndex:'custName'
	    	    }]
	        },
	        {
	        	xtype : 'grid',
	    	    region:'west',
	    	    id:'grid_02_3802',
	    	    width:'55%',
	    	    loadMask : true, // 加载时有加载的图标
	    	    multiSelect: true,  
				selModel: {  
				    selType:'checkboxmodel',
				    checkOnly:true
				},
	    	    store:Ext.create('cms.store.odata.odata_LabelMStore',
	    		{
	    	    	proxy:{
						type:'ajax',
						method:'post',
						url:'odata_CarPlanAction_queryStockLabelTmp.action',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						}
					},
					listeners:{
						'load':function(th,records,successful,eOpts ){
							if(Ext.getCmp('grid_02_3802').getStore().count()>0){
								Ext.getCmp('grid_02_3802').getSelectionModel().selectAll(true);
							}
						}
					}
	    	    }),
	    	    columns : [
	    	    {
	    	        xtype : 'rownumberer',
	    	        width : 30
	    	    },
	    	    {
	    	    	width : 100,
	    	    	text : $i18n.label_no,//标签号码
	    	    	dataIndex : 'labelNo'
	    	    },
	    	    {
	    	    	width : 80,
	    	    	text : $i18n.deliver_obj,//配送对象
	    	    	dataIndex : 'deliverObj'
	    	    },
	    	    {
	    	    	width : 80,
	    	    	text : $i18n.line_no,//线路代码
	    	    	dataIndex : 'lineNo'
	    	    },
	    	    {
	    	    	width : 80,
	    	    	text : $i18n.curr_area,//当前位置
	    	    	dataIndex : 'currArea'
	    	    },
	    	    {
	    	    	width : 80,
	    	    	text : $i18n.cust_no,//客户编号
	    	    	dataIndex : 'custNo'
	    	    },
	    	    {
	    	    	width : 100,
	    	    	text : $i18n.cust_name,//客户名称
	    	    	dataIndex : 'custName'
	    	    }]
	        },
	        {
	        	xtype:'form',
	        	region:'south',
	        	frame:true,
	        	layout:{
	        		type:'table',
	        		columns:5
	        	},
	        	defaults : {
					xtype:'textfield',
					labelWidth : 90,
					margin : '2 2 4 2',
					labelAlign : 'right'
		  		},
	        	items:[
    	        {
    	        	xtype:'container',
    	        	margin:'0 0 0 20',
    	        	items:[
	        	    {
						xtype:'label',
						readOnly:true,
						text:$i18n.deliver_qty1+'：'//配送条数
					},
					{
						xtype:'label',
						readOnly:true,
						id:'lblStItems3802',
						margin:'0 0 0 5',
						text:'0'
					}]
				},
				{
					xtype:'container',
					margin:'0 0 0 20',
					items:[
			        {
			        	xtype:'label',
			        	readOnly:true,
			        	text:$i18n.item_num+'：'
			        },
					{
						xtype:'label',
						readOnly:true,
						id:'lblArticleItems3802',
						margin:'0 0 0 5',
						text:'0'
					}]
				},
				{
					xtype:'container',
					margin:'0 0 0 20',
					items:[
			        {
						xtype:'label',
						readOnly:true,
						text:$i18n.totalbox_num+'：'
					},
					{
						xtype:'label',
						readOnly:true,
						id:'lblBoxQty3802',
						margin:'0 0 0 5',
						text:'0'
					}]
				},
				{
					xtype:'container',
					margin:'0 0 0 20',
					items:[
			        {
						xtype:'label',
						readOnly:true,
						text:$i18n.ttl_volumn+'(m³)：'
					},
					{
						xtype:'label',
						readOnly:true,
						id:'lblVolumn3802',
						margin:'0 0 0 5',
						text:'0'
					}]
				},
				{
					xtype:'container',
					margin:'0 0 0 20',
					colspan:6,
					items:[
			        {
						xtype:'label',
						readOnly:true,
						text:$i18n.ttl_weight+'(t)：'
					},
					{
						xtype:'label',
						readOnly:true,
						id:'lblWeight3802',
						margin:'0 0 0 5',
						text:'0'
					}]
				}]
	        },
	        {
	        	xtype : 'form',
	    	    region:'south',
	    	    frame : true,
	    	    layout:{
    	    		type:'table',
    	    		columns:4
    	    	},
    	    	defaults : {
					xtype:'textfield',
					labelWidth : 90,
					margin : '2 2 4 2',
					labelAlign : 'right'
		  		},
	    	    items :[
				{
	            	xtype:'bdef_DefcarCombo',
	            	fieldLabel:$i18n.car_no,//车辆代码
	            	id : 'cmbCarNo3802',
					store:Ext.create("cms.store.bdef.bdef_DefcarComboStore").load(),
			        beforeLabelTextTpl : required
	            },
	            {
					xtype:'bdef_DefWorkerCombo',
	            	fieldLabel:$i18n.plan_load_name,//指定装车人员
	            	margin : '0 0 0 90',
	            	store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore').load(),
	            	id:'cmbWorkerNo3802',
					beforeLabelTextTpl : required
	            },
	            {
					xtype:'container',
					items:[
					{
						xtype: 'button',
				    	text: $i18n.search_detail,//查看明细
				    	margin : '2 0 0 10',
				    	id:'btnSearchDetail3802'
					},
			        {
						xtype: 'button',
		            	text: $i18n.createproposeno,//装车建议单
		            	margin : '3 3 3 20',
		            	id:'btn_01_3802'
					}]
				},{
					xtype : 'radiogroup',
					id : 'rdoCheckType3802',
					margins: '3 3 3 100',
					fieldLabel :'是否打印建议单',
					width : 240,
					columns : 2,
					items : [
			        {
						boxLabel : $i18n.no_print,//不打印
						name : 'rd',
						inputValue : '0'
						
					},
					{
						boxLabel : $i18n.print,//打印
						name : 'rd',
						inputValue : '1',
						checked:true
					}
					]
				}
	            ]
	        }
	        ]
        },
        {
        	title:$i18n.load_car,//装车
        	id:'tabPId3802_T2',
	    	layout:'border',
	    	items:[
	    	{
	        	xtype : 'form',
	    	    region:'north',
	    	    frame : true,
	    	    layout:{
    	    		type:'table',
    	    		columns:4
    	    	},
    	    	defaults : {
					xtype:'textfield',
					labelWidth : 90,
					margin : '2 2 4 2',
					labelAlign : 'right'
		  		},
	    	    items :[
	            {
	            	xtype : 'radiogroup',
	    			id : 'searchType3802_d2',
	    			fieldLabel : $i18n.search_type,//查询方式
	    			width : 300,
	    			columns : 3,
	    			items : [
	    	        {
	    				boxLabel : $i18n.line,//线路
	    				name : 'rd',
	    				inputValue : '2',
	    				checked:true
	    				
	    			},
	    			{
	    				boxLabel : $i18n.sanpl_no,//承运商
	    				name : 'rd',
	    				inputValue : '1'
	    			},
	    			{
	    				boxLabel:$i18n.suggest,//建议单
	    				name : 'rd',
	    				inputValue : '3'
	    			}
	    			]
	            },
	            {
			        xtype : 'combo',
					fieldLabel : $i18n.line,//线路
					id : 'cmbLineNo3802_d3',	
					displayField: 'dropValue',
					valueField: 'value',
	 	    	    store:Ext.create("cms.store.common.comboStore",
					{
						proxy:{
							type:'ajax',
							method:'post',
							url:'odata_CarPlanAction_queryLineNoCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							},
							extraParams:{
								strFlag : 'OL'
							}
						}
	   				}),
	   				beforeLabelTextTpl : required
	            },
	            {
	            	xtype : 'combo',
	            	fieldLabel:$i18n.sanpl_no,//承运商
	            	id:'cmbShipperNo3802_d2',	
					displayField: 'dropValue',
					valueField: 'value',
					hidden:true,
	 	    	    store:Ext.create("cms.store.common.comboStore",
					{
						proxy:{
							type:'ajax',
							method:'post',
							url:'odata_CarPlanAction_queryShipperNoCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							},
							extraParams:{
								strFlag : 'OL'
							}
						}
	   				}),
	   				beforeLabelTextTpl : required
	            },
	            {
            		xtype : 'combo',
            		fieldLabel:$i18n.line,//线路
            		id:'cmbLineNo3802_d4',	
					displayField: 'dropValue',
					valueField: 'value',
					hidden:true,
	 	    	    store:Ext.create("cms.store.common.comboStore",
					{
						proxy:{
							type:'ajax',
							method:'post',
							url:'odata_CarPlanAction_queryLoadproposeLineCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
						}
	   				}),
	   				beforeLabelTextTpl : required
	            },
	            {
	            	xtype:'combo',
	            	fieldLabel:$i18n.advice,//建议单
	            	id:'cmbLoadproposeNo3802',	
					displayField: 'dropValue',
					valueField: 'value',
					hidden:true,
	 	    	    store:Ext.create("cms.store.common.comboStore",
					{
						proxy:{
							type:'ajax',
							method:'post',
							url:'odata_CarPlanAction_queryLoadproposeNo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
						}
	   				}),
	   				beforeLabelTextTpl : required
	            }
	            ]
	        },
	        {
	        	xtype : 'grid',
	    	    region:'east',
	    	    id:'grid_03_3802',
	    	    width:'50%',
	    	    loadMask : true, // 加载时有加载的图标
	    	    multiSelect: true,  
				selModel: {  
				    selType:'checkboxmodel'/*,
				    checkOnly:true*/
				},
				store:Ext.create('cms.store.odata.odata_LoadproposeMStore',{
					listeners:{
							'load':function(th,records,successful,eOpts ){
								//清空已选
								Ext.getCmp('grid_04_3802').getStore().removeAll();
								//设置为解锁状态
								Ext.getCmp('grid_03_3802').getSelectionModel().setLocked(false);
								//选择所有记录
								Ext.getCmp('grid_03_3802').getSelectionModel().selectAll();
								//锁定
								//Ext.getCmp('grid_03_3802').getSelectionModel().setLocked(true);
							}
						}
				}),
	    	    columns : [
	    	    {
	    	        xtype : 'rownumberer',
	    	        width : 30
	    	    },
	    	    {
	    	    	width:100,
	    	    	text : $i18n.label_no,//标签号码
	    	    	dataIndex:'labelNo'
	    	    },
	    	    {
	    	    	width:80,
	    	    	text : $i18n.line_no,//线路代码
	    	    	dataIndex:'lineNo'
	    	    },
	    	    {
	    	    	width: 80,
	    	    	text : $i18n.curr_area,//当前位置
	    	    	dataIndex:'currArea'
	    	    },
	    	    {
	    	    	width:80,
	    	    	text : $i18n.cust_no,//客户编号
	    	    	dataIndex:'custNo'
	    	    },
	    	    {
	    	    	width:100,
	    	    	text : $i18n.cust_name,//客户名称
	    	    	dataIndex : 'custName'
	    	    }]
	        },
	        {
	        	xtype : 'grid',
	    	    region:'east',
	    	    id:'grid_04_3802',
	    	    width:'50%',
	    	    loadMask : true, // 加载时有加载的图标
	    	    selModel : {
	    	        selType : 'cellmodel'
	    	    },
	    	    plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
	    	        clicksToEdit : 1,
	    	        onSpecialKey:function(ed,field,e){
						commEnterGridStatEdit(this.grid,true,'cms.controller.odata.odata_CarPlanForXzController',e.getKey());
					}
	    	    })],
	    	    columns : [
	    	    {
	    	        xtype : 'rownumberer',
	    	        width : 30
	    	    },
	    	    {
	    	    	width:100,
	    	    	text : $i18n.label_no,//标签号码
	    	    	dataIndex:'labelNo'
	    	    },
	    	    {
	    	    	width:80,
	    	    	text : $i18n.line_no,//线路代码
	    	    	dataIndex:'lineNo'
	    	    },
	    	    {
	    	    	width: 80,
	    	    	text : $i18n.curr_area,//当前位置
	    	    	dataIndex:'currArea'
	    	    },
	    	    {
	    	    	width:80,
	    	    	text : $i18n.cust_no,//客户编号
	    	    	dataIndex:'custNo'
	    	    },
	    	    {
	    	    	width:100,
	    	    	text : $i18n.cust_name,//客户名称
	    	    	dataIndex : 'custName'
	    	    },{
					width : 80,
					text:$i18n.deliverBox,//配送物流箱数
					dataIndex : 'deliverBox',
					cls : 'notnull',
					field : {
    	        		xtype : 'numberfield',
    	        		minValue:0
    	        	}
				}]
	        },
	        {
	        	xtype : 'form',
	    	    region:'south',
	    	    frame : true,
	    	    layout:{
    	    		type:'table',
    	    		columns:2
    	    	},
    	    	defaults : {
					xtype:'textfield',
					labelWidth : 90,
					margin : '2 2 4 2',
					labelAlign : 'right'
		  		},
	    	    items :[
                {
    	          	xtype:'bdef_DefOwnerCombo',
    				fieldLabel:$i18n.car_no,//车辆代码
    	 	    	id:'car_no3802',
    	 	    	beforeLabelTextTpl : required,
    	 	    	store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
    					proxy:{
    						type:'ajax',
    						method:'post',
    						url:'odata_CarPlanForXzAction_getCarNoQuery',
    						reader:{
    							root:'rootList',
    							totalProperty:'totalCount'
    						}
    					}
    			   	}).load()
    			},       
//	            {
//	            	fieldLabel:$i18n.car_plate,//车牌号,
//	            	readOnly:true,
//	            	id:'txtCarNo3802'
//	            },
	            {
	            	fieldLabel:$i18n.seal_no,//封条号
	            	id:'txtSealNo3802'
	            },
	            {
	            	xtype:'bdef_DefWorkerCombo',
	            	fieldLabel:$i18n.plan_load_name,//指定装车人员
	            	store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore').load(),
	            	id:'cmbWorkerNo3802_d2',
					beforeLabelTextTpl : required
	            },
	            {
					xtype:'container',
	            	margin : '0 0 0 30',
					items:[
			        {
						xtype: 'button',
		            	text: $i18n.load_car,//装车
		            	margin : '3 3 3 20',
		            	id:'btnLoadCar3802'
					}
					]
				}
	            ]
	        }
	    	]
        },{

        	title:'派车单查询',
        	id:'tabPId3802_T3',
	    	layout:'border',
	    	items:[
	        {
	        	xtype : 'form',
	    	    id : 'form_01_3802_T3',
	    	    region:'north',
	    	    frame : true,
	    	    layout:{
    	    		type:'table',
    	    		columns:4
    	    	},
    	    	defaults : {
					xtype:'textfield',
					labelWidth : 90,
					margin : '2 2 4 2',
					labelAlign : 'right'
		  		},
	    	    items :[
	            {
	            	xtype : 'bdef_DefOwnerCombo',
            		fieldLabel:$i18n.carPlanNo,//派车单号
            		id:'cmbcarPlanNo3802',
            		store:Ext.create("cms.store.common.comboStore",
 					{
 						proxy:{
 							type:'ajax',
 							method:'post',
 							url:'odata_CarPlanAction_queryCarPlanCombo.action',
 							reader:{
 								root:'rootList',
 								totalProperty:'totalCount'
 							}
 						},
 						listeners:{
 							'load':function(th,records,successful,eOpts){
 								if(th.count()>0){
									Ext.getCmp('cmbcarPlanNo3802').setValue(th.getAt(0).data.value);
									_myAppGlobal.getController('cms.controller.odata.odata_CarPlanForXzController').cmbcarPlanNo3802Select(Ext.getCmp('cmbcarPlanNo3802'));
								}
 							}
 						}
 	   				})
	               }
	            ]
	        },
	        {
	        	xtype : 'grid',
	    	    region:'west',
	    	    id:'grid_01_3802_T3',
	    	    width:'30%',
	    	    loadMask : true, // 加载时有加载的图标
	    	    multiSelect: true,  
				selModel: {  
				    selType:'checkboxmodel',
				    checkOnly:true
				},
				store:Ext.create('cms.store.odata.odata_LoadproposeMStore',{
					proxy:{
						type:'ajax',
						method:'post',
						url:'odata_CarPlanAction_queryDeliverObj.action',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						}
					}
					
				}),
	    	    columns : [
	    	    {
	    	        xtype : 'rownumberer',
	    	        width : 30
	    	    },
	    	    {
	    	    	width : 150,
	    	    	text : $i18n.deliver_obj,//配送对象
	    	    	dataIndex : 'deliverObj'
	    	    },
	    	    {
	    	    	width:80,
	    	    	text : $i18n.loadOrder,//装车顺序
	    	    	dataIndex:'loadOrder'
	    	    }]
	        },
	        {
	        	xtype : 'grid',
	    	    region:'west',
	    	    id:'grid_02_3802_T3',
	    	    width:'70%',
	    	    //loadMask : true, // 加载时有加载的图标
	    	    //multiSelect: true,  
				/*selModel: {  
				    selType:'checkboxmodel',
				    checkOnly:true
				},*/
	    	    store:Ext.create('cms.store.odata.odata_LabelMStore'
	    		/*{
					listeners:{
						'load':function(th,records,successful,eOpts ){
							if(Ext.getCmp('grid_02_3802_T3').getStore().count()>0){
								Ext.getCmp('grid_02_3802_T3').getSelectionModel().selectAll(true);
							}
						}
					}
	    	    }*/),
	    	    columns : [
	    	    {
	    	        xtype : 'rownumberer',
	    	        width : 30
	    	    }, {
	    	    	width : 150,
	    	    	text : $i18n.deliver_obj,//配送对象
	    	    	dataIndex : 'deliverObj'
	    	    },
	    	    {
	    	    	width : 100,
	    	    	text : $i18n.label_no,//标签号码
	    	    	dataIndex : 'labelNo'
	    	    },
	    	    {
	    	    	width : 80,
	    	    	text : $i18n.line_no,//线路代码
	    	    	dataIndex : 'lineNo'
	    	    },
	    	    {
	    	    	width : 80,
	    	    	text : $i18n.curr_area,//当前位置
	    	    	dataIndex : 'currArea'
	    	    },
	    	    {
	    	    	width : 80,
	    	    	text : $i18n.cust_no,//客户编号
	    	    	dataIndex : 'custNo'
	    	    },
	    	    {
	    	    	width : 100,
	    	    	text : $i18n.cust_name,//客户名称
	    	    	dataIndex : 'custName'
	    	    }]
	        },
	        {
	        	xtype:'form',
	        	region:'south',
	        	frame:true,
	        	layout:{
	        		type:'table',
	        		columns:5
	        	},
	        	defaults : {
					xtype:'textfield',
					labelWidth : 90,
					margin : '2 2 4 2',
					labelAlign : 'right'
		  		},
	        	items:[
    	        {
    	        	xtype:'container',
    	        	margin:'0 0 0 20',
    	        	items:[
	        	    {
						xtype:'label',
						readOnly:true,
						text:$i18n.deliver_qty1+'：'//配送条数
					},
					{
						xtype:'label',
						readOnly:true,
						id:'lblStItems3802_T3',
						margin:'0 0 0 5',
						text:'0'
					}]
				},
				{
					xtype:'container',
					margin:'0 0 0 20',
					items:[
			        {
			        	xtype:'label',
			        	readOnly:true,
			        	text:$i18n.item_num+'：'
			        },
					{
						xtype:'label',
						readOnly:true,
						id:'lblArticleItems3802_T3',
						margin:'0 0 0 5',
						text:'0'
					}]
				},
				{
					xtype:'container',
					margin:'0 0 0 20',
					items:[
			        {
						xtype:'label',
						readOnly:true,
						text:$i18n.totalbox_num+'：'
					},
					{
						xtype:'label',
						readOnly:true,
						id:'lblBoxQty3802_T3',
						margin:'0 0 0 5',
						text:'0'
					}]
				},
				{
					xtype:'container',
					margin:'0 0 0 20',
					items:[
			        {
						xtype:'label',
						readOnly:true,
						text:$i18n.ttl_volumn+'(m³)：'
					},
					{
						xtype:'label',
						readOnly:true,
						id:'lblVolumn3802_T3',
						margin:'0 0 0 5',
						text:'0'
					}]
				},
				{
					xtype:'container',
					margin:'0 0 0 20',
					colspan:6,
					items:[
			        {
						xtype:'label',
						readOnly:true,
						text:$i18n.ttl_weight+'：'
					},
					{
						xtype:'label',
						readOnly:true,
						id:'lblWeight3802_T3',
						margin:'0 0 0 5',
						text:'0'
					}]
				}]
	        }
	        ]
        	
        }
        ]
	}
    ]
});