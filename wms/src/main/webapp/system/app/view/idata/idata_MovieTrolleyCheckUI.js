/**
 * 模块名称：移动台车验收
 * 模块编码：4902
 * 创建：hcx
 */
//var ridata_check_MStore=Ext.create('cms.store.ridata.ridata_CheckMStore',{autoLoad:false});
var idata_ImPort_d = Ext.create('cms.store.idata.idata_ImPort_DStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'idata_MovieTrolleyCheckAction_getImportDList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	},listeners:{  
   	 'load':function(th,records,successful,eOpts ){   
   		 if(th.count()>0){
   			 var flag=_myAppGlobal.getController('cms.controller.idata.idata_MovieTrolleyCheckController').getFlag();
   			 if(flag=='1'){   				
   				 var articleNo=_myAppGlobal.getController('cms.controller.idata.idata_MovieTrolleyCheckController').getArticleNo();	
   				 var packingQty=_myAppGlobal.getController('cms.controller.idata.idata_MovieTrolleyCheckController').getPackingQty();	 				 
   				 for(var i=0; i<th.count();i++){
   					 var data = Ext.getCmp('grid4902_1').getStore().getAt(i);
   					 if(data.get('articleNo')== articleNo && data.get('packingQty')== packingQty
   					    && data.get('noCheckQty')>0){
   						 Ext.getCmp('grid4902_1').getSelectionModel().select(i);
   						 return;
   					 }		
   				 }
   				
   				 _myAppGlobal.getController('cms.controller.idata.idata_MovieTrolleyCheckController').setFlag('0');
   			 }else {
   				 if(!Ext.isEmpty(Ext.getCmp("identifierOrBarcode4902").getValue())){
   					for(var i=0; i<th.count();i++){
      					 var data = Ext.getCmp('grid4902_1').getStore().getAt(i);
      					 if(data.get('barcode')== Ext.getCmp("identifierOrBarcode4902").getValue()){
      						 Ext.getCmp('grid4902_1').getSelectionModel().select(i);
      					 }	
   					}
   				 }else{
   					Ext.getCmp('grid4902_1').getSelectionModel().select(0);
   				 }
   			 }
   		 }
   	 }
    }
});
var Idata_Import_M = Ext.create('cms.store.idata.idata_ImPort_MStore',{
	proxy:{
		type:'ajax',
		async:false,
		method:'post',
		url:'idata_MovieTrolleyCheckAction_getPoNoAndSImportNoList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	},listeners:{  
	   	 'load':function(th,records,successful,eOpts ){   
	   		 if(th.count()>0){
	   			if(!Ext.isEmpty(Ext.getCmp("poNo4902").getValue())){
   					for(var i=0; i<th.count();i++){
      					 var data = Ext.getCmp('grid4902_2').getStore().getAt(i);
      					 if(data.get('poNo')== Ext.getCmp("poNo4902").getValue()){
      						 Ext.getCmp('grid4902_2').getSelectionModel().select(i);
      					 }	
   					}
   				 }
//	   			if(!Ext.isEmpty(Ext.getCmp("identifierOrBarcode4902").getValue())){
						 Ext.getCmp('grid4902_2').getSelectionModel().select(0);
//   				 }
	   		 }
	   	 }
	 }
});
Ext.define('cms.view.idata.idata_MovieTrolleyCheckUI',{
	alias:'widget.idata_MovieTrolleyCheckUI',
	title:$i18n.title4902,//移动台车验收
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.bdef_DefWorkerCombo',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.wms_DefFieldValCombo',
	          'cms.view.common.remoteCombo'
	          ],
	items:[
	       {
	    	   xtype : 'toolbar',
				region:'north',
				items : [{
					text : $i18n.refresh,
					iconCls : 'refresh',
					id:'refresh4902'
				    }]
	       }, {  
	    	   xtype : 'tabpanel',
	    	   region : 'center',
	    	   id:'tabPid4902',
	    	   items : [{
	    	    	title:$i18n.titleD,
	    	        layout:'border',
	    	        items:[
	    	        {
				    	xtype : 'form',
				    	id:'form_01_4902',
						layout:'column',
						region : 'north',
						frame : true,
						width : '100%',
						height:'7%',
						items:[{
						    layout:{
								type : 'table',
								columns : 3
							},
							xtype:'container',
							defaults:{
		//						labelWidth : 70,
								margin : '5 5 5 0',
								labelAlign : 'right',
								xtype:'textfield'
							},
							items:[
							{
								//xtype : 'remoteCombo',
								fieldLabel:$i18n.scanStation,//扫描台
								id : 'cmbDockNo4902',	
								//displayField: 'dropValue',
			    				//valueField: 'value',
							/*	store:Ext.create("cms.store.ridata.ridata_DockComboStore",
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
								}),
							*/
						        beforeLabelTextTpl : required
							},{
								xtype : 'bdef_DefWorkerCombo',
								fieldLabel : $i18n.dp_worker,//  验收人
								id : 'cmbWorkerNo4902',
								store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore'),
								beforeLabelTextTpl : required
							}]
						}]
				    },{
		
				    	xtype : 'form',
				    	id:'form_02_4902',
						layout:'column',
						frame : true,
						region : 'north',
						width : '100%',
						height:'14%',
						items:[{
						    layout:{
								type : 'table',
								columns : 3
							},
							xtype:'container',
							defaults:{
								margin : '5 5 5 0',
								labelAlign : 'right',
								xtype:'textfield'
							},
							items:[
						    {
						    	xtype : 'combo',
								fieldLabel : $i18n.owner_no,// 委托业主
								id:'owner4902',
								width : 310,
								displayField: 'dropValue',
								valueField: 'value',
								store:Ext.create("cms.store.bdef.bdef_DefOwnerComboStore",{
									proxy:{
										type:'ajax',
										method:'post',
										url:'idata_MovieTrolleyCheckAction_queryOwnerCombo.action',
										reader:{
											root:'rootList',
											totalProperty:'totalCount'
										}
				    				},
								    listeners:{  
										'load':function(th,records,successful,eOpts ){
											if(th.count()>0){
													Ext.getCmp('owner4902').setValue(th.getAt(0).data.value);
									   				 _myAppGlobal.getController('cms.controller.idata.idata_MovieTrolleyCheckController').ownerSelect();
											}
									      }
										}
							   	}).load(),
						   		beforeLabelTextTpl : required	   
							},{
		         				fieldLabel : $i18n.suppliers,   //供应商
		         				id:'suppliers4902',
								width : 310,
		     					xtype:'wms_DefFieldValCombo',
		     					 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
			     			    	    proxy:{
			     							type:'ajax',
			     							async:false,
			     							method:'post',
			     							url:'idata_MovieTrolleyCheckAction_getSupplierNo',
			     							reader:{
			     								root:'rootList',
			     								totalProperty:'totalCount'
			     							}
			     						}/*,
			     				    	listeners:{  
			     							'load':function(th,records,successful,eOpts ){
			     								if(Ext.getCmp('suppliers4902').getStore().count()>0){
			     									Ext.getCmp('suppliers4902').setValue(Ext.getCmp('suppliers4902').getStore().getAt(0).data.value);
									   				 _myAppGlobal.getController('cms.controller.idata.idata_MovieTrolleyCheckController').suppliersSelect();
			     								}
			     							}
			     						}*/
			     				    }),
			     				    
			     				    displayField : 'dropValue',
			     				    valueField : 'value'
		         			},
						    {
						    	xtype:'wms_DefFieldValCombo',
						        fieldLabel : $i18n.wms_print_flag,//吊牌标识
								width : 310,
						        id : 'cmbPrintFlag4902',
						        editable:false,
						        store:Ext.create("cms.store.common.comboStore").load(
						        {
						        	params:{str:"BDEF_DEFARTICLE,PRINT_FLAG"}
						        }),
						        allowBlank : false,
						        beforeLabelTextTpl : required
						    },{
								xtype : 'remoteCombo',
								fieldLabel : $i18n.po_no1,// 采购单号
								width : 310,
								id:'poNo4902',
								store : Ext.create("cms.store.idata.idata_PoNoStore",{
									proxy:{
										type:'ajax',
										method:'post',
										url:'idata_MovieTrolleyCheckAction_getPoNoList.action',
										reader:{
											root:'rootList',
											totalProperty:'totalCount'
										}
				    				}
								})
							},{
								xtype : 'remoteCombo',
								fieldLabel : $i18n.identifierOrBarcode,// 助记码/条码
								id:'identifierOrBarcode4902',
								width : 310,
								store : Ext.create("cms.store.idata.idata_PoNoStore",{
									proxy:{
										type:'ajax',
										method:'post',
										url:'idata_MovieTrolleyCheckAction_getIdentifierOrBarcode1List.action',
										reader:{
											root:'rootList',
											totalProperty:'totalCount'
										}
				    				}
								})
							}]
						}]
				    
				    },{
						xtype : 'grid',
						title:$i18n.product_information,//商品信息
						id:'grid4902_1',
						store:idata_ImPort_d,
						region:'west',
						width:'68%',
						height:'70%',
						columns : [ {
							xtype : 'rownumberer',
							width : 30
						},{
							width : 100,
							text : $i18n.article_no,//商品编码
							dataIndex : 'articleNo'
						},/*{
							width : 80,
							text : $i18n.article_identifier,//助记码
							dataIndex : 'articleIdentifier'
						},*/{
							width : 110,
							text : $i18n.barcode,//商品条码
							dataIndex : 'barcode'
						},{
							width : 180,
							text : $i18n.article_name,//商品名称
							dataIndex:'articleName'
						},{
						    width:70,
						    text:$i18n.import_qty1,//进货数量
						    dataIndex:'inQty'
		    		    },{
						    width:70,
						    text:$i18n.poQty,//要货数量
						    dataIndex:'poQty'
		    		    },{
						    width:70,
						    text:$i18n.check_qty,//验收数量
						    dataIndex:'checkQty'
		    		    },{
						    width:70,
						    text:$i18n.noCheckQty,//未数量
						    dataIndex:'noCheckQty'
		    		    },{
						    width:70,
						    text:$i18n.wms_print_flag,//吊牌标识
						    dataIndex:'printFlag'
		    		    },{
		    		    	width:50,
		 				    text:'包装单位',
		 				    dataIndex:'packingUnit'
		    		    },{
		    		    	width:70,
		 				    text:'包装数量',
		 				    dataIndex:'packingQty'
		    		    }],
					dockedItems : [{
		    	        xtype : 'pagingtoolbar',
		    	        store:idata_ImPort_d,
		    	        dock : 'bottom',
		    	        displayInfo : true
		    	    }]
		        },{
		    	 	xtype:'panel',
		    	    layout:'border',
		    	    region:'east',
		    	    width : '32%',
		    	    height : '70%',
		    	    items:[{
		    	    	xtype : 'grid',
		    			title:$i18n.titleM,//单据列表
		    			id:'grid4902_2',
		    			region:'north',
						height : '70%',
		//        	    selModel: {  
		//                	selType:'cellmodel' 
		//        	    },
		    			store:Idata_Import_M,
		    			columns : [ 
		    			 {
		    				xtype : 'rownumberer',
		    				width : 30
		    			},{
		    				width : 150,
		    				text : $i18n.po_no1,//采购单号
		    				dataIndex : 'poNo'
		    			},{
		    				width : 150,
		    				text : $i18n.s_import_no,//汇总进货单号
		    				dataIndex : 'SImportNo'
		    			}],dockedItems : [{
		        	        xtype : 'pagingtoolbar',
		        	        store:Idata_Import_M,
		        	        dock : 'bottom',
		        	        displayInfo : true
		        	    }]
		    	    },{
		    	    	xtype : 'form',
						layout:'column',
						id:'form_03_4902',
						region : 'south',
						frame : true,
						height : '30%',
						items:[
						       {
							layout:{
							type : 'table',
							columns : 1
							},
							xtype:'container',
							margin:'5 0 0 0',
							defaults:{
								labelWidth : 90,
								margin : '0 0 5 0',
								labelAlign : 'right',
								xtype:'textfield'
							},
							items:[
							{
								xtype : 'remoteCombo',
								fieldLabel : $i18n.identifierOrBarcode,// 助记码/条码
								id:'articleIdentifier4902',
								store : Ext.create("cms.store.idata.idata_PoNoStore",{
									proxy:{
										type:'ajax',
										method:'post',
										url:'idata_MovieTrolleyCheckAction_getIdentifierOrBarcode1List.action',
										reader:{
											root:'rootList',
											totalProperty:'totalCount'
										}
				    				}
								}),
								beforeLabelTextTpl : required
							},{

	    						fieldLabel:'品质',
	    						xtype : 'wms_DefFieldValCombo',
	    						id : 'quality4902',
	    						store:Ext.create("cms.store.bdef.bdef_DefOwnerComboStore",
	    								{
	    									proxy:{
	    										type:'ajax',
	    										method:'post',
	    										url:'idata_CheckAction_getQualityCombo.action',
	    										reader:{
	    											root:'rootList',
	    											totalProperty:'totalCount'
	    										}
	    									},
	    									listeners:{  
	    										'load':function(th,records,successful,eOpts ){
	    											 if(th.count()>0){
	    												Ext.getCmp('quality4902').setValue(records[0].data.value);		

		    											}
	    											}
	    									}
	    									
	    						}),
	    						beforeLabelTextTpl : required
	    					
							},
							{
								xtype : 'textfield',
								fieldLabel : $i18n.check_qty,//验收数量
								id:'checkQty4902',
								beforeLabelTextTpl : required
							},{
								xtype: 'button',
				            	text:$i18n.save,
				            	margin : '3 3 3 180',
				            	width:67,
				            	id:'save4902'
							}]
						}]
		    	    }]
		        }]   
	    	   },{
	    		   title:'补印中心',
			    	layout:'border',
			    	itemId:'tabPId4902i',
			    	id:'tabPId4902i' 
	    	   }]
        }]
});