/**
 * 模块名称：打印机群组与打印机组关系维护
 * 模块编码：1T01
 * 创建：hcx 
 */
var pntdef_PrinterGrpgatherStore=Ext.create('cms.store.bdef.pntdef_PrinterGrpgatherStore',{autoLoad:true,
	listeners:{
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('grid_01_1T01').getStore().count()>0){
				Ext.getCmp('grid_01_1T01').getSelectionModel().select(0);
			}
		}
	}
});
 var bset_GroupStore=Ext.create('cms.store.bset.bset_GroupStore',{autoLoad:true,
	 proxy:{
			type:'ajax',
			method:'post',
			url:'pntdef_PrinterGrpgatherAction_getBset_GroupList.action',
			reader:{
				root:'rootList',
				totalProperty:'totalCount'
			}
		}
 });
 var pntset_GrpgatherPrinterGroupStore=Ext.create('cms.store.bdef.pntset_GrpgatherPrinterGroupStore');
Ext.define('cms.view.bdef.pntdef_PrinterGrpgatherUI',{
	alias:'widget.pntdef_PrinterGrpgatherUI',
	title:$i18n.title1T01,//打印机组与打印机关系维护
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu2',
	          'cms.view.common.commMenu5',
	          'cms.view.common.wms_DefFieldValCombo'
	          ],
	items:[
    {
	    xtype:'commMenuWidget2',
	    id:'menu1T01',
	    region:'north'
	},{
	    xtype:'grid',
	    region:'north',
	    height:240,
	    id:'grid_01_1T01',
	    store:pntdef_PrinterGrpgatherStore,
	    columns:[
	    {			
			xtype : 'rownumberer',
			width : 30
	    },{
			width : 120,
			text : $i18n.warehouse,//仓别
			dataIndex:'warehouseNo'
	    },{
			width : 150,
			text : $i18n.grpgather_no,//打印机群组代码
			dataIndex:'grpgatherNo'
	    },{
			width : 200,
			text : $i18n.grpgather_name,//打印机群组名称
			dataIndex:'grpgatherName'
	    }],
	    dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:pntdef_PrinterGrpgatherStore,
			displayInfo : true
	    }]
	},{
		xtype:'grid',
		id:'grid_02_1T01',
		title:$i18n.printer_group,//打印机组
		width:'47%',
		region:'west',
		store:bset_GroupStore,
		multiSelect: true,  
	    selModel: {  
        	selType:'checkboxmodel'  
	    },
		columns:[{			
			xtype : 'rownumberer',
			width : 30
		},{
			width:120,
			text:$i18n.printer_group_no,//打印机组代码
			dataIndex:'printerGroupNo',
			sortable: false
		},{
			width:250,
			text:$i18n.printer_group_name,//打印机组名称
			dataIndex:'printerGroupName'
		}],
		dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:bset_GroupStore,
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
		items : [{
			xtype:'button',
			margin:'80 0 0 8',
			text:'>>>',
			id:'right1T01'
		},
		{
			xtype:'button',			
			text:'<<<',
			id:'left1T01'
		}]
	},{
	    xtype:'grid',
	    id:'grid_03_1T01',
	    title:$i18n.grpgather_printer_group,//打印机群组列表
	    width:'47%',
	    region:'east',
	    store:pntset_GrpgatherPrinterGroupStore,
	    multiSelect: true,  
	    selModel: {  
	        selType:'checkboxmodel'  
	    },
	    columns:[{			
			xtype : 'rownumberer',
			width : 30
	    },{
			width:120,
			text:$i18n.grpgather_no,//打印机群组代码
			dataIndex:'grpgatherNo'
	    },{
			width:120,
			text:$i18n.printer_group_no,//打印机组代码
			dataIndex:'printerGroupNo'
	    },{
			width:200,
			text:$i18n.printer_group_name,//打印机组名称
			dataIndex:'printerGroupName'
	    }],
	    dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:pntset_GrpgatherPrinterGroupStore,
			displayInfo : true
	    }]
	},{
    	region:'south'
	}]
});