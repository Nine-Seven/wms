/**
 * 模块名称：集货作业
 * 模块编码：3931
 * 创建：czh
 */
var odata_PackageMStore=Ext.create('cms.store.odata.odata_PackageMStore',{
	autoLoad:true,
	listeners:{  
		'load':function(th,records,successful,eOpts ){
			if(th.count()>0){
				poNo=_myAppGlobal.getController('cms.controller.odata.odata_PackageIntoStockController').getPoNo3931();
				var rowCount = Ext.getCmp('grid_01_3931').getStore().getCount();
				if(!Ext.isEmpty(poNo)){
					for(var i=0;i<rowCount;i++)
		            {
		                if(Ext.getCmp('grid_01_3931').getStore().getAt(i).get('poNo') == poNo 
		                		&& Ext.getCmp('grid_01_3931').getStore().getAt(i).get('statusText') !='出库完成')
		                {   
		                    //选中默认行
		                	Ext.getCmp('grid_01_3931').getSelectionModel().select(i);
		                    return;
		                }
		            }
					Ext.getCmp('grid_01_3931').getSelectionModel().select(0);
				}else{
					Ext.getCmp('grid_01_3931').getSelectionModel().select(0);
				}
				
			}
		}
	}
});
var odata_PackageDStore=Ext.create('cms.store.odata.odata_PackageDStore',{
	autoLoad:false,
	listeners:{  
		'load':function(th,records,successful,eOpts ){
				
			}
	}
});
Ext.define('cms.view.odata.odata_PackageIntoStockUI',{
	alias:'widget.odata_PackageIntoStockUI',
	title:$i18n.package_into_stock,//集货作业
	width:'100%',
	layout:'border',
	extend:'Ext.panel.Panel',
	requires:[
	          'cms.view.common.commMenu10',
	          'cms.view.common.wms_DefFieldValCombo',
	          'cms.view.common.bdef_DefOwnerCombo',
	          'cms.view.common.remoteCombo',
	          'cms.view.common.bdef_DefWorkerCombo'
	          ],
    items:[
    {
        xtype:'commMenuWidget10',
	    id:'menu3931',
	    region:'north'
	},,
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
			xtype:'bdef_DefOwnerCombo',
			fieldLabel:$i18n.owner_no,//货主编号
		    allowBlank:true,
		    id:'cmbOwnerNo3931',
		    displayField: 'dropValue',
			valueField: 'value',
		    beforeLabelTextTpl:required,
		    store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',
					 {
						 proxy:{
								type:'ajax',
								method:'post',
								url:'odata_PackageAction_getOwnerCombo.action',
								reader:{
									root:'rootList',
									totalProperty:'totalCount'
								}
							},	 
						listeners:{  
								'load':function(th,records,successful,eOpts ){
									if(th.count()>0){
										Ext.getCmp('cmbOwnerNo3931').setValue(th.getAt(0).data.value);
										_myAppGlobal.getController('cms.controller.odata.odata_PackageIntoStockController').selectByOwnerNo();
									}
								}
							}
						}).load()	        
		},{
 			fieldLabel :$i18n.status, //状态
 			id:'statusText3931',
			xtype:'wms_DefFieldValCombo',
			store:Ext.create('cms.store.bdef.bdef_DefOwnerComboStore',{
			 proxy:{
				type:'ajax',
				method:'post',
 				url:'odata_PackageAction_getStatusList',
 				reader:{
					root:'rootList',
					totalProperty:'totalCount'
					}
				}
		    }).load()
        },{

	 		xtype : 'remoteCombo',
	 		fieldLabel : $i18n.package_no,// 提单号(模糊查询)
	 		id:'packageNo3931',
	 		store : Ext.create("cms.store.idata.idata_PoNoStore",{
	 			proxy:{
	 				type:'ajax',
	 				method:'post',
	 				url:'odata_PackageAction_getPackageInfo.action',
	 				reader:{
	 					root:'rootList',
	 					totalProperty:'totalCount'
	 				}
	 			}
	 		})
	 	
	 		
	     }
		]
	},{
		xtype:'panel',
   		region:'north',
   		layout:'border',
   		height:220,
   		items:[
        {
	    	xtype : 'grid',
			region : 'center',//'west',
			id:'grid_01_3931',
			store:odata_PackageMStore,
			columns : [ 
			{
				xtype : 'rownumberer',
				width : 30
			}, 		
			{
				width : 140,
				text : $i18n.package_no,//包裹提单号
				dataIndex:'poNo'
			}, 
			{
				width : 140,
				text : $i18n.package_type,//货物类型
				dataIndex:'poType'
			},
			{
				width : 140,
				text : $i18n.exp_date1,//出货日期
				dataIndex:'expDate'
			}, 
			{
				width : 140,
				text : $i18n.status,//状态
				dataIndex:'statusText'
			}, 
		
			{
				width : 180,
				text : $i18n.remark,//备注
				dataIndex:'remark'
			}],
        dockedItems : [{
			xtype : 'pagingtoolbar',
			dock : 'bottom',
			store:odata_PackageMStore,
			displayInfo : true
		}]
        },
        {
        	xtype : 'form',
        	id:'form_01_3931',
			layout:
			{
				type : 'table',
				columns : 1
			},
			region : 'east',
			frame : true,
			width : '25%',
			items:[
	        {
		    	margin:'0 0 0 0',
			    xtype:'fieldset',  
				defaults:
				{
				  	xtype:'textfield',
				  	labelAlign:'right'
				},
			    items:[				
				{
					xtype : 'bdef_DefWorkerCombo',
					id:'cmbOperateWorker3931',
					fieldLabel : $i18n.operate_worker,//入库/出库人员
					store:Ext.create('cms.store.bdef.bdef_DefworkerComboStore').load(),
					beforeLabelTextTpl : required
				},
				{
					id:'sourceExpNo3931',
					fieldLabel : $i18n.sourceexp_no,//订单号
				}
				]

	        },
			{
		        margin:'5 0 0 0',
				xtype:'fieldset',  
				autoHeight:true,
				items:[{
			        xtype: 'radiogroup',
			        margin:'10 10 10 20',
			        id:'operateType3931',
			        columns: 3,
			        items: [
						{ 
							boxLabel: $i18n.into_stock, 
						    name: 'movetype', 
						    inputValue: '1' ,
						    checked: true,
						    width:80
						},
						{ 
							boxLabel: $i18n.out_stock, 
							name: 'movetype',
							inputValue: '2',
							width:80
						},
			        ],
			        beforeLabelTextTpl : required
			    },
			    
				{
					layout:
					{
					type : 'table',
					columns : 2
					},
					xtype:'container',
					margin:'10 0 0 15',
					defaults:
					{
						margin : '2 2 2 2',
						labelAlign : 'right'
					},
					items:[
					{
						xtype : 'checkboxfield',
						id : 'printFaceSheet3931',
						margin:'0 0 15 10',
						inputValue: 'WAY',
						boxLabel : '打印面单',//打印面单
					},
					{
						xtype : 'checkboxfield',
						id : 'printBuilt3931',
						margin:'0 0 15 13',
						inputValue: 'BOX',
						boxLabel : '打印内置清单'//打印内置清单
					},
				    {
			    	    xtype : 'button',
			    	    margin:'0 0 0 10',
			    	    hidden:false,
			    	    id:'btnSave3931',
			    	    text : '确认',//确认
					},
					{
			    	    xtype : 'button',
			    	    margin:'0 0 0 13',
			    	    id:'btnPrint3931',
			    	    text : '打印',//打印
					}
					]
				}
				]
			}
	        ]
		}
        ]
	},
	{
		xtype : 'tabpanel',
	    region:'center',
	    id:'tabPid3931',
	    flex : 4,
	    items : 
    	[
		{
			xtype : 'grid',
			title:$i18n.package_d,//包裹明细
			id:'grid_02_3931',
			multiSelect: true,  
    	    selModel: {  
            	selType:'checkboxmodel'  
    	    },
			store:odata_PackageDStore,
			selType : 'cellmodel',
			plugins : [Ext.create('Ext.grid.plugin.CellEditing',{
				clicksToEdit : 1,
				onSpecialKey:function(ed,field,e)
				{
					commEnterGridStatEdit(this.grid,false,'',e.getKey());
				}
			})],
			columns : [
		    {
				xtype : 'rownumberer',
				width : 30
			},
			{
				width : 105,
				text : $i18n.sourceexp_no,//订单号
				dataIndex:'sourceexpNo'
			},
			{
				 width:105,
 			    text : $i18n.deliver_no,//快递单号
 			    dataIndex:'shipperDeliverNo'		
			},{
				width : 105,
				text : $i18n.owner_no,//货主
				dataIndex:'ownerName'
			},
			{
				width : 105,
				text : $i18n.sanpl_no,//承运商
				dataIndex:'shipperName'
			},
			{
				width : 105,
				text : $i18n.acdata_owner,//发货人
				dataIndex:'sendName'
			},
			{
				width : 160,
				text : $i18n.send_address,//发货地址
				dataIndex:'sendAddress'
			},
			 {
		    	width : 105,
				text : $i18n.acdata_cust,//收货人
				dataIndex:'contactorName'
		    },
		    {
				width : 160,
				text : $i18n.take_address,//收货地址
				dataIndex:'custAddress'
			},
			{
				width : 140,
				text : $i18n.status,//状态
				dataIndex:'statusText'
			}		
			],dockedItems : [{
				xtype : 'pagingtoolbar',
				dock : 'bottom',
				store:odata_PackageDStore,
				displayInfo : true
			}]
		}]
	},
	{
		region:'south'
	}
	]
});
