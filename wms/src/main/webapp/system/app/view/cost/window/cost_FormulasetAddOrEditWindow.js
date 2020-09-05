/**
 * 模块名称：计费公式管理 window
 * 模块编码：B103
 * 创建：hcx
 */
var cost_formula_discount=Ext.create('cms.store.cost.cost_FormulaDiscountStore',{autoLoad:false,
	listeners:{
		'load':function(th,records,successful,eOpts ){
			if(th.count()>0){
				Ext.getCmp('grid_01_B103').getSelectionModel().select(0);
			}
		}
	}
});
var cost_FormulaArticlefamilyList=Ext.create('cms.store.cost.cost_FormulaArticlefamilyStore',{autoLoad:false});

var cost_FormulaArticlefamily=Ext.create('cms.store.cost.cost_FormulaArticlefamilyStore',{
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'cost_FormulasetAction_getFormulaArticlefamily.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});

Ext.define('cms.view.cost.window.cost_FormulasetAddOrEditWindow',{
	extend:'Ext.window.Window',
	alias:'widget.cost_FormulasetAddOrEditWindow',
	layout:'border',
	id:'cost_FormulasetAddOrEditWindow',
	width:720,
	height:550,
	modal:true,
	items:[
	   	{
			xtype : 'form',
			id : 'IdFormB103',
			region : 'north',
			height:155,
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
            	id:'ownerNoB103',
            	store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
            	displayField : 'dropValue',
            	valueField : 'value',
            	allowBlank : false,
            	beforeLabelTextTpl : required
     		},{
    			xtype:'wms_DefFieldValCombo',
     			fieldLabel:$i18n.billingType,    //项目类型
     			id:'billingTypeB103',	     					
     			store:Ext.create("cms.store.common.comboStore",{
		   			  proxy:{
		   					 type:'ajax',
		   					 method:'post',
		   					 url:'cost_FormulasetAction_getBillingTypeForWind',
		   					 reader:{
		   					   root:'rootList',
		   					   totalProperty:'totalCount'
		   					 }
		   			  }
		   		   }).load(),
		   		allowBlank : false,
				beforeLabelTextTpl : required
     		},{
     			fieldLabel:$i18n.billingNo, //项目编号
     			id:'billingProjectB103',
     			xtype:'textfield',
     			maxLength:8,		
	     	    allowBlank : false,
	   		    beforeLabelTextTpl : required
     		},{
   				xtype:'textfield',
     			fieldLabel:$i18n.billingName,    //项目名称
     			id:'projectNameB103',	     					
     					
	     		allowBlank : false,
	     		beforeLabelTextTpl : required
     		},{
    			xtype:'wms_DefFieldValCombo',
     			fieldLabel:$i18n.isStandardFlag,    //是否为标准策略
     			id:'standardFlagB103',	     					
     			store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"COST_FORMULASET,STANDARD_FLAG"}
		   		}),
		   		allowBlank : false,
				beforeLabelTextTpl : required
     		},{
				xtype:'wms_DefFieldValCombo',
				   fieldLabel:$i18n.amountFlag,  //费用标识
				   id:'costFlagB103',
				   store:Ext.create("cms.store.common.comboStore").load(
					{
							params:{str:"COST_OTHER_LIST,COST_FLAG"}
				   	}),
				   	allowBlank : false,
//				   	readOnly:true,
					beforeLabelTextTpl : required	
			},{
				xtype : 'datefield',
				fieldLabel : $i18n.beginDate,//起始日期
				id : 'beginDateB103',								
				format : 'Y-m-d'
			},{
				xtype : 'datefield',
				fieldLabel : $i18n.endDay,//截止日期
				id : 'endDateB103',								
				format : 'Y-m-d'
			},{
	   			   xtype:'wms_DefFieldValCombo',
	   			   fieldLabel:$i18n.manage_status,  //状态
	   			   id:'statusB103',
	   			   store:Ext.create("cms.store.common.comboStore").load(
					{
							params:{str:"COST_FORMULASET,STATUS"}
				   	}),
				   	allowBlank : false,
					beforeLabelTextTpl : required
	   		   }]
		},
		{
			xtype : 'tabpanel',
			id:'tabB103',
		    region:'center',
		    items : [
		    {
		    	title:$i18n.projectPolicies,//项目策略
		    	frame : true,
		    	id:'tabB103_1',
		    	itemId:'tabB103_1i',
		    	layout:'fit',
		        items:[
				 {
		      	     xtype:'fieldset',
		         	   layout: {
		      	       type: 'table',
		      	           columns: 2
		      	       },
		      	       defaults:{
		   	   	       xtype:'textfield',
		   	   		   margin:'5 8 1 8',
		   	   		   labelAlign:'right',
		   	   		   labelWidth:120
		   	       },
		   		   items:[
		   		   {
		   			   xtype:'wms_DefFieldValCombo',
		   			   fieldLabel:$i18n.billingFlag,  //计费方式
		   			   id:'billingFlagB103',
		   			 store:Ext.create("cms.store.common.comboStore").load(
					 {
							params:{str:"COST_FORMULASET,BILLING_FLAG"}
					 }),
					 allowBlank : false,
					 beforeLabelTextTpl : required
		   		   },{
		   			   xtype:'wms_DefFieldValCombo',   
		   			   fieldLabel:$i18n.billingUnit,   //默认计费单位
		   			   id:'billingUnitB103',
		   			   store:Ext.create("cms.store.common.comboStore").load(
					   {
							params:{str:"COST_FORMULASET,BILLING_UNIT"}
					   }),
					   allowBlank : false
		   		   },{   
	     			   xtype:'combo',
	     		 	   fieldLabel:$i18n.valueFlag,//取值方式
	     		 	   id:'valueFlagB103',
	     		 	   colspan:2,
	     		 	   width : 560,
	     		 	   displayField : 'dropValue',
	     			   valueField : 'value',
	     			   store:Ext.create("cms.store.common.comboStore",{
			   			  proxy:{
			   					 type:'ajax',
			   					 method:'post',
			   					 url:'cost_FormulasetAction_getValueFlagCombo',
			   					 reader:{
			   					   root:'rootList',
			   					   totalProperty:'totalCount'
			   					 }
			   			  }
			   		   }).load()
		   		   },{
				    	xtype:'textfield',   
					    fieldLabel:$i18n.fixedValue,//固定值
					    minValue:0,
					    id:'valueB103'
				    },{
						xtype:'numberfield',   
						fieldLabel:$i18n.unitPrice,  //默认单价
						id:'unitPriceB103',
						allowBlank : false, 
						minValue:0
					},{
		   		        xtype:'wms_DefFieldValCombo',
		   		        fieldLabel:$i18n.billingCycle,  //计费周期
		   		        id:'billingCycleB103',
		   		        store:Ext.create("cms.store.common.comboStore").load(
						{
							params:{str:"COST_FORMULASET,BILLING_CYCLE"}
						}),
						allowBlank : false,
					    beforeLabelTextTpl : required
		   		   },{
		   			   xtype:'wms_DefFieldValCombo',   
		   			   fieldLabel:$i18n.amountDate,   //结算日期
		   			   id:'balanceDayB103_1',
		   			   store:Ext.create("cms.store.common.comboStore").load(
					   {
							params:{str:"COST_FORMULASET,BALANCE_DAY"}
					   })
		   		   },{
		   				xtype:'textfield',
		     			fieldLabel:$i18n.amountDate,//结算日期
		     			id:'balanceDayB103_2'
		     		},{
						xtype:'numberfield',   
						fieldLabel:$i18n.other_cost1,  //其他费用1
						id:'otherCost1_B103',
						minValue:0
					},{
						xtype:'numberfield',   
						fieldLabel:$i18n.other_cost2,  //其他费用2
						id:'otherCost2_B103',
						minValue:0
					},{
						xtype:'numberfield',   
						fieldLabel:$i18n.other_cost3,  //其他费用3
						id:'otherCost3_B103',
						minValue:0
					},{
						xtype:'numberfield',   
						fieldLabel:$i18n.other_cost4,  //其他费用4
						id:'otherCost4_B103',
						minValue:0
					},{
						xtype:'numberfield',   
						fieldLabel:$i18n.other_cost5,  //其他费用5
						id:'otherCost5_B103',
						colspan:2,
						minValue:0
					},{
						xtype:'textareafield',   
						fieldLabel:$i18n.memo,   //备注
						id:'remarkB103_1',
						colspan:2,
						width:560,
				        allowBlank : true
					}]
			    }]
		},
		{
			title:$i18n.PreferentialPolicies,//阶梯优惠
	    	id:'tabB103_2',
	    	itemId:'tabB103_2i',
			layout:'border',
			items:[
			{
		        xtype:'grid',
			    id:'grid_01_B103',
			    region:'north',
			    height:160,
			    store:cost_formula_discount,
			    columns:[{			
			        xtype : 'rownumberer',
				    width : 30
			    },
			    {
			    	 width: 100,
			  		    text : $i18n.accountLadder,  //优惠阶梯
			  		    dataIndex:'ladder'		
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
			  		    text : $i18n.remark,  //备注
			  		    dataIndex:'remark'		
				}
			    ]
//			,
//			    dockedItems : [
//			    {
//			        xtype : 'pagingtoolbar',
//			        dock : 'bottom',
//				    store:cost_formula_discount,
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
					id:'discountFlagB103_2',
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
						params:{str:"COST_FORMULA_DISCOUNT,DISCOUNT_FLAG"}
					})
				
				},{
					xtype:'numberfield',   
					fieldLabel:$i18n.value1,  //值1
					id:'value1B103_2',
					minValue:0,
			     	allowBlank : true
				},{
					xtype:'numberfield',   
					fieldLabel:$i18n.value2,  //值2
					id:'value2B103_2',
					minValue:0,
			     	allowBlank : true
				},{
					xtype:'textareafield',   
					fieldLabel:$i18n.memo,   //备注
					id:'remarkB103_2',
					colspan:2,
					width:560,
			        allowBlank : true
				}]
		    }]
			},{

		    	title:$i18n.articleFamilyNo1,//商品群组
		    	frame : true,
		    	id:'tabB103_3',
		    	itemId:'tabB103_3i',
//		    	layout:'fit',
		    	layout:'border',
		    	extend:'Ext.panel.Panel',
		        items:[
				 {

						xtype:'grid',
						id:'grid_02_B103',
						title:$i18n.articleFamilyList,//商品群组列表
						width:'48%',
						region:'west',
						store:cost_FormulaArticlefamily,
						multiSelect: true,  
					    selModel: {  
				        	selType:'checkboxmodel'  
					    },
						columns:[{			
							xtype : 'rownumberer',
							width : 30
						},{
							width : 100,
							text : $i18n.articleFamilyNo,//商品群组编码
							dataIndex:'familyNo'
					    },{
							width : 160,
							text : $i18n.articleFamilyName,//商品群组名称
							dataIndex:'familyName'
					    }],
						dockedItems : [{
							xtype : 'pagingtoolbar',
							dock : 'bottom',
							store:cost_FormulaArticlefamily,
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
							id:'rightB103'
						},
						{
							xtype:'button',			
							text:'<<',
							id:'leftB103'
						}]
					},{

					    xtype:'grid',
					    id:'grid_03_B103',
					    title:$i18n.includeArticleFamilyList,//包含商品群组列表
					    width:'48%',
					    region:'east',
					    store:cost_FormulaArticlefamilyList,
					    multiSelect: true,  
					    selModel: {  
					        selType:'checkboxmodel'  
					    },
					    columns:[{			
							xtype : 'rownumberer',
							width : 30
					    },{
							width:100,
							text:$i18n.billingProjectNo,//项目编码
							dataIndex:'billingProject'
					    },{
							width : 100,
							text : $i18n.articleFamilyNo,//商品群组编码
							dataIndex:'familyNo'
					    },{
							width : 160,
							text : $i18n.articleFamilyName,//商品群组名称
							dataIndex:'familyName'
					    }],
					    dockedItems : [{
							xtype : 'pagingtoolbar',
							dock : 'bottom',
							store:cost_FormulaArticlefamilyList,
							displayInfo : true
					    }]
					
				 }]
		
			}]
		},
		{
			region:'south',
			xtype:'commMenuWidget5',
			border:0,
     		id:'cost_FormulasetB103'
	   	}
    ]
});