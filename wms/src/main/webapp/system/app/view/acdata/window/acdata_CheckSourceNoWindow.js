Ext.define('cms.view.acdata.window.acdata_CheckSourceNoWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.acdata_CheckSourceNoWindow',
	id:'acdata_CheckSourceNoWindow',
	layout:'border',
	height:350,
	width:850,
	modal : true,
    items: [
            {
		xtype : 'form',
		region : 'north',
		width:'100%',
		frame : true,
		items : [{	xtype : 'button',
				name:'butSureForManWC101',
				margin:'3 0 0 3',
				text : $i18n.ok //确定
			}]
	},
	{
		region:'center',
	    xtype:'grid',
	    id:'grid_01_C101_d',
  	    multiSelect: true,  
//	    selModel: {  
//			   selType:'checkboxmodel',
//			   checkOnly:true
//		  },
	    width:'100%',
	    height:'100%',
	    store:Ext.create('cms.store.acdata.acdata_CheckSourceNo_Store',{autoLoad:false}),
	    columns:[{
	    	xtype:'rownumberer',
	       	width:30 
		},{
    	    width:100,
    	    text:$i18n.source_no1,//货主单号
    	    dataIndex:'sourceNo'
		},{
			width:120,
    	    text:$i18n.order_no,//接单单号
    	    dataIndex:'orderNo'
		},{
			width:150,
    	    text:$i18n.acdata_owner,//发货人
    	    dataIndex:'ownerAlias'
		},{
			width:130,
    	    text:$i18n.acdata_cust,//收货人
    	    dataIndex:'custAlias'
		}]
	}]
});