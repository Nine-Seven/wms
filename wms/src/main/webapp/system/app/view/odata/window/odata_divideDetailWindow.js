
/**
 * 模块名称：分播明细显示
 * 模块编码   
 * @author HJ
 */
var bdef_DefarticleStore = Ext.create('cms.store.odata.odata_DivideDStore',{
	autoLoad:false,
	
	proxy:{
		type:'ajax',
		method:'post',
		url:'odata_DivideWallAction_queryDivideDetailList.action',
		reader:{
			type:'json',
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});


Ext.define('cms.view.odata.window.odata_divideDetailWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.odata_divideDetailWindow',
	layout:'border',
	width : 900,
	title:'分播明细',
	height : 400,
	modal : true,
	id : 'odata_divideDetailWindow',
	items : [{
		xtype : 'grid',
		id : 'gridDivideDetail',
		//title : $i18n.title1201tab2grid1,
		region:'west',
		width : '100%',
		store:bdef_DefarticleStore,
		columns : [/*{
    	  	xtype: 'checkcolumn',
			width : 50,
			columnHeaderCheckbox: true,//必须定义如下store							
			store: Ext.data.StoreManager.lookup('bdef_DefWorkerOwnerStore'),
			dataIndex:'flag'
        }, */{
			xtype : 'rownumberer',
			width : 30
        },{
			width : 90,
			text : '商品编码',
			dataIndex : 'articleNo',
			id : 'articleNo'
		},{
			width : 120,
			text : '商品条码',
			dataIndex : 'barcode',
			id : 'barCode'
		},{
			width : 300,
			text : '商品名称',
			dataIndex : 'articleName',
			id : 'articleName'
		},{
			width : 50,
			text : '格子号',
			dataIndex : 'dpsCellNo',
			id : 'dpsCellNo'
		},{
			width : 50,
			text : '应播量',
			dataIndex : 'articleQty',
			id : 'articleQty'
		},{
			width : 50,
			text : '已播量',
			dataIndex : 'realQty',
			id : 'realQty'
		},{
			width : 100,
			text : '来源储位',
			dataIndex : 'advanCellNO',
			id : 'advanCellNO'
		},{
			width : 100,
			text : '面单号',
			dataIndex : 'shipperDeliverNo',
			id : 'shipperDeliverNo'
		},{
			width : 100,
			text : '配送对象',
			dataIndex : 'deliverObj',
			id : 'deliverObj'
		}
		],
		dockedItems : [{
		    xtype : 'pagingtoolbar',
		    store : bdef_DefarticleStore,
		    dock : 'bottom',
		    displayInfo : true
		}] 
	}]
});








