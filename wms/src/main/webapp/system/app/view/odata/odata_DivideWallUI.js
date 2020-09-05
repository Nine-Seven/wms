/*
 * 分播墙拣货
 * 3915
 * lich
 */
Ext.define('cms.view.odata.odata_DivideWallUI',{
	alias:'widget.odata_DivideWallUI',
	title:'分播墙拣货',
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	         'cms.view.common.wms_DefFieldValCombo',
	         'cms.view.common.commMenu10',
	         'cms.view.odata.window.odataDivideCellPanel',
	         'cms.view.common.remoteCombo',
	         'cms.view.common.bdef_DefCustCombo',
	         'cms.view.common.bdef_DefArticleCombo',
	         'cms.view.common.cdef_DefCellCombo'
	          ],
	items:[{
		xtype:'commMenuWidget10',
	    id:'menu3915',
	   	region:'north'
	},{
		 xtype:'tabpanel',
		 id:'tabPId3915',
		 region:'center',
		 items:[{
				xtype:'panel',
				region:'north',
				title:'分播墙',
				layout:'border',
				height:200,
				items:[{
					xtype:'panel',
					region:'north',
					layout:'border',
					height:200,
					items:[{
						xtype:'panel',
						region:'west',
						width:'50%',
						defaults:{
							margin:'5 10 0 5 ',
							labelAlign:'right'
						},
						layout: 
						{
							type: 'table',
							columns: 2
						},			
						items:[/*{
							xtype:'textfield',
							fieldLabel : '工作站',			
							id:'workspaceStation3915',
							//colspan:2,
							enableKeyEvents:true,
							beforeLabelTextTpl : required
						},*/{
							xtype:'textfield',
							fieldLabel : '分播人员',			
							id : 'workerNo3915',
							colspan:2,
							enableKeyEvents:true,
							beforeLabelTextTpl : required
						},{
							xtype:'textfield',
							fieldLabel : '分播墙号',			
							id:'deviceNo3915',
							colspan:2,
							enableKeyEvents:true,
							beforeLabelTextTpl : required
						},{
							xtype:'textfield',
							fieldLabel : '任务号/标签号',//	任务号/标签号	
							id : 'divideNo3915',
							//colspan:2,
							enableKeyEvents:true,
							beforeLabelTextTpl : required
						},{
							xtype: 'button',
			            	text : '转病单',
			            	margin:'5 10 0 45 ',
			            	name:'releaseNo'
						},{
							xtype:'textfield',
							fieldLabel : $i18n.barcode,//商品条码
							id : 'barcode3915',
							//colspan:2,
							enableKeyEvents:true,
							beforeLabelTextTpl : required
						},{
							xtype: 'button',
			            	text : '未分播明细',
			            	margin:'5 10 0 45 ',
			            	name:'divideDetail'
						},/*{
							xtype:'numberfield',
							fieldLabel : '扫描基准量',
							id : 'scanQty3915',
							minValue:1,
							colspan:2,hidden:true,
							beforeLabelTextTpl : required
						},*//*{
							xtype:'checkbox',
							fieldLabel : '扫描确定',
							id : 'scanSure3915',
							checked   : true,
							beforeLabelTextTpl : required
						},*//*{
							xtype:'textfield',
							fieldLabel : '储位验证',hidden:true,
							id : 'cellCheck3915',
							colspan:2,
							enableKeyEvents:true,
							beforeLabelTextTpl : required
						},*/{

							xtype:'container',
							margin:'10 0 0 312',
							colspan:2,
							//width:510,
							items:[
							{
								xtype:'label',
								readOnly:true,
								cls:'classDiv1',
								text:'未播量：'
							},
							{
								xtype:'label',
								readOnly:true,
								id:'unQty3916',//width:310,
								margin:'0 0 0 10',
								cls:'classDiv1',
							}]
						
						},{
							xtype:'panel',
							//colspan:2,
							border:false,
							layout:'hbox',
							margin:'0 10 0 20 ',
							items:[{
									xtype:'panel',
									html:'分播完成',
									width:60,
									height:25,
									bodyStyle: {
									    background: '#DC143C'   //DC143C 红色
									}
								},{
									xtype:'panel',
									html:'部分分播',
									width:60,
									height:25,
									bodyStyle: {
									    background: '#0000ff'	//0000ff  蓝色
									}
								},{
									xtype:'panel',
									html:'正分播',
									width:60,
									height:25,
									bodyStyle: {
									    background: '#FFA500'	//FFA500  橙色
									}
								},{
									xtype:'panel',
									html:'待分播',
									width:60,
									height:25,
									bodyStyle: {
									    background: '#FFFF00'
									}
								}
							]
						},{
							xtype: 'checkboxfield',
				        	//fieldLabel: '',
							boxLabel: "播放声音",
				        	id:'sound3915',
				        	margin:'5 10 0 45 ',
				        	checked: true	
						}]
					},{

						xtype:'panel',
						region:'center',		
						items:[{
							xtype: 'label',
				        	id: 'lblArtNo3915',
				     		style: {
			            		fontSize: '22px'
			        		},
				        	hidden: true
						},{
							xtype: 'label',
				        	id: 'lblArtName3915',
				     		style: {
			            		fontSize: '22px'
			        		},
				        	margin: '0 0 0 10'
						}]		
					}]
				},
				{
					xtype:'panel',
					region:'center',
					id:'panMain3915',
					//autoScroll:true,
					defaults:{
						margin:'2 2 2 2'
					},layout: 
						{
							type: 'table',
							columns: 8
						},		
					items:[/*{
							xtype: 'odataDivideCellPanel'
						}*/]
				},{
					xtype : 'panel',
					region:'south'
				}]
			},{
			    	title:'补印中心',
			    	layout:'border',
			    	itemId:'tabPId3915i',
			    	id:'tabPId3915i',
		    }]
	}]	        
});











































