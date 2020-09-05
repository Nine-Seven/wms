Ext.define('cms.view.Menu', {
	extend : 'Ext.tree.Panel',
	alias : 'widget.mmrmenu',
	initComponent : function() {
		Ext.apply(this, {
			id : 'menu-panel',
			title : '系统菜单',
			iconCls : 'icon-menu',
			margins : '0 0 -1 1',
			region : 'west',
			bodyBorder: false,
			enableDD : false,
			split : true,
			width : 200,
			minSize : 130,
			maxSize : 300,
			rootVisible : false,
			containerScroll : true,
			collapsible : true,
			autoScroll : false,
			store : Ext.create('cms.store.Menus'),
			tbar : [ {
				xtype : 'button',
				text : '展开',
				action : 'expand',
				iconCls:'zk'
			}, {
				xtype : 'button',
				text : $i18n.hello,//'缩收',
				action : 'collapse',
				iconCls:'sx'
			}, '->', {
				xtype : 'button',
				text : '注销系统',
				action : 'logout',
				iconCls : 'toend'
			} ]
		});
		this.callParent(arguments);
	}
});
