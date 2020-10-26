/**
 * 模块名称：电商出货手建单
 * 模块编码：3103
 * 创建：hkl
 */
var odata_Exp_MStore=Ext.create('cms.store.odata.odata_ExpMStore'
		,{autoLoad:false,
			//7-14添加
			listeners:{  
			'load':function(th,records,successful,eOpts ){
				if(Ext.getCmp('grid_01_3103').getStore().count()>0){
				   Ext.getCmp('grid_01_3103').getSelectionModel().select(0);
				}
			}
		}
		}
);
Ext.define('cms.view.odata.odata_B2CExpUI',{
	alias:'widget.odata_B2CExpUI',
	title:'电商出货手建单',//出货手建单
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	
	requires:[
			 'cms.view.common.commMenu',
			 'cms.view.common.commMenu3',
			 'cms.view.common.commMenu6',
			 'cms.view.common.bdef_DefCustCombo',
			 'cms.view.common.bdef_DefOwnerCombo',
			 'cms.view.common.bdef_DefArticleCombo',
			 'cms.view.common.bdef_PackingQtyCombo',
			 'cms.view.common.wms_DefFieldValCombo',
			 'cms.view.common.odata_ExpMCombo',
	],
	items:[
	{
		xtype:'tabpanel',
		id:'tabPId3103',
	    region:'center',
	    items:[
	    {
	    	title:$i18n.titleM,
	    	layout:'border',
	    	items:[
		    	{
		    		xtype:'commMenuWidget3',
		    		items:[
		    		{
					text : '刷新',
					iconCls : 'refresh',
					name : 'refresh'
				},{
					text : '查找',
					iconCls : 'query',
					name : 'detailQuery'
				},{
					text : '导入',
					icon: 'system/extjs/resources/icons/fam/application_split.png',
					name : 'upload'
				},{
				//	text : '打印拣货单',
					text : '打印快递单',
					iconCls : 'print',
					id : 'printPickingNo_3103'
				}//天天惠暂不使用
		    		/*,{
					text : '打印',
					iconCls : 'print',
					name : 'detailPrint',
					hidden:true
				}*/,
	    		{
					text : '关单',
					name : 'closeOrder',
					iconCls : 'delete' ,
					hidden:true,
					id : 'closeOrder_3103'
				},{
					text : '取消订单',				//7-11添加
					name : 'undoOrder',
					iconCls : 'undo' ,
					id : 'undoOrder_3103'
				}],
	    	    region:'north'
	    	},{

	    		xtype : 'form',
	    		region : 'north',
	    		layout:'column',
	    		frame : true,
	    		items :[{
					//xtype:'fieldset',
	    			frame:true,width:'100%',
				    layout: 
				    {
				        type: 'table',
				        columns: 5
				    },
				    defaults : 
				    {
				        xtype : 'textfield',
		                labelWidth : 80,
		                width:209,
		                margin:'0 4 2 4',
			            labelAlign:'right'			
				    },
				    items:[{
						xtype:'wms_DefFieldValCombo',
						fieldLabel:$i18n.exp_type,//出货单别
						id:'exp_type3103_query',
						store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		    				proxy:{
								type:'ajax',
								method:'post',
								url:'odata_ExpAction_queryExpTypeCombo.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								},
								extraParams:{
									flag : "2"
								}
		    				},
		    				listeners:{
								'load':function(th,records,successful,eOpts )
								{
									if(Ext.getCmp('exp_type3103_query').getStore().data.length>0)
									{
										Ext.getCmp('exp_type3103_query').setValue(Ext.getCmp('exp_type3103_query').getStore().getAt(0).data.value);		
									}
								}
							}
						}).load()
					},{
						xtype:'bdef_DefOwnerCombo',
						fieldLabel : $i18n.owner_no,//委托业主编号
						id : 'owner_no3103_query',					
						store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load()
					},
                    {
		            	fieldLabel:$i18n.exp_no,//出货单号
						id:'exp_no3103_query'
					},{
						fieldLabel:$i18n.po_nomark,//订单号
						id:'po_nomark3103_query',
						maxLength:20
					},{
					 	xtype:'wms_DefFieldValCombo',
					    fieldLabel : $i18n.take_type,// 提货类型
						id : 'takeType3103_query',	
						    store:Ext.create("cms.store.common.comboStore").load(
						    {
						         params:{str:"N,TAKE_TYPE"}
						    })
				    },{
						xtype:'bdef_DefCustCombo',
						fieldLabel:$i18n.cust,//客户编号
						id:'cust3103_query',
						store:Ext.create('cms.store.bdef.bdef_DefCustComboStore'),
					 	displayField : 'dropValue',
						valueField : 'value'
					},{
						xtype:'odata_ExpMCombo',
						fieldLabel:$i18n.sanpl_no,//承运商
						id:'sanpl_no3103_query',
						maxLength:50,
						store:Ext.create('cms.store.odata.odata_ExpMComboStore'),
					 	displayField : 'dropValue',
						valueField : 'value'
					},{
						fieldLabel:$i18n.deliver_no,//快递单号
						id:'deliver_no3103_query',
						maxLength:100
			  		},{
						xtype:'wms_DefFieldValCombo',
	    	    		fieldLabel:$i18n.Order_source,//订单来源
	    	    		id:'Order_source3103_query',
	    	    		store:Ext.create("cms.store.common.comboStore").load(
	    	    		{
	    	       	 		params:{str:"ODATA_EXP_M,ORDER_SOURCE"}
	    	    		})
			  		},{
					 	xtype:'wms_DefFieldValCombo',
		       	      	fieldLabel : $i18n.orgNo,// 机构代码
						id : 'orgNo3103_query',	
			       	    store:Ext.create("cms.store.common.comboStore").load(
			       	    {
			       	         params:{str:"N,ORG_NO"}
			       	    })
					},{
						fieldLabel:'店铺号',
						id:'shopNo3103_query'
					},{
						fieldLabel:'品项数',
						id:'skucount3103_query'
					},{
						fieldLabel:'平台订单号',
						id:'custExpNo3103_query'
					},{
						fieldLabel:'发货人',
						id:'sendName3103_query'
					
					},{
						fieldLabel:'收货人',
						id:'receiveName3103_query'
					
					},{
						fieldLabel:'提货人',
						id:'takeName3103_query'
					
					},{
					 	xtype:'wms_DefFieldValCombo',
		       	      	fieldLabel : '订单状态',
						id : 'curr_status3103_query',	
			       	    store:Ext.create("cms.store.common.comboStore").load(
			       	    {
			       	         params:{str:"ODATA_EXP_STATUS,CURR_STATUS"}
			       	    })
					}
					,{
					 	xtype:'wms_DefFieldValCombo',
		       	      	fieldLabel : '操作状态',
						id : 'operation_status3103_query',	
			       	    store:Ext.create("cms.store.common.comboStore").load(
			       	    {
			       	         params:{str:"ODATA_EXP_M,STATUS"}
			       	    })
					}
			       	    ] 
				  },{
					xtype:'fieldset',
					title:'日期选择',width:'100%',
				    layout: 
				    {
				        type: 'table',
				        columns: 4
				    },
				    defaults : 
				    {
				        xtype : 'textfield',
		                labelWidth : 90,
		                width:260,
		                margin:'0 4 0 4',
			            labelAlign:'right'			
				    },
				    items:[
				            {
				            	xtype : 'datetimefield',
								fieldLabel : '订单创建日期',
								id : 'rgst_Date3103_1',							
								format : 'Y-m-d H:i:s',
							//	anchor: '100%',  
							 //   value:Ext.Date.add(new Date(), Ext.Date.DAY, -1),  
							    //maxValue: new Date()
				       	    },{
				       	    	xtype:'container',
								margin:'2 0 0 5',
								width:25,
								items:[
								{
									xtype:'label',
									readOnly:true,
									cls:'classDiv1',
									text:'--'
								}]
				       	    },{
				       	    	xtype : 'datetimefield',
								id : 'rgst_Date3103_2',
								format : 'Y-m-d H:i:s',
								width:160
					            //margin:'0 20 0 0',
				       	    },{

								xtype : 'radiogroup',
								id : 'rdoCheckType3103_1',
								margins: '0 0 0 20',
								width : 600,
								columns : 8,
								items : [
						        {
									boxLabel : '今天',
									name : 'rg',
									inputValue : '0',
								},
								{
									boxLabel : '昨天',
									name : 'rg',
									inputValue : '1'
								},
								{
									boxLabel : '近1周',
									name : 'rg',
									inputValue : 'd1'
								},
								{
									boxLabel : '近2周',
									name : 'rg',
									inputValue : 'd2'
								},
								{
									boxLabel : '近3周',
									name : 'rg',
									inputValue : 'd3'
								},
								{
									boxLabel : '近1月',
									name : 'rg',
									inputValue : 'm1'
								},
								{
									boxLabel : '近2月',
									name : 'rg',
									inputValue : 'm2'
								},
								{
									boxLabel : '近3月',
									name : 'rg',
									inputValue : 'm3'
								},{
									boxLabel : '全部',
									name : 'rg',
									hidden:true,
									inputValue : 'all'
								}
								]
				   		   
				       	    }, {
				            	xtype : 'datetimefield',
								fieldLabel : '预期发货日期',
								id : 'expect_Date3103_1',							
								format : 'Y-m-d H:i:s',
				       	    },{
				       	    	xtype:'container',
								margin:'2 0 0 5',
								width:25,
								items:[
								{
									xtype:'label',
									readOnly:true,
									cls:'classDiv1',
									text:'--'
								}]
				       	    },{
				       	    	xtype : 'datetimefield',
								id : 'expect_Date3103_2',
								format : 'Y-m-d H:i:s',
								width:160
					            //margin:'0 20 0 0',
				       	    },{

								xtype : 'radiogroup',
								id : 'rdoCheckType3103_2',
								margins: '0 0 0 20',
								width : 600,
								columns : 8,
								items : [
						        {
									boxLabel : '今天',
									name : 'yj',
									inputValue : '0',
								},
								{
									boxLabel : '昨天',
									name : 'yj',
									inputValue : '1'
								},
								{
									boxLabel : '近1周',
									name : 'yj',
									inputValue : 'd1'
								},
								{
									boxLabel : '近2周',
									name : 'yj',
									inputValue : 'd2'
								},
								{
									boxLabel : '近3周',
									name : 'yj',
									inputValue : 'd3'
								},
								{
									boxLabel : '近1月',
									name : 'yj',
									inputValue : 'm1'
								},
								{
									boxLabel : '近2月',
									name : 'yj',
									inputValue : 'm2'
								},
								{
									boxLabel : '近3月',
									name : 'yj',
									inputValue : 'm3'
								},{
									boxLabel : '全部',
									name : 'yj',
									hidden:true,
									inputValue : 'all'
								}
								]
				   		   
				       	    },
				       	 {
				            	xtype : 'datetimefield',
								fieldLabel :'实际发货日期',
								id : 'reality_Date3103_1',							
								format : 'Y-m-d H:i:s',
				       	    },{
				       	    	xtype:'container',
								margin:'2 0 0 5',
								width:25,
								items:[
								{
									xtype:'label',
									readOnly:true,
									cls:'classDiv1',
									text:'--'
								}]
				       	    },{
				       	    	xtype : 'datetimefield',
								id : 'reality_Date3103_2',
								format : 'Y-m-d H:i:s',
								width:160
					            //margin:'0 20 0 0',
				       	    },{

								xtype : 'radiogroup',
								id : 'rdoCheckType3103_3',
								margins: '0 0 0 20',
								width : 600,
								columns : 8,
								items : [
						        {
									boxLabel : '今天',
									name : 'sj',
									inputValue : '0',
								},
								{
									boxLabel : '昨天',
									name : 'sj',
									inputValue : '1'
								},
								{
									boxLabel : '近1周',
									name : 'sj',
									inputValue : 'd1'
								},
								{
									boxLabel : '近2周',
									name : 'sj',
									inputValue : 'd2'
								},
								{
									boxLabel : '近3周',
									name : 'sj',
									inputValue : 'd3'
								},
								{
									boxLabel : '近1月',
									name : 'sj',
									inputValue : 'm1'
								},
								{
									boxLabel : '近2月',
									name : 'sj',
									inputValue : 'm2'
								},
								{
									boxLabel : '近3月',
									name : 'sj',
									inputValue : 'm3'
								},{
									boxLabel : '全部',
									name : 'sj',
									hidden:true,
									inputValue : 'all'
								}
								]
				   		   
				       	    }
							    
							    
							    
					]
						
		            },{
		            	xtype: 'button',
		            	text: '查询',
		            	margin : '2 3 1 10',
		            	width:120,
		            	id:'btnQuery3103'
		            },{
		            	xtype: 'button',
		            	text: '清除查询条件',
		            	margin : '2 3 1 50',
		            	width:120,
		            	id:'btnNew3103'
		            },{

						xtype: 'checkboxfield',
			        	//fieldLabel: '',
						boxLabel: "历史单据",	margin : '2 3 1 100',
			        	id:'expNothy3103'
		            }]
   
	    	
	    	},{
	    		region:'center',
	    	    xtype:'grid',
	    	    id:'grid_01_3103',
	    	    store:odata_Exp_MStore,
				multiSelect: true,
				selModel: {
						selType:'checkboxmodel',
						checkOnly:true
				},
	    	    columns:[{
	    	        xtype:'rownumberer',
	    	       	width:30 
	    	    },{
	    	       	width:70,
	    	       	text:$i18n.owner_no,//货主编号
	    	       	dataIndex:'ownerNo'
	    	    },{
	    	       	width:100,
	    	       	text:$i18n.owner_name,//货主名称
	    	       	dataIndex:'ownerName'
	    	    },
	    	    {
	    	       	width:140,
	    	       	text:$i18n.exp_no,//出货单号
	    	       	dataIndex:'expNo'
	    	    },{
	    	       	width:70,
	    	       	text:$i18n.exp_type,//出货单别
	    	       	dataIndex:'sourceexpType'
	    	    },{
	    	       	width:120,
	    	       	text:$i18n.po_nomark,//订单号
	    	       	dataIndex:'sourceexpNo'
	    	    },/*{
	    	       	width:50,
	    	       	text:$i18n.cust_dept,//部门
	    	       	dataIndex:'deptNo'
	    	    },*/{
	    	       	width:100,
	    	       	text:$i18n.cust_no,//客户编号
	    	       	dataIndex:'custNo'
	    	    },{
	    	       	width:120,
	    	       	text:$i18n.cust_name,//客户名称
	    	       	dataIndex:'custName'
	    	    },{
	    	       	width:60,
	    	       	text:$i18n.status2,//操作状态
	    	       	dataIndex:'statusText'
	    	    },{
	    	       	width:140,
	    	       	text:'预计发货日期',//出货确认日期
	    	       	dataIndex:'custsendDate'
	    	    },{
	    	       	width:140,
	    	       	text:'实际发货日期',//出货最晚日期
	    	       	dataIndex:'lastCustsendDate'
	    	    },{
	    	       	width:140,
	    	       	text:$i18n.locate_no,//波次号
	    	       	dataIndex:'waveNo'
	    	    }],
				dockedItems : [{
					xtype : 'pagingtoolbar',
					dock : 'bottom',
					id:'pagingtoolbar3103',
					store:odata_Exp_MStore,
					displayInfo : true
				}]
	    	    
	    	}]
	    },{
	    	title:$i18n.titleD,
	    	layout:'border',
	    	itemId:'tabPId3103i',
	    	items:[
	    	{
				xtype:'commMenuWidget',
				region:'north',
				id:'menu3103'
			},{
				xtype : 'tabpanel',
			    region:'center',
			    id:'tab3103_expM1',
			    items : [{
			    	title:'基本信息',
			    	layout:'border',
			    	items:[
					{
						xtype : 'form',
			    	    id : 'form_01_3103',
			    	    border : false,
			    	    bodyStyle: 'background:#DFE9F6;',
			    	    layout:{
		    	    		type:'table',
		    	    		columns:4
		    	    	},
		    	    	defaults : {
							xtype:'textfield',
							labelWidth : 90,
							margin : '2 2 4 2',
							labelAlign : 'right',
							width : 260
				  		},
			    	    items :[
		    	    	{
							fieldLabel:$i18n.exp_no,//出货单号
							id:'exp_no3103',
							allowBlank:false,
							readOnly:true,
							beforeLabelTextTpl:required
				  		},{								
							xtype:'bdef_DefOwnerCombo',
							fieldLabel : $i18n.owner_no,//委托业主编号
							id : 'owner_no3103',					
			        		//readOnly:true,
			        		store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore'),
			        		beforeLabelTextTpl : required
				  		},{
							xtype:'bdef_DefOwnerCombo',
		    	    		fieldLabel:$i18n.exp_type,//出货单别
		    	    		id:'exp_type3103',
		    	    		store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
			    				proxy:{
									type:'ajax',
									method:'post',
									url:'odata_ExpAction_queryExpTypeCombo.action',
									reader:{
										root:'rootList',
										totalProperty:'totalCount'
									},
									extraParams:{
										flag : "2"
									}
			    				}
							}),
		    	    		editable:false,
		    	    		allowBlank:false,
		    	    		//readOnly:true,
		    	    		beforeLabelTextTpl:required
				  		},{
						 	xtype:'wms_DefFieldValCombo',
			       	      	fieldLabel : $i18n.take_type,// 提货类型
							id : 'takeType3103',	
				       	    store:Ext.create("cms.store.common.comboStore").load(
				       	    {
				       	         params:{str:"N,TAKE_TYPE"}
				       	    }),
				       	    allowBlank : false,
		    	    		//readOnly:true,
				       	    beforeLabelTextTpl : required
						},{
							fieldLabel:$i18n.po_nomark,//订单号
							id:'po_nomark3103',
							allowBlank:false,
							//readOnly:true,
							maxLength:20,
							beforeLabelTextTpl:required
				  		},{
							xtype:'bdef_DefCustCombo',
				  			fieldLabel:$i18n.cust,//客户编号
				  			id:'cust3103',
				  			store:Ext.create('cms.store.bdef.bdef_DefCustComboStore'),
			       		 	displayField : 'dropValue',
			       			valueField : 'value',
			       			//readOnly:true,
			       			beforeLabelTextTpl : required
				  		},{
							fieldLabel:'货主客户编码',
							hidden:true,
							id:'ownerCustNo3103'
				  		},{
				  			xtype:'odata_ExpMCombo',
							fieldLabel:$i18n.sanpl_no,//承运商
							id:'sanpl_no3103',
							maxLength:50,
				  			store:Ext.create('cms.store.odata.odata_ExpMComboStore'),
			       		 	displayField : 'dropValue',
			       			valueField : 'value'
				  		},{
							fieldLabel:$i18n.priority,//订单优先级
							id:'priority3103',
							allowBlank:false,
							//readOnly:true,
							beforeLabelTextTpl:required
				  		},{
				  			xtype:'wms_DefFieldValCombo',
		    	    		fieldLabel:$i18n.fast_flag,//是否急单
		    	    		id:'fast_flag3103',
		    	    		store:Ext.create("cms.store.common.comboStore").load(
		    	    		{
		    	       	 		params:{str:"ODATA_EXP_M,FAST_FLAG"}
		    	    		}),
		    	    		editable:false,
		    	    		allowBlank:false,
							//readOnly:true,	    	    		
		    	    		beforeLabelTextTpl:required
				  		},{
							fieldLabel:$i18n.deliver_no,//快递单号
							id:'deliver_no3103',
							//readOnly:true,
							maxLength:100
				  		},{
							fieldLabel:$i18n.Deliver_address,//配送站点
							id:'Deliver_address3103',
							//readOnly:true,
							maxLength:100
				  		},{
							xtype:'wms_DefFieldValCombo',
		    	    		fieldLabel:$i18n.Print_bill_flag,//是否打印发票
		    	    		id:'Print_bill_flag3103',
		    	    		store:Ext.create("cms.store.common.comboStore").load(
		    	    		{ 
		    	       	 		params:{str:"ODATA_EXP_M,PRINT_BILL_FLAG"}
		    	    		}),
		    	    		editable:false,
		    	    		allowBlank:false,
		    	    		//readOnly:true,
		    	    		beforeLabelTextTpl:required
				  		},{
							xtype:'wms_DefFieldValCombo',
		    	    		fieldLabel:$i18n.Order_source,//订单来源
		    	    		id:'Order_source3103',
		    	    		store:Ext.create("cms.store.common.comboStore").load(
		    	    		{
		    	       	 		params:{str:"ODATA_EXP_M,ORDER_SOURCE"}
		    	    		}),
		    	    		editable:false,
		    	    		allowBlank:false,
		    	    		//readOnly:true,
		    	    		beforeLabelTextTpl:required
				  		},{
						 	xtype:'wms_DefFieldValCombo',
			       	      	fieldLabel : $i18n.orgNo,// 机构代码
							id : 'orgNo3103',	
				       	   // readOnly:true,
				       	    store:Ext.create("cms.store.common.comboStore").load(
				       	    {
				       	         params:{str:"N,ORG_NO"}
				       	    }),
				       	    allowBlank : false,
				       	    beforeLabelTextTpl : required
						},{
							fieldLabel:$i18n.exp_remark,//出货备注
							//readOnly:true,
							colspan:2,
							id:'exp_remark3103',
							 width:510,
							maxLength:100
				  		},{
							xtype : 'datetimefield',
							fieldLabel : '预计发货日期', //出货确认日期
							id : 'custsend_Date3103',							
							format : 'Y-m-d H:i:s',
							//readOnly:true
							//beforeLabelTextTpl : required	
						},{
							xtype : 'datetimefield',
							fieldLabel : '实际发货日期', //出货最晚日期
							id : 'last_custsend_Date3103',							
							format : 'Y-m-d H:i:s',
							//readOnly:true
						//	beforeLabelTextTpl : required	
						},{

							fieldLabel:'平台订单号',
							id:'cust_exp_no3103',
							//readOnly:true,
							maxLength:100
						}]	
					}]
			    },{

			    	title:'收货人信息',
			    	layout:'border',
			    	items:[
					{
						xtype : 'form',
			    	    id : 'form_02_3103',
			    	    border : false,
			    	    bodyStyle: 'background:#DFE9F6;',
			    	    layout:{
		    	    		type:'table',
		    	    		columns:4
		    	    	},
		    	    	defaults : {
							xtype:'textfield',
							labelWidth : 100,
							margin : '2 2 4 2',
							labelAlign : 'right'
				  		},
			    	    items :[
		    	    	{
							fieldLabel:'收货人',//联系人
							readOnly:true,
							id:'contactor_name3103',
							maxLength:50
				  		},{
							fieldLabel:'收货人电话',//客户电话
							readOnly:true,
							id:'cust_phone3103',
							maxLength:50
				  		},{
							fieldLabel:'收货人固定电话',
							readOnly:true,
							id:'receive_telephone3103',
							maxLength:100
				  		},{
							fieldLabel:'收货人邮箱',//邮箱地址
							readOnly:true,
							id:'cust_email3103',
							maxLength:50
				  		},{
							fieldLabel:'收货公司名称',
							readOnly:true,
							id:'receive_company_name3103',
							maxLength:100
				  		},{
							fieldLabel:'收货人国家二字码',
							readOnly:true,colspan:3,
							id:'receive_jpn3103',
							maxLength:100
				  		},{
							fieldLabel:'收货省',
							readOnly:true,
							id:'receive_province3103',
							maxLength:100
				  		},{
							fieldLabel:'收货市',
							readOnly:true,
							id:'receive_city3103',
							maxLength:100
				  		},{
							fieldLabel:'收货区',
							readOnly:true,
							id:'receive_zone3103',
							maxLength:100
				  		},{
							fieldLabel:'收货村镇',
							readOnly:true,
							id:'receive_country3103',
							maxLength:100
				  		},{
							fieldLabel:'收货人地址',//客户地址
							readOnly:true,colspan:2,
							width:520,
							id:'cust_address3103',
							maxLength:100
				  		}]	
					}]
			    
			    },{


			    	title:'提货人信息',
			    	layout:'border',
			    	items:[
					{
						xtype : 'form',
			    	    id : 'form_03_3103',
			    	    border : false,
			    	    bodyStyle: 'background:#DFE9F6;',
			    	    layout:{
		    	    		type:'table',
		    	    		columns:4
		    	    	},
		    	    	defaults : {
							xtype:'textfield',
							labelWidth : 100,
							margin : '2 2 4 2',
							labelAlign : 'right'
				  		},
			    	    items :[
		    	    	{
							fieldLabel:'提货人',
							readOnly:true,
							id:'take_name3103',
							maxLength:50
				  		},{
							fieldLabel:'提货人电话',
							readOnly:true,
							id:'take_mobile_phone3103',
							maxLength:50
				  		},{
							fieldLabel:'提货人固定电话',
							readOnly:true,
							id:'take_telephone3103',
							maxLength:100
				  		},{
							fieldLabel:'提货人邮箱',
							readOnly:true,
							id:'take_postcode3103',
							maxLength:50
				  		},{
							fieldLabel:'提货公司名称',
							readOnly:true,
							id:'take_company_name3103',
							maxLength:100
				  		},{
							fieldLabel:'提货人国家二字码',
							readOnly:true,colspan:3,
							id:'take_jpn3103',
							maxLength:100
				  		},{
							fieldLabel:'提货省',
							readOnly:true,
							id:'take_province3103',
							maxLength:100
				  		},{
							fieldLabel:'提货市',
							readOnly:true,
							id:'take_city3103',
							maxLength:100
				  		},{
							fieldLabel:'提货区',
							readOnly:true,
							id:'take_zone3103',
							maxLength:100
				  		},{
							fieldLabel:'提货村镇',
							readOnly:true,
							id:'take_country3103',
							maxLength:100
				  		},{
							fieldLabel:'提货人地址',
							readOnly:true,colspan:2,
							width:520,
							id:'take_address3103',
							maxLength:100
				  		}]	
					}]
			    
			    
			    },{


			    	title:'发货人信息',
			    	layout:'border',
			    	items:[
					{
						xtype : 'form',
			    	    id : 'form_04_3103',
			    	    border : false,
			    	    bodyStyle: 'background:#DFE9F6;',
			    	    layout:{
		    	    		type:'table',
		    	    		columns:4
		    	    	},
		    	    	defaults : {
							xtype:'textfield',
							labelWidth : 100,
							margin : '2 2 4 2',
							labelAlign : 'right'
				  		},
			    	    items :[
		    	    	{
							fieldLabel:'发货人',
							readOnly:true,
							id:'send_name3103',
							maxLength:50
				  		},{
							fieldLabel:'发货人电话',
							readOnly:true,
							id:'send_mobile_phone3103',
							maxLength:50
				  		},{
							fieldLabel:'发货人固定电话',
							readOnly:true,
							id:'send_telephone3103',
							maxLength:100
				  		},{
							fieldLabel:'发货人邮箱',
							readOnly:true,
							id:'send_postcode3103',
							maxLength:50
				  		},{
							fieldLabel:'发货公司名称',
							readOnly:true,
							id:'send_company_name3103',
							maxLength:100
				  		},{
							fieldLabel:'发货人国家二字码',
							readOnly:true,colspan:3,
							id:'send_jpn3103',
							maxLength:100
				  		},{
							fieldLabel:'发货省',
							readOnly:true,
							id:'send_province3103',
							maxLength:100
				  		},{
							fieldLabel:'发货市',
							readOnly:true,
							id:'send_city3103',
							maxLength:100
				  		},{
							fieldLabel:'发货区',
							readOnly:true,
							id:'send_zone3103',
							maxLength:100
				  		},{
							fieldLabel:'发货村镇',
							readOnly:true,
							id:'send_country3103',
							maxLength:100
				  		},{
							fieldLabel:'发货人地址',
							readOnly:true,colspan:2,
							width:520,
							id:'send_address3103',
							maxLength:100
				  		}]	
					}]
			    
			    
			    }]
				
			},{
	    		xtype : 'grid',
	    	    region:'south',height:310,
	    	    id:'grid_02_3103',
	    	    loadMask : true, // 加载时有加载的图标
	    	    store : Ext.create('cms.store.odata.odata_ExpDStore',{
	    	    	listeners:{
	    	    		'load':function(th,records,successful,eOpts ){
	    	    			var arrayObj = new Array();
         					arrayObj[0]='planBox';
         					arrayObj[1]='planQmin';
         					arrayObj[2]='planDis';
         					arrayObj[3]='unitCost';
         					countList('grid_02_3103',arrayObj,'articleNo');
	    	    		}
	    	    	}
	    	    }),
	    	    selModel : {
	    	        selType : 'cellmodel'
	    	    },
	    	    plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
	    	        clicksToEdit : 1,
	    	        onSpecialKey:function(ed,field,e){
						commEnterGridStatEdit(this.grid,true,'cms.controller.odata.odata_B2CExpController',e.getKey());
					}
	    	    })],
	    	    columns : [
	    	    {
	    	        xtype : 'rownumberer',
	    	        width : 30
	    	    },{
    	    		width : 100,
					text : $i18n.article_no,//商品编码
					dataIndex : 'articleNo',
					cls : 'notnull',
					field : {							
	    	        	id : 'article_no3103',
	    	        	xtype:'bdef_DefArticleCombo',
	    	        	displayField : 'value',
		        		valueField : 'value',
	    	        	store:Ext.create("cms.store.bdef.bdef_DefArticleComboStore"),
	    	        	allowBlank :false	
	    	        } 
	    	    },{
				    width:100,
				    text:$i18n.owner_article_no,//货主商品编码
				    dataIndex:'ownerArticleNo'
	    	    },{
				    width:100,
				    text:$i18n.barcode,//商品条码
				    dataIndex:'barcode'
	    	    },{
				    width:180,
				    text:$i18n.article_name,//商品名称
				    dataIndex:'articleName'
	    	    },{
	    	    	width : 65,
					text : $i18n.packing_qty,//包装数量
					dataIndex : 'packingQty',
					cls : 'notnull',
					field:{
						xtype : 'bdef_PackingQtyCombo',
						id : 'packing_qty3103',
						store:Ext.create('cms.store.bdef.bdef_PackingQtyComboStore'),
						editable:false,
						allowBlank :false
					}
	    	    },{
				    width:75,
				    text:$i18n.packingUnit,//包装单位
				    id:'packingUnit3103',
				    dataIndex:'packingUnit'
	    	    },{
				    width:75,
				    text:$i18n.packingSpec,//规格
				    id:'packingSpec3103',
				    dataIndex:'packingSpec'
	    	    },{
				    width:100,
				    text:$i18n.owner_article_no,//业主商品编码
				    hidden:true,
				    dataIndex:'ownerArticleNo'
	    	    },{
					width : 75,
					text : $i18n.planBox,//采购箱数
					dataIndex : 'planBox',
					id:'planBox3103',
					cls : 'notnull',
					field : {
			    		xtype : 'numberfield',
			    		minValue:0
			    	}
				},{
					width : 75,
					text : $i18n.planQmin,//中包装数
					dataIndex : 'planQmin',
					id:'planQmin3103',
					cls : 'notnull',
					field : {
			    		xtype : 'numberfield',
			    		minValue:0
			    	}
				},{
					width : 75,
					text : $i18n.planDis,//采购零散数
					dataIndex : 'planDis',
					id:'planDis3103',
					cls : 'notnull',
					field : {
			    		xtype : 'numberfield',
			    		minValue:0
			    	}
				},{
					width : 50,
					text:$i18n.unit_cost,//单价
					dataIndex : 'unitCost',
					cls : 'notnull',
					field : {
    	        		xtype : 'numberfield',
    	        		minValue:0
    	        	}
				},{
					width:100,
					text:'生产日期条件',
					dataIndex:'produceCond',
					field:{
						xtype:'wms_DefFieldValCombo',
	    	    		store:Ext.create("cms.store.common.comboStore").load(
	    	    		{
	    	       	 		params:{str:"ODATA_EXP_D,PRODUCE_CONDITION"}
	    	    		}),
	    	    		editable:false
					},
					renderer:function(value,text,dropvalue,kk,ff,ww){   
					    if(value=='1'){
					    	return '[1]>=';
					    }else if(value=='2'){
					    	return '[2]<=';
					    }else if(value=='3'){
					    	return '[3]>';
					    }else if(value=='4'){
					    	return '[4]<';
					    }else if(value=='5'){
					    	return '[5]=';
					    }else if(value=='6'){
					    	return '[6]like';
					    }else if(value=='7'){
					    	return '[7]between';
					    }else{
					    	return 'N';
					    }
					}/*,
					hidden:true*/
				},{
					width:100,
					text:'生产日期1',
					dataIndex:'produceV1',
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
					}/*,
					hidden:true*/
				},{
					width:100,
					text:'生产日期2',
					dataIndex:'produceV2',
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
					}/*,
					hidden:true*/
				},{
					width:100,
					text:'批号条件',
					dataIndex:'lotnoCondition',
					cls : 'allownull',
					field:{
						xtype:'wms_DefFieldValCombo',
	    	    		store:Ext.create("cms.store.common.comboStore").load(
	    	    		{
	    	       	 		params:{str:"ODATA_EXP_D,LOTNO_CONDITION"}
	    	    		})
					},
	    	        renderer:function(value,text,dropvalue,kk,ff,ww){   
					    if(value=='1'){
					    	return '[1]>=';
					    }else if(value=='2'){
					    	return '[2]<=';
					    }else if(value=='3'){
					    	return '[3]>';
					    }else if(value=='4'){
					    	return '[4]<';
					    }else if(value=='5'){
					    	return '[5]=';
					    }else if(value=='6'){
					    	return '[6]like';
					    }else if(value=='7'){
					    	return '[7]between';
					    }else if(value=='8'){
					    	return '[8]优先出';
					    }
					},
	    	    	editable:false
					
				},{
					width:150,
					text:'批次号1',
					dataIndex:'lotnoValue1',
					cls : 'allownull',
					field : {
						xtype:'textfield'
					}
				},{

					width:150,
					text:'批次号2',
					dataIndex:'lotnoValue2',
					cls : 'allownull',
					field : {
						xtype:'textfield'
					}
				
				},{

					width:150,
					text:'指定字段',
					dataIndex:'specifyField',
					cls : 'allownull',
					field:{
						xtype:'wms_DefFieldValCombo',
	    	    		store:Ext.create("cms.store.common.comboStore").load(
	    	    		{
	    	       	 		params:{str:"ODATA_EXP_D,SPECIFY_FIELD"}
	    	    		})
					},
				    renderer:function(value,text,dropvalue,kk,ff,ww){   
					    if(value=='cell_no'){
					    	return '[cell_no]储位';
					    }else{
					    	return '';
					    }
					}
				},{
					width:100,
					text:'指定条件',
					dataIndex:'specifyCondition',
					cls : 'allownull',
					field:{
						xtype:'wms_DefFieldValCombo',
	    	    		store:Ext.create("cms.store.common.comboStore").load(
	    	    		{
	    	       	 		params:{str:"ODATA_EXP_D,PRODUCE_CONDITION"}
	    	    		})
					},
    	    	    renderer:function(value,text,dropvalue,kk,ff,ww){   
					    if(value=='1'){
					    	return '[1]>=';
					    }else if(value=='2'){
					    	return '[2]<=';
					    }else if(value=='3'){
					    	return '[3]>';
					    }else if(value=='4'){
					    	return '[4]<';
					    }else if(value=='5'){
					    	return '[5]=';
					    }else if(value=='6'){
					    	return '[6]like';
					    }else if(value=='7'){
					    	return '[7]between';
					    }else if(value=='8'){
					    	return '[8]优先出';
					    }
					},
	    	    	editable:false
					
				
				},{
					width:150,
					text:'指定值1',
					dataIndex:'specifyValue1',
					cls : 'allownull',
					field : {
						xtype:'textfield'
					}
				},{

					width:150,
					text:'指定值2',
					dataIndex:'specifyValue2',
					cls : 'allownull',
					field : {
						xtype:'textfield'
					}
				
				}],
	    	    dockedItems : [{
	    	        xtype:'commMenuWidget6',
	    	        region:'north',
	    	        id:'toolbar3103'
	    	    }]
	    	}
	    	]
	    }]
	}]
});