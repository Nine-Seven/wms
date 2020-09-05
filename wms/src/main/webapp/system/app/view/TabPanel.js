var detailStore= Ext.create('cms.store.common.detailStore');

var cm =null;  //生产网格用的
var dataHome=null;//看板数据
var panelChartHome=null;//看板放置的panel

// 自动生成看板
Ext.Ajax.request({
	url:'authAction_getHomeData.action',
	async:false,
	success : function(response, options) {
		dataHome = Ext.decode(response.responseText);
		for(var i=0; i<dataHome.length ;i++){
			
			var chart=new Ext.chart.Chart({
				theme: 'Sky',
				id:'chart'+dataHome[i].itemId,
				title:dataHome[i].itemName,
				width: 450,
				height: 230,
				animate: true,//使用动画
				shadow: true,
				store: Ext.create('cms.store.common.countStore').load({
						 params: {
						    		itemId:dataHome[i].itemId
						 }
				}),
				renderTo: Ext.getBody(),
				shadow: true,//使用阴影
				axes: [{//x轴与y轴的声明
							type: 'Numeric',
							position: 'left',
							fields: 'qty',
							minimum: 0,
							label: {
							   values: [],
							   renderer: function(value) {
							        var temValue = Math.round(value);
							        if (temValue >= value) {
							             if ((temValue - value) > 0.00001) {
							                    return "";
							              }else {
							                    return temValue;
							              }
							         }else{
							             return "";
							         }
							   }
							}
						},{
						     type: 'Category',
						     position: 'bottom',
						     fields: 'name'
				}],       
				series: [{
						  type: 'column',
						  axis: 'bottom',
						  xField: 'name',
					      yField: 'qty',//x与y轴的数据声明
					      highlight: true,
					      tips:{
						         trackMouse: true,
						         width: 70,
						         height: 36,
						         renderer: function(storeItem, item) {
						               this.setTitle(storeItem.get('name') + ': ' + storeItem.get('qty'));              
						         }
						  },
						  label:{
						          display: 'insideEnd',
						          'text-anchor': 'middle',
						          field: 'qty',
						          renderer: Ext.util.Format.numberRenderer('0'),
						          orientation: 'vertical',
						          color: '#4169E1'
						  },
						  listeners: {
						               itemmouseup: function(item) {
						               Ext.getCmp('panelChart').hide();
						               Ext.getCmp('panelGrid').show();
									
						               Ext.Ajax.request({
						            		url:'authAction_getGridColumModle.action',
						            		params : {
						            			subItemId:item.storeItem.get('subItemId')
						            	    },
						            		success : function(response, options) {  
						            			var data = Ext.JSON.decode(response.responseText);//将字符串转换为json类型
						            			if(data.isSucc){
						            				var json=Ext.JSON.decode("{"+data.obj+"}");
						
						            				cm = new Ext.grid.column.Column(json.columModle);    	     		
						            		                                       
						            				var grid = Ext.create('Ext.grid.Panel',{
						            						region:'south',
						            			       		split: true,
						            			      	    border:false,
						            			            id : item.storeItem.get('subItemId'),
						            			            columnLines : true,
						            			            columns:json.columModle,
						            			            store:detailStore
						            	        	 });
						            				 var but =Ext.create('Ext.button.Button',{
						            						region:'north',
						            						text: '返回',
						            				    	handler: function() {
						            				    		 Ext.getCmp('panelChart').show();
						            				             Ext.getCmp('panelGrid').hide();
						            				    	}
						            				 });
						            				 Ext.getCmp('panelGrid').removeAll();
						            				 Ext.getCmp('panelGrid').add(but); 
						            				 Ext.getCmp('panelGrid').add(grid);            					
						            				 Ext.getCmp('panelGrid').doLayout();
						            					
						            				 var params = {
						                        			 subItemId:item.storeItem.get('subItemId')
						                        	 };
						                        		                       	
						                        	 Ext.apply(Ext.getCmp(item.storeItem.get('subItemId')).getStore().proxy.extraParams,params);
						                        	 Ext.getCmp(item.storeItem.get('subItemId')).getStore().removeAll();
													 Ext.getCmp(item.storeItem.get('subItemId')).getStore().load();
						            			}else{
						            				Ext.getCmp('panelGrid').removeAll();
						            			}
						            		}
						            	});          	
						            }  
						  }
						    }]
			  });
			
			  var dataChar={
			    	xtype:'panel',
			  		title:dataHome[i].itemName,	
			  		frame : true,
			        items:chart
			  };
			    					    		
			  if(panelChartHome==null || panelChartHome==""){
			  		panelChartHome = new Ext.create('Ext.panel.Panel', {
								  		id:'panelChart',
								  		region:'north',
									    layout:{
											type: 'table',
											columns: 2
										},
										defaults : 
										{
											xtype : 'textfield',
											margin:'10 10 10 10 ',
											labelAlign:'right'			
									    },items :[]														   	  							
			    
			  		});
			  }			
			  panelChartHome.add(dataChar);						 
		}
	}
});

   Ext.define('cms.view.TabPanel',{
			extend : 'Ext.tab.Panel',
			initComponent : function() {
				Ext.apply(this,{
								id : 'content-panel',
								region : 'center',
								bodyCls : 'bggrd',
								defaults : {
										autoScroll : true,
										border : false,
										bodyPadding : 0
								},
								activeTab : 0,
								plugins : Ext.create('Ext.ux.TabCloseMenu',{
														closeTabText : '关闭标签页',
														closeOthersTabsText : '关闭其他标签页',
														closeAllTabsText : '关闭所有标签页'
								}),
								items : [{ id: 'HomePage',
										   xtype:'panel',
										   title: '首页',
										   items:[chartPanel] 
								}]												
					});
					this.callParent(arguments);
			}
});

var chartPanel = {
	   	  autoScroll:true,
		  items: [
		  	 panelChartHome,
		  	 {
		  	   xtype:'panel',
			   id:'panelGrid',
			   hidden:true,
			   defaults : {
					xtype : 'textfield',
					margin:'5 10 5 10 ',
					labelAlign:'right'			
			   },items :[]														   	  
			}]
};

var time =function timer(){
	for(var i=0; i<dataHome.length ;i++){
		Ext.getCmp('chart'+dataHome[i].itemId).getStore().reload();
	}	
};
//setInterval(time,60000);