/**
 * 模块名称：工作站与设备组关系维护
 * 模块编码：1U01
 * 创建：hcx 
 */
var divideGroupStore=Ext.create('cms.store.bdef.DeviceDivideGroupStore',{autoLoad:true,
	listeners:{
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('grid_01_1U01').getStore().count()>0){
				Ext.getCmp('grid_01_1U01').getSelectionModel().select(0);
			}
		}
	}
});
 var workstationStore=Ext.create('cms.store.bdef.bdef_DefWorkstationStore',{autoLoad:true,
	 proxy:{
			type:'ajax',
			method:'post',
			url:'bset_workstationDivideAction_getBsetPrinterWorkstationList.action',
			reader:{
				root:'rootList',
				totalProperty:'totalCount'
			}
		}
 });
 var bset_WorkstationDivideStore=Ext.create('cms.store.bset.bset_WorkstationDivideStore');
Ext.define('cms.view.bset.bset_WorkstationDivideUI',{
	alias:'widget.bset_WorkstationDivideUI',
	title:$i18n.title1U01,//工作站与设备组关系维护
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu3',
	          'cms.view.common.commMenu5',
	          'cms.view.common.wms_DefFieldValCombo'
	          ],
	items:[
    {
	    xtype:'commMenuWidget3',
	    id:'menu1U01',
	    region:'north'
	},{
	    xtype:'grid',
	    region:'north',
	    height:240,
	    id:'grid_01_1U01',
	    store:divideGroupStore,
	    columns:[
	    {			
			xtype : 'rownumberer',
			width : 30
	    }/*,{
			width : 120,
			text : $i18n.warehouse,//仓别
			dataIndex:'warehouseNo'
	    }*/,{
			width : 150,
			text : $i18n.device_group_no,//设备组编号
			dataIndex:'deviceGroupNo'
	    },{
			width : 200,
			text : $i18n.device_group_name,//设备组名称
			dataIndex:'deviceGroupName'
	    }],
	    dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:divideGroupStore,
			displayInfo : true
	    }]
	},{
		xtype:'grid',
		id:'grid_02_1U01',
		title:$i18n.dock_no2,//工作站
		width:'47%',
		region:'west',
		store:workstationStore,
		multiSelect: true,  
	    selModel: {  
        	selType:'checkboxmodel'  
	    },
		columns:[{			
			xtype : 'rownumberer',
			width : 30
		},{
			width:120,
			text:$i18n.workstation_no,//工作站编号
			dataIndex:'workstationNo',
			sortable: false
		},{
			width:250,
			text:$i18n.workstation_name,//工作站名称
			dataIndex:'workstationName'
		}],
		dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:workstationStore,
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
			id:'right1U01'
		},
		{
			xtype:'button',			
			text:'<<<',
			id:'left1U01'
		}]
	},{
	    xtype:'grid',
	    id:'grid_03_1U01',
	    title:$i18n.device_group_list,//设备组列表
	    width:'47%',
	    region:'east',
	    store:bset_WorkstationDivideStore,
	    multiSelect: true,  
	    selModel: {  
	        selType:'checkboxmodel'  
	    },
	    columns:[{			
			xtype : 'rownumberer',
			width : 30
	    },{
			width:120,
			text:$i18n.device_group_no,//设备组编号
			dataIndex:'deviceGroupNo'
	    },{
			width:120,
			text:$i18n.workstation_no,//工作站编号
			dataIndex:'workstationNo'
	    },{
			width:200,
			text:$i18n.workstation_name,//工作站名称
			dataIndex:'workstationName'
	    }],
	    dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:bset_WorkstationDivideStore,
			displayInfo : true
	    }]
	},{
    	region:'south'
	}]
});