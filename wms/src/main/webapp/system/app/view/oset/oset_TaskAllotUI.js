/**
 * 模块名称：切单规则配置
 * 模块编码：3929
 * 创建：chensr
 */
var oset_TaskAllotMStore=Ext.create('cms.store.oset.oset_TaskAllotMStore',{autoLoad:true});
var oset_defareaWithoutAllotStore=Ext.create('cms.store.oset.oset_DefareaWhitoutAllotStore',{autoLoad:true});
var oset_defareaWithAllotStore=Ext.create('cms.store.oset.oset_DefareaWhitoutAllotStore',{autoLoad:false});
var oset_TaskAllotDStore=Ext.create('cms.store.oset.oset_TaskAllotDStore',{autoLoad:false});

Ext.define('cms.view.oset.oset_TaskAllotUI',{
	alias:'widget.oset_TaskAllotUI',
	title:'切单规则配置',
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	id:'oset_TaskAllotUI',
	requires:[
				 'cms.view.common.commMenu2',
				 'cms.view.common.commMenu5',
				 'cms.view.common.bdef_DefCustCombo',
				 'cms.view.common.bdef_DefOwnerCombo',
				 'cms.view.common.bdef_DefArticleCombo',
				 'cms.view.common.bdef_PackingQtyCombo',
				 'cms.view.common.wms_DefFieldValCombo'
		],
		items:[
		{
			xtype:'tabpanel',
			id:'tab1PId3929',
		    region:'center',
		    items:[
		    {
		    	title:'切单规则策略',
		    	layout:'border',
		    	items:[
		    	{
		    		title:'切单规则配置头挡',
		    		xtype:'commMenuWidget2',
		    		id:'menu3929',
		    	    region:'north'
		    	},{
		        	xtype : 'grid',
		    		id : 'oset_TaskAllotMUI3929',
		    		store:oset_TaskAllotMStore,
		    		region:'center',
		    		columns : [ {			
		    			xtype : 'rownumberer',
		    			width : 30
		    	    },{
		    			width : 100,
		    			text : '仓别',                //仓别
		    			dataIndex : 'warehouseNo'
		    		},{
		    			width : 100,
		    			text : '任务分配ID',      //任务分配ID
		    			dataIndex : 'taskId'
		    		},{
		    			width : 100,
		    			text : '任务分配名称',      //任务分配名称
		    			dataIndex : 'taskName'
		    		},{
		    			width : 100,
		    			text : '默认配置',        //默认配置
		    			dataIndex : 'defaultFlagText'
		    		},{
		    			width : 100,
		    			text : '备注',        //备注
		    			dataIndex : 'memo'
		    		}],
		    		dockedItems : [{
		    			xtype : 'pagingtoolbar',
		    			store : oset_TaskAllotMStore,
		    			dock : 'bottom',
		    			displayInfo : true
		    		}] 
		        },{
		        	height : 300,
		        	region:'south',
		        	layout:'border',
		        	items:[
		    			{
		    			    xtype:'commMenuWidget2',
		    			    id:'menu_3929_2',
		    			    region:'north',
		    			    items : [ 
		    			              {
		    				    	   text : $i18n.additem,
		    				    	   name : 'detailAddD',
		    				    	   iconCls : 'add'//新增
		    				       },{ text : $i18n.titleupdate,
		    				    	   name : 'detailEditD',
		    				    	   iconCls : 'edit' //修改
		    				       },{ text : $i18n.titlebrowse,
		    				    	   name : 'detailBrowseD',
		    				    	   iconCls : 'browse' //浏览
		    				       }]
		    			},
		    			{
		    				
		    				xtype : 'grid',
		    				region:'center',
		    				id : 'oset_TaskAllotDUI3929',
		    				store:oset_TaskAllotDStore,
		    				columns : [{			
		    					xtype : 'rownumberer',
		    					width : 30
		    			    },{
				    			width : 100,
				    			text : '仓别',                //仓别
				    			dataIndex : 'warehouseNo'
				    		},{
				    			width : 100,
				    			text : '任务分配ID',      //任务分配ID
				    			dataIndex : 'taskId'
				    		},{
				    			width : 100,
				    			text : '下架类型',      //下架类型
				    			dataIndex : 'outstockTypeText'
				    		},{
				    			width : 100,
				    			text : '作业类型1',      //作业类型
				    			dataIndex : 'sourceTypeText'
				    		},{
				    			width : 100,
				    			text : '作业类型2',      //作业类型
				    			dataIndex : 'operateTypeText'
				    		},{
				    			width : 100,
				    			text : '切单范围',      //切单范围
				    			dataIndex : 'allotRuleText'				    			
				    		},{
				    			width : 100,
				    			text : '切单规则',      //切单规则
				    			dataIndex : 'boxFalgText'				    			
				    		},{
				    			width : 100,
				    			text : '对应的值',      //对应的值
				    			dataIndex : 'paraValue'				    			
				    		},{
				    			width : 100,
				    			text : '拣货打单方式',      //拣货打单方式
				    			dataIndex : 'taskTypeText'				    			
				    		},{
				    			width : 100,
				    			text : '备注',        //备注
				    			dataIndex : 'memo'
				    		}],
		    				dockedItems : [{
				    			xtype : 'pagingtoolbar',
				    			store : oset_TaskAllotDStore,
				    			dock : 'bottom',
				    			displayInfo : true
				    		}] 
		    			}
		            ]
		        }]
		    },		    
	/////////////////////////第二个table页//////////////////////////////////
		    {
		    	title:'储区切单规则策略',
		    	layout:'border',
		    	itemId:'tab2PId3929',
		    	items:[{
		    	    xtype:'grid',
		    	    region:'north',
		    	    height:240,
		    	    id:'oset_TaskAllotMUI3929_1',
		    	    store:oset_TaskAllotMStore,
		    	    columns:[{			
		    			xtype : 'rownumberer',
		    			width : 30
		    	    },{
						width : 100,
						text : '仓别',                //仓别
						dataIndex : 'warehouseNo'
					},{
						width : 100,
						text : '任务分配ID',      //任务分配ID
						dataIndex : 'taskId'
					},{
						width : 100,
						text : '任务分配名称',      //任务分配名称
						dataIndex : 'taskName'
					},{
						width : 100,
						text : '默认配置',        //默认配置
						dataIndex : 'taskName'
					},{
						width : 100,
						text : '备注',        //备注
						dataIndex : 'memo'
					}],
		    	    dockedItems : [{
		    			xtype : 'pagingtoolbar',
		    			dock : 'bottom',
		    			store:oset_TaskAllotMStore,
		    			displayInfo : true
		    	    }]
		    	},{
		    			xtype:'grid',
		    			id:'defareaWithoutAllot',
		    			title:'未分配储区',//"未分配储区",
		    			width:'47%',
		    			region:'west',
		    			store:oset_defareaWithoutAllotStore,
		    			multiSelect: true,  
		    		    selModel: {  
		    	        	selType:'checkboxmodel'  
		    		    },
		    			columns:[{			
		    				xtype : 'rownumberer',
		    				width : 30
		    			},{
		    				width:160,
		    				text:'库区编号',  //'储区'
		    				dataIndex:'wareNo',
		    				sortable: false
		    			},{
		    				width:160,
		    				text:'储区编号',  //'储区'
		    				dataIndex:'areaNo',
		    				sortable: false
		    			},{
		    				width:160,
		    				text:'储区名称',  //'储区'
		    				dataIndex:'areaName',
		    				sortable: false
		    			}],
		    			dockedItems : [{
		    				xtype : 'pagingtoolbar',
		    				dock : 'bottom',
		    				store:oset_defareaWithoutAllotStore,
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
		    				margin:'10 0 0 0'
		    			},
		    			items : [
		    			{
		    				xtype:'button',
		    				margin:'80 0 0 0',
		    				text:'<<<',
		    				id:'left3929'
		    			},{
		    				xtype:'button',
		    				text:'>>>',
		    				id:'right3929'
		    			}]
		    		},{

		    		    xtype:'grid',
		    		    id:'defareaWhitAllot',
		    		    title:'已分配储区',//已分配储区
		    		    width:'47%',
		    		    region:'east',
		    		    store:oset_defareaWithAllotStore,
		    		    multiSelect: true,  
		    		    selModel: {  
		    		        selType:'checkboxmodel'  
		    		    },
		    		    columns:[{			
		    				xtype : 'rownumberer',
		    				width : 30
		    		    },{
		    				width:160,
		    				text:'库区编号',  //'储区'
		    				dataIndex:'wareNo',
		    				sortable: false
		    			},{
		    				width:160,
		    				text:'储区编号',  //'储区'
		    				dataIndex:'areaNo',
		    				sortable: false
		    			},{
		    				width:160,
		    				text:'储区名称',  //'储区'
		    				dataIndex:'areaName',
		    				sortable: false
		    			}],
		    		    dockedItems : [{
		    				xtype : 'pagingtoolbar',
		    				dock : 'bottom',
		    				store:oset_defareaWithAllotStore,
		    				displayInfo : true
		    		    }]	    			
		    		}]
		    }]
		}]
	});