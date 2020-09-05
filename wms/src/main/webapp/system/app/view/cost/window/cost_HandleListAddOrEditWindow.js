Ext.define('cms.view.cost.window.cost_HandleListAddOrEditWindow',{
 	extend:'Ext.window.Window',
 	alias:'widget.cost_HandleListAddOrEditWindow',
 	layout:'border',
    width : 630,
	height : 230,
 	modal:true,
 	id:'cost_HandleListAddOrEditWindow',
 	items:[{
 	    xtype:'form',
 	    region:'center',
 	    id:'cost_HandleListAddOrEditForm',
 	  	frame : true,
 	  	layout: 
        {
        	type: 'table',
        	columns: 2
        },
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
	        id : 'serial_noB905',	
	        readOnly:true,
	        beforeLabelTextTpl : required
        },
    	{
          	xtype:'bdef_DefOwnerCombo',
			fieldLabel:$i18n.owner,//委托业主
 	    	id:'owner_noB905',
 	    	allowBlank:false,
 	    	beforeLabelTextTpl : required,
 	    	store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',
 	    			{
 	    		      proxy:{
 					         type:'ajax',
 					         method:'post',
 					         url:'cost_HandleListAction_getOwnerNoForWindow',
						     reader:{
 						     root:'rootList',
 						     totalProperty:'totalCount'
					     }
					   },
 	    		       listeners:{  
					         'load':function(th,records,successful,eOpts ){
						     if(th.count()>0){
							     Ext.getCmp('owner_noB905').setValue(th.getAt(0).data.value);
						     }
					      }
				       }
 	    			}
 	    			).load(),
 	    	readOnly:false
		},{
			fieldLabel :$i18n.billingProject, //计费项目
 			id:'billingProjectB905',
			xtype:'wms_DefFieldValCombo',
			forceSelection : false,
			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
			 proxy:{
				type:'ajax',
				method:'post',
 				url:'cost_HandleListAction_getBillingProjectForWindow',
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
			id : 'amount_DateB905',								
			format : 'Y-m-d',
			beforeLabelTextTpl : required	
		},{
			xtype:'numberfield',   
			fieldLabel:$i18n.unitPrice,  //默认单价
			id:'unitPriceB905',
			allowBlank : false, 
			readOnly:true,
			minValue:0
		},{
	    	xtype:'textfield',   
		    fieldLabel:$i18n.accountValue,//消费值
		    minValue:0,
		    id:'valueB905',
			beforeLabelTextTpl : required	
		},{
			xtype:'wms_DefFieldValCombo',
		    fieldLabel:$i18n.amountFlag,  //费用标识
		    id:'costFlagB905',
		    store:Ext.create("cms.store.common.comboStore").load(
			 {
					params:{str:"COST_OTHER_LIST,COST_FLAG"}
		   	 }),
		     allowBlank : false,
		   	 readOnly:true
		},{
	 		 fieldLabel:$i18n.source_plan_no, //来源单号
	 		 id:'sourceNoB905',
	 	     xtype:'textfield'
	 	},{
		     xtype:'wms_DefFieldValCombo',
		     fieldLabel:$i18n.status,  //状态
		     id:'statusB905',
		     store:Ext.create("cms.store.common.comboStore").load(
			  {
					params:{str:"COST_EXPENSES_LIST,STATUS"}
		     }),
		     allowBlank : false,
		   	 readOnly:true,
			 beforeLabelTextTpl : required	
		}]
 	},{
 			region:'south',
			xtype:'commMenuWidget5',
			border:0,
			id:'menuWidgetB905'
 	}]
});