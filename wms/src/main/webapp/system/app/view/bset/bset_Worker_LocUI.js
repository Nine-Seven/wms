/**
 * 模块名称：用户与仓别与委托业主关系维护
 * 模块编码：1201
 * 创建：Jun 
 */
Ext.define('cms.view.bset.bset_Worker_LocUI',{
	alias:'widget.bset_Worker_LocUI',
	title:$i18n.title1201,//用户与仓别与货主关系维护
	width:'100%',
	height:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	id:'bset_Worker_LocUI',
	requires:[
	          'cms.view.common.commMenu4'
	         ],
	items:[
	{
		xtype : 'tabpanel',
	    region:'center',
	    flex : 4,
	    items : [
	    {
	    	title:$i18n.title1201tab1,//用户与仓别关系维护
	    	layout:'border',
	    	id:'tab_01_1201',
	    	itemId:'tab_01_1201i',
	    	items:[
	    	{
				xtype : 'toolbar',
				region:'north',
				items : [{
					text : $i18n.save,//保存
					iconCls : 'save',
					id:'Worker_LocSave1201'
				}]
			},{
				xtype : 'grid',
				id : 'grid_01_1201',
				title : $i18n.title1201tab1grid1,//仓别列表
				region:'west',
				width : '25%',
				store : Ext.create('cms.store.bdef.bdef_DeflocStore',{autoLoad:true}),
				columns : [ {
					width : 100,
					text : $i18n.warehouse_no,//仓别代码
					dataIndex : 'warehouseNo'
				},{
					width : 80,
					text : $i18n.warehouse_name,//仓别名称
					dataIndex : 'warehouseName'
				}]
			},{
				xtype:'grid',
				id:'grid_02_1201',
				title : $i18n.title1201tab1grid2,//用户列表
				store : Ext.create('cms.store.bdef.bdef_DefWorkerStore'),
				region:'center',
				columns:[
				{
	        	  	xtype: 'checkcolumn',
					width : 50,
					columnHeaderCheckbox: true,//必须定义如下store
					store: Ext.data.StoreManager.lookup('bdef_DefWorkerStore'),
					dataIndex:'flag'
		        },{
					width : 100,
					text : $i18n.worker_no,
					dataIndex : 'workerNo'
				},{
					width : 80,
					text : $i18n.worker_name,
					dataIndex : 'workerName'
				}]
			}]
	    },{
	    	title:$i18n.title1201tab2,//用户与委托业主关系维护
	    	layout:'border',
	    	id:'tab_02_1201',
	    	itemId:'tab_02_1201i',
	    	items:[
	    	{
				xtype : 'toolbar',
				region:'north',
				items : [ {
					text : $i18n.save,//保存
					iconCls : 'save',
					id:'Worker_OwnerSave1201'
				}]
			},{
				xtype : 'grid',
				id : 'grid_03_1201',
				title : $i18n.title1201tab2grid1,//委托业主列表
				region:'west',
				width : '25%',
				store : Ext.create('cms.store.bdef.bdef_DefOwnerStore',{
					proxy:{
						type:'ajax',
						method:'post',
						url:'bdef_DefWorkerAction_getOwnerList.action',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						}
    				},
    				autoLoad:true
				}),
				columns : [ {
					width : 100,
					text : $i18n.owner_no,//委托业主代码 
					dataIndex : 'ownerNo'
				},{
					width : 120,
					text : $i18n.owner_name,//委托业主名称
					dataIndex : 'ownerName'
				}]
			},{
				xtype:'grid',
				id:'grid_04_1201',
				title : $i18n.title1201tab2grid2,//用户列表
				store : Ext.create('cms.store.bdef.bdef_DefWorkerStore',{
					proxy:{
						type:'ajax',
						method:'post',
						url:'bdef_DefWorkerAction_getWorkerList2',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						}
					}
				}),
				region:'center',
				columns:[
				{
	        	  	xtype: 'checkcolumn',
					width : 50,
					columnHeaderCheckbox: true,//必须定义如下store
					store: Ext.data.StoreManager.lookup('bdef_DefWorkerStore'),
					dataIndex:'flag'
		        },{
					width : 100,
					text : $i18n.worker_no,
					dataIndex : 'workerNo'
				},{
					width : 80,
					text : $i18n.worker_name,
					dataIndex : 'workerName'
				}]
			}]
	    }]
	}]
});