/**
 * 模块名称：接口配置
 * 模块编码：I301
 * 创建：czh
 */
var interfacePlatStore=Ext.create('cms.store.bdef.bdef_WmsInterfacePlatStore',{autoLoad:true});
Ext.define('cms.view.bdef.bdef_WmsInterfacePlatUI',{
	alias:'widget.bdef_WmsInterfacePlatUI',
	title:'接口配置',//接口配置
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
		'cms.view.common.commMenu4',
		],
	items:[
		{
			xtype : 'commMenuWidget4',
			id:'menuI301',
	    	region:'north'
		},
		{
			xtype:'grid',		
			region:'center',
			id:'interfacePlatGridI301',
			width:'100%',
			height:'100%',
			store:interfacePlatStore,
			columns:[{			
				xtype : 'rownumberer',
				width : 30
			}, {
				width : 90,
				text : '接入机构类型',
				dataIndex : 'platType'
			}, {
				width : 100,
				text : '接入机构编号',
				dataIndex : 'platNo'
			}, {
				width : 90,
				text : '企业号',
				dataIndex : 'enterpriseNo'
			}, {
				width : 100,
				text : '仓库编号',
				dataIndex : 'warehourceNo'
			},{
				width : 90,
				text : 'API名称',
				dataIndex : 'apiNo'
			},{
				width : 100,
				text : 'URL地址',
				dataIndex : 'urlAddress'
			}, {
				width : 90,
				text : '数据格式',
				dataIndex : 'dataFormat'
			}, {
				width : 100,
				text : '客户编码',
				dataIndex : 'custCode'
			}, {
				width : 100,
				text : 'APP_KEY',
				dataIndex : 'appKey'
			},{
				width : 100,
				text : '安全令牌',
				dataIndex : 'appSecret'
			}, {
				width : 90,
				text : '访问令牌',
				dataIndex : 'accessTokey'
			}, {
				width : 100,
				text : '回调URL地址',
				dataIndex : 'callbackAddress'
			}
			],dockedItems : [{
					xtype : 'pagingtoolbar',
					store : interfacePlatStore,
					dock : 'bottom',
					displayInfo : true
				}] 
		},{
		region:'south'
		}]
});