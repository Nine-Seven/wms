/**
 * 模块名称：收款
 * 模块编码：B703
 * 创建：hcx 
 */
var cost_FinancialStore=Ext.create('cms.store.cost.cost_FinancialStore',{
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'cost_ReceivingAction_getFinList',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});
Ext.define('cms.view.cost.cost_receivingUI',{
	alias:'widget.cost_receivingUI',
	title: $i18n.titleB703, //收款
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
			'cms.view.common.commMenu4',
			'cms.view.common.commMenu5',
	        'cms.view.common.bdef_DefWorkerCombo',
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
   			    id:'refreshB703_1'
   		    },
   		    {
   		    	text : $i18n.find,//查找
			    iconCls : 'query',
   			    id:'queryB703_1'
   		    },
   		    {
   				text : $i18n.export1,//导出
   				iconCls : 'export',
   				id:'exportB703_1'
   			}]	
     },{
		xtype:'panel',
		region:'north',
		height: 80,
		layout: {
		    type: 'table',
	        columns: 3
		},
	    defaults : {
			xtype : 'textfield',
			margin : '10 1 3 1',
			labelAlign:'right',
			allowBlank: true,
			width : 280,
			labelWidth : 90
		},
		items:[{
			fieldLabel : $i18n.owner_no,
			id:'ownerNoB703',
			xtype:'wms_DefFieldValCombo',
			forceSelection : false,
			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{

				 proxy:{
   					type:'ajax',
    				method:'post',
     				url:'cost_ReceivingAction_getOwnerNoForQuery',
     				reader:{
   						root:'rootList',
   						totalProperty:'totalCount'
						}
					}
			    
			}).load()
		},{
				fieldLabel :$i18n.check_no, //对账单号
				id:'checkNoB703',
				xtype:'wms_DefFieldValCombo',
				forceSelection : false,
				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
					 proxy:{
 					type:'ajax',
 					method:'post',
 					url:'cost_ReceivingAction_getCheckNoForUI',
 					reader:{
						root:'rootList',
 						totalProperty:'totalCount'
						}
					}
			    }).load()
			},{

     			fieldLabel :$i18n.status, //状态
     			id:'statusB703',
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
   			    }).load()
			},{
	        	xtype:'datefield',
	            fieldLabel: $i18n.begin_date,
	            format : 'Y-m-d',
	            id: 'dtBeginDateExpB703'/*,
	            beforeLabelTextTpl : required*/
	        },{

	        	xtype:'datefield',
	            fieldLabel:  $i18n.end_time,
	            format : 'Y-m-d',
	            id: 'dtEndDateExpB703'/*,
	            beforeLabelTextTpl : required*/
	        },{
	        	xtype: 'button',
	        	width : 80,
	        	name : 'btnQueryB703',
	            text : $i18n.query//查询
	        }]
		},{
	    xtype:'grid',
	    region:'center',
	    height:240,
	    id:'grid_Financial_B703',
	    store:cost_FinancialStore,
	    columns:[
	     	    {			
	    			xtype : 'rownumberer',
	    			width : 30
	    	    },{

	    	    	xtype:'actioncolumn',
	    	    	width:50,
	    	    	align:'center',
	    			text:$i18n.operate,
	    			dataIndex:'huikuan',
	                icon: 'system/images/cost_images/huiorfukuan.png',  
	                tooltip: $i18n.returnAccount,//回/付款
	     
	                handler: function(grid, rowIndex, colIndex) {
	                	var data = grid.getStore().getAt(rowIndex); 
	                	if(!Ext.isEmpty(data.get('ownerNo'))){
	                		Ext.create('cms.view.cost.window.cost_ReturnAmountWindow',{
	            				title:$i18n.returnAccount//回款
	            			}).show();
	                		Ext.getCmp('ownerNoB703_2').setValue(data.get('ownerNo'));
	                		Ext.getCmp('planAmountB703').setValue(data.get('planAmount'));
	                		Ext.getCmp('costFlag_2').setValue(data.get('costFlag'));

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
	            					columnId:'a.cost_flag',
	            					value:data.get('costFlag')
	            				};
	            			listDetail.push(c);
	                		var strJson = Ext.encode(listDetail);
	                		var wheresql = {
	                			strQuery : strJson
	                		};
	                		Ext.apply(Ext.getCmp('grid_return_amount').getStore().proxy.extraParams,wheresql);
	                		Ext.getCmp('grid_return_amount').getStore().removeAll();
	                		Ext.getCmp('grid_return_amount').getStore().load();
	                		if(data.get('spareAmount')<=0){
	                			Ext.getCmp('cost_ReturnAmountB703').items.items[1].setVisible(false);
		                		Ext.getCmp('cost_ReturnAmountB703').items.items[2].setVisible(false);
		                		Ext.getCmp('cost_ReturnAmountB703').items.items[3].setVisible(false);
		                		Ext.getCmp('cost_ReturnAmountB703').items.items[4].setVisible(false);
		                		Ext.getCmp('cost_ReturnAmountB703').items.items[5].setVisible(true);
		                		commonSetMsterReadOnlyByArray(
		    							new Array('checkNo_2','realAmountB703','rgstNameB703'),true);
		                		Ext.getCmp('checkNo_2').setValue(data.get(''));
	                		}else{
	                			Ext.getCmp('cost_ReturnAmountB703').items.items[1].setVisible(false);
		                		Ext.getCmp('cost_ReturnAmountB703').items.items[2].setVisible(false);
		                		Ext.getCmp('cost_ReturnAmountB703').items.items[3].setVisible(false);
		                		Ext.getCmp('cost_ReturnAmountB703').items.items[4].setVisible(true);
		                		Ext.getCmp('cost_ReturnAmountB703').items.items[5].setVisible(true);
		                		commonSetMsterReadOnlyByArray(
		    							new Array('checkNo_2'),false);
		                		Ext.getCmp('checkNo_2').setValue(data.get('checkNo'));
		                		Ext.getCmp('realAmountB703').focus(false, 3);
	                		}
	                	}
	                }
	    	    },{
	    	    	xtype:'actioncolumn',
	    	    	width:70,
	    	    	align:'center',
	    			text:$i18n.operate,
	    			dataIndex:'total',
	                icon: 'system/images/cost_images/chakanduizhang.png',  
	                tooltip: $i18n.seeReconciliation,//查看对账
	     
	                handler: function(grid, rowIndex, colIndex) {
	                	var data = grid.getStore().getAt(rowIndex); 
	                	if(!Ext.isEmpty(data.get('ownerNo'))){
	                		Ext.create('cms.view.cost.window.cost_FinancialWindow',{
	            				title:$i18n.financialList//对账明细
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
	            					columnId:'a.cost_flag',
	            					value:data.get('costFlag')
	            				};
	            			listDetail.push(c);
	                		var strJson = Ext.encode(listDetail);
	                		var wheresql = {
	                			strQuery : strJson,
	                			buildDate:null,
	                			beginDate : null,
	                			endDate : null,
	                			strMenuType:'0'
	                		};
	                		Ext.getCmp('ownerNoB703_2').setValue(data.get('ownerNo'));
	                		Ext.getCmp('checkNoB703_2').setValue(data.get('checkNo'));

	                		Ext.apply(Ext.getCmp('gridFinanciaListB703').getStore().proxy.extraParams,wheresql);
	                		Ext.getCmp('gridFinanciaListB703').getStore().removeAll();
	                		Ext.getCmp('gridFinanciaListB703').getStore().load();
	                		
	                		Ext.apply(Ext.getCmp('accountNoB703_2').getStore().proxy.extraParams,wheresql);
	                		Ext.getCmp('accountNoB703_2').getStore().removeAll();
	                		Ext.getCmp('accountNoB703_2').getStore().load();
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
	    			width:120,
	    			align:'right',
	    			text:$i18n.planAmount,  //应收/付金额
	    			dataIndex:'planAmount'	
	    		},{
	    			width:120,  
	    			align:'right',
	    			text:$i18n.returnAmount,  //回/付款金额
	    			dataIndex:'realAmount'	
	    		},{
	    			width:120,  
	    			align:'right',
	    			text:$i18n.spareAmount,  //应收/付剩余金额（元）
	    			dataIndex:'spareAmount'	
	    		},{
	    			width:120,  
	    			text:$i18n.manage_status, //状态
	    			dataIndex:'statusText'	
	    		}],
	    dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:cost_FinancialStore,
			displayInfo : true
	    }]
	},{
    	region:'south'
	}]
});