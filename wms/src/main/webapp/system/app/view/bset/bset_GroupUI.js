/**
 * 模块名称：打印机组与打印机关系维护
 * 模块编码：1G01
 * 创建：Jun 
 */
var bset_GroupStore=Ext.create('cms.store.bset.bset_GroupStore',{autoLoad:true,
	listeners:{
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('grid_01_1G01').getStore().count()>0){
				Ext.getCmp('grid_01_1G01').getSelectionModel().select(0);
			}
		}
	}
});
 var bdef_DefPrinterStore=Ext.create('cms.store.bset.bdef_DefPrinterStore',{autoLoad:false});
 var bset_Printer_GroupStore=Ext.create('cms.store.bset.bset_Printer_GroupStore');
Ext.define('cms.view.bset.bset_GroupUI',{
	alias:'widget.bset_GroupUI',
	title:$i18n.title1G01,//打印机组与打印机关系维护
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
	    id:'menu1G01',
	    region:'north'
	},{
	    xtype:'grid',
	    region:'north',
	    height:240,
	    id:'grid_01_1G01',
	    store:bset_GroupStore,
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
			text : $i18n.printer_group_no,//打印机群组代码
			dataIndex:'printerGroupNo'
	    },{
			width : 200,
			text : $i18n.printer_group_name,//打印机群组名称
			dataIndex:'printerGroupName'
	    }],
	    dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:bset_GroupStore,
			displayInfo : true
	    }]
	},{
		xtype:'grid',
		id:'grid_02_1G01',
		title:$i18n.printer,//打印机
		width:'47%',
		region:'west',
		store:bdef_DefPrinterStore,
		multiSelect: true,  
	    selModel: {  
        	selType:'checkboxmodel'  
	    },
		columns:[{			
			xtype : 'rownumberer',
			width : 30
		},{
			width:85,
			text:$i18n.printer_no,//打印机代码
			dataIndex:'printerNo',
			sortable: false
		},{
			width:150,
			text:$i18n.printer_name,//打印机名称
			dataIndex:'printerName'
		},{
			width:100,
			text:$i18n.printer_type,//打印机类型
			dataIndex:'printertypeText'
		}],
		dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:bdef_DefPrinterStore,
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
			id:'right1G01'
		},
		{
			xtype:'button',			
			text:'<<<',
			id:'left1G01'
		}]
	},{
	    xtype:'grid',
	    id:'grid_03_1G01',
	    title:$i18n.printer_group_list,//打印机群组列表
	    width:'47%',
	    region:'east',
	    store:bset_Printer_GroupStore,
	    multiSelect: true,  
	    selModel: {  
	        selType:'checkboxmodel'  
	    },
	    columns:[{			
			xtype : 'rownumberer',
			width : 30
	    },{
			width:120,
			text:$i18n.printer_group_no,//打印机群组代码
			dataIndex:'printerGroupNo'
	    },{
			width:120,
			text:$i18n.printer_no,//打印机代码
			dataIndex:'printerNo'
	    },{
			width:200,
			text:$i18n.printer_name,//打印机名称
			dataIndex:'printerName'
	    }],
	    dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:bset_Printer_GroupStore,
			displayInfo : true
	    }]
	},{
    	region:'south'
	}]
});