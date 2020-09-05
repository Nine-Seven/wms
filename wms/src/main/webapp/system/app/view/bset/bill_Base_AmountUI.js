/**
 * 模块名称：费用清单维护UI
 * 模块编码：B102
 * 创建：hcx 
 */
var Bill_Base_AmountStore=Ext.create('cms.store.bset.Bill_Base_AmountStore',{autoLoad:true});
Ext.define('cms.view.bset.bill_Base_AmountUI',{
	alias:'widget.bill_Base_AmountUI',
	title: $i18n.titleB102,//基础费用清单维护
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	id:'bill_Base_AmountUI',
	requires:['cms.view.common.commMenu2',
			  'cms.view.common.commMenu5',
			  'cms.view.common.bdef_DefOwnerCombo',
			  'cms.view.common.wms_DefFieldValCombo',
			  'cms.view.common.wms_DefStrategyCombo'
	         ],
	         items:[
	                {
	             	    xtype:'commMenuWidget2',
	             	    id:'menuB102',
	             	    region:'north'
	                 },{
		         		xtype:'panel',
		         		region:'north',
		         		height: 35,
		         		layout: {
		       			    type: 'table',
		        	        columns: 3
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
		         			id:'ownerNoUIB102',
		     				xtype:'wms_DefFieldValCombo',
		     				forceSelection : false,
		     				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		     					 proxy:{
			     					type:'ajax',
			     					method:'post',
			     					url:'bill_BaseAmountAction_getOwnerNoForQuery',
			   						reader:{
			     						root:'rootList',
			     						totalProperty:'totalCount'
			    					}
			   					}
			   			    }).load(),
			   			    displayField : 'dropValue',
		    			    valueField : 'value'
	         			},{
		         			fieldLabel :$i18n.billingProject, //计费项目
		         			id:'billingProjectUIB102',
		     				xtype:'wms_DefFieldValCombo',
		     				forceSelection : false,
		     				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		    					 proxy:{
				   					type:'ajax',
				    				method:'post',
				     				url:'bill_BaseAmountAction_getBillingProjectForUI',
				     				reader:{
				   						root:'rootList',
				   						totalProperty:'totalCount'
			  						}
			   					}
			   			    }).load(),
			   			    displayField : 'dropValue',
			   			    valueField : 'value'
		         		}]
		         	},{
	             	    xtype:'grid',
	             	    id:'gridB102',
	             	    region:'center',
	             	    store : Bill_Base_AmountStore,
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
	             		    dataIndex:'ownerNo'			
	             	    },{
	             		    width:130,
	             		    text : $i18n.serialno,  //费用清单流水号
	             		    dataIndex:'serialNo'			
	             	    },{
	             		    width:150,
	             		    text : $i18n.billingProject,  //计费项目
	             		    dataIndex:'projectText'			
	             	    },{
	             		    width:100,
	             		    text : $i18n.amountDate,  //结算日期
	             		    dataIndex:'amountDateText'			
	             	    },{
	             		    width:100,
	             		    text : $i18n.fixedValue,  //固定值
	             		    dataIndex:'fixedValue'			
	             	    },{
	             		    width:80,
	             		    text : $i18n.unitPrice,  //默认单价
	             		    dataIndex:'unitPrice'			
	             	    },{
	             		    width:100,
	             		    text : $i18n.billingCycle,  //计费周期
	             		    dataIndex:'billingCycleText'			
	             	    },{
	             		    width:100,
	             		    text : $i18n.accountValue,  //消费值
	             		    dataIndex:'value'			
	             	    },{
	             		    width:100,
	             		    text : $i18n.amountFlag,  //费用标识
	             		    dataIndex:'flagText'			
	             	    }],
	             	    dockedItems : 
	             	    [
	             		    {
	             		        xtype : 'pagingtoolbar',
	             			    store : Bill_Base_AmountStore,
	             			    dock : 'bottom',
	             			    displayInfo : true
	             			}
	             	    ]
	             	},{
	             		region:'south'
	             }]          
});