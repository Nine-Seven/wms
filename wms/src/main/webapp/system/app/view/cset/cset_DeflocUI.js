/**
 * 仓别维护
 * 2501
 */
var cset_deflocStore=Ext.create('cms.store.cset.cset_deflocStore',{autoLoad:true});

Ext.define('cms.view.cset.cset_DeflocUI',{
	alias:'widget.cset_DeflocUI',
	title:'仓别维护',//仓别维护
	layout:'border',
	requires:[
	          'cms.view.common.commMenu2',
  			  'cms.view.common.commMenu5'
	],
	extend:'Ext.panel.Panel',
	items:[
	{
		xtype : 'commMenuWidget2',
		region:'north',
		id:'menu2501'
	},
	{
		xtype:'grid',	
		region:'center',
		id:'grid_01_2501',
		width:'100%',
		height:'100%',
		store:cset_deflocStore,
		columns:[{
			xtype : 'rownumberer',
			width : 30
		},{
			width : 100,
			text : $i18n.warehouse_no,//仓别代码
			dataIndex : 'warehouseNo'
		},{
			width : 100,
			text : $i18n.warehouse_name,//仓别名称
			dataIndex : 'warehouseName'
		},{
			width : 100,
			text : $i18n.paper_type,//单据类型
			dataIndex : 'creatFlagText'
		},{
			width : 100,
			text : $i18n.adress,//单据类型
			dataIndex : 'adress'
		},{
			width : 100,
			text : $i18n.linkman,//负责人
			dataIndex : 'linkman'
		},{
			width : 100,
			text : $i18n.tel,//电话
			dataIndex : 'tel'		
		},{
			width : 100,
			text : $i18n.manageName,//管理员
			dataIndex : 'manageName'
		},{
			width : 100,
			text : $i18n.province,//省
			dataIndex : 'province'
		},{
			width : 100,
			text : $i18n.city,//市
			dataIndex : 'city'
		},{
			
			width : 100,
			text : $i18n.zone,//区
			dataIndex : 'zone'
		},{
			width : 100,
			text : $i18n.memo,//备注
			dataIndex : 'memo'
		}],
		dockedItems : [{
			xtype : 'pagingtoolbar',
			store:cset_deflocStore,
			dock : 'bottom',
			displayInfo : true
		}] 
	},{
		region:'south'
	}]
	
});