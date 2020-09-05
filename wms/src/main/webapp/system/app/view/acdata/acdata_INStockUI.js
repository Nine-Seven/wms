/**
 * 模块名称：分拨入库
 * 模块编码：C101
 * 创建：hcx
 */
var acdata_Instock_M = Ext.create('cms.store.acdata.acdata_Instock_MStore',{autoLoad: true});
Ext.define('cms.view.acdata.acdata_INStockUI',{
	alias:'widget.acdata_INStockUI',
	title:$i18n.titleC101,//分拨入库
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
             'cms.view.common.commMenu3',
             'cms.view.common.remoteCombo',
             'cms.view.common.commMenu7'
	],
	items:[
	{
		xtype:'tabpanel',
		id:'tabPIdC101',
	    region:'center',
	    items:[
	    {
	    	title:$i18n.titleM,
	    	layout:'border',
	    	items:[
		    	{
		    		xtype:'commMenuWidget3',
		    		items:[
		    		{
					text : '查找',
					iconCls : 'query',
					name : 'detailQuery'
				}],
	    	    region:'north'
	    	},{
	    		xtype : 'grid',
				region:'center',
				id : 'grid_01_C101',
				width : '100%',
				height : '100%',
				store : acdata_Instock_M,
				columns : [ 
				{
					xtype : 'rownumberer',
					width : 30
				},{
					width : 150,
					text : $i18n.inStock_no2,//入库单号
					dataIndex : 'instockNo' 
				},{
					width : 150,
					text : $i18n.order_no,//接单单号
					dataIndex : 'orderNo'
				},{
					width:150,
					text:$i18n.source_no1,//货主单号
					dataIndex:'sourceNo'
				},{
					width : 150,
					text : $i18n.owner_alias1,//发货人简称
					dataIndex : 'ownerAlias'
				},{
					width : 80,
					text : $i18n.status,//状态	
					dataIndex : 'statusText'
				}],
				dockedItems : [{
					xtype : 'pagingtoolbar',
					store : acdata_Instock_M,
					dock : 'bottom',
					displayInfo : true
				}]
		     }]
	    },{
	    	title:$i18n.titleD,
	    	layout:'border',
	    	itemId:'tabPIdC101i',
	    	items:[
	    	{
				xtype:'commMenuWidget',
				region:'north',
				id:'menuC101'
			}
	    	,{
				xtype : 'form',
	    	    id : 'form_01_C101',
	    	    region:'north',
	    	    frame : true,
	    	    layout:{
    	    		type:'table',
    	    		columns:2
    	    	},
    	    	defaults : {
					xtype:'textfield',
					labelWidth : 90,
					margin : '2 2 4 2',
					labelAlign : 'right'
		  		},
	    	    items :[
	    	    {
	    	    	xtype : 'textfield',
					fieldLabel : $i18n.inStock_no2,//入库单号
					id : 'inStockNoC101',	
					readOnly:true,
					beforeLabelTextTpl : required
			    },{
				 	xtype:'combo',
					fieldLabel : $i18n.warehouse,//仓别
				 	id:'cmbWarehouseC101',
				 	readOnly:true,
	    	        displayField: 'dropValue',
					valueField: 'value',
					queryMode: 'local',
					store:Ext.create("cms.store.common.comboStore",{
						proxy:{
							type:'ajax',
							method:'post',
							url : 'bset_ValuePolicySetAction_getHouseNameComboList.action',
					        reader: {
					    		type:'json',
					            root: 'rootList',    
					            totalProperty: 'totalCount'
					        }
					    },listeners:{  
								'load':function(th,records,successful,eOpts ){
									if(th.count()>0){
										Ext.getCmp('cmbWarehouseC101').setValue(th.getAt(0).data.value);
									}
								}
							}
						}).load(),
						beforeLabelTextTpl : required
			    },
					{
			    	xtype : 'remoteCombo',
					fieldLabel : $i18n.source_no1,//货主单号  
					id : 'sourceNoC101',	
					displayField: 'sourceNo',
    				valueField: 'sourceNo',
    				readOnly:false,
					store:Ext.create("cms.store.acdata.acdata_CheckSourceNo_Store",
					{
						proxy:{
							type:'ajax',
							method:'post',
							url:'acdata_InStockAction_queryAcdataSImport.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							},
						}
					}),
					listConfig: {
			           loadingText: '查询中...',
			           emptyText: '没有找到相应的数据！' ,
			           getInnerTpl: function() {
			        	   return '{sourceNo}';
			           }
			        },
			        beforeLabelTextTpl : required
				},{
					xtype: 'button',
			    	text: '...',//货主单号选择
			    	id:'btnSearchSourceNoC101'
				},
				{	
					id:'cmbCheckOrderNoC101',
					fieldLabel : $i18n.order_no,// 接单单号
					readOnly:true,
					beforeLabelTextTpl : required
       	        },{	
					id:'cmbCheckOwnerAliasC101',
		  			fieldLabel:$i18n.acdata_owner,//发货人
					readOnly:true,
					beforeLabelTextTpl : required
       	        },{	
					id:'cmbCheckCustAliasC101',
					fieldLabel:$i18n.acdata_cust,//收货人
					readOnly:true,
					beforeLabelTextTpl : required
       	        },
				{
					fieldLabel:$i18n.remark,//备注
					readOnly:false,
					id:'remarkC101',
					maxLength:100
		  		}]
			},{
	        	xtype:'grid',
    		    id:'grid_02_C101',
    		    region:'center',
    		    loadMask : true, // 加载时有加载的图标
	    	    store : Ext.create('cms.store.acdata.acdata_Instock_DStore'),
    		    selType : 'cellmodel',
				plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
					clicksToEdit : 1,
					onSpecialKey:function(ed,field,e){
						commEnterGridStatEdit(this.grid,true,'cms.controller.acdata.acdata_INStockController',e.getKey());
					}
				})],
    		    columns:[
    		    {			
    		        xtype : 'rownumberer',
    			    width : 30
    		    },
    		    {
    			    width:200,
					text : $i18n.acdata_article_Name,//货物名称
    			    dataIndex:'articleName'			
    		    },
    		    {
				    width:150,
				    text:$i18n.acdata_barCode_NO,//条码
				    dataIndex:'barcodeNo'
    		    },
    		    {
				    width:100,
				    text:'订单件数',//订单件数
				    dataIndex:'inQty'
    		    },   		    
    		    {
    				width : 100,
    				text : '订单体积',
    				dataIndex:'inVl'
    			},
    		    {
				    width:100,
				    text : '订单重量',
				    dataIndex:'inWt'
    		    },
    		    {
				    width:100,
				    text:$i18n.acdata_iN_Qty,//入库件数
				    dataIndex:'istockQty',
				    cls:'notnull',
				    field: {
		            	xtype: 'numberfield',
		            	minValue:0,
		            }
    		    },
    		    {
				    width:100,
					text : $i18n.acdata_iN_VL,//入库体积
				    dataIndex:'istockVl',
				    cls:'notnull',
				    field: {
		            	xtype: 'numberfield',
		            	minValue:0,
		            }
    		    },
    		    {
				    width:100,
				    text:$i18n.acdata_iN_wt,//入库重量
				    dataIndex:'istockWt',
				    cls:'notnull',
				    field: {
		            	xtype: 'numberfield',
		            	minValue:0,
		            }
    		    }]
	        }]
	    }]
	}]
});