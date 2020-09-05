/**
 * 模块名称：病单处理
 * 模块编码：3917
 * 创建：hcx
 */
var odata_ExpCancelMStore=Ext.create('cms.store.odata.odata_ExpCancelMStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_ExpCancelAction_getExpCancelMList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	},listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(th.count()>0){
				Ext.getCmp('oecmGrid3917').getSelectionModel().select(0);
			}
		}
	},
	autoLoad: true
});
var odata_ExpCancelDStore=Ext.create('cms.store.odata.odata_ExpCancelDStore',{autoLoad: false});
Ext.define('cms.view.odata.odata_ExpCancelUI',{
	alias:'widget.odata_ExpCancelUI',
	title:$i18n.title3917,//病单处理
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	        'cms.view.common.remoteCombo'
	],
	items:[
	{

		xtype:'tabpanel',
		id:'tabPId3917_1',
	    region:'center',
	    items:[
	    {
	    	title:$i18n.titleM,
	    	layout:'border',
	    	items:[{
	    		xtype : 'toolbar',
	    		region:'north',
	    		items : [{
	    			text : $i18n.refresh,
					iconCls : 'refresh',
					id:'btnRefresh3917'
	    		 }]
	    	},{
	    		xtype : 'form',
				id : 'form_01_3917',
				region : 'north',
				layout:'column',
				width:'100%',
				frame : true,
				items : [ {
					layout : {
					type : 'table',
					columns : 3
					},
					xtype : 'container',
					defaults : {
						xtype : 'textfield',
						labelAlign:'right'			
					},
					items : [ {
						xtype : 'combo',
						fieldLabel : $i18n.owner,// 委托业主
						id:'owner3917',
						displayField: 'dropValue',
						valueField: 'value',
						store:Ext.create("cms.store.bdef.bdef_DefOwnerComboStore",{
							proxy:{
								type:'ajax',
								method:'post',
								url:'odata_ExpCancelAction_getOwnerCombo'
							},
							listeners:{  
									'load':function(th,records,successful,eOpts ){
										if(th.count()>0){
											Ext.getCmp('owner3917').setValue(th.getAt(0).data.value);
											_myAppGlobal.getController('cms.controller.odata.odata_ExpCancelController').owner3917Select();	
										}
								      }
									}
						   	}).load(),
				   		beforeLabelTextTpl : required
					}, {

						xtype:'combo',
			  			fieldLabel:$i18n.paper_status,//单据状态
			  			id:'statusText3917',
	       		 	    displayField : 'dropValue',
		       			valueField : 'value',
		       			store:Ext.create("cms.store.bdef.bdef_DefOwnerComboStore",{
							proxy:{
								type:'ajax',
								method:'post',
								url:'odata_ExpCancelAction_getStatusTextCombo'
							}
		       			}).load()
				}, {

						xtype:'combo',
			  			fieldLabel:$i18n.suorce_type,//病单来源
			  			id:'suorceType3917',
	       		 	    displayField : 'dropValue',
		       			valueField : 'value',
		       		    store:Ext.create("cms.store.bdef.bdef_DefOwnerComboStore",{
							proxy:{
								type:'ajax',
								method:'post',
								url:'odata_ExpCancelAction_getSuorceTypeCombo'
							}
		       			})
					},{
						xtype:'datefield',
		 	    		fieldLabel:$i18n.paper_date,//单据日期
		 	    		id:'operateDate3917',
		 	    		format : 'Y-m-d'
					}, {
						xtype:'combo',
		 	    		fieldLabel:$i18n.cancel_no,//病单单号
		 	    		id:'cancelNo3917',
		 	    		store:Ext.create("cms.store.bdef.bdef_DefOwnerComboStore",{
							proxy:{
								type:'ajax',
								method:'post',
								url:'odata_ExpCancelAction_getCancelNoCombo'
							}							
		       			})
					},{
						xtype: 'checkboxfield',
			        	fieldLabel: $i18n.whetherHtyDate,//是否历史单据
			        	id:'cbHistoryFlag3917',
			        	check: false
					}
					]
				}]	
	    	},
		    	{
	    		region:'center',
	      	     xtype:'grid',
	      	     id:'oecmGrid3917',
	   		 store:odata_ExpCancelMStore,
	      	     columns:[ {
	   				    xtype : 'rownumberer',
	   				    width : 30
	   			    },{
	      	        	   width:130,
	      	        	   text:$i18n.cancel_no,//撕票单号
	      	        	   dataIndex:'cancelNo'
	      	            },{
	      	        	   width:130,
	      	        	   text:$i18n.exp_no,//出货单号
	      	        	   dataIndex:'expNo'
	      	            },{
	      	        	   width:130,
	      	        	   text:$i18n.source_plan_no,//来源单号
	      	        	   dataIndex:'sourceexpNo'
	      	            },{
	       	        	   width:120,
	          	        	   text:$i18n.cust_no,//客户编码
	          	        	   dataIndex:'custNo'
	          	        },{
	      	        	   width:110,
	      	        	   text:$i18n.suorce_type,//病单来源
	      	        	   dataIndex:'suorceTypeText'
	      	            },{
	      	        	   width:110,
	      	        	   text:$i18n.operate_date,//单据日期
	      	        	   dataIndex:'operateDate'
	      	           },{
	      	        	   width:80,
	      	        	   text:$i18n.status,//状态
	      	        	   dataIndex:'statusText'
	      	           }],
	      	        dockedItems : [{
	      				xtype : 'pagingtoolbar',
	      				dock : 'bottom',
	      				store : odata_ExpCancelMStore,
	      				displayInfo : true
	      			}] 
	    	    
	    	}]
	    },{
	    	title:$i18n.titleD,
	    	layout:'border',
	    	itemId:'tabPId3917_2',
	    	items:[
	    	{
	    		xtype : 'toolbar',
	    		region:'north',
	    		items : [{
	    			text : $i18n.userPrev,
	    			iconCls : 'prev',
	    			id:'userPrevButton'
	    		},{
	    			text : $i18n.userNext,
	    			iconCls : 'next',
	    			id:'userNextButton'
	    		},{
	    			text : $i18n.uplocad,
	    			id:'btnUplocad3917'
	    		 },{
	    				text : $i18n.repeatLocate,
	    				id:'btnRepeatLocate3917'
	    		 }]
			},{
				xtype : 'form',
	    	    id : 'form_02_3917',
	    	    region:'north',
	    	    frame : true,
	    	    layout:{
    	    		type:'table',
    	    		columns:3
    	    	},
    	    	defaults : {
					xtype:'textfield',
					labelWidth : 90,
					margin : '2 2 6 2',
					labelAlign : 'right'
		  		},
	    	    items :[
    	    	{
    	    		xtype : 'textfield',
					fieldLabel : $i18n.owner,// 委托业主
					id:'owner_3917',
	 	    		readOnly:true
    	    	},{
    	    		xtype:'textfield',
		  			fieldLabel:$i18n.paper_status,//单据状态
		  			id:'statusText_3917',
		 	    	readOnly:true
    	    	},{
    	    		xtype:'textfield',
		  			fieldLabel:$i18n.suorce_type,//病单来源
		  			id:'suorceType_3917',
	 	    		readOnly:true
    	    	},{
    	    		xtype:'textfield',
	 	    		fieldLabel:$i18n.paper_date,//单据日期
	 	    		id:'operateDate_3917',
	 	    		readOnly:true
				
    	    	},{
    	    		xtype:'textfield',
	 	    		fieldLabel:$i18n.cancel_no,//病单单号
	 	    		id:'cancelNo_3917',
	 	    		readOnly:true
    	    	}]
			},{
		    	 
		   	     xtype:'grid',
		   	     region:'center',
		   	     id:'oecdGrid3917',
//		   	     height:300,
		   	     store:odata_ExpCancelDStore,
		   	  viewConfig : {   
		          forceFit : true,   
		          getRowClass : function(record,rowIndex,rowParams,store){   
		             //缺量数据显示红色   
		             if(record.data.differenceQty>0 == true && record.data.noEnoughQty>0 == false){   
		                 return 'x-grid-record-green';   
		              }
		             else if(record.data.differenceQty>0 == true && record.data.noEnoughQty>0 == true){
		            	 return 'x-grid-record-red';  
		              }else{   
		                 return '';   
		              }   
		          }   
			    },  	
		   	     columns:[ {
						    xtype : 'rownumberer',
						    width : 30
					    },{
		   	        	   width:90,
		   	        	   text:$i18n.article_no,//商品编码
		   	        	   dataIndex:'articleNo'
		   	            },{
		   	        	   width:90,
		   	        	   text:$i18n.owner_article_noD101,//商品内码
		   	        	   dataIndex:'ownerArticleNo'
		   	            },{
		    	        	   width:120,
		       	        	   text:$i18n.barcode,//商品条码
		       	        	   dataIndex:'barcode'
		       	        },{
		   	        	   width:200,
		   	        	   text:$i18n.article_name,//商品名称
		   	        	   dataIndex:'articleName'
		   	            },{
		   	        	   width:80,
		   	        	   text:$i18n.packing_qty,//包装数量
		   	        	   dataIndex:'packingQty'
		   	           },/*{
		   	        	   width:80,
		   	        	   text:$i18n.packing_unit,//包装单位
		   	        	   dataIndex:'unit'
		   	           },*/
			   	        {
			   			    width:80,
			   			    text:$i18n.packingUnit,//包装单位
			   			    id:'packingUnit_3917',
			   			    dataIndex:'packingUnit'
			   			},{
			   				width:80,
			   				text:$i18n.packingSpec,//规格
			   				id:'packingSpec_3917',
			   				dataIndex:'packingSpec'
			   			},{
		   	        	   width:80,
		   	        	   text:$i18n.article_qty,//计划数量
		   	        	   dataIndex:'articleQty'
		   	           },
			   	        {
			   				width : 85,
			   				text : $i18n.planBox,	//计划箱数
			   				dataIndex : 'planBox',
			   				id:'planBox_3917'
			   			},{
			   				width : 85,
			   				text : $i18n.planQmin,//计划中包数
			   				dataIndex : 'planQmin',
			   				id:'planQmin_3917'
			   			},{
			   				width : 85,
			   				text : $i18n.planDis,//计划散数
			   				dataIndex : 'planDis',
			   				id:'planDis_3917'
			   			},{   
		   	        	    width:80,
		   	        	    text : $i18n.real_qty3,//实际数量
		   					dataIndex : 'realQty',
		   	           },{
			   				width : 85,
							text : $i18n.realBox,	//实际箱数
							dataIndex : 'realBox',
							id:'realBox_3917'
						},{
							width : 85,
							text : $i18n.realQmin,//实际中包数
							dataIndex : 'realQmin',
							id:'realQmin_3917'
						},{
							width : 85,
							text : $i18n.realDis,//实际散数
							dataIndex : 'realDis',
							id:'realDis_3917'
						},{
		   	        	   width:90,
		   	        	   text:$i18n.difference_qty,//差异数量
		   	        	   dataIndex:'differenceQty'
		   	           },{
			   				width : 85,
							text : $i18n.differenceBox,	//差异箱数
							dataIndex : 'differenceBox',
							id:'differenceBox_3917'
						},{
							width : 85,
							text : $i18n.differenceQmin,//差异中包数
							dataIndex : 'differenceQmin',
							id:'differenceQmin_3917'
						},{
							width : 85,
							text : $i18n.differenceDis,//差异散数
							dataIndex : 'differenceDis',
							id:'differenceDis_3917'
						},{
		   	        	   width:90,
		   	        	   text:$i18n.available_qty,//可用数量
		   	        	   dataIndex:'availableQty'
		   	           },{
		   	        	   width:90,
		   	        	   text:$i18n.no_enough_qty,//缺量
		   	        	   dataIndex:'noEnoughQty'
		   	           }]}
	    	]
	    }]
	
	}]
});