/**
 * 模块名称：门卫登记
 * 模块编码：4B01
 * 创建：hcx
 */

var grid4B01_1=Ext.create('cms.store.idata.idata_Order_StatusStore',{
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'idata_GuardRegisteAction_getIdata_Order_StatusList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}/*,
	listeners:{  
		'load':function(th,records,successful,eOpts ){
		if(th.count()>0){
			var flag=_myAppGlobal.getController('cms.controller.idata.idata_GuardRegisteController').getFlag();
			if(flag=='1'){
				var serial_no=_myAppGlobal.getController('cms.controller.idata.idata_GuardRegisteController').getSerial_no();	
			    var owner_no=_myAppGlobal.getController('cms.controller.idata.idata_GuardRegisteController').getOwner_no();	 				 
			    for(var i=0; i<th.count();i++){
						 var data = Ext.getCmp('grid4B01_1').getStore().getAt(i);
						 if(data.get('orderSerial')== serial_no && data.get('ownerNo')== owner_no){
							 Ext.getCmp('grid4B01_1').getSelectionModel().select(i);
							 return;
						 }		
					 }
			}else{
				Ext.getCmp('grid4B01_1').getSelectionModel().select(0);
			}
			
			}
		}
	}*/
});
var grid4B01_2=Ext.create('cms.store.idata.idata_GuardRegisteStore',{
	listeners:{  
		'load':function(th,records,successful,eOpts ){
		if(th.count()>0){
			Ext.getCmp('grid4B01_2').getSelectionModel().select(0);
			}
		}
	}
});
var grid4B01_3=Ext.create('cms.store.idata.idata_Order_SheetStore',{
	autoLoad:false,
	proxy:{
		type:'ajax',
		method:'post',
		url:'idata_GuardRegisteAction_getIdata_Order_SheetList.action',
		reader:{
			root:'rootList',
			totalProperty:'totalCount'
		}
	}
});

Ext.define('cms.view.idata.idata_GuardRegisteUI',{
	alias:'widget.idata_GuardRegisteUI',
	title:$i18n.title4B01,//门卫登记
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu5',
	          'cms.view.common.commMenu6',
	          'cms.view.common.wms_DefFieldValCombo',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.remoteCombo',
	          'cms.view.common.bdef_DefSupplierCombo',
	          'cms.view.common.bdef_DefWorkerCombo',
	          'cms.view.common.bdef_DefDockCombo',
	          'cms.view.common.cdef_DefCellCombo',
	          'cms.view.common.bdef_PackingQtyCombo',
	          ],
    items:[
    {
 	   xtype : 'toolbar',
		region:'north',
		items : [{
			    text : $i18n.registe,//报到
			    iconCls : 'save',
			    id:'regist4B01'
		    },
		    {
				text : $i18n.titleupdate,//修改
				iconCls : 'edit',
				id:'edit4B01'
			},
			{
			    text : $i18n.refresh,//刷新
			    iconCls : 'refresh',
			    id:'refresh4B01'
		    }]
    },
	{
		xtype : 'form',
		region : 'north',
		layout:'column',
		border:false,
		width:'100%',
		frame : true,
		defaults : {
			labelWidth : 90,
			margin : '2 2 2 2',
			labelAlign : 'right'
		},
		items:[
		{
			xtype : 'datefield',
			fieldLabel : $i18n.appoint_date,//预约日期
			id : 'appoint_date4B01',
			format : 'Y-m-d',
			beforeLabelTextTpl : required
		},
		{
	    	xtype : 'combo',
			fieldLabel : $i18n.owner_no,// 委托业主
			id:'owner4B01',
			displayField: 'dropValue',
			valueField: 'value',
			store:Ext.create("cms.store.bdef.bdef_DefOwnerComboStore",{
				proxy:{
					type:'ajax',
					method:'post',
					url:'idata_GuardRegisteAction_queryOwnerCombo.action',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					}
				},
				listeners:{  
					'load':function(th,records,successful,eOpts ){
						if(th.count()>0){
							Ext.getCmp('owner4B01').setValue(th.getAt(0).data.value);
							_myAppGlobal.getController('cms.controller.idata.idata_GuardRegisteController').owner4B01Select();
						}else{
							Ext.getCmp('owner4B01').setValue('');
							Ext.getCmp('grid4B01_1').getStore().removeAll();
						}
					}
				}
		   	}),
	   		beforeLabelTextTpl : required	   
		},{

		 	xtype:'combo',
			fieldLabel : $i18n.suppliers,//供应商
		 	id:'suppliers4B01_1',
	        displayField: 'dropValue',
			valueField: 'value',
			store:Ext.create("cms.store.common.comboStore",
			{
				proxy:{
					type:'ajax',
					method:'post',
					url:'idata_GuardRegisteAction_querySupplierNoCombo.action',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					}
				},listeners:{  
					'load':function(th,records,successful,eOpts ){
						if(th.count()>0){
							Ext.getCmp('suppliers4B01_1').setValue(th.getAt(0).data.value);
							_myAppGlobal.getController('cms.controller.idata.idata_GuardRegisteController').suppliers4B01_1Select();
						}else{
							Ext.getCmp('suppliers4B01_1').setValue('');
							Ext.getCmp('grid4B01_1').getStore().removeAll();
						}
					}
				}
			}),
	   		beforeLabelTextTpl : required	   
		},{
		 	xtype:'combo',
			fieldLabel : $i18n.acdata_status,//状态
		 	id:'status4B01_1',
	        displayField: 'dropValue',
			valueField: 'value',
			store:Ext.create("cms.store.common.comboStore",
			{
				proxy:{
					type:'ajax',
					method:'post',
					url:'idata_GuardRegisteAction_queryStatusCombo.action',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					}
				},listeners:{  
					'load':function(th,records,successful,eOpts ){
						if(th.count()>0){
							Ext.getCmp('status4B01_1').setValue(th.getAt(0).data.value);
							_myAppGlobal.getController('cms.controller.idata.idata_GuardRegisteController').status4B01_1Select();
						}else{
							Ext.getCmp('status4B01_1').setValue('');
							Ext.getCmp('grid4B01_1').getStore().removeAll();
						}
					}
				}
			}),
	   		beforeLabelTextTpl : required	   
		},{
			xtype : 'remoteCombo',
			fieldLabel : $i18n.order_serial,//预约流水号
			id:'orderSerial4B01',
			store : Ext.create("cms.store.idata.idata_PoNoStore",{
				proxy:{
					type:'ajax',
					method:'post',
					url:'idata_GuardRegisteAction_getOrderSerialList.action',
					reader:{
						root:'rootList',
						totalProperty:'totalCount'
					}
				}
			})
		}
		]
	},
	{
		xtype:'panel',
   		region:'north',
   		layout:'border',
   		height:250,
   		items:[
        {
	    	xtype : 'grid',
			region : 'center',//'west',
			id:'grid4B01_1',
			store:grid4B01_1,
			multiSelect: true,  
    	    selModel: {  
            	selType:'checkboxmodel'  
    	    },
			columns : [ 
			{
				xtype : 'rownumberer',
				width : 30
			}, 
			{
    	  		width:140,
    	 		 text:$i18n.appoint_date,//预约日期
    	  		dataIndex:'requestDateText'
      		},
      		{
    	  		width:140,
   	 		    text:$i18n.owner_no,//货主
   	  		    dataIndex:'ownerNo'
     		},		
			{
				width : 140,
				text : $i18n.dock,//码头
				dataIndex:'dockText'
			}, 
			{
    	  		width:100,
    	  		text:$i18n.start_time,//开始时间
    	  		dataIndex:'startTime'
      		},
      		{
    	 		 width:100,
    	  		text:$i18n.endtime,//结束时间
    	  		dataIndex:'endTime'
            },{
				width : 120,
				text : $i18n.order_serial,
				dataIndex : 'orderSerial'
				},
			{
				width:250,
    	  		text:$i18n.suppliers,//供应商
    	  		dataIndex:'suppliersText'
			}, 
			{
				width : 100,
				text : $i18n.status,//状态
				dataIndex:'statusText'
			}
			],
		    dockedItems : 
		    [
		    {
		        xtype : 'pagingtoolbar',
		        store : grid4B01_1,
		        dock : 'bottom',
		        displayInfo : true
		    }
		    ]
        }
        ]
	},
	{
		xtype:'panel',
		 region:'center',
		title:$i18n.registeState,//报到状况
		layout:'border',
		items: [{
	        xtype: 'grid',
	        region: 'center',
	        split: true,
	        store:grid4B01_2,
	       
	        id: 'grid4B01_2',
	        columns : [{
				width : 80,
				text : $i18n.order_serial,
				dataIndex : 'orderSerial'
				},{
				width : 150,
				text : $i18n.registe_date,//报到时间
				dataIndex : 'registeDateText' 
				},{
				width:80,
				text:$i18n.car_plate,//车牌号
				dataIndex:'carNo'
				},{
				width : 100,
				text : $i18n.driver_no,//驾驶员
				dataIndex : 'driverNo'
				},{
				width : 135,
				text : $i18n.driver_licevse_no,//驾驶证号
				dataIndex : 'driverLicenseNo'
				},{
				width : 90,
				text : $i18n.guest_card_number,//发放来宾证数
				dataIndex : 'guestCardNumber'
				},{
					width : 90,
					text : $i18n.dock_no3,//码头号
					dataIndex : 'dockText'
				},{
					width : 150,
				    hidden:true,
					text : $i18n.begin_unload_date,//开始卸货时间
					dataIndex : 'beginUnloadDateText'
				},{
					width : 150,
				    hidden:true,
					text : $i18n.end_unload_date,//卸货结束时间
					dataIndex : 'endUnloadDateText'
				},{
					width : 150,
					text : $i18n.leave_date,//离开时间
					dataIndex : 'leaveDateText'
				}]
		},{
	        xtype: 'grid',
	        region: 'east',
	        width:350,
	        split: true,
	        margins: '0 0 0 5',
	        store:grid4B01_3,
	        id: 'grid4B01_3',
	        columns : [ {
				    xtype : 'rownumberer',
				    width : 30
				},{
				    width : 140,
				    text : $i18n.import_no,
				    dataIndex : 'importNo'
				},{
				    width : 120,
				    text : $i18n.po_no1,
				    dataIndex : 'poNo'
				},{
					width : 100,
					text : $i18n.status,
					dataIndex : 'statusText'
				}]
		}]
	}
	]
});
