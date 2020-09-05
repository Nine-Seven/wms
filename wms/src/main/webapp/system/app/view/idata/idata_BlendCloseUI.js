/**
 * 模块名称：单品验收作业
 * 模块编码：4501
 * 创建：hcx
 */

var idata_ImPort_sd = Ext.create('cms.store.idata.idata_ImPort_DStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'idata_CheckAction_getImportSDList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	},listeners:{  
   	 'load':function(th,records,successful,eOpts ){   
   		 if(th.count()>0){
   			 var flag=_myAppGlobal.getController('cms.controller.idata.idata_BlendCloseController').getFlag();
   			 if(flag=='1'){   				
   				 var articleNo=_myAppGlobal.getController('cms.controller.idata.idata_BlendCloseController').getArticleNoFlag();	
   				 var packingQty=_myAppGlobal.getController('cms.controller.idata.idata_BlendCloseController').getPackingQtyFlag();
   				 var produceDate=_myAppGlobal.getController('cms.controller.idata.idata_BlendCloseController').getProduceDateFlag();	
   				 var expireDate=_myAppGlobal.getController('cms.controller.idata.idata_BlendCloseController').getExpireDateFlag();	
   				 var temperature=_myAppGlobal.getController('cms.controller.idata.idata_BlendCloseController').getTtemperatureFlag();	
   				 for(var i=0; i<th.count();i++){
   					 var data = Ext.getCmp('grid4501_1').getStore().getAt(i);
   					 if(data.get('articleNo')== articleNo && data.get('packingQty')== packingQty){
   						 Ext.getCmp('grid4501_1').getSelectionModel().select(i);
   						 Ext.getCmp('dateProduceDate4501').setValue(produceDate);
   						 Ext.getCmp('dateExpireDate4501').setValue(expireDate);
   						 Ext.getCmp('temperature4501').setValue(temperature);
   						 return;
   					 }		
   				 }
   				
   				 _myAppGlobal.getController('cms.controller.idata.idata_BlendCloseController').setFlag('0');
   			 }else {
   				 if(!Ext.isEmpty(Ext.getCmp("identifierOrBarcode4501").getValue())){
   					for(var i=0; i<th.count();i++){
      					 var data = Ext.getCmp('grid4501_1').getStore().getAt(i);
      					 if(data.get('barcode')== Ext.getCmp("identifierOrBarcode4501").getValue()){
      						 Ext.getCmp('grid4501_1').getSelectionModel().select(i);
      					 }	
   					}
   				 }else{
   					Ext.getCmp('grid4501_1').getSelectionModel().select(0);
   				 }
   			 }
   		 }
   	 }
    }
});
var Idata_Import_SM = Ext.create('cms.store.idata.idata_ImPort_MStore',{
	proxy:{
		type:'ajax',
		async:false,
		method:'post',
		url:'idata_CheckAction_getPoNoAndSImportNoList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	},listeners:{  
	   	 'load':function(th,records,successful,eOpts ){   
	   		 if(th.count()>0){
	   			if(!Ext.isEmpty(Ext.getCmp("poNo4501").getValue())){
   					for(var i=0; i<th.count();i++){
      					 var data = Ext.getCmp('grid4501_2').getStore().getAt(i);
      					 if(data.get('poNo')== Ext.getCmp("poNo4501").getValue()){
      						 Ext.getCmp('grid4501_2').getSelectionModel().select(i);
      					 }	
   					}
   				 }
				Ext.getCmp('grid4501_2').getSelectionModel().select(0);

	   		 }
	   	 }
	 }
});
Ext.define('cms.view.idata.idata_BlendCloseUI',{
	alias:'widget.idata_BlendCloseUI',
	title:'单品验收作业',//单品验收作业
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
				id:'refresh4501'
			    }]
	       }, {
	    	   xtype : 'tabpanel',
	    	   region : 'center',
	    	   id:'tabPid4501',
	    	   items : [{
	    	    	title:$i18n.titleD,
	    	        layout:'border',
	    	        items:[
	    	        {
	    	        	xtype : 'form',
	    		    	id:'form_01_4501',
	    				layout:'column',
	    				region : 'north',
	    				frame : true,
	    				width : '100%',
	    				height:'8%',
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
	    						fieldLabel:$i18n.scanStation,//扫描台
	    						id : 'cmbDockNo4501',					
	    						store:Ext.create("cms.store.ridata.ridata_DockComboStore",
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
	    					
	    				        beforeLabelTextTpl : required
	    					},{
	    						xtype : 'bdef_DefWorkerCombo',
	    						fieldLabel : $i18n.dp_worker,//  验收人
	    						id : 'cmbWorkerNo4501',
	    						store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore'),
	    						beforeLabelTextTpl : required
	    					},
	             			{
	        	        		id:'radiogroup4501',
	        	        		xtype:'radiogroup',
	        	        		width:280,
	        	        		
	        	                columns:3,
	        	                border:1,
	        	                vertical: true,
	        	        		items:[{
	        	        			boxLabel: '不打印', name:  'rb',  inputValue: '0'
	        	        		},{
	        	        			boxLabel: '打印表单', name: 'rb',  inputValue: '1',checked:true
	        	        		},{
	        	        			boxLabel: '打印标签', name: 'rb',  inputValue: '2'
	        	        		}]     	
	        				
	               	        }]
	    				}]
	    		    },{

	    		    	xtype : 'form',
	    		    	id:'form_02_4501',
	    				layout:'column',
	    				frame : true,
	    				region : 'north',
	    				width : '100%',
	    				height:'15%',
	    				items:[{
	    				    layout:{
	    						type : 'table',
	    						columns : 3
	    					},
	    					xtype:'container',
	    					defaults:{
	    						margin : '10 2 2 0',
	    						labelAlign : 'right',
	    						xtype:'textfield'
	    					},
	    					items:[
	    				    {
	    				    	xtype : 'combo',
	    						fieldLabel : $i18n.owner_no,// 委托业主
	    						id:'owner4501',
	    						displayField: 'dropValue',
	    						valueField: 'value',
	    						store:Ext.create("cms.store.bdef.bdef_DefOwnerComboStore",{
	    							proxy:{
	    								type:'ajax',
	    								method:'post',
	    								url:'idata_CheckAction_queryOwnerCombo.action',
	    								reader:{
	    									root:'rootList',
	    									totalProperty:'totalCount'
	    								}
	    		    				}
	    					   	}).load()
	    					},{
	             				fieldLabel : $i18n.suppliers,   //供应商
	             				id:'suppliers4501',
	         					xtype:'wms_DefFieldValCombo',
	         					 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
	    	     			    	    proxy:{
	    	     							type:'ajax',
	    	     							async:false,
	    	     							method:'post',
	    	     							url:'idata_CheckAction_getSupplierNo',
	    	     							reader:{
	    	     								root:'rootList',
	    	     								totalProperty:'totalCount'
	    	     							}
	    	     						}
	    	     				    }).load(),
	    	     				    
	    	     				    displayField : 'dropValue',
	    	     				    valueField : 'value'
	             			},{
	             				fieldLabel : $i18n.outstocktype,   //单据类型
	             				id:'importType4501',
	         					xtype:'wms_DefFieldValCombo',
	         					 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
	    	     			    	    proxy:{
	    	     							type:'ajax',
	    	     							async:false,
	    	     							method:'post',
	    	     							url:'idata_CheckAction_getImportType',
	    	     							reader:{
	    	     								root:'rootList',
	    	     								totalProperty:'totalCount'
	    	     							}
	    	     						}
	    	     				    }),
	    	     				    
	    	     				    displayField : 'dropValue',
	    	     				    valueField : 'value'
	             			},{
	    						xtype : 'remoteCombo',
	    						fieldLabel : $i18n.po_no1,// 采购单号
	    						id:'poNo4501',
	    						store : Ext.create("cms.store.idata.idata_PoNoStore",{
	    							proxy:{
	    								type:'ajax',
	    								method:'post',
	    								url:'idata_CheckAction_getPoNoList.action',
	    								reader:{
	    									root:'rootList',
	    									totalProperty:'totalCount'
	    								}
	    		    				}
	    						})
	    					},{
	    						xtype : 'remoteCombo',
	    						fieldLabel : $i18n.identifierOrBarcode,// 助记码/条码
	    						id:'identifierOrBarcode4501',
	    						store : Ext.create("cms.store.idata.idata_PoNoStore",{
	    							proxy:{
	    								type:'ajax',
	    								method:'post',
	    								url:'idata_CheckAction_getIdentifierOrBarcode1List.action',
	    								reader:{
	    									root:'rootList',
	    									totalProperty:'totalCount'
	    								}
	    		    				}
	    						})
	    					},{

	    						fieldLabel:$i18n.order_serial,//预约流水号
	    						id : 'orderSerial4501'
	             			}]
	    				}]
	    		    
	    		    },{
	    				xtype : 'grid',
	    				title:$i18n.product_information,//汇总商品信息
	    				id:'grid4501_1',
	    				store:idata_ImPort_sd,
	    				region:'west',
	    				width:'55%',
	    				height:'70%',
	    				columns : [ {
	    					xtype : 'rownumberer',
	    					width : 30
	    				},{
	    					width : 100,
	    					text : $i18n.article_no,//商品编码
	    					dataIndex : 'articleNo'
	    				},{
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
	    				    text:$i18n.check_qty,//验收数量
	    				    dataIndex:'checkQty'
	        		    },{
	    				    width:70,
	    				    text:$i18n.noCheckQty,//未验数量
	    				    dataIndex:'noCheckQty'
	        		    },{
	    					width : 85,
	    					text : $i18n.no_check_box,//箱数
	    					dataIndex : 'planBox',
	    					id:'planBox4501'
	    				},{
	    					width : 85,
	    					text :  $i18n.no_check_qmin,//中包装数
	    					dataIndex : 'planQmin',
	    					id:'planQmin4501'
	    				},{
	    					width : 85,
	    					text :  $i18n.no_check_dis,//零散数
	    					dataIndex : 'planDis',
	    					id:'planDis4501'
	    				},{
	    				    width:60,
	    				    text:$i18n.packing_qty2,//商品包装
	    				    dataIndex:'packingQty'
	        		    },
	        		    {
	        				width : 60,
	        				text : $i18n.packingUnit,//包装单位
	        				id:'packingUnit4501',
	        				dataIndex:'packingUnit'
	        			},
	        		    {
	        				width : 100,
	        				text : $i18n.packingSpec,//规格
	        				id:'packingSpec4501',
	        				dataIndex:'packingSpec'
	        			}],
	    			dockedItems : [{
	        	        xtype : 'pagingtoolbar',
	        	        store:idata_ImPort_sd,
	        	        dock : 'bottom',
	        	        displayInfo : true
	        	    }]
	            },{
	        	 	xtype:'panel',
	        	    layout:'border',
	        	    region:'east',
	        	    width : '45%',
	        	    height : '69%',
	        	    items:[{
	        	    	xtype : 'grid',
	        			title:$i18n.titleM,//单据列表
	        			id:'grid4501_2',
	        			region:'north',
	    				height : '55%',
	        			store:Idata_Import_SM,
	        			columns : [ 
	        			 {
	        				xtype : 'rownumberer',
	        				width : 30
	        			},{
	    					width:80,
	    					text:$i18n.outstocktype,//单据类型
	    					dataIndex:'potypeText'
	    				},{
	        				width : 150,
	        				text : $i18n.s_import_no,//汇总进货单号
	        				dataIndex : 'SImportNo'
	        			},{
	        				width : 150,
	        				text : $i18n.po_no1,//采购单号
	        				dataIndex : 'poNo'
	        			}],dockedItems : [{
	            	        xtype : 'pagingtoolbar',
	            	        store:Idata_Import_SM,
	            	        dock : 'bottom',
	            	        displayInfo : true
	            	    }]
	        	    },{
	        	    	xtype : 'form',
	    				layout:'column',
	    				id:'form_03_4501',
	    				region : 'south',
	    				frame : true,
	    				height : '45%',
	    				items:[
	    				       {
	    					layout:{
	    					type : 'table',
	    					columns : 2
	    					},
	    					xtype:'container',
	    					margin:'8 0 0 0',
	    					defaults:{
	    						labelWidth : 80,
	    						labelAlign : 'right',
	    						xtype:'textfield'
	    					},
	    					items:[
	    					{
	    						xtype : 'remoteCombo',
	    						fieldLabel : $i18n.identifierOrBarcode,// 助记码/条码
	    						id:'articleIdentifier4501',
	    						store : Ext.create("cms.store.idata.idata_PoNoStore",{
	    							proxy:{
	    								type:'ajax',
	    								method:'post',
	    								url:'idata_CheckAction_getIdentifierOrBarcode1List.action',
	    								reader:{
	    									root:'rootList',
	    									totalProperty:'totalCount'
	    								}
	    		    				}
	    						}),
	    						beforeLabelTextTpl : required
	    					},{
	    						fieldLabel:$i18n.pal_amount,//堆叠
	    						id : 'qpalette4501',	                
	    						beforeLabelTextTpl : required,
	    						readOnly:true
	    					},{
	    						fieldLabel:$i18n.produce_date,//生产日期
	    					    xtype: 'datefield',
	    					    format : 'Ymd',
	    						id : 'dateProduceDate4501',	                
	    						beforeLabelTextTpl : required
	    					},{
	    						fieldLabel:$i18n.expire_date,//有效日期
	    						xtype: 'datefield',
	    					    format : 'Ymd',
	    						id : 'dateExpireDate4501',
	    						
	    						beforeLabelTextTpl : required
	    					},{
	    						fieldLabel:$i18n.lot_no,//批号
	    						id : 'txtLotNo4501',
	    						beforeLabelTextTpl : required
	    					},{
	    						xtype : 'numberfield',
	    						fieldLabel : "温度 ℃",// 温度
	    						id : 'temperature4501',	 
	    						maxLength:20
	    				    },
	    				    {
	    						fieldLabel:$i18n.boxQty,
	    						xtype : 'numberfield',
	    						id : 'numplanBox4501',
	    						minValue:0,
	    						beforeLabelTextTpl : required
	    					},
	    					{
	    						fieldLabel:$i18n.qminQty,
	    						xtype : 'numberfield',
	    						id : 'numplanQmin4501',
	    						minValue:0,
	    						beforeLabelTextTpl : required
	    					},
	    					{
	    						fieldLabel:$i18n.disQty,
	    						xtype : 'numberfield',
	    						id : 'numplanDis4501',
	    						minValue:0,
	    						beforeLabelTextTpl : required
	    					},
	    					{
	    						fieldLabel:'品质',
	    						xtype : 'wms_DefFieldValCombo',
	    						id : 'quality4501',
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
	    												Ext.getCmp('quality4501').setValue(records[0].data.value);		

		    											}
	    											}
	    									}
	    									
	    						}),
	    						beforeLabelTextTpl : required
	    					},
	    					{
	    						xtype: 'button',
	    		            	text:$i18n.save,
	    		            	margin : '0 0 0 20',
	    		            	width:67,
	    		            	id:'save4501'
	    					}]
	    				}]
	        	    	
	    	        }]
	    	   }]
	    	   
	       },{
	    	   
	    	   title:'补印中心',
		    	layout:'border',
		    	itemId:'tabPId4501i',
		    	id:'tabPId4501i'
	    	   
	    	   }]   	   
	       }]
        
});