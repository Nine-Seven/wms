/**
 * 模块名称：1、系统参数配置
 * 模块编码：1K01
 * 创建：zhm
 */

var workstationStore=Ext.create('cms.store.bdef.bdef_WmsDefbaseStore',{autoLoad:true});
Ext.define('cms.view.bdef.bdef_WmsDefbaseUI',{
	alias:'widget.bdef_WmsDefbaseUI',
	title:$i18n.title1K01,//工作站与打印机组关系维护
	layout:'border',
	extend:'Ext.panel.Panel',
	
	requires:[
		'cms.view.common.commMenu2',
		'cms.view.common.commMenu5',
		'cms.view.common.wms_DefFieldValCombo',
		'cms.view.common.bset_GroupCombo',
		'cms.view.common.bdef_DefOwnerCombo',
		'cms.store.common.comboStore'
		],
	items:[
	   	{
			xtype : 'commMenuWidget2',
			id:'menu1K01',
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
				id : 'cmbGroupNo1K01',
				displayField: 'dropValue',
				valueField: 'value',
				queryMode: 'local',
			   store:Ext.create("cms.store.common.comboStore",{
					proxy:{
						type:'ajax',
						method:'post',
						url : 'bdef_WmsDefbaseAction_getAllFromGroupNoCombo.action',
				        reader: {
				    		type:'json',
				            root: 'rootList',    
				            totalProperty: 'totalCount'
				        }
				    }
					}).load()
			},{
			 	xtype:'combo',
				fieldLabel : $i18n.w_sub_group_no,//供应商编号
			 	id:'cmbSubGroupNo1K01',
    	        displayField: 'dropValue',
				valueField: 'value',
				queryMode: 'local',
				store:Ext.create("cms.store.common.comboStore",{
					proxy:{
						type:'ajax',
						method:'post',
						url : 'bdef_WmsDefbaseAction_getSubGroupNoCombo.action',
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
			id:'grid_01_1K01',
			store:workstationStore,
			columns:[{			
				xtype : 'rownumberer',
				width : 30
			}
			,{
				width : 60,
				text : $i18n.w_group_no,
				dataIndex : 'groupNoText'
			},
			{
				width : 120,
				text : $i18n.w_sub_group_no,
				dataIndex : 'subGroupNoText'
			},{
				width : 150,
				text : $i18n.colname,
				dataIndex : 'colname'
			},{
				width : 100,
				text : $i18n.user_level,
				dataIndex : 'useLevel'
			},{
				width : 70,
				text : $i18n.sdefine,
				dataIndex : 'sdefineText'
			},{
				width : 70,
				text : $i18n.ndefine,
				dataIndex : 'ndefine'
			},{
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