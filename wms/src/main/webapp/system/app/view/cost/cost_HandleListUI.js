/**
 * 模块名称：手工录入消费清单
 * 模块编码：B905
 * 创建：hcx 20160707
 */
var cost_HandleListtore=Ext.create('cms.store.cost.cost_HandleListStore',{
	autoLoad:true,
	listeners:{
		'load':function(th,records,successful,eOpts ){
  			 var rowindexB905=_myAppGlobal.getController('cms.controller.cost.cost_HandleListController').getRowindexB905();
  			 var typeB905=_myAppGlobal.getController('cms.controller.cost.cost_HandleListController').getTypeB905();
			 if(typeB905=='edit'){
				 Ext.getCmp('gridB905').getSelectionModel().select(rowindexB905-(Ext.getCmp('gridB905').getStore().currentPage-1)*appConfig.getPageSize());
			 }
			 var arrayObj = new Array();
			 arrayObj[0]='value';
		     countList2('gridB905',arrayObj,'billingProject');
		}
	}
	});
Ext.define('cms.view.cost.cost_HandleListUI',{
	alias:'widget.cost_HandleListUI',
	title: $i18n.titleB905,//手工录入消费清单
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	id:'bill_Base_AmountUI',
	requires:['cms.view.common.commMenu2',
			  'cms.view.common.commMenu5',
			  'cms.view.common.bdef_DefOwnerCombo',
			  'cms.view.common.wms_DefFieldValCombo',
			  'cms.view.common.remoteCombo'
	         ],
	         items:[
	                {
	             	    xtype:'commMenuWidget2',
	             	    id:'menuB905',
	             	    region:'north'
	                 },{
		         		xtype:'panel',
		         		region:'north',
		         		height: 60,
		         		layout: {
		       			    type: 'table',
		        	        columns: 4
		         		},
		       		    defaults : {
		         			xtype : 'textfield',
		         			margin : '3 3 3 3',
		         			labelAlign:'right',
		       				allowBlank: true,
		    				width : 280,
		       				labelWidth : 90
		   				},
		       			items:[{
		         			fieldLabel : $i18n.owner_no,
		         			id:'ownerNoUIB905',
		     				xtype:'wms_DefFieldValCombo',
		     				forceSelection : false,
		     				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		     					 proxy:{
			     					type:'ajax',
			     					method:'post',
			     					url:'cost_HandleListAction_getSelectForUI',
			   						reader:{
			     						root:'rootList',
			     						totalProperty:'totalCount'
			    					}
			   					}
			   			    }),
			   			    displayField : 'dropValue',
		    			    valueField : 'value'
	         			},{
		         			fieldLabel :$i18n.amountFlag, //费用标识
		         			id:'costFlagUIB905',
		     				xtype:'wms_DefFieldValCombo',
		     				forceSelection : false,
		     				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		    					 proxy:{
		    	   					type:'ajax',
		    	    				method:'post',
		    	     				url:'cost_HandleListAction_getSelectForUI',
		    	     				reader:{
		    	   						root:'rootList',
		    	   						totalProperty:'totalCount'
		      						}
		       					}
		       			    })
		    	        },{
		         			fieldLabel :$i18n.status, //状态
		         			id:'statusUIB905',
		     				xtype:'wms_DefFieldValCombo',
		     				forceSelection : false,
		    	             colspan:2,
		     				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		    					 proxy:{
		    	   					type:'ajax',
		    	    				method:'post',
		    	     				url:'cost_HandleListAction_getSelectForUI',
		    	     				reader:{
		    	   						root:'rootList',
		    	   						totalProperty:'totalCount'
		      						}
		       					}
		       			    })
		    	        },{
		         			fieldLabel :$i18n.billingProject, //计费项目
		         			id:'billingProjectUIB905',
		     				xtype:'wms_DefFieldValCombo',
		     				forceSelection : false,
		     				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		    					 proxy:{
				   					type:'ajax',
				    				method:'post',
				     				url:'cost_HandleListAction_getSelectForUI',
				     				reader:{
				   						root:'rootList',
				   						totalProperty:'totalCount'
			  						}
			   					}
			   			    }),
			   			    displayField : 'dropValue',
			   			    valueField : 'value'
		         		},{
		    	        	xtype:'datefield',
		    	            fieldLabel: $i18n.amountDate,//结算日期
		    	            format : 'Y-m-d',
		    	            id: 'amountDateUIB905'
		    	        },{
		    				xtype : 'remoteCombo',
		    				fieldLabel : $i18n.source_plan_no,// 来源单号
		    				id:'sourceNoUIB905',
		    				store : Ext.create("cms.store.bdef.bdef_DefOwnerComboStore",{
		    					proxy:{
		    						type:'ajax',
		    						method:'post',
		    						url:'cost_HandleListAction_getSelectForUI',
		    						reader:{
		    							root:'rootList',
		    							totalProperty:'totalCount'
		    						}
		        				}
		    				})
		    			},{
		    	        	xtype: 'button',
		    	        	width : 80,
		    	        	name:'btnQueryB905',
		    	            text : $i18n.query
		    	        }]
		         	},{
	             	    xtype:'grid',
	             	    id:'gridB905',
	             	    region:'center',
	             	    store : cost_HandleListtore,
	             	    columns:[{			
	             	        xtype : 'rownumberer',
	             		    width : 30
	             	    },{
	             		    width:60,
	             		    text : $i18n.warehouse,  //仓别
	             		    dataIndex:'warehouseNo'			
	             	    },{
	             		    width:80,
	             		    text : $i18n.owner_no,  //货主编号
	             		    dataIndex:'ownerNoText'			
	             	    },{
	             		    width:130,
	             		    text : $i18n.serialno,  //费用清单流水号
	             		    dataIndex:'serialNo'			
	             	    },{
			    	    	width: 130,
			      		    text :  $i18n.account_no,  //科目代码
			      		    dataIndex:'accountNoText'		
			    		},{
	             		    width:150,
	             		    text : $i18n.billingProject,  //计费项目
	             		    dataIndex:'projectText'			
	             	    },{
	             		    width:100,
	             		    text : $i18n.amountDate,  //结算日期
	             		    dataIndex:'amountDateText'			
	             	    },{
	             		    width:80,
	             		    text : $i18n.unitPrice,  //默认单价
	             		    dataIndex:'unitPrice'			
	             	    },{
	             		    width:100,
	             		    text : $i18n.accountValue,  //消费值
	             		    dataIndex:'value'			
	             	    },{
	             		    width:150,
	             		    text : $i18n.source_plan_no,  //来源单号
	             		    dataIndex:'sourceNo'			
	             	    },{
	             		    width:100,
	             		    text : $i18n.amountFlag,  //费用标识
	             		    dataIndex:'costFlagText'			
	             	    },{
	             		    width:100,
	             		    text : $i18n.status,  //状态
	             		    dataIndex:'statusText'			
	             	    }]/*,
	             	    dockedItems : 
	             	    [
	             		    {
	             		        xtype : 'pagingtoolbar',
	             			    store : cost_HandleListtore,
	             			    dock : 'bottom',
	             			    displayInfo : true
	             			}
	             	    ]*/
	             	},{
	             		region:'south'
	             }]          
});