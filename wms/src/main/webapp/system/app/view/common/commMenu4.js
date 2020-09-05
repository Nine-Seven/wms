Ext.define('cms.view.common.commMenu4',{
	extend:'Ext.toolbar.Toolbar',
	alias:'widget.commMenuWidget4',
	dock: 'top',
	defaults:{
   		hidden:true
    },
    items: [{
			text : '新增',
			iconCls : 'add',
			name : 'add'
		},{
			text : '修改',
			iconCls : 'edit',
			name : 'edit'
		},{
			text : '删除',
			iconCls : 'delete',
			name : 'delete'
		},{
			text : '撤销',
			iconCls : 'undo',
			disabled:true,
			name : 'undo'
		},{
			text : '保存',
			iconCls : 'save',
			disabled:true,
			name : 'save'
		},{
			text : '发单',
			iconCls : 'send',
			name : 'send'
		},{
			text : '查找',
			iconCls : 'query',
			name : 'query'
		},{
			text : '刷新',
			iconCls : 'refresh',
		 	hidden:false,
			name : 'refresh'
		},{
			text : '导出',
			iconCls : 'export',
			name : 'export'
		},{
			text : '打印',
			iconCls : 'print',
			name : 'print'
		}]
});