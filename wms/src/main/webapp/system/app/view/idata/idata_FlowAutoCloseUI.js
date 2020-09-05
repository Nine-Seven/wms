/**(验收作业)
 * 模块名称：流水板自动封板
 * 模块编码：4301
 * 创建：Jun
 */
Ext.define('cms.view.idata.idata_FlowAutoCloseUI',{
	alias:'widget.idata_FlowAutoCloseUI',
	title:$i18n.title4301_1,//流水板自动封板
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu4',
	          'cms.view.common.wms_DefFieldValCombo',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.remoteCombo',
	          'cms.view.common.bdef_DefSupplierCombo',
	          'cms.view.common.bdef_DefWorkerCombo',
	          'cms.view.common.bdef_DefDockCombo',
	          'cms.view.common.odata_lotNoCombo'
	          ],
    items:[
    {
        xtype:'commMenuWidget4',
	    id:'menu4301',
	    region:'north'
	},
	{
		xtype : 'tabpanel',
	    region:'center',
	    id:'tabPid4301',
	    flex : 4,
	    items : [
	    {
	    	title:$i18n.titleD,
	        layout:'border',
	        items:[
	        {
	        	xtype : 'form',
                region:'north',
                id:'form_01_4301',
                frame : true,
                layout: 
                {
                	type: 'table',
                	columns: 4
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
	       	      	xtype:'bdef_DefOwnerCombo',
	    			fieldLabel : $i18n.owner_no,//货主编号
	    			id:'cmbOwnerNo4301',
	    			displayField : 'dropValue',
	    		    valueField : 'value',
	    	        allowBlank : false,
	    	        editable:false,
	    			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
	    				proxy:{
							type:'ajax',
							method:'post',
							url:'idata_CheckAction_queryOwnerCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
	    				}
					}).load(),
	    	        readOnly:true,
	    	        beforeLabelTextTpl : required
       	        },
       	        {	
	       	      	xtype:'wms_DefFieldValCombo',
			        fieldLabel : $i18n.outstocktype,//单据类型
			        id : 'cmbImportType4301',
			        editable:false,
			        store:Ext.create("cms.store.common.comboStore").load(
			        {
			        	params:{str:"N,IMPORT_TYPE"}
			        }),
			        allowBlank : false,
			        readOnly:true,
			        beforeLabelTextTpl : required
       	        },
       	        {	
	       	      	xtype:'wms_DefFieldValCombo',
			        fieldLabel : $i18n.status,//状态
			        id : 'cmbStatus4301',
			        editable:false,
			        store:Ext.create("cms.store.common.comboStore").load(
			        {
			        	params:{str:"N,STATUS"}
			        }),
			        allowBlank : false,
			        readOnly:true,
			        beforeLabelTextTpl : required
       	        },{
	        		id:'radiogroup4301',
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
				
       	        },
       	        {
					xtype : 'remoteCombo',
					fieldLabel : $i18n.s_import_no,//汇总进货单号  
					id : 'cmbSImportNo4301',	
					displayField: 'SImportNo',
    				valueField: 'SImportNo',
    				readOnly:true,
					store:Ext.create("cms.store.idata.idata_ImportSdStore",
					{
						proxy:{
							type:'ajax',
							method:'post',
							url:'idata_CheckAction_queryIdataImportMMCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							},
							extraParams:{
								strFlag : 'SS'
							}
						}
					}),
					listConfig: {
			           loadingText: $i18n_prompt.checking,
			           emptyText: $i18n_prompt.noFindRelationData ,
			           getInnerTpl: function() {
			        	   return '{SImportNo}';
			           }
			        },
			        beforeLabelTextTpl : required
				},
       	        {	
	       	      	xtype:'bdef_DefSupplierCombo',
					fieldLabel : $i18n.supplier_no,//供应商编号
				 	id:'cmbSupplierNo4301',
				 	colspan:3,
				 	width:530,
				 	readOnly:true,
	    	        store:Ext.create('cms.store.bdef.bdef_DefSupplierComboStore'),
		        	displayField : 'dropValue',
		        	valueField : 'value',
		        	beforeLabelTextTpl : required
       	        },
       	        {	
	       	      	xtype : 'bdef_DefWorkerCombo',
					id:'cmbCheckWorker4301',
					fieldLabel : $i18n.check_worker4,// 作业人员
					readOnly:true,
					store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore').load(),
					beforeLabelTextTpl : required
       	        },
       	        {	
	       	      	xtype : 'datefield',
					fieldLabel : $i18n.paper_date, //单据日期
					id : 'dateCheckStartDate4301',							
					format : 'Y-m-d',
					readOnly:true,
					beforeLabelTextTpl : required	
       	        },
       	        {	
	       	      	xtype:'wms_DefFieldValCombo',
			        fieldLabel : $i18n.check_tools,//验收工具
			        id : 'cmbCheckTools4301',
			        colspan:2,
			        editable:false,
			        readOnly:true,
			        store:Ext.create("cms.store.common.comboStore").load(
			        {
			        	params:{str:"N,CHECK_TOOLS"}
			        }),
			        allowBlank : false,
			        beforeLabelTextTpl : required
       	        }
                ]
	        },
	        {
	        	xtype:'grid',
    		    id:'grid_01_4301',
    		    region:'center',
    		    store : Ext.create('cms.store.idata.idata_ImportSdStore',
    		    {
    		    	autoLoad:false,
    		    	listeners:
    		    	{  
    		    		'load':function(th,records,successful,eOpts )
    		    		{
    		    			if(th.count()>0){
    		    				for(var i=0;i<records.length;i++)
    		    				{
    		    					Ext.getCmp('grid_01_4301').getStore().data.items[i].set('quality',0);
    		    				}
    		    				
    		    			}
    		    		}
    		    	}
    		    }),
    		    loadMask : true, // 加载时有加载的图标
    		    selType : 'cellmodel',
				plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
					clicksToEdit : 1,
					onSpecialKey:function(ed,field,e){
						commEnterGridStatEdit(this.grid,false,'',e.getKey());
					}
				})],
    		    columns:[
    		    {			
    		        xtype : 'rownumberer',
    			    width : 30
    		    },
    		    {
    	            xtype:'actioncolumn',
    	            text:$i18n.split,//拆笔
    	            width:35,
    	            items: [{
    	                icon: 'system/extjs/resources/icons/fam/application_split.png',  // Use a URL in the icon config
    	                tooltip: 'split'
    	            }]
    	        },
    		    {
    			    width:90,
    			    text : $i18n.article_no,//商品编码
    			    dataIndex:'articleNo'			
    		    },
    		    {
    			    width:100,
    			    text : $i18n.owner_article_no,//货主商品编码
    			    dataIndex:'ownerArticleNo'				
    		    },
    		    {
				    width:160,
				    text : $i18n.article_name,//商品名称
				    dataIndex:'articleName'			
    		    },
    		    {
				    width:100,
				    text:$i18n.barcode,//商品条码
				    dataIndex:'barcode'
    		    },
    		    {
				    width:60,
				    text:$i18n.packing_qty2,//商品包装
				    dataIndex:'packingQty'
    		    },
    		    {
    				width : 60,
    				text : $i18n.packing_qty1,//包装单位
    				id:'packingUnit4301',
    				dataIndex:'packingUnit'
    			},{
    				width : 60,
    				text : $i18n.packingSpec,
    				id:'packingSpec4301',
    				dataIndex:'packingSpec'
    			},
    		    {
				    width:65,
				    text:$i18n.import_qty1,//进货数量
				    dataIndex:'poQty'
    		    },
    		    {
				    width:65,
				    text:'已验数量',//进货数量
				    dataIndex:'importQty'
    		    },
    		    {
				    width:65,
				    text:$i18n.check_number,//验收数量
				    dataIndex:'checkQty',
				    hidden:true
    		    },{
    		    	width:65,
 				    text:'未验数量',//进货数量
 				    dataIndex:'inQty'  		    	
    		    },
    		    {
				    width:50,
				    text:$i18n.boxQty,//箱数
				    dataIndex:'planBox',
				    id:'planBox4301',
				    cls:'notnull',
				    field: {
		            	xtype: 'numberfield',
		            	minValue:0,
		            	listeners:{  
							'change': function(obj, newValue, oldValue, eOpts) {
								if(newValue!=oldValue){
									var data = Ext.getCmp('grid_01_4301').getSelectionModel()
										.getSelection();
									data[0].set('checkQty', newValue * data[0].get('packingQty')+data[0].get('planQmin')*data[0].get('qminOperatePacking')+data[0].get('planDis'));
								}
							}
		      			}
		            }
    		    },{

				    width:50,
				    text:$i18n.qminQty,//中包数
				    dataIndex:'planQmin',
				    id:'planQmin4301',
				    cls:'notnull',
				    field: {
		            	xtype: 'numberfield',
		            	minValue:0,
		            	listeners:{  
							'change': function(obj, newValue, oldValue, eOpts) {
								if(newValue!=oldValue){
									var data = Ext.getCmp('grid_01_4301').getSelectionModel()
										.getSelection();
									data[0].set('checkQty', newValue * data[0].get('qminOperatePacking')+
											data[0].get('planBox')*data[0].get('packingQty')+data[0].get('planDis'));
								}
							}
		      			}
		            }
    		    
    		    },
    		    {
				    width:50,
				    text:$i18n.disQty,//散数
				    dataIndex:'planDis',
				    id:'planDis4301',
				    cls:'notnull',
				    field: {
		            	xtype: 'numberfield',
		            	minValue:0,
		            	listeners:{  
							'change': function(obj, newValue, oldValue, eOpts) {
								if(newValue!=oldValue){
									var data = Ext.getCmp('grid_01_4301').getSelectionModel().getSelection();
									data[0].set('checkQty',newValue + data[0].get('planQmin')*data[0].get('qminOperatePacking')+
											data[0].get('planBox')*data[0].get('packingQty'));							
									}
							}
		      			}
		            }
    		    },{
				    width:60,
				    text: $i18n.lot_no,//生产批号
//				    hidden:$i18n.lotNoHidden,
				    dataIndex:'lotNo',
				    cls:'notnull',
				    field: {
				    	xtype : 'remoteCombo',
						id : 'lotNo4301',
						store:Ext.create('cms.store.odata.odata_LotNoStore',
								{
							proxy:{
								type:'ajax',
								method:'post',
								url:'idata_CheckAction_getlotNo',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								}
							}
						}),
						forceSelection : false,
				        beforeLabelTextTpl : required,
						allowBlank :false
						
		            }
    		    },
    		    {
				    width:80,
				    text:$i18n.newproduct_date,//生产日期
				    hidden:$i18n.produceDateHidden,
				    cls:'notnull',
				    dataIndex:'produceDate',
					field: {
		                xtype: 'datefield',
		                format : 'Y-m-d'
		            },	                
		            renderer:function(value){   
					    if(value instanceof Date){   				 
					        return Ext.Date.format(value,'Y-m-d');   
					    }else{				        
					        return value;   
					    }  
					}
    		    },
    		    {
				    width:80,
				    text:$i18n.end_date,//到期日 
				    hidden:$i18n.expireDateHidden,
				    cls:'notnull',
				    dataIndex:'expireDate',
					field: {
		                xtype: 'datefield',
		                format : 'Y-m-d'
		            },	                
		            renderer:function(value){   
					    if(value instanceof Date){   				 
					        return Ext.Date.format(value,'Y-m-d');   
					    }else{				        
					        return value;   
					    }  
					}
    		    },
    		    {
    				width:60,
    				text:$i18n.quality,//'品质',
    				hidden:$i18n.qualityHidden,
    				dataIndex:'quality',
    				cls:'notnull',
					field: {
						xtype:'wms_DefFieldValCombo',
				        editable:false,
				    	id:'quality4301',
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
									}
						}),
				        allowBlank : false,
				        beforeLabelTextTpl : required
		            },	                
		            renderer: function(value,metadata,record){  
		            	if(value=='0')
		            	{
		            		return $i18n.goods;
		            	}else if(value=='A')
		            	{
		            		return $i18n.rejects;
		            	}
	         		}
    			},{

    				width:50,
    				text:'温度 ℃',//温度,   			
    				dataIndex:'temperature',
    			
					field: {					
						xtype: 'numberfield'		           
		            }   			
    			},
    			{
    				width:100,
    				text:$i18n.reserved1,//'预留字段1',
    				hidden:$i18n.rsvBatch1Hidden,
    				dataIndex:'rsvBatch1'
    			},
    			{
    				width:100,
    				text:$i18n.reserved2,//'预留字段2',
    				hidden:$i18n.rsvBatch2Hidden,
    				dataIndex:'rsvBatch2'
    			},
    			{
    				width:100,
    				text:$i18n.reserved3,//'预留字段3',
    				hidden:$i18n.rsvBatch3Hidden,
    				dataIndex:'rsvBatch3'
    			},
    			{
    				width:100,
    				text:$i18n.reserved4,//'预留字段4',
    				hidden:$i18n.rsvBatch4Hidden,
    				dataIndex:'rsvBatch4'
    			},
    			{
    				width:100,
    				text:$i18n.reserved5,//'预留字段5',
    				hidden:$i18n.rsvBatch5Hidden,
    				dataIndex:'rsvBatch5'
    			},
    			{
    				width:100,
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
    				width:100,
    				text:$i18n.reserved8,//'预留字段8',
    				hidden:$i18n.rsvBatch8Hidden,
    				dataIndex:'rsvBatch8'
    			}]
	        }]
	    },{
	    	title:'补印中心',
	    	layout:'border',
	    	itemId:'tabPId4301i',
	    	id:'tabPId4301i',
	    }]
	}
	]
});
