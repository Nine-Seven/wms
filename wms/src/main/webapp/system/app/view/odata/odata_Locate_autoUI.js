/**
 * 模块名称：自动出货调度
 * 模块代码：3202
 * @author hkl
 */
var odata_LocateMStore=Ext.create('cms.store.odata.odata_LocateMStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_LocateAutoAction_getLocateM.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	},
    listeners:{
		'load':function(th,records,successful,eOpts ){
			var sum=0;
			if(th.data.length>0){
				for(var i=0;i<th.data.length;i++){
					sum+=th.data.items[i].data.expNoCount;
					//将状态为1的单据勾选上
					/*if(th.data.items[i].data.status==1){
						Ext.getCmp('grid_01_3202').getSelectionModel().select(i,true);
					}*/
				}
				Ext.getCmp('calculateQty3202').setText(th.data.items[0].data.calculateCount);
			}
			Ext.getCmp('lblexpCount3202').setText(sum);
		}
	}
});
var odata_LocateDStore=Ext.create('cms.store.odata.odata_LocateDStore');
var odata_getLocateFail =Ext.create('cms.store.odata.odata_getLocateFailStore',{autoLoad:true});
Ext.define('cms.view.odata.odata_Locate_autoUI',{
	alias:'widget.odata_Locate_autoUI',
	title:'自动出货调度',
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	            'cms.view.common.commMenu10',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.wms_DefFieldValCombo',
	          'cms.view.common.cdef_DefCellCombo',
	          'cms.view.common.bdef_DefCustCombo',
	          'cms.view.common.remoteCombo'
	          ],
	items:[
	{
		xtype:'commMenuWidget10',
	    id:'menu3202',
	    region:'north'
	},
	{
		xtype : 'tabpanel',
	    region:'center',
	    id:'tabPid3202',
	  //  flex : 4,
	    items : [{//定位条件
	    	title:'定位条件',
	    	layout:'border',
	    	items:[
			{
				xtype : 'form',
				region:'north',
				frame : true,
				layout: 
				{
					type: 'table',
					columns: 2
				},
				defaults : 
				{
					xtype : 'textfield',
					margin:'5 5 10 10 ',
					labelAlign:'right'			
			    },
			    items :[
	            {
	            	xtype:'bdef_DefOwnerCombo',
	    			fieldLabel : $i18n.owner_no,//货主编号
	    			id:'cmbOwnerNo3202',
	    			width:350,
	    			displayField : 'dropValue',
	    		    valueField : 'value',
	    	        allowBlank : false,
	    	        editable:false,
	    			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
	    				proxy:{
							type:'ajax',
							method:'post',
							url:'odata_LocateAction_queryOwnerCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
	    				},
	    				listeners:{
							'load':function(th,records,successful,eOpts )
							{
								if(Ext.getCmp('cmbOwnerNo3202').getStore().data.length>0)
								{
									Ext.getCmp('cmbOwnerNo3202').setValue(Ext.getCmp('cmbOwnerNo3202').getStore().getAt(0).data.value);		
								}
							}
						}
					})
	            },{

	            	xtype:'bdef_DefOwnerCombo',
	    			fieldLabel : $i18n.orgNo,//机构代码
	    			id:'orgNo3202',
	    			displayField : 'dropValue',
	    		    valueField : 'value',
	    	        allowBlank : false,
	    	        width:350,
	    	        editable:false,
	    			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
	    				proxy:{
							type:'ajax',
							method:'post',
							url:'odata_LocateAction_queryOrgNoCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
	    				},
	    				listeners:{
							'load':function(th,records,successful,eOpts )
							{
								if(Ext.getCmp('orgNo3202').getStore().data.length>0)
								{
									Ext.getCmp('orgNo3202').setValue(Ext.getCmp('orgNo3202').getStore().getAt(0).data.value);		
								}
							}
						}
					})	            
	            },{
	            	xtype: 'wms_DefFieldValCombo',
	            	fieldLabel:$i18n.exp_type,//出货单别
	           		width:350,
		        	id:'cmbExpType3202',
		        	store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
	    				proxy:{
							type:'ajax',
							method:'post',
							url:'odata_LocateAction_queryExpTypeCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							},
							extraParams:{
								strFlag : "1"
							}
	    				},
	    				listeners:{
							'load':function(th,records,successful,eOpts )
							{
								if(Ext.getCmp('cmbExpType3202').getStore().data.length>0)
								{
									Ext.getCmp('cmbExpType3202').setValue(Ext.getCmp('cmbExpType3202').getStore().getAt(0).data.value);		
								}
							}
						}
					})
	            },{
	            	xtype: 'wms_DefFieldValCombo',
	            	fieldLabel:'配送方式',//
	            	//colspan:2,
	           		width:350,
		        	id:'cmbDeliverType3202',
		        	store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
	    				proxy:{
							type:'ajax',
							method:'post',
							url:'odata_LocateAction_queryDeliverTypeCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
	    				},
	    				listeners:{
							'load':function(th,records,successful,eOpts )
							{
								if(Ext.getCmp('cmbDeliverType3202').getStore().data.length>0)
								{
									Ext.getCmp('cmbDeliverType3202').setValue(Ext.getCmp('cmbDeliverType3202').getStore().getAt(0).data.value);		
								}
							}
						}
					})
	            },{
					xtype:'bdef_DefCustCombo',
		  			fieldLabel:$i18n.sanpl_no,//承运商
		  			id:'sanpl3202',
		  			width:350,
		  			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
 			    	    proxy:{
 							type:'ajax',
 							async:false,
 							method:'post',
 							url:'odata_LocateAction_getlocateSanplNo',
 							reader:{
 								root:'rootList',
 								totalProperty:'totalCount'
 							}
 						}
 				    }).load(),
	       		 	displayField : 'dropValue',
	       			valueField : 'value'
		  		},{
					xtype:'bdef_DefCustCombo',
		  			fieldLabel:$i18n.cust,//客户编号
		  			id:'cust3202',
		  			colspan:2,
		  			width:350,
		  			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
 			    	    proxy:{
 							type:'ajax',
 							async:false,
 							method:'post',
 							url:'odata_LocateAction_getlocateCustNo',
 							reader:{
 								root:'rootList',
 								totalProperty:'totalCount'
 							}
 						}
 				    }).load(),
	       		 	displayField : 'dropValue',
	       			valueField : 'value'
		  		},{
    				xtype : 'datefield',
    				width:350,
    				fieldLabel : '单据接收时间',//日期
    				id : 'rgstDate3202',
    				format : 'Ymd'
    			},{
    				xtype : 'datefield',
    				width:350,
    				fieldLabel : '结束时间',//日期
    				id : 'rgstDate3202_1',
    				format : 'Ymd'
    			},
    				{
    				xtype : 'datefield',
    				width:350,
    				fieldLabel : $i18n.custsend_Date,//日期
    				id : 'cust_date3202',
    				format : 'Ymd'
    			},{
    				xtype : 'datefield',
    				width:350,
    				fieldLabel : '结束时间',//日期
    				id : 'cust_date3202_1',
    				format : 'Ymd'
    			},{
					xtype: 'checkboxfield',
		        	fieldLabel: '是否急单',   //是否急单
		        	id:'fastFlag3202',
		            Value:false
		        	   	
				},{
					xtype: 'checkboxfield',
		        	fieldLabel: $i18n.locatebyorder,//按单号调度
		        	id:'cbLocateByOrder3202',
		            Value:true		        	   	
				},{
    				xtype:'bdef_DefCustCombo',
		        	fieldLabel: '商品编码',//商品编码
		        	width:350,
		        	colspan:2,
		        	id:'articleNo3202',
		        	store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
 			    	    proxy:{
 							type:'ajax',
 							async:false,
 							method:'post',
 							url:'odata_LocateAction_getlocateArticleNo',
 							reader:{
 								root:'rootList',
 								totalProperty:'totalCount'
 							}
 						}
 				    }).load(),
 				    displayField : 'dropValue',
 				    valueField : 'value'
    			},{
					xtype:'bdef_DefCustCombo',
		        	fieldLabel: '开始路线',//路线
		        	width:350,
		        	id:'linkUI3202',
		        	store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
 			    	    proxy:{
 							type:'ajax',
 							async:false,
 							method:'post',
 							url:'odata_LocateAction_getLink',
 							reader:{
 								root:'rootList',
 								totalProperty:'totalCount'
 							}
 						}
 				    }).load(),
 				    displayField : 'dropValue',
 				    valueField : 'value'
				},{				
					xtype:'bdef_DefCustCombo',
		        	fieldLabel: '结束路线',//路线
		        	width:350,
		        	id:'linkUI3202_1',
		        	store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
 			    	    proxy:{
 							type:'ajax',
 							async:false,
 							method:'post',
 							url:'odata_LocateAction_getLink',
 							reader:{
 								root:'rootList',
 								totalProperty:'totalCount'
 							}
 						}
 				    }).load(),
 				    displayField : 'dropValue',
 				    valueField : 'value'				
				},{
					xtype: 'button',
					colspan:2,
                	text: '查询',
                	margin : '20 0 20 320',
                	height:40,
                	width:150,
                	id:'btnQuery3202'
				}]
		    }]
 	
	    },{//定位tab页>>开始
	    	xtype:'panel',
	    	title:$i18n.odataLocate,
	    	//id:'tabPid3202_2',
	    	layout:'border',
	    	items:[
			{
				xtype : 'form',
				region:'north',
				layout: 
				{
					type: 'table',
					columns: 8
				},
				defaults : 
				{
					xtype : 'textfield',
					margin:'5 10 5 10 ',
					labelAlign:'right'			
			    },
			    items :[				
				{
					xtype:'container',
					margin:'15 20 0 20 ',
					items:[
					{
						xtype:'label',
						readOnly:true,
						text:'客户数量:'
					},
					{
						xtype:'label',
						readOnly:true,
						id:'lblcustNumber3202',
						margin:'0 0 0 5',
						text:'0'
					}]
				},
				{
					xtype:'container',
					margin:'15 10 0 20 ',
					items:[
					{
						xtype:'label',
						readOnly:true,
						text:'普通配送:'
					},
					{
						xtype:'label',
						readOnly:true,
						id:'lblexpCount3202',
						margin:'0 0 0 5',
						text:'0'
					}]
				},{
					xtype: 'checkboxfield',
		        	fieldLabel: $i18n.showdetail,//显示明细
		        	id:'cbShowDetail3202',
		        	checked: true	
				},{
					xtype: 'checkboxfield',
		        	fieldLabel: $i18n.shownoenoughitem,//只显示缺量品项,
		        	id:'cbShowNoEnoughtItem3202'
				},{
					xtype: 'checkboxfield',
		        	fieldLabel: $i18n.isCheckBox,//
		        	id:'cbdivideFlag3202',
		        	checked: true		        	   	
				},{
					xtype: 'checkboxfield',
		        	fieldLabel: '月台试算',
		        	id:'calculation3202'
		        	//checked: true	
				},{
					xtype:'container',
					margin:'0 15 0 30 ',
					items:[
					{
						xtype:'label',
						readOnly:true,
						text:'月台可用货位数:'
					},
					{
						xtype:'label',
						readOnly:true,
						id:'calculateQty3202',
						margin:'0 0 0 5',
						text:'0'
					}]
				
				},{
					xtype:'container',
					margin:'0 10 0 30 ',
					items:[
			        {
						xtype: 'button',
		            	text: '重算月台资源',
		            	id:'btnBackroll3202'
					}]
					
				},{
					xtype: 'checkboxfield',
		        	fieldLabel: '分播设备',//
		        	colspan:3,
		        	margin:'0 0 0 210 ',
		        	id:'divideFlag3202',
		        	checked: true		   
				},{
					xtype: 'checkboxfield',
		        	fieldLabel: '复核台',//
		        	id:'arrangePack3202',
		        	checked: true		   
				}]
		    },
		    {
		    	xtype:'grid',
    		    region:'west',
    		    width:'40%',
    		    id:'grid_01_3202',
    		    store:odata_LocateMStore,
    		    multiSelect: true,  
				selModel: {
				    selType:'checkboxmodel',
				    checkOnly:true
				},
    		    columns:[
    		    {			
    		        xtype : 'rownumberer',
    			    width : 40
    		    },
    		    {
    			    width:125,
    			    text : $i18n.sourceexp_no,//订单号
    			    dataIndex:'sourceexpNo',
    			    hidden:true,
    			    id:'colSourceExpNo3202'
    		    },
    		    {
    			    width:130,
    			    text : $i18n.source_no,//出货单号
    			    dataIndex:'expNo',	
    			    hidden:true,
    			    id:'colExpNo3202'
    		    },
    		    {
    			    width:105,
    			    text : $i18n.cust_no,//客户编码
    			    dataIndex:'custNo'			
    		    },{
    			    width:105,
    			    text : $i18n.cust_name,//客户名称
    			    dataIndex:'custName'			
    		    },{
    		    	width:105,
      			    text : '客户简称',//客户简称
      			    dataIndex:'custAlias'	
    		    },
    		    {
    			    width:105,
    			    text : $i18n.line,//线路
    			    dataIndex:'lineNo'			
    		    },
    		    {
    			    width:105,
    			    text : $i18n.line_name1,//线路名称
    			    dataIndex:'lineName'			
    		    },
    		    {
    			    width:55,
    			    text : $i18n.total_sku,//品项数
    			    dataIndex:'articleItems'			
    		    }/*,
    		    {
    			    width:55,
    			    text : '总件数',//总数
    			    dataIndex:'sumBoxQty'			
    		    },{
    			    width:105,
    			    text : $i18n.contactor_name,//联系人
    			    dataIndex:'contactorName1'			
    		    },{
    			    width:105,
    			    text : $i18n.cust_phone1,//电话
    			    dataIndex:'custPhone1'			
    		    },{
    			    width:105,
    			    text : $i18n.cust_address,//地址
    			    dataIndex:'custAddress'			
    		    }*/]
   		    /*,
   		        dockedItems : [{
					xtype : 'pagingtoolbar',
					dock : 'bottom',
				//	ChangePage: false,
					store:odata_LocateMStore,
				    listeners : {  
		                    "beforechange" : function(bbar, params){  
		                        debugger
		                    //    Ext.getCmp("grid_01_3202").clearManagedListeners( );
		                        Ext.getCmp("grid_01_3202").removeListener("deselect",
		                        		_myAppGlobal.getController('cms.controller.odata.odata_Locate_autoController')
										.grid_01_3202Deselect());
		                    }  
		                }, 
					displayInfo : true
				}]*/
		    },
		    {
		    	xtype:'grid',
		    	region:'east',
		    	width:'60%',
		    	id:'grid_02_3202',
		    	store:odata_LocateDStore,
		    	viewConfig : {   
	                 forceFit : true,   
	                 getRowClass : function(record,rowIndex,rowParams,store){   
	                    //缺量数据显示红色   
	                    if(record.data.noEnoughQty>0 && Ext.getCmp('cbShowDetail3202').getValue()==true){   
	                        return 'x-grid-record-red';   
	                     }else{   
	                        return '';   
	                     }   
	                 }   
			    },  					
    		    columns:[
    		    {			
    		        xtype : 'rownumberer',
    			    width : 30
    		    },
    		    {
    			    width:105,
    			    text : $i18n.article_no,//商品编码
    			    dataIndex:'articleNo'			
    		    },
    		    {
    			    width:105,
    			    text : $i18n.owner_article_no,//货主商品编码
    			    dataIndex:'ownerArticleNo'			
    		    },
    		    {
    			    width:105,
    			    text : $i18n.barcode,//商品条码
    			    dataIndex:'barcode'			
    		    },
    		    {
    			    width:165,
    			    text : $i18n.article_name,//商品名称
    			    dataIndex:'articleName'			
    		    },
    		    {
    			    width:80,
    			    text : $i18n.articleqty,//计划数量
    			    dataIndex:'totalQty'			
    		    },
    		    {
    			    width:80,
    			    text : $i18n.available_qty,//可用数量
    			    dataIndex:'availableQty'			
    		    },
    		    {
    			    width:80,
    			    text : $i18n.no_enough_qty,//缺量
    			    dataIndex:'noEnoughQty'			
    		    },
    		    {
    			    width:80,
    			    text : $i18n.packingUnit,
				    id:'packingUnit3202',
    			    dataIndex:'packingUnit'			
    		    },
    		    {
    			    width:80,
    			    text : $i18n.packingSpec,
				    id:'packingSpec3202',
    			    dataIndex:'packingSpec'			
    		    }]
		    },
		    {
		    	xtype : 'form',
				region:'south',
				width:'100%',
				layout: 
				{
					type: 'table',
					columns: 5
				},
				defaults : 
				{
					xtype : 'textfield',
					labelWidth : 110,
					margin:'5 0 0 0 ',
					labelAlign:'right'			
			    },
			    items :[
				{
					xtype:'container',
					margin:'0 0 0 100',
					items:[
					{
						xtype:'label',
						readOnly:true,
						text:$i18n.ttl_volumn
					},
					{
						xtype:'label',
						readOnly:true,
						id:'lblSumVolumn3202',
						margin:'0 0 0 15',
						text:'0'
					}]
				},
				{
					xtype:'container',
					margin:'0 0 0 100',
					items:[
					{
						xtype:'label',
						readOnly:true,
						text:$i18n.ttl_weight
					},
					{
						xtype:'label',
						readOnly:true,
						id:'lblSumWeight3202',
						margin:'0 0 0 15',
						text:'0'
					}]
				},
				{
					xtype:'container',
					margin:'0 0 0 100',
					items:[
					{
						xtype:'label',
						readOnly:true,
						text:$i18n.ttl_packing_qty
					},
					{
						xtype:'label',
						readOnly:true,
						id:'lblSumBoxQty3202',
						margin:'0 0 0 15',
						text:'0'
					}]
				},{

					xtype:'cdef_DefCellCombo',
		  			fieldLabel:$i18n.addcell_no,//储位
		  			id:'cell3202',
		  			store:Ext.create('cms.store.cdef.cdef_DefCellComboStore',{
	    			    proxy:{
							type:'ajax',
							method:'post',
							url:'odata_LocateAction_getCdef_DefCellCombo',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
						}
	    			}),
	       		 	displayField : 'dropValue',
	       			valueField : 'value',
	       			beforeLabelTextTpl : required
				
				},{
					xtype:'container',
					items:[
			        {
						xtype: 'button',
		            	text: $i18n.locate_start,
		            	margin : '3 3 3 90',
		            	id:'btnFixed3202'
					}]
				}
	            ]
		    }
		    ]
	    },//定位tab页>>结束
	    {//出货续调tab>>开始
		xtype:'panel',
		title : $i18n.locateagain,//出货续调
		layout:'border',
		items:[{
			xtype:'panel',
			region:'north',
			layout:'hbox',
			defaults : {
				margin : '3 30 3 50'
			},
    		items:[{
	  			xtype: 'button',
	  			id:'but_locateContinue_3202',
	            text : $i18n.locate_continue
    			}]
			},{
			xtype:'grid',
			region:'center',
			store:Ext.create('cms.store.odata.odata_LocateAgainStore'),
			id:'grid_03_3202', 
			singleSelect:true,
			selModel: {  
			    selType:'checkboxmodel' , 
			    showHeaderCheckbox:false
			},
			columns : [{//波次号
				width : 150,
				text : $i18n.locate_no,
				dataIndex : 'waveNo'
				},{//出货单别
				width : 150,
				text : $i18n.exp_type,
				dataIndex : 'expType'
			}]	
		}]
		},//出货续调tab>>结束
		
///////////////////////////////////////////////////////////////////////////////////////////////
		{// 定位失败查询
	    	title:$i18n.locateFailReason,
	    	layout:'border',
			items:[{
				xtype:'panel',
				region:'north',
				height: 43,
				layout: {
				    type: 'table',
			        columns: 3
				},
			    defaults : {
					xtype : 'textfield',
					margin : '10 3 3 3',
					labelAlign:'right',
					allowBlank: true,
					width : 280,
					labelWidth : 90
				},
				items:[{
					fieldLabel : $i18n.locate_no,
					id:'locateNoUI3202',
					xtype:'wms_DefFieldValCombo',
					 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
						 proxy:{
							type:'ajax',
							method:'post',
							url:'odata_LocateAction_getLocateNoForQuery',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
						}
						}
			        }).load(),
				    displayField : 'dropValue',
				    valueField : 'value'
				},{
					fieldLabel :$i18n.article_no, //商品编码
					id:'articleNoUI3202',
					xtype:'remoteCombo',
					store:Ext.create('cms.store.idata.idata_PoNoStore',{
						 proxy:{
	 					type:'ajax',
	 					method:'post',
	 					url:'odata_LocateAction_getArticleNoForUI',
	 					reader:{
							root:'rootList',
	 						totalProperty:'totalCount'
							}
						}
				    }).load(),
				    displayField : 'dropValue',
				    valueField : 'value'
				}]
			},{
				xtype:'grid',
				region:'center',
				store:odata_getLocateFail,
				id:'grid_04_3202', 				
				columns : [
					{//波次号
						width : 150,
						text : $i18n.locate_no,
						dataIndex : 'waveNo'
					},{
						width : 120,
						text : '商品编码',
						dataIndex : 'articleNo'
					},{
						width:120,
						text:'货位',
						dataIndex:'cellNo'
					},{
						width:120,
						text:'操作类型',
						dataIndex:'operateType'
					},{
						width:300,
						text:'失败原因',
						dataIndex:'shortReason'
					}       
				],dockedItems : [{
					xtype : 'pagingtoolbar',
					dock : 'bottom',
					store:odata_getLocateFail,
					displayInfo : true	
		     }]	
			}]
	     }
	]}
	]
});