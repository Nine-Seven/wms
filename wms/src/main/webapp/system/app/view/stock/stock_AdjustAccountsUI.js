/**
 * 模块名称：虚拟库存调整
 * 模块编码：D101
 * 创建：hcx
 */
var stock_AdjustAccountsStore=Ext.create('cms.store.stock.stock_ADjDStore',{autoLoad: true});
Ext.define('cms.view.stock.stock_AdjustAccountsUI',{
	alias:'widget.stock_AdjustAccountsUI',
	title:$i18n.titleD101,//虚拟库存调整
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
            'cms.view.common.commMenu5',
            'cms.view.common.cdef_DefCellCombo',
	        'cms.view.common.bdef_DefArticleCombo',
	        'cms.view.common.bdef_DefWorkerCombo',
	        'cms.view.common.bdef_PackingQtyCombo',
	        'cms.view.common.wms_DefFieldValCombo',
	        'cms.view.common.bdef_DefDockCombo',
	        'cms.view.common.remoteCombo'
	],
	items:[
	{
		xtype : 'toolbar',
		region:'north',
		items : [{
			text : $i18n.save,
			iconCls : 'save',
			id:'saveD101'
		 },{
				text : $i18n.newitem,
				iconCls : 'add',
				id:'btnNewitemD101'
		 },{
				text : $i18n.refresh,
				iconCls : 'refresh',
				id:'btnRefreshD101'
			}]
     },{

			xtype : 'form',
			id : 'form_01_D101',
			region : 'north',
			layout:'column',
			width:'100%',
			frame : true,
			items : [ {
				layout : {
				type : 'table',
				columns : 2
				},
				xtype : 'container',
				defaults : {
					xtype : 'textfield',
					labelWidth : 130,
					margin:'9 3 3 9 ',
					labelAlign:'right'			
				},
				items : [ {
					xtype : 'combo',
					fieldLabel : $i18n.owner,// 委托业主
					id:'ownerD101',
					displayField: 'dropValue',
					valueField: 'value',
					beforeLabelTextTpl : required,
					store:Ext.create("cms.store.bdef.bdef_DefOwnerComboStore",{
					listeners:{  
							'load':function(th,records,successful,eOpts ){
								if(th.count()>0){
										Ext.getCmp('ownerD101').setValue(th.getAt(0).data.value);
								}
						      }
							}
				   	}).load(),
			   		beforeLabelTextTpl : required
				}, {

					xtype:'cdef_DefCellCombo',
		  			fieldLabel:$i18n.addcell_no,//储位
		  			id:'cellNoD101',
		  			store:Ext.create('cms.store.cdef.cdef_DefCellComboStore',{
	    			    proxy:{
							type:'ajax',
							method:'post',
							url:'stock_AdjustAccountsAction_getCdef_DefCellCombo',
						}
	    			}),
	       		 	displayField : 'dropValue',
	       			valueField : 'value',
	       			beforeLabelTextTpl : required
				},{
					xtype:'bdef_DefArticleCombo',
	 	    		fieldLabel:$i18n.article_no_or_ownerAticleNo_or_barcode,//商品编码
	 	    		id:'articleNoOwnerArticleNoBarcodeD101',
	 	    		displayField : 'value',
					valueField : 'value',
					store:Ext.create("cms.store.bdef.bdef_DefArticleComboStore",
							 {
						proxy:{
							type:'ajax',
							method:'post',
							url:'stock_AdjustAccountsAction_getArticleCombo',
						}
	   				})
				}, {
					xtype:'bdef_DefDockCombo',
	 	    		fieldLabel:$i18n.productDateAndLotNo,//生产日期和批号
	 	    		id:'productDateAndLotNoD101',	 	    
	 	    		store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',
							 {
								proxy:{
									type:'ajax',
									method:'post',
									url:'stock_AdjustAccountsAction_getProductDateAndLotNo',
								}
			   				})
				}]
			},{
				xtype : 'button',
				margin:'40 0 0 20',
				name:'queryD101',
				text : $i18n.query
			} ]		
     },{

	     region:'center',
   	     xtype:'grid',
   	     id:'gridD101',
   	     width:'100%',
   	     height:'100%',
   	     store:stock_AdjustAccountsStore,
   		 plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
			clicksToEdit : 1,
			onSpecialKey:function(ed,field,e){
			commEnterGridStatEdit(this.grid,true,'cms.controller.stock.stock_AdjustAccountsController',e.getKey());
			}
		})],
   	     columns:[ {
				    xtype : 'rownumberer',
				    width : 30
			    },{
   	        	   width:80,
   	        	   text:$i18n.cell_no,//储位
   	        	   align:'center',
   	        	   dataIndex:'cellNo'
   	            },{
   	        	   width:90,
   	        	   text:$i18n.article_no,//商品编码
   	        	    align:'center',
   	        	   dataIndex:'articleNo'
   	            },{
   	        	   width:90,
   	        	   text:$i18n.owner_article_noD101,//商品内码
   	        	    align:'center',
   	        	   dataIndex:'ownerArticleNo'
   	            },{
    	        	   width:120,
       	        	   text:$i18n.barcode,//商品条码
       	        	    align:'center',
       	        	   dataIndex:'barcode'
       	        },{
   	        	   width:200,
   	        	   text:$i18n.article_name,//商品名称
   	        	    align:'center',
   	        	   dataIndex:'articleName'
   	            },{
   	        	   width:60,
   	        	   text:$i18n.packing_qty,//包装数量
   	        	    align:'center',
   	        	   dataIndex:'packingQty'
   	           },
   	           {
				    width:80,
				    text:$i18n.packingUnit,//包装单位
				    id:'packingUnit_D101',
				    dataIndex:'packingUnit'
				},{
					width:80,
					text:$i18n.packingSpec,//规格
					id:'packingSpec_D101',
					dataIndex:'packingSpec'
				},{
					width:70,
					text:$i18n.articleqty,//计划数量
					dataIndex:'planQty',
				},
				{
					width : 85,
					text : $i18n.planBox,	//计划箱数
					dataIndex : 'planBox',
					//hidden:true,
					id:'planBox_D101',
				},{
					width : 85,
					text : $i18n.planQmin,//计划中包数
					dataIndex : 'planQmin',
					id:'planQmin_D101',
				},{
					width : 85,
					text : $i18n.planDis,//计划散数
					dataIndex : 'planDis',
					id:'planDis_D101',
				},{
					width : 90,
					text : $i18n.plan_real_qty,//实际数量
					dataIndex:'realQty'
				},
				{
					width : 85,
					text : $i18n.planRealBox,	//实际箱数
					dataIndex : 'realBox',
					//hidden:true,
					id:'realBox_D101',
					align:'center',
   					cls : 'notnull',
   					field : {
       	        		xtype : 'numberfield',
       	        		minValue:0,
       	        		listeners:{  
							'change': function(obj, newValue, oldValue, eOpts) {
								if(newValue!=oldValue){
									var data = Ext.getCmp('gridD101').getSelectionModel()
										.getSelection();
									data[0].set('realQty', newValue
										* data[0].get('packingQty')
										+ data[0].get('realQmin') * data[0].get('qminOperatePacking')
										+ data[0].get('realDis'));
								}
							}
		      			}
   					}
				},{
					width : 85,
					text : $i18n.planRealQmin,//实际中包数
					dataIndex : 'realQmin',
					id:'realQmin_D101',
					align:'center',
   					cls : 'notnull',
   					field : {
       	        		xtype : 'numberfield',
       	        		minValue:0,
       	        		listeners:{  
							'change': function(obj, newValue, oldValue, eOpts) {
								if(newValue!=oldValue){
									var data = Ext.getCmp('gridD101').getSelectionModel()
										.getSelection();
									data[0].set('realQty', 
											data[0].get('realBox')* data[0].get('packingQty')
										  + newValue * data[0].get('qminOperatePacking')
										  + data[0].get('realDis'));
								}
							}
		      			}
   					}
				},{
					width : 85,
					text : $i18n.planRealDis,//实际散数
					dataIndex : 'realDis',
					id:'realDis_D101',
					align:'center',
   					cls : 'notnull',
   					field : {
       	        		xtype : 'numberfield',
       	        		minValue:0,
       	        		listeners:{  
							'change': function(obj, newValue, oldValue, eOpts) {
								if(newValue!=oldValue){
									var data = Ext.getCmp('gridD101').getSelectionModel()
										.getSelection();
									data[0].set('realQty', 
											data[0].get('realBox')* data[0].get('packingQty')
										  + data[0].get('realQmin') * data[0].get('qminOperatePacking')
										  + newValue);
								}
							}
		      			}
   					}
				},{
   	        	   width:90,
   	        	   text:$i18n.porduce_date,//生产日期
   	        	    align:'center',
   	        	   dataIndex:'produceDate'
   	           },{
   	        	   width:90,
   	        	   text:$i18n.expire_date,//有效期至
   	        	    align:'center',
   	        	   dataIndex:'expireDate'
   	           },{
   	        	   width:80,
   	        	   text:$i18n.quality,//品质
   	        	    align:'center',
   	        	   dataIndex:'quality'
   	           },{
   	        	   width:150,
   	        	   text:$i18n.lot_no,//批号
   	        	    align:'center',
   	        	   dataIndex:'lotNo'
   	           }]
        },{
			region:'south'
		}],
		dockedItems : [{
			xtype : 'pagingtoolbar',
			store:stock_AdjustAccountsStore,
			dock : 'bottom',
			displayInfo : true
		}]
});