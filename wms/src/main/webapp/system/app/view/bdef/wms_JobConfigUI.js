/**
 * 模块名称： 后台管理控制
 * 模块编码：I701
 * 创建：HKL
 */
var wms_jobConfig = Ext.create('cms.store.wms.wms_jobConfigStore',{autoLoad: true});
Ext.define('cms.view.bdef.wms_JobConfigUI', {
	alias : 'widget.wms_JobConfigUI',
	id:'wms_JobConfigUI',
	title:$i18n.titleI701, //后台管理控制
	width : '100%',	
	layout : 'border',
	extend : 'Ext.panel.Panel',
	requires : [
				'cms.view.common.commMenu',
				'cms.view.common.commMenu3',
	           	'cms.view.common.wms_DefFieldValCombo'
				],
	items : [
	{
		xtype : 'tabpanel',
	    id:'tabPIdI701',
	    region:'center',
	    items : [
	    {
	    	title:'数据列表',
	    	layout:'border',
	    	itemId:'tabPIdI701i',
	    	items:[
	    	{
	    		xtype : 'commMenuWidget3',
	    		items: [{
					text : $i18n.find,//查找
					iconCls : 'query',
					id : 'detailQuery'
				}],
	    		region:'north'
	    	},{
				xtype : 'grid',
				region:'center',
				id : 'grid_01_I701',
				width : '100%',
				height : '100%',
				store : wms_jobConfig,
				columns : [ 
				{
					xtype : 'rownumberer',
					width : 30
				},{
					width : 150,
					text : '过程名字',
					dataIndex : 'procName' 
				},{
					width : 150,
					text : '过程描述',
					dataIndex : 'procNameDesc'
				},{
					width:135,
					text:'执行开始时间限制',
					dataIndex:'startTimeLimit'
				},{
					width : 135,
					text : '执行结束时间限制',
					dataIndex : 'endTimeLimit'
				},{
					width : 135,
					text : '上次执行时间',
					dataIndex : 'lastRunTime'
				},{
					width : 100,
					text : '执行状态',
					dataIndex : 'executeStatusText'
				}],
				dockedItems : [{
					xtype : 'pagingtoolbar',
					store : wms_jobConfig,
					dock : 'bottom',
					displayInfo : true
				}]
		     }]
	    },{
	    	title:'数据明细',
	    	layout:'border',
	    	id:'tabPIdd2_I701',
	    	itemId:'tabPIdd2_I701i',
	    	items:[
	    	{
	    	   	xtype:'commMenuWidget',
	    	   	id:'menuI701',
	    	   	region:'north'
	        },{
	        	xtype : 'form',
			    id : 'form_01_I701',
			    region:'center',
			    frame : true,
			    layout : 'column',
			    layout: 
                {
                	type: 'table',
                	columns: 2
                },
			    defaults : {
					labelWidth : 80,
			        margin : '2 2 2 15',
					labelAlign : 'right',
			     },
			    items :[
                { 
	                xtype:'fieldset',
	                title:'调用过程',
	              
				    items :[
				    {
						xtype : 'textareafield',
						fieldLabel :'调用的过程名字' ,
						id : 'procNameI701',
						width : 300,
						readOnly:true
					}]
		         },{
		        
					xtype : 'textareafield',
					fieldLabel :'调用备注' ,
					id : 'RemarkI701',
					width : 500,
					height:100,
					readOnly:true
			          
	             },{
	                xtype:'fieldset',
	                title:'执行状态',
	                layout: 
	                {
	                	type: 'table',
	                	columns: 2
	                },
	               
	                defaults : {
						margin : '2 2 2 10'
				    },
				    items :[{
				    	xtype : 'radiogroup',
						id : 'radiogroupI701_0',
						items : [
					        {
								boxLabel : '启动',
								name : 'rg',
								inputValue : '0',
							}]
				    },
				    {
				    	xtype:'button',			
						text:'启动',
						width : 200,
						id:'executeStatusI701_0'
					},{
						xtype : 'radiogroup',
						id : 'radiogroupI701_1',
						items : [
					        {
								boxLabel : '禁用',
								name : 'rg',
								inputValue : '1',
							}]
					},{
				    	xtype:'button',			
						text:'禁用',
						width : 200,
						id:'executeStatusI701_1'
					}]
		         
	         
	         },{
	        	  xtype:'fieldset',
	        	  items :[
	  				    {
	  				    	xtype : 'textfield',
	  						fieldLabel :'　&nbsp;上次执行时间' ,
	  						id : 'lastRunTimeI701',
	  						width : 300,
	  						readOnly:true 
	  				    }]
	        		
	         },{
	                xtype:'fieldset',
	                title:'执行限制',
				    items :[
				    {
						xtype : 'textfield',
						fieldLabel :'执行开始时间限制' ,
						id : 'startTimeLimitI701',
						readOnly:true
					},{

						xtype : 'textfield',
						fieldLabel :'执行结束时间限制' ,
						id : 'endTimeLimitI701',
						readOnly:true
					
					}]
		          
	         },{
	                xtype:'fieldset',
	                title:'运行间隔',
	                layout: 
	                {
	                	type: 'table',
	                	columns: 2
	                },
				    defaults : {
						labelWidth : 80,
				        margin : '2 2 2 15',
						labelAlign : 'right',
				     },
				    items :[
				    {
						xtype : 'textfield',
						id : 'runTimeIntervalI701',
						readOnly:true
					},{

						xtype:'textfield',
						readOnly:true,
						id:'runCountTypeI701',
						width : 80
					}]
		          
	         
	         }]
	        }]
	    }]
	}]
});