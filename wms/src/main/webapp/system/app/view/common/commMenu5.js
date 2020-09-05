Ext.define('cms.view.common.commMenu5',{
	extend:'Ext.toolbar.Toolbar',
	alias:'widget.commMenuWidget5',
	//dock: 'top',
	items: ['->', {//按钮靠右 重点
    //items: [{
			text : '上一条记录',
			name : 'prev'
		},{
			text : '下一条记录',
			name : 'next'
		},{
			text : '新增',
			name : 'add'
		},{
			text : '保存',
			name : 'save'
		},{
			text : '关闭',
			name : 'close'
		}]
});