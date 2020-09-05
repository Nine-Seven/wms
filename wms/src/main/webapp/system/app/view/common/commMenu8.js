Ext.define('cms.view.common.commMenu8',{
	extend:'Ext.toolbar.Toolbar',
	alias:'widget.commMenuWidget8',
	dock: 'top',
    items: [{
		text : '上移',
		iconCls : 'prev',
		name : 'prevButton'
	},{
		text : '下移',
		iconCls : 'next',
		name : 'nextButton'
	}],
});


