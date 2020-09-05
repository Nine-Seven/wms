var wmsWavePlanMStore3914=Ext.create('cms.store.odata.odata_WmsWavePlanMStore',{
	autoLoad:true,
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('wmsWavePlanM3914').getStore().count()>0){
			   Ext.getCmp('wmsWavePlanM3914').getSelectionModel().select(0);
			}else{
			   Ext.getCmp('wmsWavePlanD3914').getStore().removeAll();
			}
		}
	}
});

var wmsWavePlanDStore3914 = Ext.create('cms.store.odata.odata_WmsWavePlanDStore',{
	autoLoad:false,
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			var selectFlag=_myAppGlobal.getController('cms.controller.odata.odata_WmsWavePlanController').getSelectFlag();
			var ruleIdFlag=_myAppGlobal.getController('cms.controller.odata.odata_WmsWavePlanController').getRuleIdFlag();
			
			if(th.count()>0){
				if(selectFlag == '1'){
					Ext.getCmp('wmsWavePlanD3914').getSelectionModel().select(0);
				 }else{
					 if(ruleIdFlag == ''){
						 Ext.getCmp('wmsWavePlanD3914').getSelectionModel().select(th.count()-1);
					 }else{
						 for(var i=0; i<th.count();i++){
		   					 var data = Ext.getCmp('wmsWavePlanD3914').getStore().getAt(i);
		   					 if(data.get('batchRuleId')== ruleIdFlag){
		   						 Ext.getCmp('wmsWavePlanD3914').getSelectionModel().select(i);
		   						 //return;
		   					 }		
		   				 }
					 }
				 }
			}
		}
	}
});

//配置详细信息
var wmsWavePlanDTrialDetail3914 = Ext.create('cms.store.odata.odata_WmsWavePlanDStore',{
	autoLoad:false,
	
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_WmsWavePlanAction_getWmsWavePlanDTrialDetail.action',
		reader:{
			type:'json',
			root:'rootList',
			totalProperty:'totalCount'
		}
	},
	
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('wmsWaveTrialDetail3914').getStore().count()>0){
			   Ext.getCmp('wmsWaveTrialDetail3914').getSelectionModel().select(0);
			}
		}
	}
});

var wmsWavePlanDStore23914 = Ext.create('cms.store.odata.odata_WmsWavePlanDStore',{
	autoLoad:true,
	
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_WmsWavePlanAction_getWmsWavePlanDTrial.action',
		reader:{
			type:'json',
			root:'rootList',
			totalProperty:'totalCount'
		}
	},
	
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('wmsWavePlanD23914').getStore().count()>0){
			   Ext.getCmp('wmsWavePlanD23914').getSelectionModel().select(0);
			}
		}
	}
});



Ext.define('cms.view.odata.odata_WmsWavePlanUI',{
	alias:'widget.wms_warvePlanUI',
	width:'90%',
	height:'90%',
	title:$i18n.wavePlanRule,//波次计划规则
	layout:'border',
	requires:[
			'cms.view.common.commMenu2',
			'cms.view.common.commMenu6',
			'cms.view.common.wms_DefFieldValCombo',
			'cms.view.common.remoteCombo'
			],
	extend:'Ext.panel.Panel',
	items:[ 	
		{
			xtype : 'commMenuWidget2',
			region:'north',
			id:'menu3914'
		},
		{
    	xtype : 'grid',
		id : 'wmsWavePlanM3914',
		store:wmsWavePlanMStore3914,
		region:'center',
		columns : [ {
			xtype : 'rownumberer',
			width : 30
		},{
			width : 100,
			text : $i18n.strategyId,//波次策略规则ID
			dataIndex : 'batchStrategyId'
		},{
			width : 150,
			text : $i18n.strategyTypeName,//波次策略规则名称
			dataIndex : 'strategyName'
		},{
			width : 150,
			text : '创建人',//创建人
			dataIndex : 'rgstName'
		},{
			width : 150,
			text : '创建时间',//创建时间
			dataIndex : 'rgstDateText',
		}]
    },
    {
    	height : 405,
    	region:'south',
    	layout:'border',
    	items:[
			{
			    xtype:'commMenuWidget6',
			    id:'menu_3914_2',
			    region:'north',
			    items : [ {
				    	   text : $i18n.additem,
				    	   name : 'detailAdd',
				    	   iconCls : 'add'//新增
				       }, {
				    	   text : $i18n.delete_1,
				    	   name : 'detailDelete',
				    	   iconCls : 'delete'//删除
				       },{
				    	   text : $i18n.titleupdate,
				    	   name : 'detailEdit',
				    	   iconCls : 'edit' //修改
				       },{
				    	   text : $i18n.undo,
				    	   name : 'detailUndo',
				    	   iconCls : 'undo' //撤销
				       },{
				    	   text : $i18n.save,
				    	   name : 'detailSave',
				    	   iconCls : 'save' //保存
				       }]
			},
			{
				xtype:'grid',
				id:'wmsWavePlanD3914',
				store:wmsWavePlanDStore3914,
				title:$i18n.strategyType,
				width:'32%',
				region:'west',
				
				columns:[{			
					xtype : 'rownumberer',
					width : 30
				},{
					width:60,
					text:'规则代码',//规则代码   
					dataIndex:'batchRuleId',
					sortable: false
				},{
					width:218,
					text:$i18n.strategyName,//规则名称   
					dataIndex:'batchRuleName',
					sortable: false
				},{
					width:48,
					text:'顺序',//顺序   
					dataIndex:'seqOrder',   
					sortable: false
				}],
				dockedItems : [{
					xtype : 'pagingtoolbar',
					dock : 'bottom',
					store:wmsWavePlanDStore3914,
					displayInfo : true
				}]
			},{

				xtype : 'form',
				region : 'center',
				layout:{
					type:'table',
					columns:1
				},
				width:'5%',
				frame : true,
				defaults:{
					margin:'10 0 0 8'
				},
				items : [{
					xtype:'button',
					margin:'100 0 0 8',
					text:'↑',
					width:30,
					heigth:100,
					id:'top3914'
				},
				{
					xtype:'button',			
					text:'↓',
					width:30,
					heigth:100,
					id:'down3914'
				}]
			
				
			},
			//6-4修改
			{
				xtype : 'tabpanel',
				width:'63%',
			    region:'east',
			    id:'tabPid3914',
			    flex : 4,
			    items : [
			    {
			    	title:"基本信息",			//基本信息
			        layout:'border',
			        id:'tabPid3914_01',
			        items:[
		            {
		        		xtype : 'form',
		        		baseCls:'my-panel-no-border',
		        		id : 'formWavePlan3914_1',
		        		region : 'north',
		        		margin:'15 2 2 2 ',
		        		frame : true,
		        		layout: 
		                {
		                	type: 'table',
		                	columns: 2
		                },
		        		defaults : 
		                {
		                	xtype : 'textfield',
		                	labelWidth : 160,
		                	labelAlign:'right'			
		         	    },
		        		items:[
		        		{	
		        			xtype:'textfield',
			  			  	fieldLabel : "规则代码",  
			  			  	id : 'batchRuleId3914',
							labelAlign:'right',
							allowBlank : false
						},{	
			                	xtype:'textfield',
		   	                	fieldLabel : "规则名称",		
		   	                	id : 'batchRuleName3914',
				       	        labelAlign:'right',
				       	        width : 400,
				       	        allowBlank : false
			       	        },{
		         				fieldLabel : '订单类型',   //订单类型 
		         				id:'skuCountMode3914',
		     					xtype:'wms_DefFieldValCombo',
		     					store:Ext.create("cms.store.common.comboStore").load(
		     					{
		     						params:{str:"WMS_OUTWAVEPLAN_D,SKU_COUNT_MODE"}
		     					}),
		     				    displayField : 'dropValue',
		     				    valueField : 'value',
		     				    //7-28添加
		     				    listeners:{  
		     						'change':function(th,item,eOpts ){
		     							//获得下拉框的value值
		     							var t = Ext.getCmp('skuCountMode3914').getValue();
		     							if(t == '1')
		     	                        {
		     								Ext.getCmp('taskOrder3914').setDisabled(true);
		     	                        }else{
		     	                        	Ext.getCmp('taskOrder3914').setDisabled(false);
		     	                        }
		     						}
		     					} 
			       	        },{
			                	xtype:'textfield',
		   	                	fieldLabel : "每订单品项数上限",  
		   	                	id : 'skuCount3914',
				       	        labelAlign:'right',
				       	        allowBlank : false
			       	        
			       	        },{	
			                	xtype:'textfield',
		   	                	fieldLabel : "每订单重复品项数",
		   	                	id : 'skuLimmit3914',
				       	        labelAlign:'right',
				       	        allowBlank : false
			       	        },{				//商品重复度   7-15
			       	        	xtype:'textfield',
		   	                	fieldLabel : "商品重复度",
		   	                	id : 'repeatTimes3914',
				       	        labelAlign:'right',
				       	        allowBlank : false
			       	        },{	
			                	xtype:'textfield',
		   	                	fieldLabel : "每任务订单上限",
		   	                	id : 'taskOrder3914',
				       	        labelAlign:'right',
				       	        allowBlank : false
			       	        },{					//7-15  
			                	xtype:'textfield',
		   	                	fieldLabel : "每任务订单下限",
		   	                	id : 'minOrder3914',
				       	        labelAlign:'right',
				       	        allowBlank : false
			       	        },{	
			                	xtype:'textfield',
		   	                	fieldLabel : "最长等待时间(分)",
		   	                	id : 'waitTimes3914',
				       	        labelAlign:'right',
				       	        allowBlank : false
			       	        },{
			                	xtype:'textfield',    
		   	                	fieldLabel : "间隔时间(分)",   
		   	                	id : 'intervalTimes3914',
				       	        labelAlign:'right',
				       	        allowBlank : false
			       	        },/*{	
			                	xtype:'textfield',
		   	                	fieldLabel : "顺序",
		   	                	id : 'seqOrder3914',
				       	        labelAlign:'right',
				       	        allowBlank : false
			       	        },*/{	
			                	xtype:'checkboxfield',
		   	                	fieldLabel : "是否激活",
		   	                	id : 'status3914',
				       	        labelAlign:'right',
				       	        allowBlank : false,
				       	        checked:true
			       	        }]
		            }]
			    },{
			    	title:"过滤条件",			//过滤条件
			        layout:'border',
			        id:'tabPid3914_02',
			        items:[{
		        	    xtype:'form',
		        	    region:'center',
		        	    id:'formWavePlan3914_2',
		        	    frame:true,
		        	    items:[{
			           	       xtype:'fieldset',
			              	   layout: {
			           	       type: 'table',
			           	           columns: 2
			           	       },
			           	       defaults:{
			        	   	       xtype:'textfield',
			        	   		   margin:'5 4 1 4',
			        	   		   labelAlign:'right',
			        	   		   labelWidth:120
			        	       },
			        		   items:[{
			         				fieldLabel : '平台',   //平台    
			         				id:'orderSource3914',
			     					xtype:'wms_DefFieldValCombo',
			     					store:Ext.create("cms.store.common.comboStore").load(
			     					{
			     						params:{str:"WMS_OUTWAVEPLAN_D,ORDER_SOURCE"}
			     					}),
			     				    displayField : 'dropValue',
			     				    valueField : 'value'
			        			},
								{
			         				fieldLabel : '承运商',   //承运商
			         				id:'shipperNo3914',
			     					xtype:'wms_DefFieldValCombo',
			     					store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				     			    	    proxy:{
				     							type:'ajax',
				     							async:false,
				     							method:'post',
				     							url:'bdef_DefOwnerAction_queryShipperCombo.action',
				     							reader:{
				     								root:'rootList',
				     								totalProperty:'totalCount'
				     							}
				     						}
				     				    }).load(),
				     				    displayField : 'dropValue',
				     				    valueField : 'value'
			         			},{
			         				fieldLabel : '配送方式',   //配送方式   deviceType3914
			         				id:'deliverType3914',
			     					xtype:'wms_DefFieldValCombo',
			     					store:Ext.create("cms.store.common.comboStore").load(
			     					{
			     						params:{str:"WMS_OUTWAVEPLAN_D,DELIVER_TYPE"}
			     					}),
			     				    displayField : 'dropValue',
			     				    valueField : 'value'
				       	        },{
			         				fieldLabel : '配送站点',   //7-15  配送站点  DELIVER_ADDRESS
			         				id:'deliverAddress3914',
			     					xtype:'wms_DefFieldValCombo',
			     					store:Ext.create("cms.store.common.comboStore").load(
			     					{
			     						params:{str:"WMS_OUTWAVEPLAN_D,DELIVER_ADDRESS"}
			     					}),
			     				    displayField : 'dropValue',
			     				    valueField : 'value'
				       	        },{
			         				fieldLabel : '异性品限制',   //8-15  异性品限制  RULE_FLAG
			         				id:'ruleFlag3914',
			     					xtype:'wms_DefFieldValCombo',
			     					store:Ext.create("cms.store.common.comboStore").load(
			     					{
			     						params:{str:"WMS_OUTWAVEPLAN_D,RULE_FLAG"}
			     					}),
			     				    displayField : 'dropValue',
			     				    valueField : 'value'
				       	        },{
					        		id:'cLimmit3914',
					        		xtype:'radiogroup',
					        		fieldLabel:'出货类型',
					        		width:310,
					                columns: 2,
					                vertical: true,
					        		items:[{
					        			boxLabel: '拆零',id:'zx', name: 'rb',  inputValue: '0',checked:true
					        		},{
					        			boxLabel: '整箱',id:'fzx', name: 'rb',  inputValue: '1'
					        		}]
					        	
				       	        },{	
				                	xtype:'checkboxfield',
			   	                	fieldLabel : "是/否限制区域",
			   	                	id : 'areaLimmit3914',
					       	        labelAlign:'right',
					       	        allowBlank : false,
					       	        //checked:true
					       	        
			     				    listeners:{  		//8-6添加
			     						'change':function(th,item,eOpts ){
			     							//获得单选项的value值
			     							var t = Ext.getCmp('areaLimmit3914').getValue();
			     							if(t == false)
			     	                        {
			     								Ext.getCmp('areaLimmitValue3914').setDisabled(true);
			     								Ext.getCmp('areaLimmitValue3914').setValue('');
			     	                        }else{
			     	                        	Ext.getCmp('areaLimmitValue3914').setDisabled(false);
			     	                        }
			     						}
			     					}
					       	        
				       	        },{	                      //8-6添加   限制区域  AREA_LIMMIT_VALUE
				       				xtype : 'remoteCombo',
				       				fieldLabel:"限制区域",		
				       				id:'areaLimmitValue3914',
				       				store : Ext.create("cms.store.cdef.cdef_DefAreaStore",{
				       					proxy:{
				       						type:'ajax',
				       						method:'post',
				       						url:'odata_WmsWavePlanAction_queryCdefAreaCombo.action',
				       						reader:{
				       							root:'rootList',
				       							totalProperty:'totalCount'
				       						}
				       					}
				       				})
				       	        },{
				                	xtype:'checkboxfield',   //8-6添加  AREA_ALLOW
			   	                	fieldLabel : "是/否允许跨区",
			   	                	id : 'areaAllow3914',
					       	        labelAlign:'right',
					       	        allowBlank : false
					       	        //checked:true
				       	        },
			        		    {	
				                	xtype:'checkboxfield',
			   	                	fieldLabel : "是否含免拣商品",
			   	                	id : 'itemTypeFlag3914',
					       	        labelAlign:'right',
					       	        allowBlank : false,
					       	        hidden : true
				       	        }]
		        	    },
		        	    {
		                    xtype:'fieldset',
		          			layout: {
		              		type: 'table',
		              	        columns: 2
		              	    },
		              	    defaults:{
		            	   		xtype:'textfield',
		            	   		margin:'5 4 1 4',
		            	   		labelAlign:'right',
		            	   		labelWidth:120
		               	    },
		               	    items:[{
			        			xtype:'textfield',
				  			  	fieldLabel : "预留控制属性1",   
				  			  	id : 'rsvControl1',
								labelAlign:'right',
								//allowBlank : false
			       	        },{
			        			xtype:'textfield',
				  			  	fieldLabel : "预留控制属性2",
				  			  	id : 'rsvControl2',
								labelAlign:'right',
								//allowBlank : false
			       	        },{
			        			xtype:'textfield',
				  			  	fieldLabel : "预留控制属性3",
				  			  	id : 'rsvControl3',
								labelAlign:'right',
								//allowBlank : false
			       	        },{
			        			xtype:'textfield',
				  			  	fieldLabel : "预留控制属性4",
				  			  	id : 'rsvControl4',
								labelAlign:'right',
								//allowBlank : false
			       	        },{
			        			xtype:'textfield',
				  			  	fieldLabel : "预留控制属性5",
				  			  	id : 'rsvControl5',
								labelAlign:'right',
								//allowBlank : false
			       	        }]
		        	   }]
		            }]
			    },{
			    	title:'执行配置',			//执行配置
			        layout:'border',
			        id:'tabPid3914_03',
			        items:[
			           	{
			        	    xtype:'form',
			        	    region:'center',
			        	    id:'formWavePlan3914_3',
			        	    frame:true,
			        	    items:[{
				           	       xtype:'fieldset',
				              	   layout: {
				           	       type: 'table',
				           	           columns: 2
				           	       },
				           	       defaults:{
				        	   	       xtype:'textfield',
				        	   		   margin:'5 4 1 4',
				        	   		   labelAlign:'right',
				        	   		   labelWidth:120
				        	       },
				        		   items:[{
						        		id:'printType3914',
						        		xtype:'radiogroup',
						        		fieldLabel:'打印类型',
						        		width:330,
						                columns: 2,
						                hidden : true,  //7-27添加
						                vertical: true,
						        		items:[{
						        			boxLabel: '前置', name: 'rb',  inputValue: '1',checked:true
						        		},{
						        			boxLabel: '后置', name: 'rb',  inputValue: '2'
						        		}]
					       	        },{
				         				fieldLabel : '波次切分模式',   //波次切分模式
				         				id:'batchComputeType3914',
				     					xtype:'wms_DefFieldValCombo',
				     					store:Ext.create("cms.store.common.comboStore").load(
				     					{
				     						params:{str:"WMS_OUTWAVEPLAN_D,BATCH_COMPUTE"}
				     					}),
				     				    displayField : 'dropValue',
				     				    valueField : 'value',
				     				    //7-16添加
				     				    listeners:{  
				     						'change':function(th,item,eOpts ){
				     							//获得下拉框的value值
				     							var t = Ext.getCmp('batchComputeType3914').getValue();
				     							if(t == '0' || t == '1')
				     	                        {
				     								Ext.getCmp('batchCompute3914').setDisabled(true);
				     	                        }else{
				     	                        	Ext.getCmp('batchCompute3914').setDisabled(false);
				     	                        }
				     						}
				     					}   
					       	        },{
				         				fieldLabel : '波次切分方式',   //波次切分方式
				         				id:'batchCompute3914',
				     					xtype:'wms_DefFieldValCombo',
				     					store:Ext.create("cms.store.common.comboStore").load(
				     					{
				     						params:{str:"WMS_OUTWAVEPLAN_D,BATCH_COMPUTE_TYPE"}
				     					}),
				     				    displayField : 'dropValue',
				     				    valueField : 'value'
					       	        }]
				                
			        	    },
			        	    {
			                    xtype:'fieldset',
			          			layout: {
			              		type: 'table',
			              	        columns: 2
			              	    },
			              	    defaults:{
			            	   		xtype:'textfield',
			            	   		margin:'5 4 1 4',
			            	   		labelAlign:'right',
			            	   		labelWidth:120
			               	    },
			               	    items:[/*{		//7-21添加
			         				fieldLabel : '拣货方式',   //发票打印  LAST_PICK_FLAG
			         				id:'lastPickFlag3914',
			     					xtype:'wms_DefFieldValCombo',
			     					store:Ext.create("cms.store.common.comboStore").load(
			     					{
			     						params:{str:"WMS_OUTWAVEPLAN_D,LAST_PICK_FLAG"}
			     					}),
			     				    displayField : 'dropValue',
			     				    valueField : 'value'
				       	        },*/{		//7-29添加
				                	xtype:'checkboxfield',
			   	                	fieldLabel : "强制摘果",
			   	                	id : 'lastPickFlag3914',
					       	        labelAlign:'right',
					       	        allowBlank : false
					       	       // checked:true
				       	        },{		//7-29添加
			         				fieldLabel : '箱型拣货方式',   //C_DIVIDE_FLAG 箱型拣货方式
			         				id:'cDivideFlag3914',
			     					xtype:'wms_DefFieldValCombo',
			     					store:Ext.create("cms.store.common.comboStore").load(
			     					{
			     						params:{str:"WMS_OUTWAVEPLAN_D,C_DIVIDE_FLAG"}
			     					}),
			     				    displayField : 'dropValue',
			     				    valueField : 'value'
				       	        },{		//7-29添加
			         				fieldLabel : '拆零拣货方式',   //B_DIVIDE_FLAG 拆零拣货方式
			         				id:'bDivideFlag3914',
			     					xtype:'wms_DefFieldValCombo',
			     					store:Ext.create("cms.store.common.comboStore").load(
			     					{
			     						params:{str:"WMS_OUTWAVEPLAN_D,B_DIVIDE_FLAG"}
			     					}),
			     				    displayField : 'dropValue',
			     				    valueField : 'value'
				       	        }]
			        	   },{
			           	       xtype:'fieldset',
			              	   layout: {
			           	       type: 'table',
			           	           columns: 2
			           	       },
			           	       defaults:{
			        	   	       xtype:'textfield',
			        	   		   margin:'5 4 1 4',
			        	   		   labelAlign:'right',
			        	   		   labelWidth:120
			        	       },
			        		   items:[{
			         				fieldLabel : '发票打印',   //发票打印
			         				id:'printEnvoice3914',
			     					xtype:'wms_DefFieldValCombo',
			     					store:Ext.create("cms.store.common.comboStore").load(
			     					{
			     						params:{str:"WMS_OUTWAVEPLAN_D,PRINT_ENVOICE"}
			     					}),
			     				    displayField : 'dropValue',
			     				    valueField : 'value'
				       	        },{
			         				fieldLabel : '快递面单打印',   //快递面单打印
			         				id:'printWayBill3914',
			     					xtype:'wms_DefFieldValCombo',
			     					store:Ext.create("cms.store.common.comboStore").load(
			     					{
			     						params:{str:"WMS_OUTWAVEPLAN_D,PRINT_WAYBILL"}
			     					}),
			     				    displayField : 'dropValue',
			     				    valueField : 'value'
				       	        },{
			         				fieldLabel : '内置清单打印',   //内置清单打印
			         				id:'printPackList3914',
			     					xtype:'wms_DefFieldValCombo',
			     					store:Ext.create("cms.store.common.comboStore").load(
			     					{
			     						params:{str:"WMS_OUTWAVEPLAN_D,PRINT_PACKLIST"}
			     					}),
			     				    displayField : 'dropValue',
			     				    valueField : 'value'
				       	        }]
			                
		        	    
			        	   },
			        	   
			        	   
			        	   
			        	   
			        	   
			        	   {
			           	       xtype:'fieldset',
			              	   layout: {
			           	       type: 'table',
			           	           columns: 3
			           	       },
			           	       defaults:{
			        	   	       xtype:'textfield',
			        	   		   margin:'5 4 1 4',
			        	   		   labelAlign:'right',
			        	   		   labelWidth:120
			        	       },
			        		   items:[{
				        			xtype:'textfield',
					  			  	fieldLabel : "预留属性1",   
					  			  	id : 'rsvValue1',
									labelAlign:'right',
									//allowBlank : false
				       	        },{
				        			xtype:'textfield',
					  			  	fieldLabel : "预留属性2",
					  			  	id : 'rsvValue2',
									labelAlign:'right',
									//allowBlank : false
				       	        },{
				        			xtype:'textfield',
					  			  	fieldLabel : "预留属性3",
					  			  	id : 'rsvValue3',
									labelAlign:'right',
									//allowBlank : false
				       	        },{
				        			xtype:'textfield',
					  			  	fieldLabel : "预留属性4",
					  			  	id : 'rsvValue4',
									labelAlign:'right',
									//allowBlank : false
				       	        },{
				        			xtype:'textfield',
					  			  	fieldLabel : "预留属性5",
					  			  	id : 'rsvValue5',
									labelAlign:'right',
									width : 220,
									colspan:2
									//allowBlank : false
				       	        },{
				        			xtype:'textfield',
					  			  	fieldLabel : "预留打印属性1",  
					  			  	id : 'printValue1',
									labelAlign:'right',
									//allowBlank : false
				       	        },{
				        			xtype:'textfield',
					  			  	fieldLabel : "预留打印属性2",  
					  			  	id : 'printValue2',
									labelAlign:'right',
									//allowBlank : false
				       	        }]
			                }]
			            }]
			    },{
			    	title:'试算配置',			//试算配置
			        layout:'border',
			        id:'tabPid3914_04',
			        items : [{
						xtype:'grid',
						id:'wmsWavePlanD23914',
						store:wmsWavePlanDStore23914,
						//7-19添加
						listeners:{
					        beforeedit:function(editor, e, eOpts){
					        	var editFlag=_myAppGlobal.getController('cms.controller.odata.odata_WmsWavePlanController').getEditFlag();
					        	if(editFlag == '1'){
					        		return false;//不可编辑
					        	}else if(editFlag == '0'){
					        		return true;//可编辑
					        	}
					        }
					    },
						title:'配置名称',
						width:'40%',
						region:'west',
						plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
			        	    clicksToEdit : 1,
				            onSpecialKey:function(ed,field,e){
							commEnterGridStatEdit(this.grid,true,'cms.controller.odata.odata_WmsWavePlanController',e.getKey());
						}
				    })],
						
						columns:[{			
							xtype : 'rownumberer',
							width : 30
						},{
							width:150,
							text:'名称',//名称
							dataIndex:'text',
							sortable: false
						},{
		         		    width:90,
		         		    text : '规则id',  //规则id
		         		    id:'ruleIdTrial',
		         		    //cls:'notnull',
		         		    //readOnly:true,
		         		    dataIndex:'trialRuleId',
		         		    field:{
		           		       xtype:'wms_DefFieldValCombo',
		       	   			   id:'valueFlag3914',
		       	   			   //readOnly:true,
		       	   			   displayField : 'dropValue',
		       	   			   valueField : 'value',
		       	   			   autoLoad:false,
		       	   			   store:Ext.create("cms.store.common.comboStore",{
					   			  proxy:{
					   					 type:'ajax',
					   					 method:'post',
					   					 url:'odata_WmsWavePlanAction_getWmsWavePlanDTrialSelect.action',
					   					 reader:{
					   					   root:'rootList',
					   					   totalProperty:'totalCount'
					   					 }
					   			  }
					   		    }),
		       				   	allowBlank : false,
		  					},
		   					renderer: function(value,metadata,record){
		   						//debugger;
		   						if(!Ext.isEmpty(value)){
		   							var params=
		   							{
		   									flagSet:record.raw.value
		   							};
		   							var a=[];
		   	  				    	Ext.Ajax.request({
		   	  				    	url:'odata_WmsWavePlanAction_getWmsWavePlanDTrialSelect.action',
		   		    				params:params,
		   	    					async : false,
		   		    				success:function(response){
		   		    					   a = Ext.decode(response.responseText);
		   		      				    }
		   		    				});
		   	  				    	for(var i=0;i<a.length;i++){
		   	  						   if(value==a[i].value){
		   	  							   return a[i].dropValue;
		   	  						   }
		   	  					     }
		   						}
		   	     		   }						
		         	    }]
					},{
		         	    xtype:'grid',
		         	    id:'wmsWaveTrialDetail3914',
		         	    title:'详细信息',
		         	    store:wmsWavePlanDTrialDetail3914,
		         	    region:'east',
		         	    width:'60%',
		         	    height:400,
		         	    columns:[{			
		         	        xtype : 'rownumberer',
		         		    width : 30
		         	    },{
							width:60,  
							text:'规则id',  //规则id
							dataIndex:'ruleId',
							field:{
								xtype:'numberfield'
							}	
						},{
							width:100,  
							text:'规则名称',  //规则名称
							dataIndex:'memo',
							field:{
								xtype:'textfield'
							}	
						},{
							width:100,  
							text:'区域规则',  //区域规则
							dataIndex:'allotRule',
							field:{
								xtype:'textfield'
							}	
						},{
							width:100,  
							text:'打印类型',  //打印类型  
							dataIndex:'printTypeTest',
							field:{
								xtype:'textfield'
							}	
						},{
							width:100,  
							text:'是否允许拣货差异',  //是否允许拣货差异  
							dataIndex:'pickDiffFlag',
							field:{
								xtype:'textfield'
							}	
						}]
		         	}]    
			        }]
			    }
			    ]
			}
        ]
});



















