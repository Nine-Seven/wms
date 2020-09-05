Ext.define('cms.view.bset.window.bill_Base_AmountAddOrEditWindow',{
 	extend:'Ext.window.Window',
 	alias:'widget.bill_Base_AmountAddOrEditWindow',
 	layout:'border',
    width : 630,
	height : 230,
 	modal:true,
 	id:'bill_Base_AmountAddOrEditWindow',
 	items:[{
 	    xtype:'form',
 	    region:'center',
 	    id:'bill_Base_AmountAddOrEditForm',
 	  	frame : true,
		layout : 'column',
		defaults : {
 	    	xtype : 'textfield',
 	    	margin : '5 5 5 5',
			labelWidth :100,
			labelAlign:'right'	
 	    },
    items:[
       {
	        xtype : 'textfield',
	        fieldLabel : $i18n.serialno,//流水号
	        id : 'serial_noB102',	
	        readOnly:true,
	        beforeLabelTextTpl : required
        },
    	{
          	xtype:'bdef_DefOwnerCombo',
			fieldLabel:$i18n.owner,//委托业主
 	    	id:'owner_noB102',
 	    	allowBlank:false,
 	    	beforeLabelTextTpl : required,
 	    	store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',
 	    			{
 	    		      proxy:{
 					         type:'ajax',
 					         method:'post',
 					         url:'bill_BaseAmountAction_getOwnerNoForWindow',
						     reader:{
 						     root:'rootList',
 						     totalProperty:'totalCount'
					     }
					   },
 	    		       listeners:{  
					         'load':function(th,records,successful,eOpts ){
						     if(th.count()>0){
							     Ext.getCmp('owner_noB102').setValue(th.getAt(0).data.value);
						     }
					      }
				       }
 	    			}
 	    			).load(),
 	    	readOnly:false
		},{
			fieldLabel :$i18n.billingProject, //计费项目
 			id:'billingProjectB102',
				xtype:'wms_DefFieldValCombo',
				forceSelection : false,
				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				 proxy:{
   					type:'ajax',
    				method:'post',
     				url:'bill_BaseAmountAction_getBillingProjectForWindow',
     				reader:{
   						root:'rootList',
   						totalProperty:'totalCount'
						}
					}
			    }).load(),
			    displayField : 'dropValue',
			    valueField : 'value',
				beforeLabelTextTpl : required

		},{

			xtype : 'datefield',
			fieldLabel : $i18n.amountDate,//结算日期
			id : 'amount_DateB102',								
			format : 'Y-m-d',
			beforeLabelTextTpl : required	
		},{
	    	xtype:'textfield',   
		    fieldLabel:$i18n.fixedValue,//固定值
		    id:'fixedValueB102',
		   	readOnly:true
		},{
			xtype:'numberfield',   
			fieldLabel:$i18n.unitPrice,  //默认单价
			id:'unitPriceB102',
			allowBlank : false, 
			minValue:0
		},{
		        xtype:'wms_DefFieldValCombo',
   		        fieldLabel:$i18n.billingCycle,  //计费周期
   		        id:'billingCycleB102',
   		        store:Ext.create("cms.store.common.comboStore").load(
				{
					params:{str:"BILL_FORMULASET,BILLING_CYCLE"}
				}),
				allowBlank : false,
			   	readOnly:true
		},{
	    	xtype:'textfield',   
		    fieldLabel:$i18n.accountValue,//消费值
		    minValue:0,
		    id:'valueB102',
			beforeLabelTextTpl : required	
		},{
			xtype:'wms_DefFieldValCombo',
			   fieldLabel:$i18n.amountFlag,  //费用标识
			   id:'flagB102',
			   store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"BILL_BASE_AMOUNT,FLAG"}
			   	}),
			   	allowBlank : false,
			   	readOnly:true,
				beforeLabelTextTpl : required	
		}]
 	},{
 			region:'south',
			xtype:'commMenuWidget5',
			border:0,
			id:'menuWidgetB102'
 	}]
});