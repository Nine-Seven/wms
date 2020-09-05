/**
 * 模块名称：供应商维护
 * 模块编码：1601
 * 创建：Huan
 */
 
var suppilerStore=Ext.create('cms.store.bdef.bdef_DefSupplierStore',{autoLoad:true});
Ext.define('cms.view.bdef.bdef_DefSupplierUI',{
	alias:'widget.bdef_DefSupplierUI',
	title:$i18n.title1601,//供应商维护
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
		'cms.view.common.commMenu2',
		'cms.view.common.commMenu5',
		'cms.view.common.remoteCombo',
		'cms.view.common.wms_DefFieldValCombo',
		'cms.view.common.bdef_DefOwnerCombo'
		],
	items:[
		{
			xtype : 'commMenuWidget2',
			id:'menu1601',
	    	region:'north'
		},{
		    xtype:'form',
		    id:'formSupplier1601',
		   /* region:'north',
		    right: 0,
			frame : true,*/
		    layout:'column',
			frame : true,
			region : 'north',
			width : '100%',
			height:'6%',
			items:[{
			    layout:{
					type : 'table',
					columns : 4
				},
				xtype:'container',
				defaults:{
					margin : '2 2 2 0',
					labelAlign : 'right',
					xtype:'textfield'
				},
		    items:[{
		   		xtype:'textfield',
				xtype:'bdef_DefOwnerCombo',
				fieldLabel:$i18n.owner_no,//货主编号
			    id:'cmbFormOwner1601',
				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore').load(),
				displayField : 'dropValue',
				valueField : 'value'
			},
		    {
				xtype : 'remoteCombo',
				fieldLabel : '供应商编号',				//供应商编号
				id:'supplierNo1601',
				store : Ext.create("cms.store.idata.idata_PoNoStore",{
					proxy:{
						type:'ajax',
						method:'post',
						url:'bdef_DefSupplierAction_queryBdefDefSupplierCombo.action',
						reader:{
							root:'rootList',
							totalProperty:'totalCount'
						}
					}
				})
		    },{
	 			fieldLabel :$i18n.status, //状态
	 			id:'statusText1601',
				xtype:'wms_DefFieldValCombo',
				store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
				 proxy:{
					type:'ajax',
					method:'post',
	 				url:'bdef_DefOwnerAction_getStatusList',
	 				reader:{
						root:'rootList',
						totalProperty:'totalCount'
						}
					}
			    }).load()
	        },{
				xtype:'button',
				id:'btnSearch1601',
			  	text: '查询'
			}
		    ]}
			]
		},
		{
			xtype:'grid',		
			region:'center',
			id:'supplierGrid1601',
			width:'100%',
			height:'100%',
			store:suppilerStore,
			columns:[{			
				xtype : 'rownumberer',
				width : 30
			}, {
				width : 90,
				text : $i18n.owner_no,
				dataIndex : 'ownerNo'
			},{
				width : 200,
				text : $i18n.owner_name,		//7-7添加
				dataIndex : 'ownerName'
			}, {
				width : 90,
				text : $i18n.supplier_no,
				dataIndex : 'supplierNo'
			}, {
				width : 90,
				text : $i18n.real_supplier_no,
				dataIndex : 'realSupplierNo'
			}, {
				width : 200,
				text : $i18n.supplier_name,
				dataIndex : 'supplierName'
			}, {
				width : 200,
				text : $i18n.real_supplier_name,
				dataIndex : 'realSupplierName'
			}, {
				width : 200,
				text : $i18n.supplier_alias,
				dataIndex : 'supplierAlias'
			}, {
				width : 80,
				text : $i18n.unload_flag,
				dataIndex : 'unloadflagText'
			},{
				width : 80,
				text : $i18n.status,
				dataIndex : 'statusText'
			}
			],dockedItems : [{
					xtype : 'pagingtoolbar',
					store : suppilerStore,
					dock : 'bottom',
					displayInfo : true
				}] 
		},{
		region:'south'
		}]
});