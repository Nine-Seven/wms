//打印机组里的打印机组store
var bset_GroupStore=Ext.create('cms.store.bset.bset_GroupStore',{autoLoad:true,
	listeners:{
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('grid_02_01_1F01').getStore().count()>0){
				Ext.getCmp('grid_02_01_1F01').getSelectionModel().select(0);
			}
		}
	}
});
//打印机群组里的打印机组store
var bset_GroupStore1=Ext.create('cms.store.bset.bset_GroupStore',{autoLoad:true,
	 proxy:{
			type:'ajax',
			method:'post',
			url:'pntdef_PrinterGrpgatherAction_getBset_GroupList.action',
			reader:{
				root:'rootList',
				totalProperty:'totalCount'
			}
		}
});
var pntset_GrpgatherPrinterGroupStore=Ext.create('cms.store.bdef.pntset_GrpgatherPrinterGroupStore');
var pntdef_PrinterGrpgatherStore=Ext.create('cms.store.bdef.pntdef_PrinterGrpgatherStore',{autoLoad:true,
	listeners:{
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('grid_04_01_1F01').getStore().count()>0){
				Ext.getCmp('grid_04_01_1F01').getSelectionModel().select(0);
			}
		}
	}
});
var workstationStore=Ext.create('cms.store.bdef.bdef_DefWorkstationStore',{autoLoad:true});
var bset_Printer_GroupStore=Ext.create('cms.store.bset.bset_Printer_GroupStore');
var bdef_DefPrinterStore=Ext.create('cms.store.bset.bdef_DefPrinterStore',{autoLoad:true});
var printerStore=Ext.create('cms.store.bdef.bdef_DefPrinterStore',{autoLoad:true});
Ext.define('cms.view.bdef.bdef_DefPrinterUI',{
	alias:'widget.bdef_DefPrinterUI',
	title:$i18n.title1F01,//打印机维护
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu2',
	          'cms.view.common.commMenu5',
	          'cms.view.common.remoteCombo',
	          'cms.view.common.wms_DefFieldValCombo',
	          'cms.view.common.bset_GroupCombo'
	          ],
    items:[
{
    xtype:'commMenuWidget2',
    id:'menu1F01',
    region:'north'
},
           {
        	   xtype : 'tabpanel',
        	   id:'tabPId1F01',
   		       region:'center',
   		       items : [{
   		                title:$i18n.title1F01,//打印机维护
   		 		    	layout:'border',
   		 		    	id:'tabPIdd1_1F01',
   		 		    	itemId:'tabPIdd1_1F01i',
   		 		        items:[{
   		    		        xtype:'form',
   		       		     id:'formPrinter1F01',
   		       		     layout:'column',
   		       		 	frame : true,
   		       		 	region : 'north',
   		       		 	width : '100%',
   		       		 	height:'8%',
   		       		 	items:[{
   		       		 	    layout:{
   		       		 			type : 'table',
   		       		 			columns : 3
   		       		 		},
   		       		 		xtype:'container',
   		       		 		defaults:{
   		       		 			margin : '5 5 5 5',
   		       		 			labelAlign : 'right',
   		       		 			xtype:'textfield'
   		       		 		},
   		       		     items:[
   		       		     {

   		       		 		xtype : 'remoteCombo',
   		       		 		fieldLabel : $i18n.printer_no,// 打印机编码(模糊查询)
   		       		 		id:'printerNo1F01',
   		       		 		store : Ext.create("cms.store.idata.idata_PoNoStore",{
   		       		 			proxy:{
   		       		 				type:'ajax',
   		       		 				method:'post',
   		       		 				url:'bdef_DefPrinterAction_getPrinterInfo.action',
   		       		 				reader:{
   		       		 					root:'rootList',
   		       		 					totalProperty:'totalCount'
   		       		 				}
   		       		 			}
   		       		 		})
   		       		 	
   		       		 		
   		       		     },{
   		       	 			fieldLabel :$i18n.printer_type, //打印机类型
   		       	 			id:'printerTypeCombo1F01',
   		       					xtype:'wms_DefFieldValCombo',
   		       					store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
   		       					 proxy:{
   		       	   					type:'ajax',
   		       	    				method:'post',
   		       	     				url:'bdef_DefPrinterAction_getTypeList',
   		       	     				reader:{
   		       	   						root:'rootList',
   		       	   						totalProperty:'totalCount'
   		       							}
   		       						}
   		       				    }).load()
   		       	        },{
   		       	 			fieldLabel :$i18n.status, //状态
   		       	 			id:'statusCombo1F01',
   		       					xtype:'wms_DefFieldValCombo',
   		       					store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
   		       					 proxy:{
   		       	   					type:'ajax',
   		       	    				method:'post',
   		       	     				url:'bdef_DefPrinterAction_getStatusList',
   		       	     				reader:{
   		       	   						root:'rootList',
   		       	   						totalProperty:'totalCount'
   		       							}
   		       						}
   		       				    }).load()
   		       	        }
   		       		     ]}
   		       		 	]},
   		 		               {
   		 		            	xtype : 'grid',
   								region:'center',
   								id : 'grid_01_1F01',
   								width : '100%',
   								height : '100%',
   								store : printerStore,
   								columns : [
								{
									xtype : 'rownumberer',
									width : 30
								},{
									width : 90,
									text : $i18n.warehouse,//仓别
									dataIndex : 'warehouseNo' 
								},{
									width : 90,
									text : $i18n.printer_no,//打印机代码
									dataIndex : 'printerNo' 
								},{
									width : 250,
									text : $i18n.printer_name,//打印机名称
									dataIndex : 'printerName' 
								},{
									width : 90,
									text : $i18n.printer_type,//打印机类型
									dataIndex : 'printertypeText' 
								},{
									width : 90,
									text : $i18n.status,//状态
									dataIndex : 'statusText' 
								}  
   								   ],
   								dockedItems : [{
   									xtype : 'pagingtoolbar',
   									store : printerStore,
   									dock : 'bottom',
   									displayInfo : true
   								}]
   		 		               }
   		 		        ]
   		                },{
   		                title:$i18n.title1G01,//打印机组与打印机关系维护
   		 		    	layout:'border',
   		 		    	id:'tabPIdd2_1F01',
   		 		    	itemId:'tabPIdd2_1F01i',
   		 		        items:[{
   		 		    xtype:'grid',
   		 	    region:'north',
   		 	    height:240,
   		 	    id:'grid_02_01_1F01',
   		 	    store:bset_GroupStore,
   		 	    columns:[
   		 	    {			
   		 			xtype : 'rownumberer',
   		 			width : 30
   		 	    },{
   		 			width : 120,
   		 			text : $i18n.warehouse,//仓别
   		 			dataIndex:'warehouseNo'
   		 	    },{
   		 			width : 150,
   		 			text : $i18n.printer_group_no,//打印机组代码
   		 			dataIndex:'printerGroupNo'
   		 	    },{
   		 			width : 200,
   		 			text : $i18n.printer_group_name,//打印机组名称
   		 			dataIndex:'printerGroupName'
   		 	    }],
   		 	    dockedItems : [{
   		 			xtype : 'pagingtoolbar',
   		 			dock : 'bottom',
   		 			store:bset_GroupStore,
   		 			displayInfo : true
   		 	    }]
   		 	},{
   				xtype : 'form',
   				region : 'center',
   				layout:{
   					type:'table',
   					columns:1
   				},
   				width:'6%',
   				frame : true,
   				defaults:{
   					margin:'10 0 0 8'
   				},
   				items : [{
   					xtype:'button',
   					margin:'80 0 0 8',
   					text:'>>>',
   					id:'right01_1F01'
   				},
   				{
   					xtype:'button',			
   					text:'<<<',
   					id:'left01_1F01'
   				}]
   			},{
   		        xtype:'form',
	       		     id:'formPrinter1G01',
	       		     layout:'column',
	       		 	frame : true,
	       		 	region : 'north',
	       		 	width : '100%',
	       		 	height:'8%',
	       		 	items:[{
	       		 	    layout:{
	       		 			type : 'table',
	       		 			columns : 4
	       		 		},
	       		 		xtype:'container',
	       		 		defaults:{
	       		 			margin : '5 5 5 0',
	       		 			labelAlign : 'right',
	       		 			xtype:'textfield'
	       		 		},
	       		     items:[
	       		     {

	       		 		xtype : 'remoteCombo',
	       		 		fieldLabel : $i18n.printer_no,// 打印机编码(模糊查询)
	       		 		id:'printerNo1G01',
	       		 		store : Ext.create("cms.store.idata.idata_PoNoStore",{
	       		 			proxy:{
	       		 				type:'ajax',
	       		 				method:'post',
	       		 				url:'bdef_DefPrinterAction_getPrinterInfo.action',
	       		 				reader:{
	       		 					root:'rootList',
	       		 					totalProperty:'totalCount'
	       		 				}
	       		 			}
	       		 		})
	       		 	
	       		 		
	       		     },{
	       	 			fieldLabel :$i18n.printer_type, //打印机类型
	       	 			id:'printerTypeCombo1G01',
	       					xtype:'wms_DefFieldValCombo',
	       					store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
	       					 proxy:{
	       	   					type:'ajax',
	       	    				method:'post',
	       	     				url:'bdef_DefPrinterAction_getTypeList',
	       	     				reader:{
	       	   						root:'rootList',
	       	   						totalProperty:'totalCount'
	       							}
	       						}
	       				    }).load()
	       	        }
	       		     ]}
	       		 	]},{
   				xtype:'grid',
   				id:'grid_02_02_1F01',
   				title:$i18n.printer,//打印机
   				width:'47%',
   				region:'west',
   				store:bdef_DefPrinterStore,
   				multiSelect: true,  
   			    selModel: {  
   		        	selType:'checkboxmodel'  
   			    },
   				columns:[{			
   					xtype : 'rownumberer',
   					width : 30
   				},{
   					width:85,
   					text:$i18n.printer_no,//打印机代码
   					dataIndex:'printerNo',
   					sortable: false
   				},{
   					width:150,
   					text:$i18n.printer_name,//打印机名称
   					dataIndex:'printerName'
   				},{
   					width:100,
   					text:$i18n.printer_type,//打印机类型
   					dataIndex:'printertypeText'
   				}],
   				dockedItems : [{
   					xtype : 'pagingtoolbar',
   					dock : 'bottom',
   					store:bdef_DefPrinterStore,
   					displayInfo : true
   				}]
   			},{
   			    xtype:'grid',
   			    id:'grid_02_03_1F01',
   			    title:$i18n.printer_group_list,//打印机群组列表
   			    width:'47%',
   			    region:'east',
   			    store:bset_Printer_GroupStore,
   			    multiSelect: true,  
   			    selModel: {  
   			        selType:'checkboxmodel'  
   			    },
   			    columns:[{			
   					xtype : 'rownumberer',
   					width : 30
   			    },{
   					width:120,
   					text:$i18n.printer_group_no,//打印机群组代码
   					dataIndex:'printerGroupNo'
   			    },{
   					width:120,
   					text:$i18n.printer_no,//打印机代码
   					dataIndex:'printerNo'
   			    },{
   					width:200,
   					text:$i18n.printer_name,//打印机名称
   					dataIndex:'printerName'
   			    }],
   			    dockedItems : [{
   					xtype : 'pagingtoolbar',
   					dock : 'bottom',
   					store:bset_Printer_GroupStore,
   					displayInfo : true
   			    }]
   			}
   		 		   	]
   		                },{
   		                title:$i18n.title1H01,//工作站与打印机组关系维护
   		 		    	layout:'border',
   		 		    	id:'tabPIdd3_1F01',
   		 		    	itemId:'tabPIdd3_1F01i',
   		 		    items:[
{
    xtype:'form',
    id:'formWorkStation1H01',
    layout:'column',
	frame : true,
	region : 'north',
	width : '100%',
	height:'8%',
	items:[{
	    layout:{
			type : 'table',
			columns : 2
		},
		xtype:'container',
		defaults:{
			margin : '5 5 5 5',
			labelAlign : 'right',
			xtype:'textfield'
		},
    items:[
    {

		xtype : 'remoteCombo',
		fieldLabel : $i18n.workstation_no,// 工作站编码
		id:'workStation1H01',
		store : Ext.create("cms.store.idata.idata_PoNoStore",{
			proxy:{
				type:'ajax',
				method:'post',
				url:'bdef_DefWorkstationAction_getWorkStationInfo.action',
				reader:{
					root:'rootList',
					totalProperty:'totalCount'
				}
			}
		})
	
		
    },{

		xtype : 'remoteCombo',
		fieldLabel : $i18n.printer_group_no,// 打印机组编码
		id:'printerGroupNo1H01',
		store : Ext.create("cms.store.idata.idata_PoNoStore",{
			proxy:{
				type:'ajax',
				method:'post',
				url:'bdef_DefWorkstationAction_getprinterGroupInfo.action',
				reader:{
					root:'rootList',
					totalProperty:'totalCount'
				}
			}
		})
	
		
    }
    ]}
	]},
   		 		   	{
   		 		   		xtype:'grid',		
   		 		   		region:'center',
   		 		   		id:'grid_03_1F01',
   		 		   		store:workstationStore,
   		 		   		columns:[{			
   		 		   			xtype : 'rownumberer',
   		 		   			width : 30
   		 		   		},{
   		 		   			width : 90,
   		 		   			text : $i18n.workstation_no,
   		 		   			dataIndex : 'workstationNo'
   		 		   		},{
   		 		   			width : 120,
   		 		   			text : $i18n.workstation_name,
   		 		   			dataIndex : 'workstationName'
   		 		   		},{
   		 		   			width:120,
   		 		   			text:$i18n.printer_group_no,
   		 		   			dataIndex:'printerGroupNo'
   		 		   		},{
   		 		   			width : 120,
   		 		   			text : $i18n.printer_group_name,
   		 		   			dataIndex : 'printerGroupName'
   		 		   		}],
   		 		   		dockedItems : [{
   		 		   			xtype : 'pagingtoolbar',
   		 		   			store : workstationStore,
   		 		   			dock : 'bottom',
   		 		   			displayInfo : true
   		 		   		}] 
   		 		   	},{
   		 		   		region:'south'
   		 		   	}]
   		                },{
		                title:$i18n.title1T01,//打印机群组与打印机组关系维护
		 		    	layout:'border',
		 		    	id:'tabPIdd4_1F01',
		 		    	itemId:'tabPIdd4_1F01i',
		 		    	items:[
		 		    	      {
		 		    	  	    xtype:'grid',
		 		    	  	    region:'north',
		 		    	  	    height:240,
		 		    	  	    id:'grid_04_01_1F01',
		 		    	  	    store:pntdef_PrinterGrpgatherStore,
		 		    	  	    columns:[
		 		    	  	    {			
		 		    	  			xtype : 'rownumberer',
		 		    	  			width : 30
		 		    	  	    },{
		 		    	  			width : 120,
		 		    	  			text : $i18n.warehouse,//仓别
		 		    	  			dataIndex:'warehouseNo'
		 		    	  	    },{
		 		    	  			width : 150,
		 		    	  			text : $i18n.grpgather_no,//打印机群组代码
		 		    	  			dataIndex:'grpgatherNo'
		 		    	  	    },{
		 		    	  			width : 200,
		 		    	  			text : $i18n.grpgather_name,//打印机群组名称
		 		    	  			dataIndex:'grpgatherName'
		 		    	  	    }],
		 		    	  	    dockedItems : [{
		 		    	  			xtype : 'pagingtoolbar',
		 		    	  			dock : 'bottom',
		 		    	  			store:pntdef_PrinterGrpgatherStore,
		 		    	  			displayInfo : true
		 		    	  	    }]
		 		    	  	},{
		 		   		        xtype:'form',
		 		       		     id:'formPrinter1T01',
		 		       		     layout:'column',
		 		       		 	frame : true,
		 		       		 	region : 'north',
		 		       		 	width : '100%',
		 		       		 	height:'8%',
		 		       		 	items:[{
		 		       		 	    layout:{
		 		       		 			type : 'table',
		 		       		 			columns : 4
		 		       		 		},
		 		       		 		xtype:'container',
		 		       		 		defaults:{
		 		       		 			margin : '5 5 5 0',
		 		       		 			labelAlign : 'right',
		 		       		 			xtype:'textfield'
		 		       		 		},
		 		       		     items:[
		 		       		     {

		 		       		 		xtype : 'remoteCombo',
		 		       		 		fieldLabel : $i18n.printer_group_no,// 打印机组编码(模糊查询)
		 		       		 		id:'printerGroupNo1T01',
		 		       		 		store : Ext.create("cms.store.idata.idata_PoNoStore",{
		 		       		 			proxy:{
		 		       		 				type:'ajax',
		 		       		 				method:'post',
		 		       		 				url:'pntdef_PrinterGrpgatherAction_getPrinterGroupInfo.action',
		 		       		 				reader:{
		 		       		 					root:'rootList',
		 		       		 					totalProperty:'totalCount'
		 		       		 				}
		 		       		 			}
		 		       		 		})
		 		       		 	
		 		       		 		
		 		       		     }
		 		       		     ]}
		 		       		 	]},{
		 		    	  		xtype:'grid',
		 		    	  		id:'grid_04_02_1F01',
		 		    	  		title:$i18n.printer_group,//打印机组
		 		    	  		width:'47%',
		 		    	  		region:'west',
		 		    	  		store:bset_GroupStore1,
		 		    	  		multiSelect: true,  
		 		    	  	    selModel: {  
		 		    	          	selType:'checkboxmodel'  
		 		    	  	    },
		 		    	  		columns:[{			
		 		    	  			xtype : 'rownumberer',
		 		    	  			width : 30
		 		    	  		},{
		 		    	  			width:120,
		 		    	  			text:$i18n.printer_group_no,//打印机组代码
		 		    	  			dataIndex:'printerGroupNo',
		 		    	  			sortable: false
		 		    	  		},{
		 		    	  			width:250,
		 		    	  			text:$i18n.printer_group_name,//打印机组名称
		 		    	  			dataIndex:'printerGroupName'
		 		    	  		}],
		 		    	  		dockedItems : [{
		 		    	  			xtype : 'pagingtoolbar',
		 		    	  			dock : 'bottom',
		 		    	  			store:bset_GroupStore,
		 		    	  			displayInfo : true
		 		    	  		}]
		 		    	  	},{
		 		    	  		xtype : 'form',
		 		    	  		region : 'center',
		 		    	  		layout:{
		 		    	  			type:'table',
		 		    	  			columns:1
		 		    	  		},
		 		    	  		width:'6%',
		 		    	  		frame : true,
		 		    	  		defaults:{
		 		    	  			margin:'10 0 0 8'
		 		    	  		},
		 		    	  		items : [{
		 		    	  			xtype:'button',
		 		    	  			margin:'80 0 0 8',
		 		    	  			text:'>>>',
		 		    	  			id:'right02_1F01'
		 		    	  		},
		 		    	  		{
		 		    	  			xtype:'button',			
		 		    	  			text:'<<<',
		 		    	  			id:'left02_1F01'
		 		    	  		}]
		 		    	  	},{
		 		    	  	    xtype:'grid',
		 		    	  	    id:'grid_04_03_1F01',
		 		    	  	    title:$i18n.grpgather_printer_group,//打印机群组列表
		 		    	  	    width:'47%',
		 		    	  	    region:'east',
		 		    	  	    store:pntset_GrpgatherPrinterGroupStore,
		 		    	  	    multiSelect: true,  
		 		    	  	    selModel: {  
		 		    	  	        selType:'checkboxmodel'  
		 		    	  	    },
		 		    	  	    columns:[{			
		 		    	  			xtype : 'rownumberer',
		 		    	  			width : 30
		 		    	  	    },{
		 		    	  			width:120,
		 		    	  			text:$i18n.grpgather_no,//打印机群组代码
		 		    	  			dataIndex:'grpgatherNo'
		 		    	  	    },{
		 		    	  			width:120,
		 		    	  			text:$i18n.printer_group_no,//打印机组代码
		 		    	  			dataIndex:'printerGroupNo'
		 		    	  	    },{
		 		    	  			width:200,
		 		    	  			text:$i18n.printer_group_name,//打印机组名称
		 		    	  			dataIndex:'printerGroupName'
		 		    	  	    }],
		 		    	  	    dockedItems : [{
		 		    	  			xtype : 'pagingtoolbar',
		 		    	  			dock : 'bottom',
		 		    	  			store:pntset_GrpgatherPrinterGroupStore,
		 		    	  			displayInfo : true
		 		    	  	    }]
		 		    	  	},{
		 		    	      	region:'south'
		 		    	  	}]
		                }
   		       ]
           }
    ]
});