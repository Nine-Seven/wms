/**
 * 模块名称：已出账账单查询
 * 模块编码：B503
 * 创建：hcx 
 */
var cost_FinancialStore=Ext.create('cms.store.cost.cost_FinancialStore',{
	autoLoad:false,
	 listeners:{ 	 
			'load':function(th,records,successful,eOpts ){
				var arrayObj = new Array();
				arrayObj[1]='amount';
				arrayObj[2]='otherCost1';
				arrayObj[3]='otherCost2';
				arrayObj[4]='otherCost3';
				arrayObj[5]='otherCost4';
				arrayObj[6]='otherCost5';
				arrayObj[7]='discountAmount';
				arrayObj[8]='total';
				countList2('grid_Financial_B503',arrayObj,'accountNo');
			}
		}
	});
Ext.define('cms.view.cost.cost_financialUI',{
	alias:'widget.cost_financialUI',
	title: $i18n.titleB503, //已出账账单查询
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
   			    id:'refreshB503_1'
   		    },
   	        {
   		    	text : $i18n.find,//查找
			    iconCls : 'query',
   			    id:'queryB503_1'
   		    },
   		    {
   				text : $i18n.export1,//导出
   				iconCls : 'export',
   				id:'exportB503_1'
   			},
   		    {
   				text : $i18n.retry,//重算
   				iconCls : 'edit',
   				id:'retryB503_1'
   			},
   		    {
   				text : $i18n.undo1,//回退
   				iconCls : 'undo',
   				id:'undoB503_1'
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
			width : 240,
			labelWidth : 60
		},
		items:[{
			fieldLabel : $i18n.owner_no,
			id:'ownerNoB503',
			xtype:'wms_DefFieldValCombo',
			forceSelection : false,
			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{

				 proxy:{
   					type:'ajax',
    				method:'post',
     				url:'cost_FinancialAction_getOwnerNoForQuery',
     				reader:{
   						root:'rootList',
   						totalProperty:'totalCount'
						}
					}
			    
			}).load(),
			beforeLabelTextTpl : required
		},{
				fieldLabel :$i18n.account, //科目
				id:'accountB503',
				xtype:'wms_DefFieldValCombo',
				forceSelection : false,
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
			    }).load()
			},{
     			fieldLabel :$i18n.status, //状态
     			id:'statusB503',
 				xtype:'wms_DefFieldValCombo',
 				forceSelection : false,
 				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
					 proxy:{
	   					type:'ajax',
	    				method:'post',
	     				url:'cost_ReceivingAction_getStatusList',
	     				reader:{
	   						root:'rootList',
	   						totalProperty:'totalCount'
  						}
   					}
   			    }).load(),
				beforeLabelTextTpl : required
			},{
				xtype : 'datefield',
				fieldLabel : $i18n.product_date,//生成日期
				id : 'builDdateB503',
				format : 'Y-m-d',
				beforeLabelTextTpl : required
			},{
				fieldLabel :$i18n.check_no, //对账单号
				id:'checkNoB503',
				xtype:'remoteCombo',
				forceSelection : false,
				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
					 proxy:{
 					type:'ajax',
 					method:'post',
 					url:'cost_FinancialAction_getCheckNoForUI',
 					reader:{
						root:'rootList',
 						totalProperty:'totalCount'
						}
					}
			    }).load(),
				beforeLabelTextTpl : required
			},{
				fieldLabel :$i18n.check_no, //科目组
				id:'accountGroupNoB503',
				xtype:'textfield',
				hidden:true,
				beforeLabelTextTpl : required
			},{
	        	xtype: 'button',
	        	width : 80,
	        	name : 'btnQueryB503',
	            text : $i18n.query
	        }]
		},{
	    xtype:'grid',
	    region:'center',
	    height:240,
	    id:'grid_Financial_B503',
	    store:cost_FinancialStore,
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
	                icon: 'system/images/cost_images/chakanduizhang.png',  
	                tooltip: $i18n.seeReconciliation,
	     
	                handler: function(grid, rowIndex, colIndex) {
	                	var data = grid.getStore().getAt(rowIndex); 
	                	if(!Ext.isEmpty(data.get('ownerNo'))){
	                		if(data.get('accountNo')=='N'){
	                			Ext.create('cms.view.cost.window.cost_OtherListWindow',{
		            				title:$i18n.costList//杂项费用对账明细
		            			}).show();
		                    	var listDetail = [];
		                    	var a={
		            					columnId:'t1.owner_no',
		            					value:data.get('ownerNo')
		            				};
		            			listDetail.push(a);
		            			var b={
		            					columnId:'t1.check_no',
		            					value:data.get('checkNo')
		            				};
		            			listDetail.push(b);
		            			var c={
		            					columnId:'t1.status',
		            					value:'13'
		            				};
		            			listDetail.push(c);
		            			var d={
		            					columnId:'t1.cost_flag',
		            					value:data.get('costFlag')
		            				};
		            			listDetail.push(d);
		                		var strJson = Ext.encode(listDetail);
		                		var wheresql = {
		                			strQuery : strJson
		                		};
		                		Ext.getCmp('ownerNoB503_3').setValue(data.get('ownerNo'));
		                		Ext.getCmp('checkNoB503_3').setValue(data.get('checkNo'));

		                		Ext.apply(Ext.getCmp('gridCostOtherListB503').getStore().proxy.extraParams,wheresql);
		                		Ext.getCmp('gridCostOtherListB503').getStore().removeAll();
		                		Ext.getCmp('gridCostOtherListB503').getStore().load();
		                		
		                		Ext.apply(Ext.getCmp('costNoB503_3').getStore().proxy.extraParams,wheresql);
		                		Ext.getCmp('costNoB503_3').getStore().removeAll();
		                		Ext.getCmp('costNoB503_3').getStore().load();
	                		}else{
	                			Ext.create('cms.view.cost.window.cost_CostListWindow',{
		            				title:$i18n.costList//对账明细
		            			}).show();
		                    	var listDetail = [];
		                    	var a={
		            					columnId:'a.owner_no',
		            					value:data.get('ownerNo')
		            				};
		            			listDetail.push(a);
		            			var b={
		            					columnId:'a.check_no',
		            					value:data.get('checkNo')
		            				};
		            			listDetail.push(b);
		            			var c={
		            					columnId:'a.status',
		            					value:'13'
		            				};
		            			listDetail.push(c);
		            			var d={
		            					columnId:'a.account_no',
		            					value:data.get('accountNo')
		            				};
		            			listDetail.push(d);
		            			var e={
		            					columnId:'f.cost_flag',
		            					value:data.get('costFlag')
		            				};
		            			listDetail.push(e);
		                		var strJson = Ext.encode(listDetail);
		                		var wheresql = {
		                			strQuery : strJson,
		                			buildDate:null,
		                			beginDate : null,
		                			endDate : null,
		                			strMenuType:'0'
		                		};
		                		Ext.getCmp('ownerNoB503_2').setValue(data.get('ownerNo'));
		                		Ext.getCmp('accountNoB503_2').setValue(data.get('accountNo'));
		                		Ext.getCmp('checkNoB503_2').setValue(data.get('checkNo'));

		                		Ext.apply(Ext.getCmp('gridCostListB503').getStore().proxy.extraParams,wheresql);
		                		Ext.getCmp('gridCostListB503').getStore().removeAll();
		                		Ext.getCmp('gridCostListB503').getStore().load();
		                		
		                		Ext.apply(Ext.getCmp('billingProjectB503_2').getStore().proxy.extraParams,wheresql);
		                		Ext.getCmp('billingProjectB503_2').getStore().removeAll();
		                		Ext.getCmp('billingProjectB503_2').getStore().load();
	                		}
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
	    	    	width: 180,
	      		    text :  $i18n.account_no,  //科目代码
	      		    dataIndex:'accountNoText'		
	    		},{
	    	    	width: 130,
	      		    text :  $i18n.check_no,  //对账单号
	      		    dataIndex:'checkNo'		
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
					hidden:true,
	    			text:$i18n.realAmount,  //实收金额
	    			dataIndex:'realAmount'	
	    		},{
	    			width:100,  
	    			align:'right',
	    			text:$i18n.total,  //合计
	    			dataIndex:'total'	
	    		}]/*,
	    dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:cost_FinancialStore,
			displayInfo : true
	    }]*/
	},{
    	region:'south'
	}]
});