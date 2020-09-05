var auto_set=Ext.create('cms.store.wms.wms_DefcustommenuStore',{
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('customIdG102').getValue()!=''&&Ext.getCmp('customIdG102').getValue()!=null){
				var customIdG102=Ext.getCmp('customIdG102').getValue();
				for(var i=0 ; i<th.count();i++){
					var record  = Ext.getCmp('grid_01_G102').getStore().getAt(i);
					if(customIdG102==record.get('customIdG102')){
						Ext.getCmp('grid_01_G102').getSelectionModel().select(i);
						return;
					}				
				}		
			}
		}
	}
});



Ext.define('cms.view.report.aout_defineUI', {
	alias : 'widget.aout_defineUI',
	title:'自定义模块',
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
		id:'tabPIdG102',
	    region:'center',
	    items:[
	    {
	    	title:'基本属性',
	    	layout:'border',
	    	items:[
		    	{
					xtype:'commMenuWidget4',
					region:'north',
					id:'menuG102'
				},{
	            	xtype : 'form',
	                region:'center',
	                id:'form_01_G102',
	                height : '28%',
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
				        	id:'modubleIdG102',
				        	beforeLabelTextTpl : required,
				        	store:Ext.create("cms.store.common.comboStore",{
		 			    	    proxy:{
		 							type:'ajax',
		 							method:'post',
		 							url:'auto_SetAction_getModubleMenu',
		 							reader:{
		 								root:'rootList',
		 								totalProperty:'totalCount'
		 							}
		 						},
								listeners:{  
									'load':function(th,records,successful,eOpts ){
										if(th.count()>0){
											Ext.getCmp('modubleIdG102').setValue(th.getAt(0).data.value);
										}
									}
								}
		 				    }).load(),
		 				    displayField : 'dropValue',
						    valueField : 'value',
						 	allowBlank:false
					    },
					    {
					        fieldLabel : '自定义编码',//报表编码
					        id : 'customIdG102',
					        readOnly:true
					    },
					    {
					        fieldLabel : '自定义名称',//报表名称
					        id : 'customNameG102'
					    }]
	                }]
	      		},{
		    		region:'south',
		    	    xtype:'grid',
		    	    id:'grid_01_G102',
		    		height : '76%',
		    	    store:auto_set,
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
		    	       	text:'自定义编码',//报表编码
		    	       	dataIndex:'customId'
		    	    },{
		    	    	width:200,
		    	       	text:'自定义名称', //报表名称
		    	       	dataIndex:'customName'
		    	    
		    	    },{
		    	    	width : 150,
						text : '是否可见',//是否可见
						dataIndex : 'showText',
						cls : 'notnull',
						field:{
							xtype : 'wms_DefFieldValCombo',
							id : 'showG102',
							store:Ext.create("cms.store.common.comboStore").load(
							{
								 	params:{str:"N,YESORNO"}
							}),
							editable:true,
							displayField : 'dropValue',
						    valueField : 'value',
							allowBlank :false
						}
		    	    },{
		    	    	width:200,
		    	       	text:'顺序', 
		    	       	hidden:true,
		    	       	dataIndex:'seq'
		    	    
		    	    }],
					dockedItems : [{
						xtype : 'commMenuWidget8',
//						hidden: true,
						region: 'north'

					}]      			
				}]
	    },    
	    {
	    	title:'参数设置',
	    	layout:'border',
	    	itemId:'tabPIdG102_1',
	    	items:[{
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
					margin : '10 2 4 2',
					labelAlign : 'right'
		  		},
	    	    items :[
    	    	{
    	    		fieldLabel : '参数名1',//字段ID
			        id : 'fieldId0'
		  		},{
		  			fieldLabel : '数值类型',//数值类型
			        id : 'fieldType0',
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
		  			fieldLabel : '表名',//宽度
			        id : 'tableName0'
		  		},{
		  			fieldLabel : '字段名',//字段名字
			        id : 'fieldName0'
		  		},{
    	    		fieldLabel : '参数名2',//字段ID
			        id : 'fieldId1'
		  		},{
		  			fieldLabel : '数值类型',//数值类型
			        id : 'fieldType1',
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
		  			fieldLabel : '表名',//宽度
			        id : 'tableName1'
		  		},{
		  			fieldLabel : '字段名',//字段名字
			        id : 'fieldName1'
		  		},{
    	    		fieldLabel : '参数名3',//字段ID
			        id : 'fieldId2'
		  		},{
		  			fieldLabel : '数值类型',//数值类型
			        id : 'fieldType2',
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
		  			fieldLabel : '表名',//宽度
			        id : 'tableName2'
		  		},{
		  			fieldLabel : '字段名',//字段名字
			        id : 'fieldName2'
		  		},{
    	    		fieldLabel : '参数名4',//字段ID
			        id : 'fieldId3'
		  		},{
		  			fieldLabel : '数值类型',//数值类型
			        id : 'fieldType3',
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
		  			fieldLabel : '表名',//宽度
			        id : 'tableName3'
		  		},{
		  			fieldLabel : '字段名',//字段名字
			        id : 'fieldName3'
		  		},{
    	    		fieldLabel : '参数名5',//字段ID
			        id : 'fieldId4'
		  		},{
		  			fieldLabel : '数值类型',//数值类型
			        id : 'fieldType4',
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
		  			fieldLabel : '表名',//宽度
			        id : 'tableName4'
		  		},{
		  			fieldLabel : '字段名',//字段名字
			        id : 'fieldName4'
		  		},{
    	    		fieldLabel : '参数名6',//字段ID
			        id : 'fieldId5'
		  		},{
		  			fieldLabel : '数值类型',//数值类型
			        id : 'fieldType5',
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
		  			fieldLabel : '表名',//宽度
			        id : 'tableName5'
		  		},{
		  			fieldLabel : '字段名',//字段名字
			        id : 'fieldName5'
		  		},{
    	    		fieldLabel : '参数名7',//字段ID
			        id : 'fieldId6'
		  		},{
		  			fieldLabel : '数值类型',//数值类型
			        id : 'fieldType6',
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
		  			fieldLabel : '表名',//宽度
			        id : 'tableName6'
		  		},{
		  			fieldLabel : '字段名',//字段名字
			        id : 'fieldName6'
		  		},{
    	    		fieldLabel : '参数名8',//字段ID
			        id : 'fieldId7'
		  		},{
		  			fieldLabel : '数值类型',//数值类型
			        id : 'fieldType7',
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
		  			fieldLabel : '表名',//宽度
			        id : 'tableName7'
		  		},{
		  			fieldLabel : '字段名字',//字段名字
			        id : 'fieldName7'
		  		},{
    	    		fieldLabel : '参数名9',//字段ID
			        id : 'fieldId8'
		  		},{
		  			fieldLabel : '数值类型',//数值类型
			        id : 'fieldType8',
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
		  			fieldLabel : '表名',//宽度
			        id : 'tableName8'
		  		},{
		  			fieldLabel : '字段名',//字段名字
			        id : 'fieldName8'
		  		},{
    	    		fieldLabel : '参数名10',//字段ID
			        id : 'fieldId9'
		  		},{
		  			fieldLabel : '数值类型',//数值类型
			        id : 'fieldType9',
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
		  			fieldLabel : '表名',//宽度
			        id : 'tableName9'
		  		},{
		  			fieldLabel : '字段名',//字段名字
			        id : 'fieldName9'
		  		},{
		  			xtype: 'button',
		            text: '保存',
		            width:120,
		            margin : '3 3 3 110',
		            id:'btnG102'
		  		}]				
			}
	    	]
	    }]
	}
	]
});