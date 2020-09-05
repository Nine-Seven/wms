/**
 * 模块名称：科目设置维护UI
 * 模块编码：B303
 * 创建：hcx 
 */
var cost_AccountSetStore=Ext.create('cms.store.cost.cost_AccountSetStore',{autoLoad:true});
var cost_Account_Store=Ext.create('cms.store.cost.cost_AccountDStore',{autoLoad:true});
var cost_AccoubtDiscountStore=Ext.create('cms.store.cost.cost_AccountDiscountStore',{autoLoad:false});
var cost_AccountFormulaStore=Ext.create('cms.store.cost.cost_AccountFormulaStore',{autoLoad:false});
Ext.define('cms.view.cost.cost_AccountUI',{
	alias:'widget.cost_AccountUI',
	title: $i18n.titleB301, //科目设置维护
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
			'cms.view.common.commMenu2',
			'cms.view.common.commMenu4',
			'cms.view.common.commMenu5',
			'cms.view.common.bdef_DefOwnerCombo',
			'cms.view.common.wms_DefFieldValCombo',
			'cms.view.common.wms_DefStrategyCombo'
	          ],
	items:[
	       {
	   	    xtype:'tabpanel',
	   	    id:'tabPIdB303000',
	   	    region:'center',
	   	    items:[
	   		{
	           	title: $i18n.titleB301_1,//科目代码维护
	   		    id:'tabPidB303',
	   		    itemId:'tabPidB303i',
	   		    layout:'border',
	   		    items: [
	   			{
	   			    xtype : 'commMenuWidget4',
   				    id:'menuB303',
	   			    region:'north'
	   			},{
	   				xtype : 'grid',
	   				region:'center',
	   				id : 'gridAccountSetB303',
	   				width : '100%',
	   				height : '100%',
	   				store : cost_AccountSetStore,
	   				columns : [ 
	   				{
	   					xtype : 'rownumberer',
	   					width : 30
	   				},{
	   					width : 160,
	   					text : $i18n.account_no,//科目代码
	   					dataIndex : 'accountNo' 
	   				},{
	   					width : 200,
	   					text : $i18n.account_name,//科目名称
	   					dataIndex : 'accountName'
	   				},{
	   					width:200,
	   					text:$i18n.remark,//备注
	   					dataIndex:'remark'
	   				}],
	   				dockedItems : [{
	   					xtype : 'pagingtoolbar',
	   					store : cost_AccountSetStore,
	   					dock : 'bottom',
	   					displayInfo : true
	   					}]
	   				}]
	   		},{
	   			title: $i18n.titleB301,//科目设置维护
	   			id:'tabPidd2_B303',
	   			itemId:'tabPidd2_B303i',
	   			layout:'border',
	   			items:[
	   			    {
	   				    xtype:'toolbar',
	   				    region:'north',
	   				    items : [{
							text : $i18n.titleadd,//新增
							iconCls : 'add',
							id:'addB303'
						},{
							text : $i18n.titleupdate,//修改
							iconCls : 'edit',
							id:'editB303'
						},{
							text : $i18n.delete_1,//删除
							iconCls : 'delete',
							id:'deleteB303'
						},{
			   			    text : $i18n.refresh,//刷新
			   			    iconCls : 'refresh',
			   			    id:'refreshBB303'
			   		    }]	
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
	   							id:'ownerNoUIB303',
	   							xtype:'wms_DefFieldValCombo',
	   							 store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
	   								 proxy:{
	   									type:'ajax',
	   									method:'post',
	   									url:'cost_AccountAction_getOwnerNoForQuery',
	   									reader:{
	   										root:'rootList',
	   										totalProperty:'totalCount'
	   								}
	   								}
	   					    }).load(),
	   						    displayField : 'dropValue',
	   						    valueField : 'value'
	   						},{
	   							fieldLabel :$i18n.account, //科目
	   							id:'accountUIB303',
	   							xtype:'wms_DefFieldValCombo',
	   							store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
	   								 proxy:{
	   			 					type:'ajax',
	   			 					method:'post',
	   			 					url:'cost_AccountAction_getAccountForUI',
	   			 					reader:{
	   									root:'rootList',
	   			 						totalProperty:'totalCount'
	   									}
	   								}
	   						    }).load(),
	   						    displayField : 'dropValue',
	   						    valueField : 'value'
	   						}]
	   					},
	   					{
	   					    xtype:'grid',
	   					    region:'north',
	   					    height:240,
	   					    id:'account_MUIB303',
	   					    store:cost_Account_Store,
	   					    columns:[
	   					    {			
	   							xtype : 'rownumberer',
	   							width : 30
	   					    },{
	   							width : 110,
	   							text : $i18n.warehouse,//仓别
	   							dataIndex:'warehouseNo'
	   					    },{
	   					    	 width: 150,
	   				  		    text : $i18n.owner_no,  //货主编号
	   				  		    dataIndex:'ownerNoText'		
	   					    },{
	   					    	 width: 90,
	   					  		    text :  $i18n.account_group_no,  //科目组编码
	   					  		    dataIndex:'accountGroupNo'		
	   						},{
	   					    	 width: 110,
	   					  		    text :  $i18n.account_no,  //科目代码
	   					  		    dataIndex:'accountNo'		
	   						},{
	   					    	 width:160,
	   					  		    text : $i18n.account_name,  //科目名称
	   					  		    dataIndex:'accountName'		
	   						},{
	   					    	 width: 110,
	   					  		    text : $i18n.account_type,  //科目类型
	   					  		    dataIndex:'accountTypeText'		
	   						},{
	   					    	 width: 60,
	   					  		    text : $i18n.account_cycle,  //科目周期
	   					  		    dataIndex:'accountCycleText'		
	   						},{
	   					    	 width: 130,
	   					  		    text : $i18n.account_date,  //结账日期
	   					  		    dataIndex:'balanceDayText'		
	   						},{
	   			     		    width:90,
	   			     		    text : $i18n.manage_status,  //状态
	   			     		    dataIndex:'statusText'			
	   			     	    },{
	   					    	 width: 110,
	   					  		    text : $i18n.remark,  //备注
	   					  		    dataIndex:'remark_m'		
	   						},{
	   			     	    	width:100,
	   							text:$i18n.other_cost1,  //其他费用1
	   							dataIndex:'otherCost1'	
	   						},{
	   							width:100,
	   							text:$i18n.other_cost2,  //其他费用2
	   							dataIndex:'otherCost2'	
	   						},{
	   							width:100,  
	   							text:$i18n.other_cost3,  //其他费用3
	   							dataIndex:'otherCost3'	
	   						},{
	   							width:100,
	   							text:$i18n.other_cost4,  //其他费用4
	   							dataIndex:'otherCost4'	
	   						},{
	   							width:100,  
	   							text:$i18n.other_cost5,  //其他费用5
	   							dataIndex:'otherCost5'	
	   						}],
	   					    dockedItems : [{
	   							xtype : 'pagingtoolbar',
	   							dock : 'bottom',
	   							store:cost_Account_Store,
	   							displayInfo : true
	   					    }]
	   					},{

	   					xtype:'grid',
	   					id:'accountUIB303_2',
	   					title:$i18n.PreferentialPolicies,//优惠策略
	   					width:'59%',
	   					region:'west',
	   					store:cost_AccoubtDiscountStore,
	   					columns:[{			
	   						xtype : 'rownumberer',
	   						width : 30
	   					},{
	   				    	    width: 100,
	   					        text : $i18n.accountLadder,  //优惠阶梯
	   					        dataIndex:'accountLadder'		
	   				       },{
	   			 	        width: 220,
	   					        text : $i18n.discount_flag,  //优惠方式
	   					        dataIndex:'discountFlagText'		
	   				       },{
	   			 	        width: 100,
	   					        text : $i18n.value1,  //值1
	   					        dataIndex:'value1'		
	   				       },{
	   			 	        width: 100,
	   					        text : $i18n.value2,  //值2
	   					        dataIndex:'value2'		
	   				       },{
	   					    	 width: 100,
	   					  		    text : $i18n.discountAccountNo,  //优惠科目代码
	   					  		    dataIndex:'billingProject'		
	   						},{
	   			 	        width: 100,
	   					        text : $i18n.remark,  //备注
	   					        dataIndex:'remark'		
	   				       }],
	   					dockedItems : [{
	   						xtype : 'pagingtoolbar',
	   						dock : 'bottom',
	   						store:cost_AccoubtDiscountStore,
	   						displayInfo : true
	   					}]
	   				
	   			 	},{

	   				    xtype:'grid',
	   				    id:'accountUIB303_3',
	   				    title:$i18n.includeBillingProject,//包含项目
	   				    width:'40%',
	   				    region:'east',
	   				    store:cost_AccountFormulaStore,
	   				    columns:[{			
	   						xtype : 'rownumberer',
	   						width : 30
	   				    },{
	   						width : 100,
	   						text : $i18n.billingType,//项目类型
	   						dataIndex:'billingTypeText'
	   				    },{
	   						width : 100,
	   						text : $i18n.billingProject,//项目编码
	   						dataIndex:'billingProject'
	   				    },{
	   						width : 200,
	   						text : $i18n.projectName,//项目名称
	   						dataIndex:'projectName'
	   				    }],
	   				    dockedItems : [{
	   						xtype : 'pagingtoolbar',
	   						dock : 'bottom',
	   						store:cost_AccountFormulaStore,
	   						displayInfo : true
	   				    }]
	   				
	   			 	},{
	   			    	region:'south'
	   				}]
	   		}]
	   	}]
});