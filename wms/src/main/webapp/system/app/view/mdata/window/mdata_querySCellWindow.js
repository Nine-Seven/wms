/**
 * 模块名称：移库管理
 * 模块编码：5101
 * zhouhuan
 */
Ext.define('cms.view.mdata.window.mdata_querySCellWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.mdata_querySCellWindow',
	id:'mdata_querySCellWindow',
	layout:'border',
    height:500,
	width:1105,
	modal:true,
    items: [{
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
				//width : 180
			},
			items : [{
				xtype : 'remoteCombo',
				fieldLabel : $i18n.owner_article_no,// 业主商品编码
				width:200,
				id:'cmbOwnerArticleNoForManW5101',
				labelWidth : 90,
				allowBlank:true,
				displayField: 'value',
				valueField: 'value',
				//beforeLabelTextTpl : required,
 	    	    store:Ext.create("cms.store.common.comboStore",
						{
							proxy:{
								type:'ajax',
								method:'post',
								url:'mdata_PlanMAction_getMdata_GetCombo.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								},
								extraParams:{
									flag : "1"
								}
							},
				   listConfig: {
		           loadingText: '查询中...',
		           emptyText: '没有找到相应的数据！' ,
		           getInnerTpl: function() {
		        	   return '{value}';
		           }
		       }
		   	})
			},{
				xtype : 'remoteCombo',
				labelWidth : 90,
 	    		fieldLabel:$i18n.s_cell_no,//来源储位
 	    		id:'cmnSCellNoForManW5101',
 	    		width:190,
 	    		allowBlank:true,
				displayField: 'value',
				valueField: 'value',
				store:Ext.create("cms.store.common.comboStore",{
						proxy:{
							type:'ajax',
							method:'post',
							url:'mdata_PlanMAction_getMdata_GetCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							},
							extraParams:{
								flag : "2"
							}
						}
				}),
				listConfig: {
		           loadingText: '查询中...',
		           emptyText: '没有找到相应的数据！' ,
		           getInnerTpl: function() {
		        	   return '{value}';
		           }
		       }
			}, {
				xtype : 'remoteCombo',
				labelWidth : 70,
 	    		fieldLabel : $i18n.barcode,//商品条码
 	    		id:'cmbBarcodeForManW5101',
 	    		width:190,
 	    		allowBlank:true,
				displayField: 'value',
				valueField: 'value',
				store:Ext.create("cms.store.common.comboStore",{
						proxy:{
							type:'ajax',
							method:'post',
							url:'mdata_PlanMAction_getMdata_GetCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							},
							extraParams:{
								flag : "3"
							}
						}
				}),
				listConfig: {
		           loadingText: '查询中...',
		           emptyText: '没有找到相应的数据！' ,
		           getInnerTpl: function() {
		        	   return '{value}';
		           }
		       }
			},{

				xtype:'wms_DefFieldValCombo',
				id : 'qulity1401',
				fieldLabel:$i18n.quality,//商品状态
				maxLength:20,
				editable:false,
				
		        store:Ext.create("cms.store.common.comboStore").load(
		        {
		        	params:{str:"N,QUALITY"}
		        })
			
			}, {
				xtype : 'button',
				margin:'3 3 0 2',
				name:'butQueryForManW5101',
				text : $i18n.query //查询
			},{
				xtype : 'button',
				name:'butSureForManW5101',
				margin:'3 0 0 3',
				text : $i18n.ok //确定
			}]
			} ]

	},{
		  region:'center',
    	  xtype:'grid',
    	  id:'gridMdataPlanMForManW5101',
    	  store:Ext.create('cms.store.mdata.mdata_StockContentStore'),
    	  multiSelect: true,  
		  selModel: {  
			   selType:'checkboxmodel',
			   checkOnly:true
		  },
    	  width:'100%',
    	  height:'100%',
    	  columns:[{
    	       	       xtype:'rownumberer',
    	       	       width:30 
    	           },{
    	        	   width:75,
    	        	   text:$i18n.s_cell_no,//来源储位
    	        	   dataIndex:'cellNo'
    	           },{
    	        	   width:90,
    	        	   text:$i18n.article_no,//商品编码
    	        	   dataIndex:'articleNo'
    	           },{
    	   			   width : 90,
    				   text : $i18n.owner_article_no,//货主商品编码
    				   dataIndex : 'ownerArticleNo'
    			   },{
    	        	   width:180,
    	        	   text:$i18n.article_name,//商品名称
    	        	   dataIndex:'articleName'
    	           },{
    	        	   width:60,
    	        	   text:$i18n.packing_qty,//包装数量
    	        	   dataIndex:'packingQty'
    	           },{
    	        	   width:50,
    	        	   text:$i18n.spec,//规格
    	        	   dataIndex:'spec'
    	           },{  width:70,
    	        	   text:$i18n.packing_unit,
    	        	   dataIndex:'unit'
    	            },{
						width : 85,
						text : $i18n.boxQty,//箱数
						dataIndex : 'planBox',
						id:'planBox5101_2',
						field : {
				    		xtype : 'numberfield',
				    		minValue:0
				    	}
					},{
						width : 85,
						text : $i18n.qminQty,//中包装数
						dataIndex : 'planQmin',
						id:'planQmin5101_2',
						field : {
				    		xtype : 'numberfield',
				    		minValue:0
				    	}
					},{
						width : 85,
						text : $i18n.disQty,//散数
						dataIndex : 'planDis',
						id:'planDis5101_2',
						field : {
				    		xtype : 'numberfield',
				    		minValue:0
				    	}
					}/*,{
    	        	   width:50,
    	        	   text:$i18n.qty1,
    	        	   dataIndex:'pkQty'
    	           }*/,{
    	        	   width:80,
    	        	   text:$i18n.porduce_date,//生产日期
    	        	   dataIndex:'produceDate'
    	           },{
    	        	   width:80,
    	        	   text:$i18n.expire_date,//有效期至
    	        	   dataIndex:'expireDate'
    	           },{
    	        	   width:80,
    	        	   text:$i18n.lot_no,//批号
    	        	   dataIndex:'lotNo'
    	           },{
    	        	   width:80,
    	        	   text:$i18n.label_no,//标签
    	        	   dataIndex:'labelNo'
    	           },{
						width:40,
						text:$i18n.quality,//'品质',
						hidden:$i18n.qualityHidden,
						dataIndex:'textQuality'
					}/*,{
    	        	   width:80,
    	        	   text:$i18n.cell_no,//储位
    	        	   dataIndex:'cellNo'
    	           },{
    	        	   width:130,
    	        	   text:$i18n.qty,//实际储位库存
    	        	   dataIndex:'qty'
    	           },{
    	        	   width:80,
    	        	   text:$i18n.available_qty,//可用数量
    	        	   dataIndex:'availableQty'
    	           },{
    	        	   width:80,
    	        	   text:$i18n.barcode,//商品条码
    	        	   dataIndex:'barCode'
    	           }*/]
				}]
});
