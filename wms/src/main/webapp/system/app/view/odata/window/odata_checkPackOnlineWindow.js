Ext.define('cms.view.odata.window.odata_checkPackOnlineWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.odata_checkPackOnlineWindow',
	layout:'border',
	height:350,
	width:700,
	modal : true,
	items: [
	{
		region:'center',
	    xtype:'grid',
	    id:'unCheck_list_3916',
	    width:'100%',
	    height:'90%',
		store:Ext.create('cms.store.odata.odata_chcekPackLabelStore',{
			  proxy:{
					type:'ajax',
					method:'post',
					url:'odata_CheckPackOnlineAction_getUnCheckObjList.action',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					}
			  }
					
		}),
	    multiSelect: true,  
		selModel: {  
		    selType:'checkboxmodel',
		    checkOnly:true
		},
	    columns:[{
	    	xtype:'rownumberer',
	       	width:30 
		},{
    	    width:100,
    	    text:'面单号',
    	    dataIndex:'shipperDeliverNo'
		},{
    	    width:90,
    	    text:'商品编码',
    	    dataIndex:'articleNo'
		},{
    	    width:120,
    	    text:'商品条码',
    	    dataIndex:'barcode'
		},{
    	    width:160,
    	    text:'商品名称',
    	    dataIndex:'articleName'
		},{
    	    width:60,
    	    text:'未复核数',
    	    dataIndex:'articleQty'
		},{
    	    width:60,
    	    text:'已复核数',
    	    dataIndex:'realQty'
		},{
    	    width:100,
    	    text:'配送对象',
    	    dataIndex:'deliverObj'
		},{
			width:60,
    	    text:'品项数',//数量
    	    dataIndex:'countArticleNo'
		}]
	},{	xtype : 'form',
		region:'south',
		height:'10%',
		//width:'52%',
		layout: 
		{
			type: 'table',
			columns: 1
		},
	
	    items :[{
	    	xtype: 'button',
	    	text: '转病单',
	    	margin : '5 0 0 280',
	    	id:'illCheck3916'
	    }]
		
	}]
});