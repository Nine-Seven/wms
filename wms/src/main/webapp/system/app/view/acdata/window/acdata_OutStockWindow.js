Ext.define('cms.view.acdata.window.acdata_OutStockWindow',{
	extend : 'Ext.window.Window',
	alias:'widget.acdata_OutStockWindow',
	id:'acdata_OutStockWindow',
	layout:'border',
	height:350,
	width:570,
	modal : true,
	items: [{
			xtype : 'form',
			region : 'north',
			width:'100%',
			frame : true,
			items : [{
					xtype : 'button',
					name:'butSelectForC201',
					margin:'3 0 0 3',
					text : $i18n.ok //确定
				}]
				
	 },{
		region:'center',
	    xtype:'grid',
	    id:'grid_01_C201_m',
	    width:'100%',
	    height:'100%',
	    store:Ext.create('cms.store.acdata.acdata_LabelMStore',{autoLoad:false}),
	    multiSelect: true,  
		/*  selModel: {  
			   selType:'checkboxmodel',
			   checkOnly:true,
		  },*/
	    columns:[
	             
	              /*{
	     	    	xtype:'radio',
	     	       	width:30 
	     		},*/{
	    	xtype:'rownumberer',
	       	width:30 
		},{
    	    width:100,
    	    text:$i18n.acdata_source_NO,//货主单号
    	    dataIndex:'sourceNo'
		},{
			width:120,
    	    text:$i18n.acdata_order_NO,//接单单号
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