/**
 * 模块名称：科目和计费项目的关系维护 window
 * 模块编码：B301
 * 创建：chensr
 */
var bill_Account_store=Ext.create('cms.store.bset.bill_Account_Store',{autoLoad:false,
	listeners:{
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('grid_B301_d3').getStore().count()>0){
				Ext.getCmp('grid_B301_d3').getSelectionModel().select(0);
//				Ext.getCmp('grid_B301_d3').fireEvent('itemclick','');
			}
		}
	}
});

Ext.define('cms.view.bset.window.bill_AccountAddOrEditWindow',{
	extend:'Ext.window.Window',
	alias:'widget.bill_AccountAddOrEditWindow',
	layout:'border',
	id:'bill_AccountAddOrEditWindow',
	width : 650,
	height : 525,
	modal:true,
	items : [
	{
		xtype : 'form',
		id : 'IdFormB301',
		region : 'north',
		height:70,
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
        	id:'ownerNoB301',
        	store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
 	    displayField : 'dropValue',
 	    valueField : 'value',
 	    allowBlank : false,
 	    beforeLabelTextTpl : required
 		},{
 			fieldLabel:$i18n.account_type, //科目类型
 			id:'accountTypeB301',
 			xtype:'bdef_DefOwnerCombo',
 			store:Ext.create("cms.store.common.comboStore").load(
			{
				params:{str:"BILL_ACCOUNT_M,ACCOUNT_TYPE"}
			}),
			allowBlank : false,
			beforeLabelTextTpl : required
 		},{
 			fieldLabel:$i18n.account_no, //科目代码
 			id:'accountNoB301',
 			xtype:'textfield',
 			maxLength:5,		
     	    allowBlank : false,
   		    beforeLabelTextTpl : required
 		},{

 			fieldLabel:$i18n.account_name, //科目名称
 			id:'accountNameB301',
 			xtype:'textfield',		
     	    allowBlank : false,
   		    beforeLabelTextTpl : required
 		}]
	},
	{
		xtype : 'tabpanel',
		id:'tabB301',
	    region:'center',
	    items : [
	    {
	    	title:$i18n.basic_account,//基本优惠
	    	frame : true,
	    	id:'tabB301_1',
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
	   	   		   margin:'5 4 1 4',
	   	   		   labelAlign:'right',
	   	   		   labelWidth:120
	   	       },
	   		   items:[
	   		   {
					xtype:'wms_DefFieldValCombo',   
					fieldLabel:$i18n.discount_flag,   //优惠方式
					id:'discountFlagB301',
					colspan:2,
					width:560,
					store:Ext.create("cms.store.common.comboStore").load(
					{
						params:{str:"BILL_ACCOUNT_M,DISCOUNT_FLAG"}
					})
				},{
					xtype:'numberfield',   
					fieldLabel:$i18n.value1,  //值1
					id:'value1B301',
					minValue:0,
			     	allowBlank : true
				},{
					xtype:'numberfield',   
					fieldLabel:$i18n.value2,  //值2
					id:'value2B301',
					minValue:0,
			     	allowBlank : true
				},{
					xtype:'wms_DefFieldValCombo',   
					fieldLabel:$i18n.discountAccountNo,   //优惠计费项目
					id:'discountAccountNoB301',
					colspan:2,
					store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				 	    proxy:{
								type:'ajax',
								method:'post',
								url:'bill_AccountAction_getDiscountAccountNoComboList',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								}
							}
					 }).load(),
					 displayField : 'dropValue',
					 valueField : 'value',
					 allowBlank : true
				},{
					xtype:'textareafield',   
					fieldLabel:$i18n.memo,   //备注
					id:'remarkB301',
					colspan:2,
					width:560,
			        allowBlank : true
				}]
		    }]
	},
	{
		title:$i18n.ladder_account,//阶梯优惠
    	id:'tabB301_2',
		layout:'border',
		items:[
		{
	        xtype:'grid',
		    id:'grid_B301_d3',
		    region:'north',
		    height:180,
		    store:bill_Account_store,
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
		  		    dataIndex:'discountAccountNo'		
			},{
		    	 width: 100,
		  		    text : $i18n.remark,  //备注
		  		    dataIndex:'remark'		
			}
		    ]
//		,
//		    dockedItems : [
//		    {
//		        xtype : 'pagingtoolbar',
//		        dock : 'bottom',
//			    store:bill_Account_store,
//			    displayInfo : true
//		    }
//		    ]
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
				id:'discountFlagB301_2',
				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
					 proxy:{
 					type:'ajax',
 					method:'post',
 					url:'bill_AccountAction_getDiscountFlagList',
 					reader:{
						root:'rootList',
 						totalProperty:'totalCount'
						}
					}
			    }).load(),
			    displayField : 'dropValue',
			    valueField : 'value'
				
//				store:Ext.create("cms.store.common.comboStore").load(
//				{
//					params:{str:"BILL_ACCOUNT,DISCOUNT_FLAG"}
//				})
			
			},{
				xtype:'numberfield',   
				fieldLabel:$i18n.value1,  //值1
				id:'value1B301_2',
				minValue:0,
		     	allowBlank : true
			},{
				xtype:'numberfield',   
				fieldLabel:$i18n.value2,  //值2
				id:'value2B301_2',
				minValue:0,
		     	allowBlank : true
			},{
				xtype:'wms_DefFieldValCombo',   
				fieldLabel:$i18n.discountAccountNo,   //优惠计费项目
				id:'discountAccountNoB301_2',
				colspan:2,
				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
			 	    proxy:{
							type:'ajax',
							method:'post',
							url:'bill_AccountAction_getDiscountAccountNoComboList',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
						}
				 }).load(),
				 displayField : 'dropValue',
				 valueField : 'value',
				 allowBlank : true
			},{
				xtype:'textareafield',   
				fieldLabel:$i18n.memo,   //备注
				id:'remarkB301_2',
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
		id:'bill_AccountB301'
   	}
]
});