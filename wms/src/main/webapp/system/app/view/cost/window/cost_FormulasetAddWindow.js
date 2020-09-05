/**
 * 模块名称：计费公式管理 window
 * 模块编码：B103
 * 创建：hcx
 */
var cost_FormulaDiscountStore=Ext.create('cms.store.cost.cost_FormulasetStore',
	{autoLoad:true,
	 proxy: {
        type: 'ajax',
        method: 'post',
        url: 'cost_FormulasetAction_getFormulasetForWindList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    }
});

Ext.define('cms.view.cost.window.cost_FormulasetAddWindow',{
	extend:'Ext.window.Window',
	alias:'widget.cost_FormulasetAddWindow',
	layout:'border',
	id:'cost_FormulasetAddWindow',
	height:536,
	width:1305,
	modal:true,
	items:[
	    {
	        xtype : 'toolbar',
	   	    region:'north',
	   	    items : [{
	   			    text : $i18n.refresh,//刷新
	   			    iconCls : 'refresh',
	   			    id:'refreshB103_1'
	   		    }]	
	    },
	   	{
			xtype : 'form',
			id : 'IdFormB103',
			region : 'north',
			width:'100%',
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
            	id:'ownerNoB103_1',
            	store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
            	displayField : 'dropValue',
            	valueField : 'value',
            	allowBlank : false,
            	beforeLabelTextTpl : required
     		}]
		},
		{
			xtype : 'panel',
			id:'tabB103_1',
	    	frame : true,
		    region:'center',
		    items : [
		    {
         	    xtype:'grid',
         	    id:'formulasetWindB103_1',
         	    store:cost_FormulaDiscountStore,
         	    selModel : {
		        	selType : 'cellmodel'
		        },
		        plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
	        	    clicksToEdit : 1,
		            onSpecialKey:function(ed,field,e){
					commEnterGridStatEdit(this.grid,true,'cms.controller.cost.cost_FormulasetController',e.getKey());
				}
		    })],
         	    width:'100%',
         	    height:400,
         	    columns:[{			
         	        xtype : 'rownumberer',
         		    width : 30
         	    },{
         		    width:60,
         		    text : $i18n.select,  //选择
         		    dataIndex:'choiceFlag',
         		    field:{
          		       xtype:'wms_DefFieldValCombo',
      	   			   id:'choiceFlagB103_1',
      	   			   store:Ext.create("cms.store.common.comboStore").load(
      					{
      							params:{str:"N,YESORNO"}
      				   	}),
      				   	allowBlank : false
 					},
   					renderer: function(value,metadata,record){
   						if(!Ext.isEmpty(value)){
//   							var a=[];
//   	  				    	Ext.Ajax.request({
//   	  				  		url : 'bdef_ArticleGroupAction_getWmsDeffieldvalCombo',
//   		    				params:{str:"N,YESORNO"},
//   	    					async : false,
//   		    				success:function(response){
//   		    					   a = Ext.decode(response.responseText);
//   		      				    }
//   		    				});
//   	  				    	for(var i=0;i<a.length;i++){
//   	  						   if(value==a[i].value){
//   	  							   return a[i].dropValue;
//   	  						   }
//   	  					     }
   							if(value=='0')
    	   	            	{
    	   	            		return '否';
    	   	            	}else if(value=='1')
    	   	            	{
    	   	            		return '是';
    	   	            	}
   						}
   	     		   }			
         	    },{
         		    width:90,
         		    text : $i18n.billingType1,  //计费类型
         		    dataIndex:'billingTypeText'			
         	    },{
         		    width:90,
         		    text : $i18n.billingNo,  //项目编码
         		    cls:'notnull',
         		    dataIndex:'billingProject',
         		    field:{
						xtype:'textfield',
						id:'billingNoB103_1'
					}	
         	    },{
         		    width:80,
         		    text : $i18n.billingName,  //项目名称
         		    cls:'notnull',
         		    dataIndex:'projectName',
         		    field:{
						xtype:'textfield',
					}	
         	    },{
         		    width:80,
         		    text : $i18n.billingFlag,  //计费方式
         		    cls:'notnull',
         		    dataIndex:'billingFlagText',
         		    field:{
          		       xtype:'wms_DefFieldValCombo',
      	   			   id:'billingFlagB103_1',
      	   			   store:Ext.create("cms.store.common.comboStore").load(
      					{
      							params:{str:"COST_FORMULASET,BILLING_FLAG"}
      				   	}),
      				   	allowBlank : false
 					},
   					renderer: function(value,metadata,record){
   						if(!Ext.isEmpty(value)){
//   							var a=[];
//   	  				    	Ext.Ajax.request({
//   	  				  		url : 'bdef_ArticleGroupAction_getWmsDeffieldvalCombo',
//   		    				params:{str:"COST_FORMULASET,BILLING_FLAG"},
//   	    					async : false,
//   		    				success:function(response){
//   		    					   a = Ext.decode(response.responseText);
//   		      				    }
//   		    				});
//   	  				    	for(var i=0;i<a.length;i++){
//   	  						   if(value==a[i].value){
//   	  							   return a[i].dropValue;
//   	  						   }
//   	  					     }
   							if(value=='1')
    	   	            	{
    	   	            		return '固定费用';
    	   	            	}else if(value=='2')
    	   	            	{
    	   	            		return '动态费用';
    	   	            	}
   						}
   	     		   }			
         	    },{
         		    width:80,
         		    text : $i18n.billingUnit,  //计费单位
         		    cls:'notnull',
         		    dataIndex:'billingUnitText'	,
         		    field:{
           		       xtype:'wms_DefFieldValCombo',
       	   			   id:'billingUnitB103_1',
       	   			   store:Ext.create("cms.store.common.comboStore").load(
       					{
       							params:{str:"COST_FORMULASET,BILLING_UNIT"}
       				   	}),
       				   	allowBlank : false
  					},
   					renderer: function(value,metadata,record){
   						if(!Ext.isEmpty(value)){
   							var a=[];
   	  				    	Ext.Ajax.request({
   	  				  		url : 'bdef_ArticleGroupAction_getWmsDeffieldvalCombo',
   		    				params:{str:"COST_FORMULASET,BILLING_UNIT"},
   	    					async : false,
   		    				success:function(response){
   		    					   a = Ext.decode(response.responseText);
   		      				    }
   		    				});
   	  				    	for(var i=0;i<a.length;i++){
   	  						   if(value==a[i].value){
   	  							   return a[i].dropValue;
   	  						   }
   	  					     }
   						}
   	     		   }		
         	    },{
         		    width:80,
         		    text : $i18n.valueFlag,  //取值方式
         		    cls:'notnull',
         		    dataIndex:'valueFlagText',
         		    field:{
           		       xtype:'wms_DefFieldValCombo',
       	   			   id:'valueFlagB103_1',
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
			   		    }),
       				   	allowBlank : false
  					},
   					renderer: function(value,metadata,record){
   						if(!Ext.isEmpty(value)){
   							var params=
   							{
   									billingType:record.get('billingType'),
   									billingUnit:record.get('billingUnitText')
   							};
   							var a=[];
   	  				    	Ext.Ajax.request({
			   				url:'cost_FormulasetAction_getValueFlagCombo',
   		    				params:params,
   	    					async : false,
   		    				success:function(response){
   		    					   a = Ext.decode(response.responseText);
   		      				    }
   		    				});
   	  				    	for(var i=0;i<a.length;i++){
   	  						   if(value==a[i].value){
   	  							   return a[i].dropValue;
   	  						   }
   	  					     }
   						}
   	     		   }						
         	    },{
         		    width:80,
         		    text : $i18n.fixedValue,  //固定值
         		    dataIndex:'fixedValue',
         		    field:{
						xtype:'numberfield'
					}
         	    },{
         		    width:60,
         		    text : $i18n.unitPrice,  //默认单价
         		    cls:'notnull',
         		    dataIndex:'unitPrice',
         		    field:{
						xtype:'numberfield'
					}
         	    },{
         		    width:60,
         		    text : $i18n.billingCycle,  //计费周期
         		    cls:'notnull',
         		    dataIndex:'billingCycleText',
         		    field:{
            		       xtype:'wms_DefFieldValCombo',
        	   			   id:'billingCycleB103_1',
        	   			   store:Ext.create("cms.store.common.comboStore").load(
        					{
        							params:{str:"COST_FORMULASET,BILLING_CYCLE"}
        				   	}),
        				   	allowBlank : false
   					},
   					renderer: function(value,metadata,record){
   						if(!Ext.isEmpty(value)){
//   							var a=[];
//   	  				    	Ext.Ajax.request({
//   	  				  		url : 'bdef_ArticleGroupAction_getWmsDeffieldvalCombo',
//   		    				params:{str:"COST_FORMULASET,BILLING_CYCLE"},
//   	    					async : false,
//   		    				success:function(response){
//   		    					   a = Ext.decode(response.responseText);
//   		      				    }
//   		    				});
//   	  				    	for(var i=0;i<a.length;i++){
//   	  						   if(value==a[i].value){
//   	  							   return a[i].dropValue;
//   	  						   }
//   	  					     }
   							if(value=='1')
   	   	            	   {
   	   	            		   return '天';
   	   	            	   }else if(value=='2')
   	   	            	   {
   	   	            		   return '周';
   	   	            	   }else if(value=='3')
   	   	            	   {
   	   	            		   return '月';
   	   	            	   }
   						}
   	     		   }	
         	    },{
         		    width:70,
         		    text : '周结算日期',  //结算日期
         		    dataIndex:'weekBalanceDay',
         		    field:{
         		       xtype:'wms_DefFieldValCombo',
     	   			   id:'balanceDayTextB103_1',
     	   			   store:Ext.create("cms.store.common.comboStore").load(
     					{
     							params:{str:"COST_FORMULASET,BALANCE_DAY"}
     				   	}),
     				   	allowBlank : false
					},
					renderer: function(value,metadata,record){
						if(!Ext.isEmpty(value)){
							var a=[];
	  				    	Ext.Ajax.request({
	  				  		url : 'bdef_ArticleGroupAction_getWmsDeffieldvalCombo',
		    				params:{str:"COST_FORMULASET,BALANCE_DAY"},
	    					async : false,
		    				success:function(response){
		    					   a = Ext.decode(response.responseText);
		      				    }
		    				});
	  				    	for(var i=0;i<a.length;i++){
	  						   if(value==a[i].value){
	  							   return a[i].dropValue;
	  						   }
	  					     }
						}
	     		   }				
         	    },{
         		    width:70,
         		    text : '月结算日期',  //结算日期
         		    dataIndex:'monthBalanceDay',
         		    field:{
						xtype:'textfield',
					}	
         	    },{
         		    width:90,
         		    text : $i18n.beginDate,  //起始日期
         		    dataIndex:'beginDateText'	,
         		    field: {
    		             xtype: 'datefield',
    		             format : 'Y-m-d'
    		        },	                
    		        renderer:function(value){   
    					 if(value instanceof Date){   				 
    					     return Ext.Date.format(value,'Y-m-d');   
    					 }else{				        
    					     return value;   
    				     }  
    				}
         	    },{
         		    width:90,
         		    text : $i18n.endDay,  //截止日期
         		    dataIndex:'endDateText'	,
         		    field: {
    		             xtype: 'datefield',
    		             format : 'Y-m-d'
    		        },	                
    		        renderer:function(value){   
    					 if(value instanceof Date){   				 
    					     return Ext.Date.format(value,'Y-m-d');   
    					 }else{				        
    					     return value;   
    				     }  
    				}
         	    },{
         		    width:100,
         		    text : $i18n.standard_flag,  //是否为标准策略
         		    cls:'notnull',
         		    dataIndex:'standardFlagText',
         		    field:{
          		       xtype:'wms_DefFieldValCombo',
      	   			   id:'standardFlagB103_1',
      	   			   store:Ext.create("cms.store.common.comboStore").load(
      					{
      							params:{str:"COST_FORMULASET,STANDARD_FLAG"}
      				   	}),
      				   	allowBlank : false
 					},
  				    renderer: function(value,metadata,record){  
  				    	if(!Ext.isEmpty(value)){
//  				    		var a=[];
//  	  				    	Ext.Ajax.request({
//  	  				  		url : 'bdef_ArticleGroupAction_getWmsDeffieldvalCombo',
//  		    				params:{str:"COST_FORMULASET,STANDARD_FLAG"},
//  	    					async : false,
//  		    				success:function(response){
//  		    					   a = Ext.decode(response.responseText);
//  		      				    }
//  		    				});
//  	  				    	for(var i=0;i<a.length;i++){
//  	  						   if(value==a[i].value){
//  	  							   return a[i].dropValue;
//  	  						   }
//  	  					     }
  	   	            	   if(value=='0')
  	   	            	   {
  	   	            		   return '不标准策略';
  	   	            	   }else if(value=='1')
  	   	            	   {
  	   	            		   return '标准策略';
  	   	            	   }
  				    	}
   	     		   }			
         	    },{
         		    width:80,
         		    text : $i18n.amountFlag,  //费用标识 
         		    cls:'notnull',
         		    dataIndex:'costFlagText',
         		    field:{
         		       xtype:'wms_DefFieldValCombo',
     	   			   id:'costFlagB103_1',
     	   			   store:Ext.create("cms.store.common.comboStore").load(
     					{
     							params:{str:"COST_OTHER_LIST,COST_FLAG"}
     				   	}),
     				   	allowBlank : false
					},
  				    renderer: function(value,metadata,record){
  				    	if(!Ext.isEmpty(value)){
//  				    		var a=[];
//  	  				    	Ext.Ajax.request({
//  	  				  		url : 'bdef_ArticleGroupAction_getWmsDeffieldvalCombo',
//  		    				params:{str:"COST_OTHER_LIST,COST_FLAG"},
//  	    					async : false,
//  		    				success:function(response){
//  		    					   a = Ext.decode(response.responseText);
//  		      				    }
//  		    				});
//  	  				    	for(var i=0;i<a.length;i++){
//  	  						   if(value==a[i].value){
//  	  							   return a[i].dropValue;
//  	  						   }
//  	  					     }
  				    		if(value=='0')
   	   	            	   {
   	   	            		   return '应收';
   	   	            	   }else if(value=='1')
   	   	            	   {
   	   	            		   return '应付';
   	   	            	   }
  				    	}
   	     		   }	
         	    },{
         		    width:80,
         		    text : $i18n.manage_status,  //状态
         		    cls:'notnull',
         		    dataIndex:'statusText',
         		    field:{
         		       xtype:'wms_DefFieldValCombo',
     	   			   id:'statusB103_1',
     	   			   store:Ext.create("cms.store.common.comboStore").load(
     					{
     							params:{str:"COST_FORMULASET,STATUS"}
     				   	}),
     				   	allowBlank : false
					},
  				    renderer: function(value,metadata,record){
  				    	if(!Ext.isEmpty(value)){
//  				    		var a=[];
//  	  				    	Ext.Ajax.request({
//  	  				  		url : 'bdef_ArticleGroupAction_getWmsDeffieldvalCombo',
//  		    				params:{str:"COST_FORMULASET,STATUS"},
//  	    					async : false,
//  		    				success:function(response){
//  		    					   a = Ext.decode(response.responseText);
//  		      				    }
//  		    				});
//  	  				    	for(var i=0;i<a.length;i++){
//  	  						   if(value==a[i].value){
//  	  							   return a[i].dropValue;
//  	  						   }
//  	  					     }
  				    		if(value=='0')
   	   	            	   {
   	   	            		   return '启用';
   	   	            	   }else if(value=='1')
   	   	            	   {
   	   	            		   return '停用';
   	   	            	   }
  				    	}
   	     		   }	
         	    },{
         		    width:80,
         		    text : $i18n.remark,  //备注
         		    dataIndex:'remark',
         		    field:{
						xtype:'textfield'
					}	
         	    },{
         	    	width:100,
					text:$i18n.other_cost1,  //其他费用1
					dataIndex:'otherCost1',
					field:{
						xtype:'numberfield'
					}
				},{
					width:100,
					text:$i18n.other_cost2,  //其他费用2
					dataIndex:'otherCost2',
					field:{
						xtype:'numberfield'
					}	
				},{
					width:100,  
					text:$i18n.other_cost3,  //其他费用3
					dataIndex:'otherCost3'	,
					field:{
						xtype:'numberfield'
					}
				},{
					width:100,
					text:$i18n.other_cost4,  //其他费用4
					dataIndex:'otherCost4',
					field:{
						xtype:'numberfield'
					}	
				},{
					width:100,  
					text:$i18n.other_cost5,  //其他费用5
					dataIndex:'otherCost5',
					field:{
						xtype:'numberfield'
					}	
				}]
         	}]
		},
		{
			region:'south',
			xtype:'commMenuWidget5',
			border:0,
     		id:'cost_FormulasetB103_1'
	   	}
    ]
});