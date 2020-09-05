/**
 * 模块名称：权限管理 用户、角色等
 * 模块编码：1101
 * 创建：lich
 */
var storeBdef_DefWorker1101=Ext.create('cms.store.bdef.bdef_DefWorkerStore',{autoLoad:true});
var bset_Role_ListStore=Ext.create('cms.store.bset.bset_Role_ListStore',{
	autoLoad:true
});
Ext.define('cms.view.bdef.bdef_DefWorkerUI', {
	alias : 'widget.bdef_DefWorkerUI',
	title : $i18n.title1101,
	layout:'border',
	requires : ['cms.view.common.commMenu2',
	            'cms.view.common.commMenu3',
		'cms.view.common.commMenu5',
		'cms.view.common.wms_DefFieldValCombo'],
	extend : 'Ext.panel.Panel',
	items : [{
		xtype : 'tabpanel',
		id:'tabPId1101',
		region:'center',
		flex : 4,
		items : [ {
			title : $i18n.title1101tab1,
			itemId : 'userRoleTab',
			id:'userRoleTab',
			layout:'border',
			items : [ {
				xtype : 'grid',
				id : 'gridBdefDefWorker1101',
				title : $i18n.title1101tab1grid1,
				region:'center',
				store : storeBdef_DefWorker1101,
				columns : [ {
					xtype : 'rownumberer',
					width : 30
				},{
					width : 60,
					text : $i18n.worker_no,
					dataIndex : 'workerNo'
				},{
					width : 80,
					text : $i18n.worker_name,
					dataIndex : 'workerName'
				},{
					width : 80,
					text : $i18n.pwd,
					inputType:'password',
					renderer:function(){
						return '******';
					},
					dataIndex : 'pwd'
				},{
					width : 60,
					text : $i18n.sex,
					dataIndex : 'sex',
					renderer:function(v){
						if(v=='0')
							return '男';
						else 
							return '女';
					}
				},{
					width : 130,
					text : $i18n.tel,
					dataIndex : 'tel'
				},{
					width : 160,
					text : $i18n.address,
					dataIndex : 'address'
				},{
					width : 100,
					text : $i18n.title,
					dataIndex : 'title'
				},{
					width : 100,
					text : $i18n.worker_type,
					dataIndex : 'authorityTypeText'
				},{
					width : 80,
					text : $i18n.status,
					dataIndex : 'statusText'
				} ],
				dockedItems : [ {
					xtype : 'commMenuWidget2',
					id:'tab1Grid1Menu1101'
				}, {
					xtype : 'pagingtoolbar',
					store : storeBdef_DefWorker1101,
					dock : 'bottom',
					displayInfo : true
				} ]
			},{
				xtype:'grid',
				id:'bset_Role_ListGrid',
				title : $i18n.title1101tab1grid2,
				store:bset_Role_ListStore,
				width : '35%',
				region:'east',
				columns:[{
					xtype : 'rownumberer',
					width : 30
				}, {
					width : 80,
					text : $i18n.partno,
					dataIndex : 'roleId'
				},{
					width : 100,
					text : $i18n.partname,
					dataIndex : 'roleName'
				}],
				dockedItems : [ {
					xtype : 'commMenuWidget2',
					id:'tab1Grid2Menu1101'
				}, {
					xtype : 'pagingtoolbar',				
					store : bset_Role_ListStore,
					dock : 'bottom',
					displayInfo : true
				}]
			} ]
		},{//用户角色指定
			title : $i18n.title1101tab2,
			itemId : 'roleTab',
			id : 'roleTab',
			layout:'border',
			items : [ {
					xtype : 'toolbar',
					region:'north',
					items : [{
						text : $i18n.save,
						iconCls : 'save',
						id:'userRoleSave1101'
					},{
						text : '刷新',
						iconCls : 'refresh',
						id:'refresh1101'
					}]
					},{
					xtype:'grid',
					id:'bset_Role_ListGrid2',
					title : $i18n.title1101tab2grid1,
					store : bset_Role_ListStore,
					region:'west',
					width : '25%',
					columns:[{
						width : 60,
						text : $i18n.partno,//角色编码
						dataIndex : 'roleId'
						},{
						width : 100,
						text : $i18n.partname,//角色名称
						dataIndex : 'roleName'
						}]
				},{
					xtype:'container',
					defaults:{
						margin : '2 0 0 1',
						labelAlign : 'right',
						xtype:'textfield'
					},
			    items:[{
					xtype:'textfield',
					fieldLabel : '员工代码',//	员工代码
					id : 'workerNo1101',
					enableKeyEvents:true
				},{//已分配员工
					xtype : 'grid',
					id : 'bset_User_Role_Grid',
					title : $i18n.title1101tab2grid2,
					region:'center',
					store : Ext.create('cms.store.bset.bset_User_RoleStore',{autoLoad:false}),
					columns : [ {
						xtype : 'checkcolumn',
						dataIndex : 'flag',
						width : 50
						}, {
						width : 100,
						text : $i18n.worker_no,
						dataIndex : 'workerNo'
						},{
						width : 80,
						text : $i18n.worker_name,
						dataIndex : 'workerName'
						}]
				}]}/*{//已分配员工
					xtype : 'grid',
					id : 'bset_User_Role_Grid',
					title : $i18n.title1101tab2grid2,
					region:'center',
					store : Ext.create('cms.store.bset.bset_User_RoleStore',{autoLoad:false}),
					columns : [ {
						xtype : 'checkcolumn',
						dataIndex : 'flag',
						width : 50
						}, {
						width : 100,
						text : $i18n.worker_no,
						dataIndex : 'workerNo'
						},{
						width : 80,
						text : $i18n.worker_name,
						dataIndex : 'workerName'
						}]
				}*/]
		}, {//角色权限指定			
			title : $i18n.title1101tab3,
			layout:'border',
			items : [ {
					xtype : 'toolbar',
					region:'north',
					items : [ {
						text : '保存',
						iconCls : 'save',
						id:'roleModuleSave1101'
					}]
					},{//角色选择
					xtype : 'grid',
					id : 'roleListGrid3',
					title : $i18n.title1101tab3grid1,
					region:'west',
					width : '20%',
					store : bset_Role_ListStore,
					columns : [{
						width : 100,
						text : '角色ID',
						dataIndex : 'roleId'
						},{
						width : 100,
						text : '角色名称',
						dataIndex : 'roleName'
					}]
				},{//模块选择
					xtype : 'treepanel',
					title : $i18n.title1101tab3grid2,
					split : true,
					width : '40%',
					height : '100%',
					frame : true,
					id : 'moduleTree1101',
					store : Ext.create("cms.store.bset.bset_ModuleTreeStore",{autoLoad: true}),
					region:'west'
				},{//模块权限指定
					xtype : 'grid',
					id : 'moduleAuthGrid',
					title : $i18n.title1101tab3grid3,
					region:'center',
					store:Ext.create('cms.store.bset.bset_RightListStore',{autoLoad:false}),
					features: [{
			            ftype: 'grouping',
						groupHeaderTpl: '{name}',
						hideGroupedHeader: true,
			            enableGroupingMenu: false
		        	}],
			        columns: [{
		        	  	xtype: 'checkcolumn',
						width : 50,
						columnHeaderCheckbox: true,//必须定义如下store
						store: Ext.data.StoreManager.lookup('bset_RightListStore'),					   
						dataIndex:'flag'
		        	  },{
			            header: '功能',
			            width: 200,
			            dataIndex: 'rightName'
			        }]
				}]
		}]
	} ]
});





