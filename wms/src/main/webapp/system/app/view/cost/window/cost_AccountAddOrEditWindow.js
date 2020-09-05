/**
 * 模块名称：科目设置维护维护 window
 * 模块编码：B303
 * 创建：hcx
 */
var costAccountDStore=Ext.create('cms.store.cost.cost_AccountDStore',{
	autoLoad:false,
	proxy: {
        type: 'ajax',
        method: 'post',
        url: 'cost_AccountAction_getAccountDList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
	});

var cost_Accoubt_DiscountStore=Ext.create('cms.store.cost.cost_AccountDiscountStore',{autoLoad:false,
	listeners:{
		'load':function(th,records,successful,eOpts ){
			if(th.count()>0){
				Ext.getCmp('grid_01_B303').getSelectionModel().select(0);
			}
		}
	}
});

var cost_FormulasetStore=Ext.create('cms.store.cost.cost_FormulasetStore',{
	autoLoad:false,
	proxy: {
	    type: 'ajax',
	    method: 'post',
	    url: 'cost_AccountAction_getFormulasetList',
	    reader : {
	    	type:'json',
	    	root : 'rootList',
	    	totalProperty : 'totalCount'
	    }
	}
});

var cost_Account_FormulaStore=Ext.create('cms.store.cost.cost_AccountFormulaStore',{autoLoad:false});

Ext.define('cms.view.cost.window.cost_AccountAddOrEditWindow',{
	extend:'Ext.window.Window',
	alias:'widget.cost_AccountAddOrEditWindow',
	layout:'border',
	id:'cost_AccountAddOrEditWindow',
	width : 700,
	height : 600,
	modal:true,
	items : [
	{
		xtype : 'form',
		id : 'IdFormB303',
		region : 'north',
		height:100,
		frame : true,
		layout: 
        {
        	type: 'table',
        	columns: 2
        },
		defaults : 
        {
			xtype:'textfield',
       	   	margin:'5 4 1 4',
       	   	labelAlign:'right',
       	   	labelWidth:120			
 	    },
		items:[{
        	    xtype:'bdef_DefOwnerCombo',
        	    fieldLabel:$i18n.owner_no, //货主编号
        	    id:'ownerNoB303',
        	    store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
 	            displayField : 'dropValue',
 	            valueField : 'value',
 	            allowBlank : false,
 	            beforeLabelTextTpl : required
 		   },{
		        xtype:'wms_DefFieldValCombo',
   		        fieldLabel:$i18n.account_cycle,  //结账周期
   		        id:'accountCycle3B03',
   		        store:Ext.create("cms.store.common.comboStore").load(
				{
					params:{str:"COST_ACCOUNT_M,ACCOUNT_CYCLE"}
				}),
				allowBlank : false,
			    beforeLabelTextTpl : required
   		   },{
   			   xtype:'wms_DefFieldValCombo',   
   			   fieldLabel:$i18n.amountDate,   //结账日期
   			   id:'balanceDayB303_1',
   			   store:Ext.create("cms.store.common.comboStore").load(
			   {
					params:{str:"COST_FORMULASET,BALANCE_DAY"}
			   })
   		   },{
   				xtype:'textfield',
     			fieldLabel:$i18n.amountDate,//结账日期
     			id:'balanceDayB303_2'
     		},{
           	    xtype:'bdef_DefOwnerCombo',
           	    fieldLabel:$i18n.account_group_no, //科目组编码
           	    id:'accountGroupNoB303',
           	    store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
    					 proxy:{
    					type:'ajax',
    					method:'post',
						async:false,
    					url:'cost_AccountAction_getAccountGroupNoCombo',
    					reader:{
    						root:'rootList',
    						totalProperty:'totalCount'
    						}
    					}
    			    }),
    	            displayField : 'dropValue',
    	            valueField : 'value'
     		},{
			   xtype:'wms_DefFieldValCombo',
   			   fieldLabel:$i18n.manage_status,  //状态
   			   id:'statusB303',
   			   store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"COST_ACCOUNT_M,STATUS"}
			   	}),
			   	allowBlank : false,
				beforeLabelTextTpl : required
   		   },{
				fieldLabel:$i18n.remark,//备注
				id:'remarkB303_1'
		    }]
	},
	{
		xtype : 'tabpanel',
		id:'tabB303',
	    region:'center',
	    items : [
	    {
			title:$i18n.accountPolicies,//科目策略
	    	id:'tabB303_1',
			layout:'border',
			items:[
			{
		        xtype:'grid',
			    id:'grid_01',
			    region:'north',
			    height:180,
			    store:costAccountDStore,
			    columns:[
			    {			
				    xtype : 'rownumberer',
					width : 30
			    },{
					width: 110,
					text : $i18n.account_type,  //科目类型
					dataIndex:'accountTypeText'		
				},{
					width: 110,
					text :  $i18n.account_no,  //科目代码
					dataIndex:'accountNo'		
				},{
					width:160,
					text : $i18n.account_name,  //科目名称
					dataIndex:'accountName'		
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
			    },{
					width: 110,
					text : $i18n.remark,  //备注
					dataIndex:'remark'		
				}]

	        },{
	        	   xtype:'fieldset',
	         	   layout: {
	      	       type: 'table',
	      	           columns: 2
	      	       },
	      	       defaults:{
	   	   	       xtype:'textfield',
	   	   		   margin:'5 4 1 4',
	   	   		   labelAlign:'right',
	   	   		   labelWidth:120
	 	       },
	 		   items:[
	   		    {
			 		fieldLabel:$i18n.account_type, //科目类型
			 		id:'accountTypeB303',
			 		xtype:'bdef_DefOwnerCombo',
			 		store:Ext.create("cms.store.common.comboStore").load(
					{
						params:{str:"COST_ACCOUNT_D,ACCOUNT_TYPE"}
					}),
					allowBlank : false,
				    beforeLabelTextTpl : required
			 	},{
					fieldLabel :$i18n.account, //科目
					id:'accountNoB303',
					xtype:'bdef_DefOwnerCombo',
					store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
						 proxy:{
		 				type:'ajax',
		 				method:'post',
						async:false,
		 				url:'cost_AccountAction_getAccountForWind',
		 				reader:{
							root:'rootList',
		 					totalProperty:'totalCount'
							}
						}
					 }),
					 displayField : 'dropValue',
					 valueField : 'value',
				   	 beforeLabelTextTpl : required
			 	},/*{
			 		fieldLabel:$i18n.account_no, //科目代码
			 		id:'accountNoB303',
			 		xtype:'textfield',
			 		maxLength:5,		
			     	allowBlank : false,
			   		beforeLabelTextTpl : required
			 	},{
			 		fieldLabel:$i18n.account_name, //科目名称
			 		id:'accountNameB303',
			 	    xtype:'textfield',		
			     	allowBlank : false,
			   		beforeLabelTextTpl : required
			 	},*/{
			 		fieldLabel:$i18n.account_name, //科目Id
			 		id:'accountIdB303',
			 		hidden:true,
			 	    xtype:'numberfield'
			 	},{
					xtype:'numberfield',   
					fieldLabel:$i18n.other_cost1,  //其他费用1
					id:'otherCost1_B303',
					minValue:0
				},{
					xtype:'numberfield',   
					fieldLabel:$i18n.other_cost2,  //其他费用2
					id:'otherCost2_B303',
					minValue:0
				},{
					xtype:'numberfield',   
					fieldLabel:$i18n.other_cost3,  //其他费用3
					id:'otherCost3_B303',
					minValue:0
				},{
					xtype:'numberfield',   
					fieldLabel:$i18n.other_cost4,  //其他费用4
					id:'otherCost4_B303',
					minValue:0
				},{
					xtype:'numberfield',   
					fieldLabel:$i18n.other_cost5,  //其他费用5
					id:'otherCost5_B303',
					colspan:2,
					minValue:0
				},{
					xtype:'textareafield',   
					fieldLabel:$i18n.memo,   //备注
					id:'remarkB303',
					colspan:2,
					width:560,
			        allowBlank : true
				}]
		    }]
			
		
	    },
	    {


	    	title:$i18n.includeBillingProject,//包含项目
	    	frame : true,
	    	id:'tabB303_3',
	    	itemId:'tabB303_3i',
//	    	layout:'fit',
	    	layout:'border',
	    	extend:'Ext.panel.Panel',
	        items:[
			 {

					xtype:'grid',
					id:'grid_02_B303',
					title:$i18n.billingProject,//"计费项目",
					width:'48%',
					region:'west',
					store:cost_FormulasetStore,
					multiSelect: true,  
				    selModel: {  
			        	selType:'checkboxmodel'  
				    },
					columns:[{			
						xtype : 'rownumberer',
						width : 30
					},{
						width:100,
						text:$i18n.billingProjectNo,  //计费项目编号
						dataIndex:'billingProject',
						sortable: false
					},{
						width:160,
						text:$i18n.projectName,  //计费项目名称
						dataIndex:'projectName',
						sortable: false
					}],
					dockedItems : [{
						xtype : 'pagingtoolbar',
						dock : 'bottom',
						store:cost_FormulasetStore,
						displayInfo : true
					}]
				 
			 },{
					xtype : 'form',
					region : 'center',
					layout:{
						type:'table',
						columns:1
					},
					width:'4%',
					frame : true,
					defaults:{
						margin:'10 0 0 0'
					},
					items : [{
						xtype:'button',
						margin:'80 0 0 0',
						text:'>>',
						id:'rightB303'
					},
					{
						xtype:'button',			
						text:'<<',
						id:'leftB303'
					}]
				},{

				    xtype:'grid',
				    id:'grid_03_B303',
				    title:$i18n.accountRelaction,//计费项目与科目的关系
				    width:'48%',
				    region:'east',
				    store:cost_Account_FormulaStore,
				    multiSelect: true,  
				    selModel: {  
				        selType:'checkboxmodel'  
				    },
				    columns:[{			
						xtype : 'rownumberer',
						width : 30
				    },{
						width:100,
						text:$i18n.account,    //科目
						dataIndex:'accountName'
				    },{
						width:100,
						text:$i18n.billingProjectNo,  //计费项目编号
						dataIndex:'billingProject',
						sortable: false
					},{
						width:120,
						text:$i18n.projectName,  //计费项目名称
						dataIndex:'projectName',
						sortable: false
					}],
				    dockedItems : [{
						xtype : 'pagingtoolbar',
						dock : 'bottom',
						store:cost_Account_FormulaStore,
						displayInfo : true
				    }]
				
			 }]
	
		
		},{

			title:$i18n.PreferentialPolicies,//优惠策略
	    	id:'tabB303_2',
			layout:'border',
			items:[
			{
		        xtype:'grid',
			    id:'grid_01_B303',
			    region:'north',
			    height:180,
			    store:cost_Accoubt_DiscountStore,
			    columns:[{			
			        xtype : 'rownumberer',
				    width : 30
			    },
			    {
			    	 width: 100,
			  		    text : $i18n.accountLadder,  //优惠阶梯
			  		    dataIndex:'accountLadder'		
				},{
			    	 width: 180,
			  		    text : $i18n.discount_flag,  //优惠方式
			  		    dataIndex:'discountFlagText'		
				},{
			    	 width: 80,
			  		    text : $i18n.value1,  //值1
			  		    dataIndex:'value1'		
				},{
			    	 width: 80,
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
				}
			    ]
//			,
//			    dockedItems : [
//			    {
//			        xtype : 'pagingtoolbar',
//			        dock : 'bottom',
//				    store:cost_Accoubt_DiscountStore,
//				    displayInfo : true
//			    }
//			    ]
	        },{
	     	     xtype:'fieldset',
	       	   layout: {
	    	       type: 'table',
	    	           columns: 2
	    	       },
	    	       defaults:{
	 	   	       xtype:'textfield',
	 	   		   margin:'5 4 1 4',
	 	   		   labelAlign:'right',
	 	   		   labelWidth:120
	 	       },
	 		   items:[
	 		   {
	 			  xtype:'textfield',   
					fieldLabel:$i18n.accountLadder,   //优惠阶梯
					id:'accountLadder_2',
	 	     	    allowBlank : true
	 		   },      
	 		   {

					xtype:'wms_DefFieldValCombo',   
					fieldLabel:$i18n.discount_flag,   //优惠方式
					id:'discountFlagB303_2',
//					store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
//						 proxy:{
//	 					type:'ajax',
//	 					method:'post',
//	 					url:'bill_AccountAction_getDiscountFlagList',
//	 					reader:{
//							root:'rootList',
//	 						totalProperty:'totalCount'
//							}
//						}
//				    }).load(),
				    displayField : 'dropValue',
				    valueField : 'value',
					
					store:Ext.create("cms.store.common.comboStore").load(
					{
						params:{str:"COST_ACCOUNT_DISCOUNT,DISCOUNT_FLAG"}
					})
				
				},{
					xtype:'numberfield',   
					fieldLabel:$i18n.value1,  //值1
					id:'value1B303_2',
					minValue:0,
			     	allowBlank : true
				},{
					xtype:'numberfield',   
					fieldLabel:$i18n.value2,  //值2
					id:'value2B303_2',
					minValue:0,
			     	allowBlank : true
				},{
					xtype:'wms_DefFieldValCombo',   
					fieldLabel:$i18n.discountAccountNo,   //优惠计费项目
					id:'billingProjectB303_2',
					colspan:2,
					store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				 	    proxy:{
								type:'ajax',
								method:'post',
								url:'cost_AccountAction_getBillingProjectComboList',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								}
							}
					 }),
					 displayField : 'dropValue',
					 valueField : 'value',
					 allowBlank : true
				},{
					xtype:'textareafield',   
					fieldLabel:$i18n.memo,   //备注
					id:'remarkB303_2',
					colspan:2,
					width:560,
			        allowBlank : true
				}]
		    }]
			
		}]
	},
	{
		region:'south',
		xtype:'commMenuWidget5',
		border:0,
		id:'cost_AccountB303'
   	}
]
});