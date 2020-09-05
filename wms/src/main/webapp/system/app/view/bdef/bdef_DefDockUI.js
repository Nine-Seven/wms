/**
 * 模块名称：码头维护
 * 模块编码：1I01
 * 创建：Huan
 */
var dockStore=Ext.create('cms.store.bdef.bdef_DefDockStore',{autoLoad:true});
Ext.define('cms.view.bdef.bdef_DefDockUI',{
	alias:'widget.bdef_DefDockUI',
	title:$i18n.title1I01,//码头维护
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
		'cms.view.common.commMenu2',
		'cms.view.common.commMenu5',
		'cms.view.common.remoteCombo',
		'cms.view.common.wms_DefFieldValCombo',
		'cms.view.common.bdef_DefDockCombo'
		],
	items:[
		{
			xtype : 'commMenuWidget2',
			id:'menu1I01',
	    	region:'north'
		},{
		    xtype:'form',
		    id:'formDock1I01',
		   /* region:'north',
		    right: 0,
			frame : true,*/
		    layout:'column',
			frame : true,
			region : 'north',
			width : '100%',
			height:'6%',
			items:[{
			    layout:{
					type : 'table',
					columns : 4
				},
				xtype:'container',
				defaults:{
					margin : '2 2 2 0',
					labelAlign : 'right',
					xtype:'textfield'
				},
		    items:[{
				xtype : 'remoteCombo',
				fieldLabel:$i18n.dock_no3,		//码头号
				id:'dockNo1I01',
				store : Ext.create("cms.store.idata.idata_PoNoStore",{
					proxy:{
						type:'ajax',
						method:'post',
						url:'bdef_DefDockAction_getDockComboList.action',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						}
					}
				})
		    },{
	 	    	xtype:'wms_DefFieldValCombo',		//码头类型
	 	    	fieldLabel:$i18n.dock_type,
	 	    	id:'dock_type1I011',
	 	    	store:Ext.create("cms.store.common.comboStore").load(
				{
						params:{str:"N,DOCK_TYPE"}
				}),
	 	    	//allowBlank:false,
	 	    	//beforeLabelTextTpl : required
	 	    },{
				xtype:'button',
				id:'btnSearch1I01',
			  	text: '查询'
			}
		    ]}
			]
		},
		{
			xtype:'grid',		
			region:'center',
			id:'dockGrid1I01',
			width:'100%',
			height:'100%',
			store:dockStore,
			columns:[{			
				xtype : 'rownumberer',
				width : 30
			}, {
				width : 90,
				text : $i18n.dock_no3,
				dataIndex : 'dockNo'
			}, {
				width : 100,
				text : $i18n.dock_name3,
				dataIndex : 'dockName'
			}, {
				width : 90,
				text : $i18n.dock_type,
				dataIndex : 'dockTypeText'
			}, {
				width : 100,
				text : $i18n.adjust_board,
				dataIndex : 'adjustBoardText'
			}, {
				width : 100,
				text : $i18n.locate_type,
				dataIndex : 'locateTypeText'
			}
			],dockedItems : [{
					xtype : 'pagingtoolbar',
					store : dockStore,
					dock : 'bottom',
					displayInfo : true
				}] 
		},{
		region:'south'
		}]
});