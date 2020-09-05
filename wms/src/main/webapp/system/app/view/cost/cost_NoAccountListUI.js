/**
 * 模块名称：未出账费用明细查询
 * 模块编码：B403
 * 创建：hcx 
 */
var cost_NoAccountListStore=Ext.create('cms.store.cost.cost_NoAccountListStore',{
	autoLoad:false,
	proxy: {
        type: 'ajax',
        method: 'post',
        url: 'cost_ManualAccountAction_getNoAccountList',
    	reader : {
    		type:'json',
    		root : 'rootList',
    		totalProperty : 'totalCount'
    	}
    },
    listeners:{ 	 
			'load':function(th,records,successful,eOpts ){
				var arrayObj = new Array();
				arrayObj[0]='area';
				arrayObj[1]='tray';
				arrayObj[2]='qty2';
				arrayObj[3]='volume';
				arrayObj[4]='weigth';
				arrayObj[5]='costPrice';
				arrayObj[6]='cell';
				arrayObj[7]='amount';
				arrayObj[8]='otherCost1';
				arrayObj[9]='otherCost2';
				arrayObj[10]='otherCost3';
				arrayObj[11]='otherCost4';
				arrayObj[12]='otherCost5';
				arrayObj[13]='favourableAmount';
				arrayObj[14]='total';
				countList2('grid_Exp_B403',arrayObj,'billingProject');
			}
		}

	});
Ext.define('cms.view.cost.cost_NoAccountListUI',{
	alias:'widget.cost_NoAccountListUI',
	title: $i18n.titleB403, //未出账清单查询
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
			'cms.view.common.commMenu4',
			'cms.view.common.bdef_DefOwnerCombo',
			'cms.view.common.remoteCombo',
			'cms.view.common.wms_DefFieldValCombo'
	          ],
	items:[
    {
    	xtype : 'toolbar',
 	    region:'north',
   	   items : [{
   			    text : $i18n.refresh,//刷新
   			    iconCls : 'refresh',
   			    id:'refreshB403_1'
   		    },
   		    {
   		    	text : $i18n.find,//查找
			    iconCls : 'query',
   			    id:'queryB403_1'
   		    },
   		    {
   				text : $i18n.export1,//导出
   				iconCls : 'export',
   				id:'exportB403_1'
   			},
   		    {
   				text : $i18n.retry,//重算
   				iconCls : 'edit',
   				id:'retryB403_1'
   			},
   		    {
   				text : $i18n.undo1,//回退
   				iconCls : 'undo',
   				id:'undoB403_1'
   			}]	
     },{
		xtype:'panel',
		region:'north',
		height: 60,
		layout: {
		    type: 'table',
	        columns: 3
		},
	    defaults : {
			xtype : 'textfield',
			margin : '3 3 3 3',
			labelAlign:'right',
			allowBlank: true,
			width : 280,
			labelWidth : 90
		},
		items:[{
				fieldLabel : $i18n.owner_no,
				id:'ownerNoB403',
				xtype:'wms_DefFieldValCombo',
				forceSelection : false,
				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
	            beforeLabelTextTpl : required
			},{
     			fieldLabel :$i18n.billingProject, //计费项目
     			id:'billingProjectB403',
 				xtype:'wms_DefFieldValCombo',
 				forceSelection : false,
 				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
					 proxy:{
	   					type:'ajax',
	    				method:'post',
	     				url:'cost_ExpensesListAction_getBillingProjectForUI',
	     				reader:{
	   						root:'rootList',
	   						totalProperty:'totalCount'
  						}
   					}
   			    }).load(),
   			    colspan:2
     		},{
	        	xtype:'datefield',
	            fieldLabel: $i18n.begin_date,
	            format : 'Y-m-d',
	            id: 'dtBeginDateExpB403',
	            beforeLabelTextTpl : required
	        }, {
	        	xtype:'datefield',
	            fieldLabel:  $i18n.end_time,
	            format : 'Y-m-d',
	            id: 'dtEndDateExpB403',
	            beforeLabelTextTpl : required
	        },{
	        	xtype: 'button',
	        	width : 80,
	        	name:'btnQueryB403',
	            text : $i18n.query
	        }]
		},{
	    xtype:'grid',
	    region:'center',
	    height:240,
	    id:'grid_Exp_B403',
	    store:cost_NoAccountListStore,
	    multiSelect: true,  
	    selModel: {  
        	selType:'checkboxmodel'  
	    },
	    columns:[
	    {			
			xtype : 'rownumberer',
			width : 30
	    },{
	    	xtype:'actioncolumn',
	    	width:100,
	    	align:'center',
			text:$i18n.operate,
			dataIndex:'total',
            icon: 'system/images/cost_images/chakanqingdan.png',  
            tooltip: $i18n.viewList,//查看清单
 
            handler: function(grid, rowIndex, colIndex) {
            	var data = grid.getStore().getAt(rowIndex); 
            	if(!Ext.isEmpty(data.get('ownerNo'))){
            		Ext.create('cms.view.cost.window.cost_ExpensesListWindow',{
        				title:$i18n.expensesList//消费清单
        			}).show();
                	var listDetail = [];
                	var a={
        					columnId:'a.owner_no',
        					value:data.get('ownerNo')
        				};
        			listDetail.push(a);
        			var b={
        					columnId:'a.billing_project',
        					value:data.get('billingProject')
        				};
        			listDetail.push(b);
        			var b={
        					columnId:'a.serial_no',
        					value:data.get('serialNo')
        				};
        			listDetail.push(b);
            		var strJson = Ext.encode(listDetail);
            		var wheresql = {
            			strQuery : strJson,
            			buildDate:null,
            			beginDate : data.get('beginDate'),
            			endDate : data.get('endDate')
            		};
            		Ext.getCmp('ownerNoB403_2').setValue(data.get('ownerNo'));
            		Ext.getCmp('billingProjectB403_2').setValue(data.get('billingProject'));
            		Ext.getCmp('serialNoB403_2').setValue(data.get('serialNo'));

            		Ext.apply(Ext.getCmp('gridExpensesListB403').getStore().proxy.extraParams,wheresql);
            		Ext.getCmp('gridExpensesListB403').getStore().removeAll();
            		Ext.getCmp('gridExpensesListB403').getStore().load();
            		
            		Ext.apply(Ext.getCmp('sourceNoB403').getStore().proxy.extraParams,wheresql);
            		Ext.getCmp('sourceNoB403').getStore().removeAll();
            		Ext.getCmp('sourceNoB403').getStore().load();
            	}
            		
            }
	    },{
			width : 80,
			text : $i18n.warehouse,//仓别
			dataIndex:'warehouseNo'
	    },{
	    	width: 150,
  		    text : $i18n.owner_no,  //货主编号
  		    dataIndex:'ownerNoText'		
	    },{
	    	width: 130,
  		    text :  $i18n.account_no,  //科目代码
  		    dataIndex:'accountNoText'		
		},{
	    	width: 180,
  		    text :  $i18n.billingProject,  //计费项目
  		    dataIndex:'billingProjectText'		
		},{
 		    width:90,
 		    text : $i18n.amountFlag,  //费用标识
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
			width: 130,
			align:'right',
  		    text : $i18n.area1,  //面积(平方米)
  		    dataIndex:'area'		
		},{
			width: 130,
			align:'right',
  		    text : $i18n.tray,  //托盘数
  		    dataIndex:'tray'		
		},{
			width: 130,
			align:'right',
  		    text : $i18n.box,  //件数
  		    dataIndex:'qty2'		
		},{
			width: 130,
			align:'right',
  		    text : $i18n.volume1,  //体积(立方米)
  		    dataIndex:'volume'		
		},{
	    	width: 130,
	    	align:'right',
  		    text : $i18n.weight1,  //重量(吨)
  		    dataIndex:'weigth'		
		},{
			width:100,
			text:$i18n.costPrice,//'货值',
			align:'right',
			dataIndex:'costPrice'
		},{
			width:100,
			text:$i18n.cell_no,//货位
			align:'right',
			dataIndex:'cell'
		},{
			width:100,
			text:$i18n.unitPrice,//默认单价
			align:'right',
			dataIndex:'unitPrice'
		},{
			width: 130,
			align:'right',
  		    text : $i18n.charge,  //费用
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
			text:$i18n.discount_cost,  //优惠费用
			dataIndex:'favourableAmount'	
		},{
			width:100,  
			align:'right',
			text:$i18n.total,  //合计
			dataIndex:'total'	
		}]/*,
	    dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:cost_NoAccountListStore,
			displayInfo : true
	    }]*/
	},{
    	region:'south'
	}]
});