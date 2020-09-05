/**
 * 模块名称：计费公式管理 window
 * 模块编码：B101
 * 创建：chensr
 */
Ext.define('cms.view.bset.window.bill_FormulasetAddOrEditWindow',{
	extend:'Ext.window.Window',
	alias:'widget.bill_FormulasetAddOrEditWindow',
	layout:'border',
	id:'bill_FormulasetAddOrEditWindow',
	width:640,
	height:480,
	modal:true,
	items:[
		   	{
		   	    xtype:'form',
		   	    region:'center',
		   	    id:'IdFormB101',
		   	    frame:true,
		   	    items:[
		   	    {
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
		            items:[{
		            	xtype:'bdef_DefOwnerCombo',
		            	fieldLabel:$i18n.owner_no, //货主编号
		            	id:'ownerNoB101',
		            	store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
		            	displayField : 'dropValue',
		            	valueField : 'value',
		            	allowBlank : false,
		            	beforeLabelTextTpl : required
		     		},{
		    			xtype:'wms_DefFieldValCombo',
		     			fieldLabel:"项目类型",    //项目类型
		     			id:'billingTypeB101',	     					
		     			store:Ext.create("cms.store.common.comboStore").load(
						{
								params:{str:"BILL_FORMULASET,BILLING_TYPE"}
				   		}),
				   		allowBlank : false,
						beforeLabelTextTpl : required
		     		},{
		     			fieldLabel:"项目编号", //项目编号
		     			id:'billingProjectB101',
		     			xtype:'textfield',
		     			maxLength:8,		
			     	    allowBlank : false,
			   		    beforeLabelTextTpl : required
		     		},{
		   				xtype:'textfield',
		     			fieldLabel:"项目名称",    //项目名称
		     			id:'projectNameB101',	     					
		     					
			     		allowBlank : false,
			     		beforeLabelTextTpl : required
		     		},{
		     			xtype:'combo',
		     		 	   fieldLabel:$i18n.articleFamilyNo1,//商品群组
		     		 	   id:'articleFamilyNo',
		     		 	   displayField : 'dropValue',
		     			   valueField : 'value',
		     			   store:Ext.create("cms.store.common.comboStore",{
				   			  proxy:{
				   					 type:'ajax',
				   					 method:'post',
				   					 url:'bill_FormulasetAction_getarticleFamilyNoCombo',
				   					 reader:{
				   					   root:'rootList',
				   					   totalProperty:'totalCount'
				   					 }
				   			  }
				   		   }).load()

		     		},{
			   			   xtype:'wms_DefFieldValCombo',
			   			   fieldLabel:$i18n.manage_status,  //状态
			   			   id:'statusB101',
			   			   store:Ext.create("cms.store.common.comboStore").load(
							{
									params:{str:"BILL_FORMULASET,STATUS"}
						   	}),
						   	allowBlank : false,
							beforeLabelTextTpl : required
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
		   			   xtype:'wms_DefFieldValCombo',
		   			   fieldLabel:$i18n.billingFlag,  //计费方式
		   			   id:'billingFlagB101',
		   			 store:Ext.create("cms.store.common.comboStore").load(
					 {
							params:{str:"BILL_FORMULASET,BILLING_FLAG"}
					 }),
					 allowBlank : false,
					 beforeLabelTextTpl : required
		   		   },{
		   			   xtype:'wms_DefFieldValCombo',   
		   			   fieldLabel:$i18n.billingUnit,   //默认计费单位
		   			   id:'billingUnitB101',
		   			   store:Ext.create("cms.store.common.comboStore").load(
					   {
							params:{str:"BILL_FORMULASET,BILLING_UNIT"}
					   }),
					   allowBlank : false
		   		   },{   
	     			   xtype:'combo',
	     		 	   fieldLabel:$i18n.valueFlag,//租仓策略
	     		 	   id:'valueFlagB101',
	     		 	   displayField : 'dropValue',
	     			   valueField : 'value',
	     			   store:Ext.create("cms.store.common.comboStore",{
			   			  proxy:{
			   					 type:'ajax',
			   					 method:'post',
			   					 url:'bill_FormulasetAction_getValueFlagCombo',
			   					 reader:{
			   					   root:'rootList',
			   					   totalProperty:'totalCount'
			   					 }
			   			  }
			   		   }).load()
		   		   },{
				    	xtype:'textfield',   
					    fieldLabel:'固定值',
					    minValue:0,
					    id:'valueB101'
				    },{
						xtype:'numberfield',   
						fieldLabel:$i18n.unitPrice,  //默认单价
						id:'unitPriceB101',
						allowBlank : false, 
						minValue:0
					},{
		   		        xtype:'wms_DefFieldValCombo',
		   		        fieldLabel:$i18n.billingCycle,  //计费周期
		   		        id:'billingCycleB101',
		   		        store:Ext.create("cms.store.common.comboStore").load(
						{
							params:{str:"BILL_FORMULASET,BILLING_CYCLE"}
						}),
						allowBlank : false,
					    beforeLabelTextTpl : required
		   		   },{
		   			   xtype:'wms_DefFieldValCombo',   
		   			   fieldLabel:$i18n.amountDate,   //结算日期
		   			   id:'balanceDayB101_1',
		   			   store:Ext.create("cms.store.common.comboStore").load(
					   {
							params:{str:"BILL_FORMULASET,BALANCE_DAY"}
					   })
		   		   },{
		   				xtype:'textfield',
		     			fieldLabel:$i18n.amountDate,//结算日期
		     			id:'balanceDayB101_2'
		     		},{
					xtype : 'datefield',
					fieldLabel : $i18n.endDay,//截止日期
					id : 'endDateB101',								
					format : 'Y-m-d'
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
						xtype:'wms_DefFieldValCombo',   
						fieldLabel:$i18n.appendCondition,  //附加条件
						id:'appendConditionB101',
						 store:Ext.create("cms.store.common.comboStore").load(
						{
							params:{str:"BILL_FORMULASET,APPEND_CONDITION"}
						}),
						
						beforeLabelTextTpl : required,
					    colspan:2,
					    width:560,
					    allowBlank : true
					},{
						xtype:'numberfield',   
						fieldLabel : $i18n.value1,  //值1
						id:'appendValue1B101',
						minValue:0,
				     	allowBlank : true
					},{
						xtype:'numberfield',   
						fieldLabel : $i18n.value2,  //值2
						id:'appendValue2B101',
						minValue:0,
				     	allowBlank : true
					},{
						xtype:'textareafield',   
						fieldLabel:$i18n.remark,  //备注
						id:'remarkB101',
						colspan:2,
						width:560,
				     	allowBlank : true
					}]
			    }
		       ]},{
		     		region:'south',
		     		xtype:'commMenuWidget5',
		     		border:0,
		     		id:'bill_FormulasetB101'
		   	}]
		});