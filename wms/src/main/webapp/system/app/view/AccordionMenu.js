Ext.define('cms.view.AccordionMenu', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.mmrAccordionMenu',
	title: '系统菜单',    
	width: 200,     
	//height: 300,
	layout:'accordion',    
	region : 'west',
	closeAction:'destroy',
    defaults: {
           // bodyStyle: 'padding:15px'
	},
	layoutConfig: {       
		titleCollapse: false,        
		//animate: true,        
		activeOnTop: false     
	},    
	items: [{        
		xtype:'treepanel',
		title: '权限管理',
		id:'authTreePanel',
	    width: 200,	    
	    store: Ext.create('Ext.data.TreeStore', {
   			root: {
        	expanded: true,
			children:[{
			        text:'权限管理',
			        id:'1101',
			        //url:'authAction_init.action',
			        url:'cms.view.bdef.bdef_DefWorkerUI',
			        qtitle:'cms.controller.bdef.bdef_DefWorkerController',
			        leaf:true
			    	},{
			    	text:'用户与仓别与货主关系维护',
			        id:'1201',
			        //url:'authAction_init.action',
			        url:'cms.view.bset.bset_Worker_LocUI',
			        qtitle:'cms.controller.bset.bset_Worker_LocController',
			        leaf:true
			    	}]
    		}
		}),
	    rootVisible: false
	},{        
		xtype:'treepanel',
		title: $i18n.title200000,
		id:'baseinfoTreePanel',
	    width: 200,
	    store: Ext.create('Ext.data.TreeStore', {
   			root: {
        	expanded: true,
			children:[{
		    		text:'商品资料维护',//商品资料维护
		    		id:'1401',
		    		url:'cms.view.bdef.bdef_DefarticleUI',
		    		qtitle:'cms.controller.bdef.bdef_DefarticleController',
		    		leaf:true			
			 	},
			 	{
		    		text:'商品类别维护',//商品类别维护
		    		id:'1301',
		    		url:'cms.view.bdef.bdef_ArticleGroupUI',
		    		qtitle:'cms.controller.bdef.bdef_ArticleGroupController',
		    		leaf:true			
			 	},{
			 		text:'货主资料维护',//客户资料维护
				 	id:'1D01',
				 	url:'cms.view.bdef.bdef_DefOwnerUI',
				 	qtitle:'cms.controller.bdef.bdef_DefOwnerController',
				 	leaf:true
			 	},
			 	{
			 		text:'客户资料维护',//客户资料维护
				 	id:'1501',
				 	url:'cms.view.bdef.bdef_DefCustUI',
				 	qtitle:'cms.controller.bdef.bdef_DefCustController',
				 	leaf:true
			 	},{
			    	text:'供应商维护',
			        id:'1601',
			        url:'cms.view.bdef.bdef_DefSupplierUI',
			        qtitle:'cms.controller.bdef.bdef_DefSupplierController',
			        leaf:true
			 	},{
			 		text:'打印机维护',
			 		id:'1F01',
			 		url:'cms.view.bdef.bdef_DefPrinterUI',
			 		qtitle:'cms.controller.bdef.bdef_DefPrinterController',
			 		leaf:true
			 	},{
			 		text:'打印机组与打印机关系维护',
			 		id:'1G01',
			 		url:'cms.view.bset.bset_GroupUI',
			 		qtitle:'cms.controller.bset.bset_GroupController',
			 		leaf:true
			 	},{
			 		text:'打印机组与工作站关系维护',
			 		id:'1H01',
			 		url:'cms.view.bdef.bdef_DefWorkstationUI',
			 		qtitle:'cms.controller.bdef.bdef_DefWorkstationController',
			 		leaf:true
			 	},{
			 		text:'码头维护',
			 		id:'1I01',
			 		url:'cms.view.bdef.bdef_DefDockUI',
			 		qtitle:'cms.controller.bdef.bdef_DefDockController',
			 		leaf:true
			 	},{
			 		text:'打印机组与码头关系维护',
			 		id:'200009',
			 		url:'cms.view.bset.bset_DockPrintGroupUI',
			 		qtitle:'cms.controller.bset.bset_DockPrintGroupController',
			 		leaf:true
			 	}
				]
    		}
		}),
	    rootVisible: false
	},{        
		xtype:'treepanel',
		title: '储位管理维护',
		id:'cdefTreePanel',
	    width: 200,
	    store: Ext.create('Ext.data.TreeStore', {
   			root: {
        	expanded: true,
			children:[{
		    	text:'仓库维护',
		        id:'2101',
		        //url:'cdef_DefWareAction_init.action',
		        url:'cms.view.cdef.cdef_DefWareUI',
		        qtitle:'cms.controller.cdef.cdef_DefWareController',
		        leaf:true			
			 	},{
			 		text:'商品储位对应关系',
			 		id:'2201',
			 		url:'cms.view.cset.cset_CellArticleUI',
			 		qtitle:'cms.controller.cset.cset_CellArticleController',
			 		leaf:true
			 	},{
			 		text:'保拣关系设置',
			 		id:'2301',
			 		url:'cms.view.cset.cset_AreaBackupUI',
			 		qtitle:'cms.controller.cset.cset_AreaBackupController',
			 		leaf:true
			 	}
				]
    		}
		}),
	    rootVisible: false
	},{        
		xtype:'treepanel',
		title: '出货管理',
		id:'oDataTreePanel',
	    width: 200,
	    store: Ext.create('Ext.data.TreeStore', {
   			root: {
        	expanded: true,
			children:[
			{
				text:'出货手建单',
				id:'3101',
				url:'cms.view.odata.odata_ExpUI',
				qtitle:'cms.controller.odata.odata_ExpController',
				leaf:true
			},
			{
		    	text:'出货调度',
		        id:'3201',
		        url:'cms.view.odata.odata_LocateUI',
		        qtitle:'cms.controller.odata.odata_LocateController',
		        leaf:true			
			 },
			 {
			    text:'拣货批量发单',
			    id:'3301',
			    url:'cms.view.odata.odata_OutstockMAutoSendUI',
			    qtitle:'cms.controller.odata.odata_OutstockMAutoSendController',
			    leaf:true			
			},
			{
			    text:'拣货按客户发单',
			    id:'3401',
			    url:'cms.view.odata.odata_OutstockMManSendUI',
			    qtitle:'cms.controller.odata.odata_OutstockMManSendController',
			    leaf:true			
			},
		    {
			    text:'拣货任务标签回单',
			    id:'3501',
			    url:'cms.view.odata.odata_OutstockLabelReceiptUI',
			    qtitle:'cms.controller.odata.odata_OutstockLabelReceiptController',
			    leaf:true			
			},
			{
			    text:'拣货表单回单',
			    id:'3601',
			    url:'cms.view.odata.odata_OutstockFormReceiptUI',
			    qtitle:'cms.controller.odata.odata_OutstockFormReceiptController',
			    leaf:true			
			},
			{
		    	text:'分播回单',
		        id:'3701',
		        url:'cms.view.odata.odata_DivideUI',
		        qtitle:'cms.controller.odata.odata_DivideController',
		        leaf:true			
			},
			{
			    text:'出货装车作业',
			    id:'3801',
			    url:'cms.view.odata.odata_CarPlanUI',
			    qtitle:'cms.controller.odata.odata_CarPlanController',
			    leaf:true			
			}
			/*{
				text:'出货整理打包',
				id:'3701',
				url:'cms.view.odata.odata_ArrangePackUI',
				qtitle:'cms.controller.odata.odata_ArrangePackController',
				leaf:true
			}*/]
    	}
	    
			 	/*},
			 	{
			    	text:'拣货发单',
			        id:'900002',
			        //url:'cdef_DefWareAction_init.action',
			        url:'cms.view.odata.odata_OutStock_MUI',
			        qtitle:'cms.controller.odata.odata_OutStock_MController',
			        leaf:true			
				},
			 	{
			    	text:'拣货回单',
			        id:'900003x',
			        //url:'cdef_DefWareAction_init.action',
			        url:'cms.view.odata.odata_OutStock_MReceiptUI',
			        qtitle:'cms.controller.odata.odata_OutStock_MReceiptController',
			        leaf:true			
				},
			 	{
			    	text:'出货装车作业',
			        id:'900001xx',
			        //url:'cdef_DefWareAction_init.action',
			        url:'cms.view.odata.odata_CarPlan_MUI',
			        qtitle:'cms.controller.cdef.cdef_DefWareController',
			        leaf:true			
				}
				]
    		}*/
		}),
	    rootVisible: false
	},{
		xtype:'treepanel',
		title:$i18n.title500000,//盘点管理
		id:'fcdataTreePanel',
		width:200,
		store:Ext.create('Ext.data.TreeStore',{
			root:{
			expanded:true,
			children:[
			          {
			        	  text:'盘点手建单', //手建盘点计划单
			        	  id:'8101',
			        	  url:'cms.view.fcdata.fcdata_PlanUI',
			        	  qtitle:'cms.controller.fcdata.fcdata_PlanController',
			        	  leaf:true
			          },
			          {
			        	  text:'初盘发单',//初盘发单
			        	  id:'8201',
			        	  url:'cms.view.fcdata.fcdata_CheckUI',
			        	  qtitle:'cms.controller.fcdata.fcdata_CheckController',
			        	  leaf:true
			          },
			          {
			        	  text:'复盘/三盘发单',//盘点发单
			        	  id:'8301',
			        	  url:'cms.view.fcdata.fcdata_ReCheckUI',
			        	  qtitle:'cms.controller.fcdata.fcdata_ReCheckController',
			        	  leaf:true
			          },
			          {
			        	  text:'初盘回单',//初盘回单
			        	  id:'8401',
			        	  url:'cms.view.fcdata.fcdata_FirstRequestUI',
			        	  qtitle:'cms.controller.fcdata.fcdata_FirstRequestController',
			        	  leaf:true
			          },
			          {
			        	  text:'复盘/三盘回单',//盘点回单
			        	  id:'8501',
			        	  url:'cms.view.fcdata.fcdata_SecondThirdRequestUI',
			        	  qtitle:'cms.controller.fcdata.fcdata_SecondThirdRequestController',
			        	  leaf:true
			          }
			          ]
			}
		}),
	    rootVisible: false
	},{
		xtype:'treepanel',
		title:'进货管理',
		id:'idatatreepanel',
		width:200,
		store:Ext.create('Ext.data.TreeStore',{
			root:{
			expanded:true,
			children:[
			          {
			        	  text:'进货手建单',
			        	  id:'4101',
			        	  url:'cms.view.idata.idata_ImPortUI',
			        	  qtitle:'cms.controller.idata.idata_ImPortController',
			        	  leaf:true
			          },
			          {
			        	  text:'预约进货',
			        	  id:'4201',
			        	  url:'cms.view.idata.idata_Order_TimeUI',
			        	  qtitle:'cms.controller.idata.idata_Order_TimeController',
			        	  leaf:true
			          },
			          {
			        	  text:'流水板自动拆板',
			        	  id:'4301',
			        	  url:'cms.view.idata.idata_FlowAutoCloseUI',
			        	  qtitle:'cms.controller.idata.idata_FlowAutoCloseController',
			        	  leaf:true
			          },
			          {
			        	  text:'固定板人工封板',
			        	  id:'4401',
			        	  url:'cms.view.idata.idata_FixedArtificialCloseUI',
			        	  qtitle:'cms.controller.idata.idata_FixedArtificialCloseController',
			        	  leaf:true
			          },
			          {
			        	  text:'流水板人工封板',
			        	  id:'4501',
			        	  url:'cms.view.idata.idata_Check_UI',
			        	  qtitle:'cms.controller.idata.idata_CheckController',
			        	  leaf:true
			          },
			          {
			        	  text:'验收确认',
			        	  id:'4601',
			        	  url:'cms.view.idata.idata_CheckConfirmUI',
			        	  qtitle:'cms.controller.idata.idata_CheckConfirmController',
			        	  leaf:true
			          },
			          {
			        	  text:'上架回单',
			        	  id:'4701',
			        	  url:'cms.view.idata.idata_InstockUI',
			        	  qtitle:'cms.controller.idata.idata_InstockController',
			        	  leaf:true
			          }
			          ]
			}
		}),
	    rootVisible: false
	},{
		xtype:'treepanel',
		title:$i18n.title1200000,//返配管理
		id:'ridataTreePanel',
		width:200,
		store:Ext.create('Ext.data.TreeStore',{
			root:{
			expanded:true,
			children:[
			          {
			        	  text:'返配手建单',//返配手建单
			        	  id:'6101',
			        	  url:'cms.view.ridata.ridata_UntreadUI',
			        	  qtitle:'cms.controller.ridata.ridata_UntreadController',
			        	  leaf:true
			          },
			          {
			        	  text:'返配表单验收',//返配表单验收
			        	  id:'6201',
			        	  url:'cms.view.ridata.ridata_CheckUI',
			        	  qtitle:'cms.controller.ridata.ridata_CheckController',
			        	  leaf:true
			          },
			          {
			        	  text:'返配上架回单',//返配上架回单
			        	  id:'6301',
			        	  url:'cms.view.ridata.ridata_InstockUI',
			        	  qtitle:'cms.controller.ridata.ridata_InstockController',
			        	  leaf:true
			          }
			          ]
			}
		}),
	    rootVisible: false
	},{
		xtype:'treepanel',
		title:$i18n.title1300000,//退货管理
		width:200,
		store:Ext.create('Ext.data.TreeStore',{
			root:{
			expanded:true,
			children:[
			          {
			        	  text:"退厂手建单",//手建退厂单
			        	  id:'7101',
			        	  url:'cms.view.rodata.rodata_RecedeMUI',
			        	  qtitle:'cms.controller.rodata.rodata_RecedeMController',
			        	  leaf:true
			          },
			          {
			        	  text:'退厂发单',//退厂发单
			        	  id:'7201',
			        	  url:'cms.view.rodata.rodata_OutstockDirectUI',
			        	  qtitle:'cms.controller.rodata.rodata_OutstockDirectController',
			        	  leaf:true
			          },
			          {
			        	  text:'退厂回单',//退厂回单
			        	  id:'7301',
			        	  url:'cms.view.rodata.rodata_OutstockMReceiptUI',
			        	  qtitle:'cms.controller.rodata.rodata_OutstockMController',
			        	  leaf:true
			          }
			          ]
			}
		}),
	    rootVisible: false
	},{
		xtype:'treepanel',
		title:'出货基础资料设置',//出货基础资料设置
		id:'rodataTreePanel',
		width:200,
		store:Ext.create('Ext.data.TreeStore',{
			root:{
			expanded:true,
			children:[
			          {
			        	  text:'客户与线路维护',//客户与线路维护
			        	  id:'1400001',
			        	  url:'cms.view.oset.oset_DefLineUI',
			        	  qtitle:'cms.controller.oset.oset_DefLineController',
			        	  leaf:true
			          }
			          ]
		}
		}),
		rootVisible:false
	},{
		xtype:'treepanel',
		title:'移库管理',
		id:'mdatatreepanel1500001',
		width:200,
		store:Ext.create('Ext.data.TreeStore',{
			root:{
			expanded:true,
			children:[
			          {
			        	  text:'移库手键单',
			        	  id:'5101',
			        	  url:'cms.view.mdata.mdata_PlanMUI',
			        	  qtitle:'cms.controller.mdata.mdata_PlanMController',
			        	  leaf:true
			          },
			          {
			        	  text:'移库发单',
			        	  id:'5201',
			        	  url:'cms.view.mdata.odata_OutstockDirectUI',
			        	  qtitle:'cms.controller.mdata.odata_OutstockDirectController',
			        	  leaf:true
			          },
			          {
			        	  text:'移库回单',
			        	  id:'5301',
			        	  url:'cms.view.mdata.odata_OutstockDUI',
			        	  qtitle:'cms.controller.mdata.odata_OutstockDController',
			        	  leaf:true
			          }
			          ]
			}
		}),
	    rootVisible: false
	},{
		xtype:'treepanel',
		title:'打印中心',
		id:'printtreepanel',
		width:200,
		store:Ext.create('Ext.data.TreeStore',{
			root:{
			expanded:true,
			children:[
			          {
			        	  text:'补印中心',
			        	  id:'1700001',
			        	  url:'cms.view.print.print_repairUI',
			        	  qtitle:'cms.controller.print.print_RepairController',
			        	  leaf:true
			          }
			          ]
			}
		}),
	    rootVisible: false
	},{
		xtype:'treepanel',
		title:'高阶查询',
		//id:'',
		width:200,
		store:Ext.create('Ext.data.TreeStore',{
			root:{
			expanded:true,
			children:[
			          {
			        	  text:'基础资料查询',
			        	  id:'1600001',
			        	  url:'cms.view.report.report_Bdef_UI',
			        	  qtitle:'cms.controller.report.report_Bdef_Controller',
			        	  leaf:true
			          },
			          {
			        	  text:'商品库存查询',
			        	 // id:'',
			        	  url:'cms.view.report.articleStockReportUI',
			        	  qtitle:'cms.controller.report.articleStockReportController',
			        	  leaf:true
			          },
			          {
			        	  text:'分播回单查询',
			        	 // id:'',
			        	  url:'cms.view.mdata.mdata_Plan_MUI',
			        	  qtitle:'cms.controller.mdata.mdata_Plan_MController',
			        	  leaf:true
			          },
			          {
			        	  text:'商品基础资料查询',
			        	  //id:'',
			        	  url:'cms.view.report.bdef_ArticleReportUI',
			        	  qtitle:'cms.controller.report.bdef_ArticleReportController',
			        	  leaf:true
			          },
			          {
			        	  text:'储位状态查询',
			        	  //id:'',
			        	  url:'cms.view.mdata.mdata_Plan_MUI',
			        	  qtitle:'cms.controller.mdata.mdata_Plan_MController',
			        	  leaf:true
			          },
			          {
			        	  text:'验收资料查询',
			        	  id:'1600006',
			        	  url:'cms.view.report.CheckSourceReportUI',
			        	  qtitle:'cms.controller.report.idata_Check_DSourceReportController',
			        	  leaf:true
			          },
			          {
			        	  text:'上架信息查询',
			        	  //id:'',
			        	  url:'cms.view.mdata.mdata_Plan_MUI',
			        	  qtitle:'cms.controller.mdata.mdata_Plan_MController',
			        	  leaf:true
			          },
			          {
			        	  text:'出货单据状态跟踪查询',
			        	 // id:'',
			        	  url:'cms.view.mdata.mdata_Plan_MUI',
			        	  qtitle:'cms.controller.mdata.mdata_Plan_MController',
			        	  leaf:true
			          },
			          {
			        	  text:'仓储报表',
			        	  id:'1600010',
			        	   url:'cms.view.report.report_Bdef_UI',
			        	  qtitle:'cms.controller.report.report_Bdef_Controller',
			        	  leaf:true
			          },
			          {
			        	  text:'进货报表',
			        	  id:'1600012',
			        	   url:'cms.view.report.report_Import_UI',
			        	  qtitle:'cms.controller.report.report_Import_Controller',
			        	  leaf:true
			          },
			          {
			        	  text:'出货报表',
			        	  id:'1600013',
			        	   url:'cms.view.report.report_Out_UI',
			        	  qtitle:'cms.controller.report.report_Out_Controller',
			        	  leaf:true
			          },
			          {
			        	  text:'返配报表',
			        	  id:'1600014',
			        	   url:'cms.view.report.report_ridata_UI',
			        	  qtitle:'cms.controller.report.report_Ridata_Controller',
			        	  leaf:true
			          },
			          {
			        	  text:'退厂报表',
			        	  id:'1600015',
			        	   url:'cms.view.report.report_rodata_UI',
			        	  qtitle:'cms.controller.report.report_Rodata_Controller',
			        	  leaf:true
			          }
			          ]
			}
		}),
	    rootVisible: false
	}
	]
});