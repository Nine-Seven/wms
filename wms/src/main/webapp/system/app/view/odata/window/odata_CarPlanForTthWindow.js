Ext.define('cms.view.odata.window.odata_CarPlanForTthWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.odata_CarPlanForTthWindow',
	layout:'border',
	height:350,
	width:850,
	modal : true,
	items: [
	{
		region:'center',
	    xtype:'grid',
	    id:'grid_01_3912_d',
	    width:'100%',
	    height:'100%',
	    store:Ext.create('cms.store.odata.odata_LabelDTthStore',{autoLoad:false}),
	    columns:[{
	    	xtype:'rownumberer',
	       	width:30 
		},{
    	    width:100,
    	    text:$i18n.label_no1,//容器号
    	    dataIndex:'labelNo'
		},{
			width:120,
    	    text:$i18n.article_no,//商品编码
    	    dataIndex:'articleNo'
		},{
			width : 100,
			text : $i18n.owner_article_no,//货主商品编码
			dataIndex : 'ownerArticleNo'
		},{
			width:150,
    	    text:$i18n.article_name,//商品名称
    	    dataIndex:'articleName'
		},{
			width:130,
    	    text:$i18n.barcode,//商品条码'
    	    dataIndex:'barcode'
		},{
			width:100,
    	    text:$i18n.packing_qty,//包装数量
    	    dataIndex:'packingQty'
		},{
			width:100,
    	    text:$i18n.packing_unit,//包装单位
    	    dataIndex:'packingUnit'
		},{
			width:100,
    	    text:$i18n.spec,//规格
    	    dataIndex:'spec'
		},{
			width:100,
    	    text:'数量',//数量
    	    dataIndex:'poBox'
		}]
	}]
});