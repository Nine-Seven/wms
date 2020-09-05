/**
 * 模块名称：返配分播回单
 * 模块编码：6801
 * 创建：HCX
 */
Ext.define('cms.view.ridata.ridata_DivideReceiptUI',{
	alias:'widget.ridata_DivideReceiptUI',
	title:$i18n.title6801,//返配分播回单
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
			  ],
			  items:[
						{
							xtype:'tabpanel',
							id:'tabPIdC201',
						    region:'center',
						    items:[{
						    	title:$i18n.title6801tab1,
						    	layout:'border',
						    	items:[
						    	    {
									xtype : 'form',
						    	    id : 'form_01_6801',
						    	    region:'north',
						    	    frame : true,
						    	    layout:{
					    	    		type:'table',
					    	    		columns:2
					    	    	},
					    	    	defaults : {
										xtype:'textfield',
										labelWidth : 150,
										margin : '2 2 4 2',
										labelAlign : 'right'
							  		},
						    	    items :[
					    	    	{
							  			xtype:'textfield',
					    	    		fieldLabel:$i18n.label_no,//标签号
					    	    		id:'labelNo6801',
					    			    beforeLabelTextTpl : required
							  		},{
										xtype:'container',
										items:[
										{
											xtype: 'button',
									    	text: $i18n.butDivide,//选择单号
									    	margin : '0 0 0 0',
									    	id:'butDivide6801'
										}]
									}]
								}   
							    	]
						    },{
						    	title:$i18n.title6801tab2,
						    	layout:'border',
						    	itemId:'tabPIdC202i',
						    	items:[
						    	{
									xtype : 'form',
						    	    id : 'form_02_6801',
						    	    region:'north',
						    	    frame : true,
						    	    layout:{
					    	    		type:'table',
					    	    		columns:2
					    	    	},
					    	    	defaults : {
										xtype:'textfield',
										labelWidth : 150,
										margin : '2 2 4 2',
										labelAlign : 'right'
							  		},
						    	    items :[
					    	    	{
							  			xtype:'textfield',
					    	    		fieldLabel:$i18n.cell_no,//储位
					    	    		id:'cellNo6801',
					    			    beforeLabelTextTpl : required
							  		},{
										xtype:'container',
										items:[
										{
											xtype: 'button',
									    	text: $i18n.closeBox,//选择单号
									    	margin : '0 0 0 0',
									    	id:'butCloseBox6801'
										}]
									}]
								}
						    	]
						    }]
						}]
					});