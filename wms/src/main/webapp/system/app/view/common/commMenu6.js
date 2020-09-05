Ext.define('cms.view.common.commMenu6',{
	extend:'Ext.toolbar.Toolbar',
	alias:'widget.commMenuWidget6',
	dock: 'top',
	items:[
	       {
	    	   text : '新增',
	    	   name : 'detailAdd',
	    	   iconCls : 'add'
	       }, {
	    	   text : '删除',
	    	   name : 'detailDelete',
	    	   iconCls : 'delete'
	       }
	       ]
});