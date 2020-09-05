/*
 * 出货自提确认
 * hkl
 * 3919
 */
var storeOdata_MComfirm3919=Ext.create('cms.store.odata.odata_ExpMStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_OJComfirmAction_getOdata_OJComfirmM.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	},
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('gridOdata_OJComfirmM3919').getStore().count()>0){
				Ext.getCmp('gridOdata_OJComfirmM3919').getSelectionModel().select(0);
			}
		}
	}
});
var storeOdata_DComfirm3919=Ext.create('cms.store.odata.odata_ExpDStore',{
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_OJComfirmAction_getOdata_OJComfirmDComfirm.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});
Ext.define('cms.view.odata.odata_OJConfirmUI',{
	alias:'widget.odata_OJConfirmUI',
	title:$i18n.title3919,//自提确认
	id:'odata_OJConfirmUI3919',
	width:'100%',
	layout:'border',
	extend : 'Ext.panel.Panel',
	requires:[
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.wms_DefFieldValCombo'

	          ],
	items:[{
				xtype:'toolbar',
			    region:'north',
				items:[
				{
					text : '查找',
					iconCls : 'query',
					id : 'detailQuery'
			    },{
					text : '刷新',
					iconCls : 'refresh',
				 	//hidden:false,
					id : 'refresh'
				},{
					text : '确认',
					iconCls : 'print',
					id : 'confirm'
				}]
	       },{
			    xtype : 'grid',
				id:'gridOdata_OJComfirmM3919',
				store:storeOdata_MComfirm3919,
				region : 'center',
				columns : [ {
					xtype : 'rownumberer',
					width : 30
				},{
					width : 150,
					text : $i18n.source_no,//出货单号（原单号）
					dataIndex : 'sourceexpNo'
				},{
					width : 100,
					text : $i18n.sourceexp_type,//出货类型
					dataIndex : 'expType'
				},{
					width : 80,
					text : $i18n.cust_no,//客户编码
					dataIndex : 'custNo'
				},{
					width : 160,
					text : $i18n.cust_name,//客户名称
					dataIndex : 'custName'
				},{
					width : 120,
					text : $i18n.status2,//操作状态
					dataIndex:'statusText'					
				}]
			},{
					xtype : 'grid',
					id:'gridOdata_DComfirm3919',
					region : 'south',					
					store:storeOdata_DComfirm3919,
					height : 260,
					columns : [ {
						xtype : 'rownumberer',
						width : 30
					},{
						width : 120,
						text : $i18n.label_no,//标签号
						dataIndex:'labelNo'
					},{
						width : 60,
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
						width : 70,
						text : $i18n.realQty,//实际数量
						dataIndex:'realQty',
					    id:'realqty3919'
					},{
						width : 70,
						text : $i18n.realBox,//实际箱数
						dataIndex : 'realBox',
						id:'realBox3919'
					},{
						width : 75,
						text : $i18n.realQmin,//实际中包数
						dataIndex : 'realQmin',
						id:'realQmin3919'
					},{
						width : 75,
						text : $i18n.realDis,//实际散数
						dataIndex : 'realDis',
						id:'realDis3919'
					},{

						width : 75,
						text : $i18n.packing_unit,
						id:'packingUnit3919',
						dataIndex:'packingUnit'
					
					},
					{
					    width:75,
					    text:$i18n.packingSpec,//规格
					    id:'packingSpec3919',
					    dataIndex:'packingSpec'
		    	    }]
					}
	       ]          
});