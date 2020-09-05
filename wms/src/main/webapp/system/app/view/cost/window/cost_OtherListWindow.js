/**
 * 模块名称：已出账账单查询-对账明细（已出账杂项费用明细）
 * 模块编码：B503
 * hcx
 */
Ext.define('cms.view.cost.window.cost_OtherListWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.cost_OtherListWindow',
	id:'cost_OtherListWindow',
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
  			    id:'refreshB503_3'
  		    },
  		    {
  				text : $i18n.export1,//导出
  				iconCls : 'export',
  				id:'exportB503_3'
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
				id : 'ownerNoB503_3',	
				hidden:true,
				readOnly:true
		    },{
				xtype : 'textfield',
				fieldLabel :$i18n.check_no,//对账单号
				id : 'checkNoB503_3',	
				hidden:true,
				readOnly:true
		    },{
     			fieldLabel :$i18n.cost_no, //费用代码
     			id:'costNoB503_3',
 				xtype:'wms_DefFieldValCombo',
 				forceSelection : false,
 				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
					 proxy:{
	   					type:'ajax',
	    				method:'post',
	     				url:'cost_OtherAction_getCostNoForUI',
	     				reader:{
	   						root:'rootList',
	   						totalProperty:'totalCount'
  						}
   					}
   			    })
     		}]
		}]

	},{
		  region:'center',
    	  xtype:'grid',
    	  id:'gridCostOtherListB503',
    	  store:Ext.create('cms.store.cost.cost_OtherListStore',{
    		  /*listeners:{ 	 
    				'load':function(th,records,successful,eOpts ){
    					var arrayObj = new Array();
    					arrayObj[0]='area';
    					arrayObj[1]='tray';
    					arrayObj[2]='qty2';
    					arrayObj[3]='volume';
    					arrayObj[4]='weigth';
    					arrayObj[5]='amount';
    					arrayObj[6]='otherCost1';
    					arrayObj[7]='otherCost2';
    					arrayObj[8]='otherCost3';
    					arrayObj[9]='otherCost4';
    					arrayObj[10]='otherCost5';
    					arrayObj[11]='favourableAmount';
    					arrayObj[12]='total';
    					countList2('gridCostListB503',arrayObj,'billingProject');
    				}
    			}*/
    	  }),
    	  width:'100%',
    	  height:'100%',
    	  columns:[
		 		    {			
						xtype : 'rownumberer',
						width : 30
				    }/*,{
						width : 100,
						text : $i18n.warehouse,//仓别
						dataIndex:'warehouseNo'
				    },{
				    	width: 150,
			  		    text : $i18n.owner_no,  //货主编号
			  		    dataIndex:'ownerNoText'		
				    }*/,{
				    	width: 150,
				  		text : $i18n.cost_other,  //杂项费用
				  		dataIndex:'costNoText'		
					},{
				    	width: 110,
				  		text : $i18n.cost_value,  //费用值
				  		dataIndex:'costValue'		
					},{
				    	width: 90,
				  		text : $i18n.cost_date,  //费用日期
				  		dataIndex:'costDateText'		
					},{
				    	width: 80,
				  		text : $i18n.amountFlag,  //费用标识
				  		dataIndex:'costFlagText'		
					},{
		     	    	width:150,
						text:$i18n.source_plan_no,  //来源单号
						dataIndex:'sourceNo'	
					},{
		     	    	width:150,
						text:$i18n.check_no,  //对账单号
						dataIndex:'checkNo'	
					},{
				    	width: 200,
				  		text : $i18n.remark,  //备注
				  		dataIndex:'remark_c'		
					}]
			}]
});
