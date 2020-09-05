Ext.define('cms.view.common.commMenu10',{
	extend:'Ext.toolbar.Toolbar',
	alias:'widget.commMenuWidget10',
	dock: 'top',
    items: [{
		text : '刷新',
		iconCls : 'refresh',
	 	//hidden:false,
		name : 'refresh'
	},{
		text : '导入',
		icon: 'system/extjs/resources/icons/fam/application_split.png',
		name : 'upload'
	},{
		text : '确认',
		iconCls : 'print',
		name : 'confirm',
	}],
	sz_df: function(th) {
        DEFARTICLE,
        console.log('sdfsfsddf');
    }
});