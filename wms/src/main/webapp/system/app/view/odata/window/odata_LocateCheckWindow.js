/**
 * 模块名称：出货调度
 * 模块编码：3201
 * 创建：chensr
 */
Ext.define('cms.view.odata.window.odata_LocateCheckWindow',{
	extend:'Ext.window.Window',
	alias:'widget.odata_LocateCheckWindow',
	layout:'border',
	id:'odata_LocateCheckWindow',
	layout:'border',
	height:350,
	width:530,
	modal : true,
	items: [
	{
		region:'center',
	    xtype:'grid',
	    id:'windowGrid3201',
	    width:'100%',
	    height:'100%',
	    store:Ext.create('cms.store.odata.odata_checkOrderStore',{autoLoad:false}),
	    columns:[{
	    	xtype:'rownumberer',
	       	width:30 
		},
		{
    	    width:160,
    	    text : $i18n.sourceexp_no,//订单号
    	    dataIndex:'sourceexpNo'
   	    },
        {
    	    width:160,
    	    text : $i18n.source_no,//出货单号
    	    dataIndex:'expNo'
        },
        {
    	    width:160,
    	    text : $i18n.exp_qty,//计划量
    	    dataIndex:'totalQty'
        }]
    }]
});