/**
 * hkl预约单据明细查询
 */
Ext.define('cms.view.idata.windows.idata_Order_Time_DListWindow',{
 	extend:'Ext.window.Window',
 	alias:'widget.idata_Order_Time_DListWindow',
	layout:'border',
	height:350,
	width:850,
	modal : true,
	items: [
	{
		region:'center',
	    xtype:'grid',
	    id:'grid_05_4201_d',
	    width:'100%',
	    height:'100%',
	    store:Ext.create('cms.store.idata.idata_ImPortTth_DStore'/*,{
	    	autoLoad:false,
	    	proxy:{
				type:'ajax',
				method:'post',
				url:'odata_CarPlanForXzAction_searchDetail.action',
				reader:{
					root:'rootList',
					totalProperty:'totalCount'
				}
			}
	    	}*/),
	    columns:[{
	    	xtype:'rownumberer',
	       	width:30 
		},{
			width : 120,
			text : $i18n.article_no,// 商品编码
			dataIndex : 'articleNo'
		},{
			width : 110,
			text : $i18n.owner_article_no,//货主商品编码
			dataIndex : 'ownerArticleNo'
		},{
			width : 220,
			text : $i18n.article_name,//商品名称
			dataIndex : 'articleName'
		},{
			width : 110,
			text : $i18n.barcode,//商品条码
			dataIndex : 'barcode'
		},{
			width : 85,
			text : $i18n.packing_qty,//包装数量
			dataIndex : 'packingQty'
		},{
			width : 85,
			text : $i18n.packing_unit,//包装单位
			dataIndex : 'packingUnit',
			id:'packingUnit4201'
		},{
			width : 85,
			text : $i18n.packingSpec,//规格
			dataIndex : 'packingSpec',
			id:'packingSpec4201'
		},{
			width : 85,
			text : $i18n.planBox,//采购箱数
			dataIndex : 'planBox',
			id:'planBox4201'
		},{
			width : 85,
			text : $i18n.planQmin,//中包装数
			dataIndex : 'planQmin',
			id:'planQmin4201'
		},{
			width : 85,
			text : $i18n.planDis,//采购零散数
			dataIndex : 'planDis',
			id:'planDis4201'
		},{
			width : 50,
			text:$i18n.unit_cost,//单价
			dataIndex : 'unitCost'
		}]
	}]
});