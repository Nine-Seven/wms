/**
 * 模块名称：承运商资料维护
 * 模块编码：1E01
 * 创建：周欢
 */
var defShipperStore = Ext.create("cms.store.bdef.bdef_DefShipperStore",{autoLoad:true,
	listeners:{
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('grid_01_1E01').getStore().count()>0){
				Ext.getCmp('grid_01_1E01').getSelectionModel().select(0);
			}
		}
	}
});

var oset_DefLineStore=Ext.create('cms.store.oset.oset_DefLineStore',{autoLoad:true,
	proxy:{
		type:'ajax',
		method:'post',
		url:'bdef_DefShipperAction_queryLineList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});

var oset_ShipperLineStore=Ext.create('cms.store.oset.oset_ShipperLineStore');

Ext.define('cms.view.bdef.bdef_DefShipperUI',{
	alias:'widget.bdef_DefShipperUI',
	title:$i18n.title1E01,//承运商与线路关系维护
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu2',
	          'cms.view.common.commMenu5',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.wms_DefFieldValCombo'
	          ],
	items:[
    {
	    xtype:'commMenuWidget2',
	    id:'menu1E01',
	    region:'north'
    },{
    	region:'north',
	    xtype:'grid',
	    id:'grid_01_1E01',
	    height:250,
	    store:defShipperStore,
	    columns:[
	    {
	        xtype:'rownumberer',
	        width:30
	    },{
	        width:90,
	        text:$i18n.shipper_no,//承运商编号
	        dataIndex:'shipperNo'
	    },{
	        width:110,
	        text:$i18n.shipper_name,//承运商名称
	        dataIndex:'shipperName'
	    },{
	        width:160,
	        text:$i18n.address,//地址
	        dataIndex:'address'
	    },{
	        width:120,
	        text:$i18n.contact,//联系人
	        dataIndex:'contact'
	    },{
	        width:65,
	        text:$i18n.status,//状态
	        dataIndex:'statusText'
	    },{
	        width:65,
	        text:$i18n.disprice,//里程单价
	        dataIndex:'disprice'
	    },{
	        width:65,
	        text:$i18n.graprice,//重量单价
	        dataIndex:'graprice'
	    }],
	    dockedItems : [{
	        xtype : 'pagingtoolbar',
	        store : defShipperStore,
	        dock : 'bottom',
	        displayInfo : true
	    }]
	},{
		xtype:'grid',
		id:'grid_02_1E01',
		title:'线路',
		width:'44%',
		region:'west',
		/*store:Ext.create('cms.store.oset.oset_DefLineStore',{autoLoad:true,
			proxy:{
				type:'ajax',
				method:'post',
				url:'bdef_DefShipperAction_queryLineList.action',
				reader:{
					root:'rootList',
					totalProperty:'totalCount'
				}
			}
		}),*/
		store:oset_DefLineStore,
		multiSelect: true,  
	    selModel: {  
        	selType:'checkboxmodel'  
	    },
		columns:[{			
			xtype : 'rownumberer',
			width : 30
		},{
			width:85,
			text:'线路代码',
			dataIndex:'lineNo',
			sortable: false
		},{
			width:150,
			text:'线路名称',
			dataIndex:'lineFname'
		}],
	    dockedItems : [{
	        xtype : 'pagingtoolbar',
	        store:oset_DefLineStore,
	        dock : 'bottom',
	        displayInfo : true
	    }]
	},{
		xtype : 'form',
		region : 'center',
		layout:{
			type:'table',
			columns:1
		},
		width:'6%',
		frame : true,
		defaults:{
			margin:'10 0 0 8'
		},
		items : [
		{
			xtype:'button',
			margin:'80 0 0 8',
			text:'>>>',
			id:'right1E01'
		},{
			xtype:'button',
			text:'<<<',
			id:'left1E01'
		}]
	},{
	    xtype:'grid',
	    id:'grid_03_1E01',
	    title:'承运商组线路列表',
	    width:'50%',
	    region:'east',
	    //store:Ext.create('cms.store.oset.oset_ShipperLineStore'),
	    store:oset_ShipperLineStore,
	    multiSelect: true,  
	    selModel: {  
	        selType:'checkboxmodel'  
	    },
	    columns:[{			
			xtype : 'rownumberer',
			width : 30
	    },{
			width:120,
			text:'承运商代码',
			dataIndex:'shipperNo'
	    },{
			width:120,
			text:'承运商名称',
			dataIndex:'shipperName'
	    },{
			width:120,
			text:'线路代码',
			dataIndex:'lineNo'
	    },{
			width:120,
			text:'线路名称',
			dataIndex:'lineName'
	    }],
	    dockedItems : [{
	        xtype : 'pagingtoolbar',
	        store:oset_ShipperLineStore,
	        dock : 'bottom',
	        displayInfo : true
	    }]
	},{
    	region:'south'
	}]
});