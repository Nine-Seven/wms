/**
 * 模块名称：计费公式管理UI
 * 模块编码：B103
 * 创建：chensr 
 */
var cost_FormulasetStore=Ext.create('cms.store.cost.cost_FormulasetStore',{
	autoLoad:true,
	listeners:{  
		'load':function(th,records,successful,eOpts ){
		if(th.count()>0){
			var ownerNoB103=_myAppGlobal.getController('cms.controller.cost.cost_FormulasetController').getOwnerNoB103();	
		    var billingProjectB103=_myAppGlobal.getController('cms.controller.cost.cost_FormulasetController').getBillingProjectB103();	 				 
		    for(var i=0; i<th.count();i++){
					 var data = Ext.getCmp('formulasetUIB103').getStore().getAt(i);
					 if(data.get('ownerNo')== ownerNoB103 && data.get('billingProject')== billingProjectB103){
						 Ext.getCmp('formulasetUIB103').getSelectionModel().select(i);
						 return;
					 }		
				 }
			
			}
		}
	}
});
var cost_FormulaDiscountStore=Ext.create('cms.store.cost.cost_FormulaDiscountStore',{autoLoad:false});
var cost_FormulaArticlefamilyStore=Ext.create('cms.store.cost.cost_FormulaArticlefamilyStore',{autoLoad:false});

Ext.define('cms.view.cost.cost_FormulasetUI',{
	alias:'widget.cost_FormulasetUI',
	title: $i18n.titleB101,//计费项目管理
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	id:'cost_FormulasetUI',
	requires:['cms.view.common.commMenu2',
			  'cms.view.common.commMenu5',
			  'cms.view.common.bdef_DefOwnerCombo',
			  'cms.view.common.wms_DefFieldValCombo',
			  'cms.view.common.wms_DefStrategyCombo'
	         ],
	         items:[
	                {
	             	    xtype:'commMenuWidget2',
	             	    id:'menuB103',
	             	    region:'north'
	                 },{
		         		xtype:'panel',
		         		region:'north',
		         		height: 35,
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
		         			id:'ownerNoUIB103',
		     				xtype:'wms_DefFieldValCombo',
		     				forceSelection : false,
		     				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		     					 proxy:{
			     					type:'ajax',
			     					method:'post',
			     					url:'cost_FormulasetAction_getOwnerNoForQuery',
			   						reader:{
			     						root:'rootList',
			     						totalProperty:'totalCount'
			    					}
			   					}
			   			    }).load(),
			   			    displayField : 'dropValue',
		    			    valueField : 'value'
	         			},{
		         			fieldLabel :$i18n.billingProject, //计费项目
		         			id:'billingProjectUIB103',
		     				xtype:'wms_DefFieldValCombo',
		     				forceSelection : false,
		     				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
		    					 proxy:{
				   					type:'ajax',
				    				method:'post',
				     				url:'cost_FormulasetAction_getBillingProjectForUI',
				     				reader:{
				   						root:'rootList',
				   						totalProperty:'totalCount'
			  						}
			   					}
			   			    }).load(),
			   			    displayField : 'dropValue',
			   			    valueField : 'value'
		         		}]
		         	},{
	             	    xtype:'grid',
	             	    id:'formulasetUIB103',
	             	    region:'north',
	             	    height:260,
	             	    store : cost_FormulasetStore,
	             	    columns:[{			
	             	        xtype : 'rownumberer',
	             		    width : 30
	             	    },{
	             		    width:60,
	             		    text : $i18n.warehouse,  //仓别
	             		    dataIndex:'warehouseNo'			
	             	    },{
	             		    width:150,
	             		    text : $i18n.owner_no,  //货主编号
	             		    dataIndex:'ownerNoText'			
	             	    },{
	             		    width:100,
	             		    text : $i18n.billingProject,  //计费项目
	             		    dataIndex:'projectText'			
	             	    },{
	             		    width:100,
	             		    text : $i18n.billingType1,  //计费类型
	             		    dataIndex:'billingTypeText'			
	             	    },{
	             		    width:60,
	             		    text : $i18n.billingCycle,  //计费周期
	             		    dataIndex:'billingCycleText'			
	             	    },{
	             		    width:80,
	             		    text : $i18n.billingFlag,  //计费方式
	             		    dataIndex:'billingFlagText'			
	             	    },{
	             		    width:80,
	             		    text : $i18n.billingUnit,  //计费单位
	             		    dataIndex:'billingUnitText'			
	             	    },{
	             		    width:60,
	             		    text : $i18n.unitPrice,  //默认单价
	             		    dataIndex:'unitPrice'			
	             	    },{
	             		    width:100,
	             		    text : $i18n.standard_flag,  //是否为标准策略
	             		    dataIndex:'standardFlagText'			
	             	    },{
	             		    width:90,
	             		    text : $i18n.amountDate,  //结算日期
	             		    dataIndex:'balanceDayText'			
	             	    },{
	             		    width:90,
	             		    text : $i18n.beginDate,  //起始日期
	             		    dataIndex:'beginDateText'			
	             	    },{
	             		    width:90,
	             		    text : $i18n.endDay,  //截止日期
	             		    dataIndex:'endDateText'			
	             	    },{
	             		    width:90,
	             		    text : $i18n.amountFlag,  //费用标识
	             		    dataIndex:'costFlagText'			
	             	    },{
	             		    width:90,
	             		    text : $i18n.manage_status,  //状态
	             		    dataIndex:'statusText'			
	             	    },{
	             		    width:90,
	             		    text : $i18n.remark,  //备注
	             		    dataIndex:'remark'			
	             	    },{
	             	    	width:100,
							text:$i18n.other_cost1,  //其他费用1
							dataIndex:'otherCost1'	
						},{
							width:100,
							text:$i18n.other_cost2,  //其他费用2
							dataIndex:'otherCost2'	
						},{
							width:100,  
							text:$i18n.other_cost3,  //其他费用3
							dataIndex:'otherCost3'	
						},{
							width:100,
							text:$i18n.other_cost4,  //其他费用4
							dataIndex:'otherCost4'	
						},{
							width:100,  
							text:$i18n.other_cost5,  //其他费用5
							dataIndex:'otherCost5'	
						}],
	             	    dockedItems : 
	             	    [
	             		    {
	             		        xtype : 'pagingtoolbar',
	             			    store : cost_FormulasetStore,
	             			    dock : 'bottom',
	             			    displayInfo : true
	             			}
	             	    ]
	             	},{

	            		xtype:'grid',
	            		id:'formulasetUIB103_2',
	            		title:$i18n.PreferentialPolicies,//优惠策略
	            		width:'58%',
	            		region:'west',
	            		store:cost_FormulaDiscountStore,
	            		
	            		columns:[{			
	            			xtype : 'rownumberer',
	            			width : 30
	            		},{
	       		    	    width: 100,
	 		  		        text : $i18n.accountLadder,  //优惠阶梯
	 		  		        dataIndex:'ladder'		
	 			       },{
	 		    	        width: 220,
	 		  		        text : $i18n.discount_flag,  //优惠方式
	 		  		        dataIndex:'discountFlagText'		
	 			       },{
	 		    	        width: 100,
	 		  		        text : $i18n.value1,  //值1
	 		  		        dataIndex:'value1'		
	 			       },{
	 		    	        width: 100,
	 		  		        text : $i18n.value2,  //值2
	 		  		        dataIndex:'value2'		
	 			       },{
	 		    	        width: 100,
	 		  		        text : $i18n.remark,  //备注
	 		  		        dataIndex:'remark'		
	 			       }],
	            		dockedItems : [{
	            			xtype : 'pagingtoolbar',
	            			dock : 'bottom',
	            			store:cost_FormulaDiscountStore,
	            			displayInfo : true
	            		}]
	            	
	             	},{

	            	    xtype:'grid',
	            	    id:'formulasetUIB103_3',
	            	    title:$i18n.articleFamilyNo1,//商品群组
	            	    width:'40%',
	            	    region:'east',
	            	    store:cost_FormulaArticlefamilyStore,
	            	    columns:[{			
	            			xtype : 'rownumberer',
	            			width : 30
	            	    },{
	            			width : 150,
	            			text : $i18n.articleFamilyNo,//商品群组编码
	            			dataIndex:'familyNo'
	            	    },{
	            			width : 200,
	            			text : $i18n.articleFamilyName,//商品群组名称
	            			dataIndex:'familyName'
	            	    }],
	            	    dockedItems : [{
	            			xtype : 'pagingtoolbar',
	            			dock : 'bottom',
	            			store:cost_FormulaArticlefamilyStore,
	            			displayInfo : true
	            	    }]
	            	
	             	},{
	             		region:'south'
	             }]          
});