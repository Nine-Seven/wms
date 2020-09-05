/**
 * 模块名称：退厂确认（天天惠）
 * 模块编码：7303
 * 创建 ： hkl
 */
var storeRodata_MComfirm7303=Ext.create('cms.store.rodata.rodata_OutstockMStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'rodata_OutstockComfirmAction_getRodata_OutstockMComfirm.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	},
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('gridRodata_MComfirm7303').getStore().count()>0){
				Ext.getCmp('gridRodata_MComfirm7303').getSelectionModel().select(0);
			}
		}
	}
});
var storeRodata_DComfirm7303=Ext.create('cms.store.rodata.rodata_OutstockDStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'rodata_OutstockComfirmAction_getRodata_OutstockDComfirm.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	},
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(th.count()>0){
				var arrayObj = new Array();
				arrayObj[0]='planBox';
				arrayObj[1]='planQmin';
				arrayObj[2]='planDis';
				arrayObj[3]='unitCost';
				arrayObj[4]='qtyPrice';
				countList('gridRodata_DComfirm7303',arrayObj,'labelNo');
			}
		}
	}
});
Ext.define('cms.view.rodata.rodata_OutstockComfirmUI',{
	alias:'widget.rodata_OutstockComfirmUI',
	title:$i18n.title7303,//退厂确认
	id:'rodata_OutstockComfirmUI7303',
	width:'100%',
	layout:'border',
	extend : 'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu4',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.wms_DefFieldValCombo'

	          ],
	items:[{
	    		xtype:'commMenuWidget4',
	   		   	region:'north',
	   		   	id:'menu7303'
	       },{
			    xtype : 'grid',
				id:'gridRodata_MComfirm7303',
				store:storeRodata_MComfirm7303,
				region : 'center',
				columns : [ {
					xtype : 'rownumberer',
					width : 30
				},{
					width : 160,
					text : $i18n.po_nos,//退货单号
					dataIndex : 'poNo'
				},{
					width : 80,
					text : $i18n.classType,//退货类型
					dataIndex : 'classType'
				},{
					width : 80,
					text : $i18n.supplier_no,//供应商编码
					dataIndex : 'supplierNo'
				},{
					width : 160,
					text : $i18n.supplier_name,//供应商名称
					dataIndex : 'supplierName'
				},{
					width : 120,
					text : $i18n.status2,//操作状态
					dataIndex:'statusText'					
				}]/*,
				dockedItems : [{
	    	        xtype : 'pagingtoolbar',
	    	        store:storeRodata_MComfirm7303,
	    	        dock : 'bottom',
	    	        displayInfo : true
	    	    }]*/
			},{
				xtype : 'form',
				layout:'column',
				region : 'east',
				id:'formRodata_outstockm7303_2',
				frame : true,
				width : '35%',
				layout:{
					type : 'table',
					columns : 1
				},
				items:[{
					xtype:'fieldset',
					margin:'0 0 0 0',
					title: '过滤条件',
					defaults:{
						labelWidth : 90,
						margin : '5 0 5 0',
						labelAlign : 'right',
						xtype:'textfield'
					},
					items:[
					{
						xtype : 'wms_DefFieldValCombo',
						fieldLabel : $i18n.suppliers,// 供应商
						id:'cmbSuppliers7303',
						displayField: 'dropValue',
    					valueField: 'value',
						store : Ext.create("cms.store.common.comboStore",{
							proxy:{
								type:'ajax',
								method:'post',
								url:'rodata_OutstockComfirmAction_getRodata_OutstockComfirmCombo.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								},
								extraParams:{
									strFlag : "2"
								}
							}
						})
					},{
						xtype : 'numberfield',
						fieldLabel : $i18n.GrossWeight,//毛重
						id:'txtGrossWeight7303'
					}]
				},{
					xtype:'fieldset',
					margin:'0 0 0 0',
					title: '操作',
					defaults:{
						labelWidth : 90,
						margin : '5 0 5 0',
						labelAlign : 'right',
						xtype:'textfield'
					},
					items:[{
						xtype : 'button',
			    	    margin:'0 0 0 95',
			    	    id:'btnConfirm7303',
			    	    text : $i18n.recede_bill_affrim //确认退厂
					}]
				}]
	         },{
					xtype : 'grid',
					id:'gridRodata_DComfirm7303',
					region : 'south',					
					store:storeRodata_DComfirm7303,
					height : 260,
					columns : [ {
						xtype : 'rownumberer',
						width : 30
					},{
						width : 120,
						text : $i18n.label_no,//标签号
						dataIndex:'labelNo'
					},{
						width : 120,
						text : $i18n.article_no,//商品编码
						dataIndex:'articleNo'
					},{
						width : 100,
						text : $i18n.owner_article_no,//货主商品编码
						dataIndex : 'ownerArticleNo'
					},{
						width : 180,
						text : $i18n.article_name,//商品名称
						dataIndex:'articleName'
					},{
						width : 120,
						text : $i18n.barcode,//商品条码
						dataIndex:'barcode'
					},{
						width : 80,
						text : $i18n.packing_qty,//包装数量
						dataIndex:'packingQty'
					},{
						width : 80,
						text : $i18n.packing_unit,//包装单位
						dataIndex:'packingUnit',
						id:'packingUnit7303'
					},{
						width : 85,
						text : $i18n.packingSpec,//规格
						dataIndex : 'packingSpec',
						id:'packingSpec7303'
					},{
						width : 85,
						text : $i18n.boxQty,//计划箱数
						dataIndex : 'planBox',
						//hidden:true,
						id:'planBox7303',
//						cls : 'notnull',
						field : {
				    		xtype : 'numberfield',
				    		minValue:0
				    	}
					},{
						width : 85,
						text : $i18n.qminQty,//计划中包数
						dataIndex : 'planQmin',
						id:'planQmin7303',
//						cls : 'notnull',
						field : {
				    		xtype : 'numberfield',
				    		minValue:0
				    	}
					},{
						width : 85,
						text : $i18n.disQty,//计划散数
						dataIndex : 'planDis',
						id:'planDis7303',
//						cls : 'notnull',
						field : {
				    		xtype : 'numberfield',
				    		minValue:0
				    	}
					}/*,{
						width : 80,
						text : $i18n.real_qty3,//实际箱数
						dataIndex:'realWholenum'
					}*/, {
						width : 80,
						text : $i18n.unit_cost,//单价
						dataIndex:'unitCost'
					},{
						width : 80,
						text : $i18n.qty_price,//总金额
						dataIndex:'qtyPrice'
					}]
					},{
						xtype : 'panel',
						id : 'msterForm7303',
						region:'south',
						html : '<div class="view_footer" style="margin:0; padding: 8px 20px 8px 20px;width:100% ;'
								+ 'background-color:#C1D5ED; text-align: left;">'
								+ '<span><label>新增人:</label><label id="rgstName7303"></label> </span> '
								+ '<span><label>&nbsp;&nbsp;&nbsp;新增时间：</label><label id="rgstDate7303"></label></span>'
								+ '<span><label>&nbsp;&nbsp;&nbsp;修改人：</label><label id="updtName7303"></label> </span> '
								+ '<span><label>&nbsp;&nbsp;&nbsp;修改时间：</label><label id="updtDate7303"></label> </span></div>'
					}
	       ]          
});