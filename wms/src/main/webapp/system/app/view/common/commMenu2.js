Ext.define('cms.view.common.commMenu2',{
	extend:'Ext.toolbar.Toolbar',
	alias:'widget.commMenuWidget2',
	dock: 'top',
	defaults:{
   		hidden:true
    },
    items: [{
			text : '新增',
			iconCls : 'add',
			name : 'detailAdd'
		},{
			text : '修改',
			iconCls : 'edit',
			name : 'detailEdit'
		},{
			text : '浏览',
			iconCls : 'browse',
			name : 'detailBrowse'
		},{
			text : '删除',
			iconCls : 'delete',
			name : 'detailDelete'
		},{
			text : '查找',
			iconCls : 'query',
			hidden:false,
			name : 'detailQuery'
		},{							//7-9添加
			text : '刷新',
			iconCls : 'refresh',
		 	hidden:false,
			name : 'refresh'
		},{
			text : '导入',
			icon: 'system/extjs/resources/icons/fam/application_split.png',
			name : 'detailImport'
		},{
			text : '导出',
			iconCls : 'export',
			name : 'detailExport'
		},{
			text : '关闭',
			iconCls : 'delete',
			hidden:false,
			name : 'detailClose',
			handler: function() {
				this.ownerCt.ownerCt.ownerCt.activeTab.destroy();
		    }
		}]
});