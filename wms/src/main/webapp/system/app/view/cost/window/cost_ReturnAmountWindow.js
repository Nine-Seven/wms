/**
 * 模块名称：收款-回款
 * 模块编码：B703
 * hcx
 */
var cost_ReturnAmoutStore=Ext.create('cms.store.cost.cost_ReturnAmoutStore',{autoLoad:false});

Ext.define('cms.view.cost.window.cost_ReturnAmountWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.cost_ReturnAmountWindow',
	id:'cost_ReturnAmountWindow',
	layout:'border',
    height:280,
	width:800,
	modal:true,
    items: [{

        xtype:'grid',
	    id:'grid_return_amount',
	    region:'north',
	    height:160,
	    store:cost_ReturnAmoutStore,
	    columns:[{			
	        xtype : 'rownumberer',
		    width : 30
	    },
	    {
	    	 width: 180,
	  		 text : $i18n.check_no,  //对账单号
	  		 dataIndex:'checkNo'		
		},{
	    	width: 130,
  		    text :  '应收/付标识',  //应收/付标识
  		    dataIndex:'costFlagText'		
		},{
	    	 width: 150,
 			 align:'right',
 			 allowDecimals: true,
 			 decimalPrecision:3,
	  		 text : $i18n.returnAmount,  //回/付款金额（元）
	  		 dataIndex:'realAmount'		
		},{
	    	 width: 130,
	  		 text : $i18n.takeAccountName,  //收/付款人
	  		 dataIndex:'rgstName'		
		},{
	    	 width: 150,
	  		 text : $i18n.retrunAccountDate,  //回/付款时间
	  		 dataIndex:'rgstDate'		
		}]
    },{

	     xtype:'fieldset',
  	     layout: {
	       type: 'table',
	           columns: 4
	       },
	       defaults:{
   	       xtype:'textfield',
   		   margin:'1 1 1 1',
   		   labelAlign:'right',
   		   labelWidth:90
       },
	   items:[{
			xtype : 'textfield',
			fieldLabel : $i18n.owner_no,// 货主
			id : 'ownerNoB703_2',	
			hidden:true,
			readOnly:true
	    },{
		    xtype:'textfield',   
			fieldLabel:$i18n.check_no,   //对账单号
			id:'checkNo_2',
			readOnly:true,
			beforeLabelTextTpl : required
	   },{
		    xtype:'textfield',   
			fieldLabel:'应收/付标识',  //应收/付标识
			id:'costFlag_2',
			readOnly:true,
			hidden:true
	   },{
			xtype : 'numberfield',
			fieldLabel : $i18n.returnAmount,//回/付款金额（元）
			minValue:0,
			allowDecimals: true,
		    decimalPrecision: 3,
			id : 'realAmountB703',
			beforeLabelTextTpl : required
	  },{

			xtype : 'bdef_DefWorkerCombo',
			fieldLabel : $i18n.takeAccountName,// 收/付款人
			id : 'rgstNameB703',
			store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore'),
			beforeLabelTextTpl : required
	  },{
			xtype : 'numberfield',
			fieldLabel : $i18n.planAmount,//应收/付金额（元）
			minValue:0,
			id : 'planAmountB703',
			hidden:true
	  }]
      },{
 			region:'south',
			xtype:'commMenuWidget5',
			border:0,
			id:'cost_ReturnAmountB703'
 	  }]
});
