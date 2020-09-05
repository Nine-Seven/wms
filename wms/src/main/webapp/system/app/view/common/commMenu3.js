Ext.define('cms.view.common.commMenu3',{
	extend:'Ext.toolbar.Toolbar',
	alias:'widget.commMenuWidget3',
	dock: 'top',
    items: [{
				text : '查找',
				iconCls : 'query',
				name : 'detailQuery'
			},{
				text : '打印',
				iconCls : 'print',
				name : 'detailPrint'
			}]
});