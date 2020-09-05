/** 模块名称：1、仓别下的参数配置
 * 模块编码：1L01
 * 创建：zhm
 */

var workstationStore=Ext.create('cms.store.bdef.bdef_WmsWarehouseBaseStore',{autoLoad:true});
Ext.define('cms.view.bdef.bdef_WmsWarehouseBaseUI',{
	alias:'widget.bdef_WmsWarehouseBaseUI',
	title:$i18n.title1L01,//仓别参数维护
	layout:'border',
	extend:'Ext.panel.Panel',
	
	requires:[
		'cms.view.common.commMenu2',
		'cms.view.common.commMenu5',
		'cms.store.common.comboStore'
		],
	items:[
	   	{
			xtype : 'commMenuWidget2',
			id:'menu1L01',
	    	region:'north'
		},	
		{
			xtype:'panel',
			region:'north',
			height: 35,
			layout: {
			    type: 'table',
		        columns: 3
			},
		    defaults : {
				xtype : 'textfield',
				margin : '3 3 3 3',
				labelAlign:'right',
				allowBlank: true,
				width : 280,
				labelWidth : 90
			},
			items:[{
				xtype : 'combo',
				fieldLabel : $i18n.w_group_no,// 模块
				id : 'cmbGroupNo1L01',
				displayField: 'dropValue',
				valueField: 'value',
				queryMode: 'local',
			   store:Ext.create("cms.store.common.comboStore",{
					proxy:{
						type:'ajax',
						method:'post',
						url : 'bdef_WmsWarehouseBaseAction_getAllFromGroupNoCombo.action',
				        reader: {
				    		type:'json',
				            root: 'rootList',    
				            totalProperty: 'totalCount'
				        }
				    }
					}).load()
			},{
			 	xtype:'combo',
				fieldLabel : $i18n.w_sub_group_no,//子模块
			 	id:'cmbSubGroupNo1L01',
    	        displayField: 'dropValue',
				valueField: 'value',
				queryMode: 'local',
				store:Ext.create("cms.store.common.comboStore",{
					proxy:{
						type:'ajax',
						method:'post',
						url : 'bdef_WmsWarehouseBaseAction_getSubGroupNoCombo.action',
				        reader: {
				    		type:'json',
				            root: 'rootList',    
				            totalProperty: 'totalCount'
				        }
				    }
					})
			}]
		},
		{
			xtype:'grid',		
			region:'center',
			id:'grid_01_1L01',
			store:workstationStore,
			columns:[{			
				xtype : 'rownumberer',
				width : 30
			},
			{
			width : 100,
			text : $i18n.warehouse,
			//dataIndex : 'warehouseNo'
			dataIndex : 'wareHouseText'  //8-4
			},
			{
				width : 60,
				text : $i18n.w_group_no,
				dataIndex : 'groupNoText'
			},
			{
				width : 120,
				text : $i18n.w_sub_group_no,
				dataIndex : 'subGroupNoText'
			},
			{
				width : 150,
				text : $i18n.colname,
				dataIndex : 'colname'
			},
			{
			width : 70,
			text : $i18n.sdefine,
			dataIndex : 'sdefineText'
			},
			{
			width : 70,
			text : $i18n.ndefine,
			dataIndex : 'ndefine'
			},
			{
			width : 500,
			text : $i18n.memo,
			dataIndex : 'memo'
		}],
		dockedItems : [{
			xtype : 'pagingtoolbar',
			store : workstationStore,
			dock : 'bottom',
			displayInfo : true
		}] 
	   },
	 	{
		   region:'south'
		}]
});