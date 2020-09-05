Ext.define('cms.view.idata.windows.idata_GuardRegisteAddOrEditWindow',{
 	extend:'Ext.window.Window',
 	alias:'widget.idata_GuardRegisteAddOrEditWindow',
 	layout:'border',
    width : 700,
	height : 430,
 	modal:true,
 	id:'idata_GuardRegisteAddOrEditWindow',
 	items:[{
				xtype : 'form',
				id : 'formRodata_OutstockM7501',
				region : 'north',
				layout:'column',
				frame : true,
				items : [ {
					layout : {
					type : 'table',
					columns : 2
					},
					xtype : 'container',
					defaults : {
						labelWidth : 90,
						margin : '2 5 5 2',
						labelAlign : 'right',
						width : 300
					},
					items : [ {
		        	 xtype:'combo',
	            	 fieldLabel:$i18n.suppliers,//供应商
	            	 queryMode:'local',
		             allowBlank:true,
		             id:'cmbSuppliers4B01',
		             colspan:2,
		             width : 610,
		             displayField: 'dropValue',
					 valueField: 'value',
		             beforeLabelTextTpl:required
		         },{
						xtype : 'textfield',
						fieldLabel : $i18n.registe_date,//报到时间
						id : 'registeDate4B01',
			             beforeLabelTextTpl:required
				},{
						xtype : 'textfield',
						fieldLabel : $i18n.car_plate,// 车牌号
						id : 'carNo4B01',
			             beforeLabelTextTpl:required
				},{
						xtype : 'textfield',
						fieldLabel : $i18n.driver_no,//驾驶员
						id : 'driverNo4B01',
			             beforeLabelTextTpl:required
				},{
						xtype : 'textfield',
						fieldLabel : $i18n.driver_licevse_no,//驾驶证号
						id : 'driverLicevseNo4B01'
				},{
						xtype : 'numberfield',
						fieldLabel : $i18n.guest_card_number,//发放来宾证数
						minValue:0,
						id : 'guestCardNumber4B01'
				},{
				 	xtype:'combo',
					fieldLabel : $i18n.dock,//码头
				 	id:'dockNo4B01_2',
				 	hidden:true,
	    	        displayField: 'dropValue',
					valueField: 'value',
					store:Ext.create("cms.store.common.comboStore",
					{
						proxy:{
							type:'ajax',
							method:'post',
							url:'idata_Order_TimeAction_getDockNoCombo.action',
							reader:{
								root:'rootList',
								totalProperty:'totalCount'
							}
						}
	   				})
				},{
					xtype : 'textfield',
				    hidden:true,
					fieldLabel : $i18n.begin_unload_date,//开始卸货时间
					id : 'beginUnloadDate4B01'
				},{
						xtype : 'textfield',
					    hidden:true,
						fieldLabel : $i18n.end_unload_date,//卸货结束时间
						id : 'endUnloadDate4B01',
				},{
						xtype : 'textfield',
						fieldLabel : $i18n.leave_date,//离开时间
						id : 'leaveDate4B01'
				},{

				    	xtype:'wms_DefFieldValCombo',
				        fieldLabel : $i18n.status,//状态
				        id : 'status4B01_2',
				        editable:false,
				        store:Ext.create("cms.store.common.comboStore").load(
				        {
				        	params:{str:"IDATA_GUARD_RGISTE,STATUS"}
				        }),
				        beforeLabelTextTpl : required
					} ]
				} ]
			},
			{
				xtype : 'grid',
				id : 'grid4B01_4',
				store:Ext.create('cms.store.idata.idata_Order_SheetStore',
	 	    			{
	 	    		      proxy:{
	 					         type:'ajax',
	 					         method:'post',
	 					         url:'idata_GuardRegisteAction_getIdata_Order_SheetList.action',
							     reader:{
	 						     root:'rootList',
	 						     totalProperty:'totalCount'
						     }
						   }
	 	    			}
	 	    		),
				region : 'center',
				columns : [{
					xtype : 'rownumberer',
					width : 30
				},{
				    width : 150,
				    text : $i18n.import_no,
				    dataIndex : 'importNo'
				},{
				    width : 150,
				    text : $i18n.po_no1,
				    dataIndex : 'poNo'
				},{
					width : 140,
					text : $i18n.dock,//码头
					dataIndex:'dockText'
				},{
					width : 120,
					text : $i18n.order_serial,
					dataIndex : 'orderSerial'
					} ],
			    	dockedItems : [{
					xtype:'toolbar',
		    		region:'north',
		    		items : [{
		    			text : '删除',
		 	    	    id : 'detailDelete',
		 	    	    iconCls : 'delete'
				    }]
			    }]
			},{
 			region:'south',
			xtype:'commMenuWidget5',
			border:0,
			id:'menuWidget1601'
 	}]
});