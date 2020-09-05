var DeviceDivideGroupStore=Ext.create('cms.store.bdef.DeviceDivideGroupStore',{
	autoLoad:true,
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('DeviceDivideGroup1S01').getStore().count()>0){
			   Ext.getCmp('DeviceDivideGroup1S01').getSelectionModel().select(0);
			}
		}
	}
});

var device_DivideMStore=Ext.create('cms.store.bdef.DeviceDivideMStore',{
	autoLoad:true,
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('deviceDivideMGrid1S01').getStore().count()>0){
				Ext.getCmp('deviceDivideMGrid1S01').getSelectionModel().select(0);
			}
		}
	}
});

var device_DivideDStore=Ext.create('cms.store.bdef.DeviceDivideDStore',{
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(Ext.getCmp('deviceDivideDGrid1S01').getStore().count()>0){
				Ext.getCmp('deviceDivideDGrid1S01').getSelectionModel().select(0);
			}
		}
	}
});

Ext.define('cms.view.bdef.bdef_DeviceDivideUI',{
	alias:'widget.bdef_DeviceDivideUI',
	title:'设备管理',
	layout:'border',
	requires:['cms.view.common.commMenu2','cms.view.common.commMenu5',
	'cms.view.common.wms_DefFieldValCombo'],
	extend:'Ext.panel.Panel',
	items:[{
			xtype : 'commMenuWidget2',
			region:'north',
			id:'menu1S01'
		},{
		xtype:'tabpanel',
		id:'tab1S01',
		region:'center',
		items : [{//设备组
				xtype : 'grid',
				id : 'DeviceDivideGroup1S01',
				title : '设备组',
				store : DeviceDivideGroupStore,
				columns : [ {
					xtype : 'rownumberer',
					width : 30
				},{
					width : 120,
					text : '设备组号',
					dataIndex : 'deviceGroupNo'
				},{
					width : 120,
					text : '设备名称',
					dataIndex : 'deviceGroupName'
				},{
					width : 120,
					text : '设备组类型',
					dataIndex : 'useTypeText'
				},{
					width : 120,
					text : '默认组',
					dataIndex : 'defaultFlagText'
				},{
					width : 100,
					text : '状态',
					dataIndex : 'statusText'
				}],
				dockedItems : [{
					xtype :'pagingtoolbar',
					store : DeviceDivideGroupStore,
					dock : 'bottom',
					displayInfo : true
				}]
			},{//设备
				xtype : 'panel',
				id : 'deviceDivideM1S01',
				title : '设备',
				layout:'border',
			    items: [{
			        xtype:'form',
			       	region:'north',
					frame : true,
			        layout: {
				        type: 'table',
				        columns: 3
				    },
					defaults : {
						xtype : 'textfield',
						margin : '3 3 3 3',
						labelAlign:'right',
						labelWidth : 90
					},
					items:[{
						xtype:'combo',
						fieldLabel: '设备组',
						id : 'DeviceDivideGroupCombo1S01',
						store:Ext.create("cms.store.common.comboStore",{
								proxy:{
									type:'ajax',
									method:'post',
									url : 'Divice_DivideAction_getDeviceDivideGroupCombo.action',
							        reader: {
							    		type:'json',
							            root: 'rootList',    
							            totalProperty: 'totalCount'
							        }
							    }
						}).load(),
						displayField : 'dropValue',
     				    valueField : 'value'
					}]
			    
			    },{
			    xtype : 'grid',
		    	region:'center',
				id : 'deviceDivideMGrid1S01',
				store : device_DivideMStore,
				columns : [ {
					xtype : 'rownumberer',
					width : 30
				},{
					width : 80,
					text : '设备组',
					dataIndex : 'deviceGroupNo'
				},{
					width : 80,
					text : '设备编码',
					dataIndex : 'deviceNo'
				},{
					width : 160,
					text : '设备名称',
					dataIndex : 'deviceName'
				},{
					width : 80,
					text : '优先级',
					dataIndex : 'grade'
				},{
					width : 80,
					text : '设备类型',
					dataIndex : 'deviceTypeText'
				},{
					width : 80,
					text : '作业类型',
					dataIndex : 'operateTypeText'
				},{
					width : 80,
					text : '最大箱数',
					dataIndex : 'maxQty'
				},{
					width : 80,
					text : '最大品项数',
					dataIndex : 'boxItems'
				},{
					width : 80,
					text : '使用次数',
					dataIndex : 'useTimes'
				},{
					width : 80,
					text : '最大客户数',
					dataIndex : 'custQty'
				},{
					width : 80,
					text : 'astockNo',
					dataIndex : 'AStockNo',
					hidden:true
				},{
					width : 80,
					text : '状态',
					dataIndex : 'statusText'
				}],
				dockedItems : [{
					xtype : 'pagingtoolbar',
					store : device_DivideMStore,
					dock : 'bottom',
					displayInfo : true
				}]
			  }]
			},
			{//设备格子号
			xtype:'panel',
			title: '设备格子号',
			id:'deviceDivideD1S01',
			layout:'border',
		    items: [{
		        xtype:'form',
		       	region:'north',
				frame : true,
		        layout: {
			        type: 'table',
			        columns: 3
			    },
				defaults : {
					xtype : 'textfield',
					margin : '3 3 3 3',
					labelAlign:'right',
					labelWidth : 90
				},
				items:[{
					xtype:'combo',
					fieldLabel: '设备组',
					id : 'DeviceDivideGroupDCombo1S01',
					store:Ext.create("cms.store.common.comboStore",{
						proxy:{
							type:'ajax',
							method:'post',
							url : 'Divice_DivideAction_getDeviceDivideGroupCombo.action',
					        reader: {
					    		type:'json',
					            root: 'rootList',    
					            totalProperty: 'totalCount'
					        } 
					    },
						listeners:{  
							'load':function(th,records,successful,eOpts ){
								if(th.count()>0){
									Ext.getCmp('DeviceDivideGroupDCombo1S01').setValue(th.getAt(0).data.value);
								}
							}
						}
					}),
					displayField : 'dropValue',
 				    valueField : 'value'
				},{
					xtype:'combo',
					fieldLabel: '设备',
					id : 'deviceNoDCombo1S01',
					store:Ext.create('cms.store.common.comboStore',{
						 proxy:{
								type:'ajax',
								method:'post',
								url:'Divice_DivideAction_getDeviceDivideMCombo.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								}
						},
						listeners:{  
							'load':function(th,records,successful,eOpts ){
								if(th.count()>0){
									Ext.getCmp('deviceNoDCombo1S01').setValue(th.getAt(0).data.value);
								}
							}
						}
					}),
					displayField : 'dropValue',
 				    valueField : 'value'
				}]
		    
		    },{
		    xtype : 'grid',
	    	region:'center',
			id : 'deviceDivideDGrid1S01',
			store : device_DivideDStore,
		    plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
		    			clicksToEdit : 1,
		    			onSpecialKey:function(ed,field,e){
						commEnterGridStatEdit(this.grid,true,'cms.controller.bdef.bdef_DeviceDivideController',e.getKey());
		    			}
	    	    	})],
			columns : [ {
				xtype : 'rownumberer',
				width : 30
			},{
				width : 60,
				text : '设备组',
				dataIndex : 'deviceGroupNo'
			},{
				width : 60,
				text : '设备编码',
				dataIndex : 'deviceNo'
			},{
				width : 150,
				text : '设备名称',
				dataIndex : 'deviceName'
			},{
				width : 60,
				text : '位',
				dataIndex : 'bayX'			
			},{
				width : 60,
				text : '层',
				dataIndex : 'stockY'
			},{
				width : 80,
				text : '储位',
				dataIndex : 'deviceCellNo'	
			},{
				width : 140,
				text : '混供应商标识',
				dataIndex : 'mixSupplierText'	
			},{
				width : 100,
				text : '混属性标识',
				dataIndex : 'mixFlagText'
			},{
				width : 60,
				text : '最大板数',
				dataIndex : 'maxCase'
			},{
				width : 60,
				text : '最大材积',
				dataIndex : 'maxVolume'
			},{
				width : 60,
				text : '最大重量',
				dataIndex : 'maxWeight'
			},{
				width : 60,
				text : '最大箱数',
				dataIndex : 'maxQty'
			},{
				width : 60,
				text : '拣货顺序',
				dataIndex : 'pickOrder',
				cls : 'notnull',
					field : {							
	    	        id : 'pickOrder1S01',
	    	        xtype:'textfield',
	    	        allowBlank :false,
	    	        editable:true
	    	    }     	    
			},{
				width : 60,
				text : '状态',
				dataIndex : 'statusText'
			}],
			dockedItems : [{
				xtype : 'pagingtoolbar',
				store : device_DivideDStore,
				dock : 'bottom',
				displayInfo : true
			}]
		  }]
		}]
	}]
});