var cset_AreaBackupMStore2301=Ext.create('cms.store.cset.cset_AreaBackupMStore',{
	autoLoad:false,
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('cset_AreaBackupMGrid2301').getStore().count()>0){
				Ext.getCmp('cset_AreaBackupMGrid2301').getSelectionModel().select(0);
			}else{
				Ext.getCmp('cset_AreaBackupDGrid2301').getStore()
				.removeAll();
			}
		}
	}
	});
var cset_AreaBackupDStore2301 = Ext.create('cms.store.cset.cset_AreaBackupDStore');
Ext.define('cms.view.cset.cset_AreaBackupUI',{
	alias:'widget.cset_AreaBackupUI',
	title:$i18n.title2301,
	width:'90%',
	height:'90%',
	title:$i18n.title2301,//保拣关系设置 
	layout:'border',
	requires:[
			'cms.view.common.commMenu2',
			'cms.view.common.commMenu5',
			'cms.view.common.commMenu6',
			'cms.view.common.cdef_DefAreaCombo',
			'cms.view.common.cdef_DefWareCombo',
			'cms.view.common.cdef_DefStockCombo',
			'cms.view.common.wms_DefFieldValCombo',
			'cms.view.common.bdef_DefDockCombo'
			],
	extend:'Ext.panel.Panel',
	items:[ 	
		{
			xtype : 'commMenuWidget2',
			region:'north',
			id:'menu2301'
		},
		{
    	xtype : 'grid',
		id : 'cset_AreaBackupMGrid2301',
		store:cset_AreaBackupMStore2301,
		region:'center',
		columns : [ {
			xtype : 'rownumberer',
			width : 30
		},{
			width : 100,
			text : $i18n.line_id,//保拣线id
			dataIndex : 'lineId'
		},{
			width : 100,
			text : $i18n.line_name,//保拣线名称
			dataIndex : 'lineName'
		},{
			width : 40,
			text : $i18n.ware_no,//仓库代码
			dataIndex : 'SWareNo'
		},{
			width : 80,
			text : $i18n.ware_name,//仓库名称
			dataIndex : 'wareName',
			hidden:true
		},{
			width : 60,
			text : $i18n.area_no,//储区代码
			dataIndex : 'SAreaNo'
		},{
			width : 100,
			text : $i18n.area_name,//储区名称
			dataIndex : 'areaName',
			hidden:true
		},{
			width : 60,
			text : $i18n.stock_no,//通道代码
			dataIndex : 'SStockNo'
		},{
			width : 100,
			text : $i18n.default_flag,//是否默认值
			dataIndex : 'defaultFlagText'
		}],
		dockedItems : [{
			xtype : 'pagingtoolbar',
			store : cset_AreaBackupMStore2301,
			dock : 'bottom',
			displayInfo : true
		}] 
    },
    {
    	height : 300,
    	region:'south',
    	layout:'border',
    	items:[
			{
			    xtype:'commMenuWidget6',
			    id:'menu_2301_2',
			    region:'north',
			    items : [ {
				    	   text : $i18n.additem,
				    	   name : 'detailAdd',
				    	   iconCls : 'add'//新增
				       }, {
				    	   text : $i18n.delete_1,
				    	   name : 'detailDelete',
				    	   iconCls : 'delete'//删除
				       }, {
				    	   text : $i18n.titleupdate,
				    	   name : 'detailEdit',
				    	   iconCls : 'edit' //修改
				       }]
			},
			{
				xtype : 'grid',
				region:'center',
				store:cset_AreaBackupDStore2301,
				id : 'cset_AreaBackupDGrid2301',
				columns : [ {
					xtype : 'rownumberer',
					width : 20
				},{
					width : 35,
					text : $i18n.a_level,//级别
					dataIndex : 'ALevel'
				},{
					width : 40,
					text : $i18n.ware_no,//仓库代码
					dataIndex : 'wareNo'
				},{
					width : 80,
					text : $i18n.ware_name,//仓库名称
					dataIndex : 'wareName'
				},{
					width : 60,
					text : $i18n.area_no,//储区代码
					dataIndex : 'areaNo'
				},{
					width : 100,
					text : $i18n.area_name,//储区名称
					dataIndex : 'areaName'
				},{
					width : 60,
					text : $i18n.stock_no,//通道代码
					dataIndex : 'stockNo'
				},{
					width : 70,
					text : $i18n.keep_cells,//可用货位数
					dataIndex : 'keepCells'
				},{
					width : 120,
					text : $i18n.merger_flag,//合并标识
					dataIndex : 'mergerFlagText'
				},{
					width : 170,
					text : $i18n.stock_flag,//通道查找顺序
					dataIndex : 'stockFlagText'
				},{
					width : 170,
					text : $i18n.floor_flag,//层数查找顺序
					dataIndex : 'floorFlagText'
				},{
					width : 170,
					text : $i18n.bay_flag,//BAY查找顺序
					dataIndex : 'bayFlagText'
				},{
					width : 180,
					text : $i18n.sort_flag,//排列顺序
					dataIndex : 'sortFlagText'
				},{
					width : 170,
					text : $i18n.stockx_flag,//储格查找顺序
					dataIndex : 'stockxFlagText'
				}]
			}
        ]
    }]
});