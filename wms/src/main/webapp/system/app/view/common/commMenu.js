Ext.define('cms.view.common.commMenu',{
	extend:'Ext.toolbar.Toolbar',
	alias:'widget.commMenuWidget',
	dock: 'top',
    items: [{
		text : '上单',
		iconCls : 'prev',
		disabled:true,
		name : 'userPrevButton'
	}, {
		text : '下单',
		iconCls : 'next',
		disabled:true,
		name : 'userNextButton'
	},{
        text: '新单',
        name:'userAddButton',
        iconCls:'add'
    },{
        text: '修改',
        name:'userEditButton',
        disabled:true,
        iconCls:'edit'
    },{
        text: '撤销',
        name:'userUndoButton',
        disabled:true,
        iconCls:'undo'
    },{
		text : '保存',
		name : 'userSaveButton',
		disabled:true,
		iconCls : 'save'
		
	},{
        text: '删单',
        name:'userDelButton',
        disabled:true,
        iconCls:'delete'
    },/*{
        text: '审核',
        name:'userSendButton',
        iconCls:'send'
    },*/{
        text: '打印',
        name:'userPrintButton',
        disabled:true,
        iconCls:'print'
    }],
	sz_df: function(th) {
        DEFARTICLE,
        console.log('sdfsfsddf');
    }
});