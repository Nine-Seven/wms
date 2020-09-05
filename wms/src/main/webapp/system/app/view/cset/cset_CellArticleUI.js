var cset_CellArticleStore=Ext.create('cms.store.cset.cset_CellArticleStore',{autoLoad:true});
Ext.define('cms.view.cset.cset_CellArticleUI',{
	alias:'widget.cset_CellArticleUI',
	title:$i18n.title2201,//商品货位对照关系
	layout:'border',
	requires:[
	          'cms.view.common.commMenu2',
  			  'cms.view.common.commMenu5',
  			  'cms.view.common.cdef_DefAreaCombo',
  			  'cms.view.common.cdef_DefWareCombo',
  			  'cms.view.common.cdef_DefStockCombo',
  			  'cms.view.common.wms_DefFieldValCombo',
  			  'cms.view.common.bdef_PackingQtyCombo',
  			  'cms.view.common.bdef_DefOwnerCombo',
  			  'cms.view.common.cdef_DefCellCombo',
  			  'cms.view.common.bdef_DefArticleCombo',
  			  'cms.view.common.cset_LineCombo'
	],
	extend:'Ext.panel.Panel',
	items:[
	{
		xtype : 'commMenuWidget2',
		region:'north',
		id:'menu2201'
	},
	{
		xtype:'grid',	
		region:'center',
		id:'grid_01_2201',
		width:'100%',
		height:'100%',
		store:cset_CellArticleStore,
		columns:[{
			xtype : 'rownumberer',
			width : 30
		}, /*{
			width : 120,
			text : $i18n.owner_name,//委托业主名称
			//align:'center',
			dataIndex : 'ownerName'
		}, */{
			width : 100,
			text : $i18n.owner_no,//委托业主编号
			dataIndex : 'ownerNo'
		}, {
			width : 100,
			text : $i18n.owner_article_no,//业主商品编码
			dataIndex : 'ownerArticleNo'
		}, {
			width : 120,
			text : $i18n.article_no,//商品编码
			dataIndex : 'articleNo'
		}, {
			width : 180,
			text : $i18n.article_name,//商品名称
			dataIndex : 'articleName'
		}, {
			width : 60,
			text : $i18n.ware_no,//仓区
			dataIndex : 'wareNo'
		}, {
			width : 80,
			text : $i18n.cell_no,//货位
			dataIndex : 'cellNo'
		}, {
			width : 80,
			text : $i18n.stock_no,//通道代码
			dataIndex : 'stockNo'
		}, {
			width : 80,
			text : $i18n.area_no,//储区代码
			dataIndex : 'areaNo'
		}, {
			width : 80,
			text : $i18n.line_no,//线路编号
			dataIndex : 'lineId'
		},/* {
			width : 80,
			text : '线路名称',//线路名称
			dataIndex : 'lineName'
		},*/ {
			width : 80,
			text : $i18n.packing_qty,//包装数量
			dataIndex : 'packingQty'
		}, {
			width : 100,
			text : $i18n.max_qty_box,//最大存储量（箱）
			dataIndex : 'maxQtyBox'
		}, {
			width : 100,
			text : $i18n.alert_qty_box,//补货警示量（箱）
			dataIndex : 'alertQtyBox'
		}, {
			width : 130,
			text : $i18n.supp_qty_box, //'循环补货触发量（箱）
			dataIndex : 'suppQtyBox'
		}, {
			width : 90,
			text : $i18n.keep_cells,//可用货位数
			dataIndex : 'keepCells'
		}, {
			width : 80,
			text : $i18n.pick_type,//拣货类型
			dataIndex : 'pickType'
		}],
		dockedItems : [{
			xtype : 'pagingtoolbar',
			store:cset_CellArticleStore,
			dock : 'bottom',
			displayInfo : true
		}] 
	},{
		region:'south'
	}]
	
});