Ext.define('cms.view.common.commMenu9',{
	extend:'Ext.toolbar.Toolbar',
	alias:'widget.commMenuWidget9',
	dock: 'top',
	items:[{
				text : '删除',
	    	    name : 'detailDelete',
	    	    iconCls : 'delete'
	       },{
	   			text : '上移',
	   			iconCls : 'prev',
	   			name : 'prev'
	       },{
	    	    text : '下移',
	    	    iconCls : 'next',
	    	    name : 'next'
		}
	       
	       ]
});