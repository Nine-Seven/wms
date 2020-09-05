/**
 * 模块名称：收款-对账明细
 * 模块编码：B703
 * hcx
 */
Ext.define('cms.view.cost.window.cost_FinancialWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.cost_FinancialWindow',
	id:'cost_FinancialWindow',
	layout:'border',
    height:500,
	width:1105,
	modal:true,
    items: [{

   	   xtype : 'toolbar',
  	   region:'north',
  	   items : [{
  			    text : $i18n.refresh,//刷新
  			    iconCls : 'refresh',
  			    id:'refreshB703'
  		    }]	
    },{
		xtype : 'form',
		region : 'north',
		width:'100%',
		frame : true,
		items : [ {
			layout : {
			type : 'table',
			columns : 6
			},
			xtype : 'container',
			defaults : {
				xtype : 'textfield',
				margin : '2 2 2 0',
				labelAlign : 'right'
			},
			items : [{
				xtype : 'textfield',
				fieldLabel : $i18n.owner_no,// 货主
				id : 'ownerNoB703_2',	
				hidden:true,
				readOnly:true
		    },{
				xtype : 'wms_DefFieldValCombo',
				fieldLabel : $i18n.account_no,// 科目
				id : 'accountNoB703_2',	
				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
					 proxy:{
					type:'ajax',
					method:'post',
					url:'cost_FinancialAction_getAccountNoForUI',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
						}
					}
			    }),
				hidden:false,
				readOnly:false
		    },{
				xtype : 'textfield',
				fieldLabel :$i18n.check_no,//对账单号
				id : 'checkNoB703_2',	
				hidden:true,
				readOnly:true
		    }]
		}]

	},{
		  region:'center',
    	  xtype:'grid',
    	  id:'gridFinanciaListB703',
    	  store:Ext.create('cms.store.cost.cost_FinancialStore',{
    		  listeners:{ 	 
    				'load':function(th,records,successful,eOpts ){
    					var arrayObj = new Array();
    					arrayObj[5]='amount';
    					arrayObj[6]='otherCost1';
    					arrayObj[7]='otherCost2';
    					arrayObj[8]='otherCost3';
    					arrayObj[9]='otherCost4';
    					arrayObj[10]='otherCost5';
    					arrayObj[11]='discountAmount';
    					arrayObj[12]='total';
    					countList2('gridFinanciaListB703',arrayObj,'accountNo');
    				}
    			}
    	  }),
    	  width:'100%',
    	  height:'100%',
    	  columns:[
    	   	    {			
    				xtype : 'rownumberer',
    				width : 30
    		    },/*,{
    				width : 80,
    				text : $i18n.warehouse,//仓别
    				dataIndex:'warehouseNo'
    		    },{
    		    	width: 150,
    	  		    text : $i18n.owner_no,  //货主编号
    	  		    dataIndex:'ownerNoText'		
    		    }*/{
	    	    	width: 130,
	      		    text :  $i18n.check_no,  //对账单号
	      		    dataIndex:'checkNo'		
	    		},{
	    	    	width: 180,
	      		    text :  $i18n.account_no,  //科目代码
	      		    dataIndex:'accountNoText'		
	    		},{
	    	    	width: 130,
	      		    text :  '应收/付标识',  //应收/付标识
	      		    dataIndex:'costFlagText'		
	    		},{
    				width: 100,
    	  		    text : $i18n.product_date,  //生成日期
    	  		    dataIndex:'buildDateText'		
    			},{
    				width: 100,
    	  		    text : $i18n.begin_date,  //开始日期
    	  		    dataIndex:'beginDate'		
    			},{
    				width: 100,
    	  		    text : $i18n.end_time,  //结束日期
    	  		    dataIndex:'endDate'		
    			},{
	    			width: 100,
	      		    text : $i18n.amount,  //金额（元）
	      		    dataIndex:'amount'		
	    		},{
	     	    	width:100,
	     	    	align:'right',
	    			text:$i18n.other_cost1,  //其他费用1
	    			dataIndex:'otherCost1'	
	    		},{
	    			width:100,
	    			align:'right',
	    			text:$i18n.other_cost2,  //其他费用2
	    			dataIndex:'otherCost2'	
	    		},{
	    			width:100,  
	    			align:'right',
	    			text:$i18n.other_cost3,  //其他费用3
	    			dataIndex:'otherCost3'	
	    		},{
	    			width:100,
	    			align:'right',
	    			text:$i18n.other_cost4,  //其他费用4
	    			dataIndex:'otherCost4'	
	    		},{
	    			width:100,
	    			align:'right',
	    			text:$i18n.other_cost5,  //其他费用5
	    			dataIndex:'otherCost5'	
	    		},{
	    			width:100,  
	    			align:'right',
	    			text:$i18n.discountAmount,  //优惠金额
	    			dataIndex:'discountAmount'	
	    		},{
	    			width:100,  
	    			align:'right',
	    			text:$i18n.total,  //合计
	    			dataIndex:'total'	
	    		}]
			}]
});
