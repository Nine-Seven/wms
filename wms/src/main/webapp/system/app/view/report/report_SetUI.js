var report_set=Ext.create('cms.store.wms.wms_DefreportformenuStore',{
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('pgmId').getValue()!=''&&Ext.getCmp('pgmId').getValue()!=null){
				var pgmId=Ext.getCmp('pgmId').getValue();
				for(var i=0 ; i<th.count();i++){
					var record  = Ext.getCmp('grid_01_9113').getStore().getAt(i);
					if(pgmId==record.get('pgmId')){
						Ext.getCmp('grid_01_9113').getSelectionModel().select(i);
						return;
					}				
				}		
			}
		}
	}
});


var queryColumn=Ext.create('cms.store.wms.wms_DefModuleQueryColumnStore',{
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('hideColumnId').getValue()!=''&&Ext.getCmp('hideColumnId').getValue()!=null){
				var hideColumnId=Ext.getCmp('hideColumnId').getValue();
				for(var i=0 ; i<th.count();i++){
					var record  = Ext.getCmp('grid_03_9113').getStore().getAt(i);
					if(hideColumnId==record.get('columnid')){
						Ext.getCmp('grid_03_9113').getSelectionModel().select(i);
						return;
					}				
				}		
			}
		}
	}
});

Ext.define('cms.view.report.report_SetUI', {
	alias : 'widget.report_SetUI',
	title:'高阶设置',
	width : '100%',	
	layout : 'border',
	extend : 'Ext.panel.Panel',
	requires : [
				'cms.view.common.commMenu4',
				'cms.view.common.commMenu3',
				'cms.view.common.commMenu8',
				'cms.view.common.commMenu9',
				'cms.view.common.wms_DefFieldValCombo'
			   ],
	items : [
	{
		xtype:'tabpanel',
		id:'tabPId9113',
	    region:'center',
	    items:[
	    {
	    	title:'基本属性',
	    	layout:'border',
	    	items:[
		    	{
					xtype:'commMenuWidget4',
					region:'north',
					id:'menu9113'
				},{
	            	xtype : 'form',
	                region:'center',
	                id:'form_01_9113',
	                height : '32%',
	                frame : true,
	                items :[
	                {
	                	xtype:'fieldset',
	                	title:'基本资料',
	                    layout: 
	                    {
	                    	type: 'table',
	                    	columns: 3
	                    },
	                    defaults : 
	                    {
	                    	xtype : 'textfield',
	                    	labelWidth : 110,
	                    	margin:'5 4 1 4',
	                    	labelAlign:'right'			
		  	       	    },
		       	        items:[
						{

				        	xtype: 'wms_DefFieldValCombo',
				        	fieldLabel:'所属菜单',//所属菜单
				        	id:'modubleId',
				        	beforeLabelTextTpl : required,
				        	store:Ext.create("cms.store.common.comboStore",{
		 			    	    proxy:{
		 							type:'ajax',
		 							method:'post',
		 							url:'report_setAction_getModubleMenu',
		 							reader:{
		 								root:'rootList',
		 								totalProperty:'totalCount'
		 							}
		 						},
								listeners:{  
									'load':function(th,records,successful,eOpts ){
										if(th.count()>0){
											Ext.getCmp('modubleId').setValue(th.getAt(0).data.value);
										}
									}
								}
		 				    }).load(),
		 				    displayField : 'dropValue',
						    valueField : 'value',
						 	allowBlank:false
					    },
					    {
					        fieldLabel : '报表编码',//报表编码
					        id : 'pgmId',
					        readOnly:true
					    },
					    {
					        fieldLabel : '报表名称',//报表名称
					        id : 'procName'
					    }]
	                },{

	                	xtype:'fieldset',
	                	title:'数据权限',
	                    layout: 
	                    {
	                    	type: 'table',
	                    	columns: 2
	                    },
	                    defaults : 
	                    {
	                    	xtype : 'textfield',
	                    	labelWidth : 110,
	                    	margin:'5 4 1 4',
	                    	labelAlign:'right'			
		  	       	    },
		       	        items:[
						{
							xtype: 'checkboxfield',
				        	fieldLabel: '仓别',//
				        	id:'needWarehouseNo',
				        	checked: true,
				        	readOnly:true
						},
					    {
							xtype: 'checkboxfield',
				        	fieldLabel: '货主',//
				        	id:'needOwner',
				        	checked: true,
				        	readOnly:true
						}]
	                }]
	      		},{
		    		region:'south',
		    	    xtype:'grid',
		    	    id:'grid_01_9113',
		    		height : '68%',
		    	    store:report_set,
		    	    plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
		    	        clicksToEdit : 1,
		    	        onSpecialKey:function(ed,field,e){
							commEnterGridStatEdit(this.grid,true,'cms.controller.report.report_SetController',e.getKey());
						}
		    	    })],
		    	    columns:[
		    	    {
		    	        xtype:'rownumberer',
		    	       	width:30 
		    	    },{
		    	       	width:150,
		    	       	text:'菜单',//菜单
		    	       	dataIndex:'moduleId'
		    	    },{
		    	    	width:150,
		    	       	text:'报表编码',//报表编码
		    	       	dataIndex:'pgmId'
		    	    },{
		    	    	width:200,
		    	       	text:'报表名称', //报表名称
		    	       	dataIndex:'procName'
		    	    
		    	    },{
		    	    	width : 150,
						text : '是否可见',//是否可见
						dataIndex : 'showText',
						cls : 'notnull',
						field:{
							xtype : 'wms_DefFieldValCombo',
							id : 'show9113',
							store:Ext.create("cms.store.common.comboStore").load(
							{
								 	params:{str:"N,YESORNO"}
							}),
							editable:true,
							displayField : 'dropValue',
						    valueField : 'value',
							allowBlank :false
						}
		    	    }],
					dockedItems : [{
						xtype : 'commMenuWidget8',
						region:'north'

					}]      			
				}]
	    },    
	    {
	    	title:'sql语句/显示',
	    	layout:'border',
	    	itemId:'tabPId9113_1',
	    	items:[
	    	{
	    		xtype:'commMenuWidget3',
	    		items:[
	    		{
	    			text : '修改',
	    			iconCls : 'edit',
	    			name : 'editSql'
	    		},{
	    			text : '保存',
	    			iconCls : 'save',
	    			name : 'saveSql'
	    		},{
	    			text : '撤销',
	    			iconCls : 'undo',
	    			name : 'undoSql'
	    		}],
	    		region:'north'
    	},{
				xtype : 'form',
	    	    id : 'form_02_3913',
	    	    height:'40%',
	    	    region:'north',
	    	    frame : true,
	    	    layout:{
    	    		type:'table',
    	    		columns:1
    	    	},
    	    	defaults : {
					xtype:'textfield',
					labelWidth : 90,
					width:1400,
					margin : '2 2 4 2',
					labelAlign : 'right'
		  		},
	    	    items :[
    	    	{
    	    		xtype:'textareafield',
					fieldLabel:'预处理',           //预处理
					id:'beforeTreatment',
					readOnly:true
		  		},{
		  			xtype:'textareafield',
		  			fieldLabel:'数据源',          //数据源
					id:'preparedSql',
					readOnly:true
		  		},{
		  			xtype:'textareafield',
		  			fieldLabel:'处理后',          //处理后
					id:'afterTreatment',
					readOnly:true
		  		}]
			},{
				xtype : 'form',
	    	    id : 'form_02_3913_1',
	    	    height:'18%',
	    	    region:'center',
	    	    layout:{
    	    		type:'table',
    	    		columns:4
    	    	},
    	    	defaults : {
					xtype:'textfield',
					labelWidth : 90,
					margin : '2 2 4 2',
					labelAlign : 'right'
		  		},
	    	    items :[
    	    	{
    	    		fieldLabel : '字段ID',//字段ID
			        id : 'fieldId'
		  		},{
		  			fieldLabel : '字段名字',//字段名字
			        id : 'fieldName'
		  		},{
		  			fieldLabel : '数值类型',//数值类型
			        id : 'fieldType',
		        	xtype: 'wms_DefFieldValCombo',
		        	store:Ext.create("cms.store.common.comboStore",{
 			    	    proxy:{
 							type:'ajax',
 							method:'post',
 							url:'report_setAction_getFieldType',
 							reader:{
 								root:'rootList',
 								totalProperty:'totalCount'
 							}
 						}
 				    }).load(),
 				    displayField : 'dropValue',
				    valueField : 'value'
		  		},{
		  			fieldLabel : '是否合计',//合计类型
			        id : 'statisticsFlag',
			        xtype: 'wms_DefFieldValCombo',
			        store:Ext.create("cms.store.common.comboStore").load(
				 	{
				 					params:{str:"N,YESORNO"}
				 	})
		  		},{
		  			fieldLabel : '宽度',//宽度
			        id : 'width'
		  		},{
		  			 xtype : 'button',
			    	 id:'button3913',
			    	 width:90,
			    	 margin : '10 3 3 100',
			    	 buttonAlign:'center',
			    	 text : '确认' //保存确定
		  		},{
			        id : 'hideFieldId',
			        hidden:true
		  		}]				
			},
			
			{
	    		xtype : 'grid',
	    	    region:'south',
	    	    id:'grid_02_9113',
	    	    height:'42%',
//	    	    loadMask : true, // 加载时有加载的图标
	    	    store : Ext.create('cms.store.wms.wms_DefsearchDStore',{
	    	    	listeners:{  
	    	    		'load':function(th,records,successful,eOpts ){
	    	    			if(Ext.getCmp('hideFieldId').getValue()!=''&&Ext.getCmp('hideFieldId').getValue()!=null){
	    	    				var hideFieldId=Ext.getCmp('hideFieldId').getValue();
	    	    				for(var i=0 ; i<th.count();i++){
	    	    					var record  = Ext.getCmp('grid_02_9113').getStore().getAt(i);
	    	    					if(hideFieldId==record.get('fieldId')){
	    	    						Ext.getCmp('grid_02_9113').getSelectionModel().select(i);
	    	    						return;
	    	    					}				
	    	    				}		
	    	    			}
	    	    		}
	    	    	}
	    	    }),
	    	   
	    	    plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
	    	        clicksToEdit : 1,
	    	        onSpecialKey:function(ed,field,e){
						commEnterGridStatEdit(this.grid,true,'cms.controller.report.report_SetController',e.getKey());
					}
	    	    })],
	    	    columns : [
	    	    {
	    	        xtype : 'rownumberer',
	    	        width : 30
	    	    },{
    	    		width : 110,
					text : '字段ID',           //字段名称
					dataIndex : 'fieldId',
					readOnly:true 
	    	    },{
    	    		width : 110,
					text : '字段名称',           //字段名称
					dataIndex : 'fieldName',
					cls : 'notnull',
					field : {							
	    	        	id : 'fieldName9113',
	    	        	xtype:'textfield',
	    	        	allowBlank :false	
	    	        } 
	    	    },{
    	    		width : 110,
					text : '宽度',           //字段名称
					dataIndex : 'width',
					cls : 'notnull',
					field : {							
	    	        	id : 'width9113',
	    	        	xtype:'textfield',
	    	        	allowBlank :false	
					} 
	    	    },{
    	    		width : 110,
					text : '数值类型',           //数值类型
					dataIndex : 'fieldType',
					cls : 'notnull',
					field : {							
	    	        	id : 'fieldType9113',
	    	        	xtype: 'wms_DefFieldValCombo',
			        	store:Ext.create("cms.store.common.comboStore",{
	 			    	    proxy:{
	 							type:'ajax',
	 							method:'post',
	 							url:'report_setAction_getFieldType',
	 							reader:{
	 								root:'rootList',
	 								totalProperty:'totalCount'
	 							}
	 						}
	 				    }).load(),
	 				    displayField : 'dropValue',
					    valueField : 'value',
	    	        	allowBlank :false	
					}     	    	
	    	    },{
    	    		width : 110,
					text : '是否合计',           //数值类型
					dataIndex : 'statisticsFlagText',
					cls : 'notnull',
					field : {							
						xtype : 'wms_DefFieldValCombo',
						id : 'statisticsFlag9113',
						store:Ext.create("cms.store.common.comboStore").load(
						{
							 	params:{str:"N,YESORNO"}
						}),
						editable:true,
						displayField : 'dropValue',
					    valueField : 'value',
						allowBlank :false
					
					}     	    	
	    	    }],
	    	    dockedItems : [{
	    	        xtype:'commMenuWidget9',
	    	        region:'north'
	    	    }]
	    	}
	    	]
	    },{

	    	title:'查询参数设置',
	    	layout:'border',
	    	itemId:'tabPId9113_2',
	    	items:[{
	    		xtype : 'form',
	    		id : 'form_03_3913',
	    		height:'15%',
	    		region:'north',	    		
	    		layout:{
	    			type:'table',
	    			columns:3
	    		},
	    		defaults : {
	    			xtype:'textfield',
	    			labelWidth : 90,
	    			margin : '2 2 4 2',
	    			labelAlign : 'right'
	    		},
	    		items :[{
	    					fieldLabel : '参数名称',//字段ID
	    					id : 'columnId'
	    				},{
	    					fieldLabel : '参数标题',//字段名字
	    					id : 'columnname'
	    				},{
	    					fieldLabel : '参数类型',//数值类型
	    					id : 'xtype',
	    					xtype: 'wms_DefFieldValCombo',
	    					store:Ext.create("cms.store.common.comboStore",{
	    						proxy:{
	    							type:'ajax',
	    							method:'post',
	    							url:'report_setAction_getXType',
	    							reader:{
	    								root:'rootList',
	    								totalProperty:'totalCount'
	    							}
	    						}
	    					}).load(),
	    					displayField : 'dropValue',
	    					valueField : 'value'
	    				},{
	    					fieldLabel : '表名',//合计类型
	    					id : 'fieldtable',
	    					readOnly:true
       
	    				},{
	    					fieldLabel : '字段名',//宽度
	    					id : 'fieldcolumn',
	    					readOnly:true
	    				},{
	    					xtype : 'button',
	    					id:'button3913_1',
	    					width:90,
	    					margin : '10 3 3 100',
	    					buttonAlign:'center',
	    					text : '确认' //保存
	    				},{
	    					id : 'hideColumnId',
	    					hidden:true
	    				}]				
	    	},{

	    		xtype:'commMenuWidget3',
	    		height : '5%',
	    		items:[
	    		{
	    			text : '删除',
	    			iconCls : 'delete',
	    			name : 'deleteQuery'
	    		},{
	    			text : '上移',
	    			iconCls : 'prev',
	    			name : 'prevQuery'
	    		},{
	    			text : '下移',
	    			iconCls : 'next',
	    			name : 'nextQuery'
	    		}],
	    		region:'center'
    	
	    	},{   		
	    	    xtype:'grid',
	    	    id:'grid_03_9113',
	    		height : '80%',
	    		region:'south',
	    	    store:queryColumn,
	    	    plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
	    	        clicksToEdit : 1,
	    	        onSpecialKey:function(ed,field,e){
						commEnterGridStatEdit(this.grid,true,'cms.controller.report.report_SetController',e.getKey());
					}
	    	    })],
	    	    columns:[
	    		   {
	    			   xtype : 'rownumberer',
	    		       width : 30
	    		    },{
	    	    	    width : 110,
	    				text : '参数名称',           //字段名称
	    				dataIndex : 'columnid',
	    				readOnly:true 
	    		    },{
	    	    	    width : 110,
	    				text : '参数标题',           //字段名称
	    				dataIndex : 'columnname',
	    				cls : 'notnull',
	    				field : {							
	    		    	     id : 'columnname9113',
	    		    	     xtype:'textfield',
	    		    	     allowBlank :false	
	    		    		} 
	    		    	},{
	    	    	    		width : 110,
	    						text : '参数类型',           //字段名称
	    						dataIndex : 'xtype',
	    						cls : 'notnull',
	    						field : {							
	    		    	        	id : 'xtype9113',
	    		    	        	xtype: 'wms_DefFieldValCombo',
	    				        	store:Ext.create("cms.store.common.comboStore",{
	    		 			    	    proxy:{
	    		 							type:'ajax',
	    		 							method:'post',
	    		 							url:'report_setAction_getXType',
	    		 							reader:{
	    		 								root:'rootList',
	    		 								totalProperty:'totalCount'
	    		 							}
	    		 						}
	    		 				    }).load(),
	    		 				    displayField : 'dropValue',
	    						    valueField : 'value',
	    		    	        	allowBlank :false	
	    						} 
	    		    	    },{
	    	    	    		width : 110,
	    						text : '表名',           //数值类型
	    						dataIndex : 'fieldtable',
	    						cls : 'notnull',
	    						field : {							
	    		    	        	id : 'fieldtable9113',
	    		    	        	xtype:'textfield',
	    		    	        	allowBlank :false	
	    						}     	    	
	    		    	    },{
	    	    	    		width : 110,
	    						text : '字段名',           
	    						dataIndex : 'fieldcolumn',
	    						cls : 'notnull',
	    						field : {							
	    							id : 'fieldcolumn9113',
	    							xtype:'textfield',
	    		    	        	allowBlank :false	    						
	    						}     	    	
	    		    	    }]  					
	    		}
	    	]    
	    }]
	}
	]
});